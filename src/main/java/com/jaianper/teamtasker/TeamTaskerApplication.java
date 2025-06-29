package com.jaianper.teamtasker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
        "com.jaianper.teamtasker.task.infrastructure.persistence.repository",
        "com.jaianper.teamtasker.user.infrastructure.persistence.repository",
        "com.jaianper.teamtasker.team.infrastructure.persistence.repository"
})
@EntityScan(basePackages = {
        "com.jaianper.teamtasker.task.infrastructure.persistence.entity",
        "com.jaianper.teamtasker.user.infrastructure.persistence.entity",
        "com.jaianper.teamtasker.team.infrastructure.persistence.entity"
})
@ComponentScan(basePackages = {
        "com.jaianper.teamtasker.task.application",
        "com.jaianper.teamtasker.task.domain",
        "com.jaianper.teamtasker.task.infrastructure",
        "com.jaianper.teamtasker.task.entrypoint",
        "com.jaianper.teamtasker.user.application",
        "com.jaianper.teamtasker.user.domain",
        "com.jaianper.teamtasker.user.infrastructure",
        "com.jaianper.teamtasker.user.entrypoint",
        "com.jaianper.teamtasker.team.application",
        "com.jaianper.teamtasker.team.domain",
        "com.jaianper.teamtasker.team.infrastructure",
        "com.jaianper.teamtasker.team.entrypoint"
})
public class TeamTaskerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamTaskerApplication.class, args);
    }

}
