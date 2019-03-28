public class TestarHerança {

    public static void main( String[] args )
    {
        Professor p = new Professor(); // 900
        Professor p2 = new Professor(123, "ana", 1000, 40 );

        System.out.println( p.toString() );
        System.out.println( p2.toString() );
        System.out.println( p.getSalário() );
        System.out.println( p2.getSalário() );
    }
}
