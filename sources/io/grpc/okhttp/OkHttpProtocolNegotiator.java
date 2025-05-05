package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.internal.GrpcUtil;
import io.grpc.okhttp.internal.OptionalMethod;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.Protocol;
import io.grpc.okhttp.internal.Util;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

class OkHttpProtocolNegotiator {
    private static final Platform DEFAULT_PLATFORM = Platform.get();
    private static OkHttpProtocolNegotiator NEGOTIATOR;
    /* access modifiers changed from: private */
    public static final Logger logger;
    protected final Platform platform;

    static {
        Class<OkHttpProtocolNegotiator> cls = OkHttpProtocolNegotiator.class;
        logger = Logger.getLogger(cls.getName());
        NEGOTIATOR = createNegotiator(cls.getClassLoader());
    }

    OkHttpProtocolNegotiator(Platform platform2) {
        this.platform = (Platform) Preconditions.checkNotNull(platform2, "platform");
    }

    public static OkHttpProtocolNegotiator get() {
        return NEGOTIATOR;
    }

    static OkHttpProtocolNegotiator createNegotiator(ClassLoader classLoader) {
        boolean z;
        try {
            classLoader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find Conscrypt. Skipping", e);
            try {
                classLoader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Unable to find any OpenSSLSocketImpl. Skipping", e2);
                z = false;
            }
        }
        z = true;
        if (z) {
            return new AndroidNegotiator(DEFAULT_PLATFORM);
        }
        return new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
    }

    public String negotiate(SSLSocket sSLSocket, String str, @Nullable List<Protocol> list) throws IOException {
        if (list != null) {
            configureTlsExtensions(sSLSocket, str, list);
        }
        try {
            sSLSocket.startHandshake();
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            if (selectedProtocol != null) {
                return selectedProtocol;
            }
            throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + list);
        } finally {
            this.platform.afterHandshake(sSLSocket);
        }
    }

    /* access modifiers changed from: protected */
    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
        this.platform.configureTlsExtensions(sSLSocket, str, list);
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return this.platform.getSelectedProtocol(sSLSocket);
    }

    static final class AndroidNegotiator extends OkHttpProtocolNegotiator {
        private static final OptionalMethod<Socket> GET_ALPN_SELECTED_PROTOCOL;
        private static final Method GET_APPLICATION_PROTOCOL;
        private static final Method GET_APPLICATION_PROTOCOLS;
        private static final OptionalMethod<Socket> GET_NPN_SELECTED_PROTOCOL;
        private static final OptionalMethod<Socket> SET_ALPN_PROTOCOLS;
        private static final Method SET_APPLICATION_PROTOCOLS;
        private static final OptionalMethod<Socket> SET_HOSTNAME = new OptionalMethod<>((Class<?>) null, "setHostname", String.class);
        private static final OptionalMethod<Socket> SET_NPN_PROTOCOLS;
        private static final Method SET_SERVER_NAMES;
        private static final OptionalMethod<Socket> SET_USE_SESSION_TICKETS = new OptionalMethod<>((Class<?>) null, "setUseSessionTickets", Boolean.TYPE);
        private static final Constructor<?> SNI_HOST_NAME;
        private static final Method SSL_SOCKETS_IS_SUPPORTED_SOCKET;
        private static final Method SSL_SOCKETS_SET_USE_SESSION_TICKET;

        static {
            Method method;
            Method method2;
            Method method3;
            Method method4;
            Method method5;
            Method method6;
            Method method7;
            Constructor<?> constructor = null;
            Class<byte[]> cls = byte[].class;
            GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod<>(cls, "getAlpnSelectedProtocol", new Class[0]);
            SET_ALPN_PROTOCOLS = new OptionalMethod<>((Class<?>) null, "setAlpnProtocols", cls);
            GET_NPN_SELECTED_PROTOCOL = new OptionalMethod<>(cls, "getNpnSelectedProtocol", new Class[0]);
            SET_NPN_PROTOCOLS = new OptionalMethod<>((Class<?>) null, "setNpnProtocols", cls);
            Class<SSLParameters> cls2 = SSLParameters.class;
            try {
                method3 = cls2.getMethod("setApplicationProtocols", new Class[]{String[].class});
                try {
                    method4 = cls2.getMethod("getApplicationProtocols", new Class[0]);
                } catch (ClassNotFoundException e) {
                    e = e;
                    method4 = null;
                    method7 = null;
                    method = method2;
                    OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                    method5 = null;
                    SET_APPLICATION_PROTOCOLS = method3;
                    GET_APPLICATION_PROTOCOLS = method4;
                    GET_APPLICATION_PROTOCOL = method2;
                    SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                    SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                    method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                    constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                    SET_SERVER_NAMES = method6;
                    SNI_HOST_NAME = constructor;
                } catch (NoSuchMethodException e2) {
                    e = e2;
                    method4 = null;
                    method2 = null;
                    method = method2;
                    OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                    method5 = null;
                    SET_APPLICATION_PROTOCOLS = method3;
                    GET_APPLICATION_PROTOCOLS = method4;
                    GET_APPLICATION_PROTOCOL = method2;
                    SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                    SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                    method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                    constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                    SET_SERVER_NAMES = method6;
                    SNI_HOST_NAME = constructor;
                }
                try {
                    method2 = SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                    try {
                        Class<?> cls3 = Class.forName("android.net.ssl.SSLSockets");
                        method = cls3.getMethod("isSupportedSocket", new Class[]{SSLSocket.class});
                        try {
                            method5 = cls3.getMethod("setUseSessionTickets", new Class[]{SSLSocket.class, Boolean.TYPE});
                        } catch (ClassNotFoundException e3) {
                            e = e3;
                            OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                            method5 = null;
                            SET_APPLICATION_PROTOCOLS = method3;
                            GET_APPLICATION_PROTOCOLS = method4;
                            GET_APPLICATION_PROTOCOL = method2;
                            SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                            SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                            method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                            constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                            SET_SERVER_NAMES = method6;
                            SNI_HOST_NAME = constructor;
                        } catch (NoSuchMethodException e4) {
                            e = e4;
                            OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                            method5 = null;
                            SET_APPLICATION_PROTOCOLS = method3;
                            GET_APPLICATION_PROTOCOLS = method4;
                            GET_APPLICATION_PROTOCOL = method2;
                            SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                            SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                            method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                            constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                            SET_SERVER_NAMES = method6;
                            SNI_HOST_NAME = constructor;
                        }
                    } catch (ClassNotFoundException e5) {
                        e = e5;
                        method = null;
                        OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                        method5 = null;
                        SET_APPLICATION_PROTOCOLS = method3;
                        GET_APPLICATION_PROTOCOLS = method4;
                        GET_APPLICATION_PROTOCOL = method2;
                        SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                        SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                        method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                        constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                        SET_SERVER_NAMES = method6;
                        SNI_HOST_NAME = constructor;
                    } catch (NoSuchMethodException e6) {
                        e = e6;
                        method = null;
                        OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                        method5 = null;
                        SET_APPLICATION_PROTOCOLS = method3;
                        GET_APPLICATION_PROTOCOLS = method4;
                        GET_APPLICATION_PROTOCOL = method2;
                        SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                        SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                        method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                        constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                        SET_SERVER_NAMES = method6;
                        SNI_HOST_NAME = constructor;
                    }
                } catch (ClassNotFoundException e7) {
                    e = e7;
                    method7 = null;
                    method = method2;
                    OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                    method5 = null;
                    SET_APPLICATION_PROTOCOLS = method3;
                    GET_APPLICATION_PROTOCOLS = method4;
                    GET_APPLICATION_PROTOCOL = method2;
                    SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                    SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                    method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                    constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                    SET_SERVER_NAMES = method6;
                    SNI_HOST_NAME = constructor;
                } catch (NoSuchMethodException e8) {
                    e = e8;
                    method2 = null;
                    method = method2;
                    OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                    method5 = null;
                    SET_APPLICATION_PROTOCOLS = method3;
                    GET_APPLICATION_PROTOCOLS = method4;
                    GET_APPLICATION_PROTOCOL = method2;
                    SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                    SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                    method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                    constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                    SET_SERVER_NAMES = method6;
                    SNI_HOST_NAME = constructor;
                }
            } catch (ClassNotFoundException e9) {
                e = e9;
                method4 = null;
                method3 = null;
                method7 = null;
                method = method2;
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                method5 = null;
                SET_APPLICATION_PROTOCOLS = method3;
                GET_APPLICATION_PROTOCOLS = method4;
                GET_APPLICATION_PROTOCOL = method2;
                SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                SET_SERVER_NAMES = method6;
                SNI_HOST_NAME = constructor;
            } catch (NoSuchMethodException e10) {
                e = e10;
                method4 = null;
                method3 = null;
                method2 = null;
                method = method2;
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
                method5 = null;
                SET_APPLICATION_PROTOCOLS = method3;
                GET_APPLICATION_PROTOCOLS = method4;
                GET_APPLICATION_PROTOCOL = method2;
                SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
                SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                SET_SERVER_NAMES = method6;
                SNI_HOST_NAME = constructor;
            }
            SET_APPLICATION_PROTOCOLS = method3;
            GET_APPLICATION_PROTOCOLS = method4;
            GET_APPLICATION_PROTOCOL = method2;
            SSL_SOCKETS_IS_SUPPORTED_SOCKET = method;
            SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
            try {
                method6 = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                try {
                    constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
                } catch (ClassNotFoundException e11) {
                    e = e11;
                    OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 7.0+ APIs", e);
                    SET_SERVER_NAMES = method6;
                    SNI_HOST_NAME = constructor;
                } catch (NoSuchMethodException e12) {
                    e = e12;
                    OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 7.0+ APIs", e);
                    SET_SERVER_NAMES = method6;
                    SNI_HOST_NAME = constructor;
                }
            } catch (ClassNotFoundException e13) {
                e = e13;
                method6 = null;
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 7.0+ APIs", e);
                SET_SERVER_NAMES = method6;
                SNI_HOST_NAME = constructor;
            } catch (NoSuchMethodException e14) {
                e = e14;
                method6 = null;
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 7.0+ APIs", e);
                SET_SERVER_NAMES = method6;
                SNI_HOST_NAME = constructor;
            }
            SET_SERVER_NAMES = method6;
            SNI_HOST_NAME = constructor;
        }

        AndroidNegotiator(Platform platform) {
            super(platform);
        }

        public String negotiate(SSLSocket sSLSocket, String str, List<Protocol> list) throws IOException {
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            return selectedProtocol == null ? OkHttpProtocolNegotiator.super.negotiate(sSLSocket, str, list) : selectedProtocol;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00df, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e5, code lost:
            throw new java.lang.RuntimeException(r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ed, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f3, code lost:
            throw new java.lang.RuntimeException(r9);
         */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00af A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00b0  */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x00df A[ExcHandler: InstantiationException (r9v3 'e' java.lang.InstantiationException A[CUSTOM_DECLARE]), Splitter:B:2:0x000c] */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00ed A[ExcHandler: IllegalAccessException (r9v1 'e' java.lang.IllegalAccessException A[CUSTOM_DECLARE]), Splitter:B:2:0x000c] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void configureTlsExtensions(javax.net.ssl.SSLSocket r9, java.lang.String r10, java.util.List<io.grpc.okhttp.internal.Protocol> r11) {
            /*
                r8 = this;
                java.lang.String[] r0 = io.grpc.okhttp.OkHttpProtocolNegotiator.protocolIds(r11)
                javax.net.ssl.SSLParameters r1 = r9.getSSLParameters()
                r2 = 1
                r3 = 0
                if (r10 == 0) goto L_0x006a
                boolean r4 = isValidHostName(r10)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r4 == 0) goto L_0x006a
                java.lang.reflect.Method r4 = SSL_SOCKETS_IS_SUPPORTED_SOCKET     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r4 == 0) goto L_0x0038
                java.lang.Object[] r5 = new java.lang.Object[]{r9}     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r6 = 0
                java.lang.Object r4 = r4.invoke(r6, r5)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                boolean r4 = r4.booleanValue()     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r4 == 0) goto L_0x0038
                java.lang.reflect.Method r4 = SSL_SOCKETS_SET_USE_SESSION_TICKET     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r5 = 2
                java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r5[r3] = r9     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r5[r2] = r7     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r4.invoke(r6, r5)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                goto L_0x0045
            L_0x0038:
                io.grpc.okhttp.internal.OptionalMethod<java.net.Socket> r4 = SET_USE_SESSION_TICKETS     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r5[r3] = r6     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r4.invokeOptionalWithoutCheckedException(r9, r5)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
            L_0x0045:
                java.lang.reflect.Method r4 = SET_SERVER_NAMES     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r4 == 0) goto L_0x0061
                java.lang.reflect.Constructor<?> r5 = SNI_HOST_NAME     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r5 == 0) goto L_0x0061
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Object[] r10 = new java.lang.Object[]{r10}     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Object r10 = r5.newInstance(r10)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.util.List r10 = java.util.Collections.singletonList(r10)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r6[r3] = r10     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r4.invoke(r1, r6)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                goto L_0x006a
            L_0x0061:
                io.grpc.okhttp.internal.OptionalMethod<java.net.Socket> r4 = SET_HOSTNAME     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Object[] r10 = new java.lang.Object[]{r10}     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                r4.invokeOptionalWithoutCheckedException(r9, r10)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
            L_0x006a:
                java.lang.reflect.Method r10 = GET_APPLICATION_PROTOCOL     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r10 == 0) goto L_0x0093
                java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ InvocationTargetException -> 0x007d, IllegalAccessException -> 0x00ed, InstantiationException -> 0x00df }
                r10.invoke(r9, r4)     // Catch:{ InvocationTargetException -> 0x007d, IllegalAccessException -> 0x00ed, InstantiationException -> 0x00df }
                java.lang.reflect.Method r10 = SET_APPLICATION_PROTOCOLS     // Catch:{ InvocationTargetException -> 0x007d, IllegalAccessException -> 0x00ed, InstantiationException -> 0x00df }
                java.lang.Object[] r4 = new java.lang.Object[]{r0}     // Catch:{ InvocationTargetException -> 0x007d, IllegalAccessException -> 0x00ed, InstantiationException -> 0x00df }
                r10.invoke(r1, r4)     // Catch:{ InvocationTargetException -> 0x007d, IllegalAccessException -> 0x00ed, InstantiationException -> 0x00df }
                goto L_0x0094
            L_0x007d:
                r10 = move-exception
                java.lang.Throwable r2 = r10.getTargetException()     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                boolean r2 = r2 instanceof java.lang.UnsupportedOperationException     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r2 == 0) goto L_0x0092
                java.util.logging.Logger r10 = io.grpc.okhttp.OkHttpProtocolNegotiator.logger     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.util.logging.Level r2 = java.util.logging.Level.FINER     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.String r4 = "setApplicationProtocol unsupported, will try old methods"
                r10.log(r2, r4)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                goto L_0x0093
            L_0x0092:
                throw r10     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
            L_0x0093:
                r2 = r3
            L_0x0094:
                r9.setSSLParameters(r1)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r2 == 0) goto L_0x00b0
                java.lang.reflect.Method r10 = GET_APPLICATION_PROTOCOLS     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r10 == 0) goto L_0x00b0
                javax.net.ssl.SSLParameters r1 = r9.getSSLParameters()     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.Object r10 = r10.invoke(r1, r2)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                java.lang.String[] r10 = (java.lang.String[]) r10     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                boolean r10 = java.util.Arrays.equals(r0, r10)     // Catch:{ IllegalAccessException -> 0x00ed, InvocationTargetException -> 0x00e6, InstantiationException -> 0x00df }
                if (r10 == 0) goto L_0x00b0
                return
            L_0x00b0:
                byte[] r10 = io.grpc.okhttp.internal.Platform.concatLengthPrefixed(r11)
                java.lang.Object[] r10 = new java.lang.Object[]{r10}
                io.grpc.okhttp.internal.Platform r11 = r8.platform
                io.grpc.okhttp.internal.Platform$TlsExtensionType r11 = r11.getTlsExtensionType()
                io.grpc.okhttp.internal.Platform$TlsExtensionType r0 = io.grpc.okhttp.internal.Platform.TlsExtensionType.ALPN_AND_NPN
                if (r11 != r0) goto L_0x00c7
                io.grpc.okhttp.internal.OptionalMethod<java.net.Socket> r11 = SET_ALPN_PROTOCOLS
                r11.invokeWithoutCheckedException(r9, r10)
            L_0x00c7:
                io.grpc.okhttp.internal.Platform r11 = r8.platform
                io.grpc.okhttp.internal.Platform$TlsExtensionType r11 = r11.getTlsExtensionType()
                io.grpc.okhttp.internal.Platform$TlsExtensionType r0 = io.grpc.okhttp.internal.Platform.TlsExtensionType.NONE
                if (r11 == r0) goto L_0x00d7
                io.grpc.okhttp.internal.OptionalMethod<java.net.Socket> r11 = SET_NPN_PROTOCOLS
                r11.invokeWithoutCheckedException(r9, r10)
                return
            L_0x00d7:
                java.lang.RuntimeException r9 = new java.lang.RuntimeException
                java.lang.String r10 = "We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS"
                r9.<init>(r10)
                throw r9
            L_0x00df:
                r9 = move-exception
                java.lang.RuntimeException r10 = new java.lang.RuntimeException
                r10.<init>(r9)
                throw r10
            L_0x00e6:
                r9 = move-exception
                java.lang.RuntimeException r10 = new java.lang.RuntimeException
                r10.<init>(r9)
                throw r10
            L_0x00ed:
                r9 = move-exception
                java.lang.RuntimeException r10 = new java.lang.RuntimeException
                r10.<init>(r9)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpProtocolNegotiator.AndroidNegotiator.configureTlsExtensions(javax.net.ssl.SSLSocket, java.lang.String, java.util.List):void");
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            Method method = GET_APPLICATION_PROTOCOL;
            if (method != null) {
                try {
                    return (String) method.invoke(sSLSocket, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    if (e2.getTargetException() instanceof UnsupportedOperationException) {
                        OkHttpProtocolNegotiator.logger.log(Level.FINER, "Socket unsupported for getApplicationProtocol, will try old methods");
                    } else {
                        throw new RuntimeException(e2);
                    }
                }
            }
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.ALPN_AND_NPN) {
                try {
                    byte[] bArr = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                    if (bArr != null) {
                        return new String(bArr, Util.UTF_8);
                    }
                } catch (Exception e3) {
                    OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getAlpnSelectedProtocol()", e3);
                }
            }
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.NONE) {
                return null;
            }
            try {
                byte[] bArr2 = (byte[]) GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                if (bArr2 != null) {
                    return new String(bArr2, Util.UTF_8);
                }
                return null;
            } catch (Exception e4) {
                OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getNpnSelectedProtocol()", e4);
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static String[] protocolIds(List<Protocol> list) {
        ArrayList arrayList = new ArrayList();
        for (Protocol protocol : list) {
            arrayList.add(protocol.toString());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    static boolean isValidHostName(String str) {
        if (str.contains("_")) {
            return false;
        }
        try {
            GrpcUtil.checkAuthority(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }
}
