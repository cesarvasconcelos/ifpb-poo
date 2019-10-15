import java.util.*;
import java.util.stream.Collectors;

public class Catálogo implements Iterable<Camisa> {
    // variáveis de instância
    private Set<Camisa> camisas = new HashSet<>();

    public int tamanho()
    {
        return camisas.size();
    }

    public boolean adicionarCamisa( Camisa camisa )
    {
        return camisas.add( camisa );
    }

    public boolean removerCamisa( Camisa camisa )
    {
        return camisas.remove( camisa );
    }

    public boolean substituir( Camisa camisaAntiga, Camisa camisaNova )
    {
        if ( camisas.contains( camisaAntiga ) )
        {
            camisas.remove( camisaAntiga );
            camisas.add( camisaNova );
            return true;
        }
        return false;
    }

    public Set<Camisa> getCamisas()
    {
        return Collections.unmodifiableSet( this.camisas );
    }

    @Override
    public Iterator<Camisa> iterator()
    {
        return camisas.iterator();
    }

    public List<Camisa> getCamisasExtraGrandes()
    {
        return
              camisas.stream()
                     .filter(  camisa -> camisa.getTamanho().equals( TAMANHO_CAMISA_EN.XL )  )
                     .collect( Collectors.toList() );
    }
}
