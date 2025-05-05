package io.grpc.okhttp;

import androidx.webkit.Profile;
import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ChoiceChannelCredentials;
import io.grpc.CompositeCallCredentials;
import io.grpc.CompositeChannelCredentials;
import io.grpc.ForwardingChannelBuilder2;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannelBuilder;
import io.grpc.TlsChannelCredentials;
import io.grpc.internal.AtomicBackoff;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.SslSocketFactoryChannelCredentials;
import io.grpc.okhttp.internal.CipherSuite;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.TlsVersion;
import io.grpc.util.CertificateUtils;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public final class OkHttpChannelBuilder extends ForwardingChannelBuilder2<OkHttpChannelBuilder> {
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    static final ObjectPool<Executor> DEFAULT_TRANSPORT_EXECUTOR_POOL;
    static final ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256).tlsVersions(TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
    private static final SharedResourceHolder.Resource<Executor> SHARED_EXECUTOR;
    private static final Logger log = Logger.getLogger(OkHttpChannelBuilder.class.getName());
    private static final EnumSet<TlsChannelCredentials.Feature> understoodTlsFeatures = EnumSet.of(TlsChannelCredentials.Feature.MTLS, TlsChannelCredentials.Feature.CUSTOM_MANAGERS);
    private ConnectionSpec connectionSpec;
    private int flowControlWindow;
    private final boolean freezeSecurityConfiguration;
    private HostnameVerifier hostnameVerifier;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private int maxInboundMessageSize;
    private int maxInboundMetadataSize;
    private NegotiationType negotiationType;
    private ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
    private SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private ObjectPool<Executor> transportExecutorPool;
    private TransportTracer.Factory transportTracerFactory;
    private final boolean useGetForSafeMethods;

    private enum NegotiationType {
        TLS,
        PLAINTEXT
    }

    static {
        AnonymousClass1 r0 = new SharedResourceHolder.Resource<Executor>() {
            public Executor create() {
                return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
            }

            public void close(Executor executor) {
                ((ExecutorService) executor).shutdown();
            }
        };
        SHARED_EXECUTOR = r0;
        DEFAULT_TRANSPORT_EXECUTOR_POOL = SharedResourcePool.forResource(r0);
    }

    public static OkHttpChannelBuilder forAddress(String str, int i) {
        return new OkHttpChannelBuilder(str, i);
    }

    public static OkHttpChannelBuilder forAddress(String str, int i, ChannelCredentials channelCredentials) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i), channelCredentials);
    }

    public static OkHttpChannelBuilder forTarget(String str) {
        return new OkHttpChannelBuilder(str);
    }

    public static OkHttpChannelBuilder forTarget(String str, ChannelCredentials channelCredentials) {
        SslSocketFactoryResult sslSocketFactoryFrom = sslSocketFactoryFrom(channelCredentials);
        if (sslSocketFactoryFrom.error == null) {
            return new OkHttpChannelBuilder(str, channelCredentials, sslSocketFactoryFrom.callCredentials, sslSocketFactoryFrom.factory);
        }
        throw new IllegalArgumentException(sslSocketFactoryFrom.error);
    }

    private OkHttpChannelBuilder(String str, int i) {
        this(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    private OkHttpChannelBuilder(String str) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        this.scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMessageSize = 4194304;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(str, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.freezeSecurityConfiguration = false;
    }

    OkHttpChannelBuilder(String str, ChannelCredentials channelCredentials, CallCredentials callCredentials, SSLSocketFactory sSLSocketFactory) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        this.scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMessageSize = 4194304;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(str, channelCredentials, callCredentials, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.sslSocketFactory = sSLSocketFactory;
        this.negotiationType = sSLSocketFactory == null ? NegotiationType.PLAINTEXT : NegotiationType.TLS;
        this.freezeSecurityConfiguration = true;
    }

    private final class OkHttpChannelTransportFactoryBuilder implements ManagedChannelImplBuilder.ClientTransportFactoryBuilder {
        private OkHttpChannelTransportFactoryBuilder() {
        }

        public ClientTransportFactory buildClientTransportFactory() {
            return OkHttpChannelBuilder.this.buildTransportFactory();
        }
    }

    private final class OkHttpChannelDefaultPortProvider implements ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider {
        private OkHttpChannelDefaultPortProvider() {
        }

        public int getDefaultPort() {
            return OkHttpChannelBuilder.this.getDefaultPort();
        }
    }

    /* access modifiers changed from: protected */
    public ManagedChannelBuilder<?> delegate() {
        return this.managedChannelImplBuilder;
    }

    /* access modifiers changed from: package-private */
    public OkHttpChannelBuilder setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return this;
    }

    public OkHttpChannelBuilder transportExecutor(@Nullable Executor executor) {
        if (executor == null) {
            this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        } else {
            this.transportExecutorPool = new FixedObjectPool(executor);
        }
        return this;
    }

    public OkHttpChannelBuilder socketFactory(@Nullable SocketFactory socketFactory2) {
        this.socketFactory = socketFactory2;
        return this;
    }

    @Deprecated
    public OkHttpChannelBuilder negotiationType(NegotiationType negotiationType2) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(negotiationType2, "type");
        int i = AnonymousClass2.$SwitchMap$io$grpc$okhttp$NegotiationType[negotiationType2.ordinal()];
        if (i == 1) {
            this.negotiationType = NegotiationType.TLS;
        } else if (i == 2) {
            this.negotiationType = NegotiationType.PLAINTEXT;
        } else {
            throw new AssertionError("Unknown negotiation type: " + negotiationType2);
        }
        return this;
    }

    public OkHttpChannelBuilder keepAliveTime(long j, TimeUnit timeUnit) {
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

    public OkHttpChannelBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive timeout must be positive");
        long nanos = timeUnit.toNanos(j);
        this.keepAliveTimeoutNanos = nanos;
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(nanos);
        return this;
    }

    public OkHttpChannelBuilder flowControlWindow(int i) {
        Preconditions.checkState(i > 0, "flowControlWindow must be positive");
        this.flowControlWindow = i;
        return this;
    }

    public OkHttpChannelBuilder keepAliveWithoutCalls(boolean z) {
        this.keepAliveWithoutCalls = z;
        return this;
    }

    public OkHttpChannelBuilder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.sslSocketFactory = sSLSocketFactory;
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder hostnameVerifier(@Nullable HostnameVerifier hostnameVerifier2) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.hostnameVerifier = hostnameVerifier2;
        return this;
    }

    public OkHttpChannelBuilder connectionSpec(com.squareup.okhttp.ConnectionSpec connectionSpec2) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkArgument(connectionSpec2.isTls(), "plaintext ConnectionSpec is not accepted");
        this.connectionSpec = Utils.convertSpec(connectionSpec2);
        return this;
    }

    public OkHttpChannelBuilder tlsConnectionSpec(String[] strArr, String[] strArr2) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(strArr, "tls versions must not null");
        Preconditions.checkNotNull(strArr2, "ciphers must not null");
        this.connectionSpec = new ConnectionSpec.Builder(true).supportsTlsExtensions(true).tlsVersions(strArr).cipherSuites(strArr2).build();
        return this;
    }

    public OkHttpChannelBuilder usePlaintext() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.PLAINTEXT;
        return this;
    }

    public OkHttpChannelBuilder useTransportSecurity() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorServicePool = new FixedObjectPool((ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    public OkHttpChannelBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    public OkHttpChannelBuilder maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "negative max");
        this.maxInboundMessageSize = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public OkHttpTransportFactory buildTransportFactory() {
        OkHttpTransportFactory okHttpTransportFactory = r2;
        OkHttpTransportFactory okHttpTransportFactory2 = new OkHttpTransportFactory(this.transportExecutorPool, this.scheduledExecutorServicePool, this.socketFactory, createSslSocketFactory(), this.hostnameVerifier, this.connectionSpec, this.maxInboundMessageSize, this.keepAliveTimeNanos != Long.MAX_VALUE, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, false);
        return okHttpTransportFactory;
    }

    /* access modifiers changed from: package-private */
    public OkHttpChannelBuilder disableCheckAuthority() {
        this.managedChannelImplBuilder.disableCheckAuthority();
        return this;
    }

    /* access modifiers changed from: package-private */
    public OkHttpChannelBuilder enableCheckAuthority() {
        this.managedChannelImplBuilder.enableCheckAuthority();
        return this;
    }

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$NegotiationType;
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        static {
            /*
                io.grpc.okhttp.OkHttpChannelBuilder$NegotiationType[] r0 = io.grpc.okhttp.OkHttpChannelBuilder.NegotiationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType = r0
                r1 = 1
                io.grpc.okhttp.OkHttpChannelBuilder$NegotiationType r2 = io.grpc.okhttp.OkHttpChannelBuilder.NegotiationType.PLAINTEXT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.okhttp.OkHttpChannelBuilder$NegotiationType r3 = io.grpc.okhttp.OkHttpChannelBuilder.NegotiationType.TLS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                io.grpc.okhttp.NegotiationType[] r2 = io.grpc.okhttp.NegotiationType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$io$grpc$okhttp$NegotiationType = r2
                io.grpc.okhttp.NegotiationType r3 = io.grpc.okhttp.NegotiationType.TLS     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$io$grpc$okhttp$NegotiationType     // Catch:{ NoSuchFieldError -> 0x0038 }
                io.grpc.okhttp.NegotiationType r2 = io.grpc.okhttp.NegotiationType.PLAINTEXT     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpChannelBuilder.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public int getDefaultPort() {
        int i = AnonymousClass2.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()];
        if (i == 1) {
            return 80;
        }
        if (i == 2) {
            return GrpcUtil.DEFAULT_PORT_SSL;
        }
        throw new AssertionError(this.negotiationType + " not handled");
    }

    /* access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.managedChannelImplBuilder.setStatsEnabled(z);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public SSLSocketFactory createSslSocketFactory() {
        int i = AnonymousClass2.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()];
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            try {
                if (this.sslSocketFactory == null) {
                    this.sslSocketFactory = SSLContext.getInstance(Profile.DEFAULT_PROFILE_NAME, Platform.get().getProvider()).getSocketFactory();
                }
                return this.sslSocketFactory;
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("TLS Provider failure", e);
            }
        } else {
            throw new RuntimeException("Unknown negotiation type: " + this.negotiationType);
        }
    }

    static SslSocketFactoryResult sslSocketFactoryFrom(ChannelCredentials channelCredentials) {
        KeyManager[] keyManagerArr;
        TrustManager[] trustManagerArr;
        if (channelCredentials instanceof TlsChannelCredentials) {
            TlsChannelCredentials tlsChannelCredentials = (TlsChannelCredentials) channelCredentials;
            Set<TlsChannelCredentials.Feature> incomprehensible = tlsChannelCredentials.incomprehensible(understoodTlsFeatures);
            if (!incomprehensible.isEmpty()) {
                return SslSocketFactoryResult.error("TLS features not understood: " + incomprehensible);
            }
            if (tlsChannelCredentials.getKeyManagers() != null) {
                keyManagerArr = (KeyManager[]) tlsChannelCredentials.getKeyManagers().toArray(new KeyManager[0]);
            } else if (tlsChannelCredentials.getPrivateKey() == null) {
                keyManagerArr = null;
            } else if (tlsChannelCredentials.getPrivateKeyPassword() != null) {
                return SslSocketFactoryResult.error("byte[]-based private key with password unsupported. Use unencrypted file or KeyManager");
            } else {
                try {
                    keyManagerArr = createKeyManager(tlsChannelCredentials.getCertificateChain(), tlsChannelCredentials.getPrivateKey());
                } catch (GeneralSecurityException e) {
                    log.log(Level.FINE, "Exception loading private key from credential", e);
                    return SslSocketFactoryResult.error("Unable to load private key: " + e.getMessage());
                }
            }
            if (tlsChannelCredentials.getTrustManagers() != null) {
                trustManagerArr = (TrustManager[]) tlsChannelCredentials.getTrustManagers().toArray(new TrustManager[0]);
            } else if (tlsChannelCredentials.getRootCertificates() != null) {
                try {
                    trustManagerArr = createTrustManager(tlsChannelCredentials.getRootCertificates());
                } catch (GeneralSecurityException e2) {
                    log.log(Level.FINE, "Exception loading root certificates from credential", e2);
                    return SslSocketFactoryResult.error("Unable to load root certificates: " + e2.getMessage());
                }
            } else {
                trustManagerArr = null;
            }
            try {
                SSLContext instance = SSLContext.getInstance("TLS", Platform.get().getProvider());
                instance.init(keyManagerArr, trustManagerArr, (SecureRandom) null);
                return SslSocketFactoryResult.factory(instance.getSocketFactory());
            } catch (GeneralSecurityException e3) {
                throw new RuntimeException("TLS Provider failure", e3);
            }
        } else if (channelCredentials instanceof InsecureChannelCredentials) {
            return SslSocketFactoryResult.plaintext();
        } else {
            if (channelCredentials instanceof CompositeChannelCredentials) {
                CompositeChannelCredentials compositeChannelCredentials = (CompositeChannelCredentials) channelCredentials;
                return sslSocketFactoryFrom(compositeChannelCredentials.getChannelCredentials()).withCallCredentials(compositeChannelCredentials.getCallCredentials());
            } else if (channelCredentials instanceof SslSocketFactoryChannelCredentials.ChannelCredentials) {
                return SslSocketFactoryResult.factory(((SslSocketFactoryChannelCredentials.ChannelCredentials) channelCredentials).getFactory());
            } else {
                if (!(channelCredentials instanceof ChoiceChannelCredentials)) {
                    return SslSocketFactoryResult.error("Unsupported credential type: " + channelCredentials.getClass().getName());
                }
                StringBuilder sb = new StringBuilder();
                for (ChannelCredentials sslSocketFactoryFrom : ((ChoiceChannelCredentials) channelCredentials).getCredentialsList()) {
                    SslSocketFactoryResult sslSocketFactoryFrom2 = sslSocketFactoryFrom(sslSocketFactoryFrom);
                    if (sslSocketFactoryFrom2.error == null) {
                        return sslSocketFactoryFrom2;
                    }
                    sb.append(", ");
                    sb.append(sslSocketFactoryFrom2.error);
                }
                return SslSocketFactoryResult.error(sb.substring(2));
            }
        }
    }

    /* JADX INFO: finally extract failed */
    static KeyManager[] createKeyManager(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            X509Certificate[] x509Certificates = CertificateUtils.getX509Certificates(byteArrayInputStream);
            GrpcUtil.closeQuietly((Closeable) byteArrayInputStream);
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr2);
            try {
                PrivateKey privateKey = CertificateUtils.getPrivateKey(byteArrayInputStream2);
                GrpcUtil.closeQuietly((Closeable) byteArrayInputStream2);
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                try {
                    instance.load((InputStream) null, (char[]) null);
                    instance.setKeyEntry("key", privateKey, new char[0], x509Certificates);
                    KeyManagerFactory instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    instance2.init(instance, new char[0]);
                    return instance2.getKeyManagers();
                } catch (IOException e) {
                    throw new GeneralSecurityException(e);
                }
            } catch (IOException e2) {
                throw new GeneralSecurityException("Unable to decode private key", e2);
            } catch (Throwable th) {
                GrpcUtil.closeQuietly((Closeable) byteArrayInputStream2);
                throw th;
            }
        } catch (Throwable th2) {
            GrpcUtil.closeQuietly((Closeable) byteArrayInputStream);
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    static TrustManager[] createTrustManager(byte[] bArr) throws GeneralSecurityException {
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            instance.load((InputStream) null, (char[]) null);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                X509Certificate[] x509Certificates = CertificateUtils.getX509Certificates(byteArrayInputStream);
                GrpcUtil.closeQuietly((Closeable) byteArrayInputStream);
                for (X509Certificate x509Certificate : x509Certificates) {
                    instance.setCertificateEntry(x509Certificate.getSubjectX500Principal().getName("RFC2253"), x509Certificate);
                }
                TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance2.init(instance);
                return instance2.getTrustManagers();
            } catch (Throwable th) {
                GrpcUtil.closeQuietly((Closeable) byteArrayInputStream);
                throw th;
            }
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    static Collection<Class<? extends SocketAddress>> getSupportedSocketAddressTypes() {
        return Collections.singleton(InetSocketAddress.class);
    }

    static final class SslSocketFactoryResult {
        public final CallCredentials callCredentials;
        public final String error;
        public final SSLSocketFactory factory;

        private SslSocketFactoryResult(SSLSocketFactory sSLSocketFactory, CallCredentials callCredentials2, String str) {
            this.factory = sSLSocketFactory;
            this.callCredentials = callCredentials2;
            this.error = str;
        }

        public static SslSocketFactoryResult error(String str) {
            return new SslSocketFactoryResult((SSLSocketFactory) null, (CallCredentials) null, (String) Preconditions.checkNotNull(str, "error"));
        }

        public static SslSocketFactoryResult plaintext() {
            return new SslSocketFactoryResult((SSLSocketFactory) null, (CallCredentials) null, (String) null);
        }

        public static SslSocketFactoryResult factory(SSLSocketFactory sSLSocketFactory) {
            return new SslSocketFactoryResult((SSLSocketFactory) Preconditions.checkNotNull(sSLSocketFactory, "factory"), (CallCredentials) null, (String) null);
        }

        public SslSocketFactoryResult withCallCredentials(CallCredentials callCredentials2) {
            Preconditions.checkNotNull(callCredentials2, "callCreds");
            if (this.error != null) {
                return this;
            }
            if (this.callCredentials != null) {
                callCredentials2 = new CompositeCallCredentials(this.callCredentials, callCredentials2);
            }
            return new SslSocketFactoryResult(this.factory, callCredentials2, (String) null);
        }
    }

    static final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        final ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        final Executor executor;
        private final ObjectPool<Executor> executorPool;
        final int flowControlWindow;
        @Nullable
        final HostnameVerifier hostnameVerifier;
        private final AtomicBackoff keepAliveBackoff;
        private final long keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        final int maxInboundMetadataSize;
        final int maxMessageSize;
        final ScheduledExecutorService scheduledExecutorService;
        private final ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
        final SocketFactory socketFactory;
        @Nullable
        final SSLSocketFactory sslSocketFactory;
        final TransportTracer.Factory transportTracerFactory;
        final boolean useGetForSafeMethods;

        private OkHttpTransportFactory(ObjectPool<Executor> objectPool, ObjectPool<ScheduledExecutorService> objectPool2, @Nullable SocketFactory socketFactory2, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier2, ConnectionSpec connectionSpec2, int i, boolean z, long j, long j2, int i2, boolean z2, int i3, TransportTracer.Factory factory, boolean z3) {
            this.executorPool = objectPool;
            this.executor = objectPool.getObject();
            this.scheduledExecutorServicePool = objectPool2;
            this.scheduledExecutorService = objectPool2.getObject();
            this.socketFactory = socketFactory2;
            this.sslSocketFactory = sSLSocketFactory;
            this.hostnameVerifier = hostnameVerifier2;
            this.connectionSpec = connectionSpec2;
            this.maxMessageSize = i;
            this.enableKeepAlive = z;
            this.keepAliveTimeNanos = j;
            this.keepAliveBackoff = new AtomicBackoff("keepalive time nanos", j);
            this.keepAliveTimeoutNanos = j2;
            this.flowControlWindow = i2;
            this.keepAliveWithoutCalls = z2;
            this.maxInboundMetadataSize = i3;
            this.useGetForSafeMethods = z3;
            this.transportTracerFactory = (TransportTracer.Factory) Preconditions.checkNotNull(factory, "transportTracerFactory");
        }

        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            if (!this.closed) {
                final AtomicBackoff.State state = this.keepAliveBackoff.getState();
                OkHttpClientTransport okHttpClientTransport = new OkHttpClientTransport(this, (InetSocketAddress) socketAddress, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent(), clientTransportOptions.getEagAttributes(), clientTransportOptions.getHttpConnectProxiedSocketAddress(), (Runnable) new Runnable() {
                    public void run() {
                        state.backoff();
                    }
                });
                if (this.enableKeepAlive) {
                    okHttpClientTransport.enableKeepAlive(true, state.get(), this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
                }
                return okHttpClientTransport;
            }
            throw new IllegalStateException("The transport factory is closed.");
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return this.scheduledExecutorService;
        }

        @CheckReturnValue
        @Nullable
        public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials) {
            SslSocketFactoryResult sslSocketFactoryFrom = OkHttpChannelBuilder.sslSocketFactoryFrom(channelCredentials);
            if (sslSocketFactoryFrom.error != null) {
                return null;
            }
            OkHttpTransportFactory okHttpTransportFactory = r2;
            SslSocketFactoryResult sslSocketFactoryResult = sslSocketFactoryFrom;
            OkHttpTransportFactory okHttpTransportFactory2 = new OkHttpTransportFactory(this.executorPool, this.scheduledExecutorServicePool, this.socketFactory, sslSocketFactoryFrom.factory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.enableKeepAlive, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, this.useGetForSafeMethods);
            return new ClientTransportFactory.SwapChannelCredentialsResult(okHttpTransportFactory, sslSocketFactoryResult.callCredentials);
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                this.executorPool.returnObject(this.executor);
                this.scheduledExecutorServicePool.returnObject(this.scheduledExecutorService);
            }
        }

        public Collection<Class<? extends SocketAddress>> getSupportedSocketAddressTypes() {
            return OkHttpChannelBuilder.getSupportedSocketAddressTypes();
        }
    }
}
