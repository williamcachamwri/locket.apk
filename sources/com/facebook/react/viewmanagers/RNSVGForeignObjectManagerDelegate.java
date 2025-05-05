package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface;

public class RNSVGForeignObjectManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGForeignObjectManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGForeignObjectManagerDelegate(U u) {
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
                case -1267206133: goto L_0x01b8;
                case -1221029593: goto L_0x01ad;
                case -1081239615: goto L_0x01a2;
                case -993894751: goto L_0x0197;
                case -933864895: goto L_0x018c;
                case -933857362: goto L_0x0181;
                case -891980232: goto L_0x0175;
                case -734428249: goto L_0x016a;
                case -729118945: goto L_0x015c;
                case -416535885: goto L_0x014d;
                case -293492298: goto L_0x013f;
                case -53677816: goto L_0x0131;
                case -44578051: goto L_0x0122;
                case 120: goto L_0x0113;
                case 121: goto L_0x0104;
                case 3143043: goto L_0x00f6;
                case 3148879: goto L_0x00e8;
                case 3344108: goto L_0x00da;
                case 3373707: goto L_0x00cc;
                case 78845486: goto L_0x00bd;
                case 104482996: goto L_0x00ae;
                case 113126854: goto L_0x009f;
                case 217109576: goto L_0x0091;
                case 365601008: goto L_0x0083;
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
            goto L_0x01c2
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x01c2
        L_0x001a:
            r3 = 31
            goto L_0x01c2
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x01c2
        L_0x0028:
            r3 = 30
            goto L_0x01c2
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x01c2
        L_0x0037:
            r3 = 29
            goto L_0x01c2
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x01c2
        L_0x0045:
            r3 = 28
            goto L_0x01c2
        L_0x0049:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x01c2
        L_0x0054:
            r3 = 27
            goto L_0x01c2
        L_0x0058:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x01c2
        L_0x0062:
            r3 = 26
            goto L_0x01c2
        L_0x0066:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x01c2
        L_0x0070:
            r3 = 25
            goto L_0x01c2
        L_0x0074:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x01c2
        L_0x007f:
            r3 = 24
            goto L_0x01c2
        L_0x0083:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x01c2
        L_0x008d:
            r3 = 23
            goto L_0x01c2
        L_0x0091:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x01c2
        L_0x009b:
            r3 = 22
            goto L_0x01c2
        L_0x009f:
            java.lang.String r0 = "width"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00aa
            goto L_0x01c2
        L_0x00aa:
            r3 = 21
            goto L_0x01c2
        L_0x00ae:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b9
            goto L_0x01c2
        L_0x00b9:
            r3 = 20
            goto L_0x01c2
        L_0x00bd:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c8
            goto L_0x01c2
        L_0x00c8:
            r3 = 19
            goto L_0x01c2
        L_0x00cc:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d6
            goto L_0x01c2
        L_0x00d6:
            r3 = 18
            goto L_0x01c2
        L_0x00da:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e4
            goto L_0x01c2
        L_0x00e4:
            r3 = 17
            goto L_0x01c2
        L_0x00e8:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x01c2
        L_0x00f2:
            r3 = 16
            goto L_0x01c2
        L_0x00f6:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x01c2
        L_0x0100:
            r3 = 15
            goto L_0x01c2
        L_0x0104:
            java.lang.String r0 = "y"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010f
            goto L_0x01c2
        L_0x010f:
            r3 = 14
            goto L_0x01c2
        L_0x0113:
            java.lang.String r0 = "x"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011e
            goto L_0x01c2
        L_0x011e:
            r3 = 13
            goto L_0x01c2
        L_0x0122:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012d
            goto L_0x01c2
        L_0x012d:
            r3 = 12
            goto L_0x01c2
        L_0x0131:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x013b
            goto L_0x01c2
        L_0x013b:
            r3 = 11
            goto L_0x01c2
        L_0x013f:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0149
            goto L_0x01c2
        L_0x0149:
            r3 = 10
            goto L_0x01c2
        L_0x014d:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0158
            goto L_0x01c2
        L_0x0158:
            r3 = 9
            goto L_0x01c2
        L_0x015c:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0166
            goto L_0x01c2
        L_0x0166:
            r3 = 8
            goto L_0x01c2
        L_0x016a:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0173
            goto L_0x01c2
        L_0x0173:
            r3 = 7
            goto L_0x01c2
        L_0x0175:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x017f
            goto L_0x01c2
        L_0x017f:
            r3 = 6
            goto L_0x01c2
        L_0x0181:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x018a
            goto L_0x01c2
        L_0x018a:
            r3 = 5
            goto L_0x01c2
        L_0x018c:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0195
            goto L_0x01c2
        L_0x0195:
            r3 = 4
            goto L_0x01c2
        L_0x0197:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01a0
            goto L_0x01c2
        L_0x01a0:
            r3 = 3
            goto L_0x01c2
        L_0x01a2:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ab
            goto L_0x01c2
        L_0x01ab:
            r3 = 2
            goto L_0x01c2
        L_0x01ad:
            java.lang.String r0 = "height"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01b6
            goto L_0x01c2
        L_0x01b6:
            r3 = r1
            goto L_0x01c2
        L_0x01b8:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c1
            goto L_0x01c2
        L_0x01c1:
            r3 = r2
        L_0x01c2:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x046d;
                case 1: goto L_0x0446;
                case 2: goto L_0x043c;
                case 3: goto L_0x0432;
                case 4: goto L_0x0424;
                case 5: goto L_0x0416;
                case 6: goto L_0x040b;
                case 7: goto L_0x03e1;
                case 8: goto L_0x03cf;
                case 9: goto L_0x03bd;
                case 10: goto L_0x03ae;
                case 11: goto L_0x039c;
                case 12: goto L_0x038a;
                case 13: goto L_0x0360;
                case 14: goto L_0x0336;
                case 15: goto L_0x032b;
                case 16: goto L_0x0320;
                case 17: goto L_0x0311;
                case 18: goto L_0x0302;
                case 19: goto L_0x02f0;
                case 20: goto L_0x02de;
                case 21: goto L_0x02b4;
                case 22: goto L_0x02a5;
                case 23: goto L_0x027b;
                case 24: goto L_0x025d;
                case 25: goto L_0x024e;
                case 26: goto L_0x023c;
                case 27: goto L_0x022a;
                case 28: goto L_0x021b;
                case 29: goto L_0x0209;
                case 30: goto L_0x01f7;
                case 31: goto L_0x01ce;
                default: goto L_0x01c9;
            }
        L_0x01c9:
            super.setProperty(r7, r8, r9)
            goto L_0x047b
        L_0x01ce:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x01dd
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x01dd:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x01ec
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x047b
        L_0x01ec:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x01f7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x01fe
            goto L_0x0204
        L_0x01fe:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x0204:
            r8.setResponsible(r7, r2)
            goto L_0x047b
        L_0x0209:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x0210
            goto L_0x0216
        L_0x0210:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0216:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x047b
        L_0x021b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x0222
            goto L_0x0225
        L_0x0222:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0225:
            r8.setDisplay(r7, r5)
            goto L_0x047b
        L_0x022a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x0231
            goto L_0x0237
        L_0x0231:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0237:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x047b
        L_0x023c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x0243
            goto L_0x0249
        L_0x0243:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0249:
            r8.setClipRule(r7, r2)
            goto L_0x047b
        L_0x024e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x0255
            goto L_0x0258
        L_0x0255:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0258:
            r8.setClipPath(r7, r5)
            goto L_0x047b
        L_0x025d:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x026c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x026c:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x047b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x047b
        L_0x027b:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x028a
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x028a:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0299
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x047b
        L_0x0299:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x047b
        L_0x02a5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x02ac
            goto L_0x02af
        L_0x02ac:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02af:
            r8.setMarkerStart(r7, r5)
            goto L_0x047b
        L_0x02b4:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02c3
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setWidth(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x02c3:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02d2
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r9)
            goto L_0x047b
        L_0x02d2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r5)
            goto L_0x047b
        L_0x02de:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x02e5
            goto L_0x02eb
        L_0x02e5:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02eb:
            r8.setVectorEffect(r7, r2)
            goto L_0x047b
        L_0x02f0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x02f7
            goto L_0x02fd
        L_0x02f7:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x02fd:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x047b
        L_0x0302:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x0309
            goto L_0x030c
        L_0x0309:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x030c:
            r8.setName(r7, r5)
            goto L_0x047b
        L_0x0311:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x0318
            goto L_0x031b
        L_0x0318:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x031b:
            r8.setMask(r7, r5)
            goto L_0x047b
        L_0x0320:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x047b
        L_0x032b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x047b
        L_0x0336:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0345
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setY(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x0345:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0354
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r9)
            goto L_0x047b
        L_0x0354:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r5)
            goto L_0x047b
        L_0x0360:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x036f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setX(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x036f:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x037e
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r9)
            goto L_0x047b
        L_0x037e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r5)
            goto L_0x047b
        L_0x038a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x0391
            goto L_0x0397
        L_0x0391:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x0397:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x047b
        L_0x039c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x03a3
            goto L_0x03a9
        L_0x03a3:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03a9:
            r8.setFillOpacity(r7, r4)
            goto L_0x047b
        L_0x03ae:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x03b5
            goto L_0x03b8
        L_0x03b5:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03b8:
            r8.setPointerEvents(r7, r5)
            goto L_0x047b
        L_0x03bd:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x03c4
            goto L_0x03ca
        L_0x03c4:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03ca:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x047b
        L_0x03cf:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x03d6
            goto L_0x03dc
        L_0x03d6:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x03dc:
            r8.setFillRule(r7, r1)
            goto L_0x047b
        L_0x03e1:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x03f0
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x03f0:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x03ff
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x047b
        L_0x03ff:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x047b
        L_0x040b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x047b
        L_0x0416:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x041d
            goto L_0x0420
        L_0x041d:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0420:
            r8.setMarkerMid(r7, r5)
            goto L_0x047b
        L_0x0424:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            if (r9 != 0) goto L_0x042b
            goto L_0x042e
        L_0x042b:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x042e:
            r8.setMarkerEnd(r7, r5)
            goto L_0x047b
        L_0x0432:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x047b
        L_0x043c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x047b
        L_0x0446:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0454
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setHeight(r7, (java.lang.String) r9)
            goto L_0x047b
        L_0x0454:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0462
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r9)
            goto L_0x047b
        L_0x0462:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r5)
            goto L_0x047b
        L_0x046d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x0472
            goto L_0x0478
        L_0x0472:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0478:
            r8.setOpacity(r7, r4)
        L_0x047b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGForeignObjectManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
