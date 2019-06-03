class Aluno extends Thread{

    public String nome;
    public int númeroVezesEscreveu = 0;
    public static boolean escrevendo = true;

    public Aluno(String nome) {
        this.nome = nome;
    }

    public void run() {
        while(escrevendo) {
            System.out.println(this.nome + " escreveu no documento!");
            númeroVezesEscreveu++;
        }
    }
}

// EscalonadorDemo.java -- este programa demonstra o escalonador do SO em ação.
// Note que não se deve equivocadamente achar que o escalonador dará tempos
// iguais para threads; ou que elas serão executadas na ordem em que foram iniciadas
public class EscalonadorDemo {
    public static void main(String args[]) throws InterruptedException {
        Aluno maria = new Aluno("Maria");
        Aluno caio = new Aluno("Caio");

        maria.start();            // thread maria foi iniciada
        caio.start();             // thread caio foi iniciada
        Thread.sleep(1000); // pausar a thread main() por 1 segundo
        Aluno.escrevendo = false; // parar as duas threads

        maria.join(); // fazer a thread main corrente esperar até que maria termine
        caio.join(); // fazer a thread main corrente esperar até que caio termine
        System.out.format("Maria conseguiu escrever %d vezes.\n", maria.númeroVezesEscreveu);
        System.out.format("Caio conseguiu escrever %d vezes.\n", caio.númeroVezesEscreveu);
    }
}