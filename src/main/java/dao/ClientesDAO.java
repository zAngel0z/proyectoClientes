/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entidades.Clientes;

/**
 *
 * @author Angel
 */
public class ClientesDAO {
    
     private Connection conexion = null;

     
     //establecer la conexion con la base de datos
    public ClientesDAO() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/neptuno", "root", "");
        } catch (SQLException ex) {
            System.err.println("Error al conectar: " + ex.getMessage());
            //ex.printStackTrace(); //usarlo sÃ³lo cuando se hacen pruebas del programa ya que avisa de dÃ³nde se encuentra el problema
        }
    }
    
    
    
     //comprobar la conexion con la base de datos
    public Connection getConexion() {
        return conexion;
    }
    
    
     //realizar la consulta
    public Clientes read(Integer idClientes) {
        Clientes cliente = null;
        PreparedStatement stmt = null;

        if (this.conexion == null) {
            return null;
        }

        try {
            String query = "SELECT * FROM empleados WHERE idclientes = ?";
            stmt = conexion.prepareStatement(query);
            stmt.setInt(1, idClientes);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Clientes(
                        rs.getInt("CodigoEmpleado"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido1"),
                        rs.getString("Apellido2"),
                        rs.getString("Extension"),
                        rs.getString("Email"),
                        rs.getString("CodigoOficina"),
                        rs.getInt("CodigoJefe"),
                        rs.getString("Puesto")
                );
            }

            stmt.close();

        } catch (SQLException e) {

            System.err.println("Error en el Select: " + e.getMessage() + "\nQuery: " + stmt.toString());
        }

        return cliente;
    }
    
    
    
    //insertar datos
    public Boolean insert(Clientes clientes) {
        Boolean resultado = false;
        PreparedStatement stmt = null;
        Integer ultimoID = null;

        if (this.conexion == null || clientes == null) {
            return false;
        }

        try {
           

         
            String sql = "INSERT INTO empleados "
                    + "(id, empresa, contacto, cargo_contacto, ciudad, pais, telefono, fax) ";
   //                 + "VALUES ((SELECT Max(CodigoEmpleado)+1 FROM `empleados` E), ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, clientes.getId());
            stmt.setString(2, clientes.getEmpresa());
            stmt.setString(3, clientes.getContacto());
            stmt.setString(4, clientes.getCargo_contacto());
            stmt.setString(5, clientes.getCiudad());
            stmt.setString(6, clientes.getPais());
            stmt.setString(7, clientes.getTelefono());
            stmt.setString(8, clientes.getFax());

            if (stmt.executeUpdate() > 0) {
                resultado = true;

            }
        } catch (SQLException e) {
            System.err.println("Error en el Insert: " + e.getMessage()+ " SQL:" + stmt.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexiÃ³n: " + ex.getMessage());
            }
        }

        return resultado;

    }
    
    
    
     //actualizar datos del empleado
    public Boolean update(Clientes clientes) {
        Boolean resultado = null;
        PreparedStatement stmt = null;

        if (this.conexion == null || clientes == null) {
            return false;
        }

        try {

            String sql = "UPDATE clientes SET  id= ?, empresa = ?, contacto = ?, cargo_contacto = ?"
                    + ", ciudad = ?, pais = ?, telefono = ?, fax = ? WHERE id = ?";

            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, clientes.getId());
            stmt.setString(2, clientes.getEmpresa());
            stmt.setString(3, clientes.getContacto());
            stmt.setString(4, clientes.getCargo_contacto());
            stmt.setString(5, clientes.getCiudad());
            stmt.setString(6, clientes.getPais());
            stmt.setString(7, clientes.getTelefono());
            stmt.setString(8, clientes.getFax());
 
            if (stmt.executeUpdate() > 0) {
                resultado = true;

            }
        } catch (SQLException e) {
            System.err.println("Error en el Update: " + e.getMessage()+ " SQL:" + stmt.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexiÃ³n: " + ex.getMessage());
            }
        }

        return resultado;
    }
    
    //eliminar un registro
    public Boolean delete(Integer id) {
        Boolean resultado = false;
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);

            resultado = stmt.execute();

            stmt.close();

            System.out.println();

        } catch (SQLException e) {

            System.err.println("Error en el Delete: " + e.getMessage() + " " + stmt.toString());
        }

        return resultado;

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}