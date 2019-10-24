import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Livro{
    private int    id;
    private String título;

    public Livro( int id, String título )
    {
        this.id = id;
        this.título = título;
    }

    public int getId()
    {
        return id;
    }
}

public class MapVersusList {
    public static final int QUANTIDADE_LIVROS = 10_000;
    public static final int NUM_ITERAÇÕES_TESTE = 5;

    static List<Livro> livros = buildObjetosLivros();

    public static void main( String[] args )
    {
        MapVersusList.rodarTeste( new ListaLivros() );
        MapVersusList.rodarTeste( new MapaLivros() );
    }

    private static void rodarTeste( RepositórioLivros_IF repo )
    {

        System.out.println( "Rodando teste em " + repo.getClass().getSimpleName() );

        for ( int i = 0; i < NUM_ITERAÇÕES_TESTE; i++ )
        {
            // após o Java 8, o
            // pacote é o java.time
            // pode-se capturar um instante no tempo
            // Instant representa um ponto na linha de tempo
            // com precisão em nanosegundos, i.e., alta granularidade

            // iniciar o tempo
            Instant início =  Instant.now();// instante corrente em milisegundos deste 1/1/1970

            // inserir
            for ( Livro livro : livros )
                repo.addLivro( livro );

            // buscar
            for ( Livro livro : livros )
            {
                Livro tmp = repo.getLivroById( livro.getId() );
            }

            // apagar
            repo.apagarLivros();

            // "parar" o tempo
            Instant fim = Instant.now(); // milisegundos deste 1/jan/1970

            // computando diferenças entre dois Instants
            Duration diff = Duration.between( início, fim );
            System.out.println( ">> " + diff.toMillis() + " ms" );
            // acima, podia usar métodos toNanos, getSeconds, toMinutes, toHours, toDays, etc.
        }
    }

    private static List<Livro> buildObjetosLivros()
    {
        List<Livro> lista = new ArrayList<>();
        // objetos livros
        for ( int i = 1; i <= QUANTIDADE_LIVROS; i++ )
            lista.add( new Livro( i, String.format( "Livro: $d", i ) ) );

        Collections.shuffle( lista );
        return lista;
    }
}
