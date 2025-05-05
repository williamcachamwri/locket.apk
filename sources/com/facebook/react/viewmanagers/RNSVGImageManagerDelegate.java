package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGImageManagerInterface;

public class RNSVGImageManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGImageManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGImageManagerDelegate(U u) {
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
                case -1267206133: goto L_0x01b9;
                case -1221029593: goto L_0x01ae;
                case -1081239615: goto L_0x01a3;
                case -993894751: goto L_0x0198;
                case -933864895: goto L_0x018d;
                case -933857362: goto L_0x0182;
                case -891980232: goto L_0x0176;
                case -729118945: goto L_0x016b;
                case -416535885: goto L_0x015c;
                case -293492298: goto L_0x014e;
                case -53677816: goto L_0x0140;
                case -44578051: goto L_0x0131;
                case 120: goto L_0x0122;
                case 121: goto L_0x0113;
                case 114148: goto L_0x0104;
                case 3143043: goto L_0x00f6;
                case 3344108: goto L_0x00e8;
                case 3373707: goto L_0x00da;
                case 78845486: goto L_0x00cb;
                case 92903173: goto L_0x00bd;
                case 104482996: goto L_0x00ae;
                case 113126854: goto L_0x009f;
                case 217109576: goto L_0x0091;
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
            goto L_0x01c3
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x01c3
        L_0x001a:
            r3 = 31
            goto L_0x01c3
        L_0x001e:
            java.lang.String r0 = "meetOrSlice"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x01c3
        L_0x0028:
            r3 = 30
            goto L_0x01c3
        L_0x002c:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0036
            goto L_0x01c3
        L_0x0036:
            r3 = 29
            goto L_0x01c3
        L_0x003a:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x01c3
        L_0x0045:
            r3 = 28
            goto L_0x01c3
        L_0x0049:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0053
            goto L_0x01c3
        L_0x0053:
            r3 = 27
            goto L_0x01c3
        L_0x0057:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x01c3
        L_0x0062:
            r3 = 26
            goto L_0x01c3
        L_0x0066:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x01c3
        L_0x0070:
            r3 = 25
            goto L_0x01c3
        L_0x0074:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007e
            goto L_0x01c3
        L_0x007e:
            r3 = 24
            goto L_0x01c3
        L_0x0082:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x01c3
        L_0x008d:
            r3 = 23
            goto L_0x01c3
        L_0x0091:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x01c3
        L_0x009b:
            r3 = 22
            goto L_0x01c3
        L_0x009f:
            java.lang.String r0 = "width"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00aa
            goto L_0x01c3
        L_0x00aa:
            r3 = 21
            goto L_0x01c3
        L_0x00ae:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b9
            goto L_0x01c3
        L_0x00b9:
            r3 = 20
            goto L_0x01c3
        L_0x00bd:
            java.lang.String r0 = "align"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c7
            goto L_0x01c3
        L_0x00c7:
            r3 = 19
            goto L_0x01c3
        L_0x00cb:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d6
            goto L_0x01c3
        L_0x00d6:
            r3 = 18
            goto L_0x01c3
        L_0x00da:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e4
            goto L_0x01c3
        L_0x00e4:
            r3 = 17
            goto L_0x01c3
        L_0x00e8:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x01c3
        L_0x00f2:
            r3 = 16
            goto L_0x01c3
        L_0x00f6:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x01c3
        L_0x0100:
            r3 = 15
            goto L_0x01c3
        L_0x0104:
            java.lang.String r0 = "src"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010f
            goto L_0x01c3
        L_0x010f:
            r3 = 14
            goto L_0x01c3
        L_0x0113:
            java.lang.String r0 = "y"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011e
            goto L_0x01c3
        L_0x011e:
            r3 = 13
            goto L_0x01c3
        L_0x0122:
            java.lang.String r0 = "x"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012d
            goto L_0x01c3
        L_0x012d:
            r3 = 12
            goto L_0x01c3
        L_0x0131:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x013c
            goto L_0x01c3
        L_0x013c:
            r3 = 11
            goto L_0x01c3
        L_0x0140:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x014a
            goto L_0x01c3
        L_0x014a:
            r3 = 10
            goto L_0x01c3
        L_0x014e:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0158
            goto L_0x01c3
        L_0x0158:
            r3 = 9
            goto L_0x01c3
        L_0x015c:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0167
            goto L_0x01c3
        L_0x0167:
            r3 = 8
            goto L_0x01c3
        L_0x016b:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0174
            goto L_0x01c3
        L_0x0174:
            r3 = 7
            goto L_0x01c3
        L_0x0176:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0180
            goto L_0x01c3
        L_0x0180:
            r3 = 6
            goto L_0x01c3
        L_0x0182:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x018b
            goto L_0x01c3
        L_0x018b:
            r3 = 5
            goto L_0x01c3
        L_0x018d:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0196
            goto L_0x01c3
        L_0x0196:
            r3 = 4
            goto L_0x01c3
        L_0x0198:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01a1
            goto L_0x01c3
        L_0x01a1:
            r3 = 3
            goto L_0x01c3
        L_0x01a3:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01ac
            goto L_0x01c3
        L_0x01ac:
            r3 = 2
            goto L_0x01c3
        L_0x01ae:
            java.lang.String r0 = "height"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01b7
            goto L_0x01c3
        L_0x01b7:
            r3 = r1
            goto L_0x01c3
        L_0x01b9:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c2
            goto L_0x01c3
        L_0x01c2:
            r3 = r2
        L_0x01c3:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x043b;
                case 1: goto L_0x0414;
                case 2: goto L_0x040a;
                case 3: goto L_0x0400;
                case 4: goto L_0x03f2;
                case 5: goto L_0x03e4;
                case 6: goto L_0x03d9;
                case 7: goto L_0x03c7;
                case 8: goto L_0x03b5;
                case 9: goto L_0x03a6;
                case 10: goto L_0x0394;
                case 11: goto L_0x0382;
                case 12: goto L_0x0358;
                case 13: goto L_0x032e;
                case 14: goto L_0x0323;
                case 15: goto L_0x0318;
                case 16: goto L_0x0309;
                case 17: goto L_0x02fa;
                case 18: goto L_0x02e8;
                case 19: goto L_0x02d9;
                case 20: goto L_0x02c7;
                case 21: goto L_0x029d;
                case 22: goto L_0x028e;
                case 23: goto L_0x0270;
                case 24: goto L_0x0261;
                case 25: goto L_0x024f;
                case 26: goto L_0x023d;
                case 27: goto L_0x022e;
                case 28: goto L_0x021c;
                case 29: goto L_0x020a;
                case 30: goto L_0x01f8;
                case 31: goto L_0x01cf;
                default: goto L_0x01ca;
            }
        L_0x01ca:
            super.setProperty(r7, r8, r9)
            goto L_0x0449
        L_0x01cf:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x01de
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0449
        L_0x01de:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x01ed
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x0449
        L_0x01ed:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0449
        L_0x01f8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x01ff
            goto L_0x0205
        L_0x01ff:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0205:
            r8.setMeetOrSlice(r7, r2)
            goto L_0x0449
        L_0x020a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0211
            goto L_0x0217
        L_0x0211:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x0217:
            r8.setResponsible(r7, r2)
            goto L_0x0449
        L_0x021c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0223
            goto L_0x0229
        L_0x0223:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0229:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x0449
        L_0x022e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0235
            goto L_0x0238
        L_0x0235:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0238:
            r8.setDisplay(r7, r5)
            goto L_0x0449
        L_0x023d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0244
            goto L_0x024a
        L_0x0244:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x024a:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x0449
        L_0x024f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0256
            goto L_0x025c
        L_0x0256:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x025c:
            r8.setClipRule(r7, r2)
            goto L_0x0449
        L_0x0261:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0268
            goto L_0x026b
        L_0x0268:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x026b:
            r8.setClipPath(r7, r5)
            goto L_0x0449
        L_0x0270:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x027f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x0449
        L_0x027f:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x0449
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x0449
        L_0x028e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0295
            goto L_0x0298
        L_0x0295:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0298:
            r8.setMarkerStart(r7, r5)
            goto L_0x0449
        L_0x029d:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x02ac
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setWidth(r7, (java.lang.String) r9)
            goto L_0x0449
        L_0x02ac:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x02bb
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r9)
            goto L_0x0449
        L_0x02bb:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r5)
            goto L_0x0449
        L_0x02c7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x02ce
            goto L_0x02d4
        L_0x02ce:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02d4:
            r8.setVectorEffect(r7, r2)
            goto L_0x0449
        L_0x02d9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x02e0
            goto L_0x02e3
        L_0x02e0:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02e3:
            r8.setAlign(r7, r5)
            goto L_0x0449
        L_0x02e8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x02ef
            goto L_0x02f5
        L_0x02ef:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x02f5:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x0449
        L_0x02fa:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0301
            goto L_0x0304
        L_0x0301:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0304:
            r8.setName(r7, r5)
            goto L_0x0449
        L_0x0309:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0310
            goto L_0x0313
        L_0x0310:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0313:
            r8.setMask(r7, r5)
            goto L_0x0449
        L_0x0318:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x0449
        L_0x0323:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setSrc(r7, r9)
            goto L_0x0449
        L_0x032e:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x033d
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setY(r7, (java.lang.String) r9)
            goto L_0x0449
        L_0x033d:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x034c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r9)
            goto L_0x0449
        L_0x034c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r5)
            goto L_0x0449
        L_0x0358:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0367
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setX(r7, (java.lang.String) r9)
            goto L_0x0449
        L_0x0367:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0376
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r9)
            goto L_0x0449
        L_0x0376:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r5)
            goto L_0x0449
        L_0x0382:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x0389
            goto L_0x038f
        L_0x0389:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x038f:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x0449
        L_0x0394:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x039b
            goto L_0x03a1
        L_0x039b:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03a1:
            r8.setFillOpacity(r7, r4)
            goto L_0x0449
        L_0x03a6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x03ad
            goto L_0x03b0
        L_0x03ad:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03b0:
            r8.setPointerEvents(r7, r5)
            goto L_0x0449
        L_0x03b5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x03bc
            goto L_0x03c2
        L_0x03bc:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03c2:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x0449
        L_0x03c7:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x03ce
            goto L_0x03d4
        L_0x03ce:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x03d4:
            r8.setFillRule(r7, r1)
            goto L_0x0449
        L_0x03d9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x0449
        L_0x03e4:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x03eb
            goto L_0x03ee
        L_0x03eb:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03ee:
            r8.setMarkerMid(r7, r5)
            goto L_0x0449
        L_0x03f2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            if (r9 != 0) goto L_0x03f9
            goto L_0x03fc
        L_0x03f9:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03fc:
            r8.setMarkerEnd(r7, r5)
            goto L_0x0449
        L_0x0400:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x0449
        L_0x040a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x0449
        L_0x0414:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0422
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setHeight(r7, (java.lang.String) r9)
            goto L_0x0449
        L_0x0422:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0430
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r9)
            goto L_0x0449
        L_0x0430:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGImageManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGImageManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r5)
            goto L_0x0449
        L_0x043b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x0440
            goto L_0x0446
        L_0x0440:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0446:
            r8.setOpacity(r7, r4)
        L_0x0449:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGImageManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
