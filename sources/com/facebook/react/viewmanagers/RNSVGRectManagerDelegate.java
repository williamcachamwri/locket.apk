package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGRectManagerInterface;

public class RNSVGRectManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGRectManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGRectManagerDelegate(U u) {
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
                case -1267206133: goto L_0x01aa;
                case -1221029593: goto L_0x019f;
                case -1081239615: goto L_0x0194;
                case -993894751: goto L_0x0189;
                case -933864895: goto L_0x017e;
                case -933857362: goto L_0x0173;
                case -891980232: goto L_0x0167;
                case -729118945: goto L_0x015c;
                case -416535885: goto L_0x014d;
                case -293492298: goto L_0x013f;
                case -53677816: goto L_0x0131;
                case -44578051: goto L_0x0122;
                case 120: goto L_0x0113;
                case 121: goto L_0x0104;
                case 3654: goto L_0x00f6;
                case 3655: goto L_0x00e8;
                case 3143043: goto L_0x00da;
                case 3344108: goto L_0x00cc;
                case 3373707: goto L_0x00be;
                case 78845486: goto L_0x00af;
                case 104482996: goto L_0x00a0;
                case 113126854: goto L_0x0091;
                case 217109576: goto L_0x0083;
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
            goto L_0x01b4
        L_0x000f:
            java.lang.String r0 = "strokeWidth"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x01b4
        L_0x001a:
            r3 = 30
            goto L_0x01b4
        L_0x001e:
            java.lang.String r0 = "responsible"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x01b4
        L_0x0028:
            r3 = 29
            goto L_0x01b4
        L_0x002c:
            java.lang.String r0 = "strokeLinejoin"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x01b4
        L_0x0037:
            r3 = 28
            goto L_0x01b4
        L_0x003b:
            java.lang.String r0 = "display"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x01b4
        L_0x0045:
            r3 = 27
            goto L_0x01b4
        L_0x0049:
            java.lang.String r0 = "strokeLinecap"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0054
            goto L_0x01b4
        L_0x0054:
            r3 = 26
            goto L_0x01b4
        L_0x0058:
            java.lang.String r0 = "clipRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x01b4
        L_0x0062:
            r3 = 25
            goto L_0x01b4
        L_0x0066:
            java.lang.String r0 = "clipPath"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x01b4
        L_0x0070:
            r3 = 24
            goto L_0x01b4
        L_0x0074:
            java.lang.String r0 = "strokeDasharray"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x007f
            goto L_0x01b4
        L_0x007f:
            r3 = 23
            goto L_0x01b4
        L_0x0083:
            java.lang.String r0 = "markerStart"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x008d
            goto L_0x01b4
        L_0x008d:
            r3 = 22
            goto L_0x01b4
        L_0x0091:
            java.lang.String r0 = "width"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x009c
            goto L_0x01b4
        L_0x009c:
            r3 = 21
            goto L_0x01b4
        L_0x00a0:
            java.lang.String r0 = "vectorEffect"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00ab
            goto L_0x01b4
        L_0x00ab:
            r3 = 20
            goto L_0x01b4
        L_0x00af:
            java.lang.String r0 = "strokeMiterlimit"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00ba
            goto L_0x01b4
        L_0x00ba:
            r3 = 19
            goto L_0x01b4
        L_0x00be:
            java.lang.String r0 = "name"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00c8
            goto L_0x01b4
        L_0x00c8:
            r3 = 18
            goto L_0x01b4
        L_0x00cc:
            java.lang.String r0 = "mask"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00d6
            goto L_0x01b4
        L_0x00d6:
            r3 = 17
            goto L_0x01b4
        L_0x00da:
            java.lang.String r0 = "fill"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00e4
            goto L_0x01b4
        L_0x00e4:
            r3 = 16
            goto L_0x01b4
        L_0x00e8:
            java.lang.String r0 = "ry"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x00f2
            goto L_0x01b4
        L_0x00f2:
            r3 = 15
            goto L_0x01b4
        L_0x00f6:
            java.lang.String r0 = "rx"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x01b4
        L_0x0100:
            r3 = 14
            goto L_0x01b4
        L_0x0104:
            java.lang.String r0 = "y"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x010f
            goto L_0x01b4
        L_0x010f:
            r3 = 13
            goto L_0x01b4
        L_0x0113:
            java.lang.String r0 = "x"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x011e
            goto L_0x01b4
        L_0x011e:
            r3 = 12
            goto L_0x01b4
        L_0x0122:
            java.lang.String r0 = "strokeDashoffset"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x012d
            goto L_0x01b4
        L_0x012d:
            r3 = 11
            goto L_0x01b4
        L_0x0131:
            java.lang.String r0 = "fillOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x013b
            goto L_0x01b4
        L_0x013b:
            r3 = 10
            goto L_0x01b4
        L_0x013f:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0149
            goto L_0x01b4
        L_0x0149:
            r3 = 9
            goto L_0x01b4
        L_0x014d:
            java.lang.String r0 = "strokeOpacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0158
            goto L_0x01b4
        L_0x0158:
            r3 = 8
            goto L_0x01b4
        L_0x015c:
            java.lang.String r0 = "fillRule"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0165
            goto L_0x01b4
        L_0x0165:
            r3 = 7
            goto L_0x01b4
        L_0x0167:
            java.lang.String r0 = "stroke"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0171
            goto L_0x01b4
        L_0x0171:
            r3 = 6
            goto L_0x01b4
        L_0x0173:
            java.lang.String r0 = "markerMid"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x017c
            goto L_0x01b4
        L_0x017c:
            r3 = 5
            goto L_0x01b4
        L_0x017e:
            java.lang.String r0 = "markerEnd"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0187
            goto L_0x01b4
        L_0x0187:
            r3 = 4
            goto L_0x01b4
        L_0x0189:
            java.lang.String r0 = "propList"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0192
            goto L_0x01b4
        L_0x0192:
            r3 = 3
            goto L_0x01b4
        L_0x0194:
            java.lang.String r0 = "matrix"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x019d
            goto L_0x01b4
        L_0x019d:
            r3 = 2
            goto L_0x01b4
        L_0x019f:
            java.lang.String r0 = "height"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01a8
            goto L_0x01b4
        L_0x01a8:
            r3 = r1
            goto L_0x01b4
        L_0x01aa:
            java.lang.String r0 = "opacity"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01b3
            goto L_0x01b4
        L_0x01b3:
            r3 = r2
        L_0x01b4:
            r0 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            switch(r3) {
                case 0: goto L_0x0454;
                case 1: goto L_0x042d;
                case 2: goto L_0x0423;
                case 3: goto L_0x0419;
                case 4: goto L_0x040b;
                case 5: goto L_0x03fd;
                case 6: goto L_0x03f2;
                case 7: goto L_0x03e0;
                case 8: goto L_0x03ce;
                case 9: goto L_0x03bf;
                case 10: goto L_0x03ad;
                case 11: goto L_0x039b;
                case 12: goto L_0x0371;
                case 13: goto L_0x0347;
                case 14: goto L_0x031d;
                case 15: goto L_0x02f3;
                case 16: goto L_0x02e8;
                case 17: goto L_0x02d9;
                case 18: goto L_0x02ca;
                case 19: goto L_0x02b8;
                case 20: goto L_0x02a6;
                case 21: goto L_0x027c;
                case 22: goto L_0x026d;
                case 23: goto L_0x024f;
                case 24: goto L_0x0240;
                case 25: goto L_0x022e;
                case 26: goto L_0x021c;
                case 27: goto L_0x020d;
                case 28: goto L_0x01fb;
                case 29: goto L_0x01e9;
                case 30: goto L_0x01c0;
                default: goto L_0x01bb;
            }
        L_0x01bb:
            super.setProperty(r7, r8, r9)
            goto L_0x0462
        L_0x01c0:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x01cf
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x01cf:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x01de
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setStrokeWidth(r7, (java.lang.Double) r9)
            goto L_0x0462
        L_0x01de:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = "1"
            r8.setStrokeWidth(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x01e9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x01f0
            goto L_0x01f6
        L_0x01f0:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
        L_0x01f6:
            r8.setResponsible(r7, r2)
            goto L_0x0462
        L_0x01fb:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x0202
            goto L_0x0208
        L_0x0202:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0208:
            r8.setStrokeLinejoin(r7, r2)
            goto L_0x0462
        L_0x020d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x0214
            goto L_0x0217
        L_0x0214:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0217:
            r8.setDisplay(r7, r5)
            goto L_0x0462
        L_0x021c:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x0223
            goto L_0x0229
        L_0x0223:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x0229:
            r8.setStrokeLinecap(r7, r2)
            goto L_0x0462
        L_0x022e:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x0235
            goto L_0x023b
        L_0x0235:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x023b:
            r8.setClipRule(r7, r2)
            goto L_0x0462
        L_0x0240:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x0247
            goto L_0x024a
        L_0x0247:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x024a:
            r8.setClipPath(r7, r5)
            goto L_0x0462
        L_0x024f:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x025e
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setStrokeDasharray(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x025e:
            boolean r8 = r9 instanceof com.facebook.react.bridge.ReadableArray
            if (r8 == 0) goto L_0x0462
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setStrokeDasharray(r7, (com.facebook.react.bridge.ReadableArray) r9)
            goto L_0x0462
        L_0x026d:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x0274
            goto L_0x0277
        L_0x0274:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0277:
            r8.setMarkerStart(r7, r5)
            goto L_0x0462
        L_0x027c:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x028b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setWidth(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x028b:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x029a
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r9)
            goto L_0x0462
        L_0x029a:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setWidth(r7, (java.lang.Double) r5)
            goto L_0x0462
        L_0x02a6:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x02ad
            goto L_0x02b3
        L_0x02ad:
            java.lang.Double r9 = (java.lang.Double) r9
            int r2 = r9.intValue()
        L_0x02b3:
            r8.setVectorEffect(r7, r2)
            goto L_0x0462
        L_0x02b8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x02bf
            goto L_0x02c5
        L_0x02bf:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x02c5:
            r8.setStrokeMiterlimit(r7, r0)
            goto L_0x0462
        L_0x02ca:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x02d1
            goto L_0x02d4
        L_0x02d1:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02d4:
            r8.setName(r7, r5)
            goto L_0x0462
        L_0x02d9:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x02e0
            goto L_0x02e3
        L_0x02e0:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x02e3:
            r8.setMask(r7, r5)
            goto L_0x0462
        L_0x02e8:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setFill(r7, r9)
            goto L_0x0462
        L_0x02f3:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0302
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setRy(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x0302:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0311
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRy(r7, (java.lang.Double) r9)
            goto L_0x0462
        L_0x0311:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRy(r7, (java.lang.Double) r5)
            goto L_0x0462
        L_0x031d:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x032c
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setRx(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x032c:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x033b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRx(r7, (java.lang.Double) r9)
            goto L_0x0462
        L_0x033b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setRx(r7, (java.lang.Double) r5)
            goto L_0x0462
        L_0x0347:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0356
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setY(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x0356:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0365
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r9)
            goto L_0x0462
        L_0x0365:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setY(r7, (java.lang.Double) r5)
            goto L_0x0462
        L_0x0371:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x0380
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setX(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x0380:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x038f
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r9)
            goto L_0x0462
        L_0x038f:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setX(r7, (java.lang.Double) r5)
            goto L_0x0462
        L_0x039b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x03a2
            goto L_0x03a8
        L_0x03a2:
            java.lang.Double r9 = (java.lang.Double) r9
            float r0 = r9.floatValue()
        L_0x03a8:
            r8.setStrokeDashoffset(r7, r0)
            goto L_0x0462
        L_0x03ad:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x03b4
            goto L_0x03ba
        L_0x03b4:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03ba:
            r8.setFillOpacity(r7, r4)
            goto L_0x0462
        L_0x03bf:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x03c6
            goto L_0x03c9
        L_0x03c6:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x03c9:
            r8.setPointerEvents(r7, r5)
            goto L_0x0462
        L_0x03ce:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x03d5
            goto L_0x03db
        L_0x03d5:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x03db:
            r8.setStrokeOpacity(r7, r4)
            goto L_0x0462
        L_0x03e0:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x03e7
            goto L_0x03ed
        L_0x03e7:
            java.lang.Double r9 = (java.lang.Double) r9
            int r1 = r9.intValue()
        L_0x03ed:
            r8.setFillRule(r7, r1)
            goto L_0x0462
        L_0x03f2:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            com.facebook.react.bridge.ReadableMap r9 = (com.facebook.react.bridge.ReadableMap) r9
            r8.setStroke(r7, r9)
            goto L_0x0462
        L_0x03fd:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x0404
            goto L_0x0407
        L_0x0404:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0407:
            r8.setMarkerMid(r7, r5)
            goto L_0x0462
        L_0x040b:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            if (r9 != 0) goto L_0x0412
            goto L_0x0415
        L_0x0412:
            r5 = r9
            java.lang.String r5 = (java.lang.String) r5
        L_0x0415:
            r8.setMarkerEnd(r7, r5)
            goto L_0x0462
        L_0x0419:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setPropList(r7, r9)
            goto L_0x0462
        L_0x0423:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            com.facebook.react.bridge.ReadableArray r9 = (com.facebook.react.bridge.ReadableArray) r9
            r8.setMatrix(r7, r9)
            goto L_0x0462
        L_0x042d:
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L_0x043b
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.String r9 = (java.lang.String) r9
            r8.setHeight(r7, (java.lang.String) r9)
            goto L_0x0462
        L_0x043b:
            boolean r8 = r9 instanceof java.lang.Double
            if (r8 == 0) goto L_0x0449
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r9)
            goto L_0x0462
        L_0x0449:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r8 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r8
            r9 = r5
            java.lang.Double r9 = (java.lang.Double) r9
            r8.setHeight(r7, (java.lang.Double) r5)
            goto L_0x0462
        L_0x0454:
            com.facebook.react.uimanager.BaseViewManagerInterface r8 = r6.mViewManager
            if (r9 != 0) goto L_0x0459
            goto L_0x045f
        L_0x0459:
            java.lang.Double r9 = (java.lang.Double) r9
            float r4 = r9.floatValue()
        L_0x045f:
            r8.setOpacity(r7, r4)
        L_0x0462:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGRectManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
