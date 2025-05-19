package boukhrit.soufiane.gestion_credit_bancaire.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TypeRemboursementDTO {

    private Integer id;

    @NotNull
    @Size(max = 20)
    private String libelle;

}
