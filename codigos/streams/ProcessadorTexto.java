import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ProcessadorTexto {

    // antes do Java 7, modo texto!
    static void listarArquivoTextoIO( String origem, String destino )
    {
        try ( FileReader fr = new FileReader( origem );
              BufferedReader br = new BufferedReader( fr );
              FileWriter fw = new FileWriter( destino );
              BufferedWriter bw = new BufferedWriter( fw ); )
        {
            String linha = null;

            while ( ( linha = br.readLine() ) != null )
            {
                String palavras[] = linha.split( " " ); // se quiser pegar palavras isoladamente
                for ( String palavra : palavras )
                    System.out.println( palavra );
                // e fazer algo com elas
                bw.write( linha.replaceAll( "de", "DE" ) );
                bw.newLine();
            }

        } catch ( FileNotFoundException e )
        {
            System.out.println( "Erro: arquivo não existe." );
        } catch ( IOException e )
        {
            System.out.println( "Erro na varredura do arquivo." );
        }
    }
    // depois do Java 7, modo texto!
    static void listarArquivoTextoNIO( String origem, String destino )
    {
        Path pathOrigem = Paths.get( origem );
        Path pathDestino = Paths.get( destino );

        System.out.println( pathOrigem.toAbsolutePath() );

        try
        {
            // Files.readAllLines( pathOrigem, StandardCharsets.UTF_8 );
            // menos portável que o comando abaixo...
            List<String> linhas = Files.readAllLines( pathOrigem, Charset.defaultCharset() );
            Files.write( pathDestino,
                         linhas,
                         Charset.defaultCharset(),
                         StandardOpenOption.CREATE, // criar o arquivo, caso não exista
                         StandardOpenOption.TRUNCATE_EXISTING,
                         StandardOpenOption.WRITE );
        } catch ( IOException e )
        {
            System.out.println( "Erro: na varredura do arquivo." );
        }
    }

    public static void main( String[] args )
    {
        ProcessadorTexto.listarArquivoTextoIO( "files/carta.txt", "files/nova_carta.txt" );
        //ProcessadorTexto.listarArquivoTextoNIO( "files/hamlet.xml", "files/copia_hamlet.xml" );
    }
}
