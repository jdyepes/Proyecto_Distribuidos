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
    
    private int despacho; //bandera si soy el almacen que despacho envio el transporte
    public String mensajeHaciaServidor;
    
   /**
    * Constructor
    * @param puertoServidorAnterior
    * @param numPuertoSiguiente
    * @param direccionSiguiente 
    */
    public HiloCliente(int puertoServidorAnterior, int numPuertoSiguiente, String direccionSiguiente,int flagDespacho) {
        this.numPuertoServidor = puertoServidorAnterior;
        this.numPuertoSiguiente = numPuertoSiguiente;
        this.direccionSiguiente = direccionSiguiente;
        this.despacho=flagDespacho;
    }
    
    
    
    @Override
    public void run(){
//        servidor.Servidor ser = new Servidor(numPuertoServidor);
        cliente.Cliente cli = new Cliente(numPuertoSiguiente, direccionSiguiente,despacho);
//        ser.iniciarConexionServer();

     //  System.out.println("mensaje que circula por el anillo desde el hiloCliente"+ cli.mensajeHaciaServidor);
        
        try {
            cli.iniciarConexionCliente();
        } catch (Exception ex) {
            System.out.println("transporte.HiloC_S.run() "+ex);
        }
    }
    
    
    
}
