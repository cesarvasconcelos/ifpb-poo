package br.edu.ifpb.poo;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class Produto {
    private int    código;
    private String descrição;
    private double preço;

    public Produto()
    {
        this( 0, "--- sem descrição ---", 0.0 );
    }

    public Produto( int código, String descrição, double preço )
    {
        setCódigo( código );
        setDescrição( descrição );
        setPreço( preço );
    }

    public int getCódigo()
    {
        return código;
    }

    public void setCódigo( int código )
    {
        this.código = código > 0 ? código : this.código;
    }

    public String getDescrição()
    {
        return Objects.toString( descrição, "--- sem descrição ---" );
    }

    public void setDescrição( String descrição )
    {
        Objects.requireNonNull( descrição, "A descrição do produto não pode ser nula!" );
        this.descrição = descrição.isBlank() || descrição.isEmpty() ? this.descrição : descrição;
    }

    public double getPreço()
    {
        return preço;
    }

    public void setPreço( double preço )
    {
        this.preço = preço > 0 ? preço : this.preço;
    }

    @Override
    public String toString()
    {
        final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance( new Locale( "pt", "BR" ) );

        return new StringJoiner( "," )
                .add( "código= " + getCódigo() )
                .add( " descrição= " + getDescrição() )
                .add( " preço= "  + currencyFormatter.format( getPreço() ) )
                .toString();
    }

    public abstract double imposto();
}
