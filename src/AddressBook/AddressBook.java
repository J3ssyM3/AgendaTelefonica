package AddressBook;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;
public class AddressBook {
    public static void main(String[] args) {
        HashMap<String, String> AT = new HashMap<String, String>();
        Scanner lectura = new Scanner(System.in);

        int opc, fn = 0;
        String telefono, nombre;
        System.out.println(" -------------> AGENDA TELÉFONICA <-------------- ");

        do
        {
            try{

                System.out.println("---> Seleccioné la opción que quiera realizar: <---");
                System.out.println("NUEVO NÚMERO --------------> [1]");
                System.out.println("MOSTRAR CONTACTOS ---------> [2]");
                System.out.println("BORRAR CONTACTO -----------> [3]");
                System.out.println("GUARDAR CAMBIOS -----------> [4]");
                System.out.println("CARGAR DATOS --------------> [5]");
                System.out.println("SALIR ---------------------> [6]");

                opc = lectura.nextInt();

                switch(opc)
                {
                    case 1:
                        System.out.println(" Ingresé el nuevo Número: ");
                        telefono = lectura.next();
                        System.out.println(" Ingresé el Nombre del Contacto: ");
                        nombre = lectura.next();
                        create(AT,telefono,nombre);
                        break;
                    case 2:
                        list(AT);
                        break;
                    case 3:
                        System.out.println(" Inserte el teléfono a eliminar: ");
                        telefono = lectura.next();
                        delete(AT, telefono);
                        break;
                    case 4:
                        save(AT);
                        break;
                    case 5:
                        load(AT);
                        break;
                    case 6:
                        System.out.println("Saliendo ......");
                        System.out.println("LISTO");
                        fn = 1;
                        break;
                    default:
                        System.out.println("¡OPCIÓN INCORRECTA!");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println(" ¡ERROR!");
                break;
            }
        }while(fn == 0);

    }

    public static void create(HashMap<String, String> AT, String tel, String nom)
    {

        if(AT.containsKey(tel))
        {
            System.out.println(" ¡ERROR! ---> NO SE PUEDE REGISTRAR EL NÚMERO 2 VECES ---> INTENTALO DE NUEVO ");
        }

        else
        {
            AT.put(tel, nom);
            System.out.println("¡CONTACTO AGREGADO!");
        }

    }
    public static void list(HashMap<String, String> AT)
    {
        Iterator<String> iterator = AT.keySet().iterator();

        System.out.println("Contactos:");
        System.out.println("  Teléfono     |    Nombre    ");
        System.out.println("-------------------------------");
        while(iterator.hasNext())
        {
            String llave = iterator.next();
            System.out.println("  "+llave+"   |  "+AT.get(llave));
        }

    }


    public static void delete(HashMap<String, String> AT, String tel)
    {
        if(AT.containsKey(tel))
        {
            System.out.println("¡CONTACTO ELIMINADO!: "+ AT.get(tel) );
            AT.remove(tel);

        }
        else
            System.out.println(" ¡EL CONTACTO NO EXISTE! ");


    }

    public static void save(HashMap<String, String> m)
    {
        Iterator<String> iterator = m.keySet().iterator();
        String inputFilename = "C:\\Users\\ONE\\Documents\\Agenda Telefonica\\src\\AddressBook\\imput.cvs";

        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));

            while(iterator.hasNext())
            {
                String llave = iterator.next();


                bufferedWriter.write(llave+","+m.get(llave)+"\n");
            }

        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                    System.out.println("---> LOS CAMBIOS HAN SIDO GUARDADOS <---");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }


    public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C:\\Users\\ONE\\Documents\\Agenda Telefonica\\src\\AddressBook\\imput.cvs";
        String a [];

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");
                m.put(a[0],a[1]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println("---> LOS CONTACTOS HAN SIDO CARGADOS <---");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }

    }


}