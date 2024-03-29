import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.mycompany.careers")
@EnableJpaRepositories
@EntityScan("com.mycompany.careers")
public class JobListingApplicationHome {

    public static void main(String[] args) {
        SpringApplication.run(JobListingApplicationHome.class, args);
    }

}
