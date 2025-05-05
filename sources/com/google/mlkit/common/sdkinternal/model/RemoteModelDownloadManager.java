package com.google.mlkit.common.sdkinternal.model;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.LongSparseArray;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzmu;
import com.google.android.gms.internal.mlkit_common.zzna;
import com.google.android.gms.internal.mlkit_common.zzry;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import io.sentry.clientreport.DiscardedEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class RemoteModelDownloadManager {
    /* access modifiers changed from: private */
    public static final GmsLogger zza = new GmsLogger("ModelDownloadManager", "");
    private static final Map zzb = new HashMap();
    /* access modifiers changed from: private */
    public final LongSparseArray zzc = new LongSparseArray();
    /* access modifiers changed from: private */
    public final LongSparseArray zzd = new LongSparseArray();
    /* access modifiers changed from: private */
    public final MlKitContext zze;
    private final DownloadManager zzf;
    /* access modifiers changed from: private */
    public final RemoteModel zzg;
    private final ModelType zzh;
    /* access modifiers changed from: private */
    public final zzsh zzi;
    private final SharedPrefManager zzj;
    private final ModelFileHelper zzk;
    private final ModelInfoRetrieverInterop zzl;
    private final RemoteModelFileManager zzm;
    private DownloadConditions zzn;

    RemoteModelDownloadManager(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop modelInfoRetrieverInterop, zzsh zzsh) {
        this.zze = mlKitContext;
        this.zzh = remoteModel.getModelType();
        this.zzg = remoteModel;
        DownloadManager downloadManager = (DownloadManager) mlKitContext.getApplicationContext().getSystemService("download");
        this.zzf = downloadManager;
        this.zzi = zzsh;
        if (downloadManager == null) {
            zza.d("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.zzk = modelFileHelper;
        this.zzj = SharedPrefManager.getInstance(mlKitContext);
        this.zzl = modelInfoRetrieverInterop;
        this.zzm = remoteModelFileManager;
    }

    public static synchronized RemoteModelDownloadManager getInstance(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop modelInfoRetrieverInterop) {
        RemoteModelDownloadManager remoteModelDownloadManager;
        synchronized (RemoteModelDownloadManager.class) {
            Map map = zzb;
            if (!map.containsKey(remoteModel)) {
                map.put(remoteModel, new RemoteModelDownloadManager(mlKitContext, remoteModel, modelFileHelper, remoteModelFileManager, modelInfoRetrieverInterop, zzss.zzb("common")));
            }
            remoteModelDownloadManager = (RemoteModelDownloadManager) map.get(remoteModel);
        }
        return remoteModelDownloadManager;
    }

    private final Task zzj(long j) {
        MlKitContext mlKitContext = this.zze;
        ContextCompat.registerReceiver(mlKitContext.getApplicationContext(), zzm(j), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), (String) null, MLTaskExecutor.getInstance().getHandler(), 2);
        return zzk(j).getTask();
    }

    private final synchronized TaskCompletionSource zzk(long j) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zzd.get(j);
        if (taskCompletionSource != null) {
            return taskCompletionSource;
        }
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        this.zzd.put(j, taskCompletionSource2);
        return taskCompletionSource2;
    }

    private final synchronized zzc zzm(long j) {
        zzc zzc2 = (zzc) this.zzc.get(j);
        if (zzc2 != null) {
            return zzc2;
        }
        zzc zzc3 = new zzc(this, j, zzk(j), (zzb) null);
        this.zzc.put(j, zzc3);
        return zzc3;
    }

    private final synchronized Long zzn(DownloadManager.Request request, ModelInfo modelInfo) {
        DownloadManager downloadManager = this.zzf;
        if (downloadManager == null) {
            return null;
        }
        long enqueue = downloadManager.enqueue(request);
        GmsLogger gmsLogger = zza;
        gmsLogger.d("ModelDownloadManager", "Schedule a new downloading task: " + enqueue);
        this.zzj.setDownloadingModelInfo(enqueue, modelInfo);
        this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, false, modelInfo.getModelType(), zzna.SCHEDULED);
        return Long.valueOf(enqueue);
    }

    private final synchronized Long zzo(ModelInfo modelInfo, DownloadConditions downloadConditions) throws MlKitException {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        String downloadingModelHash = this.zzj.getDownloadingModelHash(this.zzg);
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        if (downloadingModelHash == null || !downloadingModelHash.equals(modelInfo.getModelHash()) || downloadingModelStatusCode == null) {
            GmsLogger gmsLogger = zza;
            gmsLogger.d("ModelDownloadManager", "Need to download a new model.");
            removeOrCancelDownload();
            DownloadManager.Request request = new DownloadManager.Request(modelInfo.getModelUri());
            if (this.zzk.modelExistsLocally(modelInfo.getModelNameForPersist(), modelInfo.getModelType())) {
                gmsLogger.d("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
                this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, false, modelInfo.getModelType(), zzna.UPDATE_AVAILABLE);
            }
            request.setRequiresCharging(downloadConditions.isChargingRequired());
            if (downloadConditions.isWifiRequired()) {
                request.setAllowedNetworkTypes(2);
            }
            return zzn(request, modelInfo);
        }
        Integer downloadingModelStatusCode2 = getDownloadingModelStatusCode();
        if (downloadingModelStatusCode2 == null || !(downloadingModelStatusCode2.intValue() == 8 || downloadingModelStatusCode2.intValue() == 16)) {
            zzsh zzsh = this.zzi;
            RemoteModel remoteModel = this.zzg;
            zzsh.zzf(zzsk.zzg(), remoteModel, zzmu.NO_ERROR, false, remoteModel.getModelType(), zzna.DOWNLOADING);
        }
        zza.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
        return null;
    }

    public Task<Void> ensureModelDownloaded() {
        MlKitException mlKitException;
        ModelInfo modelInfo;
        this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, false, ModelType.UNKNOWN, zzna.EXPLICITLY_REQUESTED);
        Long l = null;
        try {
            modelInfo = zzg();
            mlKitException = null;
        } catch (MlKitException e) {
            mlKitException = e;
            modelInfo = null;
        }
        try {
            Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
            Long downloadingId = getDownloadingId();
            if (!modelExistsLocally()) {
                if (downloadingModelStatusCode == null || downloadingModelStatusCode.intValue() != 8) {
                    if (downloadingModelStatusCode != null && downloadingModelStatusCode.intValue() == 16) {
                        MlKitException zzl2 = zzl(downloadingId);
                        removeOrCancelDownload();
                        return Tasks.forException(zzl2);
                    } else if (downloadingModelStatusCode == null || (!(downloadingModelStatusCode.intValue() == 4 || downloadingModelStatusCode.intValue() == 2 || downloadingModelStatusCode.intValue() == 1) || downloadingId == null || getDownloadingModelHash() == null)) {
                        if (modelInfo != null) {
                            l = zzo(modelInfo, this.zzn);
                        }
                        if (l == null) {
                            return Tasks.forException(new MlKitException("Failed to schedule the download task", 13, mlKitException));
                        }
                        return zzj(l.longValue());
                    } else {
                        zzsh zzsh = this.zzi;
                        zzry zzg2 = zzsk.zzg();
                        RemoteModel remoteModel = this.zzg;
                        zzsh.zzf(zzg2, remoteModel, zzmu.NO_ERROR, false, remoteModel.getModelType(), zzna.DOWNLOADING);
                        return zzj(downloadingId.longValue());
                    }
                }
            }
            if (modelInfo != null) {
                Long zzo = zzo(modelInfo, this.zzn);
                if (zzo != null) {
                    return zzj(zzo.longValue());
                }
                zza.i("ModelDownloadManager", "Didn't schedule download for the updated model");
            }
            return Tasks.forResult(null);
        } catch (MlKitException e2) {
            return Tasks.forException(new MlKitException("Failed to ensure the model is downloaded.", 13, e2));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        zza.e("ModelDownloadManager", "Downloaded file is not found");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.os.ParcelFileDescriptor getDownloadedFile() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.app.DownloadManager r0 = r5.zzf     // Catch:{ all -> 0x0022 }
            java.lang.Long r1 = r5.getDownloadingId()     // Catch:{ all -> 0x0022 }
            r2 = 0
            if (r0 == 0) goto L_0x0020
            if (r1 == 0) goto L_0x0020
            long r3 = r1.longValue()     // Catch:{ FileNotFoundException -> 0x0015 }
            android.os.ParcelFileDescriptor r2 = r0.openDownloadedFile(r3)     // Catch:{ FileNotFoundException -> 0x0015 }
            goto L_0x001e
        L_0x0015:
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x0022 }
            java.lang.String r1 = "ModelDownloadManager"
            java.lang.String r3 = "Downloaded file is not found"
            r0.e(r1, r3)     // Catch:{ all -> 0x0022 }
        L_0x001e:
            monitor-exit(r5)
            return r2
        L_0x0020:
            monitor-exit(r5)
            return r2
        L_0x0022:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.getDownloadedFile():android.os.ParcelFileDescriptor");
    }

    public synchronized Long getDownloadingId() {
        return this.zzj.getDownloadingModelId(this.zzg);
    }

    public synchronized String getDownloadingModelHash() {
        return this.zzj.getDownloadingModelHash(this.zzg);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007c, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047 A[SYNTHETIC, Splitter:B:18:0x0047] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Integer getDownloadingModelStatusCode() {
        /*
            r8 = this;
            monitor-enter(r8)
            android.app.DownloadManager r0 = r8.zzf     // Catch:{ all -> 0x007d }
            java.lang.Long r1 = r8.getDownloadingId()     // Catch:{ all -> 0x007d }
            r2 = 0
            if (r0 == 0) goto L_0x007b
            if (r1 != 0) goto L_0x000e
            goto L_0x007b
        L_0x000e:
            android.app.DownloadManager$Query r3 = new android.app.DownloadManager$Query     // Catch:{ all -> 0x007d }
            r3.<init>()     // Catch:{ all -> 0x007d }
            r4 = 1
            long[] r5 = new long[r4]     // Catch:{ all -> 0x007d }
            long r6 = r1.longValue()     // Catch:{ all -> 0x007d }
            r1 = 0
            r5[r1] = r6     // Catch:{ all -> 0x007d }
            android.app.DownloadManager$Query r1 = r3.setFilterById(r5)     // Catch:{ all -> 0x007d }
            android.database.Cursor r0 = r0.query(r1)     // Catch:{ all -> 0x007d }
            if (r0 == 0) goto L_0x003e
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x003c }
            if (r1 == 0) goto L_0x003e
            java.lang.String r1 = "status"
            int r1 = r0.getColumnIndex(r1)     // Catch:{ all -> 0x003c }
            int r1 = r0.getInt(r1)     // Catch:{ all -> 0x003c }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x003c }
            goto L_0x003f
        L_0x003c:
            r1 = move-exception
            goto L_0x0072
        L_0x003e:
            r1 = r2
        L_0x003f:
            if (r1 != 0) goto L_0x0047
            if (r0 == 0) goto L_0x007b
            r0.close()     // Catch:{ all -> 0x007d }
            goto L_0x007b
        L_0x0047:
            int r3 = r1.intValue()     // Catch:{ all -> 0x003c }
            r5 = 2
            if (r3 == r5) goto L_0x006c
            int r3 = r1.intValue()     // Catch:{ all -> 0x003c }
            r5 = 4
            if (r3 == r5) goto L_0x006c
            int r3 = r1.intValue()     // Catch:{ all -> 0x003c }
            if (r3 == r4) goto L_0x006c
            int r3 = r1.intValue()     // Catch:{ all -> 0x003c }
            r4 = 8
            if (r3 == r4) goto L_0x006c
            int r3 = r1.intValue()     // Catch:{ all -> 0x003c }
            r4 = 16
            if (r3 == r4) goto L_0x006c
            goto L_0x006d
        L_0x006c:
            r2 = r1
        L_0x006d:
            r0.close()     // Catch:{ all -> 0x007d }
            monitor-exit(r8)
            return r2
        L_0x0072:
            r0.close()     // Catch:{ all -> 0x0076 }
            goto L_0x007a
        L_0x0076:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x007d }
        L_0x007a:
            throw r1     // Catch:{ all -> 0x007d }
        L_0x007b:
            monitor-exit(r8)
            return r2
        L_0x007d:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.getDownloadingModelStatusCode():java.lang.Integer");
    }

    public boolean isModelDownloadedAndValid() throws MlKitException {
        try {
            if (modelExistsLocally()) {
                return true;
            }
        } catch (MlKitException unused) {
            zza.d("ModelDownloadManager", "Failed to check if the model exist locally.");
        }
        Long downloadingId = getDownloadingId();
        String downloadingModelHash = getDownloadingModelHash();
        if (downloadingId == null || downloadingModelHash == null) {
            zza.d("ModelDownloadManager", "No new model is downloading.");
            removeOrCancelDownload();
            return false;
        }
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        GmsLogger gmsLogger = zza;
        new StringBuilder("Download Status code: ").append(downloadingModelStatusCode);
        gmsLogger.d("ModelDownloadManager", "Download Status code: ".concat(String.valueOf(downloadingModelStatusCode)));
        if (downloadingModelStatusCode == null) {
            removeOrCancelDownload();
            return false;
        } else if (!Objects.equal(downloadingModelStatusCode, 8) || zzi(downloadingModelHash) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean modelExistsLocally() throws MlKitException {
        return this.zzk.modelExistsLocally(this.zzg.getUniqueModelNameForPersist(), this.zzh);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0058, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeOrCancelDownload() throws com.google.mlkit.common.MlKitException {
        /*
            r5 = this;
            java.lang.String r0 = "Cancel or remove existing downloading task: "
            monitor-enter(r5)
            android.app.DownloadManager r1 = r5.zzf     // Catch:{ all -> 0x0059 }
            java.lang.Long r2 = r5.getDownloadingId()     // Catch:{ all -> 0x0059 }
            if (r1 == 0) goto L_0x0057
            if (r2 != 0) goto L_0x000e
            goto L_0x0057
        L_0x000e:
            com.google.android.gms.common.internal.GmsLogger r1 = zza     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r3.<init>(r0)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = "Cancel or remove existing downloading task: "
            r3.append(r2)     // Catch:{ all -> 0x0059 }
            java.lang.String r3 = r2.toString()     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = r0.concat(r3)     // Catch:{ all -> 0x0059 }
            java.lang.String r3 = "ModelDownloadManager"
            r1.d(r3, r0)     // Catch:{ all -> 0x0059 }
            android.app.DownloadManager r0 = r5.zzf     // Catch:{ all -> 0x0059 }
            r1 = 1
            long[] r1 = new long[r1]     // Catch:{ all -> 0x0059 }
            long r2 = r2.longValue()     // Catch:{ all -> 0x0059 }
            r4 = 0
            r1[r4] = r2     // Catch:{ all -> 0x0059 }
            int r0 = r0.remove(r1)     // Catch:{ all -> 0x0059 }
            if (r0 > 0) goto L_0x003f
            java.lang.Integer r0 = r5.getDownloadingModelStatusCode()     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x0057
        L_0x003f:
            com.google.mlkit.common.sdkinternal.model.ModelFileHelper r0 = r5.zzk     // Catch:{ all -> 0x0059 }
            com.google.mlkit.common.model.RemoteModel r1 = r5.zzg     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = r1.getUniqueModelNameForPersist()     // Catch:{ all -> 0x0059 }
            com.google.mlkit.common.sdkinternal.ModelType r1 = r1.getModelType()     // Catch:{ all -> 0x0059 }
            r0.deleteTempFilesInPrivateFolder(r2, r1)     // Catch:{ all -> 0x0059 }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r0 = r5.zzj     // Catch:{ all -> 0x0059 }
            com.google.mlkit.common.model.RemoteModel r1 = r5.zzg     // Catch:{ all -> 0x0059 }
            r0.clearDownloadingModelInfo(r1)     // Catch:{ all -> 0x0059 }
            monitor-exit(r5)
            return
        L_0x0057:
            monitor-exit(r5)
            return
        L_0x0059:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.removeOrCancelDownload():void");
    }

    public void setDownloadConditions(DownloadConditions downloadConditions) {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        this.zzn = downloadConditions;
    }

    public synchronized void updateLatestModelHashAndType(String str) throws MlKitException {
        this.zzj.setLatestModelHash(this.zzg, str);
        removeOrCancelDownload();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0088, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.mlkit.common.sdkinternal.ModelInfo zzg() throws com.google.mlkit.common.MlKitException {
        /*
            r10 = this;
            java.lang.String r0 = "The model "
            monitor-enter(r10)
            boolean r1 = r10.modelExistsLocally()     // Catch:{ all -> 0x00bb }
            if (r1 == 0) goto L_0x001d
            com.google.android.gms.internal.mlkit_common.zzsh r2 = r10.zzi     // Catch:{ all -> 0x00bb }
            com.google.mlkit.common.model.RemoteModel r4 = r10.zzg     // Catch:{ all -> 0x00bb }
            com.google.android.gms.internal.mlkit_common.zzry r3 = com.google.android.gms.internal.mlkit_common.zzsk.zzg()     // Catch:{ all -> 0x00bb }
            com.google.android.gms.internal.mlkit_common.zzmu r5 = com.google.android.gms.internal.mlkit_common.zzmu.NO_ERROR     // Catch:{ all -> 0x00bb }
            r6 = 0
            com.google.mlkit.common.sdkinternal.ModelType r7 = r4.getModelType()     // Catch:{ all -> 0x00bb }
            com.google.android.gms.internal.mlkit_common.zzna r8 = com.google.android.gms.internal.mlkit_common.zzna.LIVE     // Catch:{ all -> 0x00bb }
            r2.zzf(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00bb }
        L_0x001d:
            com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop r2 = r10.zzl     // Catch:{ all -> 0x00bb }
            if (r2 == 0) goto L_0x00b1
            com.google.mlkit.common.model.RemoteModel r3 = r10.zzg     // Catch:{ all -> 0x00bb }
            com.google.mlkit.common.sdkinternal.ModelInfo r2 = r2.retrieveRemoteModelInfo(r3)     // Catch:{ all -> 0x00bb }
            r3 = 0
            if (r2 != 0) goto L_0x002c
            monitor-exit(r10)
            return r3
        L_0x002c:
            com.google.mlkit.common.sdkinternal.MlKitContext r4 = r10.zze     // Catch:{ all -> 0x00bb }
            com.google.mlkit.common.model.RemoteModel r5 = r10.zzg     // Catch:{ all -> 0x00bb }
            java.lang.String r6 = r2.getModelHash()     // Catch:{ all -> 0x00bb }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r7 = com.google.mlkit.common.sdkinternal.SharedPrefManager.getInstance(r4)     // Catch:{ all -> 0x00bb }
            java.lang.String r5 = r7.getIncompatibleModelHash(r5)     // Catch:{ all -> 0x00bb }
            boolean r5 = r6.equals(r5)     // Catch:{ all -> 0x00bb }
            r6 = 0
            r8 = 1
            if (r5 == 0) goto L_0x0061
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ all -> 0x00bb }
            java.lang.String r4 = com.google.mlkit.common.sdkinternal.CommonUtils.getAppVersion(r4)     // Catch:{ all -> 0x00bb }
            java.lang.String r5 = r7.getPreviousAppVersion()     // Catch:{ all -> 0x00bb }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00bb }
            if (r4 == 0) goto L_0x0061
            com.google.android.gms.common.internal.GmsLogger r4 = zza     // Catch:{ all -> 0x00bb }
            java.lang.String r5 = "ModelDownloadManager"
            java.lang.String r7 = "The model is incompatible with TFLite and the app is not upgraded, do not download"
            r4.e(r5, r7)     // Catch:{ all -> 0x00bb }
            r4 = r6
            goto L_0x0062
        L_0x0061:
            r4 = r8
        L_0x0062:
            if (r1 != 0) goto L_0x006b
            com.google.mlkit.common.sdkinternal.SharedPrefManager r5 = r10.zzj     // Catch:{ all -> 0x00bb }
            com.google.mlkit.common.model.RemoteModel r7 = r10.zzg     // Catch:{ all -> 0x00bb }
            r5.clearLatestModelHash(r7)     // Catch:{ all -> 0x00bb }
        L_0x006b:
            com.google.mlkit.common.sdkinternal.MlKitContext r5 = r10.zze     // Catch:{ all -> 0x00bb }
            com.google.mlkit.common.model.RemoteModel r7 = r10.zzg     // Catch:{ all -> 0x00bb }
            java.lang.String r9 = r2.getModelHash()     // Catch:{ all -> 0x00bb }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r5 = com.google.mlkit.common.sdkinternal.SharedPrefManager.getInstance(r5)     // Catch:{ all -> 0x00bb }
            java.lang.String r5 = r5.getLatestModelHash(r7)     // Catch:{ all -> 0x00bb }
            boolean r5 = r9.equals(r5)     // Catch:{ all -> 0x00bb }
            r5 = r5 ^ r8
            if (r4 == 0) goto L_0x0089
            if (r1 == 0) goto L_0x0087
            if (r5 != 0) goto L_0x0087
            goto L_0x008a
        L_0x0087:
            monitor-exit(r10)
            return r2
        L_0x0089:
            r6 = r5
        L_0x008a:
            if (r1 == 0) goto L_0x0092
            r1 = r6 ^ r4
            if (r1 == 0) goto L_0x0092
            monitor-exit(r10)
            return r3
        L_0x0092:
            com.google.mlkit.common.model.RemoteModel r1 = r10.zzg     // Catch:{ all -> 0x00bb }
            com.google.mlkit.common.MlKitException r2 = new com.google.mlkit.common.MlKitException     // Catch:{ all -> 0x00bb }
            java.lang.String r1 = r1.getModelName()     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bb }
            r3.<init>(r0)     // Catch:{ all -> 0x00bb }
            r3.append(r1)     // Catch:{ all -> 0x00bb }
            java.lang.String r0 = " is incompatible with TFLite runtime"
            r3.append(r0)     // Catch:{ all -> 0x00bb }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x00bb }
            r1 = 100
            r2.<init>(r0, r1)     // Catch:{ all -> 0x00bb }
            throw r2     // Catch:{ all -> 0x00bb }
        L_0x00b1:
            com.google.mlkit.common.MlKitException r0 = new com.google.mlkit.common.MlKitException     // Catch:{ all -> 0x00bb }
            java.lang.String r1 = "Please include com.google.mlkit:linkfirebase sdk as your dependency when you try to download from Firebase."
            r2 = 14
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00bb }
            throw r0     // Catch:{ all -> 0x00bb }
        L_0x00bb:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.zzg():com.google.mlkit.common.sdkinternal.ModelInfo");
    }

    public final File zzi(String str) throws MlKitException {
        GmsLogger gmsLogger = zza;
        gmsLogger.d("ModelDownloadManager", "Model downloaded successfully");
        this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, true, this.zzh, zzna.SUCCEEDED);
        ParcelFileDescriptor downloadedFile = getDownloadedFile();
        if (downloadedFile == null) {
            removeOrCancelDownload();
            return null;
        }
        gmsLogger.d("ModelDownloadManager", "moving downloaded model from external storage to private folder.");
        try {
            return this.zzm.moveModelToPrivateFolder(downloadedFile, str, this.zzg);
        } finally {
            removeOrCancelDownload();
        }
    }

    /* access modifiers changed from: private */
    public final MlKitException zzl(Long l) {
        DownloadManager downloadManager = this.zzf;
        Cursor cursor = null;
        if (!(downloadManager == null || l == null)) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(new long[]{l.longValue()}));
        }
        int i = 13;
        String str = "Model downloading failed";
        if (cursor != null && cursor.moveToFirst()) {
            int i2 = cursor.getInt(cursor.getColumnIndex(DiscardedEvent.JsonKeys.REASON));
            if (i2 == 1006) {
                str = "Model downloading failed due to insufficient space on the device.";
                i = 101;
            } else {
                str = "Model downloading failed due to error code: " + i2 + " from Android DownloadManager";
            }
        }
        return new MlKitException(str, i);
    }

    public int getFailureReason(Long l) {
        int columnIndex;
        DownloadManager downloadManager = this.zzf;
        Cursor cursor = null;
        if (!(downloadManager == null || l == null)) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(new long[]{l.longValue()}));
        }
        if (cursor == null || !cursor.moveToFirst() || (columnIndex = cursor.getColumnIndex(DiscardedEvent.JsonKeys.REASON)) == -1) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }
}
