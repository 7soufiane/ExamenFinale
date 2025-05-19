package boukhrit.soufiane.gestion_credit_bancaire.Repositories;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Client;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditRepository extends JpaRepository<Credit, Long> {

    Credit findFirstByClient(Client client);

}
