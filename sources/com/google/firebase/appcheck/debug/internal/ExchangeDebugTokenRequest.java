package com.google.firebase.appcheck.debug.internal;

import org.json.JSONException;
import org.json.JSONObject;

public class ExchangeDebugTokenRequest {
    static final String DEBUG_TOKEN_KEY = "debugToken";
    private final String debugToken;

    public ExchangeDebugTokenRequest(String str) {
        this.debugToken = str;
    }

    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DEBUG_TOKEN_KEY, this.debugToken);
        return jSONObject.toString();
    }
}
