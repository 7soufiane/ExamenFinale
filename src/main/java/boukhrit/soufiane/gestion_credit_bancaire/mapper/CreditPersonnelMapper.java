package boukhrit.soufiane.gestion_credit_bancaire.mapper;

import boukhrit.soufiane.gestion_credit_bancaire.DTOs.CreditPersonnelDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditPersonnel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CreditMapper.class})
public interface CreditPersonnelMapper {
    CreditPersonnelDTO toDTO(CreditPersonnel creditPersonnel);
    CreditPersonnel toEntity(CreditPersonnelDTO creditPersonnelDTO);
}
