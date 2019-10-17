package br.edu.ifpb;

import java.util.*;

public class SistemaAcadêmico implements Iterable<Aluno> {

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
    // for given search targets. Methods lower(<E), floor(<=E), ceiling(>=E), and higher(>E) return elements respectively
    // less than, less than or equal, greater than or equal, and greater than a given element, returning null
    // if there is no such element.
    private NavigableSet<Aluno> setAlunos;

    public SistemaAcadêmico( Comparator<Aluno> comparator )
    {
        Comparator<Aluno> comp = Objects.requireNonNullElse( comparator, Comparator.naturalOrder() );
        setAlunos = new TreeSet<>( comp );
    }

    public boolean adicionarAluno( Aluno aluno )
    {
        return setAlunos.add( aluno );
    }

    public Set<Aluno> getAprovados()
    {
        Aluno primeiroAprovado = encontrarPrimeiroAlunoAprovado();
        return setAlunos.tailSet( primeiroAprovado );
    }

    public Set<Aluno> getReprovados()
    {
        Aluno primeiroAprovado = encontrarPrimeiroAlunoAprovado();
        return setAlunos.headSet( primeiroAprovado );
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
        Aluno primeiroAlunoConselho = this.encontrarPrimeiroAlunoConselho();
        Aluno primeiroAlunoAprovado = this.encontrarPrimeiroAlunoAprovado();

        return setAlunos.subSet( primeiroAlunoConselho, true,
                                 setAlunos.lower( primeiroAlunoAprovado ), true  );
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

    // bom candidato a refactoring (extract method & inline)
    private Aluno encontrarPrimeiroAlunoAprovado()
    {
        Optional<Aluno> optionalAluno =
                setAlunos.stream()
                         .filter( aluno -> aluno.getNotaFinal() >= 7D )
                         .findFirst();
        return optionalAluno.orElseThrow(); // NoSuchElementException
    }

    // bom candidato a refactoring (extract method & inline)
    private Aluno encontrarPrimeiroAlunoConselho()
    {
        Optional<Aluno> optionalAluno =
                setAlunos.stream()
                         .filter( aluno -> aluno.getNotaFinal() >= 4D )
                         .findFirst();
        return optionalAluno.orElseThrow(); // NoSuchElementException
    }
}
