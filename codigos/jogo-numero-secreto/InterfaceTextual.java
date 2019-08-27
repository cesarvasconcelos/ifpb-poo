package br.edu.ifpb.poo;

import java.util.Scanner;

public class InterfaceTextual {
    public static void main( String[] args )
    {
        int palpite;
        boolean acertou;

        JogoNúmeroSecreto jogo = new JogoNúmeroSecreto( 3 );
        System.out.println( jogo.ajuda() );
        do
        {
            palpite =
                lerEntrada( "\nTentativa(s) restante(s): "
                    .concat( Integer.toString( jogo.getTentativas() )
                    .concat( "\nDigite o seu palpite (1..6): " ) )
                )
            ;
            acertou = jogo.efetuarJogada( palpite );
        } while ( !acertou && jogo.getTentativas() != 0  );

        if( !acertou ) System.out.printf( "\nQue pena. O número secreto era o %d.\n", jogo.getNúmeroSecreto() );
        else System.out.println( "Você acertou! Parabéns!" );
    }

    public static int lerEntrada( String frase  )
    {
        Scanner scanner = new Scanner( System.in );
        System.out.print( frase );
        return scanner.nextInt();
    }
}
