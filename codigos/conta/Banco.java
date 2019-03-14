package br.com.bb;

import java.util.LinkedList;
import java.util.List;

public class Banco {
    private List<Conta> listaContas = null;

    // construtor
    Banco()
    {
        listaContas = new LinkedList<>();
    }

    public boolean cadastrarConta( int número,
                                   String titular,
                                   double saldoInicial ){

        // verificar se conta existe
        for( Conta c : listaContas ) // for-each
            if( c.getNúmero() == número )
                return false; // já existe!

        //for( int i = 0; i < listaContas.size(); i++)
        //    if( listaContas.get( i ).getNúmero() == número )
        //        return false; // já existe!

        // senão existir, devo criar a conta, cadastrar na lista
        // e retornar true
        Conta temp = new Conta( número, titular, saldoInicial );
        return listaContas.add( temp );

    }

    public int quantidadeContas()
    {
        return listaContas.size();
    }

    public boolean buscarConta( int número )
    {
        return false; // falta implementar com Test-driven development
    }
}
