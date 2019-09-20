import java.sql.SQLException;

class ClienteNaoEncontrado extends Exception {

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
			throw new SQLException( "Erro no banco" );
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
			// neste caso, posso converter wrap de uma exceção em outra, sem perder a original
			throw new SQLException( "Erro no banco" );
		} catch ( SQLException sql )
		{
			ClienteNaoEncontrado cne = new ClienteNaoEncontrado();
			throw new ClienteNaoEncontrado();
			// na conversão de uma exceção para outra,
			// eu perco a referência para exceção real
			// de origem. Isso pode dificultar debug

			// posso ter o melhor dos dois mundos, com chaining
			// cne.initCause( sql ); // chaining: vai pro final da fila
			// throw cne; // assim consigo fazer wrap e ainda guardar SQLException como a exceção real de origem
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
		}

		/*
		 * saida sera apenas: ClienteNaoEncontrado at Chaining.buscarCliente(Chaining.java:18) at
		 * Chaining.main(Chaining.java:30)
		 */

		/*
		 * se eu usar metodo com chaining: ClienteNaoEncontrado at
		 * Chaining.buscarClienteUsandoChaining(Chaining.java:33) at Chaining.main(Chaining.java:47)
		 * Caused by: java.sql.SQLException: Erro no banco at
		 * Chaining.buscarClienteUsandoChaining(Chaining.java:30) ... 1 more
		 */
	}
}
