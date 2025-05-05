package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGPathManagerInterface;

public class RNSVGPathManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGPathManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGPathManagerDelegate(U u) {
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
                case -1267206133: goto L_0x0161;
                case -1081239615: goto L_0x0156;
                case -993894751: goto L_0x014b;
                case -933864895: goto L_0x0140;
                case -933857362: goto L_0x0135;
                case -891980232: goto L_0x0129;
                case -729118945: goto L_0x011e;
                case -416535885: goto L_0x0112;
                case -293492298: goto L_0x0104;
                case -53677816: goto L_0x00f6;
                case -44578051: goto L_0x00e7;
                case 100: goto L_0x00d9;
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
            goto L_0x016b
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x016b
        L_0x001a:
            r3 = 25
            goto L_0x016b
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x016b
        L_0x0028:
            r3 = 24
            goto L_0x016b
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x016b
        L_0x0037:
            r3 = 23
            goto L_0x016b
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x016b
        L_0x0045:
            r3 = 22
            goto L_0x016b
        L_0x0049:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x016b
        L_0x0054:
            r3 = 21
            goto L_0x016b
        L_0x0058:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x016b
        L_0x0062:
            r3 = 20
            goto L_0x016b
        L_0x0066:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x016b
        L_0x0070:
            r3 = 19
            goto L_0x016b
        L_0x0074:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x016b
        L_0x007f:
            r3 = 18
            goto L_0x016b
        L_0x0083:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x016b
        L_0x008d:
            r3 = 17
            goto L_0x016b
        L_0x0091:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009c
            goto L_0x016b
        L_0x009c:
            r3 = 16
            goto L_0x016b
        L_0x00a0:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00ab
            goto L_0x016b
        L_0x00ab:
            r3 = 15
            goto L_0x016b
        L_0x00af:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b9
            goto L_0x016b
        L_0x00b9:
            r3 = 14
            goto L_0x016b
        L_0x00bd:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c7
            goto L_0x016b
        L_0x00c7:
            r3 = 13
            goto L_0x016b
        L_0x00cb:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d5
            goto L_0x016b
        L_0x00d5:
            r3 = 12
            goto L_0x016b
        L_0x00d9:
            java.lang.String r0 = "d"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e3
            goto L_0x016b
        L_0x00e3:
            r3 = 11
            goto L_0x016b
        L_0x00e7:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x016b
        L_0x00f2:
            r3 = 10
            goto L_0x016b
        L_0x00f6:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x016b
        L_0x0100:
            r3 = 9
            goto L_0x016b
        L_0x0104:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010e
            goto L_0x016b
        L_0x010e:
            r3 = 8
            goto L_0x016b
        L_0x0112:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011c
            goto L_0x016b
        L_0x011c:
            r3 = 7
            goto L_0x016b
        L_0x011e:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0127
            goto L_0x016b
        L_0x0127:
            r3 = 6
            goto L_0x016b
        L_0x0129:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0133
            goto L_0x016b
        L_0x0133:
            r3 = 5
            goto L_0x016b
        L_0x0135:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x013e
            goto L_0x016b
        L_0x013e:
            r3 = 4
            goto L_0x016b
        L_0x0140:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0149
            goto L_0x016b
        L_0x0149:
            r3 = 3
            goto L_0x016b
        L_0x014b:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0154
            goto L_0x016b
        L_0x0154:
            r3 = 2
            goto L_0x016b
        L_0x0156:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x015f
            goto L_0x016b
        L_0x015f:
            r3 = r1
            goto L_0x016b
        L_0x0161:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x016a
            goto L_0x016b
        L_0x016a:
            r3 = r2
        L_0x016b:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x031e;
                case 1: goto L_0x0314;
                case 2: goto L_0x030a;
                case 3: goto L_0x02fc;
                case 4: goto L_0x02ee;
                case 5: goto L_0x02e4;
                case 6: goto L_0x02d3;
                case 7: goto L_0x02c2;
                case 8: goto L_0x02b3;
                case 9: goto L_0x02a1;
                case 10: goto L_0x028f;
                case 11: goto L_0x0280;
                case 12: goto L_0x0275;
                case 13: goto L_0x0266;
                case 14: goto L_0x0257;
                case 15: goto L_0x0245;
                case 16: goto L_0x0233;
                case 17: goto L_0x0224;
                case 18: goto L_0x0206;
                case 19: goto L_0x01f7;
                case 20: goto L_0x01e5;
                case 21: goto L_0x01d3;
                case 22: goto L_0x01c4;
                case 23: goto L_0x01b2;
                case 24: goto L_0x01a0;
                case 25: goto L_0x0177;
                default: goto L_0x0172;
            }
        L_0x0172:
            super.setProperty(r7, r8, r9)
            goto L_0x032c
        L_0x0177:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0186
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x032c
        L_0x0186:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0195
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x032c
        L_0x0195:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x032c
        L_0x01a0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x01a7
            goto L_0x01ad
        L_0x01a7:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x01ad:
            r8.setResponsible(r7, r2)
            goto L_0x032c
        L_0x01b2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x01b9
            goto L_0x01bf
        L_0x01b9:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x01bf:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x032c
        L_0x01c4:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x01cb
            goto L_0x01ce
        L_0x01cb:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x01ce:
            r8.setDisplay(r7, r5)
            goto L_0x032c
        L_0x01d3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x01da
            goto L_0x01e0
        L_0x01da:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x01e0:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x032c
        L_0x01e5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x01ec
            goto L_0x01f2
        L_0x01ec:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x01f2:
            r8.setClipRule(r7, r2)
            goto L_0x032c
        L_0x01f7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x01fe
            goto L_0x0201
        L_0x01fe:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0201:
            r8.setClipPath(r7, r5)
            goto L_0x032c
        L_0x0206:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0215
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x032c
        L_0x0215:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x032c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x032c
        L_0x0224:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x022b
            goto L_0x022e
        L_0x022b:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x022e:
            r8.setMarkerStart(r7, r5)
            goto L_0x032c
        L_0x0233:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x023a
            goto L_0x0240
        L_0x023a:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0240:
            r8.setVectorEffect(r7, r2)
            goto L_0x032c
        L_0x0245:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x024c
            goto L_0x0252
        L_0x024c:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x0252:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x032c
        L_0x0257:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x025e
            goto L_0x0261
        L_0x025e:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0261:
            r8.setName(r7, r5)
            goto L_0x032c
        L_0x0266:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x026d
            goto L_0x0270
        L_0x026d:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0270:
            r8.setMask(r7, r5)
            goto L_0x032c
        L_0x0275:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x032c
        L_0x0280:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x0287
            goto L_0x028a
        L_0x0287:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x028a:
            r8.setD(r7, r5)
            goto L_0x032c
        L_0x028f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x0296
            goto L_0x029c
        L_0x0296:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x029c:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x032c
        L_0x02a1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x02a8
            goto L_0x02ae
        L_0x02a8:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x02ae:
            r8.setFillOpacity(r7, r4)
            goto L_0x032c
        L_0x02b3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x02ba
            goto L_0x02bd
        L_0x02ba:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02bd:
            r8.setPointerEvents(r7, r5)
            goto L_0x032c
        L_0x02c2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x02c9
            goto L_0x02cf
        L_0x02c9:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x02cf:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x032c
        L_0x02d3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x02da
            goto L_0x02e0
        L_0x02da:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x02e0:
            r8.setFillRule(r7, r1)
            goto L_0x032c
        L_0x02e4:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x032c
        L_0x02ee:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x02f5
            goto L_0x02f8
        L_0x02f5:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02f8:
            r8.setMarkerMid(r7, r5)
            goto L_0x032c
        L_0x02fc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            if (r9 != 0) goto L_0x0303
            goto L_0x0306
        L_0x0303:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0306:
            r8.setMarkerEnd(r7, r5)
            goto L_0x032c
        L_0x030a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x032c
        L_0x0314:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x032c
        L_0x031e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x0323
            goto L_0x0329
        L_0x0323:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0329:
            r8.setOpacity(r7, r4)
        L_0x032c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGPathManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
