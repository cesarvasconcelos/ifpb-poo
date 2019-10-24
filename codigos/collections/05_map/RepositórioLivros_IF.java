public interface RepositórioLivros_IF {

    Livro getLivroById( int id );
    void addLivro( Livro l );
    void apagarLivros();
}
