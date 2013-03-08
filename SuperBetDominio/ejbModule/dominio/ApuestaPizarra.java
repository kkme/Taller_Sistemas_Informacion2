package dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="BLACKBOARD_BET")
public class ApuestaPizarra implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="BLACKBOARD_BET_ID" ,nullable=false )
	private Integer id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="AMOUNT")
	private Double amount;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Usuario owner;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private List<Usuario> participants;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="RESULT_ID")
	private Resultado resultado;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public Usuario getOwner() {
		return owner;
	}

	public void setParticipants(List<Usuario> participants) {
		this.participants = participants;
	}

	public List<Usuario> getParticipants() {
		return participants;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public Resultado getResultado() {
		return resultado;
	}

}
