package org.sid.GestionPatient.web;

import java.awt.print.Pageable;
import java.util.List;

import javax.validation.Valid;

import org.sid.GestionPatient.Dao.PatientRepository;
import org.sid.GestionPatient.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;

	@GetMapping(path = "/index")

	public String index() {
		return "index";
	}

	@GetMapping(path = "/patients")

	public String list(Model Model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyword", defaultValue = "") String mc) {
		Page<Patient> Pagepatients = patientRepository.findByNomContains(mc, PageRequest.of(page, size));
		Model.addAttribute("patients", Pagepatients.getContent());
		Model.addAttribute("pages", new int[Pagepatients.getTotalPages()]);
		Model.addAttribute("currentPage", page);
		Model.addAttribute("size", size);
		Model.addAttribute("keyword", mc);
		return "patients";
	}

	@GetMapping(path = "/deletePatient")

	public String delete(Long id, String keyword,int page,int size,Model Model) {
		patientRepository.deleteById(id);
		return list( Model,page,size,keyword);
	}
	@GetMapping(path = "/formPatient" )
	public String formPatient(Model Model) {
	  Model.addAttribute("patient",new Patient());
	  Model.addAttribute("mode", "new");
		return "formPatient";
	}
	@PostMapping( "/savePatient" )
	public String savePatient(Model Model,@Valid Patient Patient,BindingResult BindingResult)
	{  if(BindingResult.hasErrors())
		return "formPatient";
		patientRepository.save(Patient);
		Model.addAttribute("patient",Patient);
		return "confirmation";
		
	}
	@GetMapping(path="/editerPatient")
	public String editPatient(Model Model, Long id) {
		Patient patient=patientRepository.findById(id).get();
		Model.addAttribute("patient", patient);
		Model.addAttribute("mode", "edit");
	return "formPatient";
	}
}
