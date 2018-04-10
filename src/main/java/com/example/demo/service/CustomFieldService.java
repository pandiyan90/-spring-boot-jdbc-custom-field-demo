package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomFieldDTO;
import com.example.demo.dto.LeadCustomFieldValueDTO;
import com.example.demo.dto.LeadDTO;
import com.example.demo.dto.ModuleDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.CustomField;
import com.example.demo.model.LeadCustomFieldValue;
import com.example.demo.model.Lead;
import com.example.demo.model.Module;
import com.example.demo.respository.CustomFieldRespository;
import com.example.demo.respository.LeadCustomFieldValueRespository;
import com.example.demo.respository.LeadRespository;
import com.example.demo.respository.ModuleRespository;

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
	
	public List<Module> getAllModules() {
		return moduleRespository.findAll();
	}

	public Optional<Module> findModule(final long id){
		return moduleRespository.findById(id);
	}

	public Module addModule(final ModuleDTO moduleDTO){
		Module module = new Module();
		module.setModuleName(moduleDTO.getModuleName());
		module.setActive(true);
		module.setCreatedOn(LocalDateTime.now());
		return moduleRespository.save(module);
	}

	public String deleteModule(long id){
		moduleRespository.deleteById(id);
		return moduleRespository.findById(id)==null?"Deleted Successfully":"Record not removed";
	}
	
	public List<Lead> getAllLeads() {
		return leadRespository.findAll();
	}

	public Optional<Lead> findLead(final long id){
		return leadRespository.findById(id);
	}

	public Lead addLead(final LeadDTO leadDTO){
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
		lead.setActive(true);
		lead.setCreatedOn(LocalDateTime.now());
		lead.setModule(moduleRespository.getOne(leadDTO.getModuleId()));
/*		Iterator<Long> iterator = leadDTO.getCustomFieldIds().iterator();
		List<CustomField> lists = new ArrayList<>();
		while(iterator.hasNext()) {
			CustomField customField = customFieldRespository.getOne(iterator.next());
			lists.add(customField);
		}
		lead.setCustomFields(new HashSet<CustomField>(lists));
*/		return leadRespository.save(lead);
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
		customField.setActive(true);
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
		customFieldValue.setActive(true);
		customFieldValue.setCreatedOn(LocalDateTime.now());
		customFieldValue.setLead(leadRespository.getOne(customFieldValueDTO.getLeadId()));
		return leadCustomFieldValueRespository.save(customFieldValue);
	}

	public String deleteLeadCustomFieldValue(final long id){
		leadCustomFieldValueRespository.deleteById(id);
		return leadCustomFieldValueRespository.findById(id)==null?"Deleted Successfully":"Record not removed";
	}

	public ResponseDTO getLeads(String startDate, String endDate) {
		ResponseDTO responseDTO = new ResponseDTO();
		LocalDateTime s = LocalDateTime.of(2018, 4, 9, 23, 00);
		LocalDateTime e = LocalDateTime.of(2018, 4, 11, 23, 00);
		Set<String> customFields = leadCustomFieldValueRespository.findCustomField(s, e);
		log.info("CustomFields: "+customFields);
		List<Lead> leads = leadRespository.findAll();
		for(Lead lead : leads) {
			List<LeadCustomFieldValue> leadCustomFieldValues = lead.getLeadCustomFieldValue();
			for(LeadCustomFieldValue leadCustomFieldValue : leadCustomFieldValues) {
				if("".equals(leadCustomFieldValue.getLeadCustomFieldName())) {
					
				}
			}
		}
		return responseDTO;
	}

}
