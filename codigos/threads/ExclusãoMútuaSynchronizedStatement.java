import java.util.Locale;

class Contador extends Thread {

    static int contador = 0; // o recurso compartilhado

    public void run()
    {
        for ( int i = 0; i < 1_000_000; i++ )
            synchronized ( Contador.class ){ // informa quem irá fornecer o lock
                contador++;
            }
    }
}

public class ExclusãoMútuaSynchronizedStatement {
    public static void main( String[] args ) throws InterruptedException
    {
        Thread t1 = new Contador();
        Thread t2 = new Contador();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.printf( new Locale( "pt", "BR" ),
                           "O resultado da contagem é %,d", Contador.contador );
    }
}