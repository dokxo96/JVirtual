package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class principal {

    public static void main(String[] args) throws Exception {                
        /*  Agregen sus propias rutas
        esta ruta es de david
        String ruta1 = "C:/proyectosNeatBeans/JVirtualLocal/src/codigo/Lexer.flex";
        String ruta2 = "C:/proyectosNeatBeans/JVirtualLocal/src/codigo/LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", "C:/proyectosNeatBeans/JVirtualLocal/src/codigo/Sintax.cup"};  */
        //esta ruta es de daniel
        String ruta1 = "C:\\Users\\dokxo\\Documents\\NetBeansProjects\\JVirtual\\src\\codigo\\Lexer.flex";
        String ruta2 = "C:\\Users\\dokxo\\Documents\\NetBeansProjects\\JVirtual\\src\\codigo\\LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", "C:\\Users\\dokxo\\Documents\\NetBeansProjects\\JVirtual\\src\\codigo\\Sintax.cup"};   
        generar(ruta1, ruta2, rutaS );   
    }

    public static void generar(String ruta1 , String ruta2, String[] rutaS) throws IOException, Exception {       
        File archivo;
        archivo = new File(ruta1);
        jflex.Main.generate(archivo);
        
        archivo = new File(ruta2);
        jflex.Main.generate(archivo);

        java_cup.Main.main(rutaS);
        
        /*
        Path rutaSym = Paths.get("C:/proyectosNetBeans/analizadorPruebaDos/sym.java");
        System.out.print("Aqui rutaSym");
        if (Files.exists(rutaSym)) {            
            Files.move(
              Paths.get("C:/proyectosNetBeans/analizadorPruebaDos/sym.java"),
              Paths.get("C:/proyectosNetBeans/analizadorPruebaDos/src/codigo/sym.java")
            );
        }

        Path rutaSin = Paths.get("C:/proyectosNetBeans/analizadorPruebaDos/Sintax.java");
        if (Files.exists(rutaSin)) {
            System.out.print("Aqui rutaSym");
            Files.move(
                    Paths.get("C:/proyectosNetBeans/analizadorPruebaDos/Sintax.java"),
                    Paths.get("C:/proyectosNetBeans/analizadorPruebaDos/src/codigo/Sintax.java")
            );
        }
        */
    }
}
