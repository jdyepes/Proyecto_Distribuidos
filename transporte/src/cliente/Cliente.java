package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import transporte.HiloCliente;

public class Cliente {

    
   // puerto del nodo sucesor    
    private int portSiguiente;// = 5001;
  // direccion del servidor del sucesor
    private String serverSiguiente;// = "localhost";
    int cont =0;    
    /**
     * Constructor
     * @param PORT
     * @param SERVER 
     */
    public Cliente(int PORT, String SERVER) {
        this.portSiguiente = PORT;
        this.serverSiguiente = SERVER;
    }
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
                
                while( !exit ) { //ciclo repetitivo 
                      System.out.println("entro con "+serverSiguiente+" " +portSiguiente);  
                       
                    Socket socket = new Socket(serverSiguiente, portSiguiente);//abre socket  
                    
                    //Para leer lo que envie el servidor      
                    BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));                
                    //para imprimir datos del servidor
                    PrintStream output = new PrintStream(socket.getOutputStream());                
                    //Para leer lo que escriba el usuario            
                    BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));            
                    System.out.println("Cliente> Escriba comando");                
                    //captura comando escrito por el usuario
                    String request = brRequest.readLine();   

                    //manda peticion al servidor               
                    output.println(request); 

                    //captura respuesta del servidor e imprime
                    String st = input.readLine(); 

                    if( st != null ) 
                        System.out.println("Servidor> " + st );   

                    if(request.equals("exit")) //terminar aplicacion
                    {
                        exit=true;                  
                        System.out.println("Cliente> Fin de programa");    
                    }  
                    socket.close();
                }//end while                  
            } 
           if (cont==4)
                 {
                    
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
    
}