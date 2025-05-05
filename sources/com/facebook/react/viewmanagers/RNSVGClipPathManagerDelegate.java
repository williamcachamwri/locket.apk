package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface;

public class RNSVGClipPathManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGClipPathManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGClipPathManagerDelegate(U u) {
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
                case -1267206133: goto L_0x017d;
                case -1081239615: goto L_0x0172;
                case -993894751: goto L_0x0167;
                case -933864895: goto L_0x015c;
                case -933857362: goto L_0x0151;
                case -891980232: goto L_0x0145;
                case -734428249: goto L_0x013a;
                case -729118945: goto L_0x012f;
                case -416535885: goto L_0x0120;
                case -293492298: goto L_0x0112;
                case -53677816: goto L_0x0104;
                case -44578051: goto L_0x00f5;
                case 3143043: goto L_0x00e7;
                case 3148879: goto L_0x00d9;
                case 3344108: goto L_0x00cb;
                case 3373707: goto L_0x00bd;
                case 78845486: goto L_0x00ae;
                case 104482996: goto L_0x009f;
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
            goto L_0x0187
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x0187
        L_0x001a:
            r3 = 27
            goto L_0x0187
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x0187
        L_0x0028:
            r3 = 26
            goto L_0x0187
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x0187
        L_0x0037:
            r3 = 25
            goto L_0x0187
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0187
        L_0x0045:
            r3 = 24
            goto L_0x0187
        L_0x0049:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x0187
        L_0x0054:
            r3 = 23
            goto L_0x0187
        L_0x0058:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x0187
        L_0x0062:
            r3 = 22
            goto L_0x0187
        L_0x0066:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x0187
        L_0x0070:
            r3 = 21
            goto L_0x0187
        L_0x0074:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x0187
        L_0x007f:
            r3 = 20
            goto L_0x0187
        L_0x0083:
            java.lang.String r0 = "fontSize"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x0187
        L_0x008d:
            r3 = 19
            goto L_0x0187
        L_0x0091:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009b
            goto L_0x0187
        L_0x009b:
            r3 = 18
            goto L_0x0187
        L_0x009f:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00aa
            goto L_0x0187
        L_0x00aa:
            r3 = 17
            goto L_0x0187
        L_0x00ae:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00b9
            goto L_0x0187
        L_0x00b9:
            r3 = 16
            goto L_0x0187
        L_0x00bd:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c7
            goto L_0x0187
        L_0x00c7:
            r3 = 15
            goto L_0x0187
        L_0x00cb:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d5
            goto L_0x0187
        L_0x00d5:
            r3 = 14
            goto L_0x0187
        L_0x00d9:
            java.lang.String r0 = "font"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e3
            goto L_0x0187
        L_0x00e3:
            r3 = 13
            goto L_0x0187
        L_0x00e7:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f1
            goto L_0x0187
        L_0x00f1:
            r3 = 12
            goto L_0x0187
        L_0x00f5:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x0187
        L_0x0100:
            r3 = 11
            goto L_0x0187
        L_0x0104:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010e
            goto L_0x0187
        L_0x010e:
            r3 = 10
            goto L_0x0187
        L_0x0112:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011c
            goto L_0x0187
        L_0x011c:
            r3 = 9
            goto L_0x0187
        L_0x0120:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012b
            goto L_0x0187
        L_0x012b:
            r3 = 8
            goto L_0x0187
        L_0x012f:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0138
            goto L_0x0187
        L_0x0138:
            r3 = 7
            goto L_0x0187
        L_0x013a:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0143
            goto L_0x0187
        L_0x0143:
            r3 = 6
            goto L_0x0187
        L_0x0145:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x014f
            goto L_0x0187
        L_0x014f:
            r3 = 5
            goto L_0x0187
        L_0x0151:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x015a
            goto L_0x0187
        L_0x015a:
            r3 = 4
            goto L_0x0187
        L_0x015c:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0165
            goto L_0x0187
        L_0x0165:
            r3 = 3
            goto L_0x0187
        L_0x0167:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0170
            goto L_0x0187
        L_0x0170:
            r3 = 2
            goto L_0x0187
        L_0x0172:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x017b
            goto L_0x0187
        L_0x017b:
            r3 = r1
            goto L_0x0187
        L_0x017d:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0186
            goto L_0x0187
        L_0x0186:
            r3 = r2
        L_0x0187:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x0389;
                case 1: goto L_0x037f;
                case 2: goto L_0x0375;
                case 3: goto L_0x0367;
                case 4: goto L_0x0359;
                case 5: goto L_0x034f;
                case 6: goto L_0x0328;
                case 7: goto L_0x0316;
                case 8: goto L_0x0304;
                case 9: goto L_0x02f5;
                case 10: goto L_0x02e3;
                case 11: goto L_0x02d1;
                case 12: goto L_0x02c6;
                case 13: goto L_0x02bb;
                case 14: goto L_0x02ac;
                case 15: goto L_0x029d;
                case 16: goto L_0x028b;
                case 17: goto L_0x0279;
                case 18: goto L_0x026a;
                case 19: goto L_0x0240;
                case 20: goto L_0x0222;
                case 21: goto L_0x0213;
                case 22: goto L_0x0201;
                case 23: goto L_0x01ef;
                case 24: goto L_0x01e0;
                case 25: goto L_0x01ce;
                case 26: goto L_0x01bc;
                case 27: goto L_0x0193;
                default: goto L_0x018e;
            }
        L_0x018e:
            super.setProperty(r7, r8, r9)
            goto L_0x0397
        L_0x0193:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x01a2
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0397
        L_0x01a2:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x01b1
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x0397
        L_0x01b1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0397
        L_0x01bc:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x01c3
            goto L_0x01c9
        L_0x01c3:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x01c9:
            r8.setResponsible(r7, r2)
            goto L_0x0397
        L_0x01ce:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x01d5
            goto L_0x01db
        L_0x01d5:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x01db:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x0397
        L_0x01e0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x01e7
            goto L_0x01ea
        L_0x01e7:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x01ea:
            r8.setDisplay(r7, r5)
            goto L_0x0397
        L_0x01ef:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x01f6
            goto L_0x01fc
        L_0x01f6:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x01fc:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x0397
        L_0x0201:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x0208
            goto L_0x020e
        L_0x0208:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x020e:
            r8.setClipRule(r7, r2)
            goto L_0x0397
        L_0x0213:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x021a
            goto L_0x021d
        L_0x021a:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x021d:
            r8.setClipPath(r7, r5)
            goto L_0x0397
        L_0x0222:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0231
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x0397
        L_0x0231:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x0397
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x0397
        L_0x0240:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x024f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontSize(r7, (java.lang.String) r9)
            goto L_0x0397
        L_0x024f:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x025e
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r9)
            goto L_0x0397
        L_0x025e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontSize(r7, (java.lang.Double) r5)
            goto L_0x0397
        L_0x026a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x0271
            goto L_0x0274
        L_0x0271:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0274:
            r8.setMarkerStart(r7, r5)
            goto L_0x0397
        L_0x0279:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x0280
            goto L_0x0286
        L_0x0280:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0286:
            r8.setVectorEffect(r7, r2)
            goto L_0x0397
        L_0x028b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x0292
            goto L_0x0298
        L_0x0292:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x0298:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x0397
        L_0x029d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x02a4
            goto L_0x02a7
        L_0x02a4:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02a7:
            r8.setName(r7, r5)
            goto L_0x0397
        L_0x02ac:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x02b3
            goto L_0x02b6
        L_0x02b3:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02b6:
            r8.setMask(r7, r5)
            goto L_0x0397
        L_0x02bb:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFont(r7, r9)
            goto L_0x0397
        L_0x02c6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x0397
        L_0x02d1:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x02d8
            goto L_0x02de
        L_0x02d8:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x02de:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x0397
        L_0x02e3:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x02ea
            goto L_0x02f0
        L_0x02ea:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x02f0:
            r8.setFillOpacity(r7, r4)
            goto L_0x0397
        L_0x02f5:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x02fc
            goto L_0x02ff
        L_0x02fc:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02ff:
            r8.setPointerEvents(r7, r5)
            goto L_0x0397
        L_0x0304:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x030b
            goto L_0x0311
        L_0x030b:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0311:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x0397
        L_0x0316:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x031d
            goto L_0x0323
        L_0x031d:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x0323:
            r8.setFillRule(r7, r1)
            goto L_0x0397
        L_0x0328:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0336
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setFontWeight(r7, (java.lang.String) r9)
            goto L_0x0397
        L_0x0336:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0344
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r9)
            goto L_0x0397
        L_0x0344:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setFontWeight(r7, (java.lang.Double) r5)
            goto L_0x0397
        L_0x034f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x0397
        L_0x0359:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x0360
            goto L_0x0363
        L_0x0360:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0363:
            r8.setMarkerMid(r7, r5)
            goto L_0x0397
        L_0x0367:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            if (r9 != 0) goto L_0x036e
            goto L_0x0371
        L_0x036e:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0371:
            r8.setMarkerEnd(r7, r5)
            goto L_0x0397
        L_0x0375:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x0397
        L_0x037f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x0397
        L_0x0389:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x038e
            goto L_0x0394
        L_0x038e:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x0394:
            r8.setOpacity(r7, r4)
        L_0x0397:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGClipPathManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
