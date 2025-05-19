package boukhrit.soufiane.gestion_credit_bancaire.mapper;


import boukhrit.soufiane.gestion_credit_bancaire.DTOs.CreditDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, StatutCreditMapper.class})
public interface CreditMapper {
    @Mapping(source = "client", target = "clientDTO")
    @Mapping(source = "statutCredit", target = "statutCreditDTO")
    CreditDTO toDTO(Credit credit);

    @Mapping(source = "clientDTO", target = "client")
    @Mapping(source = "statutCreditDTO", target = "statutCredit")
    Credit toEntity(CreditDTO creditDTO);
}
