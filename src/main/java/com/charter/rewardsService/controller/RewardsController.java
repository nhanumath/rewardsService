package com.charter.rewardsService.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping("/rewards")
public class RewardsController {
	
	Logger log = LoggerFactory.getLogger(RewardsController.class);

	@Autowired
	public RestTemplate restTemplate;
	
	@Autowired
	public RewardsService service;
	
	
	@GetMapping("/findRewardsByCustomerId/{id}")
	public RewardRepsponseVO calculateRewardPerMonth(@PathVariable Long id) {
		
		log.info("Entering into calculateRewardPerMonth::" + id);
		ResponseEntity<CustomerTransaction[]> response =
				  restTemplate.getForEntity("http://localhost:9002/custTransaction/getLast3MonthsTransactionsByCustomer/"+id, CustomerTransaction[].class);
		CustomerTransaction[] threeMonthRecords = response.getBody();
		List<CustomerTransaction> customerTransactionList =  Arrays.stream(threeMonthRecords).collect(Collectors.toList());
	
		log.info("Entering into calculateRewardPerMonth::" + id);
		return service.rewardCalucationsResponse(id, customerTransactionList);
	}

	
	@GetMapping("/findAllCustomerRewards")
	public List<RewardRepsponseVO> calculateRewardPerMonth() {
		
		ResponseEntity<CustomerTransaction[]> response =
				  restTemplate.getForEntity("http://localhost:9002/custTransaction/getLast3MonthsTransactions/", CustomerTransaction[].class);
		CustomerTransaction[] threeMonthRecords = response.getBody();
		List<CustomerTransaction> customerTransactionList =  Arrays.stream(threeMonthRecords).collect(Collectors.toList());
		
		return service.rewardCalucationsResponse(customerTransactionList);
	}
	
	
	
	
	
}
