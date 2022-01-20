package ua.com.alevel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "ua.com.alevel")
@EnableJpaRepositories
@EnableTransactionManagement
public class ComeUpServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(ComeUpServerApplication.class);
        app.run();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ComeUpServerApplication.class);
    }
}
