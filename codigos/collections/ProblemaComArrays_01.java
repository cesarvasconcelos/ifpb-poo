import java.util.Arrays;
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
        return new StringJoiner( ", ", Aluno.class.getSimpleName() + "[", "]" )
                .add( "matrícula=" + matrícula )
                .add( "nome='" + nome + "'" )
                .toString();
    }
}

// Motivação: em Java, já temos uma estrutura p/ guardar 0,1 ou vários elementos: array.
// Por que não usar um array para resolver problemas de coleções, ao invés de ter de aprender algo novo?
public class ProblemaComArrays_01 {
    public static void main( String[] args )
    {
        Aluno maria = new Aluno( 10, "Maria" );
        Aluno josé = new Aluno( 20, "José" );

        // arrays possuem uma sintaxe simples
        Aluno[] arrayAlunos = { maria, josé };

        // a) mas, arrays não são "human-friendly"
        // imprima um array de objetos e veja a saída :(
        System.out.println( arrayAlunos );
        //System.out.println( Arrays.toString( arrayAlunos ) );

        // b) ainda, arrays não são redimensionáveis
        Aluno pedro = new Aluno( 30, "Pedro" );
        //arrayAlunos[ 2 ] = pedro; // java.lang.ArrayIndexOutOfBoundsException

        // *programador* :( tem de criar uma cópia do array anterior de tamanho maior,
        // e transferir os dados do array antigo para o novo (ruim)
        // arrays não dispõem de funções utilitárias prontas para me ajudar
        Aluno[] novoArrayAlunos =
                Arrays.copyOf( arrayAlunos, arrayAlunos.length + 1 );

        novoArrayAlunos[ 2 ] = pedro;
        System.out.println( Arrays.toString( novoArrayAlunos ) ); // ok

        // c) nos arrays, não há suporte nativo a enforcement de restrições (constraints)
        // e.g., como evitar duplicatas? R: array não oferece esse serviço? :(
        Aluno cópiaJosé = new Aluno( 20, "José" );
        novoArrayAlunos[ 0 ] = cópiaJosé; // problema!

        System.out.println( Arrays.toString( novoArrayAlunos ) );
    }
}
