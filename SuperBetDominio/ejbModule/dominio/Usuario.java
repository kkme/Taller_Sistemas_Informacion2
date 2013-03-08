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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID", nullable=false)
	private Integer id;

	@Column(name="USERNAME", length=100,unique=true, nullable=false)//Fata agregar que sea unico
	private String userName;

	@Column(name="PASSWORD", nullable=false,unique=true)
	private String password;
	
	@Column(name="ROLE", nullable=false)
	private String role;
	
	@Column(name="STATE", nullable=false)
	private String state;
	
	@Column(name="NAMES")
	private String names;
	
	@Column(name="SURNAMES")
	private String surnames;
	
	@Column(name="ID_CARD")
	private String id_card;
	
	@Column(name="PHOTOURL")
	private String photoUrl;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="CITY")
	private String city;
	
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="AMOUNT")
	private double monto;
	
	
	@Column(name="EMAIL",unique=true)
	private String email;
	
	@OneToMany(mappedBy="owner", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	private List<ApuestaPizarra> pizarra;
	
	@ManyToMany(mappedBy="participants", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	private List<ApuestaPizarra> apuestasPizarra;

	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	private List<Apuesta> apuestas;
	
	@OneToMany(mappedBy="usuario", cascade= CascadeType.ALL)
	private List<ParteAvatar> partesAvatar;
	
	public Usuario()
	{
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getNames() {
		return names;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getId_card() {
		return id_card;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPizarra(List<ApuestaPizarra> pizarra) {
		this.pizarra = pizarra;
	}
	
	public List<ApuestaPizarra> getPizarra() {
		return pizarra;
	}

	public void setApuestasPizarra(List<ApuestaPizarra> apuestasPizarra) {
		this.apuestasPizarra = apuestasPizarra;
	}

	public List<ApuestaPizarra> getApuestasPizarra() {
		return apuestasPizarra;
	}

	public void setApuestas(List<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public List<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getMonto() {
		return monto;
	}
	
	
	public void agregarMontoCuenta(double montoparam) {
		monto+=montoparam;
	}
	
	public void quitarMontoCuenta(double montoparam) {
		monto-=montoparam;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPartesAvatar(List<ParteAvatar> partesAvatar) {
		this.partesAvatar = partesAvatar;
	}

	public List<ParteAvatar> getPartesAvatar() {
		return partesAvatar;
	}
}