package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.ChoiceServerCredentials;
import io.grpc.ForwardingServerBuilder;
import io.grpc.InsecureServerCredentials;
import io.grpc.ServerBuilder;
import io.grpc.ServerCredentials;
import io.grpc.ServerStreamTracer;
import io.grpc.TlsServerCredentials;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InternalServer;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerImplBuilder;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.SslSocketFactoryServerCredentials;
import io.grpc.okhttp.internal.Platform;
import io.sentry.SentryLockReason;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public final class OkHttpServerBuilder extends ForwardingServerBuilder<OkHttpServerBuilder> {
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    private static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    private static final ObjectPool<Executor> DEFAULT_TRANSPORT_EXECUTOR_POOL = OkHttpChannelBuilder.DEFAULT_TRANSPORT_EXECUTOR_POOL;
    static final long MAX_CONNECTION_AGE_GRACE_NANOS_INFINITE = Long.MAX_VALUE;
    static final long MAX_CONNECTION_AGE_NANOS_DISABLED = Long.MAX_VALUE;
    static final long MAX_CONNECTION_IDLE_NANOS_DISABLED = Long.MAX_VALUE;
    private static final long MIN_MAX_CONNECTION_AGE_NANO = TimeUnit.SECONDS.toNanos(1);
    private static final long MIN_MAX_CONNECTION_IDLE_NANO = TimeUnit.SECONDS.toNanos(1);
    private static final Logger log = Logger.getLogger(OkHttpServerBuilder.class.getName());
    private static final EnumSet<TlsServerCredentials.Feature> understoodTlsFeatures = EnumSet.of(TlsServerCredentials.Feature.MTLS, TlsServerCredentials.Feature.CUSTOM_MANAGERS);
    int flowControlWindow = 65535;
    final HandshakerSocketFactory handshakerSocketFactory;
    long keepAliveTimeNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIME_NANOS;
    long keepAliveTimeoutNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS;
    final SocketAddress listenAddress;
    long maxConnectionAgeGraceInNanos = Long.MAX_VALUE;
    long maxConnectionAgeInNanos = Long.MAX_VALUE;
    long maxConnectionIdleInNanos = Long.MAX_VALUE;
    int maxInboundMessageSize = 4194304;
    int maxInboundMetadataSize = 8192;
    long permitKeepAliveTimeInNanos = TimeUnit.MINUTES.toNanos(5);
    boolean permitKeepAliveWithoutCalls;
    ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
    final ServerImplBuilder serverImplBuilder = new ServerImplBuilder(new OkHttpServerBuilder$$ExternalSyntheticLambda0(this));
    ServerSocketFactory socketFactory = ServerSocketFactory.getDefault();
    ObjectPool<Executor> transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
    TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();

    @Deprecated
    public static OkHttpServerBuilder forPort(int i) {
        throw new UnsupportedOperationException("Use forPort(int, ServerCredentials) instead");
    }

    public static OkHttpServerBuilder forPort(int i, ServerCredentials serverCredentials) {
        return forPort((SocketAddress) new InetSocketAddress(i), serverCredentials);
    }

    public static OkHttpServerBuilder forPort(SocketAddress socketAddress, ServerCredentials serverCredentials) {
        HandshakerSocketFactoryResult handshakerSocketFactoryFrom = handshakerSocketFactoryFrom(serverCredentials);
        if (handshakerSocketFactoryFrom.error == null) {
            return new OkHttpServerBuilder(socketAddress, handshakerSocketFactoryFrom.factory);
        }
        throw new IllegalArgumentException(handshakerSocketFactoryFrom.error);
    }

    OkHttpServerBuilder(SocketAddress socketAddress, HandshakerSocketFactory handshakerSocketFactory2) {
        this.listenAddress = (SocketAddress) Preconditions.checkNotNull(socketAddress, SentryLockReason.JsonKeys.ADDRESS);
        this.handshakerSocketFactory = (HandshakerSocketFactory) Preconditions.checkNotNull(handshakerSocketFactory2, "handshakerSocketFactory");
    }

    /* access modifiers changed from: protected */
    public ServerBuilder<?> delegate() {
        return this.serverImplBuilder;
    }

    /* access modifiers changed from: package-private */
    public OkHttpServerBuilder setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return this;
    }

    public OkHttpServerBuilder transportExecutor(Executor executor) {
        if (executor == null) {
            this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        } else {
            this.transportExecutorPool = new FixedObjectPool(executor);
        }
        return this;
    }

    public OkHttpServerBuilder socketFactory(ServerSocketFactory serverSocketFactory) {
        if (serverSocketFactory == null) {
            this.socketFactory = ServerSocketFactory.getDefault();
        } else {
            this.socketFactory = serverSocketFactory;
        }
        return this;
    }

    public OkHttpServerBuilder keepAliveTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive time must be positive");
        long nanos = timeUnit.toNanos(j);
        this.keepAliveTimeNanos = nanos;
        long clampKeepAliveTimeInNanos = KeepAliveManager.clampKeepAliveTimeInNanos(nanos);
        this.keepAliveTimeNanos = clampKeepAliveTimeInNanos;
        if (clampKeepAliveTimeInNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeNanos = Long.MAX_VALUE;
        }
        return this;
    }

    public OkHttpServerBuilder maxConnectionIdle(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "max connection idle must be positive: %s", j);
        long nanos = timeUnit.toNanos(j);
        this.maxConnectionIdleInNanos = nanos;
        if (nanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionIdleInNanos = Long.MAX_VALUE;
        }
        long j2 = this.maxConnectionIdleInNanos;
        long j3 = MIN_MAX_CONNECTION_IDLE_NANO;
        if (j2 < j3) {
            this.maxConnectionIdleInNanos = j3;
        }
        return this;
    }

    public OkHttpServerBuilder maxConnectionAge(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "max connection age must be positive: %s", j);
        long nanos = timeUnit.toNanos(j);
        this.maxConnectionAgeInNanos = nanos;
        if (nanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeInNanos = Long.MAX_VALUE;
        }
        long j2 = this.maxConnectionAgeInNanos;
        long j3 = MIN_MAX_CONNECTION_AGE_NANO;
        if (j2 < j3) {
            this.maxConnectionAgeInNanos = j3;
        }
        return this;
    }

    public OkHttpServerBuilder maxConnectionAgeGrace(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "max connection age grace must be non-negative: %s", j);
        long nanos = timeUnit.toNanos(j);
        this.maxConnectionAgeGraceInNanos = nanos;
        if (nanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeGraceInNanos = Long.MAX_VALUE;
        }
        return this;
    }

    public OkHttpServerBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive timeout must be positive");
        long nanos = timeUnit.toNanos(j);
        this.keepAliveTimeoutNanos = nanos;
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(nanos);
        return this;
    }

    public OkHttpServerBuilder permitKeepAliveTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "permit keepalive time must be non-negative: %s", j);
        this.permitKeepAliveTimeInNanos = timeUnit.toNanos(j);
        return this;
    }

    public OkHttpServerBuilder permitKeepAliveWithoutCalls(boolean z) {
        this.permitKeepAliveWithoutCalls = z;
        return this;
    }

    public OkHttpServerBuilder flowControlWindow(int i) {
        Preconditions.checkState(i > 0, "flowControlWindow must be positive");
        this.flowControlWindow = i;
        return this;
    }

    public OkHttpServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorServicePool = new FixedObjectPool((ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    public OkHttpServerBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    public OkHttpServerBuilder maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "negative max bytes");
        this.maxInboundMessageSize = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.serverImplBuilder.setStatsEnabled(z);
    }

    /* access modifiers changed from: package-private */
    public InternalServer buildTransportServers(List<? extends ServerStreamTracer.Factory> list) {
        return new OkHttpServer(this, list, this.serverImplBuilder.getChannelz());
    }

    static HandshakerSocketFactoryResult handshakerSocketFactoryFrom(ServerCredentials serverCredentials) {
        KeyManager[] keyManagerArr;
        TrustManager[] trustManagerArr;
        ClientCertRequestingSocketFactory clientCertRequestingSocketFactory;
        if (serverCredentials instanceof TlsServerCredentials) {
            TlsServerCredentials tlsServerCredentials = (TlsServerCredentials) serverCredentials;
            Set<TlsServerCredentials.Feature> incomprehensible = tlsServerCredentials.incomprehensible(understoodTlsFeatures);
            if (!incomprehensible.isEmpty()) {
                return HandshakerSocketFactoryResult.error("TLS features not understood: " + incomprehensible);
            }
            if (tlsServerCredentials.getKeyManagers() != null) {
                keyManagerArr = (KeyManager[]) tlsServerCredentials.getKeyManagers().toArray(new KeyManager[0]);
            } else if (tlsServerCredentials.getPrivateKey() == null) {
                keyManagerArr = null;
            } else if (tlsServerCredentials.getPrivateKeyPassword() != null) {
                return HandshakerSocketFactoryResult.error("byte[]-based private key with password unsupported. Use unencrypted file or KeyManager");
            } else {
                try {
                    keyManagerArr = OkHttpChannelBuilder.createKeyManager(tlsServerCredentials.getCertificateChain(), tlsServerCredentials.getPrivateKey());
                } catch (GeneralSecurityException e) {
                    log.log(Level.FINE, "Exception loading private key from credential", e);
                    return HandshakerSocketFactoryResult.error("Unable to load private key: " + e.getMessage());
                }
            }
            if (tlsServerCredentials.getTrustManagers() != null) {
                trustManagerArr = (TrustManager[]) tlsServerCredentials.getTrustManagers().toArray(new TrustManager[0]);
            } else if (tlsServerCredentials.getRootCertificates() != null) {
                try {
                    trustManagerArr = OkHttpChannelBuilder.createTrustManager(tlsServerCredentials.getRootCertificates());
                } catch (GeneralSecurityException e2) {
                    log.log(Level.FINE, "Exception loading root certificates from credential", e2);
                    return HandshakerSocketFactoryResult.error("Unable to load root certificates: " + e2.getMessage());
                }
            } else {
                trustManagerArr = null;
            }
            try {
                SSLContext instance = SSLContext.getInstance("TLS", Platform.get().getProvider());
                instance.init(keyManagerArr, trustManagerArr, (SecureRandom) null);
                SSLSocketFactory socketFactory2 = instance.getSocketFactory();
                int i = AnonymousClass1.$SwitchMap$io$grpc$TlsServerCredentials$ClientAuth[tlsServerCredentials.getClientAuth().ordinal()];
                if (i == 1) {
                    clientCertRequestingSocketFactory = new ClientCertRequestingSocketFactory(socketFactory2, false);
                } else if (i != 2) {
                    if (i != 3) {
                        return HandshakerSocketFactoryResult.error("Unknown TlsServerCredentials.ClientAuth value: " + tlsServerCredentials.getClientAuth());
                    }
                    return HandshakerSocketFactoryResult.factory(new TlsServerHandshakerSocketFactory(new SslSocketFactoryServerCredentials.ServerCredentials(socketFactory2)));
                } else {
                    clientCertRequestingSocketFactory = new ClientCertRequestingSocketFactory(socketFactory2, true);
                }
                socketFactory2 = clientCertRequestingSocketFactory;
                return HandshakerSocketFactoryResult.factory(new TlsServerHandshakerSocketFactory(new SslSocketFactoryServerCredentials.ServerCredentials(socketFactory2)));
            } catch (GeneralSecurityException e3) {
                throw new RuntimeException("TLS Provider failure", e3);
            }
        } else if (serverCredentials instanceof InsecureServerCredentials) {
            return HandshakerSocketFactoryResult.factory(new PlaintextHandshakerSocketFactory());
        } else {
            if (serverCredentials instanceof SslSocketFactoryServerCredentials.ServerCredentials) {
                return HandshakerSocketFactoryResult.factory(new TlsServerHandshakerSocketFactory((SslSocketFactoryServerCredentials.ServerCredentials) serverCredentials));
            }
            if (!(serverCredentials instanceof ChoiceServerCredentials)) {
                return HandshakerSocketFactoryResult.error("Unsupported credential type: " + serverCredentials.getClass().getName());
            }
            StringBuilder sb = new StringBuilder();
            for (ServerCredentials handshakerSocketFactoryFrom : ((ChoiceServerCredentials) serverCredentials).getCredentialsList()) {
                HandshakerSocketFactoryResult handshakerSocketFactoryFrom2 = handshakerSocketFactoryFrom(handshakerSocketFactoryFrom);
                if (handshakerSocketFactoryFrom2.error == null) {
                    return handshakerSocketFactoryFrom2;
                }
                sb.append(", ");
                sb.append(handshakerSocketFactoryFrom2.error);
            }
            return HandshakerSocketFactoryResult.error(sb.substring(2));
        }
    }

    /* renamed from: io.grpc.okhttp.OkHttpServerBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$TlsServerCredentials$ClientAuth;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                io.grpc.TlsServerCredentials$ClientAuth[] r0 = io.grpc.TlsServerCredentials.ClientAuth.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$TlsServerCredentials$ClientAuth = r0
                io.grpc.TlsServerCredentials$ClientAuth r1 = io.grpc.TlsServerCredentials.ClientAuth.OPTIONAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$grpc$TlsServerCredentials$ClientAuth     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.TlsServerCredentials$ClientAuth r1 = io.grpc.TlsServerCredentials.ClientAuth.REQUIRE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$grpc$TlsServerCredentials$ClientAuth     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.grpc.TlsServerCredentials$ClientAuth r1 = io.grpc.TlsServerCredentials.ClientAuth.NONE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpServerBuilder.AnonymousClass1.<clinit>():void");
        }
    }

    static final class HandshakerSocketFactoryResult {
        public final String error;
        public final HandshakerSocketFactory factory;

        private HandshakerSocketFactoryResult(HandshakerSocketFactory handshakerSocketFactory, String str) {
            this.factory = handshakerSocketFactory;
            this.error = str;
        }

        public static HandshakerSocketFactoryResult error(String str) {
            return new HandshakerSocketFactoryResult((HandshakerSocketFactory) null, (String) Preconditions.checkNotNull(str, "error"));
        }

        public static HandshakerSocketFactoryResult factory(HandshakerSocketFactory handshakerSocketFactory) {
            return new HandshakerSocketFactoryResult((HandshakerSocketFactory) Preconditions.checkNotNull(handshakerSocketFactory, "factory"), (String) null);
        }
    }

    static final class ClientCertRequestingSocketFactory extends SSLSocketFactory {
        private final boolean required;
        private final SSLSocketFactory socketFactory;

        public ClientCertRequestingSocketFactory(SSLSocketFactory sSLSocketFactory, boolean z) {
            this.socketFactory = (SSLSocketFactory) Preconditions.checkNotNull(sSLSocketFactory, "socketFactory");
            this.required = z;
        }

        private Socket apply(Socket socket) throws IOException {
            if (socket instanceof SSLSocket) {
                SSLSocket sSLSocket = (SSLSocket) socket;
                if (this.required) {
                    sSLSocket.setNeedClientAuth(true);
                } else {
                    sSLSocket.setWantClientAuth(true);
                }
                return sSLSocket;
            }
            throw new IOException("SocketFactory " + this.socketFactory + " did not produce an SSLSocket: " + socket.getClass());
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return apply(this.socketFactory.createSocket(socket, str, i, z));
        }

        public Socket createSocket(String str, int i) throws IOException {
            return apply(this.socketFactory.createSocket(str, i));
        }

        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return apply(this.socketFactory.createSocket(str, i, inetAddress, i2));
        }

        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return apply(this.socketFactory.createSocket(inetAddress, i));
        }

        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return apply(this.socketFactory.createSocket(inetAddress, i, inetAddress2, i2));
        }

        public String[] getDefaultCipherSuites() {
            return this.socketFactory.getDefaultCipherSuites();
        }

        public String[] getSupportedCipherSuites() {
            return this.socketFactory.getSupportedCipherSuites();
        }
    }
}
