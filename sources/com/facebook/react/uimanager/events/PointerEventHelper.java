package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.R;

public class PointerEventHelper {
    public static final String CLICK = "topClick";
    public static final String POINTER_CANCEL = "topPointerCancel";
    public static final String POINTER_DOWN = "topPointerDown";
    public static final String POINTER_ENTER = "topPointerEnter";
    public static final String POINTER_LEAVE = "topPointerLeave";
    public static final String POINTER_MOVE = "topPointerMove";
    public static final String POINTER_OUT = "topPointerOut";
    public static final String POINTER_OVER = "topPointerOver";
    public static final String POINTER_TYPE_MOUSE = "mouse";
    public static final String POINTER_TYPE_PEN = "pen";
    public static final String POINTER_TYPE_TOUCH = "touch";
    public static final String POINTER_TYPE_UNKNOWN = "";
    public static final String POINTER_UP = "topPointerUp";
    private static final int X_FLAG_SUPPORTS_HOVER = 16777216;

    public enum EVENT {
        CANCEL,
        CANCEL_CAPTURE,
        CLICK,
        CLICK_CAPTURE,
        DOWN,
        DOWN_CAPTURE,
        ENTER,
        ENTER_CAPTURE,
        LEAVE,
        LEAVE_CAPTURE,
        MOVE,
        MOVE_CAPTURE,
        UP,
        UP_CAPTURE,
        OUT,
        OUT_CAPTURE,
        OVER,
        OVER_CAPTURE
    }

    public static String getW3CPointerType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "" : POINTER_TYPE_MOUSE : POINTER_TYPE_PEN : POINTER_TYPE_TOUCH;
    }

    public static int getButtons(String str, String str2, int i) {
        if (isExitEvent(str)) {
            return 0;
        }
        if (POINTER_TYPE_TOUCH.equals(str2)) {
            return 1;
        }
        return i;
    }

    public static int getButtonChange(String str, int i, int i2) {
        int i3 = 0;
        if (POINTER_TYPE_TOUCH.equals(str)) {
            return 0;
        }
        int i4 = i2 ^ i;
        if (i4 == 0) {
            return -1;
        }
        if (i4 != 1) {
            i3 = 2;
            if (i4 != 2) {
                if (i4 == 4) {
                    return 1;
                }
                if (i4 != 8) {
                    return i4 != 16 ? -1 : 4;
                }
                return 3;
            }
        }
        return i3;
    }

    /* renamed from: com.facebook.react.uimanager.events.PointerEventHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT[] r0 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT = r0
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.DOWN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.DOWN_CAPTURE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.UP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.UP_CAPTURE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.CANCEL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.CANCEL_CAPTURE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.CLICK     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.CLICK_CAPTURE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.PointerEventHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static boolean isListening(View view, EVENT event) {
        if (view == null) {
            return true;
        }
        switch (AnonymousClass1.$SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT[event.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                Integer num = (Integer) view.getTag(R.id.pointer_events);
                if (num == null) {
                    return false;
                }
                if ((num.intValue() & (1 << event.ordinal())) != 0) {
                    return true;
                }
                return false;
        }
    }

    public static int getEventCategory(String str) {
        if (str == null) {
            return 2;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1786514288:
                if (str.equals(POINTER_ENTER)) {
                    c = 0;
                    break;
                }
                break;
            case -1780335505:
                if (str.equals(POINTER_LEAVE)) {
                    c = 1;
                    break;
                }
                break;
            case -1304584214:
                if (str.equals(POINTER_DOWN)) {
                    c = 2;
                    break;
                }
                break;
            case -1304316135:
                if (str.equals(POINTER_MOVE)) {
                    c = 3;
                    break;
                }
                break;
            case -1304250340:
                if (str.equals(POINTER_OVER)) {
                    c = 4;
                    break;
                }
                break;
            case -1065042973:
                if (str.equals(POINTER_UP)) {
                    c = 5;
                    break;
                }
                break;
            case 383186882:
                if (str.equals(POINTER_CANCEL)) {
                    c = 6;
                    break;
                }
                break;
            case 1343400710:
                if (str.equals(POINTER_OUT)) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 3:
            case 4:
            case 7:
                return 4;
            case 2:
            case 5:
            case 6:
                return 3;
            default:
                return 2;
        }
    }

    public static boolean supportsHover(MotionEvent motionEvent) {
        if ((motionEvent.getFlags() & 16777216) != 0) {
            return true;
        }
        return motionEvent.isFromSource(8194);
    }

    public static boolean isExitEvent(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1780335505:
                if (str.equals(POINTER_LEAVE)) {
                    c = 0;
                    break;
                }
                break;
            case -1065042973:
                if (str.equals(POINTER_UP)) {
                    c = 1;
                    break;
                }
                break;
            case 1343400710:
                if (str.equals(POINTER_OUT)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    public static double getPressure(int i, String str) {
        if (isExitEvent(str)) {
            return 0.0d;
        }
        return i != 0 ? 0.5d : 0.0d;
    }

    public static boolean isBubblingEvent(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1304584214:
                if (str.equals(POINTER_DOWN)) {
                    c = 0;
                    break;
                }
                break;
            case -1304316135:
                if (str.equals(POINTER_MOVE)) {
                    c = 1;
                    break;
                }
                break;
            case -1304250340:
                if (str.equals(POINTER_OVER)) {
                    c = 2;
                    break;
                }
                break;
            case -1065042973:
                if (str.equals(POINTER_UP)) {
                    c = 3;
                    break;
                }
                break;
            case 383186882:
                if (str.equals(POINTER_CANCEL)) {
                    c = 4;
                    break;
                }
                break;
            case 1343400710:
                if (str.equals(POINTER_OUT)) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }
}
