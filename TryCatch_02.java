import java.util.Arrays;

public class TryCatch_02 {
    public static void main( String[] args )
    {
        if ( args.length < 1 )
        {
            System.err.println( "Sintaxe: java Problema_01 <N1> <N2> ... <N>" );
            return;
        }

        double média = 0;
        try
        {
            for ( String s: args )  média += Double.parseDouble( s );

            System.out.printf( "Entradas: %s", Arrays.toString( args ) );
            System.out.printf( "%nA média: %.2f%n", média );
        } catch ( NumberFormatException nfe )
        {
            System.err.println( "Erro! Argumento(s) de entrada inválido(s)." );
        }
        // NumberFormatException é descendente de RuntimeException
        // e esta exceção não foi checada pelo compilador
    }
}
