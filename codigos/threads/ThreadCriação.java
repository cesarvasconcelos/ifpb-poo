class MyThread extends Thread {
    @Override
    public void run()
    {
        for ( int i = 0; i < 3; i++ )
            System.out.println( Thread.currentThread().getName() + " i= " + i );
    }
}

class MyThreadRunnable implements Runnable {
    @Override
    public void run()
    {
        for ( int i = 0; i < 3; i++ )
            System.out.println( Thread.currentThread()
                                      .getName() + " i= " + i );
    }
}

public class ThreadCriação {
    public static void main( String[] args )
    {
        MyThread myThread = new MyThread();
        Thread myThreadRunnable = new Thread( new MyThreadRunnable(), "MyThreadRunnable" );

        Runnable r1 = new Runnable() {
            @Override
            public void run()
            {
                for ( int i = 0; i < 3; i++ )
                    System.out.println( Thread.currentThread().getName() );
            }
        };

        Thread myThreadSemLambdas = new Thread( r1, "ThreadRunnable::Sem Lambdas" );

        Runnable r2 = () ->
        {
            for ( int i = 0; i < 3; i++ )
                System.out.println( Thread.currentThread().getName() );
        };

        Thread myThreadComLambdas = new Thread( r2, "ThreadRunnable::Com Lambdas" );

        myThread.start();
        myThreadRunnable.start();
        myThreadSemLambdas.start();
        myThreadComLambdas.start();

        System.out.println( "Main encerrando..." + Thread.currentThread().getName() );
    }
}
