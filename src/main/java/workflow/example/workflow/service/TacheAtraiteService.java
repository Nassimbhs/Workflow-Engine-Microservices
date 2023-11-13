package workflow.example.workflow.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.converter.TacheAtraiterConverter;
import workflow.example.workflow.dto.TacheAtraiterDto;
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.repository.TacheAtraiteRepository;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TacheAtraiteService {

    private final TacheAtraiteRepository tacheAtraiteRepository;
    private final TacheAtraiterConverter tacheAtraiterConverter;
    public TacheAtraiteService(TacheAtraiteRepository tacheAtraiteRepository, TacheAtraiterConverter tacheAtraiterConverter) {
        this.tacheAtraiteRepository = tacheAtraiteRepository;
        this.tacheAtraiterConverter = tacheAtraiterConverter;
    }

    public List<TacheAtraiter> getTacheAtraiterByResponsable(Long responsableId) {
        return tacheAtraiteRepository.findByResponsable(responsableId);
    }

    @Transactional
    public ResponseEntity<Object> marquerTacheCommeTraite(Long id, TacheAtraiterDto tacheAtraiterDto) {
        TacheAtraiter tacheAtraiter = tacheAtraiterConverter.dtoToEntity(tacheAtraiterDto);
        tacheAtraiteRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setStatut("traité");
                    a.setApprobation("Accepter");
                    tacheAtraiteRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found !");
                });

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("tacheAtraiter", tacheAtraiter);
        responseBody.put("message", "tacheAtraiter successfully updated!");

        return ResponseEntity.ok().body(responseBody);

    }

    @Transactional
    public ResponseEntity<Object> rejeterTache(Long id, TacheAtraiterDto tacheAtraiterDto) {
        TacheAtraiter tacheAtraiter = tacheAtraiterConverter.dtoToEntity(tacheAtraiterDto);
        tacheAtraiteRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setStatut("traité");
                    a.setApprobation("Rejeter");
                    tacheAtraiteRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found !");
                });

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("tacheAtraiter", tacheAtraiter);
        responseBody.put("message", "tacheAtraiter successfully updated!");

        return ResponseEntity.ok().body(responseBody);

    }

    public List<TacheAtraiter> getAlltachesAtraiter() {
        return tacheAtraiteRepository.findAll();
    }

    public TacheAtraiter findtacheById(Long id) {
        Optional<TacheAtraiter> tacheOptional = tacheAtraiteRepository.findById(id);
        if (tacheOptional.isPresent()) {
            return tacheOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TacheAtraiter not found");
        }
    }

    public List<TacheAtraiter> getTachesTraiteesParResponsable(Long responsableId) {
        return tacheAtraiteRepository.getTachesTraiteesParResponsable(responsableId);
    }

    public List<TacheAtraiter> findByWorkflowId(Long workflowId){
        return tacheAtraiteRepository.findByWorkflowId(workflowId);
    }
    public List<TacheAtraiter> getTacheNontraiterByResponsable(Long responsableId){
        return tacheAtraiteRepository.getTacheNontraiterByResponsable(responsableId);
    }
}