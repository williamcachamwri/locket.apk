package com.google.firebase.crashlytics.ndk;

import io.sentry.ProfilingTraceData;
import io.sentry.protocol.App;
import io.sentry.protocol.DebugImage;
import java.util.HashMap;
import org.json.JSONObject;

class SessionMetadataJsonSerializer {
    private static String emptyIfNull(String str) {
        return str == null ? "" : str;
    }

    private SessionMetadataJsonSerializer() {
    }

    static String serializeBeginSession(String str, String str2, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("session_id", str);
        hashMap.put("generator", str2);
        hashMap.put("started_at_seconds", Long.valueOf(j));
        return new JSONObject(hashMap).toString();
    }

    static String serializeSessionApp(String str, String str2, String str3, String str4, int i, String str5, String str6) {
        HashMap hashMap = new HashMap();
        hashMap.put(App.JsonKeys.APP_IDENTIFIER, str);
        hashMap.put(ProfilingTraceData.JsonKeys.VERSION_CODE, str2);
        hashMap.put("version_name", str3);
        hashMap.put("install_uuid", str4);
        hashMap.put("delivery_mechanism", Integer.valueOf(i));
        hashMap.put("development_platform", emptyIfNull(str5));
        hashMap.put("development_platform_version", emptyIfNull(str6));
        return new JSONObject(hashMap).toString();
    }

    static String serializeSessionOs(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", str);
        hashMap.put("build_version", str2);
        hashMap.put("is_rooted", Boolean.valueOf(z));
        return new JSONObject(hashMap).toString();
    }

    static String serializeSessionDevice(int i, String str, int i2, long j, long j2, boolean z, int i3, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put(DebugImage.JsonKeys.ARCH, Integer.valueOf(i));
        hashMap.put("build_model", str);
        hashMap.put("available_processors", Integer.valueOf(i2));
        hashMap.put("total_ram", Long.valueOf(j));
        hashMap.put("disk_space", Long.valueOf(j2));
        hashMap.put("is_emulator", Boolean.valueOf(z));
        hashMap.put("state", Integer.valueOf(i3));
        hashMap.put("build_manufacturer", str2);
        hashMap.put("build_product", str3);
        return new JSONObject(hashMap).toString();
    }
}
