package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.internal.model.zza;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class CustomModelLoader {
    private static final GmsLogger zza = new GmsLogger("CustomModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final LocalModel zzd;
    private final CustomRemoteModel zze;
    private final RemoteModelDownloadManager zzf;
    private final RemoteModelFileManager zzg;
    private final zzsh zzh;
    private boolean zzi;

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    public interface CustomModelLoaderHelper {
        void logLoad() throws MlKitException;

        boolean tryLoad(LocalModel localModel) throws MlKitException;
    }

    private CustomModelLoader(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        if (customRemoteModel != null) {
            RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, customRemoteModel, (ModelValidator) null, new ModelFileHelper(mlKitContext), new zza(mlKitContext, customRemoteModel.getUniqueModelNameForPersist()));
            this.zzg = remoteModelFileManager;
            this.zzf = RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
            this.zzi = true;
        } else {
            this.zzg = null;
            this.zzf = null;
        }
        this.zzc = mlKitContext;
        this.zzd = localModel;
        this.zze = customRemoteModel;
        this.zzh = zzss.zzb("common");
    }

    public static synchronized CustomModelLoader getInstance(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        String str;
        CustomModelLoader customModelLoader;
        synchronized (CustomModelLoader.class) {
            if (customRemoteModel == null) {
                str = ((LocalModel) Preconditions.checkNotNull(localModel)).toString();
            } else {
                str = customRemoteModel.getUniqueModelNameForPersist();
            }
            Map map = zzb;
            if (!map.containsKey(str)) {
                map.put(str, new CustomModelLoader(mlKitContext, localModel, customRemoteModel));
            }
            customModelLoader = (CustomModelLoader) map.get(str);
        }
        return customModelLoader;
    }

    private final File zza() throws MlKitException {
        String zzb2 = ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzb();
        if (zzb2 == null) {
            zza.d("CustomModelLoader", "No existing model file");
            return null;
        }
        File file = new File(zzb2);
        File[] listFiles = file.listFiles();
        return ((File[]) Preconditions.checkNotNull(listFiles)).length == 1 ? listFiles[0] : file;
    }

    private final void zzb() throws MlKitException {
        ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).removeOrCancelDownload();
    }

    private static final LocalModel zzc(File file) {
        if (file.isDirectory()) {
            LocalModel.Builder builder = new LocalModel.Builder();
            builder.setAbsoluteManifestFilePath(new File(file.getAbsolutePath(), Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME).toString());
            return builder.build();
        }
        LocalModel.Builder builder2 = new LocalModel.Builder();
        builder2.setAbsoluteFilePath(file.getAbsolutePath());
        return builder2.build();
    }

    public synchronized LocalModel createLocalModelByLatestExistingModel() throws MlKitException {
        zza.d("CustomModelLoader", "Try to get the latest existing model file.");
        File zza2 = zza();
        if (zza2 == null) {
            return null;
        }
        return zzc(zza2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a3 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a5 A[SYNTHETIC, Splitter:B:24:0x00a5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.google.mlkit.common.model.LocalModel createLocalModelByNewlyDownloadedModel() throws com.google.mlkit.common.MlKitException {
        /*
            r7 = this;
            java.lang.String r0 = "Download Status code: "
            monitor-enter(r7)
            com.google.android.gms.common.internal.GmsLogger r1 = zza     // Catch:{ all -> 0x00ab }
            java.lang.String r2 = "CustomModelLoader"
            java.lang.String r3 = "Try to get newly downloaded model file."
            r1.d(r2, r3)     // Catch:{ all -> 0x00ab }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r2 = r7.zzf     // Catch:{ all -> 0x00ab }
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x00ab }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r2 = (com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager) r2     // Catch:{ all -> 0x00ab }
            java.lang.Long r2 = r2.getDownloadingId()     // Catch:{ all -> 0x00ab }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r3 = r7.zzf     // Catch:{ all -> 0x00ab }
            java.lang.String r3 = r3.getDownloadingModelHash()     // Catch:{ all -> 0x00ab }
            r4 = 0
            if (r2 == 0) goto L_0x0096
            if (r3 != 0) goto L_0x0025
            goto L_0x0096
        L_0x0025:
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r5 = r7.zzf     // Catch:{ all -> 0x00ab }
            java.lang.Integer r5 = r5.getDownloadingModelStatusCode()     // Catch:{ all -> 0x00ab }
            if (r5 != 0) goto L_0x0031
            r7.zzb()     // Catch:{ all -> 0x00ab }
            goto L_0x00a0
        L_0x0031:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ab }
            r6.<init>(r0)     // Catch:{ all -> 0x00ab }
            java.lang.String r0 = "Download Status code: "
            r6.append(r5)     // Catch:{ all -> 0x00ab }
            java.lang.String r6 = r5.toString()     // Catch:{ all -> 0x00ab }
            java.lang.String r0 = r0.concat(r6)     // Catch:{ all -> 0x00ab }
            java.lang.String r6 = "CustomModelLoader"
            r1.d(r6, r0)     // Catch:{ all -> 0x00ab }
            int r0 = r5.intValue()     // Catch:{ all -> 0x00ab }
            r6 = 8
            if (r0 != r6) goto L_0x0072
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r7.zzf     // Catch:{ all -> 0x00ab }
            java.io.File r0 = r0.zzi(r3)     // Catch:{ all -> 0x00ab }
            if (r0 != 0) goto L_0x0059
            goto L_0x00a0
        L_0x0059:
            java.lang.String r2 = r0.getParent()     // Catch:{ all -> 0x00ab }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00ab }
            java.lang.String r5 = "Moved the downloaded model to private folder successfully: "
            java.lang.String r6 = "CustomModelLoader"
            java.lang.String r2 = r5.concat(r2)     // Catch:{ all -> 0x00ab }
            r1.d(r6, r2)     // Catch:{ all -> 0x00ab }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r7.zzf     // Catch:{ all -> 0x00ab }
            r1.updateLatestModelHashAndType(r3)     // Catch:{ all -> 0x00ab }
            goto L_0x00a1
        L_0x0072:
            int r0 = r5.intValue()     // Catch:{ all -> 0x00ab }
            r1 = 16
            if (r0 != r1) goto L_0x00a0
            com.google.android.gms.internal.mlkit_common.zzsh r0 = r7.zzh     // Catch:{ all -> 0x00ab }
            com.google.mlkit.common.model.CustomRemoteModel r1 = r7.zze     // Catch:{ all -> 0x00ab }
            com.google.android.gms.internal.mlkit_common.zzry r3 = com.google.android.gms.internal.mlkit_common.zzsk.zzg()     // Catch:{ all -> 0x00ab }
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)     // Catch:{ all -> 0x00ab }
            com.google.mlkit.common.model.RemoteModel r1 = (com.google.mlkit.common.model.RemoteModel) r1     // Catch:{ all -> 0x00ab }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r5 = r7.zzf     // Catch:{ all -> 0x00ab }
            int r2 = r5.getFailureReason(r2)     // Catch:{ all -> 0x00ab }
            r5 = 0
            r0.zze(r3, r1, r5, r2)     // Catch:{ all -> 0x00ab }
            r7.zzb()     // Catch:{ all -> 0x00ab }
            goto L_0x00a0
        L_0x0096:
            java.lang.String r0 = "CustomModelLoader"
            java.lang.String r2 = "No new model is downloading."
            r1.d(r0, r2)     // Catch:{ all -> 0x00ab }
            r7.zzb()     // Catch:{ all -> 0x00ab }
        L_0x00a0:
            r0 = r4
        L_0x00a1:
            if (r0 != 0) goto L_0x00a5
            monitor-exit(r7)
            return r4
        L_0x00a5:
            com.google.mlkit.common.model.LocalModel r0 = zzc(r0)     // Catch:{ all -> 0x00ab }
            monitor-exit(r7)
            return r0
        L_0x00ab:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.CustomModelLoader.createLocalModelByNewlyDownloadedModel():com.google.mlkit.common.model.LocalModel");
    }

    public void deleteLatestExistingModel() throws MlKitException {
        File zza2 = zza();
        if (zza2 != null) {
            ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzc(zza2);
            SharedPrefManager.getInstance(this.zzc).clearLatestModelHash((RemoteModel) Preconditions.checkNotNull(this.zze));
        }
    }

    public void deleteOldModels(LocalModel localModel) throws MlKitException {
        File parentFile = new File((String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath())).getParentFile();
        if (((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzd((File) Preconditions.checkNotNull(parentFile))) {
            zza.d("CustomModelLoader", "All old models are deleted.");
            this.zzg.zza(parentFile);
            return;
        }
        zza.e("CustomModelLoader", "Failed to delete old models");
    }

    public synchronized void load(CustomModelLoaderHelper customModelLoaderHelper) throws MlKitException {
        LocalModel localModel;
        LocalModel localModel2 = this.zzd;
        if (localModel2 == null) {
            localModel2 = createLocalModelByNewlyDownloadedModel();
        }
        if (localModel2 == null) {
            localModel2 = createLocalModelByLatestExistingModel();
        }
        if (localModel != null) {
            while (!customModelLoaderHelper.tryLoad(localModel)) {
                if (this.zze != null) {
                    deleteLatestExistingModel();
                    localModel = createLocalModelByLatestExistingModel();
                    continue;
                } else {
                    localModel = null;
                    continue;
                }
                if (localModel == null) {
                    customModelLoaderHelper.logLoad();
                    return;
                }
            }
            if (this.zze != null && this.zzi) {
                deleteOldModels((LocalModel) Preconditions.checkNotNull(localModel));
                this.zzi = false;
            }
            customModelLoaderHelper.logLoad();
            return;
        }
        throw new MlKitException("Model is not available.", 14);
    }
}
