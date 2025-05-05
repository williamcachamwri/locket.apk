package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface;

public class RNSVGLinearGradientManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGLinearGradientManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGLinearGradientManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r4, java.lang.String r5, java.lang.Object r6) {
        /*
            r3 = this;
            r5.hashCode()
            int r0 = r5.hashCode()
            r1 = 0
            r2 = -1
            switch(r0) {
                case -1932235233: goto L_0x00f9;
                case -1267206133: goto L_0x00ee;
                case -1081239615: goto L_0x00e3;
                case -933864895: goto L_0x00d8;
                case -933857362: goto L_0x00cd;
                case -293492298: goto L_0x00c2;
                case 3769: goto L_0x00b6;
                case 3770: goto L_0x00aa;
                case 3800: goto L_0x009b;
                case 3801: goto L_0x008c;
                case 3344108: goto L_0x007e;
                case 3373707: goto L_0x0070;
                case 89650992: goto L_0x0062;
                case 217109576: goto L_0x0054;
                case 917656469: goto L_0x0046;
                case 917735020: goto L_0x0038;
                case 1671764162: goto L_0x002a;
                case 1822665244: goto L_0x001c;
                case 1847674614: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0103
        L_0x000e:
            java.lang.String r0 = "responsible"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0018
            goto L_0x0103
        L_0x0018:
            r2 = 18
            goto L_0x0103
        L_0x001c:
            java.lang.String r0 = "gradientTransform"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0026
            goto L_0x0103
        L_0x0026:
            r2 = 17
            goto L_0x0103
        L_0x002a:
            java.lang.String r0 = "display"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0034
            goto L_0x0103
        L_0x0034:
            r2 = 16
            goto L_0x0103
        L_0x0038:
            java.lang.String r0 = "clipRule"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0042
            goto L_0x0103
        L_0x0042:
            r2 = 15
            goto L_0x0103
        L_0x0046:
            java.lang.String r0 = "clipPath"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0050
            goto L_0x0103
        L_0x0050:
            r2 = 14
            goto L_0x0103
        L_0x0054:
            java.lang.String r0 = "markerStart"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x005e
            goto L_0x0103
        L_0x005e:
            r2 = 13
            goto L_0x0103
        L_0x0062:
            java.lang.String r0 = "gradient"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x006c
            goto L_0x0103
        L_0x006c:
            r2 = 12
            goto L_0x0103
        L_0x0070:
            java.lang.String r0 = "name"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x007a
            goto L_0x0103
        L_0x007a:
            r2 = 11
            goto L_0x0103
        L_0x007e:
            java.lang.String r0 = "mask"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0088
            goto L_0x0103
        L_0x0088:
            r2 = 10
            goto L_0x0103
        L_0x008c:
            java.lang.String r0 = "y2"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0097
            goto L_0x0103
        L_0x0097:
            r2 = 9
            goto L_0x0103
        L_0x009b:
            java.lang.String r0 = "y1"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00a6
            goto L_0x0103
        L_0x00a6:
            r2 = 8
            goto L_0x0103
        L_0x00aa:
            java.lang.String r0 = "x2"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00b4
            goto L_0x0103
        L_0x00b4:
            r2 = 7
            goto L_0x0103
        L_0x00b6:
            java.lang.String r0 = "x1"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00c0
            goto L_0x0103
        L_0x00c0:
            r2 = 6
            goto L_0x0103
        L_0x00c2:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00cb
            goto L_0x0103
        L_0x00cb:
            r2 = 5
            goto L_0x0103
        L_0x00cd:
            java.lang.String r0 = "markerMid"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00d6
            goto L_0x0103
        L_0x00d6:
            r2 = 4
            goto L_0x0103
        L_0x00d8:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00e1
            goto L_0x0103
        L_0x00e1:
            r2 = 3
            goto L_0x0103
        L_0x00e3:
            java.lang.String r0 = "matrix"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00ec
            goto L_0x0103
        L_0x00ec:
            r2 = 2
            goto L_0x0103
        L_0x00ee:
            java.lang.String r0 = "opacity"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00f7
            goto L_0x0103
        L_0x00f7:
            r2 = 1
            goto L_0x0103
        L_0x00f9:
            java.lang.String r0 = "gradientUnits"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0102
            goto L_0x0103
        L_0x0102:
            r2 = r1
        L_0x0103:
            r0 = 0
            switch(r2) {
                case 0: goto L_0x027c;
                case 1: goto L_0x026b;
                case 2: goto L_0x0261;
                case 3: goto L_0x0253;
                case 4: goto L_0x0245;
                case 5: goto L_0x0237;
                case 6: goto L_0x020f;
                case 7: goto L_0x01e5;
                case 8: goto L_0x01bb;
                case 9: goto L_0x0191;
                case 10: goto L_0x0182;
                case 11: goto L_0x0173;
                case 12: goto L_0x0168;
                case 13: goto L_0x0159;
                case 14: goto L_0x014a;
                case 15: goto L_0x0138;
                case 16: goto L_0x0129;
                case 17: goto L_0x011e;
                case 18: goto L_0x010c;
                default: goto L_0x0107;
            }
        L_0x0107:
            super.setProperty(r4, r5, r6)
            goto L_0x028c
        L_0x010c:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x0113
            goto L_0x0119
        L_0x0113:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r1 = r6.booleanValue()
        L_0x0119:
            r5.setResponsible(r4, r1)
            goto L_0x028c
        L_0x011e:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            com.facebook.react.bridge.ReadableArray r6 = (com.facebook.react.bridge.ReadableArray) r6
            r5.setGradientTransform(r4, r6)
            goto L_0x028c
        L_0x0129:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x0130
            goto L_0x0133
        L_0x0130:
            r0 = r6
            java.lang.String r0 = (java.lang.String) r0
        L_0x0133:
            r5.setDisplay(r4, r0)
            goto L_0x028c
        L_0x0138:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x013f
            goto L_0x0145
        L_0x013f:
            java.lang.Double r6 = (java.lang.Double) r6
            int r1 = r6.intValue()
        L_0x0145:
            r5.setClipRule(r4, r1)
            goto L_0x028c
        L_0x014a:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x0151
            goto L_0x0154
        L_0x0151:
            r0 = r6
            java.lang.String r0 = (java.lang.String) r0
        L_0x0154:
            r5.setClipPath(r4, r0)
            goto L_0x028c
        L_0x0159:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x0160
            goto L_0x0163
        L_0x0160:
            r0 = r6
            java.lang.String r0 = (java.lang.String) r0
        L_0x0163:
            r5.setMarkerStart(r4, r0)
            goto L_0x028c
        L_0x0168:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            com.facebook.react.bridge.ReadableArray r6 = (com.facebook.react.bridge.ReadableArray) r6
            r5.setGradient(r4, r6)
            goto L_0x028c
        L_0x0173:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x017a
            goto L_0x017d
        L_0x017a:
            r0 = r6
            java.lang.String r0 = (java.lang.String) r0
        L_0x017d:
            r5.setName(r4, r0)
            goto L_0x028c
        L_0x0182:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x0189
            goto L_0x018c
        L_0x0189:
            r0 = r6
            java.lang.String r0 = (java.lang.String) r0
        L_0x018c:
            r5.setMask(r4, r0)
            goto L_0x028c
        L_0x0191:
            boolean r5 = r6 instanceof java.lang.String
            if (r5 == 0) goto L_0x01a0
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            java.lang.String r6 = (java.lang.String) r6
            r5.setY2(r4, (java.lang.String) r6)
            goto L_0x028c
        L_0x01a0:
            boolean r5 = r6 instanceof java.lang.Double
            if (r5 == 0) goto L_0x01af
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            java.lang.Double r6 = (java.lang.Double) r6
            r5.setY2(r4, (java.lang.Double) r6)
            goto L_0x028c
        L_0x01af:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            r6 = r0
            java.lang.Double r6 = (java.lang.Double) r6
            r5.setY2(r4, (java.lang.Double) r0)
            goto L_0x028c
        L_0x01bb:
            boolean r5 = r6 instanceof java.lang.String
            if (r5 == 0) goto L_0x01ca
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            java.lang.String r6 = (java.lang.String) r6
            r5.setY1(r4, (java.lang.String) r6)
            goto L_0x028c
        L_0x01ca:
            boolean r5 = r6 instanceof java.lang.Double
            if (r5 == 0) goto L_0x01d9
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            java.lang.Double r6 = (java.lang.Double) r6
            r5.setY1(r4, (java.lang.Double) r6)
            goto L_0x028c
        L_0x01d9:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            r6 = r0
            java.lang.Double r6 = (java.lang.Double) r6
            r5.setY1(r4, (java.lang.Double) r0)
            goto L_0x028c
        L_0x01e5:
            boolean r5 = r6 instanceof java.lang.String
            if (r5 == 0) goto L_0x01f4
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            java.lang.String r6 = (java.lang.String) r6
            r5.setX2(r4, (java.lang.String) r6)
            goto L_0x028c
        L_0x01f4:
            boolean r5 = r6 instanceof java.lang.Double
            if (r5 == 0) goto L_0x0203
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            java.lang.Double r6 = (java.lang.Double) r6
            r5.setX2(r4, (java.lang.Double) r6)
            goto L_0x028c
        L_0x0203:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            r6 = r0
            java.lang.Double r6 = (java.lang.Double) r6
            r5.setX2(r4, (java.lang.Double) r0)
            goto L_0x028c
        L_0x020f:
            boolean r5 = r6 instanceof java.lang.String
            if (r5 == 0) goto L_0x021e
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            java.lang.String r6 = (java.lang.String) r6
            r5.setX1(r4, (java.lang.String) r6)
            goto L_0x028c
        L_0x021e:
            boolean r5 = r6 instanceof java.lang.Double
            if (r5 == 0) goto L_0x022c
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            java.lang.Double r6 = (java.lang.Double) r6
            r5.setX1(r4, (java.lang.Double) r6)
            goto L_0x028c
        L_0x022c:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            r6 = r0
            java.lang.Double r6 = (java.lang.Double) r6
            r5.setX1(r4, (java.lang.Double) r0)
            goto L_0x028c
        L_0x0237:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x023e
            goto L_0x0241
        L_0x023e:
            r0 = r6
            java.lang.String r0 = (java.lang.String) r0
        L_0x0241:
            r5.setPointerEvents(r4, r0)
            goto L_0x028c
        L_0x0245:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x024c
            goto L_0x024f
        L_0x024c:
            r0 = r6
            java.lang.String r0 = (java.lang.String) r0
        L_0x024f:
            r5.setMarkerMid(r4, r0)
            goto L_0x028c
        L_0x0253:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x025a
            goto L_0x025d
        L_0x025a:
            r0 = r6
            java.lang.String r0 = (java.lang.String) r0
        L_0x025d:
            r5.setMarkerEnd(r4, r0)
            goto L_0x028c
        L_0x0261:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            com.facebook.react.bridge.ReadableArray r6 = (com.facebook.react.bridge.ReadableArray) r6
            r5.setMatrix(r4, r6)
            goto L_0x028c
        L_0x026b:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            if (r6 != 0) goto L_0x0272
            r6 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0278
        L_0x0272:
            java.lang.Double r6 = (java.lang.Double) r6
            float r6 = r6.floatValue()
        L_0x0278:
            r5.setOpacity(r4, r6)
            goto L_0x028c
        L_0x027c:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r5 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r5
            if (r6 != 0) goto L_0x0283
            goto L_0x0289
        L_0x0283:
            java.lang.Double r6 = (java.lang.Double) r6
            int r1 = r6.intValue()
        L_0x0289:
            r5.setGradientUnits(r4, r1)
        L_0x028c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGLinearGradientManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
