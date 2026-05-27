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
                System.out.println("\n Saliendo del sistema de gestión. ¡Hasta luego!");
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
                    leer.nextLine();
                    System.out.println("Ingrese una canción reconocida del cantante");
                    String cancionReconocida = leer.nextLine();
                    Cantante c = new Cantante(nombre, nacionalidad, generoMusical, edad, cancionReconocida);
                    dao.insertarCantante(c);

                    break;
                case 2:
                    System.out.println("\n --- LISTA DE TODOS LOS CANTANTES EN NEON ---");
                    List<Cantante> listaCompleta = dao.consultarTodos();
                    if (listaCompleta.isEmpty()) {
                        System.out.println("No hay registros almacenados en la base de datos.");
                    } else {
                        for (Cantante ca : listaCompleta) {
                            System.out.println("ID: " + ca.getId() + " | " + ca.getNombre() + " (" + ca.getNacionalidad() + ") - Género: " + ca.getGeneromusical() + " | Edad: " + ca.getEdad() + " | Éxito: '" + ca.getCancionreconocida() + "' | Registrado: " + ca.getRegistrado());
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n --- CONSULTAR UN REGISTRO ---");
                    System.out.print("Ingrese el ID del cantante a buscar: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    Cantante cantanteEncontrado = dao.consultarUnRegistro(idBuscar);

                    if (cantanteEncontrado != null) {
                        System.out.println("\n Registro Encontrado");
                        System.out.println("-------------------------------------");
                        System.out.println("• Nombre: " + cantanteEncontrado.getNombre());
                        System.out.println("• Nacionalidad: " + cantanteEncontrado.getNacionalidad());
                        System.out.println("• Género Musical: " + cantanteEncontrado.getGeneromusical());
                        System.out.println("• Edad: " + cantanteEncontrado.getEdad() + " años");
                        System.out.println("• Canción Famosa: " + cantanteEncontrado.getCancionreconocida());
                        System.out.println("• ID: " + cantanteEncontrado.getId());
                        System.out.println("• Fecha de Registro: " + cantanteEncontrado.getRegistrado());
                        System.out.println("-------------------------------------");
                    } else {
                        System.out.println("No existe ningún cantante registrado con el ID: " + idBuscar);
                    }
                    break;
                case 4:
                System.out.println("\n--- FILTRO ---");
                System.out.println("¿Por qué criterio desea filtrar?");
                System.out.println("1. Filtrar por Nacionalidad");
                System.out.println("2. Filtrar por Género Musical");
                System.out.println("3. Filtrar por Nombre del Cantante");
                System.out.print("Seleccione una opción (1, 2 o 3): ");

                int subOpcion = scanner.nextInt();
                scanner.nextLine(); // Limpieza de buffer

                String columnaSeleccionada = "";
                String nombreCriterio = "";

                switch (subOpcion) {
                    case 1:
                        columnaSeleccionada = "nacionalidad";
                        nombreCriterio = "Nacionalidad";
                        break;
                    case 2:
                        columnaSeleccionada = "generomusical";
                        nombreCriterio = "Género Musical";
                        break;
                    case 3:
                        columnaSeleccionada = "nombre";
                        nombreCriterio = "Nombre";
                        break;
                    default:
                        System.out.println("Opción de filtro no válida. Regresando al menú principal.");
                        columnaSeleccionada = null; // Bandera de control para cancelar la operación si el índice es incorrecto
                        break;
                }

                // Si la columna es válida, procesa la consulta avanzada con PreparedStatement (Criterio 6)
                if (columnaSeleccionada != null) {
                    System.out.print("Escriba el valor de " + nombreCriterio + " a buscar: ");
                    String valorBusqueda = scanner.nextLine();

                    // Ejecución de la consulta filtrada dinámica a través del DAO
                    List<Cantante> listaFiltrada = dao.filtrarPorCriterio(columnaSeleccionada, valorBusqueda);

                    if (listaFiltrada.isEmpty()) {
                        System.out.println("No se encontraron cantantes con ese criterio de búsqueda.");
                    } else {
                        System.out.println("\nCantantes encontrados (" + nombreCriterio + " similar a '" + valorBusqueda + "'):");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------");
                        // Se corrige el nombre de la variable local a 'f' para evitar la colisión de nombres con la 'c' declarada en el case 1
                        for (Cantante f : listaFiltrada) {
                            System.out.println("ID: " + f.getId() + " | " + f.getNombre() + " (" + f.getNacionalidad() + ") - Género: " + f.getGeneromusical() + " | Éxito: " + f.getCancionreconocida());
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------------------------");
                    }
                }
                break;

                default:
                    System.out.println("Opción incorrecta. Intente un número del 1 al 5.");
                    break;
            }
        }
        }
    }
