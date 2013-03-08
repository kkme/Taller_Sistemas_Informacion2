package negocio.apuestas.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import dominio.Apuesta;
import dominio.Combinacion;
import dominio.Competicion;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Usuario;


import persistencia.interfaces.ApuestaDAO;
import negocio.apuestas.interfaces.IApuestasUsuario;
import negocio.apuestas.interfaces.IApuestasMovil;
import negocio.apuestas.interfaces.IApuestasUsuario;
import negocio.apuestas.interfaces.IApuestasUsuarioRemote;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ControladorApuestas implements IApuestasUsuario,IApuestasUsuarioRemote ,IApuestasMovil {

	@EJB(lookup="java:global/SuperBetPersistencia/ApuestaDAOImpl!persistencia.interfaces.ApuestaDAO")
	private ApuestaDAO apuestaDAO;

	@Override
	public List<TipoApuesta> getTiposApuesta() { 
		return apuestaDAO.getTiposApuesta();
	}


	@Override
	public void altaApuesta(Apuesta apuesta, Combinacion combinacion, List<Resultado> resultados, TipoApuesta tipoApuesta, Usuario user) {
		apuestaDAO.altaApuesta(apuesta, combinacion, resultados,tipoApuesta,user);
	}
	
	@Override
	public List<TipoEvento> getEventosExternos() {
		
		return apuestaDAO.getEventosExternos();
	}


	@Override
	public void altaApuestaCombinada(Usuario usuario, Combinacion combinacion,
			List<Resultado> resultados, Apuesta apuesta) {
		 apuestaDAO.altaApuestaCombinada(usuario, combinacion, resultados, apuesta);
		
	}


	@Override
	public int altaApuestaMobile(Double monto, Integer idResultado,
			int resultadoExacto, Integer idUser) {
		 return apuestaDAO.altaApuestaMobile(monto,idResultado, resultadoExacto, idUser);
		
	}


	@Override
	public List<Apuesta> obtenerApuestasPagasUsuario(Integer idusuario) {
		
		return apuestaDAO.obtenerApuestasPagasUsuario(idusuario);
	}
	
	
	
}
