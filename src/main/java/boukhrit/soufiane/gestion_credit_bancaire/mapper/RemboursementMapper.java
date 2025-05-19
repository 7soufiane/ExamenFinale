package boukhrit.soufiane.gestion_credit_bancaire.mapper;

import boukhrit.soufiane.gestion_credit_bancaire.DTOs.RemboursementDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.Remboursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CreditMapper.class, TypeRemboursementMapper.class})
public interface RemboursementMapper {
    @Mapping(source = "credit", target = "creditDTO")
    @Mapping(source = "typeRemboursement", target = "typeRemboursementDTO")
    RemboursementDTO toDTO(Remboursement remboursement);

    @Mapping(source = "creditDTO", target = "credit")
    @Mapping(source = "typeRemboursementDTO", target = "typeRemboursement")
    Remboursement toEntity(RemboursementDTO remboursementDTO);
}
