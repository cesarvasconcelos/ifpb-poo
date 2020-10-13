import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

// file: ScannerPrintWriter_0.java
// uso de Scanner e PrintWriter para escrita/leitura
// de arquivos texto de exemplo abaixo:

// produtos.txt
// 100;café;2.99
// 200;balde;5.99
// 300;vassoura;3.99
// 400;livro;8.99

public class ScannerPrintWriter_0 {

    public static void main( String[] args )
    {
        Scanner s = null;
        PrintWriter pw = null;
        try
        {
            // importante:
            // a) e arquivo tiver backslashes, use "c:\\temp\\produtos.txt"
            // b) se passar apenas new Scanner("produtos.txt"),
            //    scanner irá tratar String como dados e lerá caracteres "p","r","o"...
            // c) senão passar encoding, Java usará o encode default da máquina
            // d) se passar path relativo, o diretório será relativo a um diretório de partida
            //    que pode ser obtido via System.getProperty("user.dir");
            //    por padrão, o diretório de partida é sempre o diretório onde a JVM foi iniciada

            System.out.println("Diretório de partida: " + System.getProperty( "user.dir" ) );
            // Ex: /Users/cesar/IdeaProjects/input-output

            s = new Scanner( Path.of("files/produtos.txt"), StandardCharsets.UTF_8 );
            s.useDelimiter( "[;\n]" ); // ignore ";" e também "\n"

            // se arquivo não existir, será criado automaticamente
            pw = new PrintWriter( "files/cópia_produtos.txt" );

            int código = 0;
            double preço = 0D;
            String descrição = "";

            while ( s.hasNext() )
            {
                // lendo com Scanner
                if ( s.hasNextInt() )
                    código = s.nextInt();
                if ( s.hasNext() )
                    descrição = s.next();
                if ( s.hasNextDouble() )
                    preço = s.nextDouble();

                // gravando com PrintWriter
                pw.printf( "código:%3d - descrição:%-10s - preço(R$):%.2f%n", código, descrição, preço );
            }
        } catch ( IOException e )
        {
            e.printStackTrace();
        }finally
        {
            // aqui, quero testar as duas condições (i.e., não quero o short-circuit)
            // neste caso, tenho de usar & e vez do tradicional &&
            if ( s != null & pw != null )
            {
                pw.flush();
                pw.close();
                s.close();
            }
        }
    }
}
