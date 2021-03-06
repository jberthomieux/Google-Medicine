package com.jackson.controller;

import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jackson.dto.BbSearchResponse;
import com.jackson.model.*;
import com.jackson.service.*;


@CrossOrigin(origins="*",maxAge=3600)
@RestController
@RequestMapping("/bloodbank")
public class BloodBankController {
@Autowired
private BloodBankService bloodBankService;

//private Map map = new Map(Member);

//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/bloodbanks",method=RequestMethod.GET)
	public List<BloodBank> listMember(){
		return bloodBankService.getAllBloodBanks();
	 }
	@RequestMapping(value="/nanme/{name}" ,method=RequestMethod.GET)
	public List<BloodBank> searchBloodBankByType(@PathVariable(value="type") String type){
		return bloodBankService.getBloodBankByType(type);
	 }
	@RequestMapping(value="/type/{type}",method=RequestMethod.GET)
    public List<BbSearchResponse> result(@PathVariable(value="type") String type){
	return bloodBankService.searchResult(type);
    }
	
	//@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/bloodbank/{id}",method= RequestMethod.GET)
	public BloodBank getOne(@PathVariable(value="id") int id) {
		return bloodBankService.getBloodBankById(id);
		}
	
	@RequestMapping(value="/add/{id}",method= RequestMethod.POST)
	public List<BloodBank> addBloodBank(@RequestBody BloodBank bloodBank,@PathVariable(value="id") int id) {
		bloodBankService.saveOrUpdate(bloodBank,id);
         return bloodBankService.getAllBloodBanks();
	}
	
	@RequestMapping(value="/update/{id}",method= RequestMethod.PUT)
	public BloodBank updateBloodBank(@RequestBody BloodBank bloodBank, @PathVariable int id) {

		BloodBank newbloodBank=bloodBank;
		
		bloodBank=bloodBankService.getBloodBankById(id);
		//bloodBank.setLocation(newbloodBank.getLocation());
		bloodBank.setType(newbloodBank.getType());
		bloodBank.setPrice(newbloodBank.getPrice());
		bloodBank.setQty(newbloodBank.getQty());
		bloodBankService.saveOrUpdate(bloodBank,id);
		return bloodBankService.getBloodBankById(id);
		
    }
		
	@RequestMapping(value="/delete/{id}",method= RequestMethod.DELETE)
	public List<BloodBank> deleteById(@PathVariable(value="id") int id) {
		bloodBankService.delete(id);
		return bloodBankService.getAllBloodBanks();
	}
}

