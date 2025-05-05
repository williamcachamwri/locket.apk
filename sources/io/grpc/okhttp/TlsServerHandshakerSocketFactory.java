package io.grpc.okhttp;

import io.grpc.Attributes;
import io.grpc.Grpc;
import io.grpc.InternalChannelz;
import io.grpc.SecurityLevel;
import io.grpc.internal.GrpcAttributes;
import io.grpc.okhttp.HandshakerSocketFactory;
import io.grpc.okhttp.SslSocketFactoryServerCredentials;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Protocol;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

final class TlsServerHandshakerSocketFactory implements HandshakerSocketFactory {
    private final ConnectionSpec connectionSpec;
    private final PlaintextHandshakerSocketFactory delegate = new PlaintextHandshakerSocketFactory();
    private final SSLSocketFactory socketFactory;

    public TlsServerHandshakerSocketFactory(SslSocketFactoryServerCredentials.ServerCredentials serverCredentials) {
        this.socketFactory = serverCredentials.getFactory();
        this.connectionSpec = serverCredentials.getConnectionSpec();
    }

    public HandshakerSocketFactory.HandshakeResult handshake(Socket socket, Attributes attributes) throws IOException {
        List list;
        HandshakerSocketFactory.HandshakeResult handshake = this.delegate.handshake(socket, attributes);
        Socket createSocket = this.socketFactory.createSocket(handshake.socket, (String) null, -1, true);
        if (createSocket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            sSLSocket.setUseClientMode(false);
            this.connectionSpec.apply(sSLSocket, false);
            Protocol protocol = Protocol.HTTP_2;
            OkHttpProtocolNegotiator okHttpProtocolNegotiator = OkHttpProtocolNegotiator.get();
            if (this.connectionSpec.supportsTlsExtensions()) {
                list = Arrays.asList(new Protocol[]{protocol});
            } else {
                list = null;
            }
            String negotiate = okHttpProtocolNegotiator.negotiate(sSLSocket, (String) null, list);
            if (protocol.toString().equals(negotiate)) {
                return new HandshakerSocketFactory.HandshakeResult(createSocket, handshake.attributes.toBuilder().set(GrpcAttributes.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).set(Grpc.TRANSPORT_ATTR_SSL_SESSION, sSLSocket.getSession()).build(), new InternalChannelz.Security(new InternalChannelz.Tls(sSLSocket.getSession())));
            }
            throw new IOException("Expected NPN/ALPN " + protocol + ": " + negotiate);
        }
        throw new IOException("SocketFactory " + this.socketFactory + " did not produce an SSLSocket: " + createSocket.getClass());
    }
}
