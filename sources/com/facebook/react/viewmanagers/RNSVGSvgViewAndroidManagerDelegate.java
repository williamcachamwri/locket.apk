package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface;

public class RNSVGSvgViewAndroidManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGSvgViewAndroidManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGSvgViewAndroidManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r8, java.lang.String r9, java.lang.Object r10) {
        /*
            r7 = this;
            r9.hashCode()
            int r0 = r9.hashCode()
            r1 = 0
            r2 = -1
            switch(r0) {
                case -2064426617: goto L_0x023a;
                case -1989576717: goto L_0x022f;
                case -1697814026: goto L_0x0224;
                case -1567958285: goto L_0x0218;
                case -1470826662: goto L_0x020d;
                case -1308858324: goto L_0x0202;
                case -1228066334: goto L_0x01f7;
                case -1141400650: goto L_0x01ec;
                case -1122140597: goto L_0x01de;
                case -867333731: goto L_0x01d0;
                case -679581037: goto L_0x01c2;
                case -631506969: goto L_0x01b4;
                case -631278772: goto L_0x01a6;
                case -483490364: goto L_0x0198;
                case -329721498: goto L_0x018a;
                case -293492298: goto L_0x017c;
                case -252105751: goto L_0x016e;
                case -242276144: goto L_0x0160;
                case -148030058: goto L_0x0152;
                case -109689771: goto L_0x0144;
                case 3351622: goto L_0x0136;
                case 3351623: goto L_0x0128;
                case 92903173: goto L_0x011a;
                case 94842723: goto L_0x010c;
                case 240482938: goto L_0x00fd;
                case 333432965: goto L_0x00ef;
                case 503397728: goto L_0x00e1;
                case 581268560: goto L_0x00d3;
                case 588239831: goto L_0x00c5;
                case 660795168: goto L_0x00b7;
                case 722830999: goto L_0x00a9;
                case 737768677: goto L_0x009b;
                case 926871597: goto L_0x008d;
                case 1220735892: goto L_0x007f;
                case 1327599912: goto L_0x0070;
                case 1349188574: goto L_0x0062;
                case 1629011506: goto L_0x0054;
                case 1667773924: goto L_0x0046;
                case 1747724810: goto L_0x0038;
                case 1908075304: goto L_0x002a;
                case 1910855543: goto L_0x001c;
                case 2119889261: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0244
        L_0x000e:
            java.lang.String r0 = "borderStartColor"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0018
            goto L_0x0244
        L_0x0018:
            r2 = 41
            goto L_0x0244
        L_0x001c:
            java.lang.String r0 = "nextFocusRight"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0026
            goto L_0x0244
        L_0x0026:
            r2 = 40
            goto L_0x0244
        L_0x002a:
            java.lang.String r0 = "meetOrSlice"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0034
            goto L_0x0244
        L_0x0034:
            r2 = 39
            goto L_0x0244
        L_0x0038:
            java.lang.String r0 = "nativeBackgroundAndroid"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0042
            goto L_0x0244
        L_0x0042:
            r2 = 38
            goto L_0x0244
        L_0x0046:
            java.lang.String r0 = "needsOffscreenAlphaCompositing"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0050
            goto L_0x0244
        L_0x0050:
            r2 = 37
            goto L_0x0244
        L_0x0054:
            java.lang.String r0 = "focusable"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x005e
            goto L_0x0244
        L_0x005e:
            r2 = 36
            goto L_0x0244
        L_0x0062:
            java.lang.String r0 = "borderRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x006c
            goto L_0x0244
        L_0x006c:
            r2 = 35
            goto L_0x0244
        L_0x0070:
            java.lang.String r0 = "tintColor"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x007b
            goto L_0x0244
        L_0x007b:
            r2 = 34
            goto L_0x0244
        L_0x007f:
            java.lang.String r0 = "borderEndColor"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0089
            goto L_0x0244
        L_0x0089:
            r2 = 33
            goto L_0x0244
        L_0x008d:
            java.lang.String r0 = "hitSlop"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0097
            goto L_0x0244
        L_0x0097:
            r2 = 32
            goto L_0x0244
        L_0x009b:
            java.lang.String r0 = "borderStyle"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x00a5
            goto L_0x0244
        L_0x00a5:
            r2 = 31
            goto L_0x0244
        L_0x00a9:
            java.lang.String r0 = "borderColor"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x00b3
            goto L_0x0244
        L_0x00b3:
            r2 = 30
            goto L_0x0244
        L_0x00b7:
            java.lang.String r0 = "nextFocusUp"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x00c1
            goto L_0x0244
        L_0x00c1:
            r2 = 29
            goto L_0x0244
        L_0x00c5:
            java.lang.String r0 = "borderBottomRightRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x00cf
            goto L_0x0244
        L_0x00cf:
            r2 = 28
            goto L_0x0244
        L_0x00d3:
            java.lang.String r0 = "borderBottomLeftRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x00dd
            goto L_0x0244
        L_0x00dd:
            r2 = 27
            goto L_0x0244
        L_0x00e1:
            java.lang.String r0 = "nextFocusForward"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x00eb
            goto L_0x0244
        L_0x00eb:
            r2 = 26
            goto L_0x0244
        L_0x00ef:
            java.lang.String r0 = "borderTopRightRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x00f9
            goto L_0x0244
        L_0x00f9:
            r2 = 25
            goto L_0x0244
        L_0x00fd:
            java.lang.String r0 = "vbWidth"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0108
            goto L_0x0244
        L_0x0108:
            r2 = 24
            goto L_0x0244
        L_0x010c:
            java.lang.String r0 = "color"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0116
            goto L_0x0244
        L_0x0116:
            r2 = 23
            goto L_0x0244
        L_0x011a:
            java.lang.String r0 = "align"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0124
            goto L_0x0244
        L_0x0124:
            r2 = 22
            goto L_0x0244
        L_0x0128:
            java.lang.String r0 = "minY"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0132
            goto L_0x0244
        L_0x0132:
            r2 = 21
            goto L_0x0244
        L_0x0136:
            java.lang.String r0 = "minX"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0140
            goto L_0x0244
        L_0x0140:
            r2 = 20
            goto L_0x0244
        L_0x0144:
            java.lang.String r0 = "nativeForegroundAndroid"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x014e
            goto L_0x0244
        L_0x014e:
            r2 = 19
            goto L_0x0244
        L_0x0152:
            java.lang.String r0 = "borderBottomEndRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x015c
            goto L_0x0244
        L_0x015c:
            r2 = 18
            goto L_0x0244
        L_0x0160:
            java.lang.String r0 = "borderLeftColor"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x016a
            goto L_0x0244
        L_0x016a:
            r2 = 17
            goto L_0x0244
        L_0x016e:
            java.lang.String r0 = "removeClippedSubviews"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0178
            goto L_0x0244
        L_0x0178:
            r2 = 16
            goto L_0x0244
        L_0x017c:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0186
            goto L_0x0244
        L_0x0186:
            r2 = 15
            goto L_0x0244
        L_0x018a:
            java.lang.String r0 = "bbWidth"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0194
            goto L_0x0244
        L_0x0194:
            r2 = 14
            goto L_0x0244
        L_0x0198:
            java.lang.String r0 = "borderTopEndRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01a2
            goto L_0x0244
        L_0x01a2:
            r2 = 13
            goto L_0x0244
        L_0x01a6:
            java.lang.String r0 = "nextFocusLeft"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01b0
            goto L_0x0244
        L_0x01b0:
            r2 = 12
            goto L_0x0244
        L_0x01b4:
            java.lang.String r0 = "nextFocusDown"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01be
            goto L_0x0244
        L_0x01be:
            r2 = 11
            goto L_0x0244
        L_0x01c2:
            java.lang.String r0 = "hasTVPreferredFocus"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01cc
            goto L_0x0244
        L_0x01cc:
            r2 = 10
            goto L_0x0244
        L_0x01d0:
            java.lang.String r0 = "borderBottomStartRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01da
            goto L_0x0244
        L_0x01da:
            r2 = 9
            goto L_0x0244
        L_0x01de:
            java.lang.String r0 = "borderTopStartRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01e8
            goto L_0x0244
        L_0x01e8:
            r2 = 8
            goto L_0x0244
        L_0x01ec:
            java.lang.String r0 = "accessible"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01f5
            goto L_0x0244
        L_0x01f5:
            r2 = 7
            goto L_0x0244
        L_0x01f7:
            java.lang.String r0 = "borderTopLeftRadius"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0200
            goto L_0x0244
        L_0x0200:
            r2 = 6
            goto L_0x0244
        L_0x0202:
            java.lang.String r0 = "borderBottomColor"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x020b
            goto L_0x0244
        L_0x020b:
            r2 = 5
            goto L_0x0244
        L_0x020d:
            java.lang.String r0 = "borderTopColor"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0216
            goto L_0x0244
        L_0x0216:
            r2 = 4
            goto L_0x0244
        L_0x0218:
            java.lang.String r0 = "vbHeight"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0222
            goto L_0x0244
        L_0x0222:
            r2 = 3
            goto L_0x0244
        L_0x0224:
            java.lang.String r0 = "backfaceVisibility"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x022d
            goto L_0x0244
        L_0x022d:
            r2 = 2
            goto L_0x0244
        L_0x022f:
            java.lang.String r0 = "borderRightColor"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0238
            goto L_0x0244
        L_0x0238:
            r2 = 1
            goto L_0x0244
        L_0x023a:
            java.lang.String r0 = "bbHeight"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x0243
            goto L_0x0244
        L_0x0243:
            r2 = r1
        L_0x0244:
            r0 = 0
            r3 = 2143289344(0x7fc00000, float:NaN)
            r4 = 0
            r6 = 0
            switch(r2) {
                case 0: goto L_0x051d;
                case 1: goto L_0x050d;
                case 2: goto L_0x04ff;
                case 3: goto L_0x04ee;
                case 4: goto L_0x04de;
                case 5: goto L_0x04ce;
                case 6: goto L_0x04bc;
                case 7: goto L_0x04aa;
                case 8: goto L_0x0498;
                case 9: goto L_0x0486;
                case 10: goto L_0x0474;
                case 11: goto L_0x0462;
                case 12: goto L_0x0450;
                case 13: goto L_0x043e;
                case 14: goto L_0x0414;
                case 15: goto L_0x0405;
                case 16: goto L_0x03f3;
                case 17: goto L_0x03e2;
                case 18: goto L_0x03d0;
                case 19: goto L_0x03c5;
                case 20: goto L_0x03b3;
                case 21: goto L_0x03a1;
                case 22: goto L_0x0392;
                case 23: goto L_0x0381;
                case 24: goto L_0x036f;
                case 25: goto L_0x035d;
                case 26: goto L_0x034b;
                case 27: goto L_0x0339;
                case 28: goto L_0x0327;
                case 29: goto L_0x0315;
                case 30: goto L_0x0304;
                case 31: goto L_0x02f5;
                case 32: goto L_0x02ea;
                case 33: goto L_0x02d9;
                case 34: goto L_0x02c8;
                case 35: goto L_0x02b6;
                case 36: goto L_0x02a4;
                case 37: goto L_0x0292;
                case 38: goto L_0x0287;
                case 39: goto L_0x0275;
                case 40: goto L_0x0263;
                case 41: goto L_0x0252;
                default: goto L_0x024d;
            }
        L_0x024d:
            super.setProperty(r8, r9, r10)
            goto L_0x0543
        L_0x0252:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setBorderStartColor(r8, r10)
            goto L_0x0543
        L_0x0263:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x026a
            goto L_0x0270
        L_0x026a:
            java.lang.Double r10 = (java.lang.Double) r10
            int r1 = r10.intValue()
        L_0x0270:
            r9.setNextFocusRight(r8, r1)
            goto L_0x0543
        L_0x0275:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x027c
            goto L_0x0282
        L_0x027c:
            java.lang.Double r10 = (java.lang.Double) r10
            int r1 = r10.intValue()
        L_0x0282:
            r9.setMeetOrSlice(r8, r1)
            goto L_0x0543
        L_0x0287:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            com.facebook.react.bridge.ReadableMap r10 = (com.facebook.react.bridge.ReadableMap) r10
            r9.setNativeBackgroundAndroid(r8, r10)
            goto L_0x0543
        L_0x0292:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0299
            goto L_0x029f
        L_0x0299:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r1 = r10.booleanValue()
        L_0x029f:
            r9.setNeedsOffscreenAlphaCompositing(r8, r1)
            goto L_0x0543
        L_0x02a4:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x02ab
            goto L_0x02b1
        L_0x02ab:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r1 = r10.booleanValue()
        L_0x02b1:
            r9.setFocusable(r8, r1)
            goto L_0x0543
        L_0x02b6:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x02bd
            goto L_0x02c3
        L_0x02bd:
            java.lang.Double r10 = (java.lang.Double) r10
            double r4 = r10.doubleValue()
        L_0x02c3:
            r9.setBorderRadius(r8, r4)
            goto L_0x0543
        L_0x02c8:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setTintColor(r8, r10)
            goto L_0x0543
        L_0x02d9:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setBorderEndColor(r8, r10)
            goto L_0x0543
        L_0x02ea:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            com.facebook.react.bridge.ReadableMap r10 = (com.facebook.react.bridge.ReadableMap) r10
            r9.setHitSlop(r8, r10)
            goto L_0x0543
        L_0x02f5:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x02fc
            goto L_0x02ff
        L_0x02fc:
            r6 = r10
            java.lang.String r6 = (java.lang.String) r6
        L_0x02ff:
            r9.setBorderStyle(r8, r6)
            goto L_0x0543
        L_0x0304:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setBorderColor(r8, r10)
            goto L_0x0543
        L_0x0315:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x031c
            goto L_0x0322
        L_0x031c:
            java.lang.Double r10 = (java.lang.Double) r10
            int r1 = r10.intValue()
        L_0x0322:
            r9.setNextFocusUp(r8, r1)
            goto L_0x0543
        L_0x0327:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x032e
            goto L_0x0334
        L_0x032e:
            java.lang.Double r10 = (java.lang.Double) r10
            double r4 = r10.doubleValue()
        L_0x0334:
            r9.setBorderBottomRightRadius(r8, r4)
            goto L_0x0543
        L_0x0339:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0340
            goto L_0x0346
        L_0x0340:
            java.lang.Double r10 = (java.lang.Double) r10
            double r4 = r10.doubleValue()
        L_0x0346:
            r9.setBorderBottomLeftRadius(r8, r4)
            goto L_0x0543
        L_0x034b:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0352
            goto L_0x0358
        L_0x0352:
            java.lang.Double r10 = (java.lang.Double) r10
            int r1 = r10.intValue()
        L_0x0358:
            r9.setNextFocusForward(r8, r1)
            goto L_0x0543
        L_0x035d:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0364
            goto L_0x036a
        L_0x0364:
            java.lang.Double r10 = (java.lang.Double) r10
            double r4 = r10.doubleValue()
        L_0x036a:
            r9.setBorderTopRightRadius(r8, r4)
            goto L_0x0543
        L_0x036f:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0376
            goto L_0x037c
        L_0x0376:
            java.lang.Double r10 = (java.lang.Double) r10
            float r3 = r10.floatValue()
        L_0x037c:
            r9.setVbWidth(r8, r3)
            goto L_0x0543
        L_0x0381:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x0543
        L_0x0392:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0399
            goto L_0x039c
        L_0x0399:
            r6 = r10
            java.lang.String r6 = (java.lang.String) r6
        L_0x039c:
            r9.setAlign(r8, r6)
            goto L_0x0543
        L_0x03a1:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x03a8
            goto L_0x03ae
        L_0x03a8:
            java.lang.Double r10 = (java.lang.Double) r10
            float r3 = r10.floatValue()
        L_0x03ae:
            r9.setMinY(r8, r3)
            goto L_0x0543
        L_0x03b3:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x03ba
            goto L_0x03c0
        L_0x03ba:
            java.lang.Double r10 = (java.lang.Double) r10
            float r3 = r10.floatValue()
        L_0x03c0:
            r9.setMinX(r8, r3)
            goto L_0x0543
        L_0x03c5:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            com.facebook.react.bridge.ReadableMap r10 = (com.facebook.react.bridge.ReadableMap) r10
            r9.setNativeForegroundAndroid(r8, r10)
            goto L_0x0543
        L_0x03d0:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x03d7
            goto L_0x03dd
        L_0x03d7:
            java.lang.Double r10 = (java.lang.Double) r10
            float r0 = r10.floatValue()
        L_0x03dd:
            r9.setBorderBottomEndRadius(r8, r0)
            goto L_0x0543
        L_0x03e2:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setBorderLeftColor(r8, r10)
            goto L_0x0543
        L_0x03f3:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x03fa
            goto L_0x0400
        L_0x03fa:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r1 = r10.booleanValue()
        L_0x0400:
            r9.setRemoveClippedSubviews(r8, r1)
            goto L_0x0543
        L_0x0405:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x040c
            goto L_0x040f
        L_0x040c:
            r6 = r10
            java.lang.String r6 = (java.lang.String) r6
        L_0x040f:
            r9.setPointerEvents(r8, r6)
            goto L_0x0543
        L_0x0414:
            boolean r9 = r10 instanceof java.lang.String
            if (r9 == 0) goto L_0x0423
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            java.lang.String r10 = (java.lang.String) r10
            r9.setBbWidth(r8, (java.lang.String) r10)
            goto L_0x0543
        L_0x0423:
            boolean r9 = r10 instanceof java.lang.Double
            if (r9 == 0) goto L_0x0432
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            java.lang.Double r10 = (java.lang.Double) r10
            r9.setBbWidth(r8, (java.lang.Double) r10)
            goto L_0x0543
        L_0x0432:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            r10 = r6
            java.lang.Double r10 = (java.lang.Double) r10
            r9.setBbWidth(r8, (java.lang.Double) r6)
            goto L_0x0543
        L_0x043e:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0445
            goto L_0x044b
        L_0x0445:
            java.lang.Double r10 = (java.lang.Double) r10
            float r0 = r10.floatValue()
        L_0x044b:
            r9.setBorderTopEndRadius(r8, r0)
            goto L_0x0543
        L_0x0450:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0457
            goto L_0x045d
        L_0x0457:
            java.lang.Double r10 = (java.lang.Double) r10
            int r1 = r10.intValue()
        L_0x045d:
            r9.setNextFocusLeft(r8, r1)
            goto L_0x0543
        L_0x0462:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0469
            goto L_0x046f
        L_0x0469:
            java.lang.Double r10 = (java.lang.Double) r10
            int r1 = r10.intValue()
        L_0x046f:
            r9.setNextFocusDown(r8, r1)
            goto L_0x0543
        L_0x0474:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x047b
            goto L_0x0481
        L_0x047b:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r1 = r10.booleanValue()
        L_0x0481:
            r9.setHasTVPreferredFocus(r8, r1)
            goto L_0x0543
        L_0x0486:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x048d
            goto L_0x0493
        L_0x048d:
            java.lang.Double r10 = (java.lang.Double) r10
            float r0 = r10.floatValue()
        L_0x0493:
            r9.setBorderBottomStartRadius(r8, r0)
            goto L_0x0543
        L_0x0498:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x049f
            goto L_0x04a5
        L_0x049f:
            java.lang.Double r10 = (java.lang.Double) r10
            float r0 = r10.floatValue()
        L_0x04a5:
            r9.setBorderTopStartRadius(r8, r0)
            goto L_0x0543
        L_0x04aa:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x04b1
            goto L_0x04b7
        L_0x04b1:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r1 = r10.booleanValue()
        L_0x04b7:
            r9.setAccessible(r8, r1)
            goto L_0x0543
        L_0x04bc:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x04c3
            goto L_0x04c9
        L_0x04c3:
            java.lang.Double r10 = (java.lang.Double) r10
            double r4 = r10.doubleValue()
        L_0x04c9:
            r9.setBorderTopLeftRadius(r8, r4)
            goto L_0x0543
        L_0x04ce:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setBorderBottomColor(r8, r10)
            goto L_0x0543
        L_0x04de:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setBorderTopColor(r8, r10)
            goto L_0x0543
        L_0x04ee:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x04f5
            goto L_0x04fb
        L_0x04f5:
            java.lang.Double r10 = (java.lang.Double) r10
            float r3 = r10.floatValue()
        L_0x04fb:
            r9.setVbHeight(r8, r3)
            goto L_0x0543
        L_0x04ff:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            if (r10 != 0) goto L_0x0506
            goto L_0x0509
        L_0x0506:
            r6 = r10
            java.lang.String r6 = (java.lang.String) r6
        L_0x0509:
            r9.setBackfaceVisibility(r8, r6)
            goto L_0x0543
        L_0x050d:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setBorderRightColor(r8, r10)
            goto L_0x0543
        L_0x051d:
            boolean r9 = r10 instanceof java.lang.String
            if (r9 == 0) goto L_0x052b
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            java.lang.String r10 = (java.lang.String) r10
            r9.setBbHeight(r8, (java.lang.String) r10)
            goto L_0x0543
        L_0x052b:
            boolean r9 = r10 instanceof java.lang.Double
            if (r9 == 0) goto L_0x0539
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            java.lang.Double r10 = (java.lang.Double) r10
            r9.setBbHeight(r8, (java.lang.Double) r10)
            goto L_0x0543
        L_0x0539:
            com.facebook.react.uimanager.BaseViewManagerInterface r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r9
            r10 = r6
            java.lang.Double r10 = (java.lang.Double) r10
            r9.setBbHeight(r8, (java.lang.Double) r6)
        L_0x0543:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
