package expo.modules.kotlin.jni;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"expo/modules/kotlin/jni/JNIDeallocator$destructorThread$1", "Ljava/lang/Thread;", "run", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: JNIDeallocator.kt */
public final class JNIDeallocator$destructorThread$1 extends Thread {
    final /* synthetic */ JNIDeallocator this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    JNIDeallocator$destructorThread$1(JNIDeallocator jNIDeallocator) {
        super("Expo JNI deallocator");
        this.this$0 = jNIDeallocator;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r2 = this;
        L_0x0000:
            boolean r0 = r2.isInterrupted()
            if (r0 != 0) goto L_0x0026
            expo.modules.kotlin.jni.JNIDeallocator r0 = r2.this$0     // Catch:{ InterruptedException -> 0x0026 }
            java.lang.ref.ReferenceQueue r0 = r0.referenceQueue     // Catch:{ InterruptedException -> 0x0026 }
            java.lang.ref.Reference r0 = r0.remove()     // Catch:{ InterruptedException -> 0x0026 }
            expo.modules.kotlin.jni.JNIDeallocator r1 = r2.this$0     // Catch:{ InterruptedException -> 0x0026 }
            monitor-enter(r2)     // Catch:{ InterruptedException -> 0x0026 }
            java.util.Map r1 = r1.destructorMap     // Catch:{ all -> 0x0023 }
            java.util.Map r1 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r1)     // Catch:{ all -> 0x0023 }
            java.lang.Object r0 = r1.remove(r0)     // Catch:{ all -> 0x0023 }
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0     // Catch:{ all -> 0x0023 }
            monitor-exit(r2)     // Catch:{ InterruptedException -> 0x0026 }
            goto L_0x0000
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ InterruptedException -> 0x0026 }
            throw r0     // Catch:{ InterruptedException -> 0x0026 }
        L_0x0026:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.jni.JNIDeallocator$destructorThread$1.run():void");
    }
}
