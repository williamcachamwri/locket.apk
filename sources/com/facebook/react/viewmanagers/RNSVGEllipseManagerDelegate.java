package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface;

public class RNSVGEllipseManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGEllipseManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGEllipseManagerDelegate(U u) {
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
                case -1267206133: goto L_0x018b;
                case -1081239615: goto L_0x0180;
                case -993894751: goto L_0x0175;
                case -933864895: goto L_0x016a;
                case -933857362: goto L_0x015f;
                case -891980232: goto L_0x0153;
                case -729118945: goto L_0x0148;
                case -416535885: goto L_0x013c;
                case -293492298: goto L_0x012e;
                case -53677816: goto L_0x0120;
                case -44578051: goto L_0x0111;
                case 3189: goto L_0x0103;
                case 3190: goto L_0x00f5;
                case 3654: goto L_0x00e7;
                case 3655: goto L_0x00d9;
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
            goto L_0x0195
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x0195
        L_0x001a:
            r3 = 28
            goto L_0x0195
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x0195
        L_0x0028:
            r3 = 27
            goto L_0x0195
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x0195
        L_0x0037:
            r3 = 26
            goto L_0x0195
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0195
        L_0x0045:
            r3 = 25
            goto L_0x0195
        L_0x0049:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x0195
        L_0x0054:
            r3 = 24
            goto L_0x0195
        L_0x0058:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x0195
        L_0x0062:
            r3 = 23
            goto L_0x0195
        L_0x0066:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x0195
        L_0x0070:
            r3 = 22
            goto L_0x0195
        L_0x0074:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x0195
        L_0x007f:
            r3 = 21
            goto L_0x0195
        L_0x0083:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x0195
        L_0x008d:
            r3 = 20
            goto L_0x0195
        L_0x0091:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009c
            goto L_0x0195
        L_0x009c:
            r3 = 19
            goto L_0x0195
        L_0x00a0:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00ab
            goto L_0x0195
        L_0x00ab:
            r3 = 18
            goto L_0x0195
        L_0x00af:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b9
            goto L_0x0195
        L_0x00b9:
            r3 = 17
            goto L_0x0195
        L_0x00bd:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c7
            goto L_0x0195
        L_0x00c7:
            r3 = 16
            goto L_0x0195
        L_0x00cb:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d5
            goto L_0x0195
        L_0x00d5:
            r3 = 15
            goto L_0x0195
        L_0x00d9:
            java.lang.String r0 = "ry"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e3
            goto L_0x0195
        L_0x00e3:
            r3 = 14
            goto L_0x0195
        L_0x00e7:
            java.lang.String r0 = "rx"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f1
            goto L_0x0195
        L_0x00f1:
            r3 = 13
            goto L_0x0195
        L_0x00f5:
            java.lang.String r0 = "cy"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00ff
            goto L_0x0195
        L_0x00ff:
            r3 = 12
            goto L_0x0195
        L_0x0103:
            java.lang.String r0 = "cx"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010d
            goto L_0x0195
        L_0x010d:
            r3 = 11
            goto L_0x0195
        L_0x0111:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011c
            goto L_0x0195
        L_0x011c:
            r3 = 10
            goto L_0x0195
        L_0x0120:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012a
            goto L_0x0195
        L_0x012a:
            r3 = 9
            goto L_0x0195
        L_0x012e:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0138
            goto L_0x0195
        L_0x0138:
            r3 = 8
            goto L_0x0195
        L_0x013c:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0146
            goto L_0x0195
        L_0x0146:
            r3 = 7
            goto L_0x0195
        L_0x0148:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0151
            goto L_0x0195
        L_0x0151:
            r3 = 6
            goto L_0x0195
        L_0x0153:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x015d
            goto L_0x0195
        L_0x015d:
            r3 = 5
            goto L_0x0195
        L_0x015f:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0168
            goto L_0x0195
        L_0x0168:
            r3 = 4
            goto L_0x0195
        L_0x016a:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0173
            goto L_0x0195
        L_0x0173:
            r3 = 3
            goto L_0x0195
        L_0x0175:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x017e
            goto L_0x0195
        L_0x017e:
            r3 = 2
            goto L_0x0195
        L_0x0180:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0189
            goto L_0x0195
        L_0x0189:
            r3 = r1
            goto L_0x0195
        L_0x018b:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0194
            goto L_0x0195
        L_0x0194:
            r3 = r2
        L_0x0195:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x03e1;
                case 1: goto L_0x03d7;
                case 2: goto L_0x03cd;
                case 3: goto L_0x03bf;
                case 4: goto L_0x03b1;
                case 5: goto L_0x03a7;
                case 6: goto L_0x0396;
                case 7: goto L_0x0385;
                case 8: goto L_0x0376;
                case 9: goto L_0x0364;
                case 10: goto L_0x0352;
                case 11: goto L_0x0328;
                case 12: goto L_0x02fe;
                case 13: goto L_0x02d4;
                case 14: goto L_0x02aa;
                case 15: goto L_0x029f;
                case 16: goto L_0x0290;
                case 17: goto L_0x0281;
                case 18: goto L_0x026f;
                case 19: goto L_0x025d;
                case 20: goto L_0x024e;
                case 21: goto L_0x0230;
                case 22: goto L_0x0221;
                case 23: goto L_0x020f;
                case 24: goto L_0x01fd;
                case 25: goto L_0x01ee;
                case 26: goto L_0x01dc;
                case 27: goto L_0x01ca;
                case 28: goto L_0x01a1;
                default: goto L_0x019c;
            }
        L_0x019c:
            super.setProperty(r7, r8, r9)
            goto L_0x03ef
        L_0x01a1:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x01b0
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x03ef
        L_0x01b0:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x01bf
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x03ef
        L_0x01bf:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x03ef
        L_0x01ca:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x01d1
            goto L_0x01d7
        L_0x01d1:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x01d7:
            r8.setResponsible(r7, r2)
            goto L_0x03ef
        L_0x01dc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x01e3
            goto L_0x01e9
        L_0x01e3:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x01e9:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x03ef
        L_0x01ee:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x01f5
            goto L_0x01f8
        L_0x01f5:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x01f8:
            r8.setDisplay(r7, r5)
            goto L_0x03ef
        L_0x01fd:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0204
            goto L_0x020a
        L_0x0204:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x020a:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x03ef
        L_0x020f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0216
            goto L_0x021c
        L_0x0216:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x021c:
            r8.setClipRule(r7, r2)
            goto L_0x03ef
        L_0x0221:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0228
            goto L_0x022b
        L_0x0228:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x022b:
            r8.setClipPath(r7, r5)
            goto L_0x03ef
        L_0x0230:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x023f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x03ef
        L_0x023f:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x03ef
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x03ef
        L_0x024e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0255
            goto L_0x0258
        L_0x0255:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0258:
            r8.setMarkerStart(r7, r5)
            goto L_0x03ef
        L_0x025d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0264
            goto L_0x026a
        L_0x0264:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x026a:
            r8.setVectorEffect(r7, r2)
            goto L_0x03ef
        L_0x026f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0276
            goto L_0x027c
        L_0x0276:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x027c:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x03ef
        L_0x0281:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0288
            goto L_0x028b
        L_0x0288:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x028b:
            r8.setName(r7, r5)
            goto L_0x03ef
        L_0x0290:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0297
            goto L_0x029a
        L_0x0297:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x029a:
            r8.setMask(r7, r5)
            goto L_0x03ef
        L_0x029f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x03ef
        L_0x02aa:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02b9
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setRy(r7, (java.lang.String) r9)
            goto L_0x03ef
        L_0x02b9:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02c8
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRy(r7, (java.lang.Double) r9)
            goto L_0x03ef
        L_0x02c8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRy(r7, (java.lang.Double) r5)
            goto L_0x03ef
        L_0x02d4:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02e3
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setRx(r7, (java.lang.String) r9)
            goto L_0x03ef
        L_0x02e3:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02f2
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRx(r7, (java.lang.Double) r9)
            goto L_0x03ef
        L_0x02f2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRx(r7, (java.lang.Double) r5)
            goto L_0x03ef
        L_0x02fe:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x030d
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setCy(r7, (java.lang.String) r9)
            goto L_0x03ef
        L_0x030d:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x031c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setCy(r7, (java.lang.Double) r9)
            goto L_0x03ef
        L_0x031c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setCy(r7, (java.lang.Double) r5)
            goto L_0x03ef
        L_0x0328:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0337
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setCx(r7, (java.lang.String) r9)
            goto L_0x03ef
        L_0x0337:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0346
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setCx(r7, (java.lang.Double) r9)
            goto L_0x03ef
        L_0x0346:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setCx(r7, (java.lang.Double) r5)
            goto L_0x03ef
        L_0x0352:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x0359
            goto L_0x035f
        L_0x0359:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x035f:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x03ef
        L_0x0364:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x036b
            goto L_0x0371
        L_0x036b:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0371:
            r8.setFillOpacity(r7, r4)
            goto L_0x03ef
        L_0x0376:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x037d
            goto L_0x0380
        L_0x037d:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0380:
            r8.setPointerEvents(r7, r5)
            goto L_0x03ef
        L_0x0385:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x038c
            goto L_0x0392
        L_0x038c:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0392:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x03ef
        L_0x0396:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x039d
            goto L_0x03a3
        L_0x039d:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x03a3:
            r8.setFillRule(r7, r1)
            goto L_0x03ef
        L_0x03a7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x03ef
        L_0x03b1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x03b8
            goto L_0x03bb
        L_0x03b8:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03bb:
            r8.setMarkerMid(r7, r5)
            goto L_0x03ef
        L_0x03bf:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            if (r9 != 0) goto L_0x03c6
            goto L_0x03c9
        L_0x03c6:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03c9:
            r8.setMarkerEnd(r7, r5)
            goto L_0x03ef
        L_0x03cd:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x03ef
        L_0x03d7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x03ef
        L_0x03e1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x03e6
            goto L_0x03ec
        L_0x03e6:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03ec:
            r8.setOpacity(r7, r4)
        L_0x03ef:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGEllipseManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
