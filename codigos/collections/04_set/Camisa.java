import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

enum TAMANHO_CAMISA_EN { P, G, XL }

public class Camisa {
    // variáveis de instância
    private int código;
    private String descrição;
    private TAMANHO_CAMISA_EN tamanho;

    // constante pública
    public static final Comparator<Camisa> POR_TAMANHO = Comparator.comparing( Camisa::getTamanho );

    public Camisa()
    {
        this( 0, "--- sem descrição ---" , TAMANHO_CAMISA_EN.P );
    }

    public Camisa( int código, String descrição, TAMANHO_CAMISA_EN tamanho )
    {
        setCódigo( código );
        setDescrição( descrição );
        setTamanho( tamanho );
    }

    public void setCódigo( int código )
    {
        this.código = (código < 0 ) ? 0 : código;
    }

    public int getCódigo()
    {
        return código;
    }

    public String getDescrição()
    {
        return descrição;
    }

    public void setDescrição( String descrição )
    {
        this.descrição = Objects.requireNonNullElse( descrição, "--- sem descrição ---" );
    }

    public TAMANHO_CAMISA_EN getTamanho()
    {
        return tamanho;
    }

    public void setTamanho( TAMANHO_CAMISA_EN tamanho )
    {
        this.tamanho = tamanho;
    }

    @Override
    public String toString()
    {
        return new StringJoiner( ", ", Camisa.class.getSimpleName() + "[", "]" )
                .add( "código=" + código )
                .add( "descrição='" + descrição + "'" )
                .add( "tamanho=" + tamanho )
                .toString();
    }

/*    @Override
    public boolean equals( Object outro )
    {
        if ( this == outro ) return true;
        if ( outro == null || getClass() != outro.getClass() ) return false;
        Camisa camisa = ( Camisa ) outro;
        return getCódigo() == camisa.getCódigo();
    }

    @Override
    public int hashCode()
    {
        return Integer.hashCode( getCódigo() );
    }*/
}
