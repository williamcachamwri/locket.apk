package com.brentvatne.common.api;

import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u001f\u001a\u00020\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0002R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006\""}, d2 = {"Lcom/brentvatne/common/api/DRMProps;", "", "()V", "drmLicenseHeader", "", "", "getDrmLicenseHeader", "()[Ljava/lang/String;", "setDrmLicenseHeader", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "drmLicenseServer", "getDrmLicenseServer", "()Ljava/lang/String;", "setDrmLicenseServer", "(Ljava/lang/String;)V", "drmType", "getDrmType", "setDrmType", "drmUUID", "Ljava/util/UUID;", "getDrmUUID", "()Ljava/util/UUID;", "setDrmUUID", "(Ljava/util/UUID;)V", "multiDrm", "", "getMultiDrm", "()Z", "setMultiDrm", "(Z)V", "equals", "other", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DRMProps.kt */
public final class DRMProps {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROP_DRM_HEADERS = "headers";
    private static final String PROP_DRM_HEADERS_KEY = "key";
    private static final String PROP_DRM_HEADERS_VALUE = "value";
    private static final String PROP_DRM_LICENSE_SERVER = "licenseServer";
    private static final String PROP_DRM_MULTI_DRM = "multiDrm";
    private static final String PROP_DRM_TYPE = "type";
    private String[] drmLicenseHeader = new String[0];
    private String drmLicenseServer;
    private String drmType;
    private UUID drmUUID;
    private boolean multiDrm;

    @JvmStatic
    public static final DRMProps parse(ReadableMap readableMap) {
        return Companion.parse(readableMap);
    }

    public final String getDrmType() {
        return this.drmType;
    }

    public final void setDrmType(String str) {
        this.drmType = str;
    }

    public final UUID getDrmUUID() {
        return this.drmUUID;
    }

    public final void setDrmUUID(UUID uuid) {
        this.drmUUID = uuid;
    }

    public final String getDrmLicenseServer() {
        return this.drmLicenseServer;
    }

    public final void setDrmLicenseServer(String str) {
        this.drmLicenseServer = str;
    }

    public final String[] getDrmLicenseHeader() {
        return this.drmLicenseHeader;
    }

    public final void setDrmLicenseHeader(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.drmLicenseHeader = strArr;
    }

    public final boolean getMultiDrm() {
        return this.multiDrm;
    }

    public final void setMultiDrm(boolean z) {
        this.multiDrm = z;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DRMProps)) {
            return false;
        }
        DRMProps dRMProps = (DRMProps) obj;
        if (!Intrinsics.areEqual((Object) this.drmType, (Object) dRMProps.drmType) || !Intrinsics.areEqual((Object) this.drmLicenseServer, (Object) dRMProps.drmLicenseServer) || this.multiDrm != dRMProps.multiDrm || !ArraysKt.contentDeepEquals(this.drmLicenseHeader, dRMProps.drmLicenseHeader)) {
            return false;
        }
        return true;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/brentvatne/common/api/DRMProps$Companion;", "", "()V", "PROP_DRM_HEADERS", "", "PROP_DRM_HEADERS_KEY", "PROP_DRM_HEADERS_VALUE", "PROP_DRM_LICENSE_SERVER", "PROP_DRM_MULTI_DRM", "PROP_DRM_TYPE", "parse", "Lcom/brentvatne/common/api/DRMProps;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DRMProps.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DRMProps parse(ReadableMap readableMap) {
            if (readableMap == null || !readableMap.hasKey("type")) {
                return null;
            }
            DRMProps dRMProps = new DRMProps();
            dRMProps.setDrmType(ReactBridgeUtils.safeGetString(readableMap, "type"));
            dRMProps.setDrmLicenseServer(ReactBridgeUtils.safeGetString(readableMap, DRMProps.PROP_DRM_LICENSE_SERVER));
            dRMProps.setMultiDrm(ReactBridgeUtils.safeGetBool(readableMap, DRMProps.PROP_DRM_MULTI_DRM, false));
            ReadableArray safeGetArray = ReactBridgeUtils.safeGetArray(readableMap, "headers");
            if (dRMProps.getDrmType() == null || dRMProps.getDrmLicenseServer() == null) {
                return null;
            }
            if (safeGetArray != null) {
                ArrayList arrayList = new ArrayList();
                int size = safeGetArray.size();
                for (int i = 0; i < size; i++) {
                    ReadableMap map = safeGetArray.getMap(i);
                    Intrinsics.checkNotNullExpressionValue(map, "getMap(...)");
                    arrayList.add(ReactBridgeUtils.safeGetString(map, DRMProps.PROP_DRM_HEADERS_KEY));
                    arrayList.add(ReactBridgeUtils.safeGetString(map, "value"));
                }
                Object[] array = arrayList.toArray(new String[0]);
                Intrinsics.checkNotNullExpressionValue(array, "toArray(...)");
                dRMProps.setDrmLicenseHeader((String[]) array);
            }
            return dRMProps;
        }
    }
}
