package br.edu.ifpb.poo;

import java.util.List;

public class TestarApp
{
    public static void main( String[] args )
    {
        Caneta canetaPilot = new Caneta(
                123,
                "Caneta Esferográfica Pilot BP-S N23",
                1.9, "preta"
        );

        Notebook notebookAcer = new Notebook(
                456, "Notebook Acer Aspire A315-54-54B1 Intel Core i5 8GB 1TB Tela 15.6 pol",
                3371, 2
        );

        Notebook notebookDell = new Notebook(
                789, "Notebook Dell Inspiron I15-3584-D30P Intel Core i3 16GB 1TB 17.3 pol",
                5887.9, 4
        );

        ReceitaFederal receita = new ReceitaFederal();

        // receita.processarImposto( canetaPilot );
        receita.processarImposto( notebookAcer );
        receita.processarImposto( List.of( notebookAcer, notebookDell ) );
    }
}
