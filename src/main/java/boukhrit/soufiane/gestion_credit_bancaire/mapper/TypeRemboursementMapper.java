package boukhrit.soufiane.gestion_credit_bancaire.mapper;

import boukhrit.soufiane.gestion_credit_bancaire.DTOs.TypeRemboursementDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.TypeRemboursement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeRemboursementMapper {
    TypeRemboursementDTO toDTO(TypeRemboursement typeRemboursement);
    TypeRemboursement toEntity(TypeRemboursementDTO typeRemboursementDTO);
}
