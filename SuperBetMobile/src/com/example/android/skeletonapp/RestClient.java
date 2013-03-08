package com.example.android.skeletonapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.EditText;

import superbet.rest.cliente.MobileClient;
import superbet.rest.cliente.MobileClientImpl;
import superbet.rest.datatypes.DataEvento;
import superbet.rest.datatypes.DataResultado;

public class RestClient {
	
	private static MobileClient proxy = new MobileClientImpl();
	private static String 	host 		= "50.19.168.73:80";//Host y puerto
	private static int 		idEvento	= -1;
	private static int 		idUsuario	= -1;
	private static int 		idResultado	= -1;
	private static Map<String, Integer> eventos 	= new HashMap<String, Integer>();
	private static Map<String, Integer> resultados 	= new HashMap<String, Integer>();

	
	
	
	
	public static boolean login(String user, String pass){
		proxy = new MobileClientImpl();
		idUsuario = proxy.login(user, pass, host);
		if(idUsuario>-1){
			return true;
		}else return false;
	}

	public static String[] getEventos(){
		List<DataEvento> lista = proxy.listarEventos(host);
		String[] resultado = new String[lista.size()];
		int i =0;
		for(DataEvento de: lista){
			eventos.put(de.getNombre(), de.getId());
			resultado[i++] = de.getNombre();
		}
		return resultado;
	}
	
	public static void setEvento(String nombre){
		idEvento = eventos.get(nombre);
	}
	
	public static String[] getResultados(){
		List<DataResultado> lista = proxy.listarResultados(idEvento, host);
		String[] resultado = new String[lista.size()];
		int i=0;
		for(DataResultado res : lista){
			resultados.put(res.getDescripcion(), res.getId());
			resultado[i++]=res.getDescripcion();
		} 
		return resultado;
	}
	
	public static void setResultado(String desc){
		idResultado=resultados.get(desc);
	}

	public static String apostar(double monto) {
		return proxy.apostar(idUsuario, idEvento, idResultado, monto, host);
		
	}
	
	

}
