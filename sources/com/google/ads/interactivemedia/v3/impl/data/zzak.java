package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.FriendlyObstructionPurpose;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzak extends zzcb {
    private boolean attached;
    private zzbb bounds;
    private String detailedReason;
    private boolean hidden;
    private FriendlyObstructionPurpose purpose;
    private byte set$0;
    private String type;

    zzak() {
    }

    public zzcb attached(boolean z) {
        this.attached = z;
        this.set$0 = (byte) (this.set$0 | 1);
        return this;
    }

    public zzcb bounds(zzbb zzbb) {
        if (zzbb != null) {
            this.bounds = zzbb;
            return this;
        }
        throw new NullPointerException("Null bounds");
    }

    public zzcc build() {
        zzbb zzbb;
        FriendlyObstructionPurpose friendlyObstructionPurpose;
        String str;
        if (this.set$0 == 3 && (zzbb = this.bounds) != null && (friendlyObstructionPurpose = this.purpose) != null && (str = this.type) != null) {
            return new zzam(this.attached, zzbb, this.detailedReason, this.hidden, friendlyObstructionPurpose, str);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.set$0 & 1) == 0) {
            sb.append(" attached");
        }
        if (this.bounds == null) {
            sb.append(" bounds");
        }
        if ((this.set$0 & 2) == 0) {
            sb.append(" hidden");
        }
        if (this.purpose == null) {
            sb.append(" purpose");
        }
        if (this.type == null) {
            sb.append(" type");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public zzcb detailedReason(String str) {
        this.detailedReason = str;
        return this;
    }

    public zzcb hidden(boolean z) {
        this.hidden = z;
        this.set$0 = (byte) (this.set$0 | 2);
        return this;
    }

    public zzcb purpose(FriendlyObstructionPurpose friendlyObstructionPurpose) {
        if (friendlyObstructionPurpose != null) {
            this.purpose = friendlyObstructionPurpose;
            return this;
        }
        throw new NullPointerException("Null purpose");
    }

    public zzcb type(String str) {
        if (str != null) {
            this.type = str;
            return this;
        }
        throw new NullPointerException("Null type");
    }
}
