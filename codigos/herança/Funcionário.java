public class Funcionário extends Object {

    private int matrícula;
    private String nome;
    private double salárioBase;

    public Funcionário()
    {
        this( 0, "--- sem nome ---", 900 );
    }

    public Funcionário( int matrícula, String nome, double salárioBase )
    {
        setMatrícula( matrícula );
        setNome( nome );
        setSalário( salárioBase );
    }

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
        return salárioBase;
    }

    public void setSalário( double salário )
    {
        this.salárioBase = salário;
    }

    @Override
    public String toString()
    {
        return
               "matrícula=" + getMatrícula() +
               ", nome='" + getNome() + '\'' +
               ", salárioBase=" + getSalário();
    }
}
