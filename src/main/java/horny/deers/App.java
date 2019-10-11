package horny.deers;

import org.eclipse.jetty.server.Server;

public class App
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(9090);
        server.setHandler(new HelloWorld());

        server.start();
        server.join();
    }
}
