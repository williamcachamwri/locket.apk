package expo.modules.manifests.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Manifest.kt */
final class Manifest$jsEngine$2 extends Lambda implements Function0<String> {
    final /* synthetic */ Manifest this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Manifest$jsEngine$2(Manifest manifest) {
        super(0);
        this.this$0 = manifest;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:79:0x019b, code lost:
        if (r4 != null) goto L_0x0279;
     */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:149:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String invoke() {
        /*
            r9 = this;
            expo.modules.manifests.core.Manifest r0 = r9.this$0
            org.json.JSONObject r0 = r0.getExpoClientConfigRootObject()
            java.lang.String r1 = "jsEngine"
            r2 = 0
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.String"
            if (r0 == 0) goto L_0x01ab
            java.lang.String r4 = "android"
            boolean r5 = r0.has(r4)
            if (r5 != 0) goto L_0x0018
            r4 = r2
            goto L_0x00d5
        L_0x0018:
            java.lang.Class<org.json.JSONObject> r5 = org.json.JSONObject.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            java.lang.String r7 = "null cannot be cast to non-null type org.json.JSONObject"
            if (r6 == 0) goto L_0x003c
            java.lang.String r4 = r0.getString(r4)
            if (r4 == 0) goto L_0x0036
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            goto L_0x00d5
        L_0x0036:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r7)
            throw r0
        L_0x003c:
            java.lang.Class r6 = java.lang.Double.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0054
            double r4 = r0.getDouble(r4)
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            goto L_0x00d5
        L_0x0054:
            java.lang.Class r6 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x006b
            int r4 = r0.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            goto L_0x00d5
        L_0x006b:
            java.lang.Class r6 = java.lang.Long.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0082
            long r4 = r0.getLong(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            goto L_0x00d5
        L_0x0082:
            java.lang.Class r6 = java.lang.Boolean.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0099
            boolean r4 = r0.getBoolean(r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            goto L_0x00d5
        L_0x0099:
            java.lang.Class<org.json.JSONArray> r6 = org.json.JSONArray.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x00b4
            org.json.JSONArray r4 = r0.getJSONArray(r4)
            if (r4 == 0) goto L_0x00ae
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            goto L_0x00d5
        L_0x00ae:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r7)
            throw r0
        L_0x00b4:
            java.lang.Class<org.json.JSONObject> r6 = org.json.JSONObject.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x00cd
            org.json.JSONObject r4 = r0.getJSONObject(r4)
            if (r4 == 0) goto L_0x00c7
            goto L_0x00d5
        L_0x00c7:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r7)
            throw r0
        L_0x00cd:
            java.lang.Object r4 = r0.get(r4)
            if (r4 == 0) goto L_0x01a5
            org.json.JSONObject r4 = (org.json.JSONObject) r4
        L_0x00d5:
            if (r4 == 0) goto L_0x01ab
            boolean r5 = r4.has(r1)
            if (r5 != 0) goto L_0x00e0
            r4 = r2
            goto L_0x019b
        L_0x00e0:
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0100
            java.lang.String r4 = r4.getString(r1)
            if (r4 == 0) goto L_0x00fa
            goto L_0x019b
        L_0x00fa:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r3)
            throw r0
        L_0x0100:
            java.lang.Class r6 = java.lang.Double.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0118
            double r4 = r4.getDouble(r1)
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x019b
        L_0x0118:
            java.lang.Class r6 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x012f
            int r4 = r4.getInt(r1)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x019b
        L_0x012f:
            java.lang.Class r6 = java.lang.Long.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0146
            long r4 = r4.getLong(r1)
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x019b
        L_0x0146:
            java.lang.Class r6 = java.lang.Boolean.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x015d
            boolean r4 = r4.getBoolean(r1)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x019b
        L_0x015d:
            java.lang.Class<org.json.JSONArray> r6 = org.json.JSONArray.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0178
            org.json.JSONArray r4 = r4.getJSONArray(r1)
            if (r4 == 0) goto L_0x0172
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x019b
        L_0x0172:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r3)
            throw r0
        L_0x0178:
            java.lang.Class<org.json.JSONObject> r6 = org.json.JSONObject.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x0193
            org.json.JSONObject r4 = r4.getJSONObject(r1)
            if (r4 == 0) goto L_0x018d
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x019b
        L_0x018d:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r3)
            throw r0
        L_0x0193:
            java.lang.Object r4 = r4.get(r1)
            if (r4 == 0) goto L_0x019f
            java.lang.String r4 = (java.lang.String) r4
        L_0x019b:
            if (r4 == 0) goto L_0x01ab
            goto L_0x0279
        L_0x019f:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r3)
            throw r0
        L_0x01a5:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r7)
            throw r0
        L_0x01ab:
            if (r0 == 0) goto L_0x0278
            boolean r4 = r0.has(r1)
            if (r4 != 0) goto L_0x01b5
            goto L_0x0278
        L_0x01b5:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x01d5
            java.lang.String r0 = r0.getString(r1)
            if (r0 == 0) goto L_0x01cf
            goto L_0x0270
        L_0x01cf:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r3)
            throw r0
        L_0x01d5:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x01ed
            double r0 = r0.getDouble(r1)
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0270
        L_0x01ed:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0204
            int r0 = r0.getInt(r1)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0270
        L_0x0204:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x021b
            long r0 = r0.getLong(r1)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0270
        L_0x021b:
            java.lang.Class r5 = java.lang.Boolean.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0232
            boolean r0 = r0.getBoolean(r1)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0270
        L_0x0232:
            java.lang.Class<org.json.JSONArray> r5 = org.json.JSONArray.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x024d
            org.json.JSONArray r0 = r0.getJSONArray(r1)
            if (r0 == 0) goto L_0x0247
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0270
        L_0x0247:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r3)
            throw r0
        L_0x024d:
            java.lang.Class<org.json.JSONObject> r5 = org.json.JSONObject.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0268
            org.json.JSONObject r0 = r0.getJSONObject(r1)
            if (r0 == 0) goto L_0x0262
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0270
        L_0x0262:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r3)
            throw r0
        L_0x0268:
            java.lang.Object r0 = r0.get(r1)
            if (r0 == 0) goto L_0x0272
            java.lang.String r0 = (java.lang.String) r0
        L_0x0270:
            r4 = r0
            goto L_0x0279
        L_0x0272:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r3)
            throw r0
        L_0x0278:
            r4 = r2
        L_0x0279:
            if (r4 != 0) goto L_0x02d0
            expo.modules.manifests.core.Manifest r0 = r9.this$0
            java.lang.String r0 = r0.getExpoGoSDKVersion()
            if (r0 == 0) goto L_0x0294
            r3 = r0
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.String r0 = "."
            java.lang.String[] r4 = new java.lang.String[]{r0}
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.util.List r2 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r3, (java.lang.String[]) r4, (boolean) r5, (int) r6, (int) r7, (java.lang.Object) r8)
        L_0x0294:
            r0 = 1
            r1 = 0
            if (r2 == 0) goto L_0x02a1
            int r3 = r2.size()
            r4 = 3
            if (r3 != r4) goto L_0x02a1
            r3 = r0
            goto L_0x02a2
        L_0x02a1:
            r3 = r1
        L_0x02a2:
            if (r3 == 0) goto L_0x02af
            java.lang.Object r2 = r2.get(r1)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Integer r2 = kotlin.text.StringsKt.toIntOrNull(r2)
            goto L_0x02b3
        L_0x02af:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
        L_0x02b3:
            kotlin.ranges.IntRange r3 = new kotlin.ranges.IntRange
            r4 = 47
            r3.<init>(r0, r4)
            if (r2 == 0) goto L_0x02c7
            int r2 = r2.intValue()
            boolean r2 = r3.contains((int) r2)
            if (r2 == 0) goto L_0x02c7
            goto L_0x02c8
        L_0x02c7:
            r0 = r1
        L_0x02c8:
            if (r0 == 0) goto L_0x02cd
            java.lang.String r0 = "jsc"
            goto L_0x02cf
        L_0x02cd:
            java.lang.String r0 = "hermes"
        L_0x02cf:
            r4 = r0
        L_0x02d0:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.manifests.core.Manifest$jsEngine$2.invoke():java.lang.String");
    }
}
