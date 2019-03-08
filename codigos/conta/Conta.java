package br.com.bb;

public class Conta
{
    // área de atributos
    private int    número;
    private String titular;
    private double saldo;

    // construtores
    public Conta(){
        setNúmero( 0 );
        setTitular( "---sem nome---" );
        // saldo inicial == 0
    }

    public Conta( int número, String titular, double saldoInicial ){
        setNúmero( número );
        setTitular( titular );
        depositar( saldoInicial );
    }

    public void sacar( double umaQuantia ){
        if( umaQuantia > 0 && umaQuantia <= this.saldo )
            this.saldo -= umaQuantia;
    }

    public void depositar( double umaQuantia ){
        if( umaQuantia > 0 )
            this.saldo += umaQuantia;
    }

    // área de métodos (get/set == métodos de acesso)
    public void setNúmero( int umNúmero ){
        if ( umNúmero > 0 )
            número = umNúmero;
    }

    public int getNúmero() {  return número;  }

    public void setTitular( String umTitular )
    {
        if( umTitular != null && umTitular.length() > 0 )
            this.titular = umTitular;
    }

    public String getTitular()
    {
        return titular;
    }

    public double getSaldo() { return saldo; }

    @Override
    public String toString()
    {
        return "Conta: " + this.getNúmero() +
               "; Titular: " + this.getTitular() +
               "; R$ " +  this.getSaldo();
    }
}
