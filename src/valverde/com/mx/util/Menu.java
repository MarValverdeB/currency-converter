package valverde.com.mx.util;

import valverde.com.mx.ConverterV2;
import java.util.Scanner;
import static valverde.com.mx.util.Colors.*;

public class Menu {

    boolean exit;

    public void printHeader(){
        System.out.println(ANSI_GREEN +"+--------------------------------------------------------------------------+"+ ANSI_RESET);
        System.out.println(ANSI_GREEN +"|-----------------            Bienvenido al            --------------------|"+ ANSI_RESET);
        System.out.println(ANSI_GREEN +"|-----------------         Conversor de Moneda         --------------------|"+ ANSI_RESET);
        System.out.println(ANSI_GREEN +"+--------------------------------------------------------------------------+"+ ANSI_RESET);
    }

    public void printMenu(){
        System.out.println(ANSI_BLUE +"\nSelecciona una opción: "+ ANSI_RESET);
        System.out.println(ANSI_BLUE +"1) Conversión por Default (USD)"+ ANSI_RESET);
        System.out.println(ANSI_BLUE +"2) Convertir moneda"+ ANSI_RESET);
        System.out.println(ANSI_BLUE +"3) Convertir de una moneda a otra."+ ANSI_RESET);
        System.out.println(ANSI_BLUE +"4) Convertir una cantidad de una moneda a otra."+ ANSI_RESET);
        System.out.println(ANSI_BLUE +"0) Salir"+ ANSI_RESET);
    }

    public void runMenu(){
        printHeader();
        while(!exit){
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }

    private int getInput(){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while(choice < 0 || choice > 4){
            try{
                System.out.println(ANSI_RED +"\nSelecciona la opción deseada entre 0 y 4: "+ ANSI_RESET);
                choice = Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                System.out.println(ANSI_PURPLE+"Opción incorrecta. Intenta de nuevo."+ ANSI_RESET);
            }
        }
        return choice;
    }

    private void performAction(int choice) {
        ConverterV2 converter = new ConverterV2();
        Scanner scanner = new Scanner(System.in);
        switch(choice){
            case 0:
                exit = true;
                System.out.println("\n"+ANSI_YELLOW +"Gracias por usar la aplicación"+ ANSI_RESET);
                break;
                case 1:
                    converter.getConversionRates();
                    break;
            case 2:
                System.out.println("\n"+ANSI_CYAN+"Ingrese el código de moneda a convertir: "+ ANSI_RESET);
                String moneda = scanner.nextLine();
                converter.getConversionRates(moneda);
                break;
                case 3:
                    System.out.println("\n"+ANSI_CYAN+"Ingresa el código de moneda origen y el código de moneda destino. Separado por un espacio: Ejemplo: CAD MXN "+ ANSI_RESET);
                    String[] monedas = scanner.nextLine().split(" ");
                    if(monedas.length != 2){
                        System.out.println("\n"+ANSI_PURPLE+"Ingresa 2 valores en formato moneda"+ ANSI_RESET);
                        break;
                    }
                    converter.getConversionRates(monedas[0], monedas[1]);
                    break;
            case 4:
                System.out.println("\n"+ANSI_CYAN+"Ingresa el código de moneda origen, el código de moneda destino y la cantidad. Separado por un espacio. Ejemplo: CAD MXN 100.0: "+ ANSI_RESET);
                String[] amount = scanner.nextLine().split(" ");
                if(amount.length != 3){
                    System.out.println("\n"+ANSI_PURPLE+"Ingresa 3 valores en formato moneda y cantidad"+ ANSI_RESET);
                    break;
                }
                converter.getConversionRates(amount[0], amount[1], amount[2]);
                break;
            default:
                System.out.println("\n"+ANSI_PURPLE+"Error desconocido"+ ANSI_RESET);
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
    }

}
