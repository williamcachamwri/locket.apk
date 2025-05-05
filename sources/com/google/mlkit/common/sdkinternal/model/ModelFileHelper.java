package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import java.io.File;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class ModelFileHelper {
    public static final int INVALID_INDEX = -1;
    public static final String zza = String.format("com.google.mlkit.%s.models", new Object[]{"translate"});
    public static final String zzb = String.format("com.google.mlkit.%s.models", new Object[]{"custom"});
    static final String zzc = String.format("com.google.mlkit.%s.models", new Object[]{"base"});
    private static final GmsLogger zzd = new GmsLogger("ModelFileHelper", "");
    private final MlKitContext zze;

    public ModelFileHelper(MlKitContext mlKitContext) {
        this.zze = mlKitContext;
    }

    private final File zzc(String str, ModelType modelType, boolean z) throws MlKitException {
        File modelDirUnsafe = getModelDirUnsafe(str, modelType, z);
        if (!modelDirUnsafe.exists()) {
            zzd.d("ModelFileHelper", "model folder does not exist, creating one: ".concat(String.valueOf(modelDirUnsafe.getAbsolutePath())));
            if (!modelDirUnsafe.mkdirs()) {
                throw new MlKitException("Failed to create model folder: ".concat(String.valueOf(String.valueOf(modelDirUnsafe))), 13);
            }
        } else if (!modelDirUnsafe.isDirectory()) {
            throw new MlKitException("Can not create model folder, since an existing file has the same name: ".concat(String.valueOf(String.valueOf(modelDirUnsafe))), 6);
        }
        return modelDirUnsafe;
    }

    public synchronized void deleteAllModels(ModelType modelType, String str) {
        deleteRecursively(getModelDirUnsafe(str, modelType, false));
        deleteRecursively(getModelDirUnsafe(str, modelType, true));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r5 != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean deleteRecursively(java.io.File r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8.isDirectory()
            r2 = 1
            if (r1 == 0) goto L_0x002c
            java.io.File[] r1 = r8.listFiles()
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)
            java.io.File[] r1 = (java.io.File[]) r1
            int r3 = r1.length
            r4 = r0
            r5 = r2
        L_0x0018:
            if (r4 >= r3) goto L_0x002a
            r6 = r1[r4]
            if (r5 == 0) goto L_0x0026
            boolean r5 = r7.deleteRecursively(r6)
            if (r5 == 0) goto L_0x0026
            r5 = r2
            goto L_0x0027
        L_0x0026:
            r5 = r0
        L_0x0027:
            int r4 = r4 + 1
            goto L_0x0018
        L_0x002a:
            if (r5 == 0) goto L_0x0033
        L_0x002c:
            boolean r8 = r8.delete()
            if (r8 == 0) goto L_0x0033
            return r2
        L_0x0033:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.ModelFileHelper.deleteRecursively(java.io.File):boolean");
    }

    public void deleteTempFilesInPrivateFolder(String str, ModelType modelType) throws MlKitException {
        File zzc2 = zzc(str, modelType, true);
        if (!deleteRecursively(zzc2)) {
            zzd.e("ModelFileHelper", "Failed to delete the temp labels file directory: ".concat(String.valueOf(zzc2 != null ? zzc2.getAbsolutePath() : null)));
        }
    }

    public int getLatestCachedModelVersion(File file) {
        File[] listFiles = file.listFiles();
        int i = -1;
        if (!(listFiles == null || (r1 = listFiles.length) == 0)) {
            for (File file2 : listFiles) {
                try {
                    i = Math.max(i, Integer.parseInt(file2.getName()));
                } catch (NumberFormatException unused) {
                    zzd.d("ModelFileHelper", "Contains non-integer file name ".concat(String.valueOf(file2.getName())));
                }
            }
        }
        return i;
    }

    public File getModelDir(String str, ModelType modelType) throws MlKitException {
        return zzc(str, modelType, false);
    }

    public File getModelDirUnsafe(String str, ModelType modelType, boolean z) {
        String str2;
        ModelType modelType2 = ModelType.UNKNOWN;
        int ordinal = modelType.ordinal();
        if (ordinal == 1) {
            str2 = zzc;
        } else if (ordinal == 2) {
            str2 = zza;
        } else if (ordinal == 4) {
            str2 = zzb;
        } else {
            String name = modelType.name();
            throw new IllegalArgumentException("Unknown model type " + name + ". Cannot find a dir to store the downloaded model.");
        }
        File file = new File(this.zze.getApplicationContext().getNoBackupFilesDir(), str2);
        if (z) {
            file = new File(file, "temp");
        }
        return new File(file, str);
    }

    public File getModelTempDir(String str, ModelType modelType) throws MlKitException {
        return zzc(str, modelType, true);
    }

    public File getTempFileInPrivateFolder(String str, ModelType modelType, String str2) throws MlKitException {
        File zzc2 = zzc(str, modelType, true);
        if (!zzc2.exists() || !zzc2.isFile() || zzc2.delete()) {
            if (!zzc2.exists()) {
                zzd.d("ModelFileHelper", "Temp labels folder does not exist, creating one: ".concat(String.valueOf(zzc2.getAbsolutePath())));
                if (!zzc2.mkdirs()) {
                    throw new MlKitException("Failed to create a directory to hold the AutoML model's labels file.", 13);
                }
            }
            return new File(zzc2, str2);
        }
        throw new MlKitException("Failed to delete the temp labels file: ".concat(String.valueOf(zzc2.getAbsolutePath())), 13);
    }

    public boolean modelExistsLocally(String str, ModelType modelType) throws MlKitException {
        String zzb2;
        if (modelType == ModelType.UNKNOWN || (zzb2 = zzb(str, modelType)) == null) {
            return false;
        }
        File file = new File(zzb2);
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(file, Constants.MODEL_FILE_NAME);
        zzd.i("ModelFileHelper", "Model file path: ".concat(String.valueOf(file2.getAbsolutePath())));
        return file2.exists();
    }

    public final File zza(String str, ModelType modelType) throws MlKitException {
        return zzc(str, modelType, true);
    }

    public final String zzb(String str, ModelType modelType) throws MlKitException {
        File modelDir = getModelDir(str, modelType);
        int latestCachedModelVersion = getLatestCachedModelVersion(modelDir);
        if (latestCachedModelVersion == -1) {
            return null;
        }
        String absolutePath = modelDir.getAbsolutePath();
        return absolutePath + "/" + latestCachedModelVersion;
    }
}
