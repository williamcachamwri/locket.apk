package com.google.mlkit.common.sdkinternal.model;

import android.net.Uri;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ktx.BuildConfig;
import com.google.mlkit.common.MlKitException;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class ModelLoader {
    private static final GmsLogger zza = new GmsLogger("ModelLoader", "");
    public final LocalModelLoader localModelLoader;
    protected ModelLoadingState modelLoadingState = ModelLoadingState.NO_MODEL_LOADED;
    public final RemoteModelLoader remoteModelLoader;
    private final ModelLoadingLogger zzb;

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    public interface ModelContentHandler {
        void constructModel(MappedByteBuffer mappedByteBuffer) throws MlKitException;
    }

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    public interface ModelLoadingLogger {
        void logErrorCodes(List<Integer> list);
    }

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    protected enum ModelLoadingState {
        NO_MODEL_LOADED,
        REMOTE_MODEL_LOADED,
        LOCAL_MODEL_LOADED
    }

    public ModelLoader(RemoteModelLoader remoteModelLoader2, LocalModelLoader localModelLoader2, ModelLoadingLogger modelLoadingLogger) {
        boolean z = true;
        if (remoteModelLoader2 == null && localModelLoader2 == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "At least one of RemoteModelLoader or LocalModelLoader must be non-null.");
        Preconditions.checkNotNull(modelLoadingLogger);
        this.remoteModelLoader = remoteModelLoader2;
        this.localModelLoader = localModelLoader2;
        this.zzb = modelLoadingLogger;
    }

    private final String zza() {
        LocalModelLoader localModelLoader2 = this.localModelLoader;
        String str = null;
        if (localModelLoader2 != null) {
            if (localModelLoader2.getLocalModel().getAssetFilePath() != null) {
                str = this.localModelLoader.getLocalModel().getAssetFilePath();
            } else if (this.localModelLoader.getLocalModel().getAbsoluteFilePath() != null) {
                str = this.localModelLoader.getLocalModel().getAbsoluteFilePath();
            } else if (this.localModelLoader.getLocalModel().getUri() != null) {
                str = ((Uri) Preconditions.checkNotNull(this.localModelLoader.getLocalModel().getUri())).toString();
            }
        }
        RemoteModelLoader remoteModelLoader2 = this.remoteModelLoader;
        return String.format("Local model path: %s. Remote model name: %s. ", new Object[]{str, remoteModelLoader2 == null ? BuildConfig.VERSION_NAME : remoteModelLoader2.getRemoteModel().getUniqueModelNameForPersist()});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzb(com.google.mlkit.common.sdkinternal.model.ModelLoader.ModelContentHandler r2, java.util.List r3) throws com.google.mlkit.common.MlKitException {
        /*
            r1 = this;
            monitor-enter(r1)
            com.google.mlkit.common.sdkinternal.model.LocalModelLoader r0 = r1.localModelLoader     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0025
            java.nio.MappedByteBuffer r0 = r0.load()     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0025
            r2.constructModel(r0)     // Catch:{ RuntimeException -> 0x001a }
            com.google.android.gms.common.internal.GmsLogger r2 = zza     // Catch:{ all -> 0x0028 }
            java.lang.String r3 = "ModelLoader"
            java.lang.String r0 = "Local model source is loaded successfully"
            r2.d(r3, r0)     // Catch:{ all -> 0x0028 }
            monitor-exit(r1)
            r2 = 1
            return r2
        L_0x001a:
            r2 = move-exception
            r0 = 18
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0028 }
            r3.add(r0)     // Catch:{ all -> 0x0028 }
            throw r2     // Catch:{ all -> 0x0028 }
        L_0x0025:
            monitor-exit(r1)
            r2 = 0
            return r2
        L_0x0028:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.ModelLoader.zzb(com.google.mlkit.common.sdkinternal.model.ModelLoader$ModelContentHandler, java.util.List):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzc(com.google.mlkit.common.sdkinternal.model.ModelLoader.ModelContentHandler r4, java.util.List r5) throws com.google.mlkit.common.MlKitException {
        /*
            r3 = this;
            monitor-enter(r3)
            com.google.mlkit.common.sdkinternal.model.RemoteModelLoader r0 = r3.remoteModelLoader     // Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x004c
            java.nio.MappedByteBuffer r0 = r0.load()     // Catch:{ MlKitException -> 0x0038 }
            if (r0 == 0) goto L_0x0025
            r4.constructModel(r0)     // Catch:{ RuntimeException -> 0x001a }
            com.google.android.gms.common.internal.GmsLogger r4 = zza     // Catch:{ all -> 0x004f }
            java.lang.String r5 = "ModelLoader"
            java.lang.String r0 = "Remote model source is loaded successfully"
            r4.d(r5, r0)     // Catch:{ all -> 0x004f }
            monitor-exit(r3)
            r4 = 1
            return r4
        L_0x001a:
            r4 = move-exception
            r0 = 19
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x004f }
            r5.add(r0)     // Catch:{ all -> 0x004f }
            throw r4     // Catch:{ all -> 0x004f }
        L_0x0025:
            com.google.android.gms.common.internal.GmsLogger r4 = zza     // Catch:{ all -> 0x004f }
            java.lang.String r0 = "ModelLoader"
            java.lang.String r1 = "Remote model source can NOT be loaded, try local model."
            r4.d(r0, r1)     // Catch:{ all -> 0x004f }
            r4 = 21
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x004f }
            r5.add(r4)     // Catch:{ all -> 0x004f }
            goto L_0x004c
        L_0x0038:
            r4 = move-exception
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x004f }
            java.lang.String r1 = "ModelLoader"
            java.lang.String r2 = "Remote model source can NOT be loaded, try local model."
            r0.d(r1, r2)     // Catch:{ all -> 0x004f }
            r0 = 20
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x004f }
            r5.add(r0)     // Catch:{ all -> 0x004f }
            throw r4     // Catch:{ all -> 0x004f }
        L_0x004c:
            monitor-exit(r3)
            r4 = 0
            return r4
        L_0x004f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.ModelLoader.zzc(com.google.mlkit.common.sdkinternal.model.ModelLoader$ModelContentHandler, java.util.List):boolean");
    }

    public synchronized boolean isRemoteModelLoaded() {
        return this.modelLoadingState == ModelLoadingState.REMOTE_MODEL_LOADED;
    }

    public synchronized void loadWithModelContentHandler(ModelContentHandler modelContentHandler) throws MlKitException {
        Exception exc;
        boolean z;
        ArrayList arrayList = new ArrayList();
        Exception e = null;
        boolean z2 = false;
        try {
            z = zzc(modelContentHandler, arrayList);
            exc = null;
        } catch (Exception e2) {
            exc = e2;
            z = false;
        }
        if (z) {
            this.zzb.logErrorCodes(arrayList);
            this.modelLoadingState = ModelLoadingState.REMOTE_MODEL_LOADED;
            return;
        }
        try {
            z2 = zzb(modelContentHandler, arrayList);
        } catch (Exception e3) {
            e = e3;
        }
        if (z2) {
            this.zzb.logErrorCodes(arrayList);
            this.modelLoadingState = ModelLoadingState.LOCAL_MODEL_LOADED;
            return;
        }
        arrayList.add(17);
        this.zzb.logErrorCodes(arrayList);
        this.modelLoadingState = ModelLoadingState.NO_MODEL_LOADED;
        if (exc != null) {
            throw new MlKitException("Remote model load failed with the model options: ".concat(String.valueOf(zza())), 14, exc);
        } else if (e != null) {
            throw new MlKitException("Local model load failed with the model options: ".concat(String.valueOf(zza())), 14, e);
        } else {
            throw new MlKitException("Cannot load any model with the model options: ".concat(String.valueOf(zza())), 14);
        }
    }
}
