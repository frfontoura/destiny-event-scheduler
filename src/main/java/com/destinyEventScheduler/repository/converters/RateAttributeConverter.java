package com.destinyEventScheduler.repository.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.destinyEventScheduler.enums.Rate;

@Converter(autoApply = true)
public class RateAttributeConverter implements AttributeConverter<Rate, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Rate rate) {
		return rate.getValue();
	}

	@Override
	public Rate convertToEntityAttribute(Integer rate) {
		return Rate.parse(rate);
	}

}