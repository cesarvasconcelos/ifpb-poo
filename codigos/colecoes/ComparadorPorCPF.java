import java.util.Comparator;

// 1a forma: criar uma classe que implemente a interface Comparator
public class ComparadorPorCPF implements
                              Comparator<Aluno> {
    @Override
    public int compare( Aluno o1, Aluno o2 )
    {
        return Long.compare( o1.getCpf(),
                             o2.getCpf() );
    }
}
