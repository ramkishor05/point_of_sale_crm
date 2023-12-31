package com.brijframework.crm.controller;

import static com.brijframework.crm.contants.Constants.OWNER_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.crm.dto.UIBusiness;
import com.brijframework.crm.dto.UIBusinessDetail;
import com.brijframework.crm.service.BusinessService;

@RestController
@RequestMapping("api/business")
public class BusinessController {

	@Autowired
	BusinessService businessService;
	
	@PostMapping
	public UIBusiness addBusiness(@RequestHeader(OWNER_ID) long ownerId, @RequestBody UIBusiness uiBusiness) {
		return businessService.saveBusiness(ownerId,uiBusiness);
	}
	
	@PutMapping
	public UIBusiness updateBusiness(@RequestHeader(OWNER_ID) long ownerId,@RequestBody UIBusiness uiBusiness) {
		return businessService.saveBusiness(ownerId, uiBusiness);
	}
	
	@GetMapping("/vendors")
	public List<UIBusiness> getVendorBusinessDetailList(@RequestHeader(OWNER_ID) long ownerId) {
		return businessService.getBusinessList(ownerId);
	}
	
	@GetMapping
	public List<UIBusiness> getBusinessDetailList(@RequestHeader(OWNER_ID) long ownerId) {
		return businessService.getBusinessList(ownerId);
	}
	
	@DeleteMapping("/{businessId}")
	public boolean deleteBusiness(@PathVariable("businessId") long businessId) {
		return businessService.deleteBusiness(businessId);
	}
	
	@GetMapping("/{businessId}")
	public UIBusiness getBusiness(@PathVariable("businessId") long businessId) {
		return businessService.getBusiness(businessId);
	}
	
	@GetMapping("/{businessId}/detail")
	public UIBusinessDetail getBusinessDetail(@PathVariable("businessId") long businessId) {
		return businessService.getBusinessDetail(businessId);
	}
	
	
}
