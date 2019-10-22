import java.util.*;

class ComparadorPorPreço implements Comparator<Produto> {

    @Override
    public int compare( Produto o1, Produto o2 )
    {
        return Double.compare( o1.preço, o2.preço ); // 1, -1,  0
    }
}


public class Produto implements Comparable<Produto>{
    int id;
    String descrição;
    double preço;

    public Produto( int id, String descrição, double preço )
    {
        this.id = id;
        this.descrição = descrição;
        this.preço = preço;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getDescrição()
    {
        return descrição;
    }

    public void setDescrição( String descrição )
    {
        this.descrição = descrição;
    }

    public double getPreço()
    {
        return preço;
    }

    public void setPreço( double preço )
    {
        this.preço = preço;
    }

    @Override
    public String toString()
    {
        return new StringJoiner( ", ", Produto.class.getSimpleName() + "[", "]" )
                .add( "id=" + id )
                .add( "descrição='" + descrição + "'" )
                .add( "preço=" + preço )
                .toString();
    }

    @Override
    public boolean equals( Object otherObject )
    {
        if ( this == otherObject ) return true;
        if ( otherObject == null || getClass() != otherObject.getClass() ) return false;

        Produto outroProduto = ( Produto ) otherObject;

        return id == outroProduto.id &&
               // this.descrição.equalsIgnoreCase( produto.descrição ) &&
               Objects.equals( this.descrição, outroProduto.descrição ) &&
               Double.compare( this.preço, outroProduto.preço ) == 0; // double
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( this.id, this.descrição, this.preço );
        //return Integer.hashCode( this.id );
    }

    @Override
    public int compareTo( Produto otherProduct )
    {
        // a ordenação padrão (pelo id)

        // if( this.id  < otherProduto.id ) return -1;
        // if( this.id  > otherProduto.id ) return 1;
        // return 0; // iguais!
        return Integer.compare( this.id, otherProduct.id );

    }

    public static void main( String[] args )
    {
        Produto pb = new Produto(100, "B", 1200.99);
        Produto pa = new Produto(200, "C", 12.99);
        Produto pc = new Produto(300, "A", 500.99 );
        Produto pclone = new Produto(300, "A", 500.99 );

        Set<Produto> hashSet = new HashSet<>();
        hashSet.add( pb );
        hashSet.add( pa );
        hashSet.add( pc );
        hashSet.add( pclone ); // duplicata?

        // System.out.println( "hashSet.size() = " + hashSet.size() );
        // System.out.println( "hashSet.toString() = " + hashSet.toString() );

        // TreeSet<Produto> treeSet = new TreeSet<>( new ComparadorPorPreço() );

        Comparator<Produto> comparatorPorPreço =
                (o1, o2)  -> Double.compare( o1.preço, o2.preço );
        Comparator<Produto> comparatorPorPreço_V3 =
                Comparator.comparingDouble( Produto::getPreço );

        Comparator<Produto> comparatorPelaDesc =
                ( p1, p2 ) -> p1.descrição.compareTo( p2.descrição );
        Comparator<Produto> comparatorPelaDesc_v2 =
                Comparator.comparing( Produto::getDescrição );

        TreeSet<Produto> treeSet = new TreeSet<>( comparatorPelaDesc );
        treeSet.addAll( hashSet );
        System.out.println( "treeSet.size() = " + treeSet.size() );
        System.out.println( "treeSet.toString() = " + treeSet.toString() );


    }



}
