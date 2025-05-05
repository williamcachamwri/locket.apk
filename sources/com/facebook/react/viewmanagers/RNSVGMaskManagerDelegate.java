package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGMaskManagerInterface;

public class RNSVGMaskManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGMaskManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGMaskManagerDelegate(U u) {
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
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: boolean} */
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
                case -1267206133: goto L_0x01d4;
                case -1221029593: goto L_0x01c9;
                case -1081239615: goto L_0x01be;
                case -993894751: goto L_0x01b3;
                case -933864895: goto L_0x01a8;
                case -933857362: goto L_0x019d;
                case -891980232: goto L_0x0191;
                case -734428249: goto L_0x0186;
                case -729118945: goto L_0x0178;
                case -416535885: goto L_0x0169;
                case -293492298: goto L_0x015b;
                case -61221917: goto L_0x014d;
                case -53677816: goto L_0x013f;
                case -44578051: goto L_0x0130;
                case 120: goto L_0x0121;
                case 121: goto L_0x0112;
                case 3143043: goto L_0x0104;
                case 3148879: goto L_0x00f6;
                case 3344108: goto L_0x00e8;
                case 3373707: goto L_0x00da;
                case 78845486: goto L_0x00cb;
                case 104482996: goto L_0x00bc;
                case 113126854: goto L_0x00ad;
                case 217109576: goto L_0x009f;
                case 365601008: goto L_0x0091;
                case 401643183: goto L_0x0082;
                case 917656469: goto L_0x0074;
                case 917735020: goto L_0x0066;
                case 1027575302: goto L_0x0057;
                case 1671764162: goto L_0x0049;
                case 1790285174: goto L_0x003a;
                case 1847674614: goto L_0x002c;
                case 1924065902: goto L_0x001d;
                case 2037673858: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x01de
        L_0x000f:
            java.lang.String r0 = "maskContentUnits"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0019
            goto L_0x01de
        L_0x0019:
            r3 = 33
            goto L_0x01de
        L_0x001d:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x01de
        L_0x0028:
            r3 = 32
            goto L_0x01de
        L_0x002c:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0036
            goto L_0x01de
        L_0x0036:
            r3 = 31
            goto L_0x01de
        L_0x003a:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x01de
        L_0x0045:
            r3 = 30
            goto L_0x01de
        L_0x0049:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0053
            goto L_0x01de
        L_0x0053:
            r3 = 29
            goto L_0x01de
        L_0x0057:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x01de
        L_0x0062:
            r3 = 28
            goto L_0x01de
        L_0x0066:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x01de
        L_0x0070:
            r3 = 27
            goto L_0x01de
        L_0x0074:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007e
            goto L_0x01de
        L_0x007e:
            r3 = 26
            goto L_0x01de
        L_0x0082:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x01de
        L_0x008d:
            r3 = 25
            goto L_0x01de
        L_0x0091:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x01de
        L_0x009b:
            r3 = 24
            goto L_0x01de
        L_0x009f:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00a9
            goto L_0x01de
        L_0x00a9:
            r3 = 23
            goto L_0x01de
        L_0x00ad:
            java.lang.String r0 = "width"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b8
            goto L_0x01de
        L_0x00b8:
            r3 = 22
            goto L_0x01de
        L_0x00bc:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c7
            goto L_0x01de
        L_0x00c7:
            r3 = 21
            goto L_0x01de
        L_0x00cb:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d6
            goto L_0x01de
        L_0x00d6:
            r3 = 20
            goto L_0x01de
        L_0x00da:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e4
            goto L_0x01de
        L_0x00e4:
            r3 = 19
            goto L_0x01de
        L_0x00e8:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x01de
        L_0x00f2:
            r3 = 18
            goto L_0x01de
        L_0x00f6:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x01de
        L_0x0100:
            r3 = 17
            goto L_0x01de
        L_0x0104:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010e
            goto L_0x01de
        L_0x010e:
            r3 = 16
            goto L_0x01de
        L_0x0112:
            java.lang.String r0 = "y"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011d
            goto L_0x01de
        L_0x011d:
            r3 = 15
            goto L_0x01de
        L_0x0121:
            java.lang.String r0 = "x"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012c
            goto L_0x01de
        L_0x012c:
            r3 = 14
            goto L_0x01de
        L_0x0130:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x013b
            goto L_0x01de
        L_0x013b:
            r3 = 13
            goto L_0x01de
        L_0x013f:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0149
            goto L_0x01de
        L_0x0149:
            r3 = 12
            goto L_0x01de
        L_0x014d:
            java.lang.String r0 = "maskUnits"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0157
            goto L_0x01de
        L_0x0157:
            r3 = 11
            goto L_0x01de
        L_0x015b:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0165
            goto L_0x01de
        L_0x0165:
            r3 = 10
            goto L_0x01de
        L_0x0169:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0174
            goto L_0x01de
        L_0x0174:
            r3 = 9
            goto L_0x01de
        L_0x0178:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0182
            goto L_0x01de
        L_0x0182:
            r3 = 8
            goto L_0x01de
        L_0x0186:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x018f
            goto L_0x01de
        L_0x018f:
            r3 = 7
            goto L_0x01de
        L_0x0191:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x019b
            goto L_0x01de
        L_0x019b:
            r3 = 6
            goto L_0x01de
        L_0x019d:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01a6
            goto L_0x01de
        L_0x01a6:
            r3 = 5
            goto L_0x01de
        L_0x01a8:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01b1
            goto L_0x01de
        L_0x01b1:
            r3 = 4
            goto L_0x01de
        L_0x01b3:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01bc
            goto L_0x01de
        L_0x01bc:
            r3 = 3
            goto L_0x01de
        L_0x01be:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c7
            goto L_0x01de
        L_0x01c7:
            r3 = 2
            goto L_0x01de
        L_0x01c9:
            java.lang.String r0 = "height"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01d2
            goto L_0x01de
        L_0x01d2:
            r3 = r1
            goto L_0x01de
        L_0x01d4:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01dd
            goto L_0x01de
        L_0x01dd:
            r3 = r2
        L_0x01de:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x04ad;
                case 1: goto L_0x0486;
                case 2: goto L_0x047c;
                case 3: goto L_0x0472;
                case 4: goto L_0x0464;
                case 5: goto L_0x0456;
                case 6: goto L_0x044b;
                case 7: goto L_0x0421;
                case 8: goto L_0x040f;
                case 9: goto L_0x03fd;
                case 10: goto L_0x03ee;
                case 11: goto L_0x03dc;
                case 12: goto L_0x03ca;
                case 13: goto L_0x03b8;
                case 14: goto L_0x038e;
                case 15: goto L_0x0364;
                case 16: goto L_0x0359;
                case 17: goto L_0x034e;
                case 18: goto L_0x033f;
                case 19: goto L_0x0330;
                case 20: goto L_0x031e;
                case 21: goto L_0x030c;
                case 22: goto L_0x02e2;
                case 23: goto L_0x02d3;
                case 24: goto L_0x02a9;
                case 25: goto L_0x028b;
                case 26: goto L_0x027c;
                case 27: goto L_0x026a;
                case 28: goto L_0x0258;
                case 29: goto L_0x0249;
                case 30: goto L_0x0237;
                case 31: goto L_0x0225;
                case 32: goto L_0x01fc;
                case 33: goto L_0x01ea;
                default: goto L_0x01e5;
            }
        L_0x01e5:
            super.setProperty(r7, r8, r9)
            goto L_0x04bb
        L_0x01ea:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x01f1
            goto L_0x01f7
        L_0x01f1:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x01f7:
            r8.setMaskContentUnits(r7, r2)
            goto L_0x04bb
        L_0x01fc:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x020b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x020b:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x021a
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x04bb
        L_0x021a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x0225:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x022c
            goto L_0x0232
        L_0x022c:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x0232:
            r8.setResponsible(r7, r2)
            goto L_0x04bb
        L_0x0237:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x023e
            goto L_0x0244
        L_0x023e:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0244:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x04bb
        L_0x0249:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0250
            goto L_0x0253
        L_0x0250:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0253:
            r8.setDisplay(r7, r5)
            goto L_0x04bb
        L_0x0258:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x025f
            goto L_0x0265
        L_0x025f:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0265:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x04bb
        L_0x026a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0271
            goto L_0x0277
        L_0x0271:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0277:
            r8.setClipRule(r7, r2)
            goto L_0x04bb
        L_0x027c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0283
            goto L_0x0286
        L_0x0283:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0286:
            r8.setClipPath(r7, r5)
            goto L_0x04bb
        L_0x028b:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x029a
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x029a:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x04bb
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x04bb
        L_0x02a9:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02b8
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x02b8:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02c7
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x04bb
        L_0x02c7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x04bb
        L_0x02d3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x02da
            goto L_0x02dd
        L_0x02da:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02dd:
            r8.setMarkerStart(r7, r5)
            goto L_0x04bb
        L_0x02e2:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02f1
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setWidth(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x02f1:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0300
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r9)
            goto L_0x04bb
        L_0x0300:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r5)
            goto L_0x04bb
        L_0x030c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0313
            goto L_0x0319
        L_0x0313:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0319:
            r8.setVectorEffect(r7, r2)
            goto L_0x04bb
        L_0x031e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0325
            goto L_0x032b
        L_0x0325:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x032b:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x04bb
        L_0x0330:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0337
            goto L_0x033a
        L_0x0337:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x033a:
            r8.setName(r7, r5)
            goto L_0x04bb
        L_0x033f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0346
            goto L_0x0349
        L_0x0346:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0349:
            r8.setMask(r7, r5)
            goto L_0x04bb
        L_0x034e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x04bb
        L_0x0359:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x04bb
        L_0x0364:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0373
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setY(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x0373:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0382
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r9)
            goto L_0x04bb
        L_0x0382:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r5)
            goto L_0x04bb
        L_0x038e:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x039d
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setX(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x039d:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x03ac
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r9)
            goto L_0x04bb
        L_0x03ac:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r5)
            goto L_0x04bb
        L_0x03b8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x03bf
            goto L_0x03c5
        L_0x03bf:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x03c5:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x04bb
        L_0x03ca:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x03d1
            goto L_0x03d7
        L_0x03d1:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03d7:
            r8.setFillOpacity(r7, r4)
            goto L_0x04bb
        L_0x03dc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x03e3
            goto L_0x03e9
        L_0x03e3:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x03e9:
            r8.setMaskUnits(r7, r2)
            goto L_0x04bb
        L_0x03ee:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x03f5
            goto L_0x03f8
        L_0x03f5:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03f8:
            r8.setPointerEvents(r7, r5)
            goto L_0x04bb
        L_0x03fd:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0404
            goto L_0x040a
        L_0x0404:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x040a:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x04bb
        L_0x040f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x0416
            goto L_0x041c
        L_0x0416:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x041c:
            r8.setFillRule(r7, r1)
            goto L_0x04bb
        L_0x0421:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0430
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x0430:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x043f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x04bb
        L_0x043f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x04bb
        L_0x044b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x04bb
        L_0x0456:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x045d
            goto L_0x0460
        L_0x045d:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0460:
            r8.setMarkerMid(r7, r5)
            goto L_0x04bb
        L_0x0464:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            if (r9 != 0) goto L_0x046b
            goto L_0x046e
        L_0x046b:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x046e:
            r8.setMarkerEnd(r7, r5)
            goto L_0x04bb
        L_0x0472:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x04bb
        L_0x047c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x04bb
        L_0x0486:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0494
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setHeight(r7, (java.lang.String) r9)
            goto L_0x04bb
        L_0x0494:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x04a2
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r9)
            goto L_0x04bb
        L_0x04a2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r5)
            goto L_0x04bb
        L_0x04ad:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x04b2
            goto L_0x04b8
        L_0x04b2:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x04b8:
            r8.setOpacity(r7, r4)
        L_0x04bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGMaskManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
