package coil.memory;

import android.os.Parcel;
import android.os.Parcelable;
import coil.memory.MemoryCache;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001d\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"coil/memory/MemoryCache$Key$Companion$CREATOR$1", "Landroid/os/Parcelable$Creator;", "Lcoil/memory/MemoryCache$Key;", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcoil/memory/MemoryCache$Key;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MemoryCache.kt */
public final class MemoryCache$Key$Companion$CREATOR$1 implements Parcelable.Creator<MemoryCache.Key> {
    MemoryCache$Key$Companion$CREATOR$1() {
    }

    public MemoryCache.Key createFromParcel(Parcel parcel) {
        String readString = parcel.readString();
        Intrinsics.checkNotNull(readString);
        int readInt = parcel.readInt();
        LinkedHashMap linkedHashMap = new LinkedHashMap(readInt);
        for (int i = 0; i < readInt; i++) {
            String readString2 = parcel.readString();
            Intrinsics.checkNotNull(readString2);
            String readString3 = parcel.readString();
            Intrinsics.checkNotNull(readString3);
            linkedHashMap.put(readString2, readString3);
        }
        return new MemoryCache.Key(readString, linkedHashMap);
    }

    public MemoryCache.Key[] newArray(int i) {
        return new MemoryCache.Key[i];
    }
}
