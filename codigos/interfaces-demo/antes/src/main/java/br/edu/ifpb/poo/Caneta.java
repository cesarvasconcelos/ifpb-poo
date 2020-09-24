package br.edu.ifpb.poo;

import java.util.Objects;
import java.util.StringJoiner;

public class Caneta extends Produto {
    private String corTinta;

    public Caneta()
    {
        super();
        setCorTinta( "azul" ); // um default
    }

    public Caneta( int código, String descrição, double preço, String corTinta )
    {
        super( código, descrição, preço );
        setCorTinta( corTinta );
    }

    public String getCorTinta()
    {
        return corTinta;
    }

    public void setCorTinta( String corTinta )
    {
        Objects.requireNonNull( corTinta, "A cor da tinta não pode ser nula!" );
        this.corTinta = corTinta.isBlank() || corTinta.isEmpty() ? this.corTinta : corTinta;
    }

    @Override
    public String toString()
    {
        return new StringJoiner( ", ", "[", "]" )
                .add( super.toString() )
                .add( "corTinta= " + getCorTinta() )
                .toString();
    }

    @Override
    public double imposto()
    {
        return 0.0; // isento
    }
}
