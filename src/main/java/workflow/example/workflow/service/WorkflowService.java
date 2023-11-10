package workflow.example.workflow.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.Workflow;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.repository.WorkflowRepository;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class WorkflowService {

    private final WorkflowRepository workflowRepository;
    private final TacheRepository tacheRepository;
    private TableService tableService;

    public WorkflowService(WorkflowRepository workflowRepository, TacheRepository tacheRepository) {
        this.workflowRepository = workflowRepository;
        this.tacheRepository = tacheRepository;
    }

    @Autowired
    public WorkflowService(WorkflowRepository workflowRepository, TacheRepository tacheRepository, TableService tableService) {
        this.workflowRepository = workflowRepository;
        this.tacheRepository = tacheRepository;
        this.tableService = tableService;
    }

    @Transactional
    public ResponseEntity<Object> addWorkflow(Workflow workflow) {
        Long id = workflow.getId();
        if (id != null && workflowRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Workflow with id " + id + " already exists");
        }
        workflow.setCreationDate(new Date());
        workflowRepository.save(workflow);

        var activity1 = new Tache();
        activity1.setName("Début");
        activity1.setDescription("Default activity 1");
        activity1.setCreationDate(new Date());
        activity1.setWorkflowTache(workflow);
        tacheRepository.save(activity1);

        var activity2 = new Tache();
        activity2.setName("Fin");
        activity2.setDescription("Default activity 2");
        activity2.setCreationDate(new Date());
        activity2.setWorkflowTache(workflow);
        tacheRepository.save(activity2);
        workflowRepository.save(workflow);
        Map<String, Object> response = new HashMap<>();
        response.put("workflow", workflow);
        response.put("message", "Workflow successfully created!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> updateWorkflow(Long id, Workflow workflow) {
        workflowRepository.findById(id).ifPresentOrElse(
                w -> {
                    w.setName(workflow.getName());
                    w.setDescription(workflow.getDescription());
                    w.setEtat(workflow.getEtat());
                    w.setLastModifiedDate(new Date());
                    w.setDeclencheur(workflow.getDeclencheur());
                    w.setSgbd(workflow.getSgbd());
                    w.setJdbcUrl(workflow.getJdbcUrl());
                    w.setUsername(workflow.getUsername());
                    w.setPassword(workflow.getPassword());
                    w.setTacheAecouter(workflow.getTacheAecouter());
                    w.setEvenement(workflow.getEvenement());
                    w.setWebhookUrl(workflow.getWebhookUrl());
                    workflowRepository.save(w);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workflow not found !");
                });
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("workflow", workflow);
        responseBody.put("message", "Workflow successfully updated!");

        return ResponseEntity.ok().body(responseBody);

    }

    @Transactional
    public void deleteWorkflowById(Long id) {
        var workflow = workflowRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workflow not found !"));
        workflowRepository.delete(workflow);
    }

    public List<Workflow> getAllWorkflows() {
        return workflowRepository.findAll();
    }

    public Workflow findWorkflowById(Long id) {
        Optional<Workflow> workflowOptional = workflowRepository.findById(id);
        if (workflowOptional.isPresent()) {
            return workflowOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workflow not found");
        }
    }

    public List<String> getWorkflowTables(Long workflowId) throws NotFoundException {
        var workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() -> new NotFoundException("Workflow not found with id: " + workflowId));
        return tableService.getTables(workflow.getJdbcUrl(), workflow.getUsername(), workflow.getPassword(),workflow.getSgbd());
    }

}