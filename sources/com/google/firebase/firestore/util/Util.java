package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.cloud.datastore.core.number.NumberComparisonHelper;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;

public class Util {
    private static final String AUTO_ID_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int AUTO_ID_LENGTH = 20;
    private static final Continuation<Void, Void> VOID_ERROR_TRANSFORMER = new Util$$ExternalSyntheticLambda3();
    private static final Random rand = new SecureRandom();

    public static int compareBooleans(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public static int compareIntegers(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static String autoId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(AUTO_ID_ALPHABET.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public static int compareLongs(long j, long j2) {
        return NumberComparisonHelper.compareLongs(j, j2);
    }

    public static int compareDoubles(double d, double d2) {
        return NumberComparisonHelper.firestoreCompareDoubles(d, d2);
    }

    public static int compareMixed(double d, long j) {
        return NumberComparisonHelper.firestoreCompareDoubleWithLong(d, j);
    }

    public static <T extends Comparable<T>> Comparator<T> comparator() {
        return new Util$$ExternalSyntheticLambda2();
    }

    public static FirebaseFirestoreException exceptionFromStatus(Status status) {
        StatusException asException = status.asException();
        return new FirebaseFirestoreException(asException.getMessage(), FirebaseFirestoreException.Code.fromValue(status.getCode().value()), asException);
    }

    private static Exception convertStatusException(Exception exc) {
        if (exc instanceof StatusException) {
            return exceptionFromStatus(((StatusException) exc).getStatus());
        }
        return exc instanceof StatusRuntimeException ? exceptionFromStatus(((StatusRuntimeException) exc).getStatus()) : exc;
    }

    public static Exception convertThrowableToException(Throwable th) {
        if (th instanceof Exception) {
            return convertStatusException((Exception) th);
        }
        return new Exception(th);
    }

    static /* synthetic */ Void lambda$static$0(Task task) throws Exception {
        if (task.isSuccessful()) {
            return (Void) task.getResult();
        }
        Exception convertStatusException = convertStatusException(task.getException());
        if (convertStatusException instanceof FirebaseFirestoreException) {
            throw convertStatusException;
        }
        throw new FirebaseFirestoreException(convertStatusException.getMessage(), FirebaseFirestoreException.Code.UNKNOWN, convertStatusException);
    }

    public static Continuation<Void, Void> voidErrorTransformer() {
        return VOID_ERROR_TRANSFORMER;
    }

    public static List<Object> collectUpdateArguments(int i, Object obj, Object obj2, Object... objArr) {
        if (objArr.length % 2 != 1) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(obj);
            arrayList.add(obj2);
            Collections.addAll(arrayList, objArr);
            int i2 = 0;
            while (i2 < arrayList.size()) {
                Object obj3 = arrayList.get(i2);
                if ((obj3 instanceof String) || (obj3 instanceof FieldPath)) {
                    i2 += 2;
                } else {
                    throw new IllegalArgumentException("Excepted field name at argument position " + (i2 + i + 1) + " but got " + obj3 + " in call to update.  The arguments to update should alternate between field names and values");
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("Missing value in call to update().  There must be an even number of arguments that alternate between field names and values");
    }

    public static String toDebugString(ByteString byteString) {
        int size = byteString.size();
        StringBuilder sb = new StringBuilder(size * 2);
        for (int i = 0; i < size; i++) {
            byte byteAt = byteString.byteAt(i) & 255;
            sb.append(Character.forDigit(byteAt >>> 4, 16));
            sb.append(Character.forDigit(byteAt & 15, 16));
        }
        return sb.toString();
    }

    public static String typeName(Object obj) {
        return obj == null ? "null" : obj.getClass().getName();
    }

    public static void crashMainThread(RuntimeException runtimeException) {
        new Handler(Looper.getMainLooper()).post(new Util$$ExternalSyntheticLambda1(runtimeException));
    }

    static /* synthetic */ void lambda$crashMainThread$1(RuntimeException runtimeException) {
        throw runtimeException;
    }

    public static int compareByteArrays(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        for (int i = 0; i < min; i++) {
            byte b = bArr[i] & 255;
            byte b2 = bArr2[i] & 255;
            if (b < b2) {
                return -1;
            }
            if (b > b2) {
                return 1;
            }
        }
        return compareIntegers(bArr.length, bArr2.length);
    }

    public static int compareByteStrings(ByteString byteString, ByteString byteString2) {
        int min = Math.min(byteString.size(), byteString2.size());
        for (int i = 0; i < min; i++) {
            byte byteAt = byteString.byteAt(i) & 255;
            byte byteAt2 = byteString2.byteAt(i) & 255;
            if (byteAt < byteAt2) {
                return -1;
            }
            if (byteAt > byteAt2) {
                return 1;
            }
        }
        return compareIntegers(byteString.size(), byteString2.size());
    }

    public static StringBuilder repeatSequence(CharSequence charSequence, int i, CharSequence charSequence2) {
        StringBuilder sb = new StringBuilder();
        if (i != 0) {
            sb.append(charSequence);
            for (int i2 = 1; i2 < i; i2++) {
                sb.append(charSequence2);
                sb.append(charSequence);
            }
        }
        return sb;
    }

    public static <T> void diffCollections(Collection<T> collection, Collection<T> collection2, Comparator<T> comparator, Consumer<T> consumer, Consumer<T> consumer2) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList, comparator);
        ArrayList arrayList2 = new ArrayList(collection2);
        Collections.sort(arrayList2, comparator);
        diffCollections(arrayList.iterator(), arrayList2.iterator(), comparator, consumer, consumer2);
    }

    public static <T extends Comparable<T>> void diffCollections(SortedSet<T> sortedSet, SortedSet<T> sortedSet2, Consumer<T> consumer, Consumer<T> consumer2) {
        Comparator<? super T> comparator;
        Iterator it = sortedSet.iterator();
        Iterator it2 = sortedSet2.iterator();
        if (sortedSet.comparator() != null) {
            comparator = sortedSet.comparator();
        } else {
            new Util$$ExternalSyntheticLambda4
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: CONSTRUCTOR  (r2v2 ? I:com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda4) =  call: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda4.<init>():void type: CONSTRUCTOR in method: com.google.firebase.firestore.util.Util.diffCollections(java.util.SortedSet, java.util.SortedSet, com.google.firebase.firestore.util.Consumer, com.google.firebase.firestore.util.Consumer):void, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:156)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r2v2 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 34 more
                */
            /*
                java.util.Iterator r0 = r2.iterator()
                java.util.Iterator r3 = r3.iterator()
                java.util.Comparator r1 = r2.comparator()
                if (r1 == 0) goto L_0x0013
                java.util.Comparator r2 = r2.comparator()
                goto L_0x0018
            L_0x0013:
                com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda4 r2 = new com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda4
                r2.<init>()
            L_0x0018:
                diffCollections(r0, r3, r2, r4, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.util.Util.diffCollections(java.util.SortedSet, java.util.SortedSet, com.google.firebase.firestore.util.Consumer, com.google.firebase.firestore.util.Consumer):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
            if (r0 != null) goto L_0x0026;
         */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0030  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static <T> void diffCollections(java.util.Iterator<T> r6, java.util.Iterator<T> r7, java.util.Comparator<? super T> r8, com.google.firebase.firestore.util.Consumer<T> r9, com.google.firebase.firestore.util.Consumer<T> r10) {
            /*
                java.lang.Object r0 = advanceIterator(r6)
                java.lang.Object r1 = advanceIterator(r7)
            L_0x0008:
                if (r0 != 0) goto L_0x000e
                if (r1 == 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                return
            L_0x000e:
                r2 = 0
                r3 = 1
                if (r0 == 0) goto L_0x0020
                if (r1 == 0) goto L_0x0020
                int r4 = r8.compare(r0, r1)
                if (r4 >= 0) goto L_0x001b
                goto L_0x0026
            L_0x001b:
                if (r4 <= 0) goto L_0x001e
                goto L_0x0023
            L_0x001e:
                r3 = r2
                goto L_0x0026
            L_0x0020:
                if (r0 == 0) goto L_0x0023
                goto L_0x0026
            L_0x0023:
                r5 = r3
                r3 = r2
                r2 = r5
            L_0x0026:
                if (r2 == 0) goto L_0x0030
                r9.accept(r1)
                java.lang.Object r1 = advanceIterator(r7)
                goto L_0x0008
            L_0x0030:
                if (r3 == 0) goto L_0x003a
                r10.accept(r0)
                java.lang.Object r0 = advanceIterator(r6)
                goto L_0x0008
            L_0x003a:
                java.lang.Object r0 = advanceIterator(r6)
                java.lang.Object r1 = advanceIterator(r7)
                goto L_0x0008
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.util.Util.diffCollections(java.util.Iterator, java.util.Iterator, java.util.Comparator, com.google.firebase.firestore.util.Consumer, com.google.firebase.firestore.util.Consumer):void");
        }

        private static <T> T advanceIterator(Iterator<T> it) {
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        }

        public static <K, V> Iterable<V> values(Iterable<Map.Entry<K, V>> iterable) {
            return new Util$$ExternalSyntheticLambda0(iterable);
        }

        public static <K, V> Map<K, V> firstNEntries(Map<K, V> map, int i, Comparator<V> comparator) {
            if (map.size() <= i) {
                return map;
            }
            ArrayList arrayList = new ArrayList(map.entrySet());
            Collections.sort(arrayList, new Util$$ExternalSyntheticLambda5(comparator));
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < i; i2++) {
                hashMap.put(((Map.Entry) arrayList.get(i2)).getKey(), ((Map.Entry) arrayList.get(i2)).getValue());
            }
            return hashMap;
        }
    }
