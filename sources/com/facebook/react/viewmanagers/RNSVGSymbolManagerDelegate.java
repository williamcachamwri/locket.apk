package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface;

public class RNSVGSymbolManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGSymbolManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGSymbolManagerDelegate(U u) {
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
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: boolean} */
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
                case -1567958285: goto L_0x01d2;
                case -1267206133: goto L_0x01c7;
                case -1081239615: goto L_0x01bc;
                case -993894751: goto L_0x01b1;
                case -933864895: goto L_0x01a6;
                case -933857362: goto L_0x019b;
                case -891980232: goto L_0x018f;
                case -734428249: goto L_0x0184;
                case -729118945: goto L_0x0176;
                case -416535885: goto L_0x0167;
                case -293492298: goto L_0x0159;
                case -53677816: goto L_0x014b;
                case -44578051: goto L_0x013c;
                case 3143043: goto L_0x012e;
                case 3148879: goto L_0x0120;
                case 3344108: goto L_0x0112;
                case 3351622: goto L_0x0104;
                case 3351623: goto L_0x00f6;
                case 3373707: goto L_0x00e8;
                case 78845486: goto L_0x00d9;
                case 92903173: goto L_0x00cb;
                case 104482996: goto L_0x00bc;
                case 217109576: goto L_0x00ae;
                case 240482938: goto L_0x009f;
                case 365601008: goto L_0x0091;
                case 401643183: goto L_0x0082;
                case 917656469: goto L_0x0074;
                case 917735020: goto L_0x0066;
                case 1027575302: goto L_0x0057;
                case 1671764162: goto L_0x0049;
                case 1790285174: goto L_0x003a;
                case 1847674614: goto L_0x002c;
                case 1908075304: goto L_0x001e;
                case 1924065902: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x01dd
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x01dd
        L_0x001a:
            r3 = 33
            goto L_0x01dd
        L_0x001e:
            java.lang.String r0 = "meetOrSlice"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x01dd
        L_0x0028:
            r3 = 32
            goto L_0x01dd
        L_0x002c:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0036
            goto L_0x01dd
        L_0x0036:
            r3 = 31
            goto L_0x01dd
        L_0x003a:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x01dd
        L_0x0045:
            r3 = 30
            goto L_0x01dd
        L_0x0049:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0053
            goto L_0x01dd
        L_0x0053:
            r3 = 29
            goto L_0x01dd
        L_0x0057:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x01dd
        L_0x0062:
            r3 = 28
            goto L_0x01dd
        L_0x0066:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x01dd
        L_0x0070:
            r3 = 27
            goto L_0x01dd
        L_0x0074:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007e
            goto L_0x01dd
        L_0x007e:
            r3 = 26
            goto L_0x01dd
        L_0x0082:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x01dd
        L_0x008d:
            r3 = 25
            goto L_0x01dd
        L_0x0091:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x01dd
        L_0x009b:
            r3 = 24
            goto L_0x01dd
        L_0x009f:
            java.lang.String r0 = "vbWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00aa
            goto L_0x01dd
        L_0x00aa:
            r3 = 23
            goto L_0x01dd
        L_0x00ae:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b8
            goto L_0x01dd
        L_0x00b8:
            r3 = 22
            goto L_0x01dd
        L_0x00bc:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c7
            goto L_0x01dd
        L_0x00c7:
            r3 = 21
            goto L_0x01dd
        L_0x00cb:
            java.lang.String r0 = "align"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d5
            goto L_0x01dd
        L_0x00d5:
            r3 = 20
            goto L_0x01dd
        L_0x00d9:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e4
            goto L_0x01dd
        L_0x00e4:
            r3 = 19
            goto L_0x01dd
        L_0x00e8:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x01dd
        L_0x00f2:
            r3 = 18
            goto L_0x01dd
        L_0x00f6:
            java.lang.String r0 = "minY"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x01dd
        L_0x0100:
            r3 = 17
            goto L_0x01dd
        L_0x0104:
            java.lang.String r0 = "minX"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010e
            goto L_0x01dd
        L_0x010e:
            r3 = 16
            goto L_0x01dd
        L_0x0112:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011c
            goto L_0x01dd
        L_0x011c:
            r3 = 15
            goto L_0x01dd
        L_0x0120:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012a
            goto L_0x01dd
        L_0x012a:
            r3 = 14
            goto L_0x01dd
        L_0x012e:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0138
            goto L_0x01dd
        L_0x0138:
            r3 = 13
            goto L_0x01dd
        L_0x013c:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0147
            goto L_0x01dd
        L_0x0147:
            r3 = 12
            goto L_0x01dd
        L_0x014b:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0155
            goto L_0x01dd
        L_0x0155:
            r3 = 11
            goto L_0x01dd
        L_0x0159:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0163
            goto L_0x01dd
        L_0x0163:
            r3 = 10
            goto L_0x01dd
        L_0x0167:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0172
            goto L_0x01dd
        L_0x0172:
            r3 = 9
            goto L_0x01dd
        L_0x0176:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0180
            goto L_0x01dd
        L_0x0180:
            r3 = 8
            goto L_0x01dd
        L_0x0184:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x018d
            goto L_0x01dd
        L_0x018d:
            r3 = 7
            goto L_0x01dd
        L_0x018f:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0199
            goto L_0x01dd
        L_0x0199:
            r3 = 6
            goto L_0x01dd
        L_0x019b:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01a4
            goto L_0x01dd
        L_0x01a4:
            r3 = 5
            goto L_0x01dd
        L_0x01a6:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01af
            goto L_0x01dd
        L_0x01af:
            r3 = 4
            goto L_0x01dd
        L_0x01b1:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ba
            goto L_0x01dd
        L_0x01ba:
            r3 = 3
            goto L_0x01dd
        L_0x01bc:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c5
            goto L_0x01dd
        L_0x01c5:
            r3 = 2
            goto L_0x01dd
        L_0x01c7:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01d0
            goto L_0x01dd
        L_0x01d0:
            r3 = r1
            goto L_0x01dd
        L_0x01d2:
            java.lang.String r0 = "vbHeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01dc
            goto L_0x01dd
        L_0x01dc:
            r3 = r2
        L_0x01dd:
            r0 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            r5 = 0
            switch(r3) {
                case 0: goto L_0x0447;
                case 1: goto L_0x0438;
                case 2: goto L_0x042e;
                case 3: goto L_0x0424;
                case 4: goto L_0x0416;
                case 5: goto L_0x0408;
                case 6: goto L_0x03fe;
                case 7: goto L_0x03d5;
                case 8: goto L_0x03c3;
                case 9: goto L_0x03b1;
                case 10: goto L_0x03a2;
                case 11: goto L_0x0390;
                case 12: goto L_0x037e;
                case 13: goto L_0x0373;
                case 14: goto L_0x0368;
                case 15: goto L_0x0359;
                case 16: goto L_0x0347;
                case 17: goto L_0x0335;
                case 18: goto L_0x0326;
                case 19: goto L_0x0314;
                case 20: goto L_0x0305;
                case 21: goto L_0x02f3;
                case 22: goto L_0x02e4;
                case 23: goto L_0x02d2;
                case 24: goto L_0x02a8;
                case 25: goto L_0x028a;
                case 26: goto L_0x027b;
                case 27: goto L_0x0269;
                case 28: goto L_0x0257;
                case 29: goto L_0x0248;
                case 30: goto L_0x0236;
                case 31: goto L_0x0224;
                case 32: goto L_0x0212;
                case 33: goto L_0x01e9;
                default: goto L_0x01e4;
            }
        L_0x01e4:
            super.setProperty(r7, r8, r9)
            goto L_0x0457
        L_0x01e9:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x01f8
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0457
        L_0x01f8:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0207
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x0457
        L_0x0207:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0457
        L_0x0212:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x0219
            goto L_0x021f
        L_0x0219:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x021f:
            r8.setMeetOrSlice(r7, r2)
            goto L_0x0457
        L_0x0224:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x022b
            goto L_0x0231
        L_0x022b:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x0231:
            r8.setResponsible(r7, r2)
            goto L_0x0457
        L_0x0236:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x023d
            goto L_0x0243
        L_0x023d:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0243:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x0457
        L_0x0248:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x024f
            goto L_0x0252
        L_0x024f:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0252:
            r8.setDisplay(r7, r5)
            goto L_0x0457
        L_0x0257:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x025e
            goto L_0x0264
        L_0x025e:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0264:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x0457
        L_0x0269:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x0270
            goto L_0x0276
        L_0x0270:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0276:
            r8.setClipRule(r7, r2)
            goto L_0x0457
        L_0x027b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x0282
            goto L_0x0285
        L_0x0282:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0285:
            r8.setClipPath(r7, r5)
            goto L_0x0457
        L_0x028a:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0299
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x0457
        L_0x0299:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x0457
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x0457
        L_0x02a8:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02b7
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x0457
        L_0x02b7:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02c6
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x0457
        L_0x02c6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x0457
        L_0x02d2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x02d9
            goto L_0x02df
        L_0x02d9:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x02df:
            r8.setVbWidth(r7, r4)
            goto L_0x0457
        L_0x02e4:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x02eb
            goto L_0x02ee
        L_0x02eb:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02ee:
            r8.setMarkerStart(r7, r5)
            goto L_0x0457
        L_0x02f3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x02fa
            goto L_0x0300
        L_0x02fa:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0300:
            r8.setVectorEffect(r7, r2)
            goto L_0x0457
        L_0x0305:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x030c
            goto L_0x030f
        L_0x030c:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x030f:
            r8.setAlign(r7, r5)
            goto L_0x0457
        L_0x0314:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x031b
            goto L_0x0321
        L_0x031b:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0321:
            r8.setStrokeMiterlimit(r7, r4)
            goto L_0x0457
        L_0x0326:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x032d
            goto L_0x0330
        L_0x032d:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0330:
            r8.setName(r7, r5)
            goto L_0x0457
        L_0x0335:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x033c
            goto L_0x0342
        L_0x033c:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0342:
            r8.setMinY(r7, r4)
            goto L_0x0457
        L_0x0347:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x034e
            goto L_0x0354
        L_0x034e:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0354:
            r8.setMinX(r7, r4)
            goto L_0x0457
        L_0x0359:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x0360
            goto L_0x0363
        L_0x0360:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0363:
            r8.setMask(r7, r5)
            goto L_0x0457
        L_0x0368:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x0457
        L_0x0373:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x0457
        L_0x037e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x0385
            goto L_0x038b
        L_0x0385:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x038b:
            r8.setStrokeDashoffset(r7, r4)
            goto L_0x0457
        L_0x0390:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x0397
            goto L_0x039d
        L_0x0397:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x039d:
            r8.setFillOpacity(r7, r0)
            goto L_0x0457
        L_0x03a2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x03a9
            goto L_0x03ac
        L_0x03a9:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03ac:
            r8.setPointerEvents(r7, r5)
            goto L_0x0457
        L_0x03b1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x03b8
            goto L_0x03be
        L_0x03b8:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x03be:
            r8.setStrokeOpacity(r7, r0)
            goto L_0x0457
        L_0x03c3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x03ca
            goto L_0x03d0
        L_0x03ca:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x03d0:
            r8.setFillRule(r7, r1)
            goto L_0x0457
        L_0x03d5:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x03e4
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x0457
        L_0x03e4:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x03f3
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x0457
        L_0x03f3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x0457
        L_0x03fe:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x0457
        L_0x0408:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x040f
            goto L_0x0412
        L_0x040f:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0412:
            r8.setMarkerMid(r7, r5)
            goto L_0x0457
        L_0x0416:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x041d
            goto L_0x0420
        L_0x041d:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0420:
            r8.setMarkerEnd(r7, r5)
            goto L_0x0457
        L_0x0424:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x0457
        L_0x042e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x0457
        L_0x0438:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x043d
            goto L_0x0443
        L_0x043d:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x0443:
            r8.setOpacity(r7, r0)
            goto L_0x0457
        L_0x0447:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r8
            if (r9 != 0) goto L_0x044e
            goto L_0x0454
        L_0x044e:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0454:
            r8.setVbHeight(r7, r4)
        L_0x0457:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGSymbolManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
