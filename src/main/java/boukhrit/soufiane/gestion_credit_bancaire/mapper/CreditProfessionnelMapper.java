package boukhrit.soufiane.gestion_credit_bancaire.mapper;

import boukhrit.soufiane.gestion_credit_bancaire.DTOs.CreditProfessionnelDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditProfessionnel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CreditMapper.class})
public interface CreditProfessionnelMapper {
    CreditProfessionnelDTO toDTO(CreditProfessionnel creditProfessionnel);
    CreditProfessionnel toEntity(CreditProfessionnelDTO creditProfessionnelDTO);
}
