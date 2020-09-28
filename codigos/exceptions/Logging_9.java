package br.edu.ifpb;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Path.of;

public class Logging_9 {

    private static Logger logger; // registrará mensagens no console handler
    private static Logger loggerBanco; // poderá registrar no console handler E ainda um arquivo .xml (via file handler)

    public static void main( String[] args )
    {
        // SEM LOGGING:
        /*try
        {
            Files.lines(
               of( "uma_pasta", "um_arquivo.txt" ), UTF_8
            )
            .forEach( System.out::println );

        } catch ( IOException ioe )
        {
            // muitos problemas, erros e exceções diferentes podem acontecer
            // na execução de um programa;
            // até agora, temos ignorado ou impresso mensagens no terminal;
            // apesar de simples, não é uma boa prática---e se a mensagem
            // estivesse em uma aplicação em um servidor ou mesmo em
            // um plugin que você executa em um app móvel (celular)
            // ou uma extensão para o IntelliJ, por exemplo?
            // onde este mensagem seria impressa? E realmente seria lida?
            // possivelmente não;

            // o ideal é ter um log de erros, preferencialmente um arquivo de log
            // sem usar um framework de logging:
            System.err.print( "Erro no processamento do arquivo!" );
            // ou ainda
            ioe.printStackTrace();
        }*/


        // USANDO JAVA NATIVE LOGGING:
        // primeiro, deve-se criar uma instância do Log
        // dica: loggers são hierárquicos--o "." indica uma hierarquia de Loggers:
        //      Logging_9 é um logger, br.edu.ifpb é outro, br.edu é outro, etc.
        //      configs aplicadas a um logger de uma hierarquia "acima" afetam os loggers-filhos
        // isso é útil para definir Levels mínimos em pacotes, por exemplo;

        // Apenas os mensagens com level INFO e superiores irão por padrão ser escritas no console; 
        // ConsoleHandler interceptará mensagens e as apresentará no terminal, se Level >= INFO,
        // posto que ConsoleHandler tem como default o level INFO
        // vide abaixo:
        // The default logging configuration logs all records with the level of INFO or higher [Core]
        // esse comportamento é definido no arquivo "sua_jdk/conf/logging.properties"

        // o logger será identificado por: "br.edu.ifpb.Logging_9"
        logger = Logger.getLogger( Logging_9.class.getName() );


        // mas, veja que posso alterar o nível
        loggerBanco = Logger.getLogger( Logging_9.class.getName().concat( ".banco" ) );
        loggerBanco.setLevel( Level.ALL ); // para ficar sincronizado com FileHandler, cujo default é Level.ALL

        // importante: se eu quisesse acima que ambos log+file handler interceptassem só mensagens Level >= FINE,
        // teria de alterar o level de FileHandler via arquivo: java.util.logging.FileHandler.level = FINE
        // pois, para interceptar mensagens, o file handler sempre deve estar "sincronizado" com o Level do seu logger
        // If you set the logging level to a value finer than INFO, you also need to change the log handler configuration.
        // The default log handler suppresses messages below INFO. [Core]
        // logo, conclui-se que:
        //   + logger banco irá mostrar no console handler apenas mensagens Level >= INFO, pois console só intercepta >= INFO por default
        //   + o arquivo de log terá todas as mensagens, já que os Levels do FileHandler e logger banco estão Level.ALL

        try
        {
            Files.lines(
                    of( "uma_pasta", "um_arquivo.txt" ), UTF_8
            )
            .forEach( System.out::println );

        } catch ( IOException ioe )
        {
            // o logger irá gravar a mensagem + full stack trace (geralmente)
            // mas, há implementações que só gravam uma mensagem curta
            // o log irá por default direcionar o log para o console
            // porém, o log dá mais flexibilidade e controle, pois:
            // a) pode-se direcionar mensagens para outros meios,
            //      via handlers de arquivo, sockets, etc.
            // b) pode-se decidir qual Level de log deve ser gravado, e ignorar outros;
            //      no exemplo acima um Level mínimo INFO foi definido para logger operar
            // c) pode-se filtrar quais mensagens devem ou não ser gravadas;
            // d) pode-se controlar a formatação das mensagens;
            // e) pode-se configurar o log via arquivo externo ou programatica/te

            // para se efetuar um registro das exceções, pode-se usar dois métodos convenientes:
            // void log(Level l, String message, Throwable t)
            // void throwing(String className, String methodName, Throwable t) (mais adiante)
            logger.log( Level.WARNING, "Erro no processamento do arquivo!", ioe ); // console apenas

            // existem métodos para cada nível, recebendo uma mensagem
            // logger.info( "..." );, logger.warning( "..." );, logger.fine( "..." );...
        }

        // USANDO UM FILEHANDLER:
        // além do console handler, agora mensagens do log também serão direcionadas p/ um arquivo
        try
        {
            // se passar path relativo, o diretório será relativo a um diretório de partida
            // que pode ser obtido via System.getProperty("user.dir");
            // por padrão, o diretório de partida é sempre o diretório onde a JVM foi iniciada
            // e.g., /Users/cesar/IdeaProjects/myproject/mylog.xml
            FileHandler fileHandler = new FileHandler( "mylog.xml" ); // The default log level FileHandler is ALL
            loggerBanco.addHandler( fileHandler );

            // aparecerá no console + mylog.xml
            loggerBanco.log( Level.INFO, "Antes de executar o processamento do banco de dados..." );

            var sqlException = new SQLException( "Erro de consulta SQL" );
            throw  sqlException;

        } catch ( SQLException sqlEx )
        {
            // para se logar exceções, pode-se usar dois métodos convenientes:
            // void log(Level l, String message, Throwable t)
            // void throwing(String className, String methodName, Throwable t)

            // aparecerá no console + mylog.xml
            loggerBanco.log( Level.WARNING, "Erro no processamento do banco de dados!", sqlEx );
            // ou ...
            // The throwing call logs a record with level FINER and a message that starts with THROW [Core]
            // mensagem abaixo aparecerá só no mylog.xml (não aparecece no console handler, cujo default LEVEL é >= INFO)
            loggerBanco.throwing( Logging_9.class.getName(), "main() method", sqlEx );

        } catch ( IOException ioe )
        {
            loggerBanco.log( Level.WARNING, "Problema na criação do FileHandler object", ioe ); // console + mylog.xml
        }

        loggerBanco.log( Level.INFO, "Fim do processamento do banco de dados..." ); // console + mylog.xml
        // ou ainda: loggerBanco.info( "Fim do processamento do banco de dados..." );

        // abaixo, log não será mostrado no arquivo e nem console, por conta de level default de console handler
        // e ainda não ter FileHandler vinculado
        // importante: se definir um level abaixo de INFO, deve-se fazer o mesmo no handler,
        // pois: "The default log handler suppresses messages below INFO [Core]"
        // logger.log( Level.CONFIG, "Ooops, não será gravado no arquivo?", ioe );

        umMétodo( "abc", 55 );
    }

    static int umMétodo( String s, int a )
    {
        int i = a;
        // se quiser granularidade baixa ao registrar no log a execução de métodos,
        // pode-se usar os seguintes métodos e suas versões sobrecarregadas:
        //   a) void logp(Level l, String className, String methodName, String message)
        //
        //   b)  void entering(String className, String methodName, Object[] params)
        //       void exiting(String className, String methodName, Object result)

        outroMétodo();

        // quero registrar a entrada no método (default é FINER), logo, só aparecerá no mylog.xml
        loggerBanco.entering( Logging_9.class.getName(), "entrou em umMétodo", new Object[]{ s, a } );

        // corpo do método seria executado

        // e agora quero registrar a saída do método (default é FINER), logo, só aparecerá no mylog.xml
        loggerBanco.exiting( Logging_9.class.getName(), "saiu de umMétodo", i );

        return i;
    }

    static void outroMétodo()
    {
        // void logp(Level l, String className, String methodName, String message) e versões sobrecarregadas
        // aparecerá no console, só por conta do SEVERE, e ainda no mylog.xml
        loggerBanco.logp( Level.SEVERE, Logging_9.class.getName(), "outroMétodo()", "Entrou em outroMétodo()"  );
    }

}

// os Levels:
//    Level.SEVERE: algo (problemas/erros) muito, muito grave que todos devem ver;
//    Level.WARNING: algo importante ocorreu, provavel/te um problema/erro;
//    Level.INFO: algo interessante que todos devem ver <--- o nível usado no ex. acima faz ignorar os níveis abaixo
//    Level.CONFIG: informações de ocnfiguração, direcionada a administradores, developers, etc.
//    Level.FINE: detalhes operacionais para resolução de erros, pouco verboso, bom para debug  <---- File Handler default level
//    Level.FINER: detalhes operacionais para resolução de erros, médio verboso, bom para debug
//    Level.FINEST: detalhes operacionais para resolução de erros, muito verboso, bom para debug

//    Level.ALL;
//    Level.OFF;

// em servidores, geralmente, há, pelo menos, dois logs: um file handler que produz um
// trace log com Level ALL mensagens, e um console handler com Level >= INFO
// para exibir no terminal apenas apenas erros mais graves, enquando o arquivo guardará
// todas as mensagens possíveis

// para não codificar o Level no código e ter de recompilar quando level mudar,
// pode-se usar jvm/lib/logging.properties
// e dizer no arquivo qual será o level dos handlers e dos logs qdo sua app iniciar, exemplo:
// br.edu.ifpb.Logging_9 = FINEST
// java.util.logging.ConsoleHandler.level = FINEST


// veja o que diz a Oracle: https://docs.oracle.com/cd/E57471_01/bigData.100/data_processing_bdd/src/rdp_logging_config.html

// SEVERE   Indicates a serious failure. In general, SEVERE messages describe events that are of considerable 
//          importance and which will prevent normal program execution.
// WARNING  Indicates a potential problem. In general, WARNING messages describe events that will be of 
//          interest to end users or system managers, or which indicate potential problems.
// INFO     A message level for informational messages. The INFO level should only be used for reasonably 
//          significant messages that will make sense to end users and system administrators.
// CONFIG   A message level for static configuration messages. CONFIG messages are intended to provide a 
//          variety of static configuration information, and to assist in debugging problems that may be 
//          associated with particular configurations.
// FINE     A message level providing tracing information. All options, FINE, FINER, and FINEST, 
//          are intended for relatively detailed tracing. Of these levels, FINE should be used
//          for the lowest volume (and most important) tracing messages.
// FINER    Indicates a fairly detailed tracing message.
// FINEST   Indicates a highly detailed tracing message. FINEST should be used for the most voluminous detailed output.
// ALL      Enables logging of all messages.
