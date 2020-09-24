package br.edu.ifpb.poo;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ReceitaFederal {
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance( new Locale( "pt", "BR" ) );

    public void processarImposto( Notebook umNotebook )
    {
        imprimirDadosImpostoTerminal( umNotebook );
    }

    public void processarImposto( List<Notebook> listaNotebooks )
    {
        for ( Notebook notebook : listaNotebooks )
            imprimirDadosImpostoTerminal( notebook );
    }

    private void imprimirDadosImpostoTerminal( Notebook notebook )
    {
        System.out.println( notebook.toString() );
        System.out.println( "O valor do imposto adicional é: " + currencyFormatter.format( notebook.imposto() ) );
    }
}
