package io.grpc.android;

import android.net.LocalSocketAddress;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;

class UdsSocketFactory extends SocketFactory {
    private final LocalSocketAddress localSocketAddress;

    public UdsSocketFactory(String str, LocalSocketAddress.Namespace namespace) {
        this.localSocketAddress = new LocalSocketAddress(str, namespace);
    }

    public Socket createSocket() throws IOException {
        return create();
    }

    public Socket createSocket(String str, int i) throws IOException {
        return createAndConnect();
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return createAndConnect();
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return createAndConnect();
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return createAndConnect();
    }

    private Socket create() {
        return new UdsSocket(this.localSocketAddress);
    }

    private Socket createAndConnect() throws IOException {
        Socket create = create();
        create.connect(new InetSocketAddress(0));
        return create;
    }
}
