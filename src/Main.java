import db.operaciones.CantanteDAO;
import model.Cantante;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CantanteDAO dao = new CantanteDAO();

        Scanner leer =  new Scanner(System.in);

        System.out.println("Ingrese el nombre del cantante");
        String Nombre = leer.nextLine();
        System.out.println("Ingrese la nacionalidad del cantante");
        String Nacionalidad = leer.nextLine();
        System.out.println("Ingrese el g[enero musical del cantante");
        String generoMusical = leer.nextLine();
        System.out.println("Ingrese la edad del cantante");
        int Edad = leer.nextInt();
        System.out.println("Ingrese una canción reconocida del cantante");
        String CancionReconocida = leer.nextLine();
        //dao.insertarCantante(new Cantante("KOLO TOURE", 19,"DELANTERO",true));
        Cantante c = new Cantante(Nombre, Nacionalidad, generoMusical, Edad, CancionReconocida);

        dao.insertarCantante(c);
    }

}
