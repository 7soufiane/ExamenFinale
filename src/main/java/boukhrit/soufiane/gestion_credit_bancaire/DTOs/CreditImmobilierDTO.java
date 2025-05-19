package boukhrit.soufiane.gestion_credit_bancaire.DTOs;

import boukhrit.soufiane.gestion_credit_bancaire.enums.TypeDuBien;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditImmobilierDTO {

    private Integer id;
    private TypeDuBien type;
    private Long credit;

}
