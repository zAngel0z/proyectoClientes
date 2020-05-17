

package clientesDAO;

/**
 *
 * @author Angel
 */
import entidades.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ClientesDao {
    
    private Connection conexion = null;

    public ClientesDao() {
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/neptuno", "root", "");
        }catch(SQLException e){
            System.out.println("Conexion incorrecta: "+e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }
    
    public ArrayList<Cliente> listarClientes(Integer siguientes){
        Cliente cliente = null;
        ArrayList<Cliente> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        
        if(this.getConexion()==null){
            return null;
        }
        
        
        try{
            String select = "SELECT * FROM `clientes` LIMIT 10 OFFSET ?";
            stmt = conexion.prepareStatement(select);
            stmt.setInt(1, siguientes);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("empresa"),
                        rs.getString("contacto"),
                        rs.getString("cargo_contacto"),
                        rs.getString("direccion"),
                        rs.getString("ciudad"),
                        rs.getString("region"),
                        rs.getString("cp"),
                        rs.getString("pais"),
                        rs.getString("telefono"),
                        rs.getString("fax")
                );
                lista.add(cliente);
            }
        }catch(SQLException e){
            System.out.println("Sentencia incorrecta: "+e.getMessage());
        }
        
        return lista;
        
    }
    
    
    public Cliente read(Integer idCliente){
        Cliente cliente = null;
        PreparedStatement stmt = null;
        
        if(this.conexion == null) {
            return null;
        }
        
        try{
            String select = "SELECT * FROM clientes WHERE id = ?";
            stmt = conexion.prepareStatement(select);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("empresa"),
                        rs.getString("contacto"),
                        rs.getString("cargo_contacto"),
                        rs.getString("direccion"),
                        rs.getString("ciudad"),
                        rs.getString("region"),
                        rs.getString("cp"),
                        rs.getString("pais"),
                        rs.getString("telefono"),
                        rs.getString("fax")
                );
            }
            
            stmt.close();
            
        }catch(SQLException e){
            System.out.println("Sentencia incorrecta: "+e.getMessage());
        }
        
        return cliente;
    }
    
    
    
    public Boolean insert(Cliente cliente){
        Boolean resultado = null;
        PreparedStatement stmt = null;
        Integer idUltimo = null;
        
        
        if(this.conexion==null){
            return null;
        }
        
        try{
            String insert = "INSERT INTO clientes "
                 +"(id, codigo, empresa, contacto, cargo_contacto, direccion, ciudad, region, cp, pais, telefono, fax)"
                 +" VALUES ((SELECT Max(id)+1 FROM clientes E), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            stmt = conexion.prepareStatement(insert);
            stmt.setString(1, cliente.getCodigoCliente());
            stmt.setString(2, cliente.getEmpresa());
            stmt.setString(3, cliente.getContacto());
            stmt.setString(4, cliente.getCargoContacto());
            stmt.setString(5, cliente.getDireccion());
            stmt.setString(6, cliente.getCiudad());
            stmt.setString(7, cliente.getRegion());
            stmt.setString(8, cliente.getCodigoPostal());
            stmt.setString(9, cliente.getPais());
            stmt.setString(10, cliente.getTlfno());
            stmt.setString(11, cliente.getFax());
            
            if(stmt.executeUpdate()>0){
                resultado = true;
            }
            
        }catch(SQLException e){
            System.out.println("Sentencia incorrecta: "+e.getMessage());
        }finally{
            try{
                if(stmt!=null){
                    stmt.close();
                }
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion: "+e.getMessage());
            }
        }
        return resultado;
    }
    
    public Boolean delete(Integer idCliente){
        Boolean resultado = false;
        PreparedStatement stmt = null;
        
        try{
            String delete = "DELETE FROM clientes WHERE id = ?";
            stmt = conexion.prepareStatement(delete);
            stmt.setInt(1, idCliente);
            
            resultado = stmt.execute();
            
            stmt.close();
            
            System.out.println();
            
        }catch(SQLException e){
            System.out.println("Error en esta seleccion: "+e.getMessage());
        }
        
        return resultado;
    }
    
    
    public Boolean update(Cliente cliente){
        Boolean resultado = null;
        PreparedStatement stmt = null;
        
        if(this.conexion==null||cliente==null){
            return false;
        }
        
        try{
            String update = "UPDATE clientes SET codigo = ?, empresa = ?, contacto = ?, cargo_contacto = ?, direccion = ?, ciudad = ?, region = ?, cp = ?, pais = ?, telefono = ?, fax = ? WHERE id = ?";
            
            stmt = conexion.prepareStatement(update);
            stmt.setString(1, cliente.getCodigoCliente());
            stmt.setString(2, cliente.getEmpresa());
            stmt.setString(3, cliente.getContacto());
            stmt.setString(4, cliente.getCargoContacto());
            stmt.setString(5, cliente.getDireccion());
            stmt.setString(6, cliente.getCiudad());
            stmt.setString(7, cliente.getRegion());
            stmt.setString(8, cliente.getCodigoPostal());
            stmt.setString(9, cliente.getPais());
            stmt.setString(10, cliente.getTlfno());
            stmt.setString(11, cliente.getFax());
            
            stmt.setInt(12, cliente.getIdCliente());
            
            if(stmt.executeUpdate()>0){
                resultado = true;
            }
            
        }catch(SQLException e){
            System.out.println("Sentencia incorrecta en UPDATE : "+e.getMessage());
        }finally{
            try{
                if(stmt!=null){
                    stmt.close();
                }
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion: "+e.getMessage());
            }
        }
        return resultado;
    }
    
    public ArrayList<Cliente> listarUltimos(){
        Cliente cliente = null;
        ArrayList<Cliente> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        
        if(this.getConexion()==null){
            return null;
        }
        
        
        try{
            String select = "SELECT * FROM clientes ORDER BY id DESC LIMIT 10";
            stmt = conexion.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("empresa"),
                        rs.getString("contacto"),
                        rs.getString("cargo_contacto"),
                        rs.getString("direccion"),
                        rs.getString("ciudad"),
                        rs.getString("region"),
                        rs.getString("cp"),
                        rs.getString("pais"),
                        rs.getString("telefono"),
                        rs.getString("fax")
                );
                lista.add(cliente);
            }
        }catch(SQLException e){
            System.out.println("Sentencia incorrecta: "+e.getMessage());
        }
        
        return lista;
        
    }
    
}
