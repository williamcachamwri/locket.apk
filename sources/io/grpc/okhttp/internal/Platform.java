package io.grpc.okhttp.internal;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

public class Platform {
    private static final String[] ANDROID_SECURITY_PROVIDERS = {"com.google.android.gms.org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLProvider", "org.apache.harmony.xnet.provider.jsse.OpenSSLProvider", "com.google.android.libraries.stitch.sslguard.SslGuardProvider"};
    private static final Platform PLATFORM = findPlatform();
    public static final Logger logger = Logger.getLogger(Platform.class.getName());
    private final Provider sslProvider;

    public enum TlsExtensionType {
        ALPN_AND_NPN,
        NPN,
        NONE
    }

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public void tagSocket(Socket socket) throws SocketException {
    }

    public void untagSocket(Socket socket) throws SocketException {
    }

    public static Platform get() {
        return PLATFORM;
    }

    public Platform(Provider provider) {
        this.sslProvider = provider;
    }

    public void logW(String str) {
        System.out.println(str);
    }

    public Provider getProvider() {
        return this.sslProvider;
    }

    public TlsExtensionType getTlsExtensionType() {
        return TlsExtensionType.NONE;
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0095  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static io.grpc.okhttp.internal.Platform findPlatform() {
        /*
            java.security.Provider r7 = getAndroidSecurityProvider()
            r0 = 1
            r1 = 0
            r2 = 0
            if (r7 == 0) goto L_0x00a4
            io.grpc.okhttp.internal.OptionalMethod r3 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r4 = new java.lang.Class[r0]
            java.lang.Class r5 = java.lang.Boolean.TYPE
            r4[r2] = r5
            java.lang.String r5 = "setUseSessionTickets"
            r3.<init>(r1, r5, r4)
            io.grpc.okhttp.internal.OptionalMethod r4 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r5 = new java.lang.Class[r0]
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r2] = r6
            java.lang.String r6 = "setHostname"
            r4.<init>(r1, r6, r5)
            io.grpc.okhttp.internal.OptionalMethod r5 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.String r6 = "getAlpnSelectedProtocol"
            java.lang.Class[] r8 = new java.lang.Class[r2]
            java.lang.Class<byte[]> r9 = byte[].class
            r5.<init>(r9, r6, r8)
            io.grpc.okhttp.internal.OptionalMethod r6 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r8 = new java.lang.Class[r0]
            r8[r2] = r9
            java.lang.String r9 = "setAlpnProtocols"
            r6.<init>(r1, r9, r8)
            java.lang.String r8 = "android.net.TrafficStats"
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0059 }
            java.lang.String r9 = "tagSocket"
            java.lang.Class[] r10 = new java.lang.Class[r0]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0059 }
            java.lang.Class<java.net.Socket> r11 = java.net.Socket.class
            r10[r2] = r11     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0059 }
            java.lang.reflect.Method r9 = r8.getMethod(r9, r10)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0059 }
            java.lang.String r10 = "untagSocket"
            java.lang.Class[] r0 = new java.lang.Class[r0]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x005a }
            java.lang.Class<java.net.Socket> r11 = java.net.Socket.class
            r0[r2] = r11     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x005a }
            java.lang.reflect.Method r0 = r8.getMethod(r10, r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x005a }
            r8 = r0
            goto L_0x005b
        L_0x0059:
            r9 = r1
        L_0x005a:
            r8 = r1
        L_0x005b:
            java.lang.String r0 = r7.getName()
            java.lang.String r1 = "GmsCore_OpenSSL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0095
            java.lang.String r0 = r7.getName()
            java.lang.String r1 = "Conscrypt"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0095
            java.lang.String r0 = r7.getName()
            java.lang.String r1 = "Ssl_Guard"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0080
            goto L_0x0095
        L_0x0080:
            boolean r0 = isAtLeastAndroid5()
            if (r0 == 0) goto L_0x0089
            io.grpc.okhttp.internal.Platform$TlsExtensionType r0 = io.grpc.okhttp.internal.Platform.TlsExtensionType.ALPN_AND_NPN
            goto L_0x0097
        L_0x0089:
            boolean r0 = isAtLeastAndroid41()
            if (r0 == 0) goto L_0x0092
            io.grpc.okhttp.internal.Platform$TlsExtensionType r0 = io.grpc.okhttp.internal.Platform.TlsExtensionType.NPN
            goto L_0x0097
        L_0x0092:
            io.grpc.okhttp.internal.Platform$TlsExtensionType r0 = io.grpc.okhttp.internal.Platform.TlsExtensionType.NONE
            goto L_0x0097
        L_0x0095:
            io.grpc.okhttp.internal.Platform$TlsExtensionType r0 = io.grpc.okhttp.internal.Platform.TlsExtensionType.ALPN_AND_NPN
        L_0x0097:
            r10 = r0
            io.grpc.okhttp.internal.Platform$Android r11 = new io.grpc.okhttp.internal.Platform$Android
            r0 = r11
            r1 = r3
            r2 = r4
            r3 = r9
            r4 = r8
            r8 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r11
        L_0x00a4:
            javax.net.ssl.SSLContext r3 = javax.net.ssl.SSLContext.getDefault()     // Catch:{ NoSuchAlgorithmException -> 0x0132 }
            java.security.Provider r3 = r3.getProvider()     // Catch:{ NoSuchAlgorithmException -> 0x0132 }
            java.lang.String r4 = "TLS"
            javax.net.ssl.SSLContext r4 = javax.net.ssl.SSLContext.getInstance(r4, r3)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            r4.init(r1, r1, r1)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            javax.net.ssl.SSLEngine r4 = r4.createSSLEngine()     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            io.grpc.okhttp.internal.Platform$1 r5 = new io.grpc.okhttp.internal.Platform$1     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            r5.<init>()     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            java.lang.Object r5 = java.security.AccessController.doPrivileged(r5)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            java.lang.reflect.Method r5 = (java.lang.reflect.Method) r5     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            r5.invoke(r4, r6)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            io.grpc.okhttp.internal.Platform$2 r4 = new io.grpc.okhttp.internal.Platform$2     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            r4.<init>()     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            java.lang.Object r4 = java.security.AccessController.doPrivileged(r4)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            java.lang.reflect.Method r4 = (java.lang.reflect.Method) r4     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            io.grpc.okhttp.internal.Platform$3 r5 = new io.grpc.okhttp.internal.Platform$3     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            r5.<init>()     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            java.lang.Object r5 = java.security.AccessController.doPrivileged(r5)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            java.lang.reflect.Method r5 = (java.lang.reflect.Method) r5     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            io.grpc.okhttp.internal.Platform$JdkAlpnPlatform r6 = new io.grpc.okhttp.internal.Platform$JdkAlpnPlatform     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            r6.<init>(r3, r4, r5)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00e5 }
            return r6
        L_0x00e5:
            java.lang.String r1 = "org.eclipse.jetty.alpn.ALPN"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.String r4 = "org.eclipse.jetty.alpn.ALPN$Provider"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.String r5 = "org.eclipse.jetty.alpn.ALPN$ClientProvider"
            java.lang.Class r8 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.String r5 = "org.eclipse.jetty.alpn.ALPN$ServerProvider"
            java.lang.Class r9 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.String r5 = "put"
            r6 = 2
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r6[r2] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            r6[r0] = r4     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.reflect.Method r5 = r1.getMethod(r5, r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.String r4 = "get"
            java.lang.Class[] r6 = new java.lang.Class[r0]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r6[r2] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.reflect.Method r6 = r1.getMethod(r4, r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.String r4 = "remove"
            java.lang.Class[] r0 = new java.lang.Class[r0]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r0[r2] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            java.lang.reflect.Method r7 = r1.getMethod(r4, r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform r0 = new io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            r4 = r0
            r10 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x012c }
            return r0
        L_0x012c:
            io.grpc.okhttp.internal.Platform r0 = new io.grpc.okhttp.internal.Platform
            r0.<init>(r3)
            return r0
        L_0x0132:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.internal.Platform.findPlatform():io.grpc.okhttp.internal.Platform");
    }

    private static boolean isAtLeastAndroid5() {
        try {
            Platform.class.getClassLoader().loadClass("android.net.Network");
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Can't find class", e);
            return false;
        }
    }

    private static boolean isAtLeastAndroid41() {
        try {
            Platform.class.getClassLoader().loadClass("android.app.ActivityOptions");
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Can't find class", e);
            return false;
        }
    }

    private static Provider getAndroidSecurityProvider() {
        for (Provider provider : Security.getProviders()) {
            for (String str : ANDROID_SECURITY_PROVIDERS) {
                if (str.equals(provider.getClass().getName())) {
                    logger.log(Level.FINE, "Found registered provider {0}", str);
                    return provider;
                }
            }
        }
        logger.log(Level.WARNING, "Unable to find Conscrypt");
        return null;
    }

    private static class Android extends Platform {
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final TlsExtensionType tlsExtensionType;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(OptionalMethod<Socket> optionalMethod, OptionalMethod<Socket> optionalMethod2, Method method, Method method2, OptionalMethod<Socket> optionalMethod3, OptionalMethod<Socket> optionalMethod4, Provider provider, TlsExtensionType tlsExtensionType2) {
            super(provider);
            this.setUseSessionTickets = optionalMethod;
            this.setHostname = optionalMethod2;
            this.trafficStatsTagSocket = method;
            this.trafficStatsUntagSocket = method2;
            this.getAlpnSelectedProtocol = optionalMethod3;
            this.setAlpnProtocols = optionalMethod4;
            this.tlsExtensionType = tlsExtensionType2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return this.tlsExtensionType;
        }

        public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (SecurityException e) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e);
                throw iOException;
            }
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sSLSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
            if (this.setAlpnProtocols.isSupported(sSLSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sSLSocket, concatLengthPrefixed(list));
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            byte[] bArr;
            if (this.getAlpnSelectedProtocol.isSupported(sSLSocket) && (bArr = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sSLSocket, new Object[0])) != null) {
                return new String(bArr, Util.UTF_8);
            }
            return null;
        }

        public void tagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsTagSocket;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }

        public void untagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsUntagSocket;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }
    }

    private static class JdkAlpnPlatform extends Platform {
        private final Method getApplicationProtocol;
        private final Method setApplicationProtocols;

        private JdkAlpnPlatform(Provider provider, Method method, Method method2) {
            super(provider);
            this.setApplicationProtocols = method;
            this.getApplicationProtocol = method2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return TlsExtensionType.ALPN_AND_NPN;
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            ArrayList arrayList = new ArrayList(list.size());
            for (Protocol next : list) {
                if (next != Protocol.HTTP_1_0) {
                    arrayList.add(next.toString());
                }
            }
            try {
                this.setApplicationProtocols.invoke(sSLParameters, new Object[]{arrayList.toArray(new String[arrayList.size()])});
                sSLSocket.setSSLParameters(sSLParameters);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                return (String) this.getApplicationProtocol.invoke(sSLSocket, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    private static class JdkWithJettyBootPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2, Provider provider) {
            super(provider);
            this.putMethod = method;
            this.getMethod = method2;
            this.removeMethod = method3;
            this.clientProviderClass = cls;
            this.serverProviderClass = cls2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return TlsExtensionType.ALPN_AND_NPN;
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = list.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.toString());
                }
            }
            try {
                this.putMethod.invoke((Object) null, new Object[]{sSLSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(arrayList))});
            } catch (InvocationTargetException e) {
                throw new AssertionError(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void afterHandshake(SSLSocket sSLSocket) {
            try {
                this.removeMethod.invoke((Object) null, new Object[]{sSLSocket});
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e) {
                logger.log(Level.FINE, "Failed to remove SSLSocket from Jetty ALPN", e);
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                JettyNegoProvider jettyNegoProvider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke((Object) null, new Object[]{sSLSocket}));
                if (!jettyNegoProvider.unsupported && jettyNegoProvider.selected == null) {
                    logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                } else if (jettyNegoProvider.unsupported) {
                    return null;
                } else {
                    return jettyNegoProvider.selected;
                }
            } catch (InvocationTargetException unused) {
                throw new AssertionError();
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        }
    }

    private static class JettyNegoProvider implements InvocationHandler {
        private final List<String> protocols;
        /* access modifiers changed from: private */
        public String selected;
        /* access modifiers changed from: private */
        public boolean unsupported;

        public JettyNegoProvider(List<String> list) {
            this.protocols = list;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = Util.EMPTY_STRING_ARRAY;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.protocols;
            } else {
                if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1) {
                    Object obj2 = objArr[0];
                    if (obj2 instanceof List) {
                        List list = (List) obj2;
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            if (this.protocols.contains(list.get(i))) {
                                String str = (String) list.get(i);
                                this.selected = str;
                                return str;
                            }
                        }
                        String str2 = this.protocols.get(0);
                        this.selected = str2;
                        return str2;
                    }
                }
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                }
                this.selected = (String) objArr[0];
                return null;
            }
        }
    }

    public static byte[] concatLengthPrefixed(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }
}
