package rmiexample;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author karol
 */
public class RMIClientExample {
	private static final String IP = "localhost"; // Puedes cambiar a localhost
	private static final int PUERTO = 5555; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
	
//     public void establecerConexion()throws RemoteException, NotBoundException 
//     {
//         Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
//         TestRemote testRemote = (TestRemote) registry.lookup("Calculadora"); //Buscar en el registro...
//       
//     }
     public void actualizar(String trama) throws RemoteException, NotBoundException
     {
         
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        TestRemote testRemote = (TestRemote) registry.lookup("Calculadora"); //Buscar en el registro...
        System.out.println("x =");
       
        Calendar calendario = new GregorianCalendar();
        int hora, minutos, segundos;
        hora =calendario.get(Calendar.HOUR);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        
        String x = testRemote.actualizarEstadisticas(trama+hora+":"+minutos+":"+segundos);
        System.out.println("x ="+x);
     
     }
        
//   // public static void main(String[] args) throws RemoteException, NotBoundException {
//        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
//        TestRemote testRemote = (TestRemote) registry.lookup("Calculadora"); //Buscar en el registro...
//        Scanner sc = new Scanner(System.in);
//        int eleccion;
//        float numero1, numero2, resultado = 0;
//        System.out.println("x =");
//       
//        Calendar calendario = new GregorianCalendar();
//        int hora, minutos, segundos;
//        hora =calendario.get(Calendar.HOUR);
//        minutos = calendario.get(Calendar.MINUTE);
//        segundos = calendario.get(Calendar.SECOND);
//        
//        String x = testRemote.actualizarEstadisticas("hola ayudame te lo pido por favor"+hora+":"+minutos+":"+segundos);
//        System.out.println("x ="+x);
//        
//        
////        String menu = "\n\n------------------\n\n[-1] => Salir\n[0] => Sumar\n[1] => Restar\n[2] => Multiplicar\n[3] => Dividir\nElige: ";
////        do {
////            System.out.println(menu);
////
//////            try {
//////                eleccion = Integer.parseInt(sc.nextLine());
//////            } catch (NumberFormatException e) {
//////                eleccion = -1;
//////            }
////
//////            if(eleccion != -1){
////
//////            	System.out.println("Ingresa el número 1: ");
//////            	try{
//////                	numero1 = Float.parseFloat(sc.nextLine());
//////            	}catch(NumberFormatException e){
//////            		numero1 = 0;
//////            	}
//////
//////            	System.out.println("Ingresa el número 2: ");
//////            	try{
//////                	numero2 = Float.parseFloat(sc.nextLine());
//////            	}catch(NumberFormatException e){
//////            		numero2 = 0;
//////            	}
//////                switch (eleccion) {
////////	                case 0:
////////	                    resultado = testRemote.sumar(numero1, numero2);
////////	                    break;
////////	                case 1:
////////	                    resultado = testRemote.restar(numero1, numero2);
////////	                    break;
////////	                case 2:
////////	                    resultado = testRemote.multiplicar(numero1, numero2);
////////	                    break;
////////	                case 3:
////////	                    resultado = testRemote.dividir(numero1, numero2);
////////	                    break;
//////                        case 0:
//////                            System.out.println("x =");
//////                            String x = testRemote.actualizarEstadisticas("hoal");
//////                            System.out.println("x ="+x);
//////                            break;
//////	            }
////
////                System.out.println("Resultado es => " + String.valueOf(resultado));
////                System.out.println("Presiona ENTER para continuar");
////                sc.nextLine();
//////            }
////        } while (eleccion != -1);
//    }
}
