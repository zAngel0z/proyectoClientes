
package principal;


/**
 *
 * @author Angel
 */
import entidades.Cliente;
import clientesDAO.ClientesDao;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        Cliente cliente = new Cliente();
        ClientesDao clientes = new ClientesDao();
        
        System.out.println("Estos son los 10 primeros clientes:");
        mostrarSiguientes(0);
        

        mostrarMenu();
        
    }
    
    
    
    public static void mostrarMenu(){
        boolean parar = false;
        Integer siguientes = 0;
        
        while(!parar){
            Scanner in = new Scanner(System.in);
            
            System.out.println();
            System.out.println("\t"+"****MENU****");
            System.out.println("=========================================");
            System.out.println("1- Mostrar los 10 siguientes");
            System.out.println("2- Mostrar los 10 anteriores");
            System.out.println("3- Mostrar un cliente por ID");
            System.out.println("4- Si desea aÃ±adir un cliente");
            System.out.println("5- Si desea eliminar un cliente");
            System.out.println("6- Si desea actualizar un cliente");
            System.out.println("-----------------------------------------");
            System.out.println("7- Para volver a mostrar los 10 primeros");
            System.out.println("8- Para mostrar los 10 ultimos");
            System.out.println("-----------------------------------------");
            System.out.println("0- Si desea SALIR del menu");
            System.out.println("=========================================");
            int eleccion=0;
            
            while(true){
                try{
                     String opcion = in.nextLine();
                     eleccion = Integer.parseInt(opcion);
                     break;
                }catch(NumberFormatException e){
                    System.out.println("Introduce solo numeros.\n Vuelve a introducir la opcion:");
                }
            }
            
            switch(eleccion){
                case 1:
                    siguientes+=10;
                    mostrarSiguientes(siguientes);
                    break;
                case 2:
                    if(siguientes==0){
                        System.out.println("No hay menos clientes");
                    }else{
                    siguientes-=10;
                    mostrarSiguientes(siguientes);
                    }
                    break;
                case 3:
                    readCliente();
                    break;
                    
                case 4:
                    introducirCliente();
                    break;
                case 5:
                    eliminarCliente();
                    break;
                case 6:
                    actualizarCliente();
                    break;
                case 7:
                    mostrarSiguientes(0);
                    break;
                case 8:
                    mostrarUltimos();
                    break;
                case 0:
                    System.out.println("Saliendo del menu...");
                    parar = true;
                    break;
                
                default:
                    System.out.println("Introduce los numeros permitidos:");
            }
        }
    }
    
    
    public static void mostrarSiguientes(Integer siguientes){
        Cliente cliente = new Cliente();
        ClientesDao clientes = new ClientesDao();
        
        System.out.println("Visualizando los 10 siguientes Clientes");
        System.out.println("id  Codigo      Empresa\t\t\t\t\tContacto\t\t\tCargoContacto");
        System.out.println();
        for(Cliente e: clientes.listarClientes(siguientes)){
            System.out.printf("%03d %-6s %-40s %-30s %-30s\n",e.getIdCliente(), e.getCodigoCliente(), e.getEmpresa(), e.getContacto(), e.getCargoContacto());
        }
    }
    
    public static Integer comprobar(Integer siguientes){
        Cliente cliente = new Cliente();
        ClientesDao clientes = new ClientesDao();
        if(siguientes==0){
            siguientes=80;
        }else{
            siguientes-=10;
        }
        return siguientes;
    }
    
    public static Cliente existe(){
        Cliente cliente = null;
        ClientesDao clientes = new ClientesDao();
        Scanner in = new Scanner(System.in);
        
        System.out.println("Indique el id del cliente que desea: ");
        Integer idDeseado = in.nextInt();
        cliente = clientes.read(idDeseado);
        return cliente;
        
    }
    
    
    public static void readCliente(){
        Cliente cliente = existe();
        ClientesDao clientes = new ClientesDao();
        Scanner in = new Scanner(System.in);
        
        System.out.println();
        if(cliente==null){
            System.out.println("El cliente seleccionado no existe.");
        }else{
            System.out.println("El cliente que ha seleccionado es: ");
            System.out.println(cliente.toString());
        }
    }
    
    public static void introducirCliente(){
        Scanner in = new Scanner(System.in);
        Cliente cliente = new Cliente();
        ClientesDao clientes = new ClientesDao();
        
        System.out.println("Indique el CÃ³digo de Cliente");
        cliente.setCodigoCliente(in.nextLine());
        
        System.out.println("Indique el nombre de la Empresa");
        cliente.setEmpresa(in.nextLine());
        
        System.out.println("Indique el Contacto");
        cliente.setContacto(in.nextLine());
        
        System.out.println("Indique el Cargo del contacto");
        cliente.setCargoContacto(in.nextLine());
        
        System.out.println("Indique la direcciÃ³n");
        cliente.setDireccion(in.nextLine());
        
        System.out.println("Indique la ciudad");
        cliente.setCiudad(in.nextLine());
        
        System.out.println("Indique la RegiÃ³n");
        cliente.setRegion(in.nextLine());
        
        System.out.println("Indique el CÃ³digo Postal");
        cliente.setCodigoPostal(in.nextLine());
        
        System.out.println("Indique el PaÃ­s");
        cliente.setPais(in.nextLine());
        
        System.out.println("Indiue un TelÃ©fono de contacto");
        cliente.setTlfno(in.nextLine());
        
        System.out.println("Indique un NÃºmero de FAX");
        cliente.setFax(in.nextLine());
        
        if(clientes.insert(cliente)){
            System.out.println("El cliente con nombre de empresa: "+cliente.getEmpresa()+" y Contacto: "+cliente.getContacto()+" ha sido aÃ±adido");
            
        }else{
            System.out.println("El Cliente introducido no es vÃ¡lido.");
        }
        
    }
    
    public static void eliminarCliente(){
         Scanner in = new Scanner(System.in);
         Cliente cliente = existe();
         ClientesDao clientes = new ClientesDao();
         
         String respuesta = "";
         
         if(cliente==null){
             System.out.println("El Cliente seleccionado no existe.");
         }else{
             clientes.delete(cliente.getIdCliente());
             System.out.println("El cliente ha sido eliminado .");
         }
         
    }
    
    public static void actualizarCliente(){
        Scanner in = new Scanner(System.in);
        Cliente cliente = existe();
        ClientesDao clientes = new ClientesDao();
        if(cliente == null){
            System.out.println("El cliente no existe o no se puede leer");
            return;
        }
        
        while(true){
            try{
                System.out.println();
                System.out.println("El cliente que ha elegido es: "+cliente.toString());
                
                Integer eleccion=0;
                
                System.out.println("En el siguiente menu, elija la opcion que desee...");
                System.out.println("\t"+"****MENU****");
                System.out.println("-----------------------------------------------------");
                System.out.println("1- Cambiar el Codigo de Cliente(no confundir con ID)");
                System.out.println("2- Cambiar el nombre de la Empresa");
                System.out.println("3- Cambiar el nombre del contacto");
                System.out.println("4- Cambiar el cargo del contacto");
                System.out.println("5- Cambiar la direccion");
                System.out.println("6- Cambiar la ciudad");
                System.out.println("7- Cambiar la region");
                System.out.println("8- Cambiar el Codigo Postal");
                System.out.println("9- Cambiar el pais");
                System.out.println("10- Cambiar el Telefono de contacto");
                System.out.println("11- Cambiar el fax");
                System.out.println("----------------------------------------------------");
                System.out.println("0- Para salir del MENU");
                System.out.println("----------------------------------------------------");
                
               
                
                while(true){
                    try{
                         String opcion = in.nextLine();
                         eleccion = Integer.parseInt(opcion);
                         break;
                    }catch(NumberFormatException e){
                        System.out.println("Introduce solo numeros.\n Vuelve a introducir la opcion:");
                    }
                }
                
                
                
                
                if(eleccion>0 && eleccion<12){
                    System.out.print("Introduzca la modificacion: ");
                }
                switch(eleccion){
                    case 1:
                        cliente.setCodigoCliente(in.next());
                        clientes.update(cliente);
                        break;
                    case 2:
                        cliente.setEmpresa(in.next());
                        clientes.update(cliente);
                        break;
                    case 3:
                        cliente.setContacto(in.next());
                        clientes.update(cliente);
                        break;
                    case 4:
                        cliente.setCargoContacto(in.next());
                        clientes.update(cliente);
                        break;
                    case 5:
                        cliente.setDireccion(in.next());
                        clientes.update(cliente);
                        break;
                    case 6:
                        cliente.setCiudad(in.next());
                        clientes.update(cliente);
                        break;
                    case 7:
                        cliente.setRegion(in.next());
                        clientes.update(cliente);
                        break;
                    case 8:
                        cliente.setCodigoPostal(in.next());
                        clientes.update(cliente);
                        break;
                    case 9:
                        cliente.setPais(in.next());
                        clientes.update(cliente);
                        break;
                    case 10:
                        cliente.setTlfno(in.next());
                        clientes.update(cliente);
                        break;
                    case 11:
                        cliente.setFax(in.next());
                        clientes.update(cliente);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Introduce solo los numeros permitidos:");
                        break;
                }
                
            }catch(NumberFormatException e){
                System.out.println("Introduce numeros:");
                
            }
        }
    }
    
    public static void mostrarUltimos(){
        Cliente cliente = new Cliente();
        ClientesDao clientes = new ClientesDao();
        
        System.out.println("Visualizando los 10 ULTIMOS Clientes");
        System.out.println("id  Codigo      Empresa\t\t\t\t\tContacto\t\t\tCargoContacto");
        System.out.println();
        for(Cliente e: clientes.listarUltimos()){
            System.out.printf("%03d %-6s %-40s %-30s %-30s\n",e.getIdCliente(), e.getCodigoCliente(), e.getEmpresa(), e.getContacto(), e.getCargoContacto());
        }
    }
}
