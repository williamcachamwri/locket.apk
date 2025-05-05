package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface;

public class RNGestureHandlerButtonManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNGestureHandlerButtonManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNGestureHandlerButtonManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r5, java.lang.String r6, java.lang.Object r7) {
        /*
            r4 = this;
            r6.hashCode()
            int r0 = r6.hashCode()
            r1 = 1
            r2 = 0
            r3 = -1
            switch(r0) {
                case -2143114526: goto L_0x0079;
                case -1609594047: goto L_0x006e;
                case -775297261: goto L_0x0063;
                case 722830999: goto L_0x0058;
                case 737768677: goto L_0x004d;
                case 741115130: goto L_0x0042;
                case 1387411372: goto L_0x0036;
                case 1686617758: goto L_0x002b;
                case 1825644485: goto L_0x001d;
                case 1984457027: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0083
        L_0x000f:
            java.lang.String r0 = "foreground"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0019
            goto L_0x0083
        L_0x0019:
            r3 = 9
            goto L_0x0083
        L_0x001d:
            java.lang.String r0 = "borderless"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0027
            goto L_0x0083
        L_0x0027:
            r3 = 8
            goto L_0x0083
        L_0x002b:
            java.lang.String r0 = "exclusive"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0034
            goto L_0x0083
        L_0x0034:
            r3 = 7
            goto L_0x0083
        L_0x0036:
            java.lang.String r0 = "touchSoundDisabled"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0040
            goto L_0x0083
        L_0x0040:
            r3 = 6
            goto L_0x0083
        L_0x0042:
            java.lang.String r0 = "borderWidth"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x004b
            goto L_0x0083
        L_0x004b:
            r3 = 5
            goto L_0x0083
        L_0x004d:
            java.lang.String r0 = "borderStyle"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0056
            goto L_0x0083
        L_0x0056:
            r3 = 4
            goto L_0x0083
        L_0x0058:
            java.lang.String r0 = "borderColor"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0061
            goto L_0x0083
        L_0x0061:
            r3 = 3
            goto L_0x0083
        L_0x0063:
            java.lang.String r0 = "rippleColor"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x006c
            goto L_0x0083
        L_0x006c:
            r3 = 2
            goto L_0x0083
        L_0x006e:
            java.lang.String r0 = "enabled"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0077
            goto L_0x0083
        L_0x0077:
            r3 = r1
            goto L_0x0083
        L_0x0079:
            java.lang.String r0 = "rippleRadius"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0082
            goto L_0x0083
        L_0x0082:
            r3 = r2
        L_0x0083:
            switch(r3) {
                case 0: goto L_0x0125;
                case 1: goto L_0x0114;
                case 2: goto L_0x0104;
                case 3: goto L_0x00f4;
                case 4: goto L_0x00e4;
                case 5: goto L_0x00d2;
                case 6: goto L_0x00c1;
                case 7: goto L_0x00af;
                case 8: goto L_0x009d;
                case 9: goto L_0x008b;
                default: goto L_0x0086;
            }
        L_0x0086:
            super.setProperty(r5, r6, r7)
            goto L_0x0135
        L_0x008b:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x0092
            goto L_0x0098
        L_0x0092:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x0098:
            r6.setForeground(r5, r2)
            goto L_0x0135
        L_0x009d:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00a4
            goto L_0x00aa
        L_0x00a4:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x00aa:
            r6.setBorderless(r5, r2)
            goto L_0x0135
        L_0x00af:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00b6
            goto L_0x00bc
        L_0x00b6:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r1 = r7.booleanValue()
        L_0x00bc:
            r6.setExclusive(r5, r1)
            goto L_0x0135
        L_0x00c1:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00c8
            goto L_0x00ce
        L_0x00c8:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x00ce:
            r6.setTouchSoundDisabled(r5, r2)
            goto L_0x0135
        L_0x00d2:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00da
            r7 = 0
            goto L_0x00e0
        L_0x00da:
            java.lang.Double r7 = (java.lang.Double) r7
            float r7 = r7.floatValue()
        L_0x00e0:
            r6.setBorderWidth(r5, r7)
            goto L_0x0135
        L_0x00e4:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00ee
            java.lang.String r7 = "solid"
            goto L_0x00f0
        L_0x00ee:
            java.lang.String r7 = (java.lang.String) r7
        L_0x00f0:
            r6.setBorderStyle(r5, r7)
            goto L_0x0135
        L_0x00f4:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setBorderColor(r5, r7)
            goto L_0x0135
        L_0x0104:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setRippleColor(r5, r7)
            goto L_0x0135
        L_0x0114:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x011b
            goto L_0x0121
        L_0x011b:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r1 = r7.booleanValue()
        L_0x0121:
            r6.setEnabled(r5, r1)
            goto L_0x0135
        L_0x0125:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x012c
            goto L_0x0132
        L_0x012c:
            java.lang.Double r7 = (java.lang.Double) r7
            int r2 = r7.intValue()
        L_0x0132:
            r6.setRippleRadius(r5, r2)
        L_0x0135:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
