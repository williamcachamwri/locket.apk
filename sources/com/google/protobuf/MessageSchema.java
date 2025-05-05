package com.google.protobuf;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import com.facebook.soloader.Elf64;
import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

@CheckReturnValue
final class MessageSchema<T> implements Schema<T> {
    private static final int CHECK_INITIALIZED_BIT = 1024;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int HAS_HAS_BIT = 4096;
    private static final int INTS_PER_FIELD = 3;
    private static final int LEGACY_ENUM_IS_CLOSED_BIT = 2048;
    private static final int LEGACY_ENUM_IS_CLOSED_MASK = Integer.MIN_VALUE;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_BIT = 256;
    private static final int REQUIRED_MASK = 268435456;
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
    private static final int UTF8_CHECK_BIT = 512;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final int repeatedFieldOffsetStart;
    private final ProtoSyntax syntax;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;

    private static boolean isEnforceUtf8(int i) {
        return (i & 536870912) != 0;
    }

    private static boolean isLegacyEnumIsClosed(int i) {
        return (i & Integer.MIN_VALUE) != 0;
    }

    private static boolean isRequired(int i) {
        return (i & 268435456) != 0;
    }

    private static long offset(int i) {
        return (long) (i & 1048575);
    }

    private static int type(int i) {
        return (i & FIELD_TYPE_MASK) >>> 20;
    }

    private MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, ProtoSyntax protoSyntax, boolean z, int[] iArr2, int i3, int i4, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.syntax = protoSyntax;
        this.hasExtensions = extensionSchema2 != null && extensionSchema2.hasExtensions(messageLite);
        this.useCachedSizeField = z;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema = newInstanceSchema2;
        this.listFieldSchema = listFieldSchema2;
        this.unknownFieldSchema = unknownFieldSchema2;
        this.extensionSchema = extensionSchema2;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema2;
    }

    static <T> MessageSchema<T> newSchema(Class<T> cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:118:0x0248  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0265  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.protobuf.MessageSchema<T> newSchemaForRawMessageInfo(com.google.protobuf.RawMessageInfo r32, com.google.protobuf.NewInstanceSchema r33, com.google.protobuf.ListFieldSchema r34, com.google.protobuf.UnknownFieldSchema<?, ?> r35, com.google.protobuf.ExtensionSchema<?> r36, com.google.protobuf.MapFieldSchema r37) {
        /*
            java.lang.String r0 = r32.getStringInfo()
            int r1 = r0.length()
            r2 = 0
            char r3 = r0.charAt(r2)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r5) goto L_0x001d
            r3 = 1
        L_0x0013:
            int r6 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x001e
            r3 = r6
            goto L_0x0013
        L_0x001d:
            r6 = 1
        L_0x001e:
            int r3 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x003d
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x002a:
            int r9 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x003a
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r8
            r6 = r6 | r3
            int r8 = r8 + 13
            r3 = r9
            goto L_0x002a
        L_0x003a:
            int r3 = r3 << r8
            r6 = r6 | r3
            r3 = r9
        L_0x003d:
            if (r6 != 0) goto L_0x004e
            int[] r6 = EMPTY_INT_ARRAY
            r10 = r2
            r11 = r10
            r12 = r11
            r13 = r12
            r15 = r13
            r17 = r15
            r16 = r6
            r6 = r17
            goto L_0x0158
        L_0x004e:
            int r6 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x006d
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x005a:
            int r9 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x006a
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r8
            r3 = r3 | r6
            int r8 = r8 + 13
            r6 = r9
            goto L_0x005a
        L_0x006a:
            int r6 = r6 << r8
            r3 = r3 | r6
            r6 = r9
        L_0x006d:
            int r8 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x008c
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0079:
            int r10 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x0089
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r6 = r6 | r8
            int r9 = r9 + 13
            r8 = r10
            goto L_0x0079
        L_0x0089:
            int r8 = r8 << r9
            r6 = r6 | r8
            r8 = r10
        L_0x008c:
            int r9 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x00ab
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0098:
            int r11 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00a8
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r8 = r8 | r9
            int r10 = r10 + 13
            r9 = r11
            goto L_0x0098
        L_0x00a8:
            int r9 = r9 << r10
            r8 = r8 | r9
            r9 = r11
        L_0x00ab:
            int r10 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00ca
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00b7:
            int r12 = r10 + 1
            char r10 = r0.charAt(r10)
            if (r10 < r5) goto L_0x00c7
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x00b7
        L_0x00c7:
            int r10 = r10 << r11
            r9 = r9 | r10
            r10 = r12
        L_0x00ca:
            int r11 = r10 + 1
            char r10 = r0.charAt(r10)
            if (r10 < r5) goto L_0x00e9
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00d6:
            int r13 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00e6
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00d6
        L_0x00e6:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00e9:
            int r12 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x0108
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00f5:
            int r14 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x0105
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00f5
        L_0x0105:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x0108:
            int r13 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x0127
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x0114:
            int r15 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x0124
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x0114
        L_0x0124:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x0127:
            int r14 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x0148
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x0133:
            int r16 = r14 + 1
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x0144
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x0133
        L_0x0144:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0148:
            int r15 = r13 + r11
            int r15 = r15 + r12
            int[] r12 = new int[r15]
            int r15 = r3 * 2
            int r15 = r15 + r6
            r6 = r3
            r16 = r12
            r17 = r13
            r3 = r14
            r12 = r8
            r13 = r9
        L_0x0158:
            sun.misc.Unsafe r8 = UNSAFE
            java.lang.Object[] r9 = r32.getObjects()
            com.google.protobuf.MessageLite r14 = r32.getDefaultInstance()
            java.lang.Class r14 = r14.getClass()
            int r2 = r10 * 3
            int[] r2 = new int[r2]
            int r10 = r10 * 2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            int r19 = r17 + r11
            r21 = r17
            r22 = r19
            r11 = 0
            r20 = 0
        L_0x0177:
            if (r3 >= r1) goto L_0x03ba
            int r23 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x019f
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r7 = r23
            r23 = 13
        L_0x0187:
            int r24 = r7 + 1
            char r7 = r0.charAt(r7)
            if (r7 < r5) goto L_0x0199
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r23
            r3 = r3 | r7
            int r23 = r23 + 13
            r7 = r24
            goto L_0x0187
        L_0x0199:
            int r7 = r7 << r23
            r3 = r3 | r7
            r7 = r24
            goto L_0x01a1
        L_0x019f:
            r7 = r23
        L_0x01a1:
            int r23 = r7 + 1
            char r7 = r0.charAt(r7)
            if (r7 < r5) goto L_0x01c7
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r4 = r23
            r23 = 13
        L_0x01af:
            int r25 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x01c1
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r23
            r7 = r7 | r4
            int r23 = r23 + 13
            r4 = r25
            goto L_0x01af
        L_0x01c1:
            int r4 = r4 << r23
            r7 = r7 | r4
            r4 = r25
            goto L_0x01c9
        L_0x01c7:
            r4 = r23
        L_0x01c9:
            r5 = r7 & 255(0xff, float:3.57E-43)
            r25 = r1
            r1 = r7 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L_0x01d6
            int r1 = r11 + 1
            r16[r11] = r20
            r11 = r1
        L_0x01d6:
            r1 = 51
            r28 = r11
            if (r5 < r1) goto L_0x027a
            int r1 = r4 + 1
            char r4 = r0.charAt(r4)
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r11) goto L_0x0205
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r30 = 13
        L_0x01eb:
            int r31 = r1 + 1
            char r1 = r0.charAt(r1)
            if (r1 < r11) goto L_0x0200
            r1 = r1 & 8191(0x1fff, float:1.1478E-41)
            int r1 = r1 << r30
            r4 = r4 | r1
            int r30 = r30 + 13
            r1 = r31
            r11 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01eb
        L_0x0200:
            int r1 = r1 << r30
            r4 = r4 | r1
            r1 = r31
        L_0x0205:
            int r11 = r5 + -51
            r30 = r1
            r1 = 9
            if (r11 == r1) goto L_0x0233
            r1 = 17
            if (r11 != r1) goto L_0x0212
            goto L_0x0233
        L_0x0212:
            r1 = 12
            if (r11 != r1) goto L_0x0240
            com.google.protobuf.ProtoSyntax r1 = r32.getSyntax()
            com.google.protobuf.ProtoSyntax r11 = com.google.protobuf.ProtoSyntax.PROTO2
            boolean r1 = r1.equals(r11)
            if (r1 != 0) goto L_0x0226
            r1 = r7 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0240
        L_0x0226:
            int r1 = r20 / 3
            int r1 = r1 * 2
            r11 = 1
            int r1 = r1 + r11
            int r11 = r15 + 1
            r15 = r9[r15]
            r10[r1] = r15
            goto L_0x023f
        L_0x0233:
            int r1 = r20 / 3
            int r1 = r1 * 2
            r11 = 1
            int r1 = r1 + r11
            int r11 = r15 + 1
            r15 = r9[r15]
            r10[r1] = r15
        L_0x023f:
            r15 = r11
        L_0x0240:
            int r4 = r4 * 2
            r1 = r9[r4]
            boolean r11 = r1 instanceof java.lang.reflect.Field
            if (r11 == 0) goto L_0x024b
            java.lang.reflect.Field r1 = (java.lang.reflect.Field) r1
            goto L_0x0253
        L_0x024b:
            java.lang.String r1 = (java.lang.String) r1
            java.lang.reflect.Field r1 = reflectField(r14, r1)
            r9[r4] = r1
        L_0x0253:
            r31 = r12
            long r11 = r8.objectFieldOffset(r1)
            int r1 = (int) r11
            int r4 = r4 + 1
            r11 = r9[r4]
            boolean r12 = r11 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x0265
            java.lang.reflect.Field r11 = (java.lang.reflect.Field) r11
            goto L_0x026d
        L_0x0265:
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = reflectField(r14, r11)
            r9[r4] = r11
        L_0x026d:
            long r11 = r8.objectFieldOffset(r11)
            int r4 = (int) r11
            r29 = r0
            r0 = r4
            r12 = r30
            r4 = 0
            goto L_0x0375
        L_0x027a:
            r31 = r12
            int r1 = r15 + 1
            r11 = r9[r15]
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = reflectField(r14, r11)
            r12 = 49
            r15 = 9
            if (r5 == r15) goto L_0x02f4
            r15 = 17
            if (r5 != r15) goto L_0x0291
            goto L_0x02f4
        L_0x0291:
            r15 = 27
            if (r5 == r15) goto L_0x02e5
            if (r5 != r12) goto L_0x0298
            goto L_0x02e5
        L_0x0298:
            r15 = 12
            if (r5 == r15) goto L_0x02cc
            r15 = 30
            if (r5 == r15) goto L_0x02cc
            r15 = 44
            if (r5 != r15) goto L_0x02a5
            goto L_0x02cc
        L_0x02a5:
            r15 = 50
            if (r5 != r15) goto L_0x02ca
            int r15 = r21 + 1
            r16[r21] = r20
            int r21 = r20 / 3
            int r21 = r21 * 2
            int r26 = r1 + 1
            r1 = r9[r1]
            r10[r21] = r1
            r1 = r7 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x02c6
            int r21 = r21 + 1
            int r1 = r26 + 1
            r26 = r9[r26]
            r10[r21] = r26
            r21 = r15
            goto L_0x02ca
        L_0x02c6:
            r21 = r15
            r1 = r26
        L_0x02ca:
            r15 = 1
            goto L_0x0300
        L_0x02cc:
            com.google.protobuf.ProtoSyntax r15 = r32.getSyntax()
            com.google.protobuf.ProtoSyntax r12 = com.google.protobuf.ProtoSyntax.PROTO2
            if (r15 == r12) goto L_0x02d8
            r12 = r7 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x02ca
        L_0x02d8:
            int r12 = r20 / 3
            int r12 = r12 * 2
            r15 = 1
            int r12 = r12 + r15
            int r24 = r1 + 1
            r1 = r9[r1]
            r10[r12] = r1
            goto L_0x02f1
        L_0x02e5:
            r15 = 1
            int r12 = r20 / 3
            int r12 = r12 * 2
            int r12 = r12 + r15
            int r24 = r1 + 1
            r1 = r9[r1]
            r10[r12] = r1
        L_0x02f1:
            r1 = r24
            goto L_0x0300
        L_0x02f4:
            r15 = 1
            int r12 = r20 / 3
            int r12 = r12 * 2
            int r12 = r12 + r15
            java.lang.Class r24 = r11.getType()
            r10[r12] = r24
        L_0x0300:
            long r11 = r8.objectFieldOffset(r11)
            int r11 = (int) r11
            r12 = r7 & 4096(0x1000, float:5.74E-42)
            if (r12 == 0) goto L_0x030b
            r12 = r15
            goto L_0x030c
        L_0x030b:
            r12 = 0
        L_0x030c:
            if (r12 == 0) goto L_0x035b
            r12 = 17
            if (r5 > r12) goto L_0x035b
            int r12 = r4 + 1
            char r4 = r0.charAt(r4)
            r15 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r15) goto L_0x0338
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r23 = 13
        L_0x0321:
            int r27 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r15) goto L_0x0333
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r23
            r4 = r4 | r12
            int r23 = r23 + 13
            r12 = r27
            goto L_0x0321
        L_0x0333:
            int r12 = r12 << r23
            r4 = r4 | r12
            r12 = r27
        L_0x0338:
            int r23 = r6 * 2
            int r27 = r4 / 32
            int r23 = r23 + r27
            r15 = r9[r23]
            r29 = r0
            boolean r0 = r15 instanceof java.lang.reflect.Field
            if (r0 == 0) goto L_0x0349
            java.lang.reflect.Field r15 = (java.lang.reflect.Field) r15
            goto L_0x0351
        L_0x0349:
            java.lang.String r15 = (java.lang.String) r15
            java.lang.reflect.Field r15 = reflectField(r14, r15)
            r9[r23] = r15
        L_0x0351:
            r23 = r1
            long r0 = r8.objectFieldOffset(r15)
            int r0 = (int) r0
            int r4 = r4 % 32
            goto L_0x0364
        L_0x035b:
            r29 = r0
            r23 = r1
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r12 = r4
            r4 = 0
        L_0x0364:
            r1 = 18
            if (r5 < r1) goto L_0x0372
            r1 = 49
            if (r5 > r1) goto L_0x0372
            int r1 = r22 + 1
            r16[r22] = r11
            r22 = r1
        L_0x0372:
            r1 = r11
            r15 = r23
        L_0x0375:
            int r11 = r20 + 1
            r2[r20] = r3
            int r3 = r11 + 1
            r20 = r6
            r6 = r7 & 512(0x200, float:7.175E-43)
            if (r6 == 0) goto L_0x0384
            r6 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0385
        L_0x0384:
            r6 = 0
        L_0x0385:
            r23 = r8
            r8 = r7 & 256(0x100, float:3.59E-43)
            if (r8 == 0) goto L_0x038e
            r8 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x038f
        L_0x038e:
            r8 = 0
        L_0x038f:
            r6 = r6 | r8
            r7 = r7 & 2048(0x800, float:2.87E-42)
            if (r7 == 0) goto L_0x0397
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0398
        L_0x0397:
            r7 = 0
        L_0x0398:
            r6 = r6 | r7
            int r5 = r5 << 20
            r5 = r5 | r6
            r1 = r1 | r5
            r2[r11] = r1
            int r1 = r3 + 1
            int r4 = r4 << 20
            r0 = r0 | r4
            r2[r3] = r0
            r3 = r12
            r6 = r20
            r8 = r23
            r11 = r28
            r0 = r29
            r12 = r31
            r5 = 55296(0xd800, float:7.7486E-41)
            r20 = r1
            r1 = r25
            goto L_0x0177
        L_0x03ba:
            r31 = r12
            com.google.protobuf.MessageSchema r0 = new com.google.protobuf.MessageSchema
            com.google.protobuf.MessageLite r1 = r32.getDefaultInstance()
            com.google.protobuf.ProtoSyntax r14 = r32.getSyntax()
            r15 = 0
            r8 = r0
            r9 = r2
            r11 = r31
            r12 = r13
            r13 = r1
            r18 = r19
            r19 = r33
            r20 = r34
            r21 = r35
            r22 = r36
            r23 = r37
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.newSchemaForRawMessageInfo(com.google.protobuf.RawMessageInfo, com.google.protobuf.NewInstanceSchema, com.google.protobuf.ListFieldSchema, com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, com.google.protobuf.MapFieldSchema):com.google.protobuf.MessageSchema");
    }

    private static Field reflectField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        int i;
        int i2;
        int i3;
        FieldInfo[] fields = structuralMessageInfo.getFields();
        if (fields.length == 0) {
            i2 = 0;
            i = 0;
        } else {
            i2 = fields[0].getFieldNumber();
            i = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[(length * 3)];
        Object[] objArr = new Object[(length * 2)];
        int i4 = 0;
        int i5 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i4++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i5++;
            }
        }
        int[] iArr2 = null;
        int[] iArr3 = i4 > 0 ? new int[i4] : null;
        if (i5 > 0) {
            iArr2 = new int[i5];
        }
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i6 < fields.length) {
            FieldInfo fieldInfo2 = fields[i6];
            int fieldNumber = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i7, objArr);
            if (i8 < checkInitialized.length && checkInitialized[i8] == fieldNumber) {
                checkInitialized[i8] = i7;
                i8++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr3[i9] = i7;
                i9++;
            } else if (fieldInfo2.getType().id() >= 18 && fieldInfo2.getType().id() <= 49) {
                i3 = i7;
                iArr2[i10] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                i10++;
                i6++;
                i7 = i3 + 3;
            }
            i3 = i7;
            i6++;
            i7 = i3 + 3;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[(checkInitialized.length + iArr3.length + iArr2.length)];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length, iArr3.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length + iArr3.length, iArr2.length);
        return new MessageSchema(iArr, objArr, i2, i, structuralMessageInfo.getDefaultInstance(), structuralMessageInfo.getSyntax(), true, iArr4, checkInitialized.length, checkInitialized.length + iArr3.length, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void storeFieldData(com.google.protobuf.FieldInfo r8, int[] r9, int r10, java.lang.Object[] r11) {
        /*
            com.google.protobuf.OneofInfo r0 = r8.getOneof()
            r1 = 0
            if (r0 == 0) goto L_0x0025
            com.google.protobuf.FieldType r2 = r8.getType()
            int r2 = r2.id()
            int r2 = r2 + 51
            java.lang.reflect.Field r3 = r0.getValueField()
            long r3 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r3)
            int r3 = (int) r3
            java.lang.reflect.Field r0 = r0.getCaseField()
            long r4 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r0)
        L_0x0022:
            int r0 = (int) r4
            r4 = r1
            goto L_0x006c
        L_0x0025:
            com.google.protobuf.FieldType r0 = r8.getType()
            java.lang.reflect.Field r2 = r8.getField()
            long r2 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r2)
            int r3 = (int) r2
            int r2 = r0.id()
            boolean r4 = r0.isList()
            if (r4 != 0) goto L_0x005a
            boolean r0 = r0.isMap()
            if (r0 != 0) goto L_0x005a
            java.lang.reflect.Field r0 = r8.getPresenceField()
            if (r0 != 0) goto L_0x004c
            r0 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0051
        L_0x004c:
            long r4 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r0)
            int r0 = (int) r4
        L_0x0051:
            int r4 = r8.getPresenceMask()
            int r4 = java.lang.Integer.numberOfTrailingZeros(r4)
            goto L_0x006c
        L_0x005a:
            java.lang.reflect.Field r0 = r8.getCachedSizeField()
            if (r0 != 0) goto L_0x0063
            r0 = r1
            r4 = r0
            goto L_0x006c
        L_0x0063:
            java.lang.reflect.Field r0 = r8.getCachedSizeField()
            long r4 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r0)
            goto L_0x0022
        L_0x006c:
            int r5 = r8.getFieldNumber()
            r9[r10] = r5
            int r5 = r10 + 1
            boolean r6 = r8.isEnforceUtf8()
            if (r6 == 0) goto L_0x007d
            r6 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x007e
        L_0x007d:
            r6 = r1
        L_0x007e:
            boolean r7 = r8.isRequired()
            if (r7 == 0) goto L_0x0086
            r1 = 268435456(0x10000000, float:2.5243549E-29)
        L_0x0086:
            r1 = r1 | r6
            int r2 = r2 << 20
            r1 = r1 | r2
            r1 = r1 | r3
            r9[r5] = r1
            int r1 = r10 + 2
            int r2 = r4 << 20
            r0 = r0 | r2
            r9[r1] = r0
            java.lang.Class r9 = r8.getMessageFieldClass()
            java.lang.Object r0 = r8.getMapDefaultEntry()
            if (r0 == 0) goto L_0x00be
            int r10 = r10 / 3
            int r10 = r10 * 2
            java.lang.Object r0 = r8.getMapDefaultEntry()
            r11[r10] = r0
            if (r9 == 0) goto L_0x00af
            int r10 = r10 + 1
            r11[r10] = r9
            goto L_0x00db
        L_0x00af:
            com.google.protobuf.Internal$EnumVerifier r9 = r8.getEnumVerifier()
            if (r9 == 0) goto L_0x00db
            int r10 = r10 + 1
            com.google.protobuf.Internal$EnumVerifier r8 = r8.getEnumVerifier()
            r11[r10] = r8
            goto L_0x00db
        L_0x00be:
            if (r9 == 0) goto L_0x00c9
            int r10 = r10 / 3
            int r10 = r10 * 2
            int r10 = r10 + 1
            r11[r10] = r9
            goto L_0x00db
        L_0x00c9:
            com.google.protobuf.Internal$EnumVerifier r9 = r8.getEnumVerifier()
            if (r9 == 0) goto L_0x00db
            int r10 = r10 / 3
            int r10 = r10 * 2
            int r10 = r10 + 1
            com.google.protobuf.Internal$EnumVerifier r8 = r8.getEnumVerifier()
            r11[r10] = r8
        L_0x00db:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.storeFieldData(com.google.protobuf.FieldInfo, int[], int, java.lang.Object[]):void");
    }

    public T newInstance() {
        return this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    public boolean equals(T t, T t2) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(t, t2, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(t).equals(this.unknownFieldSchema.getFromMessage(t2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t).equals(this.extensionSchema.getExtensions(t2));
        }
        return true;
    }

    private boolean equals(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (!arePresentForEquals(t, t2, i) || Double.doubleToLongBits(UnsafeUtil.getDouble((Object) t, offset)) != Double.doubleToLongBits(UnsafeUtil.getDouble((Object) t2, offset))) {
                    return false;
                }
                return true;
            case 1:
                if (!arePresentForEquals(t, t2, i) || Float.floatToIntBits(UnsafeUtil.getFloat((Object) t, offset)) != Float.floatToIntBits(UnsafeUtil.getFloat((Object) t2, offset))) {
                    return false;
                }
                return true;
            case 2:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getLong((Object) t, offset) != UnsafeUtil.getLong((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 3:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getLong((Object) t, offset) != UnsafeUtil.getLong((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 4:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getInt((Object) t, offset) != UnsafeUtil.getInt((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 5:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getLong((Object) t, offset) != UnsafeUtil.getLong((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 6:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getInt((Object) t, offset) != UnsafeUtil.getInt((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 7:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getBoolean((Object) t, offset) != UnsafeUtil.getBoolean((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 8:
                if (!arePresentForEquals(t, t2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset))) {
                    return false;
                }
                return true;
            case 9:
                if (!arePresentForEquals(t, t2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset))) {
                    return false;
                }
                return true;
            case 10:
                if (!arePresentForEquals(t, t2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset))) {
                    return false;
                }
                return true;
            case 11:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getInt((Object) t, offset) != UnsafeUtil.getInt((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 12:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getInt((Object) t, offset) != UnsafeUtil.getInt((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 13:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getInt((Object) t, offset) != UnsafeUtil.getInt((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 14:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getLong((Object) t, offset) != UnsafeUtil.getLong((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 15:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getInt((Object) t, offset) != UnsafeUtil.getInt((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 16:
                if (!arePresentForEquals(t, t2, i) || UnsafeUtil.getLong((Object) t, offset) != UnsafeUtil.getLong((Object) t2, offset)) {
                    return false;
                }
                return true;
            case 17:
                if (!arePresentForEquals(t, t2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset))) {
                    return false;
                }
                return true;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case Elf64.Ehdr.E_SHENTSIZE /*58*/:
            case 59:
            case 60:
            case LockFreeTaskQueueCore.CLOSED_SHIFT /*61*/:
            case Elf64.Ehdr.E_SHSTRNDX /*62*/:
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
            case 64:
            case 65:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
            case 67:
            case 68:
                if (!isOneofCaseEqual(t, t2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) t, offset), UnsafeUtil.getObject((Object) t2, offset))) {
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c1, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0225, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0226, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int hashCode(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.buffer
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x022a
            int r3 = r8.typeAndOffsetAt(r1)
            int r4 = r8.numberAt(r1)
            long r5 = offset(r3)
            int r3 = type(r3)
            r7 = 37
            switch(r3) {
                case 0: goto L_0x0217;
                case 1: goto L_0x020c;
                case 2: goto L_0x0201;
                case 3: goto L_0x01f6;
                case 4: goto L_0x01ef;
                case 5: goto L_0x01e4;
                case 6: goto L_0x01dd;
                case 7: goto L_0x01d2;
                case 8: goto L_0x01c5;
                case 9: goto L_0x01b7;
                case 10: goto L_0x01ab;
                case 11: goto L_0x01a3;
                case 12: goto L_0x019b;
                case 13: goto L_0x0193;
                case 14: goto L_0x0187;
                case 15: goto L_0x017f;
                case 16: goto L_0x0173;
                case 17: goto L_0x0168;
                case 18: goto L_0x015c;
                case 19: goto L_0x015c;
                case 20: goto L_0x015c;
                case 21: goto L_0x015c;
                case 22: goto L_0x015c;
                case 23: goto L_0x015c;
                case 24: goto L_0x015c;
                case 25: goto L_0x015c;
                case 26: goto L_0x015c;
                case 27: goto L_0x015c;
                case 28: goto L_0x015c;
                case 29: goto L_0x015c;
                case 30: goto L_0x015c;
                case 31: goto L_0x015c;
                case 32: goto L_0x015c;
                case 33: goto L_0x015c;
                case 34: goto L_0x015c;
                case 35: goto L_0x015c;
                case 36: goto L_0x015c;
                case 37: goto L_0x015c;
                case 38: goto L_0x015c;
                case 39: goto L_0x015c;
                case 40: goto L_0x015c;
                case 41: goto L_0x015c;
                case 42: goto L_0x015c;
                case 43: goto L_0x015c;
                case 44: goto L_0x015c;
                case 45: goto L_0x015c;
                case 46: goto L_0x015c;
                case 47: goto L_0x015c;
                case 48: goto L_0x015c;
                case 49: goto L_0x015c;
                case 50: goto L_0x0150;
                case 51: goto L_0x013a;
                case 52: goto L_0x0128;
                case 53: goto L_0x0116;
                case 54: goto L_0x0104;
                case 55: goto L_0x00f6;
                case 56: goto L_0x00e4;
                case 57: goto L_0x00d6;
                case 58: goto L_0x00c4;
                case 59: goto L_0x00b0;
                case 60: goto L_0x009e;
                case 61: goto L_0x008c;
                case 62: goto L_0x007e;
                case 63: goto L_0x0070;
                case 64: goto L_0x0062;
                case 65: goto L_0x0050;
                case 66: goto L_0x0042;
                case 67: goto L_0x0030;
                case 68: goto L_0x001e;
                default: goto L_0x001c;
            }
        L_0x001c:
            goto L_0x0226
        L_0x001e:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0225
        L_0x0030:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x0042:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x0225
        L_0x0050:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x0062:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x0225
        L_0x0070:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x0225
        L_0x007e:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x0225
        L_0x008c:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            int r3 = r3.hashCode()
            goto L_0x0225
        L_0x009e:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0225
        L_0x00b0:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0225
        L_0x00c4:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            boolean r3 = oneofBooleanAt(r9, r5)
            int r3 = com.google.protobuf.Internal.hashBoolean(r3)
            goto L_0x0225
        L_0x00d6:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x0225
        L_0x00e4:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x00f6:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x0225
        L_0x0104:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x0116:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x0128:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            float r3 = oneofFloatAt(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0225
        L_0x013a:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0226
            int r2 = r2 * 53
            double r3 = oneofDoubleAt(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x0150:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            int r3 = r3.hashCode()
            goto L_0x0225
        L_0x015c:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            int r3 = r3.hashCode()
            goto L_0x0225
        L_0x0168:
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            if (r3 == 0) goto L_0x01c1
            int r7 = r3.hashCode()
            goto L_0x01c1
        L_0x0173:
            int r2 = r2 * 53
            long r3 = com.google.protobuf.UnsafeUtil.getLong((java.lang.Object) r9, (long) r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x017f:
            int r2 = r2 * 53
            int r3 = com.google.protobuf.UnsafeUtil.getInt((java.lang.Object) r9, (long) r5)
            goto L_0x0225
        L_0x0187:
            int r2 = r2 * 53
            long r3 = com.google.protobuf.UnsafeUtil.getLong((java.lang.Object) r9, (long) r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x0193:
            int r2 = r2 * 53
            int r3 = com.google.protobuf.UnsafeUtil.getInt((java.lang.Object) r9, (long) r5)
            goto L_0x0225
        L_0x019b:
            int r2 = r2 * 53
            int r3 = com.google.protobuf.UnsafeUtil.getInt((java.lang.Object) r9, (long) r5)
            goto L_0x0225
        L_0x01a3:
            int r2 = r2 * 53
            int r3 = com.google.protobuf.UnsafeUtil.getInt((java.lang.Object) r9, (long) r5)
            goto L_0x0225
        L_0x01ab:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            int r3 = r3.hashCode()
            goto L_0x0225
        L_0x01b7:
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            if (r3 == 0) goto L_0x01c1
            int r7 = r3.hashCode()
        L_0x01c1:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0226
        L_0x01c5:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r9, (long) r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0225
        L_0x01d2:
            int r2 = r2 * 53
            boolean r3 = com.google.protobuf.UnsafeUtil.getBoolean((java.lang.Object) r9, (long) r5)
            int r3 = com.google.protobuf.Internal.hashBoolean(r3)
            goto L_0x0225
        L_0x01dd:
            int r2 = r2 * 53
            int r3 = com.google.protobuf.UnsafeUtil.getInt((java.lang.Object) r9, (long) r5)
            goto L_0x0225
        L_0x01e4:
            int r2 = r2 * 53
            long r3 = com.google.protobuf.UnsafeUtil.getLong((java.lang.Object) r9, (long) r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x01ef:
            int r2 = r2 * 53
            int r3 = com.google.protobuf.UnsafeUtil.getInt((java.lang.Object) r9, (long) r5)
            goto L_0x0225
        L_0x01f6:
            int r2 = r2 * 53
            long r3 = com.google.protobuf.UnsafeUtil.getLong((java.lang.Object) r9, (long) r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x0201:
            int r2 = r2 * 53
            long r3 = com.google.protobuf.UnsafeUtil.getLong((java.lang.Object) r9, (long) r5)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
            goto L_0x0225
        L_0x020c:
            int r2 = r2 * 53
            float r3 = com.google.protobuf.UnsafeUtil.getFloat((java.lang.Object) r9, (long) r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0225
        L_0x0217:
            int r2 = r2 * 53
            double r3 = com.google.protobuf.UnsafeUtil.getDouble((java.lang.Object) r9, (long) r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.protobuf.Internal.hashLong(r3)
        L_0x0225:
            int r2 = r2 + r3
        L_0x0226:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022a:
            int r2 = r2 * 53
            com.google.protobuf.UnknownFieldSchema<?, ?> r0 = r8.unknownFieldSchema
            java.lang.Object r0 = r0.getFromMessage(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.hasExtensions
            if (r0 == 0) goto L_0x0248
            int r2 = r2 * 53
            com.google.protobuf.ExtensionSchema<?> r0 = r8.extensionSchema
            com.google.protobuf.FieldSet r9 = r0.getExtensions(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x0248:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.hashCode(java.lang.Object):int");
    }

    public void mergeFrom(T t, T t2) {
        checkMutable(t);
        t2.getClass();
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(t, t2, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t, t2);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t, t2);
        }
    }

    private void mergeSingleField(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(i);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putDouble((Object) t, offset, UnsafeUtil.getDouble((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 1:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putFloat((Object) t, offset, UnsafeUtil.getFloat((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 2:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 3:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 4:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 5:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 6:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 7:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putBoolean((Object) t, offset, UnsafeUtil.getBoolean((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 8:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putObject((Object) t, offset, UnsafeUtil.getObject((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 9:
                mergeMessage(t, t2, i);
                return;
            case 10:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putObject((Object) t, offset, UnsafeUtil.getObject((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 11:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 12:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 13:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 14:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 15:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt((Object) t, offset, UnsafeUtil.getInt((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 16:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong((Object) t, offset, UnsafeUtil.getLong((Object) t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 17:
                mergeMessage(t, t2, i);
                return;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                this.listFieldSchema.mergeListsAt(t, t2, offset);
                return;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, t, t2, offset);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case Elf64.Ehdr.E_SHENTSIZE /*58*/:
            case 59:
                if (isOneofPresent(t2, numberAt, i)) {
                    UnsafeUtil.putObject((Object) t, offset, UnsafeUtil.getObject((Object) t2, offset));
                    setOneofPresent(t, numberAt, i);
                    return;
                }
                return;
            case 60:
                mergeOneofMessage(t, t2, i);
                return;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /*61*/:
            case Elf64.Ehdr.E_SHSTRNDX /*62*/:
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
            case 64:
            case 65:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
            case 67:
                if (isOneofPresent(t2, numberAt, i)) {
                    UnsafeUtil.putObject((Object) t, offset, UnsafeUtil.getObject((Object) t2, offset));
                    setOneofPresent(t, numberAt, i);
                    return;
                }
                return;
            case 68:
                mergeOneofMessage(t, t2, i);
                return;
            default:
                return;
        }
    }

    private void mergeMessage(T t, T t2, int i) {
        if (isFieldPresent(t2, i)) {
            long offset = offset(typeAndOffsetAt(i));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(t2, offset);
            if (object != null) {
                Schema messageFieldSchema = getMessageFieldSchema(i);
                if (!isFieldPresent(t, i)) {
                    if (!isMutable(object)) {
                        unsafe.putObject(t, offset, object);
                    } else {
                        Object newInstance = messageFieldSchema.newInstance();
                        messageFieldSchema.mergeFrom(newInstance, object);
                        unsafe.putObject(t, offset, newInstance);
                    }
                    setFieldPresent(t, i);
                    return;
                }
                Object object2 = unsafe.getObject(t, offset);
                if (!isMutable(object2)) {
                    Object newInstance2 = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance2, object2);
                    unsafe.putObject(t, offset, newInstance2);
                    object2 = newInstance2;
                }
                messageFieldSchema.mergeFrom(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + t2);
        }
    }

    private void mergeOneofMessage(T t, T t2, int i) {
        int numberAt = numberAt(i);
        if (isOneofPresent(t2, numberAt, i)) {
            long offset = offset(typeAndOffsetAt(i));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(t2, offset);
            if (object != null) {
                Schema messageFieldSchema = getMessageFieldSchema(i);
                if (!isOneofPresent(t, numberAt, i)) {
                    if (!isMutable(object)) {
                        unsafe.putObject(t, offset, object);
                    } else {
                        Object newInstance = messageFieldSchema.newInstance();
                        messageFieldSchema.mergeFrom(newInstance, object);
                        unsafe.putObject(t, offset, newInstance);
                    }
                    setOneofPresent(t, numberAt, i);
                    return;
                }
                Object object2 = unsafe.getObject(t, offset);
                if (!isMutable(object2)) {
                    Object newInstance2 = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance2, object2);
                    unsafe.putObject(t, offset, newInstance2);
                    object2 = newInstance2;
                }
                messageFieldSchema.mergeFrom(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + t2);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0346, code lost:
        r0 = (r0 + r2) + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x040f, code lost:
        r12 = r12 + r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0410, code lost:
        r15 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0546, code lost:
        r12 = r12 + r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0547, code lost:
        r15 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x05fc, code lost:
        r11 = r11 + 3;
        r0 = r14;
        r9 = r15;
        r1 = r16;
        r10 = 1048575;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getSerializedSize(T r19) {
        /*
            r18 = this;
            r6 = r18
            r7 = r19
            sun.misc.Unsafe r8 = UNSAFE
            r9 = 0
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r9
            r11 = r1
            r12 = r11
            r0 = r10
        L_0x000e:
            int[] r2 = r6.buffer
            int r2 = r2.length
            if (r11 >= r2) goto L_0x0607
            int r2 = r6.typeAndOffsetAt(r11)
            int r3 = type(r2)
            int r13 = r6.numberAt(r11)
            int[] r4 = r6.buffer
            int r5 = r11 + 2
            r4 = r4[r5]
            r5 = r4 & r10
            r14 = 17
            r15 = 1
            if (r3 > r14) goto L_0x0043
            if (r5 == r0) goto L_0x0039
            if (r5 != r10) goto L_0x0032
            r0 = r9
            goto L_0x0037
        L_0x0032:
            long r0 = (long) r5
            int r0 = r8.getInt(r7, r0)
        L_0x0037:
            r1 = r0
            r0 = r5
        L_0x0039:
            int r4 = r4 >>> 20
            int r4 = r15 << r4
            r14 = r0
            r16 = r1
            r17 = r4
            goto L_0x0048
        L_0x0043:
            r14 = r0
            r16 = r1
            r17 = r9
        L_0x0048:
            long r1 = offset(r2)
            com.google.protobuf.FieldType r0 = com.google.protobuf.FieldType.DOUBLE_LIST_PACKED
            int r0 = r0.id()
            if (r3 < r0) goto L_0x005d
            com.google.protobuf.FieldType r0 = com.google.protobuf.FieldType.SINT64_LIST_PACKED
            int r0 = r0.id()
            if (r3 > r0) goto L_0x005d
            goto L_0x005e
        L_0x005d:
            r5 = r9
        L_0x005e:
            r0 = r5
            r4 = 0
            switch(r3) {
                case 0: goto L_0x05e3;
                case 1: goto L_0x05cb;
                case 2: goto L_0x05b0;
                case 3: goto L_0x0595;
                case 4: goto L_0x0579;
                case 5: goto L_0x0561;
                case 6: goto L_0x054a;
                case 7: goto L_0x0532;
                case 8: goto L_0x050b;
                case 9: goto L_0x04ed;
                case 10: goto L_0x04d0;
                case 11: goto L_0x04b5;
                case 12: goto L_0x049a;
                case 13: goto L_0x0482;
                case 14: goto L_0x046b;
                case 15: goto L_0x0450;
                case 16: goto L_0x0435;
                case 17: goto L_0x0413;
                case 18: goto L_0x0405;
                case 19: goto L_0x03fa;
                case 20: goto L_0x03ef;
                case 21: goto L_0x03e4;
                case 22: goto L_0x03d9;
                case 23: goto L_0x03ce;
                case 24: goto L_0x03c3;
                case 25: goto L_0x03b8;
                case 26: goto L_0x03ad;
                case 27: goto L_0x039e;
                case 28: goto L_0x0392;
                case 29: goto L_0x0386;
                case 30: goto L_0x037a;
                case 31: goto L_0x036e;
                case 32: goto L_0x0362;
                case 33: goto L_0x0356;
                case 34: goto L_0x034a;
                case 35: goto L_0x032a;
                case 36: goto L_0x030d;
                case 37: goto L_0x02f0;
                case 38: goto L_0x02d3;
                case 39: goto L_0x02b5;
                case 40: goto L_0x0297;
                case 41: goto L_0x0279;
                case 42: goto L_0x025b;
                case 43: goto L_0x023d;
                case 44: goto L_0x021f;
                case 45: goto L_0x0201;
                case 46: goto L_0x01e3;
                case 47: goto L_0x01c5;
                case 48: goto L_0x01a7;
                case 49: goto L_0x0197;
                case 50: goto L_0x0187;
                case 51: goto L_0x0179;
                case 52: goto L_0x016c;
                case 53: goto L_0x015c;
                case 54: goto L_0x014c;
                case 55: goto L_0x013c;
                case 56: goto L_0x0130;
                case 57: goto L_0x0124;
                case 58: goto L_0x0118;
                case 59: goto L_0x00fa;
                case 60: goto L_0x00e6;
                case 61: goto L_0x00d4;
                case 62: goto L_0x00c4;
                case 63: goto L_0x00b4;
                case 64: goto L_0x00a8;
                case 65: goto L_0x009c;
                case 66: goto L_0x008c;
                case 67: goto L_0x007c;
                case 68: goto L_0x0066;
                default: goto L_0x0064;
            }
        L_0x0064:
            goto L_0x0410
        L_0x0066:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            java.lang.Object r0 = r8.getObject(r7, r1)
            com.google.protobuf.MessageLite r0 = (com.google.protobuf.MessageLite) r0
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = com.google.protobuf.CodedOutputStream.computeGroupSize(r13, r0, r1)
            goto L_0x040f
        L_0x007c:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            long r0 = oneofLongAt(r7, r1)
            int r0 = com.google.protobuf.CodedOutputStream.computeSInt64Size(r13, r0)
            goto L_0x040f
        L_0x008c:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = oneofIntAt(r7, r1)
            int r0 = com.google.protobuf.CodedOutputStream.computeSInt32Size(r13, r0)
            goto L_0x040f
        L_0x009c:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = com.google.protobuf.CodedOutputStream.computeSFixed64Size(r13, r4)
            goto L_0x040f
        L_0x00a8:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = com.google.protobuf.CodedOutputStream.computeSFixed32Size(r13, r9)
            goto L_0x040f
        L_0x00b4:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = oneofIntAt(r7, r1)
            int r0 = com.google.protobuf.CodedOutputStream.computeEnumSize(r13, r0)
            goto L_0x040f
        L_0x00c4:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = oneofIntAt(r7, r1)
            int r0 = com.google.protobuf.CodedOutputStream.computeUInt32Size(r13, r0)
            goto L_0x040f
        L_0x00d4:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            java.lang.Object r0 = r8.getObject(r7, r1)
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            int r0 = com.google.protobuf.CodedOutputStream.computeBytesSize(r13, r0)
            goto L_0x040f
        L_0x00e6:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            java.lang.Object r0 = r8.getObject(r7, r1)
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = com.google.protobuf.SchemaUtil.computeSizeMessage(r13, r0, r1)
            goto L_0x040f
        L_0x00fa:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            java.lang.Object r0 = r8.getObject(r7, r1)
            boolean r1 = r0 instanceof com.google.protobuf.ByteString
            if (r1 == 0) goto L_0x0110
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            int r0 = com.google.protobuf.CodedOutputStream.computeBytesSize(r13, r0)
            goto L_0x040f
        L_0x0110:
            java.lang.String r0 = (java.lang.String) r0
            int r0 = com.google.protobuf.CodedOutputStream.computeStringSize(r13, r0)
            goto L_0x040f
        L_0x0118:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = com.google.protobuf.CodedOutputStream.computeBoolSize(r13, r15)
            goto L_0x040f
        L_0x0124:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = com.google.protobuf.CodedOutputStream.computeFixed32Size(r13, r9)
            goto L_0x040f
        L_0x0130:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = com.google.protobuf.CodedOutputStream.computeFixed64Size(r13, r4)
            goto L_0x040f
        L_0x013c:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            int r0 = oneofIntAt(r7, r1)
            int r0 = com.google.protobuf.CodedOutputStream.computeInt32Size(r13, r0)
            goto L_0x040f
        L_0x014c:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            long r0 = oneofLongAt(r7, r1)
            int r0 = com.google.protobuf.CodedOutputStream.computeUInt64Size(r13, r0)
            goto L_0x040f
        L_0x015c:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            long r0 = oneofLongAt(r7, r1)
            int r0 = com.google.protobuf.CodedOutputStream.computeInt64Size(r13, r0)
            goto L_0x040f
        L_0x016c:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            r4 = 0
            int r0 = com.google.protobuf.CodedOutputStream.computeFloatSize(r13, r4)
            goto L_0x040f
        L_0x0179:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0410
            r4 = 0
            int r0 = com.google.protobuf.CodedOutputStream.computeDoubleSize(r13, r4)
            goto L_0x040f
        L_0x0187:
            com.google.protobuf.MapFieldSchema r0 = r6.mapFieldSchema
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.lang.Object r2 = r6.getMapFieldDefaultEntry(r11)
            int r0 = r0.getSerializedSize(r13, r1, r2)
            goto L_0x040f
        L_0x0197:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = com.google.protobuf.SchemaUtil.computeSizeGroupList(r13, r0, r1)
            goto L_0x040f
        L_0x01a7:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeSInt64ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x01bb
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x01bb:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x01c5:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeSInt32ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x01d9
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x01d9:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x01e3:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeFixed64ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x01f7
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x01f7:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x0201:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeFixed32ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x0215
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x0215:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x021f:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeEnumListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x0233
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x0233:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x023d:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeUInt32ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x0251
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x0251:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x025b:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeBoolListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x026f
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x026f:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x0279:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeFixed32ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x028d
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x028d:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x0297:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeFixed64ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x02ab
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x02ab:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x02b5:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeInt32ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x02c9
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x02c9:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x02d3:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeUInt64ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x02e7
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x02e7:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x02f0:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeInt64ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x0304
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x0304:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x030d:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeFixed32ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x0321
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x0321:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
            goto L_0x0346
        L_0x032a:
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.util.List r1 = (java.util.List) r1
            int r1 = com.google.protobuf.SchemaUtil.computeSizeFixed64ListNoTag(r1)
            if (r1 <= 0) goto L_0x0410
            boolean r2 = r6.useCachedSizeField
            if (r2 == 0) goto L_0x033e
            long r2 = (long) r0
            r8.putInt(r7, r2, r1)
        L_0x033e:
            int r0 = com.google.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r1)
        L_0x0346:
            int r0 = r0 + r2
            int r0 = r0 + r1
            goto L_0x040f
        L_0x034a:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeSInt64List(r13, r0, r9)
            goto L_0x040f
        L_0x0356:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeSInt32List(r13, r0, r9)
            goto L_0x040f
        L_0x0362:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeFixed64List(r13, r0, r9)
            goto L_0x040f
        L_0x036e:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeFixed32List(r13, r0, r9)
            goto L_0x040f
        L_0x037a:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeEnumList(r13, r0, r9)
            goto L_0x040f
        L_0x0386:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeUInt32List(r13, r0, r9)
            goto L_0x040f
        L_0x0392:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeByteStringList(r13, r0)
            goto L_0x040f
        L_0x039e:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = com.google.protobuf.SchemaUtil.computeSizeMessageList(r13, r0, r1)
            goto L_0x040f
        L_0x03ad:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeStringList(r13, r0)
            goto L_0x040f
        L_0x03b8:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeBoolList(r13, r0, r9)
            goto L_0x040f
        L_0x03c3:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeFixed32List(r13, r0, r9)
            goto L_0x040f
        L_0x03ce:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeFixed64List(r13, r0, r9)
            goto L_0x040f
        L_0x03d9:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeInt32List(r13, r0, r9)
            goto L_0x040f
        L_0x03e4:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeUInt64List(r13, r0, r9)
            goto L_0x040f
        L_0x03ef:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeInt64List(r13, r0, r9)
            goto L_0x040f
        L_0x03fa:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeFixed32List(r13, r0, r9)
            goto L_0x040f
        L_0x0405:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.protobuf.SchemaUtil.computeSizeFixed64List(r13, r0, r9)
        L_0x040f:
            int r12 = r12 + r0
        L_0x0410:
            r15 = r9
            goto L_0x05fc
        L_0x0413:
            r0 = r18
            r4 = r1
            r1 = r19
            r2 = r11
            r3 = r14
            r9 = r4
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            java.lang.Object r0 = r8.getObject(r7, r9)
            com.google.protobuf.MessageLite r0 = (com.google.protobuf.MessageLite) r0
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = com.google.protobuf.CodedOutputStream.computeGroupSize(r13, r0, r1)
            goto L_0x0546
        L_0x0435:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            long r0 = r8.getLong(r7, r9)
            int r0 = com.google.protobuf.CodedOutputStream.computeSInt64Size(r13, r0)
            goto L_0x0546
        L_0x0450:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            int r0 = r8.getInt(r7, r9)
            int r0 = com.google.protobuf.CodedOutputStream.computeSInt32Size(r13, r0)
            goto L_0x0546
        L_0x046b:
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r9 = r4
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            int r0 = com.google.protobuf.CodedOutputStream.computeSFixed64Size(r13, r9)
            goto L_0x0546
        L_0x0482:
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            r0 = 0
            int r1 = com.google.protobuf.CodedOutputStream.computeSFixed32Size(r13, r0)
            int r12 = r12 + r1
            goto L_0x0547
        L_0x049a:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            int r0 = r8.getInt(r7, r9)
            int r0 = com.google.protobuf.CodedOutputStream.computeEnumSize(r13, r0)
            goto L_0x0546
        L_0x04b5:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            int r0 = r8.getInt(r7, r9)
            int r0 = com.google.protobuf.CodedOutputStream.computeUInt32Size(r13, r0)
            goto L_0x0546
        L_0x04d0:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            java.lang.Object r0 = r8.getObject(r7, r9)
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            int r0 = com.google.protobuf.CodedOutputStream.computeBytesSize(r13, r0)
            goto L_0x0546
        L_0x04ed:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            java.lang.Object r0 = r8.getObject(r7, r9)
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = com.google.protobuf.SchemaUtil.computeSizeMessage(r13, r0, r1)
            goto L_0x0546
        L_0x050b:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            java.lang.Object r0 = r8.getObject(r7, r9)
            boolean r1 = r0 instanceof com.google.protobuf.ByteString
            if (r1 == 0) goto L_0x052b
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            int r0 = com.google.protobuf.CodedOutputStream.computeBytesSize(r13, r0)
            goto L_0x0546
        L_0x052b:
            java.lang.String r0 = (java.lang.String) r0
            int r0 = com.google.protobuf.CodedOutputStream.computeStringSize(r13, r0)
            goto L_0x0546
        L_0x0532:
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            int r0 = com.google.protobuf.CodedOutputStream.computeBoolSize(r13, r15)
        L_0x0546:
            int r12 = r12 + r0
        L_0x0547:
            r15 = 0
            goto L_0x05fc
        L_0x054a:
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0547
            r15 = 0
            int r0 = com.google.protobuf.CodedOutputStream.computeFixed32Size(r13, r15)
            goto L_0x05fb
        L_0x0561:
            r15 = r9
            r9 = r4
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x05fc
            int r0 = com.google.protobuf.CodedOutputStream.computeFixed64Size(r13, r9)
            goto L_0x05fb
        L_0x0579:
            r15 = r9
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x05fc
            int r0 = r8.getInt(r7, r9)
            int r0 = com.google.protobuf.CodedOutputStream.computeInt32Size(r13, r0)
            goto L_0x05fb
        L_0x0595:
            r15 = r9
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x05fc
            long r0 = r8.getLong(r7, r9)
            int r0 = com.google.protobuf.CodedOutputStream.computeUInt64Size(r13, r0)
            goto L_0x05fb
        L_0x05b0:
            r15 = r9
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x05fc
            long r0 = r8.getLong(r7, r9)
            int r0 = com.google.protobuf.CodedOutputStream.computeInt64Size(r13, r0)
            goto L_0x05fb
        L_0x05cb:
            r15 = r9
            r4 = 0
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r9 = r4
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x05fc
            int r0 = com.google.protobuf.CodedOutputStream.computeFloatSize(r13, r9)
            goto L_0x05fb
        L_0x05e3:
            r15 = r9
            r4 = 0
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r9 = r4
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x05fc
            int r0 = com.google.protobuf.CodedOutputStream.computeDoubleSize(r13, r9)
        L_0x05fb:
            int r12 = r12 + r0
        L_0x05fc:
            int r11 = r11 + 3
            r0 = r14
            r9 = r15
            r1 = r16
            r10 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x000e
        L_0x0607:
            com.google.protobuf.UnknownFieldSchema<?, ?> r0 = r6.unknownFieldSchema
            int r0 = r6.getUnknownFieldsSerializedSize(r0, r7)
            int r12 = r12 + r0
            boolean r0 = r6.hasExtensions
            if (r0 == 0) goto L_0x061d
            com.google.protobuf.ExtensionSchema<?> r0 = r6.extensionSchema
            com.google.protobuf.FieldSet r0 = r0.getExtensions(r7)
            int r0 = r0.getSerializedSize()
            int r12 = r12 + r0
        L_0x061d:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.getSerializedSize(java.lang.Object):int");
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema2, T t) {
        return unknownFieldSchema2.getSerializedSize(unknownFieldSchema2.getFromMessage(t));
    }

    public void writeTo(T t, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(t, writer);
        } else {
            writeFieldsInAscendingOrder(t, writer);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:117:0x03b3, code lost:
        r19 = r4;
        r20 = r11;
        r16 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0614, code lost:
        r15 = r15 + 3;
        r0 = r9;
        r1 = r16;
        r2 = r17;
        r11 = r20;
        r13 = 1048575;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
        r20 = r11;
        r16 = r14;
     */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0626  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeFieldsInAscendingOrder(T r22, com.google.protobuf.Writer r23) throws java.io.IOException {
        /*
            r21 = this;
            r6 = r21
            r7 = r22
            r8 = r23
            boolean r0 = r6.hasExtensions
            if (r0 == 0) goto L_0x0022
            com.google.protobuf.ExtensionSchema<?> r0 = r6.extensionSchema
            com.google.protobuf.FieldSet r0 = r0.getExtensions(r7)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0022
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r10 = r0
            goto L_0x0024
        L_0x0022:
            r1 = 0
            r10 = 0
        L_0x0024:
            int[] r0 = r6.buffer
            int r11 = r0.length
            sun.misc.Unsafe r12 = UNSAFE
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r13
            r2 = 0
            r15 = 0
        L_0x002f:
            if (r15 >= r11) goto L_0x0622
            int r3 = r6.typeAndOffsetAt(r15)
            int r5 = r6.numberAt(r15)
            int r4 = type(r3)
            r9 = 17
            if (r4 > r9) goto L_0x0068
            int[] r9 = r6.buffer
            int r16 = r15 + 2
            r9 = r9[r16]
            r14 = r9 & r13
            if (r14 == r0) goto L_0x005b
            if (r14 != r13) goto L_0x0051
            r17 = r1
            r2 = 0
            goto L_0x0059
        L_0x0051:
            r17 = r1
            long r0 = (long) r14
            int r0 = r12.getInt(r7, r0)
            r2 = r0
        L_0x0059:
            r0 = r14
            goto L_0x005d
        L_0x005b:
            r17 = r1
        L_0x005d:
            int r1 = r9 >>> 20
            r9 = 1
            int r1 = r9 << r1
            r9 = r0
            r18 = r1
            r14 = r17
            goto L_0x006f
        L_0x0068:
            r17 = r1
            r9 = r0
            r14 = r17
            r18 = 0
        L_0x006f:
            r17 = r2
        L_0x0071:
            if (r14 == 0) goto L_0x0090
            com.google.protobuf.ExtensionSchema<?> r0 = r6.extensionSchema
            int r0 = r0.extensionNumber(r14)
            if (r0 > r5) goto L_0x0090
            com.google.protobuf.ExtensionSchema<?> r0 = r6.extensionSchema
            r0.serializeExtension(r8, r14)
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x008e
            java.lang.Object r0 = r10.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r14 = r0
            goto L_0x0071
        L_0x008e:
            r14 = 0
            goto L_0x0071
        L_0x0090:
            long r2 = offset(r3)
            switch(r4) {
                case 0: goto L_0x05f5;
                case 1: goto L_0x05d5;
                case 2: goto L_0x05b5;
                case 3: goto L_0x0594;
                case 4: goto L_0x0573;
                case 5: goto L_0x0552;
                case 6: goto L_0x0531;
                case 7: goto L_0x0510;
                case 8: goto L_0x04ef;
                case 9: goto L_0x04ca;
                case 10: goto L_0x04a7;
                case 11: goto L_0x0486;
                case 12: goto L_0x0465;
                case 13: goto L_0x0444;
                case 14: goto L_0x0423;
                case 15: goto L_0x0402;
                case 16: goto L_0x03e1;
                case 17: goto L_0x03bb;
                case 18: goto L_0x03a5;
                case 19: goto L_0x0396;
                case 20: goto L_0x0387;
                case 21: goto L_0x0378;
                case 22: goto L_0x0369;
                case 23: goto L_0x035a;
                case 24: goto L_0x034b;
                case 25: goto L_0x033c;
                case 26: goto L_0x032d;
                case 27: goto L_0x031a;
                case 28: goto L_0x030b;
                case 29: goto L_0x02fb;
                case 30: goto L_0x02eb;
                case 31: goto L_0x02db;
                case 32: goto L_0x02cb;
                case 33: goto L_0x02bb;
                case 34: goto L_0x02ab;
                case 35: goto L_0x029b;
                case 36: goto L_0x028b;
                case 37: goto L_0x027b;
                case 38: goto L_0x026b;
                case 39: goto L_0x025b;
                case 40: goto L_0x024b;
                case 41: goto L_0x023b;
                case 42: goto L_0x022b;
                case 43: goto L_0x021b;
                case 44: goto L_0x020b;
                case 45: goto L_0x01fb;
                case 46: goto L_0x01eb;
                case 47: goto L_0x01db;
                case 48: goto L_0x01cb;
                case 49: goto L_0x01b8;
                case 50: goto L_0x01af;
                case 51: goto L_0x01a0;
                case 52: goto L_0x0191;
                case 53: goto L_0x0182;
                case 54: goto L_0x0173;
                case 55: goto L_0x0164;
                case 56: goto L_0x0155;
                case 57: goto L_0x0146;
                case 58: goto L_0x0137;
                case 59: goto L_0x0128;
                case 60: goto L_0x0115;
                case 61: goto L_0x0105;
                case 62: goto L_0x00f7;
                case 63: goto L_0x00e9;
                case 64: goto L_0x00db;
                case 65: goto L_0x00cd;
                case 66: goto L_0x00bf;
                case 67: goto L_0x00b1;
                case 68: goto L_0x009f;
                default: goto L_0x0097;
            }
        L_0x0097:
            r20 = r11
            r16 = r14
            r19 = 0
            goto L_0x0614
        L_0x009f:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            java.lang.Object r0 = r12.getObject(r7, r2)
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r15)
            r8.writeGroup(r5, r0, r1)
            goto L_0x0097
        L_0x00b1:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            long r0 = oneofLongAt(r7, r2)
            r8.writeSInt64(r5, r0)
            goto L_0x0097
        L_0x00bf:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            int r0 = oneofIntAt(r7, r2)
            r8.writeSInt32(r5, r0)
            goto L_0x0097
        L_0x00cd:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            long r0 = oneofLongAt(r7, r2)
            r8.writeSFixed64(r5, r0)
            goto L_0x0097
        L_0x00db:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            int r0 = oneofIntAt(r7, r2)
            r8.writeSFixed32(r5, r0)
            goto L_0x0097
        L_0x00e9:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            int r0 = oneofIntAt(r7, r2)
            r8.writeEnum(r5, r0)
            goto L_0x0097
        L_0x00f7:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            int r0 = oneofIntAt(r7, r2)
            r8.writeUInt32(r5, r0)
            goto L_0x0097
        L_0x0105:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            java.lang.Object r0 = r12.getObject(r7, r2)
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            r8.writeBytes(r5, r0)
            goto L_0x0097
        L_0x0115:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            java.lang.Object r0 = r12.getObject(r7, r2)
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r15)
            r8.writeMessage(r5, r0, r1)
            goto L_0x0097
        L_0x0128:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            java.lang.Object r0 = r12.getObject(r7, r2)
            r6.writeString(r5, r0, r8)
            goto L_0x0097
        L_0x0137:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            boolean r0 = oneofBooleanAt(r7, r2)
            r8.writeBool(r5, r0)
            goto L_0x0097
        L_0x0146:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            int r0 = oneofIntAt(r7, r2)
            r8.writeFixed32(r5, r0)
            goto L_0x0097
        L_0x0155:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            long r0 = oneofLongAt(r7, r2)
            r8.writeFixed64(r5, r0)
            goto L_0x0097
        L_0x0164:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            int r0 = oneofIntAt(r7, r2)
            r8.writeInt32(r5, r0)
            goto L_0x0097
        L_0x0173:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            long r0 = oneofLongAt(r7, r2)
            r8.writeUInt64(r5, r0)
            goto L_0x0097
        L_0x0182:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            long r0 = oneofLongAt(r7, r2)
            r8.writeInt64(r5, r0)
            goto L_0x0097
        L_0x0191:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            float r0 = oneofFloatAt(r7, r2)
            r8.writeFloat(r5, r0)
            goto L_0x0097
        L_0x01a0:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0097
            double r0 = oneofDoubleAt(r7, r2)
            r8.writeDouble(r5, r0)
            goto L_0x0097
        L_0x01af:
            java.lang.Object r0 = r12.getObject(r7, r2)
            r6.writeMapHelper(r8, r5, r0, r15)
            goto L_0x0097
        L_0x01b8:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.Schema r2 = r6.getMessageFieldSchema(r15)
            com.google.protobuf.SchemaUtil.writeGroupList(r0, r1, r8, r2)
            goto L_0x0097
        L_0x01cb:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r4 = 1
            com.google.protobuf.SchemaUtil.writeSInt64List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x01db:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeSInt32List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x01eb:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeSFixed64List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x01fb:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeSFixed32List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x020b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeEnumList(r0, r1, r8, r4)
            goto L_0x0097
        L_0x021b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeUInt32List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x022b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeBoolList(r0, r1, r8, r4)
            goto L_0x0097
        L_0x023b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeFixed32List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x024b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeFixed64List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x025b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeInt32List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x026b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeUInt64List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x027b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeInt64List(r0, r1, r8, r4)
            goto L_0x0097
        L_0x028b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeFloatList(r0, r1, r8, r4)
            goto L_0x0097
        L_0x029b:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeDoubleList(r0, r1, r8, r4)
            goto L_0x0097
        L_0x02ab:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r4 = 0
            com.google.protobuf.SchemaUtil.writeSInt64List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x02bb:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeSInt32List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x02cb:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeSFixed64List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x02db:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeSFixed32List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x02eb:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeEnumList(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x02fb:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeUInt32List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x030b:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeBytesList(r0, r1, r8)
            goto L_0x0097
        L_0x031a:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.Schema r2 = r6.getMessageFieldSchema(r15)
            com.google.protobuf.SchemaUtil.writeMessageList(r0, r1, r8, r2)
            goto L_0x0097
        L_0x032d:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeStringList(r0, r1, r8)
            goto L_0x0097
        L_0x033c:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r4 = 0
            com.google.protobuf.SchemaUtil.writeBoolList(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x034b:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeFixed32List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x035a:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeFixed64List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x0369:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeInt32List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x0378:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeUInt64List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x0387:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeInt64List(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x0396:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeFloatList(r0, r1, r8, r4)
            goto L_0x03b3
        L_0x03a5:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.protobuf.SchemaUtil.writeDoubleList(r0, r1, r8, r4)
        L_0x03b3:
            r19 = r4
            r20 = r11
            r16 = r14
            goto L_0x0614
        L_0x03bb:
            r4 = 0
            r0 = r21
            r1 = r22
            r16 = r14
            r13 = r2
            r2 = r15
            r3 = r9
            r19 = r4
            r4 = r17
            r20 = r11
            r11 = r5
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            java.lang.Object r0 = r12.getObject(r7, r13)
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r15)
            r8.writeGroup(r11, r0, r1)
            goto L_0x0614
        L_0x03e1:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            long r0 = r12.getLong(r7, r13)
            r8.writeSInt64(r11, r0)
            goto L_0x0614
        L_0x0402:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            int r0 = r12.getInt(r7, r13)
            r8.writeSInt32(r11, r0)
            goto L_0x0614
        L_0x0423:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            long r0 = r12.getLong(r7, r13)
            r8.writeSFixed64(r11, r0)
            goto L_0x0614
        L_0x0444:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            int r0 = r12.getInt(r7, r13)
            r8.writeSFixed32(r11, r0)
            goto L_0x0614
        L_0x0465:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            int r0 = r12.getInt(r7, r13)
            r8.writeEnum(r11, r0)
            goto L_0x0614
        L_0x0486:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            int r0 = r12.getInt(r7, r13)
            r8.writeUInt32(r11, r0)
            goto L_0x0614
        L_0x04a7:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            java.lang.Object r0 = r12.getObject(r7, r13)
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            r8.writeBytes(r11, r0)
            goto L_0x0614
        L_0x04ca:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            java.lang.Object r0 = r12.getObject(r7, r13)
            com.google.protobuf.Schema r1 = r6.getMessageFieldSchema(r15)
            r8.writeMessage(r11, r0, r1)
            goto L_0x0614
        L_0x04ef:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            java.lang.Object r0 = r12.getObject(r7, r13)
            r6.writeString(r11, r0, r8)
            goto L_0x0614
        L_0x0510:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            boolean r0 = booleanAt(r7, r13)
            r8.writeBool(r11, r0)
            goto L_0x0614
        L_0x0531:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            int r0 = r12.getInt(r7, r13)
            r8.writeFixed32(r11, r0)
            goto L_0x0614
        L_0x0552:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            long r0 = r12.getLong(r7, r13)
            r8.writeFixed64(r11, r0)
            goto L_0x0614
        L_0x0573:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            int r0 = r12.getInt(r7, r13)
            r8.writeInt32(r11, r0)
            goto L_0x0614
        L_0x0594:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            long r0 = r12.getLong(r7, r13)
            r8.writeUInt64(r11, r0)
            goto L_0x0614
        L_0x05b5:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            long r0 = r12.getLong(r7, r13)
            r8.writeInt64(r11, r0)
            goto L_0x0614
        L_0x05d5:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            float r0 = floatAt(r7, r13)
            r8.writeFloat(r11, r0)
            goto L_0x0614
        L_0x05f5:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0614
            double r0 = doubleAt(r7, r13)
            r8.writeDouble(r11, r0)
        L_0x0614:
            int r15 = r15 + 3
            r0 = r9
            r1 = r16
            r2 = r17
            r11 = r20
            r13 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x002f
        L_0x0622:
            r17 = r1
        L_0x0624:
            if (r1 == 0) goto L_0x063b
            com.google.protobuf.ExtensionSchema<?> r0 = r6.extensionSchema
            r0.serializeExtension(r8, r1)
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0639
            java.lang.Object r0 = r10.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r1 = r0
            goto L_0x0624
        L_0x0639:
            r1 = 0
            goto L_0x0624
        L_0x063b:
            com.google.protobuf.UnknownFieldSchema<?, ?> r0 = r6.unknownFieldSchema
            r6.writeUnknownInMessageTo(r0, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.writeFieldsInAscendingOrder(java.lang.Object, com.google.protobuf.Writer):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:161:0x058e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeFieldsInDescendingOrder(T r11, com.google.protobuf.Writer r12) throws java.io.IOException {
        /*
            r10 = this;
            com.google.protobuf.UnknownFieldSchema<?, ?> r0 = r10.unknownFieldSchema
            r10.writeUnknownInMessageTo(r0, r11, r12)
            boolean r0 = r10.hasExtensions
            r1 = 0
            if (r0 == 0) goto L_0x0021
            com.google.protobuf.ExtensionSchema<?> r0 = r10.extensionSchema
            com.google.protobuf.FieldSet r0 = r0.getExtensions(r11)
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x0021
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            goto L_0x0023
        L_0x0021:
            r0 = r1
            r2 = r0
        L_0x0023:
            int[] r3 = r10.buffer
            int r3 = r3.length
            int r3 = r3 + -3
        L_0x0028:
            if (r3 < 0) goto L_0x058c
            int r4 = r10.typeAndOffsetAt(r3)
            int r5 = r10.numberAt(r3)
        L_0x0032:
            if (r2 == 0) goto L_0x0050
            com.google.protobuf.ExtensionSchema<?> r6 = r10.extensionSchema
            int r6 = r6.extensionNumber(r2)
            if (r6 <= r5) goto L_0x0050
            com.google.protobuf.ExtensionSchema<?> r6 = r10.extensionSchema
            r6.serializeExtension(r12, r2)
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x004e
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            goto L_0x0032
        L_0x004e:
            r2 = r1
            goto L_0x0032
        L_0x0050:
            int r6 = type(r4)
            r7 = 1
            r8 = 0
            switch(r6) {
                case 0: goto L_0x0577;
                case 1: goto L_0x0565;
                case 2: goto L_0x0553;
                case 3: goto L_0x0541;
                case 4: goto L_0x052f;
                case 5: goto L_0x051d;
                case 6: goto L_0x050b;
                case 7: goto L_0x04f8;
                case 8: goto L_0x04e5;
                case 9: goto L_0x04ce;
                case 10: goto L_0x04b9;
                case 11: goto L_0x04a6;
                case 12: goto L_0x0493;
                case 13: goto L_0x0480;
                case 14: goto L_0x046d;
                case 15: goto L_0x045a;
                case 16: goto L_0x0447;
                case 17: goto L_0x0430;
                case 18: goto L_0x041d;
                case 19: goto L_0x040a;
                case 20: goto L_0x03f7;
                case 21: goto L_0x03e4;
                case 22: goto L_0x03d1;
                case 23: goto L_0x03be;
                case 24: goto L_0x03ab;
                case 25: goto L_0x0398;
                case 26: goto L_0x0385;
                case 27: goto L_0x036e;
                case 28: goto L_0x035b;
                case 29: goto L_0x0348;
                case 30: goto L_0x0335;
                case 31: goto L_0x0322;
                case 32: goto L_0x030f;
                case 33: goto L_0x02fc;
                case 34: goto L_0x02e9;
                case 35: goto L_0x02d6;
                case 36: goto L_0x02c3;
                case 37: goto L_0x02b0;
                case 38: goto L_0x029d;
                case 39: goto L_0x028a;
                case 40: goto L_0x0277;
                case 41: goto L_0x0264;
                case 42: goto L_0x0251;
                case 43: goto L_0x023e;
                case 44: goto L_0x022b;
                case 45: goto L_0x0218;
                case 46: goto L_0x0205;
                case 47: goto L_0x01f2;
                case 48: goto L_0x01df;
                case 49: goto L_0x01c8;
                case 50: goto L_0x01bb;
                case 51: goto L_0x01a8;
                case 52: goto L_0x0195;
                case 53: goto L_0x0182;
                case 54: goto L_0x016f;
                case 55: goto L_0x015c;
                case 56: goto L_0x0149;
                case 57: goto L_0x0136;
                case 58: goto L_0x0123;
                case 59: goto L_0x0110;
                case 60: goto L_0x00f9;
                case 61: goto L_0x00e4;
                case 62: goto L_0x00d1;
                case 63: goto L_0x00be;
                case 64: goto L_0x00ab;
                case 65: goto L_0x0098;
                case 66: goto L_0x0085;
                case 67: goto L_0x0072;
                case 68: goto L_0x005b;
                default: goto L_0x0059;
            }
        L_0x0059:
            goto L_0x0588
        L_0x005b:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            com.google.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            r12.writeGroup(r5, r4, r6)
            goto L_0x0588
        L_0x0072:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeSInt64(r5, r6)
            goto L_0x0588
        L_0x0085:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeSInt32(r5, r4)
            goto L_0x0588
        L_0x0098:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeSFixed64(r5, r6)
            goto L_0x0588
        L_0x00ab:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeSFixed32(r5, r4)
            goto L_0x0588
        L_0x00be:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeEnum(r5, r4)
            goto L_0x0588
        L_0x00d1:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeUInt32(r5, r4)
            goto L_0x0588
        L_0x00e4:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            com.google.protobuf.ByteString r4 = (com.google.protobuf.ByteString) r4
            r12.writeBytes(r5, r4)
            goto L_0x0588
        L_0x00f9:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            com.google.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            r12.writeMessage(r5, r4, r6)
            goto L_0x0588
        L_0x0110:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            r10.writeString(r5, r4, r12)
            goto L_0x0588
        L_0x0123:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            boolean r4 = oneofBooleanAt(r11, r6)
            r12.writeBool(r5, r4)
            goto L_0x0588
        L_0x0136:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeFixed32(r5, r4)
            goto L_0x0588
        L_0x0149:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeFixed64(r5, r6)
            goto L_0x0588
        L_0x015c:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeInt32(r5, r4)
            goto L_0x0588
        L_0x016f:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeUInt64(r5, r6)
            goto L_0x0588
        L_0x0182:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeInt64(r5, r6)
            goto L_0x0588
        L_0x0195:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            float r4 = oneofFloatAt(r11, r6)
            r12.writeFloat(r5, r4)
            goto L_0x0588
        L_0x01a8:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            double r6 = oneofDoubleAt(r11, r6)
            r12.writeDouble(r5, r6)
            goto L_0x0588
        L_0x01bb:
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            r10.writeMapHelper(r12, r5, r4, r3)
            goto L_0x0588
        L_0x01c8:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            com.google.protobuf.SchemaUtil.writeGroupList(r5, r4, r12, r6)
            goto L_0x0588
        L_0x01df:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeSInt64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x01f2:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeSInt32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0205:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeSFixed64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0218:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeSFixed32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x022b:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeEnumList(r5, r4, r12, r7)
            goto L_0x0588
        L_0x023e:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeUInt32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0251:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeBoolList(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0264:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeFixed32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0277:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeFixed64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x028a:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeInt32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x029d:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeUInt64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x02b0:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeInt64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x02c3:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeFloatList(r5, r4, r12, r7)
            goto L_0x0588
        L_0x02d6:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r8)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeDoubleList(r5, r4, r12, r7)
            goto L_0x0588
        L_0x02e9:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeSInt64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x02fc:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeSInt32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x030f:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeSFixed64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0322:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeSFixed32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0335:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeEnumList(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0348:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeUInt32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x035b:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeBytesList(r5, r4, r12)
            goto L_0x0588
        L_0x036e:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            com.google.protobuf.SchemaUtil.writeMessageList(r5, r4, r12, r6)
            goto L_0x0588
        L_0x0385:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeStringList(r5, r4, r12)
            goto L_0x0588
        L_0x0398:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeBoolList(r5, r4, r12, r8)
            goto L_0x0588
        L_0x03ab:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeFixed32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x03be:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeFixed64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x03d1:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeInt32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x03e4:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeUInt64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x03f7:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeInt64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x040a:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeFloatList(r5, r4, r12, r8)
            goto L_0x0588
        L_0x041d:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            java.util.List r4 = (java.util.List) r4
            com.google.protobuf.SchemaUtil.writeDoubleList(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0430:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            com.google.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            r12.writeGroup(r5, r4, r6)
            goto L_0x0588
        L_0x0447:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeSInt64(r5, r6)
            goto L_0x0588
        L_0x045a:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeSInt32(r5, r4)
            goto L_0x0588
        L_0x046d:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeSFixed64(r5, r6)
            goto L_0x0588
        L_0x0480:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeSFixed32(r5, r4)
            goto L_0x0588
        L_0x0493:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeEnum(r5, r4)
            goto L_0x0588
        L_0x04a6:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeUInt32(r5, r4)
            goto L_0x0588
        L_0x04b9:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            com.google.protobuf.ByteString r4 = (com.google.protobuf.ByteString) r4
            r12.writeBytes(r5, r4)
            goto L_0x0588
        L_0x04ce:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            com.google.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            r12.writeMessage(r5, r4, r6)
            goto L_0x0588
        L_0x04e5:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r11, (long) r6)
            r10.writeString(r5, r4, r12)
            goto L_0x0588
        L_0x04f8:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            boolean r4 = booleanAt(r11, r6)
            r12.writeBool(r5, r4)
            goto L_0x0588
        L_0x050b:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeFixed32(r5, r4)
            goto L_0x0588
        L_0x051d:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeFixed64(r5, r6)
            goto L_0x0588
        L_0x052f:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeInt32(r5, r4)
            goto L_0x0588
        L_0x0541:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeUInt64(r5, r6)
            goto L_0x0588
        L_0x0553:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeInt64(r5, r6)
            goto L_0x0588
        L_0x0565:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            float r4 = floatAt(r11, r6)
            r12.writeFloat(r5, r4)
            goto L_0x0588
        L_0x0577:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            double r6 = doubleAt(r11, r6)
            r12.writeDouble(r5, r6)
        L_0x0588:
            int r3 = r3 + -3
            goto L_0x0028
        L_0x058c:
            if (r2 == 0) goto L_0x05a3
            com.google.protobuf.ExtensionSchema<?> r11 = r10.extensionSchema
            r11.serializeExtension(r12, r2)
            boolean r11 = r0.hasNext()
            if (r11 == 0) goto L_0x05a1
            java.lang.Object r11 = r0.next()
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11
            r2 = r11
            goto L_0x058c
        L_0x05a1:
            r2 = r1
            goto L_0x058c
        L_0x05a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.writeFieldsInDescendingOrder(java.lang.Object, com.google.protobuf.Writer):void");
    }

    private <K, V> void writeMapHelper(Writer writer, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            writer.writeMap(i, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> unknownFieldSchema2, T t, Writer writer) throws IOException {
        unknownFieldSchema2.writeTo(unknownFieldSchema2.getFromMessage(t), writer);
    }

    public void mergeFrom(T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        extensionRegistryLite.getClass();
        checkMutable(t);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t, reader, extensionRegistryLite);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:94|93|153|169|170|(0)(0)|191|214|206) */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x069a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x018f, code lost:
        r13 = r5;
        r14 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0242, code lost:
        r15 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:169:0x063c */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0642 A[Catch:{ all -> 0x069a }] */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0668  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x06ab A[LOOP:6: B:200:0x06a7->B:202:0x06ab, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x06c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <UT, UB, ET extends com.google.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(com.google.protobuf.UnknownFieldSchema<UT, UB> r19, com.google.protobuf.ExtensionSchema<ET> r20, T r21, com.google.protobuf.Reader r22, com.google.protobuf.ExtensionRegistryLite r23) throws java.io.IOException {
        /*
            r18 = this;
            r8 = r18
            r7 = r19
            r15 = r21
            r0 = r22
            r6 = r23
            r17 = 0
            r5 = r17
            r9 = r5
        L_0x000f:
            int r2 = r22.getFieldNumber()     // Catch:{ all -> 0x069f }
            int r3 = r8.positionForFieldNumber(r2)     // Catch:{ all -> 0x069f }
            r10 = 0
            if (r3 >= 0) goto L_0x00b4
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r2 != r1) goto L_0x003f
            int r0 = r8.checkInitializedCount
            r4 = r5
        L_0x0022:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x0039
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r18
            r2 = r21
            r5 = r19
            r6 = r21
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x0022
        L_0x0039:
            if (r4 == 0) goto L_0x003e
            r7.setBuilderToMessage(r15, r4)
        L_0x003e:
            return
        L_0x003f:
            boolean r1 = r8.hasExtensions     // Catch:{ all -> 0x00b1 }
            if (r1 != 0) goto L_0x0048
            r4 = r20
            r12 = r17
            goto L_0x0051
        L_0x0048:
            com.google.protobuf.MessageLite r1 = r8.defaultInstance     // Catch:{ all -> 0x00b1 }
            r4 = r20
            java.lang.Object r1 = r4.findExtensionByNumber(r6, r1, r2)     // Catch:{ all -> 0x00b1 }
            r12 = r1
        L_0x0051:
            if (r12 == 0) goto L_0x006e
            if (r9 != 0) goto L_0x0059
            com.google.protobuf.FieldSet r9 = r20.getMutableExtensions(r21)     // Catch:{ all -> 0x00b1 }
        L_0x0059:
            r1 = r9
            r9 = r20
            r10 = r21
            r11 = r22
            r13 = r23
            r14 = r1
            r3 = r15
            r15 = r5
            r16 = r19
            java.lang.Object r5 = r9.parseExtension(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x00ad }
            r9 = r1
        L_0x006c:
            r15 = r3
            goto L_0x000f
        L_0x006e:
            r3 = r15
            boolean r1 = r7.shouldDiscardUnknownFields(r0)     // Catch:{ all -> 0x00ad }
            if (r1 == 0) goto L_0x007c
            boolean r1 = r22.skipField()     // Catch:{ all -> 0x00ad }
            if (r1 == 0) goto L_0x0089
        L_0x007b:
            goto L_0x006c
        L_0x007c:
            if (r5 != 0) goto L_0x0082
            java.lang.Object r5 = r7.getBuilderFromMessage(r3)     // Catch:{ all -> 0x00ad }
        L_0x0082:
            boolean r1 = r7.mergeOneFieldFrom(r5, r0, r10)     // Catch:{ all -> 0x00ad }
            if (r1 == 0) goto L_0x0089
            goto L_0x007b
        L_0x0089:
            int r0 = r8.checkInitializedCount
            r4 = r5
        L_0x008c:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x00a6
            int[] r1 = r8.intArray
            r5 = r1[r0]
            r1 = r18
            r2 = r21
            r11 = r3
            r3 = r5
            r5 = r19
            r6 = r21
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            r3 = r11
            goto L_0x008c
        L_0x00a6:
            r11 = r3
            if (r4 == 0) goto L_0x00ac
            r7.setBuilderToMessage(r11, r4)
        L_0x00ac:
            return
        L_0x00ad:
            r0 = move-exception
            r11 = r3
            goto L_0x06a2
        L_0x00b1:
            r0 = move-exception
            goto L_0x06a1
        L_0x00b4:
            r4 = r20
            r11 = r15
            int r12 = r8.typeAndOffsetAt(r3)     // Catch:{ all -> 0x069c }
            int r1 = type(r12)     // Catch:{ InvalidWireTypeException -> 0x0639 }
            switch(r1) {
                case 0: goto L_0x05f8;
                case 1: goto L_0x05e6;
                case 2: goto L_0x05d4;
                case 3: goto L_0x05c2;
                case 4: goto L_0x05b0;
                case 5: goto L_0x059d;
                case 6: goto L_0x058a;
                case 7: goto L_0x0577;
                case 8: goto L_0x056c;
                case 9: goto L_0x0557;
                case 10: goto L_0x0544;
                case 11: goto L_0x0531;
                case 12: goto L_0x050b;
                case 13: goto L_0x04f8;
                case 14: goto L_0x04e5;
                case 15: goto L_0x04d2;
                case 16: goto L_0x04bf;
                case 17: goto L_0x04aa;
                case 18: goto L_0x0498;
                case 19: goto L_0x0486;
                case 20: goto L_0x0474;
                case 21: goto L_0x0462;
                case 22: goto L_0x0450;
                case 23: goto L_0x043e;
                case 24: goto L_0x042c;
                case 25: goto L_0x041a;
                case 26: goto L_0x0412;
                case 27: goto L_0x03fd;
                case 28: goto L_0x03eb;
                case 29: goto L_0x03d9;
                case 30: goto L_0x03b8;
                case 31: goto L_0x03a6;
                case 32: goto L_0x0394;
                case 33: goto L_0x0382;
                case 34: goto L_0x0370;
                case 35: goto L_0x035e;
                case 36: goto L_0x034c;
                case 37: goto L_0x033a;
                case 38: goto L_0x0328;
                case 39: goto L_0x0316;
                case 40: goto L_0x0304;
                case 41: goto L_0x02f2;
                case 42: goto L_0x02e0;
                case 43: goto L_0x02ce;
                case 44: goto L_0x02ad;
                case 45: goto L_0x029b;
                case 46: goto L_0x0289;
                case 47: goto L_0x0277;
                case 48: goto L_0x0265;
                case 49: goto L_0x0245;
                case 50: goto L_0x0230;
                case 51: goto L_0x0219;
                case 52: goto L_0x0205;
                case 53: goto L_0x01f2;
                case 54: goto L_0x01df;
                case 55: goto L_0x01cc;
                case 56: goto L_0x01b9;
                case 57: goto L_0x01a6;
                case 58: goto L_0x0193;
                case 59: goto L_0x0189;
                case 60: goto L_0x0178;
                case 61: goto L_0x0169;
                case 62: goto L_0x0156;
                case 63: goto L_0x012e;
                case 64: goto L_0x011b;
                case 65: goto L_0x0107;
                case 66: goto L_0x00f3;
                case 67: goto L_0x00df;
                case 68: goto L_0x00cd;
                default: goto L_0x00c2;
            }
        L_0x00c2:
            r13 = r5
            r14 = r6
            r15 = r7
            if (r13 != 0) goto L_0x0612
            java.lang.Object r5 = r15.getBuilderFromMessage(r11)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0613
        L_0x00cd:
            java.lang.Object r1 = r8.mutableOneofMessageFieldForMerge(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.MessageLite r1 = (com.google.protobuf.MessageLite) r1     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.Schema r12 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r0.mergeGroupField(r1, r12, r6)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.storeOneofMessageField(r11, r2, r3, r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x00df:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            long r14 = r22.readSInt64()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x00f3:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            int r1 = r22.readSInt32()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x0107:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            long r14 = r22.readSFixed64()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x011b:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            int r1 = r22.readSFixed32()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x012e:
            int r1 = r22.readEnum()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.Internal$EnumVerifier r13 = r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            if (r13 == 0) goto L_0x0147
            boolean r13 = r13.isInRange(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            if (r13 == 0) goto L_0x013f
            goto L_0x0147
        L_0x013f:
            java.lang.Object r5 = com.google.protobuf.SchemaUtil.storeUnknownEnum(r11, r2, r1, r5, r7)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r14 = r6
            r15 = r7
            goto L_0x0695
        L_0x0147:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x0156:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            int r1 = r22.readUInt32()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x0169:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.ByteString r1 = r22.readBytes()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x0178:
            java.lang.Object r1 = r8.mutableOneofMessageFieldForMerge(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.MessageLite r1 = (com.google.protobuf.MessageLite) r1     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.Schema r12 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r0.mergeMessageField(r1, r12, r6)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.storeOneofMessageField(r11, r2, r3, r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x0189:
            r8.readString(r11, r12, r0)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
        L_0x018f:
            r13 = r5
            r14 = r6
            goto L_0x0242
        L_0x0193:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            boolean r1 = r22.readBool()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x01a6:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            int r1 = r22.readFixed32()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x01b9:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            long r14 = r22.readFixed64()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x01cc:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            int r1 = r22.readInt32()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x01df:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            long r14 = r22.readUInt64()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x01f2:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            long r14 = r22.readInt64()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x0205:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            float r1 = r22.readFloat()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x0219:
            long r12 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            double r14 = r22.readDouble()     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            java.lang.Double r1 = java.lang.Double.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r12, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x063a, all -> 0x022d }
            goto L_0x018f
        L_0x022d:
            r0 = move-exception
            goto L_0x06a2
        L_0x0230:
            java.lang.Object r12 = r8.getMapFieldDefaultEntry(r3)     // Catch:{ InvalidWireTypeException -> 0x0639 }
            r1 = r18
            r2 = r21
            r4 = r12
            r13 = r5
            r5 = r23
            r14 = r6
            r6 = r22
            r1.mergeMap(r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x0262, all -> 0x025e }
        L_0x0242:
            r15 = r7
            goto L_0x0609
        L_0x0245:
            r13 = r5
            r14 = r6
            long r4 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0262, all -> 0x025e }
            com.google.protobuf.Schema r6 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x0262, all -> 0x025e }
            r1 = r18
            r2 = r21
            r3 = r4
            r5 = r22
            r15 = r7
            r7 = r23
            r1.readGroupList(r2, r3, r5, r6, r7)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x025e:
            r0 = move-exception
            r15 = r7
            goto L_0x060d
        L_0x0262:
            r15 = r7
            goto L_0x0610
        L_0x0265:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readSInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0277:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readSInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0289:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readSFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x029b:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readSFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x02ad:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r4 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r4 = r1.mutableListAt(r11, r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readEnumList(r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.Internal$EnumVerifier r5 = r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r1 = r21
            r3 = r4
            r4 = r5
            r5 = r13
            r6 = r19
            java.lang.Object r5 = com.google.protobuf.SchemaUtil.filterUnknownEnumList((java.lang.Object) r1, (int) r2, (java.util.List<java.lang.Integer>) r3, (com.google.protobuf.Internal.EnumVerifier) r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0695
        L_0x02ce:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readUInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x02e0:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readBoolList(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x02f2:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0304:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0316:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0328:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readUInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x033a:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x034c:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readFloatList(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x035e:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readDoubleList(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0370:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readSInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0382:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readSInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0394:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readSFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x03a6:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readSFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x03b8:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r4 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r4 = r1.mutableListAt(r11, r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readEnumList(r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.Internal$EnumVerifier r5 = r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r1 = r21
            r3 = r4
            r4 = r5
            r5 = r13
            r6 = r19
            java.lang.Object r5 = com.google.protobuf.SchemaUtil.filterUnknownEnumList((java.lang.Object) r1, (int) r2, (java.util.List<java.lang.Integer>) r3, (com.google.protobuf.Internal.EnumVerifier) r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0695
        L_0x03d9:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readUInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x03eb:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readBytesList(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x03fd:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.Schema r5 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r1 = r18
            r2 = r21
            r3 = r12
            r4 = r22
            r6 = r23
            r1.readMessageList(r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0412:
            r13 = r5
            r14 = r6
            r15 = r7
            r8.readStringList(r11, r12, r0)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x041a:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readBoolList(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x042c:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x043e:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0450:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0462:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readUInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0474:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0486:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readFloatList(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0498:
            r13 = r5
            r14 = r6
            r15 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r2 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.readDoubleList(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x04aa:
            r13 = r5
            r14 = r6
            r15 = r7
            java.lang.Object r1 = r8.mutableMessageFieldForMerge(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.MessageLite r1 = (com.google.protobuf.MessageLite) r1     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.Schema r2 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.mergeGroupField(r1, r2, r14)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.storeMessageField(r11, r3, r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x04bf:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r4 = r22.readSInt64()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r11, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x04d2:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            int r4 = r22.readSInt32()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r11, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x04e5:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r4 = r22.readSFixed64()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r11, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x04f8:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            int r4 = r22.readSFixed32()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r11, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x050b:
            r13 = r5
            r14 = r6
            r15 = r7
            int r1 = r22.readEnum()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.Internal$EnumVerifier r4 = r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            if (r4 == 0) goto L_0x0525
            boolean r4 = r4.isInRange(r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            if (r4 == 0) goto L_0x051f
            goto L_0x0525
        L_0x051f:
            java.lang.Object r5 = com.google.protobuf.SchemaUtil.storeUnknownEnum(r11, r2, r1, r13, r15)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0695
        L_0x0525:
            long r4 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r11, (long) r4, (int) r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0531:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            int r4 = r22.readUInt32()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r11, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0544:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.ByteString r4 = r22.readBytes()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r11, (long) r1, (java.lang.Object) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0557:
            r13 = r5
            r14 = r6
            r15 = r7
            java.lang.Object r1 = r8.mutableMessageFieldForMerge(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.MessageLite r1 = (com.google.protobuf.MessageLite) r1     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.Schema r2 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r0.mergeMessageField(r1, r2, r14)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.storeMessageField(r11, r3, r1)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x056c:
            r13 = r5
            r14 = r6
            r15 = r7
            r8.readString(r11, r12, r0)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x0577:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            boolean r4 = r22.readBool()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putBoolean((java.lang.Object) r11, (long) r1, (boolean) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x058a:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            int r4 = r22.readFixed32()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r11, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x059d:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r4 = r22.readFixed64()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r11, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x05b0:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            int r4 = r22.readInt32()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r11, (long) r1, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x05c2:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r4 = r22.readUInt64()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r11, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x05d4:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            long r4 = r22.readInt64()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r11, (long) r1, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x05e6:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            float r4 = r22.readFloat()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putFloat((java.lang.Object) r11, (long) r1, (float) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            goto L_0x0609
        L_0x05f8:
            r13 = r5
            r14 = r6
            r15 = r7
            long r1 = offset(r12)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            double r4 = r22.readDouble()     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            com.google.protobuf.UnsafeUtil.putDouble((java.lang.Object) r11, (long) r1, (double) r4)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x0610, all -> 0x060c }
        L_0x0609:
            r5 = r13
            goto L_0x0695
        L_0x060c:
            r0 = move-exception
        L_0x060d:
            r5 = r13
            goto L_0x06a3
        L_0x0610:
            r5 = r13
            goto L_0x063c
        L_0x0612:
            r5 = r13
        L_0x0613:
            boolean r1 = r15.mergeOneFieldFrom(r5, r0, r10)     // Catch:{ InvalidWireTypeException -> 0x063c }
            if (r1 != 0) goto L_0x0695
            int r0 = r8.checkInitializedCount
            r4 = r5
        L_0x061c:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x0633
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r18
            r2 = r21
            r5 = r19
            r6 = r21
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x061c
        L_0x0633:
            if (r4 == 0) goto L_0x0638
            r15.setBuilderToMessage(r11, r4)
        L_0x0638:
            return
        L_0x0639:
            r13 = r5
        L_0x063a:
            r14 = r6
            r15 = r7
        L_0x063c:
            boolean r1 = r15.shouldDiscardUnknownFields(r0)     // Catch:{ all -> 0x069a }
            if (r1 == 0) goto L_0x0668
            boolean r1 = r22.skipField()     // Catch:{ all -> 0x069a }
            if (r1 != 0) goto L_0x0695
            int r0 = r8.checkInitializedCount
            r4 = r5
        L_0x064b:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x0662
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r18
            r2 = r21
            r5 = r19
            r6 = r21
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x064b
        L_0x0662:
            if (r4 == 0) goto L_0x0667
            r15.setBuilderToMessage(r11, r4)
        L_0x0667:
            return
        L_0x0668:
            if (r5 != 0) goto L_0x066f
            java.lang.Object r1 = r15.getBuilderFromMessage(r11)     // Catch:{ all -> 0x069a }
            r5 = r1
        L_0x066f:
            boolean r1 = r15.mergeOneFieldFrom(r5, r0, r10)     // Catch:{ all -> 0x069a }
            if (r1 != 0) goto L_0x0695
            int r0 = r8.checkInitializedCount
            r4 = r5
        L_0x0678:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x068f
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r18
            r2 = r21
            r5 = r19
            r6 = r21
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x0678
        L_0x068f:
            if (r4 == 0) goto L_0x0694
            r15.setBuilderToMessage(r11, r4)
        L_0x0694:
            return
        L_0x0695:
            r6 = r14
            r7 = r15
            r15 = r11
            goto L_0x000f
        L_0x069a:
            r0 = move-exception
            goto L_0x06a3
        L_0x069c:
            r0 = move-exception
            r13 = r5
            goto L_0x06a2
        L_0x069f:
            r0 = move-exception
            r13 = r5
        L_0x06a1:
            r11 = r15
        L_0x06a2:
            r15 = r7
        L_0x06a3:
            int r1 = r8.checkInitializedCount
            r7 = r1
            r4 = r5
        L_0x06a7:
            int r1 = r8.repeatedFieldOffsetStart
            if (r7 >= r1) goto L_0x06be
            int[] r1 = r8.intArray
            r3 = r1[r7]
            r1 = r18
            r2 = r21
            r5 = r19
            r6 = r21
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r7 = r7 + 1
            goto L_0x06a7
        L_0x06be:
            if (r4 == 0) goto L_0x06c3
            r15.setBuilderToMessage(r11, r4)
        L_0x06c3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFromHelper(com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, java.lang.Object, com.google.protobuf.Reader, com.google.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = newInstance;
        return newInstance;
    }

    /* renamed from: com.google.protobuf.MessageSchema$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.WireFormat$FieldType[] r0 = com.google.protobuf.WireFormat.FieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.BOOL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.BYTES     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED32     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED32     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED64     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED64     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FLOAT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.ENUM     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT32     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT32     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT64     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x009c }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT64     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.MESSAGE     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT32     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT64     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.STRING     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return r3 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int decodeMapEntryValue(byte[] r2, int r3, int r4, com.google.protobuf.WireFormat.FieldType r5, java.lang.Class<?> r6, com.google.protobuf.ArrayDecoders.Registers r7) throws java.io.IOException {
        /*
            r1 = this;
            int[] r0 = com.google.protobuf.MessageSchema.AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType
            int r5 = r5.ordinal()
            r5 = r0[r5]
            switch(r5) {
                case 1: goto L_0x0099;
                case 2: goto L_0x0094;
                case 3: goto L_0x0087;
                case 4: goto L_0x007a;
                case 5: goto L_0x007a;
                case 6: goto L_0x006f;
                case 7: goto L_0x006f;
                case 8: goto L_0x0064;
                case 9: goto L_0x0057;
                case 10: goto L_0x0057;
                case 11: goto L_0x0057;
                case 12: goto L_0x004a;
                case 13: goto L_0x004a;
                case 14: goto L_0x003d;
                case 15: goto L_0x002b;
                case 16: goto L_0x0019;
                case 17: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.String r3 = "unsupported field type."
            r2.<init>(r3)
            throw r2
        L_0x0013:
            int r2 = com.google.protobuf.ArrayDecoders.decodeStringRequireUtf8(r2, r3, r7)
            goto L_0x00ae
        L_0x0019:
            int r2 = com.google.protobuf.ArrayDecoders.decodeVarint64(r2, r3, r7)
            long r3 = r7.long1
            long r3 = com.google.protobuf.CodedInputStream.decodeZigZag64(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r7.object1 = r3
            goto L_0x00ae
        L_0x002b:
            int r2 = com.google.protobuf.ArrayDecoders.decodeVarint32(r2, r3, r7)
            int r3 = r7.int1
            int r3 = com.google.protobuf.CodedInputStream.decodeZigZag32(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r7.object1 = r3
            goto L_0x00ae
        L_0x003d:
            com.google.protobuf.Protobuf r5 = com.google.protobuf.Protobuf.getInstance()
            com.google.protobuf.Schema r5 = r5.schemaFor(r6)
            int r2 = com.google.protobuf.ArrayDecoders.decodeMessageField(r5, r2, r3, r4, r7)
            goto L_0x00ae
        L_0x004a:
            int r2 = com.google.protobuf.ArrayDecoders.decodeVarint64(r2, r3, r7)
            long r3 = r7.long1
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r7.object1 = r3
            goto L_0x00ae
        L_0x0057:
            int r2 = com.google.protobuf.ArrayDecoders.decodeVarint32(r2, r3, r7)
            int r3 = r7.int1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r7.object1 = r3
            goto L_0x00ae
        L_0x0064:
            float r2 = com.google.protobuf.ArrayDecoders.decodeFloat(r2, r3)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r7.object1 = r2
            goto L_0x0084
        L_0x006f:
            long r4 = com.google.protobuf.ArrayDecoders.decodeFixed64(r2, r3)
            java.lang.Long r2 = java.lang.Long.valueOf(r4)
            r7.object1 = r2
            goto L_0x0091
        L_0x007a:
            int r2 = com.google.protobuf.ArrayDecoders.decodeFixed32(r2, r3)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.object1 = r2
        L_0x0084:
            int r2 = r3 + 4
            goto L_0x00ae
        L_0x0087:
            double r4 = com.google.protobuf.ArrayDecoders.decodeDouble(r2, r3)
            java.lang.Double r2 = java.lang.Double.valueOf(r4)
            r7.object1 = r2
        L_0x0091:
            int r2 = r3 + 8
            goto L_0x00ae
        L_0x0094:
            int r2 = com.google.protobuf.ArrayDecoders.decodeBytes(r2, r3, r7)
            goto L_0x00ae
        L_0x0099:
            int r2 = com.google.protobuf.ArrayDecoders.decodeVarint64(r2, r3, r7)
            long r3 = r7.long1
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x00a7
            r3 = 1
            goto L_0x00a8
        L_0x00a7:
            r3 = 0
        L_0x00a8:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r7.object1 = r3
        L_0x00ae:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.decodeMapEntryValue(byte[], int, int, com.google.protobuf.WireFormat$FieldType, java.lang.Class, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r0v6, types: [int, byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <K, V> int decodeMapEntry(byte[] r15, int r16, int r17, com.google.protobuf.MapEntryLite.Metadata<K, V> r18, java.util.Map<K, V> r19, com.google.protobuf.ArrayDecoders.Registers r20) throws java.io.IOException {
        /*
            r14 = this;
            r7 = r15
            r8 = r17
            r9 = r18
            r0 = r16
            r10 = r20
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r15, r0, r10)
            int r1 = r10.int1
            if (r1 < 0) goto L_0x0081
            int r2 = r8 - r0
            if (r1 > r2) goto L_0x0081
            int r11 = r0 + r1
            K r1 = r9.defaultKey
            V r2 = r9.defaultValue
            r12 = r1
            r13 = r2
        L_0x001d:
            if (r0 >= r11) goto L_0x0074
            int r1 = r0 + 1
            byte r0 = r7[r0]
            if (r0 >= 0) goto L_0x002e
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r0, r15, r1, r10)
            int r1 = r10.int1
            r2 = r0
            r0 = r1
            goto L_0x002f
        L_0x002e:
            r2 = r1
        L_0x002f:
            int r1 = r0 >>> 3
            r3 = r0 & 7
            r4 = 1
            if (r1 == r4) goto L_0x0057
            r4 = 2
            if (r1 == r4) goto L_0x003a
            goto L_0x006f
        L_0x003a:
            com.google.protobuf.WireFormat$FieldType r1 = r9.valueType
            int r1 = r1.getWireType()
            if (r3 != r1) goto L_0x006f
            com.google.protobuf.WireFormat$FieldType r4 = r9.valueType
            V r0 = r9.defaultValue
            java.lang.Class r5 = r0.getClass()
            r0 = r14
            r1 = r15
            r3 = r17
            r6 = r20
            int r0 = r0.decodeMapEntryValue(r1, r2, r3, r4, r5, r6)
            java.lang.Object r13 = r10.object1
            goto L_0x001d
        L_0x0057:
            com.google.protobuf.WireFormat$FieldType r1 = r9.keyType
            int r1 = r1.getWireType()
            if (r3 != r1) goto L_0x006f
            com.google.protobuf.WireFormat$FieldType r4 = r9.keyType
            r5 = 0
            r0 = r14
            r1 = r15
            r3 = r17
            r6 = r20
            int r0 = r0.decodeMapEntryValue(r1, r2, r3, r4, r5, r6)
            java.lang.Object r12 = r10.object1
            goto L_0x001d
        L_0x006f:
            int r0 = com.google.protobuf.ArrayDecoders.skipField(r0, r15, r2, r8, r10)
            goto L_0x001d
        L_0x0074:
            if (r0 != r11) goto L_0x007c
            r0 = r19
            r0.put(r12, r13)
            return r11
        L_0x007c:
            com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r0
        L_0x0081:
            com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.decodeMapEntry(byte[], int, int, com.google.protobuf.MapEntryLite$Metadata, java.util.Map, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    private int parseRepeatedField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, ArrayDecoders.Registers registers) throws IOException {
        int i8;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i5;
        int i11 = i6;
        long j3 = j2;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe.getObject(t, j3);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
            unsafe.putObject(t, j3, protobufList);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, i9, protobufList, registers2);
                }
                if (i10 == 1) {
                    return ArrayDecoders.decodeDoubleList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 19:
            case 36:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, i9, protobufList, registers2);
                }
                if (i10 == 5) {
                    return ArrayDecoders.decodeFloatList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, i9, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeVarint64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, i9, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, i9, protobufList, registers2);
                }
                if (i10 == 1) {
                    return ArrayDecoders.decodeFixed64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, i9, protobufList, registers2);
                }
                if (i10 == 5) {
                    return ArrayDecoders.decodeFixed32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 25:
            case 42:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, i9, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeBoolList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 26:
                if (i10 == 2) {
                    if ((j & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(i3, bArr, i, i2, protobufList, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 27:
                if (i10 == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(i11), i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 28:
                if (i10 == 2) {
                    return ArrayDecoders.decodeBytesList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 30:
            case 44:
                if (i10 == 2) {
                    i8 = ArrayDecoders.decodePackedVarint32List(bArr, i9, protobufList, registers2);
                } else if (i10 == 0) {
                    i8 = ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                SchemaUtil.filterUnknownEnumList((Object) t, i4, (List<Integer>) protobufList, getEnumFieldVerifier(i11), null, this.unknownFieldSchema);
                return i8;
            case 33:
            case 47:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedSInt32List(bArr, i9, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeSInt32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 34:
            case 48:
                if (i10 == 2) {
                    return ArrayDecoders.decodePackedSInt64List(bArr, i9, protobufList, registers2);
                }
                if (i10 == 0) {
                    return ArrayDecoders.decodeSInt64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                if (i10 == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(i11), i3, bArr, i, i2, protobufList, registers);
                }
                break;
        }
        return i9;
    }

    private <K, V> int parseMapField(T t, byte[] bArr, int i, int i2, int i3, long j, ArrayDecoders.Registers registers) throws IOException {
        T t2 = t;
        long j2 = j;
        Unsafe unsafe = UNSAFE;
        int i4 = i3;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i3);
        Object object = unsafe.getObject(t, j2);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            unsafe.putObject(t, j2, newMapField);
            object = newMapField;
        }
        return decodeMapEntry(bArr, i, i2, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, ArrayDecoders.Registers registers) throws IOException {
        T t2 = t;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        long j2 = j;
        int i13 = i8;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe = UNSAFE;
        long j3 = (long) (this.buffer[i13 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Double.valueOf(ArrayDecoders.decodeDouble(bArr, i)));
                    int i14 = i9 + 8;
                    unsafe.putInt(t2, j3, i11);
                    return i14;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Float.valueOf(ArrayDecoders.decodeFloat(bArr, i)));
                    int i15 = i9 + 4;
                    unsafe.putInt(t2, j3, i11);
                    return i15;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Long.valueOf(registers2.long1));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint64;
                }
                break;
            case 55:
            case Elf64.Ehdr.E_SHSTRNDX /*62*/:
                if (i12 == 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Integer.valueOf(registers2.int1));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint32;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i)));
                    int i16 = i9 + 8;
                    unsafe.putInt(t2, j3, i11);
                    return i16;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i)));
                    int i17 = i9 + 4;
                    unsafe.putInt(t2, j3, i11);
                    return i17;
                }
                break;
            case Elf64.Ehdr.E_SHENTSIZE /*58*/:
                if (i12 == 0) {
                    int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Boolean.valueOf(registers2.long1 != 0));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint642;
                }
                break;
            case 59:
                if (i12 == 2) {
                    int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr2, i9, registers2);
                    int i18 = registers2.int1;
                    if (i18 == 0) {
                        unsafe.putObject(t2, j2, "");
                    } else if ((i6 & 536870912) == 0 || Utf8.isValidUtf8(bArr2, decodeVarint322, decodeVarint322 + i18)) {
                        unsafe.putObject(t2, j2, new String(bArr2, decodeVarint322, i18, Internal.UTF_8));
                        decodeVarint322 += i18;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint322;
                }
                break;
            case 60:
                if (i12 == 2) {
                    Object mutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(t2, i11, i13);
                    int mergeMessageField = ArrayDecoders.mergeMessageField(mutableOneofMessageFieldForMerge, getMessageFieldSchema(i13), bArr, i, i2, registers);
                    storeOneofMessageField(t2, i11, i13, mutableOneofMessageFieldForMerge);
                    return mergeMessageField;
                }
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /*61*/:
                if (i12 == 2) {
                    int decodeBytes = ArrayDecoders.decodeBytes(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, registers2.object1);
                    unsafe.putInt(t2, j3, i11);
                    return decodeBytes;
                }
                break;
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
                if (i12 == 0) {
                    int decodeVarint323 = ArrayDecoders.decodeVarint32(bArr2, i9, registers2);
                    int i19 = registers2.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i13);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i19)) {
                        unsafe.putObject(t2, j2, Integer.valueOf(i19));
                        unsafe.putInt(t2, j3, i11);
                    } else {
                        getMutableUnknownFields(t).storeField(i10, Long.valueOf((long) i19));
                    }
                    return decodeVarint323;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                if (i12 == 0) {
                    int decodeVarint324 = ArrayDecoders.decodeVarint32(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Integer.valueOf(CodedInputStream.decodeZigZag32(registers2.int1)));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint324;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr2, i9, registers2);
                    unsafe.putObject(t2, j2, Long.valueOf(CodedInputStream.decodeZigZag64(registers2.long1)));
                    unsafe.putInt(t2, j3, i11);
                    return decodeVarint643;
                }
                break;
            case 68:
                if (i12 == 3) {
                    Object mutableOneofMessageFieldForMerge2 = mutableOneofMessageFieldForMerge(t2, i11, i13);
                    int mergeGroupField = ArrayDecoders.mergeGroupField(mutableOneofMessageFieldForMerge2, getMessageFieldSchema(i13), bArr, i, i2, (i10 & -8) | 4, registers);
                    storeOneofMessageField(t2, i11, i13, mutableOneofMessageFieldForMerge2);
                    return mergeGroupField;
                }
                break;
        }
        return i9;
    }

    private Schema getMessageFieldSchema(int i) {
        int i2 = (i / 3) * 2;
        Schema schema = (Schema) this.objects[i2];
        if (schema != null) {
            return schema;
        }
        Schema schemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i2 + 1]);
        this.objects[i2] = schemaFor;
        return schemaFor;
    }

    private Object getMapFieldDefaultEntry(int i) {
        return this.objects[(i / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i) {
        return (Internal.EnumVerifier) this.objects[((i / 3) * 2) + 1];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.google.protobuf.UnknownFieldSetLite} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v29, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0337, code lost:
        if (r0 != r15) goto L_0x0339;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x037c, code lost:
        if (r0 != r15) goto L_0x0339;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x039f, code lost:
        if (r0 != r15) goto L_0x0339;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x03a2, code lost:
        r8 = r32;
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0252, code lost:
        r5 = r12 | r17;
        r11 = r32;
        r2 = r6;
        r12 = r7;
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x026e, code lost:
        r0 = r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0288, code lost:
        r5 = r12 | r17;
        r11 = r32;
        r2 = r6;
        r12 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x028e, code lost:
        r3 = r13;
        r6 = r22;
        r1 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0295, code lost:
        r8 = r32;
        r2 = r3;
        r20 = r6;
        r26 = r10;
        r19 = r12;
        r9 = r13;
        r17 = r18;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int parseMessage(T r28, byte[] r29, int r30, int r31, int r32, com.google.protobuf.ArrayDecoders.Registers r33) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r33
            checkMutable(r28)
            sun.misc.Unsafe r10 = UNSAFE
            r16 = 0
            r0 = r30
            r2 = r16
            r3 = r2
            r5 = r3
            r1 = -1
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001d:
            if (r0 >= r13) goto L_0x03fd
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002e
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r0, r12, r3, r9)
            int r3 = r9.int1
            r4 = r3
            r3 = r0
            goto L_0x002f
        L_0x002e:
            r4 = r0
        L_0x002f:
            int r0 = r4 >>> 3
            r8 = r4 & 7
            r7 = 3
            if (r0 <= r1) goto L_0x003c
            int r2 = r2 / r7
            int r1 = r15.positionForFieldNumber(r0, r2)
            goto L_0x0040
        L_0x003c:
            int r1 = r15.positionForFieldNumber(r0)
        L_0x0040:
            r2 = r1
            r1 = -1
            if (r2 != r1) goto L_0x0055
            r23 = r0
            r17 = r1
            r2 = r3
            r9 = r4
            r19 = r5
            r22 = r6
            r26 = r10
            r8 = r11
            r20 = r16
            goto L_0x03a6
        L_0x0055:
            int[] r1 = r15.buffer
            int r19 = r2 + 1
            r1 = r1[r19]
            int r7 = type(r1)
            long r11 = offset(r1)
            r19 = r4
            r4 = 17
            r20 = r11
            if (r7 > r4) goto L_0x02a3
            int[] r4 = r15.buffer
            int r12 = r2 + 2
            r4 = r4[r12]
            int r12 = r4 >>> 20
            r11 = 1
            int r12 = r11 << r12
            r11 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r11
            r17 = r12
            if (r4 == r6) goto L_0x0095
            if (r6 == r11) goto L_0x0087
            long r11 = (long) r6
            r10.putInt(r14, r11, r5)
            r11 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0087:
            if (r4 != r11) goto L_0x008c
            r5 = r16
            goto L_0x0091
        L_0x008c:
            long r5 = (long) r4
            int r5 = r10.getInt(r14, r5)
        L_0x0091:
            r22 = r4
            r12 = r5
            goto L_0x0098
        L_0x0095:
            r12 = r5
            r22 = r6
        L_0x0098:
            r4 = 5
            switch(r7) {
                case 0: goto L_0x0271;
                case 1: goto L_0x025a;
                case 2: goto L_0x0234;
                case 3: goto L_0x0234;
                case 4: goto L_0x021d;
                case 5: goto L_0x01fc;
                case 6: goto L_0x01e6;
                case 7: goto L_0x01c4;
                case 8: goto L_0x01a0;
                case 9: goto L_0x017c;
                case 10: goto L_0x0163;
                case 11: goto L_0x021d;
                case 12: goto L_0x011e;
                case 13: goto L_0x01e6;
                case 14: goto L_0x01fc;
                case 15: goto L_0x0102;
                case 16: goto L_0x00e0;
                case 17: goto L_0x00a7;
                default: goto L_0x009c;
            }
        L_0x009c:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r18 = -1
            goto L_0x0295
        L_0x00a7:
            r5 = 3
            if (r8 != r5) goto L_0x00d5
            java.lang.Object r7 = r15.mutableMessageFieldForMerge(r14, r2)
            int r1 = r0 << 3
            r5 = r1 | 4
            com.google.protobuf.Schema r1 = r15.getMessageFieldSchema(r2)
            r23 = r0
            r0 = r7
            r18 = -1
            r8 = r2
            r2 = r29
            r6 = r19
            r4 = r31
            r13 = r6
            r6 = r33
            int r0 = com.google.protobuf.ArrayDecoders.mergeGroupField(r0, r1, r2, r3, r4, r5, r6)
            r15.storeMessageField(r14, r8, r7)
            r5 = r12 | r17
            r12 = r29
            r11 = r32
            r2 = r8
            goto L_0x028e
        L_0x00d5:
            r23 = r0
            r13 = r19
            r18 = -1
            r7 = r29
            r6 = r2
            goto L_0x0295
        L_0x00e0:
            r23 = r0
            r6 = r2
            r13 = r19
            r18 = -1
            r7 = r29
            if (r8 != 0) goto L_0x0295
            r4 = r20
            int r8 = com.google.protobuf.ArrayDecoders.decodeVarint64(r7, r3, r9)
            long r0 = r9.long1
            long r19 = com.google.protobuf.CodedInputStream.decodeZigZag64(r0)
            r0 = r10
            r1 = r28
            r2 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
            goto L_0x0252
        L_0x0102:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r4 = r20
            r18 = -1
            if (r8 != 0) goto L_0x0295
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r7, r3, r9)
            int r1 = r9.int1
            int r1 = com.google.protobuf.CodedInputStream.decodeZigZag32(r1)
            r10.putInt(r14, r4, r1)
            goto L_0x0288
        L_0x011e:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r4 = r20
            r18 = -1
            if (r8 != 0) goto L_0x0295
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r7, r3, r9)
            int r2 = r9.int1
            com.google.protobuf.Internal$EnumVerifier r3 = r15.getEnumFieldVerifier(r6)
            boolean r1 = isLegacyEnumIsClosed(r1)
            if (r1 == 0) goto L_0x015e
            if (r3 == 0) goto L_0x015e
            boolean r1 = r3.isInRange(r2)
            if (r1 == 0) goto L_0x0144
            goto L_0x015e
        L_0x0144:
            com.google.protobuf.UnknownFieldSetLite r1 = getMutableUnknownFields(r28)
            long r2 = (long) r2
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r1.storeField(r13, r2)
            r11 = r32
            r2 = r6
            r5 = r12
            r3 = r13
            r6 = r22
            r1 = r23
            r13 = r31
            r12 = r7
            goto L_0x001d
        L_0x015e:
            r10.putInt(r14, r4, r2)
            goto L_0x0288
        L_0x0163:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r4 = r20
            r0 = 2
            r18 = -1
            if (r8 != r0) goto L_0x0295
            int r0 = com.google.protobuf.ArrayDecoders.decodeBytes(r7, r3, r9)
            java.lang.Object r1 = r9.object1
            r10.putObject(r14, r4, r1)
            goto L_0x0288
        L_0x017c:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r0 = 2
            r18 = -1
            if (r8 != r0) goto L_0x0295
            java.lang.Object r8 = r15.mutableMessageFieldForMerge(r14, r6)
            com.google.protobuf.Schema r1 = r15.getMessageFieldSchema(r6)
            r0 = r8
            r2 = r29
            r4 = r31
            r5 = r33
            int r0 = com.google.protobuf.ArrayDecoders.mergeMessageField(r0, r1, r2, r3, r4, r5)
            r15.storeMessageField(r14, r6, r8)
            goto L_0x0288
        L_0x01a0:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r4 = r20
            r0 = 2
            r18 = -1
            if (r8 != r0) goto L_0x0295
            boolean r0 = isEnforceUtf8(r1)
            if (r0 == 0) goto L_0x01b9
            int r0 = com.google.protobuf.ArrayDecoders.decodeStringRequireUtf8(r7, r3, r9)
            goto L_0x01bd
        L_0x01b9:
            int r0 = com.google.protobuf.ArrayDecoders.decodeString(r7, r3, r9)
        L_0x01bd:
            java.lang.Object r1 = r9.object1
            r10.putObject(r14, r4, r1)
            goto L_0x0288
        L_0x01c4:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r4 = r20
            r18 = -1
            if (r8 != 0) goto L_0x0295
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint64(r7, r3, r9)
            long r1 = r9.long1
            r19 = 0
            int r1 = (r1 > r19 ? 1 : (r1 == r19 ? 0 : -1))
            if (r1 == 0) goto L_0x01df
            r1 = 1
            goto L_0x01e1
        L_0x01df:
            r1 = r16
        L_0x01e1:
            com.google.protobuf.UnsafeUtil.putBoolean((java.lang.Object) r14, (long) r4, (boolean) r1)
            goto L_0x0288
        L_0x01e6:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r1 = r20
            r18 = -1
            if (r8 != r4) goto L_0x0295
            int r0 = com.google.protobuf.ArrayDecoders.decodeFixed32(r7, r3)
            r10.putInt(r14, r1, r0)
            goto L_0x026e
        L_0x01fc:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r1 = r20
            r0 = 1
            r18 = -1
            if (r8 != r0) goto L_0x0295
            long r4 = com.google.protobuf.ArrayDecoders.decodeFixed64(r7, r3)
            r0 = r10
            r20 = r1
            r1 = r28
            r8 = r3
            r2 = r20
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            goto L_0x0288
        L_0x021d:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r18 = -1
            if (r8 != 0) goto L_0x0295
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r7, r3, r9)
            int r1 = r9.int1
            r4 = r20
            r10.putInt(r14, r4, r1)
            goto L_0x0288
        L_0x0234:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r4 = r20
            r18 = -1
            if (r8 != 0) goto L_0x0295
            int r8 = com.google.protobuf.ArrayDecoders.decodeVarint64(r7, r3, r9)
            long r2 = r9.long1
            r0 = r10
            r1 = r28
            r19 = r2
            r2 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
        L_0x0252:
            r5 = r12 | r17
            r11 = r32
            r2 = r6
            r12 = r7
            r0 = r8
            goto L_0x028e
        L_0x025a:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r0 = r20
            r18 = -1
            if (r8 != r4) goto L_0x0295
            float r2 = com.google.protobuf.ArrayDecoders.decodeFloat(r7, r3)
            com.google.protobuf.UnsafeUtil.putFloat((java.lang.Object) r14, (long) r0, (float) r2)
        L_0x026e:
            int r0 = r3 + 4
            goto L_0x0288
        L_0x0271:
            r7 = r29
            r23 = r0
            r6 = r2
            r13 = r19
            r0 = r20
            r2 = 1
            r18 = -1
            if (r8 != r2) goto L_0x0295
            double r4 = com.google.protobuf.ArrayDecoders.decodeDouble(r7, r3)
            com.google.protobuf.UnsafeUtil.putDouble((java.lang.Object) r14, (long) r0, (double) r4)
            int r0 = r3 + 8
        L_0x0288:
            r5 = r12 | r17
            r11 = r32
            r2 = r6
            r12 = r7
        L_0x028e:
            r3 = r13
            r6 = r22
            r1 = r23
            goto L_0x02f6
        L_0x0295:
            r8 = r32
            r2 = r3
            r20 = r6
            r26 = r10
            r19 = r12
            r9 = r13
            r17 = r18
            goto L_0x03a6
        L_0x02a3:
            r23 = r0
            r12 = r2
            r17 = r6
            r13 = r19
            r11 = 1048575(0xfffff, float:1.469367E-39)
            r18 = -1
            r19 = r5
            r5 = r20
            r0 = 27
            if (r7 != r0) goto L_0x0307
            r0 = 2
            if (r8 != r0) goto L_0x02fa
            java.lang.Object r0 = r10.getObject(r14, r5)
            com.google.protobuf.Internal$ProtobufList r0 = (com.google.protobuf.Internal.ProtobufList) r0
            boolean r1 = r0.isModifiable()
            if (r1 != 0) goto L_0x02d8
            int r1 = r0.size()
            if (r1 != 0) goto L_0x02cf
            r1 = 10
            goto L_0x02d1
        L_0x02cf:
            int r1 = r1 * 2
        L_0x02d1:
            com.google.protobuf.Internal$ProtobufList r0 = r0.mutableCopyWithCapacity(r1)
            r10.putObject(r14, r5, r0)
        L_0x02d8:
            r5 = r0
            com.google.protobuf.Schema r0 = r15.getMessageFieldSchema(r12)
            r1 = r13
            r2 = r29
            r4 = r31
            r22 = r17
            r6 = r33
            int r0 = com.google.protobuf.ArrayDecoders.decodeMessageList(r0, r1, r2, r3, r4, r5, r6)
            r11 = r32
            r2 = r12
            r3 = r13
            r5 = r19
            r6 = r22
            r1 = r23
            r12 = r29
        L_0x02f6:
            r13 = r31
            goto L_0x001d
        L_0x02fa:
            r22 = r17
            r15 = r3
            r26 = r10
            r20 = r12
            r17 = r18
            r18 = r13
            goto L_0x037f
        L_0x0307:
            r22 = r17
            r0 = 49
            if (r7 > r0) goto L_0x0353
            long r1 = (long) r1
            r0 = r27
            r20 = r1
            r1 = r28
            r2 = r29
            r4 = r3
            r15 = r4
            r4 = r31
            r24 = r5
            r5 = r13
            r6 = r23
            r30 = r7
            r17 = r18
            r7 = r8
            r8 = r12
            r26 = r10
            r9 = r20
            r11 = r30
            r20 = r12
            r18 = r13
            r12 = r24
            r14 = r33
            int r0 = r0.parseRepeatedField(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x03a2
        L_0x0339:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r33
            r3 = r18
            r5 = r19
            r2 = r20
            r6 = r22
            r1 = r23
            r10 = r26
            goto L_0x001d
        L_0x0353:
            r15 = r3
            r24 = r5
            r30 = r7
            r26 = r10
            r20 = r12
            r17 = r18
            r18 = r13
            r0 = 50
            r9 = r30
            if (r9 != r0) goto L_0x0385
            r0 = 2
            if (r8 != r0) goto L_0x037f
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r20
            r6 = r24
            r8 = r33
            int r0 = r0.parseMapField(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x03a2
            goto L_0x0339
        L_0x037f:
            r8 = r32
            r2 = r15
        L_0x0382:
            r9 = r18
            goto L_0x03a6
        L_0x0385:
            r0 = r27
            r10 = r1
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r18
            r6 = r23
            r7 = r8
            r8 = r10
            r10 = r24
            r12 = r20
            r13 = r33
            int r0 = r0.parseOneofField(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x03a2
            goto L_0x0339
        L_0x03a2:
            r8 = r32
            r2 = r0
            goto L_0x0382
        L_0x03a6:
            if (r9 != r8) goto L_0x03b6
            if (r8 == 0) goto L_0x03b6
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r27
            r6 = r2
            r5 = r19
            r0 = r22
            goto L_0x040c
        L_0x03b6:
            r10 = r27
            boolean r0 = r10.hasExtensions
            r11 = r33
            if (r0 == 0) goto L_0x03d8
            com.google.protobuf.ExtensionRegistryLite r0 = r11.extensionRegistry
            com.google.protobuf.ExtensionRegistryLite r1 = com.google.protobuf.ExtensionRegistryLite.getEmptyRegistry()
            if (r0 == r1) goto L_0x03d8
            com.google.protobuf.MessageLite r5 = r10.defaultInstance
            com.google.protobuf.UnknownFieldSchema<?, ?> r6 = r10.unknownFieldSchema
            r0 = r9
            r1 = r29
            r3 = r31
            r4 = r28
            r7 = r33
            int r0 = com.google.protobuf.ArrayDecoders.decodeExtensionOrUnknownField(r0, r1, r2, r3, r4, r5, r6, r7)
            goto L_0x03e7
        L_0x03d8:
            com.google.protobuf.UnknownFieldSetLite r4 = getMutableUnknownFields(r28)
            r0 = r9
            r1 = r29
            r3 = r31
            r5 = r33
            int r0 = com.google.protobuf.ArrayDecoders.decodeUnknownField(r0, r1, r2, r3, r4, r5)
        L_0x03e7:
            r14 = r28
            r12 = r29
            r13 = r31
            r3 = r9
            r15 = r10
            r9 = r11
            r5 = r19
            r2 = r20
            r6 = r22
            r1 = r23
            r10 = r26
            r11 = r8
            goto L_0x001d
        L_0x03fd:
            r19 = r5
            r22 = r6
            r26 = r10
            r8 = r11
            r10 = r15
            r6 = r0
            r9 = r3
            r0 = r22
            r1 = 1048575(0xfffff, float:1.469367E-39)
        L_0x040c:
            if (r0 == r1) goto L_0x0417
            long r0 = (long) r0
            r7 = r28
            r2 = r26
            r2.putInt(r7, r0, r5)
            goto L_0x0419
        L_0x0417:
            r7 = r28
        L_0x0419:
            int r0 = r10.checkInitializedCount
            r1 = 0
            r11 = r0
            r3 = r1
        L_0x041e:
            int r0 = r10.repeatedFieldOffsetStart
            if (r11 >= r0) goto L_0x0438
            int[] r0 = r10.intArray
            r2 = r0[r11]
            com.google.protobuf.UnknownFieldSchema<?, ?> r4 = r10.unknownFieldSchema
            r0 = r27
            r1 = r28
            r5 = r28
            java.lang.Object r0 = r0.filterMapUnknownEnumValues(r1, r2, r3, r4, r5)
            r3 = r0
            com.google.protobuf.UnknownFieldSetLite r3 = (com.google.protobuf.UnknownFieldSetLite) r3
            int r11 = r11 + 1
            goto L_0x041e
        L_0x0438:
            if (r3 == 0) goto L_0x043f
            com.google.protobuf.UnknownFieldSchema<?, ?> r0 = r10.unknownFieldSchema
            r0.setBuilderToMessage(r7, r3)
        L_0x043f:
            if (r8 != 0) goto L_0x044b
            r0 = r31
            if (r6 != r0) goto L_0x0446
            goto L_0x0451
        L_0x0446:
            com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r0
        L_0x044b:
            r0 = r31
            if (r6 > r0) goto L_0x0452
            if (r9 != r8) goto L_0x0452
        L_0x0451:
            return r6
        L_0x0452:
            com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.parseMessage(java.lang.Object, byte[], int, int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    private Object mutableMessageFieldForMerge(T t, int i) {
        Schema messageFieldSchema = getMessageFieldSchema(i);
        long offset = offset(typeAndOffsetAt(i));
        if (!isFieldPresent(t, i)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(t, offset);
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeMessageField(T t, int i, Object obj) {
        UNSAFE.putObject(t, offset(typeAndOffsetAt(i)), obj);
        setFieldPresent(t, i);
    }

    private Object mutableOneofMessageFieldForMerge(T t, int i, int i2) {
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        if (!isOneofPresent(t, i, i2)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(t, offset(typeAndOffsetAt(i2)));
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeOneofMessageField(T t, int i, int i2, Object obj) {
        UNSAFE.putObject(t, offset(typeAndOffsetAt(i2)), obj);
        setOneofPresent(t, i, i2);
    }

    public void mergeFrom(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws IOException {
        parseMessage(t, bArr, i, i2, 0, registers);
    }

    public void makeImmutable(T t) {
        if (isMutable(t)) {
            if (t instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int length = this.buffer.length;
            for (int i = 0; i < length; i += 3) {
                int typeAndOffsetAt = typeAndOffsetAt(i);
                long offset = offset(typeAndOffsetAt);
                int type = type(typeAndOffsetAt);
                if (type != 9) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(t, numberAt(i), i)) {
                            getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(t, offset));
                        }
                    } else {
                        switch (type) {
                            case 17:
                                break;
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                                this.listFieldSchema.makeImmutableListAt(t, offset);
                                continue;
                            case 50:
                                Unsafe unsafe = UNSAFE;
                                Object object = unsafe.getObject(t, offset);
                                if (object != null) {
                                    unsafe.putObject(t, offset, this.mapFieldSchema.toImmutable(object));
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (isFieldPresent(t, i)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(t, offset));
                }
            }
            this.unknownFieldSchema.makeImmutable(t);
            if (this.hasExtensions) {
                this.extensionSchema.makeImmutable(t);
            }
        }
    }

    private final <K, V> void mergeMap(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(i));
        Object object = UnsafeUtil.getObject(obj, offset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.putObject(obj, offset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            UnsafeUtil.putObject(obj, offset, newMapField);
            object = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema2, Object obj2) {
        Internal.EnumVerifier enumFieldVerifier;
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) {
            return ub;
        }
        return filterUnknownEnumMap(i, numberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema2, obj2);
    }

    private <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema2, Object obj) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema2.getBuilderFromMessage(obj);
                }
                ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.getCodedOutput(), forMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema2.addLengthDelimited(ub, i2, newCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean isInitialized(T t) {
        int i;
        int i2;
        T t2 = t;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.checkInitializedCount) {
            int i6 = this.intArray[i5];
            int numberAt = numberAt(i6);
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            int i7 = this.buffer[i6 + 2];
            int i8 = i7 & 1048575;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i3) {
                if (i8 != 1048575) {
                    i4 = UNSAFE.getInt(t2, (long) i8);
                }
                i = i4;
                i2 = i8;
            } else {
                i2 = i3;
                i = i4;
            }
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(t, i6, i2, i, i9)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type != 9 && type != 17) {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(t2, numberAt, i6) && !isInitialized(t2, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type == 50 && !isMapInitialized(t2, typeAndOffsetAt, i6)) {
                            return false;
                        }
                    }
                }
                if (!isListInitialized(t2, typeAndOffsetAt, i6)) {
                    return false;
                }
            } else if (isFieldPresent(t, i6, i2, i, i9) && !isInitialized(t2, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                return false;
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(t2).isInitialized();
    }

    private static boolean isInitialized(Object obj, int i, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i)));
    }

    private <N> boolean isListInitialized(Object obj, int i, int i2) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!messageFieldSchema.isInitialized(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject((Object) t, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        Schema<?> schema = null;
        for (Object next : forMapData.values()) {
            if (schema == null) {
                schema = Protobuf.getInstance().schemaFor(next.getClass());
            }
            if (!schema.isInitialized(next)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int i, Object obj, Writer writer) throws IOException {
        if (obj instanceof String) {
            writer.writeString(i, (String) obj);
        } else {
            writer.writeBytes(i, (ByteString) obj);
        }
    }

    private void readString(Object obj, int i, Reader reader) throws IOException {
        if (isEnforceUtf8(i)) {
            UnsafeUtil.putObject(obj, offset(i), (Object) reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(obj, offset(i), (Object) reader.readString());
        } else {
            UnsafeUtil.putObject(obj, offset(i), (Object) reader.readBytes());
        }
    }

    private void readStringList(Object obj, int i, Reader reader) throws IOException {
        if (isEnforceUtf8(i)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i)));
        }
    }

    private <E> void readMessageList(Object obj, int i, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i)), schema, extensionRegistryLite);
    }

    private <E> void readGroupList(Object obj, long j, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j), schema, extensionRegistryLite);
    }

    private int numberAt(int i) {
        return this.buffer[i];
    }

    private int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    private int presenceMaskAndOffsetAt(int i) {
        return this.buffer[i + 2];
    }

    private static boolean isMutable(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) obj).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object obj) {
        if (!isMutable(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: " + obj);
        }
    }

    private static <T> double doubleAt(T t, long j) {
        return UnsafeUtil.getDouble((Object) t, j);
    }

    private static <T> float floatAt(T t, long j) {
        return UnsafeUtil.getFloat((Object) t, j);
    }

    private static <T> int intAt(T t, long j) {
        return UnsafeUtil.getInt((Object) t, j);
    }

    private static <T> long longAt(T t, long j) {
        return UnsafeUtil.getLong((Object) t, j);
    }

    private static <T> boolean booleanAt(T t, long j) {
        return UnsafeUtil.getBoolean((Object) t, j);
    }

    private static <T> double oneofDoubleAt(T t, long j) {
        return ((Double) UnsafeUtil.getObject((Object) t, j)).doubleValue();
    }

    private static <T> float oneofFloatAt(T t, long j) {
        return ((Float) UnsafeUtil.getObject((Object) t, j)).floatValue();
    }

    private static <T> int oneofIntAt(T t, long j) {
        return ((Integer) UnsafeUtil.getObject((Object) t, j)).intValue();
    }

    private static <T> long oneofLongAt(T t, long j) {
        return ((Long) UnsafeUtil.getObject((Object) t, j)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T t, long j) {
        return ((Boolean) UnsafeUtil.getObject((Object) t, j)).booleanValue();
    }

    private boolean arePresentForEquals(T t, T t2, int i) {
        return isFieldPresent(t, i) == isFieldPresent(t2, i);
    }

    private boolean isFieldPresent(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return isFieldPresent(t, i);
        }
        return (i3 & i4) != 0;
    }

    private boolean isFieldPresent(T t, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = (long) (1048575 & presenceMaskAndOffsetAt);
        if (j == 1048575) {
            int typeAndOffsetAt = typeAndOffsetAt(i);
            long offset = offset(typeAndOffsetAt);
            switch (type(typeAndOffsetAt)) {
                case 0:
                    if (Double.doubleToRawLongBits(UnsafeUtil.getDouble((Object) t, offset)) != 0) {
                        return true;
                    }
                    return false;
                case 1:
                    if (Float.floatToRawIntBits(UnsafeUtil.getFloat((Object) t, offset)) != 0) {
                        return true;
                    }
                    return false;
                case 2:
                    if (UnsafeUtil.getLong((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 3:
                    if (UnsafeUtil.getLong((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 4:
                    if (UnsafeUtil.getInt((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 5:
                    if (UnsafeUtil.getLong((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 6:
                    if (UnsafeUtil.getInt((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 7:
                    return UnsafeUtil.getBoolean((Object) t, offset);
                case 8:
                    Object object = UnsafeUtil.getObject((Object) t, offset);
                    if (object instanceof String) {
                        return !((String) object).isEmpty();
                    }
                    if (object instanceof ByteString) {
                        return !ByteString.EMPTY.equals(object);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    if (UnsafeUtil.getObject((Object) t, offset) != null) {
                        return true;
                    }
                    return false;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject((Object) t, offset));
                case 11:
                    if (UnsafeUtil.getInt((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 12:
                    if (UnsafeUtil.getInt((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 13:
                    if (UnsafeUtil.getInt((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 14:
                    if (UnsafeUtil.getLong((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 15:
                    if (UnsafeUtil.getInt((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 16:
                    if (UnsafeUtil.getLong((Object) t, offset) != 0) {
                        return true;
                    }
                    return false;
                case 17:
                    if (UnsafeUtil.getObject((Object) t, offset) != null) {
                        return true;
                    }
                    return false;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            if ((UnsafeUtil.getInt((Object) t, j) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0) {
                return true;
            }
            return false;
        }
    }

    private void setFieldPresent(T t, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = (long) (1048575 & presenceMaskAndOffsetAt);
        if (j != 1048575) {
            UnsafeUtil.putInt((Object) t, j, (1 << (presenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt((Object) t, j));
        }
    }

    private boolean isOneofPresent(T t, int i, int i2) {
        return UnsafeUtil.getInt((Object) t, (long) (presenceMaskAndOffsetAt(i2) & 1048575)) == i;
    }

    private boolean isOneofCaseEqual(T t, T t2, int i) {
        long presenceMaskAndOffsetAt = (long) (presenceMaskAndOffsetAt(i) & 1048575);
        return UnsafeUtil.getInt((Object) t, presenceMaskAndOffsetAt) == UnsafeUtil.getInt((Object) t2, presenceMaskAndOffsetAt);
    }

    private void setOneofPresent(T t, int i, int i2) {
        UnsafeUtil.putInt((Object) t, (long) (presenceMaskAndOffsetAt(i2) & 1048575), i);
    }

    private int positionForFieldNumber(int i) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, 0);
    }

    private int positionForFieldNumber(int i, int i2) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, i2);
    }

    private int slowPositionForFieldNumber(int i, int i2) {
        int length = (this.buffer.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int numberAt = numberAt(i4);
            if (i == numberAt) {
                return i4;
            }
            if (i < numberAt) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
