package boukhrit.soufiane.gestion_credit_bancaire.Repositories;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.CreditProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Integer> {

    CreditProfessionnel findFirstByCredit(Credit credit);

}
