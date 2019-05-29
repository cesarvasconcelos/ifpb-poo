import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

    private Cliente() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? "localhost" : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Mensagem_IF stub = (Mensagem_IF) registry.lookup("mensagem");
            String resposta = stub.getMensagem();
            System.out.println("resposta: " + resposta);
        } catch (Exception e) {
            System.err.println("Cliente exception: " + e.toString());
            e.printStackTrace();
        }
    } // [c:\proj-rmi$] java -classpath . Cliente 192.168.15.226
}