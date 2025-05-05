package com.google.firebase.appcheck.playintegrity.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.FirebaseException;
import org.json.JSONException;
import org.json.JSONObject;

class GeneratePlayIntegrityChallengeResponse {
    static final String CHALLENGE_KEY = "challenge";
    static final String TIME_TO_LIVE_KEY = "ttl";
    private String challenge;
    private String timeToLive;

    public static GeneratePlayIntegrityChallengeResponse fromJsonString(String str) throws FirebaseException, JSONException {
        JSONObject jSONObject = new JSONObject(str);
        String emptyToNull = Strings.emptyToNull(jSONObject.optString("challenge"));
        String emptyToNull2 = Strings.emptyToNull(jSONObject.optString(TIME_TO_LIVE_KEY));
        if (emptyToNull != null && emptyToNull2 != null) {
            return new GeneratePlayIntegrityChallengeResponse(emptyToNull, emptyToNull2);
        }
        throw new FirebaseException("Unexpected server response.");
    }

    private GeneratePlayIntegrityChallengeResponse(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.challenge = str;
        this.timeToLive = str2;
    }

    public String getChallenge() {
        return this.challenge;
    }

    public String getTimeToLive() {
        return this.timeToLive;
    }
}
