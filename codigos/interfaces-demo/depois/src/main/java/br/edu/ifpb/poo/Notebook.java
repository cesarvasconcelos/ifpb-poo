package br.edu.ifpb.poo;

import java.util.StringJoiner;

public class Notebook extends Produto implements Tributável {
    private int númeroCores;

    public Notebook()
    {
        super();
        setNúmeroCores( 2 ); // um default
    }

    public Notebook( int código, String descrição, double preço, int númeroCores )
    {
        super( código, descrição, preço );
        setNúmeroCores( númeroCores );
    }

    public void setNúmeroCores( int númeroCores )
    {
        this.númeroCores = númeroCores > 0 ? númeroCores : this.númeroCores;
    }

    public int getNúmeroCores()
    {
        return númeroCores;
    }

    @Override
    public String toString()
    {
        return new StringJoiner( ", ", "[", "]" )
                .add( super.toString() )
                .add( "#cores= " + getNúmeroCores() )
                .toString();
    }

    @Override
    public double imposto()
    {
        return getPreço() * .6;
    }
}
