package com.adsbynimbus.openrtb.enumerations;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/adsbynimbus/openrtb/enumerations/DeviceType;", "", "()V", "CONNECTED_DEVICE", "", "CONNECTED_TV", "MOBILE_TABLET", "PERSONAL_COMPUTER", "PHONE", "SET_TOP_BOX", "TABLET", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
/* compiled from: DeviceType.kt */
public final class DeviceType {
    public static final byte CONNECTED_DEVICE = 6;
    public static final byte CONNECTED_TV = 3;
    public static final DeviceType INSTANCE = new DeviceType();
    public static final byte MOBILE_TABLET = 1;
    public static final byte PERSONAL_COMPUTER = 2;
    public static final byte PHONE = 4;
    public static final byte SET_TOP_BOX = 7;
    public static final byte TABLET = 5;

    private DeviceType() {
    }
}
