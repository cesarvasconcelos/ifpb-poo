import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Filósofo extends Thread{
    private Lock       garfo1;
    private Lock       garfo2;
    private static int contadorMacarrão = 5; // sem deadlock aparente...
    //private static int contadorMacarrão = 10_000; // deadlock :(

    Filósofo( String nome, Lock garfo1, Lock garfo2 ){
        this.garfo1 = garfo1;
        this.garfo2 = garfo2;
        this.setName( nome );
    }

    @Override
    public void run()
    {
        while ( ( contadorMacarrão > 0 ) ) // comer enquanto houver macarrão disponível
        {
            // pegar os dois garfos
            garfo1.lock();
            garfo2.lock();

            if ( contadorMacarrão > 0 ){
                contadorMacarrão--;
                System.out.println( this.getName() + " comeu um macarrão! Quantidade restante: " + contadorMacarrão );
            }

            // soltar os dois garfos
            garfo1.unlock();
            garfo2.unlock();
        }
    }
}


public class Deadlock {
    public static void main( String[] args )
    {
        Lock garfoA = new ReentrantLock();
        Lock garfoB = new ReentrantLock();
        Lock garfoC = new ReentrantLock();

        Thread f1 = new Filósofo( "Sócrates", garfoA, garfoB  );
        Thread f2 = new Filósofo( "Platão", garfoB, garfoC  );
        Thread f3 = new Filósofo( "Aristóteles", garfoC, garfoA  );
        f1.start();
        f2.start();
        f3.start();
    }
}
