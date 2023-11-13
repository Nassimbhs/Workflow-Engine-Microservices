package workflow.example.workflow.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import workflow.example.workflow.dto.TacheDto;
import workflow.example.workflow.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TacheConverter {
    private final LienTacheConverter lienTacheConverter;
    private final TacheAtraiterConverter tacheAtraiterConverter;

    public TacheDto entityToDto(Tache tache){
        var dto = new TacheDto();
        dto.setId(tache.getId());
        dto.setName(tache.getName());
        dto.setCreationDate(tache.getCreationDate());
        dto.setDescription(tache.getDescription());
        dto.setStartDate(tache.getStartDate());
        dto.setEndDate(tache.getEndDate());
        dto.setStatut(tache.getStatut());
        dto.setAction(tache.getAction());
        dto.setApprobation(tache.getApprobation());
        dto.setLienTacheDtos(lienTacheConverter.entityToDto(tache.getLienTaches()));
        dto.setTacheAtraiterDtos(tacheAtraiterConverter.entityToDto(tache.getTacheAtraiters()));
        return dto;
    }

    public List<TacheDto> entityToDto(List<Tache> taches)
    {
        return taches.stream().map(this::entityToDto).collect(Collectors.toList());
    }
    public Tache dtoToEntity(TacheDto tacheDto){
        var entity = new Tache();
        entity.setId(tacheDto.getId());
        entity.setName(tacheDto.getName());
        entity.setCreationDate(tacheDto.getCreationDate());
        entity.setDescription(tacheDto.getDescription());
        entity.setStartDate(tacheDto.getStartDate());
        entity.setEndDate(tacheDto.getEndDate());
        entity.setStatut(tacheDto.getStatut());
        entity.setAction(tacheDto.getAction());
        entity.setApprobation(tacheDto.getApprobation());
        List<LienTache> lienTaches = tacheDto.getLienTacheDtos().stream()
                .map(lienTacheConverter::dtoToEntity)
                .collect(Collectors.toList());
        entity.setLienTaches(lienTaches);

        List<TacheAtraiter> tacheAtraiters = tacheDto.getTacheAtraiterDtos().stream()
                .map(tacheAtraiterConverter::dtoToEntity)
                .collect(Collectors.toList());
        entity.setTacheAtraiters(tacheAtraiters);

        return entity;
    }

}
