package dev.matheuslf.desafio.inscritos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableJpaAuditing
public class InscritosApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().directory(".env").ignoreIfMissing().ignoreIfMalformed().load();
		dotenv.get("SPRING_APPLICATION_NAME");
		dotenv.get("SPRING_DATASOURCE_URL");
		dotenv.get("SPRING_DATASOURCE_USERNAME");
		dotenv.get("SPRING_DATASOURCE_PASSWORD");
		dotenv.get("SPRING_DATASOURCE_DRIVER_CLASS_NAME");
		dotenv.get("SPRING_JPA_HIBERNATE_DDL_AUTO");
		dotenv.get("SPRING_JPA_DATABASE_PLATFORM");
		dotenv.get("SPRING_H2_CONSOLE_ENABLED");
		dotenv.get("SPRING_H2_CONSOLE_PATH");
		SpringApplication.run(InscritosApplication.class, args);
	}

}
