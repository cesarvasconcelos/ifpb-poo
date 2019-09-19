import java.util.Arrays;

//  java Problema_01 10 9 55.9 100.99
public class Problema_01 {
    public static void main( String[] args )
    {
        if ( args.length < 1 )
        {
            System.err.println( "Sintaxe: java Problema_01 <N1> <N2> ... <N>" );
            return;
        }

        double média = 0;
        for ( String entrada : args )  média += Double.parseDouble( entrada );

        System.out.printf( "Entradas: %s", Arrays.toString( args ) );
        System.out.printf( "%nA média: %.2f%n", média );
    }
}

// java Problema_01 10 9 55.9 pedro 100.99
// Exception in thread "main" java.lang.NumberFormatException