import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

// commons-lang3-3.0.jar é a third library
// que será usada para uma verificação mínima no nome

// <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
// <dependency>
//   <groupId>org.apache.commons</groupId>
//   <artifactId>commons-lang3</artifactId>
//   <version>3.0</version>
// </dependency>

public class ValidarDadosCommonsLang_04 {

    public static String lerEntrada( String frase, Scanner scanner )
    {
        System.out.print( frase );
        return scanner.nextLine();
    }

    private static void imprimirDados( String nome, byte idade, double salario )
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance( new Locale( "pt", "BR" ) );
        System.out.printf( "Nome= %s Idade= %d Salário= %s", nome, idade, nf.format( salario ) );
    }

    public static void main( String[] args )
    {
        String nome = null;
        byte idade = 0;
        double salario = 0.0;
        boolean ok = false;
        Scanner scanner = new Scanner( System.in );

        do
        {
            try
            {
                nome = lerEntrada( "Entre com o nome: ", scanner );

                if ( !StringUtils.isAlphaSpace( nome ) ) throw new NomeInválidoException();

                idade = Byte.parseByte( lerEntrada( "Entre com a idade: ", scanner ) );

                salario = Double.parseDouble( lerEntrada( "Entre com o salário: ", scanner ) );

                scanner.close(); // encerrando o Scanner
                ok = true; // se chegou até aqui, não houve exceção
            } catch ( NumberFormatException nfe )
            {
                System.out.println( "\t>> Você não está digitando dados numéricos corretamente. Vamos começar novamente." );
            } catch ( NomeInválidoException nie )
            {
                System.out.printf( "\t>> O nome \'%s\' digitado %s\n", nome, "é inválido. Vamos começar novamente." );
            }
        } while ( !ok );

        imprimirDados( nome, idade, salario );
    }

}

// note como é simples criar sua própria classe de exceção
class NomeInválidoException extends Exception {
    public NomeInválidoException()
    {
        this( "O nome é inválido" );
    }

    public NomeInválidoException( String message )
    {
        super( message );
    }
}