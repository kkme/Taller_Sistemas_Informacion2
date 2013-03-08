
package superbet.servicios.externo.provider;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the superbet.servicios.externo.provider package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Apostar_QNAME = new QName("http://provider.externo.servicios.superbet/", "apostar");
    private final static QName _ObtenerApuestasPagasUsuario_QNAME = new QName("http://provider.externo.servicios.superbet/", "obtenerApuestasPagasUsuario");
    private final static QName _ApostarResponse_QNAME = new QName("http://provider.externo.servicios.superbet/", "apostarResponse");
    private final static QName _ListarEventosSimples_QNAME = new QName("http://provider.externo.servicios.superbet/", "listarEventosSimples");
    private final static QName _ObtenerApuestasPagasUsuarioResponse_QNAME = new QName("http://provider.externo.servicios.superbet/", "obtenerApuestasPagasUsuarioResponse");
    private final static QName _EnviarResultadosGanadoresResponse_QNAME = new QName("http://provider.externo.servicios.superbet/", "enviarResultadosGanadoresResponse");
    private final static QName _ListarResultados_QNAME = new QName("http://provider.externo.servicios.superbet/", "listarResultados");
    private final static QName _ListarResultadosResponse_QNAME = new QName("http://provider.externo.servicios.superbet/", "listarResultadosResponse");
    private final static QName _EnviarResultadosGanadores_QNAME = new QName("http://provider.externo.servicios.superbet/", "enviarResultadosGanadores");
    private final static QName _ListarEventosSimplesResponse_QNAME = new QName("http://provider.externo.servicios.superbet/", "listarEventosSimplesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: superbet.servicios.externo.provider
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Apostar }
     * 
     */
    public Apostar createApostar() {
        return new Apostar();
    }

    /**
     * Create an instance of {@link ObtenerApuestasPagasUsuario }
     * 
     */
    public ObtenerApuestasPagasUsuario createObtenerApuestasPagasUsuario() {
        return new ObtenerApuestasPagasUsuario();
    }

    /**
     * Create an instance of {@link EnviarResultadosGanadores }
     * 
     */
    public EnviarResultadosGanadores createEnviarResultadosGanadores() {
        return new EnviarResultadosGanadores();
    }

    /**
     * Create an instance of {@link DataEvento }
     * 
     */
    public DataEvento createDataEvento() {
        return new DataEvento();
    }

    /**
     * Create an instance of {@link DataGanador }
     * 
     */
    public DataGanador createDataGanador() {
        return new DataGanador();
    }

    /**
     * Create an instance of {@link ListarEventosSimplesResponse }
     * 
     */
    public ListarEventosSimplesResponse createListarEventosSimplesResponse() {
        return new ListarEventosSimplesResponse();
    }

    /**
     * Create an instance of {@link DataResultado }
     * 
     */
    public DataResultado createDataResultado() {
        return new DataResultado();
    }

    /**
     * Create an instance of {@link ListarResultadosResponse }
     * 
     */
    public ListarResultadosResponse createListarResultadosResponse() {
        return new ListarResultadosResponse();
    }

    /**
     * Create an instance of {@link ListarEventosSimples }
     * 
     */
    public ListarEventosSimples createListarEventosSimples() {
        return new ListarEventosSimples();
    }

    /**
     * Create an instance of {@link EnviarResultadosGanadoresResponse }
     * 
     */
    public EnviarResultadosGanadoresResponse createEnviarResultadosGanadoresResponse() {
        return new EnviarResultadosGanadoresResponse();
    }

    /**
     * Create an instance of {@link ObtenerApuestasPagasUsuarioResponse }
     * 
     */
    public ObtenerApuestasPagasUsuarioResponse createObtenerApuestasPagasUsuarioResponse() {
        return new ObtenerApuestasPagasUsuarioResponse();
    }

    /**
     * Create an instance of {@link ListarResultados }
     * 
     */
    public ListarResultados createListarResultados() {
        return new ListarResultados();
    }

    /**
     * Create an instance of {@link ApostarResponse }
     * 
     */
    public ApostarResponse createApostarResponse() {
        return new ApostarResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Apostar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "apostar")
    public JAXBElement<Apostar> createApostar(Apostar value) {
        return new JAXBElement<Apostar>(_Apostar_QNAME, Apostar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerApuestasPagasUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "obtenerApuestasPagasUsuario")
    public JAXBElement<ObtenerApuestasPagasUsuario> createObtenerApuestasPagasUsuario(ObtenerApuestasPagasUsuario value) {
        return new JAXBElement<ObtenerApuestasPagasUsuario>(_ObtenerApuestasPagasUsuario_QNAME, ObtenerApuestasPagasUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApostarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "apostarResponse")
    public JAXBElement<ApostarResponse> createApostarResponse(ApostarResponse value) {
        return new JAXBElement<ApostarResponse>(_ApostarResponse_QNAME, ApostarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEventosSimples }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "listarEventosSimples")
    public JAXBElement<ListarEventosSimples> createListarEventosSimples(ListarEventosSimples value) {
        return new JAXBElement<ListarEventosSimples>(_ListarEventosSimples_QNAME, ListarEventosSimples.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerApuestasPagasUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "obtenerApuestasPagasUsuarioResponse")
    public JAXBElement<ObtenerApuestasPagasUsuarioResponse> createObtenerApuestasPagasUsuarioResponse(ObtenerApuestasPagasUsuarioResponse value) {
        return new JAXBElement<ObtenerApuestasPagasUsuarioResponse>(_ObtenerApuestasPagasUsuarioResponse_QNAME, ObtenerApuestasPagasUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarResultadosGanadoresResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "enviarResultadosGanadoresResponse")
    public JAXBElement<EnviarResultadosGanadoresResponse> createEnviarResultadosGanadoresResponse(EnviarResultadosGanadoresResponse value) {
        return new JAXBElement<EnviarResultadosGanadoresResponse>(_EnviarResultadosGanadoresResponse_QNAME, EnviarResultadosGanadoresResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarResultados }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "listarResultados")
    public JAXBElement<ListarResultados> createListarResultados(ListarResultados value) {
        return new JAXBElement<ListarResultados>(_ListarResultados_QNAME, ListarResultados.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarResultadosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "listarResultadosResponse")
    public JAXBElement<ListarResultadosResponse> createListarResultadosResponse(ListarResultadosResponse value) {
        return new JAXBElement<ListarResultadosResponse>(_ListarResultadosResponse_QNAME, ListarResultadosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarResultadosGanadores }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "enviarResultadosGanadores")
    public JAXBElement<EnviarResultadosGanadores> createEnviarResultadosGanadores(EnviarResultadosGanadores value) {
        return new JAXBElement<EnviarResultadosGanadores>(_EnviarResultadosGanadores_QNAME, EnviarResultadosGanadores.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEventosSimplesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://provider.externo.servicios.superbet/", name = "listarEventosSimplesResponse")
    public JAXBElement<ListarEventosSimplesResponse> createListarEventosSimplesResponse(ListarEventosSimplesResponse value) {
        return new JAXBElement<ListarEventosSimplesResponse>(_ListarEventosSimplesResponse_QNAME, ListarEventosSimplesResponse.class, null, value);
    }

}
