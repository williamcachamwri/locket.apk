package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface;

public class RNSScreenStackHeaderConfigManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSScreenStackHeaderConfigManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSScreenStackHeaderConfigManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v27, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v30, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v31, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v32, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v33, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v34, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v35, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v36, resolved type: boolean} */
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
                case -1822687399: goto L_0x015e;
                case -1799367701: goto L_0x0152;
                case -1774658170: goto L_0x0147;
                case -1715368693: goto L_0x013b;
                case -1503810304: goto L_0x0130;
                case -1225100257: goto L_0x0124;
                case -1217487446: goto L_0x0119;
                case -1094575123: goto L_0x010e;
                case -1063138943: goto L_0x0100;
                case -962590849: goto L_0x00f2;
                case -389245640: goto L_0x00e4;
                case -140063148: goto L_0x00d6;
                case 347216: goto L_0x00c8;
                case 94842723: goto L_0x00ba;
                case 110371416: goto L_0x00ab;
                case 183888321: goto L_0x009d;
                case 243070244: goto L_0x008f;
                case 339462402: goto L_0x0081;
                case 490615652: goto L_0x0073;
                case 1038753243: goto L_0x0065;
                case 1287124693: goto L_0x0057;
                case 1324688817: goto L_0x0049;
                case 1518161768: goto L_0x003a;
                case 1564506303: goto L_0x002c;
                case 2029798365: goto L_0x001e;
                case 2099541337: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0169
        L_0x000f:
            java.lang.String r0 = "topInsetEnabled"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x0169
        L_0x001a:
            r3 = 25
            goto L_0x0169
        L_0x001e:
            java.lang.String r0 = "largeTitle"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0028
            goto L_0x0169
        L_0x0028:
            r3 = 24
            goto L_0x0169
        L_0x002c:
            java.lang.String r0 = "largeTitleHideShadow"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0036
            goto L_0x0169
        L_0x0036:
            r3 = 23
            goto L_0x0169
        L_0x003a:
            java.lang.String r0 = "titleFontSize"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0169
        L_0x0045:
            r3 = 22
            goto L_0x0169
        L_0x0049:
            java.lang.String r0 = "backTitle"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0053
            goto L_0x0169
        L_0x0053:
            r3 = 21
            goto L_0x0169
        L_0x0057:
            java.lang.String r0 = "backgroundColor"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0061
            goto L_0x0169
        L_0x0061:
            r3 = 20
            goto L_0x0169
        L_0x0065:
            java.lang.String r0 = "hideBackButton"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x006f
            goto L_0x0169
        L_0x006f:
            r3 = 19
            goto L_0x0169
        L_0x0073:
            java.lang.String r0 = "largeTitleFontWeight"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x007d
            goto L_0x0169
        L_0x007d:
            r3 = 18
            goto L_0x0169
        L_0x0081:
            java.lang.String r0 = "hideShadow"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x008b
            goto L_0x0169
        L_0x008b:
            r3 = 17
            goto L_0x0169
        L_0x008f:
            java.lang.String r0 = "backTitleFontFamily"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0099
            goto L_0x0169
        L_0x0099:
            r3 = 16
            goto L_0x0169
        L_0x009d:
            java.lang.String r0 = "backTitleFontSize"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x00a7
            goto L_0x0169
        L_0x00a7:
            r3 = 15
            goto L_0x0169
        L_0x00ab:
            java.lang.String r0 = "title"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x00b6
            goto L_0x0169
        L_0x00b6:
            r3 = 14
            goto L_0x0169
        L_0x00ba:
            java.lang.String r0 = "color"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x00c4
            goto L_0x0169
        L_0x00c4:
            r3 = 13
            goto L_0x0169
        L_0x00c8:
            java.lang.String r0 = "largeTitleFontFamily"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x00d2
            goto L_0x0169
        L_0x00d2:
            r3 = 12
            goto L_0x0169
        L_0x00d6:
            java.lang.String r0 = "backButtonInCustomView"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x00e0
            goto L_0x0169
        L_0x00e0:
            r3 = 11
            goto L_0x0169
        L_0x00e4:
            java.lang.String r0 = "largeTitleBackgroundColor"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x00ee
            goto L_0x0169
        L_0x00ee:
            r3 = 10
            goto L_0x0169
        L_0x00f2:
            java.lang.String r0 = "direction"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x00fc
            goto L_0x0169
        L_0x00fc:
            r3 = 9
            goto L_0x0169
        L_0x0100:
            java.lang.String r0 = "backTitleVisible"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x010a
            goto L_0x0169
        L_0x010a:
            r3 = 8
            goto L_0x0169
        L_0x010e:
            java.lang.String r0 = "largeTitleFontSize"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0117
            goto L_0x0169
        L_0x0117:
            r3 = 7
            goto L_0x0169
        L_0x0119:
            java.lang.String r0 = "hidden"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0122
            goto L_0x0169
        L_0x0122:
            r3 = 6
            goto L_0x0169
        L_0x0124:
            java.lang.String r0 = "titleFontWeight"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x012e
            goto L_0x0169
        L_0x012e:
            r3 = 5
            goto L_0x0169
        L_0x0130:
            java.lang.String r0 = "disableBackButtonMenu"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0139
            goto L_0x0169
        L_0x0139:
            r3 = 4
            goto L_0x0169
        L_0x013b:
            java.lang.String r0 = "titleFontFamily"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0145
            goto L_0x0169
        L_0x0145:
            r3 = 3
            goto L_0x0169
        L_0x0147:
            java.lang.String r0 = "largeTitleColor"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0150
            goto L_0x0169
        L_0x0150:
            r3 = 2
            goto L_0x0169
        L_0x0152:
            java.lang.String r0 = "titleColor"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x015c
            goto L_0x0169
        L_0x015c:
            r3 = r1
            goto L_0x0169
        L_0x015e:
            java.lang.String r0 = "translucent"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0168
            goto L_0x0169
        L_0x0168:
            r3 = r2
        L_0x0169:
            r0 = 0
            switch(r3) {
                case 0: goto L_0x030d;
                case 1: goto L_0x02fd;
                case 2: goto L_0x02ed;
                case 3: goto L_0x02df;
                case 4: goto L_0x02ce;
                case 5: goto L_0x02c0;
                case 6: goto L_0x02af;
                case 7: goto L_0x029d;
                case 8: goto L_0x028b;
                case 9: goto L_0x0280;
                case 10: goto L_0x026f;
                case 11: goto L_0x025d;
                case 12: goto L_0x024e;
                case 13: goto L_0x023d;
                case 14: goto L_0x022e;
                case 15: goto L_0x021c;
                case 16: goto L_0x020d;
                case 17: goto L_0x01fb;
                case 18: goto L_0x01ec;
                case 19: goto L_0x01da;
                case 20: goto L_0x01c9;
                case 21: goto L_0x01ba;
                case 22: goto L_0x01a8;
                case 23: goto L_0x0196;
                case 24: goto L_0x0184;
                case 25: goto L_0x0172;
                default: goto L_0x016d;
            }
        L_0x016d:
            super.setProperty(r5, r6, r7)
            goto L_0x031d
        L_0x0172:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0179
            goto L_0x017f
        L_0x0179:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x017f:
            r6.setTopInsetEnabled(r5, r2)
            goto L_0x031d
        L_0x0184:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x018b
            goto L_0x0191
        L_0x018b:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x0191:
            r6.setLargeTitle(r5, r2)
            goto L_0x031d
        L_0x0196:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x019d
            goto L_0x01a3
        L_0x019d:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x01a3:
            r6.setLargeTitleHideShadow(r5, r2)
            goto L_0x031d
        L_0x01a8:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x01af
            goto L_0x01b5
        L_0x01af:
            java.lang.Double r7 = (java.lang.Double) r7
            int r2 = r7.intValue()
        L_0x01b5:
            r6.setTitleFontSize(r5, r2)
            goto L_0x031d
        L_0x01ba:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x01c1
            goto L_0x01c4
        L_0x01c1:
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
        L_0x01c4:
            r6.setBackTitle(r5, r0)
            goto L_0x031d
        L_0x01c9:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setBackgroundColor(r5, r7)
            goto L_0x031d
        L_0x01da:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x01e1
            goto L_0x01e7
        L_0x01e1:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x01e7:
            r6.setHideBackButton(r5, r2)
            goto L_0x031d
        L_0x01ec:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x01f3
            goto L_0x01f6
        L_0x01f3:
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
        L_0x01f6:
            r6.setLargeTitleFontWeight(r5, r0)
            goto L_0x031d
        L_0x01fb:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0202
            goto L_0x0208
        L_0x0202:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x0208:
            r6.setHideShadow(r5, r2)
            goto L_0x031d
        L_0x020d:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0214
            goto L_0x0217
        L_0x0214:
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
        L_0x0217:
            r6.setBackTitleFontFamily(r5, r0)
            goto L_0x031d
        L_0x021c:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0223
            goto L_0x0229
        L_0x0223:
            java.lang.Double r7 = (java.lang.Double) r7
            int r2 = r7.intValue()
        L_0x0229:
            r6.setBackTitleFontSize(r5, r2)
            goto L_0x031d
        L_0x022e:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0235
            goto L_0x0238
        L_0x0235:
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
        L_0x0238:
            r6.setTitle(r5, r0)
            goto L_0x031d
        L_0x023d:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setColor(r5, r7)
            goto L_0x031d
        L_0x024e:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0255
            goto L_0x0258
        L_0x0255:
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
        L_0x0258:
            r6.setLargeTitleFontFamily(r5, r0)
            goto L_0x031d
        L_0x025d:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0264
            goto L_0x026a
        L_0x0264:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x026a:
            r6.setBackButtonInCustomView(r5, r2)
            goto L_0x031d
        L_0x026f:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setLargeTitleBackgroundColor(r5, r7)
            goto L_0x031d
        L_0x0280:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            java.lang.String r7 = (java.lang.String) r7
            r6.setDirection(r5, r7)
            goto L_0x031d
        L_0x028b:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0292
            goto L_0x0298
        L_0x0292:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r1 = r7.booleanValue()
        L_0x0298:
            r6.setBackTitleVisible(r5, r1)
            goto L_0x031d
        L_0x029d:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x02a4
            goto L_0x02aa
        L_0x02a4:
            java.lang.Double r7 = (java.lang.Double) r7
            int r2 = r7.intValue()
        L_0x02aa:
            r6.setLargeTitleFontSize(r5, r2)
            goto L_0x031d
        L_0x02af:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x02b6
            goto L_0x02bc
        L_0x02b6:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x02bc:
            r6.setHidden(r5, r2)
            goto L_0x031d
        L_0x02c0:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x02c7
            goto L_0x02ca
        L_0x02c7:
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
        L_0x02ca:
            r6.setTitleFontWeight(r5, r0)
            goto L_0x031d
        L_0x02ce:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x02d5
            goto L_0x02db
        L_0x02d5:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x02db:
            r6.setDisableBackButtonMenu(r5, r2)
            goto L_0x031d
        L_0x02df:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x02e6
            goto L_0x02e9
        L_0x02e6:
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
        L_0x02e9:
            r6.setTitleFontFamily(r5, r0)
            goto L_0x031d
        L_0x02ed:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setLargeTitleColor(r5, r7)
            goto L_0x031d
        L_0x02fd:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setTitleColor(r5, r7)
            goto L_0x031d
        L_0x030d:
            com.facebook.react.uimanager.BaseViewManagerInterface r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r6 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r6
            if (r7 != 0) goto L_0x0314
            goto L_0x031a
        L_0x0314:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r2 = r7.booleanValue()
        L_0x031a:
            r6.setTranslucent(r5, r2)
        L_0x031d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
