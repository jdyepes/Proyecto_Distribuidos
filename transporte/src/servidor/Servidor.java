
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
    
    static final String T1 = "localhost:192.168.0.108:holaT1$localhost:192.168.0.100:T1hola2$localhost:192.168.0.101:T1hola3$localhost:192.168.0.108:T1hola4$localhost:192.168.0.108:T1hola5";
    static final String T2 = "localhost:192.168.0.108:holaT2$localhost:192.168.0.100:T2hola2$localhost:192.168.0.101:T2hola3$localhost:192.168.0.108:T2hola4$localhost:192.168.0.108:T2hola5";
    static final String T3 = "localhost:192.168.0.108:holaT3$localhost:192.168.0.100:T3hola2$localhost:192.168.0.101:T3hola3$localhost:192.168.0.108:T3lhola4$ocalhost:192.168.0.108:T3hola5";
    /*****************************************************************/
    private static int despacho; // BANDERA que indica si soy el despachador
    private int portEscuchaAnterior;//= 5001;
    private String mensajeDespacho;

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
            while(true){
                //en espera de conexion, si existe la acepta
                clientSocket = serverSocket.accept();
                //Para leer lo que envie el cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //para imprimir datos de salida                
                PrintStream output = new PrintStream(clientSocket.getOutputStream());
                PrintStream output1 = new PrintStream(clientSocket.getOutputStream());//T1
                PrintStream output2 = new PrintStream(clientSocket.getOutputStream());//T2
                PrintStream output3 = new PrintStream(clientSocket.getOutputStream());//T3
                //se lee peticion del cliente
                String request = input.readLine();
                System.out.println("Servidor recibe petición > [" + request + "]");
                //se procesa la peticion y se espera resultado
                String strOutput = process(request); 
                int x = getDespacho();
                //iniciar transporte
                 output.flush();//vacia contenido
                if(x==1 && strOutput.equals("hola")){ 

                        Thread.sleep(1000);// cada 5 segundos envio
                        output1.flush();//vacia contenido
                        output1.println(mensajeTransporte(1)); // enviar mensaje al cliente 
                        Thread.sleep(5000);// cada 5 segundos envio                            
                        output2.println(mensajeTransporte(2)); // enviar mensaje al cliente 
                        Thread.sleep(5000);// cada 5 segundos envio
                        output3.println(mensajeTransporte(3)); // enviar mensaje al cliente 
                        Thread.sleep(5000);// cada 5 segundos envio
//                             cont++;
                        setDespacho(0);
                             
                }
                //se imprime en cliente
                output.flush();//vacia contenido
                output.println(strOutput); // enviar mensaje al cliente               
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
    public String mensajeTransporte(int numeroTransporte){
        System.out.println("entro a transporte con "+numeroTransporte);
        String mens=null;
        if (numeroTransporte==1) 
          return   mens=T1;
        else if(numeroTransporte==2)
          return   mens=T2;
        else if(numeroTransporte==3)
           return  mens=T3;
        
        return mens;
    }
    
    /**
     * procesa peticion del cliente y retorna resultado
     * @param request peticion del cliente
     * @return String
     */
    public static String process(String request){
       String result="";      
       
        //frases
        String[] phrases = {
            "La tecnología se alimenta a si misma. La tecnología hace posible más tecnología.-Alvin Toffler.",
            "La tecnología es sólo una herramienta. En términos de llevar a los niños a trabajar juntos y motivarlos, el profesor es el más importante.-Bill Gates.",
            "La máquina tecnológicamente más eficiente que el hombre ha inventado es el libro.-Northrop Frye.",
            "Ya no hacen más los bugs como bunny - Olav Mjelde",
            "Un lenguaje de programación es de bajo nivel cuando requiere que prestes atencion a lo irrelevante.-Alan J. Perlis.",
            "Hablar es barato. Enséñame el código.-Linus Torvalds ",
            "No me importa si funciona en su máquina! No me envían su máquina!.-Vidiu Platon",
            "Siempre codifica como si la persona que finalmente mantendrá tu código fuera un psicópata violento que sabe dónde vives.-Martin Golding"};
	ArrayList<String> phrasesList = new ArrayList<>();
	Collections.addAll(phrasesList, phrases);
        //libros
        String[] books = {
            "Divina Comedia - Dante Alighieri", 
            "Don Quijote de la Mancha - Miguel de Cervantes",
            "Cien años de soledad - Gabriel García Márquez",
            "Moby Dick - Herman Melville",
            "Ana Karenina - Lev Tolstói",
            "Eneida - Virgilio",
            "Otelo - William Shakespeare",
            "El viejo y el mar - Ernest Hemingway",
            "Orgullo y prejuicio - Jane Austen"};
	ArrayList<String> booksList = new ArrayList<>();
	Collections.addAll(booksList, books);        
        
        if(request!=null) switch(request){
            case "frase":
                Collections.shuffle(phrasesList);
                result = phrasesList.get(0);
                break;
            case "libro":
                Collections.shuffle(booksList);
                result = booksList.get(0);
                break;
            case "hola":                
                result = "hola";
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
        
}
