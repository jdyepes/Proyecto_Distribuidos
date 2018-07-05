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
    
    private int flagDespacho;
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
     * @param flagDespacho 
     */
    public Cliente(int PORT, String SERVER,int flagDespacho) {
        this.portSiguiente = PORT;
        this.serverSiguiente = SERVER;
        this.flagDespacho = flagDespacho;
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
               // while( !exit ) { //ciclo repetitivo 
                    System.out.println("entro con "+serverSiguiente+" " +portSiguiente);  
                    /* modificado 1/jul/2018 Creacion del contructor para los buffers y el socket*/ 
                    socket = new Socket(serverSiguiente, portSiguiente);//abre socket 
                  while( !exit ) 
                  {
                    input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                    output = new PrintStream(socket.getOutputStream());
                 
                  PrintStream    output1 = new PrintStream(socket.getOutputStream());  
                  PrintStream   output2 = new PrintStream(socket.getOutputStream());
                  PrintStream    output3 = new PrintStream(socket.getOutputStream());
                 
                      //Socket socket = new Socket(serverSiguiente, portSiguiente);//abre socket 
//                    //Para leer lo que envie el servidor      
//                    BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));                
//                    //para imprimir datos del servidor
//                    PrintStream output = new PrintStream(socket.getOutputStream());   
                    
                     Thread.sleep(6000);
                    //Para leer lo que escriba el usuario            
                    BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));
                    
                    /*************************************************************/
                   // System.out.println("Cliente> Escriba comando");   
                   
                    System.out.println("leyendo paquetes desde "+myIP);
                    
//                    //captura comando escrito por el usuario
//                    String request1 =  leerPaquetes(1);// por modificar leerPaquetes();
//                    String request2 =  leerPaquetes(2);// por modificar leerPaquetes();
//                    String request3=  leerPaquetes(3);// por modificar leerPaquetes();
                          
//                   // brRequest.readLine();   
//               //     Thread.sleep(5000);// cada 5 segundos envio el 
//               
//                    //manda peticion al servidor               
////                    output.println(request); 
//                    mensajeHaciaServidor= enviarCliente( output,request1+request2+request3); /// falta colocar mi ip local de la maquina
//                    for (int ind =1; ind<=3;ind ++)
//                    {
                         //String request =  leerPaquetes(1);// por modificar leerPaquetes();
//                       String request2 =  leerPaquetes(2);// por modificar leerPaquetes();
//                    S  tring request3=  leerPaquetes(3);// por modificar leerPaquetes();
//                         Thread.sleep(5000);
                  // si soy el despachador de transporte los envio desde mi almacen
                        if(flagDespacho==1) 
                        {
                            Thread.sleep(5000);
                            mensajeHaciaServidor= enviarCliente( output,"transporte");
                            for (int ind =1; ind<=3;ind ++)
                            {
                                Thread.sleep(5000);
                                mensajeHaciaServidor= enviarCliente( output1,leerPaquetes(1));
                                Thread.sleep(5000);
                                mensajeHaciaServidor= enviarCliente( output2,leerPaquetes(2));
                                Thread.sleep(5000);
                                mensajeHaciaServidor= enviarCliente( output3,leerPaquetes(3));
                                Thread.sleep(5000);
                            }
                        }
                        else{
                            mensajeHaciaServidor= enviarCliente( output,"espero mi mensaje");
                        }
//                    }
                    //para que transporte sepa origen destino del mensaje
         /***********************************************************************/           
                    //captura respuesta del servidor e imprime
//                    String st = input.readLine(); 
//                    recibirCliente( input);
//                    recibirCliente( input);
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
            //System.out.println("recibiendo transporte");
            System.out.println("estoy  recibiendo ");
            String recibirCliente;
            try {
                recibirCliente = recibirCliente(input);
                 recibirCliente = recibirCliente(input);
                recibirCliente = recibirCliente(input);
            } catch (Exception ex) {
                System.out.println("No se pudo recibir, hay un error "+ex);
            }
         

           
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
    public String recibirCliente( BufferedReader input) throws Exception
    {
        int numIntentos=0;
        String st=null; 
        try {
            st = input.readLine();
            if( st != null ) 
                  System.out.println("Cliente: -- Servidor respondio> " + st );   
        } catch (IOException ex) {
            while(numIntentos<=5)
            {
                System.out.println("Intentando establecer conexion intento(s): "+numIntentos);
                Thread.sleep(5000);
                iniciarConexionCliente();
                numIntentos++;
            }
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
//        BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));
//         System.out.println("Cliente> Escriba comando");                
                    //captura comando escrito por el usuario
                    //            request = brRequest.readLine();
                    output.flush();
                    output.println(request);

       return request;
 }
 /*
 public String leerPaquetes(int numTransporte){
      File archivo = null, archivoAlmacen =null;
      FileReader fr = null;
     // FileWriter fw = null;
      BufferedReader br = null;
      String linea =null;
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
      //   archivo = new File ("src/paquetes/paquetes.txt");
         archivoAlmacen = new File("src/almacenes/almacen"+numTransporte+".txt");
      //   fr = new FileReader (archivo);
         fr = new File (archivoAlmacen);
         br = new BufferedReader(fr);

         // Lectura del fichero
        linea=br.readLine();
        fw.write(linea);
        fw.close();
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
 */
 
    /**
     * /@see http://chuwiki.chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java
     * @return 
     */
public String leerPaquetes(int numTransporte){
      File archivo = null, archivoAlmacen = null;
      FileReader fr = null;
      BufferedReader br = null;
      FileWriter fw = null;
      String linea =null;

      try {
          
          System.out.println("Entro en leerPaquetes con el num de transporte: "+numTransporte);
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
       //  String myIP = InetAddress.getLocalHost().getHostAddress();
        // archivo = new File ("src/paquetes/paquetes.txt");
         archivoAlmacen = new File("src/almacenes/almacenT"+numTransporte+".txt");
         fr = new FileReader (archivoAlmacen);
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