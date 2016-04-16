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
			Advocacy massHousing = advocacyRepository.save(new Advocacy("Mass housing", "Mass housing blabla"));
			Advocacy massTransaport = advocacyRepository.save(new Advocacy("Addressing mass transport problem", "Addressing mass transport problem blabla"));
			Advocacy fightCrimeDrugs = advocacyRepository.save(new Advocacy("Fighting criminality, illegal drugs", "Fighting criminality, illegal drugs blabla"));
			Advocacy antiCorrupt = advocacyRepository.save(new Advocacy("Addressing corruption", "Addressing corruption blabla"));
			Advocacy federalism = advocacyRepository.save(new Advocacy("Shifting to a federal system of government", "Shifting to a federal system of government blabla"));
			Advocacy deathPenalty = advocacyRepository.save(new Advocacy("Reviving the death penalty", "Reviving the death penalty blabla"));
			Advocacy charterChange = advocacyRepository.save(new Advocacy("Charter change", "Charter change blabla"));
			Advocacy childrenWelfare = advocacyRepository.save(new Advocacy("Children's Welfare", "Children's Welfare blabla"));
			Advocacy electoralReform = advocacyRepository.save(new Advocacy("Electoral reform", "Electoral reform blabla"));
			Advocacy foi = advocacyRepository.save(new Advocacy("Freedom of Information", "Freedom of Information blabla"));
			Advocacy goodGovernance = advocacyRepository.save(new Advocacy("Good governance", "Good governance blabla"));
			Advocacy education = advocacyRepository.save(new Advocacy("Education", "Education blabla"));
			Advocacy ruleOfLaw = advocacyRepository.save(new Advocacy("Rule of Law", "Rule of Law blabla"));
			Advocacy foreignPolicy = advocacyRepository.save(new Advocacy("Foreign Policy", "Foreign Policy blabla"));
			Advocacy womensRight = advocacyRepository.save(new Advocacy("Women's right", "Women's right blabla"));

			List<CandidateAdvocacy> caBinay = new ArrayList<>();
			caBinay.add(new CandidateAdvocacy(binay, povertyReduction));
			caBinay.add(new CandidateAdvocacy(binay, jobCreation));
			caBinay.add(new CandidateAdvocacy(binay, protectionPromoteOFW));
			caBinay.add(new CandidateAdvocacy(binay, welfareSeniorCit));
			caBinay.add(new CandidateAdvocacy(binay, increaseBenSchTchr));
			caBinay.add(new CandidateAdvocacy(binay, massHousing));
			caBinay.add(new CandidateAdvocacy(binay, massTransaport));
			candidateAdvocacyRepository.save(caBinay);

			List<CandidateAdvocacy> caDuterte = new ArrayList<>();
			caDuterte.add(new CandidateAdvocacy(duterte, fightCrimeDrugs));
			caDuterte.add(new CandidateAdvocacy(duterte, antiCorrupt));
			caDuterte.add(new CandidateAdvocacy(duterte, federalism));
			caDuterte.add(new CandidateAdvocacy(duterte, deathPenalty));
			caDuterte.add(new CandidateAdvocacy(duterte, charterChange));
			candidateAdvocacyRepository.save(caDuterte);

			List<CandidateAdvocacy> caPoe = new ArrayList<>();
			caPoe.add(new CandidateAdvocacy(poe, povertyReduction));
			caPoe.add(new CandidateAdvocacy(poe, massTransaport));
			caPoe.add(new CandidateAdvocacy(poe, childrenWelfare));
			caPoe.add(new CandidateAdvocacy(poe, electoralReform));
			caPoe.add(new CandidateAdvocacy(poe, foi));
			candidateAdvocacyRepository.save(caPoe);

			List<CandidateAdvocacy> caRoxas = new ArrayList<>();
			caRoxas.add(new CandidateAdvocacy(roxas, povertyReduction));
			caRoxas.add(new CandidateAdvocacy(roxas, jobCreation));
			caRoxas.add(new CandidateAdvocacy(roxas, antiCorrupt));
			caRoxas.add(new CandidateAdvocacy(roxas, goodGovernance));
			caRoxas.add(new CandidateAdvocacy(roxas, education));
			candidateAdvocacyRepository.save(caRoxas);

			List<CandidateAdvocacy> caSantiago = new ArrayList<>();
			caSantiago.add(new CandidateAdvocacy(santiago, antiCorrupt));
			caSantiago.add(new CandidateAdvocacy(santiago, goodGovernance));
			caSantiago.add(new CandidateAdvocacy(santiago, ruleOfLaw));
			caSantiago.add(new CandidateAdvocacy(santiago, foreignPolicy));
			caSantiago.add(new CandidateAdvocacy(santiago, womensRight));
			candidateAdvocacyRepository.save(caSantiago);
		};
	}
}
