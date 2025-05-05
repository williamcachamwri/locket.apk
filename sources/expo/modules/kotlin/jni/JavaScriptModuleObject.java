package expo.modules.kotlin.jni;

import androidx.tracing.Trace;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.WritableNativeMap;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.functions.AnyFunction;
import expo.modules.kotlin.objects.ObjectDefinitionData;
import expo.modules.kotlin.objects.PropertyComponent;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0011\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH J\b\u0010\u0010\u001a\u00020\fH\u0004J\t\u0010\u0011\u001a\u00020\bH J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J4\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH ¢\u0006\u0002\u0010\u001fJ<\u0010 \u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\"H ¢\u0006\u0002\u0010#JV\u0010$\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00192\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010'\u001a\u0004\u0018\u00010\"2\u0006\u0010(\u001a\u00020\u00192\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010*\u001a\u0004\u0018\u00010\"H ¢\u0006\u0002\u0010+J4\u0010,\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\"H ¢\u0006\u0002\u0010-J\u0011\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\u0000H J\b\u00100\u001a\u00020\u0005H\u0016R\u0010\u0010\u0007\u001a\u00020\b8\u0002X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u00061"}, d2 = {"Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "Lexpo/modules/kotlin/jni/Destructible;", "jniDeallocator", "Lexpo/modules/kotlin/jni/JNIDeallocator;", "name", "", "(Lexpo/modules/kotlin/jni/JNIDeallocator;Ljava/lang/String;)V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getName", "()Ljava/lang/String;", "deallocate", "", "exportConstants", "constants", "Lcom/facebook/react/bridge/NativeMap;", "finalize", "initHybrid", "initUsingObjectDefinition", "appContext", "Lexpo/modules/kotlin/AppContext;", "definition", "Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "registerAsyncFunction", "takesOwner", "", "desiredTypes", "", "Lexpo/modules/kotlin/jni/ExpectedType;", "body", "Lexpo/modules/kotlin/jni/JNIAsyncFunctionBody;", "(Ljava/lang/String;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIAsyncFunctionBody;)V", "registerClass", "classModule", "Lexpo/modules/kotlin/jni/JNIFunctionBody;", "(Ljava/lang/String;Lexpo/modules/kotlin/jni/JavaScriptModuleObject;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIFunctionBody;)V", "registerProperty", "getterTakesOwner", "getterExpectedType", "getter", "setterTakesOwner", "setterExpectedType", "setter", "(Ljava/lang/String;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIFunctionBody;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIFunctionBody;)V", "registerSyncFunction", "(Ljava/lang/String;Z[Lexpo/modules/kotlin/jni/ExpectedType;Lexpo/modules/kotlin/jni/JNIFunctionBody;)V", "registerViewPrototype", "viewPrototype", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: JavaScriptModuleObject.kt */
public final class JavaScriptModuleObject implements Destructible {
    private final HybridData mHybridData = initHybrid();
    private final String name;

    private final native HybridData initHybrid();

    public final native void exportConstants(NativeMap nativeMap);

    public final native void registerAsyncFunction(String str, boolean z, ExpectedType[] expectedTypeArr, JNIAsyncFunctionBody jNIAsyncFunctionBody);

    public final native void registerClass(String str, JavaScriptModuleObject javaScriptModuleObject, boolean z, ExpectedType[] expectedTypeArr, JNIFunctionBody jNIFunctionBody);

    public final native void registerProperty(String str, boolean z, ExpectedType[] expectedTypeArr, JNIFunctionBody jNIFunctionBody, boolean z2, ExpectedType[] expectedTypeArr2, JNIFunctionBody jNIFunctionBody2);

    public final native void registerSyncFunction(String str, boolean z, ExpectedType[] expectedTypeArr, JNIFunctionBody jNIFunctionBody);

    public final native void registerViewPrototype(JavaScriptModuleObject javaScriptModuleObject);

    public JavaScriptModuleObject(JNIDeallocator jNIDeallocator, String str) {
        Intrinsics.checkNotNullParameter(jNIDeallocator, "jniDeallocator");
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
        jNIDeallocator.addReference(this);
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: finally extract failed */
    public final JavaScriptModuleObject initUsingObjectDefinition(AppContext appContext, ObjectDefinitionData objectDefinitionData) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(objectDefinitionData, "definition");
        JavaScriptModuleObject javaScriptModuleObject = this;
        WritableNativeMap makeNativeMap = Arguments.makeNativeMap(objectDefinitionData.getConstantsProvider().invoke());
        Trace.beginSection("[ExpoModulesCore] Exporting constants");
        try {
            Intrinsics.checkNotNull(makeNativeMap);
            exportConstants(makeNativeMap);
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
            Trace.beginSection("[ExpoModulesCore] Attaching functions");
            try {
                Iterator functions = objectDefinitionData.getFunctions();
                while (functions.hasNext()) {
                    ((AnyFunction) functions.next()).attachToJSObject(appContext, this);
                }
                Unit unit2 = Unit.INSTANCE;
                Trace.endSection();
                Trace.beginSection("[ExpoModulesCore] Attaching properties");
                try {
                    for (Map.Entry<String, PropertyComponent> value : objectDefinitionData.getProperties().entrySet()) {
                        ((PropertyComponent) value.getValue()).attachToJSObject(appContext, this);
                    }
                    Unit unit3 = Unit.INSTANCE;
                    return this;
                } finally {
                    Trace.endSection();
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } catch (Throwable th2) {
            Trace.endSection();
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        deallocate();
    }

    public void deallocate() {
        this.mHybridData.resetNative();
    }

    public String toString() {
        return "JavaScriptModuleObject_" + this.name;
    }
}
