// Trylock.java : exemplo de uso do try lock
// a metáfora é a seguinte: três threads (um casal+sogra)
// adicionando dinheiro no cofre até conseguir juntar no mínimo R$50.
// tryLock() permite que uma thread possa fazer outra tarefa útil
// quando ela não consegue adquirir o lock para adicionar R$ no cofre

import java.time.Duration;
import java.time.Instant;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadPessoa extends Thread {
    private        double quantiaADepositar = 0D; // quantia que a pessoa está esperando para depositar
    private static double cofrinho          = 0D; // o cofrinho que representa o recurso compartilhando
    private static Lock   lock              = new ReentrantLock(); // o mutex

    private static Locale localeBR = new Locale( "pt", "BR" );

    public ThreadPessoa( String nome )
    {
        this.setName( nome );
    }

    @Override
    public void run() // sem try-lock: uma thread ficará bloqueada até o cofre estar acessível
    {
        while ( cofrinho <= 50D )
        {
            if ( quantiaADepositar > 0 )
            {
                // região crítica:
                try
                {
                    lock.lock();
                    cofrinho += quantiaADepositar;
                    System.out.format( localeBR, "%s adicionou %,.2f reais no cofrinho.%n",
                                       this.getName(), quantiaADepositar );
                    System.out.format( localeBR, "Há R$ %,.2f no cofrinho.%n", cofrinho );
                    quantiaADepositar = 0;
                    Thread.sleep( 300 ); // tempo gasto para adicionar ao cofre
                } catch ( InterruptedException e )
                {
                    e.printStackTrace();
                } finally
                {
                    lock.unlock();
                }
            } else // junte $ para adicionar ao cofre
            {
                try
                {
                    Thread.sleep( 100 ); // tempo gasto para juntar dinheiro...
                    quantiaADepositar++;
                    System.out.format( "%s juntou dinheiro.%n", this.getName() );
                } catch ( InterruptedException e )
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //@Override
    //public void run() // com try-lock: pode-se "ficar livre" para executar uma tarefa útil se cofre está inacessível
    //{
    //    while ( cofrinho <= 50D )
    //    {
    //        if ( ( quantiaADepositar > 0 ) && lock.tryLock() )
    //        {
    //            // região crítica:
    //            try
    //            {
    //                cofrinho += quantiaADepositar;
    //                System.out.format( localeBR, "%s adicionou %,.2f reais no cofrinho.%n",
    //                                   this.getName(), quantiaADepositar );
    //                System.out.format( localeBR, "Há R$ %,.2f no cofrinho.%n", cofrinho );
    //                quantiaADepositar = 0;
    //                Thread.sleep( 300 ); // tempo gasto para abrir e adicionar ao cofre
    //            } catch ( InterruptedException e )
    //            {
    //                e.printStackTrace();
    //            } finally
    //            {
    //                lock.unlock();
    //            }
    //        } else // junte $ para adicionar ao cofre
    //        {
    //            // afinal, nada impede da thread ir executando a tarefa de
    //            // juntar mais dinheiro enquanto não pode acessar o cofre.
    //            try
    //            {
    //                Thread.sleep( 100 ); // tempo gasto para juntar dinheiro...
    //                quantiaADepositar++;
    //                System.out.format( "%s juntou dinheiro.%n", this.getName() );
    //            } catch ( InterruptedException e )
    //            {
    //                e.printStackTrace();
    //            }
    //        }
    //    }
    //}
}

public class TryLock {
    public static void main( String[] args ) throws InterruptedException
    {
        Thread césar = new ThreadPessoa( "César" );
        Thread dani = new ThreadPessoa( "Dani" );
        Thread sogra = new ThreadPessoa( "Sogra" );

        Instant início = Instant.now();
        césar.start();
        dani.start();
        sogra.start();

        césar.join();
        dani.join();
        sogra.join();
        Instant fim = Instant.now();

        Duration diff = Duration.between( início, fim );
        System.out.println( "Tempo gasto: " + diff.getSeconds() + " segundos." );
    }
}
