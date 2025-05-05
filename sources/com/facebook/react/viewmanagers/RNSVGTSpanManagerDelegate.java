package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface;

public class RNSVGTSpanManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGTSpanManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGTSpanManagerDelegate(U u) {
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
                case -1603134955: goto L_0x0229;
                case -1267206133: goto L_0x021e;
                case -1171891896: goto L_0x0213;
                case -1139902161: goto L_0x0207;
                case -1081239615: goto L_0x01fc;
                case -993894751: goto L_0x01f1;
                case -933864895: goto L_0x01e6;
                case -933857362: goto L_0x01db;
                case -925180581: goto L_0x01cd;
                case -891980232: goto L_0x01be;
                case -734428249: goto L_0x01b0;
                case -729118945: goto L_0x01a2;
                case -416535885: goto L_0x0193;
                case -293492298: goto L_0x0185;
                case -53677816: goto L_0x0177;
                case -44578051: goto L_0x0168;
                case 120: goto L_0x0159;
                case 121: goto L_0x014a;
                case 3220: goto L_0x013c;
                case 3221: goto L_0x012e;
                case 3143043: goto L_0x0120;
                case 3148879: goto L_0x0112;
                case 3344108: goto L_0x0104;
                case 3373707: goto L_0x00f6;
                case 78845486: goto L_0x00e7;
                case 104482996: goto L_0x00d8;
                case 217109576: goto L_0x00ca;
                case 275888445: goto L_0x00bc;
                case 365601008: goto L_0x00ae;
                case 401643183: goto L_0x009f;
                case 778043962: goto L_0x0091;
                case 917656469: goto L_0x0083;
                case 917735020: goto L_0x0075;
                case 951530617: goto L_0x0067;
                case 1027575302: goto L_0x0058;
                case 1637488243: goto L_0x0049;
                case 1671764162: goto L_0x003b;
                case 1790285174: goto L_0x002c;
                case 1847674614: goto L_0x001e;
                case 1924065902: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0233
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x0233
        L_0x001a:
            r3 = 39
            goto L_0x0233
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x0233
        L_0x0028:
            r3 = 38
            goto L_0x0233
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x0233
        L_0x0037:
            r3 = 37
            goto L_0x0233
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0233
        L_0x0045:
            r3 = 36
            goto L_0x0233
        L_0x0049:
            java.lang.String r0 = "textLength"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x0233
        L_0x0054:
            r3 = 35
            goto L_0x0233
        L_0x0058:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0063
            goto L_0x0233
        L_0x0063:
            r3 = 34
            goto L_0x0233
        L_0x0067:
            java.lang.String r0 = "content"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0071
            goto L_0x0233
        L_0x0071:
            r3 = 33
            goto L_0x0233
        L_0x0075:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x0233
        L_0x007f:
            r3 = 32
            goto L_0x0233
        L_0x0083:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x0233
        L_0x008d:
            r3 = 31
            goto L_0x0233
        L_0x0091:
            java.lang.String r0 = "inlineSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x0233
        L_0x009b:
            r3 = 30
            goto L_0x0233
        L_0x009f:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00aa
            goto L_0x0233
        L_0x00aa:
            r3 = 29
            goto L_0x0233
        L_0x00ae:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b8
            goto L_0x0233
        L_0x00b8:
            r3 = 28
            goto L_0x0233
        L_0x00bc:
            java.lang.String r0 = "baselineShift"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c6
            goto L_0x0233
        L_0x00c6:
            r3 = 27
            goto L_0x0233
        L_0x00ca:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d4
            goto L_0x0233
        L_0x00d4:
            r3 = 26
            goto L_0x0233
        L_0x00d8:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e3
            goto L_0x0233
        L_0x00e3:
            r3 = 25
            goto L_0x0233
        L_0x00e7:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x0233
        L_0x00f2:
            r3 = 24
            goto L_0x0233
        L_0x00f6:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x0233
        L_0x0100:
            r3 = 23
            goto L_0x0233
        L_0x0104:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010e
            goto L_0x0233
        L_0x010e:
            r3 = 22
            goto L_0x0233
        L_0x0112:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011c
            goto L_0x0233
        L_0x011c:
            r3 = 21
            goto L_0x0233
        L_0x0120:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012a
            goto L_0x0233
        L_0x012a:
            r3 = 20
            goto L_0x0233
        L_0x012e:
            java.lang.String r0 = "dy"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0138
            goto L_0x0233
        L_0x0138:
            r3 = 19
            goto L_0x0233
        L_0x013c:
            java.lang.String r0 = "dx"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0146
            goto L_0x0233
        L_0x0146:
            r3 = 18
            goto L_0x0233
        L_0x014a:
            java.lang.String r0 = "y"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0155
            goto L_0x0233
        L_0x0155:
            r3 = 17
            goto L_0x0233
        L_0x0159:
            java.lang.String r0 = "x"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0164
            goto L_0x0233
        L_0x0164:
            r3 = 16
            goto L_0x0233
        L_0x0168:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0173
            goto L_0x0233
        L_0x0173:
            r3 = 15
            goto L_0x0233
        L_0x0177:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0181
            goto L_0x0233
        L_0x0181:
            r3 = 14
            goto L_0x0233
        L_0x0185:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x018f
            goto L_0x0233
        L_0x018f:
            r3 = 13
            goto L_0x0233
        L_0x0193:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x019e
            goto L_0x0233
        L_0x019e:
            r3 = 12
            goto L_0x0233
        L_0x01a2:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ac
            goto L_0x0233
        L_0x01ac:
            r3 = 11
            goto L_0x0233
        L_0x01b0:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ba
            goto L_0x0233
        L_0x01ba:
            r3 = 10
            goto L_0x0233
        L_0x01be:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c9
            goto L_0x0233
        L_0x01c9:
            r3 = 9
            goto L_0x0233
        L_0x01cd:
            java.lang.String r0 = "rotate"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01d7
            goto L_0x0233
        L_0x01d7:
            r3 = 8
            goto L_0x0233
        L_0x01db:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01e4
            goto L_0x0233
        L_0x01e4:
            r3 = 7
            goto L_0x0233
        L_0x01e6:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ef
            goto L_0x0233
        L_0x01ef:
            r3 = 6
            goto L_0x0233
        L_0x01f1:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01fa
            goto L_0x0233
        L_0x01fa:
            r3 = 5
            goto L_0x0233
        L_0x01fc:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0205
            goto L_0x0233
        L_0x0205:
            r3 = 4
            goto L_0x0233
        L_0x0207:
            java.lang.String r0 = "verticalAlign"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0211
            goto L_0x0233
        L_0x0211:
            r3 = 3
            goto L_0x0233
        L_0x0213:
            java.lang.String r0 = "alignmentBaseline"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x021c
            goto L_0x0233
        L_0x021c:
            r3 = 2
            goto L_0x0233
        L_0x021e:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0227
            goto L_0x0233
        L_0x0227:
            r3 = r1
            goto L_0x0233
        L_0x0229:
            java.lang.String r0 = "lengthAdjust"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0232
            goto L_0x0233
        L_0x0232:
            r3 = r2
        L_0x0233:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x0528;
                case 1: goto L_0x0519;
                case 2: goto L_0x050b;
                case 3: goto L_0x04fd;
                case 4: goto L_0x04f3;
                case 5: goto L_0x04e9;
                case 6: goto L_0x04db;
                case 7: goto L_0x04cd;
                case 8: goto L_0x04c2;
                case 9: goto L_0x04b7;
                case 10: goto L_0x048d;
                case 11: goto L_0x047b;
                case 12: goto L_0x0469;
                case 13: goto L_0x045a;
                case 14: goto L_0x0448;
                case 15: goto L_0x0436;
                case 16: goto L_0x042b;
                case 17: goto L_0x0420;
                case 18: goto L_0x0415;
                case 19: goto L_0x040a;
                case 20: goto L_0x03ff;
                case 21: goto L_0x03f4;
                case 22: goto L_0x03e5;
                case 23: goto L_0x03d6;
                case 24: goto L_0x03c4;
                case 25: goto L_0x03b2;
                case 26: goto L_0x03a3;
                case 27: goto L_0x0379;
                case 28: goto L_0x034f;
                case 29: goto L_0x0331;
                case 30: goto L_0x0307;
                case 31: goto L_0x02f8;
                case 32: goto L_0x02e6;
                case 33: goto L_0x02d7;
                case 34: goto L_0x02c5;
                case 35: goto L_0x029b;
                case 36: goto L_0x028c;
                case 37: goto L_0x027a;
                case 38: goto L_0x0268;
                case 39: goto L_0x023f;
                default: goto L_0x023a;
            }
        L_0x023a:
            super.setProperty(r7, r8, r9)
            goto L_0x0535
        L_0x023f:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x024e
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0535
        L_0x024e:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x025d
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x0535
        L_0x025d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0535
        L_0x0268:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x026f
            goto L_0x0275
        L_0x026f:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x0275:
            r8.setResponsible(r7, r2)
            goto L_0x0535
        L_0x027a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x0281
            goto L_0x0287
        L_0x0281:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0287:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x0535
        L_0x028c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x0293
            goto L_0x0296
        L_0x0293:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0296:
            r8.setDisplay(r7, r5)
            goto L_0x0535
        L_0x029b:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02aa
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setTextLength(r7, (java.lang.String) r9)
            goto L_0x0535
        L_0x02aa:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02b9
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setTextLength(r7, (java.lang.Double) r9)
            goto L_0x0535
        L_0x02b9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setTextLength(r7, (java.lang.Double) r5)
            goto L_0x0535
        L_0x02c5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x02cc
            goto L_0x02d2
        L_0x02cc:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02d2:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x0535
        L_0x02d7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x02de
            goto L_0x02e1
        L_0x02de:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02e1:
            r8.setContent(r7, r5)
            goto L_0x0535
        L_0x02e6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x02ed
            goto L_0x02f3
        L_0x02ed:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02f3:
            r8.setClipRule(r7, r2)
            goto L_0x0535
        L_0x02f8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x02ff
            goto L_0x0302
        L_0x02ff:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0302:
            r8.setClipPath(r7, r5)
            goto L_0x0535
        L_0x0307:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0316
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setInlineSize(r7, (java.lang.String) r9)
            goto L_0x0535
        L_0x0316:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0325
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setInlineSize(r7, (java.lang.Double) r9)
            goto L_0x0535
        L_0x0325:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setInlineSize(r7, (java.lang.Double) r5)
            goto L_0x0535
        L_0x0331:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0340
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x0535
        L_0x0340:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x0535
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x0535
        L_0x034f:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x035e
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x0535
        L_0x035e:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x036d
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x0535
        L_0x036d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x0535
        L_0x0379:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0388
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setBaselineShift(r7, (java.lang.String) r9)
            goto L_0x0535
        L_0x0388:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0397
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setBaselineShift(r7, (java.lang.Double) r9)
            goto L_0x0535
        L_0x0397:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setBaselineShift(r7, (java.lang.Double) r5)
            goto L_0x0535
        L_0x03a3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x03aa
            goto L_0x03ad
        L_0x03aa:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03ad:
            r8.setMarkerStart(r7, r5)
            goto L_0x0535
        L_0x03b2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x03b9
            goto L_0x03bf
        L_0x03b9:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x03bf:
            r8.setVectorEffect(r7, r2)
            goto L_0x0535
        L_0x03c4:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x03cb
            goto L_0x03d1
        L_0x03cb:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x03d1:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x0535
        L_0x03d6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x03dd
            goto L_0x03e0
        L_0x03dd:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03e0:
            r8.setName(r7, r5)
            goto L_0x0535
        L_0x03e5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x03ec
            goto L_0x03ef
        L_0x03ec:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03ef:
            r8.setMask(r7, r5)
            goto L_0x0535
        L_0x03f4:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x0535
        L_0x03ff:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x0535
        L_0x040a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setDy(r7, r9)
            goto L_0x0535
        L_0x0415:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setDx(r7, r9)
            goto L_0x0535
        L_0x0420:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setY(r7, r9)
            goto L_0x0535
        L_0x042b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setX(r7, r9)
            goto L_0x0535
        L_0x0436:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x043d
            goto L_0x0443
        L_0x043d:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x0443:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x0535
        L_0x0448:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x044f
            goto L_0x0455
        L_0x044f:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0455:
            r8.setFillOpacity(r7, r4)
            goto L_0x0535
        L_0x045a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x0461
            goto L_0x0464
        L_0x0461:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0464:
            r8.setPointerEvents(r7, r5)
            goto L_0x0535
        L_0x0469:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x0470
            goto L_0x0476
        L_0x0470:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0476:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x0535
        L_0x047b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x0482
            goto L_0x0488
        L_0x0482:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x0488:
            r8.setFillRule(r7, r1)
            goto L_0x0535
        L_0x048d:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x049c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x0535
        L_0x049c:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x04ab
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x0535
        L_0x04ab:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x0535
        L_0x04b7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x0535
        L_0x04c2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setRotate(r7, r9)
            goto L_0x0535
        L_0x04cd:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x04d4
            goto L_0x04d7
        L_0x04d4:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04d7:
            r8.setMarkerMid(r7, r5)
            goto L_0x0535
        L_0x04db:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x04e2
            goto L_0x04e5
        L_0x04e2:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04e5:
            r8.setMarkerEnd(r7, r5)
            goto L_0x0535
        L_0x04e9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x0535
        L_0x04f3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x0535
        L_0x04fd:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x0504
            goto L_0x0507
        L_0x0504:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0507:
            r8.setVerticalAlign(r7, r5)
            goto L_0x0535
        L_0x050b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x0512
            goto L_0x0515
        L_0x0512:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0515:
            r8.setAlignmentBaseline(r7, r5)
            goto L_0x0535
        L_0x0519:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x051e
            goto L_0x0524
        L_0x051e:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0524:
            r8.setOpacity(r7, r4)
            goto L_0x0535
        L_0x0528:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r8
            if (r9 != 0) goto L_0x052f
            goto L_0x0532
        L_0x052f:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0532:
            r8.setLengthAdjust(r7, r5)
        L_0x0535:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGTSpanManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
