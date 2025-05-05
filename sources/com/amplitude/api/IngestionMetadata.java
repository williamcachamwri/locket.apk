package com.amplitude.api;

import org.json.JSONException;
import org.json.JSONObject;

public class IngestionMetadata {
    private static final String TAG = "com.amplitude.api.IngestionMetadata";
    private String sourceName;
    private String sourceVersion;

    public IngestionMetadata setSourceName(String str) {
        this.sourceName = str;
        return this;
    }

    public IngestionMetadata setSourceVersion(String str) {
        this.sourceVersion = str;
        return this;
    }

    /* access modifiers changed from: protected */
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!Utils.isEmptyString(this.sourceName)) {
                jSONObject.put(Constants.AMP_INGESTION_METADATA_SOURCE_NAME, this.sourceName);
            }
            if (!Utils.isEmptyString(this.sourceVersion)) {
                jSONObject.put(Constants.AMP_INGESTION_METADATA_SOURCE_VERSION, this.sourceVersion);
            }
        } catch (JSONException unused) {
            AmplitudeLog.getLogger().e(TAG, "JSON Serialization of ingestion metadata object failed");
        }
        return jSONObject;
    }
}
