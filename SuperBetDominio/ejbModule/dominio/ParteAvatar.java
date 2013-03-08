package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AVATAR_PARTS")
public class ParteAvatar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AVATAR_PART_ID", nullable=false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID",nullable=true)
	private Usuario usuario;
	
	@Column(name="ID_SECUENCIA")
	private Integer idSecuencia;
	
	@Column(name="CONT_PARTE_AVATAR")
	private String contParteAvatar;

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setIdSecuencia(Integer idSecuencia) {
		this.idSecuencia = idSecuencia;
	}

	public Integer getIdSecuencia() {
		return idSecuencia;
	}

	public void setContParteAvatar(String contParteAvatar) {
		this.contParteAvatar = contParteAvatar;
	}

	public String getContParteAvatar() {
		return contParteAvatar;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	} 
	
	
	
}
