package utiles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class Constantes {
	public static Double LATITUD_INICIAL = -34.876918;
	public static Double LONGITUD_INICIAL = -56.185684;
	public static final int PAY_PAL_MAXRECEIVERS = 6;
	public static final String PAY_PAL_EMAIL_BANCA_SUPER_BET = "tsi204_1316180228_biz@gmail.com";
	public static final String EMAIL_SUPER_BET = "tsi204.2011@gmail.com";
	
	public static final String DOMINIO(){
		String publicIP = null;
		try {
            URL tempURL = new URL("http://www.whatismyip.org/");
            HttpURLConnection tempConn = (HttpURLConnection)tempURL.openConnection();
            InputStream tempInStream = tempConn.getInputStream();
            InputStreamReader tempIsr = new InputStreamReader(tempInStream);
            BufferedReader tempBr = new BufferedReader(tempIsr);        

            publicIP = tempBr.readLine();

            tempBr.close();
            tempInStream.close();

		} catch (Exception ex) {
            publicIP = "<No es posible resolver la direccion IP>";  
		}

		System.out.println("Mi IP Publica es " +publicIP);
		return publicIP;
	};
	
	public static final String PUERTO ="80";
	
	
	public static final Object APUESTA_SIMPLE = "Apuesta Simple";
	public static final int NUM_NOTICIAS_POR_FUENTE = 10;
	public static final Integer MMEDIA_VIDEO = 1;
	public static final Integer MMEDIA_IMG = 2;
	public static final int MAX_NUM_VIDEOS = 4;
	public static final int MAX_VIDEOS_POR_EVENTO = 12;
	public static final int RESULTADO_EXACTO=7;
	public static final String REL_PATH_TO_AVATARS = "/images/avatars/";
	public static final int TAM_FILE_BUFF = 250; 
	public static final String URL_TO_DEFAULT_PHOTO = "/resources/default_image.jpg";
	
	// roles de usuario
	public static final String ROL_MOD = "moderador";
	public static final String ROL_USUARIO = "usuario";
	public static final String ROL_ADMIN = "administrador";
	
	// estados de usuario
	public static final String ESTADO_REG = "registered";
	public static final String ESTADO_EXTERN = "extern";
	

	
	public static void main(String args[]){

		
	}
	

}
