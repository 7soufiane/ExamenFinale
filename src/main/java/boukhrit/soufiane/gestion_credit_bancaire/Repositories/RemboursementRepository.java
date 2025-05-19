package boukhrit.soufiane.gestion_credit_bancaire.Repositories;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.Credit;
import boukhrit.soufiane.gestion_credit_bancaire.Entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {

    Remboursement findFirstByCredit(Credit credit);

}
