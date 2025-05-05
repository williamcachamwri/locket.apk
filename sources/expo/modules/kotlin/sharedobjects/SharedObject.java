package expo.modules.kotlin.sharedobjects;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016R\"\u0010\u0003\u001a\u00020\u0004X\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\f"}, d2 = {"Lexpo/modules/kotlin/sharedobjects/SharedObject;", "", "()V", "sharedObjectId", "Lexpo/modules/kotlin/sharedobjects/SharedObjectId;", "getSharedObjectId-HSeVr_g$expo_modules_core_release", "()I", "setSharedObjectId-kyJHjyY$expo_modules_core_release", "(I)V", "I", "deallocate", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SharedObject.kt */
public class SharedObject {
    private int sharedObjectId = SharedObjectId.m2281constructorimpl(0);

    public void deallocate() {
    }

    /* renamed from: getSharedObjectId-HSeVr_g$expo_modules_core_release  reason: not valid java name */
    public final int m2278getSharedObjectIdHSeVr_g$expo_modules_core_release() {
        return this.sharedObjectId;
    }

    /* renamed from: setSharedObjectId-kyJHjyY$expo_modules_core_release  reason: not valid java name */
    public final void m2279setSharedObjectIdkyJHjyY$expo_modules_core_release(int i) {
        this.sharedObjectId = i;
    }
}
