package boukhrit.soufiane.gestion_credit_bancaire.Repositories;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditImmobilier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Integer> {

    CreditImmobilier findFirstByCredit(Credit credit);

}
