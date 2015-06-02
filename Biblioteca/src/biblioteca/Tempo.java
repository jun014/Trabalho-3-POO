package biblioteca;

import java.util.Scanner;

public class Tempo {
	public static String ano;
	public static String mes;
	public static String dia;
	public static boolean modificado = false;
	
	public void setInfo() {
		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite o ano: (ex: 2014)");
		ano = ler.nextLine();
		System.out.println("Digite o mes: (ex: 05)");
		mes = ler.nextLine();
		System.out.println("Digite o dia: (ex: 04)");
		dia = ler.nextLine();
		modificado = true;
		}

}
