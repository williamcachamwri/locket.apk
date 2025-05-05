package com.brentvatne.common.api;

import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.facebook.react.bridge.ReadableMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0012\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003\u0012\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003\u0012\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003\u0012\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u001b\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003HÆ\u0003J\u001b\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003HÆ\u0003J\u001b\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003HÆ\u0003J\u001b\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\nHÆ\u0003J\u0001\u0010\u0018\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u00032\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u00032\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u00032\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\nHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R#\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR#\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR#\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR#\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/brentvatne/common/api/CMCDProps;", "", "cmcdObject", "", "Lkotlin/Pair;", "", "cmcdRequest", "cmcdSession", "cmcdStatus", "mode", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;I)V", "getCmcdObject", "()Ljava/util/List;", "getCmcdRequest", "getCmcdSession", "getCmcdStatus", "getMode", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CMCDProps.kt */
public final class CMCDProps {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROP_CMCD_MODE = "mode";
    private static final String PROP_CMCD_OBJECT = "object";
    private static final String PROP_CMCD_REQUEST = "request";
    private static final String PROP_CMCD_SESSION = "session";
    private static final String PROP_CMCD_STATUS = "status";
    private final List<Pair<String, Object>> cmcdObject;
    private final List<Pair<String, Object>> cmcdRequest;
    private final List<Pair<String, Object>> cmcdSession;
    private final List<Pair<String, Object>> cmcdStatus;
    private final int mode;

    public CMCDProps() {
        this((List) null, (List) null, (List) null, (List) null, 0, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ CMCDProps copy$default(CMCDProps cMCDProps, List<Pair<String, Object>> list, List<Pair<String, Object>> list2, List<Pair<String, Object>> list3, List<Pair<String, Object>> list4, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = cMCDProps.cmcdObject;
        }
        if ((i2 & 2) != 0) {
            list2 = cMCDProps.cmcdRequest;
        }
        List<Pair<String, Object>> list5 = list2;
        if ((i2 & 4) != 0) {
            list3 = cMCDProps.cmcdSession;
        }
        List<Pair<String, Object>> list6 = list3;
        if ((i2 & 8) != 0) {
            list4 = cMCDProps.cmcdStatus;
        }
        List<Pair<String, Object>> list7 = list4;
        if ((i2 & 16) != 0) {
            i = cMCDProps.mode;
        }
        return cMCDProps.copy(list, list5, list6, list7, i);
    }

    @JvmStatic
    public static final CMCDProps parse(ReadableMap readableMap) {
        return Companion.parse(readableMap);
    }

    public final List<Pair<String, Object>> component1() {
        return this.cmcdObject;
    }

    public final List<Pair<String, Object>> component2() {
        return this.cmcdRequest;
    }

    public final List<Pair<String, Object>> component3() {
        return this.cmcdSession;
    }

    public final List<Pair<String, Object>> component4() {
        return this.cmcdStatus;
    }

    public final int component5() {
        return this.mode;
    }

    public final CMCDProps copy(List<? extends Pair<String, ? extends Object>> list, List<? extends Pair<String, ? extends Object>> list2, List<? extends Pair<String, ? extends Object>> list3, List<? extends Pair<String, ? extends Object>> list4, int i) {
        Intrinsics.checkNotNullParameter(list, "cmcdObject");
        Intrinsics.checkNotNullParameter(list2, "cmcdRequest");
        Intrinsics.checkNotNullParameter(list3, "cmcdSession");
        Intrinsics.checkNotNullParameter(list4, "cmcdStatus");
        return new CMCDProps(list, list2, list3, list4, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CMCDProps)) {
            return false;
        }
        CMCDProps cMCDProps = (CMCDProps) obj;
        return Intrinsics.areEqual((Object) this.cmcdObject, (Object) cMCDProps.cmcdObject) && Intrinsics.areEqual((Object) this.cmcdRequest, (Object) cMCDProps.cmcdRequest) && Intrinsics.areEqual((Object) this.cmcdSession, (Object) cMCDProps.cmcdSession) && Intrinsics.areEqual((Object) this.cmcdStatus, (Object) cMCDProps.cmcdStatus) && this.mode == cMCDProps.mode;
    }

    public int hashCode() {
        return (((((((this.cmcdObject.hashCode() * 31) + this.cmcdRequest.hashCode()) * 31) + this.cmcdSession.hashCode()) * 31) + this.cmcdStatus.hashCode()) * 31) + Integer.hashCode(this.mode);
    }

    public String toString() {
        List<Pair<String, Object>> list = this.cmcdObject;
        List<Pair<String, Object>> list2 = this.cmcdRequest;
        List<Pair<String, Object>> list3 = this.cmcdSession;
        List<Pair<String, Object>> list4 = this.cmcdStatus;
        return "CMCDProps(cmcdObject=" + list + ", cmcdRequest=" + list2 + ", cmcdSession=" + list3 + ", cmcdStatus=" + list4 + ", mode=" + this.mode + ")";
    }

    public CMCDProps(List<? extends Pair<String, ? extends Object>> list, List<? extends Pair<String, ? extends Object>> list2, List<? extends Pair<String, ? extends Object>> list3, List<? extends Pair<String, ? extends Object>> list4, int i) {
        Intrinsics.checkNotNullParameter(list, "cmcdObject");
        Intrinsics.checkNotNullParameter(list2, "cmcdRequest");
        Intrinsics.checkNotNullParameter(list3, "cmcdSession");
        Intrinsics.checkNotNullParameter(list4, "cmcdStatus");
        this.cmcdObject = list;
        this.cmcdRequest = list2;
        this.cmcdSession = list3;
        this.cmcdStatus = list4;
        this.mode = i;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CMCDProps(java.util.List r4, java.util.List r5, java.util.List r6, java.util.List r7, int r8, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
        /*
            r3 = this;
            r10 = r9 & 1
            if (r10 == 0) goto L_0x0008
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0008:
            r10 = r9 & 2
            if (r10 == 0) goto L_0x0010
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0010:
            r10 = r5
            r5 = r9 & 4
            if (r5 == 0) goto L_0x0019
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0019:
            r0 = r6
            r5 = r9 & 8
            if (r5 == 0) goto L_0x0022
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0022:
            r1 = r7
            r5 = r9 & 16
            if (r5 == 0) goto L_0x0028
            r8 = 1
        L_0x0028:
            r2 = r8
            r5 = r3
            r6 = r4
            r7 = r10
            r8 = r0
            r9 = r1
            r10 = r2
            r5.<init>(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.common.api.CMCDProps.<init>(java.util.List, java.util.List, java.util.List, java.util.List, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final List<Pair<String, Object>> getCmcdObject() {
        return this.cmcdObject;
    }

    public final List<Pair<String, Object>> getCmcdRequest() {
        return this.cmcdRequest;
    }

    public final List<Pair<String, Object>> getCmcdSession() {
        return this.cmcdSession;
    }

    public final List<Pair<String, Object>> getCmcdStatus() {
        return this.cmcdStatus;
    }

    public final int getMode() {
        return this.mode;
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J$\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/brentvatne/common/api/CMCDProps$Companion;", "", "()V", "PROP_CMCD_MODE", "", "PROP_CMCD_OBJECT", "PROP_CMCD_REQUEST", "PROP_CMCD_SESSION", "PROP_CMCD_STATUS", "parse", "Lcom/brentvatne/common/api/CMCDProps;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "parseKeyValuePairs", "", "Lkotlin/Pair;", "array", "Lcom/facebook/react/bridge/ReadableArray;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CMCDProps.kt */
    public static final class Companion {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* compiled from: CMCDProps.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.common.api.CMCDProps.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CMCDProps parse(ReadableMap readableMap) {
            if (readableMap == null) {
                return null;
            }
            return new CMCDProps(parseKeyValuePairs(readableMap.getArray(CMCDProps.PROP_CMCD_OBJECT)), parseKeyValuePairs(readableMap.getArray("request")), parseKeyValuePairs(readableMap.getArray("session")), parseKeyValuePairs(readableMap.getArray("status")), ReactBridgeUtils.safeGetInt(readableMap, CMCDProps.PROP_CMCD_MODE, 1));
        }

        /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x001d A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final java.util.List<kotlin.Pair<java.lang.String, java.lang.Object>> parseKeyValuePairs(com.facebook.react.bridge.ReadableArray r9) {
            /*
                r8 = this;
                if (r9 != 0) goto L_0x0007
                java.util.List r9 = kotlin.collections.CollectionsKt.emptyList()
                return r9
            L_0x0007:
                r0 = 0
                int r1 = r9.size()
                kotlin.ranges.IntRange r0 = kotlin.ranges.RangesKt.until((int) r0, (int) r1)
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r0 = r0.iterator()
            L_0x001d:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x007d
                r2 = r0
                kotlin.collections.IntIterator r2 = (kotlin.collections.IntIterator) r2
                int r2 = r2.nextInt()
                com.facebook.react.bridge.ReadableMap r2 = r9.getMap(r2)
                java.lang.String r3 = "getMap(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                r3 = 0
                if (r2 == 0) goto L_0x003d
                java.lang.String r4 = "key"
                java.lang.String r4 = r2.getString(r4)
                goto L_0x003e
            L_0x003d:
                r4 = r3
            L_0x003e:
                java.lang.String r5 = "value"
                if (r2 == 0) goto L_0x0048
                com.facebook.react.bridge.ReadableType r6 = r2.getType(r5)
                goto L_0x0049
            L_0x0048:
                r6 = r3
            L_0x0049:
                if (r6 != 0) goto L_0x004d
                r6 = -1
                goto L_0x0055
            L_0x004d:
                int[] r7 = com.brentvatne.common.api.CMCDProps.Companion.WhenMappings.$EnumSwitchMapping$0
                int r6 = r6.ordinal()
                r6 = r7[r6]
            L_0x0055:
                r7 = 1
                if (r6 == r7) goto L_0x0064
                r7 = 2
                if (r6 == r7) goto L_0x005d
            L_0x005b:
                r2 = r3
                goto L_0x006e
            L_0x005d:
                if (r2 == 0) goto L_0x005b
                java.lang.String r2 = r2.getString(r5)
                goto L_0x006e
            L_0x0064:
                if (r2 == 0) goto L_0x005b
                double r5 = r2.getDouble(r5)
                java.lang.Double r2 = java.lang.Double.valueOf(r5)
            L_0x006e:
                if (r4 == 0) goto L_0x0077
                if (r2 == 0) goto L_0x0077
                kotlin.Pair r3 = new kotlin.Pair
                r3.<init>(r4, r2)
            L_0x0077:
                if (r3 == 0) goto L_0x001d
                r1.add(r3)
                goto L_0x001d
            L_0x007d:
                java.util.List r1 = (java.util.List) r1
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.common.api.CMCDProps.Companion.parseKeyValuePairs(com.facebook.react.bridge.ReadableArray):java.util.List");
        }
    }
}
