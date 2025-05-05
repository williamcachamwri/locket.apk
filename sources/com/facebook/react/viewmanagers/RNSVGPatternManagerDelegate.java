package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGPatternManagerInterface;

public class RNSVGPatternManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGPatternManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGPatternManagerDelegate(U u) {
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
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: boolean} */
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
                case -1567958285: goto L_0x0237;
                case -1267206133: goto L_0x022c;
                case -1221029593: goto L_0x0221;
                case -1081239615: goto L_0x0216;
                case -993894751: goto L_0x020b;
                case -933864895: goto L_0x0200;
                case -933857362: goto L_0x01f5;
                case -891980232: goto L_0x01e9;
                case -734428249: goto L_0x01db;
                case -729118945: goto L_0x01cd;
                case -416535885: goto L_0x01be;
                case -293492298: goto L_0x01b0;
                case -207800897: goto L_0x01a2;
                case -128680410: goto L_0x0194;
                case -53677816: goto L_0x0186;
                case -44578051: goto L_0x0177;
                case 120: goto L_0x0168;
                case 121: goto L_0x0159;
                case 3143043: goto L_0x014b;
                case 3148879: goto L_0x013d;
                case 3344108: goto L_0x012f;
                case 3351622: goto L_0x0121;
                case 3351623: goto L_0x0113;
                case 3373707: goto L_0x0105;
                case 78845486: goto L_0x00f6;
                case 92903173: goto L_0x00e8;
                case 104482996: goto L_0x00d9;
                case 113126854: goto L_0x00ca;
                case 217109576: goto L_0x00bc;
                case 240482938: goto L_0x00ad;
                case 365601008: goto L_0x009f;
                case 401643183: goto L_0x0090;
                case 746561980: goto L_0x0082;
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
            goto L_0x0242
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x0242
        L_0x001a:
            r3 = 40
            goto L_0x0242
        L_0x001e:
            java.lang.String r0 = "meetOrSlice"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x0242
        L_0x0028:
            r3 = 39
            goto L_0x0242
        L_0x002c:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0036
            goto L_0x0242
        L_0x0036:
            r3 = 38
            goto L_0x0242
        L_0x003a:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0242
        L_0x0045:
            r3 = 37
            goto L_0x0242
        L_0x0049:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0053
            goto L_0x0242
        L_0x0053:
            r3 = 36
            goto L_0x0242
        L_0x0057:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x0242
        L_0x0062:
            r3 = 35
            goto L_0x0242
        L_0x0066:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x0242
        L_0x0070:
            r3 = 34
            goto L_0x0242
        L_0x0074:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007e
            goto L_0x0242
        L_0x007e:
            r3 = 33
            goto L_0x0242
        L_0x0082:
            java.lang.String r0 = "patternTransform"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008c
            goto L_0x0242
        L_0x008c:
            r3 = 32
            goto L_0x0242
        L_0x0090:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x0242
        L_0x009b:
            r3 = 31
            goto L_0x0242
        L_0x009f:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00a9
            goto L_0x0242
        L_0x00a9:
            r3 = 30
            goto L_0x0242
        L_0x00ad:
            java.lang.String r0 = "vbWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b8
            goto L_0x0242
        L_0x00b8:
            r3 = 29
            goto L_0x0242
        L_0x00bc:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c6
            goto L_0x0242
        L_0x00c6:
            r3 = 28
            goto L_0x0242
        L_0x00ca:
            java.lang.String r0 = "width"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d5
            goto L_0x0242
        L_0x00d5:
            r3 = 27
            goto L_0x0242
        L_0x00d9:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e4
            goto L_0x0242
        L_0x00e4:
            r3 = 26
            goto L_0x0242
        L_0x00e8:
            java.lang.String r0 = "align"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x0242
        L_0x00f2:
            r3 = 25
            goto L_0x0242
        L_0x00f6:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0101
            goto L_0x0242
        L_0x0101:
            r3 = 24
            goto L_0x0242
        L_0x0105:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010f
            goto L_0x0242
        L_0x010f:
            r3 = 23
            goto L_0x0242
        L_0x0113:
            java.lang.String r0 = "minY"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011d
            goto L_0x0242
        L_0x011d:
            r3 = 22
            goto L_0x0242
        L_0x0121:
            java.lang.String r0 = "minX"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012b
            goto L_0x0242
        L_0x012b:
            r3 = 21
            goto L_0x0242
        L_0x012f:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0139
            goto L_0x0242
        L_0x0139:
            r3 = 20
            goto L_0x0242
        L_0x013d:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0147
            goto L_0x0242
        L_0x0147:
            r3 = 19
            goto L_0x0242
        L_0x014b:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0155
            goto L_0x0242
        L_0x0155:
            r3 = 18
            goto L_0x0242
        L_0x0159:
            java.lang.String r0 = "y"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0164
            goto L_0x0242
        L_0x0164:
            r3 = 17
            goto L_0x0242
        L_0x0168:
            java.lang.String r0 = "x"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0173
            goto L_0x0242
        L_0x0173:
            r3 = 16
            goto L_0x0242
        L_0x0177:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0182
            goto L_0x0242
        L_0x0182:
            r3 = 15
            goto L_0x0242
        L_0x0186:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0190
            goto L_0x0242
        L_0x0190:
            r3 = 14
            goto L_0x0242
        L_0x0194:
            java.lang.String r0 = "patternContentUnits"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x019e
            goto L_0x0242
        L_0x019e:
            r3 = 13
            goto L_0x0242
        L_0x01a2:
            java.lang.String r0 = "patternUnits"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ac
            goto L_0x0242
        L_0x01ac:
            r3 = 12
            goto L_0x0242
        L_0x01b0:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ba
            goto L_0x0242
        L_0x01ba:
            r3 = 11
            goto L_0x0242
        L_0x01be:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c9
            goto L_0x0242
        L_0x01c9:
            r3 = 10
            goto L_0x0242
        L_0x01cd:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01d7
            goto L_0x0242
        L_0x01d7:
            r3 = 9
            goto L_0x0242
        L_0x01db:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01e5
            goto L_0x0242
        L_0x01e5:
            r3 = 8
            goto L_0x0242
        L_0x01e9:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01f3
            goto L_0x0242
        L_0x01f3:
            r3 = 7
            goto L_0x0242
        L_0x01f5:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01fe
            goto L_0x0242
        L_0x01fe:
            r3 = 6
            goto L_0x0242
        L_0x0200:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0209
            goto L_0x0242
        L_0x0209:
            r3 = 5
            goto L_0x0242
        L_0x020b:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0214
            goto L_0x0242
        L_0x0214:
            r3 = 4
            goto L_0x0242
        L_0x0216:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x021f
            goto L_0x0242
        L_0x021f:
            r3 = 3
            goto L_0x0242
        L_0x0221:
            java.lang.String r0 = "height"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x022a
            goto L_0x0242
        L_0x022a:
            r3 = 2
            goto L_0x0242
        L_0x022c:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0235
            goto L_0x0242
        L_0x0235:
            r3 = r1
            goto L_0x0242
        L_0x0237:
            java.lang.String r0 = "vbHeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0241
            goto L_0x0242
        L_0x0241:
            r3 = r2
        L_0x0242:
            r0 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            r5 = 0
            switch(r3) {
                case 0: goto L_0x0583;
                case 1: goto L_0x0574;
                case 2: goto L_0x054d;
                case 3: goto L_0x0543;
                case 4: goto L_0x0539;
                case 5: goto L_0x052b;
                case 6: goto L_0x051c;
                case 7: goto L_0x0511;
                case 8: goto L_0x04e7;
                case 9: goto L_0x04d5;
                case 10: goto L_0x04c3;
                case 11: goto L_0x04b4;
                case 12: goto L_0x04a2;
                case 13: goto L_0x0490;
                case 14: goto L_0x047e;
                case 15: goto L_0x046c;
                case 16: goto L_0x0442;
                case 17: goto L_0x0418;
                case 18: goto L_0x040d;
                case 19: goto L_0x0402;
                case 20: goto L_0x03f3;
                case 21: goto L_0x03e1;
                case 22: goto L_0x03cf;
                case 23: goto L_0x03c0;
                case 24: goto L_0x03ae;
                case 25: goto L_0x039f;
                case 26: goto L_0x038d;
                case 27: goto L_0x0363;
                case 28: goto L_0x0354;
                case 29: goto L_0x0342;
                case 30: goto L_0x0318;
                case 31: goto L_0x02fa;
                case 32: goto L_0x02ef;
                case 33: goto L_0x02e0;
                case 34: goto L_0x02ce;
                case 35: goto L_0x02bc;
                case 36: goto L_0x02ad;
                case 37: goto L_0x029b;
                case 38: goto L_0x0289;
                case 39: goto L_0x0277;
                case 40: goto L_0x024e;
                default: goto L_0x0249;
            }
        L_0x0249:
            super.setProperty(r7, r8, r9)
            goto L_0x0593
        L_0x024e:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x025d
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x025d:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x026c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x0593
        L_0x026c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x0277:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x027e
            goto L_0x0284
        L_0x027e:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0284:
            r8.setMeetOrSlice(r7, r2)
            goto L_0x0593
        L_0x0289:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x0290
            goto L_0x0296
        L_0x0290:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x0296:
            r8.setResponsible(r7, r2)
            goto L_0x0593
        L_0x029b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x02a2
            goto L_0x02a8
        L_0x02a2:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02a8:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x0593
        L_0x02ad:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x02b4
            goto L_0x02b7
        L_0x02b4:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02b7:
            r8.setDisplay(r7, r5)
            goto L_0x0593
        L_0x02bc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x02c3
            goto L_0x02c9
        L_0x02c3:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02c9:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x0593
        L_0x02ce:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x02d5
            goto L_0x02db
        L_0x02d5:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02db:
            r8.setClipRule(r7, r2)
            goto L_0x0593
        L_0x02e0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x02e7
            goto L_0x02ea
        L_0x02e7:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02ea:
            r8.setClipPath(r7, r5)
            goto L_0x0593
        L_0x02ef:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPatternTransform(r7, r9)
            goto L_0x0593
        L_0x02fa:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0309
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x0309:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x0593
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x0593
        L_0x0318:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0327
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x0327:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0336
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x0593
        L_0x0336:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x0593
        L_0x0342:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x0349
            goto L_0x034f
        L_0x0349:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x034f:
            r8.setVbWidth(r7, r4)
            goto L_0x0593
        L_0x0354:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x035b
            goto L_0x035e
        L_0x035b:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x035e:
            r8.setMarkerStart(r7, r5)
            goto L_0x0593
        L_0x0363:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0372
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setWidth(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x0372:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0381
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r9)
            goto L_0x0593
        L_0x0381:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r5)
            goto L_0x0593
        L_0x038d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x0394
            goto L_0x039a
        L_0x0394:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x039a:
            r8.setVectorEffect(r7, r2)
            goto L_0x0593
        L_0x039f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x03a6
            goto L_0x03a9
        L_0x03a6:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03a9:
            r8.setAlign(r7, r5)
            goto L_0x0593
        L_0x03ae:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x03b5
            goto L_0x03bb
        L_0x03b5:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03bb:
            r8.setStrokeMiterlimit(r7, r4)
            goto L_0x0593
        L_0x03c0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x03c7
            goto L_0x03ca
        L_0x03c7:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03ca:
            r8.setName(r7, r5)
            goto L_0x0593
        L_0x03cf:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x03d6
            goto L_0x03dc
        L_0x03d6:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03dc:
            r8.setMinY(r7, r4)
            goto L_0x0593
        L_0x03e1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x03e8
            goto L_0x03ee
        L_0x03e8:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03ee:
            r8.setMinX(r7, r4)
            goto L_0x0593
        L_0x03f3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x03fa
            goto L_0x03fd
        L_0x03fa:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03fd:
            r8.setMask(r7, r5)
            goto L_0x0593
        L_0x0402:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x0593
        L_0x040d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x0593
        L_0x0418:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0427
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setY(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x0427:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0436
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r9)
            goto L_0x0593
        L_0x0436:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r5)
            goto L_0x0593
        L_0x0442:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0451
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setX(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x0451:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0460
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r9)
            goto L_0x0593
        L_0x0460:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r5)
            goto L_0x0593
        L_0x046c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x0473
            goto L_0x0479
        L_0x0473:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0479:
            r8.setStrokeDashoffset(r7, r4)
            goto L_0x0593
        L_0x047e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x0485
            goto L_0x048b
        L_0x0485:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x048b:
            r8.setFillOpacity(r7, r0)
            goto L_0x0593
        L_0x0490:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x0497
            goto L_0x049d
        L_0x0497:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x049d:
            r8.setPatternContentUnits(r7, r2)
            goto L_0x0593
        L_0x04a2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x04a9
            goto L_0x04af
        L_0x04a9:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x04af:
            r8.setPatternUnits(r7, r2)
            goto L_0x0593
        L_0x04b4:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x04bb
            goto L_0x04be
        L_0x04bb:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04be:
            r8.setPointerEvents(r7, r5)
            goto L_0x0593
        L_0x04c3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x04ca
            goto L_0x04d0
        L_0x04ca:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x04d0:
            r8.setStrokeOpacity(r7, r0)
            goto L_0x0593
        L_0x04d5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x04dc
            goto L_0x04e2
        L_0x04dc:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x04e2:
            r8.setFillRule(r7, r1)
            goto L_0x0593
        L_0x04e7:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x04f6
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x04f6:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0505
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x0593
        L_0x0505:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x0593
        L_0x0511:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x0593
        L_0x051c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x0523
            goto L_0x0526
        L_0x0523:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0526:
            r8.setMarkerMid(r7, r5)
            goto L_0x0593
        L_0x052b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x0532
            goto L_0x0535
        L_0x0532:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0535:
            r8.setMarkerEnd(r7, r5)
            goto L_0x0593
        L_0x0539:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x0593
        L_0x0543:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x0593
        L_0x054d:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x055b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setHeight(r7, (java.lang.String) r9)
            goto L_0x0593
        L_0x055b:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0569
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r9)
            goto L_0x0593
        L_0x0569:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r5)
            goto L_0x0593
        L_0x0574:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x0579
            goto L_0x057f
        L_0x0579:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x057f:
            r8.setOpacity(r7, r0)
            goto L_0x0593
        L_0x0583:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r8
            if (r9 != 0) goto L_0x058a
            goto L_0x0590
        L_0x058a:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0590:
            r8.setVbHeight(r7, r4)
        L_0x0593:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGPatternManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
