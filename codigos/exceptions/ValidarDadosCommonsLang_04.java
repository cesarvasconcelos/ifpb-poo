import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
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

    private static void imprimirDados( String nome, byte idade, double salário )
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance( new Locale( "pt", "BR" ) );
        System.out.printf( "Nome= %s Idade= %d Salário= %s", nome, idade, nf.format( salário ) );
    }

    public static void main( String[] args )
    {
        String nome = null;
        byte idade = 0;
        BigDecimal salário = BigDecimal.ZERO;
        boolean capturaDosDadosOK = false;
        Scanner scanner = new Scanner( System.in );

        do
        {
            try
            {
                nome = lerEntrada( "Entre com o nome: ", scanner );

                if ( !StringUtils.isAlphaSpace( nome ) ) throw new NomeInválidoException();

                idade = Byte.parseByte( lerEntrada( "Entre com a idade: ", scanner ) );

                salário = new BigDecimal( lerEntrada( "Entre com o salário: ", scanner ) ).setScale( 2 );

                capturaDosDadosOK = true; // se chegou até aqui, não houve exceção

                try{
                    // este try interno serve apenas para fechar o scanner;
                    // coloquei aqui para fins didáticos pois
                    // erros no close() serão reportados no último bloco catch (Exception e)
                    // vamos refatorar para try-with-resources, para eliminar o close() e o catch!
                }finally
                {
                    scanner.close(); // encerrando o Scanner (tudo isso seria evitado com try-with-resources)
                }
            } catch ( NumberFormatException nfe )
            {
                System.out.println( "\t>> Você não está digitando dados numéricos corretamente. Vamos começar novamente." );
            } catch ( NomeInválidoException nie )
            {
                System.out.printf( "\t>> O nome \'%s\' digitado %s\n", nome, "é inválido. Vamos começar novamente." );
            }catch ( Exception e ){
                System.out.printf( "\t>> Erro desconhecido: " + e.getMessage() );
                e.printStackTrace();
            }

        } while ( !capturaDosDadosOK );

        imprimirDados( nome, idade, salário.doubleValue() );
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