package br.edu.ifpb;

public class Gerente extends Funcionário{
    private double comissão;

    public Gerente( int matrícula,
                    String nome,
                    double salário,
                    double comissão ) {

        super( matrícula, nome, salário );
        this.setComissão( comissão );
    }

    public double getComissão()
    {
        return comissão;
    }

    public void setComissão( double comissão )
    {
        this.comissão = comissão;
    }

    @Override
    public double getSalário()
    {
        return super.getSalário() + comissão;
    }

    @Override
    public String toString()
    {
        return super.toString() + " Comissão(R$): " + comissão +
               " Salário Base(R$): " + super.getSalário();
    }
}
