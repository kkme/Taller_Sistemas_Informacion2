package superbet.rest.cliente.test;

import superbet.rest.datatypes.DataEvento;

import com.example.android.skeletonapp.RestClient;

public class TestClienteRest {
	
	public static void main(String args[]){
		
		RestClient.login("u", "u");
	/*	
		for(String de: RestClient.getEventos())  System.out.println(de+"  ");
		RestClient.setEvento(RestClient.getEventos()[0]);
		
		for(String de: RestClient.getResultados())  System.out.println(de+"  ");
		
		RestClient.setResultado(RestClient.getResultados()[0]);
		
		RestClient.apostar(100.0);*/
	}

}
