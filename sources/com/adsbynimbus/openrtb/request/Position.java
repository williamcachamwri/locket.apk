package com.adsbynimbus.openrtb.request;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0002R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Position;", "", "()V", "ABOVE_THE_FOLD", "", "BELOW_THE_FOLD", "FOOTER", "FULLSCREEN", "FULL_SCREEN", "getFULL_SCREEN$annotations", "HEADER", "POSITION_UNKNOWN", "getPOSITION_UNKNOWN$annotations", "SIDEBAR", "UNKNOWN", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "Position has been moved to the openrtb.enumerations package", replaceWith = @ReplaceWith(expression = "Position", imports = {"com.adsbynimbus.openrtb.enumerations.Position"}))
/* compiled from: Compat.kt */
public final class Position {
    public static final byte ABOVE_THE_FOLD = 1;
    public static final byte BELOW_THE_FOLD = 3;
    public static final byte FOOTER = 5;
    public static final byte FULLSCREEN = 7;
    public static final byte FULL_SCREEN = 7;
    public static final byte HEADER = 4;
    public static final Position INSTANCE = new Position();
    public static final byte POSITION_UNKNOWN = 0;
    public static final byte SIDEBAR = 6;
    public static final byte UNKNOWN = 0;

    @Deprecated(message = "Replaced by Position.FULLSCREEN", replaceWith = @ReplaceWith(expression = "Position.FULLSCREEN", imports = {}))
    public static /* synthetic */ void getFULL_SCREEN$annotations() {
    }

    @Deprecated(message = "Replaced by Position.UNKNOWN", replaceWith = @ReplaceWith(expression = "Position.UNKNOWN", imports = {}))
    public static /* synthetic */ void getPOSITION_UNKNOWN$annotations() {
    }

    private Position() {
    }
}
