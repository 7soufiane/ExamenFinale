package boukhrit.soufiane.gestion_credit_bancaire.mapper;



import boukhrit.soufiane.gestion_credit_bancaire.DTOs.ClientDTO;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);
    Client toEntity(ClientDTO clientDTO);
}
