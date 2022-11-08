package com.nolanmortenson.portfolio;

import com.nolanmortenson.portfolio.models.AppUser;
import com.nolanmortenson.portfolio.repositories.AppUserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppUserRepo appUserRepo){
		return args -> {
//			AppUser user1 = new AppUser(
//					1L,
//					"bruh@bruh.com",
//					"Nolan",
//					"Mortenson",
//					"password",
//					null,
//					null,
//					null
//			);
//
//			appUserRepo.save(user1);
		};
	}

}
