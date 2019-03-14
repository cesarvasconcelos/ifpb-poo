package br.com.bb;

import org.junit.Assert;
import org.junit.Test;

public class BancoTest {
    @Test
    public void testCadastrarConta()
    {
        // conta: número, titular, saldo
        // banco: para cadastrar a conta
        Banco b = new Banco();
        Assert.assertEquals( 0, b.quantidadeContas() );

        // funcionamento normal
        Assert.assertTrue( b.cadastrarConta( 777, "Pedro", 112.90 ) );
        Assert.assertEquals( 1, b.quantidadeContas() );

        Assert.assertTrue( b.cadastrarConta( 888, "Ana", 2300.99) );
        Assert.assertEquals( 2, b.quantidadeContas() );

        // caso especial: caso haja duplicatas
        Assert.assertFalse( b.cadastrarConta( 777, "Pedro", 112.90 ) );
        Assert.assertEquals( 2, b.quantidadeContas() );

    }
}
