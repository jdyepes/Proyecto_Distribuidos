package cliente;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import transporte.HiloCliente;

public class Cliente {

    
    /***********************************************************************/
   // puerto del nodo sucesor    
    private int portSiguiente;// = 5001;
  // direccion del servidor del sucesor
    private String serverSiguiente;// = "localhost";
    int cont =0;  // intentos de reconexion con el servidor  
    
    /**********************************************************************/
    private BufferedReader input ;
    private PrintStream output;
    private Socket socket;
    public String mensajeHaciaServidor;

    
    public Cliente(BufferedReader input, PrintStream output, Socket socket) {
        this.input = input;
        this.output = output;
        this.socket = socket;
    }
        
    /**
     * Constructor
     * @param PORT
     * @param SERVER 
     */
    public Cliente(int PORT, String SERVER) {
        this.portSiguiente = PORT;
        this.serverSiguiente = SERVER;
    }

//    public Cliente() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    /**
     * @since 30/jun/2018
     * @see http://www.jc-mouse.net/proyectos/ejemplo-socket-java-clienteservidor
     * @throws Exception 
     */
    public void iniciarConexionCliente() throws Exception {
    	boolean exit=false;//bandera para controlar ciclo del programa        
         try {  
           while (cont<=5)
            { 
                System.out.println("Cliente> Inicio");  
                String myIP = InetAddress.getLocalHost().getHostAddress(); // Obtengo la ip del nodo actal
                while( !exit ) { //ciclo repetitivo 
                    System.out.println("entro con "+serverSiguiente+" " +portSiguiente);  
                    /* modificado 1/jul/2018 Creacion del contructor para los buffers y el socket*/ 
                    socket = new Socket(serverSiguiente, portSiguiente);//abre socket 
                    input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                    output = new PrintStream(socket.getOutputStream());
                 
                      //Socket socket = new Socket(serverSiguiente, portSiguiente);//abre socket 
//                    //Para leer lo que envie el servidor      
//                    BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));                
//                    //para imprimir datos del servidor
//                    PrintStream output = new PrintStream(socket.getOutputStream());   
                    
                    
                    //Para leer lo que escriba el usuario            
                    BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));
                    /*************************************************************/
                    System.out.println("Cliente> Escriba comando");                
//                    //captura comando escrito por el usuario
                    String request = "hola";// por modificar leerPaquetes();
                   // brRequest.readLine();   
               //     Thread.sleep(5000);// cada 5 segundos envio el 
               
                    //manda peticion al servidor               
//                    output.println(request); 
                    mensajeHaciaServidor= enviarCliente( output,request); /// falta colocar mi ip local de la maquina
                    //para que transporte sepa origen destino del mensaje
         /***********************************************************************/           
                    //captura respuesta del servidor e imprime
//                    String st = input.readLine(); 
//                    recibirCliente( input);
                      Recibir receive = new Recibir(input, output, socket);
                      receive.start();
//                    if( st != null ) 
//                        System.out.println("Servidor> " + st );   

//                    if(request.equals("exit")) //terminar aplicacion
//                    {
//                        exit=true;                  
//                        System.out.println("Cliente> Fin de programa");    
//                    }  
                  //  socket.close();
                }//end while                  
            } 
        }//fin try
         
         catch (IOException ex) 
            {        
                 System.out.println(cont+": intento(s) : Esperando servidor " + ex.getMessage());   
                 HiloCliente.sleep(10000); // aprox 5seg de espera
                 cont++;
                 iniciarConexionCliente();                 
              
            }
         catch (Exception ex) 
         {
             System.err.println("Hubo una excepcion no disponible " + ex.getMessage()); 
         }
    }
    
    public class Recibir extends Thread
    {

        private final BufferedReader input;
        private final PrintStream output;
        private final Socket socket;
        @Override
        public void run()
        {
            System.out.println("recibiendo transporte");
            String recibirCliente = recibirCliente(input);
        }
          public Recibir(BufferedReader input, PrintStream output, Socket socket) {
                this.input = input;
                this.output = output;
                this.socket = socket;
            }
        
    }
    /**
     * recibe mensajes desde el servidor
     * @param input : buffer de recepcion del servidor (nodo siguiente)
     * @return 
     */    
    public String recibirCliente( BufferedReader input)
    {
        String st=null; 
        try {
            st = input.readLine();
            if( st != null ) 
                  System.out.println("Cliente: -- Servidor respondio> " + st );   
        } catch (IOException ex) {
            System.err.println("Ocurrio un error en recibir mensaje del servidor (nodo siguiente)"+ex);
        }
     return st;
                    
    }
    /** 
     * envia mensaje hacia el servidor
     * @param output
     * @param request
     * @return 
     */
 public String enviarCliente( PrintStream output,String request) {
         //Para leer lo que escriba el usuario            
//         BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));
//         System.out.println("Cliente> Escriba comando");                
                    //captura comando escrito por el usuario
                    //            request = brRequest.readLine();
                    output.println(request);
       return request;
 }
 
    /**
     * /@see http://chuwiki.chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java
     * @return 
     */
 public String leerPaquetes(){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String linea =null;
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("src/paquetes/paquetes.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
        linea=br.readLine();
        // while((linea=br.readLine())!=null)
            //System.out.println(linea);
      }
      catch(Exception e){
        // e.printStackTrace();
         System.err.println(e);
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
           // e2.printStackTrace();
            System.err.println(e2);
         }
      }    
     return linea;

 }
// /**
//  * envia los
//  * @return 
//  */
// public String enviarPaquetes()
// {
//     String contenidoPaquete = leerPaquetes();
//     String paquetes []= contenidoPaquete.split("$");
//     
//     return"";
// }
 
 
}