package expo.modules.kotlin;

import expo.modules.kotlin.jni.JavaScriptModuleObject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "T", "Lexpo/modules/kotlin/modules/Module;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleHolder.kt */
final class ModuleHolder$jsObject$2 extends Lambda implements Function0<JavaScriptModuleObject> {
    final /* synthetic */ ModuleHolder<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ModuleHolder$jsObject$2(ModuleHolder<T> moduleHolder) {
        super(0);
        this.this$0 = moduleHolder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d6, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0070 A[Catch:{ all -> 0x016c, all -> 0x00d2, all -> 0x0171 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x010f A[Catch:{ all -> 0x016c, all -> 0x00d2, all -> 0x0171 }, LOOP:1: B:33:0x0109->B:35:0x010f, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final expo.modules.kotlin.jni.JavaScriptModuleObject invoke() {
        /*
            r13 = this;
            expo.modules.kotlin.ModuleHolder<T> r0 = r13.this$0
            java.lang.String r0 = r0.getName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = ".jsObject"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            expo.modules.kotlin.ModuleHolder<T> r1 = r13.this$0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "[ExpoModulesCore] "
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            androidx.tracing.Trace.beginSection(r0)
            expo.modules.kotlin.modules.Module r0 = r1.getModule()     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.AppContext r0 = r0.getAppContext()     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.jni.JNIDeallocator r2 = r0.getJniDeallocator()     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.jni.JavaScriptModuleObject r9 = new expo.modules.kotlin.jni.JavaScriptModuleObject     // Catch:{ all -> 0x0171 }
            java.lang.String r3 = r1.getName()     // Catch:{ all -> 0x0171 }
            r9.<init>(r2, r3)     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.modules.ModuleDefinitionData r3 = r1.getDefinition()     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.objects.ObjectDefinitionData r3 = r3.getObjectDefinition()     // Catch:{ all -> 0x0171 }
            r9.initUsingObjectDefinition(r0, r3)     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.modules.ModuleDefinitionData r3 = r1.getDefinition()     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.views.ViewManagerDefinition r3 = r3.getViewManagerDefinition()     // Catch:{ all -> 0x0171 }
            r4 = 0
            if (r3 == 0) goto L_0x005d
            java.util.List r3 = r3.getAsyncFunctions()     // Catch:{ all -> 0x0171 }
            goto L_0x005e
        L_0x005d:
            r3 = r4
        L_0x005e:
            r10 = 0
            if (r3 == 0) goto L_0x006d
            r5 = r3
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x0171 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0171 }
            r6 = 1
            r5 = r5 ^ r6
            if (r5 != r6) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r6 = r10
        L_0x006e:
            if (r6 == 0) goto L_0x00d7
            java.lang.String r5 = "[ExpoModulesCore] Attaching view prototype"
            androidx.tracing.Trace.beginSection(r5)     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.jni.JavaScriptModuleObject r5 = new expo.modules.kotlin.jni.JavaScriptModuleObject     // Catch:{ all -> 0x00d2 }
            java.lang.String r6 = r9.getName()     // Catch:{ all -> 0x00d2 }
            expo.modules.kotlin.modules.ModuleDefinitionData r7 = r1.getDefinition()     // Catch:{ all -> 0x00d2 }
            expo.modules.kotlin.views.ViewManagerDefinition r7 = r7.getViewManagerDefinition()     // Catch:{ all -> 0x00d2 }
            if (r7 == 0) goto L_0x008f
            java.lang.Class r7 = r7.getViewType$expo_modules_core_release()     // Catch:{ all -> 0x00d2 }
            if (r7 == 0) goto L_0x008f
            java.lang.String r4 = r7.getName()     // Catch:{ all -> 0x00d2 }
        L_0x008f:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d2 }
            r7.<init>()     // Catch:{ all -> 0x00d2 }
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ all -> 0x00d2 }
            java.lang.String r7 = "_"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x00d2 }
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch:{ all -> 0x00d2 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00d2 }
            r5.<init>(r2, r4)     // Catch:{ all -> 0x00d2 }
            expo.modules.kotlin.jni.JNIDeallocator r4 = r0.getJniDeallocator()     // Catch:{ all -> 0x00d2 }
            r6 = r5
            expo.modules.kotlin.jni.Destructible r6 = (expo.modules.kotlin.jni.Destructible) r6     // Catch:{ all -> 0x00d2 }
            r4.addReference(r6)     // Catch:{ all -> 0x00d2 }
            java.lang.Iterable r3 = (java.lang.Iterable) r3     // Catch:{ all -> 0x00d2 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00d2 }
        L_0x00b9:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x00d2 }
            if (r4 == 0) goto L_0x00c9
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x00d2 }
            expo.modules.kotlin.functions.BaseAsyncFunctionComponent r4 = (expo.modules.kotlin.functions.BaseAsyncFunctionComponent) r4     // Catch:{ all -> 0x00d2 }
            r4.attachToJSObject(r0, r5)     // Catch:{ all -> 0x00d2 }
            goto L_0x00b9
        L_0x00c9:
            r9.registerViewPrototype(r5)     // Catch:{ all -> 0x00d2 }
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00d2 }
            androidx.tracing.Trace.endSection()     // Catch:{ all -> 0x0171 }
            goto L_0x00d7
        L_0x00d2:
            r0 = move-exception
            androidx.tracing.Trace.endSection()     // Catch:{ all -> 0x0171 }
            throw r0     // Catch:{ all -> 0x0171 }
        L_0x00d7:
            java.lang.String r3 = "Attaching classes"
            java.lang.String r4 = "ExpoModulesCore"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0171 }
            r5.<init>()     // Catch:{ all -> 0x0171 }
            java.lang.String r6 = "["
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0171 }
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ all -> 0x0171 }
            java.lang.String r5 = "] "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0171 }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x0171 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0171 }
            androidx.tracing.Trace.beginSection(r3)     // Catch:{ all -> 0x0171 }
            expo.modules.kotlin.modules.ModuleDefinitionData r3 = r1.getDefinition()     // Catch:{ all -> 0x016c }
            java.util.List r3 = r3.getClassData()     // Catch:{ all -> 0x016c }
            java.lang.Iterable r3 = (java.lang.Iterable) r3     // Catch:{ all -> 0x016c }
            java.util.Iterator r11 = r3.iterator()     // Catch:{ all -> 0x016c }
        L_0x0109:
            boolean r3 = r11.hasNext()     // Catch:{ all -> 0x016c }
            if (r3 == 0) goto L_0x0163
            java.lang.Object r3 = r11.next()     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.classcomponent.ClassDefinitionData r3 = (expo.modules.kotlin.classcomponent.ClassDefinitionData) r3     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.jni.JavaScriptModuleObject r4 = new expo.modules.kotlin.jni.JavaScriptModuleObject     // Catch:{ all -> 0x016c }
            java.lang.String r5 = r3.getName()     // Catch:{ all -> 0x016c }
            r4.<init>(r2, r5)     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.modules.Module r5 = r1.getModule()     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.AppContext r5 = r5.getAppContext()     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.objects.ObjectDefinitionData r6 = r3.getObjectDefinition()     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.jni.JavaScriptModuleObject r5 = r4.initUsingObjectDefinition(r5, r6)     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.jni.JNIDeallocator r4 = r0.getJniDeallocator()     // Catch:{ all -> 0x016c }
            r6 = r5
            expo.modules.kotlin.jni.Destructible r6 = (expo.modules.kotlin.jni.Destructible) r6     // Catch:{ all -> 0x016c }
            r4.addReference(r6)     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.functions.SyncFunctionComponent r4 = r3.getConstructor()     // Catch:{ all -> 0x016c }
            java.lang.String r6 = r3.getName()     // Catch:{ all -> 0x016c }
            boolean r7 = r4.getTakesOwner$expo_modules_core_release()     // Catch:{ all -> 0x016c }
            java.util.List r8 = r4.getCppRequiredTypes()     // Catch:{ all -> 0x016c }
            java.util.Collection r8 = (java.util.Collection) r8     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.jni.ExpectedType[] r12 = new expo.modules.kotlin.jni.ExpectedType[r10]     // Catch:{ all -> 0x016c }
            java.lang.Object[] r8 = r8.toArray(r12)     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.jni.ExpectedType[] r8 = (expo.modules.kotlin.jni.ExpectedType[]) r8     // Catch:{ all -> 0x016c }
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x016c }
            expo.modules.kotlin.jni.JNIFunctionBody r12 = r4.getJNIFunctionBody$expo_modules_core_release(r3, r0)     // Catch:{ all -> 0x016c }
            r3 = r9
            r4 = r6
            r6 = r7
            r7 = r8
            r8 = r12
            r3.registerClass(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x016c }
            goto L_0x0109
        L_0x0163:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x016c }
            androidx.tracing.Trace.endSection()     // Catch:{ all -> 0x0171 }
            androidx.tracing.Trace.endSection()
            return r9
        L_0x016c:
            r0 = move-exception
            androidx.tracing.Trace.endSection()     // Catch:{ all -> 0x0171 }
            throw r0     // Catch:{ all -> 0x0171 }
        L_0x0171:
            r0 = move-exception
            androidx.tracing.Trace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.ModuleHolder$jsObject$2.invoke():expo.modules.kotlin.jni.JavaScriptModuleObject");
    }
}
