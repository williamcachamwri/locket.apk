package com.google.firebase.appcheck.playintegrity.internal;

import org.json.JSONObject;

class GeneratePlayIntegrityChallengeRequest {
    public String toJsonString() {
        return new JSONObject().toString();
    }
}
