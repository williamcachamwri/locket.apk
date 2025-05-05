package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.ModalHostViewManagerInterface;

public class ModalHostViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & ModalHostViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public ModalHostViewManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r4, java.lang.String r5, java.lang.Object r6) {
        /*
            r3 = this;
            r5.hashCode()
            int r0 = r5.hashCode()
            r1 = 0
            r2 = -1
            switch(r0) {
                case -1851617609: goto L_0x006d;
                case -1850124175: goto L_0x0061;
                case -1726194350: goto L_0x0055;
                case -1618432855: goto L_0x004a;
                case -1156137512: goto L_0x003e;
                case -795203165: goto L_0x0033;
                case 466743410: goto L_0x0027;
                case 1195991583: goto L_0x001c;
                case 2031205598: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0077
        L_0x000e:
            java.lang.String r0 = "animationType"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0018
            goto L_0x0077
        L_0x0018:
            r2 = 8
            goto L_0x0077
        L_0x001c:
            java.lang.String r0 = "hardwareAccelerated"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0025
            goto L_0x0077
        L_0x0025:
            r2 = 7
            goto L_0x0077
        L_0x0027:
            java.lang.String r0 = "visible"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0031
            goto L_0x0077
        L_0x0031:
            r2 = 6
            goto L_0x0077
        L_0x0033:
            java.lang.String r0 = "animated"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x003c
            goto L_0x0077
        L_0x003c:
            r2 = 5
            goto L_0x0077
        L_0x003e:
            java.lang.String r0 = "statusBarTranslucent"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0048
            goto L_0x0077
        L_0x0048:
            r2 = 4
            goto L_0x0077
        L_0x004a:
            java.lang.String r0 = "identifier"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0053
            goto L_0x0077
        L_0x0053:
            r2 = 3
            goto L_0x0077
        L_0x0055:
            java.lang.String r0 = "transparent"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x005f
            goto L_0x0077
        L_0x005f:
            r2 = 2
            goto L_0x0077
        L_0x0061:
            java.lang.String r0 = "supportedOrientations"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x006b
            goto L_0x0077
        L_0x006b:
            r2 = 1
            goto L_0x0077
        L_0x006d:
            java.lang.String r0 = "presentationStyle"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0076
            goto L_0x0077
        L_0x0076:
            r2 = r1
        L_0x0077:
            switch(r2) {
                case 0: goto L_0x00fb;
                case 1: goto L_0x00f1;
                case 2: goto L_0x00e0;
                case 3: goto L_0x00cf;
                case 4: goto L_0x00be;
                case 5: goto L_0x00ad;
                case 6: goto L_0x009c;
                case 7: goto L_0x008a;
                case 8: goto L_0x007f;
                default: goto L_0x007a;
            }
        L_0x007a:
            super.setProperty(r4, r5, r6)
            goto L_0x0104
        L_0x007f:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            java.lang.String r6 = (java.lang.String) r6
            r5.setAnimationType(r4, r6)
            goto L_0x0104
        L_0x008a:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x0091
            goto L_0x0097
        L_0x0091:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r1 = r6.booleanValue()
        L_0x0097:
            r5.setHardwareAccelerated(r4, r1)
            goto L_0x0104
        L_0x009c:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00a3
            goto L_0x00a9
        L_0x00a3:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r1 = r6.booleanValue()
        L_0x00a9:
            r5.setVisible(r4, r1)
            goto L_0x0104
        L_0x00ad:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00b4
            goto L_0x00ba
        L_0x00b4:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r1 = r6.booleanValue()
        L_0x00ba:
            r5.setAnimated(r4, r1)
            goto L_0x0104
        L_0x00be:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00c5
            goto L_0x00cb
        L_0x00c5:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r1 = r6.booleanValue()
        L_0x00cb:
            r5.setStatusBarTranslucent(r4, r1)
            goto L_0x0104
        L_0x00cf:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00d6
            goto L_0x00dc
        L_0x00d6:
            java.lang.Double r6 = (java.lang.Double) r6
            int r1 = r6.intValue()
        L_0x00dc:
            r5.setIdentifier(r4, r1)
            goto L_0x0104
        L_0x00e0:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00e7
            goto L_0x00ed
        L_0x00e7:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r1 = r6.booleanValue()
        L_0x00ed:
            r5.setTransparent(r4, r1)
            goto L_0x0104
        L_0x00f1:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            com.facebook.react.bridge.ReadableArray r6 = (com.facebook.react.bridge.ReadableArray) r6
            r5.setSupportedOrientations(r4, r6)
            goto L_0x0104
        L_0x00fb:
            com.facebook.react.uimanager.BaseViewManagerInterface r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            java.lang.String r6 = (java.lang.String) r6
            r5.setPresentationStyle(r4, r6)
        L_0x0104:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.ModalHostViewManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
