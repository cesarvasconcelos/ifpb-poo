package br.edu.ifpb;

import org.junit.Assert;
import org.junit.Test;

public class UniversidadeTest
{
    @Test
    public void testCadastroFuncionárioDeveFuncionar()
    {
        // Dado que...
        Universidade u = new Universidade();

        // Quando....
        Professor p1 =
                new Professor(467, "ana",
                              1200, 20 );
        Gerente g1 =
                new Gerente(332, "pedro",
                            3000, 500.99 );
        boolean resultadoProf = u.adicionarFuncionário( p1 );
        boolean resultadoGer = u.adicionarFuncionário( g1 );

        // Então...
        // p1 e g1 foi cadastrado com sucesso
        Assert.assertTrue( resultadoProf );
        Assert.assertTrue( resultadoGer );
    }

    @Test
    public void testCadastroFuncionárioNãoDeveFuncionar()
    {
        // Dado que...
        Universidade u = new Universidade();

        // Quando....
        Professor p1 =
                new Professor(467, "ana",
                              1200, 20 );
        Professor cloneP1 =
                new Professor(467, "ana",
                              1200, 20 );

        Professor pNull = null;

        boolean resultadoProf = u.adicionarFuncionário( p1 );
        boolean resultadoClone = u.adicionarFuncionário( p1 );
        boolean resultadoPNull = u.adicionarFuncionário( pNull );

        // Então...
        Assert.assertTrue( resultadoProf );
        Assert.assertFalse( resultadoClone );
        Assert.assertFalse( resultadoPNull );
    }
}
