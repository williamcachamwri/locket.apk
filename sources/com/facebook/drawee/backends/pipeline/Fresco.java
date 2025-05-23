package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

public class Fresco {
    private static final Class<?> TAG = Fresco.class;
    private static PipelineDraweeControllerBuilderSupplier sDraweeControllerBuilderSupplier = null;
    private static volatile boolean sIsInitialized = false;

    private Fresco() {
    }

    public static void initialize(Context context) {
        initialize(context, (ImagePipelineConfig) null, (DraweeConfig) null);
    }

    public static void initialize(Context context, @Nullable ImagePipelineConfig imagePipelineConfig) {
        initialize(context, imagePipelineConfig, (DraweeConfig) null);
    }

    public static void initialize(Context context, @Nullable ImagePipelineConfig imagePipelineConfig, @Nullable DraweeConfig draweeConfig) {
        initialize(context, imagePipelineConfig, draweeConfig, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() != false) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(new com.facebook.soloader.nativeloader.SystemDelegate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005e, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(new com.facebook.soloader.nativeloader.SystemDelegate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006d, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(new com.facebook.soloader.nativeloader.SystemDelegate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007c, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(new com.facebook.soloader.nativeloader.SystemDelegate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008b, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        com.facebook.imagepipeline.systrace.FrescoSystrace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0095, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() != false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0097, code lost:
        com.facebook.imagepipeline.systrace.FrescoSystrace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009a, code lost:
        throw r4;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:18:0x0052, B:22:0x0061, B:26:0x0070, B:30:0x007f] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0052 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0061 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0070 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x007f */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:26:0x0070=Splitter:B:26:0x0070, B:22:0x0061=Splitter:B:22:0x0061, B:18:0x0052=Splitter:B:18:0x0052, B:30:0x007f=Splitter:B:30:0x007f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initialize(android.content.Context r4, @javax.annotation.Nullable com.facebook.imagepipeline.core.ImagePipelineConfig r5, @javax.annotation.Nullable com.facebook.drawee.backends.pipeline.DraweeConfig r6, boolean r7) {
        /*
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = "Fresco#initialize"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
        L_0x000b:
            boolean r0 = sIsInitialized
            r1 = 1
            if (r0 == 0) goto L_0x0018
            java.lang.Class<?> r0 = TAG
            java.lang.String r2 = "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!"
            com.facebook.common.logging.FLog.w((java.lang.Class<?>) r0, (java.lang.String) r2)
            goto L_0x001a
        L_0x0018:
            sIsInitialized = r1
        L_0x001a:
            com.facebook.imagepipeline.core.NativeCodeSetup.setUseNativeCode(r7)
            boolean r7 = com.facebook.soloader.nativeloader.NativeLoader.isInitialized()
            if (r7 != 0) goto L_0x009b
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L_0x002e
            java.lang.String r7 = "Fresco.initialize->SoLoader.init"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r7)
        L_0x002e:
            java.lang.String r7 = "com.facebook.imagepipeline.nativecode.NativeCodeInitializer"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{ ClassNotFoundException -> 0x007f, IllegalAccessException -> 0x0070, InvocationTargetException -> 0x0061, NoSuchMethodException -> 0x0052 }
            java.lang.String r0 = "init"
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x007f, IllegalAccessException -> 0x0070, InvocationTargetException -> 0x0061, NoSuchMethodException -> 0x0052 }
            java.lang.Class<android.content.Context> r2 = android.content.Context.class
            r3 = 0
            r1[r3] = r2     // Catch:{ ClassNotFoundException -> 0x007f, IllegalAccessException -> 0x0070, InvocationTargetException -> 0x0061, NoSuchMethodException -> 0x0052 }
            java.lang.reflect.Method r7 = r7.getMethod(r0, r1)     // Catch:{ ClassNotFoundException -> 0x007f, IllegalAccessException -> 0x0070, InvocationTargetException -> 0x0061, NoSuchMethodException -> 0x0052 }
            java.lang.Object[] r0 = new java.lang.Object[]{r4}     // Catch:{ ClassNotFoundException -> 0x007f, IllegalAccessException -> 0x0070, InvocationTargetException -> 0x0061, NoSuchMethodException -> 0x0052 }
            r1 = 0
            r7.invoke(r1, r0)     // Catch:{ ClassNotFoundException -> 0x007f, IllegalAccessException -> 0x0070, InvocationTargetException -> 0x0061, NoSuchMethodException -> 0x0052 }
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L_0x009b
            goto L_0x008d
        L_0x0050:
            r4 = move-exception
            goto L_0x0091
        L_0x0052:
            com.facebook.soloader.nativeloader.SystemDelegate r7 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch:{ all -> 0x0050 }
            r7.<init>()     // Catch:{ all -> 0x0050 }
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r7)     // Catch:{ all -> 0x0050 }
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L_0x009b
            goto L_0x008d
        L_0x0061:
            com.facebook.soloader.nativeloader.SystemDelegate r7 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch:{ all -> 0x0050 }
            r7.<init>()     // Catch:{ all -> 0x0050 }
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r7)     // Catch:{ all -> 0x0050 }
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L_0x009b
            goto L_0x008d
        L_0x0070:
            com.facebook.soloader.nativeloader.SystemDelegate r7 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch:{ all -> 0x0050 }
            r7.<init>()     // Catch:{ all -> 0x0050 }
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r7)     // Catch:{ all -> 0x0050 }
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L_0x009b
            goto L_0x008d
        L_0x007f:
            com.facebook.soloader.nativeloader.SystemDelegate r7 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch:{ all -> 0x0050 }
            r7.<init>()     // Catch:{ all -> 0x0050 }
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r7)     // Catch:{ all -> 0x0050 }
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r7 == 0) goto L_0x009b
        L_0x008d:
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
            goto L_0x009b
        L_0x0091:
            boolean r5 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r5 == 0) goto L_0x009a
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x009a:
            throw r4
        L_0x009b:
            android.content.Context r4 = r4.getApplicationContext()
            if (r5 != 0) goto L_0x00a5
            com.facebook.imagepipeline.core.ImagePipelineFactory.initialize((android.content.Context) r4)
            goto L_0x00a8
        L_0x00a5:
            com.facebook.imagepipeline.core.ImagePipelineFactory.initialize((com.facebook.imagepipeline.core.ImagePipelineConfigInterface) r5)
        L_0x00a8:
            initializeDrawee(r4, r6)
            boolean r4 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r4 == 0) goto L_0x00b4
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x00b4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.backends.pipeline.Fresco.initialize(android.content.Context, com.facebook.imagepipeline.core.ImagePipelineConfig, com.facebook.drawee.backends.pipeline.DraweeConfig, boolean):void");
    }

    private static void initializeDrawee(Context context, @Nullable DraweeConfig draweeConfig) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("Fresco.initializeDrawee");
        }
        PipelineDraweeControllerBuilderSupplier pipelineDraweeControllerBuilderSupplier = new PipelineDraweeControllerBuilderSupplier(context, draweeConfig);
        sDraweeControllerBuilderSupplier = pipelineDraweeControllerBuilderSupplier;
        SimpleDraweeView.initialize(pipelineDraweeControllerBuilderSupplier);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    public static PipelineDraweeControllerBuilderSupplier getDraweeControllerBuilderSupplier() {
        return sDraweeControllerBuilderSupplier;
    }

    public static PipelineDraweeControllerBuilder newDraweeControllerBuilder() {
        return sDraweeControllerBuilderSupplier.get();
    }

    public static ImagePipelineFactory getImagePipelineFactory() {
        return ImagePipelineFactory.getInstance();
    }

    public static ImagePipeline getImagePipeline() {
        return getImagePipelineFactory().getImagePipeline();
    }

    public static void shutDown() {
        sDraweeControllerBuilderSupplier = null;
        SimpleDraweeView.shutDown();
        ImagePipelineFactory.shutDown();
    }

    public static boolean hasBeenInitialized() {
        return sIsInitialized;
    }
}
