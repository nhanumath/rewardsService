package com.charter.rewardsService.vo;

import java.util.Map;

public class RewardRepsponseVO {
	
	private Long customerId;
	private Map<String, Long> rewardPerMonthMap;
	private Long rewardPointsTotal;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getRewardPointsTotal() {
		return rewardPointsTotal;
	}
	public void setRewardPointsTotal(Long rewardPointsTotal) {
		this.rewardPointsTotal = rewardPointsTotal;
	}
	public Map<String, Long> getRewardPerMonthMap() {
		return rewardPerMonthMap;
	}
	public void setRewardPerMonthMap(Map<String, Long> rewardPerMonthMap) {
		this.rewardPerMonthMap = rewardPerMonthMap;
	}
	
	

}
