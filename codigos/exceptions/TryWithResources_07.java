// file TryWithResources_07.java
// Desde o Java 7, pode-se usar try-with-resources para
// "fechar" um recurso alocado no sistema, automaticamente;
// a ideia é substituir o pattern:
//      open a resource
//      try
//      {
//          do something with the resource
//      } finally
//      {
//          close the resource
//      }
// por este pattern:
//      try (Resource res = ...)
//      {
//          do something with the resource
//      }
// A condição é que o recurso implemente a interface AutoCloseable

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class TryWithResources_07 {
    public static void main( String[] args )
    {
        // Antes do Java 7: sem try-resources (old pattern)
        try
        {
            var scanner0 = new Scanner( Path.of( "files/arquivo.txt" ), StandardCharsets.UTF_8  ) ;
            try
            {
                String line = scanner0.nextLine(); // tentar fazer algo com scanner
            } finally
            {
                scanner0.close(); // tenta fechar scanner; como close pode gerar exceção,
                                  // então melhor reportar o erro
            }
        }
        catch ( IOException ioex )
        {
            // faça algo com a exceção
        }

        // Antes do Java 7: sem try-resources (outro exemplo)
        // note com o pattern era confuso
        Scanner scanner1 = null;
        try
        {
            scanner1 = new Scanner( Path.of( "files/arquivo.txt" ), StandardCharsets.UTF_8  ) ;
            String line = scanner1.nextLine(); // tentar fazer algo com scanner
        }
        catch ( IOException ioex )
        {
            // faça algo com a exceção
        }
        finally
        {
            if ( scanner1 != null ){
                try{
                    scanner1.close();
                }catch ( Exception e ){
                    // faça algo com a exceção
                }
            }
        }

        // Try-with-resources pattern :)
        try ( var scanner = new Scanner( System.in ) ) {
            System.out.print( "Entre com seu nome completo: " );
            String nome = scanner.nextLine();
            System.out.println( "Nome digitado: " + (nome.length() == 0 ? "(nada foi digitado)" : nome ) );
        }
        // quando o try-block terminar, o scanner.close() será chamado automaticamente

        var scanner = new Scanner( "a e i o u" );
        método( scanner );
    }

    // a partir do Java 9, pode-se passar variáveis finais ou efetivamente finais ao try-with-resources
    public static void método( Scanner umScanner )
    {
        try ( umScanner ) { // effectively final scanner
            umScanner.tokens()
                     .forEach( System.out::println );
        } // umScanner.close() será chamado automaticamente aqui :)
    }
}