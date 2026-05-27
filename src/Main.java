import db.operaciones.CantanteDAO;
import model.Cantante;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CantanteDAO dao = new CantanteDAO();
        Scanner scanner = new Scanner(System.in);
        Scanner leer = new Scanner(System.in);

        while (true) {
            System.out.println("\n---------------------------------------------");
            System.out.println("   SISTEMA DE GESTIÓN DE CANTANTES (NEON)   ");
            System.out.println("--------------------------------------------");
            System.out.println("1. Adicionar un registro de cantante");
            System.out.println("2. Consultar todos los registros");
            System.out.println("3. Consultar un registro por ID");
            System.out.println("4. Filtrar cantantes por nacionalidad");
            System.out.println("5. Salir del programa");
            System.out.print("Seleccione una opción (1-5): ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 5) {
                System.out.println("\n👋 Saliendo del sistema de gestión. ¡Hasta luego!");
                break;
            }

            switch (opcion) {
                case 1:
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
                    Cantante c = new Cantante(nombre, nacionalidad, generoMusical, edad, cancionReconocida);
                    dao.insertarCantante(c);
            }

        }
    }
}
