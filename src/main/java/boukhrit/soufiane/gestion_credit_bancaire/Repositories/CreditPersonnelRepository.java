package boukhrit.soufiane.gestion_credit_bancaire.Repositories;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditPersonnelRepository extends JpaRepository<CreditPersonnel, Integer> {

    CreditPersonnel findFirstByCredit(Credit credit);

}
