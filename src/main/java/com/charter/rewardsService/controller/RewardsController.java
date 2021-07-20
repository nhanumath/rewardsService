package com.charter.rewardsService.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.charter.rewardsService.service.RewardsService;
import com.charter.rewardsService.vo.CustomerTransaction;
import com.charter.rewardsService.vo.RewardRepsponseVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rewards")
@Slf4j
public class RewardsController {

	@Autowired
	public RestTemplate restTemplate;
	
	@Autowired
	public RewardsService service;
	
	
	@GetMapping("/findRewardsByCustomerId/{id}")
	public RewardRepsponseVO calculateRewardPerMonth(@PathVariable Long id) {
		ResponseEntity<CustomerTransaction[]> response =
				  restTemplate.getForEntity("http://localhost:9002/custTransaction/getLast3MonthsTransactionsByCustomer/"+id, CustomerTransaction[].class);
		CustomerTransaction[] threeMonthRecords = response.getBody();
		List<CustomerTransaction> customerTransactionList =  Arrays.stream(threeMonthRecords).collect(Collectors.toList());
	
		return service.rewardCalucationsResponse(id, customerTransactionList);
	}

	
	@GetMapping("/findAllCustomerRewards/{id}")
	public List<RewardRepsponseVO> calculateRewardPerMonth() {
		
		ResponseEntity<CustomerTransaction[]> response =
				  restTemplate.getForEntity("http://localhost:9002/custTransaction/getLast3MonthsTransactions/", CustomerTransaction[].class);
		CustomerTransaction[] threeMonthRecords = response.getBody();
		List<CustomerTransaction> customerTransactionList =  Arrays.stream(threeMonthRecords).collect(Collectors.toList());
		
		return service.rewardCalucationsResponse(customerTransactionList);
	}
	
	
	
	
	
}
