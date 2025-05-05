package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class ImaSdkSettingsImpl implements ImaSdkSettings {
    private boolean autoPlayAdBreaks = true;
    private boolean debugMode = false;
    private Map<String, String> featureFlags;
    private transient String language = "en";
    private int numRedirects = 4;
    private String playerType;
    private String playerVersion;
    private String ppid;
    private transient boolean restrictToCustomPlayer;
    private String sessionId;
    private final boolean supportsMultipleVideoDisplayChannels = true;
    private TestingConfiguration testingConfig;

    public boolean doesRestrictToCustomPlayer() {
        return this.restrictToCustomPlayer;
    }

    public boolean getAutoPlayAdBreaks() {
        return this.autoPlayAdBreaks;
    }

    public Map<String, String> getFeatureFlags() {
        return this.featureFlags;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getMaxRedirects() {
        return this.numRedirects;
    }

    public String getPlayerType() {
        return this.playerType;
    }

    public String getPlayerVersion() {
        return this.playerVersion;
    }

    public String getPpid() {
        return this.ppid;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public TestingConfiguration getTestingConfig() {
        return this.testingConfig;
    }

    public boolean isDebugMode() {
        return this.debugMode;
    }

    public void setAutoPlayAdBreaks(boolean z) {
        this.autoPlayAdBreaks = z;
    }

    public void setDebugMode(boolean z) {
        this.debugMode = z;
    }

    public void setFeatureFlags(Map<String, String> map) {
        this.featureFlags = map;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setMaxRedirects(int i) {
        this.numRedirects = i;
    }

    public void setPlayerType(String str) {
        this.playerType = str;
    }

    public void setPlayerVersion(String str) {
        this.playerVersion = str;
    }

    public void setPpid(String str) {
        this.ppid = str;
    }

    public void setRestrictToCustomPlayer(boolean z) {
        this.restrictToCustomPlayer = z;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setTestingConfig(TestingConfiguration testingConfiguration) {
        this.testingConfig = testingConfiguration;
    }

    public String toString() {
        String str = this.ppid;
        int i = this.numRedirects;
        String str2 = this.playerType;
        String str3 = this.playerVersion;
        String str4 = this.language;
        boolean z = this.restrictToCustomPlayer;
        boolean z2 = this.autoPlayAdBreaks;
        String str5 = this.sessionId;
        return "ImaSdkSettings [ppid=" + str + ", numRedirects=" + i + ", playerType=" + str2 + ", playerVersion=" + str3 + ", language=" + str4 + ", restrictToCustom=" + z + ", autoPlayAdBreaks=" + z2 + ", sessionId=" + str5 + "]";
    }
}
