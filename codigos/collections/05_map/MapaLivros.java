import java.util.HashMap;
import java.util.Map;

public class MapaLivros implements RepositórioLivros_IF {

    private Map<Integer, Livro> mapa = new HashMap<>();

    @Override
    public Livro getLivroById( int id )
    {
        return mapa.get( id );
    }

    @Override
    public void addLivro( Livro l )  { mapa.put( l.getId(), l ); }

    @Override
    public void apagarLivros()
    {
        mapa.clear();
    }
}
