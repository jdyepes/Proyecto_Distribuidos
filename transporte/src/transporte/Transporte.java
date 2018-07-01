package transporte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import servidor.Servidor;

/**
 *
 * @author jdyepes
 */
public class Transporte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //Lectura por teclado del usuario  
        // indicar el puerto de escucha para el nodo anterior
        BufferedReader portServer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese su puerto de escucha *rol servidor* ");             
        String puertoServidorAnterior = portServer.readLine(); 
        int numPuertoServidor = Integer.parseInt(puertoServidorAnterior);
        
        //indica el puerto y direccion ip al nodo siguiente para conectarse
        BufferedReader portNext = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el puerto del nodo sig a conectar *rol cliente* ");    
        String puertoSiguiente = portNext.readLine(); 
        int numPuertoSiguiente = Integer.parseInt(puertoSiguiente);
        
        //indica  direccion ip al nodo siguiente para conectarse
        BufferedReader dirNext = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese la direccion ip del nodo sig a conectar *rol cliente* ");    
        String direccionSiguiente = dirNext.readLine(); 
        
//        servidor.Servidor ser = new Servidor(numPuertoServidor);
//        ser.iniciarConexionServer();
        HiloServidor hiloSer= new HiloServidor(numPuertoServidor);     
        HiloCliente hiloCli = new HiloCliente(numPuertoServidor, numPuertoSiguiente, direccionSiguiente);
       hiloCli.start();
       hiloSer.start();
    }
    
}
