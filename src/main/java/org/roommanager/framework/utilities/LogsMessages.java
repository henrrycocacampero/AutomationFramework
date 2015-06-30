package org.roommanager.framework.utilities;

import org.apache.log4j.Logger;

public class LogsMessages {
	
	static Logger log = Logger.getLogger(LogsMessages.class.getName());
	 
	public static void info(String message){
		   log.info(message);
	}
}
