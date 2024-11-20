import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Random aleatorio = new Random();

        String eleccion;

        System.out.println("Introduce 6 números y el reintegro con el siguiente formato N-N-N-N-N-N/R: ");
        eleccion = entrada.nextLine();

        String regex = "^(\\d|[1-4]\\d)(-(\\d|[1-4]\\d)){5}/\\d$"; //expresió per a validar el format. D'aquesta
                                                                   //forma ja esta delimitat que ha de ser de 1-49 i
        boolean correcto = eleccion.matches(regex);                // la ultima ha de ser nomes d'una xifra
        //comprova que el format siga el correcte
        if (correcto){
            int[] boleto = new int[7]; // per a almacenar els 6 numeros i el reintregro
            int complementario=0;
            int reintegro;
            String[] split = eleccion.split("[-/]"); // per a llevar els signes - i /

            for (int i = 0; i < boleto.length; i++) {
                boolean repetido = false;
                boleto[i] = Integer.parseInt(split[i]);  // per a passar-ho a número
            }
            Arrays.sort(boleto,0,6);
            System.out.println("Elección ordenada del usuario: " + Arrays.toString(boleto));
            boolean repetido = false;
            for (int i = 0; i < boleto.length; i++) {  // bucle per a trobar numeros repetits

                for (int j = 0; j < boleto.length; j++) {

                    if (boleto[i] == boleto[j] && i != j) {
                        repetido = true;
                        break;
                    }
                }
                if (repetido) {
                    System.out.println("Número repetido: " + boleto[i]);
                    break;
                }
            }

            if (!repetido){

                int[] sorteo = new int[6]; // per a almacenar els numeros del sorteig

                boolean repetirNumeroSorteo = false;

                for (int i = 0; i <= sorteo.length; i++) {  // per a generar els numeros del sorteig

                    int numero = aleatorio.nextInt(49 ) + 1; //genera números del 1-49

                    for (int j = 0; j < sorteo.length; j++) {
                        if (numero==sorteo[j]){
                            repetirNumeroSorteo= true;
                            break;
                        }
                    }

                    if (repetirNumeroSorteo){
                        i--;  // repeteix la iteració en cas de que el número estiga repetit
                        repetirNumeroSorteo=false;
                        continue;
                    }
                        if (i == sorteo.length){
                            complementario=numero;

                        }else {
                            sorteo[i] = numero;
                        }

                }
                Arrays.sort(sorteo);  // ordena els numeros
                System.out.println("Sorteo: " + Arrays.toString(sorteo));

                System.out.println("Complementario: "+ complementario);

                reintegro = aleatorio.nextInt(10); //genera un numero del 0-9
                System.out.println("Reintegro: "+ reintegro);
                int aciertos = 0;
                for (int i = 0; i < 6; i++) {  // per a comprovar quantes coincidencies tenim
                    for (int j = 0; j < 6; j++) {
                        if (boleto[i] == sorteo[j]) {
                            aciertos++;
                        }
                    }
                }

                boolean aciertoComplementario = false; //per a verificar l'acert del complementari
                for (int i = 0; i < 6; i++) {
                    if (boleto[i] == complementario) {
                        aciertoComplementario = true;
                        break;
                    }
                }

                boolean aciertoReintegro = boleto[6] == reintegro; // per a verificar l'acer del reintegro

                if (aciertos == 6 && aciertoReintegro) { //evalua les categories i els premis que han tocat
                    System.out.println("Categoría Especial: 6 aciertos y reintegro.");
                } else if (aciertos == 6) {
                    System.out.println("1ª Categoría: 6 aciertos.");
                } else if (aciertos == 5 && aciertoComplementario) {
                    System.out.println("2ª Categoría: 5 aciertos y complementario.");
                } else if (aciertos == 5) {
                    System.out.println("3ª Categoría: 5 aciertos.");
                } else if (aciertos == 4) {
                    System.out.println("4ª Categoría: 4 aciertos.");
                } else if (aciertos == 3) {
                    System.out.println("5ª Categoría: 3 aciertos.");
                } else if (aciertoReintegro) {
                    System.out.println("Reintegro: Acertaste el reintegro.");
                } else {
                    System.out.println("No premiado.");
                }
            }
        }else{
            System.out.println("Número incorrecto.");
        }
        entrada.close();
        }
    }
