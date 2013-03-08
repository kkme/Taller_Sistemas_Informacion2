package utiles;

public class ExceptionManager {

	public static GenericException process(Throwable ex) {
		
		// If it is already a GenericException, return it as it is 
		if (ex instanceof GenericException) {
			GenericException exCaptured = (GenericException)ex;
			if (!exCaptured.isLogged())
			{
				LoggingUtiles.error(ex);
				exCaptured.setLogged(true);
			}
			return exCaptured;
		}
		  
		// Start evaluating different scenarios
		if (ex != null && ex.getCause() instanceof javax.persistence.PersistenceException) {
			Throwable cause = ex.getCause().getCause();
			
			if (cause != null) {
				String genericCause = cause.getClass().getName();
				if (genericCause.indexOf("ConstraintViolationException") != -1) {
					GenericException retex = new GenericException("Error de integridad de datos");
					
					LoggingUtiles.error(ex);
					retex.setLogged(true);
					
					return retex;
				}
			}
		}
		
		 if( ex instanceof javax.persistence.NoResultException)
			{
				GenericException retex = new GenericException("No se encontraron datos");
				
				LoggingUtiles.error(ex);
				retex.setLogged(true);
				
				return retex;
			}
		
		if (ex instanceof javax.persistence.PersistenceException)
		{
			GenericException retex = new GenericException("Error de persistencia");
			
			LoggingUtiles.error(ex);
			retex.setLogged(true);
			
			return retex;
		}
		
		// Throw an empty generic exception. These will trigger the default error message
		GenericException unknowException = new GenericException();
		
		LoggingUtiles.error(ex);
		unknowException.setLogged(true);
		
		return unknowException;
	}
	
}
