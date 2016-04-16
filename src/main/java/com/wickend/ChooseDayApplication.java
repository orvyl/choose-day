package com.wickend;

import com.wickend.entity.Advocacy;
import com.wickend.entity.Candidate;
import com.wickend.entity.CandidateAdvocacy;
import com.wickend.repo.AdvocacyRepository;
import com.wickend.repo.CandidateAdvocacyRepository;
import com.wickend.repo.CandidateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ChooseDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChooseDayApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDb(CandidateRepository candidateRepository,
									AdvocacyRepository advocacyRepository,
									CandidateAdvocacyRepository candidateAdvocacyRepository) {
		return strings -> {
			Candidate binay = candidateRepository.save(new Candidate("Jejomar", "Binay"));
			Candidate duterte = candidateRepository.save(new Candidate("Rudy", "Duterte"));
			Candidate poe = candidateRepository.save(new Candidate("Grace", "Poe"));
			Candidate roxas = candidateRepository.save(new Candidate("Mar", "Roxas"));
			Candidate santiago = candidateRepository.save(new Candidate("Miriam", "Santiago"));

			Advocacy povertyReduction = advocacyRepository.save(new Advocacy("Poverty Reduction", "Poverty reduction blabla"));
			Advocacy jobCreation = advocacyRepository.save(new Advocacy("Job Creation", "Job Creation blabla"));
			Advocacy protectionPromoteOFW = advocacyRepository.save(new Advocacy("Protection and promotion of rights of OFWs", "Protection and promotion of rights of OFWs blabla"));
			Advocacy welfareSeniorCit = advocacyRepository.save(new Advocacy("Welfare of senior citizens", "Welfare of senior citizens blabla"));
			Advocacy increaseBenSchTchr = advocacyRepository.save(new Advocacy("Increased benefits of public school teachers", "Increased benefits of public school teachers blabla"));

			List<CandidateAdvocacy> binayAdvc = new ArrayList<>();
			binayAdvc.add(new CandidateAdvocacy(binay, povertyReduction.getId()));

			candidateAdvocacyRepository.save(binayAdvc);
		};
	}
}
