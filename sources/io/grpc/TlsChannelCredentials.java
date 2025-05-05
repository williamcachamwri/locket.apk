package io.grpc;

import com.google.common.io.ByteStreams;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

public final class TlsChannelCredentials extends ChannelCredentials {
    private final byte[] certificateChain;
    private final boolean fakeFeature;
    private final List<KeyManager> keyManagers;
    private final byte[] privateKey;
    private final String privateKeyPassword;
    private final byte[] rootCertificates;
    private final List<TrustManager> trustManagers;

    public enum Feature {
        FAKE,
        MTLS,
        CUSTOM_MANAGERS
    }

    public ChannelCredentials withoutBearerTokens() {
        return this;
    }

    public static ChannelCredentials create() {
        return newBuilder().build();
    }

    TlsChannelCredentials(Builder builder) {
        this.fakeFeature = builder.fakeFeature;
        this.certificateChain = builder.certificateChain;
        this.privateKey = builder.privateKey;
        this.privateKeyPassword = builder.privateKeyPassword;
        this.keyManagers = builder.keyManagers;
        this.rootCertificates = builder.rootCertificates;
        this.trustManagers = builder.trustManagers;
    }

    public byte[] getCertificateChain() {
        byte[] bArr = this.certificateChain;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getPrivateKey() {
        byte[] bArr = this.privateKey;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String getPrivateKeyPassword() {
        return this.privateKeyPassword;
    }

    public List<KeyManager> getKeyManagers() {
        return this.keyManagers;
    }

    public byte[] getRootCertificates() {
        byte[] bArr = this.rootCertificates;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public List<TrustManager> getTrustManagers() {
        return this.trustManagers;
    }

    public Set<Feature> incomprehensible(Set<Feature> set) {
        EnumSet<E> noneOf = EnumSet.noneOf(Feature.class);
        if (this.fakeFeature) {
            requiredFeature(set, noneOf, Feature.FAKE);
        }
        if (!(this.rootCertificates == null && this.privateKey == null && this.keyManagers == null)) {
            requiredFeature(set, noneOf, Feature.MTLS);
        }
        if (!(this.keyManagers == null && this.trustManagers == null)) {
            requiredFeature(set, noneOf, Feature.CUSTOM_MANAGERS);
        }
        return Collections.unmodifiableSet(noneOf);
    }

    private static void requiredFeature(Set<Feature> set, Set<Feature> set2, Feature feature) {
        if (!set.contains(feature)) {
            set2.add(feature);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public byte[] certificateChain;
        /* access modifiers changed from: private */
        public boolean fakeFeature;
        /* access modifiers changed from: private */
        public List<KeyManager> keyManagers;
        /* access modifiers changed from: private */
        public byte[] privateKey;
        /* access modifiers changed from: private */
        public String privateKeyPassword;
        /* access modifiers changed from: private */
        public byte[] rootCertificates;
        /* access modifiers changed from: private */
        public List<TrustManager> trustManagers;

        private Builder() {
        }

        public Builder requireFakeFeature() {
            this.fakeFeature = true;
            return this;
        }

        public Builder keyManager(File file, File file2) throws IOException {
            return keyManager(file, file2, (String) null);
        }

        public Builder keyManager(File file, File file2, String str) throws IOException {
            FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
            try {
                create = SentryFileInputStream.Factory.create(new FileInputStream(file2), file2);
                return keyManager((InputStream) create, (InputStream) create, str);
            } catch (Throwable th) {
                throw th;
            } finally {
                create.close();
            }
        }

        public Builder keyManager(InputStream inputStream, InputStream inputStream2) throws IOException {
            return keyManager(inputStream, inputStream2, (String) null);
        }

        public Builder keyManager(InputStream inputStream, InputStream inputStream2, String str) throws IOException {
            byte[] byteArray = ByteStreams.toByteArray(inputStream);
            byte[] byteArray2 = ByteStreams.toByteArray(inputStream2);
            clearKeyManagers();
            this.certificateChain = byteArray;
            this.privateKey = byteArray2;
            this.privateKeyPassword = str;
            return this;
        }

        public Builder keyManager(KeyManager... keyManagerArr) {
            List<KeyManager> unmodifiableList = Collections.unmodifiableList(new ArrayList(Arrays.asList(keyManagerArr)));
            clearKeyManagers();
            this.keyManagers = unmodifiableList;
            return this;
        }

        private void clearKeyManagers() {
            this.certificateChain = null;
            this.privateKey = null;
            this.privateKeyPassword = null;
            this.keyManagers = null;
        }

        public Builder trustManager(File file) throws IOException {
            FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
            try {
                return trustManager((InputStream) create);
            } finally {
                create.close();
            }
        }

        public Builder trustManager(InputStream inputStream) throws IOException {
            byte[] byteArray = ByteStreams.toByteArray(inputStream);
            clearTrustManagers();
            this.rootCertificates = byteArray;
            return this;
        }

        public Builder trustManager(TrustManager... trustManagerArr) {
            List<TrustManager> unmodifiableList = Collections.unmodifiableList(new ArrayList(Arrays.asList(trustManagerArr)));
            clearTrustManagers();
            this.trustManagers = unmodifiableList;
            return this;
        }

        private void clearTrustManagers() {
            this.rootCertificates = null;
            this.trustManagers = null;
        }

        public ChannelCredentials build() {
            return new TlsChannelCredentials(this);
        }
    }
}
