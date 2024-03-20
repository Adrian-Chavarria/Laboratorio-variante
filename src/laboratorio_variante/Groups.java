
package laboratorio_variante;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * This is the main class where the communication with the user is located,
 * loading the txt file and the methods to create groups by quantity
 * groups and by number of students
 * @author Adrian Chavarria, Joselyn Abarca, Carlos Barroso, Jeison Alvarez
 * 
 */


public class Groups {
    /**
     * 
     * In this section you will find the main with the communication with the user.
     * a switch as a menu and everything inside a while to repeat itself
     * @throws IOException Excepcion
     */
    public static void main(String[] args) throws IOException {
        /**
         * 
         */
        Scanner scanner = new Scanner(System.in);
        Student[] students = readFile();
        boolean salir=false;
        while (!salir) {
            System.out.println("Seleccione cómo desea crear los grupos:");
            System.out.println("1 = Crear grupos por cantidad total de grupos.");
            System.out.println("2 = Crear grupos por cantidad de personas por grupo.");
            System.out.println("3 = Salir");
            int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Digite la cantidad de grupos:");
                int cantidadGrupos = scanner.nextInt();
                Arrays.sort(students); // Ordenar estudiantes por nota
                crearGruposPorCantidadGrupos(students, cantidadGrupos);
                break;
            case 2:
                System.out.println("Ingrese la cantidad de "
                                + "estudiantes por grupo:");
                int cantidadEstudiantesPorGrupo = scanner.nextInt();
                Arrays.sort(students); // Ordenar estudiantes por nota
                crearGruposPorCantidadEstudiantes(students, 
                    cantidadEstudiantesPorGrupo);
                break;
            case 3:
                salir=true;
                 System.out.println("Haz salido del sistema.");
            default:
                System.out.println("Opción no válida.");
        }
        }
    }
    /**
     * Method used to store data preloaded into an array
     * @return returns the array with the preloaded data
     * @throws FileNotFoundException Esception
     * @throws IOException  Exception 
     */
    private static Student[] readFile() throws FileNotFoundException, IOException {
        Student[] students = new Student[30];
        String fileName = "reporte.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        try {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                Student student = new Student(
                        line.split(",")[1],
                        line.split(",")[2],
                        Integer.parseInt(line.split(",")[3])
                );
                students[i] = student;
                i++;
            }
        } finally {
            br.close();
        }

        return students;
    }
    /**
     * 
     * @param students array with student data
     * @param cantidadGrupos variable that stores the number of groups
     */
    private static void crearGruposPorCantidadGrupos(Student[] students, 
            int cantidadGrupos) {
        int cantidadPersonas = students.length / cantidadGrupos;
        int estudiantesExtra = students.length % cantidadGrupos;
        int[][] matriz = new int[cantidadGrupos][cantidadPersonas + 1];

        int indiceEstudiante = 0;
        for (int i = 0; i < cantidadGrupos; i++) {
            int cantidadPorGrupo = cantidadPersonas;
            if (i < estudiantesExtra) {
                cantidadPorGrupo++;
            }
            for (int j = 0; j < cantidadPorGrupo; j++) {
                if (indiceEstudiante < students.length) {
                    matriz[i][j] = students[indiceEstudiante].grade;
                    indiceEstudiante++;
                }
            }
        }
        // Imprimir los grupos
        for (int i = 0; i < cantidadGrupos; i++) {
            System.out.println("Grupo " + (i + 1) + ":");
            for (int j = 0; j < matriz[i].length && matriz[i][j] != 0; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**
     * 
     * @param students array with student data
     * @param cantidadEstudiantesPorGrupo variable that stores the quantity
     * students per group
     */
    private static void crearGruposPorCantidadEstudiantes(Student[] students, 
            int cantidadEstudiantesPorGrupo) {
        int cantidadGrupos2 = students.length / cantidadEstudiantesPorGrupo;
        if (students.length % cantidadEstudiantesPorGrupo != 0) {
            cantidadGrupos2++; // Add an additional group if the quantity
                            //of students is not divisible
        }
        int[][] matriz2 = new int[cantidadGrupos2][cantidadEstudiantesPorGrupo];
        int indiceEstudiante2 = 0;
        for (int i = 0; i < cantidadGrupos2; i++) {
            for (int j = 0; j < cantidadEstudiantesPorGrupo; j++) {
                if (indiceEstudiante2 < students.length) {
                    matriz2[i][j] = students[indiceEstudiante2].grade;
                    indiceEstudiante2++;
                }
            }
        }
        // Imprimir los grupos
        for (int i = 0; i < cantidadGrupos2; i++) {
            System.out.println("Grupo " + (i + 1) + ":");
            for (int j = 0; j < cantidadEstudiantesPorGrupo; j++) {
                if (matriz2[i][j] != 0) {
                    System.out.print(matriz2[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}