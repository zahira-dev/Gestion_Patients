package org.sid.GestionPatient;

import java.util.Date;

import org.sid.GestionPatient.Dao.PatientRepository;
import org.sid.GestionPatient.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestionPatientApplication implements CommandLineRunner{
	@Autowired
    private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(GestionPatientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null,"Zahira",new Date(),false,5));
		patientRepository.save(new Patient(null,"Oumaima",new Date(),false,6));
		patientRepository.save(new Patient(null,"Hamid",new Date(),true,7));
		patientRepository.save(new Patient(null,"Malak",new Date(),true,10));
		patientRepository.findAll().forEach(p->{
			System.out.println(p.getNom());
		});
	}

}
