import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private static int[] recursoCompartilhado = { 10, 20, 30, 40, 50, };
    private static int   posição              = 0;

    private static ReentrantLock          tradicionalLock = new ReentrantLock(); // o tradicional
    //private static ReentrantReadWriteLock rwLock          = new ReentrantReadWriteLock(); // o read-write-rwLock
    //private static Lock                   readLock        = rwLock.readLock();
    //private static Lock                   writeLock       = rwLock.writeLock();

    private static Runnable runnableWriter =
            () -> {
                while( posição < recursoCompartilhado.length )
                {
                    tradicionalLock.lock();
                    recursoCompartilhado[ posição ] = 10 * recursoCompartilhado[ posição ];
                    posição++;
                    System.out.println( Thread.currentThread().getName() + " modificou o recurso: " +
                                        Arrays.toString( recursoCompartilhado ) );
                    tradicionalLock.unlock();

                    //writeLock.lock();
                    //recursoCompartilhado[ posição ] = 10 * recursoCompartilhado[ posição ];
                    //posição++;
                    //System.out.println( Thread.currentThread().getName() + " modificou o recurso: " +
                    //                    Arrays.toString( recursoCompartilhado ) +
                    //                    "; # de writers: " + rwLock.getWriteHoldCount() );
                    //writeLock.unlock();
                }
            };

    private static Runnable runnableReader =
            () -> {
                while( posição < recursoCompartilhado.length )
                {
                    tradicionalLock.lock();
                    System.out.println( Thread.currentThread().getName() + " leu o recurso: " +
                                        Arrays.toString( recursoCompartilhado ) );
                    tradicionalLock.unlock();
                    //readLock.lock();
                    //System.out.println( Thread.currentThread().getName() + " leu o recurso: " +
                    //                    Arrays.toString( recursoCompartilhado ) +
                    //                    "; # de readers: " + rwLock.getReadLockCount() );
                    //readLock.unlock();
                }
            };

    public static void main( String[] args )
    {
        new Thread( runnableWriter, "Thread Writer" ).start();
        for ( int i = 0; i < 10; i++ )
            new Thread( runnableReader ).start();
    }
}
