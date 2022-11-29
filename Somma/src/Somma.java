import java.util.Scanner;
public class Somma {
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Primo numero : ");
		int n1 = input.nextInt();
		System.out.println("Secondo numero : ");
		int n2 = input.nextInt();
		int somma = n1 + n2;
		System.out.println("La somma e\' di : "+somma);
		input.close();		
	}
}
