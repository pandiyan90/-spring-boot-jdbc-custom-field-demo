package com.example.demo.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.CustomException;
import com.example.demo.config.ReplaceNamingStrategy;
import com.example.demo.dto.CustomFieldDTO;
import com.example.demo.dto.LeadCustomFieldValueDTO;
import com.example.demo.dto.LeadDTO;
import com.example.demo.dto.ModuleDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.CustomField;
import com.example.demo.model.CustomFieldPropertz;
import com.example.demo.model.Lead;
import com.example.demo.model.LeadCustomFieldValue;
import com.example.demo.model.Module;
import com.example.demo.respository.CustomFieldPropertzRespository;
import com.example.demo.respository.CustomFieldRespository;
import com.example.demo.respository.LeadCustomFieldValueRespository;
import com.example.demo.respository.LeadRespository;
import com.example.demo.respository.ModuleRespository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomFieldService {

	@Autowired
	private ModuleRespository moduleRespository;

	@Autowired
	private LeadRespository leadRespository;

	@Autowired
	private CustomFieldRespository customFieldRespository;

	@Autowired
	private LeadCustomFieldValueRespository leadCustomFieldValueRespository;

	@Autowired
	private CustomFieldPropertzRespository customFieldPropertzRespository;

	public List<Module> getAllModules() {
		return moduleRespository.findAll();
	}

	public Optional<Module> findModule(final long id){
		return moduleRespository.findById(id);
	}

	public Module addModule(final ModuleDTO moduleDTO){
		Module module = new Module();
		module.setModuleName(moduleDTO.getModuleName());
		module.setIsActive(true);
		module.setCreatedOn(LocalDateTime.now());
		return moduleRespository.save(module);
	}

	public String deleteModule(long id){
		moduleRespository.deleteById(id);
		return moduleRespository.findById(id)==null?"Deleted Successfully":"Record not removed";
	}

	private static List<Map<?, ?>> convert(Map<?, ?> fieldLabel, List<Lead> leads) {
		try{
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(new ReplaceNamingStrategy(fieldLabel));
			TypeReference<List<Map<?, ?>>> typeReference = new TypeReference<List<Map<?, ?>>>() {};
			String result = mapper.writeValueAsString(leads);
			return mapper.readValue(result, typeReference);
		}
		catch(IOException e){
			log.error(e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}

	private static Map<?, ?> convert(Map<?, ?> fieldLabel, Lead lead) {
		try{
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(new ReplaceNamingStrategy(fieldLabel));
			TypeReference<Map<?, ?>> typeReference = new TypeReference<Map<?, ?>>() {};
			String result = mapper.writeValueAsString(lead);
			return mapper.readValue(result, typeReference);
		}
		catch(IOException e){
			log.error(e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}

	public ResponseDTO getAllLeads(String moduleName) {
		ResponseDTO responseDTO = new ResponseDTO();
		Module module = moduleRespository.findByModuleName(moduleName);
		List<CustomFieldPropertz> customFieldPropertz = customFieldPropertzRespository.findAllByModule(module);
		List<Lead> leads = leadRespository.findByIsActive(true);

		Map<?, ?> fieldLabel = customFieldLablePropertz(customFieldPropertz);
		List<Map<?, ?>> list = convert(fieldLabel, leads);

		responseDTO.put("total-records", list.size());
		responseDTO.put("data", list);
		return responseDTO;
	}

	public ResponseDTO findLead(final long id){
		ResponseDTO responseDTO = new ResponseDTO();
		Optional<Lead> lead = leadRespository.findById(id);
		Module module = lead.get().getModule();
		List<CustomFieldPropertz> customFieldPropertz = customFieldPropertzRespository.findAllByModule(module);
		responseDTO.put("field-label", customFieldLablePropertz(customFieldPropertz));
		responseDTO.put("data",lead);
		return responseDTO;
	}

	public ResponseDTO addLead(final LeadDTO leadDTO){
		ResponseDTO responseDTO = new ResponseDTO();
		Lead lead = new Lead();
		lead.setTitle(leadDTO.getTitle());
		lead.setFirstName(leadDTO.getFirstName());
		lead.setLastName(leadDTO.getLastName());
		lead.setEmail(leadDTO.getEmail());
		lead.setMobile(leadDTO.getMobile());
		lead.setLeadSource(leadDTO.getLeadSource());
		lead.setLeadStatus(leadDTO.isLeadStatus());
		lead.setAddress(leadDTO.getAddress());
		lead.setCity(leadDTO.getCity());
		lead.setState(leadDTO.getState());
		lead.setCountry(leadDTO.getCountry());
		lead.setZipcode(leadDTO.getZipcode());
		lead.setIsActive(true);
		lead.setCreatedOn(LocalDateTime.now());
		Module module = moduleRespository.getOne(leadDTO.getModuleId());
		lead.setModule(module);
		lead.setCustomField1(leadDTO.getCustomField1());
		lead.setCustomField2(leadDTO.getCustomField2());
		lead.setCustomField3(leadDTO.getCustomField3());
		lead.setCustomField4(leadDTO.getCustomField4());
		lead.setCustomField5(leadDTO.getCustomField5());
		lead.setCustomField6(leadDTO.getCustomField6());
		Lead result = leadRespository.save(lead);
		if(null != result){
			responseDTO.put("message", "Data saved successfully");
			responseDTO.put("success", true);
		}
		else{
			responseDTO.put("message", "Data not saved");
			responseDTO.put("success", false);
		}
		List<CustomFieldPropertz> customFieldPropertz = customFieldPropertzRespository.findAllByModule(module);

		Map<?, ?> fieldLabel = customFieldLablePropertz(customFieldPropertz);
		responseDTO.put("field-value", convert(fieldLabel, lead));

		return responseDTO;
	}

	public String deleteLead(long id){
		leadRespository.deleteById(id);
		return leadRespository.findById(id)==null?"Deleted Successfully":"Record not removed";
	}

	public List<CustomField> getAllCustomFields() {
		return customFieldRespository.findAll();
	}

	public Optional<CustomField> findCustomField(final long id){
		return customFieldRespository.findById(id);
	}

	public CustomField addCustomField(final CustomFieldDTO customFieldDTO){
		CustomField customField = new CustomField();
		customField.setCustomFieldName(customFieldDTO.getCustomFieldName());
		customField.setIsActive(true);
		customField.setCreatedOn(LocalDateTime.now());
		return customFieldRespository.save(customField);
	}

	public String deleteCustomField(final long id){
		customFieldRespository.deleteById(id);
		return customFieldRespository.findById(id)==null?"Deleted Successfully":"Record not removed";
	}

	public List<LeadCustomFieldValue> getAllLeadCustomFieldValues() {
		return leadCustomFieldValueRespository.findAll();
	}

	public Optional<LeadCustomFieldValue> findLeadCustomFieldValue(final long id){
		return leadCustomFieldValueRespository.findById(id);
	}

	public LeadCustomFieldValue addLeadCustomFieldValue(final LeadCustomFieldValueDTO customFieldValueDTO){
		LeadCustomFieldValue customFieldValue = new LeadCustomFieldValue();
		customFieldValue.setLeadCustomFieldName(customFieldValueDTO.getCustomFieldName());
		customFieldValue.setLeadCustomFieldValue(customFieldValueDTO.getCustomFieldValue());
		customFieldValue.setIsActive(true);
		customFieldValue.setCreatedOn(LocalDateTime.now());
		customFieldValue.setLead(leadRespository.getOne(customFieldValueDTO.getLeadId()));
		return leadCustomFieldValueRespository.save(customFieldValue);
	}

	public String deleteLeadCustomFieldValue(final long id){
		leadCustomFieldValueRespository.deleteById(id);
		return leadCustomFieldValueRespository.findById(id)==null?"Deleted Successfully":"Record not removed";
	}

	public Map<?,?> findCustomFieldPropertz(String moduleName){
		Module module = moduleRespository.findByModuleName(moduleName);
		return customPropertzObject(customFieldPropertzRespository.findAllByModule(module));
	}

	public void insertCustomFields(Field[] fields, String moduleName){
		Module module = moduleRespository.findByModuleName(moduleName);
		List<String> columnNames = Stream.of(fields)/*.filter(c -> c.getAnnotation(Custom.class) != null)*/.map(Field::getName).collect(Collectors.toList());
		List<CustomFieldPropertz> listPropertz = columnNames.stream().map(c -> new CustomFieldPropertz(c, module)).collect(Collectors.toList());
		customFieldPropertzRespository.saveAll(listPropertz);
	}

	public CustomFieldPropertz updatePropertz(CustomFieldPropertz propertz, String moduleName) {
		Module module = moduleRespository.findByModuleName(moduleName);
		propertz.setModule(module);
		return customFieldPropertzRespository.save(propertz);
	}

	private Map<?, ?> customPropertzObject(List<CustomFieldPropertz> customFieldPropertz){
		return customFieldPropertz.stream()
				//.peek(c -> log.info(c.getCustomFieldName()+"-"+c.getCustomFieldLabel()))
				.collect(Collectors.toMap(c -> c.getCustomFieldName(), c -> c));
	}

	private Map<?, ?> customFieldLablePropertz(List<CustomFieldPropertz> customFieldPropertz){
		return customFieldPropertz.stream()
				//.peek(c -> log.info(c.getCustomFieldName()+"-"+c.getCustomFieldLabel()))
				.collect(Collectors.toMap(c -> c.getCustomFieldName(), c -> c.getCustomFieldLabel()));
	}

	public void insertLeads(){
		for(int i=6788; i<100000; i++){
			LeadDTO leadDTO = new LeadDTO();
			leadDTO.setActive(true);
			leadDTO.setAddress(i+"st street");
			leadDTO.setCity("Chennai");
			leadDTO.setCountry("IND");
			leadDTO.setCustomField1("");
			leadDTO.setCustomField2("");
			leadDTO.setCustomField3("");
			leadDTO.setCustomField4("");
			leadDTO.setCustomField5("");
			leadDTO.setCustomField6("");
			leadDTO.setEmail(i+"abc@xyz.com");
			leadDTO.setFirstName("");
			leadDTO.setLastName("");
			leadDTO.setModuleId(1L);
			insertLeads(leadDTO);
		}
	}
	private void insertLeads(final LeadDTO leadDTO){
		Lead lead = new Lead();
		lead.setTitle(leadDTO.getTitle());
		lead.setFirstName(leadDTO.getFirstName());
		lead.setLastName(leadDTO.getLastName());
		lead.setEmail(leadDTO.getEmail());
		lead.setMobile(leadDTO.getMobile());
		lead.setLeadSource(leadDTO.getLeadSource());
		lead.setLeadStatus(leadDTO.isLeadStatus());
		lead.setAddress(leadDTO.getAddress());
		lead.setCity(leadDTO.getCity());
		lead.setState(leadDTO.getState());
		lead.setCountry(leadDTO.getCountry());
		lead.setZipcode(leadDTO.getZipcode());
		lead.setIsActive(true);
		lead.setCreatedOn(LocalDateTime.now());
		Module module = moduleRespository.getOne(leadDTO.getModuleId());
		lead.setModule(module);
		lead.setCustomField1(leadDTO.getCustomField1());
		lead.setCustomField2(leadDTO.getCustomField2());
		lead.setCustomField3(leadDTO.getCustomField3());
		lead.setCustomField4(leadDTO.getCustomField4());
		lead.setCustomField5(leadDTO.getCustomField5());
		lead.setCustomField6(leadDTO.getCustomField6());
		Lead result = leadRespository.save(lead);
		log.info("Lead inserted id : "+result.getLeadId()+" - "+result.getEmail());
	}
}
