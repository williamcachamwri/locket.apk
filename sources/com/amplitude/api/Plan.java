package com.amplitude.api;

import org.json.JSONException;
import org.json.JSONObject;

public class Plan {
    private static final String TAG = "com.amplitude.api.Plan";
    private String branch;
    private String source;
    private String version;
    private String versionId;

    public Plan setBranch(String str) {
        this.branch = str;
        return this;
    }

    public Plan setSource(String str) {
        this.source = str;
        return this;
    }

    public Plan setVersion(String str) {
        this.version = str;
        return this;
    }

    public Plan setVersionId(String str) {
        this.versionId = str;
        return this;
    }

    /* access modifiers changed from: protected */
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!Utils.isEmptyString(this.branch)) {
                jSONObject.put(Constants.AMP_PLAN_BRANCH, this.branch);
            }
            if (!Utils.isEmptyString(this.source)) {
                jSONObject.put("source", this.source);
            }
            if (!Utils.isEmptyString(this.version)) {
                jSONObject.put("version", this.version);
            }
            if (!Utils.isEmptyString(this.versionId)) {
                jSONObject.put(Constants.AMP_PLAN_VERSION_ID, this.versionId);
            }
        } catch (JSONException unused) {
            AmplitudeLog.getLogger().e(TAG, "JSON Serialization of tacking plan object failed");
        }
        return jSONObject;
    }
}
