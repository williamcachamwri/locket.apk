package com.google.firestore.v1;

import com.google.firestore.v1.BitSequence;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BloomFilter extends GeneratedMessageLite<BloomFilter, Builder> implements BloomFilterOrBuilder {
    public static final int BITS_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final BloomFilter DEFAULT_INSTANCE;
    public static final int HASH_COUNT_FIELD_NUMBER = 2;
    private static volatile Parser<BloomFilter> PARSER;
    private int bitField0_;
    private BitSequence bits_;
    private int hashCount_;

    private BloomFilter() {
    }

    public boolean hasBits() {
        return (this.bitField0_ & 1) != 0;
    }

    public BitSequence getBits() {
        BitSequence bitSequence = this.bits_;
        return bitSequence == null ? BitSequence.getDefaultInstance() : bitSequence;
    }

    /* access modifiers changed from: private */
    public void setBits(BitSequence bitSequence) {
        bitSequence.getClass();
        this.bits_ = bitSequence;
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    public void mergeBits(BitSequence bitSequence) {
        bitSequence.getClass();
        BitSequence bitSequence2 = this.bits_;
        if (bitSequence2 == null || bitSequence2 == BitSequence.getDefaultInstance()) {
            this.bits_ = bitSequence;
        } else {
            this.bits_ = (BitSequence) ((BitSequence.Builder) BitSequence.newBuilder(this.bits_).mergeFrom(bitSequence)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    public void clearBits() {
        this.bits_ = null;
        this.bitField0_ &= -2;
    }

    public int getHashCount() {
        return this.hashCount_;
    }

    /* access modifiers changed from: private */
    public void setHashCount(int i) {
        this.hashCount_ = i;
    }

    /* access modifiers changed from: private */
    public void clearHashCount() {
        this.hashCount_ = 0;
    }

    public static BloomFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BloomFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BloomFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BloomFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BloomFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BloomFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BloomFilter parseFrom(InputStream inputStream) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BloomFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BloomFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BloomFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BloomFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BloomFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BloomFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BloomFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BloomFilter bloomFilter) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(bloomFilter);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BloomFilter, Builder> implements BloomFilterOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(BloomFilter.DEFAULT_INSTANCE);
        }

        public boolean hasBits() {
            return ((BloomFilter) this.instance).hasBits();
        }

        public BitSequence getBits() {
            return ((BloomFilter) this.instance).getBits();
        }

        public Builder setBits(BitSequence bitSequence) {
            copyOnWrite();
            ((BloomFilter) this.instance).setBits(bitSequence);
            return this;
        }

        public Builder setBits(BitSequence.Builder builder) {
            copyOnWrite();
            ((BloomFilter) this.instance).setBits((BitSequence) builder.build());
            return this;
        }

        public Builder mergeBits(BitSequence bitSequence) {
            copyOnWrite();
            ((BloomFilter) this.instance).mergeBits(bitSequence);
            return this;
        }

        public Builder clearBits() {
            copyOnWrite();
            ((BloomFilter) this.instance).clearBits();
            return this;
        }

        public int getHashCount() {
            return ((BloomFilter) this.instance).getHashCount();
        }

        public Builder setHashCount(int i) {
            copyOnWrite();
            ((BloomFilter) this.instance).setHashCount(i);
            return this;
        }

        public Builder clearHashCount() {
            copyOnWrite();
            ((BloomFilter) this.instance).clearHashCount();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.BloomFilter$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.v1.BloomFilter.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BloomFilter();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u0004", new Object[]{"bitField0_", "bits_", "hashCount_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BloomFilter> parser = PARSER;
                if (parser == null) {
                    synchronized (BloomFilter.class) {
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
        BloomFilter bloomFilter = new BloomFilter();
        DEFAULT_INSTANCE = bloomFilter;
        GeneratedMessageLite.registerDefaultInstance(BloomFilter.class, bloomFilter);
    }

    public static BloomFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BloomFilter> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
