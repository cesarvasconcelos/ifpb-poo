import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ModoBinário {

    static void copiarOrigemDestino( String origem, String destino )
    {
        // try-with-resources Java 7+
        try ( // ler
              FileInputStream fis = new FileInputStream( origem );
              // gravar
              FileOutputStream fos = new FileOutputStream( destino ); )
        {
            byte buffer[] = new byte[ 8 * 1024 ]; // 8kb
            int tamanho;
            while ( ( tamanho = fis.read( buffer ) ) != -1 )
                fos.write( buffer, 0, tamanho );

            System.out.println( "Sucesso na cópia!" );

        } catch ( FileNotFoundException e )
        {
            System.out.println( "Arquivo não foi encontrado!" );
            e.printStackTrace();
        } catch ( IOException e )
        {
            System.out.println( "Erro no fechamento ou no processamento dos arquivos!" );
            e.printStackTrace();
        }
    }

    static void copiarOrigemDestino_COM_BUFFER_AUTOMÁTICO( String origem, String destino )
    {
        // try-with-resources
        try (
              FileInputStream fis = new FileInputStream( origem );
              FileOutputStream fos = new FileOutputStream( destino );
              // note os buffers abaixo:
              BufferedInputStream bis = new BufferedInputStream( fis );
              BufferedOutputStream bos = new BufferedOutputStream( fos );

        )
        {
            // byte buffer[] = new byte[ 8 * 1024 ]; // 8kb
            int tamanho;
            while ( ( tamanho = bis.read() ) != -1 )
                bos.write( tamanho );

            System.out.println( "Sucesso na cópia (agora com buffer)!" );

        } catch ( FileNotFoundException e )
        {
            System.out.println( "Arquivo não foi encontrado!" );
            e.printStackTrace();
        } catch ( IOException e )
        {
            System.out.println( "Erro no fechamento ou no processamento dos arquivos!" );
            e.printStackTrace();
        }
    }

    static void copiarOrigemDestinoNIO( String origem, String destino )
    {
        Path pathOrigem = Paths.get( origem );
        Path pathDestino = Paths.get( destino );

        try
        {
            byte[] bytesLidos = Files.readAllBytes( pathOrigem );
            Files.write( pathDestino,
                         bytesLidos,
                         StandardOpenOption.CREATE,
                         StandardOpenOption.TRUNCATE_EXISTING,
                         StandardOpenOption.WRITE );
            System.out.println( "Sucesso na cópia!" );

        } catch ( IOException e )
        {
            System.out.println( "Erro no fechamento ou no processamento dos arquivos!" );
            e.printStackTrace();
        }

    }

    public static void main( String[] args )
    {
        ModoBinário.copiarOrigemDestino( "files/mini.iso", "files/cópia_mini.iso" );
        ModoBinário.copiarOrigemDestinoNIO( "files/mini.iso", "files/cópia_mini.iso" );
        ModoBinário.copiarOrigemDestino_COM_BUFFER_AUTOMÁTICO(
                "files/mini.iso", "files/cópia_mini.iso" );
    }

}
