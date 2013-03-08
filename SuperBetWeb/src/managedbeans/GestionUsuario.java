package managedbeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;

import negocio.usuarios.interfaces.IUsuarios;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.validator.Email;
import org.jboss.wsf.spi.annotation.WebContextImpl;
import org.primefaces.event.FileUploadEvent;

import paypal.examples.common.JsonHelper;
import paypal.examples.common.RequestHelper;
import sun.misc.BASE64Encoder;
import utiles.Constantes;
import utiles.MessagePrinter;
import utiles.Mailer;

import dominio.Apuesta;
import dominio.Usuario;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class GestionUsuario extends MessagePrinter {
	@EJB(lookup = "java:global/SuperBetNegocio/ControladorUsuarios!negocio.usuarios.interfaces.IUsuarios")
	private IUsuarios usrNegocio;

	@ManagedProperty(value = "#{coleccionesBean}")
	private ColeccionesBean coleccionesBean;
	
	public void setColeccionesBean (ColeccionesBean coleccionesBean) {
		this.coleccionesBean = coleccionesBean;
	}
   
	private Usuario logedUser;
	private Usuario newUser;

	private double txtMonto;
	private double txtMontoDepositar;
	
	private String confirmDocInput;
	
	private String confirmPassInput;
	
	private String newPassword;
	private String oldPassword;
	private String selectedPais;
	
	private String base64String = null; 
	private String base64StringEnMem = "";

	@Email
	private String txtEmailPagador = "tsi204_1316180327_per@gmail.com";

	@Email
	private String txtEmailCobrador = "tsi204_1316180327_per@gmail.com";
	
	private ArrayList<Apuesta> apuestas;
	
	public GestionUsuario () {
	}

	public void onSelectPais(ValueChangeEvent changeEvent) {
		System.out.println(changeEvent.getOldValue() + " cambia por "
				+ changeEvent.getNewValue());
		selectedPais = (String) changeEvent.getNewValue();
		
	}
	
	public String onGuardarClick(){
		if((logedUser.getEmail() == null) || (logedUser.getEmail().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su email");
		} else	if((logedUser.getEmail() != null) && (logedUser.getEmail().length()!= 0) && (!java.util.regex.Pattern.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", logedUser.getEmail()))){
				MessagePrinter.showErrorMessage("Formato de email incorrecto");
		} else if((logedUser.getPhone() == null) || (logedUser.getPhone().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su numero de telefono o celular");
		} else if((logedUser.getCity() == null) || (logedUser.getCity().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su ciudad");
		} else if((logedUser.getUserName() == null) || (logedUser.getUserName().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su nombre de usuario");
		} else if((oldPassword != null) && (oldPassword.length() != 0)){
			if((newPassword != null) && (newPassword.length()!= 0) && (!confirmPassInput.equals(newPassword))){
				MessagePrinter.showErrorMessage("La contrase�a nueva no coincide con la confirmaci�n de la misma");
			} else if((newPassword != null) && (newPassword.length()!= 0) && (confirmPassInput.equals(newPassword))){
				logedUser.setPassword(newPassword);
				logedUser.setCountry(selectedPais);
				usrNegocio.updateUser(logedUser);
			} else {
				usrNegocio.updateUser(logedUser);
			}
		} else {
			if (base64StringEnMem != null) {
				usrNegocio.eliminarAvatar(logedUser);
				usrNegocio.altaBase64Avatar(logedUser,base64StringEnMem);
			}
			
			usrNegocio.updateUser(logedUser);
		}
		return "/views/public/usuarios/perfil.xhtml";
	}
	
	public String altaUsuario() {
		System.out.print("altaUsuario");
		if((newUser.getNames() == null) || (newUser.getNames().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su nombre en el campo nombres");
		} else if((newUser.getSurnames() == null) || (newUser.getSurnames().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su apellido en el campo apellidos");
		} else if((newUser.getId_card() == null) || (newUser.getId_card().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese un n�mero de documento");
		} else if((newUser.getId_card().length() != 8) || (!java.util.regex.Pattern.matches("[0-9]+", newUser.getId_card()))){
			MessagePrinter.showErrorMessage("Formato de documento incorrecto");
		} else if(!newUser.getId_card().equals(confirmDocInput)){
			MessagePrinter.showErrorMessage("La confirmacion del documento tiene que ser igual al ingresado en el campo documento");
		} else if (confirmDocInput == null || confirmDocInput == ""){
			MessagePrinter.showErrorMessage("Por favor ingrese el n�mero de confirmaci�n del documento.");
		} else if((newUser.getEmail() == null) || (newUser.getEmail().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su email");
		} else if((newUser.getEmail() != null) && (newUser.getEmail().length()!= 0) && (!java.util.regex.Pattern.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", newUser.getEmail()))){
			MessagePrinter.showErrorMessage("Formato de email incorrecto");
		} else if((newUser.getPhone() == null) || (newUser.getPhone().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su numero de telefono o celular");
		} else if((selectedPais == null) || (selectedPais.length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese el pais donde reside");
		} else if((newUser.getCity() == null) || (newUser.getCity().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese la ciudad donde reside");
		} else if((newUser.getUserName() == null) || (newUser.getUserName().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese su nombre de usuario");
		} else if((newUser.getPassword() == null) || (newUser.getPassword().length()== 0)){
			MessagePrinter.showErrorMessage("Por favor ingrese la contrase�a de su cuenta");
		} else if (!newUser.getPassword().equals(confirmPassInput)){
			MessagePrinter.showErrorMessage("La confirmacion de la contrase�a tiene que ser igual al ingresado en el campo contrase�a");
		} else if(usrNegocio.existeMail(newUser.getEmail())){
			MessagePrinter.showInformationMessage("Correo ya registrado o esperando confirmación");
		}else{
				newUser.setRole("usuario");
				newUser.setState("aconfirmar");
				newUser.setCountry(selectedPais);
				newUser.setPhotoUrl("/resources/default_image.jpg");
				newUser.setMonto(0.0);
				usrNegocio.insertarUsuario(newUser);
				MessagePrinter.showInformationMessage("Usuario ingresado con Éxito recibirá mail de confirmación");
				String mensajeHtml = "Haga click en el siguiente link para confirmar su registro:\n" +
									 "\thttp://"+Constantes.DOMINIO()+":"+Constantes.PUERTO+"/SuperBetWeb/ConfirmarUsuario?id=" + newUser.getId_card();
				Mailer.enviarMail(newUser.getEmail(), "NoReply: Mail de confirmación de SuperBet.com", mensajeHtml);
				limpiarCampos();
				System.out.println("alta de usuario realizada satisfactoriamente");
		} 
		return null;
	}

	public void setNewUser(Usuario newUser) {
		this.newUser = newUser;
	}

	public Usuario getNewUser() {
		if(newUser == null){
			newUser = new Usuario();
		}
		return newUser;
	}

	public String getTxtEmailCobrador() {
		return txtEmailCobrador;
	}

	public void setTxtEmailCobrador(String txtEmailCobrador) {
		this.txtEmailCobrador = txtEmailCobrador;
	}

	public double getTxtMonto() {
		return txtMonto;
	}

	public void setTxtMonto(double txtMonto) {
		this.txtMonto = txtMonto;
	}



	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setSelectedPais(String selectedPais) {
		this.selectedPais = selectedPais;
	}

	public String getSelectedPais() {
		if((selectedPais == null) && (logedUser != null)){
			selectedPais = logedUser.getCountry();
		}
		return selectedPais;
	}

	public String getTxtEmailPagador() {
		return txtEmailPagador;
	}

	public void setTxtEmailPagador(String txtEmailPagador) {
		this.txtEmailPagador = txtEmailPagador;
	}

	public void setLogedUser(Usuario logedUser) {
		if((this.logedUser != null) && (logedUser != null) && (logedUser.getPhone() == null)){
			logedUser.setPhone("Ingrese su telefono");
		}
		this.logedUser = logedUser;
	}

	public Usuario getLogedUser() {
		return logedUser;
	}

	public void setConfirmPassInput(String confirmPassInput) {
		this.confirmPassInput = confirmPassInput;
	}

	public String getConfirmPassInput() {
		return confirmPassInput;
	}

	public void setConfirmDocInput(String confirmDocInput) {
		this.confirmDocInput = confirmDocInput;
	}

	public String getConfirmDocInput() {
		return confirmDocInput;
	}

	public void ConfirmarPago() {
		System.out.println("confirmo pago");
		try {
			this.logedUser.agregarMontoCuenta(txtMonto);
			usrNegocio.updateUser(logedUser);
		} catch (Exception e) {
			// TODO: aca queda pendiente hacer un refund sobre paypal
			showErrorMessage("Su Dinero no ha podido ser depositado");
			e.printStackTrace();
		}

		// showInformationMessage("Se a Depositado el Dinero en su cuenta");
	}

	public String depositarDinero() {
		try {
			txtMonto = txtMontoDepositar;
			RequestHelper ppRequest = new RequestHelper();

			// Construct HTTP header.
			Properties headers = RequestHelper.generateHeader();
			headers.put("X-PAYPAL-REQUEST-DATA-FORMAT", "JSON");
			headers.put("X-PAYPAL-RESPONSE-DATA-FORMAT", "JSON");

			// Construct ClientDetailsType element.
			StringBuffer clientDetailsValue = new StringBuffer();
			clientDetailsValue.append("{");
			StringBuffer applicationId = JsonHelper.stringNvp("applicationId",
					"PayJsonDemo");
			clientDetailsValue.append(applicationId);
			clientDetailsValue.append(",");
			StringBuffer customerId = JsonHelper.stringNvp("customerId",
					"the buyer");
			clientDetailsValue.append(customerId);
			clientDetailsValue.append(",");
			StringBuffer ipAddress = JsonHelper.stringNvp("ipAddress",
					"127.0.0.1");
			clientDetailsValue.append(ipAddress);
			clientDetailsValue.append("}");

			// Construct ReceiverList array.
			StringBuffer[] receiverArray = new StringBuffer[Constantes.PAY_PAL_MAXRECEIVERS];
			receiverArray[0] = new StringBuffer();
			receiverArray[0].append("{");
			StringBuffer amount0 = JsonHelper.stringNvp("amount",
					Double.toString(txtMonto));
			receiverArray[0].append(amount0);
			receiverArray[0].append(",");
			StringBuffer email0 = JsonHelper.stringNvp("email",
					Constantes.PAY_PAL_EMAIL_BANCA_SUPER_BET);
			receiverArray[0].append(email0);
			receiverArray[0].append("}");
			StringBuffer receiverValue = JsonHelper.bufferArray(receiverArray);
			StringBuffer receiver = JsonHelper.bufferNvp("receiver",
					receiverValue);
			StringBuffer receiverListValue = new StringBuffer();
			receiverListValue.append("{");
			receiverListValue.append(receiver);
			receiverListValue.append("}");

			// Construct RequestEvelope element.
			StringBuffer requestEnvelopeValue = new StringBuffer();
			requestEnvelopeValue.append("{");
			StringBuffer detailLevel = JsonHelper.stringNvp("detailLevel",
					"ReturnAll");
			requestEnvelopeValue.append(detailLevel);
			requestEnvelopeValue.append(",");
			StringBuffer errorLanguage = JsonHelper.stringNvp("errorLanguage",
					"en_US");
			requestEnvelopeValue.append(errorLanguage);
			requestEnvelopeValue.append("}");

			// Construct request body.
			StringBuffer body = new StringBuffer();
			body.append("{");
			StringBuffer actionType = JsonHelper.stringNvp("actionType", "PAY");
			body.append(actionType);
			body.append(",");
			StringBuffer cancelUrl = JsonHelper
					.stringNvp(
							"cancelUrl",
							"http://"
									+ Constantes.DOMINIO()+":"+Constantes.PUERTO
									+ "/SuperBetWeb/ComunicacionPayPal?transaction=false");

			body.append(cancelUrl);
			body.append(",");
			StringBuffer clientDetails = JsonHelper.bufferNvp("clientDetails",
					clientDetailsValue);
			body.append(clientDetails);
			body.append(",");
			StringBuffer currencyCode = JsonHelper.stringNvp("currencyCode",
					"USD");
			body.append(currencyCode);
			body.append(",");
			StringBuffer feesPayer = JsonHelper.stringNvp("feesPayer",
					"EACHRECEIVER");
			body.append(feesPayer);
			body.append(",");
			StringBuffer receiverList = JsonHelper.bufferNvp("receiverList",
					receiverListValue);
			body.append(receiverList);
			body.append(",");
			StringBuffer requestEnvelope = JsonHelper.bufferNvp(
					"requestEnvelope", requestEnvelopeValue);
			body.append(requestEnvelope);
			body.append(",");
			StringBuffer returnUrl = JsonHelper
					.stringNvp(
							"returnUrl",
							"http://"
									+ Constantes.DOMINIO()+":"+Constantes.PUERTO
									+ "/SuperBetWeb/ComunicacionPayPal?transaction=true");
			body.append(returnUrl);
			body.append(",");
			StringBuffer senderEmail = JsonHelper.stringNvp("senderEmail",
					txtEmailPagador);
			body.append(senderEmail);
			body.append("}");

			// Endpoint URL.
			String url = "https://svcs.sandbox.paypal.com/AdaptivePayments/Pay/";
			System.out.println("Endpoint:\n" + url + "\n");

			String response = ppRequest.sendHttpPost(url, body.toString(),
					headers);

			System.out.println("Response:\n" + response + "\n");

			// Obtenemos un Pattern con la expresi�n regular, y de �l
			// un Matcher, para extraer los trozos de inter�s.
			java.util.regex.Pattern patron = java.util.regex.Pattern
					.compile("\"payKey\":\"(.*)\",");
			Matcher matcher = patron.matcher(response);

			// Hace que Matcher busque los trozos.
			matcher.find();

			String paykey = matcher.group(1);
			/*
			 * ejemplo respuesta
			 * 
			 * { "responseEnvelope":{
			 * "timestamp":"2011-09-18T05:44:29.727-07:00",
			 * "ack":"Success","correlationId":"8a4306e1e9a9e",
			 * "build":"2050734"} , "payKey":"AP-1E654299FP924433R",
			 * "paymentExecStatus":"CREATED" }
			 */
			String urlRespuesta = "https://www.sandbox.paypal.com/webscr?cmd=_ap-payment&paykey="
					+ paykey;

			System.out.println("URL   ---------------" + urlRespuesta);

			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			try {
				ec.redirect(urlRespuesta);
			} catch (IOException ex) {
				// Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE,
				// null, ex);
			}

			return null;

		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
		return "error";
	}

	public String retirarDinero() {
		try {

			RequestHelper ppRequest = new RequestHelper();

			// Construct HTTP header.
			Properties headers = RequestHelper.generateHeader();
			headers.put("X-PAYPAL-REQUEST-DATA-FORMAT", "JSON");
			headers.put("X-PAYPAL-RESPONSE-DATA-FORMAT", "JSON");

			// Construct ClientDetailsType element.
			StringBuffer clientDetailsValue = new StringBuffer();
			clientDetailsValue.append("{");
			StringBuffer applicationId = JsonHelper.stringNvp("applicationId",
					"PayJsonDemo");
			clientDetailsValue.append(applicationId);
			clientDetailsValue.append(",");
			StringBuffer customerId = JsonHelper.stringNvp("customerId",
					"the buyer");
			clientDetailsValue.append(customerId);
			clientDetailsValue.append(",");
			StringBuffer ipAddress = JsonHelper.stringNvp("ipAddress",
					"127.0.0.1");
			clientDetailsValue.append(ipAddress);
			clientDetailsValue.append("}");

			// Construct ReceiverList array.
			StringBuffer[] receiverArray = new StringBuffer[Constantes.PAY_PAL_MAXRECEIVERS];
			receiverArray[0] = new StringBuffer();
			receiverArray[0].append("{");
			StringBuffer amount0 = JsonHelper.stringNvp("amount",
					Double.toString(txtMonto));
			receiverArray[0].append(amount0);
			receiverArray[0].append(",");
			StringBuffer email0 = JsonHelper.stringNvp("email",
					txtEmailCobrador);
			receiverArray[0].append(email0);
			receiverArray[0].append("}");
			StringBuffer receiverValue = JsonHelper.bufferArray(receiverArray);
			StringBuffer receiver = JsonHelper.bufferNvp("receiver",
					receiverValue);
			StringBuffer receiverListValue = new StringBuffer();
			receiverListValue.append("{");
			receiverListValue.append(receiver);
			receiverListValue.append("}");

			// Construct RequestEvelope element.
			StringBuffer requestEnvelopeValue = new StringBuffer();
			requestEnvelopeValue.append("{");
			StringBuffer detailLevel = JsonHelper.stringNvp("detailLevel",
					"ReturnAll");
			requestEnvelopeValue.append(detailLevel);
			requestEnvelopeValue.append(",");
			StringBuffer errorLanguage = JsonHelper.stringNvp("errorLanguage",
					"en_US");
			requestEnvelopeValue.append(errorLanguage);
			requestEnvelopeValue.append("}");

			// Construct request body.
			StringBuffer body = new StringBuffer();
			body.append("{");
			StringBuffer actionType = JsonHelper.stringNvp("actionType", "PAY");
			body.append(actionType);
			body.append(",");
			StringBuffer cancelUrl = JsonHelper
					.stringNvp(
							"cancelUrl",
							"http://"
									+ Constantes.DOMINIO()+":"+Constantes.PUERTO
									+ "/SuperBetWeb/jsp/PayPalConfirmacionPago.jsp?transaction=false");

			body.append(cancelUrl);
			body.append(",");
			StringBuffer clientDetails = JsonHelper.bufferNvp("clientDetails",
					clientDetailsValue);
			body.append(clientDetails);
			body.append(",");
			StringBuffer currencyCode = JsonHelper.stringNvp("currencyCode",
					"USD");
			body.append(currencyCode);
			body.append(",");
			StringBuffer feesPayer = JsonHelper.stringNvp("feesPayer",
					"EACHRECEIVER");
			body.append(feesPayer);
			body.append(",");
			StringBuffer receiverList = JsonHelper.bufferNvp("receiverList",
					receiverListValue);
			body.append(receiverList);
			body.append(",");
			StringBuffer requestEnvelope = JsonHelper.bufferNvp(
					"requestEnvelope", requestEnvelopeValue);
			body.append(requestEnvelope);
			body.append(",");
			StringBuffer returnUrl = JsonHelper
					.stringNvp(
							"returnUrl",
							"http://"
									+ Constantes.DOMINIO()+":"+Constantes.PUERTO
									+ "/SuperBetWeb/jsp/PayPalConfirmacionPago.jsp?transaction=false");
			body.append(returnUrl);
			body.append(",");
			StringBuffer senderEmail = JsonHelper.stringNvp("senderEmail",
					Constantes.PAY_PAL_EMAIL_BANCA_SUPER_BET);
			body.append(senderEmail);
			body.append("}");

			// Endpoint URL.
			String url = "https://svcs.sandbox.paypal.com/AdaptivePayments/Pay/";
			System.out.println("Endpoint:\n" + url + "\n");

			String response = ppRequest.sendHttpPost(url, body.toString(),
					headers);

			System.out.println("Respuesta con confirmaci�n autom�tica:\n"
					+ response + "\n");

			// Una vez que retire dinero, deduzco del monto actual y actualizo
			// monto en la base

			this.logedUser.setMonto(logedUser.getMonto() - txtMonto);

			usrNegocio.updateUser(logedUser);
			showInformationMessage("Se retiro el dinero de su cuenta");
			return "home";

		} catch (Exception e) {
			showErrorMessage("Ha ocurrido un error");

			System.out.println("ERROR: " + e);
		}finally{
			limpiarCampos();
		}
		return "error";
	}
	
	private void limpiarCampos() {
		this.txtMonto=0.0;
		this.newUser = new Usuario();
		
	}

	public String verPerfil(){
		return "perfil";
	}

	public void setApuestas(ArrayList<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public ArrayList<Apuesta> getApuestas() {
		this.apuestas = usrNegocio.getApuestas(this.logedUser);
		return apuestas;
	}

	public Usuario updateUser(Usuario usr) {
		return usrNegocio.updateUser(usr);
	}

	public void setTxtMontoDepositar(double txtMontoDepositar) {
		this.txtMontoDepositar = txtMontoDepositar;
	}

	public double getTxtMontoDepositar() {
		return txtMontoDepositar;
	}

	public void handleAvatarUpload (FileUploadEvent event) {

		System.out.println("fileName: " + event.getFile().getFileName());
		System.out.println("fileSize: " + event.getFile().getSize());
		
		// convertir a Base64 
		byte[] imageBytes = event.getFile().getContents();
		
		base64StringEnMem = new BASE64Encoder().encode(imageBytes);
		base64String = base64StringEnMem;
	}

	public String getBase64String() {
		if (base64String == null) {
			String aux = usrNegocio.obtenerBase64Avatar(logedUser);
			base64String = (aux == null) ? 
							base64StringEnMem : aux;
		}
		return base64String;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

}
