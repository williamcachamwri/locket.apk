package io.grpc.util;

import com.google.common.base.Preconditions;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

public final class AdvancedTlsX509KeyManager extends X509ExtendedKeyManager {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(AdvancedTlsX509KeyManager.class.getName());
    private volatile KeyInfo keyInfo;

    public interface Closeable extends java.io.Closeable {
        void close();
    }

    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        return "default";
    }

    public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return "default";
    }

    public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
        return "default";
    }

    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        return "default";
    }

    public PrivateKey getPrivateKey(String str) {
        if (str.equals("default")) {
            return this.keyInfo.key;
        }
        return null;
    }

    public X509Certificate[] getCertificateChain(String str) {
        if (str.equals("default")) {
            return (X509Certificate[]) Arrays.copyOf(this.keyInfo.certs, this.keyInfo.certs.length);
        }
        return null;
    }

    public String[] getClientAliases(String str, Principal[] principalArr) {
        return new String[]{"default"};
    }

    public String[] getServerAliases(String str, Principal[] principalArr) {
        return new String[]{"default"};
    }

    public void updateIdentityCredentials(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
        this.keyInfo = new KeyInfo((PrivateKey) Preconditions.checkNotNull(privateKey, "key"), (X509Certificate[]) Preconditions.checkNotNull(x509CertificateArr, "certs"));
    }

    public Closeable updateIdentityCredentialsFromFile(File file, File file2, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) throws IOException, GeneralSecurityException {
        if (readAndUpdate(file, file2, 0, 0).success) {
            final ScheduledFuture<?> scheduleWithFixedDelay = scheduledExecutorService.scheduleWithFixedDelay(new LoadFilePathExecution(file, file2), j, j, timeUnit);
            return new Closeable() {
                public void close() {
                    scheduleWithFixedDelay.cancel(false);
                }
            };
        }
        throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
    }

    public void updateIdentityCredentialsFromFile(File file, File file2) throws IOException, GeneralSecurityException {
        if (!readAndUpdate(file, file2, 0, 0).success) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
    }

    private static class KeyInfo {
        final X509Certificate[] certs;
        final PrivateKey key;

        public KeyInfo(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.key = privateKey;
            this.certs = x509CertificateArr;
        }
    }

    private class LoadFilePathExecution implements Runnable {
        File certFile;
        long currentCertTime = 0;
        long currentKeyTime = 0;
        File keyFile;

        public LoadFilePathExecution(File file, File file2) {
            this.keyFile = file;
            this.certFile = file2;
        }

        public void run() {
            try {
                UpdateResult access$000 = AdvancedTlsX509KeyManager.this.readAndUpdate(this.keyFile, this.certFile, this.currentKeyTime, this.currentCertTime);
                if (access$000.success) {
                    this.currentKeyTime = access$000.keyTime;
                    this.currentCertTime = access$000.certTime;
                }
            } catch (IOException | GeneralSecurityException e) {
                AdvancedTlsX509KeyManager.log.log(Level.SEVERE, "Failed refreshing private key and certificate chain from files. Using previous ones", e);
            }
        }
    }

    private static class UpdateResult {
        long certTime;
        long keyTime;
        boolean success;

        public UpdateResult(boolean z, long j, long j2) {
            this.success = z;
            this.keyTime = j;
            this.certTime = j2;
        }
    }

    /* access modifiers changed from: private */
    public UpdateResult readAndUpdate(File file, File file2, long j, long j2) throws IOException, GeneralSecurityException {
        UpdateResult updateResult;
        File file3 = file;
        File file4 = file2;
        long lastModified = file.lastModified();
        long lastModified2 = file2.lastModified();
        if (lastModified == j || lastModified2 == j2) {
            return new UpdateResult(false, j, j2);
        }
        FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
        try {
            PrivateKey privateKey = CertificateUtils.getPrivateKey(create);
            FileInputStream create2 = SentryFileInputStream.Factory.create(new FileInputStream(file2), file2);
            try {
                try {
                    updateIdentityCredentials(privateKey, CertificateUtils.getX509Certificates(create2));
                    updateResult = new UpdateResult(true, lastModified, lastModified2);
                } catch (Throwable th) {
                    th = th;
                    create2.close();
                    throw th;
                }
                try {
                    create2.close();
                    create.close();
                    return updateResult;
                } catch (Throwable th2) {
                    th = th2;
                    create.close();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                create2.close();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            create.close();
            throw th;
        }
    }
}
