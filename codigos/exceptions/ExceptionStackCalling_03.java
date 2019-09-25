public class ExceptionStackCalling_03 {

    public static void main( String[] args )
    {
        métodoInicial();
    }

    public static void métodoInicial()
    {
        métodoGerarExceção();
    }

    public static void métodoGerarExceção()
    {
        int[] array = { 10, 20 };
        array[ 1000 ] = 30;
        // note a exceção "bubbling up" até o main()
        // Stack trace:
        // at ExceptionStackCalling.métodoGerarExceção(ExceptionStackCalling.java:16)
        // at ExceptionStackCalling.métodoInicial(ExceptionStackCalling.java:10)
        // at ExceptionStackCalling.main(ExceptionStackCalling.java:5)
    }
}

// se uma exceção não for tratada no local onde ocorreu,
// será repassada para quem chamou o método

// se este método também não possui um try-catch
// para tratar a exceção, será repassada para quem
// chamou o método; e assim por diante até ...

// se a exceção atingir o main() e não for tratada,
// o programa será encerrado abruptamente.