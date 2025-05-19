package boukhrit.soufiane.gestion_credit_bancaire.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("boukhrit.soufiane.gestion_credit_bancaire.Entities")
@EnableJpaRepositories("boukhrit.soufiane.gestion_credit_bancaire.Repositories")
@EnableTransactionManagement
public class DomainConfig {
}
