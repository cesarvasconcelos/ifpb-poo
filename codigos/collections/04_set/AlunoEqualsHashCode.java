import java.util.Objects;
import java.util.StringJoiner;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AlunoEqualsHashCode {

    private static final String NOME_NULO_DEFAULT = "--sem nome--";

    int matrícula;
    String nome;

    public void setMatrícula( int matrícula )
    {
        this.matrícula = matrícula;
    }

    public void setNome( String nome )
    {
        this.nome = Objects.requireNonNullElse( nome, NOME_NULO_DEFAULT );
    }

    @Override
    public String toString()
    {
        return new StringJoiner( ", ", AlunoEqualsHashCode.class.getSimpleName() + "[", "]" )
                .add( "matrícula=" + matrícula )
                .add( "nome='" + Objects.toString( nome, NOME_NULO_DEFAULT ) + "'" )
                .toString();
    }

    /*
     * Um guia é manter alinhado os comportamentos de equals() e hashCode(),
     * isto é, se equals usa 2 campos para decidir se dois objetos são iguais,
     * o número hash deve ser computado a partir destes mesmos campos
     */

    // To guard against the possibility that fields are null, use
    // the Objects.equals method. The call Objects.equals(a, b)
    // returns true if both arguments are null, false if only one is null,
    // and calls a.equals(b) otherwise.
    @Override
    public boolean equals( Object otherObject )
    {
        if ( this == otherObject ) return true;
        if ( otherObject == null || getClass() != otherObject.getClass() ) return false;
        AlunoEqualsHashCode other = ( AlunoEqualsHashCode ) otherObject;
        return matrícula == other.matrícula &&
               Objects.equals( nome, other.nome );
    }

    @Override
    public int hashCode()
    {
        // returns a hash code for this object.
        // A hash code can be any integer, positive or negative.
        // Equal objects need to return identical hash codes.

        // Versão 1:
        // return 7  * Integer.hashCode( matrícula ) +
        //        11 * nome.hashCode();

        // Versão 2:
        // return 7  * Integer.hashCode( matrícula ) +
        //        11 * Objects.hashCode( nome );
        // acima: Objects.hashCode(Object a) returns 0 if a is null or a.hashCode() otherwise.
        // acima: podia ser 7 * Objects.hashCode(Object o) passando a matricula), mas
        // a JVM teria de criar um objeto Integer. Logo, melhor usar Integer.hashCode(int)

        // Versão 3 (melhor):
        // returns a hash code that is combined from the hash codes of all supplied objects.
        return Objects.hash( matrícula, nome );
    }


    // se for usar AapacheEqualsBuilder e HashCodeBuilder,
    // insira a dependência no pom.xml
    // <dependency>
    //         <groupId>org.apache.commons</groupId>
    //         <artifactId>commons-lang3</artifactId>
    //         <version>3.8.1</version>
    // </dependency>

    // @Override
    // public boolean equals( Object o )
    // {
    //     if ( this == o ) return true;
    //
    //     if ( o == null || getClass() != o.getClass() ) return false;
    //
    //     AlunoEqualsHashCode that = ( AlunoEqualsHashCode ) o;
    //
    //     return new EqualsBuilder()
    //             .append( matrícula, that.matrícula )
    //             .append( nome, that.nome )
    //             .isEquals();
    // }
    //
    // @Override
    // public int hashCode()
    // {
    //     return new HashCodeBuilder( 17, 37 )
    //             .append( matrícula )
    //             .append( nome )
    //             .toHashCode();
    // }

    public static void main( String[] args )
    {
        AlunoEqualsHashCode a1 = new AlunoEqualsHashCode();
        a1.setMatrícula( 100 );
        a1.setNome( "Maria" );

        AlunoEqualsHashCode cloneA1 = new AlunoEqualsHashCode();
        cloneA1.setMatrícula( 100 );
        cloneA1.setNome( "Maria" );

        AlunoEqualsHashCode a2 = new AlunoEqualsHashCode();
        a2.setMatrícula( 200 );
        a2.setNome( "Pedro" );

        System.out.println( "a1.hashCode() = " + a1.hashCode() );
        System.out.println( "cloneA1.hashCode() = " + cloneA1.hashCode() );

        System.out.println( "a2.hashCode() = " + a2.hashCode() );

        System.out.println( "a1.equals( cloneA1 ) = " + a1.equals( cloneA1 ) );
    }
}

/*
 * Here is a recipe for writing the *perfect* equals method:
 * 1. Name the explicit parameter otherObject—later, you will need to cast it to another variable that you should call other.
 * 2. Test whether this happens to be identical to otherObject:
 *     if (this == otherObject) return true;
 *     This statement is just an optimization. In practice, this is a common case.
 *     It is much cheaper to check for identity than to compare the fields.
 * 3. Test whether otherObject is null and return false if it is. This test is required.
 *     if (otherObject == null) return false;
 * 4. Compare the classes of this and otherObject.
 *   If the semantics of equals can change in subclasses, use the getClass test:
 *      if (getClass() != otherObject.getClass()) return false;
 *
 *   If the same semantics holds for all subclasses, you can use an instanceof test:
 *      (e podia marcar equals() com final na superclasse)
 *      if (!(otherObject instanceof ClassName)) return false;
 * 5. Cast otherObject to a variable of your class type:
 *      ClassName other = (ClassName) otherObject
 * 6. Now compare the fields, as required by your notion of equality.
 *    Use == for primitive type fields, Objects.equals for object fields.
 *    Return true if all fields match, false otherwise.
 *       return field1 == other.field1
 *       && Objects.equals(field2, other.field2) && . . .;
 *
 *   If you redefine equals in a subclass, include a call to super.equals(other).
 *   If you have fields of array type, you can use the static Arrays.equals method
 *   to check that the corresponding array elements are equal.
 */