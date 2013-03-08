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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY")
public class Pais implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="COUNTRY_ID" ,nullable=false )
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(mappedBy="country", fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	private List<Competicion> contest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContest(List<Competicion> contest) {
		this.contest = contest;
	}

	public List<Competicion> getContest() {
		return contest;
	}

}
