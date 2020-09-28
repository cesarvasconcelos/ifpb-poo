import java.sql.SQLException;

class ClienteNaoEncontrado extends Exception {
	public ClienteNaoEncontrado( String mensagem )
	{
		super( mensagem );
	}
}

/*
 * Veja um trecho do que diz o Bruce Eckel do Thinking in Java:
 * Throwing a low-level exception from a higher-level class isn't a good idea because it exposes
 * details of that class's implementation. The correct solution is for the class to catch the
 * low-level exceptions, and to rethrow exceptions of a higher level of abstraction. If the
 * getEmployee() method retrieves employee objects from a database via JDBC, for instance, a
 * SQLException might be caught inside the method. This exception would be converted to an
 * EmployeeLookupException and rethrown to the calling method
 */

public class Chaining_06 {

	public void buscarCliente() throws ClienteNaoEncontrado
	{
		try
		{
			// suponha que bd.executeQuery gerou SQlException. Esta é uma exceção de baixo
			// nível e que poderia relevar informações de que a abstração usa bd em vez de arquivos.
			throw new SQLException( "Erro no banco!" );
		} catch ( SQLException e )
		{
			e.printStackTrace( System.out ); // low-level exception
		}

	}

	public void buscarClienteUsandoChaining() throws ClienteNaoEncontrado
	{
		try
		{
			// para não expor a low-level exception, tenho duas opções:
			// neste caso, posso converter uma exceção em outra, perdendo a original
			// ou posso fazer "wrapping/chaining" de uma exceção em outra, sem perder a original
			throw new SQLException( "Erro no banco!" );
		} catch ( SQLException sql )
		{
			// throw new ClienteNaoEncontrado( "Cliente não encontrado!" ); // acabei de perder a SQlException :(
			// na conversão de uma exceção (baixo nível) para outra (alto nível, neste caso),
			// eu perco a referência para exceção real
			// de origem. Isso pode dificultar o debug

			// ou posso ter o melhor dos dois mundos, com chaining
			var cne = new ClienteNaoEncontrado( "Cliente não encontrado!" ); // Java 10
			cne.initCause( sql ); // chaining: vai pro final da fila
			throw cne; // assim consigo fazer wrap e ainda guardar SQLException como a exceção real de origem
			// isso permite gerar exceções de alto nível em subsistemas sem perder detalhes da
			// exceção de baixo nível
		}

	}

	public static void main( String[] args )
	{

		try
		{
			Chaining_06 c = new Chaining_06();
			// c.buscarCliente();
			c.buscarClienteUsandoChaining();
		} catch ( ClienteNaoEncontrado cne )
		{
			cne.printStackTrace( System.out );
			Throwable original = cne.getCause(); // SQLException (a causa original de baixo nível)
			System.out.println( original.getMessage() ); // Erro no banco!
		}

		/*
		 * (sem chaining) saida sera apenas:
		 * java.sql.SQLException: Erro no banco!
		 * 		at Chaining_06.buscarCliente(Chaining_06.java:28)
		 * 		at Chaining_06.main(Chaining_06.java:67)
		 */

		/*
		 * (com chaining): saida terá a exceção de baixo nível (do subsistema)
		 *	ClienteNaoEncontrado: Cliente não encontrado!
		 *		at Chaining_06.buscarClienteUsandoChaining(Chaining_06.java:52)
		 *		at Chaining_06.main(Chaining_06.java:68)
		 *	Caused by: java.sql.SQLException: Erro no banco!
		 *		at Chaining_06.buscarClienteUsandoChaining(Chaining_06.java:43)
		 */
	}
}
