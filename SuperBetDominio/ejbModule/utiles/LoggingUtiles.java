package utiles;

import org.apache.log4j.Logger;

public class LoggingUtiles {

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Log4J logger
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public enum Level {
		INFO,
		WARN,
		ERROR,
		DEBUG,
		FATAL
	}

	static Logger logger = Logger.getLogger("application");

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Simple message logging
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void debug(String message) {
		try{
			logger.debug(message);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static void error(String message) {
		try {
			logger.error(message);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static void error(Throwable error) {
		try {
			exception(error);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	public static void fatal(String message) {
		try {
			logger.fatal(message);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static void warn(String message) {
		try {
			logger.warn(message);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static void info(String message) {
		try {
			logger.info(message);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isLoggingEnabledFor(Level level) {
		if (level == Level.DEBUG)
			return logger.isEnabledFor(org.apache.log4j.Level.DEBUG);
		if (level == Level.ERROR)
			return logger.isEnabledFor(org.apache.log4j.Level.ERROR);
		if (level == Level.FATAL)
			return logger.isEnabledFor(org.apache.log4j.Level.FATAL);
		if (level == Level.INFO)
			return logger.isEnabledFor(org.apache.log4j.Level.INFO);
		if (level == Level.WARN)
			return logger.isEnabledFor(org.apache.log4j.Level.WARN);

		return false;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Exception logging
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void exception(Throwable e) {
		try {
			if (e instanceof GenericException) {
				GenericException aex = (GenericException)e;

				StringBuffer sb = new StringBuffer();
				sb.append(aex.getLocalizedMessage());
				sb.append("\n");
				sb.append(GenericException.getStackTrace(aex));
				sb.append("\n");

				logger.error(sb.toString());
			} else {
				logger.error(GenericException.getStackTrace(e));
			}
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

}
