package expo.modules.imagemanipulator;

import android.content.Context;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lexpo/modules/imagemanipulator/ImageManipulatorModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "runActions", "", "bitmap", "Landroid/graphics/Bitmap;", "actions", "Lexpo/modules/imagemanipulator/arguments/Actions;", "saveOptions", "Lexpo/modules/imagemanipulator/SaveOptions;", "promise", "Lexpo/modules/kotlin/Promise;", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageManipulatorModule.kt */
public final class ImageManipulatorModule extends Module {
    private final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoImageManipulator");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("manipulateAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$1.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), false, ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$2.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(SaveOptions.class), false, ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$3.INSTANCE))}, new ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$4(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("manipulateAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$5.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), false, ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$6.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(SaveOptions.class), false, ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$7.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$8.INSTANCE))}, new ImageManipulatorModule$definition$lambda$1$$inlined$AsyncFunction$9(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("manipulateAsync", asyncFunction);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0075, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0079, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a5, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a6, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a9, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void runActions(android.graphics.Bitmap r7, expo.modules.imagemanipulator.arguments.Actions r8, expo.modules.imagemanipulator.SaveOptions r9, expo.modules.kotlin.Promise r10) {
        /*
            r6 = this;
            java.util.List r8 = r8.getActions()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
        L_0x000a:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x001b
            java.lang.Object r0 = r8.next()
            expo.modules.imagemanipulator.actions.Action r0 = (expo.modules.imagemanipulator.actions.Action) r0
            android.graphics.Bitmap r7 = r0.run(r7)
            goto L_0x000a
        L_0x001b:
            expo.modules.imagemanipulator.FileUtils r8 = expo.modules.imagemanipulator.FileUtils.INSTANCE
            android.content.Context r0 = r6.getContext()
            android.graphics.Bitmap$CompressFormat r1 = r9.getCompressFormat()
            java.lang.String r8 = r8.generateRandomOutputPath(r0, r1)
            double r0 = r9.getCompress()
            r2 = 100
            double r2 = (double) r2
            double r0 = r0 * r2
            int r0 = (int) r0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            r1.<init>(r8)
            java.io.FileOutputStream r1 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r1, (java.lang.String) r8)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = r1
            java.io.FileOutputStream r2 = (java.io.FileOutputStream) r2     // Catch:{ all -> 0x00a3 }
            android.graphics.Bitmap$CompressFormat r3 = r9.getCompressFormat()     // Catch:{ all -> 0x00a3 }
            java.io.OutputStream r2 = (java.io.OutputStream) r2     // Catch:{ all -> 0x00a3 }
            r7.compress(r3, r0, r2)     // Catch:{ all -> 0x00a3 }
            boolean r2 = r9.getBase64()     // Catch:{ all -> 0x00a3 }
            r3 = 0
            if (r2 == 0) goto L_0x007a
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x00a3 }
            r2.<init>()     // Catch:{ all -> 0x00a3 }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ all -> 0x00a3 }
            r4 = r2
            java.io.ByteArrayOutputStream r4 = (java.io.ByteArrayOutputStream) r4     // Catch:{ all -> 0x0073 }
            android.graphics.Bitmap$CompressFormat r9 = r9.getCompressFormat()     // Catch:{ all -> 0x0073 }
            r5 = r4
            java.io.OutputStream r5 = (java.io.OutputStream) r5     // Catch:{ all -> 0x0073 }
            r7.compress(r9, r0, r5)     // Catch:{ all -> 0x0073 }
            byte[] r9 = r4.toByteArray()     // Catch:{ all -> 0x0073 }
            r0 = 2
            java.lang.String r9 = android.util.Base64.encodeToString(r9, r0)     // Catch:{ all -> 0x0073 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0073 }
            kotlin.io.CloseableKt.closeFinally(r2, r3)     // Catch:{ all -> 0x00a3 }
            goto L_0x007b
        L_0x0073:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r8 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r7)     // Catch:{ all -> 0x00a3 }
            throw r8     // Catch:{ all -> 0x00a3 }
        L_0x007a:
            r9 = r3
        L_0x007b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00a3 }
            kotlin.io.CloseableKt.closeFinally(r1, r3)
            expo.modules.imagemanipulator.ImageResult r0 = new expo.modules.imagemanipulator.ImageResult
            java.io.File r1 = new java.io.File
            r1.<init>(r8)
            android.net.Uri r8 = android.net.Uri.fromFile(r1)
            java.lang.String r8 = r8.toString()
            java.lang.String r1 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)
            int r1 = r7.getWidth()
            int r7 = r7.getHeight()
            r0.<init>(r8, r1, r7, r9)
            r10.resolve(r0)
            return
        L_0x00a3:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00a5 }
        L_0x00a5:
            r8 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagemanipulator.ImageManipulatorModule.runActions(android.graphics.Bitmap, expo.modules.imagemanipulator.arguments.Actions, expo.modules.imagemanipulator.SaveOptions, expo.modules.kotlin.Promise):void");
    }
}
