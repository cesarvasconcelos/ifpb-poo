import java.util.*;

public class Catálogo implements Iterable<Camisa> {
    // variáveis de instância
    private List<Camisa> camisas = new ArrayList<>();

    // constantes públicas
    public static final int POSIÇÃO_NÃO_EXISTENTE = -1;

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
        int posiçãoCamisaAntiga = camisas.indexOf( camisaAntiga );

        if ( posiçãoCamisaAntiga == POSIÇÃO_NÃO_EXISTENTE ) return false;

        camisas.set( posiçãoCamisaAntiga, camisaNova );
        return true;
    }

    public List<Camisa> getCamisas()
    {
        return Collections.unmodifiableList( this.camisas );
    }

    @Override
    public Iterator<Camisa> iterator()
    {
        return camisas.iterator();
    }

    public List<Camisa> getCamisasExtraGrandes(){
        // note o método para ordenação
        camisas.sort( Camisa.POR_TAMANHO );

        int indexSeparação = POSIÇÃO_NÃO_EXISTENTE;

        // note o método de acesso get(index)
        for ( int i = 0;  i < camisas.size(); i++ )
        {
            if ( camisas.get( i )
                        .getTamanho()
                        .equals( TAMANHO_CAMISA_EN.XL ) )
            {
                indexSeparação = i;
                break;
            }
        }

        if( indexSeparação == POSIÇÃO_NÃO_EXISTENTE ) return Collections.emptyList();
        else
            // note o método para obtenção de sub-lista
            return camisas.subList( indexSeparação, camisas.size() );
    }
}
