package br.com.banco;


import org.junit.Assert;
import org.junit.Test;

public class BancoTest {

    @Test
    public void testCadastroContasDeveriaFuncionar()
    {
        Banco banco = new Banco();
        // garantir que banco não tem ainda nenhuma conta
        Assert.assertEquals( 0 , banco.quantidadeContas() );

        banco.cadastrarConta( 555, "ana", 1200.99 );
        Assert.assertEquals( 1 , banco.quantidadeContas() );
    }

    @Test
    public void testCadastroDeContasDuplicatasNãoDeveriaFuncionar()
    {
        Banco banco = new Banco();

        Assert.assertEquals( 0 , banco.quantidadeContas() );
        banco.cadastrarConta( 555, "ana", 1200.99 );
        Assert.assertEquals( 1 , banco.quantidadeContas() );

        banco.cadastrarConta( 555, "ana", 1200.99 );
        Assert.assertEquals( 1 , banco.quantidadeContas() );
    }

    @Test
    public void testBuscaContaExistenteDeveriaFuncionar()
    {
        Banco banco = new Banco();
        banco.cadastrarConta( 555, "ana", 1200.99 );

        Assert.assertTrue( banco.buscarConta( 555 ) ); // V/F
    }


    @Test
    public void testBuscaContaNãoExisteNãoDeveFuncionar()
    {
        Banco banco = new Banco();
        Assert.assertFalse( banco.buscarConta( 555 ) ); // V/F

        banco.cadastrarConta( 555, "ana", 1200.99 );
        Assert.assertFalse( banco.buscarConta( 999 ) ); // V/F
    }
}
