
package servidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;


/**
/**
 * sorce http://www.jc-mouse.net/proyectos/ejemplo-socket-java-clienteservidor
 * @author Jesus Yepes
 */
public class Servidor {
    /******************constantes para cada transporte****/
    
    static final String T1 = "T1=192.168.1.100:holaT1$localhost:192.168.0.100:T1hola2$localhost:192.168.0.101:T1hola3$localhost:192.168.0.108:T1hola4$localhost:192.168.0.108:T1hola5";
    static final String T2 = "T2=192.168.2.102:holaT2$localhost:192.168.0.100:T2hola2$localhost:192.168.0.101:T2hola3$localhost:192.168.0.108:T2hola4$localhost:192.168.0.108:T2hola5";
    static final String T3 = "T3=localhost3:103.168.0.108:holaT3$localhost:192.168.0.100:T3hola2$localhost:192.168.0.101:T3hola3$localhost:192.168.0.108:T3lhola4$ocalhost:192.168.0.108:T3hola5";
    /*****************************************************************/
    private static int despacho; // BANDERA que indica si soy el despachador
    private int portEscuchaAnterior;//= 5001;
    private String mensajeDespacho;
    int cont=0;

    public int getDespacho() {
        return despacho;
    }

    public static void setDespacho(int despacho) {
        Servidor.despacho = despacho;
    }
    
    
    /**
     * Constructor para ingresar el puerto de escucha del nodo anterior
     * @param portEscuchaAnterior 
     * @param flagDespacho 
     */
    public Servidor(int portEscuchaAnterior,int flagDespacho) {
        this.portEscuchaAnterior = portEscuchaAnterior;
        this.despacho= flagDespacho;// bandera si atendera despacho 1, sino 0
    }
    /**
     * Constructor para generar el transporte/ despacho
     * Inicia o cambia cada 5 seg el mensaje desde la clase DespachoHilo
     * @param mensajeDespacho 
     */
    public Servidor(String mensajeDespacho) {
        this.mensajeDespacho = mensajeDespacho;
    }

    
    
    /**
     *  @since 30/jun/2018
     * @see http://www.jc-mouse.net/proyectos/ejemplo-socket-java-clienteservidor
     */
    public void iniciarConexionServer() {
        
        try {
            //Socket de servidor para esperar peticiones de la red
            //Escuchara peticiones de su predecesor (nodo anterior)
            System.out.println("servidor levantado en el puerto "+portEscuchaAnterior);
            ServerSocket serverSocket = new ServerSocket(portEscuchaAnterior);
            System.out.println("Servidor> Servidor iniciado ");
            System.out.println("Servidor> En espera de cliente...");    
            //Socket de cliente
            Socket clientSocket;
//            clientSocket = serverSocket.accept();
            while(true){
                //en espera de conexion, si existe la acepta
                clientSocket = serverSocket.accept();
                //Para leer lo que envie el cliente
               // BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //para imprimir datos de salida                
               
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedReader input1 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedReader input2 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedReader input3 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 /*   PrintStream output = new PrintStream(clientSocket.getOutputStream());
                    PrintStream output1 = new PrintStream(clientSocket.getOutputStream());//T1
                    PrintStream output2 = new PrintStream(clientSocket.getOutputStream());//T2
                    PrintStream output3 = new PrintStream(clientSocket.getOutputStream());//T3*/
                    //se lee peticion del cliente
                    String request = input.readLine();
                    System.out.println("nodo recibe petición del nodo anterior > [" + request + "]");
                    if (request.equals("transporte"))
                    {
                        String request1 = input1.readLine();// para cada transporte
                        String request2 = input2.readLine();//
                        String request3 = input3.readLine();  
                        Thread.sleep(20000);//cada 20 seg lo q dura el tramo
                        System.out.println("nodo recibe petición del nodo anterior 1> [" + request1 + "]");
                        Thread.sleep(20000);//cada 20 seg lo q dura el tramo
                        System.out.println("nodo recibe petición del nodo anterior 2> [" + request2 + "]");
                        Thread.sleep(20000);//cada 20 seg lo q dura el tramo
                        System.out.println("nodo recibe petición del nodo anterior3 > [" + request3 + "]");
                    }
                    
//                    if(request != null)
//                    {
//                        guardarPaquetes(request);
//                     
//                    }
                    //se procesa la peticion y se espera resultado
                   // String strOutput = process(request); 
                    int x = getDespacho();
                    //iniciar transporte
                    // output.flush();//vacia contenido
                    if(x==1 && cont ==0 ){ //&& strOutput.equals("enviar")
                            System.out.println("entro a despachar");
                            Thread.sleep(5000);// cada 5 segundos envio
                           // output1.flush();//vacia contenido
                            //output1.println(mensajeTransporte(1)); // enviar mensaje al cliente 
                            guardarPaquetes(mensajeTransporte(1));
//                            Thread.sleep(5000);// cada 5 segundos envio                            
                            guardarPaquetes(mensajeTransporte(2));
                            //output2.println(mensajeTransporte(2)); // enviar mensaje al cliente 
//                            Thread.sleep(5000);// cada 5 segundos envio
                           guardarPaquetes(mensajeTransporte(3));
                            // output3.println(mensajeTransporte(3)); // enviar mensaje al cliente 
//                            Thread.sleep(5000);// cada 5 segundos envio
                                 cont++;
                            setDespacho(0);
                             
                }
                //se imprime en cliente
               // output.flush();//vacia contenido
                //output.println(strOutput); // enviar mensaje al cliente               
                //cierra conexion
               // clientSocket.close();
            }    
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
         catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    /**
     * Generar mensaje para cada transporte
     * @param numeroTransporte
     * @return 
     */
    public String mensajeTransporte(int numeroTransporte) throws InterruptedException{
        System.out.println("embarcando transporte "+numeroTransporte);
         Thread.sleep(5000);// cada 5 segundos envio
        String mens=null;
        if (numeroTransporte==1) 
            mens=T1;
        else if(numeroTransporte==2)
            mens=T2;
        else if(numeroTransporte==3)
            mens=T3;
        ///System.out.println("IMPRIMIR DE "+ mens);
        return mens;
    }
    
    /**
     * procesa peticion del cliente y retorna resultado
     * @param request peticion del cliente
     * @return String
     */
    public static String process(String request){
       String result="";      
       
        if(request!=null) switch(request){
            case "frase":          
                break;
            case "libro":
                break;
            case "enviar":                
                result = "enviar";
                break;
            case "exit":                
                result = "bye";
                break;            
            
            default:
//              int x = despacho;
//              if(x==1)
//                  result="Empezando a despachar";
//              else
                result = "La peticion no se puede resolver.";
              break;
        }
        return result;
    }
    

// public String leerPaquetes(int numTransporte){
//      File archivo = null, archivoAlmacen =null;
//      FileReader fr = null;
//      FileWriter fw = null;
//      BufferedReader br = null;
//      String linea =null;
//      try {
//         // Apertura del fichero y creacion de BufferedReader para poder
//         // hacer una lectura comoda (disponer del metodo readLine()).
//         archivo = new File ("src/paquetes/paquetes.txt");
//         archivoAlmacen = new File("src/almacenes/almacen"+numTransporte+".txt");
//         fr = new FileReader (archivo);
//         fw = new FileWriter (archivoAlmacen);
//         br = new BufferedReader(fr);
//
//         // Lectura del fichero
//        linea=br.readLine();
//        fw.write(linea);
//        fw.close();
//        // while((linea=br.readLine())!=null)
//            //System.out.println(linea);
//      }
//      catch(Exception e){
//        // e.printStackTrace();
//         System.err.println(e);
//      }finally{
//         // En el finally cerramos el fichero, para asegurarnos
//         // que se cierra tanto si todo va bien como si salta 
//         // una excepcion.
//         try{                    
//            if( null != fr ){   
//               fr.close();     
//            }                  
//         }catch (Exception e2){ 
//           // e2.printStackTrace();
//            System.err.println(e2);
//         }
//      }    
//     return linea;
//
// }
//    
    /**
     * 
     * @param request
     * @param numTransporte
     * @return 
     */
 public String guardarPaquetes(String request){
      File archivo = null, archivoAlmacen = null;
    //  FileReader fr = null;
      BufferedReader br = null;
      FileWriter fw = null;
      String linea =null;
      String numTransporte =null;
     
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         
       //  archivo = new File ("src/paquetes/paquetes.txt");
      //   fr = new FileReader (archivo);
      //  String myIP = InetAddress.getLocalHost().getHostAddress();
        String [] trama= request.split("="); // obtendre el numero del transporte
        numTransporte=trama[0];
        if(trama[1] != null)
            request = trama[1];
      
         archivoAlmacen = new File ("src/almacenes/almacen"+numTransporte+".txt");
          System.out.println("Guardando el trasporte "+numTransporte);
        // br = new BufferedReader(fr);
         fw = new FileWriter (archivoAlmacen);
         fw.write(request);
         fw.close();
         // Lectura del fichero
        //linea=br.readLine();
        // while((linea=br.readLine())!=null)
            //System.out.println(linea);
            
            /* archivo = new File ("src/paquetes/paquetes.txt");
         archivoAlmacen = new File("src/almacenes/almacen"+portSiguiente+".txt");
         fr = new FileReader (archivo);
         fw = new FileWriter (archivoAlmacen);
         br = new BufferedReader(fr);

         // Lectura del fichero
        linea=br.readLine();
        fw.write(linea);
        fw.close();*/
      }
      catch(Exception e){
        // e.printStackTrace();
         System.err.println(e);
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fw ){   
               fw.close();     
            }                  
         }catch (Exception e2){ 
           // e2.printStackTrace();
            System.err.println(e2);
         }
      }    
     return linea;

 }
        
}
