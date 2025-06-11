package com.jaianper.teamtasker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.jaianper.teamtasker.core.infrastructure.persistence.repository")
@EntityScan("com.jaianper.teamtasker.core.infrastructure.persistence.entity")
@ComponentScan(basePackages = {
        "com.jaianper.teamtasker.core.application",
        "com.jaianper.teamtasker.core.domain",
        "com.jaianper.teamtasker.core.infrastructure",
        "com.jaianper.teamtasker.core.entrypoint"
})
public class TeamTaskerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamTaskerApplication.class, args);
    }

}
