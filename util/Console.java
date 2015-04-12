package util;

import java.util.Scanner;

/**
 * Created by marcus.rodrigues on 11/04/2015.
 */
public class Console {
    /**
     * Metodo para entrada de dados do tipo String
     *
     * @param input Parametro do tipo Object com a mensagem informando tipo de dado de entrada.
     * @return ler.nextLine() retorna a chamada do metodo de entrada da classe Scanner
     */
    public static String lerString(Object input){

        System.out.print(input);
        Scanner ler = new Scanner(System.in);
        return(ler.nextLine());
    }

    /**
     * Metodo para entrada de dados do tipo Int
     *
     * @param input Parametro do tipo Object com a mensagem informando tipo de dado de entrada.
     * @return ler.nextInt() retorna a chamada do metodo de entrada da classe Scanner
     */
    public static int lerInt(Object input){

        System.out.print(input);
        Scanner ler = new Scanner(System.in);
        return (ler.nextInt());
    }

    /**
     * Metodo para entrada de dados do tipo Double
     *
     * @param input Parametro do tipo Object com a mensagem informando tipo de dado de entrada.
     * @return ler.nextDouble() retorna a chamada do metodo de entrada da classe Scanner
     */
    public static Double lerDouble(Object input){

        System.out.print(input);
        Scanner ler = new Scanner(System.in);
        return (ler.nextDouble());
    }

    /**
     * Metodo para entrada de dados do tipo Boolean
     *
     * @param input Parametro do tipo Object com a mensagem informando tipo de dado de entrada.
     * @return ler.nextBoolean() retorna a chamada do metodo de entrada da classe Scanner
     */
    public static Boolean lerBoolean(Object input){

        System.out.print(input);
        Scanner ler = new Scanner(System.in);
        return (ler.nextBoolean());
    }
    /**
     * Metodo para entrada de dados do tipo Float
     *
     * @param input Parametro do tipo Object com a mensagem informando tipo de dado de entrada.
     * @return ler.nextFloat() retorna a chamada do metodo de entrada da classe Scanner
     */
    public static float lerFloat(Object input){

        System.out.print(input);
        Scanner ler = new Scanner(System.in);
        return (ler.nextFloat());
    }

    /**
     * Metodo para entrada de dados do tipo Char
     *
     * @param input Parametro do tipo Object com a mensagem informando tipo de dado de entrada.
     * @return ler.next() retorna a chamada do metodo de entrada da classe Scanner
     */
    public static char lerChar(Object input) {

        System.out.print(input);
        Scanner ler = new Scanner(System.in);
        return (ler.next().charAt(0));
    }
}
