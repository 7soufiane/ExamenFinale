package boukhrit.soufiane.gestion_credit_bancaire.DTOs;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditProfessionnelDTO {

    private Integer id;

    @Size(max = 255)
    private String raisonSociale;

    @Size(max = 255)
    private String motif;

    private Long credit;

}
