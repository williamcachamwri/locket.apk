package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.components.Component;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.model.RemoteModel;
import io.sentry.protocol.App;
import java.util.UUID;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class SharedPrefManager {
    public static final Component<?> COMPONENT = Component.builder(SharedPrefManager.class).add(Dependency.required((Class<?>) MlKitContext.class)).add(Dependency.required((Class<?>) Context.class)).factory(new zzs()).build();
    public static final String PREF_FILE = "com.google.mlkit.internal";
    protected final Context zza;

    public SharedPrefManager(Context context) {
        this.zza = context;
    }

    public static SharedPrefManager getInstance(MlKitContext mlKitContext) {
        return (SharedPrefManager) mlKitContext.get(SharedPrefManager.class);
    }

    public synchronized void clearDownloadingModelInfo(RemoteModel remoteModel) {
        String downloadingModelHash = getDownloadingModelHash(remoteModel);
        zza().edit().remove(String.format("downloading_model_id_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()})).remove(String.format("downloading_model_hash_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()})).remove(String.format("downloading_model_type_%s", new Object[]{downloadingModelHash})).remove(String.format("downloading_begin_time_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()})).remove(String.format("model_first_use_time_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()})).apply();
    }

    public synchronized void clearIncompatibleModelInfo(RemoteModel remoteModel) {
        zza().edit().remove(String.format("bad_hash_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()})).remove(App.JsonKeys.APP_VERSION).apply();
    }

    public synchronized void clearLatestModelHash(RemoteModel remoteModel) {
        zza().edit().remove(String.format("current_model_hash_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()})).commit();
    }

    public synchronized String getDownloadingModelHash(RemoteModel remoteModel) {
        return zza().getString(String.format("downloading_model_hash_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), (String) null);
    }

    public synchronized Long getDownloadingModelId(RemoteModel remoteModel) {
        long j = zza().getLong(String.format("downloading_model_id_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), -1);
        if (j < 0) {
            return null;
        }
        return Long.valueOf(j);
    }

    public synchronized String getIncompatibleModelHash(RemoteModel remoteModel) {
        return zza().getString(String.format("bad_hash_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), (String) null);
    }

    public synchronized String getLatestModelHash(RemoteModel remoteModel) {
        return zza().getString(String.format("current_model_hash_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), (String) null);
    }

    public synchronized String getMlSdkInstanceId() {
        String string = zza().getString("ml_sdk_instance_id", (String) null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        zza().edit().putString("ml_sdk_instance_id", uuid).apply();
        return uuid;
    }

    public synchronized long getModelDownloadBeginTimeMs(RemoteModel remoteModel) {
        return zza().getLong(String.format("downloading_begin_time_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), 0);
    }

    public synchronized long getModelFirstUseTimeMs(RemoteModel remoteModel) {
        return zza().getLong(String.format("model_first_use_time_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), 0);
    }

    public synchronized String getPreviousAppVersion() {
        return zza().getString(App.JsonKeys.APP_VERSION, (String) null);
    }

    public synchronized void setDownloadingModelInfo(long j, ModelInfo modelInfo) {
        String modelNameForPersist = modelInfo.getModelNameForPersist();
        zza().edit().putString(String.format("downloading_model_hash_%s", new Object[]{modelNameForPersist}), modelInfo.getModelHash()).putLong(String.format("downloading_model_id_%s", new Object[]{modelNameForPersist}), j).putLong(String.format("downloading_begin_time_%s", new Object[]{modelNameForPersist}), SystemClock.elapsedRealtime()).apply();
    }

    public synchronized void setIncompatibleModelInfo(RemoteModel remoteModel, String str, String str2) {
        zza().edit().putString(String.format("bad_hash_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), str).putString(App.JsonKeys.APP_VERSION, str2).apply();
    }

    public synchronized void setLatestModelHash(RemoteModel remoteModel, String str) {
        zza().edit().putString(String.format("current_model_hash_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), str).apply();
    }

    public synchronized void setModelFirstUseTimeMs(RemoteModel remoteModel, long j) {
        zza().edit().putLong(String.format("model_first_use_time_%s", new Object[]{remoteModel.getUniqueModelNameForPersist()}), j).apply();
    }

    /* access modifiers changed from: protected */
    public final SharedPreferences zza() {
        return this.zza.getSharedPreferences(PREF_FILE, 0);
    }

    public final synchronized String zzb(String str, long j) {
        return zza().getString(String.format("cached_local_model_hash_%1s_%2s", new Object[]{Preconditions.checkNotNull(str), Long.valueOf(j)}), (String) null);
    }

    public final synchronized void zzc(String str, long j, String str2) {
        zza().edit().putString(String.format("cached_local_model_hash_%1s_%2s", new Object[]{Preconditions.checkNotNull(str), Long.valueOf(j)}), str2).apply();
    }
}
