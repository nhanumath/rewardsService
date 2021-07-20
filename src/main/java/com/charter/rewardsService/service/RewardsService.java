package com.charter.rewardsService.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.charter.rewardsService.vo.CustomerTransaction;
import com.charter.rewardsService.vo.RewardRepsponseVO;


@Service
public class RewardsService {

	
	public RewardRepsponseVO rewardCalucationsResponse(Long id, List<CustomerTransaction> customerTransactionList) {
		RewardRepsponseVO vo = new RewardRepsponseVO();
		Map<String,Long> permonthReward  = new HashMap<String, Long>();
		
	    String pattern = "yyyyMM";
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	    	
			for(CustomerTransaction transaction : customerTransactionList) {
				String month = simpleDateFormat.format(transaction.getTransactionDate());
			
				
					if(!permonthReward.containsKey(month)) {
						permonthReward.put(month, calculateRewards(transaction.getAmountSpent()));
					} else {
						permonthReward.put(month, permonthReward.get(month)+ calculateRewards(transaction.getAmountSpent()));
					}
			}
		
			vo.setCustomerId(id);
		    vo.setRewardPerMonthMap(permonthReward);
			
		    Long totalRewards = permonthReward.values().stream().mapToLong(x -> x).sum();
		    
		    vo.setRewardPointsTotal(totalRewards);
		return vo;
	}
	
	
	public List<RewardRepsponseVO> rewardCalucationsResponse(List<CustomerTransaction> customerTransactionList) { 
		
		Map<Long, List<CustomerTransaction>> transactionsPerCustomerMap = new HashMap<Long, List<CustomerTransaction>>();
		
		Set<Long> uniqueCustomerSet = customerTransactionList.stream().map(x -> x.getCustomerId()).collect(Collectors.toSet());
		
		for(Long ucs : uniqueCustomerSet) {
			List<CustomerTransaction> transactionList = customerTransactionList.stream().filter(y -> y.getCustomerId().equals(ucs)).collect(Collectors.toList());
			transactionsPerCustomerMap.put(ucs, transactionList);
		}
		List<RewardRepsponseVO> responseList = new ArrayList<RewardRepsponseVO>();
		
		transactionsPerCustomerMap.forEach((k,v) ->  {
			RewardRepsponseVO vo = rewardCalucationsResponse(k, v);
			responseList.add(vo);
		});
		
		return responseList;
	}
	public static Long calculateRewards(Double amount) {
		
		if(amount == null || amount < 50) {
			return 0l;
		} else if(amount >= 50 && amount <= 100) {
			return (Double.valueOf(amount).longValue() -50 ) * 1;
		} else if(amount > 100){
			return ((Double.valueOf(amount).longValue() - 100) * 2) + 50;
		}
		
		return 0l;
	}
	
}
