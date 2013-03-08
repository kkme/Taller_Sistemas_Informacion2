package superbet.rest.cliente;

import java.util.ArrayList;
import java.util.List;

import superbet.rest.datatypes.DataEvento;
import superbet.rest.datatypes.DataResultado;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpVersion;
import org.apache.http.protocol.HTTP;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpPut;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class MobileClientImpl implements MobileClient {
	
	private final HttpClient httpClient;
	
	public MobileClientImpl() {
        BasicHttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, false);
        httpClient = new DefaultHttpClient(params);
    }
	
	@Override
	public Integer login(String user, String passwd, String host) {
		System.out.println("MobileClient::login "+" "+user+" "+passwd+" "+host);
		
        String content = put("login?user=" + user + "&pass=" + passwd, host);
        if (content != null) {
        	return Integer.parseInt(content);
         }
        return null;
	}

	@Override
	public List<DataEvento> listarEventos(String host) {
		System.out.println("MobileClient:: listarEventos "+host);
		
		List<DataEvento> result = new ArrayList<DataEvento>();
        String content = get("eventos", host);
        if (content != null) {
            try {
                JSONTokener tokener = new JSONTokener(content);
                JSONArray array = (JSONArray) tokener.nextValue();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    Integer id = Integer.parseInt(obj.getString("id"));
                    String nombre = obj.getString("nombre");
                    DataEvento de = new DataEvento();
                    de.setId(id);
                    de.setNombre(nombre);
                    result.add(de);
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return result;
	}

	@Override
	public List<DataResultado> listarResultados(Integer idEvento, String host) {
		System.out.println("MobileClientImpl:: listarResultados evento"+idEvento+" "+host);
		
		List<DataResultado> result = new ArrayList<DataResultado>();
        String content = get(idEvento + "/resultados", host);
        if (content != null) {
            try {
                JSONTokener tokener = new JSONTokener(content);
                JSONArray array = (JSONArray) tokener.nextValue();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    Integer id = Integer.parseInt(obj.getString("id"));
                    String descripcion = obj.getString("desc");
                    Double cuota = Double.parseDouble(obj.getString("cuota"));
                    DataResultado dr = new DataResultado();
                    dr.setId(id);
                    dr.setDescripcion(descripcion);
                    dr.setCuota(cuota);
                    result.add(dr);
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return result;
	}

	@Override
	public String apostar(Integer idUser, Integer idEvento,
						  Integer idResultado, Double monto, String host) {
		System.out.println("MobileClientImpl:: "+idUser+" "+idEvento+" "+idResultado+" "+monto+" "+host);
		
        String content = put("apostar?user=" + idUser + "&idEvento=" + idEvento + "&result=" + idResultado + "&monto=" + monto, host);
        if (content != null) {
           return content;
        }
        return null;
	}
	
	private String get(String path, String host) {
        try {
            HttpGet request = new HttpGet(getRequestURI(path, host));
            HttpResponse res = httpClient.execute(request);
            String content = EntityUtils.toString(res.getEntity());
            return content;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	
	private String put(String path, String host) {
        try {
            HttpPut request = new HttpPut(getRequestURI(path, host));
            HttpResponse res = httpClient.execute(request);
            String content = EntityUtils.toString(res.getEntity());
            return content;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	
	private URI getRequestURI(String path, String host) throws URISyntaxException {
        URI requestURI = new URI("http://" +  host  +"/SuperBetService/rest/" + path);
        
        System.out.println("MobileClientImpl:: getRequestURI "+requestURI);
        return requestURI;
    }
}
