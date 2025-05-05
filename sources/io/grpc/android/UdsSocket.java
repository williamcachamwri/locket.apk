package io.grpc.android;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

class UdsSocket extends Socket {
    private boolean closed = false;
    private boolean inputShutdown = false;
    private final LocalSocket localSocket;
    private final LocalSocketAddress localSocketAddress;
    private boolean outputShutdown = false;

    public void bind(SocketAddress socketAddress) {
    }

    public int getSoLinger() {
        return -1;
    }

    public boolean getTcpNoDelay() {
        return true;
    }

    public void setTcpNoDelay(boolean z) {
    }

    public UdsSocket(LocalSocketAddress localSocketAddress2) {
        this.localSocketAddress = localSocketAddress2;
        this.localSocket = new LocalSocket();
    }

    public synchronized void close() throws IOException {
        if (!this.closed) {
            if (!this.inputShutdown) {
                shutdownInput();
            }
            if (!this.outputShutdown) {
                shutdownOutput();
            }
            this.localSocket.close();
            this.closed = true;
        }
    }

    public void connect(SocketAddress socketAddress) throws IOException {
        this.localSocket.connect(this.localSocketAddress);
    }

    public void connect(SocketAddress socketAddress, int i) throws IOException {
        this.localSocket.connect(this.localSocketAddress, i);
    }

    public SocketChannel getChannel() {
        throw new UnsupportedOperationException("getChannel() not supported");
    }

    public InetAddress getInetAddress() {
        throw new UnsupportedOperationException("getInetAddress() not supported");
    }

    public InputStream getInputStream() throws IOException {
        return new FilterInputStream(this.localSocket.getInputStream()) {
            public void close() throws IOException {
                UdsSocket.this.close();
            }
        };
    }

    public boolean getKeepAlive() {
        throw new UnsupportedOperationException("Unsupported operation getKeepAlive()");
    }

    public InetAddress getLocalAddress() {
        throw new UnsupportedOperationException("Unsupported operation getLocalAddress()");
    }

    public int getLocalPort() {
        throw new UnsupportedOperationException("Unsupported operation getLocalPort()");
    }

    public SocketAddress getLocalSocketAddress() {
        return new SocketAddress() {
        };
    }

    public boolean getOOBInline() {
        throw new UnsupportedOperationException("Unsupported operation getOOBInline()");
    }

    public OutputStream getOutputStream() throws IOException {
        return new FilterOutputStream(this.localSocket.getOutputStream()) {
            public void close() throws IOException {
                UdsSocket.this.close();
            }
        };
    }

    public int getPort() {
        throw new UnsupportedOperationException("Unsupported operation getPort()");
    }

    public int getReceiveBufferSize() throws SocketException {
        try {
            return this.localSocket.getReceiveBufferSize();
        } catch (IOException e) {
            throw toSocketException(e);
        }
    }

    public SocketAddress getRemoteSocketAddress() {
        return new SocketAddress() {
        };
    }

    public boolean getReuseAddress() {
        throw new UnsupportedOperationException("Unsupported operation getReuseAddress()");
    }

    public int getSendBufferSize() throws SocketException {
        try {
            return this.localSocket.getSendBufferSize();
        } catch (IOException e) {
            throw toSocketException(e);
        }
    }

    public int getSoTimeout() throws SocketException {
        try {
            return this.localSocket.getSoTimeout();
        } catch (IOException e) {
            throw toSocketException(e);
        }
    }

    public int getTrafficClass() {
        throw new UnsupportedOperationException("Unsupported operation getTrafficClass()");
    }

    public boolean isBound() {
        return this.localSocket.isBound();
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    public boolean isConnected() {
        return this.localSocket.isConnected();
    }

    public synchronized boolean isInputShutdown() {
        return this.inputShutdown;
    }

    public synchronized boolean isOutputShutdown() {
        return this.outputShutdown;
    }

    public void sendUrgentData(int i) {
        throw new UnsupportedOperationException("Unsupported operation sendUrgentData()");
    }

    public void setKeepAlive(boolean z) {
        throw new UnsupportedOperationException("Unsupported operation setKeepAlive()");
    }

    public void setOOBInline(boolean z) {
        throw new UnsupportedOperationException("Unsupported operation setOOBInline()");
    }

    public void setPerformancePreferences(int i, int i2, int i3) {
        throw new UnsupportedOperationException("Unsupported operation setPerformancePreferences()");
    }

    public void setReceiveBufferSize(int i) throws SocketException {
        try {
            this.localSocket.setReceiveBufferSize(i);
        } catch (IOException e) {
            throw toSocketException(e);
        }
    }

    public void setReuseAddress(boolean z) {
        throw new UnsupportedOperationException("Unsupported operation setReuseAddress()");
    }

    public void setSendBufferSize(int i) throws SocketException {
        try {
            this.localSocket.setSendBufferSize(i);
        } catch (IOException e) {
            throw toSocketException(e);
        }
    }

    public void setSoLinger(boolean z, int i) {
        throw new UnsupportedOperationException("Unsupported operation setSoLinger()");
    }

    public void setSoTimeout(int i) throws SocketException {
        try {
            this.localSocket.setSoTimeout(i);
        } catch (IOException e) {
            throw toSocketException(e);
        }
    }

    public void setTrafficClass(int i) {
        throw new UnsupportedOperationException("Unsupported operation setTrafficClass()");
    }

    public synchronized void shutdownInput() throws IOException {
        this.localSocket.shutdownInput();
        this.inputShutdown = true;
    }

    public synchronized void shutdownOutput() throws IOException {
        this.localSocket.shutdownOutput();
        this.outputShutdown = true;
    }

    private static SocketException toSocketException(Throwable th) {
        SocketException socketException = new SocketException();
        socketException.initCause(th);
        return socketException;
    }
}
