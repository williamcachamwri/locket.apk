package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerInterface;

public abstract class BaseViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T>> implements ViewManagerDelegate<T> {
    protected final U mViewManager;

    public void receiveCommand(T t, String str, ReadableArray readableArray) {
    }

    public BaseViewManagerDelegate(U u) {
        this.mViewManager = u;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r5, java.lang.String r6, java.lang.Object r7) {
        /*
            r4 = this;
            r6.hashCode()
            int r0 = r6.hashCode()
            r1 = 0
            r2 = -1
            switch(r0) {
                case -1721943862: goto L_0x01a3;
                case -1721943861: goto L_0x0197;
                case -1589741021: goto L_0x018b;
                case -1267206133: goto L_0x0180;
                case -1228066334: goto L_0x0175;
                case -908189618: goto L_0x016a;
                case -908189617: goto L_0x015f;
                case -877170387: goto L_0x0153;
                case -781597262: goto L_0x0144;
                case -731417480: goto L_0x0135;
                case -101663499: goto L_0x0127;
                case -101359900: goto L_0x0119;
                case -80891667: goto L_0x010b;
                case -40300674: goto L_0x00fd;
                case -4379043: goto L_0x00ef;
                case 3506294: goto L_0x00e1;
                case 36255470: goto L_0x00d3;
                case 333432965: goto L_0x00c5;
                case 581268560: goto L_0x00b7;
                case 588239831: goto L_0x00a9;
                case 746986311: goto L_0x009b;
                case 1052666732: goto L_0x008c;
                case 1146842694: goto L_0x007e;
                case 1153872867: goto L_0x0070;
                case 1287124693: goto L_0x0062;
                case 1349188574: goto L_0x0054;
                case 1505602511: goto L_0x0046;
                case 1761903244: goto L_0x0038;
                case 1865277756: goto L_0x002a;
                case 1993034687: goto L_0x001c;
                case 2045685618: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x01ae
        L_0x000e:
            java.lang.String r0 = "nativeID"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0018
            goto L_0x01ae
        L_0x0018:
            r2 = 30
            goto L_0x01ae
        L_0x001c:
            java.lang.String r0 = "accessibilityCollectionItem"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0026
            goto L_0x01ae
        L_0x0026:
            r2 = 29
            goto L_0x01ae
        L_0x002a:
            java.lang.String r0 = "accessibilityLabelledBy"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0034
            goto L_0x01ae
        L_0x0034:
            r2 = 28
            goto L_0x01ae
        L_0x0038:
            java.lang.String r0 = "accessibilityCollection"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0042
            goto L_0x01ae
        L_0x0042:
            r2 = 27
            goto L_0x01ae
        L_0x0046:
            java.lang.String r0 = "accessibilityActions"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0050
            goto L_0x01ae
        L_0x0050:
            r2 = 26
            goto L_0x01ae
        L_0x0054:
            java.lang.String r0 = "borderRadius"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x005e
            goto L_0x01ae
        L_0x005e:
            r2 = 25
            goto L_0x01ae
        L_0x0062:
            java.lang.String r0 = "backgroundColor"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x006c
            goto L_0x01ae
        L_0x006c:
            r2 = 24
            goto L_0x01ae
        L_0x0070:
            java.lang.String r0 = "accessibilityState"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x007a
            goto L_0x01ae
        L_0x007a:
            r2 = 23
            goto L_0x01ae
        L_0x007e:
            java.lang.String r0 = "accessibilityLabel"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0088
            goto L_0x01ae
        L_0x0088:
            r2 = 22
            goto L_0x01ae
        L_0x008c:
            java.lang.String r0 = "transform"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0097
            goto L_0x01ae
        L_0x0097:
            r2 = 21
            goto L_0x01ae
        L_0x009b:
            java.lang.String r0 = "importantForAccessibility"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x00a5
            goto L_0x01ae
        L_0x00a5:
            r2 = 20
            goto L_0x01ae
        L_0x00a9:
            java.lang.String r0 = "borderBottomRightRadius"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x00b3
            goto L_0x01ae
        L_0x00b3:
            r2 = 19
            goto L_0x01ae
        L_0x00b7:
            java.lang.String r0 = "borderBottomLeftRadius"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x00c1
            goto L_0x01ae
        L_0x00c1:
            r2 = 18
            goto L_0x01ae
        L_0x00c5:
            java.lang.String r0 = "borderTopRightRadius"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x00cf
            goto L_0x01ae
        L_0x00cf:
            r2 = 17
            goto L_0x01ae
        L_0x00d3:
            java.lang.String r0 = "accessibilityLiveRegion"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x00dd
            goto L_0x01ae
        L_0x00dd:
            r2 = 16
            goto L_0x01ae
        L_0x00e1:
            java.lang.String r0 = "role"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x00eb
            goto L_0x01ae
        L_0x00eb:
            r2 = 15
            goto L_0x01ae
        L_0x00ef:
            java.lang.String r0 = "elevation"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x00f9
            goto L_0x01ae
        L_0x00f9:
            r2 = 14
            goto L_0x01ae
        L_0x00fd:
            java.lang.String r0 = "rotation"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0107
            goto L_0x01ae
        L_0x0107:
            r2 = 13
            goto L_0x01ae
        L_0x010b:
            java.lang.String r0 = "renderToHardwareTextureAndroid"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0115
            goto L_0x01ae
        L_0x0115:
            r2 = 12
            goto L_0x01ae
        L_0x0119:
            java.lang.String r0 = "accessibilityRole"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0123
            goto L_0x01ae
        L_0x0123:
            r2 = 11
            goto L_0x01ae
        L_0x0127:
            java.lang.String r0 = "accessibilityHint"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0131
            goto L_0x01ae
        L_0x0131:
            r2 = 10
            goto L_0x01ae
        L_0x0135:
            java.lang.String r0 = "zIndex"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0140
            goto L_0x01ae
        L_0x0140:
            r2 = 9
            goto L_0x01ae
        L_0x0144:
            java.lang.String r0 = "transformOrigin"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x014f
            goto L_0x01ae
        L_0x014f:
            r2 = 8
            goto L_0x01ae
        L_0x0153:
            java.lang.String r0 = "testID"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x015d
            goto L_0x01ae
        L_0x015d:
            r2 = 7
            goto L_0x01ae
        L_0x015f:
            java.lang.String r0 = "scaleY"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0168
            goto L_0x01ae
        L_0x0168:
            r2 = 6
            goto L_0x01ae
        L_0x016a:
            java.lang.String r0 = "scaleX"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0173
            goto L_0x01ae
        L_0x0173:
            r2 = 5
            goto L_0x01ae
        L_0x0175:
            java.lang.String r0 = "borderTopLeftRadius"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x017e
            goto L_0x01ae
        L_0x017e:
            r2 = 4
            goto L_0x01ae
        L_0x0180:
            java.lang.String r0 = "opacity"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0189
            goto L_0x01ae
        L_0x0189:
            r2 = 3
            goto L_0x01ae
        L_0x018b:
            java.lang.String r0 = "shadowColor"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0195
            goto L_0x01ae
        L_0x0195:
            r2 = 2
            goto L_0x01ae
        L_0x0197:
            java.lang.String r0 = "translateY"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x01a1
            goto L_0x01ae
        L_0x01a1:
            r2 = 1
            goto L_0x01ae
        L_0x01a3:
            java.lang.String r0 = "translateX"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x01ad
            goto L_0x01ae
        L_0x01ad:
            r2 = r1
        L_0x01ae:
            r6 = 1065353216(0x3f800000, float:1.0)
            r0 = 0
            r3 = 2143289344(0x7fc00000, float:NaN)
            switch(r2) {
                case 0: goto L_0x0338;
                case 1: goto L_0x0329;
                case 2: goto L_0x0314;
                case 3: goto L_0x0305;
                case 4: goto L_0x02f6;
                case 5: goto L_0x02e7;
                case 6: goto L_0x02d8;
                case 7: goto L_0x02cf;
                case 8: goto L_0x02c6;
                case 9: goto L_0x02b6;
                case 10: goto L_0x02ad;
                case 11: goto L_0x02a4;
                case 12: goto L_0x0294;
                case 13: goto L_0x0284;
                case 14: goto L_0x0274;
                case 15: goto L_0x026b;
                case 16: goto L_0x0262;
                case 17: goto L_0x0252;
                case 18: goto L_0x0242;
                case 19: goto L_0x0232;
                case 20: goto L_0x0229;
                case 21: goto L_0x0220;
                case 22: goto L_0x0217;
                case 23: goto L_0x020e;
                case 24: goto L_0x01f8;
                case 25: goto L_0x01e8;
                case 26: goto L_0x01df;
                case 27: goto L_0x01d6;
                case 28: goto L_0x01ca;
                case 29: goto L_0x01c1;
                case 30: goto L_0x01b8;
                default: goto L_0x01b6;
            }
        L_0x01b6:
            goto L_0x0346
        L_0x01b8:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setNativeId(r5, r7)
            goto L_0x0346
        L_0x01c1:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableMap r7 = (com.facebook.react.bridge.ReadableMap) r7
            r6.setAccessibilityCollectionItem(r5, r7)
            goto L_0x0346
        L_0x01ca:
            com.facebook.react.bridge.DynamicFromObject r6 = new com.facebook.react.bridge.DynamicFromObject
            r6.<init>(r7)
            U r7 = r4.mViewManager
            r7.setAccessibilityLabelledBy(r5, r6)
            goto L_0x0346
        L_0x01d6:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableMap r7 = (com.facebook.react.bridge.ReadableMap) r7
            r6.setAccessibilityCollection(r5, r7)
            goto L_0x0346
        L_0x01df:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setAccessibilityActions(r5, r7)
            goto L_0x0346
        L_0x01e8:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x01ed
            goto L_0x01f3
        L_0x01ed:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x01f3:
            r6.setBorderRadius(r5, r3)
            goto L_0x0346
        L_0x01f8:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x01fd
            goto L_0x0209
        L_0x01fd:
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            int r1 = r7.intValue()
        L_0x0209:
            r6.setBackgroundColor(r5, r1)
            goto L_0x0346
        L_0x020e:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableMap r7 = (com.facebook.react.bridge.ReadableMap) r7
            r6.setViewState(r5, r7)
            goto L_0x0346
        L_0x0217:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setAccessibilityLabel(r5, r7)
            goto L_0x0346
        L_0x0220:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setTransform(r5, r7)
            goto L_0x0346
        L_0x0229:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setImportantForAccessibility(r5, r7)
            goto L_0x0346
        L_0x0232:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0237
            goto L_0x023d
        L_0x0237:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x023d:
            r6.setBorderBottomRightRadius(r5, r3)
            goto L_0x0346
        L_0x0242:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0247
            goto L_0x024d
        L_0x0247:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x024d:
            r6.setBorderBottomLeftRadius(r5, r3)
            goto L_0x0346
        L_0x0252:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0257
            goto L_0x025d
        L_0x0257:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x025d:
            r6.setBorderTopRightRadius(r5, r3)
            goto L_0x0346
        L_0x0262:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setAccessibilityLiveRegion(r5, r7)
            goto L_0x0346
        L_0x026b:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setRole(r5, r7)
            goto L_0x0346
        L_0x0274:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0279
            goto L_0x027f
        L_0x0279:
            java.lang.Double r7 = (java.lang.Double) r7
            float r0 = r7.floatValue()
        L_0x027f:
            r6.setElevation(r5, r0)
            goto L_0x0346
        L_0x0284:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0289
            goto L_0x028f
        L_0x0289:
            java.lang.Double r7 = (java.lang.Double) r7
            float r0 = r7.floatValue()
        L_0x028f:
            r6.setRotation(r5, r0)
            goto L_0x0346
        L_0x0294:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0299
            goto L_0x029f
        L_0x0299:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r1 = r7.booleanValue()
        L_0x029f:
            r6.setRenderToHardwareTexture(r5, r1)
            goto L_0x0346
        L_0x02a4:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setAccessibilityRole(r5, r7)
            goto L_0x0346
        L_0x02ad:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setAccessibilityHint(r5, r7)
            goto L_0x0346
        L_0x02b6:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x02bb
            goto L_0x02c1
        L_0x02bb:
            java.lang.Double r7 = (java.lang.Double) r7
            float r0 = r7.floatValue()
        L_0x02c1:
            r6.setZIndex(r5, r0)
            goto L_0x0346
        L_0x02c6:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setTransformOrigin(r5, r7)
            goto L_0x0346
        L_0x02cf:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setTestId(r5, r7)
            goto L_0x0346
        L_0x02d8:
            U r0 = r4.mViewManager
            if (r7 != 0) goto L_0x02dd
            goto L_0x02e3
        L_0x02dd:
            java.lang.Double r7 = (java.lang.Double) r7
            float r6 = r7.floatValue()
        L_0x02e3:
            r0.setScaleY(r5, r6)
            goto L_0x0346
        L_0x02e7:
            U r0 = r4.mViewManager
            if (r7 != 0) goto L_0x02ec
            goto L_0x02f2
        L_0x02ec:
            java.lang.Double r7 = (java.lang.Double) r7
            float r6 = r7.floatValue()
        L_0x02f2:
            r0.setScaleX(r5, r6)
            goto L_0x0346
        L_0x02f6:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x02fb
            goto L_0x0301
        L_0x02fb:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x0301:
            r6.setBorderTopLeftRadius(r5, r3)
            goto L_0x0346
        L_0x0305:
            U r0 = r4.mViewManager
            if (r7 != 0) goto L_0x030a
            goto L_0x0310
        L_0x030a:
            java.lang.Double r7 = (java.lang.Double) r7
            float r6 = r7.floatValue()
        L_0x0310:
            r0.setOpacity(r5, r6)
            goto L_0x0346
        L_0x0314:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0319
            goto L_0x0325
        L_0x0319:
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            int r1 = r7.intValue()
        L_0x0325:
            r6.setShadowColor(r5, r1)
            goto L_0x0346
        L_0x0329:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x032e
            goto L_0x0334
        L_0x032e:
            java.lang.Double r7 = (java.lang.Double) r7
            float r0 = r7.floatValue()
        L_0x0334:
            r6.setTranslateY(r5, r0)
            goto L_0x0346
        L_0x0338:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x033d
            goto L_0x0343
        L_0x033d:
            java.lang.Double r7 = (java.lang.Double) r7
            float r0 = r7.floatValue()
        L_0x0343:
            r6.setTranslateX(r5, r0)
        L_0x0346:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.BaseViewManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
