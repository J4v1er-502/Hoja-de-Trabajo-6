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
    public void startProgram() {

        boolean isActive = true;

        while (isActive) {
            System.out.println("\n :: TIENDA ONLINE ::");

            System.out.println("1. Agregar producto a la collecion");
            System.out.println("2. Mostrar categoria de un producto");
            System.out.println("3. Mostrar coleccion del usuario");
            System.out.println("4. Mostrar coleccion del usuario (Ordenados por categoria)");
            System.out.println("5. Mostrar inventario");
            System.out.println("6. Mostrar inventario (Ordenados por categoria)");
            System.out.println("7. Salir");
            Integer option = scInt.nextInt();

            if (option == 1) {

                addProductToCollection();

            } else if (option == 2) {
                showCategoryOfAProduct();

            } else if (option == 3) {
                System.out.println("\n :: COLECCION ::\n");
                showIMAP(collection, false);

            } else if (option == 4) {
                System.out.println("\n :: COLECCION ORDENADA POR CATEGORIA ::\n");
                showIMAP(collection, true);

            } else if (option == 5) {

                System.out.println("\n :: INVENTARIO ::\n");
                showIMAP(inventary, false);

            } else if (option == 6) {
                System.out.println("\n :: INVENTARIO ORDENADA POR CATEGORIA  ::\n");
                showIMAP(inventary, true);

            } else if (option == 7) {

                isActive = false;
                System.out.println("Finalizando programa ...");
                break;
            }

        }

    }

   /*
     * Agrega un producto del inventario a la coleccion del usuario
     */
    public void addProductToCollection() {

        Boolean isActive = true;

        while (isActive) {

            System.out.println("\n :: AGREGAR PRODUCTOS A LA COLECCION ::");

            System.out.print("\n-> Ingrese el nombre de una category (X -> salir): ");
            String category = sc.nextLine().toUpperCase();

            if (category.equals("X")) {
                isActive = false;
                break;

            } else {

                List<String> products = inventary.get(category);

                if (products == null) {
                    System.out.println("[!] La categoria que desea ingresar no existe");
                } else {

                    for (int i = 0; i < products.size(); i++) {
                        System.out.println((i + 1) + ". " + products.get(i));
                    }

                    System.out.print("\n-> Ingrese el numero de un producto ");
                    int productIndex = scInt.nextInt();
                    productIndex = productIndex - 1;

                    if (productIndex > products.size() || productIndex < 1) {
                        System.out.println("\n[!] No existe ese producto");
                    } else {

                        // Revisa si existe la categoria, si no existe la creo
                        if (collection.get(category) != null) {

                            List<String> currentList = collection.get(category);
                            currentList.add(products.get(productIndex));
                            collection.put(category, currentList);

                        } else {
                            List<String> newList = new ArrayList<String>();
                            newList.add(products.get(productIndex));
                            collection.put(category, newList);
                        }

                        System.out.println("\n[OK] Producto agregado exitosamente");

                    }

                }

            }
        }

    }


    /*
     * Muestra de forma visual el contenido de un IMAP, ya sea el inventario o la
     * coleccion del usuario
     */
    public void showIMAP(IMap<String, List<String>> tempList, Boolean isSort) {

        if (isSort) {
            Collections.sort(categories);
        }

        for (int i = 0; i < categories.size(); i++) {

            List<String> products = tempList.get(categories.get(i));

            if (products != null) {
                System.out.println("\n-> " + categories.get(i));

                for (int e = 0; e < products.size(); e++) {
                    System.out.println("  - " + products.get(e) + "| 1");
                }

            }
        }

        pressAnyKeyToContinue();

    }


     /*
     * Busca el producto en el inventario y muestra de que categoria es
     */
    public void showCategoryOfAProduct() {
        System.out.println("\n :: MOSTRAR CATEGORIA DE UN PRODUCTO ::\n");

        System.out.print("-> Ingrese el nombre de un producto: ");
        String product = sc.nextLine();

        String tempCategory = "";

        for (int i = 0; i < categories.size(); i++) {

            List<String> products = inventary.get(categories.get(i));

            for (int e = 0; e < products.size(); e++) {
                if (products.get(e).equals(product)) {
                    tempCategory = categories.get(i);
                }
            }

        }

        System.out.println("\n [OK] El producto ingresado pertence a la categoria: ");
        System.out.println("         -> " + tempCategory + "\n");
        pressAnyKeyToContinue();

    }
    
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