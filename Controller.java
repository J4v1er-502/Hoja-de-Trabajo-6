import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Controller {

     // Utilidades
     private Scanner sc = new Scanner(System.in);
     private Scanner scInt = new Scanner(System.in);
 
     String src = "./ListadoProducto.txt";
     File archivo = null;
     FileReader fr = null;
     BufferedReader br = null;
 
     // Implementación MAP
     private FactoryMap factoryMap = new FactoryMap();
     private IMap<String, List<String>> collection;
     private IMap<String, List<String>> inventary;
     private List<String> categories = new ArrayList<String>();
 
    /*
     * Indica el tipo de implementación de MAP que se utilizara
     */
    public void instanceFatory() {}

     /*
     * Lee el archivo y guarda los datos en el inventario
     */
    public void readFile() {}

     /*
     * Muestra al usuario las opciónes disponibles
     */
    public void startProgram() {}

    /*
     * Agrega un producto del inventario a la coleccion del usuario
     */
    public void addProductToCollection(){}

    /*
     * Muestra de forma visual el contenido de un IMAP, ya sea el inventario o la
     * coleccion del usuario
     */
    public void showIMAP() {}

     /*
     * Busca el producto en el inventario y muestra de que categoria es
     */
    public void showCategoryOfAProduct() {}

    /*
     * Espera a que el usuario presione una tecla para continuar la ejecución
     */
    public void pressAnyKeyToContinue() {
        String seguir;
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n [] Presiona enter para continuar ... \n");
        try {
            seguir = teclado.nextLine();
        } catch (Exception e) {
        }
    }


}