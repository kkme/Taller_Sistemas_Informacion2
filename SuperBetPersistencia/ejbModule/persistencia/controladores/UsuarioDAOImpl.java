package persistencia.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import javax.persistence.Query;

import persistencia.interfaces.UsuarioDAO;

import utiles.ExceptionManager;
import dominio.Apuesta;
import dominio.Evento;
import dominio.ParteAvatar;
import dominio.TipoApuesta;
import dominio.Usuario;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioDAOImpl implements UsuarioDAO {

	private static final String BUSCAR_USUARIOS_POR_LOGIN_PASSWORD = "select u from Usuario u where upper(u.userName) = :login  and upper(u.password) = :password";
	
	private static final String BUSCAR_USUARIOS_POR_CEDULA = "select u from Usuario u where upper(u.id_card) = :idCardParam";
	private static final String BUSCAR_APUESTAS_DE_USUARIO = "select u.apuestas from Usuario u where u.id = :idUser";
	private static final String BUSCAR_TIPO_DE_APUESTA = "select a.tipoApuesta from Apuesta a where a.id = :idApuesta";
	private static final String OBTENER_TODOS_MODERADORES = "select m from Usuario m where m.role = 'moderador'";
	private static final String OBTENER_PARTES_AVATAR = "select pa from ParteAvatar pa where pa.usuario.id = :idUsuario order by pa.idSecuencia asc";
	
	@javax.persistence.PersistenceContext(unitName="SUPERBET_UNIT" )
	private javax.persistence.EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario insert(Usuario entity )
	{
		try
		{
			em.merge(entity );
			return entity;
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario update(Usuario entity )
	{
		try
		{
			return em.merge(entity );
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
			throw ExceptionManager.process(ex);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Usuario entity )
	{
		try
		{
			Usuario managedEntity = em.find(Usuario.class ,entity.getId() );
			em.remove(managedEntity);
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id )
	{
		try
		{
			Usuario managedEntity = em.find(Usuario.class ,id );
			em.remove(managedEntity);
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario findById(Integer id )
	{
		try
		{
			Usuario managedEntity = em.find(Usuario.class ,id );
			return managedEntity;
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Usuario> findAll()
	{
		try
		{
			javax.persistence.Query query = em.createQuery("select o from Usuario o" );
			return query.getResultList();
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Usuario> buscarUsuarioPorLoginPassword(String login ,String password )
	{
		try
		{
			javax.persistence.Query query = em.createQuery(BUSCAR_USUARIOS_POR_LOGIN_PASSWORD);
			query.setParameter("login" ,login );
			query.setParameter("password" ,password );
			return query.getResultList();
		}
		catch (Throwable ex)
		{
			throw ExceptionManager.process(ex);
		}

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario findByEmail(String usermail) throws Exception{
		try{
			Usuario s;
			javax.persistence.Query query = em.createQuery("select u from Usuario u where upper(u.email) = :mailParam");
			query.setParameter("mailParam", usermail.toUpperCase());
			s= (Usuario) query.getSingleResult();
			return s;
		}
		catch (javax.persistence.NoResultException ex)
		{//tomo una runtime exception y tiro un checked exception "No existe usuario con ese mail"
			throw new Exception("No existe usuario con ese mail");
		}catch (Exception e){
			e.printStackTrace();
			throw new Exception("Error al buscar usuario por mail");
		}
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario findByCedula(String cedula) throws Exception{
		try{
			Usuario s;
			javax.persistence.Query query = em.createQuery(BUSCAR_USUARIOS_POR_CEDULA);
			query.setParameter("idCardParam", cedula.toUpperCase());
			s = (Usuario) query.getSingleResult();
			return s;
		}
		catch (javax.persistence.NoResultException ex)
		{//tomo una runtime exception y tiro un checked exception "No existe usuario con ese mail"
			throw new Exception("No existe usuario con ese mail");
		}catch (Exception e){
			e.printStackTrace();
			throw new Exception("Error al buscar usuario por mail");
		}
	}

	@Override
	public double getMonto(int id_user) {
		Usuario user = em.find(Usuario.class, id_user);
		return user.getMonto();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Apuesta> getApuestas(Usuario logedUser) {
		Query q = em.createQuery(BUSCAR_APUESTAS_DE_USUARIO);
		q.setParameter("idUser", logedUser.getId());
		List<Apuesta> apuestas = q.getResultList();
		
		for(Apuesta apuesta:apuestas){
			Query q2 = em.createQuery(BUSCAR_TIPO_DE_APUESTA);
			q2.setParameter("idApuesta", apuesta.getId());
			apuesta.setTipoApuesta((TipoApuesta) q2.getSingleResult());
		}
		
		return (ArrayList<Apuesta>) apuestas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> obtenerModeradores() {
		List<Usuario> moderadores = null;
		try {
			Query q = em.createQuery(OBTENER_TODOS_MODERADORES);
			moderadores = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moderadores;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void altaNuevaParteAvatar(Usuario logedUser, int idSec, String part) {
		try {
			ParteAvatar pAvatar = new ParteAvatar();
			pAvatar.setUsuario(logedUser);
			pAvatar.setIdSecuencia(idSec);
			pAvatar.setContParteAvatar(part);
			em.persist(pAvatar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<String> obtenerPartesAvatar(Usuario logedUser) {
		List<String> ret = null; 
		try {
			Query q = em.createQuery(OBTENER_PARTES_AVATAR);
			q.setParameter("idUsuario", logedUser.getId());
			List<ParteAvatar> pAvatars = q.getResultList();
			if (pAvatars != null) {
				ret = new ArrayList<String>();
				for (ParteAvatar pa : pAvatars) {
					ret.add(pa.getContParteAvatar());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarAvatar(Usuario logedUser) {
		try {
			Query q = em.createQuery(OBTENER_PARTES_AVATAR);
			q.setParameter("idUsuario", logedUser.getId());
			List<ParteAvatar> pAvatars = q.getResultList();
			if (pAvatars != null) {
				for (ParteAvatar pa : pAvatars) {
					em.remove(pa);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean existeMail(String email) {
		Usuario s=null;
		javax.persistence.Query query = em.createQuery("select u from Usuario u where upper(u.email) = :mailParam");
		query.setParameter("mailParam", email.toUpperCase());
		try{
		s= (Usuario) query.getResultList().get(0);
		}catch(javax.ejb.EJBTransactionRolledbackException e){
			s=null;
		}catch(java.lang.IndexOutOfBoundsException e){
			s=null;
		}
		return s!=null;
	}
}