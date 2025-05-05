package com.adsbynimbus.openrtb.enumerations;

import com.google.common.base.Ascii;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0019\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006¨\u0006\u001d"}, d2 = {"Lcom/adsbynimbus/openrtb/enumerations/NativeAdContextSubType;", "", "()V", "APPSTORE", "", "getAPPSTORE", "()B", "ARTICLE", "getARTICLE", "AUDIO", "getAUDIO", "CHAT", "getCHAT", "EMAIL", "getEMAIL", "GENERALORMIXED", "getGENERALORMIXED", "IMAGE", "getIMAGE", "PRODUCTREVIEWS", "getPRODUCTREVIEWS", "SALES", "getSALES", "SOCIAL", "getSOCIAL", "USERGENERATED", "getUSERGENERATED", "VIDEO", "getVIDEO", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
/* compiled from: NativeAdContextSubType.kt */
public final class NativeAdContextSubType {
    private static final byte APPSTORE = Ascii.US;
    private static final byte ARTICLE = 11;
    private static final byte AUDIO = 13;
    private static final byte CHAT = Ascii.SYN;
    private static final byte EMAIL = Ascii.NAK;
    private static final byte GENERALORMIXED = 10;
    private static final byte IMAGE = 14;
    public static final NativeAdContextSubType INSTANCE = new NativeAdContextSubType();
    private static final byte PRODUCTREVIEWS = 32;
    private static final byte SALES = Ascii.RS;
    private static final byte SOCIAL = Ascii.DC4;
    private static final byte USERGENERATED = 15;
    private static final byte VIDEO = 12;

    private NativeAdContextSubType() {
    }

    public final byte getGENERALORMIXED() {
        return GENERALORMIXED;
    }

    public final byte getARTICLE() {
        return ARTICLE;
    }

    public final byte getVIDEO() {
        return VIDEO;
    }

    public final byte getAUDIO() {
        return AUDIO;
    }

    public final byte getIMAGE() {
        return IMAGE;
    }

    public final byte getUSERGENERATED() {
        return USERGENERATED;
    }

    public final byte getSOCIAL() {
        return SOCIAL;
    }

    public final byte getEMAIL() {
        return EMAIL;
    }

    public final byte getCHAT() {
        return CHAT;
    }

    public final byte getSALES() {
        return SALES;
    }

    public final byte getAPPSTORE() {
        return APPSTORE;
    }

    public final byte getPRODUCTREVIEWS() {
        return PRODUCTREVIEWS;
    }
}
