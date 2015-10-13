package au.com.auspost.microservice.autoconfigure;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by miaot on 13/10/2015.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"au.com.auspost.microservice.domain"})
@EnableJpaRepositories(basePackages = {"au.com.auspost.microservice.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
