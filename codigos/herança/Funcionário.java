package br.edu.ifpb;

public class Funcionário extends Object {
    // atributos
    private int    matrícula;
    private String nome;
    private double salário;

    // construtores sobrecarregados
    public Funcionário()
    {
        // definir valores-padrão para cada atributo
        this( 0, "-- sem nome --", 890.50 );
    }

    public Funcionário( int matrícula, String nome, double salário )
    {
        // sabe como inicializar cada atributo mediante argumentos
        super(); // Object....
        this.setMatrícula( matrícula );
        this.setNome( nome );
        this.setSalário( salário );
    }

    // métodos (interface pública/faz?)
    public int getMatrícula()
    {
        return matrícula;
    }

    public void setMatrícula( int matrícula )
    {
        this.matrícula = matrícula;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public double getSalário()
    {
        return salário;
    }

    public void setSalário( double salário )
    {
        this.salário = salário;
    }

    @Override
    public String toString()
    {
        return "Matrícula: " +
               this.getMatrícula() +
               " Nome: " +
               this.getNome() +
               " Salário (R$):" +
               this.getSalário();
    }
} // classe
