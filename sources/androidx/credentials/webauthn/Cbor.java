package androidx.credentials.webauthn;

import androidx.media3.muxer.MuxerUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002#$B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0016J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0001J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\u0018\u0010 \u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006¨\u0006%"}, d2 = {"Landroidx/credentials/webauthn/Cbor;", "", "()V", "TYPE_ARRAY", "", "getTYPE_ARRAY", "()I", "TYPE_BYTE_STRING", "getTYPE_BYTE_STRING", "TYPE_FLOAT", "getTYPE_FLOAT", "TYPE_MAP", "getTYPE_MAP", "TYPE_NEGATIVE_INT", "getTYPE_NEGATIVE_INT", "TYPE_TAG", "getTYPE_TAG", "TYPE_TEXT_STRING", "getTYPE_TEXT_STRING", "TYPE_UNSIGNED_INT", "getTYPE_UNSIGNED_INT", "createArg", "", "type", "arg", "", "decode", "data", "encode", "getArg", "Landroidx/credentials/webauthn/Cbor$Arg;", "offset", "getType", "parseItem", "Landroidx/credentials/webauthn/Cbor$Item;", "Arg", "Item", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Cbor.kt */
public final class Cbor {
    private final int TYPE_ARRAY = 4;
    private final int TYPE_BYTE_STRING = 2;
    private final int TYPE_FLOAT = 7;
    private final int TYPE_MAP = 5;
    private final int TYPE_NEGATIVE_INT = 1;
    private final int TYPE_TAG = 6;
    private final int TYPE_TEXT_STRING = 3;
    private final int TYPE_UNSIGNED_INT;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0001HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/credentials/webauthn/Cbor$Item;", "", "item", "len", "", "(Ljava/lang/Object;I)V", "getItem", "()Ljava/lang/Object;", "getLen", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Cbor.kt */
    public static final class Item {
        private final Object item;
        private final int len;

        public static /* synthetic */ Item copy$default(Item item2, Object obj, int i, int i2, Object obj2) {
            if ((i2 & 1) != 0) {
                obj = item2.item;
            }
            if ((i2 & 2) != 0) {
                i = item2.len;
            }
            return item2.copy(obj, i);
        }

        public final Object component1() {
            return this.item;
        }

        public final int component2() {
            return this.len;
        }

        public final Item copy(Object obj, int i) {
            Intrinsics.checkNotNullParameter(obj, "item");
            return new Item(obj, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Item)) {
                return false;
            }
            Item item2 = (Item) obj;
            return Intrinsics.areEqual(this.item, item2.item) && this.len == item2.len;
        }

        public int hashCode() {
            return (this.item.hashCode() * 31) + Integer.hashCode(this.len);
        }

        public String toString() {
            return "Item(item=" + this.item + ", len=" + this.len + ')';
        }

        public Item(Object obj, int i) {
            Intrinsics.checkNotNullParameter(obj, "item");
            this.item = obj;
            this.len = i;
        }

        public final Object getItem() {
            return this.item;
        }

        public final int getLen() {
            return this.len;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/credentials/webauthn/Cbor$Arg;", "", "arg", "", "len", "", "(JI)V", "getArg", "()J", "getLen", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Cbor.kt */
    public static final class Arg {
        private final long arg;
        private final int len;

        public static /* synthetic */ Arg copy$default(Arg arg2, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j = arg2.arg;
            }
            if ((i2 & 2) != 0) {
                i = arg2.len;
            }
            return arg2.copy(j, i);
        }

        public final long component1() {
            return this.arg;
        }

        public final int component2() {
            return this.len;
        }

        public final Arg copy(long j, int i) {
            return new Arg(j, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arg)) {
                return false;
            }
            Arg arg2 = (Arg) obj;
            return this.arg == arg2.arg && this.len == arg2.len;
        }

        public int hashCode() {
            return (Long.hashCode(this.arg) * 31) + Integer.hashCode(this.len);
        }

        public String toString() {
            return "Arg(arg=" + this.arg + ", len=" + this.len + ')';
        }

        public Arg(long j, int i) {
            this.arg = j;
            this.len = i;
        }

        public final long getArg() {
            return this.arg;
        }

        public final int getLen() {
            return this.len;
        }
    }

    public final int getTYPE_UNSIGNED_INT() {
        return this.TYPE_UNSIGNED_INT;
    }

    public final int getTYPE_NEGATIVE_INT() {
        return this.TYPE_NEGATIVE_INT;
    }

    public final int getTYPE_BYTE_STRING() {
        return this.TYPE_BYTE_STRING;
    }

    public final int getTYPE_TEXT_STRING() {
        return this.TYPE_TEXT_STRING;
    }

    public final int getTYPE_ARRAY() {
        return this.TYPE_ARRAY;
    }

    public final int getTYPE_MAP() {
        return this.TYPE_MAP;
    }

    public final int getTYPE_TAG() {
        return this.TYPE_TAG;
    }

    public final int getTYPE_FLOAT() {
        return this.TYPE_FLOAT;
    }

    public final Object decode(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        return parseItem(bArr, 0).getItem();
    }

    public final byte[] encode(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "data");
        if (obj instanceof Number) {
            if (!(obj instanceof Double)) {
                long longValue = ((Number) obj).longValue();
                if (longValue >= 0) {
                    return createArg(this.TYPE_UNSIGNED_INT, longValue);
                }
                return createArg(this.TYPE_NEGATIVE_INT, ((long) -1) - longValue);
            }
            throw new IllegalArgumentException("Don't support doubles yet");
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            return ArraysKt.plus(createArg(this.TYPE_BYTE_STRING, (long) bArr.length), bArr);
        } else if (obj instanceof String) {
            String str = (String) obj;
            return ArraysKt.plus(createArg(this.TYPE_TEXT_STRING, (long) str.length()), StringsKt.encodeToByteArray(str));
        } else if (obj instanceof List) {
            List list = (List) obj;
            byte[] createArg = createArg(this.TYPE_ARRAY, (long) list.size());
            for (Object next : list) {
                Intrinsics.checkNotNull(next);
                createArg = ArraysKt.plus(createArg, encode(next));
            }
            return createArg;
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            byte[] createArg2 = createArg(this.TYPE_MAP, (long) map.size());
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (Map) new LinkedHashMap();
            for (Map.Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                Intrinsics.checkNotNull(key);
                byte[] encode = encode(key);
                Object value = entry.getValue();
                Intrinsics.checkNotNull(value);
                ((Map) objectRef.element).put(encode, encode(value));
            }
            ArrayList arrayList = new ArrayList(((Map) objectRef.element).keySet());
            CollectionsKt.sortedWith(arrayList, new Cbor$$ExternalSyntheticLambda0(objectRef));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] bArr2 = (byte[]) it.next();
                Intrinsics.checkNotNullExpressionValue(bArr2, "key");
                byte[] plus = ArraysKt.plus(createArg2, bArr2);
                Object obj2 = ((Map) objectRef.element).get(bArr2);
                Intrinsics.checkNotNull(obj2);
                createArg2 = ArraysKt.plus(plus, (byte[]) obj2);
            }
            return createArg2;
        } else {
            throw new IllegalArgumentException("Bad type");
        }
    }

    /* access modifiers changed from: private */
    public static final int encode$lambda$0(Ref.ObjectRef objectRef, byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNullParameter(objectRef, "$byteMap");
        Object obj = ((Map) objectRef.element).get(bArr);
        Intrinsics.checkNotNull(obj);
        byte[] bArr3 = (byte[]) obj;
        Object obj2 = ((Map) objectRef.element).get(bArr2);
        Intrinsics.checkNotNull(obj2);
        byte[] bArr4 = (byte[]) obj2;
        if (bArr.length > bArr2.length) {
            return 1;
        }
        if (bArr.length >= bArr2.length) {
            if (bArr3.length > bArr4.length) {
                return 1;
            }
            if (bArr3.length >= bArr4.length) {
                return 0;
            }
        }
        return -1;
    }

    private final int getType(byte[] bArr, int i) {
        return (bArr[i] & 255) >> 5;
    }

    private final Arg getArg(byte[] bArr, int i) {
        long j = ((long) bArr[i]) & 31;
        int i2 = (j > 24 ? 1 : (j == 24 ? 0 : -1));
        if (i2 < 0) {
            return new Arg(j, 1);
        }
        if (i2 == 0) {
            return new Arg(((long) bArr[i + 1]) & 255, 2);
        }
        if (j == 25) {
            return new Arg((((long) bArr[i + 2]) & 255) | ((((long) bArr[i + 1]) & 255) << 8), 3);
        } else if (j == 26) {
            return new Arg((((long) bArr[i + 4]) & 255) | ((((long) bArr[i + 1]) & 255) << 24) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 8), 5);
        } else {
            throw new IllegalArgumentException("Bad arg");
        }
    }

    private final Item parseItem(byte[] bArr, int i) {
        int type = getType(bArr, i);
        Arg arg = getArg(bArr, i);
        System.out.println("Type " + type + ' ' + arg.getArg() + ' ' + arg.getLen());
        if (type == this.TYPE_UNSIGNED_INT) {
            return new Item(Long.valueOf(arg.getArg()), arg.getLen());
        }
        if (type == this.TYPE_NEGATIVE_INT) {
            return new Item(Long.valueOf(((long) -1) - arg.getArg()), arg.getLen());
        }
        if (type == this.TYPE_BYTE_STRING) {
            return new Item(ArraysKt.sliceArray(bArr, RangesKt.until(arg.getLen() + i, i + arg.getLen() + ((int) arg.getArg()))), arg.getLen() + ((int) arg.getArg()));
        }
        if (type == this.TYPE_TEXT_STRING) {
            return new Item(new String(ArraysKt.sliceArray(bArr, RangesKt.until(arg.getLen() + i, i + arg.getLen() + ((int) arg.getArg()))), Charsets.UTF_8), arg.getLen() + ((int) arg.getArg()));
        }
        int i2 = 0;
        if (type == this.TYPE_ARRAY) {
            List arrayList = new ArrayList();
            int len = arg.getLen();
            int arg2 = (int) arg.getArg();
            while (i2 < arg2) {
                Item parseItem = parseItem(bArr, i + len);
                arrayList.add(parseItem.getItem());
                len += parseItem.getLen();
                i2++;
            }
            return new Item(CollectionsKt.toList(arrayList), len);
        } else if (type == this.TYPE_MAP) {
            Map linkedHashMap = new LinkedHashMap();
            int len2 = arg.getLen();
            int arg3 = (int) arg.getArg();
            while (i2 < arg3) {
                Item parseItem2 = parseItem(bArr, i + len2);
                int len3 = len2 + parseItem2.getLen();
                Item parseItem3 = parseItem(bArr, i + len3);
                len2 = len3 + parseItem3.getLen();
                linkedHashMap.put(parseItem2.getItem(), parseItem3.getItem());
                i2++;
            }
            return new Item(MapsKt.toMap(linkedHashMap), len2);
        } else {
            throw new IllegalArgumentException("Bad type");
        }
    }

    private final byte[] createArg(int i, long j) {
        int i2 = i << 5;
        int i3 = (int) j;
        if (j < 24) {
            return new byte[]{(byte) ((i2 | i3) & 255)};
        } else if (j <= 255) {
            return new byte[]{(byte) ((i2 | 24) & 255), (byte) (i3 & 255)};
        } else if (j <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            return new byte[]{(byte) ((i2 | 25) & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255)};
        } else if (j <= MuxerUtil.UNSIGNED_INT_MAX_VALUE) {
            return new byte[]{(byte) ((i2 | 26) & 255), (byte) ((i3 >> 24) & 255), (byte) ((i3 >> 16) & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255)};
        } else {
            throw new IllegalArgumentException("bad Arg");
        }
    }
}
