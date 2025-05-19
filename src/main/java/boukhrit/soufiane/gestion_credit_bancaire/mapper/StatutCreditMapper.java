package boukhrit.soufiane.gestion_credit_bancaire.mapper;

import boukhrit.soufiane.gestion_credit_bancaire.DTOs.StatutCreditDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.StatutCredit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatutCreditMapper {
    StatutCreditDTO toDTO(StatutCredit statutCredit);
    StatutCredit toEntity(StatutCreditDTO statutCreditDTO);
}
