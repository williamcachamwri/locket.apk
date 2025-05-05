package io.grpc.util;

import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;

public final class AdvancedTlsX509TrustManager extends X509ExtendedTrustManager {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(AdvancedTlsX509TrustManager.class.getName());
    private volatile X509ExtendedTrustManager delegateManager;
    private final SslSocketAndEnginePeerVerifier socketAndEnginePeerVerifier;
    private final Verification verification;

    public interface Closeable extends java.io.Closeable {
        void close();
    }

    public interface SslSocketAndEnginePeerVerifier {
        void verifyPeerCertificate(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException;

        void verifyPeerCertificate(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException;
    }

    public enum Verification {
        CERTIFICATE_AND_HOST_NAME_VERIFICATION,
        CERTIFICATE_ONLY_VERIFICATION,
        INSECURELY_SKIP_ALL_VERIFICATION
    }

    private AdvancedTlsX509TrustManager(Verification verification2, SslSocketAndEnginePeerVerifier sslSocketAndEnginePeerVerifier) throws CertificateException {
        this.delegateManager = null;
        this.verification = verification2;
        this.socketAndEnginePeerVerifier = sslSocketAndEnginePeerVerifier;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Not enough information to validate peer. SSLEngine or Socket required.");
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        checkTrusted(x509CertificateArr, str, (SSLEngine) null, socket, false);
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        checkTrusted(x509CertificateArr, str, sSLEngine, (Socket) null, false);
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        checkTrusted(x509CertificateArr, str, sSLEngine, (Socket) null, true);
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Not enough information to validate peer. SSLEngine or Socket required.");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        checkTrusted(x509CertificateArr, str, (SSLEngine) null, socket, true);
    }

    public X509Certificate[] getAcceptedIssuers() {
        if (this.delegateManager == null) {
            return new X509Certificate[0];
        }
        return this.delegateManager.getAcceptedIssuers();
    }

    public void useSystemDefaultTrustCerts() throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        this.delegateManager = createDelegateTrustManager((KeyStore) null);
    }

    public void updateTrustCredentials(X509Certificate[] x509CertificateArr) throws IOException, GeneralSecurityException {
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        instance.load((InputStream) null, (char[]) null);
        int i = 1;
        for (X509Certificate certificateEntry : x509CertificateArr) {
            instance.setCertificateEntry(Integer.toString(i), certificateEntry);
            i++;
        }
        this.delegateManager = createDelegateTrustManager(instance);
    }

    private static X509ExtendedTrustManager createDelegateTrustManager(KeyStore keyStore) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        X509ExtendedTrustManager x509ExtendedTrustManager;
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        TrustManager[] trustManagers = instance.getTrustManagers();
        int i = 0;
        while (true) {
            if (i >= trustManagers.length) {
                x509ExtendedTrustManager = null;
                break;
            }
            TrustManager trustManager = trustManagers[i];
            if (trustManager instanceof X509ExtendedTrustManager) {
                x509ExtendedTrustManager = (X509ExtendedTrustManager) trustManager;
                break;
            }
            i++;
        }
        if (x509ExtendedTrustManager != null) {
            return x509ExtendedTrustManager;
        }
        throw new CertificateException("Failed to find X509ExtendedTrustManager with default TrustManager algorithm " + TrustManagerFactory.getDefaultAlgorithm());
    }

    private void checkTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine, Socket socket, boolean z) throws CertificateException {
        if (x509CertificateArr == null || x509CertificateArr.length == 0) {
            throw new IllegalArgumentException("Want certificate verification but got null or empty certificates");
        } else if (sSLEngine == null && socket == null) {
            throw new CertificateException("Not enough information to validate peer. SSLEngine or Socket required.");
        } else {
            if (this.verification != Verification.INSECURELY_SKIP_ALL_VERIFICATION) {
                X509ExtendedTrustManager x509ExtendedTrustManager = this.delegateManager;
                if (x509ExtendedTrustManager == null) {
                    throw new CertificateException("No trust roots configured");
                } else if (z) {
                    String str2 = this.verification == Verification.CERTIFICATE_AND_HOST_NAME_VERIFICATION ? "HTTPS" : "";
                    if (sSLEngine != null) {
                        SSLParameters sSLParameters = sSLEngine.getSSLParameters();
                        sSLParameters.setEndpointIdentificationAlgorithm(str2);
                        sSLEngine.setSSLParameters(sSLParameters);
                        x509ExtendedTrustManager.checkServerTrusted(x509CertificateArr, str, sSLEngine);
                    } else if (socket instanceof SSLSocket) {
                        SSLSocket sSLSocket = (SSLSocket) socket;
                        SSLParameters sSLParameters2 = sSLSocket.getSSLParameters();
                        sSLParameters2.setEndpointIdentificationAlgorithm(str2);
                        sSLSocket.setSSLParameters(sSLParameters2);
                        x509ExtendedTrustManager.checkServerTrusted(x509CertificateArr, str, sSLSocket);
                    } else {
                        throw new CertificateException("socket is not a type of SSLSocket");
                    }
                } else {
                    x509ExtendedTrustManager.checkClientTrusted(x509CertificateArr, str, sSLEngine);
                }
            }
            SslSocketAndEnginePeerVerifier sslSocketAndEnginePeerVerifier = this.socketAndEnginePeerVerifier;
            if (sslSocketAndEnginePeerVerifier == null) {
                return;
            }
            if (sSLEngine != null) {
                sslSocketAndEnginePeerVerifier.verifyPeerCertificate(x509CertificateArr, str, sSLEngine);
            } else {
                sslSocketAndEnginePeerVerifier.verifyPeerCertificate(x509CertificateArr, str, socket);
            }
        }
    }

    public Closeable updateTrustCredentialsFromFile(File file, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) throws IOException, GeneralSecurityException {
        if (readAndUpdate(file, 0) != 0) {
            final ScheduledFuture<?> scheduleWithFixedDelay = scheduledExecutorService.scheduleWithFixedDelay(new LoadFilePathExecution(file), j, j, timeUnit);
            return new Closeable() {
                public void close() {
                    scheduleWithFixedDelay.cancel(false);
                }
            };
        }
        throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
    }

    private class LoadFilePathExecution implements Runnable {
        long currentTime = 0;
        File file;

        public LoadFilePathExecution(File file2) {
            this.file = file2;
        }

        public void run() {
            try {
                this.currentTime = AdvancedTlsX509TrustManager.this.readAndUpdate(this.file, this.currentTime);
            } catch (IOException | GeneralSecurityException e) {
                AdvancedTlsX509TrustManager.log.log(Level.SEVERE, "Failed refreshing trust CAs from file. Using previous CAs", e);
            }
        }
    }

    public void updateTrustCredentialsFromFile(File file) throws IOException, GeneralSecurityException {
        if (readAndUpdate(file, 0) == 0) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
    }

    /* access modifiers changed from: private */
    public long readAndUpdate(File file, long j) throws IOException, GeneralSecurityException {
        long lastModified = file.lastModified();
        if (lastModified == j) {
            return j;
        }
        FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
        try {
            updateTrustCredentials(CertificateUtils.getX509Certificates(create));
            return lastModified;
        } finally {
            create.close();
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private SslSocketAndEnginePeerVerifier socketAndEnginePeerVerifier;
        private Verification verification;

        private Builder() {
            this.verification = Verification.CERTIFICATE_AND_HOST_NAME_VERIFICATION;
        }

        public Builder setVerification(Verification verification2) {
            this.verification = verification2;
            return this;
        }

        public Builder setSslSocketAndEnginePeerVerifier(SslSocketAndEnginePeerVerifier sslSocketAndEnginePeerVerifier) {
            this.socketAndEnginePeerVerifier = sslSocketAndEnginePeerVerifier;
            return this;
        }

        public AdvancedTlsX509TrustManager build() throws CertificateException {
            return new AdvancedTlsX509TrustManager(this.verification, this.socketAndEnginePeerVerifier);
        }
    }
}
