import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

enum TAMANHO_CAMISA_EN { P, G, XL }

public class Camisa {
    // variáveis de instância
    private String descrição;
    private TAMANHO_CAMISA_EN tamanho;

    // constante pública
    public static final Comparator<Camisa> POR_TAMANHO = Comparator.comparing( Camisa::getTamanho );

    public Camisa()
    {
        this( "--- sem descrição ---" , TAMANHO_CAMISA_EN.P );
    }

    public Camisa( String descrição, TAMANHO_CAMISA_EN tamanho )
    {
        setDescrição( descrição );
        setTamanho( tamanho );
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
        return new StringJoiner( ", ", Camisa.class.getSimpleName() + "{", "}" )
                .add( "descrição='" + descrição + "'" )
                .add( "tamanho=" + tamanho )
                .toString();
    }
}
