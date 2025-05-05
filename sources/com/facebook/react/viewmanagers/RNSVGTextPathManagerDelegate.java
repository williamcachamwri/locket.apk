package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface;

public class RNSVGTextPathManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGTextPathManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGTextPathManagerDelegate(U u) {
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
                case -2012158909: goto L_0x0271;
                case -1993948267: goto L_0x0265;
                case -1603134955: goto L_0x025a;
                case -1267206133: goto L_0x024f;
                case -1171891896: goto L_0x0244;
                case -1139902161: goto L_0x0238;
                case -1081239615: goto L_0x022d;
                case -1077554975: goto L_0x0222;
                case -993894751: goto L_0x0214;
                case -933864895: goto L_0x0206;
                case -933857362: goto L_0x01f8;
                case -925180581: goto L_0x01ea;
                case -891980232: goto L_0x01db;
                case -734428249: goto L_0x01cd;
                case -729118945: goto L_0x01bf;
                case -416535885: goto L_0x01b0;
                case -293492298: goto L_0x01a2;
                case -53677816: goto L_0x0194;
                case -44578051: goto L_0x0185;
                case 120: goto L_0x0176;
                case 121: goto L_0x0167;
                case 3220: goto L_0x0159;
                case 3221: goto L_0x014b;
                case 3143043: goto L_0x013d;
                case 3148879: goto L_0x012f;
                case 3211051: goto L_0x0121;
                case 3344108: goto L_0x0113;
                case 3373707: goto L_0x0105;
                case 3530071: goto L_0x00f6;
                case 78845486: goto L_0x00e7;
                case 104482996: goto L_0x00d8;
                case 217109576: goto L_0x00ca;
                case 275888445: goto L_0x00bc;
                case 365601008: goto L_0x00ae;
                case 401643183: goto L_0x009f;
                case 778043962: goto L_0x0091;
                case 917656469: goto L_0x0083;
                case 917735020: goto L_0x0075;
                case 1027575302: goto L_0x0066;
                case 1054434908: goto L_0x0058;
                case 1637488243: goto L_0x0049;
                case 1671764162: goto L_0x003b;
                case 1790285174: goto L_0x002c;
                case 1847674614: goto L_0x001e;
                case 1924065902: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x027c
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x027c
        L_0x001a:
            r3 = 44
            goto L_0x027c
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x027c
        L_0x0028:
            r3 = 43
            goto L_0x027c
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x027c
        L_0x0037:
            r3 = 42
            goto L_0x027c
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x027c
        L_0x0045:
            r3 = 41
            goto L_0x027c
        L_0x0049:
            java.lang.String r0 = "textLength"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x027c
        L_0x0054:
            r3 = 40
            goto L_0x027c
        L_0x0058:
            java.lang.String r0 = "midLine"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x027c
        L_0x0062:
            r3 = 39
            goto L_0x027c
        L_0x0066:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0071
            goto L_0x027c
        L_0x0071:
            r3 = 38
            goto L_0x027c
        L_0x0075:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x027c
        L_0x007f:
            r3 = 37
            goto L_0x027c
        L_0x0083:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x027c
        L_0x008d:
            r3 = 36
            goto L_0x027c
        L_0x0091:
            java.lang.String r0 = "inlineSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x027c
        L_0x009b:
            r3 = 35
            goto L_0x027c
        L_0x009f:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00aa
            goto L_0x027c
        L_0x00aa:
            r3 = 34
            goto L_0x027c
        L_0x00ae:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b8
            goto L_0x027c
        L_0x00b8:
            r3 = 33
            goto L_0x027c
        L_0x00bc:
            java.lang.String r0 = "baselineShift"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c6
            goto L_0x027c
        L_0x00c6:
            r3 = 32
            goto L_0x027c
        L_0x00ca:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d4
            goto L_0x027c
        L_0x00d4:
            r3 = 31
            goto L_0x027c
        L_0x00d8:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e3
            goto L_0x027c
        L_0x00e3:
            r3 = 30
            goto L_0x027c
        L_0x00e7:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x027c
        L_0x00f2:
            r3 = 29
            goto L_0x027c
        L_0x00f6:
            java.lang.String r0 = "side"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0101
            goto L_0x027c
        L_0x0101:
            r3 = 28
            goto L_0x027c
        L_0x0105:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010f
            goto L_0x027c
        L_0x010f:
            r3 = 27
            goto L_0x027c
        L_0x0113:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011d
            goto L_0x027c
        L_0x011d:
            r3 = 26
            goto L_0x027c
        L_0x0121:
            java.lang.String r0 = "href"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012b
            goto L_0x027c
        L_0x012b:
            r3 = 25
            goto L_0x027c
        L_0x012f:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0139
            goto L_0x027c
        L_0x0139:
            r3 = 24
            goto L_0x027c
        L_0x013d:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0147
            goto L_0x027c
        L_0x0147:
            r3 = 23
            goto L_0x027c
        L_0x014b:
            java.lang.String r0 = "dy"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0155
            goto L_0x027c
        L_0x0155:
            r3 = 22
            goto L_0x027c
        L_0x0159:
            java.lang.String r0 = "dx"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0163
            goto L_0x027c
        L_0x0163:
            r3 = 21
            goto L_0x027c
        L_0x0167:
            java.lang.String r0 = "y"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0172
            goto L_0x027c
        L_0x0172:
            r3 = 20
            goto L_0x027c
        L_0x0176:
            java.lang.String r0 = "x"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0181
            goto L_0x027c
        L_0x0181:
            r3 = 19
            goto L_0x027c
        L_0x0185:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0190
            goto L_0x027c
        L_0x0190:
            r3 = 18
            goto L_0x027c
        L_0x0194:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x019e
            goto L_0x027c
        L_0x019e:
            r3 = 17
            goto L_0x027c
        L_0x01a2:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ac
            goto L_0x027c
        L_0x01ac:
            r3 = 16
            goto L_0x027c
        L_0x01b0:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01bb
            goto L_0x027c
        L_0x01bb:
            r3 = 15
            goto L_0x027c
        L_0x01bf:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c9
            goto L_0x027c
        L_0x01c9:
            r3 = 14
            goto L_0x027c
        L_0x01cd:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01d7
            goto L_0x027c
        L_0x01d7:
            r3 = 13
            goto L_0x027c
        L_0x01db:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01e6
            goto L_0x027c
        L_0x01e6:
            r3 = 12
            goto L_0x027c
        L_0x01ea:
            java.lang.String r0 = "rotate"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01f4
            goto L_0x027c
        L_0x01f4:
            r3 = 11
            goto L_0x027c
        L_0x01f8:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0202
            goto L_0x027c
        L_0x0202:
            r3 = 10
            goto L_0x027c
        L_0x0206:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0210
            goto L_0x027c
        L_0x0210:
            r3 = 9
            goto L_0x027c
        L_0x0214:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x021e
            goto L_0x027c
        L_0x021e:
            r3 = 8
            goto L_0x027c
        L_0x0222:
            java.lang.String r0 = "method"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x022b
            goto L_0x027c
        L_0x022b:
            r3 = 7
            goto L_0x027c
        L_0x022d:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0236
            goto L_0x027c
        L_0x0236:
            r3 = 6
            goto L_0x027c
        L_0x0238:
            java.lang.String r0 = "verticalAlign"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0242
            goto L_0x027c
        L_0x0242:
            r3 = 5
            goto L_0x027c
        L_0x0244:
            java.lang.String r0 = "alignmentBaseline"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x024d
            goto L_0x027c
        L_0x024d:
            r3 = 4
            goto L_0x027c
        L_0x024f:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0258
            goto L_0x027c
        L_0x0258:
            r3 = 3
            goto L_0x027c
        L_0x025a:
            java.lang.String r0 = "lengthAdjust"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0263
            goto L_0x027c
        L_0x0263:
            r3 = 2
            goto L_0x027c
        L_0x0265:
            java.lang.String r0 = "startOffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x026f
            goto L_0x027c
        L_0x026f:
            r3 = r1
            goto L_0x027c
        L_0x0271:
            java.lang.String r0 = "spacing"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x027b
            goto L_0x027c
        L_0x027b:
            r3 = r2
        L_0x027c:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x05d7;
                case 1: goto L_0x05b0;
                case 2: goto L_0x05a2;
                case 3: goto L_0x0593;
                case 4: goto L_0x0585;
                case 5: goto L_0x0577;
                case 6: goto L_0x056c;
                case 7: goto L_0x055d;
                case 8: goto L_0x0552;
                case 9: goto L_0x0543;
                case 10: goto L_0x0534;
                case 11: goto L_0x0529;
                case 12: goto L_0x051e;
                case 13: goto L_0x04f4;
                case 14: goto L_0x04e2;
                case 15: goto L_0x04d0;
                case 16: goto L_0x04c1;
                case 17: goto L_0x04af;
                case 18: goto L_0x049d;
                case 19: goto L_0x0492;
                case 20: goto L_0x0487;
                case 21: goto L_0x047c;
                case 22: goto L_0x0471;
                case 23: goto L_0x0466;
                case 24: goto L_0x045b;
                case 25: goto L_0x044c;
                case 26: goto L_0x043d;
                case 27: goto L_0x042e;
                case 28: goto L_0x041f;
                case 29: goto L_0x040d;
                case 30: goto L_0x03fb;
                case 31: goto L_0x03ec;
                case 32: goto L_0x03c2;
                case 33: goto L_0x0398;
                case 34: goto L_0x037a;
                case 35: goto L_0x0350;
                case 36: goto L_0x0341;
                case 37: goto L_0x032f;
                case 38: goto L_0x031d;
                case 39: goto L_0x030e;
                case 40: goto L_0x02e4;
                case 41: goto L_0x02d5;
                case 42: goto L_0x02c3;
                case 43: goto L_0x02b1;
                case 44: goto L_0x0288;
                default: goto L_0x0283;
            }
        L_0x0283:
            super.setProperty(r7, r8, r9)
            goto L_0x05e4
        L_0x0288:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0297
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x0297:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02a6
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x05e4
        L_0x02a6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x02b1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x02b8
            goto L_0x02be
        L_0x02b8:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x02be:
            r8.setResponsible(r7, r2)
            goto L_0x05e4
        L_0x02c3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x02ca
            goto L_0x02d0
        L_0x02ca:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02d0:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x05e4
        L_0x02d5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x02dc
            goto L_0x02df
        L_0x02dc:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02df:
            r8.setDisplay(r7, r5)
            goto L_0x05e4
        L_0x02e4:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02f3
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setTextLength(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x02f3:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0302
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setTextLength(r7, (java.lang.Double) r9)
            goto L_0x05e4
        L_0x0302:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setTextLength(r7, (java.lang.Double) r5)
            goto L_0x05e4
        L_0x030e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0315
            goto L_0x0318
        L_0x0315:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0318:
            r8.setMidLine(r7, r5)
            goto L_0x05e4
        L_0x031d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0324
            goto L_0x032a
        L_0x0324:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x032a:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x05e4
        L_0x032f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0336
            goto L_0x033c
        L_0x0336:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x033c:
            r8.setClipRule(r7, r2)
            goto L_0x05e4
        L_0x0341:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0348
            goto L_0x034b
        L_0x0348:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x034b:
            r8.setClipPath(r7, r5)
            goto L_0x05e4
        L_0x0350:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x035f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setInlineSize(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x035f:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x036e
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setInlineSize(r7, (java.lang.Double) r9)
            goto L_0x05e4
        L_0x036e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setInlineSize(r7, (java.lang.Double) r5)
            goto L_0x05e4
        L_0x037a:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0389
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x0389:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x05e4
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x05e4
        L_0x0398:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x03a7
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x03a7:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x03b6
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x05e4
        L_0x03b6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x05e4
        L_0x03c2:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x03d1
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setBaselineShift(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x03d1:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x03e0
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setBaselineShift(r7, (java.lang.Double) r9)
            goto L_0x05e4
        L_0x03e0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setBaselineShift(r7, (java.lang.Double) r5)
            goto L_0x05e4
        L_0x03ec:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x03f3
            goto L_0x03f6
        L_0x03f3:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03f6:
            r8.setMarkerStart(r7, r5)
            goto L_0x05e4
        L_0x03fb:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0402
            goto L_0x0408
        L_0x0402:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0408:
            r8.setVectorEffect(r7, r2)
            goto L_0x05e4
        L_0x040d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0414
            goto L_0x041a
        L_0x0414:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x041a:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x05e4
        L_0x041f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0426
            goto L_0x0429
        L_0x0426:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0429:
            r8.setSide(r7, r5)
            goto L_0x05e4
        L_0x042e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0435
            goto L_0x0438
        L_0x0435:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0438:
            r8.setName(r7, r5)
            goto L_0x05e4
        L_0x043d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0444
            goto L_0x0447
        L_0x0444:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0447:
            r8.setMask(r7, r5)
            goto L_0x05e4
        L_0x044c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0453
            goto L_0x0456
        L_0x0453:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0456:
            r8.setHref(r7, r5)
            goto L_0x05e4
        L_0x045b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x05e4
        L_0x0466:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x05e4
        L_0x0471:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setDy(r7, r9)
            goto L_0x05e4
        L_0x047c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setDx(r7, r9)
            goto L_0x05e4
        L_0x0487:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setY(r7, r9)
            goto L_0x05e4
        L_0x0492:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setX(r7, r9)
            goto L_0x05e4
        L_0x049d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x04a4
            goto L_0x04aa
        L_0x04a4:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x04aa:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x05e4
        L_0x04af:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x04b6
            goto L_0x04bc
        L_0x04b6:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x04bc:
            r8.setFillOpacity(r7, r4)
            goto L_0x05e4
        L_0x04c1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x04c8
            goto L_0x04cb
        L_0x04c8:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04cb:
            r8.setPointerEvents(r7, r5)
            goto L_0x05e4
        L_0x04d0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x04d7
            goto L_0x04dd
        L_0x04d7:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x04dd:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x05e4
        L_0x04e2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x04e9
            goto L_0x04ef
        L_0x04e9:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x04ef:
            r8.setFillRule(r7, r1)
            goto L_0x05e4
        L_0x04f4:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0503
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x0503:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0512
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x05e4
        L_0x0512:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x05e4
        L_0x051e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x05e4
        L_0x0529:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setRotate(r7, r9)
            goto L_0x05e4
        L_0x0534:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x053b
            goto L_0x053e
        L_0x053b:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x053e:
            r8.setMarkerMid(r7, r5)
            goto L_0x05e4
        L_0x0543:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x054a
            goto L_0x054d
        L_0x054a:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x054d:
            r8.setMarkerEnd(r7, r5)
            goto L_0x05e4
        L_0x0552:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x05e4
        L_0x055d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x0564
            goto L_0x0567
        L_0x0564:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0567:
            r8.setMethod(r7, r5)
            goto L_0x05e4
        L_0x056c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x05e4
        L_0x0577:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x057e
            goto L_0x0581
        L_0x057e:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0581:
            r8.setVerticalAlign(r7, r5)
            goto L_0x05e4
        L_0x0585:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x058c
            goto L_0x058f
        L_0x058c:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x058f:
            r8.setAlignmentBaseline(r7, r5)
            goto L_0x05e4
        L_0x0593:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x0598
            goto L_0x059e
        L_0x0598:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x059e:
            r8.setOpacity(r7, r4)
            goto L_0x05e4
        L_0x05a2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x05a9
            goto L_0x05ac
        L_0x05a9:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x05ac:
            r8.setLengthAdjust(r7, r5)
            goto L_0x05e4
        L_0x05b0:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x05be
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStartOffset(r7, (java.lang.String) r9)
            goto L_0x05e4
        L_0x05be:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x05cc
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStartOffset(r7, (java.lang.Double) r9)
            goto L_0x05e4
        L_0x05cc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStartOffset(r7, (java.lang.Double) r5)
            goto L_0x05e4
        L_0x05d7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r8
            if (r9 != 0) goto L_0x05de
            goto L_0x05e1
        L_0x05de:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x05e1:
            r8.setSpacing(r7, r5)
        L_0x05e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGTextPathManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
