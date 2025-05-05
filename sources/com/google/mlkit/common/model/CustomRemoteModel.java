package com.google.mlkit.common.model;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final class CustomRemoteModel extends RemoteModel {
    private final RemoteModelSource zzb;

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    public static class Builder {
        private final RemoteModelSource zza;

        public Builder(RemoteModelSource remoteModelSource) {
            Preconditions.checkNotNull(remoteModelSource);
            this.zza = remoteModelSource;
        }

        public CustomRemoteModel build() {
            return new CustomRemoteModel(this.zza, (zza) null);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* synthetic */ CustomRemoteModel(com.google.mlkit.common.model.RemoteModelSource r3, com.google.mlkit.common.model.zza r4) {
        /*
            r2 = this;
            java.lang.String r4 = r3.zza()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x000d
            java.lang.String r4 = "no_model_name"
            goto L_0x0011
        L_0x000d:
            java.lang.String r4 = r3.zza()
        L_0x0011:
            r0 = 0
            com.google.mlkit.common.sdkinternal.ModelType r1 = com.google.mlkit.common.sdkinternal.ModelType.CUSTOM
            r2.<init>(r4, r0, r1)
            r2.zzb = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.model.CustomRemoteModel.<init>(com.google.mlkit.common.model.RemoteModelSource, com.google.mlkit.common.model.zza):void");
    }

    public RemoteModelSource getRemoteModelSource() {
        return this.zzb;
    }
}
