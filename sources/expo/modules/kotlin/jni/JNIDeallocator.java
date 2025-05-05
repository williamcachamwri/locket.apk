package expo.modules.kotlin.jni;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000?\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000*\u0001\u000b\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0007J\u0011\u0010\u0012\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0016R&\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t0\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lexpo/modules/kotlin/jni/JNIDeallocator;", "", "shouldCreateDestructorThread", "", "(Z)V", "destructorMap", "", "Ljava/lang/ref/PhantomReference;", "Lexpo/modules/kotlin/jni/Destructible;", "Ljava/lang/ref/WeakReference;", "destructorThread", "expo/modules/kotlin/jni/JNIDeallocator$destructorThread$1", "Lexpo/modules/kotlin/jni/JNIDeallocator$destructorThread$1;", "referenceQueue", "Ljava/lang/ref/ReferenceQueue;", "addReference", "", "destructible", "deallocate", "deallocate$expo_modules_core_release", "()Lkotlin/Unit;", "inspectMemory", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: JNIDeallocator.kt */
public final class JNIDeallocator {
    /* access modifiers changed from: private */
    public final Map<PhantomReference<Destructible>, WeakReference<Destructible>> destructorMap;
    private final JNIDeallocator$destructorThread$1 destructorThread;
    /* access modifiers changed from: private */
    public final ReferenceQueue<Destructible> referenceQueue;
    private final boolean shouldCreateDestructorThread;

    public JNIDeallocator() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    public JNIDeallocator(boolean z) {
        JNIDeallocator$destructorThread$1 jNIDeallocator$destructorThread$1;
        this.shouldCreateDestructorThread = z;
        this.referenceQueue = new ReferenceQueue<>();
        this.destructorMap = new LinkedHashMap();
        if (z) {
            jNIDeallocator$destructorThread$1 = new JNIDeallocator$destructorThread$1(this);
            jNIDeallocator$destructorThread$1.start();
        } else {
            jNIDeallocator$destructorThread$1 = null;
        }
        this.destructorThread = jNIDeallocator$destructorThread$1;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ JNIDeallocator(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    public final void addReference(Destructible destructible) {
        Intrinsics.checkNotNullParameter(destructible, "destructible");
        synchronized (this) {
            WeakReference weakReference = new WeakReference(destructible);
            this.destructorMap.put(new PhantomReference(destructible, this.referenceQueue), weakReference);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final Unit deallocate$expo_modules_core_release() {
        Unit unit;
        synchronized (this) {
            for (WeakReference weakReference : this.destructorMap.values()) {
                Destructible destructible = (Destructible) weakReference.get();
                if (destructible != null) {
                    destructible.deallocate();
                }
            }
            this.destructorMap.clear();
            JNIDeallocator$destructorThread$1 jNIDeallocator$destructorThread$1 = this.destructorThread;
            if (jNIDeallocator$destructorThread$1 != null) {
                jNIDeallocator$destructorThread$1.interrupt();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
        }
        return unit;
    }

    public final List<Destructible> inspectMemory() {
        List<Destructible> list;
        synchronized (this) {
            Collection arrayList = new ArrayList();
            for (WeakReference weakReference : this.destructorMap.values()) {
                Destructible destructible = (Destructible) weakReference.get();
                if (destructible != null) {
                    arrayList.add(destructible);
                }
            }
            list = (List) arrayList;
        }
        return list;
    }
}
