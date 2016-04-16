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
			Candidate binay = candidateRepository.save(new Candidate(1L, "Jejomar", "Binay"));
			Candidate duterte = candidateRepository.save(new Candidate(2L, "Rudy", "Duterte"));
			Candidate poe = candidateRepository.save(new Candidate(3L, "Grace", "Poe"));
			Candidate roxas = candidateRepository.save(new Candidate(4L, "Mar", "Roxas"));
			Candidate santiago = candidateRepository.save(new Candidate(5L, "Miriam", "Santiago"));

			Advocacy povertyReduction = advocacyRepository.save(new Advocacy("Poverty Reduction", "According to the 2012 SWS  survey on the most important problem of the country, 10.22% of the electorate mentioned Poverty which makes it the 3rd most cited problem"));
			Advocacy jobCreation = advocacyRepository.save(new Advocacy("Job Creation", "Unemployment, at 33.96%  is the top most important problem of the country based on the 2012 SWS survey "));
			Advocacy protectionPromoteOFW = advocacyRepository.save(new Advocacy("Protection and promotion of rights of OFWs", "Protection and promotion of rights of OFWs."));
			Advocacy welfareSeniorCit = advocacyRepository.save(new Advocacy("Welfare of senior citizens", "Social Services (Education, Health, Disaster etc.), in general, has 11.23% frequency on the 2012 SWS survey on the most important problem of the country."));
			Advocacy increaseBenSchTchr = advocacyRepository.save(new Advocacy("Increased benefits of public school teachers", "Based on the 2012 SWS survey, Income/Salaries/Wags Too low is one of the most  important problem of the country with 2.85% frequency"));
			Advocacy massHousing = advocacyRepository.save(new Advocacy("Mass housing", "Housing problems has 0.26% frequency on the 2012 SWS survey on the most important problem of the country"));
			Advocacy massTransaport = advocacyRepository.save(new Advocacy("Addressing mass transport problem", "Addressing mass transport problem."));
			Advocacy fightCrimeDrugs = advocacyRepository.save(new Advocacy("Fighting criminality, illegal drugs", "Crime, in general, has 3.02% frequency on the 2012 SWS survey on the most important problem of the country"));
			Advocacy antiCorrupt = advocacyRepository.save(new Advocacy("Addressing corruption", "Corruption/Public Morality, at 11.37%, is the second top most important problem of the country based on the 2012 SWS survey"));
			Advocacy federalism = advocacyRepository.save(new Advocacy("Shifting to a federal system of government", "1.02% of the electorate mentioned democracy in general as the most important problem of  the country"));
			Advocacy deathPenalty = advocacyRepository.save(new Advocacy("Reviving the death penalty", "Crime, in general, has 3.02% frequency on the 2012 SWS survey on the most important problem of the country"));
			Advocacy charterChange = advocacyRepository.save(new Advocacy("Charter change", "1.02% of the electorate mentioned democracy in geneal as the most important problem of  the country"));
			Advocacy childrenWelfare = advocacyRepository.save(new Advocacy("Children's Welfare", "Social Services (Education, Health, Disaster etc.), in general, has 11.23% frequency on the 2012 SWS survey on the most important problem of the country"));
			Advocacy electoralReform = advocacyRepository.save(new Advocacy("Electoral reform", "According to the 2012 SWS  survey on the most important problem of the country, 0.08% of the electorate mentioned Election Problems"));
			Advocacy foi = advocacyRepository.save(new Advocacy("Freedom of Information", "Freedom of Information."));
			Advocacy goodGovernance = advocacyRepository.save(new Advocacy("Good governance", "Governance, in general, has 12.11% frequency on the 2012 SWS survey on the most important problem of the country. This includes Corruption/Public Morality at 11.37% and Fiscal Deficit at 0.43%"));
			Advocacy education = advocacyRepository.save(new Advocacy("Education", "Based on the 2012 SWS survey on the most important problem of the country, Education has 0.61% frequency"));
			Advocacy ruleOfLaw = advocacyRepository.save(new Advocacy("Rule of Law", "Rule of Law."));
			Advocacy foreignPolicy = advocacyRepository.save(new Advocacy("Foreign Policy", "Security Problems in general has 1.79% frequency on the 2012 SWS survey on the most important problem of the country"));
			Advocacy womensRight = advocacyRepository.save(new Advocacy("Women's right", "Social Services (Education, Health, Disaster etc.), in general, has 11.23% frequency on the 2012 SWS survey on the most important problem of the country"));

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

			// todo seed result
		};
	}
}
