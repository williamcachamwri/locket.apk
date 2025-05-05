package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGTextManagerInterface;

public class RNSVGTextManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGTextManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGTextManagerDelegate(U u) {
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
                case -1603134955: goto L_0x021b;
                case -1267206133: goto L_0x0210;
                case -1171891896: goto L_0x0205;
                case -1139902161: goto L_0x01f9;
                case -1081239615: goto L_0x01ee;
                case -993894751: goto L_0x01e3;
                case -933864895: goto L_0x01d8;
                case -933857362: goto L_0x01cd;
                case -925180581: goto L_0x01bf;
                case -891980232: goto L_0x01b0;
                case -734428249: goto L_0x01a2;
                case -729118945: goto L_0x0194;
                case -416535885: goto L_0x0185;
                case -293492298: goto L_0x0177;
                case -53677816: goto L_0x0169;
                case -44578051: goto L_0x015a;
                case 120: goto L_0x014b;
                case 121: goto L_0x013c;
                case 3220: goto L_0x012e;
                case 3221: goto L_0x0120;
                case 3143043: goto L_0x0112;
                case 3148879: goto L_0x0104;
                case 3344108: goto L_0x00f6;
                case 3373707: goto L_0x00e8;
                case 78845486: goto L_0x00d9;
                case 104482996: goto L_0x00ca;
                case 217109576: goto L_0x00bc;
                case 275888445: goto L_0x00ae;
                case 365601008: goto L_0x00a0;
                case 401643183: goto L_0x0091;
                case 778043962: goto L_0x0083;
                case 917656469: goto L_0x0075;
                case 917735020: goto L_0x0067;
                case 1027575302: goto L_0x0058;
                case 1637488243: goto L_0x0049;
                case 1671764162: goto L_0x003b;
                case 1790285174: goto L_0x002c;
                case 1847674614: goto L_0x001e;
                case 1924065902: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0225
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x0225
        L_0x001a:
            r3 = 38
            goto L_0x0225
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x0225
        L_0x0028:
            r3 = 37
            goto L_0x0225
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x0225
        L_0x0037:
            r3 = 36
            goto L_0x0225
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0225
        L_0x0045:
            r3 = 35
            goto L_0x0225
        L_0x0049:
            java.lang.String r0 = "textLength"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x0225
        L_0x0054:
            r3 = 34
            goto L_0x0225
        L_0x0058:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0063
            goto L_0x0225
        L_0x0063:
            r3 = 33
            goto L_0x0225
        L_0x0067:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0071
            goto L_0x0225
        L_0x0071:
            r3 = 32
            goto L_0x0225
        L_0x0075:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x0225
        L_0x007f:
            r3 = 31
            goto L_0x0225
        L_0x0083:
            java.lang.String r0 = "inlineSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x0225
        L_0x008d:
            r3 = 30
            goto L_0x0225
        L_0x0091:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009c
            goto L_0x0225
        L_0x009c:
            r3 = 29
            goto L_0x0225
        L_0x00a0:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00aa
            goto L_0x0225
        L_0x00aa:
            r3 = 28
            goto L_0x0225
        L_0x00ae:
            java.lang.String r0 = "baselineShift"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b8
            goto L_0x0225
        L_0x00b8:
            r3 = 27
            goto L_0x0225
        L_0x00bc:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c6
            goto L_0x0225
        L_0x00c6:
            r3 = 26
            goto L_0x0225
        L_0x00ca:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d5
            goto L_0x0225
        L_0x00d5:
            r3 = 25
            goto L_0x0225
        L_0x00d9:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e4
            goto L_0x0225
        L_0x00e4:
            r3 = 24
            goto L_0x0225
        L_0x00e8:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x0225
        L_0x00f2:
            r3 = 23
            goto L_0x0225
        L_0x00f6:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x0225
        L_0x0100:
            r3 = 22
            goto L_0x0225
        L_0x0104:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010e
            goto L_0x0225
        L_0x010e:
            r3 = 21
            goto L_0x0225
        L_0x0112:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011c
            goto L_0x0225
        L_0x011c:
            r3 = 20
            goto L_0x0225
        L_0x0120:
            java.lang.String r0 = "dy"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012a
            goto L_0x0225
        L_0x012a:
            r3 = 19
            goto L_0x0225
        L_0x012e:
            java.lang.String r0 = "dx"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0138
            goto L_0x0225
        L_0x0138:
            r3 = 18
            goto L_0x0225
        L_0x013c:
            java.lang.String r0 = "y"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0147
            goto L_0x0225
        L_0x0147:
            r3 = 17
            goto L_0x0225
        L_0x014b:
            java.lang.String r0 = "x"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0156
            goto L_0x0225
        L_0x0156:
            r3 = 16
            goto L_0x0225
        L_0x015a:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0165
            goto L_0x0225
        L_0x0165:
            r3 = 15
            goto L_0x0225
        L_0x0169:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0173
            goto L_0x0225
        L_0x0173:
            r3 = 14
            goto L_0x0225
        L_0x0177:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0181
            goto L_0x0225
        L_0x0181:
            r3 = 13
            goto L_0x0225
        L_0x0185:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0190
            goto L_0x0225
        L_0x0190:
            r3 = 12
            goto L_0x0225
        L_0x0194:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x019e
            goto L_0x0225
        L_0x019e:
            r3 = 11
            goto L_0x0225
        L_0x01a2:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ac
            goto L_0x0225
        L_0x01ac:
            r3 = 10
            goto L_0x0225
        L_0x01b0:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01bb
            goto L_0x0225
        L_0x01bb:
            r3 = 9
            goto L_0x0225
        L_0x01bf:
            java.lang.String r0 = "rotate"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c9
            goto L_0x0225
        L_0x01c9:
            r3 = 8
            goto L_0x0225
        L_0x01cd:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01d6
            goto L_0x0225
        L_0x01d6:
            r3 = 7
            goto L_0x0225
        L_0x01d8:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01e1
            goto L_0x0225
        L_0x01e1:
            r3 = 6
            goto L_0x0225
        L_0x01e3:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ec
            goto L_0x0225
        L_0x01ec:
            r3 = 5
            goto L_0x0225
        L_0x01ee:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01f7
            goto L_0x0225
        L_0x01f7:
            r3 = 4
            goto L_0x0225
        L_0x01f9:
            java.lang.String r0 = "verticalAlign"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0203
            goto L_0x0225
        L_0x0203:
            r3 = 3
            goto L_0x0225
        L_0x0205:
            java.lang.String r0 = "alignmentBaseline"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x020e
            goto L_0x0225
        L_0x020e:
            r3 = 2
            goto L_0x0225
        L_0x0210:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0219
            goto L_0x0225
        L_0x0219:
            r3 = r1
            goto L_0x0225
        L_0x021b:
            java.lang.String r0 = "lengthAdjust"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0224
            goto L_0x0225
        L_0x0224:
            r3 = r2
        L_0x0225:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x050b;
                case 1: goto L_0x04fc;
                case 2: goto L_0x04ee;
                case 3: goto L_0x04e0;
                case 4: goto L_0x04d6;
                case 5: goto L_0x04cc;
                case 6: goto L_0x04be;
                case 7: goto L_0x04b0;
                case 8: goto L_0x04a5;
                case 9: goto L_0x049a;
                case 10: goto L_0x0470;
                case 11: goto L_0x045e;
                case 12: goto L_0x044c;
                case 13: goto L_0x043d;
                case 14: goto L_0x042b;
                case 15: goto L_0x0419;
                case 16: goto L_0x040e;
                case 17: goto L_0x0403;
                case 18: goto L_0x03f8;
                case 19: goto L_0x03ed;
                case 20: goto L_0x03e2;
                case 21: goto L_0x03d7;
                case 22: goto L_0x03c8;
                case 23: goto L_0x03b9;
                case 24: goto L_0x03a7;
                case 25: goto L_0x0395;
                case 26: goto L_0x0386;
                case 27: goto L_0x035c;
                case 28: goto L_0x0332;
                case 29: goto L_0x0314;
                case 30: goto L_0x02ea;
                case 31: goto L_0x02db;
                case 32: goto L_0x02c9;
                case 33: goto L_0x02b7;
                case 34: goto L_0x028d;
                case 35: goto L_0x027e;
                case 36: goto L_0x026c;
                case 37: goto L_0x025a;
                case 38: goto L_0x0231;
                default: goto L_0x022c;
            }
        L_0x022c:
            super.setProperty(r7, r8, r9)
            goto L_0x0518
        L_0x0231:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0240
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0518
        L_0x0240:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x024f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x0518
        L_0x024f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0518
        L_0x025a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0261
            goto L_0x0267
        L_0x0261:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x0267:
            r8.setResponsible(r7, r2)
            goto L_0x0518
        L_0x026c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0273
            goto L_0x0279
        L_0x0273:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0279:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x0518
        L_0x027e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0285
            goto L_0x0288
        L_0x0285:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0288:
            r8.setDisplay(r7, r5)
            goto L_0x0518
        L_0x028d:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x029c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setTextLength(r7, (java.lang.String) r9)
            goto L_0x0518
        L_0x029c:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02ab
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setTextLength(r7, (java.lang.Double) r9)
            goto L_0x0518
        L_0x02ab:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setTextLength(r7, (java.lang.Double) r5)
            goto L_0x0518
        L_0x02b7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x02be
            goto L_0x02c4
        L_0x02be:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02c4:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x0518
        L_0x02c9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x02d0
            goto L_0x02d6
        L_0x02d0:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02d6:
            r8.setClipRule(r7, r2)
            goto L_0x0518
        L_0x02db:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x02e2
            goto L_0x02e5
        L_0x02e2:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02e5:
            r8.setClipPath(r7, r5)
            goto L_0x0518
        L_0x02ea:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02f9
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setInlineSize(r7, (java.lang.String) r9)
            goto L_0x0518
        L_0x02f9:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0308
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setInlineSize(r7, (java.lang.Double) r9)
            goto L_0x0518
        L_0x0308:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setInlineSize(r7, (java.lang.Double) r5)
            goto L_0x0518
        L_0x0314:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0323
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x0518
        L_0x0323:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x0518
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x0518
        L_0x0332:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0341
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x0518
        L_0x0341:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0350
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x0518
        L_0x0350:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x0518
        L_0x035c:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x036b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setBaselineShift(r7, (java.lang.String) r9)
            goto L_0x0518
        L_0x036b:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x037a
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setBaselineShift(r7, (java.lang.Double) r9)
            goto L_0x0518
        L_0x037a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setBaselineShift(r7, (java.lang.Double) r5)
            goto L_0x0518
        L_0x0386:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x038d
            goto L_0x0390
        L_0x038d:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0390:
            r8.setMarkerStart(r7, r5)
            goto L_0x0518
        L_0x0395:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x039c
            goto L_0x03a2
        L_0x039c:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x03a2:
            r8.setVectorEffect(r7, r2)
            goto L_0x0518
        L_0x03a7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x03ae
            goto L_0x03b4
        L_0x03ae:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x03b4:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x0518
        L_0x03b9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x03c0
            goto L_0x03c3
        L_0x03c0:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03c3:
            r8.setName(r7, r5)
            goto L_0x0518
        L_0x03c8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x03cf
            goto L_0x03d2
        L_0x03cf:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03d2:
            r8.setMask(r7, r5)
            goto L_0x0518
        L_0x03d7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x0518
        L_0x03e2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x0518
        L_0x03ed:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setDy(r7, r9)
            goto L_0x0518
        L_0x03f8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setDx(r7, r9)
            goto L_0x0518
        L_0x0403:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setY(r7, r9)
            goto L_0x0518
        L_0x040e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setX(r7, r9)
            goto L_0x0518
        L_0x0419:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0420
            goto L_0x0426
        L_0x0420:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x0426:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x0518
        L_0x042b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0432
            goto L_0x0438
        L_0x0432:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0438:
            r8.setFillOpacity(r7, r4)
            goto L_0x0518
        L_0x043d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0444
            goto L_0x0447
        L_0x0444:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0447:
            r8.setPointerEvents(r7, r5)
            goto L_0x0518
        L_0x044c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0453
            goto L_0x0459
        L_0x0453:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0459:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x0518
        L_0x045e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0465
            goto L_0x046b
        L_0x0465:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x046b:
            r8.setFillRule(r7, r1)
            goto L_0x0518
        L_0x0470:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x047f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x0518
        L_0x047f:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x048e
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x0518
        L_0x048e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x0518
        L_0x049a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x0518
        L_0x04a5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setRotate(r7, r9)
            goto L_0x0518
        L_0x04b0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x04b7
            goto L_0x04ba
        L_0x04b7:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04ba:
            r8.setMarkerMid(r7, r5)
            goto L_0x0518
        L_0x04be:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x04c5
            goto L_0x04c8
        L_0x04c5:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04c8:
            r8.setMarkerEnd(r7, r5)
            goto L_0x0518
        L_0x04cc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x0518
        L_0x04d6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x0518
        L_0x04e0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x04e7
            goto L_0x04ea
        L_0x04e7:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04ea:
            r8.setVerticalAlign(r7, r5)
            goto L_0x0518
        L_0x04ee:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x04f5
            goto L_0x04f8
        L_0x04f5:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04f8:
            r8.setAlignmentBaseline(r7, r5)
            goto L_0x0518
        L_0x04fc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x0501
            goto L_0x0507
        L_0x0501:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0507:
            r8.setOpacity(r7, r4)
            goto L_0x0518
        L_0x050b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r8
            if (r9 != 0) goto L_0x0512
            goto L_0x0515
        L_0x0512:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0515:
            r8.setLengthAdjust(r7, r5)
        L_0x0518:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGTextManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
