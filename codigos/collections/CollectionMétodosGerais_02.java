import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringJoiner;

class Aluno {
    int    matrícula;
    String nome;

    public Aluno( int matrícula, String nome )
    {
        this.matrícula = matrícula;
        this.nome = nome;
    }

    @Override
    public String toString()
    {
        return new StringJoiner( ", ", Aluno.class.getSimpleName() + "{", "}" )
                .add( "matrícula=" + matrícula )
                .add( "nome='" + nome + "'" )
                .toString();
    }
}

// motivação: comportamento geral da interface java.util.Collection
// interfaces List<T>, Set<T>, ...  herdam da interface java.util.Collection,
// que por sua vez, *herda* da interace Iterable
public class CollectionMétodosGerais_02 {
    public static void main( String[] args )
    {
        Aluno maria = new Aluno( 10, "Maria" );
        Aluno josé = new Aluno( 20, "José" );
        Aluno pedro = new Aluno( 30, "Pedro" );

        Collection<Aluno> alunos = new ArrayList<>( );

        // vamos ao contrato geral, isto é, os
        // métodos gerais que as coleções possuirão
        alunos.add( maria );
        alunos.add( josé );
        alunos.add( pedro );

        // note a conveniência do toString de coleções,
        // quando comparado a um array
        System.out.println( alunos );

        // iterar com for-each ( var : Iterable ):
        for ( Aluno a: alunos )
            System.out.println( "a = " + a );

        // iterar pedindo um Iterator
        for ( Iterator<Aluno> it = alunos.iterator(); it.hasNext() ; )
        {
            // retorna o próx aluno e move
            // o cursor para o seguinte (se houver)
            Aluno a =  it.next();
            System.out.println( "a_it = " + a );
        }

        // iterar via Iterator, usando Consumer<T>
        alunos.iterator()
              .forEachRemaining( aluno -> System.out.println( "a_it_forEachRemaining= " + aluno.toString() ) );

        // iterar com Streams, Java 8
        alunos.stream()
              .forEach( System.out::println ); // method reference aluno -> System.out.println( aluno )

        // tamanho?, vazia?, contém?, remover, removerIF, addAll, etc.
        System.out.println( "alunos.isEmpty() = " + alunos.isEmpty() );
        System.out.println( "alunos.remove( pedro ) = " + alunos.remove( pedro ) );
        System.out.println( "alunos.contains( pedro ) = " + alunos.contains( pedro ) );
        System.out.println( "alunos.removeIf( Predicate ) = " + alunos.removeIf( aluno -> aluno.matrícula < 20 ) );
        System.out.println( "alunos.size() = " + alunos.size() );
        System.out.println( alunos );

        System.out.println();
        alunos.add( maria );
        alunos.add( pedro );

        Collection<Aluno> outraLista = new ArrayList<>();
        outraLista.addAll( alunos );
        System.out.println( "outraLista= " + outraLista );

        // não modifique (i.e, remover, adicionar, clear, etc. )
        // enquanto estiver iterando no loop.
        //for ( Aluno aluno : outraLista )
        //{
        //    if ( aluno.matrícula < 20  ){
        //        System.out.println( "aluno = " + aluno );
        //    }
        //    else{
        //        outraLista.remove( aluno ); // Exception: java.util.ConcurrentModificationException
        //    }
        //}

        for ( Iterator<Aluno> iterator = outraLista.iterator(); iterator.hasNext(); )
        {
            Aluno aluno =  iterator.next();
            if ( aluno.matrícula < 20  ){
                System.out.println( "aluno = " + aluno );
            }
            else{
                iterator.remove(); // mas, com iterator.remove(), não há exceção :)
            }
        }
        System.out.println( "outraLista= " + outraLista );
    }
}
/*  Listagem dos métodos de Collection Interface (vide Core Java):
 *  - Iterator<E> iterator()
 *      returns an iterator that can be used to visit the elements in the collection.
 *  - int size()
 *      returns the number of elements currently stored in the collection.
 *  - boolean isEmpty()
 *      returns true if this collection contains no elements.
 *  - boolean contains(Object obj)
 *      returns true if this collection contains an object equal to obj.
 *  - boolean containsAll(Collection<?> other)
 *      returns true if this collection contains all elements in the other collection.
 *  - boolean add(E element)
 *      adds an element to the collection. Returns true if the collection changed as a result of this call.
 *  - boolean addAll(Collection<? extends E> other)
 *      adds all elements from the other collection to this collection.
 *      Returns true if the collection changed as a result of this call.
 *  - boolean remove(Object obj)
 *      removes an object equal to obj from this collection. Returns true if a matching object was removed.
 *  - boolean removeAll(Collection<?> other)
 *      removes from this collection all elements from the other collection.
 *      Returns true if the collection changed as a result of this call.
 *  - default boolean removeIf(Predicate<? super E> filter) 8
 *      removes all elements for which filter returns true.
 *      Returns true if the collection changed as a result of this call.
 *  - void clear()
 *      removes all elements from this collection.
 *  - boolean retainAll(Collection<?> other)
 *      removes all elements from this collection that do not equal one of the elements in the other collection.
 *      Returns true if the collection changed as a result of this call.
 *  - Object[] toArray()
 *      returns an array of the objects in the collection.
 *  - <T> T[] toArray(T[] arrayToFill)
 *      returns an array of the objects in the collection.
 *      If arrayToFill has sufficient length, it is filled with the elements of this collection.
 *      If there is space, a null element is appended.
 *      Otherwise, a new array with the same component type as arrayToFill and
 *      the same length as the size of this collection is allocated and filled.
 */