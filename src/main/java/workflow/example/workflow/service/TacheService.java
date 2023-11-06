package workflow.example.workflow.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.entity.GroupeUser;
import reactor.core.publisher.Mono;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.entity.User;
import workflow.example.workflow.listener.TacheListener;
import workflow.example.workflow.repository.TacheAtraiteRepository;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TacheService {
    final
    TacheRepository tacheRepository;

    private final TacheAtraiteRepository tacheAtraiteRepository;

    private final TacheListener tacheListener;

    private final WebClient webClient;

    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<Object> addTache(Tache tache) {
        Long id = tache.getId();
        if (id != null && tacheRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tache with id " + id + " already exists");
        }

        tache.setCreationDate(new Date());
        tache.setStatut("non traité");
        tache.setApprobation("en attente");

        tacheRepository.save(tache);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Tache", tache);
        responseBody.put("message", "Tache successfully created!");

        return ResponseEntity.ok().body(responseBody);

    }

    @Transactional
    public ResponseEntity<Object> updateTache(Long id, Tache tache) {
        tacheRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setName(tache.getName());
                    a.setDescription(tache.getDescription());
                    a.setCreationDate(tache.getCreationDate());
                    a.setStartDate(tache.getStartDate());
                    a.setEndDate(tache.getEndDate());
                    a.setStatut(tache.getStatut());
                    a.setAction(tache.getAction());
                    a.setApprobation(tache.getApprobation());
                    tacheRepository.save(a);

                    List<TacheAtraiter> tacheAtraiters = a.getTacheAtraiters();
                    for (TacheAtraiter tacheAtraiter : tacheAtraiters) {
                        tacheAtraiter.setName(a.getName());
                        tacheAtraiter.setDescription(a.getDescription());
                        tacheAtraiter.setCreationDate(a.getCreationDate());
                        tacheAtraiter.setStartDate(a.getStartDate());
                        tacheAtraiter.setEndDate(a.getEndDate());
                        tacheAtraiter.setStatut(a.getStatut());
                        tacheAtraiter.setAction(a.getAction());
                        tacheAtraiter.setApprobation(a.getApprobation());
                    }
                    tacheAtraiteRepository.saveAll(tacheAtraiters);

                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found !");
                });

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Tache", tache);
        responseBody.put("message", "Tache successfully updated!");

        return ResponseEntity.ok().body(responseBody);

    }

    @Transactional
    public void deleteTacheById(Long id) {
        Tache tache = tacheRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found !"));
        tacheRepository.delete(tache);
    }

    public List<Tache> getAlltaches() {
        return tacheRepository.findAll();
    }

    public Tache findtacheById(Long id) {
        Optional<Tache> tacheOptional = tacheRepository.findById(id);
        if (tacheOptional.isPresent()) {
            return tacheOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found");
        }
    }

    public List<Tache> findByWorkflowId(Long id) {
        return tacheRepository.findByWorkflowId(id);
    }

    @Transactional
    public void assignerTacheAUtilisateurs(Long tacheId, List<Long> userIds, Long workflowId) {
        Tache task = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        List<User> usersToAdd = new ArrayList<>();

        for (Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + userId));

            if (!user.getTaches().isEmpty() && user.getTaches().contains(task)) {
                throw new RuntimeException("L'utilisateur est déjà assigné à cette tâche");
            }

            usersToAdd.add(user);
        }

        task.getUserList().addAll(usersToAdd);
        tacheRepository.save(task);

        for (User user : usersToAdd) {
            TacheAtraiter atraiter = TacheAtraiter.builder()
                    .name(task.getName())
                    .description(task.getDescription())
                    .approbation(task.getApprobation())
                    .action(task.getAction())
                    .creationDate(task.getCreationDate())
                    .endDate(task.getEndDate())
                    .statut("non traité")
                    .workflowId(task.getWorkflowTache().getId())
                    .responsable(user.getId())
                    .build();

            tacheAtraiteRepository.save(atraiter);
        }
    }

    @Transactional
    public void desassignerTacheAUtilisateur(Long tacheId, Long userId) {
        Tache task = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!task.getUserList().contains(user)) {
            throw new RuntimeException("L'utilisateur n'est pas assigné à cette tâche.");
        }

        task.getUserList().remove(user);
        tacheRepository.save(task);
    }

    public List<User> getUtilisateursDeTache(long tacheId) {
        Tache tache = tacheRepository.findById(tacheId);
        return tache.getUserList();
    }

    public List<Tache> getTasksByUser(Long userId) {
        return tacheRepository.findByUserId(userId);
    }

    public List<Tache> findByUserIdtraite(Long userId) {
        return tacheRepository.findByUserIdtraite(userId);
    }

    @Transactional
    public void assignUsersFromGroupToTask(Long groupId, Long taskId) {
        GroupeUser groupeUser = webClient
                .get()
                .uri("/api/v1/GroupeUser/getGroup/{groupId}", groupId)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        response -> Mono.error(new IllegalArgumentException("Group not found"))
                )
                .bodyToMono(GroupeUser.class)
                .block();
        System.out.println("Group user id : "+groupeUser.getId());
        if (groupeUser == null) {
            throw new IllegalArgumentException("Group not found");
        }

        Tache tache = tacheRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Set<User> users = groupeUser.getUsers();
        tache.getUserList().addAll(users);
        tacheRepository.save(tache);

    }

}