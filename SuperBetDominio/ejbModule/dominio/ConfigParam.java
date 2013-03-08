package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONFIG_PARAMS")
public class ConfigParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="PARAM_ID" ,nullable=false )
	private Integer id;
	
	@Column(name="PATH_TO_AVATARS",nullable=false)
	private String pathToAvatars;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setPathToAvatars(String pathToAvatars) {
		this.pathToAvatars = pathToAvatars;
	}

	public String getPathToAvatars() {
		return pathToAvatars;
	}
	
}
