package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import io.sentry.instrumentation.file.SentryFileInputStream;
import io.sentry.instrumentation.file.SentryFileWriter;
import java.io.File;
import java.io.FileInputStream;
import org.json.JSONObject;

public class CachedSettingsIo {
    private static final String SETTINGS_CACHE_FILENAME = "com.crashlytics.settings.json";
    private final File cachedSettingsFile;

    public CachedSettingsIo(FileStore fileStore) {
        this.cachedSettingsFile = fileStore.getCommonFile(SETTINGS_CACHE_FILENAME);
    }

    private File getSettingsFile() {
        return this.cachedSettingsFile;
    }

    public JSONObject readCachedSettings() {
        FileInputStream fileInputStream;
        JSONObject jSONObject;
        Logger.getLogger().d("Checking for cached settings...");
        FileInputStream fileInputStream2 = null;
        try {
            File settingsFile = getSettingsFile();
            if (settingsFile.exists()) {
                fileInputStream = SentryFileInputStream.Factory.create(new FileInputStream(settingsFile), settingsFile);
                try {
                    jSONObject = new JSONObject(CommonUtils.streamToString(fileInputStream));
                    fileInputStream2 = fileInputStream;
                } catch (Exception e) {
                    e = e;
                    try {
                        Logger.getLogger().e("Failed to fetch cached settings", e);
                        CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
                        throw th;
                    }
                }
            } else {
                Logger.getLogger().v("Settings file does not exist.");
                jSONObject = null;
            }
            CommonUtils.closeOrLog(fileInputStream2, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            Logger.getLogger().e("Failed to fetch cached settings", e);
            CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            fileInputStream = null;
            th = th3;
            CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
            throw th;
        }
    }

    public void writeCachedSettings(long j, JSONObject jSONObject) {
        Logger.getLogger().v("Writing settings to cache file...");
        if (jSONObject != null) {
            SentryFileWriter sentryFileWriter = null;
            try {
                jSONObject.put("expires_at", j);
                SentryFileWriter sentryFileWriter2 = new SentryFileWriter(getSettingsFile());
                try {
                    sentryFileWriter2.write(jSONObject.toString());
                    sentryFileWriter2.flush();
                    CommonUtils.closeOrLog(sentryFileWriter2, "Failed to close settings writer.");
                } catch (Exception e) {
                    e = e;
                    sentryFileWriter = sentryFileWriter2;
                    try {
                        Logger.getLogger().e("Failed to cache settings", e);
                        CommonUtils.closeOrLog(sentryFileWriter, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        th = th;
                        CommonUtils.closeOrLog(sentryFileWriter, "Failed to close settings writer.");
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    sentryFileWriter = sentryFileWriter2;
                    CommonUtils.closeOrLog(sentryFileWriter, "Failed to close settings writer.");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                Logger.getLogger().e("Failed to cache settings", e);
                CommonUtils.closeOrLog(sentryFileWriter, "Failed to close settings writer.");
            }
        }
    }
}
