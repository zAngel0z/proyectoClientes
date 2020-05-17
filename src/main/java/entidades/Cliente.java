
package entidades;

/**
 *
 * @author Angel
 */
public class Cliente {
    private Integer idCliente;
    private String codigoCliente ;
    private String empresa;
    private String contacto;
    private String cargoContacto;
    private String direccion;
    private String ciudad;
    private String region;
    private String codigoPostal;
    private String pais;
    private String tlfno;
    private String fax;

    public Cliente(Integer idCliente, String codigoCliente, String empresa, String contacto, String cargoContacto, String direccion, String ciudad, String region, String codigoPostal, String pais, String tlfno, String fax) {
        this.idCliente = idCliente;
        this.codigoCliente = codigoCliente;
        this.empresa = empresa;
        this.contacto = contacto;
        this.cargoContacto = cargoContacto;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.region = region;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.tlfno = tlfno;
        this.fax = fax;
    }

    public Cliente() {
       
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCargoContacto() {
        return cargoContacto;
    }

    public void setCargoContacto(String cargoContacto) {
        this.cargoContacto = cargoContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTlfno() {
        return tlfno;
    }

    public void setTlfno(String tlfno) {
        this.tlfno = tlfno;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return idCliente + "\t" + "\t"+ codigoCliente + "\t" +"\t"+ empresa + "\t" + "\t"+contacto + "\t" +"\t"+ cargoContacto ;
    }
    
    
    
}
