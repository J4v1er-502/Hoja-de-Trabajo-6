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
    public void instanceFatory() {
        System.out.println(" :: TIENDA ONLINE ::");

        // Implementar patron de diseño Factory para seleccionar que tipo de MAP
        // utilizara
        System.out.println("Ingrese que MAP implementara: ");
        System.out.println("1. HashMAP");
        System.out.println("2. TreeMAP");
        System.out.println("3. LinkedHashMap");
        Integer optionMAP = scInt.nextInt();

        // Instanciar el tipo de MAP que se utilizara
        collection = factoryMap.getMap(optionMAP);
        inventary = factoryMap.getMap(optionMAP);

    }

     /*
     * Lee el archivo y guarda los datos en el inventario
     */
    public void readFile() {
        // Leer el archivo de texto
        try {
            archivo = new File(src);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
        } catch (Exception e) {
            System.out.println("[!] No se encontro el archivo");
        }

        try {
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] products = linea.replace("|", ",").split(",");
                String category = products[0].trim().toUpperCase();
                String product = products[1].trim();

                try {

                    // Revisa si existe la categoria, si no existe la crea
                    if (inventary.get(category) != null) { // Existe la categoria

                        List<String> currentList = inventary.get(category);
                        currentList.add(product);
                        inventary.put(category, currentList);

                    } else { // No existe la categoria

                        List<String> newList = new ArrayList<String>();
                        newList.add(product);
                        inventary.put(category, newList);
                        categories.add(category);

                    }

                } catch (Exception e) {
                    System.out.println("[!] Error al agregar producto al inventario");
                }

            }

        } catch (Exception e) {
            System.out.println("[!] Error al leer el archivo ListadoProductos");
        }
        // Recorre todas las lineas del archivo
    }

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