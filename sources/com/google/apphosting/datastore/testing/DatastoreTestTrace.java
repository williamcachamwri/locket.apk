package com.google.apphosting.datastore.testing;

import com.google.firestore.v1.BatchGetDocumentsRequest;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder;
import com.google.firestore.v1.BeginTransactionRequest;
import com.google.firestore.v1.BeginTransactionResponse;
import com.google.firestore.v1.CommitRequest;
import com.google.firestore.v1.CommitResponse;
import com.google.firestore.v1.CreateDocumentRequest;
import com.google.firestore.v1.DeleteDocumentRequest;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.GetDocumentRequest;
import com.google.firestore.v1.ListCollectionIdsRequest;
import com.google.firestore.v1.ListCollectionIdsResponse;
import com.google.firestore.v1.ListDocumentsRequest;
import com.google.firestore.v1.ListDocumentsResponse;
import com.google.firestore.v1.ListenRequest;
import com.google.firestore.v1.ListenResponse;
import com.google.firestore.v1.RollbackRequest;
import com.google.firestore.v1.RunQueryRequest;
import com.google.firestore.v1.RunQueryResponse;
import com.google.firestore.v1.RunQueryResponseOrBuilder;
import com.google.firestore.v1.UpdateDocumentRequest;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Empty;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class DatastoreTestTrace {

    public interface DatastoreActionOrBuilder extends MessageLiteOrBuilder {
        DatastoreAction.ActionCase getActionCase();

        int getActionId();

        FirestoreV1Action getFirestoreV1Action();

        ValidationRule getValidationRule();

        boolean hasFirestoreV1Action();

        boolean hasValidationRule();
    }

    public interface FirestoreV1ActionOrBuilder extends MessageLiteOrBuilder {
        FirestoreV1Action.ActionCase getActionCase();

        FirestoreV1Action.BatchGetDocuments getBatchGetDocuments();

        FirestoreV1Action.BeginTransaction getBeginTransaction();

        FirestoreV1Action.Commit getCommit();

        FirestoreV1Action.CreateDocument getCreateDocument();

        FirestoreV1Action.RunQuery getDatabaseContentsBeforeAction();

        FirestoreV1Action.DeleteDocument getDeleteDocument();

        FirestoreV1Action.GetDocument getGetDocument();

        FirestoreV1Action.ListCollectionIds getListCollectionIds();

        FirestoreV1Action.ListDocuments getListDocuments();

        FirestoreV1Action.Listen getListen();

        FirestoreV1Action.MatchingDocuments getMatchingDocuments(int i);

        int getMatchingDocumentsCount();

        List<FirestoreV1Action.MatchingDocuments> getMatchingDocumentsList();

        FirestoreV1Action.RemoveListen getRemoveListen();

        FirestoreV1Action.Rollback getRollback();

        FirestoreV1Action.RunQuery getRunQuery();

        StatusProto getStatus();

        FirestoreV1Action.UpdateDocument getUpdateDocument();

        boolean hasBatchGetDocuments();

        boolean hasBeginTransaction();

        boolean hasCommit();

        boolean hasCreateDocument();

        boolean hasDatabaseContentsBeforeAction();

        boolean hasDeleteDocument();

        boolean hasGetDocument();

        boolean hasListCollectionIds();

        boolean hasListDocuments();

        boolean hasListen();

        boolean hasRemoveListen();

        boolean hasRollback();

        boolean hasRunQuery();

        boolean hasStatus();

        boolean hasUpdateDocument();
    }

    public interface ParallelTestTraceOrBuilder extends MessageLiteOrBuilder {
        TestTrace getTestTrace();

        boolean hasTestTrace();
    }

    public interface StatusProtoOrBuilder extends MessageLiteOrBuilder {
        int getCanonicalCode();

        int getCode();

        String getMessage();

        ByteString getMessageBytes();

        String getSpace();

        ByteString getSpaceBytes();
    }

    public interface TestTraceOrBuilder extends MessageLiteOrBuilder {
        DatastoreAction getAction(int i);

        int getActionCount();

        List<DatastoreAction> getActionList();

        String getTraceDescription();

        ByteString getTraceDescriptionBytes();

        String getTraceId();

        ByteString getTraceIdBytes();
    }

    public interface TimelineTestTraceOrBuilder extends MessageLiteOrBuilder {
        TestTrace getTestTrace();

        boolean hasTestTrace();
    }

    public interface ValidationRuleOrBuilder extends MessageLiteOrBuilder {
        boolean getValidateQueryIndexes();

        boolean getValidateQueryResultOrder();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DatastoreTestTrace() {
    }

    public static final class StatusProto extends GeneratedMessageLite<StatusProto, Builder> implements StatusProtoOrBuilder {
        public static final int CANONICAL_CODE_FIELD_NUMBER = 6;
        public static final int CODE_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final StatusProto DEFAULT_INSTANCE;
        public static final int MESSAGE_FIELD_NUMBER = 3;
        private static volatile Parser<StatusProto> PARSER = null;
        public static final int SPACE_FIELD_NUMBER = 2;
        private int canonicalCode_;
        private int code_;
        private String message_ = "";
        private String space_ = "";

        private StatusProto() {
        }

        public int getCode() {
            return this.code_;
        }

        /* access modifiers changed from: private */
        public void setCode(int i) {
            this.code_ = i;
        }

        /* access modifiers changed from: private */
        public void clearCode() {
            this.code_ = 0;
        }

        public String getSpace() {
            return this.space_;
        }

        public ByteString getSpaceBytes() {
            return ByteString.copyFromUtf8(this.space_);
        }

        /* access modifiers changed from: private */
        public void setSpace(String str) {
            str.getClass();
            this.space_ = str;
        }

        /* access modifiers changed from: private */
        public void clearSpace() {
            this.space_ = getDefaultInstance().getSpace();
        }

        /* access modifiers changed from: private */
        public void setSpaceBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.space_ = byteString.toStringUtf8();
        }

        public String getMessage() {
            return this.message_;
        }

        public ByteString getMessageBytes() {
            return ByteString.copyFromUtf8(this.message_);
        }

        /* access modifiers changed from: private */
        public void setMessage(String str) {
            str.getClass();
            this.message_ = str;
        }

        /* access modifiers changed from: private */
        public void clearMessage() {
            this.message_ = getDefaultInstance().getMessage();
        }

        /* access modifiers changed from: private */
        public void setMessageBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.message_ = byteString.toStringUtf8();
        }

        public int getCanonicalCode() {
            return this.canonicalCode_;
        }

        /* access modifiers changed from: private */
        public void setCanonicalCode(int i) {
            this.canonicalCode_ = i;
        }

        /* access modifiers changed from: private */
        public void clearCanonicalCode() {
            this.canonicalCode_ = 0;
        }

        public static StatusProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static StatusProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StatusProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StatusProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StatusProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StatusProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StatusProto parseFrom(InputStream inputStream) throws IOException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StatusProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StatusProto parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StatusProto) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StatusProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StatusProto) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StatusProto parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StatusProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(StatusProto statusProto) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(statusProto);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StatusProto, Builder> implements StatusProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(StatusProto.DEFAULT_INSTANCE);
            }

            public int getCode() {
                return ((StatusProto) this.instance).getCode();
            }

            public Builder setCode(int i) {
                copyOnWrite();
                ((StatusProto) this.instance).setCode(i);
                return this;
            }

            public Builder clearCode() {
                copyOnWrite();
                ((StatusProto) this.instance).clearCode();
                return this;
            }

            public String getSpace() {
                return ((StatusProto) this.instance).getSpace();
            }

            public ByteString getSpaceBytes() {
                return ((StatusProto) this.instance).getSpaceBytes();
            }

            public Builder setSpace(String str) {
                copyOnWrite();
                ((StatusProto) this.instance).setSpace(str);
                return this;
            }

            public Builder clearSpace() {
                copyOnWrite();
                ((StatusProto) this.instance).clearSpace();
                return this;
            }

            public Builder setSpaceBytes(ByteString byteString) {
                copyOnWrite();
                ((StatusProto) this.instance).setSpaceBytes(byteString);
                return this;
            }

            public String getMessage() {
                return ((StatusProto) this.instance).getMessage();
            }

            public ByteString getMessageBytes() {
                return ((StatusProto) this.instance).getMessageBytes();
            }

            public Builder setMessage(String str) {
                copyOnWrite();
                ((StatusProto) this.instance).setMessage(str);
                return this;
            }

            public Builder clearMessage() {
                copyOnWrite();
                ((StatusProto) this.instance).clearMessage();
                return this;
            }

            public Builder setMessageBytes(ByteString byteString) {
                copyOnWrite();
                ((StatusProto) this.instance).setMessageBytes(byteString);
                return this;
            }

            public int getCanonicalCode() {
                return ((StatusProto) this.instance).getCanonicalCode();
            }

            public Builder setCanonicalCode(int i) {
                copyOnWrite();
                ((StatusProto) this.instance).setCanonicalCode(i);
                return this;
            }

            public Builder clearCanonicalCode() {
                copyOnWrite();
                ((StatusProto) this.instance).clearCanonicalCode();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new StatusProto();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0006\u0004\u0000\u0000\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ\u0006\u0004", new Object[]{"code_", "space_", "message_", "canonicalCode_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<StatusProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (StatusProto.class) {
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
            StatusProto statusProto = new StatusProto();
            DEFAULT_INSTANCE = statusProto;
            GeneratedMessageLite.registerDefaultInstance(StatusProto.class, statusProto);
        }

        public static StatusProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StatusProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.apphosting.datastore.testing.DatastoreTestTrace$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.apphosting.datastore.testing.DatastoreTestTrace.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class ValidationRule extends GeneratedMessageLite<ValidationRule, Builder> implements ValidationRuleOrBuilder {
        /* access modifiers changed from: private */
        public static final ValidationRule DEFAULT_INSTANCE;
        private static volatile Parser<ValidationRule> PARSER = null;
        public static final int VALIDATE_QUERY_INDEXES_FIELD_NUMBER = 2;
        public static final int VALIDATE_QUERY_RESULT_ORDER_FIELD_NUMBER = 1;
        private boolean validateQueryIndexes_;
        private boolean validateQueryResultOrder_;

        private ValidationRule() {
        }

        public boolean getValidateQueryResultOrder() {
            return this.validateQueryResultOrder_;
        }

        /* access modifiers changed from: private */
        public void setValidateQueryResultOrder(boolean z) {
            this.validateQueryResultOrder_ = z;
        }

        /* access modifiers changed from: private */
        public void clearValidateQueryResultOrder() {
            this.validateQueryResultOrder_ = false;
        }

        public boolean getValidateQueryIndexes() {
            return this.validateQueryIndexes_;
        }

        /* access modifiers changed from: private */
        public void setValidateQueryIndexes(boolean z) {
            this.validateQueryIndexes_ = z;
        }

        /* access modifiers changed from: private */
        public void clearValidateQueryIndexes() {
            this.validateQueryIndexes_ = false;
        }

        public static ValidationRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ValidationRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ValidationRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ValidationRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(InputStream inputStream) throws IOException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ValidationRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ValidationRule parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ValidationRule) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ValidationRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ValidationRule) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ValidationRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ValidationRule validationRule) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(validationRule);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ValidationRule, Builder> implements ValidationRuleOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ValidationRule.DEFAULT_INSTANCE);
            }

            public boolean getValidateQueryResultOrder() {
                return ((ValidationRule) this.instance).getValidateQueryResultOrder();
            }

            public Builder setValidateQueryResultOrder(boolean z) {
                copyOnWrite();
                ((ValidationRule) this.instance).setValidateQueryResultOrder(z);
                return this;
            }

            public Builder clearValidateQueryResultOrder() {
                copyOnWrite();
                ((ValidationRule) this.instance).clearValidateQueryResultOrder();
                return this;
            }

            public boolean getValidateQueryIndexes() {
                return ((ValidationRule) this.instance).getValidateQueryIndexes();
            }

            public Builder setValidateQueryIndexes(boolean z) {
                copyOnWrite();
                ((ValidationRule) this.instance).setValidateQueryIndexes(z);
                return this;
            }

            public Builder clearValidateQueryIndexes() {
                copyOnWrite();
                ((ValidationRule) this.instance).clearValidateQueryIndexes();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ValidationRule();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0007\u0002\u0007", new Object[]{"validateQueryResultOrder_", "validateQueryIndexes_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ValidationRule> parser = PARSER;
                    if (parser == null) {
                        synchronized (ValidationRule.class) {
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
            ValidationRule validationRule = new ValidationRule();
            DEFAULT_INSTANCE = validationRule;
            GeneratedMessageLite.registerDefaultInstance(ValidationRule.class, validationRule);
        }

        public static ValidationRule getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ValidationRule> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class FirestoreV1Action extends GeneratedMessageLite<FirestoreV1Action, Builder> implements FirestoreV1ActionOrBuilder {
        public static final int BATCH_GET_DOCUMENTS_FIELD_NUMBER = 10;
        public static final int BEGIN_TRANSACTION_FIELD_NUMBER = 6;
        public static final int COMMIT_FIELD_NUMBER = 7;
        public static final int CREATE_DOCUMENT_FIELD_NUMBER = 3;
        public static final int DATABASE_CONTENTS_BEFORE_ACTION_FIELD_NUMBER = 202;
        /* access modifiers changed from: private */
        public static final FirestoreV1Action DEFAULT_INSTANCE;
        public static final int DELETE_DOCUMENT_FIELD_NUMBER = 5;
        public static final int GET_DOCUMENT_FIELD_NUMBER = 1;
        public static final int LISTEN_FIELD_NUMBER = 12;
        public static final int LIST_COLLECTION_IDS_FIELD_NUMBER = 9;
        public static final int LIST_DOCUMENTS_FIELD_NUMBER = 2;
        public static final int MATCHING_DOCUMENTS_FIELD_NUMBER = 203;
        private static volatile Parser<FirestoreV1Action> PARSER = null;
        public static final int REMOVE_LISTEN_FIELD_NUMBER = 13;
        public static final int ROLLBACK_FIELD_NUMBER = 8;
        public static final int RUN_QUERY_FIELD_NUMBER = 11;
        public static final int STATUS_FIELD_NUMBER = 201;
        public static final int UPDATE_DOCUMENT_FIELD_NUMBER = 4;
        private int actionCase_ = 0;
        private Object action_;
        private int bitField0_;
        private RunQuery databaseContentsBeforeAction_;
        private Internal.ProtobufList<MatchingDocuments> matchingDocuments_ = emptyProtobufList();
        private StatusProto status_;

        public interface BatchGetDocumentsOrBuilder extends MessageLiteOrBuilder {
            BatchGetDocumentsRequest getRequest();

            BatchGetDocumentsResponse getResponse(int i);

            int getResponseCount();

            List<BatchGetDocumentsResponse> getResponseList();

            boolean hasRequest();
        }

        public interface BeginTransactionOrBuilder extends MessageLiteOrBuilder {
            BeginTransactionRequest getRequest();

            BeginTransactionResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface CommitOrBuilder extends MessageLiteOrBuilder {
            CommitRequest getRequest();

            CommitResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface CreateDocumentOrBuilder extends MessageLiteOrBuilder {
            CreateDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface DeleteDocumentOrBuilder extends MessageLiteOrBuilder {
            DeleteDocumentRequest getRequest();

            Empty getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface GetDocumentOrBuilder extends MessageLiteOrBuilder {
            GetDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface ListCollectionIdsOrBuilder extends MessageLiteOrBuilder {
            ListCollectionIdsRequest getRequest();

            ListCollectionIdsResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface ListDocumentsOrBuilder extends MessageLiteOrBuilder {
            ListDocumentsRequest getRequest();

            ListDocumentsResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface ListenOrBuilder extends MessageLiteOrBuilder {
            ListenRequest getRequest();

            ListenResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface MatchingDocumentsOrBuilder extends MessageLiteOrBuilder {
            ListenResponse getListenResponse();

            RunQueryResponse getMatchingDocuments();

            boolean hasListenResponse();

            boolean hasMatchingDocuments();
        }

        public interface RemoveListenOrBuilder extends MessageLiteOrBuilder {
            int getTargetId();
        }

        public interface RollbackOrBuilder extends MessageLiteOrBuilder {
            RollbackRequest getRequest();

            Empty getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface RunQueryOrBuilder extends MessageLiteOrBuilder {
            RunQueryRequest getRequest();

            RunQueryResponse getResponse(int i);

            int getResponseCount();

            List<RunQueryResponse> getResponseList();

            boolean hasRequest();
        }

        public interface UpdateDocumentOrBuilder extends MessageLiteOrBuilder {
            UpdateDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        private FirestoreV1Action() {
        }

        public static final class GetDocument extends GeneratedMessageLite<GetDocument, Builder> implements GetDocumentOrBuilder {
            /* access modifiers changed from: private */
            public static final GetDocument DEFAULT_INSTANCE;
            private static volatile Parser<GetDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private GetDocumentRequest request_;
            private Document response_;

            private GetDocument() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public GetDocumentRequest getRequest() {
                GetDocumentRequest getDocumentRequest = this.request_;
                return getDocumentRequest == null ? GetDocumentRequest.getDefaultInstance() : getDocumentRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(GetDocumentRequest getDocumentRequest) {
                getDocumentRequest.getClass();
                this.request_ = getDocumentRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(GetDocumentRequest getDocumentRequest) {
                getDocumentRequest.getClass();
                GetDocumentRequest getDocumentRequest2 = this.request_;
                if (getDocumentRequest2 == null || getDocumentRequest2 == GetDocumentRequest.getDefaultInstance()) {
                    this.request_ = getDocumentRequest;
                } else {
                    this.request_ = (GetDocumentRequest) ((GetDocumentRequest.Builder) GetDocumentRequest.newBuilder(this.request_).mergeFrom(getDocumentRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.getDefaultInstance() : document;
            }

            /* access modifiers changed from: private */
            public void setResponse(Document document) {
                document.getClass();
                this.response_ = document;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Document document) {
                document.getClass();
                Document document2 = this.response_;
                if (document2 == null || document2 == Document.getDefaultInstance()) {
                    this.response_ = document;
                } else {
                    this.response_ = (Document) ((Document.Builder) Document.newBuilder(this.response_).mergeFrom(document)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static GetDocument parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static GetDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static GetDocument parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static GetDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static GetDocument parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static GetDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static GetDocument parseFrom(InputStream inputStream) throws IOException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static GetDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static GetDocument parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (GetDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static GetDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (GetDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static GetDocument parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static GetDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(GetDocument getDocument) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(getDocument);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<GetDocument, Builder> implements GetDocumentOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(GetDocument.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((GetDocument) this.instance).hasRequest();
                }

                public GetDocumentRequest getRequest() {
                    return ((GetDocument) this.instance).getRequest();
                }

                public Builder setRequest(GetDocumentRequest getDocumentRequest) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setRequest(getDocumentRequest);
                    return this;
                }

                public Builder setRequest(GetDocumentRequest.Builder builder) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setRequest((GetDocumentRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(GetDocumentRequest getDocumentRequest) {
                    copyOnWrite();
                    ((GetDocument) this.instance).mergeRequest(getDocumentRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((GetDocument) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((GetDocument) this.instance).hasResponse();
                }

                public Document getResponse() {
                    return ((GetDocument) this.instance).getResponse();
                }

                public Builder setResponse(Document document) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setResponse(document);
                    return this;
                }

                public Builder setResponse(Document.Builder builder) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setResponse((Document) builder.build());
                    return this;
                }

                public Builder mergeResponse(Document document) {
                    copyOnWrite();
                    ((GetDocument) this.instance).mergeResponse(document);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((GetDocument) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new GetDocument();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<GetDocument> parser = PARSER;
                        if (parser == null) {
                            synchronized (GetDocument.class) {
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
                GetDocument getDocument = new GetDocument();
                DEFAULT_INSTANCE = getDocument;
                GeneratedMessageLite.registerDefaultInstance(GetDocument.class, getDocument);
            }

            public static GetDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<GetDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class ListDocuments extends GeneratedMessageLite<ListDocuments, Builder> implements ListDocumentsOrBuilder {
            /* access modifiers changed from: private */
            public static final ListDocuments DEFAULT_INSTANCE;
            private static volatile Parser<ListDocuments> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private ListDocumentsRequest request_;
            private ListDocumentsResponse response_;

            private ListDocuments() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public ListDocumentsRequest getRequest() {
                ListDocumentsRequest listDocumentsRequest = this.request_;
                return listDocumentsRequest == null ? ListDocumentsRequest.getDefaultInstance() : listDocumentsRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(ListDocumentsRequest listDocumentsRequest) {
                listDocumentsRequest.getClass();
                this.request_ = listDocumentsRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(ListDocumentsRequest listDocumentsRequest) {
                listDocumentsRequest.getClass();
                ListDocumentsRequest listDocumentsRequest2 = this.request_;
                if (listDocumentsRequest2 == null || listDocumentsRequest2 == ListDocumentsRequest.getDefaultInstance()) {
                    this.request_ = listDocumentsRequest;
                } else {
                    this.request_ = (ListDocumentsRequest) ((ListDocumentsRequest.Builder) ListDocumentsRequest.newBuilder(this.request_).mergeFrom(listDocumentsRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public ListDocumentsResponse getResponse() {
                ListDocumentsResponse listDocumentsResponse = this.response_;
                return listDocumentsResponse == null ? ListDocumentsResponse.getDefaultInstance() : listDocumentsResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(ListDocumentsResponse listDocumentsResponse) {
                listDocumentsResponse.getClass();
                this.response_ = listDocumentsResponse;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(ListDocumentsResponse listDocumentsResponse) {
                listDocumentsResponse.getClass();
                ListDocumentsResponse listDocumentsResponse2 = this.response_;
                if (listDocumentsResponse2 == null || listDocumentsResponse2 == ListDocumentsResponse.getDefaultInstance()) {
                    this.response_ = listDocumentsResponse;
                } else {
                    this.response_ = (ListDocumentsResponse) ((ListDocumentsResponse.Builder) ListDocumentsResponse.newBuilder(this.response_).mergeFrom(listDocumentsResponse)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static ListDocuments parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static ListDocuments parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static ListDocuments parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static ListDocuments parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(InputStream inputStream) throws IOException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static ListDocuments parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static ListDocuments parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (ListDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static ListDocuments parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ListDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static ListDocuments parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(ListDocuments listDocuments) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(listDocuments);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ListDocuments, Builder> implements ListDocumentsOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(ListDocuments.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((ListDocuments) this.instance).hasRequest();
                }

                public ListDocumentsRequest getRequest() {
                    return ((ListDocuments) this.instance).getRequest();
                }

                public Builder setRequest(ListDocumentsRequest listDocumentsRequest) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setRequest(listDocumentsRequest);
                    return this;
                }

                public Builder setRequest(ListDocumentsRequest.Builder builder) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setRequest((ListDocumentsRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(ListDocumentsRequest listDocumentsRequest) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).mergeRequest(listDocumentsRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((ListDocuments) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((ListDocuments) this.instance).hasResponse();
                }

                public ListDocumentsResponse getResponse() {
                    return ((ListDocuments) this.instance).getResponse();
                }

                public Builder setResponse(ListDocumentsResponse listDocumentsResponse) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setResponse(listDocumentsResponse);
                    return this;
                }

                public Builder setResponse(ListDocumentsResponse.Builder builder) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setResponse((ListDocumentsResponse) builder.build());
                    return this;
                }

                public Builder mergeResponse(ListDocumentsResponse listDocumentsResponse) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).mergeResponse(listDocumentsResponse);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((ListDocuments) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new ListDocuments();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<ListDocuments> parser = PARSER;
                        if (parser == null) {
                            synchronized (ListDocuments.class) {
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
                ListDocuments listDocuments = new ListDocuments();
                DEFAULT_INSTANCE = listDocuments;
                GeneratedMessageLite.registerDefaultInstance(ListDocuments.class, listDocuments);
            }

            public static ListDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ListDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class CreateDocument extends GeneratedMessageLite<CreateDocument, Builder> implements CreateDocumentOrBuilder {
            /* access modifiers changed from: private */
            public static final CreateDocument DEFAULT_INSTANCE;
            private static volatile Parser<CreateDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private CreateDocumentRequest request_;
            private Document response_;

            private CreateDocument() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public CreateDocumentRequest getRequest() {
                CreateDocumentRequest createDocumentRequest = this.request_;
                return createDocumentRequest == null ? CreateDocumentRequest.getDefaultInstance() : createDocumentRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(CreateDocumentRequest createDocumentRequest) {
                createDocumentRequest.getClass();
                this.request_ = createDocumentRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(CreateDocumentRequest createDocumentRequest) {
                createDocumentRequest.getClass();
                CreateDocumentRequest createDocumentRequest2 = this.request_;
                if (createDocumentRequest2 == null || createDocumentRequest2 == CreateDocumentRequest.getDefaultInstance()) {
                    this.request_ = createDocumentRequest;
                } else {
                    this.request_ = (CreateDocumentRequest) ((CreateDocumentRequest.Builder) CreateDocumentRequest.newBuilder(this.request_).mergeFrom(createDocumentRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.getDefaultInstance() : document;
            }

            /* access modifiers changed from: private */
            public void setResponse(Document document) {
                document.getClass();
                this.response_ = document;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Document document) {
                document.getClass();
                Document document2 = this.response_;
                if (document2 == null || document2 == Document.getDefaultInstance()) {
                    this.response_ = document;
                } else {
                    this.response_ = (Document) ((Document.Builder) Document.newBuilder(this.response_).mergeFrom(document)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static CreateDocument parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static CreateDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static CreateDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static CreateDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(InputStream inputStream) throws IOException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static CreateDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static CreateDocument parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (CreateDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static CreateDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CreateDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static CreateDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(CreateDocument createDocument) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(createDocument);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<CreateDocument, Builder> implements CreateDocumentOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(CreateDocument.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((CreateDocument) this.instance).hasRequest();
                }

                public CreateDocumentRequest getRequest() {
                    return ((CreateDocument) this.instance).getRequest();
                }

                public Builder setRequest(CreateDocumentRequest createDocumentRequest) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setRequest(createDocumentRequest);
                    return this;
                }

                public Builder setRequest(CreateDocumentRequest.Builder builder) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setRequest((CreateDocumentRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(CreateDocumentRequest createDocumentRequest) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).mergeRequest(createDocumentRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((CreateDocument) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((CreateDocument) this.instance).hasResponse();
                }

                public Document getResponse() {
                    return ((CreateDocument) this.instance).getResponse();
                }

                public Builder setResponse(Document document) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setResponse(document);
                    return this;
                }

                public Builder setResponse(Document.Builder builder) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setResponse((Document) builder.build());
                    return this;
                }

                public Builder mergeResponse(Document document) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).mergeResponse(document);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((CreateDocument) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new CreateDocument();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<CreateDocument> parser = PARSER;
                        if (parser == null) {
                            synchronized (CreateDocument.class) {
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
                CreateDocument createDocument = new CreateDocument();
                DEFAULT_INSTANCE = createDocument;
                GeneratedMessageLite.registerDefaultInstance(CreateDocument.class, createDocument);
            }

            public static CreateDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CreateDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class UpdateDocument extends GeneratedMessageLite<UpdateDocument, Builder> implements UpdateDocumentOrBuilder {
            /* access modifiers changed from: private */
            public static final UpdateDocument DEFAULT_INSTANCE;
            private static volatile Parser<UpdateDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private UpdateDocumentRequest request_;
            private Document response_;

            private UpdateDocument() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public UpdateDocumentRequest getRequest() {
                UpdateDocumentRequest updateDocumentRequest = this.request_;
                return updateDocumentRequest == null ? UpdateDocumentRequest.getDefaultInstance() : updateDocumentRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(UpdateDocumentRequest updateDocumentRequest) {
                updateDocumentRequest.getClass();
                this.request_ = updateDocumentRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(UpdateDocumentRequest updateDocumentRequest) {
                updateDocumentRequest.getClass();
                UpdateDocumentRequest updateDocumentRequest2 = this.request_;
                if (updateDocumentRequest2 == null || updateDocumentRequest2 == UpdateDocumentRequest.getDefaultInstance()) {
                    this.request_ = updateDocumentRequest;
                } else {
                    this.request_ = (UpdateDocumentRequest) ((UpdateDocumentRequest.Builder) UpdateDocumentRequest.newBuilder(this.request_).mergeFrom(updateDocumentRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.getDefaultInstance() : document;
            }

            /* access modifiers changed from: private */
            public void setResponse(Document document) {
                document.getClass();
                this.response_ = document;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Document document) {
                document.getClass();
                Document document2 = this.response_;
                if (document2 == null || document2 == Document.getDefaultInstance()) {
                    this.response_ = document;
                } else {
                    this.response_ = (Document) ((Document.Builder) Document.newBuilder(this.response_).mergeFrom(document)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static UpdateDocument parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static UpdateDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static UpdateDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static UpdateDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(InputStream inputStream) throws IOException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static UpdateDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static UpdateDocument parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (UpdateDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static UpdateDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (UpdateDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static UpdateDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(UpdateDocument updateDocument) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(updateDocument);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<UpdateDocument, Builder> implements UpdateDocumentOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(UpdateDocument.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((UpdateDocument) this.instance).hasRequest();
                }

                public UpdateDocumentRequest getRequest() {
                    return ((UpdateDocument) this.instance).getRequest();
                }

                public Builder setRequest(UpdateDocumentRequest updateDocumentRequest) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setRequest(updateDocumentRequest);
                    return this;
                }

                public Builder setRequest(UpdateDocumentRequest.Builder builder) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setRequest((UpdateDocumentRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(UpdateDocumentRequest updateDocumentRequest) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).mergeRequest(updateDocumentRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((UpdateDocument) this.instance).hasResponse();
                }

                public Document getResponse() {
                    return ((UpdateDocument) this.instance).getResponse();
                }

                public Builder setResponse(Document document) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setResponse(document);
                    return this;
                }

                public Builder setResponse(Document.Builder builder) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setResponse((Document) builder.build());
                    return this;
                }

                public Builder mergeResponse(Document document) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).mergeResponse(document);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new UpdateDocument();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<UpdateDocument> parser = PARSER;
                        if (parser == null) {
                            synchronized (UpdateDocument.class) {
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
                UpdateDocument updateDocument = new UpdateDocument();
                DEFAULT_INSTANCE = updateDocument;
                GeneratedMessageLite.registerDefaultInstance(UpdateDocument.class, updateDocument);
            }

            public static UpdateDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<UpdateDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class DeleteDocument extends GeneratedMessageLite<DeleteDocument, Builder> implements DeleteDocumentOrBuilder {
            /* access modifiers changed from: private */
            public static final DeleteDocument DEFAULT_INSTANCE;
            private static volatile Parser<DeleteDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private DeleteDocumentRequest request_;
            private Empty response_;

            private DeleteDocument() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public DeleteDocumentRequest getRequest() {
                DeleteDocumentRequest deleteDocumentRequest = this.request_;
                return deleteDocumentRequest == null ? DeleteDocumentRequest.getDefaultInstance() : deleteDocumentRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(DeleteDocumentRequest deleteDocumentRequest) {
                deleteDocumentRequest.getClass();
                this.request_ = deleteDocumentRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(DeleteDocumentRequest deleteDocumentRequest) {
                deleteDocumentRequest.getClass();
                DeleteDocumentRequest deleteDocumentRequest2 = this.request_;
                if (deleteDocumentRequest2 == null || deleteDocumentRequest2 == DeleteDocumentRequest.getDefaultInstance()) {
                    this.request_ = deleteDocumentRequest;
                } else {
                    this.request_ = (DeleteDocumentRequest) ((DeleteDocumentRequest.Builder) DeleteDocumentRequest.newBuilder(this.request_).mergeFrom(deleteDocumentRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public Empty getResponse() {
                Empty empty = this.response_;
                return empty == null ? Empty.getDefaultInstance() : empty;
            }

            /* access modifiers changed from: private */
            public void setResponse(Empty empty) {
                empty.getClass();
                this.response_ = empty;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Empty empty) {
                empty.getClass();
                Empty empty2 = this.response_;
                if (empty2 == null || empty2 == Empty.getDefaultInstance()) {
                    this.response_ = empty;
                } else {
                    this.response_ = (Empty) ((Empty.Builder) Empty.newBuilder(this.response_).mergeFrom(empty)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static DeleteDocument parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static DeleteDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static DeleteDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static DeleteDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(InputStream inputStream) throws IOException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static DeleteDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static DeleteDocument parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (DeleteDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static DeleteDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (DeleteDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static DeleteDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(DeleteDocument deleteDocument) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(deleteDocument);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<DeleteDocument, Builder> implements DeleteDocumentOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(DeleteDocument.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((DeleteDocument) this.instance).hasRequest();
                }

                public DeleteDocumentRequest getRequest() {
                    return ((DeleteDocument) this.instance).getRequest();
                }

                public Builder setRequest(DeleteDocumentRequest deleteDocumentRequest) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setRequest(deleteDocumentRequest);
                    return this;
                }

                public Builder setRequest(DeleteDocumentRequest.Builder builder) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setRequest((DeleteDocumentRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(DeleteDocumentRequest deleteDocumentRequest) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).mergeRequest(deleteDocumentRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((DeleteDocument) this.instance).hasResponse();
                }

                public Empty getResponse() {
                    return ((DeleteDocument) this.instance).getResponse();
                }

                public Builder setResponse(Empty empty) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setResponse(empty);
                    return this;
                }

                public Builder setResponse(Empty.Builder builder) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setResponse((Empty) builder.build());
                    return this;
                }

                public Builder mergeResponse(Empty empty) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).mergeResponse(empty);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new DeleteDocument();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<DeleteDocument> parser = PARSER;
                        if (parser == null) {
                            synchronized (DeleteDocument.class) {
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
                DeleteDocument deleteDocument = new DeleteDocument();
                DEFAULT_INSTANCE = deleteDocument;
                GeneratedMessageLite.registerDefaultInstance(DeleteDocument.class, deleteDocument);
            }

            public static DeleteDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<DeleteDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class BeginTransaction extends GeneratedMessageLite<BeginTransaction, Builder> implements BeginTransactionOrBuilder {
            /* access modifiers changed from: private */
            public static final BeginTransaction DEFAULT_INSTANCE;
            private static volatile Parser<BeginTransaction> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private BeginTransactionRequest request_;
            private BeginTransactionResponse response_;

            private BeginTransaction() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public BeginTransactionRequest getRequest() {
                BeginTransactionRequest beginTransactionRequest = this.request_;
                return beginTransactionRequest == null ? BeginTransactionRequest.getDefaultInstance() : beginTransactionRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(BeginTransactionRequest beginTransactionRequest) {
                beginTransactionRequest.getClass();
                this.request_ = beginTransactionRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(BeginTransactionRequest beginTransactionRequest) {
                beginTransactionRequest.getClass();
                BeginTransactionRequest beginTransactionRequest2 = this.request_;
                if (beginTransactionRequest2 == null || beginTransactionRequest2 == BeginTransactionRequest.getDefaultInstance()) {
                    this.request_ = beginTransactionRequest;
                } else {
                    this.request_ = (BeginTransactionRequest) ((BeginTransactionRequest.Builder) BeginTransactionRequest.newBuilder(this.request_).mergeFrom(beginTransactionRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public BeginTransactionResponse getResponse() {
                BeginTransactionResponse beginTransactionResponse = this.response_;
                return beginTransactionResponse == null ? BeginTransactionResponse.getDefaultInstance() : beginTransactionResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(BeginTransactionResponse beginTransactionResponse) {
                beginTransactionResponse.getClass();
                this.response_ = beginTransactionResponse;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(BeginTransactionResponse beginTransactionResponse) {
                beginTransactionResponse.getClass();
                BeginTransactionResponse beginTransactionResponse2 = this.response_;
                if (beginTransactionResponse2 == null || beginTransactionResponse2 == BeginTransactionResponse.getDefaultInstance()) {
                    this.response_ = beginTransactionResponse;
                } else {
                    this.response_ = (BeginTransactionResponse) ((BeginTransactionResponse.Builder) BeginTransactionResponse.newBuilder(this.response_).mergeFrom(beginTransactionResponse)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static BeginTransaction parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static BeginTransaction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static BeginTransaction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static BeginTransaction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(InputStream inputStream) throws IOException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BeginTransaction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static BeginTransaction parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (BeginTransaction) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BeginTransaction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BeginTransaction) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static BeginTransaction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(BeginTransaction beginTransaction) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(beginTransaction);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<BeginTransaction, Builder> implements BeginTransactionOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(BeginTransaction.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((BeginTransaction) this.instance).hasRequest();
                }

                public BeginTransactionRequest getRequest() {
                    return ((BeginTransaction) this.instance).getRequest();
                }

                public Builder setRequest(BeginTransactionRequest beginTransactionRequest) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setRequest(beginTransactionRequest);
                    return this;
                }

                public Builder setRequest(BeginTransactionRequest.Builder builder) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setRequest((BeginTransactionRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(BeginTransactionRequest beginTransactionRequest) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).mergeRequest(beginTransactionRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((BeginTransaction) this.instance).hasResponse();
                }

                public BeginTransactionResponse getResponse() {
                    return ((BeginTransaction) this.instance).getResponse();
                }

                public Builder setResponse(BeginTransactionResponse beginTransactionResponse) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setResponse(beginTransactionResponse);
                    return this;
                }

                public Builder setResponse(BeginTransactionResponse.Builder builder) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setResponse((BeginTransactionResponse) builder.build());
                    return this;
                }

                public Builder mergeResponse(BeginTransactionResponse beginTransactionResponse) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).mergeResponse(beginTransactionResponse);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new BeginTransaction();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<BeginTransaction> parser = PARSER;
                        if (parser == null) {
                            synchronized (BeginTransaction.class) {
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
                BeginTransaction beginTransaction = new BeginTransaction();
                DEFAULT_INSTANCE = beginTransaction;
                GeneratedMessageLite.registerDefaultInstance(BeginTransaction.class, beginTransaction);
            }

            public static BeginTransaction getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<BeginTransaction> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Commit extends GeneratedMessageLite<Commit, Builder> implements CommitOrBuilder {
            /* access modifiers changed from: private */
            public static final Commit DEFAULT_INSTANCE;
            private static volatile Parser<Commit> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private CommitRequest request_;
            private CommitResponse response_;

            private Commit() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public CommitRequest getRequest() {
                CommitRequest commitRequest = this.request_;
                return commitRequest == null ? CommitRequest.getDefaultInstance() : commitRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(CommitRequest commitRequest) {
                commitRequest.getClass();
                this.request_ = commitRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(CommitRequest commitRequest) {
                commitRequest.getClass();
                CommitRequest commitRequest2 = this.request_;
                if (commitRequest2 == null || commitRequest2 == CommitRequest.getDefaultInstance()) {
                    this.request_ = commitRequest;
                } else {
                    this.request_ = (CommitRequest) ((CommitRequest.Builder) CommitRequest.newBuilder(this.request_).mergeFrom(commitRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public CommitResponse getResponse() {
                CommitResponse commitResponse = this.response_;
                return commitResponse == null ? CommitResponse.getDefaultInstance() : commitResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(CommitResponse commitResponse) {
                commitResponse.getClass();
                this.response_ = commitResponse;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(CommitResponse commitResponse) {
                commitResponse.getClass();
                CommitResponse commitResponse2 = this.response_;
                if (commitResponse2 == null || commitResponse2 == CommitResponse.getDefaultInstance()) {
                    this.response_ = commitResponse;
                } else {
                    this.response_ = (CommitResponse) ((CommitResponse.Builder) CommitResponse.newBuilder(this.response_).mergeFrom(commitResponse)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static Commit parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Commit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Commit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Commit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Commit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Commit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Commit parseFrom(InputStream inputStream) throws IOException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Commit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Commit parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Commit) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Commit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Commit) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Commit parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Commit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Commit commit) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(commit);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Commit, Builder> implements CommitOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(Commit.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((Commit) this.instance).hasRequest();
                }

                public CommitRequest getRequest() {
                    return ((Commit) this.instance).getRequest();
                }

                public Builder setRequest(CommitRequest commitRequest) {
                    copyOnWrite();
                    ((Commit) this.instance).setRequest(commitRequest);
                    return this;
                }

                public Builder setRequest(CommitRequest.Builder builder) {
                    copyOnWrite();
                    ((Commit) this.instance).setRequest((CommitRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(CommitRequest commitRequest) {
                    copyOnWrite();
                    ((Commit) this.instance).mergeRequest(commitRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Commit) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((Commit) this.instance).hasResponse();
                }

                public CommitResponse getResponse() {
                    return ((Commit) this.instance).getResponse();
                }

                public Builder setResponse(CommitResponse commitResponse) {
                    copyOnWrite();
                    ((Commit) this.instance).setResponse(commitResponse);
                    return this;
                }

                public Builder setResponse(CommitResponse.Builder builder) {
                    copyOnWrite();
                    ((Commit) this.instance).setResponse((CommitResponse) builder.build());
                    return this;
                }

                public Builder mergeResponse(CommitResponse commitResponse) {
                    copyOnWrite();
                    ((Commit) this.instance).mergeResponse(commitResponse);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Commit) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new Commit();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Commit> parser = PARSER;
                        if (parser == null) {
                            synchronized (Commit.class) {
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
                Commit commit = new Commit();
                DEFAULT_INSTANCE = commit;
                GeneratedMessageLite.registerDefaultInstance(Commit.class, commit);
            }

            public static Commit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Commit> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Rollback extends GeneratedMessageLite<Rollback, Builder> implements RollbackOrBuilder {
            /* access modifiers changed from: private */
            public static final Rollback DEFAULT_INSTANCE;
            private static volatile Parser<Rollback> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private RollbackRequest request_;
            private Empty response_;

            private Rollback() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public RollbackRequest getRequest() {
                RollbackRequest rollbackRequest = this.request_;
                return rollbackRequest == null ? RollbackRequest.getDefaultInstance() : rollbackRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(RollbackRequest rollbackRequest) {
                rollbackRequest.getClass();
                this.request_ = rollbackRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(RollbackRequest rollbackRequest) {
                rollbackRequest.getClass();
                RollbackRequest rollbackRequest2 = this.request_;
                if (rollbackRequest2 == null || rollbackRequest2 == RollbackRequest.getDefaultInstance()) {
                    this.request_ = rollbackRequest;
                } else {
                    this.request_ = (RollbackRequest) ((RollbackRequest.Builder) RollbackRequest.newBuilder(this.request_).mergeFrom(rollbackRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public Empty getResponse() {
                Empty empty = this.response_;
                return empty == null ? Empty.getDefaultInstance() : empty;
            }

            /* access modifiers changed from: private */
            public void setResponse(Empty empty) {
                empty.getClass();
                this.response_ = empty;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Empty empty) {
                empty.getClass();
                Empty empty2 = this.response_;
                if (empty2 == null || empty2 == Empty.getDefaultInstance()) {
                    this.response_ = empty;
                } else {
                    this.response_ = (Empty) ((Empty.Builder) Empty.newBuilder(this.response_).mergeFrom(empty)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static Rollback parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Rollback parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Rollback parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Rollback parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Rollback parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Rollback parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Rollback parseFrom(InputStream inputStream) throws IOException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Rollback parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Rollback parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Rollback) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Rollback parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Rollback) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Rollback parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Rollback parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Rollback rollback) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(rollback);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Rollback, Builder> implements RollbackOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(Rollback.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((Rollback) this.instance).hasRequest();
                }

                public RollbackRequest getRequest() {
                    return ((Rollback) this.instance).getRequest();
                }

                public Builder setRequest(RollbackRequest rollbackRequest) {
                    copyOnWrite();
                    ((Rollback) this.instance).setRequest(rollbackRequest);
                    return this;
                }

                public Builder setRequest(RollbackRequest.Builder builder) {
                    copyOnWrite();
                    ((Rollback) this.instance).setRequest((RollbackRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(RollbackRequest rollbackRequest) {
                    copyOnWrite();
                    ((Rollback) this.instance).mergeRequest(rollbackRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Rollback) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((Rollback) this.instance).hasResponse();
                }

                public Empty getResponse() {
                    return ((Rollback) this.instance).getResponse();
                }

                public Builder setResponse(Empty empty) {
                    copyOnWrite();
                    ((Rollback) this.instance).setResponse(empty);
                    return this;
                }

                public Builder setResponse(Empty.Builder builder) {
                    copyOnWrite();
                    ((Rollback) this.instance).setResponse((Empty) builder.build());
                    return this;
                }

                public Builder mergeResponse(Empty empty) {
                    copyOnWrite();
                    ((Rollback) this.instance).mergeResponse(empty);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Rollback) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new Rollback();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Rollback> parser = PARSER;
                        if (parser == null) {
                            synchronized (Rollback.class) {
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
                Rollback rollback = new Rollback();
                DEFAULT_INSTANCE = rollback;
                GeneratedMessageLite.registerDefaultInstance(Rollback.class, rollback);
            }

            public static Rollback getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Rollback> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class ListCollectionIds extends GeneratedMessageLite<ListCollectionIds, Builder> implements ListCollectionIdsOrBuilder {
            /* access modifiers changed from: private */
            public static final ListCollectionIds DEFAULT_INSTANCE;
            private static volatile Parser<ListCollectionIds> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private ListCollectionIdsRequest request_;
            private ListCollectionIdsResponse response_;

            private ListCollectionIds() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public ListCollectionIdsRequest getRequest() {
                ListCollectionIdsRequest listCollectionIdsRequest = this.request_;
                return listCollectionIdsRequest == null ? ListCollectionIdsRequest.getDefaultInstance() : listCollectionIdsRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(ListCollectionIdsRequest listCollectionIdsRequest) {
                listCollectionIdsRequest.getClass();
                this.request_ = listCollectionIdsRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(ListCollectionIdsRequest listCollectionIdsRequest) {
                listCollectionIdsRequest.getClass();
                ListCollectionIdsRequest listCollectionIdsRequest2 = this.request_;
                if (listCollectionIdsRequest2 == null || listCollectionIdsRequest2 == ListCollectionIdsRequest.getDefaultInstance()) {
                    this.request_ = listCollectionIdsRequest;
                } else {
                    this.request_ = (ListCollectionIdsRequest) ((ListCollectionIdsRequest.Builder) ListCollectionIdsRequest.newBuilder(this.request_).mergeFrom(listCollectionIdsRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public ListCollectionIdsResponse getResponse() {
                ListCollectionIdsResponse listCollectionIdsResponse = this.response_;
                return listCollectionIdsResponse == null ? ListCollectionIdsResponse.getDefaultInstance() : listCollectionIdsResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(ListCollectionIdsResponse listCollectionIdsResponse) {
                listCollectionIdsResponse.getClass();
                this.response_ = listCollectionIdsResponse;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(ListCollectionIdsResponse listCollectionIdsResponse) {
                listCollectionIdsResponse.getClass();
                ListCollectionIdsResponse listCollectionIdsResponse2 = this.response_;
                if (listCollectionIdsResponse2 == null || listCollectionIdsResponse2 == ListCollectionIdsResponse.getDefaultInstance()) {
                    this.response_ = listCollectionIdsResponse;
                } else {
                    this.response_ = (ListCollectionIdsResponse) ((ListCollectionIdsResponse.Builder) ListCollectionIdsResponse.newBuilder(this.response_).mergeFrom(listCollectionIdsResponse)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static ListCollectionIds parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static ListCollectionIds parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static ListCollectionIds parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static ListCollectionIds parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(InputStream inputStream) throws IOException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static ListCollectionIds parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static ListCollectionIds parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (ListCollectionIds) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static ListCollectionIds parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ListCollectionIds) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static ListCollectionIds parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(ListCollectionIds listCollectionIds) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(listCollectionIds);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ListCollectionIds, Builder> implements ListCollectionIdsOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(ListCollectionIds.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((ListCollectionIds) this.instance).hasRequest();
                }

                public ListCollectionIdsRequest getRequest() {
                    return ((ListCollectionIds) this.instance).getRequest();
                }

                public Builder setRequest(ListCollectionIdsRequest listCollectionIdsRequest) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setRequest(listCollectionIdsRequest);
                    return this;
                }

                public Builder setRequest(ListCollectionIdsRequest.Builder builder) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setRequest((ListCollectionIdsRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(ListCollectionIdsRequest listCollectionIdsRequest) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).mergeRequest(listCollectionIdsRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((ListCollectionIds) this.instance).hasResponse();
                }

                public ListCollectionIdsResponse getResponse() {
                    return ((ListCollectionIds) this.instance).getResponse();
                }

                public Builder setResponse(ListCollectionIdsResponse listCollectionIdsResponse) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setResponse(listCollectionIdsResponse);
                    return this;
                }

                public Builder setResponse(ListCollectionIdsResponse.Builder builder) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setResponse((ListCollectionIdsResponse) builder.build());
                    return this;
                }

                public Builder mergeResponse(ListCollectionIdsResponse listCollectionIdsResponse) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).mergeResponse(listCollectionIdsResponse);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new ListCollectionIds();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<ListCollectionIds> parser = PARSER;
                        if (parser == null) {
                            synchronized (ListCollectionIds.class) {
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
                ListCollectionIds listCollectionIds = new ListCollectionIds();
                DEFAULT_INSTANCE = listCollectionIds;
                GeneratedMessageLite.registerDefaultInstance(ListCollectionIds.class, listCollectionIds);
            }

            public static ListCollectionIds getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ListCollectionIds> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class BatchGetDocuments extends GeneratedMessageLite<BatchGetDocuments, Builder> implements BatchGetDocumentsOrBuilder {
            /* access modifiers changed from: private */
            public static final BatchGetDocuments DEFAULT_INSTANCE;
            private static volatile Parser<BatchGetDocuments> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private BatchGetDocumentsRequest request_;
            private Internal.ProtobufList<BatchGetDocumentsResponse> response_ = emptyProtobufList();

            private BatchGetDocuments() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public BatchGetDocumentsRequest getRequest() {
                BatchGetDocumentsRequest batchGetDocumentsRequest = this.request_;
                return batchGetDocumentsRequest == null ? BatchGetDocumentsRequest.getDefaultInstance() : batchGetDocumentsRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(BatchGetDocumentsRequest batchGetDocumentsRequest) {
                batchGetDocumentsRequest.getClass();
                this.request_ = batchGetDocumentsRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(BatchGetDocumentsRequest batchGetDocumentsRequest) {
                batchGetDocumentsRequest.getClass();
                BatchGetDocumentsRequest batchGetDocumentsRequest2 = this.request_;
                if (batchGetDocumentsRequest2 == null || batchGetDocumentsRequest2 == BatchGetDocumentsRequest.getDefaultInstance()) {
                    this.request_ = batchGetDocumentsRequest;
                } else {
                    this.request_ = (BatchGetDocumentsRequest) ((BatchGetDocumentsRequest.Builder) BatchGetDocumentsRequest.newBuilder(this.request_).mergeFrom(batchGetDocumentsRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public List<BatchGetDocumentsResponse> getResponseList() {
                return this.response_;
            }

            public List<? extends BatchGetDocumentsResponseOrBuilder> getResponseOrBuilderList() {
                return this.response_;
            }

            public int getResponseCount() {
                return this.response_.size();
            }

            public BatchGetDocumentsResponse getResponse(int i) {
                return (BatchGetDocumentsResponse) this.response_.get(i);
            }

            public BatchGetDocumentsResponseOrBuilder getResponseOrBuilder(int i) {
                return (BatchGetDocumentsResponseOrBuilder) this.response_.get(i);
            }

            private void ensureResponseIsMutable() {
                Internal.ProtobufList<BatchGetDocumentsResponse> protobufList = this.response_;
                if (!protobufList.isModifiable()) {
                    this.response_ = GeneratedMessageLite.mutableCopy(protobufList);
                }
            }

            /* access modifiers changed from: private */
            public void setResponse(int i, BatchGetDocumentsResponse batchGetDocumentsResponse) {
                batchGetDocumentsResponse.getClass();
                ensureResponseIsMutable();
                this.response_.set(i, batchGetDocumentsResponse);
            }

            /* access modifiers changed from: private */
            public void addResponse(BatchGetDocumentsResponse batchGetDocumentsResponse) {
                batchGetDocumentsResponse.getClass();
                ensureResponseIsMutable();
                this.response_.add(batchGetDocumentsResponse);
            }

            /* access modifiers changed from: private */
            public void addResponse(int i, BatchGetDocumentsResponse batchGetDocumentsResponse) {
                batchGetDocumentsResponse.getClass();
                ensureResponseIsMutable();
                this.response_.add(i, batchGetDocumentsResponse);
            }

            /* access modifiers changed from: private */
            public void addAllResponse(Iterable<? extends BatchGetDocumentsResponse> iterable) {
                ensureResponseIsMutable();
                AbstractMessageLite.addAll(iterable, this.response_);
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            public void removeResponse(int i) {
                ensureResponseIsMutable();
                this.response_.remove(i);
            }

            public static BatchGetDocuments parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static BatchGetDocuments parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static BatchGetDocuments parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static BatchGetDocuments parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(InputStream inputStream) throws IOException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BatchGetDocuments parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static BatchGetDocuments parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (BatchGetDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BatchGetDocuments parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BatchGetDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static BatchGetDocuments parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(BatchGetDocuments batchGetDocuments) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(batchGetDocuments);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<BatchGetDocuments, Builder> implements BatchGetDocumentsOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(BatchGetDocuments.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((BatchGetDocuments) this.instance).hasRequest();
                }

                public BatchGetDocumentsRequest getRequest() {
                    return ((BatchGetDocuments) this.instance).getRequest();
                }

                public Builder setRequest(BatchGetDocumentsRequest batchGetDocumentsRequest) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setRequest(batchGetDocumentsRequest);
                    return this;
                }

                public Builder setRequest(BatchGetDocumentsRequest.Builder builder) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setRequest((BatchGetDocumentsRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(BatchGetDocumentsRequest batchGetDocumentsRequest) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).mergeRequest(batchGetDocumentsRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).clearRequest();
                    return this;
                }

                public List<BatchGetDocumentsResponse> getResponseList() {
                    return Collections.unmodifiableList(((BatchGetDocuments) this.instance).getResponseList());
                }

                public int getResponseCount() {
                    return ((BatchGetDocuments) this.instance).getResponseCount();
                }

                public BatchGetDocumentsResponse getResponse(int i) {
                    return ((BatchGetDocuments) this.instance).getResponse(i);
                }

                public Builder setResponse(int i, BatchGetDocumentsResponse batchGetDocumentsResponse) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setResponse(i, batchGetDocumentsResponse);
                    return this;
                }

                public Builder setResponse(int i, BatchGetDocumentsResponse.Builder builder) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setResponse(i, (BatchGetDocumentsResponse) builder.build());
                    return this;
                }

                public Builder addResponse(BatchGetDocumentsResponse batchGetDocumentsResponse) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(batchGetDocumentsResponse);
                    return this;
                }

                public Builder addResponse(int i, BatchGetDocumentsResponse batchGetDocumentsResponse) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(i, batchGetDocumentsResponse);
                    return this;
                }

                public Builder addResponse(BatchGetDocumentsResponse.Builder builder) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse((BatchGetDocumentsResponse) builder.build());
                    return this;
                }

                public Builder addResponse(int i, BatchGetDocumentsResponse.Builder builder) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(i, (BatchGetDocumentsResponse) builder.build());
                    return this;
                }

                public Builder addAllResponse(Iterable<? extends BatchGetDocumentsResponse> iterable) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addAllResponse(iterable);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).clearResponse();
                    return this;
                }

                public Builder removeResponse(int i) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).removeResponse(i);
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new BatchGetDocuments();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဉ\u0000\u0002\u001b", new Object[]{"bitField0_", "request_", "response_", BatchGetDocumentsResponse.class});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<BatchGetDocuments> parser = PARSER;
                        if (parser == null) {
                            synchronized (BatchGetDocuments.class) {
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
                BatchGetDocuments batchGetDocuments = new BatchGetDocuments();
                DEFAULT_INSTANCE = batchGetDocuments;
                GeneratedMessageLite.registerDefaultInstance(BatchGetDocuments.class, batchGetDocuments);
            }

            public static BatchGetDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<BatchGetDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class RunQuery extends GeneratedMessageLite<RunQuery, Builder> implements RunQueryOrBuilder {
            /* access modifiers changed from: private */
            public static final RunQuery DEFAULT_INSTANCE;
            private static volatile Parser<RunQuery> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private RunQueryRequest request_;
            private Internal.ProtobufList<RunQueryResponse> response_ = emptyProtobufList();

            private RunQuery() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public RunQueryRequest getRequest() {
                RunQueryRequest runQueryRequest = this.request_;
                return runQueryRequest == null ? RunQueryRequest.getDefaultInstance() : runQueryRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(RunQueryRequest runQueryRequest) {
                runQueryRequest.getClass();
                this.request_ = runQueryRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(RunQueryRequest runQueryRequest) {
                runQueryRequest.getClass();
                RunQueryRequest runQueryRequest2 = this.request_;
                if (runQueryRequest2 == null || runQueryRequest2 == RunQueryRequest.getDefaultInstance()) {
                    this.request_ = runQueryRequest;
                } else {
                    this.request_ = (RunQueryRequest) ((RunQueryRequest.Builder) RunQueryRequest.newBuilder(this.request_).mergeFrom(runQueryRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public List<RunQueryResponse> getResponseList() {
                return this.response_;
            }

            public List<? extends RunQueryResponseOrBuilder> getResponseOrBuilderList() {
                return this.response_;
            }

            public int getResponseCount() {
                return this.response_.size();
            }

            public RunQueryResponse getResponse(int i) {
                return (RunQueryResponse) this.response_.get(i);
            }

            public RunQueryResponseOrBuilder getResponseOrBuilder(int i) {
                return (RunQueryResponseOrBuilder) this.response_.get(i);
            }

            private void ensureResponseIsMutable() {
                Internal.ProtobufList<RunQueryResponse> protobufList = this.response_;
                if (!protobufList.isModifiable()) {
                    this.response_ = GeneratedMessageLite.mutableCopy(protobufList);
                }
            }

            /* access modifiers changed from: private */
            public void setResponse(int i, RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                ensureResponseIsMutable();
                this.response_.set(i, runQueryResponse);
            }

            /* access modifiers changed from: private */
            public void addResponse(RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                ensureResponseIsMutable();
                this.response_.add(runQueryResponse);
            }

            /* access modifiers changed from: private */
            public void addResponse(int i, RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                ensureResponseIsMutable();
                this.response_.add(i, runQueryResponse);
            }

            /* access modifiers changed from: private */
            public void addAllResponse(Iterable<? extends RunQueryResponse> iterable) {
                ensureResponseIsMutable();
                AbstractMessageLite.addAll(iterable, this.response_);
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            public void removeResponse(int i) {
                ensureResponseIsMutable();
                this.response_.remove(i);
            }

            public static RunQuery parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static RunQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static RunQuery parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static RunQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static RunQuery parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static RunQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static RunQuery parseFrom(InputStream inputStream) throws IOException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static RunQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static RunQuery parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (RunQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static RunQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (RunQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static RunQuery parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static RunQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(RunQuery runQuery) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(runQuery);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<RunQuery, Builder> implements RunQueryOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(RunQuery.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((RunQuery) this.instance).hasRequest();
                }

                public RunQueryRequest getRequest() {
                    return ((RunQuery) this.instance).getRequest();
                }

                public Builder setRequest(RunQueryRequest runQueryRequest) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setRequest(runQueryRequest);
                    return this;
                }

                public Builder setRequest(RunQueryRequest.Builder builder) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setRequest((RunQueryRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(RunQueryRequest runQueryRequest) {
                    copyOnWrite();
                    ((RunQuery) this.instance).mergeRequest(runQueryRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((RunQuery) this.instance).clearRequest();
                    return this;
                }

                public List<RunQueryResponse> getResponseList() {
                    return Collections.unmodifiableList(((RunQuery) this.instance).getResponseList());
                }

                public int getResponseCount() {
                    return ((RunQuery) this.instance).getResponseCount();
                }

                public RunQueryResponse getResponse(int i) {
                    return ((RunQuery) this.instance).getResponse(i);
                }

                public Builder setResponse(int i, RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setResponse(i, runQueryResponse);
                    return this;
                }

                public Builder setResponse(int i, RunQueryResponse.Builder builder) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setResponse(i, (RunQueryResponse) builder.build());
                    return this;
                }

                public Builder addResponse(RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(runQueryResponse);
                    return this;
                }

                public Builder addResponse(int i, RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(i, runQueryResponse);
                    return this;
                }

                public Builder addResponse(RunQueryResponse.Builder builder) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse((RunQueryResponse) builder.build());
                    return this;
                }

                public Builder addResponse(int i, RunQueryResponse.Builder builder) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(i, (RunQueryResponse) builder.build());
                    return this;
                }

                public Builder addAllResponse(Iterable<? extends RunQueryResponse> iterable) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addAllResponse(iterable);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((RunQuery) this.instance).clearResponse();
                    return this;
                }

                public Builder removeResponse(int i) {
                    copyOnWrite();
                    ((RunQuery) this.instance).removeResponse(i);
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new RunQuery();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဉ\u0000\u0002\u001b", new Object[]{"bitField0_", "request_", "response_", RunQueryResponse.class});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<RunQuery> parser = PARSER;
                        if (parser == null) {
                            synchronized (RunQuery.class) {
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
                RunQuery runQuery = new RunQuery();
                DEFAULT_INSTANCE = runQuery;
                GeneratedMessageLite.registerDefaultInstance(RunQuery.class, runQuery);
            }

            public static RunQuery getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<RunQuery> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Listen extends GeneratedMessageLite<Listen, Builder> implements ListenOrBuilder {
            /* access modifiers changed from: private */
            public static final Listen DEFAULT_INSTANCE;
            private static volatile Parser<Listen> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private int bitField0_;
            private ListenRequest request_;
            private ListenResponse response_;

            private Listen() {
            }

            public boolean hasRequest() {
                return (this.bitField0_ & 1) != 0;
            }

            public ListenRequest getRequest() {
                ListenRequest listenRequest = this.request_;
                return listenRequest == null ? ListenRequest.getDefaultInstance() : listenRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(ListenRequest listenRequest) {
                listenRequest.getClass();
                this.request_ = listenRequest;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(ListenRequest listenRequest) {
                listenRequest.getClass();
                ListenRequest listenRequest2 = this.request_;
                if (listenRequest2 == null || listenRequest2 == ListenRequest.getDefaultInstance()) {
                    this.request_ = listenRequest;
                } else {
                    this.request_ = (ListenRequest) ((ListenRequest.Builder) ListenRequest.newBuilder(this.request_).mergeFrom(listenRequest)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasResponse() {
                return (this.bitField0_ & 2) != 0;
            }

            public ListenResponse getResponse() {
                ListenResponse listenResponse = this.response_;
                return listenResponse == null ? ListenResponse.getDefaultInstance() : listenResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(ListenResponse listenResponse) {
                listenResponse.getClass();
                this.response_ = listenResponse;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(ListenResponse listenResponse) {
                listenResponse.getClass();
                ListenResponse listenResponse2 = this.response_;
                if (listenResponse2 == null || listenResponse2 == ListenResponse.getDefaultInstance()) {
                    this.response_ = listenResponse;
                } else {
                    this.response_ = (ListenResponse) ((ListenResponse.Builder) ListenResponse.newBuilder(this.response_).mergeFrom(listenResponse)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
                this.bitField0_ &= -3;
            }

            public static Listen parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Listen parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Listen parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Listen parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Listen parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Listen parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Listen parseFrom(InputStream inputStream) throws IOException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Listen parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Listen parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Listen) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Listen parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Listen) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Listen parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Listen parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Listen listen) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(listen);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Listen, Builder> implements ListenOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(Listen.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((Listen) this.instance).hasRequest();
                }

                public ListenRequest getRequest() {
                    return ((Listen) this.instance).getRequest();
                }

                public Builder setRequest(ListenRequest listenRequest) {
                    copyOnWrite();
                    ((Listen) this.instance).setRequest(listenRequest);
                    return this;
                }

                public Builder setRequest(ListenRequest.Builder builder) {
                    copyOnWrite();
                    ((Listen) this.instance).setRequest((ListenRequest) builder.build());
                    return this;
                }

                public Builder mergeRequest(ListenRequest listenRequest) {
                    copyOnWrite();
                    ((Listen) this.instance).mergeRequest(listenRequest);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Listen) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((Listen) this.instance).hasResponse();
                }

                public ListenResponse getResponse() {
                    return ((Listen) this.instance).getResponse();
                }

                public Builder setResponse(ListenResponse listenResponse) {
                    copyOnWrite();
                    ((Listen) this.instance).setResponse(listenResponse);
                    return this;
                }

                public Builder setResponse(ListenResponse.Builder builder) {
                    copyOnWrite();
                    ((Listen) this.instance).setResponse((ListenResponse) builder.build());
                    return this;
                }

                public Builder mergeResponse(ListenResponse listenResponse) {
                    copyOnWrite();
                    ((Listen) this.instance).mergeResponse(listenResponse);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Listen) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new Listen();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Listen> parser = PARSER;
                        if (parser == null) {
                            synchronized (Listen.class) {
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
                Listen listen = new Listen();
                DEFAULT_INSTANCE = listen;
                GeneratedMessageLite.registerDefaultInstance(Listen.class, listen);
            }

            public static Listen getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Listen> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class RemoveListen extends GeneratedMessageLite<RemoveListen, Builder> implements RemoveListenOrBuilder {
            /* access modifiers changed from: private */
            public static final RemoveListen DEFAULT_INSTANCE;
            private static volatile Parser<RemoveListen> PARSER = null;
            public static final int TARGET_ID_FIELD_NUMBER = 1;
            private int targetId_;

            private RemoveListen() {
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

            public static RemoveListen parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static RemoveListen parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static RemoveListen parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static RemoveListen parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(InputStream inputStream) throws IOException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static RemoveListen parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static RemoveListen parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (RemoveListen) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static RemoveListen parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (RemoveListen) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static RemoveListen parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(RemoveListen removeListen) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(removeListen);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<RemoveListen, Builder> implements RemoveListenOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(RemoveListen.DEFAULT_INSTANCE);
                }

                public int getTargetId() {
                    return ((RemoveListen) this.instance).getTargetId();
                }

                public Builder setTargetId(int i) {
                    copyOnWrite();
                    ((RemoveListen) this.instance).setTargetId(i);
                    return this;
                }

                public Builder clearTargetId() {
                    copyOnWrite();
                    ((RemoveListen) this.instance).clearTargetId();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new RemoveListen();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004", new Object[]{"targetId_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<RemoveListen> parser = PARSER;
                        if (parser == null) {
                            synchronized (RemoveListen.class) {
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
                RemoveListen removeListen = new RemoveListen();
                DEFAULT_INSTANCE = removeListen;
                GeneratedMessageLite.registerDefaultInstance(RemoveListen.class, removeListen);
            }

            public static RemoveListen getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<RemoveListen> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class MatchingDocuments extends GeneratedMessageLite<MatchingDocuments, Builder> implements MatchingDocumentsOrBuilder {
            /* access modifiers changed from: private */
            public static final MatchingDocuments DEFAULT_INSTANCE;
            public static final int LISTEN_RESPONSE_FIELD_NUMBER = 1;
            public static final int MATCHING_DOCUMENTS_FIELD_NUMBER = 2;
            private static volatile Parser<MatchingDocuments> PARSER;
            private int bitField0_;
            private ListenResponse listenResponse_;
            private RunQueryResponse matchingDocuments_;

            private MatchingDocuments() {
            }

            public boolean hasListenResponse() {
                return (this.bitField0_ & 1) != 0;
            }

            public ListenResponse getListenResponse() {
                ListenResponse listenResponse = this.listenResponse_;
                return listenResponse == null ? ListenResponse.getDefaultInstance() : listenResponse;
            }

            /* access modifiers changed from: private */
            public void setListenResponse(ListenResponse listenResponse) {
                listenResponse.getClass();
                this.listenResponse_ = listenResponse;
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void mergeListenResponse(ListenResponse listenResponse) {
                listenResponse.getClass();
                ListenResponse listenResponse2 = this.listenResponse_;
                if (listenResponse2 == null || listenResponse2 == ListenResponse.getDefaultInstance()) {
                    this.listenResponse_ = listenResponse;
                } else {
                    this.listenResponse_ = (ListenResponse) ((ListenResponse.Builder) ListenResponse.newBuilder(this.listenResponse_).mergeFrom(listenResponse)).buildPartial();
                }
                this.bitField0_ |= 1;
            }

            /* access modifiers changed from: private */
            public void clearListenResponse() {
                this.listenResponse_ = null;
                this.bitField0_ &= -2;
            }

            public boolean hasMatchingDocuments() {
                return (this.bitField0_ & 2) != 0;
            }

            public RunQueryResponse getMatchingDocuments() {
                RunQueryResponse runQueryResponse = this.matchingDocuments_;
                return runQueryResponse == null ? RunQueryResponse.getDefaultInstance() : runQueryResponse;
            }

            /* access modifiers changed from: private */
            public void setMatchingDocuments(RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                this.matchingDocuments_ = runQueryResponse;
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void mergeMatchingDocuments(RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                RunQueryResponse runQueryResponse2 = this.matchingDocuments_;
                if (runQueryResponse2 == null || runQueryResponse2 == RunQueryResponse.getDefaultInstance()) {
                    this.matchingDocuments_ = runQueryResponse;
                } else {
                    this.matchingDocuments_ = (RunQueryResponse) ((RunQueryResponse.Builder) RunQueryResponse.newBuilder(this.matchingDocuments_).mergeFrom(runQueryResponse)).buildPartial();
                }
                this.bitField0_ |= 2;
            }

            /* access modifiers changed from: private */
            public void clearMatchingDocuments() {
                this.matchingDocuments_ = null;
                this.bitField0_ &= -3;
            }

            public static MatchingDocuments parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static MatchingDocuments parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static MatchingDocuments parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static MatchingDocuments parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(InputStream inputStream) throws IOException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static MatchingDocuments parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static MatchingDocuments parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (MatchingDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static MatchingDocuments parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (MatchingDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static MatchingDocuments parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(MatchingDocuments matchingDocuments) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(matchingDocuments);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<MatchingDocuments, Builder> implements MatchingDocumentsOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 r1) {
                    this();
                }

                private Builder() {
                    super(MatchingDocuments.DEFAULT_INSTANCE);
                }

                public boolean hasListenResponse() {
                    return ((MatchingDocuments) this.instance).hasListenResponse();
                }

                public ListenResponse getListenResponse() {
                    return ((MatchingDocuments) this.instance).getListenResponse();
                }

                public Builder setListenResponse(ListenResponse listenResponse) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setListenResponse(listenResponse);
                    return this;
                }

                public Builder setListenResponse(ListenResponse.Builder builder) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setListenResponse((ListenResponse) builder.build());
                    return this;
                }

                public Builder mergeListenResponse(ListenResponse listenResponse) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).mergeListenResponse(listenResponse);
                    return this;
                }

                public Builder clearListenResponse() {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).clearListenResponse();
                    return this;
                }

                public boolean hasMatchingDocuments() {
                    return ((MatchingDocuments) this.instance).hasMatchingDocuments();
                }

                public RunQueryResponse getMatchingDocuments() {
                    return ((MatchingDocuments) this.instance).getMatchingDocuments();
                }

                public Builder setMatchingDocuments(RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setMatchingDocuments(runQueryResponse);
                    return this;
                }

                public Builder setMatchingDocuments(RunQueryResponse.Builder builder) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setMatchingDocuments((RunQueryResponse) builder.build());
                    return this;
                }

                public Builder mergeMatchingDocuments(RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).mergeMatchingDocuments(runQueryResponse);
                    return this;
                }

                public Builder clearMatchingDocuments() {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).clearMatchingDocuments();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new MatchingDocuments();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "listenResponse_", "matchingDocuments_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<MatchingDocuments> parser = PARSER;
                        if (parser == null) {
                            synchronized (MatchingDocuments.class) {
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
                MatchingDocuments matchingDocuments = new MatchingDocuments();
                DEFAULT_INSTANCE = matchingDocuments;
                GeneratedMessageLite.registerDefaultInstance(MatchingDocuments.class, matchingDocuments);
            }

            public static MatchingDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<MatchingDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public enum ActionCase {
            GET_DOCUMENT(1),
            LIST_DOCUMENTS(2),
            CREATE_DOCUMENT(3),
            UPDATE_DOCUMENT(4),
            DELETE_DOCUMENT(5),
            BEGIN_TRANSACTION(6),
            COMMIT(7),
            ROLLBACK(8),
            LIST_COLLECTION_IDS(9),
            BATCH_GET_DOCUMENTS(10),
            RUN_QUERY(11),
            LISTEN(12),
            REMOVE_LISTEN(13),
            ACTION_NOT_SET(0);
            
            private final int value;

            private ActionCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static ActionCase valueOf(int i) {
                return forNumber(i);
            }

            public static ActionCase forNumber(int i) {
                switch (i) {
                    case 0:
                        return ACTION_NOT_SET;
                    case 1:
                        return GET_DOCUMENT;
                    case 2:
                        return LIST_DOCUMENTS;
                    case 3:
                        return CREATE_DOCUMENT;
                    case 4:
                        return UPDATE_DOCUMENT;
                    case 5:
                        return DELETE_DOCUMENT;
                    case 6:
                        return BEGIN_TRANSACTION;
                    case 7:
                        return COMMIT;
                    case 8:
                        return ROLLBACK;
                    case 9:
                        return LIST_COLLECTION_IDS;
                    case 10:
                        return BATCH_GET_DOCUMENTS;
                    case 11:
                        return RUN_QUERY;
                    case 12:
                        return LISTEN;
                    case 13:
                        return REMOVE_LISTEN;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public ActionCase getActionCase() {
            return ActionCase.forNumber(this.actionCase_);
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.actionCase_ = 0;
            this.action_ = null;
        }

        public boolean hasGetDocument() {
            return this.actionCase_ == 1;
        }

        public GetDocument getGetDocument() {
            if (this.actionCase_ == 1) {
                return (GetDocument) this.action_;
            }
            return GetDocument.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setGetDocument(GetDocument getDocument) {
            getDocument.getClass();
            this.action_ = getDocument;
            this.actionCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void mergeGetDocument(GetDocument getDocument) {
            getDocument.getClass();
            if (this.actionCase_ != 1 || this.action_ == GetDocument.getDefaultInstance()) {
                this.action_ = getDocument;
            } else {
                this.action_ = ((GetDocument.Builder) GetDocument.newBuilder((GetDocument) this.action_).mergeFrom(getDocument)).buildPartial();
            }
            this.actionCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void clearGetDocument() {
            if (this.actionCase_ == 1) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasListDocuments() {
            return this.actionCase_ == 2;
        }

        public ListDocuments getListDocuments() {
            if (this.actionCase_ == 2) {
                return (ListDocuments) this.action_;
            }
            return ListDocuments.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setListDocuments(ListDocuments listDocuments) {
            listDocuments.getClass();
            this.action_ = listDocuments;
            this.actionCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeListDocuments(ListDocuments listDocuments) {
            listDocuments.getClass();
            if (this.actionCase_ != 2 || this.action_ == ListDocuments.getDefaultInstance()) {
                this.action_ = listDocuments;
            } else {
                this.action_ = ((ListDocuments.Builder) ListDocuments.newBuilder((ListDocuments) this.action_).mergeFrom(listDocuments)).buildPartial();
            }
            this.actionCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearListDocuments() {
            if (this.actionCase_ == 2) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasCreateDocument() {
            return this.actionCase_ == 3;
        }

        public CreateDocument getCreateDocument() {
            if (this.actionCase_ == 3) {
                return (CreateDocument) this.action_;
            }
            return CreateDocument.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setCreateDocument(CreateDocument createDocument) {
            createDocument.getClass();
            this.action_ = createDocument;
            this.actionCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeCreateDocument(CreateDocument createDocument) {
            createDocument.getClass();
            if (this.actionCase_ != 3 || this.action_ == CreateDocument.getDefaultInstance()) {
                this.action_ = createDocument;
            } else {
                this.action_ = ((CreateDocument.Builder) CreateDocument.newBuilder((CreateDocument) this.action_).mergeFrom(createDocument)).buildPartial();
            }
            this.actionCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearCreateDocument() {
            if (this.actionCase_ == 3) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasUpdateDocument() {
            return this.actionCase_ == 4;
        }

        public UpdateDocument getUpdateDocument() {
            if (this.actionCase_ == 4) {
                return (UpdateDocument) this.action_;
            }
            return UpdateDocument.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setUpdateDocument(UpdateDocument updateDocument) {
            updateDocument.getClass();
            this.action_ = updateDocument;
            this.actionCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void mergeUpdateDocument(UpdateDocument updateDocument) {
            updateDocument.getClass();
            if (this.actionCase_ != 4 || this.action_ == UpdateDocument.getDefaultInstance()) {
                this.action_ = updateDocument;
            } else {
                this.action_ = ((UpdateDocument.Builder) UpdateDocument.newBuilder((UpdateDocument) this.action_).mergeFrom(updateDocument)).buildPartial();
            }
            this.actionCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void clearUpdateDocument() {
            if (this.actionCase_ == 4) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasDeleteDocument() {
            return this.actionCase_ == 5;
        }

        public DeleteDocument getDeleteDocument() {
            if (this.actionCase_ == 5) {
                return (DeleteDocument) this.action_;
            }
            return DeleteDocument.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setDeleteDocument(DeleteDocument deleteDocument) {
            deleteDocument.getClass();
            this.action_ = deleteDocument;
            this.actionCase_ = 5;
        }

        /* access modifiers changed from: private */
        public void mergeDeleteDocument(DeleteDocument deleteDocument) {
            deleteDocument.getClass();
            if (this.actionCase_ != 5 || this.action_ == DeleteDocument.getDefaultInstance()) {
                this.action_ = deleteDocument;
            } else {
                this.action_ = ((DeleteDocument.Builder) DeleteDocument.newBuilder((DeleteDocument) this.action_).mergeFrom(deleteDocument)).buildPartial();
            }
            this.actionCase_ = 5;
        }

        /* access modifiers changed from: private */
        public void clearDeleteDocument() {
            if (this.actionCase_ == 5) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasBeginTransaction() {
            return this.actionCase_ == 6;
        }

        public BeginTransaction getBeginTransaction() {
            if (this.actionCase_ == 6) {
                return (BeginTransaction) this.action_;
            }
            return BeginTransaction.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setBeginTransaction(BeginTransaction beginTransaction) {
            beginTransaction.getClass();
            this.action_ = beginTransaction;
            this.actionCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void mergeBeginTransaction(BeginTransaction beginTransaction) {
            beginTransaction.getClass();
            if (this.actionCase_ != 6 || this.action_ == BeginTransaction.getDefaultInstance()) {
                this.action_ = beginTransaction;
            } else {
                this.action_ = ((BeginTransaction.Builder) BeginTransaction.newBuilder((BeginTransaction) this.action_).mergeFrom(beginTransaction)).buildPartial();
            }
            this.actionCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void clearBeginTransaction() {
            if (this.actionCase_ == 6) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasCommit() {
            return this.actionCase_ == 7;
        }

        public Commit getCommit() {
            if (this.actionCase_ == 7) {
                return (Commit) this.action_;
            }
            return Commit.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setCommit(Commit commit) {
            commit.getClass();
            this.action_ = commit;
            this.actionCase_ = 7;
        }

        /* access modifiers changed from: private */
        public void mergeCommit(Commit commit) {
            commit.getClass();
            if (this.actionCase_ != 7 || this.action_ == Commit.getDefaultInstance()) {
                this.action_ = commit;
            } else {
                this.action_ = ((Commit.Builder) Commit.newBuilder((Commit) this.action_).mergeFrom(commit)).buildPartial();
            }
            this.actionCase_ = 7;
        }

        /* access modifiers changed from: private */
        public void clearCommit() {
            if (this.actionCase_ == 7) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasRollback() {
            return this.actionCase_ == 8;
        }

        public Rollback getRollback() {
            if (this.actionCase_ == 8) {
                return (Rollback) this.action_;
            }
            return Rollback.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setRollback(Rollback rollback) {
            rollback.getClass();
            this.action_ = rollback;
            this.actionCase_ = 8;
        }

        /* access modifiers changed from: private */
        public void mergeRollback(Rollback rollback) {
            rollback.getClass();
            if (this.actionCase_ != 8 || this.action_ == Rollback.getDefaultInstance()) {
                this.action_ = rollback;
            } else {
                this.action_ = ((Rollback.Builder) Rollback.newBuilder((Rollback) this.action_).mergeFrom(rollback)).buildPartial();
            }
            this.actionCase_ = 8;
        }

        /* access modifiers changed from: private */
        public void clearRollback() {
            if (this.actionCase_ == 8) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasListCollectionIds() {
            return this.actionCase_ == 9;
        }

        public ListCollectionIds getListCollectionIds() {
            if (this.actionCase_ == 9) {
                return (ListCollectionIds) this.action_;
            }
            return ListCollectionIds.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setListCollectionIds(ListCollectionIds listCollectionIds) {
            listCollectionIds.getClass();
            this.action_ = listCollectionIds;
            this.actionCase_ = 9;
        }

        /* access modifiers changed from: private */
        public void mergeListCollectionIds(ListCollectionIds listCollectionIds) {
            listCollectionIds.getClass();
            if (this.actionCase_ != 9 || this.action_ == ListCollectionIds.getDefaultInstance()) {
                this.action_ = listCollectionIds;
            } else {
                this.action_ = ((ListCollectionIds.Builder) ListCollectionIds.newBuilder((ListCollectionIds) this.action_).mergeFrom(listCollectionIds)).buildPartial();
            }
            this.actionCase_ = 9;
        }

        /* access modifiers changed from: private */
        public void clearListCollectionIds() {
            if (this.actionCase_ == 9) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasBatchGetDocuments() {
            return this.actionCase_ == 10;
        }

        public BatchGetDocuments getBatchGetDocuments() {
            if (this.actionCase_ == 10) {
                return (BatchGetDocuments) this.action_;
            }
            return BatchGetDocuments.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setBatchGetDocuments(BatchGetDocuments batchGetDocuments) {
            batchGetDocuments.getClass();
            this.action_ = batchGetDocuments;
            this.actionCase_ = 10;
        }

        /* access modifiers changed from: private */
        public void mergeBatchGetDocuments(BatchGetDocuments batchGetDocuments) {
            batchGetDocuments.getClass();
            if (this.actionCase_ != 10 || this.action_ == BatchGetDocuments.getDefaultInstance()) {
                this.action_ = batchGetDocuments;
            } else {
                this.action_ = ((BatchGetDocuments.Builder) BatchGetDocuments.newBuilder((BatchGetDocuments) this.action_).mergeFrom(batchGetDocuments)).buildPartial();
            }
            this.actionCase_ = 10;
        }

        /* access modifiers changed from: private */
        public void clearBatchGetDocuments() {
            if (this.actionCase_ == 10) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasRunQuery() {
            return this.actionCase_ == 11;
        }

        public RunQuery getRunQuery() {
            if (this.actionCase_ == 11) {
                return (RunQuery) this.action_;
            }
            return RunQuery.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setRunQuery(RunQuery runQuery) {
            runQuery.getClass();
            this.action_ = runQuery;
            this.actionCase_ = 11;
        }

        /* access modifiers changed from: private */
        public void mergeRunQuery(RunQuery runQuery) {
            runQuery.getClass();
            if (this.actionCase_ != 11 || this.action_ == RunQuery.getDefaultInstance()) {
                this.action_ = runQuery;
            } else {
                this.action_ = ((RunQuery.Builder) RunQuery.newBuilder((RunQuery) this.action_).mergeFrom(runQuery)).buildPartial();
            }
            this.actionCase_ = 11;
        }

        /* access modifiers changed from: private */
        public void clearRunQuery() {
            if (this.actionCase_ == 11) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasListen() {
            return this.actionCase_ == 12;
        }

        public Listen getListen() {
            if (this.actionCase_ == 12) {
                return (Listen) this.action_;
            }
            return Listen.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setListen(Listen listen) {
            listen.getClass();
            this.action_ = listen;
            this.actionCase_ = 12;
        }

        /* access modifiers changed from: private */
        public void mergeListen(Listen listen) {
            listen.getClass();
            if (this.actionCase_ != 12 || this.action_ == Listen.getDefaultInstance()) {
                this.action_ = listen;
            } else {
                this.action_ = ((Listen.Builder) Listen.newBuilder((Listen) this.action_).mergeFrom(listen)).buildPartial();
            }
            this.actionCase_ = 12;
        }

        /* access modifiers changed from: private */
        public void clearListen() {
            if (this.actionCase_ == 12) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasRemoveListen() {
            return this.actionCase_ == 13;
        }

        public RemoveListen getRemoveListen() {
            if (this.actionCase_ == 13) {
                return (RemoveListen) this.action_;
            }
            return RemoveListen.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setRemoveListen(RemoveListen removeListen) {
            removeListen.getClass();
            this.action_ = removeListen;
            this.actionCase_ = 13;
        }

        /* access modifiers changed from: private */
        public void mergeRemoveListen(RemoveListen removeListen) {
            removeListen.getClass();
            if (this.actionCase_ != 13 || this.action_ == RemoveListen.getDefaultInstance()) {
                this.action_ = removeListen;
            } else {
                this.action_ = ((RemoveListen.Builder) RemoveListen.newBuilder((RemoveListen) this.action_).mergeFrom(removeListen)).buildPartial();
            }
            this.actionCase_ = 13;
        }

        /* access modifiers changed from: private */
        public void clearRemoveListen() {
            if (this.actionCase_ == 13) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasStatus() {
            return (this.bitField0_ & 1) != 0;
        }

        public StatusProto getStatus() {
            StatusProto statusProto = this.status_;
            return statusProto == null ? StatusProto.getDefaultInstance() : statusProto;
        }

        /* access modifiers changed from: private */
        public void setStatus(StatusProto statusProto) {
            statusProto.getClass();
            this.status_ = statusProto;
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void mergeStatus(StatusProto statusProto) {
            statusProto.getClass();
            StatusProto statusProto2 = this.status_;
            if (statusProto2 == null || statusProto2 == StatusProto.getDefaultInstance()) {
                this.status_ = statusProto;
            } else {
                this.status_ = (StatusProto) ((StatusProto.Builder) StatusProto.newBuilder(this.status_).mergeFrom(statusProto)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void clearStatus() {
            this.status_ = null;
            this.bitField0_ &= -2;
        }

        public boolean hasDatabaseContentsBeforeAction() {
            return (this.bitField0_ & 2) != 0;
        }

        public RunQuery getDatabaseContentsBeforeAction() {
            RunQuery runQuery = this.databaseContentsBeforeAction_;
            return runQuery == null ? RunQuery.getDefaultInstance() : runQuery;
        }

        /* access modifiers changed from: private */
        public void setDatabaseContentsBeforeAction(RunQuery runQuery) {
            runQuery.getClass();
            this.databaseContentsBeforeAction_ = runQuery;
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        public void mergeDatabaseContentsBeforeAction(RunQuery runQuery) {
            runQuery.getClass();
            RunQuery runQuery2 = this.databaseContentsBeforeAction_;
            if (runQuery2 == null || runQuery2 == RunQuery.getDefaultInstance()) {
                this.databaseContentsBeforeAction_ = runQuery;
            } else {
                this.databaseContentsBeforeAction_ = (RunQuery) ((RunQuery.Builder) RunQuery.newBuilder(this.databaseContentsBeforeAction_).mergeFrom(runQuery)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        public void clearDatabaseContentsBeforeAction() {
            this.databaseContentsBeforeAction_ = null;
            this.bitField0_ &= -3;
        }

        public List<MatchingDocuments> getMatchingDocumentsList() {
            return this.matchingDocuments_;
        }

        public List<? extends MatchingDocumentsOrBuilder> getMatchingDocumentsOrBuilderList() {
            return this.matchingDocuments_;
        }

        public int getMatchingDocumentsCount() {
            return this.matchingDocuments_.size();
        }

        public MatchingDocuments getMatchingDocuments(int i) {
            return (MatchingDocuments) this.matchingDocuments_.get(i);
        }

        public MatchingDocumentsOrBuilder getMatchingDocumentsOrBuilder(int i) {
            return (MatchingDocumentsOrBuilder) this.matchingDocuments_.get(i);
        }

        private void ensureMatchingDocumentsIsMutable() {
            Internal.ProtobufList<MatchingDocuments> protobufList = this.matchingDocuments_;
            if (!protobufList.isModifiable()) {
                this.matchingDocuments_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
        }

        /* access modifiers changed from: private */
        public void setMatchingDocuments(int i, MatchingDocuments matchingDocuments) {
            matchingDocuments.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.set(i, matchingDocuments);
        }

        /* access modifiers changed from: private */
        public void addMatchingDocuments(MatchingDocuments matchingDocuments) {
            matchingDocuments.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.add(matchingDocuments);
        }

        /* access modifiers changed from: private */
        public void addMatchingDocuments(int i, MatchingDocuments matchingDocuments) {
            matchingDocuments.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.add(i, matchingDocuments);
        }

        /* access modifiers changed from: private */
        public void addAllMatchingDocuments(Iterable<? extends MatchingDocuments> iterable) {
            ensureMatchingDocumentsIsMutable();
            AbstractMessageLite.addAll(iterable, this.matchingDocuments_);
        }

        /* access modifiers changed from: private */
        public void clearMatchingDocuments() {
            this.matchingDocuments_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeMatchingDocuments(int i) {
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.remove(i);
        }

        public static FirestoreV1Action parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static FirestoreV1Action parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FirestoreV1Action parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FirestoreV1Action parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(InputStream inputStream) throws IOException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirestoreV1Action parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirestoreV1Action parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FirestoreV1Action) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirestoreV1Action parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirestoreV1Action) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FirestoreV1Action parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FirestoreV1Action firestoreV1Action) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(firestoreV1Action);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FirestoreV1Action, Builder> implements FirestoreV1ActionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(FirestoreV1Action.DEFAULT_INSTANCE);
            }

            public ActionCase getActionCase() {
                return ((FirestoreV1Action) this.instance).getActionCase();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearAction();
                return this;
            }

            public boolean hasGetDocument() {
                return ((FirestoreV1Action) this.instance).hasGetDocument();
            }

            public GetDocument getGetDocument() {
                return ((FirestoreV1Action) this.instance).getGetDocument();
            }

            public Builder setGetDocument(GetDocument getDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setGetDocument(getDocument);
                return this;
            }

            public Builder setGetDocument(GetDocument.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setGetDocument((GetDocument) builder.build());
                return this;
            }

            public Builder mergeGetDocument(GetDocument getDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeGetDocument(getDocument);
                return this;
            }

            public Builder clearGetDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearGetDocument();
                return this;
            }

            public boolean hasListDocuments() {
                return ((FirestoreV1Action) this.instance).hasListDocuments();
            }

            public ListDocuments getListDocuments() {
                return ((FirestoreV1Action) this.instance).getListDocuments();
            }

            public Builder setListDocuments(ListDocuments listDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListDocuments(listDocuments);
                return this;
            }

            public Builder setListDocuments(ListDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListDocuments((ListDocuments) builder.build());
                return this;
            }

            public Builder mergeListDocuments(ListDocuments listDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListDocuments(listDocuments);
                return this;
            }

            public Builder clearListDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListDocuments();
                return this;
            }

            public boolean hasCreateDocument() {
                return ((FirestoreV1Action) this.instance).hasCreateDocument();
            }

            public CreateDocument getCreateDocument() {
                return ((FirestoreV1Action) this.instance).getCreateDocument();
            }

            public Builder setCreateDocument(CreateDocument createDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCreateDocument(createDocument);
                return this;
            }

            public Builder setCreateDocument(CreateDocument.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCreateDocument((CreateDocument) builder.build());
                return this;
            }

            public Builder mergeCreateDocument(CreateDocument createDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeCreateDocument(createDocument);
                return this;
            }

            public Builder clearCreateDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearCreateDocument();
                return this;
            }

            public boolean hasUpdateDocument() {
                return ((FirestoreV1Action) this.instance).hasUpdateDocument();
            }

            public UpdateDocument getUpdateDocument() {
                return ((FirestoreV1Action) this.instance).getUpdateDocument();
            }

            public Builder setUpdateDocument(UpdateDocument updateDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setUpdateDocument(updateDocument);
                return this;
            }

            public Builder setUpdateDocument(UpdateDocument.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setUpdateDocument((UpdateDocument) builder.build());
                return this;
            }

            public Builder mergeUpdateDocument(UpdateDocument updateDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeUpdateDocument(updateDocument);
                return this;
            }

            public Builder clearUpdateDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearUpdateDocument();
                return this;
            }

            public boolean hasDeleteDocument() {
                return ((FirestoreV1Action) this.instance).hasDeleteDocument();
            }

            public DeleteDocument getDeleteDocument() {
                return ((FirestoreV1Action) this.instance).getDeleteDocument();
            }

            public Builder setDeleteDocument(DeleteDocument deleteDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDeleteDocument(deleteDocument);
                return this;
            }

            public Builder setDeleteDocument(DeleteDocument.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDeleteDocument((DeleteDocument) builder.build());
                return this;
            }

            public Builder mergeDeleteDocument(DeleteDocument deleteDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeDeleteDocument(deleteDocument);
                return this;
            }

            public Builder clearDeleteDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearDeleteDocument();
                return this;
            }

            public boolean hasBeginTransaction() {
                return ((FirestoreV1Action) this.instance).hasBeginTransaction();
            }

            public BeginTransaction getBeginTransaction() {
                return ((FirestoreV1Action) this.instance).getBeginTransaction();
            }

            public Builder setBeginTransaction(BeginTransaction beginTransaction) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBeginTransaction(beginTransaction);
                return this;
            }

            public Builder setBeginTransaction(BeginTransaction.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBeginTransaction((BeginTransaction) builder.build());
                return this;
            }

            public Builder mergeBeginTransaction(BeginTransaction beginTransaction) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeBeginTransaction(beginTransaction);
                return this;
            }

            public Builder clearBeginTransaction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearBeginTransaction();
                return this;
            }

            public boolean hasCommit() {
                return ((FirestoreV1Action) this.instance).hasCommit();
            }

            public Commit getCommit() {
                return ((FirestoreV1Action) this.instance).getCommit();
            }

            public Builder setCommit(Commit commit) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCommit(commit);
                return this;
            }

            public Builder setCommit(Commit.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCommit((Commit) builder.build());
                return this;
            }

            public Builder mergeCommit(Commit commit) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeCommit(commit);
                return this;
            }

            public Builder clearCommit() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearCommit();
                return this;
            }

            public boolean hasRollback() {
                return ((FirestoreV1Action) this.instance).hasRollback();
            }

            public Rollback getRollback() {
                return ((FirestoreV1Action) this.instance).getRollback();
            }

            public Builder setRollback(Rollback rollback) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRollback(rollback);
                return this;
            }

            public Builder setRollback(Rollback.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRollback((Rollback) builder.build());
                return this;
            }

            public Builder mergeRollback(Rollback rollback) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRollback(rollback);
                return this;
            }

            public Builder clearRollback() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRollback();
                return this;
            }

            public boolean hasListCollectionIds() {
                return ((FirestoreV1Action) this.instance).hasListCollectionIds();
            }

            public ListCollectionIds getListCollectionIds() {
                return ((FirestoreV1Action) this.instance).getListCollectionIds();
            }

            public Builder setListCollectionIds(ListCollectionIds listCollectionIds) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListCollectionIds(listCollectionIds);
                return this;
            }

            public Builder setListCollectionIds(ListCollectionIds.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListCollectionIds((ListCollectionIds) builder.build());
                return this;
            }

            public Builder mergeListCollectionIds(ListCollectionIds listCollectionIds) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListCollectionIds(listCollectionIds);
                return this;
            }

            public Builder clearListCollectionIds() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListCollectionIds();
                return this;
            }

            public boolean hasBatchGetDocuments() {
                return ((FirestoreV1Action) this.instance).hasBatchGetDocuments();
            }

            public BatchGetDocuments getBatchGetDocuments() {
                return ((FirestoreV1Action) this.instance).getBatchGetDocuments();
            }

            public Builder setBatchGetDocuments(BatchGetDocuments batchGetDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBatchGetDocuments(batchGetDocuments);
                return this;
            }

            public Builder setBatchGetDocuments(BatchGetDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBatchGetDocuments((BatchGetDocuments) builder.build());
                return this;
            }

            public Builder mergeBatchGetDocuments(BatchGetDocuments batchGetDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeBatchGetDocuments(batchGetDocuments);
                return this;
            }

            public Builder clearBatchGetDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearBatchGetDocuments();
                return this;
            }

            public boolean hasRunQuery() {
                return ((FirestoreV1Action) this.instance).hasRunQuery();
            }

            public RunQuery getRunQuery() {
                return ((FirestoreV1Action) this.instance).getRunQuery();
            }

            public Builder setRunQuery(RunQuery runQuery) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRunQuery(runQuery);
                return this;
            }

            public Builder setRunQuery(RunQuery.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRunQuery((RunQuery) builder.build());
                return this;
            }

            public Builder mergeRunQuery(RunQuery runQuery) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRunQuery(runQuery);
                return this;
            }

            public Builder clearRunQuery() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRunQuery();
                return this;
            }

            public boolean hasListen() {
                return ((FirestoreV1Action) this.instance).hasListen();
            }

            public Listen getListen() {
                return ((FirestoreV1Action) this.instance).getListen();
            }

            public Builder setListen(Listen listen) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListen(listen);
                return this;
            }

            public Builder setListen(Listen.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListen((Listen) builder.build());
                return this;
            }

            public Builder mergeListen(Listen listen) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListen(listen);
                return this;
            }

            public Builder clearListen() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListen();
                return this;
            }

            public boolean hasRemoveListen() {
                return ((FirestoreV1Action) this.instance).hasRemoveListen();
            }

            public RemoveListen getRemoveListen() {
                return ((FirestoreV1Action) this.instance).getRemoveListen();
            }

            public Builder setRemoveListen(RemoveListen removeListen) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRemoveListen(removeListen);
                return this;
            }

            public Builder setRemoveListen(RemoveListen.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRemoveListen((RemoveListen) builder.build());
                return this;
            }

            public Builder mergeRemoveListen(RemoveListen removeListen) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRemoveListen(removeListen);
                return this;
            }

            public Builder clearRemoveListen() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRemoveListen();
                return this;
            }

            public boolean hasStatus() {
                return ((FirestoreV1Action) this.instance).hasStatus();
            }

            public StatusProto getStatus() {
                return ((FirestoreV1Action) this.instance).getStatus();
            }

            public Builder setStatus(StatusProto statusProto) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setStatus(statusProto);
                return this;
            }

            public Builder setStatus(StatusProto.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setStatus((StatusProto) builder.build());
                return this;
            }

            public Builder mergeStatus(StatusProto statusProto) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeStatus(statusProto);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearStatus();
                return this;
            }

            public boolean hasDatabaseContentsBeforeAction() {
                return ((FirestoreV1Action) this.instance).hasDatabaseContentsBeforeAction();
            }

            public RunQuery getDatabaseContentsBeforeAction() {
                return ((FirestoreV1Action) this.instance).getDatabaseContentsBeforeAction();
            }

            public Builder setDatabaseContentsBeforeAction(RunQuery runQuery) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDatabaseContentsBeforeAction(runQuery);
                return this;
            }

            public Builder setDatabaseContentsBeforeAction(RunQuery.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDatabaseContentsBeforeAction((RunQuery) builder.build());
                return this;
            }

            public Builder mergeDatabaseContentsBeforeAction(RunQuery runQuery) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeDatabaseContentsBeforeAction(runQuery);
                return this;
            }

            public Builder clearDatabaseContentsBeforeAction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearDatabaseContentsBeforeAction();
                return this;
            }

            public List<MatchingDocuments> getMatchingDocumentsList() {
                return Collections.unmodifiableList(((FirestoreV1Action) this.instance).getMatchingDocumentsList());
            }

            public int getMatchingDocumentsCount() {
                return ((FirestoreV1Action) this.instance).getMatchingDocumentsCount();
            }

            public MatchingDocuments getMatchingDocuments(int i) {
                return ((FirestoreV1Action) this.instance).getMatchingDocuments(i);
            }

            public Builder setMatchingDocuments(int i, MatchingDocuments matchingDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setMatchingDocuments(i, matchingDocuments);
                return this;
            }

            public Builder setMatchingDocuments(int i, MatchingDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setMatchingDocuments(i, (MatchingDocuments) builder.build());
                return this;
            }

            public Builder addMatchingDocuments(MatchingDocuments matchingDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(matchingDocuments);
                return this;
            }

            public Builder addMatchingDocuments(int i, MatchingDocuments matchingDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(i, matchingDocuments);
                return this;
            }

            public Builder addMatchingDocuments(MatchingDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments((MatchingDocuments) builder.build());
                return this;
            }

            public Builder addMatchingDocuments(int i, MatchingDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(i, (MatchingDocuments) builder.build());
                return this;
            }

            public Builder addAllMatchingDocuments(Iterable<? extends MatchingDocuments> iterable) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addAllMatchingDocuments(iterable);
                return this;
            }

            public Builder clearMatchingDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearMatchingDocuments();
                return this;
            }

            public Builder removeMatchingDocuments(int i) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).removeMatchingDocuments(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new FirestoreV1Action();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0010\u0001\u0001\u0001Ë\u0010\u0000\u0001\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000\b<\u0000\t<\u0000\n<\u0000\u000b<\u0000\f<\u0000\r<\u0000Éဉ\u0000Êဉ\u0001Ë\u001b", new Object[]{"action_", "actionCase_", "bitField0_", GetDocument.class, ListDocuments.class, CreateDocument.class, UpdateDocument.class, DeleteDocument.class, BeginTransaction.class, Commit.class, Rollback.class, ListCollectionIds.class, BatchGetDocuments.class, RunQuery.class, Listen.class, RemoveListen.class, "status_", "databaseContentsBeforeAction_", "matchingDocuments_", MatchingDocuments.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FirestoreV1Action> parser = PARSER;
                    if (parser == null) {
                        synchronized (FirestoreV1Action.class) {
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
            FirestoreV1Action firestoreV1Action = new FirestoreV1Action();
            DEFAULT_INSTANCE = firestoreV1Action;
            GeneratedMessageLite.registerDefaultInstance(FirestoreV1Action.class, firestoreV1Action);
        }

        public static FirestoreV1Action getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FirestoreV1Action> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DatastoreAction extends GeneratedMessageLite<DatastoreAction, Builder> implements DatastoreActionOrBuilder {
        public static final int ACTION_ID_FIELD_NUMBER = 200;
        /* access modifiers changed from: private */
        public static final DatastoreAction DEFAULT_INSTANCE;
        public static final int FIRESTORE_V1_ACTION_FIELD_NUMBER = 3;
        private static volatile Parser<DatastoreAction> PARSER = null;
        public static final int VALIDATION_RULE_FIELD_NUMBER = 201;
        private int actionCase_ = 0;
        private int actionId_;
        private Object action_;
        private int bitField0_;
        private ValidationRule validationRule_;

        private DatastoreAction() {
        }

        public enum ActionCase {
            FIRESTORE_V1_ACTION(3),
            ACTION_NOT_SET(0);
            
            private final int value;

            private ActionCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static ActionCase valueOf(int i) {
                return forNumber(i);
            }

            public static ActionCase forNumber(int i) {
                if (i == 0) {
                    return ACTION_NOT_SET;
                }
                if (i != 3) {
                    return null;
                }
                return FIRESTORE_V1_ACTION;
            }

            public int getNumber() {
                return this.value;
            }
        }

        public ActionCase getActionCase() {
            return ActionCase.forNumber(this.actionCase_);
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.actionCase_ = 0;
            this.action_ = null;
        }

        public boolean hasFirestoreV1Action() {
            return this.actionCase_ == 3;
        }

        public FirestoreV1Action getFirestoreV1Action() {
            if (this.actionCase_ == 3) {
                return (FirestoreV1Action) this.action_;
            }
            return FirestoreV1Action.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setFirestoreV1Action(FirestoreV1Action firestoreV1Action) {
            firestoreV1Action.getClass();
            this.action_ = firestoreV1Action;
            this.actionCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeFirestoreV1Action(FirestoreV1Action firestoreV1Action) {
            firestoreV1Action.getClass();
            if (this.actionCase_ != 3 || this.action_ == FirestoreV1Action.getDefaultInstance()) {
                this.action_ = firestoreV1Action;
            } else {
                this.action_ = ((FirestoreV1Action.Builder) FirestoreV1Action.newBuilder((FirestoreV1Action) this.action_).mergeFrom(firestoreV1Action)).buildPartial();
            }
            this.actionCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearFirestoreV1Action() {
            if (this.actionCase_ == 3) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public int getActionId() {
            return this.actionId_;
        }

        /* access modifiers changed from: private */
        public void setActionId(int i) {
            this.actionId_ = i;
        }

        /* access modifiers changed from: private */
        public void clearActionId() {
            this.actionId_ = 0;
        }

        public boolean hasValidationRule() {
            return (this.bitField0_ & 1) != 0;
        }

        public ValidationRule getValidationRule() {
            ValidationRule validationRule = this.validationRule_;
            return validationRule == null ? ValidationRule.getDefaultInstance() : validationRule;
        }

        /* access modifiers changed from: private */
        public void setValidationRule(ValidationRule validationRule) {
            validationRule.getClass();
            this.validationRule_ = validationRule;
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void mergeValidationRule(ValidationRule validationRule) {
            validationRule.getClass();
            ValidationRule validationRule2 = this.validationRule_;
            if (validationRule2 == null || validationRule2 == ValidationRule.getDefaultInstance()) {
                this.validationRule_ = validationRule;
            } else {
                this.validationRule_ = (ValidationRule) ((ValidationRule.Builder) ValidationRule.newBuilder(this.validationRule_).mergeFrom(validationRule)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void clearValidationRule() {
            this.validationRule_ = null;
            this.bitField0_ &= -2;
        }

        public static DatastoreAction parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static DatastoreAction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DatastoreAction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DatastoreAction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(InputStream inputStream) throws IOException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DatastoreAction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DatastoreAction parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DatastoreAction) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DatastoreAction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DatastoreAction) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DatastoreAction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(DatastoreAction datastoreAction) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(datastoreAction);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DatastoreAction, Builder> implements DatastoreActionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(DatastoreAction.DEFAULT_INSTANCE);
            }

            public ActionCase getActionCase() {
                return ((DatastoreAction) this.instance).getActionCase();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearAction();
                return this;
            }

            public boolean hasFirestoreV1Action() {
                return ((DatastoreAction) this.instance).hasFirestoreV1Action();
            }

            public FirestoreV1Action getFirestoreV1Action() {
                return ((DatastoreAction) this.instance).getFirestoreV1Action();
            }

            public Builder setFirestoreV1Action(FirestoreV1Action firestoreV1Action) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setFirestoreV1Action(firestoreV1Action);
                return this;
            }

            public Builder setFirestoreV1Action(FirestoreV1Action.Builder builder) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setFirestoreV1Action((FirestoreV1Action) builder.build());
                return this;
            }

            public Builder mergeFirestoreV1Action(FirestoreV1Action firestoreV1Action) {
                copyOnWrite();
                ((DatastoreAction) this.instance).mergeFirestoreV1Action(firestoreV1Action);
                return this;
            }

            public Builder clearFirestoreV1Action() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearFirestoreV1Action();
                return this;
            }

            public int getActionId() {
                return ((DatastoreAction) this.instance).getActionId();
            }

            public Builder setActionId(int i) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setActionId(i);
                return this;
            }

            public Builder clearActionId() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearActionId();
                return this;
            }

            public boolean hasValidationRule() {
                return ((DatastoreAction) this.instance).hasValidationRule();
            }

            public ValidationRule getValidationRule() {
                return ((DatastoreAction) this.instance).getValidationRule();
            }

            public Builder setValidationRule(ValidationRule validationRule) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setValidationRule(validationRule);
                return this;
            }

            public Builder setValidationRule(ValidationRule.Builder builder) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setValidationRule((ValidationRule) builder.build());
                return this;
            }

            public Builder mergeValidationRule(ValidationRule validationRule) {
                copyOnWrite();
                ((DatastoreAction) this.instance).mergeValidationRule(validationRule);
                return this;
            }

            public Builder clearValidationRule() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearValidationRule();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new DatastoreAction();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0001\u0003É\u0003\u0000\u0000\u0000\u0003<\u0000È\u0004Éဉ\u0000", new Object[]{"action_", "actionCase_", "bitField0_", FirestoreV1Action.class, "actionId_", "validationRule_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<DatastoreAction> parser = PARSER;
                    if (parser == null) {
                        synchronized (DatastoreAction.class) {
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
            DatastoreAction datastoreAction = new DatastoreAction();
            DEFAULT_INSTANCE = datastoreAction;
            GeneratedMessageLite.registerDefaultInstance(DatastoreAction.class, datastoreAction);
        }

        public static DatastoreAction getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DatastoreAction> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class TestTrace extends GeneratedMessageLite<TestTrace, Builder> implements TestTraceOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final TestTrace DEFAULT_INSTANCE;
        private static volatile Parser<TestTrace> PARSER = null;
        public static final int TRACE_DESCRIPTION_FIELD_NUMBER = 3;
        public static final int TRACE_ID_FIELD_NUMBER = 1;
        private Internal.ProtobufList<DatastoreAction> action_ = emptyProtobufList();
        private String traceDescription_ = "";
        private String traceId_ = "";

        private TestTrace() {
        }

        public String getTraceId() {
            return this.traceId_;
        }

        public ByteString getTraceIdBytes() {
            return ByteString.copyFromUtf8(this.traceId_);
        }

        /* access modifiers changed from: private */
        public void setTraceId(String str) {
            str.getClass();
            this.traceId_ = str;
        }

        /* access modifiers changed from: private */
        public void clearTraceId() {
            this.traceId_ = getDefaultInstance().getTraceId();
        }

        /* access modifiers changed from: private */
        public void setTraceIdBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.traceId_ = byteString.toStringUtf8();
        }

        public List<DatastoreAction> getActionList() {
            return this.action_;
        }

        public List<? extends DatastoreActionOrBuilder> getActionOrBuilderList() {
            return this.action_;
        }

        public int getActionCount() {
            return this.action_.size();
        }

        public DatastoreAction getAction(int i) {
            return (DatastoreAction) this.action_.get(i);
        }

        public DatastoreActionOrBuilder getActionOrBuilder(int i) {
            return (DatastoreActionOrBuilder) this.action_.get(i);
        }

        private void ensureActionIsMutable() {
            Internal.ProtobufList<DatastoreAction> protobufList = this.action_;
            if (!protobufList.isModifiable()) {
                this.action_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
        }

        /* access modifiers changed from: private */
        public void setAction(int i, DatastoreAction datastoreAction) {
            datastoreAction.getClass();
            ensureActionIsMutable();
            this.action_.set(i, datastoreAction);
        }

        /* access modifiers changed from: private */
        public void addAction(DatastoreAction datastoreAction) {
            datastoreAction.getClass();
            ensureActionIsMutable();
            this.action_.add(datastoreAction);
        }

        /* access modifiers changed from: private */
        public void addAction(int i, DatastoreAction datastoreAction) {
            datastoreAction.getClass();
            ensureActionIsMutable();
            this.action_.add(i, datastoreAction);
        }

        /* access modifiers changed from: private */
        public void addAllAction(Iterable<? extends DatastoreAction> iterable) {
            ensureActionIsMutable();
            AbstractMessageLite.addAll(iterable, this.action_);
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.action_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeAction(int i) {
            ensureActionIsMutable();
            this.action_.remove(i);
        }

        public String getTraceDescription() {
            return this.traceDescription_;
        }

        public ByteString getTraceDescriptionBytes() {
            return ByteString.copyFromUtf8(this.traceDescription_);
        }

        /* access modifiers changed from: private */
        public void setTraceDescription(String str) {
            str.getClass();
            this.traceDescription_ = str;
        }

        /* access modifiers changed from: private */
        public void clearTraceDescription() {
            this.traceDescription_ = getDefaultInstance().getTraceDescription();
        }

        /* access modifiers changed from: private */
        public void setTraceDescriptionBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.traceDescription_ = byteString.toStringUtf8();
        }

        public static TestTrace parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static TestTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static TestTrace parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static TestTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static TestTrace parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static TestTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static TestTrace parseFrom(InputStream inputStream) throws IOException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TestTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TestTrace parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TestTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TestTrace parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static TestTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(TestTrace testTrace) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(testTrace);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TestTrace, Builder> implements TestTraceOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(TestTrace.DEFAULT_INSTANCE);
            }

            public String getTraceId() {
                return ((TestTrace) this.instance).getTraceId();
            }

            public ByteString getTraceIdBytes() {
                return ((TestTrace) this.instance).getTraceIdBytes();
            }

            public Builder setTraceId(String str) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceId(str);
                return this;
            }

            public Builder clearTraceId() {
                copyOnWrite();
                ((TestTrace) this.instance).clearTraceId();
                return this;
            }

            public Builder setTraceIdBytes(ByteString byteString) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceIdBytes(byteString);
                return this;
            }

            public List<DatastoreAction> getActionList() {
                return Collections.unmodifiableList(((TestTrace) this.instance).getActionList());
            }

            public int getActionCount() {
                return ((TestTrace) this.instance).getActionCount();
            }

            public DatastoreAction getAction(int i) {
                return ((TestTrace) this.instance).getAction(i);
            }

            public Builder setAction(int i, DatastoreAction datastoreAction) {
                copyOnWrite();
                ((TestTrace) this.instance).setAction(i, datastoreAction);
                return this;
            }

            public Builder setAction(int i, DatastoreAction.Builder builder) {
                copyOnWrite();
                ((TestTrace) this.instance).setAction(i, (DatastoreAction) builder.build());
                return this;
            }

            public Builder addAction(DatastoreAction datastoreAction) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(datastoreAction);
                return this;
            }

            public Builder addAction(int i, DatastoreAction datastoreAction) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(i, datastoreAction);
                return this;
            }

            public Builder addAction(DatastoreAction.Builder builder) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction((DatastoreAction) builder.build());
                return this;
            }

            public Builder addAction(int i, DatastoreAction.Builder builder) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(i, (DatastoreAction) builder.build());
                return this;
            }

            public Builder addAllAction(Iterable<? extends DatastoreAction> iterable) {
                copyOnWrite();
                ((TestTrace) this.instance).addAllAction(iterable);
                return this;
            }

            public Builder clearAction() {
                copyOnWrite();
                ((TestTrace) this.instance).clearAction();
                return this;
            }

            public Builder removeAction(int i) {
                copyOnWrite();
                ((TestTrace) this.instance).removeAction(i);
                return this;
            }

            public String getTraceDescription() {
                return ((TestTrace) this.instance).getTraceDescription();
            }

            public ByteString getTraceDescriptionBytes() {
                return ((TestTrace) this.instance).getTraceDescriptionBytes();
            }

            public Builder setTraceDescription(String str) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceDescription(str);
                return this;
            }

            public Builder clearTraceDescription() {
                copyOnWrite();
                ((TestTrace) this.instance).clearTraceDescription();
                return this;
            }

            public Builder setTraceDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceDescriptionBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new TestTrace();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003Ȉ", new Object[]{"traceId_", "action_", DatastoreAction.class, "traceDescription_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<TestTrace> parser = PARSER;
                    if (parser == null) {
                        synchronized (TestTrace.class) {
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
            TestTrace testTrace = new TestTrace();
            DEFAULT_INSTANCE = testTrace;
            GeneratedMessageLite.registerDefaultInstance(TestTrace.class, testTrace);
        }

        public static TestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ParallelTestTrace extends GeneratedMessageLite<ParallelTestTrace, Builder> implements ParallelTestTraceOrBuilder {
        /* access modifiers changed from: private */
        public static final ParallelTestTrace DEFAULT_INSTANCE;
        private static volatile Parser<ParallelTestTrace> PARSER = null;
        public static final int TEST_TRACE_FIELD_NUMBER = 1;
        private int bitField0_;
        private TestTrace testTrace_;

        private ParallelTestTrace() {
        }

        public boolean hasTestTrace() {
            return (this.bitField0_ & 1) != 0;
        }

        public TestTrace getTestTrace() {
            TestTrace testTrace = this.testTrace_;
            return testTrace == null ? TestTrace.getDefaultInstance() : testTrace;
        }

        /* access modifiers changed from: private */
        public void setTestTrace(TestTrace testTrace) {
            testTrace.getClass();
            this.testTrace_ = testTrace;
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void mergeTestTrace(TestTrace testTrace) {
            testTrace.getClass();
            TestTrace testTrace2 = this.testTrace_;
            if (testTrace2 == null || testTrace2 == TestTrace.getDefaultInstance()) {
                this.testTrace_ = testTrace;
            } else {
                this.testTrace_ = (TestTrace) ((TestTrace.Builder) TestTrace.newBuilder(this.testTrace_).mergeFrom(testTrace)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void clearTestTrace() {
            this.testTrace_ = null;
            this.bitField0_ &= -2;
        }

        public static ParallelTestTrace parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ParallelTestTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ParallelTestTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ParallelTestTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(InputStream inputStream) throws IOException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ParallelTestTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ParallelTestTrace parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ParallelTestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ParallelTestTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ParallelTestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ParallelTestTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ParallelTestTrace parallelTestTrace) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(parallelTestTrace);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ParallelTestTrace, Builder> implements ParallelTestTraceOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ParallelTestTrace.DEFAULT_INSTANCE);
            }

            public boolean hasTestTrace() {
                return ((ParallelTestTrace) this.instance).hasTestTrace();
            }

            public TestTrace getTestTrace() {
                return ((ParallelTestTrace) this.instance).getTestTrace();
            }

            public Builder setTestTrace(TestTrace testTrace) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).setTestTrace(testTrace);
                return this;
            }

            public Builder setTestTrace(TestTrace.Builder builder) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).setTestTrace((TestTrace) builder.build());
                return this;
            }

            public Builder mergeTestTrace(TestTrace testTrace) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).mergeTestTrace(testTrace);
                return this;
            }

            public Builder clearTestTrace() {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).clearTestTrace();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ParallelTestTrace();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "testTrace_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ParallelTestTrace> parser = PARSER;
                    if (parser == null) {
                        synchronized (ParallelTestTrace.class) {
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
            ParallelTestTrace parallelTestTrace = new ParallelTestTrace();
            DEFAULT_INSTANCE = parallelTestTrace;
            GeneratedMessageLite.registerDefaultInstance(ParallelTestTrace.class, parallelTestTrace);
        }

        public static ParallelTestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ParallelTestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class TimelineTestTrace extends GeneratedMessageLite<TimelineTestTrace, Builder> implements TimelineTestTraceOrBuilder {
        /* access modifiers changed from: private */
        public static final TimelineTestTrace DEFAULT_INSTANCE;
        private static volatile Parser<TimelineTestTrace> PARSER = null;
        public static final int TEST_TRACE_FIELD_NUMBER = 1;
        private int bitField0_;
        private TestTrace testTrace_;

        private TimelineTestTrace() {
        }

        public boolean hasTestTrace() {
            return (this.bitField0_ & 1) != 0;
        }

        public TestTrace getTestTrace() {
            TestTrace testTrace = this.testTrace_;
            return testTrace == null ? TestTrace.getDefaultInstance() : testTrace;
        }

        /* access modifiers changed from: private */
        public void setTestTrace(TestTrace testTrace) {
            testTrace.getClass();
            this.testTrace_ = testTrace;
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void mergeTestTrace(TestTrace testTrace) {
            testTrace.getClass();
            TestTrace testTrace2 = this.testTrace_;
            if (testTrace2 == null || testTrace2 == TestTrace.getDefaultInstance()) {
                this.testTrace_ = testTrace;
            } else {
                this.testTrace_ = (TestTrace) ((TestTrace.Builder) TestTrace.newBuilder(this.testTrace_).mergeFrom(testTrace)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void clearTestTrace() {
            this.testTrace_ = null;
            this.bitField0_ &= -2;
        }

        public static TimelineTestTrace parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static TimelineTestTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static TimelineTestTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static TimelineTestTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(InputStream inputStream) throws IOException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TimelineTestTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TimelineTestTrace parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TimelineTestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TimelineTestTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TimelineTestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static TimelineTestTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(TimelineTestTrace timelineTestTrace) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(timelineTestTrace);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TimelineTestTrace, Builder> implements TimelineTestTraceOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(TimelineTestTrace.DEFAULT_INSTANCE);
            }

            public boolean hasTestTrace() {
                return ((TimelineTestTrace) this.instance).hasTestTrace();
            }

            public TestTrace getTestTrace() {
                return ((TimelineTestTrace) this.instance).getTestTrace();
            }

            public Builder setTestTrace(TestTrace testTrace) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).setTestTrace(testTrace);
                return this;
            }

            public Builder setTestTrace(TestTrace.Builder builder) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).setTestTrace((TestTrace) builder.build());
                return this;
            }

            public Builder mergeTestTrace(TestTrace testTrace) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).mergeTestTrace(testTrace);
                return this;
            }

            public Builder clearTestTrace() {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).clearTestTrace();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new TimelineTestTrace();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "testTrace_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<TimelineTestTrace> parser = PARSER;
                    if (parser == null) {
                        synchronized (TimelineTestTrace.class) {
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
            TimelineTestTrace timelineTestTrace = new TimelineTestTrace();
            DEFAULT_INSTANCE = timelineTestTrace;
            GeneratedMessageLite.registerDefaultInstance(TimelineTestTrace.class, timelineTestTrace);
        }

        public static TimelineTestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TimelineTestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
