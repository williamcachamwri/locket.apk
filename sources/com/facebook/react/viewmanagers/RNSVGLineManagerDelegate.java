package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGLineManagerInterface;

public class RNSVGLineManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGLineManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGLineManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r7, java.lang.String r8, java.lang.Object r9) {
        /*
            r6 = this;
            r8.hashCode()
            int r0 = r8.hashCode()
            r1 = 1
            r2 = 0
            r3 = -1
            switch(r0) {
                case -1267206133: goto L_0x018f;
                case -1081239615: goto L_0x0184;
                case -993894751: goto L_0x0179;
                case -933864895: goto L_0x016e;
                case -933857362: goto L_0x0163;
                case -891980232: goto L_0x0157;
                case -729118945: goto L_0x014c;
                case -416535885: goto L_0x0140;
                case -293492298: goto L_0x0132;
                case -53677816: goto L_0x0124;
                case -44578051: goto L_0x0115;
                case 3769: goto L_0x0106;
                case 3770: goto L_0x00f7;
                case 3800: goto L_0x00e8;
                case 3801: goto L_0x00d9;
                case 3143043: goto L_0x00cb;
                case 3344108: goto L_0x00bd;
                case 3373707: goto L_0x00af;
                case 78845486: goto L_0x00a0;
                case 104482996: goto L_0x0091;
                case 217109576: goto L_0x0083;
                case 401643183: goto L_0x0074;
                case 917656469: goto L_0x0066;
                case 917735020: goto L_0x0058;
                case 1027575302: goto L_0x0049;
                case 1671764162: goto L_0x003b;
                case 1790285174: goto L_0x002c;
                case 1847674614: goto L_0x001e;
                case 1924065902: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0199
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x0199
        L_0x001a:
            r3 = 28
            goto L_0x0199
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x0199
        L_0x0028:
            r3 = 27
            goto L_0x0199
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x0199
        L_0x0037:
            r3 = 26
            goto L_0x0199
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0199
        L_0x0045:
            r3 = 25
            goto L_0x0199
        L_0x0049:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x0199
        L_0x0054:
            r3 = 24
            goto L_0x0199
        L_0x0058:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x0199
        L_0x0062:
            r3 = 23
            goto L_0x0199
        L_0x0066:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x0199
        L_0x0070:
            r3 = 22
            goto L_0x0199
        L_0x0074:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x0199
        L_0x007f:
            r3 = 21
            goto L_0x0199
        L_0x0083:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x0199
        L_0x008d:
            r3 = 20
            goto L_0x0199
        L_0x0091:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009c
            goto L_0x0199
        L_0x009c:
            r3 = 19
            goto L_0x0199
        L_0x00a0:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00ab
            goto L_0x0199
        L_0x00ab:
            r3 = 18
            goto L_0x0199
        L_0x00af:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b9
            goto L_0x0199
        L_0x00b9:
            r3 = 17
            goto L_0x0199
        L_0x00bd:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c7
            goto L_0x0199
        L_0x00c7:
            r3 = 16
            goto L_0x0199
        L_0x00cb:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d5
            goto L_0x0199
        L_0x00d5:
            r3 = 15
            goto L_0x0199
        L_0x00d9:
            java.lang.String r0 = "y2"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e4
            goto L_0x0199
        L_0x00e4:
            r3 = 14
            goto L_0x0199
        L_0x00e8:
            java.lang.String r0 = "y1"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f3
            goto L_0x0199
        L_0x00f3:
            r3 = 13
            goto L_0x0199
        L_0x00f7:
            java.lang.String r0 = "x2"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0102
            goto L_0x0199
        L_0x0102:
            r3 = 12
            goto L_0x0199
        L_0x0106:
            java.lang.String r0 = "x1"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0111
            goto L_0x0199
        L_0x0111:
            r3 = 11
            goto L_0x0199
        L_0x0115:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0120
            goto L_0x0199
        L_0x0120:
            r3 = 10
            goto L_0x0199
        L_0x0124:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012e
            goto L_0x0199
        L_0x012e:
            r3 = 9
            goto L_0x0199
        L_0x0132:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x013c
            goto L_0x0199
        L_0x013c:
            r3 = 8
            goto L_0x0199
        L_0x0140:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x014a
            goto L_0x0199
        L_0x014a:
            r3 = 7
            goto L_0x0199
        L_0x014c:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0155
            goto L_0x0199
        L_0x0155:
            r3 = 6
            goto L_0x0199
        L_0x0157:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0161
            goto L_0x0199
        L_0x0161:
            r3 = 5
            goto L_0x0199
        L_0x0163:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x016c
            goto L_0x0199
        L_0x016c:
            r3 = 4
            goto L_0x0199
        L_0x016e:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0177
            goto L_0x0199
        L_0x0177:
            r3 = 3
            goto L_0x0199
        L_0x0179:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0182
            goto L_0x0199
        L_0x0182:
            r3 = 2
            goto L_0x0199
        L_0x0184:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x018d
            goto L_0x0199
        L_0x018d:
            r3 = r1
            goto L_0x0199
        L_0x018f:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0198
            goto L_0x0199
        L_0x0198:
            r3 = r2
        L_0x0199:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x03e5;
                case 1: goto L_0x03db;
                case 2: goto L_0x03d1;
                case 3: goto L_0x03c3;
                case 4: goto L_0x03b5;
                case 5: goto L_0x03ab;
                case 6: goto L_0x039a;
                case 7: goto L_0x0389;
                case 8: goto L_0x037a;
                case 9: goto L_0x0368;
                case 10: goto L_0x0356;
                case 11: goto L_0x032c;
                case 12: goto L_0x0302;
                case 13: goto L_0x02d8;
                case 14: goto L_0x02ae;
                case 15: goto L_0x02a3;
                case 16: goto L_0x0294;
                case 17: goto L_0x0285;
                case 18: goto L_0x0273;
                case 19: goto L_0x0261;
                case 20: goto L_0x0252;
                case 21: goto L_0x0234;
                case 22: goto L_0x0225;
                case 23: goto L_0x0213;
                case 24: goto L_0x0201;
                case 25: goto L_0x01f2;
                case 26: goto L_0x01e0;
                case 27: goto L_0x01ce;
                case 28: goto L_0x01a5;
                default: goto L_0x01a0;
            }
        L_0x01a0:
            super.setProperty(r7, r8, r9)
            goto L_0x03f3
        L_0x01a5:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x01b4
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x03f3
        L_0x01b4:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x01c3
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x03f3
        L_0x01c3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x03f3
        L_0x01ce:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x01d5
            goto L_0x01db
        L_0x01d5:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x01db:
            r8.setResponsible(r7, r2)
            goto L_0x03f3
        L_0x01e0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x01e7
            goto L_0x01ed
        L_0x01e7:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x01ed:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x03f3
        L_0x01f2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x01f9
            goto L_0x01fc
        L_0x01f9:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x01fc:
            r8.setDisplay(r7, r5)
            goto L_0x03f3
        L_0x0201:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x0208
            goto L_0x020e
        L_0x0208:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x020e:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x03f3
        L_0x0213:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x021a
            goto L_0x0220
        L_0x021a:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0220:
            r8.setClipRule(r7, r2)
            goto L_0x03f3
        L_0x0225:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x022c
            goto L_0x022f
        L_0x022c:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x022f:
            r8.setClipPath(r7, r5)
            goto L_0x03f3
        L_0x0234:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0243
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x03f3
        L_0x0243:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x03f3
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x03f3
        L_0x0252:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x0259
            goto L_0x025c
        L_0x0259:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x025c:
            r8.setMarkerStart(r7, r5)
            goto L_0x03f3
        L_0x0261:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x0268
            goto L_0x026e
        L_0x0268:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x026e:
            r8.setVectorEffect(r7, r2)
            goto L_0x03f3
        L_0x0273:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x027a
            goto L_0x0280
        L_0x027a:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x0280:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x03f3
        L_0x0285:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x028c
            goto L_0x028f
        L_0x028c:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x028f:
            r8.setName(r7, r5)
            goto L_0x03f3
        L_0x0294:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x029b
            goto L_0x029e
        L_0x029b:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x029e:
            r8.setMask(r7, r5)
            goto L_0x03f3
        L_0x02a3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x03f3
        L_0x02ae:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02bd
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setY2(r7, (java.lang.String) r9)
            goto L_0x03f3
        L_0x02bd:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02cc
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY2(r7, (java.lang.Double) r9)
            goto L_0x03f3
        L_0x02cc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY2(r7, (java.lang.Double) r5)
            goto L_0x03f3
        L_0x02d8:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02e7
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setY1(r7, (java.lang.String) r9)
            goto L_0x03f3
        L_0x02e7:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02f6
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY1(r7, (java.lang.Double) r9)
            goto L_0x03f3
        L_0x02f6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY1(r7, (java.lang.Double) r5)
            goto L_0x03f3
        L_0x0302:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0311
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setX2(r7, (java.lang.String) r9)
            goto L_0x03f3
        L_0x0311:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0320
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX2(r7, (java.lang.Double) r9)
            goto L_0x03f3
        L_0x0320:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX2(r7, (java.lang.Double) r5)
            goto L_0x03f3
        L_0x032c:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x033b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setX1(r7, (java.lang.String) r9)
            goto L_0x03f3
        L_0x033b:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x034a
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX1(r7, (java.lang.Double) r9)
            goto L_0x03f3
        L_0x034a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX1(r7, (java.lang.Double) r5)
            goto L_0x03f3
        L_0x0356:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x035d
            goto L_0x0363
        L_0x035d:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x0363:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x03f3
        L_0x0368:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x036f
            goto L_0x0375
        L_0x036f:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0375:
            r8.setFillOpacity(r7, r4)
            goto L_0x03f3
        L_0x037a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x0381
            goto L_0x0384
        L_0x0381:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0384:
            r8.setPointerEvents(r7, r5)
            goto L_0x03f3
        L_0x0389:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x0390
            goto L_0x0396
        L_0x0390:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0396:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x03f3
        L_0x039a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x03a1
            goto L_0x03a7
        L_0x03a1:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x03a7:
            r8.setFillRule(r7, r1)
            goto L_0x03f3
        L_0x03ab:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x03f3
        L_0x03b5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x03bc
            goto L_0x03bf
        L_0x03bc:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03bf:
            r8.setMarkerMid(r7, r5)
            goto L_0x03f3
        L_0x03c3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            if (r9 != 0) goto L_0x03ca
            goto L_0x03cd
        L_0x03ca:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03cd:
            r8.setMarkerEnd(r7, r5)
            goto L_0x03f3
        L_0x03d1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x03f3
        L_0x03db:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGLineManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGLineManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x03f3
        L_0x03e5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x03ea
            goto L_0x03f0
        L_0x03ea:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03f0:
            r8.setOpacity(r7, r4)
        L_0x03f3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGLineManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
