package com.example.demo.resource;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomFieldDTO;
import com.example.demo.dto.CustomFieldPropertzDTO;
import com.example.demo.dto.LeadCustomFieldValueDTO;
import com.example.demo.dto.LeadDTO;
import com.example.demo.dto.ModuleDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.CustomField;
import com.example.demo.model.CustomFieldPropertz;
import com.example.demo.model.LeadCustomFieldValue;
import com.example.demo.model.Lead;
import com.example.demo.model.Module;
import com.example.demo.service.CustomFieldService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/api")
public class CustomFieldResource {

	@Autowired
	private CustomFieldService customFieldService;
	
	@GetMapping("modules")
	public List<Module> getModules(){
		return customFieldService.getAllModules();
	}

	@GetMapping("module")
	public Optional<Module> getModule(@RequestParam long id){
		return customFieldService.findModule(id);
	}

	@PostMapping("module")
	public Module postModule(@RequestBody @Validated ModuleDTO moduleDTO){
		return customFieldService.addModule(moduleDTO);
	}

	@DeleteMapping("module")
	public String deleteModule(@RequestParam long id){
		return customFieldService.deleteModule(id);		
	}

	@GetMapping("leads")
	public ResponseDTO getAllLeads(@RequestParam(required=true) String moduleName){
		return customFieldService.getAllLeads(moduleName);
	}

	@GetMapping("lead")
	public ResponseDTO getLead(@RequestParam long id){
		return customFieldService.findLead(id);
	}

	@PostMapping("lead")
	public ResponseDTO postLead(@RequestBody @Validated LeadDTO leadDTO){
		return customFieldService.addLead(leadDTO);
	}

	@DeleteMapping("lead")
	public String deleteLead(@RequestParam long id){
		return customFieldService.deleteLead(id);
	}

	@GetMapping("custom-fields")
	public List<CustomField> getAllCustomFields(){
		return customFieldService.getAllCustomFields();
	}

	@GetMapping("custom-field")
	public Optional<CustomField> getCustomField(@RequestParam long id){
		return customFieldService.findCustomField(id);
	}

	@PostMapping("custom-field")
	public CustomField postCustomField(@RequestBody @Validated CustomFieldDTO customFieldDTO){
		return customFieldService.addCustomField(customFieldDTO);
	}

	@DeleteMapping("custom-field")
	public String deleteCustomField(@RequestParam long id){
		return customFieldService.deleteCustomField(id);
	}

	@GetMapping("lead-custom-field-values")
	public List<LeadCustomFieldValue> getAllLeadCustomFieldValues(){
		return customFieldService.getAllLeadCustomFieldValues();
	}

	@GetMapping("lead-custom-field-value")
	public Optional<LeadCustomFieldValue> getLeadCustomFieldValue(@RequestParam long id){
		return customFieldService.findLeadCustomFieldValue(id);
	}

	@PostMapping("lead-custom-field-value")
	public LeadCustomFieldValue postLeadCustomFieldValue(@RequestBody @Validated LeadCustomFieldValueDTO customFieldValueDTO){
		return customFieldService.addLeadCustomFieldValue(customFieldValueDTO);
	}

	@DeleteMapping("lead-custom-field-value")
	public String deleteLeadCustomFieldValue(@RequestParam long id){
		return customFieldService.deleteLeadCustomFieldValue(id);
	}

	@GetMapping("lead-fields")
	public Map<?,?> findCustomFieldPropertz(@RequestParam(required=true) String module) {
		return customFieldService.findCustomFieldPropertz(module);
	}

	@PutMapping("custom-field-propertz")
	public CustomFieldPropertz updatePropertz(@RequestBody CustomFieldPropertz propertz, @RequestParam(required=true) String module) {
		return customFieldService.updatePropertz(propertz, module);
	}
	
	@PutMapping("insert-field-propertz")
	public void insertCustomFields() {
		Field[] fields = Lead.class.getDeclaredFields();
		customFieldService.insertCustomFields(fields, "Leads");
	}

	@GetMapping("insert-leads")
	public void insertLeads() {
		customFieldService.insertLeads();
	}

	@GetMapping("/message-test")
	public ResponseDTO messageTest(@RequestParam("lang") String language){
		log.info("Lang : "+language);
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.put("first-name", messageSource.getMessage("first-name", new Object[0], new Locale(language)));
		responseDTO.put("last-name", messageSource.getMessage("last-name", new Object[0], new Locale(language)));
		responseDTO.put("email", messageSource.getMessage("email", new Object[0], new Locale(language)));
		responseDTO.put("mobile", messageSource.getMessage("mobile", new Object[0], new Locale(language)));
		responseDTO.put("success", true);
		return responseDTO;
	}
	
	@Autowired
	private MessageSource messageSource;
}
