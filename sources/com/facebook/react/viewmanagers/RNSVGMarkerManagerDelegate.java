package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface;

public class RNSVGMarkerManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGMarkerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGMarkerManagerDelegate(U u) {
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
                case -1567958285: goto L_0x0226;
                case -1267206133: goto L_0x021b;
                case -1081239615: goto L_0x0210;
                case -1008621499: goto L_0x0205;
                case -993894751: goto L_0x01fa;
                case -933864895: goto L_0x01ef;
                case -933857362: goto L_0x01e4;
                case -891980232: goto L_0x01d8;
                case -734428249: goto L_0x01ca;
                case -729118945: goto L_0x01bc;
                case -416535885: goto L_0x01ad;
                case -293492298: goto L_0x019f;
                case -53677816: goto L_0x0191;
                case -44578051: goto L_0x0182;
                case 3143043: goto L_0x0174;
                case 3148879: goto L_0x0166;
                case 3344108: goto L_0x0158;
                case 3351622: goto L_0x014a;
                case 3351623: goto L_0x013c;
                case 3373707: goto L_0x012e;
                case 3496485: goto L_0x0120;
                case 3496486: goto L_0x0112;
                case 78845486: goto L_0x0103;
                case 92903173: goto L_0x00f5;
                case 104482996: goto L_0x00e6;
                case 217109576: goto L_0x00d8;
                case 218785621: goto L_0x00ca;
                case 220478892: goto L_0x00bc;
                case 240482938: goto L_0x00ad;
                case 365601008: goto L_0x009f;
                case 401643183: goto L_0x0090;
                case 917656469: goto L_0x0082;
                case 917735020: goto L_0x0074;
                case 1027575302: goto L_0x0065;
                case 1671764162: goto L_0x0057;
                case 1790285174: goto L_0x0048;
                case 1847674614: goto L_0x003a;
                case 1908075304: goto L_0x002c;
                case 1924065902: goto L_0x001d;
                case 2106883585: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0231
        L_0x000f:
            java.lang.String r0 = "markerHeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0019
            goto L_0x0231
        L_0x0019:
            r3 = 39
            goto L_0x0231
        L_0x001d:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x0231
        L_0x0028:
            r3 = 38
            goto L_0x0231
        L_0x002c:
            java.lang.String r0 = "meetOrSlice"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0036
            goto L_0x0231
        L_0x0036:
            r3 = 37
            goto L_0x0231
        L_0x003a:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0044
            goto L_0x0231
        L_0x0044:
            r3 = 36
            goto L_0x0231
        L_0x0048:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0053
            goto L_0x0231
        L_0x0053:
            r3 = 35
            goto L_0x0231
        L_0x0057:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0061
            goto L_0x0231
        L_0x0061:
            r3 = 34
            goto L_0x0231
        L_0x0065:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x0231
        L_0x0070:
            r3 = 33
            goto L_0x0231
        L_0x0074:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007e
            goto L_0x0231
        L_0x007e:
            r3 = 32
            goto L_0x0231
        L_0x0082:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008c
            goto L_0x0231
        L_0x008c:
            r3 = 31
            goto L_0x0231
        L_0x0090:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x0231
        L_0x009b:
            r3 = 30
            goto L_0x0231
        L_0x009f:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00a9
            goto L_0x0231
        L_0x00a9:
            r3 = 29
            goto L_0x0231
        L_0x00ad:
            java.lang.String r0 = "vbWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b8
            goto L_0x0231
        L_0x00b8:
            r3 = 28
            goto L_0x0231
        L_0x00bc:
            java.lang.String r0 = "markerWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c6
            goto L_0x0231
        L_0x00c6:
            r3 = 27
            goto L_0x0231
        L_0x00ca:
            java.lang.String r0 = "markerUnits"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d4
            goto L_0x0231
        L_0x00d4:
            r3 = 26
            goto L_0x0231
        L_0x00d8:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e2
            goto L_0x0231
        L_0x00e2:
            r3 = 25
            goto L_0x0231
        L_0x00e6:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f1
            goto L_0x0231
        L_0x00f1:
            r3 = 24
            goto L_0x0231
        L_0x00f5:
            java.lang.String r0 = "align"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00ff
            goto L_0x0231
        L_0x00ff:
            r3 = 23
            goto L_0x0231
        L_0x0103:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010e
            goto L_0x0231
        L_0x010e:
            r3 = 22
            goto L_0x0231
        L_0x0112:
            java.lang.String r0 = "refY"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011c
            goto L_0x0231
        L_0x011c:
            r3 = 21
            goto L_0x0231
        L_0x0120:
            java.lang.String r0 = "refX"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012a
            goto L_0x0231
        L_0x012a:
            r3 = 20
            goto L_0x0231
        L_0x012e:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0138
            goto L_0x0231
        L_0x0138:
            r3 = 19
            goto L_0x0231
        L_0x013c:
            java.lang.String r0 = "minY"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0146
            goto L_0x0231
        L_0x0146:
            r3 = 18
            goto L_0x0231
        L_0x014a:
            java.lang.String r0 = "minX"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0154
            goto L_0x0231
        L_0x0154:
            r3 = 17
            goto L_0x0231
        L_0x0158:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0162
            goto L_0x0231
        L_0x0162:
            r3 = 16
            goto L_0x0231
        L_0x0166:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0170
            goto L_0x0231
        L_0x0170:
            r3 = 15
            goto L_0x0231
        L_0x0174:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x017e
            goto L_0x0231
        L_0x017e:
            r3 = 14
            goto L_0x0231
        L_0x0182:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x018d
            goto L_0x0231
        L_0x018d:
            r3 = 13
            goto L_0x0231
        L_0x0191:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x019b
            goto L_0x0231
        L_0x019b:
            r3 = 12
            goto L_0x0231
        L_0x019f:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01a9
            goto L_0x0231
        L_0x01a9:
            r3 = 11
            goto L_0x0231
        L_0x01ad:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01b8
            goto L_0x0231
        L_0x01b8:
            r3 = 10
            goto L_0x0231
        L_0x01bc:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c6
            goto L_0x0231
        L_0x01c6:
            r3 = 9
            goto L_0x0231
        L_0x01ca:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01d4
            goto L_0x0231
        L_0x01d4:
            r3 = 8
            goto L_0x0231
        L_0x01d8:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01e2
            goto L_0x0231
        L_0x01e2:
            r3 = 7
            goto L_0x0231
        L_0x01e4:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ed
            goto L_0x0231
        L_0x01ed:
            r3 = 6
            goto L_0x0231
        L_0x01ef:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01f8
            goto L_0x0231
        L_0x01f8:
            r3 = 5
            goto L_0x0231
        L_0x01fa:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0203
            goto L_0x0231
        L_0x0203:
            r3 = 4
            goto L_0x0231
        L_0x0205:
            java.lang.String r0 = "orient"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x020e
            goto L_0x0231
        L_0x020e:
            r3 = 3
            goto L_0x0231
        L_0x0210:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0219
            goto L_0x0231
        L_0x0219:
            r3 = 2
            goto L_0x0231
        L_0x021b:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0224
            goto L_0x0231
        L_0x0224:
            r3 = r1
            goto L_0x0231
        L_0x0226:
            java.lang.String r0 = "vbHeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0230
            goto L_0x0231
        L_0x0230:
            r3 = r2
        L_0x0231:
            r0 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            r5 = 0
            switch(r3) {
                case 0: goto L_0x0561;
                case 1: goto L_0x0552;
                case 2: goto L_0x0548;
                case 3: goto L_0x053a;
                case 4: goto L_0x0530;
                case 5: goto L_0x0522;
                case 6: goto L_0x0514;
                case 7: goto L_0x050a;
                case 8: goto L_0x04e0;
                case 9: goto L_0x04ce;
                case 10: goto L_0x04bc;
                case 11: goto L_0x04ad;
                case 12: goto L_0x049b;
                case 13: goto L_0x0489;
                case 14: goto L_0x047e;
                case 15: goto L_0x0473;
                case 16: goto L_0x0464;
                case 17: goto L_0x0452;
                case 18: goto L_0x0440;
                case 19: goto L_0x0431;
                case 20: goto L_0x0407;
                case 21: goto L_0x03dd;
                case 22: goto L_0x03cb;
                case 23: goto L_0x03bc;
                case 24: goto L_0x03aa;
                case 25: goto L_0x039b;
                case 26: goto L_0x038c;
                case 27: goto L_0x0362;
                case 28: goto L_0x0350;
                case 29: goto L_0x0326;
                case 30: goto L_0x0308;
                case 31: goto L_0x02f9;
                case 32: goto L_0x02e7;
                case 33: goto L_0x02d5;
                case 34: goto L_0x02c6;
                case 35: goto L_0x02b4;
                case 36: goto L_0x02a2;
                case 37: goto L_0x0290;
                case 38: goto L_0x0267;
                case 39: goto L_0x023d;
                default: goto L_0x0238;
            }
        L_0x0238:
            super.setProperty(r7, r8, r9)
            goto L_0x0571
        L_0x023d:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x024c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setMarkerHeight(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x024c:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x025b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setMarkerHeight(r7, (java.lang.Double) r9)
            goto L_0x0571
        L_0x025b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setMarkerHeight(r7, (java.lang.Double) r5)
            goto L_0x0571
        L_0x0267:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0276
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x0276:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0285
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x0571
        L_0x0285:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x0290:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0297
            goto L_0x029d
        L_0x0297:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x029d:
            r8.setMeetOrSlice(r7, r2)
            goto L_0x0571
        L_0x02a2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x02a9
            goto L_0x02af
        L_0x02a9:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x02af:
            r8.setResponsible(r7, r2)
            goto L_0x0571
        L_0x02b4:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x02bb
            goto L_0x02c1
        L_0x02bb:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02c1:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x0571
        L_0x02c6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x02cd
            goto L_0x02d0
        L_0x02cd:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02d0:
            r8.setDisplay(r7, r5)
            goto L_0x0571
        L_0x02d5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x02dc
            goto L_0x02e2
        L_0x02dc:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02e2:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x0571
        L_0x02e7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x02ee
            goto L_0x02f4
        L_0x02ee:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02f4:
            r8.setClipRule(r7, r2)
            goto L_0x0571
        L_0x02f9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0300
            goto L_0x0303
        L_0x0300:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0303:
            r8.setClipPath(r7, r5)
            goto L_0x0571
        L_0x0308:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0317
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x0317:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x0571
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x0571
        L_0x0326:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0335
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x0335:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0344
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x0571
        L_0x0344:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x0571
        L_0x0350:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0357
            goto L_0x035d
        L_0x0357:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x035d:
            r8.setVbWidth(r7, r4)
            goto L_0x0571
        L_0x0362:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0371
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setMarkerWidth(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x0371:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0380
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setMarkerWidth(r7, (java.lang.Double) r9)
            goto L_0x0571
        L_0x0380:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setMarkerWidth(r7, (java.lang.Double) r5)
            goto L_0x0571
        L_0x038c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0393
            goto L_0x0396
        L_0x0393:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0396:
            r8.setMarkerUnits(r7, r5)
            goto L_0x0571
        L_0x039b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x03a2
            goto L_0x03a5
        L_0x03a2:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03a5:
            r8.setMarkerStart(r7, r5)
            goto L_0x0571
        L_0x03aa:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x03b1
            goto L_0x03b7
        L_0x03b1:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x03b7:
            r8.setVectorEffect(r7, r2)
            goto L_0x0571
        L_0x03bc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x03c3
            goto L_0x03c6
        L_0x03c3:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03c6:
            r8.setAlign(r7, r5)
            goto L_0x0571
        L_0x03cb:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x03d2
            goto L_0x03d8
        L_0x03d2:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03d8:
            r8.setStrokeMiterlimit(r7, r4)
            goto L_0x0571
        L_0x03dd:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x03ec
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setRefY(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x03ec:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x03fb
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRefY(r7, (java.lang.Double) r9)
            goto L_0x0571
        L_0x03fb:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRefY(r7, (java.lang.Double) r5)
            goto L_0x0571
        L_0x0407:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0416
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setRefX(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x0416:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0425
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRefX(r7, (java.lang.Double) r9)
            goto L_0x0571
        L_0x0425:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRefX(r7, (java.lang.Double) r5)
            goto L_0x0571
        L_0x0431:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0438
            goto L_0x043b
        L_0x0438:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x043b:
            r8.setName(r7, r5)
            goto L_0x0571
        L_0x0440:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0447
            goto L_0x044d
        L_0x0447:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x044d:
            r8.setMinY(r7, r4)
            goto L_0x0571
        L_0x0452:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0459
            goto L_0x045f
        L_0x0459:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x045f:
            r8.setMinX(r7, r4)
            goto L_0x0571
        L_0x0464:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x046b
            goto L_0x046e
        L_0x046b:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x046e:
            r8.setMask(r7, r5)
            goto L_0x0571
        L_0x0473:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x0571
        L_0x047e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x0571
        L_0x0489:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0490
            goto L_0x0496
        L_0x0490:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0496:
            r8.setStrokeDashoffset(r7, r4)
            goto L_0x0571
        L_0x049b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x04a2
            goto L_0x04a8
        L_0x04a2:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x04a8:
            r8.setFillOpacity(r7, r0)
            goto L_0x0571
        L_0x04ad:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x04b4
            goto L_0x04b7
        L_0x04b4:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x04b7:
            r8.setPointerEvents(r7, r5)
            goto L_0x0571
        L_0x04bc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x04c3
            goto L_0x04c9
        L_0x04c3:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x04c9:
            r8.setStrokeOpacity(r7, r0)
            goto L_0x0571
        L_0x04ce:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x04d5
            goto L_0x04db
        L_0x04d5:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x04db:
            r8.setFillRule(r7, r1)
            goto L_0x0571
        L_0x04e0:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x04ef
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x0571
        L_0x04ef:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x04fe
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x0571
        L_0x04fe:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x0571
        L_0x050a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x0571
        L_0x0514:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x051b
            goto L_0x051e
        L_0x051b:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x051e:
            r8.setMarkerMid(r7, r5)
            goto L_0x0571
        L_0x0522:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0529
            goto L_0x052c
        L_0x0529:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x052c:
            r8.setMarkerEnd(r7, r5)
            goto L_0x0571
        L_0x0530:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x0571
        L_0x053a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0541
            goto L_0x0544
        L_0x0541:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0544:
            r8.setOrient(r7, r5)
            goto L_0x0571
        L_0x0548:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x0571
        L_0x0552:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x0557
            goto L_0x055d
        L_0x0557:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x055d:
            r8.setOpacity(r7, r0)
            goto L_0x0571
        L_0x0561:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r8
            if (r9 != 0) goto L_0x0568
            goto L_0x056e
        L_0x0568:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x056e:
            r8.setVbHeight(r7, r4)
        L_0x0571:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGMarkerManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
