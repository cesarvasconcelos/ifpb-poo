public class Professor extends Funcionário {
    private int numHorasDisciplinas;

    // default
    public Professor()
    {
        super();
        this.setNumHorasDisciplinas( 2 );
    }
    // com parâmetros
    public Professor( int matrícula, String nome, double salárioBase, int númeroHoras )
    {
        super( matrícula, nome, salárioBase );
        this.setNumHorasDisciplinas( númeroHoras );
    }

    public int getNumHorasDisciplinas()
    {
        return numHorasDisciplinas;
    }

    public void setNumHorasDisciplinas( int numHorasDisciplinas )
    {
        this.numHorasDisciplinas = numHorasDisciplinas;
    }

    @Override
    public double getSalário()
    {
        return super.getSalário() + 2 * getNumHorasDisciplinas();
    }

    @Override
    public String toString()
    {
        return super.toString() + ", Horas: " + getNumHorasDisciplinas();
    }
}