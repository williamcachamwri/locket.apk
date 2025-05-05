package com.facebook.fresco.ui.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\b\u0001\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/facebook/fresco/ui/common/ImageLoadStatus;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "toString", "", "UNKNOWN", "REQUESTED", "INTERMEDIATE_AVAILABLE", "SUCCESS", "CANCELED", "ERROR", "DRAW", "EMPTY_EVENT", "Companion", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageLoadStatus.kt */
public enum ImageLoadStatus {
    UNKNOWN(-1),
    REQUESTED(0),
    INTERMEDIATE_AVAILABLE(2),
    SUCCESS(3),
    CANCELED(4),
    ERROR(5),
    DRAW(6),
    EMPTY_EVENT(7);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final ImageLoadStatus[] VALUES = null;
    private final int value;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImageLoadStatus.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.facebook.fresco.ui.common.ImageLoadStatus[] r0 = com.facebook.fresco.ui.common.ImageLoadStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.REQUESTED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.CANCELED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.INTERMEDIATE_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.ERROR     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.ui.common.ImageLoadStatus.WhenMappings.<clinit>():void");
        }
    }

    private ImageLoadStatus(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
        VALUES = values();
    }

    public String toString() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "requested";
        }
        if (i == 2) {
            return FirebaseAnalytics.Param.SUCCESS;
        }
        if (i == 3) {
            return "canceled";
        }
        if (i != 4) {
            return i != 5 ? "unknown" : "error";
        }
        return "intermediate_available";
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/facebook/fresco/ui/common/ImageLoadStatus$Companion;", "", "()V", "VALUES", "", "Lcom/facebook/fresco/ui/common/ImageLoadStatus;", "[Lcom/facebook/fresco/ui/common/ImageLoadStatus;", "fromInt", "value", "", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImageLoadStatus.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImageLoadStatus fromInt(int i) {
            for (ImageLoadStatus imageLoadStatus : ImageLoadStatus.VALUES) {
                if (imageLoadStatus.getValue() == i) {
                    return imageLoadStatus;
                }
            }
            return null;
        }
    }
}
