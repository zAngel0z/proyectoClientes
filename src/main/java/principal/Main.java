
package principal;

import dao.ClientesDAO;
import entidades.Clientes;
import java.util.Scanner;

/**
 *
 * @author Angel
 */
public class Main {
    
    
    static Scanner sc = new Scanner(System.in, "ISO-8859-1");
    static ClientesDAO clientes = new ClientesDAO();
    
    public static void main(String[] args) {
        Integer opcion = null;

        if (clientes.getConexion() == null) {
            System.err.println("Programa terminado. Error en la conexión.");
            System.exit(1);
        }
        
            System.out.println("\t\tBIENVENIDO");
        System.out.println("\t\t-----------");
        while (true) {
            try {
                System.out.println("ELIJA ALGUNA DE LAS OPCIONES QUE SE MUESTRAN A CONTINUACIÓN\n");
                System.out.println("  1. Buscar datos de clientes.");
                System.out.println("  2. Insertar datos de un cliente.");
                System.out.println("  3. Actualizar datos de un cliente.");
                System.out.println("  4. Eliminar datos de un cliente.\n");
                System.out.print("Su elección [introduzca 0 para salir]: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 0:
                        System.out.println("\nHasta pronto.\n");
                        System.out.println("\t    -------------");
                        System.out.println("\t\tFIN\n");
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("\nBÚSQUEDA");
                        System.out.println("--------");
                        buscarCliente();
                        break;
                    case 2:
                        System.out.println("\nINSERCIÓN");
                        System.out.println("---------");
                        introducirCliente();
                        break;
                    case 3:
                        System.out.println("\nACTUALIZAR");
                        System.out.println("----------");
                        actualizarCliente();
                        break;
                    case 4:
                        System.out.println("\nBORRADO");
                        System.out.println("-------");
                        borrarCliente();
                        break;
                    default:
                        System.out.println("\nIntroduzca alguna de las opciones válidas.");
                }
                System.out.println();
            } catch (NumberFormatException nfe) {
                System.err.println("\nError: Entrada no válida." + nfe.getMessage() + "\n");
            }
        }
        
    }
    public static Clientes existeCliente() {
        Clientes cliente = null;

        System.out.print("Indique el ID del cliente que desea buscar: ");
        cliente = clientes.read(Integer.parseInt(sc.nextLine()));

        return cliente;
    }
    
     public static void buscarCliente() {
        Clientes cliente = existeCliente();

        if (cliente != null) {
            System.out.println(cliente.toString());
        } else {
            System.err.println("El cliente no existe o no se puede leer.");
        }
    }
    
     public static void introducirCliente() {
        Clientes cliente = new Clientes();

        System.out.print("Indique el ID del cliente: ");
        cliente.setId(Integer.parseInt(sc.nextLine()));

        System.out.print("Indique el código del cliente: ");
        cliente.setCodigo(sc.nextLine());

        System.out.print("Indique la empresa del cliente: ");
        cliente.setEmpresa(sc.nextLine());

        System.out.print("Indique el contacto del cliente: ");
        cliente.setContacto(sc.nextLine());

        System.out.print("Indique el cargo del cliente: ");
        cliente.setCargo_contacto(sc.nextLine());

        System.out.print("Indique la dirección del cliente: ");
        cliente.setDireccion(sc.nextLine());

        System.out.print("Indique la ciudad del cliente: ");
        cliente.setCiudad((sc.nextLine()));

        System.out.print("Indique la región del cliente: ");
        cliente.setRegion(sc.nextLine());
        
        System.out.print("Indique el código postal del cliente: ");
        cliente.setCp(sc.nextLine());
        
        System.out.print("Indique el País del cliente: ");
        cliente.setPais(sc.nextLine());
        
        System.out.print("Indique el teléfono del cliente: ");
        cliente.setTelefono(sc.nextLine());
        
        System.out.print("Indique el fax del cliente: ");
        cliente.setFax(sc.nextLine());


        if (clientes.insert(cliente)) {
            System.out.println("El cliente '" + cliente.getId() + " " + cliente.getCodigo()
                    + " " + cliente.getEmpresa() + "" + cliente.getContacto()+ "" +cliente.getCargo_contacto() + "" + cliente.getDireccion() + "" + cliente.getCiudad() + "" + cliente.getRegion()+ "" + cliente.getCp() + "" + cliente.getPais() 
                            + "" + cliente.getTelefono() + "" + cliente.getFax()+ "' ha sido añadido satisfactoriamente.");
        } else {
            System.err.println("El cliente que intenta introducir no es válido.\n");
        }
    }
    
    public static void actualizarCliente() {
        Clientes cliente = existeCliente();

        if (cliente == null) {
            System.err.println("El cliente no existe o no se puede leer.");
            return;
        }

        while (true) {
            try {
                System.out.println("\n" + cliente);

                Integer opcion;

                System.out.println("\nELIJA ALGUNA DE LAS OPCIONES QUE SE MUESTRAN A CONTINUACIÓN\n");
                System.out.println("  1. ID.");
                System.out.println("  2. Código.");
                System.out.println("  3. Empresa.");
                System.out.println("  4. Contacto.");
                System.out.println("  5. Cargo_contacto.");
                System.out.println("  6. Dirección.");
                System.out.println("  7. Ciudad.");
                System.out.println("  8. Región.");
                System.out.println("  9. Código Postal .");
                System.out.println("  10. País .");
                System.out.println("  11. Teléfono .");
                System.out.println("  12. Fax.");
                
                System.out.print("\nSu elección [introduzca 0 para retroceder]: ");
                opcion = Integer.parseInt(sc.nextLine());

                if (opcion > 0 && opcion < 13) {
                    System.out.print("\nIntroduzca la modificación que desea realizar: ");
                }

                switch (opcion) {
                    case 0:
                        return;
                    case 1:
                        cliente.setId(Integer.parseInt(sc.nextLine()));
                        clientes.update(cliente);
                        break;
                    case 2:
                        cliente.setCodigo(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 3:
                        cliente.setEmpresa(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 4:
                        cliente.setContacto(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 5:
                        cliente.setCargo_contacto(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 6:
                        cliente.setDireccion(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 7:
                        cliente.setCiudad(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 8:
                        cliente.setRegion(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 9:
                        cliente.setCp(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 10:
                        cliente.setPais(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 11:
                        cliente.setTelefono(sc.nextLine());
                        clientes.update(cliente);
                        break;                      
                    case 12:
                        cliente.setFax(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    default:
                        System.out.println("\nIntroduzca alguna de las opciones válidas.\n");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("\nError: " + nfe.getMessage() + "\n");
            }
        }
    }
     
     public static void borrarCliente() {
        Clientes cliente = existeCliente();
        String resp = null;

        if (cliente != null) {
                System.out.println("\n¿Está seguro que desea eliminar al siguiente usuario?"
                        + "\n  " + cliente);
                System.out.print("Su respuesta [Y/N]: ");
                resp = sc.nextLine();
                
                if (resp.equalsIgnoreCase("y")) {
                    clientes.delete(cliente.getId());
                    System.out.println("Entrada eliminada.");
                } else {
                    System.out.println("Entrada no eliminada.");
                }
        } else {
            System.err.println("El cliente no existe o no se puede leer.");
        }
    }
     
     
    
}
