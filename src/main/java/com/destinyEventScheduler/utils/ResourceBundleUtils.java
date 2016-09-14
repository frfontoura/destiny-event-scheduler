package com.destinyEventScheduler.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


public class ResourceBundleUtils {

	private static ResourceBundle messagesEn = ResourceBundle.getBundle("messages");
	private static ResourceBundle messagesPt = ResourceBundle.getBundle("messages", new Locale("PT"));
	private static ResourceBundle messagesEs = ResourceBundle.getBundle("messages", new Locale("ES"));
	
	public static String getStringEn(String key, Object... arguments){
		return MessageFormat.format(messagesEn.getString(key), arguments);
	}
	
	public static String getStringPt(String key, Object... arguments){
		return MessageFormat.format(messagesPt.getString(key), arguments);
	}
	
	public static String getStringEs(String key, Object... arguments){
		return MessageFormat.format(messagesEs.getString(key), arguments);
	}
}
