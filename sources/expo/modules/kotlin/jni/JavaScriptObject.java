package expo.modules.kotlin.jni;

import com.facebook.jni.HybridData;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\r\b\u0017\u0018\u00002\u00020\u0001:\u00011B\u000f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0006H J\b\u0010\u0007\u001a\u00020\bH\u0016J!\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH J\u001b\u0010\u0010\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\u0000¢\u0006\u0002\b\u0013J!\u0010\u0014\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH J#\u0010\u0016\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u000e\u001a\u00020\u000fH J#\u0010\u0017\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u000e\u001a\u00020\u000fH J\u0011\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u001aH J(\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00002\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ(\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00182\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ&\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ&\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00152\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ&\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ(\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ(\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ#\u0010 \u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH J\b\u0010!\u001a\u00020\bH\u0004J\u0013\u0010\"\u001a\u0004\u0018\u00010\u00182\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0011\u0010#\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u000bH J\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0%H ¢\u0006\u0002\u0010&J\u0011\u0010'\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH J\u0006\u0010(\u001a\u00020\rJ\u001b\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0000H\u0002J\u001b\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0018H\u0002J\u0019\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0019\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0015H\u0002J\u0019\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000fH\u0002J\u001b\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u001b\u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u0019\u0010*\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH J\u0019\u0010+\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0015H J\u001b\u0010,\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0000H J\u001b\u0010-\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0018H J\u0018\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0000J\u0018\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0018J\u0016\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0015J\u0016\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000fJ\u0018\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u0018\u0010.\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u001b\u0010/\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH J\u0011\u00100\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lexpo/modules/kotlin/jni/JavaScriptObject;", "Lexpo/modules/kotlin/jni/Destructible;", "mHybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "createWeak", "Lexpo/modules/kotlin/jni/JavaScriptWeakObject;", "deallocate", "", "defineBoolProperty", "name", "", "value", "", "options", "", "defineDeallocator", "deallocator", "Lkotlin/Function0;", "defineDeallocator$expo_modules_core_release", "defineDoubleProperty", "", "defineJSObjectProperty", "defineJSValueProperty", "Lexpo/modules/kotlin/jni/JavaScriptValue;", "defineNativeDeallocator", "Lexpo/modules/kotlin/jni/JNIFunctionBody;", "defineProperty", "", "Lexpo/modules/kotlin/jni/JavaScriptObject$PropertyDescriptor;", "null", "", "defineStringProperty", "finalize", "get", "getProperty", "getPropertyNames", "", "()[Ljava/lang/String;", "hasProperty", "isValid", "set", "setBoolProperty", "setDoubleProperty", "setJSObjectProperty", "setJSValueProperty", "setProperty", "setStringProperty", "unsetProperty", "PropertyDescriptor", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: JavaScriptObject.kt */
public class JavaScriptObject implements Destructible {
    private final HybridData mHybridData;

    private final native void defineBoolProperty(String str, boolean z, int i);

    private final native void defineDoubleProperty(String str, double d, int i);

    private final native void defineJSObjectProperty(String str, JavaScriptObject javaScriptObject, int i);

    private final native void defineJSValueProperty(String str, JavaScriptValue javaScriptValue, int i);

    private final native void defineNativeDeallocator(JNIFunctionBody jNIFunctionBody);

    private final native void defineStringProperty(String str, String str2, int i);

    private final native void setBoolProperty(String str, boolean z);

    private final native void setDoubleProperty(String str, double d);

    private final native void setJSObjectProperty(String str, JavaScriptObject javaScriptObject);

    private final native void setJSValueProperty(String str, JavaScriptValue javaScriptValue);

    private final native void setStringProperty(String str, String str2);

    private final native void unsetProperty(String str);

    public final native JavaScriptWeakObject createWeak();

    public final native JavaScriptValue getProperty(String str);

    public final native String[] getPropertyNames();

    public final native boolean hasProperty(String str);

    public JavaScriptObject(HybridData hybridData) {
        Intrinsics.checkNotNullParameter(hybridData, "mHybridData");
        this.mHybridData = hybridData;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lexpo/modules/kotlin/jni/JavaScriptObject$PropertyDescriptor;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "Configurable", "Enumerable", "Writable", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: JavaScriptObject.kt */
    public enum PropertyDescriptor {
        Configurable(1),
        Enumerable(2),
        Writable(4);
        
        private final int value;

        public static EnumEntries<PropertyDescriptor> getEntries() {
            return $ENTRIES;
        }

        private PropertyDescriptor(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        static {
            PropertyDescriptor[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }

    public final boolean isValid() {
        return this.mHybridData.isValid();
    }

    public final JavaScriptValue get(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        if (hasProperty(str)) {
            return getProperty(str);
        }
        return null;
    }

    public final void defineDeallocator$expo_modules_core_release(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "deallocator");
        defineNativeDeallocator(new JavaScriptObject$$ExternalSyntheticLambda0(function0));
    }

    /* access modifiers changed from: private */
    public static final Unit defineDeallocator$lambda$0(Function0 function0, Object[] objArr) {
        Intrinsics.checkNotNullParameter(function0, "$deallocator");
        Intrinsics.checkNotNullParameter(objArr, "it");
        function0.invoke();
        return Unit.INSTANCE;
    }

    public final void setProperty(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "name");
        setBoolProperty(str, z);
    }

    public final void set(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "name");
        setBoolProperty(str, z);
    }

    public final void setProperty(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "name");
        setDoubleProperty(str, (double) i);
    }

    public final void set(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "name");
        setDoubleProperty(str, (double) i);
    }

    public final void setProperty(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "name");
        setDoubleProperty(str, d);
    }

    public final void set(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "name");
        setDoubleProperty(str, d);
    }

    public final void setProperty(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        setStringProperty(str, str2);
    }

    public final void set(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        setStringProperty(str, str2);
    }

    public final void setProperty(String str, JavaScriptValue javaScriptValue) {
        Intrinsics.checkNotNullParameter(str, "name");
        setJSValueProperty(str, javaScriptValue);
    }

    public final void set(String str, JavaScriptValue javaScriptValue) {
        Intrinsics.checkNotNullParameter(str, "name");
        setJSValueProperty(str, javaScriptValue);
    }

    public final void setProperty(String str, JavaScriptObject javaScriptObject) {
        Intrinsics.checkNotNullParameter(str, "name");
        setJSObjectProperty(str, javaScriptObject);
    }

    public final void set(String str, JavaScriptObject javaScriptObject) {
        Intrinsics.checkNotNullParameter(str, "name");
        setJSObjectProperty(str, javaScriptObject);
    }

    public final void setProperty(String str, Void voidR) {
        Intrinsics.checkNotNullParameter(str, "name");
        unsetProperty(str);
    }

    public final void set(String str, Void voidR) {
        Intrinsics.checkNotNullParameter(str, "name");
        unsetProperty(str);
    }

    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, boolean z, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            javaScriptObject.defineProperty(str, z, (List<? extends PropertyDescriptor>) list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
    }

    public final void defineProperty(String str, boolean z, List<? extends PropertyDescriptor> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "options");
        defineBoolProperty(str, z, JavaScriptObjectKt.toCppOptions(list));
    }

    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, int i, List list, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            javaScriptObject.defineProperty(str, i, (List<? extends PropertyDescriptor>) list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
    }

    public final void defineProperty(String str, int i, List<? extends PropertyDescriptor> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "options");
        defineDoubleProperty(str, (double) i, JavaScriptObjectKt.toCppOptions(list));
    }

    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, double d, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            javaScriptObject.defineProperty(str, d, (List<? extends PropertyDescriptor>) list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
    }

    public final void defineProperty(String str, double d, List<? extends PropertyDescriptor> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "options");
        defineDoubleProperty(str, d, JavaScriptObjectKt.toCppOptions(list));
    }

    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, String str2, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            javaScriptObject.defineProperty(str, str2, (List<? extends PropertyDescriptor>) list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
    }

    public final void defineProperty(String str, String str2, List<? extends PropertyDescriptor> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "options");
        defineStringProperty(str, str2, JavaScriptObjectKt.toCppOptions(list));
    }

    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, JavaScriptValue javaScriptValue, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            javaScriptObject.defineProperty(str, javaScriptValue, (List<? extends PropertyDescriptor>) list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
    }

    public final void defineProperty(String str, JavaScriptValue javaScriptValue, List<? extends PropertyDescriptor> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "options");
        defineJSValueProperty(str, javaScriptValue, JavaScriptObjectKt.toCppOptions(list));
    }

    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, JavaScriptObject javaScriptObject2, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            javaScriptObject.defineProperty(str, javaScriptObject2, (List<? extends PropertyDescriptor>) list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
    }

    public final void defineProperty(String str, JavaScriptObject javaScriptObject, List<? extends PropertyDescriptor> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "options");
        defineJSObjectProperty(str, javaScriptObject, JavaScriptObjectKt.toCppOptions(list));
    }

    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, Void voidR, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            javaScriptObject.defineProperty(str, voidR, (List<? extends PropertyDescriptor>) list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
    }

    public final void defineProperty(String str, Void voidR, List<? extends PropertyDescriptor> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "options");
        defineJSObjectProperty(str, (JavaScriptObject) null, JavaScriptObjectKt.toCppOptions(list));
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        deallocate();
    }

    public void deallocate() {
        this.mHybridData.resetNative();
    }
}
