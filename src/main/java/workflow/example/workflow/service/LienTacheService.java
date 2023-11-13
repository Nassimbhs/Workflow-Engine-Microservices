package workflow.example.workflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.converter.LienTacheConverter;
import workflow.example.workflow.dto.LienTacheDto;
import workflow.example.workflow.entity.LienTache;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.repository.LienTacheRepository;
import workflow.example.workflow.repository.TacheRepository;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LienTacheService {

    private final LienTacheRepository lienTacheRepository;
    private final TacheRepository tacheRepository;
    private final LienTacheConverter lienTacheConverter;

    @Transactional
    public ResponseEntity<Object> addLink(LienTacheDto lienTacheDto) {
        var lienTache = lienTacheConverter.dtoToEntity(lienTacheDto);
        Optional<Tache> tache = tacheRepository.findById(lienTache.getId());
        if (tache.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "activity with id " + lienTache.getId() + " not found");
        }
        lienTache.setTacheLien(tache.get());

        lienTacheRepository.save(lienTache);
        var lienTacheDTO = new LienTacheDto();
        lienTacheDTO.setId(lienTache.getId());
        lienTacheDTO.setSource(lienTache.getSource());
        lienTacheDTO.setTarget(lienTache.getTarget());
        lienTacheDTO.setWorkflowId(lienTache.getWorkflowId());
        lienTacheDTO.setTacheSourceName(lienTache.getTacheSourceName());
        lienTacheDTO.setTacheTargetName(lienTache.getTacheTargetName());
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("LienActivite", lienTacheDTO);
        responseBody.put("message", "Link successfully created!");

        return ResponseEntity.ok().body(responseBody);

    }
    @Transactional
    public ResponseEntity<Object> updateLink(Long id, LienTacheDto lienTacheDto) {
        var lienTache = lienTacheConverter.dtoToEntity(lienTacheDto);
        lienTacheRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setSource(lienTache.getSource());
                    a.setTarget(lienTache.getTarget());
                    a.setType(lienTache.getType());
                    lienTacheRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found !");
                });
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("LienActivite", lienTache);
        responseBody.put("message", "Link successfully updated !");

        return ResponseEntity.ok().body(responseBody);
    }

    @Transactional
    public void deleteLinkById(Long id) {
        var lienTache = lienTacheRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found !"));
        lienTacheRepository.delete(lienTache);
    }

    public List<LienTache> getAllLinks() {
        return lienTacheRepository.findAll();
    }

    public LienTache findLinkById(Long id) {
        Optional<LienTache> lienTacheOptional = lienTacheRepository.findById(id);
        if (lienTacheOptional.isPresent()) {
            return lienTacheOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found");
        }
    }
    public  List<LienTache> findByTacheIdWithTacheLiee(Long activiteId){
        return lienTacheRepository.findByTacheIdWithTacheLiee(activiteId);
    }
    public List<LienTache> findByWorkflowId(String workflowId){
        return lienTacheRepository.findByWorkflowId(workflowId);
    }
}
