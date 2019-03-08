package br.com.bb;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple Conta.
 * version 1.0
 */
public class ContaTest
{
    @Test
    public void testSacar(){
        Conta conta = new Conta( 555, "ana", 1000.99 );

        // 1o testar o funcionamento normal
        conta.sacar( 100 );
        Assert.assertEquals( 900.99, conta.getSaldo(), 0.0  );

        // 2o casos excepcionais
        conta.sacar( -100 ); // sacar quantias negativas
        Assert.assertEquals( 900.99, conta.getSaldo(), 0.0  );

        conta.sacar( 10000 ); // sacar quando não há fundos
        Assert.assertEquals( 900.99, conta.getSaldo(), 0.0  );

        conta.sacar( 900.99 ); // "zerar" a conta
        Assert.assertEquals( 0.0, conta.getSaldo(), 0.0  );
    }

    @Test
    public void testSetTitular()
    {
        Conta conta = new Conta();

        // 1o testar o funcionamento normal
        conta.setTitular( "ana" );
        Assert.assertEquals( "ana" , conta.getTitular() );

        // 2o casos excepcionais
        conta.setTitular( null ); // caso argumento seja null
        Assert.assertEquals( "ana" , conta.getTitular() );

        conta.setTitular( "" ); // caso argumento seja "String vazia"
        Assert.assertEquals( "ana" , conta.getTitular() );
    }

    @Test
    public void testDepositar()
    {
        Conta conta = new Conta();

        conta.depositar( 1000 );
        Assert.assertEquals( 1000.00, conta.getSaldo(), 0.0  );

        conta.depositar( -10 );
        Assert.assertEquals( 1000.00, conta.getSaldo(), 0.0  );
    }

    @Test
    public void testSetNúmero()
    {
        Conta conta = new Conta();

        conta.setNúmero( 555 );
        Assert.assertEquals( 555, conta.getNúmero() );

        conta.setNúmero( -555 );
        Assert.assertEquals( 555, conta.getNúmero() );
    }

    @Test
    public void testConstrutores()
    {
        // construtor default
        Conta c1 = new Conta();
        Assert.assertEquals( 0, c1.getNúmero() );
        Assert.assertEquals( "---sem nome---", c1.getTitular());
        Assert.assertEquals( 0.0, c1.getSaldo(), 0.0 );

        // construtor com argumentos
        Conta c2 = new Conta( 888, "pedro", 2380.99);
        Assert.assertEquals( 888, c2.getNúmero() );
        Assert.assertEquals( "pedro", c2.getTitular());
        Assert.assertEquals( 2380.99, c2.getSaldo(), 0.0 );
    }

    @Test
    public void testToString(){
        String str1 = "Conta: 0; Titular: ---sem nome---; R$ 0.0";
        String str2 = "Conta: 777; Titular: pedro; R$ 2300.99";

        Conta c1 = new Conta();
        Conta c2 = new Conta(777, "pedro", 2300.99 );

        Assert.assertEquals( str1, c1.toString() );
        Assert.assertEquals( str2, c2.toString() );
    }
}
