package br.com.bb;

public class TesteConta {
    public static void main( String[] args )
    {
        // criar 2 objetos do tipo conta
        Conta o1 = new Conta(); // construtor sem argumentos
        Conta o2 = new Conta( 999, "José", 260.99 );

        o1.setNúmero( 555 );
        o1.setTitular( "ana" );
        //o1.setSaldo( 1200.99 );

        System.out.println( " Conta: " + o1.getNúmero() +
                            " Titular: " + o1.getTitular() +
                            " Saldo R$: " + o1.getSaldo() );

        System.out.println( " Conta: " + o2.getNúmero() +
                            " Titular: " + o2.getTitular() +
                            " Saldo R$: " + o2.getSaldo() );
    }
}
