
// A Thread que irá executar por 5 segundos
class MyThread extends Thread {

    public void run()
    {
        for ( int i = 1; i <= 5; i++ )
            try
            {
                System.out.println( "Thread (" + this.getName() + ") i = " + i );
                Thread.sleep( 1000 );
            } catch ( InterruptedException e )
            {
                e.printStackTrace();
            }
    }
}

// ThreadProcesso.java: programa que demonstra o uso de processos e threads.
public class ThreadProcesso {
    public static void main( String args[] )
    {
        // informação sobre o processo corrente
        Runtime rt = Runtime.getRuntime();
        long memKB = ( rt.totalMemory() - rt.freeMemory() ) / 1024;
        System.out.printf( "Processo ID: %d\n", ProcessHandle.current().pid() );
        System.out.printf( "# Threads: %d\n", Thread.activeCount() );
        System.out.printf( "Consumo de Memória: %d KB\n", memKB );
        System.out.printf( "# CPUs: %d\n", rt.availableProcessors() );

        System.out.println( "\nIniciando 4 threads...\n" );
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        memKB = ( rt.totalMemory() - rt.freeMemory() ) / 1024;
        System.out.printf( "Processo ID: %d\n", ProcessHandle.current().pid() );
        System.out.printf( "# de Threads: %d\n", Thread.activeCount() );
        System.out.printf( "Consumo de Memória: %d KB\n", memKB );
        System.out.printf( "# CPUs: %d\n", rt.availableProcessors() );
        System.out.println("Fim do main()!");
    }
}