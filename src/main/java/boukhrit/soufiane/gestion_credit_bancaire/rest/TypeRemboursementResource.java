package boukhrit.soufiane.gestion_credit_bancaire.rest;

import boukhrit.soufiane.gestion_credit_bancaire.DTOs.TypeRemboursementDTO;
import boukhrit.soufiane.gestion_credit_bancaire.service.TypeRemboursementService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/typeRemboursements", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeRemboursementResource {

    private final TypeRemboursementService typeRemboursementService;

    public TypeRemboursementResource(final TypeRemboursementService typeRemboursementService) {
        this.typeRemboursementService = typeRemboursementService;
    }

    @GetMapping
    public ResponseEntity<List<TypeRemboursementDTO>> getAllTypeRemboursements() {
        return ResponseEntity.ok(typeRemboursementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeRemboursementDTO> getTypeRemboursement(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(typeRemboursementService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createTypeRemboursement(
            @RequestBody @Valid final TypeRemboursementDTO typeRemboursementDTO) {
        final Integer createdId = typeRemboursementService.create(typeRemboursementDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateTypeRemboursement(
            @PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final TypeRemboursementDTO typeRemboursementDTO) {
        typeRemboursementService.update(id, typeRemboursementDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTypeRemboursement(
            @PathVariable(name = "id") final Integer id) {
        typeRemboursementService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
