package transporte;

import cliente.Cliente;
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
//        int numPuertoServidor = Integer.parseInt(puertoServidorAnterior);
        int numPuertoServidor = 5000;
        
        //indica el puerto y direccion ip al nodo siguiente para conectarse
        BufferedReader portNext = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el puerto del nodo sig a conectar *rol cliente* ");    
        String puertoSiguiente = portNext.readLine(); 
       // int numPuertoSiguiente = Integer.parseInt(puertoSiguiente);
        int numPuertoSiguiente = 5000;
        
        //indica  direccion ip al nodo siguiente para conectarse
        BufferedReader dirNext = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese la direccion ip del nodo sig a conectar *rol cliente* ");    
       // String direccionSiguiente = dirNext.readLine(); 
       String direccionSiguiente = "192.168.0.100"; 
       
        //indica si sera el almacen que va iniciar los transportes
        BufferedReader flagDespacho= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Atender Despacho ? Indique Si = 1  No =0 *Iniciar Transporte* ");    
        String IniciarDespacho = flagDespacho.readLine(); 
//        int numIniciarDespacho = Integer.parseInt(IniciarDespacho);
        int numIniciarDespacho = 0;
        
//        servidor.Servidor ser = new Servidor(numPuertoServidor);
//        ser.iniciarConexionServer();
       HiloServidor hiloSer= new HiloServidor(numPuertoServidor,numIniciarDespacho);     
        HiloCliente hiloCli = new HiloCliente(numPuertoServidor, numPuertoSiguiente, direccionSiguiente,numIniciarDespacho);
    
       hiloSer.start();// falta los mensajes
          hiloCli.start();
       
      // System.out.println("mensaje que circula por el anillo desde el tranporte"+ hiloCli.mensajeHaciaServidor);
    }
    
}
