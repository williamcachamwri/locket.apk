package com.canhub.cropper.common;

import android.os.Build;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/canhub/cropper/common/CommonVersionCheck;", "", "()V", "isAtLeastJ18", "", "isAtLeastM23", "isAtLeastO26", "isAtLeastQ29", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CommonVersionCheck.kt */
public final class CommonVersionCheck {
    public static final CommonVersionCheck INSTANCE = new CommonVersionCheck();

    public final boolean isAtLeastJ18() {
        return true;
    }

    public final boolean isAtLeastM23() {
        return true;
    }

    public final boolean isAtLeastO26() {
        return true;
    }

    private CommonVersionCheck() {
    }

    public final boolean isAtLeastQ29() {
        return Build.VERSION.SDK_INT >= 29;
    }
}
