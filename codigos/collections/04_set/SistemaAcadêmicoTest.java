import br.edu.ifpb.Aluno;
import br.edu.ifpb.SistemaAcadêmico;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SistemaAcadêmicoTest {
    private static final Aluno caio  = new Aluno( 100, "Caio", 4.0 );
    private static final Aluno maria = new Aluno( 200, "Maria", 7.0 );
    private static final Aluno bruno = new Aluno( 300, "Bruno", 6.9 );
    private static final Aluno josé  = new Aluno( 400, "José", 1.5 );
    private static final Aluno ana   = new Aluno( 500, "Ana", 8.5 );

    private static final Aluno cloneAna = new Aluno( 500, "Ana", 8.5 );

    private SistemaAcadêmico acadêmico = new SistemaAcadêmico( Aluno.COMPARATOR_POR_NOTA );

    @Before
    public void initializeSetOfAlunos()
    {
        acadêmico.adicionarAluno( caio );
        acadêmico.adicionarAluno( maria );
        acadêmico.adicionarAluno( bruno );
        acadêmico.adicionarAluno( josé );
        acadêmico.adicionarAluno( ana );
    }

    @Test
    public void testNãoDeveAdicionarAluno()
    {
        acadêmico.adicionarAluno( ana ); // não deve inserir duplicatas
        acadêmico.adicionarAluno( cloneAna ); // não deve inserir duplicatas

        // o contains aqui verifica a ordem (TreeSet ordenará pelo comparador)
        assertThat( acadêmico, contains( josé, caio, bruno, maria, ana ) );
        assertThat( acadêmico.quantidadeAlunos(), is( 5 ) );
    }

    @Test
    public void testDeveRetornarApenasAprovados()
    {
        Set<Aluno> aprovados = acadêmico.getAprovados();
        assertThat( aprovados, contains( maria, ana ) );
    }

    @Test
    public void testDeveRetornarApenasReprovados()
    {
        Set<Aluno> reprovados = acadêmico.getReprovados();
        assertThat( reprovados, contains( josé, caio, bruno ) );
    }

    @Test
    public void testDeveRetornarApenasParaConselhoClasse()
    {
        Set<Aluno> alunosConselhoClasse = acadêmico.getAlunosConselhoClasse();
        assertThat( alunosConselhoClasse, contains( caio, bruno ) );
    }

    @Test
    public void testDeveRetornarAlunoMaiorNota()
    {
        assertThat( acadêmico.getAlunoMaiorNota(), is( ana ) );
    }

    @Test
    public void testDeveRetornarAlunoMenorNota()
    {
        assertThat( acadêmico.getAlunoMenorNota(), is( josé ) );
    }
}