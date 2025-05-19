package boukhrit.soufiane.gestion_credit_bancaire.Repositories;

import boukhrit.soufiane.gestion_credit_bancaire.Entities.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatutCreditRepository extends JpaRepository<StatutCredit, Integer> {
}
