package boukhrit.soufiane.gestion_credit_bancaire.mapper;

import boukhrit.soufiane.gestion_credit_bancaire.DTOs.CreditImmobilierDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditImmobilier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CreditMapper.class})
public interface CreditImmobilierMapper {
    CreditImmobilierDTO toDTO(CreditImmobilier creditImmobilier);
    CreditImmobilier toEntity(CreditImmobilierDTO creditImmobilierDTO);
}
