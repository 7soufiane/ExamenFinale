package boukhrit.soufiane.gestion_credit_bancaire.Repositories;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
