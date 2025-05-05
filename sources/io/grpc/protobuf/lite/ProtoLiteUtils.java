package io.grpc.protobuf.lite;

import com.google.common.base.Preconditions;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;

public final class ProtoLiteUtils {
    private static final int BUF_SIZE = 8192;
    static final int DEFAULT_MAX_MESSAGE_SIZE = 4194304;
    static volatile ExtensionRegistryLite globalRegistry = ExtensionRegistryLite.getEmptyRegistry();

    public static void setExtensionRegistry(ExtensionRegistryLite extensionRegistryLite) {
        globalRegistry = (ExtensionRegistryLite) Preconditions.checkNotNull(extensionRegistryLite, "newRegistry");
    }

    public static <T extends MessageLite> MethodDescriptor.Marshaller<T> marshaller(T t) {
        return new MessageMarshaller(t, -1);
    }

    public static <T extends MessageLite> MethodDescriptor.Marshaller<T> marshallerWithRecursionLimit(T t, int i) {
        return new MessageMarshaller(t, i);
    }

    public static <T extends MessageLite> Metadata.BinaryMarshaller<T> metadataMarshaller(T t) {
        return new MetadataMarshaller(t);
    }

    static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream, "inputStream cannot be null!");
        Preconditions.checkNotNull(outputStream, "outputStream cannot be null!");
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private ProtoLiteUtils() {
    }

    private static final class MessageMarshaller<T extends MessageLite> implements MethodDescriptor.PrototypeMarshaller<T> {
        private static final ThreadLocal<Reference<byte[]>> bufs = new ThreadLocal<>();
        private final T defaultInstance;
        private final Parser<T> parser;
        private final int recursionLimit;

        MessageMarshaller(T t, int i) {
            this.defaultInstance = (MessageLite) Preconditions.checkNotNull(t, "defaultInstance cannot be null");
            this.parser = t.getParserForType();
            this.recursionLimit = i;
        }

        public Class<T> getMessageClass() {
            return this.defaultInstance.getClass();
        }

        public T getMessagePrototype() {
            return this.defaultInstance;
        }

        public InputStream stream(T t) {
            return new ProtoInputStream(t, this.parser);
        }

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0017 */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x001b A[Catch:{ IOException -> 0x00b0 }] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0088  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x0096  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T parse(java.io.InputStream r6) {
            /*
                r5 = this;
                boolean r0 = r6 instanceof io.grpc.protobuf.lite.ProtoInputStream
                if (r0 == 0) goto L_0x0017
                r0 = r6
                io.grpc.protobuf.lite.ProtoInputStream r0 = (io.grpc.protobuf.lite.ProtoInputStream) r0
                com.google.protobuf.Parser r0 = r0.parser()
                com.google.protobuf.Parser<T> r1 = r5.parser
                if (r0 != r1) goto L_0x0017
                r0 = r6
                io.grpc.protobuf.lite.ProtoInputStream r0 = (io.grpc.protobuf.lite.ProtoInputStream) r0     // Catch:{ IllegalStateException -> 0x0017 }
                com.google.protobuf.MessageLite r6 = r0.message()     // Catch:{ IllegalStateException -> 0x0017 }
                return r6
            L_0x0017:
                boolean r0 = r6 instanceof io.grpc.KnownLength     // Catch:{ IOException -> 0x00b0 }
                if (r0 == 0) goto L_0x0085
                int r0 = r6.available()     // Catch:{ IOException -> 0x00b0 }
                if (r0 <= 0) goto L_0x0080
                r1 = 4194304(0x400000, float:5.877472E-39)
                if (r0 > r1) goto L_0x0080
                java.lang.ThreadLocal<java.lang.ref.Reference<byte[]>> r1 = bufs     // Catch:{ IOException -> 0x00b0 }
                java.lang.Object r2 = r1.get()     // Catch:{ IOException -> 0x00b0 }
                java.lang.ref.Reference r2 = (java.lang.ref.Reference) r2     // Catch:{ IOException -> 0x00b0 }
                if (r2 == 0) goto L_0x003a
                java.lang.Object r2 = r2.get()     // Catch:{ IOException -> 0x00b0 }
                byte[] r2 = (byte[]) r2     // Catch:{ IOException -> 0x00b0 }
                if (r2 == 0) goto L_0x003a
                int r3 = r2.length     // Catch:{ IOException -> 0x00b0 }
                if (r3 >= r0) goto L_0x0044
            L_0x003a:
                byte[] r2 = new byte[r0]     // Catch:{ IOException -> 0x00b0 }
                java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference     // Catch:{ IOException -> 0x00b0 }
                r3.<init>(r2)     // Catch:{ IOException -> 0x00b0 }
                r1.set(r3)     // Catch:{ IOException -> 0x00b0 }
            L_0x0044:
                r1 = r0
            L_0x0045:
                if (r1 <= 0) goto L_0x0053
                int r3 = r0 - r1
                int r3 = r6.read(r2, r3, r1)     // Catch:{ IOException -> 0x00b0 }
                r4 = -1
                if (r3 != r4) goto L_0x0051
                goto L_0x0053
            L_0x0051:
                int r1 = r1 - r3
                goto L_0x0045
            L_0x0053:
                if (r1 != 0) goto L_0x005b
                r1 = 0
                com.google.protobuf.CodedInputStream r0 = com.google.protobuf.CodedInputStream.newInstance(r2, r1, r0)     // Catch:{ IOException -> 0x00b0 }
                goto L_0x0086
            L_0x005b:
                int r6 = r0 - r1
                java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ IOException -> 0x00b0 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00b0 }
                r2.<init>()     // Catch:{ IOException -> 0x00b0 }
                java.lang.String r3 = "size inaccurate: "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x00b0 }
                java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ IOException -> 0x00b0 }
                java.lang.String r2 = " != "
                java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ IOException -> 0x00b0 }
                java.lang.StringBuilder r6 = r0.append(r6)     // Catch:{ IOException -> 0x00b0 }
                java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x00b0 }
                r1.<init>(r6)     // Catch:{ IOException -> 0x00b0 }
                throw r1     // Catch:{ IOException -> 0x00b0 }
            L_0x0080:
                if (r0 != 0) goto L_0x0085
                T r6 = r5.defaultInstance     // Catch:{ IOException -> 0x00b0 }
                return r6
            L_0x0085:
                r0 = 0
            L_0x0086:
                if (r0 != 0) goto L_0x008c
                com.google.protobuf.CodedInputStream r0 = com.google.protobuf.CodedInputStream.newInstance((java.io.InputStream) r6)
            L_0x008c:
                r6 = 2147483647(0x7fffffff, float:NaN)
                r0.setSizeLimit(r6)
                int r6 = r5.recursionLimit
                if (r6 < 0) goto L_0x0099
                r0.setRecursionLimit(r6)
            L_0x0099:
                com.google.protobuf.MessageLite r6 = r5.parseFrom(r0)     // Catch:{ InvalidProtocolBufferException -> 0x009e }
                return r6
            L_0x009e:
                r6 = move-exception
                io.grpc.Status r0 = io.grpc.Status.INTERNAL
                java.lang.String r1 = "Invalid protobuf byte sequence"
                io.grpc.Status r0 = r0.withDescription(r1)
                io.grpc.Status r6 = r0.withCause(r6)
                io.grpc.StatusRuntimeException r6 = r6.asRuntimeException()
                throw r6
            L_0x00b0:
                r6 = move-exception
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                r0.<init>(r6)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.protobuf.lite.ProtoLiteUtils.MessageMarshaller.parse(java.io.InputStream):com.google.protobuf.MessageLite");
        }

        private T parseFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
            T t = (MessageLite) this.parser.parseFrom(codedInputStream, ProtoLiteUtils.globalRegistry);
            try {
                codedInputStream.checkLastTagWas(0);
                return t;
            } catch (InvalidProtocolBufferException e) {
                e.setUnfinishedMessage(t);
                throw e;
            }
        }
    }

    private static final class MetadataMarshaller<T extends MessageLite> implements Metadata.BinaryMarshaller<T> {
        private final T defaultInstance;

        MetadataMarshaller(T t) {
            this.defaultInstance = t;
        }

        public byte[] toBytes(T t) {
            return t.toByteArray();
        }

        public T parseBytes(byte[] bArr) {
            try {
                return (MessageLite) this.defaultInstance.getParserForType().parseFrom(bArr, ProtoLiteUtils.globalRegistry);
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
