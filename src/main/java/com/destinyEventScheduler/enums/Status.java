package com.destinyEventScheduler.enums;

import com.google.common.primitives.Ints;

public enum Status {

	NEW(0),
	WAITING(1),
	VALIDATED(2);
	
	private Integer value;
	
	private Status(Integer value){
		this.value = value;
	}
	
	public Integer getValue(){
		return value;
	}
	
	public static Status parse(Integer status) {
		for(Status p: values()){
			if(p.getValue().equals(status)){
				return p;
			}
		}
		return null;
	}
	
	public static Status parse(String status) {
		return parse(Ints.tryParse(status));
	}
}
