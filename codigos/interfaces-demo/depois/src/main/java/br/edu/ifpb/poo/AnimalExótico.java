package br.edu.ifpb.poo;

import java.util.StringJoiner;

public class AnimalExótico implements Tributável {

    private String espécie;
    private double peso;

    public AnimalExótico( String espécie, double peso )
    {
        this.setEspécie( espécie );
        this.setPeso( peso );
    }

    @Override
    public double imposto()
    {
        return 10e+3;
    }

    public String getEspécie()
    {
        return espécie;
    }

    public void setEspécie( String espécie )
    {
        this.espécie = espécie;
    }

    public double getPeso()
    {
        return peso;
    }

    public void setPeso( double peso )
    {
        this.peso = peso;
    }

    @Override
    public String toString()
    {
        return new StringJoiner( ", ", "[", "]" )
                .add( "espécie= " + espécie )
                .add( "peso (kg)= " + peso )
                .toString();
    }
}
