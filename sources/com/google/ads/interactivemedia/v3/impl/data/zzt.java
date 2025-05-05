package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;
import com.google.ads.interactivemedia.v3.api.FriendlyObstructionPurpose;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzt implements zzbg {
    private String detailedReason;
    private FriendlyObstructionPurpose purpose;
    private View view;

    zzt() {
    }

    public zzbh build() {
        FriendlyObstructionPurpose friendlyObstructionPurpose;
        View view2 = this.view;
        if (view2 != null && (friendlyObstructionPurpose = this.purpose) != null) {
            return new zzv(view2, friendlyObstructionPurpose, this.detailedReason);
        }
        StringBuilder sb = new StringBuilder();
        if (this.view == null) {
            sb.append(" view");
        }
        if (this.purpose == null) {
            sb.append(" purpose");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public zzbg detailedReason(String str) {
        this.detailedReason = str;
        return this;
    }

    public zzbg purpose(FriendlyObstructionPurpose friendlyObstructionPurpose) {
        if (friendlyObstructionPurpose != null) {
            this.purpose = friendlyObstructionPurpose;
            return this;
        }
        throw new NullPointerException("Null purpose");
    }

    public zzbg view(View view2) {
        if (view2 != null) {
            this.view = view2;
            return this;
        }
        throw new NullPointerException("Null view");
    }
}
