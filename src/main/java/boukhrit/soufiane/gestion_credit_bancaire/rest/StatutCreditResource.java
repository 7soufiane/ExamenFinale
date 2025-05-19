package boukhrit.soufiane.gestion_credit_bancaire.rest;

import boukhrit.soufiane.gestion_credit_bancaire.DTOs.StatutCreditDTO;
import boukhrit.soufiane.gestion_credit_bancaire.service.StatutCreditService;
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
@RequestMapping(value = "/api/statutCredits", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatutCreditResource {

    private final StatutCreditService statutCreditService;

    public StatutCreditResource(final StatutCreditService statutCreditService) {
        this.statutCreditService = statutCreditService;
    }

    @GetMapping
    public ResponseEntity<List<StatutCreditDTO>> getAllStatutCredits() {
        return ResponseEntity.ok(statutCreditService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatutCreditDTO> getStatutCredit(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(statutCreditService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createStatutCredit(
            @RequestBody @Valid final StatutCreditDTO statutCreditDTO) {
        final Integer createdId = statutCreditService.create(statutCreditDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateStatutCredit(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final StatutCreditDTO statutCreditDTO) {
        statutCreditService.update(id, statutCreditDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteStatutCredit(@PathVariable(name = "id") final Integer id) {
        statutCreditService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
