package com.amplitude.analytics.connector;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006HÆ\u0003J9\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u001f\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amplitude/analytics/connector/Identity;", "", "userId", "", "deviceId", "userProperties", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getDeviceId", "()Ljava/lang/String;", "getUserId", "getUserProperties", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "analytics-connector_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: IdentityStore.kt */
public final class Identity {
    private final String deviceId;
    private final String userId;
    private final Map<String, Object> userProperties;

    public Identity() {
        this((String) null, (String) null, (Map) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Identity copy$default(Identity identity, String str, String str2, Map<String, Object> map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = identity.userId;
        }
        if ((i & 2) != 0) {
            str2 = identity.deviceId;
        }
        if ((i & 4) != 0) {
            map = identity.userProperties;
        }
        return identity.copy(str, str2, map);
    }

    public final String component1() {
        return this.userId;
    }

    public final String component2() {
        return this.deviceId;
    }

    public final Map<String, Object> component3() {
        return this.userProperties;
    }

    public final Identity copy(String str, String str2, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "userProperties");
        return new Identity(str, str2, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Identity)) {
            return false;
        }
        Identity identity = (Identity) obj;
        return Intrinsics.areEqual((Object) this.userId, (Object) identity.userId) && Intrinsics.areEqual((Object) this.deviceId, (Object) identity.deviceId) && Intrinsics.areEqual((Object) this.userProperties, (Object) identity.userProperties);
    }

    public int hashCode() {
        String str = this.userId;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.deviceId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode + i) * 31) + this.userProperties.hashCode();
    }

    public String toString() {
        return "Identity(userId=" + this.userId + ", deviceId=" + this.deviceId + ", userProperties=" + this.userProperties + ')';
    }

    public Identity(String str, String str2, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "userProperties");
        this.userId = str;
        this.deviceId = str2;
        this.userProperties = map;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Identity(String str, String str2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, Object> getUserProperties() {
        return this.userProperties;
    }
}
