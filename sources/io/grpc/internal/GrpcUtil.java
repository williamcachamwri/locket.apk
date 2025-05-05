package io.grpc.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.InternalMetadata;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ProxiedSocketAddress;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.StreamListener;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FilenameUtils;

public final class GrpcUtil {
    public static final Splitter ACCEPT_ENCODING_SPLITTER = Splitter.on((char) AbstractJsonLexerKt.COMMA).trimResults();
    public static final CallOptions.Key<Boolean> CALL_OPTIONS_RPC_OWNED_BY_BALANCER = CallOptions.Key.create("io.grpc.internal.CALL_OPTIONS_RPC_OWNED_BY_BALANCER");
    public static final String CONTENT_ACCEPT_ENCODING = "accept-encoding";
    public static final Metadata.Key<byte[]> CONTENT_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf(CONTENT_ACCEPT_ENCODING, new AcceptEncodingMarshaller());
    public static final String CONTENT_ENCODING = "content-encoding";
    public static final Metadata.Key<String> CONTENT_ENCODING_KEY = Metadata.Key.of(CONTENT_ENCODING, Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key<String> CONTENT_LENGTH_KEY = Metadata.Key.of("content-length", Metadata.ASCII_STRING_MARSHALLER);
    public static final String CONTENT_TYPE_GRPC = "application/grpc";
    public static final Metadata.Key<String> CONTENT_TYPE_KEY = Metadata.Key.of("content-type", Metadata.ASCII_STRING_MARSHALLER);
    public static final long DEFAULT_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20);
    public static final String DEFAULT_LB_POLICY = "pick_first";
    public static final int DEFAULT_MAX_HEADER_LIST_SIZE = 8192;
    public static final int DEFAULT_MAX_MESSAGE_SIZE = 4194304;
    public static final int DEFAULT_PORT_PLAINTEXT = 80;
    public static final int DEFAULT_PORT_SSL = 443;
    public static final ProxyDetector DEFAULT_PROXY_DETECTOR = new ProxyDetectorImpl();
    public static final long DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20);
    public static final long DEFAULT_SERVER_KEEPALIVE_TIME_NANOS = TimeUnit.HOURS.toNanos(2);
    public static final String HTTP_METHOD = "POST";
    public static final String IMPLEMENTATION_VERSION = "1.62.2";
    private static final Set<Status.Code> INAPPROPRIATE_CONTROL_PLANE_STATUS = Collections.unmodifiableSet(EnumSet.of(Status.Code.OK, new Status.Code[]{Status.Code.INVALID_ARGUMENT, Status.Code.NOT_FOUND, Status.Code.ALREADY_EXISTS, Status.Code.FAILED_PRECONDITION, Status.Code.ABORTED, Status.Code.OUT_OF_RANGE, Status.Code.DATA_LOSS}));
    public static final long KEEPALIVE_TIME_NANOS_DISABLED = Long.MAX_VALUE;
    public static final String MESSAGE_ACCEPT_ENCODING = "grpc-accept-encoding";
    public static final Metadata.Key<byte[]> MESSAGE_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf(MESSAGE_ACCEPT_ENCODING, new AcceptEncodingMarshaller());
    public static final String MESSAGE_ENCODING = "grpc-encoding";
    public static final Metadata.Key<String> MESSAGE_ENCODING_KEY = Metadata.Key.of(MESSAGE_ENCODING, Metadata.ASCII_STRING_MARSHALLER);
    public static final ProxyDetector NOOP_PROXY_DETECTOR = new ProxyDetector() {
        @Nullable
        public ProxiedSocketAddress proxyFor(SocketAddress socketAddress) {
            return null;
        }
    };
    /* access modifiers changed from: private */
    public static final ClientStreamTracer NOOP_TRACER = new ClientStreamTracer() {
    };
    public static final long SERVER_KEEPALIVE_TIME_NANOS_DISABLED = Long.MAX_VALUE;
    public static final SharedResourceHolder.Resource<Executor> SHARED_CHANNEL_EXECUTOR = new SharedResourceHolder.Resource<Executor>() {
        private static final String NAME = "grpc-default-executor";

        public String toString() {
            return NAME;
        }

        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-default-executor-%d", true));
        }

        public void close(Executor executor) {
            ((ExecutorService) executor).shutdown();
        }
    };
    public static final Supplier<Stopwatch> STOPWATCH_SUPPLIER = new Supplier<Stopwatch>() {
        public Stopwatch get() {
            return Stopwatch.createUnstarted();
        }
    };
    public static final Metadata.Key<String> TE_HEADER = Metadata.Key.of("te", Metadata.ASCII_STRING_MARSHALLER);
    public static final String TE_TRAILERS = "trailers";
    public static final String TIMEOUT = "grpc-timeout";
    public static final Metadata.Key<Long> TIMEOUT_KEY = Metadata.Key.of(TIMEOUT, new TimeoutMarshaller());
    public static final SharedResourceHolder.Resource<ScheduledExecutorService> TIMER_SERVICE = new SharedResourceHolder.Resource<ScheduledExecutorService>() {
        public ScheduledExecutorService create() {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, GrpcUtil.getThreadFactory("grpc-timer-%d", true));
            try {
                newScheduledThreadPool.getClass().getMethod("setRemoveOnCancelPolicy", new Class[]{Boolean.TYPE}).invoke(newScheduledThreadPool, new Object[]{true});
            } catch (NoSuchMethodException unused) {
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
            return Executors.unconfigurableScheduledExecutorService(newScheduledThreadPool);
        }

        public void close(ScheduledExecutorService scheduledExecutorService) {
            scheduledExecutorService.shutdown();
        }
    };
    public static final Metadata.Key<String> USER_AGENT_KEY = Metadata.Key.of("user-agent", Metadata.ASCII_STRING_MARSHALLER);
    public static final Charset US_ASCII = Charset.forName(CharEncoding.US_ASCII);
    private static final Logger log = Logger.getLogger(GrpcUtil.class.getName());

    private static final class AcceptEncodingMarshaller implements InternalMetadata.TrustedAsciiMarshaller<byte[]> {
        public byte[] parseAsciiString(byte[] bArr) {
            return bArr;
        }

        public byte[] toAsciiString(byte[] bArr) {
            return bArr;
        }

        private AcceptEncodingMarshaller() {
        }
    }

    public static boolean shouldBeCountedForInUse(CallOptions callOptions) {
        return !Boolean.TRUE.equals(callOptions.getOption(CALL_OPTIONS_RPC_OWNED_BY_BALANCER));
    }

    public static Status httpStatusToGrpcStatus(int i) {
        return httpStatusToGrpcCode(i).toStatus().withDescription("HTTP status code " + i);
    }

    private static Status.Code httpStatusToGrpcCode(int i) {
        if (i >= 100 && i < 200) {
            return Status.Code.INTERNAL;
        }
        if (i != 400) {
            if (i == 401) {
                return Status.Code.UNAUTHENTICATED;
            }
            if (i == 403) {
                return Status.Code.PERMISSION_DENIED;
            }
            if (i == 404) {
                return Status.Code.UNIMPLEMENTED;
            }
            if (i != 429) {
                if (i != 431) {
                    switch (i) {
                        case TypedValues.PositionType.TYPE_DRAWPATH:
                        case TypedValues.PositionType.TYPE_PERCENT_WIDTH:
                        case TypedValues.PositionType.TYPE_PERCENT_HEIGHT:
                            break;
                        default:
                            return Status.Code.UNKNOWN;
                    }
                }
            }
            return Status.Code.UNAVAILABLE;
        }
        return Status.Code.INTERNAL;
    }

    public enum Http2Error {
        NO_ERROR(0, Status.UNAVAILABLE),
        PROTOCOL_ERROR(1, Status.INTERNAL),
        INTERNAL_ERROR(2, Status.INTERNAL),
        FLOW_CONTROL_ERROR(3, Status.INTERNAL),
        SETTINGS_TIMEOUT(4, Status.INTERNAL),
        STREAM_CLOSED(5, Status.INTERNAL),
        FRAME_SIZE_ERROR(6, Status.INTERNAL),
        REFUSED_STREAM(7, Status.UNAVAILABLE),
        CANCEL(8, Status.CANCELLED),
        COMPRESSION_ERROR(9, Status.INTERNAL),
        CONNECT_ERROR(10, Status.INTERNAL),
        ENHANCE_YOUR_CALM(11, Status.RESOURCE_EXHAUSTED.withDescription("Bandwidth exhausted")),
        INADEQUATE_SECURITY(12, Status.PERMISSION_DENIED.withDescription("Permission denied as protocol is not secure enough to call")),
        HTTP_1_1_REQUIRED(13, Status.UNKNOWN);
        
        private static final Http2Error[] codeMap = null;
        private final int code;
        private final Status status;

        static {
            codeMap = buildHttp2CodeMap();
        }

        private static Http2Error[] buildHttp2CodeMap() {
            Http2Error[] values = values();
            Http2Error[] http2ErrorArr = new Http2Error[(((int) values[values.length - 1].code()) + 1)];
            for (Http2Error http2Error : values) {
                http2ErrorArr[(int) http2Error.code()] = http2Error;
            }
            return http2ErrorArr;
        }

        private Http2Error(int i, Status status2) {
            this.code = i;
            String str = "HTTP/2 error code: " + name();
            this.status = status2.withDescription(status2.getDescription() != null ? str + " (" + status2.getDescription() + ")" : str);
        }

        public long code() {
            return (long) this.code;
        }

        public Status status() {
            return this.status;
        }

        public static Http2Error forCode(long j) {
            Http2Error[] http2ErrorArr = codeMap;
            if (j >= ((long) http2ErrorArr.length) || j < 0) {
                return null;
            }
            return http2ErrorArr[(int) j];
        }

        public static Status statusForCode(long j) {
            Http2Error forCode = forCode(j);
            if (forCode == null) {
                return Status.fromCodeValue(INTERNAL_ERROR.status().getCode().value()).withDescription("Unrecognized HTTP/2 error code: " + j);
            }
            return forCode.status();
        }
    }

    public static boolean isGrpcContentType(String str) {
        if (str == null || 16 > str.length()) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        if (!lowerCase.startsWith(CONTENT_TYPE_GRPC)) {
            return false;
        }
        if (lowerCase.length() == 16) {
            return true;
        }
        char charAt = lowerCase.charAt(16);
        if (charAt == '+' || charAt == ';') {
            return true;
        }
        return false;
    }

    public static String getGrpcUserAgent(String str, @Nullable String str2) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append(str2);
            sb.append(' ');
        }
        sb.append("grpc-java-");
        sb.append(str);
        sb.append("/1.62.2");
        return sb.toString();
    }

    public static final class GrpcBuildVersion {
        private final String implementationVersion;
        private final String userAgent;

        private GrpcBuildVersion(String str, String str2) {
            this.userAgent = (String) Preconditions.checkNotNull(str, "userAgentName");
            this.implementationVersion = (String) Preconditions.checkNotNull(str2, "implementationVersion");
        }

        public String getUserAgent() {
            return this.userAgent;
        }

        public String getImplementationVersion() {
            return this.implementationVersion;
        }

        public String toString() {
            return this.userAgent + " " + this.implementationVersion;
        }
    }

    public static GrpcBuildVersion getGrpcBuildVersion() {
        return new GrpcBuildVersion("gRPC Java", IMPLEMENTATION_VERSION);
    }

    public static URI authorityToUri(String str) {
        Preconditions.checkNotNull(str, "authority");
        try {
            return new URI((String) null, str, (String) null, (String) null, (String) null);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid authority: " + str, e);
        }
    }

    public static String checkAuthority(String str) {
        Preconditions.checkArgument(authorityToUri(str).getAuthority().indexOf(64) == -1, "Userinfo must not be present on authority: '%s'", (Object) str);
        return str;
    }

    public static String authorityFromHostAndPort(String str, int i) {
        try {
            return new URI((String) null, (String) null, str, i, (String) null, (String) null, (String) null).getAuthority();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid host or port: " + str + " " + i, e);
        }
    }

    public static ThreadFactory getThreadFactory(String str, boolean z) {
        return new ThreadFactoryBuilder().setDaemon(z).setNameFormat(str).build();
    }

    public static String getHost(InetSocketAddress inetSocketAddress) {
        try {
            return (String) InetSocketAddress.class.getMethod("getHostString", new Class[0]).invoke(inetSocketAddress, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return inetSocketAddress.getHostName();
        }
    }

    static class TimeoutMarshaller implements Metadata.AsciiMarshaller<Long> {
        TimeoutMarshaller() {
        }

        public String toAsciiString(Long l) {
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            if (l.longValue() < 0) {
                throw new IllegalArgumentException("Timeout too small");
            } else if (l.longValue() < 100000000) {
                return l + "n";
            } else {
                if (l.longValue() < 100000000000L) {
                    return timeUnit.toMicros(l.longValue()) + "u";
                }
                if (l.longValue() < 100000000000000L) {
                    return timeUnit.toMillis(l.longValue()) + "m";
                }
                if (l.longValue() < 100000000000000000L) {
                    return timeUnit.toSeconds(l.longValue()) + ExifInterface.LATITUDE_SOUTH;
                }
                if (l.longValue() < 6000000000000000000L) {
                    return timeUnit.toMinutes(l.longValue()) + "M";
                }
                return timeUnit.toHours(l.longValue()) + "H";
            }
        }

        public Long parseAsciiString(String str) {
            Preconditions.checkArgument(str.length() > 0, "empty timeout");
            Preconditions.checkArgument(str.length() <= 9, "bad timeout format");
            long parseLong = Long.parseLong(str.substring(0, str.length() - 1));
            char charAt = str.charAt(str.length() - 1);
            if (charAt == 'H') {
                return Long.valueOf(TimeUnit.HOURS.toNanos(parseLong));
            }
            if (charAt == 'M') {
                return Long.valueOf(TimeUnit.MINUTES.toNanos(parseLong));
            }
            if (charAt == 'S') {
                return Long.valueOf(TimeUnit.SECONDS.toNanos(parseLong));
            }
            if (charAt == 'u') {
                return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(parseLong));
            }
            if (charAt == 'm') {
                return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(parseLong));
            }
            if (charAt == 'n') {
                return Long.valueOf(parseLong);
            }
            throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", new Object[]{Character.valueOf(charAt)}));
        }
    }

    @Nullable
    static ClientTransport getTransportFromPickResult(LoadBalancer.PickResult pickResult, boolean z) {
        LoadBalancer.Subchannel subchannel = pickResult.getSubchannel();
        final ClientTransport obtainActiveTransport = subchannel != null ? ((TransportProvider) subchannel.getInternalSubchannel()).obtainActiveTransport() : null;
        if (obtainActiveTransport != null) {
            final ClientStreamTracer.Factory streamTracerFactory = pickResult.getStreamTracerFactory();
            if (streamTracerFactory == null) {
                return obtainActiveTransport;
            }
            return new ClientTransport() {
                public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
                    ClientStreamTracer newClientStreamTracer = ClientStreamTracer.Factory.this.newClientStreamTracer(ClientStreamTracer.StreamInfo.newBuilder().setCallOptions(callOptions).build(), metadata);
                    Preconditions.checkState(clientStreamTracerArr[clientStreamTracerArr.length - 1] == GrpcUtil.NOOP_TRACER, "lb tracer already assigned");
                    clientStreamTracerArr[clientStreamTracerArr.length - 1] = newClientStreamTracer;
                    return obtainActiveTransport.newStream(methodDescriptor, metadata, callOptions, clientStreamTracerArr);
                }

                public void ping(ClientTransport.PingCallback pingCallback, Executor executor) {
                    obtainActiveTransport.ping(pingCallback, executor);
                }

                public InternalLogId getLogId() {
                    return obtainActiveTransport.getLogId();
                }

                public ListenableFuture<InternalChannelz.SocketStats> getStats() {
                    return obtainActiveTransport.getStats();
                }
            };
        }
        if (!pickResult.getStatus().isOk()) {
            if (pickResult.isDrop()) {
                return new FailingClientTransport(replaceInappropriateControlPlaneStatus(pickResult.getStatus()), ClientStreamListener.RpcProgress.DROPPED);
            }
            if (!z) {
                return new FailingClientTransport(replaceInappropriateControlPlaneStatus(pickResult.getStatus()), ClientStreamListener.RpcProgress.PROCESSED);
            }
        }
        return null;
    }

    public static ClientStreamTracer[] getClientStreamTracers(CallOptions callOptions, Metadata metadata, int i, boolean z) {
        List<ClientStreamTracer.Factory> streamTracerFactories = callOptions.getStreamTracerFactories();
        int size = streamTracerFactories.size() + 1;
        ClientStreamTracer[] clientStreamTracerArr = new ClientStreamTracer[size];
        ClientStreamTracer.StreamInfo build = ClientStreamTracer.StreamInfo.newBuilder().setCallOptions(callOptions).setPreviousAttempts(i).setIsTransparentRetry(z).build();
        for (int i2 = 0; i2 < streamTracerFactories.size(); i2++) {
            clientStreamTracerArr[i2] = streamTracerFactories.get(i2).newClientStreamTracer(build, metadata);
        }
        clientStreamTracerArr[size - 1] = NOOP_TRACER;
        return clientStreamTracerArr;
    }

    static void closeQuietly(StreamListener.MessageProducer messageProducer) {
        while (true) {
            InputStream next = messageProducer.next();
            if (next != null) {
                closeQuietly((Closeable) next);
            } else {
                return;
            }
        }
    }

    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.log(Level.WARNING, "exception caught in closeQuietly", e);
            }
        }
    }

    public static void exhaust(InputStream inputStream) throws IOException {
        do {
        } while (inputStream.read(new byte[256]) != -1);
    }

    public static Status replaceInappropriateControlPlaneStatus(Status status) {
        Preconditions.checkArgument(status != null);
        return INAPPROPRIATE_CONTROL_PLANE_STATUS.contains(status.getCode()) ? Status.INTERNAL.withDescription("Inappropriate status code from control plane: " + status.getCode() + " " + status.getDescription()).withCause(status.getCause()) : status;
    }

    static <T> boolean iterableContains(Iterable<T> iterable, T t) {
        if (iterable instanceof Collection) {
            try {
                return ((Collection) iterable).contains(t);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        } else {
            for (T equal : iterable) {
                if (Objects.equal(equal, t)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class AuthorityEscaper {
        private static final Set<Character> AUTHORITY_DELIMS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Character[]{Character.valueOf(AbstractJsonLexerKt.COLON), Character.valueOf(AbstractJsonLexerKt.BEGIN_LIST), Character.valueOf(AbstractJsonLexerKt.END_LIST), '@'})));
        private static final Set<Character> SUB_DELIMS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Character[]{'!', Character.valueOf(Typography.dollar), Character.valueOf(Typography.amp), '\'', '(', ')', '*', '+', Character.valueOf(AbstractJsonLexerKt.COMMA), ';', '='})));
        private static final Set<Character> UNRESERVED_CHARACTERS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Character[]{'-', '_', Character.valueOf(FilenameUtils.EXTENSION_SEPARATOR), '~'})));
        private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();

        private static boolean shouldEscape(char c) {
            if (c > 127) {
                return false;
            }
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return false;
            }
            if ((c < '0' || c > '9') && !UNRESERVED_CHARACTERS.contains(Character.valueOf(c)) && !SUB_DELIMS.contains(Character.valueOf(c)) && !AUTHORITY_DELIMS.contains(Character.valueOf(c))) {
                return true;
            }
            return false;
        }

        public static String encodeAuthority(String str) {
            Preconditions.checkNotNull(str, "authority");
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (shouldEscape(str.charAt(i2))) {
                    i++;
                }
            }
            if (i == 0) {
                return str;
            }
            StringBuilder sb = new StringBuilder((i * 2) + length);
            for (int i3 = 0; i3 < length; i3++) {
                char charAt = str.charAt(i3);
                if (shouldEscape(charAt)) {
                    sb.append('%');
                    char[] cArr = UPPER_HEX_DIGITS;
                    sb.append(cArr[charAt >>> 4]);
                    sb.append(cArr[charAt & 15]);
                } else {
                    sb.append(charAt);
                }
            }
            return sb.toString();
        }
    }

    public static boolean getFlag(String str, boolean z) {
        String str2 = System.getenv(str);
        if (str2 == null) {
            str2 = System.getProperty(str);
        }
        if (z) {
            if (Strings.isNullOrEmpty(str2) || Boolean.parseBoolean(str2)) {
                return true;
            }
            return false;
        } else if (Strings.isNullOrEmpty(str2) || !Boolean.parseBoolean(str2)) {
            return false;
        } else {
            return true;
        }
    }

    private GrpcUtil() {
    }
}
