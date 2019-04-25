public class Aluno implements Comparable<Aluno> {
    private long cpf;
    private int matrícula;
    private String nome;

    public Aluno( long cpf, int matrícula, String nome )
    {
        this.cpf = cpf;
        this.matrícula = matrícula;
        this.nome = nome;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Aluno aluno = ( Aluno ) o;

        return getCpf() == aluno.getCpf();

    }

    @Override
    public int hashCode()
    {
        return ( int ) ( getCpf() ^ ( getCpf() >>> 32 ) );
        //return Objects.hash( getCpf() );
    }

    public long getCpf()
    {
        return cpf;
    }

    public void setCpf( long cpf )
    {
        this.cpf = cpf;
    }

    public int getMatrícula()
    {
        return matrícula;
    }

    public void setMatrícula( int matrícula )
    {
        this.matrícula = matrícula;
    }

    @Override
    public String toString()
    {
        return "\nAluno{" +
               "cpf=" + cpf +
               ", matrícula=" + matrícula +
               ", nome='" + nome + '\'' +
               '}';
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    @Override
    public int compareTo( Aluno o )
    {
        return Integer.compare( this.getMatrícula(), o.getMatrícula() );
    }
}
