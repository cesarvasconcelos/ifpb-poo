package br.edu.ifpb.poo;

import java.util.Random;

public class JogoNúmeroSecreto {
    public static final int LIMITE_INICIAL     = 1;
    public static final int LIMITE_FINAL       = 6;
    public static final int DEFAULT_TENTATIVAS = 3;

    private int númeroSecreto;
    private int tentativas;

    public JogoNúmeroSecreto( int tentativas )
    {
        this.tentativas = ( tentativas >= LIMITE_INICIAL &&
                            tentativas <= LIMITE_FINAL ) ? tentativas : DEFAULT_TENTATIVAS;
        this.gerarNúmeroSecreto();
    }
    
    private void gerarNúmeroSecreto()
    {
        // número secreto entre 1 e 6
        this.númeroSecreto = LIMITE_INICIAL + ( int ) ( new Random().nextDouble() * LIMITE_FINAL );
    }

    public int getNúmeroSecreto()
    {
        return númeroSecreto;
    }

    public int getTentativas()
    {
        return tentativas;
    }

    private boolean éNúmeroSecreto( int número )
    {
        if ( número == this.getNúmeroSecreto() ) return true;

        this.tentativas--;
        return false;
    }

    public String ajuda()
    {
        StringBuilder mensagem = new StringBuilder();

        // uma String contendo o título de abertura do jogo
        mensagem.append(
                String.format( "%n%1$c :: Jogo do Número Secreto :: %1$c%n", '\uFFFD' ) );

        // gerar uma linha se separação
        mensagem.append( "-".repeat( 8 ) );

        mensagem.append( "\nAjuda: o objetivo do jogo é tentar\n" )
                .append( "adivinhar o número secreto dentro de " )
                .append( "\num número máximo de tentativas." )
                .append( "\nDivirta-se e boa sorte!" );

        return mensagem.toString();
    }

    private void encerrarJogo()
    {
        this.tentativas = 0;
    }

    public boolean efetuarJogada( int palpite )
    {
        if ( !this.éNúmeroSecreto( palpite ) ) return false;

        // se palpite estiver certo, deve-se encerrar o jogo
        this.encerrarJogo();
        return true;
    }
}