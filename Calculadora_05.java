import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculadora_05 {

	public double somar( double um, double outro )
	{
		return um + outro;
	}

	public double dividir( double numerador, double denominador ) throws DivisãoPorZeroException
	{
		if ( denominador == 0 )
		{
			throw new DivisãoPorZeroException( "Divisão por zero? Não pode!" );
		}
		return numerador / denominador;
	}

	public static void main( String[] args )
	{
		Calculadora_05 c = new Calculadora_05();
		Scanner s = new Scanner( System.in );

		// try-with-resources... Java SE 7
		// try (Scanner s = new Scanner( System.in );)
		try
		{
			// obter os dados
			System.out.print( "Digite um número: " );
			double numerador = s.nextDouble();

			System.out.print( "Digite outro número: " );
			double denominador = s.nextDouble();

			// calcular...
			double resultado = c.dividir( numerador, denominador );

			// exibir os resultados
			System.out.printf( "O resultado da operação é: %.2f\n", resultado );

			// sair do sistema
			System.out.println( "Aguarde...encerrando o sistema. Pronto. Até logo" );
			System.exit( 0 );
		} catch ( InputMismatchException ime )
		{
			System.out.println( "Entrada de dados inválida." );
			System.out.println( "Aguarde...encerrando o sistema. Pronto. Até logo" );
			System.exit( 0 );
		} catch ( DivisãoPorZeroException dze )
		{
			System.out.println( dze.getMessage() );
			System.out.println( "Divisão por zero? Não pode!" );
			System.out.println( "Aguarde...encerrando o sistema. Pronto. Até logo" );
			System.exit( 0 );
		} catch ( Exception e )
		{
			System.out.println( "Erro desconhecido. Contacte o administrador." );
			System.out.println( "Aguarde...encerrando o sistema. Pronto. Até logo" );
			System.exit( 0 );
		}
		// finally
		// {
		// 	System.out.println( "Encerrando o sistema via finally!" );
		// 	s.close();
		// 	System.exit( 0 );
		// }
	}
}

class DivisãoPorZeroException extends Exception {
	public DivisãoPorZeroException( String umamensagem ) {
		super( umamensagem );
	}

	public DivisãoPorZeroException() {
		super( "Divisão por zero." );
	}
}
