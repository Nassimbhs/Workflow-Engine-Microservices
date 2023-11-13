package workflow.example.workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workflow.example.workflow.converter.TacheAtraiterConverter;
import workflow.example.workflow.dto.TacheAtraiterDto;
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.service.TacheAtraiteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/TacheAtraiter")
@Tag(name = "TacheAtraiter", description = "CRUD TacheAtraiter")
@CrossOrigin(origins = "http://localhost:4200")
public class TacheAtraiterController {

    private final TacheAtraiteService tacheAtraiteService;
    private final TacheAtraiterConverter tacheAtraiterConverter;

    @GetMapping("/responsable/{responsableId}")
    @Operation(
            summary = "Find TacheAtraiter by user id",
            description = "Find TacheAtraiter by user id.",
            tags = {"TacheAtraiter"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheAtraiterDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<TacheAtraiterDto>> getTacheAtraiterByResponsable(@PathVariable Long responsableId) {
        List<TacheAtraiterDto> tacheAtraiterDtos = tacheAtraiterConverter.entityToDto(tacheAtraiteService.getTacheAtraiterByResponsable(responsableId));
        return ResponseEntity.ok(tacheAtraiterDtos);
    }

    @PutMapping("/traite/{id}")
    @Operation(
            summary = "Update TacheAtraiter",
            description = "Update TacheAtraiter by id.",
            tags = {"TacheAtraiter"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheAtraiterDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateTache(@PathVariable Long id, @RequestBody TacheAtraiterDto tacheAtraiterDto) {
        return tacheAtraiteService.marquerTacheCommeTraite(id, tacheAtraiterDto);
    }
    @PutMapping("/rejeter/{id}")
    public ResponseEntity<Object> rejeterTache(@PathVariable Long id, @RequestBody TacheAtraiterDto tacheAtraiterDto) {
        return tacheAtraiteService.rejeterTache(id, tacheAtraiterDto);
    }

    @GetMapping("/allTacheAtraiter/")
    @Operation(
            summary = "Find all TacheAtraiter",
            description = "Find all TacheAtraiter.",
            tags = {"TacheAtraiter"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheAtraiterDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TacheAtraiterDto> findAll() {
        return tacheAtraiterConverter.entityToDto(tacheAtraiteService.getAlltachesAtraiter());
    }
    @GetMapping("/getTacheAtraiter/{id}")
    @Operation(
            summary = "Find TacheAtraiter",
            description = "Find Tache by id.",
            tags = {"TacheAtraiter"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheAtraiterDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public TacheAtraiterDto findTacheById(@PathVariable Long id) {
        return tacheAtraiterConverter.entityToDto(tacheAtraiteService.findtacheById(id));
    }

    @GetMapping("/traitees/{responsableId}")
    @Operation(
            summary = "Find TacheAtraiter traité",
            description = "Find Tache traité",
            tags = {"TacheAtraiter"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheAtraiterDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TacheAtraiterDto> getTachesNonTraiteesParResponsable(@PathVariable Long responsableId) {
        return tacheAtraiterConverter.entityToDto( tacheAtraiteService.getTachesTraiteesParResponsable(responsableId));
    }

    @GetMapping("/findByWorkflowId/{workflowId}")
    @Operation(
            summary = "Find workflowId",
            description = "Find Tache tacheAtraiter by workflowId",
            tags = {"TacheAtraiter"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheAtraiterDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TacheAtraiterDto> findByWorkflowId(@PathVariable Long workflowId){
        return tacheAtraiterConverter.entityToDto(tacheAtraiteService.findByWorkflowId(workflowId));
    }

    @GetMapping("/getTacheNontraiterByResponsable/{responsableId}")
    @Operation(
            summary = "Find responsableId",
            description = "Find Tache tacheAtraiter by responsableId",
            tags = {"TacheAtraiter"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheAtraiterDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TacheAtraiterDto> getTacheNontraiterByResponsable(@PathVariable Long responsableId){
        return tacheAtraiterConverter.entityToDto(tacheAtraiteService.getTacheNontraiterByResponsable(responsableId));
    }

}