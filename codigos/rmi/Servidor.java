import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

        
public class Servidor implements Mensagem_IF {
        
    public Servidor() {}

    public String getMensagem() {
        return "Mensagem do servidor!";
    }
        
    public static void main(String args[]) {
        
        try {
            Servidor obj = new Servidor();
            Mensagem_IF stub = (Mensagem_IF) UnicastRemoteObject.exportObject(obj, 0);

            // cadastrar o stub no registry
            //System.setProperty("java.rmi.server.hostname","192.168.15.226");
            Registry registry = LocateRegistry.createRegistry(1099); // default
            registry.bind("mensagem", stub);

            System.err.println("Servidor pronto...");
        } catch (Exception e) {
            System.err.println("Servidor exception: " + e.toString());
            e.printStackTrace();
        }
    } 
    // compilar: [proj-rmi] javac -d bin $(find src -iname "*.java")
    // servidor: [proj-rmi] java -classpath bin -Djava.rmi.server.hostname=192.168.15.226 -Djava.rmi.server.codebase=file:bin/ Servidor
}
