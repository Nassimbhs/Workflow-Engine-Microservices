package workflow.example.workflow.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import workflow.example.workflow.dto.WorkflowDto;
import workflow.example.workflow.entity.Workflow;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkflowConverter {
    private final TacheConverter tacheConverter;

    public WorkflowDto entityToDto(Workflow workflow){

        var dto = new WorkflowDto();
        dto.setId(workflow.getId());
        dto.setName(workflow.getName());
        dto.setDescription(workflow.getDescription());
        dto.setEtat(workflow.getEtat());
        dto.setCreationDate(workflow.getCreationDate());
        dto.setLastModifiedDate(workflow.getLastModifiedDate());
        dto.setWebhookUrl(workflow.getWebhookUrl());
        dto.setDeclencheur(workflow.getDeclencheur());
        dto.setSgbd(workflow.getSgbd());
        dto.setJdbcUrl(workflow.getJdbcUrl());
        dto.setUsername(workflow.getUsername());
        dto.setPassword(workflow.getPassword());
        dto.setTacheAecouter(workflow.getTacheAecouter());
        dto.setEvenement(workflow.getEvenement());
        dto.setTacheDtoList(tacheConverter.entityToDto(workflow.getTaches()));
        return dto;
    }
    public Workflow dtoToEntity(WorkflowDto dto) {
        var entity = new Workflow();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setEtat(dto.getEtat());
        entity.setCreationDate(dto.getCreationDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setWebhookUrl(dto.getWebhookUrl());
        entity.setDeclencheur(dto.getDeclencheur());
        entity.setSgbd(dto.getSgbd());
        entity.setJdbcUrl(dto.getJdbcUrl());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setTacheAecouter(dto.getTacheAecouter());
        entity.setEvenement(dto.getEvenement());
        return entity;
    }
    public List<WorkflowDto> entityToDto(List<Workflow> workflows)
    {
        return workflows.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
