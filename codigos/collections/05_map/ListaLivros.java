import java.util.ArrayList;
import java.util.List;

public class ListaLivros implements RepositórioLivros_IF {

    private List<Livro> lista = new ArrayList<>();

    @Override
    public Livro getLivroById( int id )
    {
        for ( Livro livro : lista )
            if ( livro.getId() == id )
                return livro;

        return null;
    }

    @Override
    public void addLivro( Livro l )
    {
        for ( Livro livro : lista )
            if ( livro.getId() == l.getId() )
                throw new IllegalArgumentException( "Erro de inserção na lista, há duplicata em: " + livro );

        lista.add( l );
    }

    @Override
    public void apagarLivros()
    {
        lista.clear();
    }
}
