package com.destinyEventScheduler.enums;

public enum Rate {

	LIKE(1),
	DISLIKE(-1),
	NEUTRAL(0);
	
	private Integer value;
	
	private Rate(Integer value){
		this.value = value;
	}
	
	public Integer getValue(){
		return value;
	}
	
	public static Rate parse(Integer rate) {
		for(Rate r: values()){
			if(r.getValue().equals(rate)){
				return r;
			}
		}
		return null;
	}
	
}
