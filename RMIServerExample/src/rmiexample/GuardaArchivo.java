package rmiexample;

import java.io.*;

/**
 *
 * @author jdyepes
 */
public class GuardaArchivo 
{
    public String guardarArchivo(String informacion) throws IOException
    {
        String men=null;
        BufferedWriter bw = null;
    FileWriter fw = null;

    try {      
        File file = new File("src/rmiexample/informacion.txt");
        // Si el archivo no existe, se crea!
        if (!file.exists()) {
            file.createNewFile();
        }
        // flag true, indica adjuntar información al archivo.
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        bw.write(informacion+"$");
        men= "información agregada!";
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
                        //Cierra instancias de FileWriter y BufferedWriter
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//    FileWriter fichero = null;
//    PrintWriter pw = null;
//    String men = null;
//    try
//        {
//
//       
//            fichero = new FileWriter("src/rmiexample/informacion.txt");
//            pw = new PrintWriter(fichero,true);
//            //pw.println(informacion);
//            pw.append(informacion);
////            for (int i = 0; i < 10; i++)
////                pw.println("Linea " + i);
//            men= "se escribio exitoso";
//   
//        } catch (Exception e) {
//            System.out.println("ha ocurrido un error al escribir en el archivo");
//            men =e.toString();
//            
//        } 
//              fichero.close();
              return men;
//          
    }   
}
