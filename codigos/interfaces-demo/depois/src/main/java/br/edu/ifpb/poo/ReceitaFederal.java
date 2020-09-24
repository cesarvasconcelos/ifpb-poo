package br.edu.ifpb.poo;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ReceitaFederal {
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance( new Locale( "pt", "BR" ) );

    public void processarImposto( Tributável itemTributável )
    {
        imprimirDadosImpostoTerminal( itemTributável );
    }

    public void processarImposto( List<Tributável> listaItensTributáveis )
    {
        for ( Tributável itemTributável : listaItensTributáveis )
            imprimirDadosImpostoTerminal( itemTributável );
    }

    private void imprimirDadosImpostoTerminal( Tributável itemTributável )
    {
        System.out.println( itemTributável.toString() );
        System.out.println( "O valor do imposto adicional é: " + currencyFormatter.format( itemTributável.imposto() ) );
    }
}
