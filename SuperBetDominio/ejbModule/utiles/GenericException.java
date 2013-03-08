package utiles;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class GenericException extends RuntimeException {

	private boolean logged;
	
	public GenericException() {
		super();
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(Throwable inner) {
		super(inner);
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// Utility methods
	////////////////////////////////////////////////////////////////////////////////////////////////////

	public static String getStackTrace(Throwable e) {
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		PrintWriter printWriter = new PrintWriter(byteArrayOut);
		e.printStackTrace(printWriter);
		printWriter.flush();
		return byteArrayOut.toString();
	}

	public static GenericException rethrow(Throwable e) {
		if (e instanceof GenericException) {
			return (GenericException)e;
		} else {
			// Wrap the current exception into a new GenericException
			GenericException aex = new GenericException(e);
			return aex;
		}
	}

}
