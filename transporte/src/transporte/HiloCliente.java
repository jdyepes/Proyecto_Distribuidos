package transporte;

import cliente.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Servidor;

/**
 *
 * @author jdyepes
 */
public class HiloCliente extends Thread{
    private int numPuertoServidor; // numero del puerto rol servidor para que escuche al nodo anterior
    private int numPuertoSiguiente; // puerto del siguinete nodo rol de cliente
    String direccionSiguiente;// dir ip del nod sigiente en rol de cliente
    
   /**
    * Constructor
    * @param puertoServidorAnterior
    * @param numPuertoSiguiente
    * @param direccionSiguiente 
    */
    public HiloCliente(int puertoServidorAnterior, int numPuertoSiguiente, String direccionSiguiente) {
        this.numPuertoServidor = puertoServidorAnterior;
        this.numPuertoSiguiente = numPuertoSiguiente;
        this.direccionSiguiente = direccionSiguiente;
    }
    
    @Override
    public void run(){
//        servidor.Servidor ser = new Servidor(numPuertoServidor);
        cliente.Cliente cli = new Cliente(numPuertoSiguiente, direccionSiguiente);
//        ser.iniciarConexionServer();
        
        try {
            cli.iniciarConexionCliente();
        } catch (Exception ex) {
            System.out.println("transporte.HiloC_S.run() "+ex);
        }
    }
    
    
    
}
