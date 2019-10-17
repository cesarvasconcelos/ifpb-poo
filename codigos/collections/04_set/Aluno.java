package br.edu.ifpb;

import java.util.Comparator;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class Aluno implements Comparable<Aluno> {
    private int    matrícula;
    private String nome;
    private double notaFinal;

    public static final Comparator<Aluno> COMPARATOR_POR_NOTA = Comparator.comparingDouble( Aluno::getNotaFinal );

    public Aluno( int matrícula, String nome, double notaFinal )
    {
        this.setMatrícula( matrícula );
        this.setNome( nome );
        this.setNotaFinal( notaFinal );
    }

    public int getMatrícula()
    {
        return matrícula;
    }

    public void setMatrícula( int matrícula )
    {
        this.matrícula = matrícula > 0 ? matrícula : this.matrícula;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome =
            Optional.ofNullable( nome ) // não pode ser nula
                    .map( String::strip ) // remove leading spaces no início e final
                    .filter( Predicate.not( String::isBlank ) ) // auto explicativo
                    .filter( Predicate.not( String::isEmpty ) ) // auto explicativo
                    .orElse( "--- sem nome ---" ); // ou então um default é usado
    }

    public double getNotaFinal()
    {
        return notaFinal;
    }

    public void setNotaFinal( double notaFinal )
    {
        this.notaFinal = notaFinal >= 0 ? notaFinal : this.notaFinal;
    }


    @Override
    public int compareTo( Aluno outro )
    {
        return Integer.compare( this.matrícula, outro.matrícula );
    }

    @Override
    public String toString()
    {
        return new StringJoiner( ", ", Aluno.class.getSimpleName() + "[", "]" )
                .add( "matrícula=" + matrícula )
                .add( "nome='" + nome + "'" )
                .add( "nota=" + notaFinal )
                .toString();
    }
}
