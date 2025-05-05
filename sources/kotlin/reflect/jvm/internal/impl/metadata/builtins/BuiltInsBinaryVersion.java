package kotlin.reflect.jvm.internal.impl.metadata.builtins;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;

/* compiled from: BuiltInsBinaryVersion.kt */
public final class BuiltInsBinaryVersion extends BinaryVersion {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final BuiltInsBinaryVersion INSTANCE = new BuiltInsBinaryVersion(1, 0, 7);
    public static final BuiltInsBinaryVersion INVALID_VERSION = new BuiltInsBinaryVersion(new int[0]);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BuiltInsBinaryVersion(int... iArr) {
        super(Arrays.copyOf(iArr, iArr.length));
        Intrinsics.checkNotNullParameter(iArr, "numbers");
    }

    public boolean isCompatibleWithCurrentCompilerVersion() {
        return isCompatibleTo(INSTANCE);
    }

    /* compiled from: BuiltInsBinaryVersion.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BuiltInsBinaryVersion readFrom(InputStream inputStream) {
            Intrinsics.checkNotNullParameter(inputStream, "stream");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            Iterable intRange = new IntRange(1, dataInputStream.readInt());
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator it = intRange.iterator();
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                arrayList.add(Integer.valueOf(dataInputStream.readInt()));
            }
            int[] intArray = CollectionsKt.toIntArray((List) arrayList);
            return new BuiltInsBinaryVersion(Arrays.copyOf(intArray, intArray.length));
        }
    }
}
