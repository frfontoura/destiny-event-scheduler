package com.destinyEventScheduler.enums;

import com.google.common.primitives.Ints;

public enum Platform {

	LIVE(1), PSN(2);
	
	private Integer value;
	
	private Platform(Integer value){
		this.value = value;
	}
	
	public Integer getValue(){
		return value;
	}

	public static Platform parse(Integer platform) {
		for(Platform p: values()){
			if(p.getValue().equals(platform)){
				return p;
			}
		}
		return null;
	}
	
	public static Platform parse(String platform) {
		return parse(Ints.tryParse(platform));
	}
}
