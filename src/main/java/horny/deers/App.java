package horny.deers;

public class App
{
    public static void main(String[] args) throws Exception
    {
        new Backend()
                .start()
                .join();
    }
}
