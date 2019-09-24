package br.edu.ifpb;

import org.junit.Assert;
import org.junit.Test;

public class UniversidadeTest
{
    @Test
    public void testAdicionarDeveFuncionar()
    {
        // Dado que (pré-condições)
        Universidade u = new Universidade();
        Professor p1 = new Professor( 555, "ana",
                                      1200.99, 20 );
        Gerente g1 = new Gerente( 777, "pedro",
                                      3000.99, 500 );

        // Quando
        boolean resultadoProf = u.adicionarFuncionário( p1  );
        boolean resultadoGer = u.adicionarFuncionário( g1 );

        // Então (verifique)
        Assert.assertTrue( resultadoProf );
        Assert.assertTrue( resultadoGer );
    }

    @Test
    public void testAdicionarNãoDeveFuncionar()
    {
        // Dado que (pré-condições)
        Universidade u = new Universidade();

        Professor p1 = new Professor( 555, "ana",
                                      1200.99, 20 );

        Professor pClone = new Professor( 555, "ana",
                                      1200.99, 20 );

        Gerente g1 = new Gerente( 777, "pedro",
                                  3000.99, 500 );
        Gerente gNull  = null;

        // Quando
        boolean resultadoProf = u.adicionarFuncionário( p1  ); // ok
        boolean resultadoGer = u.adicionarFuncionário( g1 );  // ok
        boolean resultadoProfClone = u.adicionarFuncionário( pClone  ); // não
        boolean resultadoGNull = u.adicionarFuncionário( gNull ); // não

        // Então (verifique)
        Assert.assertTrue( resultadoProf );
        Assert.assertTrue( resultadoGer );
        Assert.assertFalse( resultadoProfClone );
        Assert.assertFalse( resultadoGNull );
    }

    @Test
    public void testBuscaDeveFuncionar()
    {
        // Dado que (pré-condições)
        Universidade u = new Universidade();

        Professor p1 = new Professor( 555, "ana",
                                      1200.99, 20 );
        u.adicionarFuncionário( p1 );

        // Quando
        Funcionário funcionário = u.buscarFuncionário( 555 );

        // Então
        Assert.assertNotNull( funcionário );
        Assert.assertEquals( funcionário.getMatrícula(), p1.getMatrícula() );
    }

    @Test
    public void testBuscaNãoDeveFuncionar()
    {
        // Dado que (pré-condições)
        Universidade u = new Universidade();

        Professor p1 = new Professor( 555, "ana",
                                      1200.99, 20 );

        // Quando
        u.adicionarFuncionário( p1 );
        Funcionário funcionário = u.buscarFuncionário( 999 );

        // Então
        Assert.assertNull( funcionário );
    }
}
