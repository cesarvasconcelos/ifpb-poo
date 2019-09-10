package br.edu.ifpb;

public class Professor extends Funcionário{
    private int númHorasDisciplinas;

    public Professor()
    {
        super();
        this.setNúmHorasDisciplinas( 8 );
    }

    public Professor( int matrícula,
                      String nome,
                      double salário,
                      int numHoras )
    {
        super( matrícula, nome, salário );
        this.setNúmHorasDisciplinas( numHoras );
    }

    public int getNúmHorasDisciplinas()
    {
        return númHorasDisciplinas;
    }

    public void setNúmHorasDisciplinas( int númHorasDisciplinas )
    {
        this.númHorasDisciplinas = númHorasDisciplinas;
    }

    @Override
    public String toString()
    {
        return super.toString() +
               " NúmHoras: " + this.númHorasDisciplinas +
               " Salário Base (R$): " + super.getSalário();
    }

    // @Override
    public double getSalário()
    {
        return super.getSalário() +
               2 * númHorasDisciplinas;
    }
} // classe Professor

