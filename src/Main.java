import db.operaciones.CantanteDAO;
import model.Cantante;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CantanteDAO dao = new CantanteDAO();

        Scanner leer =  new Scanner(System.in);

        System.out.println("Ingrese el nombre del cantante");
        String nombre = leer.nextLine();
        System.out.println("Ingrese la nacionalidad del cantante");
        String nacionalidad = leer.nextLine();
        System.out.println("Ingrese el g[enero musical del cantante");
        String generoMusical = leer.nextLine();
        System.out.println("Ingrese la edad del cantante");
        int edad = leer.nextInt();
        System.out.println("Ingrese una canción reconocida del cantante");
        String cancionReconocida = leer.nextLine();
        //dao.insertarCantante(new Cantante("KOLO TOURE", 19,"DELANTERO",true));
        Cantante c = new Cantante(nombre, nacionalidad, generoMusical, edad, cancionReconocida);

        dao.insertarCantante(c);
    }

}
