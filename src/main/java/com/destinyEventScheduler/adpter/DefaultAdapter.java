package com.destinyEventScheduler.adpter;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultAdapter<T, E> {

	public abstract E convertToDTO(T obj);
	
	public List<E> convertToDTO(Iterable<T> objs) {
		List<E> dtos = null;
		if(objs != null){
			dtos = new ArrayList<>();
			for(T o: objs){
				dtos.add(convertToDTO(o));
			}
		}
		return dtos;
	}
	
}
