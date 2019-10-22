import java.util.*;
import java.util.function.Predicate;

public class SistemaAcadêmico implements Iterable<Aluno> {
    public static final double MÉDIA_APROVADO = 7D;
    public static final double MÉDIA_CONSELHO = 4D;

    public static final Predicate<Aluno> CONDIÇÃO_APROVADO =
                                        aluno -> aluno.getNotaFinal() >= MÉDIA_APROVADO;
    public static final Predicate<Aluno> CONDIÇÃO_CONSELHO =
                                        aluno -> aluno.getNotaFinal() >= MÉDIA_CONSELHO;

    // como TreeSet implementa SortedSet e NavigableSet (extends SortedSet)
    // logo, posso usar métodos que não existem em HashSet

    // The SortedSet interface expose the comparator object used for sorting,
    // and it define methods to obtain *views of subsets* of the collections.
    // importante: mudanças nas views/sub-ranges são refletidas na coleção original e vice-versa
    // lembrar que SortedSet define uma ordem, mas não é como listas, pois não há índices.
    // principais métodos: headSet, tailSet, subSet, first e last
        // Ex: (10, 15, 18, 20,) 23, 31, 44, 56  ← headSet(23)
        // Ex: 10, 15, 18, 20, (23, 31, 44, 56)  ← tailSet(23)
        // Ex: 10, (15, 18, 20, 23, 31,) 44, 56  ← subset(15, 44)

    // Por outro lado, NavigableSet extends SortedSet, adicionando novos comportamentos
    // Javadoc: A SortedSet extended with navigation methods reporting closest matches
    // for given search targets. Methods lower(<E), floor(<=E), ceiling(>=E), and higher(>E)
    // return elements respectively
    // less than, less than or equal, greater than or equal, and greater than a given element, returning null
    // if there is no such element.
    private NavigableSet<Aluno> setAlunos;

    public SistemaAcadêmico( Comparator<Aluno> comparator )
    {
        Comparator<Aluno> comp =
                Objects.requireNonNullElse( comparator, Comparator.naturalOrder() );
        setAlunos = new TreeSet<>( comp );
    }

    public boolean adicionarAluno( Aluno aluno )
    {
        return setAlunos.add( aluno );
    }

    public Set<Aluno> getAprovados()
    {
        return setAlunos.tailSet( getAluno( CONDIÇÃO_APROVADO ) );
    }

    public Set<Aluno> getReprovados()
    {
        return setAlunos.headSet( getAluno( CONDIÇÃO_APROVADO ) );
    }

    public Aluno getAlunoMaiorNota()
    {
        return setAlunos.last();
    }

    public Aluno getAlunoMenorNota()
    {
        return setAlunos.first();
    }

    public Set<Aluno> getAlunosConselhoClasse()
    {
        return
        setAlunos.subSet( getAluno( CONDIÇÃO_CONSELHO ), true,
                         setAlunos.lower( getAluno( CONDIÇÃO_APROVADO ) ), true );
    }

    @Override
    public Iterator<Aluno> iterator()
    {
        return setAlunos.iterator();
    }

    public int quantidadeAlunos()
    {
        return setAlunos.size();
    }

    private Aluno getAluno( Predicate<Aluno> predicate )
    {
        return setAlunos.stream() // mostre os dados
                .filter( predicate )
                .findFirst()
                .orElseThrow(); // NoSuchElementException
    }
}
