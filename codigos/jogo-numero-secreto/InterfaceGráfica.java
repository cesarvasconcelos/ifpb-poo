package br.edu.ifpb.poo;

import javax.swing.JOptionPane;

public class InterfaceGráfica {
    public static void main( String[] args )
    {
        int palpite;
        boolean acertou;

        JogoNúmeroSecreto jogo = new JogoNúmeroSecreto( 3 );
        JOptionPane.showMessageDialog( null, jogo.ajuda() );
        do
        {
            palpite =
                lerEntrada( "\nTentativa(s) restante(s): "
                    .concat( Integer.toString( jogo.getTentativas() )
                    .concat( "\nDigite o seu palpite (1..6): " ) )
                )
            ;
            acertou = jogo.efetuarJogada( palpite );
        } while ( !acertou && jogo.getTentativas() != 0 );

        if ( !acertou )
            JOptionPane.showMessageDialog(
                    null,
                    String.format( "\nQue pena. O número secreto era o %d.",
                                   jogo.getNúmeroSecreto() ),
                    "Resultado",
                    JOptionPane.ERROR_MESSAGE
            );
        else JOptionPane.showMessageDialog( null,
                                            "Você acertou! Parabéns!" );
    }

    public static int lerEntrada( String frase )
    {
        String string = JOptionPane.showInputDialog( frase );
        return Integer.parseInt( string );
    }
}