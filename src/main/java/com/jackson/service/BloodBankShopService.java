package com.jackson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackson.model.BloodBankShop;
import com.jackson.repo.BloodBankShopRepo;

@Service
public class BloodBankShopService {
	@Autowired 	
	BloodBankShopRepo repo; 	
	public List<BloodBankShop> getAllbloodbank() { 
		List<BloodBankShop> list = new ArrayList<>(); 
		repo.findAll().forEach(bbs -> list.add(bbs));
		return list; 
		} 	
	
	public BloodBankShop getBloodBankShoopById(int id) {
		return repo.findById(id).get(); 
		}	 	
	
	public void saveOrUpdate(BloodBankShop bloodBankShop) { 
		repo.save(bloodBankShop); 	
		}	 
	
	public void delete(int id) { 
		repo.deleteById(id); 
		} 	 
	
	public void update(BloodBankShop bloodBankShoop,int id) { 
		repo.save(bloodBankShoop); 
		} 
}