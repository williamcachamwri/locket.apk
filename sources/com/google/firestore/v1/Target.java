package com.google.firestore.v1;

import com.google.firestore.v1.StructuredQuery;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Target extends GeneratedMessageLite<Target, Builder> implements TargetOrBuilder {
    /* access modifiers changed from: private */
    public static final Target DEFAULT_INSTANCE;
    public static final int DOCUMENTS_FIELD_NUMBER = 3;
    public static final int EXPECTED_COUNT_FIELD_NUMBER = 12;
    public static final int ONCE_FIELD_NUMBER = 6;
    private static volatile Parser<Target> PARSER = null;
    public static final int QUERY_FIELD_NUMBER = 2;
    public static final int READ_TIME_FIELD_NUMBER = 11;
    public static final int RESUME_TOKEN_FIELD_NUMBER = 4;
    public static final int TARGET_ID_FIELD_NUMBER = 5;
    private int bitField0_;
    private Int32Value expectedCount_;
    private boolean once_;
    private int resumeTypeCase_ = 0;
    private Object resumeType_;
    private int targetId_;
    private int targetTypeCase_ = 0;
    private Object targetType_;

    public interface DocumentsTargetOrBuilder extends MessageLiteOrBuilder {
        String getDocuments(int i);

        ByteString getDocumentsBytes(int i);

        int getDocumentsCount();

        List<String> getDocumentsList();
    }

    public interface QueryTargetOrBuilder extends MessageLiteOrBuilder {
        String getParent();

        ByteString getParentBytes();

        QueryTarget.QueryTypeCase getQueryTypeCase();

        StructuredQuery getStructuredQuery();

        boolean hasStructuredQuery();
    }

    private Target() {
    }

    public static final class DocumentsTarget extends GeneratedMessageLite<DocumentsTarget, Builder> implements DocumentsTargetOrBuilder {
        /* access modifiers changed from: private */
        public static final DocumentsTarget DEFAULT_INSTANCE;
        public static final int DOCUMENTS_FIELD_NUMBER = 2;
        private static volatile Parser<DocumentsTarget> PARSER;
        private Internal.ProtobufList<String> documents_ = GeneratedMessageLite.emptyProtobufList();

        private DocumentsTarget() {
        }

        public List<String> getDocumentsList() {
            return this.documents_;
        }

        public int getDocumentsCount() {
            return this.documents_.size();
        }

        public String getDocuments(int i) {
            return (String) this.documents_.get(i);
        }

        public ByteString getDocumentsBytes(int i) {
            return ByteString.copyFromUtf8((String) this.documents_.get(i));
        }

        private void ensureDocumentsIsMutable() {
            Internal.ProtobufList<String> protobufList = this.documents_;
            if (!protobufList.isModifiable()) {
                this.documents_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
        }

        /* access modifiers changed from: private */
        public void setDocuments(int i, String str) {
            str.getClass();
            ensureDocumentsIsMutable();
            this.documents_.set(i, str);
        }

        /* access modifiers changed from: private */
        public void addDocuments(String str) {
            str.getClass();
            ensureDocumentsIsMutable();
            this.documents_.add(str);
        }

        /* access modifiers changed from: private */
        public void addAllDocuments(Iterable<String> iterable) {
            ensureDocumentsIsMutable();
            AbstractMessageLite.addAll(iterable, this.documents_);
        }

        /* access modifiers changed from: private */
        public void clearDocuments() {
            this.documents_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void addDocumentsBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            ensureDocumentsIsMutable();
            this.documents_.add(byteString.toStringUtf8());
        }

        public static DocumentsTarget parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static DocumentsTarget parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DocumentsTarget parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DocumentsTarget parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(InputStream inputStream) throws IOException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DocumentsTarget parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DocumentsTarget parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DocumentsTarget) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DocumentsTarget parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DocumentsTarget) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DocumentsTarget parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(DocumentsTarget documentsTarget) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(documentsTarget);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DocumentsTarget, Builder> implements DocumentsTargetOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(DocumentsTarget.DEFAULT_INSTANCE);
            }

            public List<String> getDocumentsList() {
                return Collections.unmodifiableList(((DocumentsTarget) this.instance).getDocumentsList());
            }

            public int getDocumentsCount() {
                return ((DocumentsTarget) this.instance).getDocumentsCount();
            }

            public String getDocuments(int i) {
                return ((DocumentsTarget) this.instance).getDocuments(i);
            }

            public ByteString getDocumentsBytes(int i) {
                return ((DocumentsTarget) this.instance).getDocumentsBytes(i);
            }

            public Builder setDocuments(int i, String str) {
                copyOnWrite();
                ((DocumentsTarget) this.instance).setDocuments(i, str);
                return this;
            }

            public Builder addDocuments(String str) {
                copyOnWrite();
                ((DocumentsTarget) this.instance).addDocuments(str);
                return this;
            }

            public Builder addAllDocuments(Iterable<String> iterable) {
                copyOnWrite();
                ((DocumentsTarget) this.instance).addAllDocuments(iterable);
                return this;
            }

            public Builder clearDocuments() {
                copyOnWrite();
                ((DocumentsTarget) this.instance).clearDocuments();
                return this;
            }

            public Builder addDocumentsBytes(ByteString byteString) {
                copyOnWrite();
                ((DocumentsTarget) this.instance).addDocumentsBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new DocumentsTarget();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0001\u0000\u0002Ț", new Object[]{"documents_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<DocumentsTarget> parser = PARSER;
                    if (parser == null) {
                        synchronized (DocumentsTarget.class) {
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
            DocumentsTarget documentsTarget = new DocumentsTarget();
            DEFAULT_INSTANCE = documentsTarget;
            GeneratedMessageLite.registerDefaultInstance(DocumentsTarget.class, documentsTarget);
        }

        public static DocumentsTarget getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DocumentsTarget> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.Target$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.v1.Target.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class QueryTarget extends GeneratedMessageLite<QueryTarget, Builder> implements QueryTargetOrBuilder {
        /* access modifiers changed from: private */
        public static final QueryTarget DEFAULT_INSTANCE;
        public static final int PARENT_FIELD_NUMBER = 1;
        private static volatile Parser<QueryTarget> PARSER = null;
        public static final int STRUCTURED_QUERY_FIELD_NUMBER = 2;
        private String parent_ = "";
        private int queryTypeCase_ = 0;
        private Object queryType_;

        private QueryTarget() {
        }

        public enum QueryTypeCase {
            STRUCTURED_QUERY(2),
            QUERYTYPE_NOT_SET(0);
            
            private final int value;

            private QueryTypeCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static QueryTypeCase valueOf(int i) {
                return forNumber(i);
            }

            public static QueryTypeCase forNumber(int i) {
                if (i == 0) {
                    return QUERYTYPE_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return STRUCTURED_QUERY;
            }

            public int getNumber() {
                return this.value;
            }
        }

        public QueryTypeCase getQueryTypeCase() {
            return QueryTypeCase.forNumber(this.queryTypeCase_);
        }

        /* access modifiers changed from: private */
        public void clearQueryType() {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }

        public String getParent() {
            return this.parent_;
        }

        public ByteString getParentBytes() {
            return ByteString.copyFromUtf8(this.parent_);
        }

        /* access modifiers changed from: private */
        public void setParent(String str) {
            str.getClass();
            this.parent_ = str;
        }

        /* access modifiers changed from: private */
        public void clearParent() {
            this.parent_ = getDefaultInstance().getParent();
        }

        /* access modifiers changed from: private */
        public void setParentBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.parent_ = byteString.toStringUtf8();
        }

        public boolean hasStructuredQuery() {
            return this.queryTypeCase_ == 2;
        }

        public StructuredQuery getStructuredQuery() {
            if (this.queryTypeCase_ == 2) {
                return (StructuredQuery) this.queryType_;
            }
            return StructuredQuery.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setStructuredQuery(StructuredQuery structuredQuery) {
            structuredQuery.getClass();
            this.queryType_ = structuredQuery;
            this.queryTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeStructuredQuery(StructuredQuery structuredQuery) {
            structuredQuery.getClass();
            if (this.queryTypeCase_ != 2 || this.queryType_ == StructuredQuery.getDefaultInstance()) {
                this.queryType_ = structuredQuery;
            } else {
                this.queryType_ = ((StructuredQuery.Builder) StructuredQuery.newBuilder((StructuredQuery) this.queryType_).mergeFrom(structuredQuery)).buildPartial();
            }
            this.queryTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearStructuredQuery() {
            if (this.queryTypeCase_ == 2) {
                this.queryTypeCase_ = 0;
                this.queryType_ = null;
            }
        }

        public static QueryTarget parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static QueryTarget parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static QueryTarget parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static QueryTarget parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(InputStream inputStream) throws IOException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static QueryTarget parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static QueryTarget parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (QueryTarget) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static QueryTarget parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryTarget) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static QueryTarget parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(QueryTarget queryTarget) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(queryTarget);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<QueryTarget, Builder> implements QueryTargetOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(QueryTarget.DEFAULT_INSTANCE);
            }

            public QueryTypeCase getQueryTypeCase() {
                return ((QueryTarget) this.instance).getQueryTypeCase();
            }

            public Builder clearQueryType() {
                copyOnWrite();
                ((QueryTarget) this.instance).clearQueryType();
                return this;
            }

            public String getParent() {
                return ((QueryTarget) this.instance).getParent();
            }

            public ByteString getParentBytes() {
                return ((QueryTarget) this.instance).getParentBytes();
            }

            public Builder setParent(String str) {
                copyOnWrite();
                ((QueryTarget) this.instance).setParent(str);
                return this;
            }

            public Builder clearParent() {
                copyOnWrite();
                ((QueryTarget) this.instance).clearParent();
                return this;
            }

            public Builder setParentBytes(ByteString byteString) {
                copyOnWrite();
                ((QueryTarget) this.instance).setParentBytes(byteString);
                return this;
            }

            public boolean hasStructuredQuery() {
                return ((QueryTarget) this.instance).hasStructuredQuery();
            }

            public StructuredQuery getStructuredQuery() {
                return ((QueryTarget) this.instance).getStructuredQuery();
            }

            public Builder setStructuredQuery(StructuredQuery structuredQuery) {
                copyOnWrite();
                ((QueryTarget) this.instance).setStructuredQuery(structuredQuery);
                return this;
            }

            public Builder setStructuredQuery(StructuredQuery.Builder builder) {
                copyOnWrite();
                ((QueryTarget) this.instance).setStructuredQuery((StructuredQuery) builder.build());
                return this;
            }

            public Builder mergeStructuredQuery(StructuredQuery structuredQuery) {
                copyOnWrite();
                ((QueryTarget) this.instance).mergeStructuredQuery(structuredQuery);
                return this;
            }

            public Builder clearStructuredQuery() {
                copyOnWrite();
                ((QueryTarget) this.instance).clearStructuredQuery();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new QueryTarget();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000", new Object[]{"queryType_", "queryTypeCase_", "parent_", StructuredQuery.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<QueryTarget> parser = PARSER;
                    if (parser == null) {
                        synchronized (QueryTarget.class) {
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
            QueryTarget queryTarget = new QueryTarget();
            DEFAULT_INSTANCE = queryTarget;
            GeneratedMessageLite.registerDefaultInstance(QueryTarget.class, queryTarget);
        }

        public static QueryTarget getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<QueryTarget> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public enum TargetTypeCase {
        QUERY(2),
        DOCUMENTS(3),
        TARGETTYPE_NOT_SET(0);
        
        private final int value;

        private TargetTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static TargetTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static TargetTypeCase forNumber(int i) {
            if (i == 0) {
                return TARGETTYPE_NOT_SET;
            }
            if (i == 2) {
                return QUERY;
            }
            if (i != 3) {
                return null;
            }
            return DOCUMENTS;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public TargetTypeCase getTargetTypeCase() {
        return TargetTypeCase.forNumber(this.targetTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearTargetType() {
        this.targetTypeCase_ = 0;
        this.targetType_ = null;
    }

    public enum ResumeTypeCase {
        RESUME_TOKEN(4),
        READ_TIME(11),
        RESUMETYPE_NOT_SET(0);
        
        private final int value;

        private ResumeTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ResumeTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ResumeTypeCase forNumber(int i) {
            if (i == 0) {
                return RESUMETYPE_NOT_SET;
            }
            if (i == 4) {
                return RESUME_TOKEN;
            }
            if (i != 11) {
                return null;
            }
            return READ_TIME;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ResumeTypeCase getResumeTypeCase() {
        return ResumeTypeCase.forNumber(this.resumeTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearResumeType() {
        this.resumeTypeCase_ = 0;
        this.resumeType_ = null;
    }

    public boolean hasQuery() {
        return this.targetTypeCase_ == 2;
    }

    public QueryTarget getQuery() {
        if (this.targetTypeCase_ == 2) {
            return (QueryTarget) this.targetType_;
        }
        return QueryTarget.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setQuery(QueryTarget queryTarget) {
        queryTarget.getClass();
        this.targetType_ = queryTarget;
        this.targetTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeQuery(QueryTarget queryTarget) {
        queryTarget.getClass();
        if (this.targetTypeCase_ != 2 || this.targetType_ == QueryTarget.getDefaultInstance()) {
            this.targetType_ = queryTarget;
        } else {
            this.targetType_ = ((QueryTarget.Builder) QueryTarget.newBuilder((QueryTarget) this.targetType_).mergeFrom(queryTarget)).buildPartial();
        }
        this.targetTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearQuery() {
        if (this.targetTypeCase_ == 2) {
            this.targetTypeCase_ = 0;
            this.targetType_ = null;
        }
    }

    public boolean hasDocuments() {
        return this.targetTypeCase_ == 3;
    }

    public DocumentsTarget getDocuments() {
        if (this.targetTypeCase_ == 3) {
            return (DocumentsTarget) this.targetType_;
        }
        return DocumentsTarget.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocuments(DocumentsTarget documentsTarget) {
        documentsTarget.getClass();
        this.targetType_ = documentsTarget;
        this.targetTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void mergeDocuments(DocumentsTarget documentsTarget) {
        documentsTarget.getClass();
        if (this.targetTypeCase_ != 3 || this.targetType_ == DocumentsTarget.getDefaultInstance()) {
            this.targetType_ = documentsTarget;
        } else {
            this.targetType_ = ((DocumentsTarget.Builder) DocumentsTarget.newBuilder((DocumentsTarget) this.targetType_).mergeFrom(documentsTarget)).buildPartial();
        }
        this.targetTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void clearDocuments() {
        if (this.targetTypeCase_ == 3) {
            this.targetTypeCase_ = 0;
            this.targetType_ = null;
        }
    }

    public boolean hasResumeToken() {
        return this.resumeTypeCase_ == 4;
    }

    public ByteString getResumeToken() {
        if (this.resumeTypeCase_ == 4) {
            return (ByteString) this.resumeType_;
        }
        return ByteString.EMPTY;
    }

    /* access modifiers changed from: private */
    public void setResumeToken(ByteString byteString) {
        byteString.getClass();
        this.resumeTypeCase_ = 4;
        this.resumeType_ = byteString;
    }

    /* access modifiers changed from: private */
    public void clearResumeToken() {
        if (this.resumeTypeCase_ == 4) {
            this.resumeTypeCase_ = 0;
            this.resumeType_ = null;
        }
    }

    public boolean hasReadTime() {
        return this.resumeTypeCase_ == 11;
    }

    public Timestamp getReadTime() {
        if (this.resumeTypeCase_ == 11) {
            return (Timestamp) this.resumeType_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.resumeType_ = timestamp;
        this.resumeTypeCase_ = 11;
    }

    /* access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        timestamp.getClass();
        if (this.resumeTypeCase_ != 11 || this.resumeType_ == Timestamp.getDefaultInstance()) {
            this.resumeType_ = timestamp;
        } else {
            this.resumeType_ = ((Timestamp.Builder) Timestamp.newBuilder((Timestamp) this.resumeType_).mergeFrom(timestamp)).buildPartial();
        }
        this.resumeTypeCase_ = 11;
    }

    /* access modifiers changed from: private */
    public void clearReadTime() {
        if (this.resumeTypeCase_ == 11) {
            this.resumeTypeCase_ = 0;
            this.resumeType_ = null;
        }
    }

    public int getTargetId() {
        return this.targetId_;
    }

    /* access modifiers changed from: private */
    public void setTargetId(int i) {
        this.targetId_ = i;
    }

    /* access modifiers changed from: private */
    public void clearTargetId() {
        this.targetId_ = 0;
    }

    public boolean getOnce() {
        return this.once_;
    }

    /* access modifiers changed from: private */
    public void setOnce(boolean z) {
        this.once_ = z;
    }

    /* access modifiers changed from: private */
    public void clearOnce() {
        this.once_ = false;
    }

    public boolean hasExpectedCount() {
        return (this.bitField0_ & 1) != 0;
    }

    public Int32Value getExpectedCount() {
        Int32Value int32Value = this.expectedCount_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    /* access modifiers changed from: private */
    public void setExpectedCount(Int32Value int32Value) {
        int32Value.getClass();
        this.expectedCount_ = int32Value;
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    public void mergeExpectedCount(Int32Value int32Value) {
        int32Value.getClass();
        Int32Value int32Value2 = this.expectedCount_;
        if (int32Value2 == null || int32Value2 == Int32Value.getDefaultInstance()) {
            this.expectedCount_ = int32Value;
        } else {
            this.expectedCount_ = (Int32Value) ((Int32Value.Builder) Int32Value.newBuilder(this.expectedCount_).mergeFrom(int32Value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    public void clearExpectedCount() {
        this.expectedCount_ = null;
        this.bitField0_ &= -2;
    }

    public static Target parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Target parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Target parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Target parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Target parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Target parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Target parseFrom(InputStream inputStream) throws IOException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Target parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Target parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Target) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Target parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Target) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Target parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Target parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Target target) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(target);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Target, Builder> implements TargetOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(Target.DEFAULT_INSTANCE);
        }

        public TargetTypeCase getTargetTypeCase() {
            return ((Target) this.instance).getTargetTypeCase();
        }

        public Builder clearTargetType() {
            copyOnWrite();
            ((Target) this.instance).clearTargetType();
            return this;
        }

        public ResumeTypeCase getResumeTypeCase() {
            return ((Target) this.instance).getResumeTypeCase();
        }

        public Builder clearResumeType() {
            copyOnWrite();
            ((Target) this.instance).clearResumeType();
            return this;
        }

        public boolean hasQuery() {
            return ((Target) this.instance).hasQuery();
        }

        public QueryTarget getQuery() {
            return ((Target) this.instance).getQuery();
        }

        public Builder setQuery(QueryTarget queryTarget) {
            copyOnWrite();
            ((Target) this.instance).setQuery(queryTarget);
            return this;
        }

        public Builder setQuery(QueryTarget.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setQuery((QueryTarget) builder.build());
            return this;
        }

        public Builder mergeQuery(QueryTarget queryTarget) {
            copyOnWrite();
            ((Target) this.instance).mergeQuery(queryTarget);
            return this;
        }

        public Builder clearQuery() {
            copyOnWrite();
            ((Target) this.instance).clearQuery();
            return this;
        }

        public boolean hasDocuments() {
            return ((Target) this.instance).hasDocuments();
        }

        public DocumentsTarget getDocuments() {
            return ((Target) this.instance).getDocuments();
        }

        public Builder setDocuments(DocumentsTarget documentsTarget) {
            copyOnWrite();
            ((Target) this.instance).setDocuments(documentsTarget);
            return this;
        }

        public Builder setDocuments(DocumentsTarget.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setDocuments((DocumentsTarget) builder.build());
            return this;
        }

        public Builder mergeDocuments(DocumentsTarget documentsTarget) {
            copyOnWrite();
            ((Target) this.instance).mergeDocuments(documentsTarget);
            return this;
        }

        public Builder clearDocuments() {
            copyOnWrite();
            ((Target) this.instance).clearDocuments();
            return this;
        }

        public boolean hasResumeToken() {
            return ((Target) this.instance).hasResumeToken();
        }

        public ByteString getResumeToken() {
            return ((Target) this.instance).getResumeToken();
        }

        public Builder setResumeToken(ByteString byteString) {
            copyOnWrite();
            ((Target) this.instance).setResumeToken(byteString);
            return this;
        }

        public Builder clearResumeToken() {
            copyOnWrite();
            ((Target) this.instance).clearResumeToken();
            return this;
        }

        public boolean hasReadTime() {
            return ((Target) this.instance).hasReadTime();
        }

        public Timestamp getReadTime() {
            return ((Target) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setReadTime((Timestamp) builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((Target) this.instance).clearReadTime();
            return this;
        }

        public int getTargetId() {
            return ((Target) this.instance).getTargetId();
        }

        public Builder setTargetId(int i) {
            copyOnWrite();
            ((Target) this.instance).setTargetId(i);
            return this;
        }

        public Builder clearTargetId() {
            copyOnWrite();
            ((Target) this.instance).clearTargetId();
            return this;
        }

        public boolean getOnce() {
            return ((Target) this.instance).getOnce();
        }

        public Builder setOnce(boolean z) {
            copyOnWrite();
            ((Target) this.instance).setOnce(z);
            return this;
        }

        public Builder clearOnce() {
            copyOnWrite();
            ((Target) this.instance).clearOnce();
            return this;
        }

        public boolean hasExpectedCount() {
            return ((Target) this.instance).hasExpectedCount();
        }

        public Int32Value getExpectedCount() {
            return ((Target) this.instance).getExpectedCount();
        }

        public Builder setExpectedCount(Int32Value int32Value) {
            copyOnWrite();
            ((Target) this.instance).setExpectedCount(int32Value);
            return this;
        }

        public Builder setExpectedCount(Int32Value.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setExpectedCount((Int32Value) builder.build());
            return this;
        }

        public Builder mergeExpectedCount(Int32Value int32Value) {
            copyOnWrite();
            ((Target) this.instance).mergeExpectedCount(int32Value);
            return this;
        }

        public Builder clearExpectedCount() {
            copyOnWrite();
            ((Target) this.instance).clearExpectedCount();
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Target();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0002\u0001\u0002\f\u0007\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004=\u0001\u0005\u0004\u0006\u0007\u000b<\u0001\fဉ\u0000", new Object[]{"targetType_", "targetTypeCase_", "resumeType_", "resumeTypeCase_", "bitField0_", QueryTarget.class, DocumentsTarget.class, "targetId_", "once_", Timestamp.class, "expectedCount_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Target> parser = PARSER;
                if (parser == null) {
                    synchronized (Target.class) {
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
        Target target = new Target();
        DEFAULT_INSTANCE = target;
        GeneratedMessageLite.registerDefaultInstance(Target.class, target);
    }

    public static Target getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Target> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
