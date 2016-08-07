package com.destinyEventScheduler.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public abstract class DateUtil {

	public static LocalDateTime toUTC(LocalDateTime localDateTime, ZoneId zoneFrom){
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneFrom);
		ZonedDateTime utcDate = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
		return utcDate.toLocalDateTime();
	}
	
	public static LocalDateTime fromUTC(LocalDateTime localDateTime, ZoneId zoneTo){
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
		ZonedDateTime utcDate = zonedDateTime.withZoneSameInstant(zoneTo);
		return utcDate.toLocalDateTime();
	}
	
}
