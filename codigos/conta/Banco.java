package br.com.banco;

import java.util.ArrayList;

public class Banco {
    private ArrayList<Conta> listaContas = null;

    // construtor
    public Banco()
    {
        listaContas = new ArrayList<>();
    }

    public boolean cadastrarConta( int número,
                                   String titular,
                                   double saldoInicial ){
        if( buscarConta( número ) ) return false;
        return listaContas.add( new Conta( número, titular, saldoInicial ) );
    }

    public int quantidadeContas()
    {
        return this.listaContas.size();
    }

    public boolean buscarConta( int umNúmero )
    {
        if( quantidadeContas() > 0 )
            for( Conta cadaConta : listaContas )
                if( cadaConta.getNúmero() == umNúmero ) return true;

        return false;
    }
}
