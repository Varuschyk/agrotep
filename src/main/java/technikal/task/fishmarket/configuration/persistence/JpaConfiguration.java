package technikal.task.fishmarket.configuration.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("technikal.task.fishmarket.persistence.repository")
@EntityScan("technikal.task.fishmarket")
public class JpaConfiguration {
}
