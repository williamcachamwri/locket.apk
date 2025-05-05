package com.google.firestore.bundle;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class BundledDocumentMetadata extends GeneratedMessageLite<BundledDocumentMetadata, Builder> implements BundledDocumentMetadataOrBuilder {
    /* access modifiers changed from: private */
    public static final BundledDocumentMetadata DEFAULT_INSTANCE;
    public static final int EXISTS_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<BundledDocumentMetadata> PARSER = null;
    public static final int QUERIES_FIELD_NUMBER = 4;
    public static final int READ_TIME_FIELD_NUMBER = 2;
    private int bitField0_;
    private boolean exists_;
    private String name_ = "";
    private Internal.ProtobufList<String> queries_ = GeneratedMessageLite.emptyProtobufList();
    private Timestamp readTime_;

    private BundledDocumentMetadata() {
    }

    public String getName() {
        return this.name_;
    }

    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
    }

    public boolean hasReadTime() {
        return (this.bitField0_ & 1) != 0;
    }

    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.readTime_ = timestamp;
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.readTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.getDefaultInstance()) {
            this.readTime_ = timestamp;
        } else {
            this.readTime_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.readTime_).mergeFrom(timestamp)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    public void clearReadTime() {
        this.readTime_ = null;
        this.bitField0_ &= -2;
    }

    public boolean getExists() {
        return this.exists_;
    }

    /* access modifiers changed from: private */
    public void setExists(boolean z) {
        this.exists_ = z;
    }

    /* access modifiers changed from: private */
    public void clearExists() {
        this.exists_ = false;
    }

    public List<String> getQueriesList() {
        return this.queries_;
    }

    public int getQueriesCount() {
        return this.queries_.size();
    }

    public String getQueries(int i) {
        return (String) this.queries_.get(i);
    }

    public ByteString getQueriesBytes(int i) {
        return ByteString.copyFromUtf8((String) this.queries_.get(i));
    }

    private void ensureQueriesIsMutable() {
        Internal.ProtobufList<String> protobufList = this.queries_;
        if (!protobufList.isModifiable()) {
            this.queries_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setQueries(int i, String str) {
        str.getClass();
        ensureQueriesIsMutable();
        this.queries_.set(i, str);
    }

    /* access modifiers changed from: private */
    public void addQueries(String str) {
        str.getClass();
        ensureQueriesIsMutable();
        this.queries_.add(str);
    }

    /* access modifiers changed from: private */
    public void addAllQueries(Iterable<String> iterable) {
        ensureQueriesIsMutable();
        AbstractMessageLite.addAll(iterable, this.queries_);
    }

    /* access modifiers changed from: private */
    public void clearQueries() {
        this.queries_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addQueriesBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        ensureQueriesIsMutable();
        this.queries_.add(byteString.toStringUtf8());
    }

    public static BundledDocumentMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BundledDocumentMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BundledDocumentMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BundledDocumentMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseFrom(InputStream inputStream) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundledDocumentMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BundledDocumentMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundledDocumentMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledDocumentMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BundledDocumentMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundledDocumentMetadata bundledDocumentMetadata) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(bundledDocumentMetadata);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundledDocumentMetadata, Builder> implements BundledDocumentMetadataOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(BundledDocumentMetadata.DEFAULT_INSTANCE);
        }

        public String getName() {
            return ((BundledDocumentMetadata) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((BundledDocumentMetadata) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setNameBytes(byteString);
            return this;
        }

        public boolean hasReadTime() {
            return ((BundledDocumentMetadata) this.instance).hasReadTime();
        }

        public Timestamp getReadTime() {
            return ((BundledDocumentMetadata) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setReadTime((Timestamp) builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearReadTime();
            return this;
        }

        public boolean getExists() {
            return ((BundledDocumentMetadata) this.instance).getExists();
        }

        public Builder setExists(boolean z) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setExists(z);
            return this;
        }

        public Builder clearExists() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearExists();
            return this;
        }

        public List<String> getQueriesList() {
            return Collections.unmodifiableList(((BundledDocumentMetadata) this.instance).getQueriesList());
        }

        public int getQueriesCount() {
            return ((BundledDocumentMetadata) this.instance).getQueriesCount();
        }

        public String getQueries(int i) {
            return ((BundledDocumentMetadata) this.instance).getQueries(i);
        }

        public ByteString getQueriesBytes(int i) {
            return ((BundledDocumentMetadata) this.instance).getQueriesBytes(i);
        }

        public Builder setQueries(int i, String str) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setQueries(i, str);
            return this;
        }

        public Builder addQueries(String str) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addQueries(str);
            return this;
        }

        public Builder addAllQueries(Iterable<String> iterable) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addAllQueries(iterable);
            return this;
        }

        public Builder clearQueries() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearQueries();
            return this;
        }

        public Builder addQueriesBytes(ByteString byteString) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addQueriesBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundledDocumentMetadata$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.bundle.BundledDocumentMetadata.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BundledDocumentMetadata();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002ဉ\u0000\u0003\u0007\u0004Ț", new Object[]{"bitField0_", "name_", "readTime_", "exists_", "queries_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundledDocumentMetadata> parser = PARSER;
                if (parser == null) {
                    synchronized (BundledDocumentMetadata.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        BundledDocumentMetadata bundledDocumentMetadata = new BundledDocumentMetadata();
        DEFAULT_INSTANCE = bundledDocumentMetadata;
        GeneratedMessageLite.registerDefaultInstance(BundledDocumentMetadata.class, bundledDocumentMetadata);
    }

    public static BundledDocumentMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundledDocumentMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
