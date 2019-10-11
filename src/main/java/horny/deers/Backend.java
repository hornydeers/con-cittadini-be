package horny.deers;

import org.eclipse.jetty.server.Server;

public class Backend implements AutoCloseable {
    private final Server _server;

    public Backend() {
        _server = new Server(9090);
        _server.setHandler(new Handler());
    }

    public Backend start() throws Exception {
        _server.start();
        return this;
    }

    public void join() throws InterruptedException {
        _server.join();
    }

    @Override
    public void close() throws Exception {
        _server.stop();
    }
}
