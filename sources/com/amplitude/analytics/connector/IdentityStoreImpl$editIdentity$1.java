package com.amplitude.analytics.connector;

import com.amplitude.analytics.connector.IdentityStore;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010\u000b\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016J\u001e\u0010\f\u001a\u00020\u00012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0016J*\u0010\r\u001a\u00020\u00012 \u0010\u000e\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u0006H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"com/amplitude/analytics/connector/IdentityStoreImpl$editIdentity$1", "Lcom/amplitude/analytics/connector/IdentityStore$Editor;", "deviceId", "", "userId", "userProperties", "", "", "commit", "", "setDeviceId", "setUserId", "setUserProperties", "updateUserProperties", "actions", "analytics-connector_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: IdentityStore.kt */
public final class IdentityStoreImpl$editIdentity$1 implements IdentityStore.Editor {
    final /* synthetic */ Identity $originalIdentity;
    private String deviceId;
    final /* synthetic */ IdentityStoreImpl this$0;
    private String userId;
    private Map<String, ? extends Object> userProperties;

    IdentityStoreImpl$editIdentity$1(Identity identity, IdentityStoreImpl identityStoreImpl) {
        this.$originalIdentity = identity;
        this.this$0 = identityStoreImpl;
        this.userId = identity.getUserId();
        this.deviceId = identity.getDeviceId();
        this.userProperties = identity.getUserProperties();
    }

    public IdentityStore.Editor setUserId(String str) {
        this.userId = str;
        return this;
    }

    public IdentityStore.Editor setDeviceId(String str) {
        this.deviceId = str;
        return this;
    }

    public IdentityStore.Editor setUserProperties(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "userProperties");
        this.userProperties = map;
        return this;
    }

    public IdentityStore.Editor updateUserProperties(Map<String, ? extends Map<String, ? extends Object>> map) {
        Intrinsics.checkNotNullParameter(map, "actions");
        Map<String, ? extends Object> mutableMap = MapsKt.toMutableMap(this.userProperties);
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            Map map2 = (Map) next.getValue();
            int hashCode = str.hashCode();
            if (hashCode != 1186238) {
                if (hashCode != 146417720) {
                    if (hashCode == 1142092165 && str.equals("$unset")) {
                        for (Map.Entry key : map2.entrySet()) {
                            mutableMap.remove(key.getKey());
                        }
                    }
                } else if (str.equals("$clearAll")) {
                    mutableMap.clear();
                }
            } else if (str.equals("$set")) {
                mutableMap.putAll(map2);
            }
        }
        this.userProperties = mutableMap;
        return this;
    }

    public void commit() {
        this.this$0.setIdentity(new Identity(this.userId, this.deviceId, this.userProperties));
    }
}
