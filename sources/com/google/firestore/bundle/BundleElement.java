package com.google.firestore.bundle;

import com.google.firestore.bundle.BundleMetadata;
import com.google.firestore.bundle.BundledDocumentMetadata;
import com.google.firestore.bundle.NamedQuery;
import com.google.firestore.v1.Document;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BundleElement extends GeneratedMessageLite<BundleElement, Builder> implements BundleElementOrBuilder {
    /* access modifiers changed from: private */
    public static final BundleElement DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 4;
    public static final int DOCUMENT_METADATA_FIELD_NUMBER = 3;
    public static final int METADATA_FIELD_NUMBER = 1;
    public static final int NAMED_QUERY_FIELD_NUMBER = 2;
    private static volatile Parser<BundleElement> PARSER;
    private int elementTypeCase_ = 0;
    private Object elementType_;

    private BundleElement() {
    }

    public enum ElementTypeCase {
        METADATA(1),
        NAMED_QUERY(2),
        DOCUMENT_METADATA(3),
        DOCUMENT(4),
        ELEMENTTYPE_NOT_SET(0);
        
        private final int value;

        private ElementTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ElementTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ElementTypeCase forNumber(int i) {
            if (i == 0) {
                return ELEMENTTYPE_NOT_SET;
            }
            if (i == 1) {
                return METADATA;
            }
            if (i == 2) {
                return NAMED_QUERY;
            }
            if (i == 3) {
                return DOCUMENT_METADATA;
            }
            if (i != 4) {
                return null;
            }
            return DOCUMENT;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ElementTypeCase getElementTypeCase() {
        return ElementTypeCase.forNumber(this.elementTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearElementType() {
        this.elementTypeCase_ = 0;
        this.elementType_ = null;
    }

    public boolean hasMetadata() {
        return this.elementTypeCase_ == 1;
    }

    public BundleMetadata getMetadata() {
        if (this.elementTypeCase_ == 1) {
            return (BundleMetadata) this.elementType_;
        }
        return BundleMetadata.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setMetadata(BundleMetadata bundleMetadata) {
        bundleMetadata.getClass();
        this.elementType_ = bundleMetadata;
        this.elementTypeCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeMetadata(BundleMetadata bundleMetadata) {
        bundleMetadata.getClass();
        if (this.elementTypeCase_ != 1 || this.elementType_ == BundleMetadata.getDefaultInstance()) {
            this.elementType_ = bundleMetadata;
        } else {
            this.elementType_ = ((BundleMetadata.Builder) BundleMetadata.newBuilder((BundleMetadata) this.elementType_).mergeFrom(bundleMetadata)).buildPartial();
        }
        this.elementTypeCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearMetadata() {
        if (this.elementTypeCase_ == 1) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    public boolean hasNamedQuery() {
        return this.elementTypeCase_ == 2;
    }

    public NamedQuery getNamedQuery() {
        if (this.elementTypeCase_ == 2) {
            return (NamedQuery) this.elementType_;
        }
        return NamedQuery.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setNamedQuery(NamedQuery namedQuery) {
        namedQuery.getClass();
        this.elementType_ = namedQuery;
        this.elementTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeNamedQuery(NamedQuery namedQuery) {
        namedQuery.getClass();
        if (this.elementTypeCase_ != 2 || this.elementType_ == NamedQuery.getDefaultInstance()) {
            this.elementType_ = namedQuery;
        } else {
            this.elementType_ = ((NamedQuery.Builder) NamedQuery.newBuilder((NamedQuery) this.elementType_).mergeFrom(namedQuery)).buildPartial();
        }
        this.elementTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearNamedQuery() {
        if (this.elementTypeCase_ == 2) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    public boolean hasDocumentMetadata() {
        return this.elementTypeCase_ == 3;
    }

    public BundledDocumentMetadata getDocumentMetadata() {
        if (this.elementTypeCase_ == 3) {
            return (BundledDocumentMetadata) this.elementType_;
        }
        return BundledDocumentMetadata.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
        bundledDocumentMetadata.getClass();
        this.elementType_ = bundledDocumentMetadata;
        this.elementTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
        bundledDocumentMetadata.getClass();
        if (this.elementTypeCase_ != 3 || this.elementType_ == BundledDocumentMetadata.getDefaultInstance()) {
            this.elementType_ = bundledDocumentMetadata;
        } else {
            this.elementType_ = ((BundledDocumentMetadata.Builder) BundledDocumentMetadata.newBuilder((BundledDocumentMetadata) this.elementType_).mergeFrom(bundledDocumentMetadata)).buildPartial();
        }
        this.elementTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void clearDocumentMetadata() {
        if (this.elementTypeCase_ == 3) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    public boolean hasDocument() {
        return this.elementTypeCase_ == 4;
    }

    public Document getDocument() {
        if (this.elementTypeCase_ == 4) {
            return (Document) this.elementType_;
        }
        return Document.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.elementType_ = document;
        this.elementTypeCase_ = 4;
    }

    /* access modifiers changed from: private */
    public void mergeDocument(Document document) {
        document.getClass();
        if (this.elementTypeCase_ != 4 || this.elementType_ == Document.getDefaultInstance()) {
            this.elementType_ = document;
        } else {
            this.elementType_ = ((Document.Builder) Document.newBuilder((Document) this.elementType_).mergeFrom(document)).buildPartial();
        }
        this.elementTypeCase_ = 4;
    }

    /* access modifiers changed from: private */
    public void clearDocument() {
        if (this.elementTypeCase_ == 4) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    public static BundleElement parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BundleElement parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BundleElement parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BundleElement parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BundleElement parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BundleElement parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BundleElement parseFrom(InputStream inputStream) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleElement parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleElement parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BundleElement) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleElement parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleElement) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleElement parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BundleElement parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundleElement bundleElement) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(bundleElement);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundleElement, Builder> implements BundleElementOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(BundleElement.DEFAULT_INSTANCE);
        }

        public ElementTypeCase getElementTypeCase() {
            return ((BundleElement) this.instance).getElementTypeCase();
        }

        public Builder clearElementType() {
            copyOnWrite();
            ((BundleElement) this.instance).clearElementType();
            return this;
        }

        public boolean hasMetadata() {
            return ((BundleElement) this.instance).hasMetadata();
        }

        public BundleMetadata getMetadata() {
            return ((BundleElement) this.instance).getMetadata();
        }

        public Builder setMetadata(BundleMetadata bundleMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).setMetadata(bundleMetadata);
            return this;
        }

        public Builder setMetadata(BundleMetadata.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setMetadata((BundleMetadata) builder.build());
            return this;
        }

        public Builder mergeMetadata(BundleMetadata bundleMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeMetadata(bundleMetadata);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((BundleElement) this.instance).clearMetadata();
            return this;
        }

        public boolean hasNamedQuery() {
            return ((BundleElement) this.instance).hasNamedQuery();
        }

        public NamedQuery getNamedQuery() {
            return ((BundleElement) this.instance).getNamedQuery();
        }

        public Builder setNamedQuery(NamedQuery namedQuery) {
            copyOnWrite();
            ((BundleElement) this.instance).setNamedQuery(namedQuery);
            return this;
        }

        public Builder setNamedQuery(NamedQuery.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setNamedQuery((NamedQuery) builder.build());
            return this;
        }

        public Builder mergeNamedQuery(NamedQuery namedQuery) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeNamedQuery(namedQuery);
            return this;
        }

        public Builder clearNamedQuery() {
            copyOnWrite();
            ((BundleElement) this.instance).clearNamedQuery();
            return this;
        }

        public boolean hasDocumentMetadata() {
            return ((BundleElement) this.instance).hasDocumentMetadata();
        }

        public BundledDocumentMetadata getDocumentMetadata() {
            return ((BundleElement) this.instance).getDocumentMetadata();
        }

        public Builder setDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocumentMetadata(bundledDocumentMetadata);
            return this;
        }

        public Builder setDocumentMetadata(BundledDocumentMetadata.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocumentMetadata((BundledDocumentMetadata) builder.build());
            return this;
        }

        public Builder mergeDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeDocumentMetadata(bundledDocumentMetadata);
            return this;
        }

        public Builder clearDocumentMetadata() {
            copyOnWrite();
            ((BundleElement) this.instance).clearDocumentMetadata();
            return this;
        }

        public boolean hasDocument() {
            return ((BundleElement) this.instance).hasDocument();
        }

        public Document getDocument() {
            return ((BundleElement) this.instance).getDocument();
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocument(document);
            return this;
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocument((Document) builder.build());
            return this;
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeDocument(document);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((BundleElement) this.instance).clearDocument();
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundleElement$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.bundle.BundleElement.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BundleElement();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000", new Object[]{"elementType_", "elementTypeCase_", BundleMetadata.class, NamedQuery.class, BundledDocumentMetadata.class, Document.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundleElement> parser = PARSER;
                if (parser == null) {
                    synchronized (BundleElement.class) {
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
        BundleElement bundleElement = new BundleElement();
        DEFAULT_INSTANCE = bundleElement;
        GeneratedMessageLite.registerDefaultInstance(BundleElement.class, bundleElement);
    }

    public static BundleElement getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundleElement> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
