package com.yugesh.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yugesh.healthcare.entity.Specilization;
import com.yugesh.healthcare.exception.SpecializationNotFoundException;
import com.yugesh.healthcare.service.SpecilizationService;
import com.yugesh.healthcare.view.SpecializationPdfView;
import com.yugesh.healthcare.view.SpecilizationExcelView;

@Controller
@RequestMapping("/spec")
public class SpecController {
	@Autowired
	private SpecilizationService service;

	@GetMapping("/register")
	public String SpecRegister() {
		return "SpecReg";
	}

	@PostMapping("/save")
	public String specSave(@ModelAttribute Specilization specilization, Model model) {
		System.out.println("specilixation" + specilization);
		Long id = service.saveSpecialization(specilization);
		String message = "Record ('" + id + "') is created";
		model.addAttribute("message", message);
		return "SpecReg";
	}

	@GetMapping("/all")
	public String SpecCreate(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Specilization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "SpecData";
	}

	@GetMapping("/delete")
	public String deletebyd(@RequestParam Long id, RedirectAttributes att) {
		try {
			service.removeSpecialization(id);
			att.addAttribute("message", "Record is (" + id + ") removed");
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			att.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String specEdit(@RequestParam Long id, Model mo,RedirectAttributes attributes) {
		String page=null;
		try {
			Specilization specialization = service.getOneSpecialization(id);
			mo.addAttribute("specialization", specialization);
			page="SpecEdit";
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("specialization", e.getMessage());
			page="redirect:all";

		}
		return page;
	}

	@PostMapping("/update")
	public String specUpdate(@ModelAttribute Specilization spec, RedirectAttributes attr) {
		service.updateSpecialization(spec);
		attr.addAttribute("specialization", "Record " + spec.getId() + " is created");
		return "redirect:all";
	}

	@GetMapping("/checkCode")
	@ResponseBody
	public String codeheck(@RequestParam String code,Long id) {
		String message = "";
		if(id==0 && service.iscodeExist(code)) { //register check
			message = code + ", already exist";
		} else if(id!=0 && service.isSpecCodeExistForEdit(code,id)) { //edit check
			message = code + ", already exist";
		} 

		return message; //this is not viewName(it is message)
	}

	@GetMapping("/checkName")
	@ResponseBody
	public String codename(@RequestParam String name,Long id) {
		String message = "";
		if(id==0 && service.isNameExist(name)) { //register check
			message = name + ", already exist";
		} else if(id!=0 && service.isSpecNameExistForEdit(name,id)) { //edit check
			message = name + ", already exist";
		} 

		return message; //this is not viewName(it is message)
	}
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m =  new ModelAndView();
		m.setView(new SpecilizationExcelView());

		//read data from DB
		List<Specilization> list = service.getAllSpecializations();
		//send to Excel Impl class
		m.addObject("list", list);

		return m;
	}
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView m = new ModelAndView();
		m.setView(new SpecializationPdfView());
		
		//read data from DB
		List<Specilization> list = service.getAllSpecializations();
		//send to Excel Impl class
		m.addObject("list", list);

		return m;
	}
}
