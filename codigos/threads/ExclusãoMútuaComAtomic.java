import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

class Contador extends Thread {

    //static int  contador      = 0; // o recurso compartilhado
    //static Lock reentrantLock = new ReentrantLock(); // lock/mutex

    static AtomicInteger contador = new AtomicInteger( 0 );

    public void run()
    {
        for ( int i = 0; i < 1_000_000; i++ )
        {
            //reentrantLock.lock();
            contador.incrementAndGet(); // a operação de incremento é atômica
            //reentrantLock.unlock();
        }
    }
}

public class ExclusãoMútuaComAtomic {
    public static void main( String[] args ) throws InterruptedException
    {
        Thread t1 = new Contador();
        Thread t2 = new Contador();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.printf( new Locale( "pt", "BR" ),
                           "O resultado da contagem é %,d", Contador.contador.get() );
    }
}