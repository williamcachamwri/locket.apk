package androidx.media3.transformer;

import android.os.Build;
import androidx.media3.common.util.Util;
import io.sentry.protocol.ViewHierarchyNode;

class DeviceMappedEncoderBitrateProvider implements EncoderBitrateProvider {
    DeviceMappedEncoderBitrateProvider() {
    }

    public int getBitrate(String str, int i, int i2, float f) {
        return (int) (((double) (((float) (i * i2)) * f)) * getBitrateMultiplierFromMapping(str, Util.SDK_INT, Build.MODEL, "" + i + ViewHierarchyNode.JsonKeys.X + i2, Math.round(f)));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:1137:0x0f6a, code lost:
        r13 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double getBitrateMultiplierFromMapping(java.lang.String r31, int r32, java.lang.String r33, java.lang.String r34, int r35) {
        /*
            r0 = r31
            r1 = r32
            r2 = r33
            r3 = r34
            r4 = r35
            r31.hashCode()
            int r5 = r31.hashCode()
            r11 = 1
            r12 = 0
            r13 = -1
            switch(r5) {
                case -1520327126: goto L_0x0081;
                case -1221990898: goto L_0x0076;
                case -1112627957: goto L_0x006b;
                case -782354407: goto L_0x0060;
                case -579904071: goto L_0x0055;
                case -8329946: goto L_0x004a;
                case 113303871: goto L_0x003f;
                case 265661798: goto L_0x0034;
                case 728806133: goto L_0x0027;
                case 1301817791: goto L_0x001a;
                default: goto L_0x0017;
            }
        L_0x0017:
            r0 = r13
            goto L_0x008b
        L_0x001a:
            java.lang.String r5 = "c2.qti.hevc.encoder"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x0023
            goto L_0x0017
        L_0x0023:
            r0 = 9
            goto L_0x008b
        L_0x0027:
            java.lang.String r5 = "c2.qti.avc.encoder"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x0030
            goto L_0x0017
        L_0x0030:
            r0 = 8
            goto L_0x008b
        L_0x0034:
            java.lang.String r5 = "OMX.Exynos.HEVC.Encoder"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x003d
            goto L_0x0017
        L_0x003d:
            r0 = 7
            goto L_0x008b
        L_0x003f:
            java.lang.String r5 = "OMX.qcom.video.encoder.avc"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x0048
            goto L_0x0017
        L_0x0048:
            r0 = 6
            goto L_0x008b
        L_0x004a:
            java.lang.String r5 = "OMX.hisi.video.encoder.avc"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x0053
            goto L_0x0017
        L_0x0053:
            r0 = 5
            goto L_0x008b
        L_0x0055:
            java.lang.String r5 = "c2.exynos.h264.encoder"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x005e
            goto L_0x0017
        L_0x005e:
            r0 = 4
            goto L_0x008b
        L_0x0060:
            java.lang.String r5 = "OMX.qcom.video.encoder.hevc"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x0069
            goto L_0x0017
        L_0x0069:
            r0 = 3
            goto L_0x008b
        L_0x006b:
            java.lang.String r5 = "OMX.MTK.VIDEO.ENCODER.AVC"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x0074
            goto L_0x0017
        L_0x0074:
            r0 = 2
            goto L_0x008b
        L_0x0076:
            java.lang.String r5 = "OMX.Exynos.AVC.Encoder"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x007f
            goto L_0x0017
        L_0x007f:
            r0 = r11
            goto L_0x008b
        L_0x0081:
            java.lang.String r5 = "OMX.IMG.TOPAZ.VIDEO.Encoder"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x008a
            goto L_0x0017
        L_0x008a:
            r0 = r12
        L_0x008b:
            r14 = 4602903999154015437(0x3fe0cccccccccccd, double:0.525)
            java.lang.String r5 = "3840x2160"
            r16 = 4600764789331014451(0x3fd9333333333333, double:0.39375)
            r6 = 60
            r18 = 4597364571612349727(0x3fcd1eb851eb851f, double:0.2275)
            java.lang.String r7 = "1280x720"
            r20 = 4599976659396224614(0x3fd6666666666666, double:0.35)
            r8 = 30
            r22 = 4597995075560181596(0x3fcf5c28f5c28f5c, double:0.245)
            r24 = 4598715651500560876(0x3fd1eb851eb851ec, double:0.28)
            r26 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            java.lang.String r9 = "640x480"
            r29 = 4596734067664517857(0x3fcae147ae147ae1, double:0.21)
            java.lang.String r10 = "1920x1080"
            switch(r0) {
                case 0: goto L_0x181e;
                case 1: goto L_0x1489;
                case 2: goto L_0x1290;
                case 3: goto L_0x1255;
                case 4: goto L_0x1214;
                case 5: goto L_0x1180;
                case 6: goto L_0x0374;
                case 7: goto L_0x0353;
                case 8: goto L_0x00df;
                case 9: goto L_0x00c8;
                default: goto L_0x00c2;
            }
        L_0x00c2:
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x00c8:
            r33.hashCode()
            java.lang.String r0 = "Pixel 4"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x00d9
            r0 = 4592230468037147361(0x3fbae147ae147ae1, double:0.105)
            return r0
        L_0x00d9:
            r0 = 4590969460141483622(0x3fb6666666666666, double:0.0875)
            return r0
        L_0x00df:
            switch(r1) {
                case 29: goto L_0x02db;
                case 30: goto L_0x0204;
                case 31: goto L_0x0112;
                case 32: goto L_0x00e8;
                default: goto L_0x00e2;
            }
        L_0x00e2:
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x00e8:
            r33.hashCode()
            java.lang.String r0 = "Pixel 4"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0111
            java.lang.String r0 = "Pixel 5"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x00fc
            return r24
        L_0x00fc:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0110
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x010c
            return r24
        L_0x010c:
            if (r4 == r8) goto L_0x010f
            return r24
        L_0x010f:
            return r29
        L_0x0110:
            return r18
        L_0x0111:
            return r29
        L_0x0112:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -1069982836: goto L_0x01cb;
                case -870267800: goto L_0x01bf;
                case -407354123: goto L_0x01b3;
                case -78464376: goto L_0x01a7;
                case -78464345: goto L_0x019b;
                case -78464314: goto L_0x0190;
                case 401880992: goto L_0x0185;
                case 403762630: goto L_0x017a;
                case 432595513: goto L_0x016d;
                case 432602240: goto L_0x0160;
                case 775883933: goto L_0x0153;
                case 1105847545: goto L_0x0146;
                case 1105847546: goto L_0x0139;
                case 1105847547: goto L_0x012c;
                case 1905086331: goto L_0x011f;
                default: goto L_0x011c;
            }
        L_0x011c:
            r6 = r13
            goto L_0x01d6
        L_0x011f:
            java.lang.String r0 = "Pixel 3 XL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0128
            goto L_0x011c
        L_0x0128:
            r6 = 14
            goto L_0x01d6
        L_0x012c:
            java.lang.String r0 = "Pixel 5"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0135
            goto L_0x011c
        L_0x0135:
            r6 = 13
            goto L_0x01d6
        L_0x0139:
            java.lang.String r0 = "Pixel 4"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0142
            goto L_0x011c
        L_0x0142:
            r6 = 12
            goto L_0x01d6
        L_0x0146:
            java.lang.String r0 = "Pixel 3"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x014f
            goto L_0x011c
        L_0x014f:
            r6 = 11
            goto L_0x01d6
        L_0x0153:
            java.lang.String r0 = "SM-S908U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x015c
            goto L_0x011c
        L_0x015c:
            r6 = 10
            goto L_0x01d6
        L_0x0160:
            java.lang.String r0 = "SM-G998U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0169
            goto L_0x011c
        L_0x0169:
            r6 = 9
            goto L_0x01d6
        L_0x016d:
            java.lang.String r0 = "SM-G991U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0176
            goto L_0x011c
        L_0x0176:
            r6 = 8
            goto L_0x01d6
        L_0x017a:
            java.lang.String r0 = "SM-F926U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0183
            goto L_0x011c
        L_0x0183:
            r6 = 7
            goto L_0x01d6
        L_0x0185:
            java.lang.String r0 = "SM-F711U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x018e
            goto L_0x011c
        L_0x018e:
            r6 = 6
            goto L_0x01d6
        L_0x0190:
            java.lang.String r0 = "Pixel 5a"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0199
            goto L_0x011c
        L_0x0199:
            r6 = 5
            goto L_0x01d6
        L_0x019b:
            java.lang.String r0 = "Pixel 4a"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x01a5
            goto L_0x011c
        L_0x01a5:
            r6 = 4
            goto L_0x01d6
        L_0x01a7:
            java.lang.String r0 = "Pixel 3a"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x01b1
            goto L_0x011c
        L_0x01b1:
            r6 = 3
            goto L_0x01d6
        L_0x01b3:
            java.lang.String r0 = "SM-A528B"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x01bd
            goto L_0x011c
        L_0x01bd:
            r6 = 2
            goto L_0x01d6
        L_0x01bf:
            java.lang.String r0 = "Pixel 4a (5G)"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x01c9
            goto L_0x011c
        L_0x01c9:
            r6 = r11
            goto L_0x01d6
        L_0x01cb:
            java.lang.String r0 = "Pixel 3a XL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x01d5
            goto L_0x011c
        L_0x01d5:
            r6 = r12
        L_0x01d6:
            switch(r6) {
                case 0: goto L_0x0203;
                case 1: goto L_0x0202;
                case 2: goto L_0x01fc;
                case 3: goto L_0x0203;
                case 4: goto L_0x01fb;
                case 5: goto L_0x0202;
                case 6: goto L_0x0202;
                case 7: goto L_0x0202;
                case 8: goto L_0x01fb;
                case 9: goto L_0x0202;
                case 10: goto L_0x01f5;
                case 11: goto L_0x0203;
                case 12: goto L_0x01ef;
                case 13: goto L_0x01da;
                case 14: goto L_0x0203;
                default: goto L_0x01d9;
            }
        L_0x01d9:
            return r24
        L_0x01da:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x01ee
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x01ea
            return r24
        L_0x01ea:
            if (r4 == r8) goto L_0x01ed
            return r24
        L_0x01ed:
            return r29
        L_0x01ee:
            return r18
        L_0x01ef:
            r0 = 4596576621821544985(0x3fca52157689ca19, double:0.20563)
            return r0
        L_0x01f5:
            r0 = 4594212051873190380(0x3fc1eb851eb851ec, double:0.14)
            return r0
        L_0x01fb:
            return r29
        L_0x01fc:
            r0 = 4596418815690601923(0x3fc9c28f5c28f5c3, double:0.20125)
            return r0
        L_0x0202:
            return r18
        L_0x0203:
            return r22
        L_0x0204:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -78464314: goto L_0x0252;
                case 401880992: goto L_0x0247;
                case 403762630: goto L_0x023c;
                case 432595513: goto L_0x0231;
                case 1105847546: goto L_0x0226;
                case 1105847547: goto L_0x021b;
                case 1905116122: goto L_0x0210;
                default: goto L_0x020e;
            }
        L_0x020e:
            r6 = r13
            goto L_0x025c
        L_0x0210:
            java.lang.String r0 = "Pixel 4 XL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0219
            goto L_0x020e
        L_0x0219:
            r6 = 6
            goto L_0x025c
        L_0x021b:
            java.lang.String r0 = "Pixel 5"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0224
            goto L_0x020e
        L_0x0224:
            r6 = 5
            goto L_0x025c
        L_0x0226:
            java.lang.String r0 = "Pixel 4"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x022f
            goto L_0x020e
        L_0x022f:
            r6 = 4
            goto L_0x025c
        L_0x0231:
            java.lang.String r0 = "SM-G991U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x023a
            goto L_0x020e
        L_0x023a:
            r6 = 3
            goto L_0x025c
        L_0x023c:
            java.lang.String r0 = "SM-F926U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0245
            goto L_0x020e
        L_0x0245:
            r6 = 2
            goto L_0x025c
        L_0x0247:
            java.lang.String r0 = "SM-F711U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0250
            goto L_0x020e
        L_0x0250:
            r6 = r11
            goto L_0x025c
        L_0x0252:
            java.lang.String r0 = "Pixel 5a"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x025b
            goto L_0x020e
        L_0x025b:
            r6 = r12
        L_0x025c:
            switch(r6) {
                case 0: goto L_0x02c6;
                case 1: goto L_0x0296;
                case 2: goto L_0x0284;
                case 3: goto L_0x0283;
                case 4: goto L_0x0278;
                case 5: goto L_0x0261;
                case 6: goto L_0x0260;
                default: goto L_0x025f;
            }
        L_0x025f:
            return r26
        L_0x0260:
            return r29
        L_0x0261:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0272
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0271
            return r24
        L_0x0271:
            return r18
        L_0x0272:
            r0 = 4596418815690601923(0x3fc9c28f5c28f5c3, double:0.20125)
            return r0
        L_0x0278:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0282
            return r20
        L_0x0282:
            return r29
        L_0x0283:
            return r18
        L_0x0284:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0295
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0294
            return r26
        L_0x0294:
            return r18
        L_0x0295:
            return r29
        L_0x0296:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x02b4;
                case -1719904874: goto L_0x02ab;
                case 802059049: goto L_0x02a2;
                default: goto L_0x02a0;
            }
        L_0x02a0:
            r10 = r13
            goto L_0x02bc
        L_0x02a2:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x02a9
            goto L_0x02a0
        L_0x02a9:
            r10 = 2
            goto L_0x02bc
        L_0x02ab:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x02b2
            goto L_0x02a0
        L_0x02b2:
            r10 = r11
            goto L_0x02bc
        L_0x02b4:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x02bb
            goto L_0x02a0
        L_0x02bb:
            r10 = r12
        L_0x02bc:
            switch(r10) {
                case 0: goto L_0x02c5;
                case 1: goto L_0x02c4;
                case 2: goto L_0x02c0;
                default: goto L_0x02bf;
            }
        L_0x02bf:
            return r26
        L_0x02c0:
            if (r4 == r8) goto L_0x02c3
            return r26
        L_0x02c3:
            return r22
        L_0x02c4:
            return r18
        L_0x02c5:
            return r29
        L_0x02c6:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x02da
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x02d6
            return r24
        L_0x02d6:
            if (r4 == r8) goto L_0x02d9
            return r24
        L_0x02d9:
            return r29
        L_0x02da:
            return r18
        L_0x02db:
            r33.hashCode()
            java.lang.String r0 = "Pixel 3"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0341
            java.lang.String r0 = "Pixel 4"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x02f4
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x02f4:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x031b;
                case -1719904874: goto L_0x0312;
                case 802059049: goto L_0x0309;
                case 1514345136: goto L_0x0300;
                default: goto L_0x02fe;
            }
        L_0x02fe:
            r9 = r13
            goto L_0x0323
        L_0x0300:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0307
            goto L_0x02fe
        L_0x0307:
            r9 = 3
            goto L_0x0323
        L_0x0309:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0310
            goto L_0x02fe
        L_0x0310:
            r9 = 2
            goto L_0x0323
        L_0x0312:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0319
            goto L_0x02fe
        L_0x0319:
            r9 = r11
            goto L_0x0323
        L_0x031b:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0322
            goto L_0x02fe
        L_0x0322:
            r9 = r12
        L_0x0323:
            switch(r9) {
                case 0: goto L_0x033b;
                case 1: goto L_0x033b;
                case 2: goto L_0x0332;
                case 3: goto L_0x032c;
                default: goto L_0x0326;
            }
        L_0x0326:
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x032c:
            r0 = 4590936313648226176(0x3fb64840e1719f80, double:0.08704)
            return r0
        L_0x0332:
            if (r4 == r8) goto L_0x033a
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x033a:
            return r29
        L_0x033b:
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x0341:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0352
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0351
            return r26
        L_0x0351:
            return r22
        L_0x0352:
            return r29
        L_0x0353:
            r0 = 28
            if (r1 == r0) goto L_0x035d
            r0 = 4589708452245819884(0x3fb1eb851eb851ec, double:0.07)
            return r0
        L_0x035d:
            r33.hashCode()
            java.lang.String r0 = "SM-G965N"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x036e
            r0 = 4589708452245819884(0x3fb1eb851eb851ec, double:0.07)
            return r0
        L_0x036e:
            r0 = 4587726868409776865(0x3faae147ae147ae1, double:0.0525)
            return r0
        L_0x0374:
            switch(r1) {
                case 23: goto L_0x106f;
                case 24: goto L_0x0fe0;
                case 25: goto L_0x0f20;
                case 26: goto L_0x0b17;
                case 27: goto L_0x08fa;
                case 28: goto L_0x0503;
                case 29: goto L_0x0441;
                case 30: goto L_0x0398;
                case 31: goto L_0x0397;
                case 32: goto L_0x037d;
                default: goto L_0x0377;
            }
        L_0x0377:
            r0 = 4607407598781385933(0x3ff0cccccccccccd, double:1.05)
            return r0
        L_0x037d:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0391
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x038d
            return r20
        L_0x038d:
            if (r4 == r8) goto L_0x0390
            return r20
        L_0x0390:
            return r18
        L_0x0391:
            r0 = 4594212051873190380(0x3fc1eb851eb851ec, double:0.14)
            return r0
        L_0x0397:
            return r29
        L_0x0398:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -2038157987: goto L_0x040c;
                case -407445445: goto L_0x0401;
                case -407295591: goto L_0x03f6;
                case -396392085: goto L_0x03eb;
                case -395223463: goto L_0x03e0;
                case 403732839: goto L_0x03d5;
                case 768522173: goto L_0x03ca;
                case 1241611957: goto L_0x03bf;
                case 1465879103: goto L_0x03b2;
                case 1691568319: goto L_0x03a5;
                default: goto L_0x03a2;
            }
        L_0x03a2:
            r6 = r13
            goto L_0x0416
        L_0x03a5:
            java.lang.String r0 = "CPH2127"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x03ae
            goto L_0x03a2
        L_0x03ae:
            r6 = 9
            goto L_0x0416
        L_0x03b2:
            java.lang.String r0 = "M2101K7AG"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x03bb
            goto L_0x03a2
        L_0x03bb:
            r6 = 8
            goto L_0x0416
        L_0x03bf:
            java.lang.String r0 = "Redmi Note 9S"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x03c8
            goto L_0x03a2
        L_0x03c8:
            r6 = 7
            goto L_0x0416
        L_0x03ca:
            java.lang.String r0 = "SM-S115DL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x03d3
            goto L_0x03a2
        L_0x03d3:
            r6 = 6
            goto L_0x0416
        L_0x03d5:
            java.lang.String r0 = "SM-F916U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x03de
            goto L_0x03a2
        L_0x03de:
            r6 = 5
            goto L_0x0416
        L_0x03e0:
            java.lang.String r0 = "SM-N986U"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x03e9
            goto L_0x03a2
        L_0x03e9:
            r6 = 4
            goto L_0x0416
        L_0x03eb:
            java.lang.String r0 = "SM-M115F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x03f4
            goto L_0x03a2
        L_0x03f4:
            r6 = 3
            goto L_0x0416
        L_0x03f6:
            java.lang.String r0 = "SM-A715F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x03ff
            goto L_0x03a2
        L_0x03ff:
            r6 = 2
            goto L_0x0416
        L_0x0401:
            java.lang.String r0 = "SM-A207F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x040a
            goto L_0x03a2
        L_0x040a:
            r6 = r11
            goto L_0x0416
        L_0x040c:
            java.lang.String r0 = "Redmi Note 8"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0415
            goto L_0x03a2
        L_0x0415:
            r6 = r12
        L_0x0416:
            switch(r6) {
                case 0: goto L_0x0440;
                case 1: goto L_0x043a;
                case 2: goto L_0x0440;
                case 3: goto L_0x043a;
                case 4: goto L_0x0434;
                case 5: goto L_0x041a;
                case 6: goto L_0x043a;
                case 7: goto L_0x0440;
                case 8: goto L_0x0440;
                case 9: goto L_0x0440;
                default: goto L_0x0419;
            }
        L_0x0419:
            return r20
        L_0x041a:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x042e
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x042a
            return r20
        L_0x042a:
            if (r4 == r8) goto L_0x042d
            return r20
        L_0x042d:
            return r18
        L_0x042e:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x0434:
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x043a:
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x0440:
            return r18
        L_0x0441:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -1546863963: goto L_0x04b5;
                case -1288580290: goto L_0x04aa;
                case -478880298: goto L_0x049f;
                case -395224492: goto L_0x0494;
                case -78463250: goto L_0x0489;
                case 432505179: goto L_0x047e;
                case 432565722: goto L_0x0473;
                case 602019019: goto L_0x0468;
                case 1691546241: goto L_0x045b;
                case 1905056540: goto L_0x044e;
                default: goto L_0x044b;
            }
        L_0x044b:
            r6 = r13
            goto L_0x04bf
        L_0x044e:
            java.lang.String r0 = "Pixel 2 XL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0457
            goto L_0x044b
        L_0x0457:
            r6 = 9
            goto L_0x04bf
        L_0x045b:
            java.lang.String r0 = "CPH1931"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0464
            goto L_0x044b
        L_0x0464:
            r6 = 8
            goto L_0x04bf
        L_0x0468:
            java.lang.String r0 = "Redmi Note 9 Pro"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0471
            goto L_0x044b
        L_0x0471:
            r6 = 7
            goto L_0x04bf
        L_0x0473:
            java.lang.String r0 = "SM-G981U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x047c
            goto L_0x044b
        L_0x047c:
            r6 = 6
            goto L_0x04bf
        L_0x047e:
            java.lang.String r0 = "SM-G960U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0487
            goto L_0x044b
        L_0x0487:
            r6 = 5
            goto L_0x04bf
        L_0x0489:
            java.lang.String r0 = "Pixel XL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0492
            goto L_0x044b
        L_0x0492:
            r6 = 4
            goto L_0x04bf
        L_0x0494:
            java.lang.String r0 = "SM-N9750"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x049d
            goto L_0x044b
        L_0x049d:
            r6 = 3
            goto L_0x04bf
        L_0x049f:
            java.lang.String r0 = "moto g(7)"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x04a8
            goto L_0x044b
        L_0x04a8:
            r6 = 2
            goto L_0x04bf
        L_0x04aa:
            java.lang.String r0 = "moto g(7) play"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x04b3
            goto L_0x044b
        L_0x04b3:
            r6 = r11
            goto L_0x04bf
        L_0x04b5:
            java.lang.String r0 = "Redmi 8"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x04be
            goto L_0x044b
        L_0x04be:
            r6 = r12
        L_0x04bf:
            switch(r6) {
                case 0: goto L_0x0502;
                case 1: goto L_0x04fc;
                case 2: goto L_0x04fc;
                case 3: goto L_0x04fb;
                case 4: goto L_0x04f5;
                case 5: goto L_0x04f5;
                case 6: goto L_0x04e0;
                case 7: goto L_0x04df;
                case 8: goto L_0x04df;
                case 9: goto L_0x04c3;
                default: goto L_0x04c2;
            }
        L_0x04c2:
            return r26
        L_0x04c3:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x04d9
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x04d3
            return r26
        L_0x04d3:
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x04d9:
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x04df:
            return r18
        L_0x04e0:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x04f4
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x04f0
            return r26
        L_0x04f0:
            if (r4 == r8) goto L_0x04f3
            return r26
        L_0x04f3:
            return r18
        L_0x04f4:
            return r20
        L_0x04f5:
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x04fb:
            return r29
        L_0x04fc:
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x0502:
            return r20
        L_0x0503:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -1910414559: goto L_0x06a9;
                case -1890032429: goto L_0x069d;
                case -1855483508: goto L_0x0691;
                case -1855483477: goto L_0x0685;
                case -1850865933: goto L_0x0679;
                case -1850865872: goto L_0x066d;
                case -1809870076: goto L_0x0661;
                case -1398045836: goto L_0x0655;
                case -1288579676: goto L_0x0647;
                case -509282085: goto L_0x0639;
                case -399073275: goto L_0x062b;
                case -389717862: goto L_0x061d;
                case -389716901: goto L_0x060f;
                case -78464376: goto L_0x0601;
                case 53196885: goto L_0x05f3;
                case 68211431: goto L_0x05e5;
                case 68211586: goto L_0x05d7;
                case 68213353: goto L_0x05c9;
                case 68243389: goto L_0x05bb;
                case 74271988: goto L_0x05ad;
                case 78881479: goto L_0x059f;
                case 206187613: goto L_0x0591;
                case 513630441: goto L_0x0584;
                case 599248456: goto L_0x0577;
                case 632879445: goto L_0x056a;
                case 979275507: goto L_0x055d;
                case 979419631: goto L_0x0550;
                case 1105847545: goto L_0x0543;
                case 1311300219: goto L_0x0536;
                case 1311330013: goto L_0x0529;
                case 1682561561: goto L_0x051d;
                case 1905056540: goto L_0x0510;
                default: goto L_0x050d;
            }
        L_0x050d:
            r0 = r13
            goto L_0x06b4
        L_0x0510:
            java.lang.String r0 = "Pixel 2 XL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0519
            goto L_0x050d
        L_0x0519:
            r0 = 31
            goto L_0x06b4
        L_0x051d:
            java.lang.String r0 = "ASUS_X00TD"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0526
            goto L_0x050d
        L_0x0526:
            r0 = r8
            goto L_0x06b4
        L_0x0529:
            java.lang.String r0 = "ONEPLUS A6013"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0532
            goto L_0x050d
        L_0x0532:
            r0 = 29
            goto L_0x06b4
        L_0x0536:
            java.lang.String r0 = "ONEPLUS A5010"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x053f
            goto L_0x050d
        L_0x053f:
            r0 = 28
            goto L_0x06b4
        L_0x0543:
            java.lang.String r0 = "Pixel 3"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x054c
            goto L_0x050d
        L_0x054c:
            r0 = 27
            goto L_0x06b4
        L_0x0550:
            java.lang.String r0 = "LM-V405"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0559
            goto L_0x050d
        L_0x0559:
            r0 = 26
            goto L_0x06b4
        L_0x055d:
            java.lang.String r0 = "LM-Q910"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0566
            goto L_0x050d
        L_0x0566:
            r0 = 25
            goto L_0x06b4
        L_0x056a:
            java.lang.String r0 = "SM-N950U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0573
            goto L_0x050d
        L_0x0573:
            r0 = 24
            goto L_0x06b4
        L_0x0577:
            java.lang.String r0 = "Redmi Note 6 Pro"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0580
            goto L_0x050d
        L_0x0580:
            r0 = 23
            goto L_0x06b4
        L_0x0584:
            java.lang.String r0 = "SM-J415FN"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x058d
            goto L_0x050d
        L_0x058d:
            r0 = 22
            goto L_0x06b4
        L_0x0591:
            java.lang.String r0 = "Nokia 7.2"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x059b
            goto L_0x050d
        L_0x059b:
            r0 = 21
            goto L_0x06b4
        L_0x059f:
            java.lang.String r0 = "SHV39"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x05a9
            goto L_0x050d
        L_0x05a9:
            r0 = 20
            goto L_0x06b4
        L_0x05ad:
            java.lang.String r0 = "Mi A1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x05b7
            goto L_0x050d
        L_0x05b7:
            r0 = 19
            goto L_0x06b4
        L_0x05bb:
            java.lang.String r0 = "H9493"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x05c5
            goto L_0x050d
        L_0x05c5:
            r0 = 18
            goto L_0x06b4
        L_0x05c9:
            java.lang.String r0 = "H8416"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x05d3
            goto L_0x050d
        L_0x05d3:
            r0 = 17
            goto L_0x06b4
        L_0x05d7:
            java.lang.String r0 = "H8266"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x05e1
            goto L_0x050d
        L_0x05e1:
            r0 = 16
            goto L_0x06b4
        L_0x05e5:
            java.lang.String r0 = "H8216"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x05ef
            goto L_0x050d
        L_0x05ef:
            r0 = 15
            goto L_0x06b4
        L_0x05f3:
            java.lang.String r0 = "801SO"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x05fd
            goto L_0x050d
        L_0x05fd:
            r0 = 14
            goto L_0x06b4
        L_0x0601:
            java.lang.String r0 = "Pixel 3a"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x060b
            goto L_0x050d
        L_0x060b:
            r0 = 13
            goto L_0x06b4
        L_0x060f:
            java.lang.String r0 = "SM-T837V"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0619
            goto L_0x050d
        L_0x0619:
            r0 = 12
            goto L_0x06b4
        L_0x061d:
            java.lang.String r0 = "SM-T827V"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0627
            goto L_0x050d
        L_0x0627:
            r0 = 11
            goto L_0x06b4
        L_0x062b:
            java.lang.String r0 = "SM-J415F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0635
            goto L_0x050d
        L_0x0635:
            r0 = 10
            goto L_0x06b4
        L_0x0639:
            java.lang.String r0 = "Nokia 9"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0643
            goto L_0x050d
        L_0x0643:
            r0 = 9
            goto L_0x06b4
        L_0x0647:
            java.lang.String r0 = "moto g(7) plus"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0651
            goto L_0x050d
        L_0x0651:
            r0 = 8
            goto L_0x06b4
        L_0x0655:
            java.lang.String r0 = "SM-T720"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x065f
            goto L_0x050d
        L_0x065f:
            r0 = 7
            goto L_0x06b4
        L_0x0661:
            java.lang.String r0 = "U693CL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x066b
            goto L_0x050d
        L_0x066b:
            r0 = 6
            goto L_0x06b4
        L_0x066d:
            java.lang.String r0 = "SH-03K"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0677
            goto L_0x050d
        L_0x0677:
            r0 = 5
            goto L_0x06b4
        L_0x0679:
            java.lang.String r0 = "SH-01L"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0683
            goto L_0x050d
        L_0x0683:
            r0 = 4
            goto L_0x06b4
        L_0x0685:
            java.lang.String r0 = "SC-03K"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x068f
            goto L_0x050d
        L_0x068f:
            r0 = 3
            goto L_0x06b4
        L_0x0691:
            java.lang.String r0 = "SC-02K"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x069b
            goto L_0x050d
        L_0x069b:
            r0 = 2
            goto L_0x06b4
        L_0x069d:
            java.lang.String r0 = "MI MAX 3"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x06a7
            goto L_0x050d
        L_0x06a7:
            r0 = r11
            goto L_0x06b4
        L_0x06a9:
            java.lang.String r0 = "MI 8 Pro"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x06b3
            goto L_0x050d
        L_0x06b3:
            r0 = r12
        L_0x06b4:
            switch(r0) {
                case 0: goto L_0x08e7;
                case 1: goto L_0x08d2;
                case 2: goto L_0x08e7;
                case 3: goto L_0x08c1;
                case 4: goto L_0x08a7;
                case 5: goto L_0x0888;
                case 6: goto L_0x0882;
                case 7: goto L_0x0868;
                case 8: goto L_0x082e;
                case 9: goto L_0x0825;
                case 10: goto L_0x0882;
                case 11: goto L_0x0810;
                case 12: goto L_0x07e0;
                case 13: goto L_0x07dc;
                case 14: goto L_0x07d1;
                case 15: goto L_0x07c3;
                case 16: goto L_0x07b8;
                case 17: goto L_0x07ad;
                case 18: goto L_0x07d1;
                case 19: goto L_0x0798;
                case 20: goto L_0x075e;
                case 21: goto L_0x0729;
                case 22: goto L_0x0882;
                case 23: goto L_0x0729;
                case 24: goto L_0x0728;
                case 25: goto L_0x07e0;
                case 26: goto L_0x0715;
                case 27: goto L_0x06f6;
                case 28: goto L_0x06eb;
                case 29: goto L_0x06d4;
                case 30: goto L_0x06bd;
                case 31: goto L_0x07e0;
                default: goto L_0x06b7;
            }
        L_0x06b7:
            r0 = 4607407598781385933(0x3ff0cccccccccccd, double:1.05)
            return r0
        L_0x06bd:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x06d1
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x06cd
            return r26
        L_0x06cd:
            if (r4 == r8) goto L_0x06d0
            return r26
        L_0x06d0:
            return r22
        L_0x06d1:
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x06d4:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x06ea
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x06e4
            return r20
        L_0x06e4:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x06ea:
            return r29
        L_0x06eb:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x06f5
            return r18
        L_0x06f5:
            return r29
        L_0x06f6:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x070c
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0706
            return r29
        L_0x0706:
            r0 = 4591563935292296528(0x3fb883126e978d50, double:0.09575)
            return r0
        L_0x070c:
            if (r4 == r8) goto L_0x070f
            return r29
        L_0x070f:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x0715:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x071f
            return r20
        L_0x071f:
            if (r4 == r8) goto L_0x0722
            return r29
        L_0x0722:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x0728:
            return r18
        L_0x0729:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x0747;
                case 802059049: goto L_0x073e;
                case 1514345136: goto L_0x0735;
                default: goto L_0x0733;
            }
        L_0x0733:
            r10 = r13
            goto L_0x074f
        L_0x0735:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x073c
            goto L_0x0733
        L_0x073c:
            r10 = 2
            goto L_0x074f
        L_0x073e:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0745
            goto L_0x0733
        L_0x0745:
            r10 = r11
            goto L_0x074f
        L_0x0747:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x074e
            goto L_0x0733
        L_0x074e:
            r10 = r12
        L_0x074f:
            switch(r10) {
                case 0: goto L_0x075d;
                case 1: goto L_0x0759;
                case 2: goto L_0x0753;
                default: goto L_0x0752;
            }
        L_0x0752:
            return r26
        L_0x0753:
            r0 = 4596067534919667024(0x3fc883126e978d50, double:0.1915)
            return r0
        L_0x0759:
            if (r4 == r8) goto L_0x075c
            return r26
        L_0x075c:
            return r22
        L_0x075d:
            return r24
        L_0x075e:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x077c;
                case -1719904874: goto L_0x0773;
                case 802059049: goto L_0x076a;
                default: goto L_0x0768;
            }
        L_0x0768:
            r10 = r13
            goto L_0x0784
        L_0x076a:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0771
            goto L_0x0768
        L_0x0771:
            r10 = 2
            goto L_0x0784
        L_0x0773:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x077a
            goto L_0x0768
        L_0x077a:
            r10 = r11
            goto L_0x0784
        L_0x077c:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0783
            goto L_0x0768
        L_0x0783:
            r10 = r12
        L_0x0784:
            switch(r10) {
                case 0: goto L_0x0792;
                case 1: goto L_0x078c;
                case 2: goto L_0x0788;
                default: goto L_0x0787;
            }
        L_0x0787:
            return r26
        L_0x0788:
            if (r4 == r8) goto L_0x078b
            return r26
        L_0x078b:
            return r22
        L_0x078c:
            r0 = 4600370814435612080(0x3fd7cce1c58255b0, double:0.37188)
            return r0
        L_0x0792:
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x0798:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x07ac
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x07a8
            return r26
        L_0x07a8:
            if (r4 == r8) goto L_0x07ab
            return r26
        L_0x07ab:
            return r24
        L_0x07ac:
            return r18
        L_0x07ad:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x07b7
            return r16
        L_0x07b7:
            return r20
        L_0x07b8:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x07c2
            return r14
        L_0x07c2:
            return r20
        L_0x07c3:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x07cd
            return r14
        L_0x07cd:
            if (r4 == r8) goto L_0x07d0
            return r14
        L_0x07d0:
            return r20
        L_0x07d1:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x07db
            return r20
        L_0x07db:
            return r29
        L_0x07dc:
            if (r4 == r8) goto L_0x07df
            return r26
        L_0x07df:
            return r22
        L_0x07e0:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x07fe;
                case -1719904874: goto L_0x07f5;
                case 802059049: goto L_0x07ec;
                default: goto L_0x07ea;
            }
        L_0x07ea:
            r10 = r13
            goto L_0x0806
        L_0x07ec:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x07f3
            goto L_0x07ea
        L_0x07f3:
            r10 = 2
            goto L_0x0806
        L_0x07f5:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x07fc
            goto L_0x07ea
        L_0x07fc:
            r10 = r11
            goto L_0x0806
        L_0x07fe:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0805
            goto L_0x07ea
        L_0x0805:
            r10 = r12
        L_0x0806:
            switch(r10) {
                case 0: goto L_0x080f;
                case 1: goto L_0x080e;
                case 2: goto L_0x080a;
                default: goto L_0x0809;
            }
        L_0x0809:
            return r26
        L_0x080a:
            if (r4 == r8) goto L_0x080d
            return r26
        L_0x080d:
            return r22
        L_0x080e:
            return r20
        L_0x080f:
            return r29
        L_0x0810:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0824
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0820
            return r26
        L_0x0820:
            if (r4 == r8) goto L_0x0823
            return r26
        L_0x0823:
            return r22
        L_0x0824:
            return r18
        L_0x0825:
            if (r4 == r8) goto L_0x0828
            return r29
        L_0x0828:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x082e:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x084c;
                case 802059049: goto L_0x0843;
                case 1514345136: goto L_0x083a;
                default: goto L_0x0838;
            }
        L_0x0838:
            r10 = r13
            goto L_0x0854
        L_0x083a:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0841
            goto L_0x0838
        L_0x0841:
            r10 = 2
            goto L_0x0854
        L_0x0843:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x084a
            goto L_0x0838
        L_0x084a:
            r10 = r11
            goto L_0x0854
        L_0x084c:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0853
            goto L_0x0838
        L_0x0853:
            r10 = r12
        L_0x0854:
            switch(r10) {
                case 0: goto L_0x0862;
                case 1: goto L_0x085e;
                case 2: goto L_0x0858;
                default: goto L_0x0857;
            }
        L_0x0857:
            return r26
        L_0x0858:
            r0 = 4596694796275767187(0x3fcabd9018e75793, double:0.20891)
            return r0
        L_0x085e:
            if (r4 == r8) goto L_0x0861
            return r26
        L_0x0861:
            return r24
        L_0x0862:
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x0868:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0881
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0878
            return r20
        L_0x0878:
            if (r4 == r8) goto L_0x087b
            return r29
        L_0x087b:
            r0 = 4595788311742770053(0x3fc7851eb851eb85, double:0.18375)
            return r0
        L_0x0881:
            return r29
        L_0x0882:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x0888:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x08a6
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x089d
            r0 = 4607407598781385933(0x3ff0cccccccccccd, double:1.05)
            return r0
        L_0x089d:
            if (r4 == r6) goto L_0x08a5
            r0 = 4607407598781385933(0x3ff0cccccccccccd, double:1.05)
            return r0
        L_0x08a5:
            return r26
        L_0x08a6:
            return r14
        L_0x08a7:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x08bb
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x08b7
            return r26
        L_0x08b7:
            if (r4 == r8) goto L_0x08ba
            return r26
        L_0x08ba:
            return r24
        L_0x08bb:
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x08c1:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x08d1
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x08d1
            return r20
        L_0x08d1:
            return r29
        L_0x08d2:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x08e6
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x08e2
            return r26
        L_0x08e2:
            if (r4 == r8) goto L_0x08e5
            return r26
        L_0x08e5:
            return r22
        L_0x08e6:
            return r24
        L_0x08e7:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x08f1
            return r29
        L_0x08f1:
            if (r4 == r8) goto L_0x08f4
            return r29
        L_0x08f4:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x08fa:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -2038157990: goto L_0x09c2;
                case -1578412503: goto L_0x09b6;
                case -1157762381: goto L_0x09aa;
                case -929944936: goto L_0x099e;
                case -398982863: goto L_0x0992;
                case 2568591: goto L_0x0986;
                case 66034788: goto L_0x097b;
                case 136552289: goto L_0x0970;
                case 206182807: goto L_0x0963;
                case 340385120: goto L_0x0955;
                case 511927773: goto L_0x0948;
                case 632909236: goto L_0x093b;
                case 1105847544: goto L_0x092e;
                case 1467059320: goto L_0x0921;
                case 1682561561: goto L_0x0914;
                case 1905056540: goto L_0x0907;
                default: goto L_0x0904;
            }
        L_0x0904:
            r0 = r13
            goto L_0x09cd
        L_0x0907:
            java.lang.String r0 = "Pixel 2 XL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0910
            goto L_0x0904
        L_0x0910:
            r0 = 15
            goto L_0x09cd
        L_0x0914:
            java.lang.String r0 = "ASUS_X00TD"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x091d
            goto L_0x0904
        L_0x091d:
            r0 = 14
            goto L_0x09cd
        L_0x0921:
            java.lang.String r0 = "Redmi 5 Plus"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x092a
            goto L_0x0904
        L_0x092a:
            r0 = 13
            goto L_0x09cd
        L_0x092e:
            java.lang.String r0 = "Pixel 2"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0937
            goto L_0x0904
        L_0x0937:
            r0 = 12
            goto L_0x09cd
        L_0x093b:
            java.lang.String r0 = "SM-N960U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0944
            goto L_0x0904
        L_0x0944:
            r0 = 11
            goto L_0x09cd
        L_0x0948:
            java.lang.String r0 = "SM-J260MU"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0951
            goto L_0x0904
        L_0x0951:
            r0 = 10
            goto L_0x09cd
        L_0x0955:
            java.lang.String r0 = "vivo 1805"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x095f
            goto L_0x0904
        L_0x095f:
            r0 = 9
            goto L_0x09cd
        L_0x0963:
            java.lang.String r0 = "Nokia 2.1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x096c
            goto L_0x0904
        L_0x096c:
            r0 = 8
            goto L_0x09cd
        L_0x0970:
            java.lang.String r0 = "moto e5 play"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0979
            goto L_0x0904
        L_0x0979:
            r0 = 7
            goto L_0x09cd
        L_0x097b:
            java.lang.String r0 = "F-01L"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0984
            goto L_0x0904
        L_0x0984:
            r0 = 6
            goto L_0x09cd
        L_0x0986:
            java.lang.String r0 = "TC77"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0990
            goto L_0x0904
        L_0x0990:
            r0 = 5
            goto L_0x09cd
        L_0x0992:
            java.lang.String r0 = "SM-J727V"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x099c
            goto L_0x0904
        L_0x099c:
            r0 = 4
            goto L_0x09cd
        L_0x099e:
            java.lang.String r0 = "Moto Z3 Play"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x09a8
            goto L_0x0904
        L_0x09a8:
            r0 = 3
            goto L_0x09cd
        L_0x09aa:
            java.lang.String r0 = "Lenovo TB-8504F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x09b4
            goto L_0x0904
        L_0x09b4:
            r0 = 2
            goto L_0x09cd
        L_0x09b6:
            java.lang.String r0 = "DUB-LX1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x09c0
            goto L_0x0904
        L_0x09c0:
            r0 = r11
            goto L_0x09cd
        L_0x09c2:
            java.lang.String r0 = "Redmi Note 5"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x09cc
            goto L_0x0904
        L_0x09cc:
            r0 = r12
        L_0x09cd:
            switch(r0) {
                case 0: goto L_0x0b13;
                case 1: goto L_0x0b12;
                case 2: goto L_0x0afb;
                case 3: goto L_0x0aeb;
                case 4: goto L_0x0ae0;
                case 5: goto L_0x0a9c;
                case 6: goto L_0x0a62;
                case 7: goto L_0x0afb;
                case 8: goto L_0x0a4b;
                case 9: goto L_0x0a3a;
                case 10: goto L_0x0a34;
                case 11: goto L_0x0a1a;
                case 12: goto L_0x0a19;
                case 13: goto L_0x09fa;
                case 14: goto L_0x09e5;
                case 15: goto L_0x09d1;
                default: goto L_0x09d0;
            }
        L_0x09d0:
            return r26
        L_0x09d1:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x09e4
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x09e1
            return r22
        L_0x09e1:
            if (r4 == r6) goto L_0x09e4
            return r22
        L_0x09e4:
            return r29
        L_0x09e5:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x09f4
            r0 = 4601946894161206659(0x3fdd66516db0dd83, double:0.45937)
            return r0
        L_0x09f4:
            if (r4 == r8) goto L_0x09f9
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x09f9:
            return r24
        L_0x09fa:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0a15
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0a0f
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x0a0f:
            r0 = 4595440273563566861(0x3fc64894c447c30d, double:0.17409)
            return r0
        L_0x0a15:
            if (r4 == r6) goto L_0x0a18
            return r24
        L_0x0a18:
            return r22
        L_0x0a19:
            return r29
        L_0x0a1a:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0a33
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0a2a
            return r20
        L_0x0a2a:
            if (r4 == r8) goto L_0x0a2d
            return r29
        L_0x0a2d:
            r0 = 4595788311742770053(0x3fc7851eb851eb85, double:0.18375)
            return r0
        L_0x0a33:
            return r29
        L_0x0a34:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x0a3a:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0a4a
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0a4a
            return r20
        L_0x0a4a:
            return r29
        L_0x0a4b:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0a61
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0a60
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x0a60:
            return r20
        L_0x0a61:
            return r16
        L_0x0a62:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x0a80;
                case -1719904874: goto L_0x0a77;
                case 802059049: goto L_0x0a6e;
                default: goto L_0x0a6c;
            }
        L_0x0a6c:
            r10 = r13
            goto L_0x0a88
        L_0x0a6e:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0a75
            goto L_0x0a6c
        L_0x0a75:
            r10 = 2
            goto L_0x0a88
        L_0x0a77:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0a7e
            goto L_0x0a6c
        L_0x0a7e:
            r10 = r11
            goto L_0x0a88
        L_0x0a80:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0a87
            goto L_0x0a6c
        L_0x0a87:
            r10 = r12
        L_0x0a88:
            switch(r10) {
                case 0: goto L_0x0a96;
                case 1: goto L_0x0a90;
                case 2: goto L_0x0a8c;
                default: goto L_0x0a8b;
            }
        L_0x0a8b:
            return r26
        L_0x0a8c:
            if (r4 == r8) goto L_0x0a8f
            return r26
        L_0x0a8f:
            return r24
        L_0x0a90:
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x0a96:
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x0a9c:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x0ac3;
                case -1719904874: goto L_0x0aba;
                case 802059049: goto L_0x0ab1;
                case 1514345136: goto L_0x0aa8;
                default: goto L_0x0aa6;
            }
        L_0x0aa6:
            r9 = r13
            goto L_0x0acb
        L_0x0aa8:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0aaf
            goto L_0x0aa6
        L_0x0aaf:
            r9 = 3
            goto L_0x0acb
        L_0x0ab1:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0ab8
            goto L_0x0aa6
        L_0x0ab8:
            r9 = 2
            goto L_0x0acb
        L_0x0aba:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0ac1
            goto L_0x0aa6
        L_0x0ac1:
            r9 = r11
            goto L_0x0acb
        L_0x0ac3:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0aca
            goto L_0x0aa6
        L_0x0aca:
            r9 = r12
        L_0x0acb:
            switch(r9) {
                case 0: goto L_0x0adf;
                case 1: goto L_0x0ad9;
                case 2: goto L_0x0ad5;
                case 3: goto L_0x0acf;
                default: goto L_0x0ace;
            }
        L_0x0ace:
            return r26
        L_0x0acf:
            r0 = 4596067534919667024(0x3fc883126e978d50, double:0.1915)
            return r0
        L_0x0ad5:
            if (r4 == r8) goto L_0x0ad8
            return r26
        L_0x0ad8:
            return r24
        L_0x0ad9:
            r0 = 4603692129088805274(0x3fe399999999999a, double:0.6125)
            return r0
        L_0x0adf:
            return r16
        L_0x0ae0:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0aea
            return r24
        L_0x0aea:
            return r29
        L_0x0aeb:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0af5
            return r26
        L_0x0af5:
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x0afb:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0b11
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0b10
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x0b10:
            return r20
        L_0x0b11:
            return r16
        L_0x0b12:
            return r18
        L_0x0b13:
            if (r4 == r6) goto L_0x0b16
            return r24
        L_0x0b16:
            return r22
        L_0x0b17:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -1899903032: goto L_0x0d2e;
                case -1844401288: goto L_0x0d22;
                case -615500520: goto L_0x0d16;
                case -615476526: goto L_0x0d0a;
                case -478880329: goto L_0x0cfe;
                case -407235225: goto L_0x0cf2;
                case -407235203: goto L_0x0ce6;
                case -401717969: goto L_0x0cda;
                case -401717934: goto L_0x0ccc;
                case -401717084: goto L_0x0cbe;
                case -401717064: goto L_0x0cb0;
                case -401693100: goto L_0x0ca2;
                case -401692947: goto L_0x0c94;
                case -401691024: goto L_0x0c86;
                case -401691022: goto L_0x0c78;
                case -401690100: goto L_0x0c6a;
                case -395226532: goto L_0x0c5c;
                case 66365407: goto L_0x0c4e;
                case 66365408: goto L_0x0c40;
                case 67288960: goto L_0x0c32;
                case 67289920: goto L_0x0c24;
                case 68212390: goto L_0x0c16;
                case 68212421: goto L_0x0c08;
                case 73318370: goto L_0x0bfa;
                case 77128294: goto L_0x0bec;
                case 79090010: goto L_0x0bde;
                case 136552289: goto L_0x0bd0;
                case 432420521: goto L_0x0bc2;
                case 432475388: goto L_0x0bb4;
                case 432480193: goto L_0x0ba6;
                case 432505179: goto L_0x0b99;
                case 432509984: goto L_0x0b8c;
                case 740519914: goto L_0x0b7f;
                case 807232546: goto L_0x0b72;
                case 1105847544: goto L_0x0b65;
                case 1311240609: goto L_0x0b58;
                case 1743099574: goto L_0x0b4b;
                case 1882314478: goto L_0x0b3e;
                case 2005978936: goto L_0x0b31;
                case 2140902952: goto L_0x0b24;
                default: goto L_0x0b21;
            }
        L_0x0b21:
            r0 = r13
            goto L_0x0d39
        L_0x0b24:
            java.lang.String r0 = "HTC 10"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b2d
            goto L_0x0b21
        L_0x0b2d:
            r0 = 39
            goto L_0x0d39
        L_0x0b31:
            java.lang.String r0 = "MI 5s Plus"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b3a
            goto L_0x0b21
        L_0x0b3a:
            r0 = 38
            goto L_0x0d39
        L_0x0b3e:
            java.lang.String r0 = "HTC U11 plus"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b47
            goto L_0x0b21
        L_0x0b47:
            r0 = 37
            goto L_0x0d39
        L_0x0b4b:
            java.lang.String r0 = "Nokia 8 Sirocco"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b54
            goto L_0x0b21
        L_0x0b54:
            r0 = 36
            goto L_0x0d39
        L_0x0b58:
            java.lang.String r0 = "ONEPLUS A3003"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b61
            goto L_0x0b21
        L_0x0b61:
            r0 = 35
            goto L_0x0d39
        L_0x0b65:
            java.lang.String r0 = "Pixel 2"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b6e
            goto L_0x0b21
        L_0x0b6e:
            r0 = 34
            goto L_0x0d39
        L_0x0b72:
            java.lang.String r0 = "LG-H932"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b7b
            goto L_0x0b21
        L_0x0b7b:
            r0 = 33
            goto L_0x0d39
        L_0x0b7f:
            java.lang.String r0 = "Mi MIX 2"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b88
            goto L_0x0b21
        L_0x0b88:
            r0 = 32
            goto L_0x0d39
        L_0x0b8c:
            java.lang.String r0 = "SM-G965U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0b95
            goto L_0x0b21
        L_0x0b95:
            r0 = 31
            goto L_0x0d39
        L_0x0b99:
            java.lang.String r0 = "SM-G960U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0ba3
            goto L_0x0b21
        L_0x0ba3:
            r0 = r8
            goto L_0x0d39
        L_0x0ba6:
            java.lang.String r0 = "SM-G955U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0bb0
            goto L_0x0b21
        L_0x0bb0:
            r0 = 29
            goto L_0x0d39
        L_0x0bb4:
            java.lang.String r0 = "SM-G950U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0bbe
            goto L_0x0b21
        L_0x0bbe:
            r0 = 28
            goto L_0x0d39
        L_0x0bc2:
            java.lang.String r0 = "SM-G935R4"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0bcc
            goto L_0x0b21
        L_0x0bcc:
            r0 = 27
            goto L_0x0d39
        L_0x0bd0:
            java.lang.String r0 = "moto e5 play"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0bda
            goto L_0x0b21
        L_0x0bda:
            r0 = 26
            goto L_0x0d39
        L_0x0bde:
            java.lang.String r0 = "SOV33"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0be8
            goto L_0x0b21
        L_0x0be8:
            r0 = 25
            goto L_0x0d39
        L_0x0bec:
            java.lang.String r0 = "Pixel"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0bf6
            goto L_0x0b21
        L_0x0bf6:
            r0 = 24
            goto L_0x0d39
        L_0x0bfa:
            java.lang.String r0 = "MI 5s"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c04
            goto L_0x0b21
        L_0x0c04:
            r0 = 23
            goto L_0x0d39
        L_0x0c08:
            java.lang.String r0 = "H8324"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c12
            goto L_0x0b21
        L_0x0c12:
            r0 = 22
            goto L_0x0d39
        L_0x0c16:
            java.lang.String r0 = "H8314"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c20
            goto L_0x0b21
        L_0x0c20:
            r0 = 21
            goto L_0x0d39
        L_0x0c24:
            java.lang.String r0 = "G8441"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c2e
            goto L_0x0b21
        L_0x0c2e:
            r0 = 20
            goto L_0x0d39
        L_0x0c32:
            java.lang.String r0 = "G8342"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c3c
            goto L_0x0b21
        L_0x0c3c:
            r0 = 19
            goto L_0x0d39
        L_0x0c40:
            java.lang.String r0 = "F8332"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c4a
            goto L_0x0b21
        L_0x0c4a:
            r0 = 18
            goto L_0x0d39
        L_0x0c4e:
            java.lang.String r0 = "F8331"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c58
            goto L_0x0b21
        L_0x0c58:
            r0 = 17
            goto L_0x0d39
        L_0x0c5c:
            java.lang.String r0 = "SM-N950U"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c66
            goto L_0x0b21
        L_0x0c66:
            r0 = 16
            goto L_0x0d39
        L_0x0c6a:
            java.lang.String r0 = "SM-G9650"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c74
            goto L_0x0b21
        L_0x0c74:
            r0 = 15
            goto L_0x0d39
        L_0x0c78:
            java.lang.String r0 = "SM-G955W"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c82
            goto L_0x0b21
        L_0x0c82:
            r0 = 14
            goto L_0x0d39
        L_0x0c86:
            java.lang.String r0 = "SM-G955U"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c90
            goto L_0x0b21
        L_0x0c90:
            r0 = 13
            goto L_0x0d39
        L_0x0c94:
            java.lang.String r0 = "SM-G935T"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0c9e
            goto L_0x0b21
        L_0x0c9e:
            r0 = 12
            goto L_0x0d39
        L_0x0ca2:
            java.lang.String r0 = "SM-G930V"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0cac
            goto L_0x0b21
        L_0x0cac:
            r0 = 11
            goto L_0x0d39
        L_0x0cb0:
            java.lang.String r0 = "SM-G892U"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0cba
            goto L_0x0b21
        L_0x0cba:
            r0 = 10
            goto L_0x0d39
        L_0x0cbe:
            java.lang.String r0 = "SM-G892A"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0cc8
            goto L_0x0b21
        L_0x0cc8:
            r0 = 9
            goto L_0x0d39
        L_0x0ccc:
            java.lang.String r0 = "SM-G885S"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0cd6
            goto L_0x0b21
        L_0x0cd6:
            r0 = 8
            goto L_0x0d39
        L_0x0cda:
            java.lang.String r0 = "SM-G8850"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0ce4
            goto L_0x0b21
        L_0x0ce4:
            r0 = 7
            goto L_0x0d39
        L_0x0ce6:
            java.lang.String r0 = "SM-A920F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0cf0
            goto L_0x0b21
        L_0x0cf0:
            r0 = 6
            goto L_0x0d39
        L_0x0cf2:
            java.lang.String r0 = "SM-A9200"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0cfc
            goto L_0x0b21
        L_0x0cfc:
            r0 = 5
            goto L_0x0d39
        L_0x0cfe:
            java.lang.String r0 = "moto g(6)"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0d08
            goto L_0x0b21
        L_0x0d08:
            r0 = 4
            goto L_0x0d39
        L_0x0d0a:
            java.lang.String r0 = "SAMSUNG-SM-G930A"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0d14
            goto L_0x0b21
        L_0x0d14:
            r0 = 3
            goto L_0x0d39
        L_0x0d16:
            java.lang.String r0 = "SAMSUNG-SM-G891A"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0d20
            goto L_0x0b21
        L_0x0d20:
            r0 = 2
            goto L_0x0d39
        L_0x0d22:
            java.lang.String r0 = "SO-01J"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0d2c
            goto L_0x0b21
        L_0x0d2c:
            r0 = r11
            goto L_0x0d39
        L_0x0d2e:
            java.lang.String r0 = "SAMSUNG-SM-G930AZ"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0d38
            goto L_0x0b21
        L_0x0d38:
            r0 = r12
        L_0x0d39:
            switch(r0) {
                case 0: goto L_0x0f1c;
                case 1: goto L_0x0f0e;
                case 2: goto L_0x0f0d;
                case 3: goto L_0x0ef8;
                case 4: goto L_0x0ef7;
                case 5: goto L_0x0ee9;
                case 6: goto L_0x0ee9;
                case 7: goto L_0x0ed4;
                case 8: goto L_0x0ebf;
                case 9: goto L_0x0eab;
                case 10: goto L_0x0f1c;
                case 11: goto L_0x0ef7;
                case 12: goto L_0x0f0e;
                case 13: goto L_0x0eaa;
                case 14: goto L_0x0ef7;
                case 15: goto L_0x0e97;
                case 16: goto L_0x0eaa;
                case 17: goto L_0x0e62;
                case 18: goto L_0x0f0e;
                case 19: goto L_0x0ef8;
                case 20: goto L_0x0e57;
                case 21: goto L_0x0e4c;
                case 22: goto L_0x0e43;
                case 23: goto L_0x0ef8;
                case 24: goto L_0x0f0e;
                case 25: goto L_0x0e2c;
                case 26: goto L_0x0e15;
                case 27: goto L_0x0f1c;
                case 28: goto L_0x0eaa;
                case 29: goto L_0x0de0;
                case 30: goto L_0x0da6;
                case 31: goto L_0x0d6c;
                case 32: goto L_0x0f1c;
                case 33: goto L_0x0e4c;
                case 34: goto L_0x0e57;
                case 35: goto L_0x0f1c;
                case 36: goto L_0x0d58;
                case 37: goto L_0x0d43;
                case 38: goto L_0x0d42;
                case 39: goto L_0x0ef7;
                default: goto L_0x0d3c;
            }
        L_0x0d3c:
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x0d42:
            return r18
        L_0x0d43:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0d57
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0d53
            return r24
        L_0x0d53:
            if (r4 == r8) goto L_0x0d56
            return r24
        L_0x0d56:
            return r22
        L_0x0d57:
            return r18
        L_0x0d58:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0d6b
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0d68
            return r24
        L_0x0d68:
            if (r4 == r6) goto L_0x0d6b
            return r22
        L_0x0d6b:
            return r29
        L_0x0d6c:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x0d8a;
                case 802059049: goto L_0x0d81;
                case 1514345136: goto L_0x0d78;
                default: goto L_0x0d76;
            }
        L_0x0d76:
            r10 = r13
            goto L_0x0d92
        L_0x0d78:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0d7f
            goto L_0x0d76
        L_0x0d7f:
            r10 = 2
            goto L_0x0d92
        L_0x0d81:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0d88
            goto L_0x0d76
        L_0x0d88:
            r10 = r11
            goto L_0x0d92
        L_0x0d8a:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0d91
            goto L_0x0d76
        L_0x0d91:
            r10 = r12
        L_0x0d92:
            switch(r10) {
                case 0: goto L_0x0da5;
                case 1: goto L_0x0d9c;
                case 2: goto L_0x0d96;
                default: goto L_0x0d95;
            }
        L_0x0d95:
            return r18
        L_0x0d96:
            r0 = 4591563935292296528(0x3fb883126e978d50, double:0.09575)
            return r0
        L_0x0d9c:
            if (r4 == r8) goto L_0x0d9f
            return r29
        L_0x0d9f:
            r0 = 4595788311742770053(0x3fc7851eb851eb85, double:0.18375)
            return r0
        L_0x0da5:
            return r29
        L_0x0da6:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x0dc4;
                case 802059049: goto L_0x0dbb;
                case 1514345136: goto L_0x0db2;
                default: goto L_0x0db0;
            }
        L_0x0db0:
            r10 = r13
            goto L_0x0dcc
        L_0x0db2:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0db9
            goto L_0x0db0
        L_0x0db9:
            r10 = 2
            goto L_0x0dcc
        L_0x0dbb:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0dc2
            goto L_0x0db0
        L_0x0dc2:
            r10 = r11
            goto L_0x0dcc
        L_0x0dc4:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0dcb
            goto L_0x0db0
        L_0x0dcb:
            r10 = r12
        L_0x0dcc:
            switch(r10) {
                case 0: goto L_0x0ddf;
                case 1: goto L_0x0dd6;
                case 2: goto L_0x0dd0;
                default: goto L_0x0dcf;
            }
        L_0x0dcf:
            return r20
        L_0x0dd0:
            r0 = 4591563935292296528(0x3fb883126e978d50, double:0.09575)
            return r0
        L_0x0dd6:
            if (r4 == r8) goto L_0x0dd9
            return r29
        L_0x0dd9:
            r0 = 4595788311742770053(0x3fc7851eb851eb85, double:0.18375)
            return r0
        L_0x0ddf:
            return r29
        L_0x0de0:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x0dfe;
                case -1719904874: goto L_0x0df5;
                case 802059049: goto L_0x0dec;
                default: goto L_0x0dea;
            }
        L_0x0dea:
            r10 = r13
            goto L_0x0e06
        L_0x0dec:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0df3
            goto L_0x0dea
        L_0x0df3:
            r10 = 2
            goto L_0x0e06
        L_0x0df5:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0dfc
            goto L_0x0dea
        L_0x0dfc:
            r10 = r11
            goto L_0x0e06
        L_0x0dfe:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0e05
            goto L_0x0dea
        L_0x0e05:
            r10 = r12
        L_0x0e06:
            switch(r10) {
                case 0: goto L_0x0e14;
                case 1: goto L_0x0e0e;
                case 2: goto L_0x0e0a;
                default: goto L_0x0e09;
            }
        L_0x0e09:
            return r24
        L_0x0e0a:
            if (r4 == r8) goto L_0x0e0d
            return r24
        L_0x0e0d:
            return r22
        L_0x0e0e:
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x0e14:
            return r29
        L_0x0e15:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0e2b
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0e2a
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x0e2a:
            return r20
        L_0x0e2b:
            return r16
        L_0x0e2c:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0e42
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0e3c
            return r24
        L_0x0e3c:
            r0 = 4590936313648226176(0x3fb64840e1719f80, double:0.08704)
            return r0
        L_0x0e42:
            return r18
        L_0x0e43:
            if (r4 == r8) goto L_0x0e46
            return r29
        L_0x0e46:
            r0 = 4595788311742770053(0x3fc7851eb851eb85, double:0.18375)
            return r0
        L_0x0e4c:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0e56
            return r18
        L_0x0e56:
            return r29
        L_0x0e57:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0e61
            return r24
        L_0x0e61:
            return r18
        L_0x0e62:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x0e80;
                case 802059049: goto L_0x0e77;
                case 1514345136: goto L_0x0e6e;
                default: goto L_0x0e6c;
            }
        L_0x0e6c:
            r10 = r13
            goto L_0x0e88
        L_0x0e6e:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x0e75
            goto L_0x0e6c
        L_0x0e75:
            r10 = 2
            goto L_0x0e88
        L_0x0e77:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0e7e
            goto L_0x0e6c
        L_0x0e7e:
            r10 = r11
            goto L_0x0e88
        L_0x0e80:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0e87
            goto L_0x0e6c
        L_0x0e87:
            r10 = r12
        L_0x0e88:
            switch(r10) {
                case 0: goto L_0x0e96;
                case 1: goto L_0x0e92;
                case 2: goto L_0x0e8c;
                default: goto L_0x0e8b;
            }
        L_0x0e8b:
            return r22
        L_0x0e8c:
            r0 = 4590936313648226176(0x3fb64840e1719f80, double:0.08704)
            return r0
        L_0x0e92:
            if (r4 == r6) goto L_0x0e95
            return r22
        L_0x0e95:
            return r29
        L_0x0e96:
            return r18
        L_0x0e97:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0ea1
            return r29
        L_0x0ea1:
            if (r4 == r8) goto L_0x0ea4
            return r29
        L_0x0ea4:
            r0 = 4595788311742770053(0x3fc7851eb851eb85, double:0.18375)
            return r0
        L_0x0eaa:
            return r29
        L_0x0eab:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0ebe
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0ebb
            return r22
        L_0x0ebb:
            if (r4 == r6) goto L_0x0ebe
            return r22
        L_0x0ebe:
            return r29
        L_0x0ebf:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0ed3
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0ecf
            return r20
        L_0x0ecf:
            if (r4 == r8) goto L_0x0ed2
            return r24
        L_0x0ed2:
            return r22
        L_0x0ed3:
            return r24
        L_0x0ed4:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0ee8
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0ee4
            return r20
        L_0x0ee4:
            if (r4 == r6) goto L_0x0ee7
            return r22
        L_0x0ee7:
            return r29
        L_0x0ee8:
            return r24
        L_0x0ee9:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0ef3
            return r24
        L_0x0ef3:
            if (r4 == r6) goto L_0x0ef6
            return r22
        L_0x0ef6:
            return r29
        L_0x0ef7:
            return r18
        L_0x0ef8:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x0f0c
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0f08
            return r22
        L_0x0f08:
            if (r4 == r6) goto L_0x0f0b
            return r22
        L_0x0f0b:
            return r29
        L_0x0f0c:
            return r18
        L_0x0f0d:
            return r24
        L_0x0f0e:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0f18
            return r24
        L_0x0f18:
            if (r4 == r8) goto L_0x0f1b
            return r24
        L_0x0f1b:
            return r22
        L_0x0f1c:
            if (r4 == r6) goto L_0x0f1f
            return r22
        L_0x0f1f:
            return r29
        L_0x0f20:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -708142656: goto L_0x0fae;
                case -708142633: goto L_0x0fa3;
                case -708142625: goto L_0x0f98;
                case -399017595: goto L_0x0f8d;
                case -78463250: goto L_0x0f82;
                case 2453916: goto L_0x0f77;
                case 67287038: goto L_0x0f6c;
                case 77128294: goto L_0x0f60;
                case 514549157: goto L_0x0f53;
                case 1311300188: goto L_0x0f46;
                case 1467059320: goto L_0x0f39;
                case 1691545187: goto L_0x0f2c;
                default: goto L_0x0f2a;
            }
        L_0x0f2a:
            goto L_0x0fb8
        L_0x0f2c:
            java.lang.String r0 = "CPH1801"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f36
            goto L_0x0fb8
        L_0x0f36:
            r0 = 11
            goto L_0x0f6a
        L_0x0f39:
            java.lang.String r0 = "Redmi 5 Plus"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f43
            goto L_0x0fb8
        L_0x0f43:
            r0 = 10
            goto L_0x0f6a
        L_0x0f46:
            java.lang.String r0 = "ONEPLUS A5000"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f50
            goto L_0x0fb8
        L_0x0f50:
            r0 = 9
            goto L_0x0f6a
        L_0x0f53:
            java.lang.String r0 = "SM-J510FN"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f5d
            goto L_0x0fb8
        L_0x0f5d:
            r0 = 8
            goto L_0x0f6a
        L_0x0f60:
            java.lang.String r0 = "Pixel"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f69
            goto L_0x0fb8
        L_0x0f69:
            r0 = 7
        L_0x0f6a:
            r13 = r0
            goto L_0x0fb8
        L_0x0f6c:
            java.lang.String r0 = "G8142"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f75
            goto L_0x0fb8
        L_0x0f75:
            r13 = 6
            goto L_0x0fb8
        L_0x0f77:
            java.lang.String r0 = "PH-1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f80
            goto L_0x0fb8
        L_0x0f80:
            r13 = 5
            goto L_0x0fb8
        L_0x0f82:
            java.lang.String r0 = "Pixel XL"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f8b
            goto L_0x0fb8
        L_0x0f8b:
            r13 = 4
            goto L_0x0fb8
        L_0x0f8d:
            java.lang.String r0 = "LM-X210(G)"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0f96
            goto L_0x0fb8
        L_0x0f96:
            r13 = 3
            goto L_0x0fb8
        L_0x0f98:
            java.lang.String r0 = "Redmi 5A"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0fa1
            goto L_0x0fb8
        L_0x0fa1:
            r13 = 2
            goto L_0x0fb8
        L_0x0fa3:
            java.lang.String r0 = "Redmi 4X"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0fac
            goto L_0x0fb8
        L_0x0fac:
            r13 = r11
            goto L_0x0fb8
        L_0x0fae:
            java.lang.String r0 = "Redmi 4A"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0fb7
            goto L_0x0fb8
        L_0x0fb7:
            r13 = r12
        L_0x0fb8:
            switch(r13) {
                case 0: goto L_0x0fdf;
                case 1: goto L_0x0fdf;
                case 2: goto L_0x0fdf;
                case 3: goto L_0x0fdf;
                case 4: goto L_0x0fc6;
                case 5: goto L_0x0fbd;
                case 6: goto L_0x0fc6;
                case 7: goto L_0x0fc6;
                case 8: goto L_0x0fbc;
                case 9: goto L_0x0fbc;
                case 10: goto L_0x0fbc;
                case 11: goto L_0x0fbc;
                default: goto L_0x0fbb;
            }
        L_0x0fbb:
            return r14
        L_0x0fbc:
            return r20
        L_0x0fbd:
            if (r4 == r6) goto L_0x0fc5
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x0fc5:
            return r16
        L_0x0fc6:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x0fdf
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x0fd6
            return r14
        L_0x0fd6:
            if (r4 == r8) goto L_0x0fd9
            return r14
        L_0x0fd9:
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x0fdf:
            return r16
        L_0x0fe0:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -1696512900: goto L_0x1011;
                case -401692945: goto L_0x1005;
                case 3272512: goto L_0x0ff9;
                case 1490819771: goto L_0x0fed;
                default: goto L_0x0fea;
            }
        L_0x0fea:
            r28 = r13
            goto L_0x101c
        L_0x0fed:
            java.lang.String r0 = "Nexus 6P"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0ff6
            goto L_0x0fea
        L_0x0ff6:
            r28 = 3
            goto L_0x101c
        L_0x0ff9:
            java.lang.String r0 = "Moto G (5)"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1002
            goto L_0x0fea
        L_0x1002:
            r28 = 2
            goto L_0x101c
        L_0x1005:
            java.lang.String r0 = "SM-G935V"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x100e
            goto L_0x0fea
        L_0x100e:
            r28 = r11
            goto L_0x101c
        L_0x1011:
            java.lang.String r0 = "XT1650"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x101a
            goto L_0x0fea
        L_0x101a:
            r28 = r12
        L_0x101c:
            switch(r28) {
                case 0: goto L_0x1066;
                case 1: goto L_0x1060;
                case 2: goto L_0x105f;
                case 3: goto L_0x1025;
                default: goto L_0x101f;
            }
        L_0x101f:
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x1025:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x1043;
                case 802059049: goto L_0x103a;
                case 1514345136: goto L_0x1031;
                default: goto L_0x102f;
            }
        L_0x102f:
            r10 = r13
            goto L_0x104b
        L_0x1031:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x1038
            goto L_0x102f
        L_0x1038:
            r10 = 2
            goto L_0x104b
        L_0x103a:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1041
            goto L_0x102f
        L_0x1041:
            r10 = r11
            goto L_0x104b
        L_0x1043:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x104a
            goto L_0x102f
        L_0x104a:
            r10 = r12
        L_0x104b:
            switch(r10) {
                case 0: goto L_0x1059;
                case 1: goto L_0x1055;
                case 2: goto L_0x104f;
                default: goto L_0x104e;
            }
        L_0x104e:
            return r16
        L_0x104f:
            r0 = 4596694796275767187(0x3fcabd9018e75793, double:0.20891)
            return r0
        L_0x1055:
            if (r4 == r8) goto L_0x1058
            return r16
        L_0x1058:
            return r20
        L_0x1059:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x105f:
            return r16
        L_0x1060:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x1066:
            if (r4 == r6) goto L_0x106e
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x106e:
            return r16
        L_0x106f:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -1984447159: goto L_0x10ca;
                case -783193036: goto L_0x10bf;
                case -751286836: goto L_0x10b4;
                case -401695999: goto L_0x10a9;
                case 3272481: goto L_0x109e;
                case 102694224: goto L_0x1093;
                case 340383224: goto L_0x1087;
                case 1490819771: goto L_0x107b;
                default: goto L_0x1079;
            }
        L_0x1079:
            goto L_0x10d4
        L_0x107b:
            java.lang.String r0 = "Nexus 6P"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1084
            goto L_0x10d4
        L_0x1084:
            r0 = 7
            r13 = r0
            goto L_0x10d4
        L_0x1087:
            java.lang.String r0 = "vivo 1610"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1091
            goto L_0x10d4
        L_0x1091:
            r13 = 6
            goto L_0x10d4
        L_0x1093:
            java.lang.String r0 = "Moto G Play"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x109c
            goto L_0x10d4
        L_0x109c:
            r13 = 5
            goto L_0x10d4
        L_0x109e:
            java.lang.String r0 = "Moto G (4)"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x10a7
            goto L_0x10d4
        L_0x10a7:
            r13 = 4
            goto L_0x10d4
        L_0x10a9:
            java.lang.String r0 = "SM-G900F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x10b2
            goto L_0x10d4
        L_0x10b2:
            r13 = 3
            goto L_0x10d4
        L_0x10b4:
            java.lang.String r0 = "LG-AS110"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x10bd
            goto L_0x10d4
        L_0x10bd:
            r13 = 2
            goto L_0x10d4
        L_0x10bf:
            java.lang.String r0 = "Nexus 5"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x10c8
            goto L_0x10d4
        L_0x10c8:
            r13 = r11
            goto L_0x10d4
        L_0x10ca:
            java.lang.String r0 = "MotoG3"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x10d3
            goto L_0x10d4
        L_0x10d3:
            r13 = r12
        L_0x10d4:
            switch(r13) {
                case 0: goto L_0x117a;
                case 1: goto L_0x115b;
                case 2: goto L_0x1144;
                case 3: goto L_0x1143;
                case 4: goto L_0x112e;
                case 5: goto L_0x1116;
                case 6: goto L_0x10fc;
                case 7: goto L_0x10dd;
                default: goto L_0x10d7;
            }
        L_0x10d7:
            r0 = 4603692129088805274(0x3fe399999999999a, double:0.6125)
            return r0
        L_0x10dd:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x10f6
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x10f2
            r0 = 4603692129088805274(0x3fe399999999999a, double:0.6125)
            return r0
        L_0x10f2:
            if (r4 == r8) goto L_0x10f5
            return r16
        L_0x10f5:
            return r20
        L_0x10f6:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x10fc:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x110b
            r0 = 4603101076673709170(0x3fe1800a7c5ac472, double:0.54688)
            return r0
        L_0x110b:
            if (r4 == r6) goto L_0x1110
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x1110:
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x1116:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1125
            r0 = 4600370814435612080(0x3fd7cce1c58255b0, double:0.37188)
            return r0
        L_0x1125:
            if (r4 == r6) goto L_0x1128
            return r20
        L_0x1128:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x112e:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x113a
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x113a:
            if (r4 == r6) goto L_0x113d
            return r20
        L_0x113d:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x1143:
            return r20
        L_0x1144:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x115a
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1159
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x1159:
            return r20
        L_0x115a:
            return r16
        L_0x115b:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x1174
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1170
            r0 = 4602341049200594125(0x3fdecccccccccccd, double:0.48125)
            return r0
        L_0x1170:
            if (r4 == r6) goto L_0x1173
            return r20
        L_0x1173:
            return r24
        L_0x1174:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x117a:
            r0 = 4600370814435612080(0x3fd7cce1c58255b0, double:0.37188)
            return r0
        L_0x1180:
            r0 = 24
            if (r1 == r0) goto L_0x11fd
            r0 = 27
            if (r1 == r0) goto L_0x11a4
            r0 = 28
            if (r1 == r0) goto L_0x1192
            r0 = 4603298064121410355(0x3fe2333333333333, double:0.56875)
            return r0
        L_0x1192:
            r33.hashCode()
            java.lang.String r0 = "HMA-L29"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x119e
            return r29
        L_0x119e:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x11a4:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case 1588010961: goto L_0x11c5;
                case 1672051372: goto L_0x11ba;
                case 1832520436: goto L_0x11af;
                default: goto L_0x11ae;
            }
        L_0x11ae:
            goto L_0x11cf
        L_0x11af:
            java.lang.String r0 = "EML-AL00"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x11b8
            goto L_0x11cf
        L_0x11b8:
            r13 = 2
            goto L_0x11cf
        L_0x11ba:
            java.lang.String r0 = "COR-L29"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x11c3
            goto L_0x11cf
        L_0x11c3:
            r13 = r11
            goto L_0x11cf
        L_0x11c5:
            java.lang.String r0 = "CLT-L29"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x11ce
            goto L_0x11cf
        L_0x11ce:
            r13 = r12
        L_0x11cf:
            switch(r13) {
                case 0: goto L_0x11f7;
                case 1: goto L_0x11d3;
                case 2: goto L_0x11f7;
                default: goto L_0x11d2;
            }
        L_0x11d2:
            return r22
        L_0x11d3:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x11f1
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x11e3
            return r22
        L_0x11e3:
            if (r4 == r6) goto L_0x11eb
            r0 = 4594212051873190380(0x3fc1eb851eb851ec, double:0.14)
            return r0
        L_0x11eb:
            r0 = 4593896799899274445(0x3fc0cccccccccccd, double:0.13125)
            return r0
        L_0x11f1:
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x11f7:
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x11fd:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x1213
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1212
            r0 = 4603298064121410355(0x3fe2333333333333, double:0.56875)
            return r0
        L_0x1212:
            return r22
        L_0x1213:
            return r14
        L_0x1214:
            switch(r1) {
                case 31: goto L_0x124a;
                case 32: goto L_0x1219;
                case 33: goto L_0x1218;
                default: goto L_0x1217;
            }
        L_0x1217:
            return r26
        L_0x1218:
            return r22
        L_0x1219:
            r33.hashCode()
            java.lang.String r0 = "Pixel 6 Pro"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x123f
            java.lang.String r0 = "Pixel 6"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x122d
            return r26
        L_0x122d:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x123e
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x123d
            return r26
        L_0x123d:
            return r22
        L_0x123e:
            return r24
        L_0x123f:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x1249
            return r26
        L_0x1249:
            return r22
        L_0x124a:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x1254
            return r26
        L_0x1254:
            return r22
        L_0x1255:
            switch(r1) {
                case 26: goto L_0x1271;
                case 27: goto L_0x126b;
                case 28: goto L_0x1259;
                default: goto L_0x1258;
            }
        L_0x1258:
            return r20
        L_0x1259:
            r33.hashCode()
            java.lang.String r0 = "Nokia 7.2"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1265
            return r20
        L_0x1265:
            r0 = 4594842555821022249(0x3fc428f5c28f5c29, double:0.1575)
            return r0
        L_0x126b:
            r0 = 4594842555821022249(0x3fc428f5c28f5c29, double:0.1575)
            return r0
        L_0x1271:
            r33.hashCode()
            java.lang.String r0 = "F8331"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x128a
            java.lang.String r0 = "MI 5s"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x128a
            r0 = 4594842555821022249(0x3fc428f5c28f5c29, double:0.1575)
            return r0
        L_0x128a:
            r0 = 4592230468037147361(0x3fbae147ae147ae1, double:0.105)
            return r0
        L_0x1290:
            switch(r1) {
                case 22: goto L_0x1488;
                case 23: goto L_0x1440;
                case 24: goto L_0x142e;
                case 25: goto L_0x1293;
                case 26: goto L_0x1293;
                case 27: goto L_0x13b2;
                case 28: goto L_0x13ac;
                case 29: goto L_0x1324;
                case 30: goto L_0x1294;
                default: goto L_0x1293;
            }
        L_0x1293:
            return r26
        L_0x1294:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -1390526813: goto L_0x12fc;
                case -407502129: goto L_0x12f1;
                case -407475236: goto L_0x12e6;
                case -407475229: goto L_0x12db;
                case -407473376: goto L_0x12d0;
                case -407413794: goto L_0x12c5;
                case 48050974: goto L_0x12ba;
                case 340386080: goto L_0x12ae;
                case 1255552637: goto L_0x12a1;
                default: goto L_0x129e;
            }
        L_0x129e:
            r6 = r13
            goto L_0x1307
        L_0x12a1:
            java.lang.String r0 = "M1908C3JGG"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x12aa
            goto L_0x129e
        L_0x12aa:
            r6 = 8
            goto L_0x1307
        L_0x12ae:
            java.lang.String r0 = "vivo 1904"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x12b8
            goto L_0x129e
        L_0x12b8:
            r6 = 7
            goto L_0x1307
        L_0x12ba:
            java.lang.String r0 = "W-K610-TVM"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x12c3
            goto L_0x129e
        L_0x12c3:
            r6 = 6
            goto L_0x1307
        L_0x12c5:
            java.lang.String r0 = "SM-A325F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x12ce
            goto L_0x129e
        L_0x12ce:
            r6 = 5
            goto L_0x1307
        L_0x12d0:
            java.lang.String r0 = "SM-A125F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x12d9
            goto L_0x129e
        L_0x12d9:
            r6 = 4
            goto L_0x1307
        L_0x12db:
            java.lang.String r0 = "SM-A107M"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x12e4
            goto L_0x129e
        L_0x12e4:
            r6 = 3
            goto L_0x1307
        L_0x12e6:
            java.lang.String r0 = "SM-A107F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x12ef
            goto L_0x129e
        L_0x12ef:
            r6 = 2
            goto L_0x1307
        L_0x12f1:
            java.lang.String r0 = "SM-A037U"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x12fa
            goto L_0x129e
        L_0x12fa:
            r6 = r11
            goto L_0x1307
        L_0x12fc:
            java.lang.String r0 = "wembley_2GB_full"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1306
            goto L_0x129e
        L_0x1306:
            r6 = r12
        L_0x1307:
            switch(r6) {
                case 0: goto L_0x1312;
                case 1: goto L_0x1311;
                case 2: goto L_0x1311;
                case 3: goto L_0x1311;
                case 4: goto L_0x1311;
                case 5: goto L_0x130b;
                case 6: goto L_0x1311;
                case 7: goto L_0x1311;
                case 8: goto L_0x130b;
                default: goto L_0x130a;
            }
        L_0x130a:
            return r26
        L_0x130b:
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x1311:
            return r24
        L_0x1312:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x1323
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x1322
            return r26
        L_0x1322:
            return r14
        L_0x1323:
            return r20
        L_0x1324:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -407504183: goto L_0x138b;
                case 132773294: goto L_0x1380;
                case 254121476: goto L_0x1375;
                case 582751196: goto L_0x136a;
                case 674860964: goto L_0x135f;
                case 791410201: goto L_0x1354;
                case 979092886: goto L_0x1349;
                case 979273647: goto L_0x133e;
                case 1691568476: goto L_0x1331;
                default: goto L_0x132e;
            }
        L_0x132e:
            r6 = r13
            goto L_0x1395
        L_0x1331:
            java.lang.String r0 = "CPH2179"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x133a
            goto L_0x132e
        L_0x133a:
            r6 = 8
            goto L_0x1395
        L_0x133e:
            java.lang.String r0 = "LM-Q730"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1347
            goto L_0x132e
        L_0x1347:
            r6 = 7
            goto L_0x1395
        L_0x1349:
            java.lang.String r0 = "LM-K500"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1352
            goto L_0x132e
        L_0x1352:
            r6 = 6
            goto L_0x1395
        L_0x1354:
            java.lang.String r0 = "Infinix X688B"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x135d
            goto L_0x132e
        L_0x135d:
            r6 = 5
            goto L_0x1395
        L_0x135f:
            java.lang.String r0 = "k61v1_basic_ref"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1368
            goto L_0x132e
        L_0x1368:
            r6 = 4
            goto L_0x1395
        L_0x136a:
            java.lang.String r0 = "M2006C3LG"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1373
            goto L_0x132e
        L_0x1373:
            r6 = 3
            goto L_0x1395
        L_0x1375:
            java.lang.String r0 = "SM-A215U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x137e
            goto L_0x132e
        L_0x137e:
            r6 = 2
            goto L_0x1395
        L_0x1380:
            java.lang.String r0 = "TECNO KE5"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1389
            goto L_0x132e
        L_0x1389:
            r6 = r11
            goto L_0x1395
        L_0x138b:
            java.lang.String r0 = "SM-A013M"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1394
            goto L_0x132e
        L_0x1394:
            r6 = r12
        L_0x1395:
            switch(r6) {
                case 0: goto L_0x13ab;
                case 1: goto L_0x13ab;
                case 2: goto L_0x13ab;
                case 3: goto L_0x13ab;
                case 4: goto L_0x1399;
                case 5: goto L_0x13ab;
                case 6: goto L_0x13ab;
                case 7: goto L_0x13ab;
                case 8: goto L_0x13ab;
                default: goto L_0x1398;
            }
        L_0x1398:
            return r26
        L_0x1399:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x13aa
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x13a9
            return r26
        L_0x13a9:
            return r14
        L_0x13aa:
            return r20
        L_0x13ab:
            return r24
        L_0x13ac:
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x13b2:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -708142594: goto L_0x13de;
                case -509282093: goto L_0x13d3;
                case 1691546209: goto L_0x13c8;
                case 1965191908: goto L_0x13bd;
                default: goto L_0x13bc;
            }
        L_0x13bc:
            goto L_0x13e8
        L_0x13bd:
            java.lang.String r0 = "Infinix X650"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x13c6
            goto L_0x13e8
        L_0x13c6:
            r13 = 3
            goto L_0x13e8
        L_0x13c8:
            java.lang.String r0 = "CPH1920"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x13d1
            goto L_0x13e8
        L_0x13d1:
            r13 = 2
            goto L_0x13e8
        L_0x13d3:
            java.lang.String r0 = "Nokia 1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x13dc
            goto L_0x13e8
        L_0x13dc:
            r13 = r11
            goto L_0x13e8
        L_0x13de:
            java.lang.String r0 = "Redmi 6A"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x13e7
            goto L_0x13e8
        L_0x13e7:
            r13 = r12
        L_0x13e8:
            switch(r13) {
                case 0: goto L_0x1411;
                case 1: goto L_0x1401;
                case 2: goto L_0x13fb;
                case 3: goto L_0x13ee;
                default: goto L_0x13eb;
            }
        L_0x13eb:
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x13ee:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x13fa
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x13fa:
            return r24
        L_0x13fb:
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x1401:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x1410
            r0 = 4599030903474476810(0x3fd30a3d70a3d70a, double:0.2975)
            return r0
        L_0x1410:
            return r29
        L_0x1411:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1420
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x1420:
            if (r4 == r6) goto L_0x1428
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x1428:
            r0 = 4594527303847106314(0x3fc30a3d70a3d70a, double:0.14875)
            return r0
        L_0x142e:
            r33.hashCode()
            java.lang.String r0 = "Moto C"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x143a
            return r24
        L_0x143a:
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x1440:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -2038157991: goto L_0x146d;
                case -401812218: goto L_0x1462;
                case -401812217: goto L_0x1457;
                case 807317112: goto L_0x144c;
                default: goto L_0x144a;
            }
        L_0x144a:
            r9 = r13
            goto L_0x1477
        L_0x144c:
            java.lang.String r0 = "LG-K430"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1455
            goto L_0x144a
        L_0x1455:
            r9 = 3
            goto L_0x1477
        L_0x1457:
            java.lang.String r0 = "SM-G532G"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1460
            goto L_0x144a
        L_0x1460:
            r9 = 2
            goto L_0x1477
        L_0x1462:
            java.lang.String r0 = "SM-G532F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x146b
            goto L_0x144a
        L_0x146b:
            r9 = r11
            goto L_0x1477
        L_0x146d:
            java.lang.String r0 = "Redmi Note 4"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1476
            goto L_0x144a
        L_0x1476:
            r9 = r12
        L_0x1477:
            switch(r9) {
                case 0: goto L_0x1487;
                case 1: goto L_0x1486;
                case 2: goto L_0x147c;
                case 3: goto L_0x147b;
                default: goto L_0x147a;
            }
        L_0x147a:
            return r14
        L_0x147b:
            return r24
        L_0x147c:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x1486
            return r14
        L_0x1486:
            return r16
        L_0x1487:
            return r20
        L_0x1488:
            return r22
        L_0x1489:
            switch(r1) {
                case 24: goto L_0x17fe;
                case 25: goto L_0x148c;
                case 26: goto L_0x1717;
                case 27: goto L_0x1695;
                case 28: goto L_0x1574;
                case 29: goto L_0x14f0;
                case 30: goto L_0x1493;
                case 31: goto L_0x1492;
                default: goto L_0x148c;
            }
        L_0x148c:
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x1492:
            return r22
        L_0x1493:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -407473314: goto L_0x14d6;
                case -407444484: goto L_0x14cb;
                case -407356134: goto L_0x14c0;
                case -407355173: goto L_0x14b5;
                case -396332503: goto L_0x14aa;
                case 256892039: goto L_0x149f;
                default: goto L_0x149d;
            }
        L_0x149d:
            r7 = r13
            goto L_0x14e0
        L_0x149f:
            java.lang.String r0 = "SM-A515U1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x14a8
            goto L_0x149d
        L_0x14a8:
            r7 = 5
            goto L_0x14e0
        L_0x14aa:
            java.lang.String r0 = "SM-M315F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x14b3
            goto L_0x149d
        L_0x14b3:
            r7 = 4
            goto L_0x14e0
        L_0x14b5:
            java.lang.String r0 = "SM-A515F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x14be
            goto L_0x149d
        L_0x14be:
            r7 = 3
            goto L_0x14e0
        L_0x14c0:
            java.lang.String r0 = "SM-A505F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x14c9
            goto L_0x149d
        L_0x14c9:
            r7 = 2
            goto L_0x14e0
        L_0x14cb:
            java.lang.String r0 = "SM-A217F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x14d4
            goto L_0x149d
        L_0x14d4:
            r7 = r11
            goto L_0x14e0
        L_0x14d6:
            java.lang.String r0 = "SM-A127F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x14df
            goto L_0x149d
        L_0x14df:
            r7 = r12
        L_0x14e0:
            switch(r7) {
                case 0: goto L_0x14ef;
                case 1: goto L_0x14ef;
                case 2: goto L_0x14e9;
                case 3: goto L_0x14e9;
                case 4: goto L_0x14e9;
                case 5: goto L_0x14e9;
                default: goto L_0x14e3;
            }
        L_0x14e3:
            r0 = 4600370814435612080(0x3fd7cce1c58255b0, double:0.37188)
            return r0
        L_0x14e9:
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x14ef:
            return r20
        L_0x14f0:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -401689047: goto L_0x1512;
                case -395224625: goto L_0x1507;
                case 253167728: goto L_0x14fc;
                default: goto L_0x14fa;
            }
        L_0x14fa:
            r0 = r13
            goto L_0x151c
        L_0x14fc:
            java.lang.String r0 = "SM-A105FN"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1505
            goto L_0x14fa
        L_0x1505:
            r0 = 2
            goto L_0x151c
        L_0x1507:
            java.lang.String r0 = "SM-N970F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1510
            goto L_0x14fa
        L_0x1510:
            r0 = r11
            goto L_0x151c
        L_0x1512:
            java.lang.String r0 = "SM-G977N"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x151b
            goto L_0x14fa
        L_0x151b:
            r0 = r12
        L_0x151c:
            switch(r0) {
                case 0: goto L_0x1567;
                case 1: goto L_0x1566;
                case 2: goto L_0x1525;
                default: goto L_0x151f;
            }
        L_0x151f:
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x1525:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x1543;
                case -1719904874: goto L_0x153a;
                case 802059049: goto L_0x1531;
                default: goto L_0x152f;
            }
        L_0x152f:
            r10 = r13
            goto L_0x154b
        L_0x1531:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1538
            goto L_0x152f
        L_0x1538:
            r10 = 2
            goto L_0x154b
        L_0x153a:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x1541
            goto L_0x152f
        L_0x1541:
            r10 = r11
            goto L_0x154b
        L_0x1543:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x154a
            goto L_0x152f
        L_0x154a:
            r10 = r12
        L_0x154b:
            switch(r10) {
                case 0: goto L_0x1563;
                case 1: goto L_0x1562;
                case 2: goto L_0x1554;
                default: goto L_0x154e;
            }
        L_0x154e:
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x1554:
            if (r4 == r8) goto L_0x155c
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            return r0
        L_0x155c:
            r0 = 4607407598781385933(0x3ff0cccccccccccd, double:1.05)
            return r0
        L_0x1562:
            return r26
        L_0x1563:
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x1566:
            return r22
        L_0x1567:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x1571
            return r26
        L_0x1571:
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x1574:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -401691194: goto L_0x15d7;
                case -401691039: goto L_0x15cb;
                case -401690078: goto L_0x15bf;
                case -401690070: goto L_0x15b3;
                case -398984987: goto L_0x15a7;
                case -395225586: goto L_0x159b;
                case -395225578: goto L_0x158f;
                case 253167728: goto L_0x1582;
                default: goto L_0x157e;
            }
        L_0x157e:
            r28 = r13
            goto L_0x15e2
        L_0x1582:
            java.lang.String r0 = "SM-A105FN"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x158b
            goto L_0x157e
        L_0x158b:
            r0 = 7
            r28 = r0
            goto L_0x15e2
        L_0x158f:
            java.lang.String r0 = "SM-N960N"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1598
            goto L_0x157e
        L_0x1598:
            r28 = 6
            goto L_0x15e2
        L_0x159b:
            java.lang.String r0 = "SM-N960F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x15a4
            goto L_0x157e
        L_0x15a4:
            r28 = 5
            goto L_0x15e2
        L_0x15a7:
            java.lang.String r0 = "SM-J701F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x15b0
            goto L_0x157e
        L_0x15b0:
            r28 = 4
            goto L_0x15e2
        L_0x15b3:
            java.lang.String r0 = "SM-G965N"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x15bc
            goto L_0x157e
        L_0x15bc:
            r28 = 3
            goto L_0x15e2
        L_0x15bf:
            java.lang.String r0 = "SM-G965F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x15c8
            goto L_0x157e
        L_0x15c8:
            r28 = 2
            goto L_0x15e2
        L_0x15cb:
            java.lang.String r0 = "SM-G955F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x15d4
            goto L_0x157e
        L_0x15d4:
            r28 = r11
            goto L_0x15e2
        L_0x15d7:
            java.lang.String r0 = "SM-G950F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x15e0
            goto L_0x157e
        L_0x15e0:
            r28 = r12
        L_0x15e2:
            switch(r28) {
                case 0: goto L_0x1676;
                case 1: goto L_0x1670;
                case 2: goto L_0x1659;
                case 3: goto L_0x1622;
                case 4: goto L_0x161c;
                case 5: goto L_0x15fd;
                case 6: goto L_0x15fd;
                case 7: goto L_0x15e6;
                default: goto L_0x15e5;
            }
        L_0x15e5:
            return r26
        L_0x15e6:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x15f7
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x15f6
            return r26
        L_0x15f6:
            return r20
        L_0x15f7:
            r0 = 4601158944370401917(0x3fda99ae924f227d, double:0.41563)
            return r0
        L_0x15fd:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x161b
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x160d
            return r16
        L_0x160d:
            if (r4 == r6) goto L_0x1615
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x1615:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x161b:
            return r29
        L_0x161c:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x1622:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x1640;
                case 802059049: goto L_0x1637;
                case 1514345136: goto L_0x162e;
                default: goto L_0x162c;
            }
        L_0x162c:
            r10 = r13
            goto L_0x1648
        L_0x162e:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x1635
            goto L_0x162c
        L_0x1635:
            r10 = 2
            goto L_0x1648
        L_0x1637:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x163e
            goto L_0x162c
        L_0x163e:
            r10 = r11
            goto L_0x1648
        L_0x1640:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x1647
            goto L_0x162c
        L_0x1647:
            r10 = r12
        L_0x1648:
            switch(r10) {
                case 0: goto L_0x1658;
                case 1: goto L_0x1652;
                case 2: goto L_0x164c;
                default: goto L_0x164b;
            }
        L_0x164b:
            return r16
        L_0x164c:
            r0 = 4590622863114161189(0x3fb52b2bfdb4cc25, double:0.08269)
            return r0
        L_0x1652:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x1658:
            return r29
        L_0x1659:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x166f
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1669
            return r16
        L_0x1669:
            r0 = 4592860971984979231(0x3fbd1eb851eb851f, double:0.11375)
            return r0
        L_0x166f:
            return r29
        L_0x1670:
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x1676:
            r34.hashCode()
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x168f
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1686
            return r22
        L_0x1686:
            if (r4 == r6) goto L_0x1689
            return r29
        L_0x1689:
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x168f:
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x1695:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -401784411: goto L_0x16c1;
                case -399128207: goto L_0x16b6;
                case -399128206: goto L_0x16ab;
                case -396363255: goto L_0x16a0;
                default: goto L_0x169f;
            }
        L_0x169f:
            goto L_0x16cb
        L_0x16a0:
            java.lang.String r0 = "SM-M205F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x16a9
            goto L_0x16cb
        L_0x16a9:
            r13 = 3
            goto L_0x16cb
        L_0x16ab:
            java.lang.String r0 = "SM-J260G"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x16b4
            goto L_0x16cb
        L_0x16b4:
            r13 = 2
            goto L_0x16cb
        L_0x16b6:
            java.lang.String r0 = "SM-J260F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x16bf
            goto L_0x16cb
        L_0x16bf:
            r13 = r11
            goto L_0x16cb
        L_0x16c1:
            java.lang.String r0 = "SM-G610F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x16ca
            goto L_0x16cb
        L_0x16ca:
            r13 = r12
        L_0x16cb:
            switch(r13) {
                case 0: goto L_0x1711;
                case 1: goto L_0x1711;
                case 2: goto L_0x16f8;
                case 3: goto L_0x16d4;
                default: goto L_0x16ce;
            }
        L_0x16ce:
            r0 = 4603298064121410355(0x3fe2333333333333, double:0.56875)
            return r0
        L_0x16d4:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x16f2
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x16e9
            r0 = 4603298064121410355(0x3fe2333333333333, double:0.56875)
            return r0
        L_0x16e9:
            if (r4 == r6) goto L_0x16f1
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x16f1:
            return r24
        L_0x16f2:
            r0 = 4601158944370401917(0x3fda99ae924f227d, double:0.41563)
            return r0
        L_0x16f8:
            r34.hashCode()
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x170b
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1708
            return r14
        L_0x1708:
            r0 = 4601552919265804288(0x3fdc000000000000, double:0.4375)
            return r0
        L_0x170b:
            r0 = 4601158944370401917(0x3fda99ae924f227d, double:0.41563)
            return r0
        L_0x1711:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x1717:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -407354367: goto L_0x176c;
                case -401693116: goto L_0x1760;
                case -401692961: goto L_0x1754;
                case -401691194: goto L_0x1748;
                case -401691039: goto L_0x173c;
                case -401690233: goto L_0x1730;
                case -399014808: goto L_0x1724;
                default: goto L_0x1721;
            }
        L_0x1721:
            r28 = r13
            goto L_0x1777
        L_0x1724:
            java.lang.String r0 = "SM-J600G"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x172d
            goto L_0x1721
        L_0x172d:
            r28 = 6
            goto L_0x1777
        L_0x1730:
            java.lang.String r0 = "SM-G960F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1739
            goto L_0x1721
        L_0x1739:
            r28 = 5
            goto L_0x1777
        L_0x173c:
            java.lang.String r0 = "SM-G955F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1745
            goto L_0x1721
        L_0x1745:
            r28 = 4
            goto L_0x1777
        L_0x1748:
            java.lang.String r0 = "SM-G950F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1751
            goto L_0x1721
        L_0x1751:
            r28 = 3
            goto L_0x1777
        L_0x1754:
            java.lang.String r0 = "SM-G935F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x175d
            goto L_0x1721
        L_0x175d:
            r28 = 2
            goto L_0x1777
        L_0x1760:
            java.lang.String r0 = "SM-G930F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1769
            goto L_0x1721
        L_0x1769:
            r28 = r11
            goto L_0x1777
        L_0x176c:
            java.lang.String r0 = "SM-A520F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1775
            goto L_0x1721
        L_0x1775:
            r28 = r12
        L_0x1777:
            switch(r28) {
                case 0: goto L_0x17f8;
                case 1: goto L_0x17f2;
                case 2: goto L_0x17dd;
                case 3: goto L_0x17d7;
                case 4: goto L_0x1793;
                case 5: goto L_0x177b;
                case 6: goto L_0x17f8;
                default: goto L_0x177a;
            }
        L_0x177a:
            return r20
        L_0x177b:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x1785
            return r20
        L_0x1785:
            if (r4 == r8) goto L_0x178d
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x178d:
            r0 = 4594842555821022249(0x3fc428f5c28f5c29, double:0.1575)
            return r0
        L_0x1793:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -2077737242: goto L_0x17b1;
                case 802059049: goto L_0x17a8;
                case 1514345136: goto L_0x179f;
                default: goto L_0x179d;
            }
        L_0x179d:
            r10 = r13
            goto L_0x17b9
        L_0x179f:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x17a6
            goto L_0x179d
        L_0x17a6:
            r10 = 2
            goto L_0x17b9
        L_0x17a8:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x17af
            goto L_0x179d
        L_0x17af:
            r10 = r11
            goto L_0x17b9
        L_0x17b1:
            boolean r0 = r3.equals(r9)
            if (r0 != 0) goto L_0x17b8
            goto L_0x179d
        L_0x17b8:
            r10 = r12
        L_0x17b9:
            switch(r10) {
                case 0: goto L_0x17d6;
                case 1: goto L_0x17c8;
                case 2: goto L_0x17c2;
                default: goto L_0x17bc;
            }
        L_0x17bc:
            r0 = 4599030903474476810(0x3fd30a3d70a3d70a, double:0.2975)
            return r0
        L_0x17c2:
            r0 = 4591250484758231541(0x3fb765fd8adab9f5, double:0.0914)
            return r0
        L_0x17c8:
            if (r4 == r6) goto L_0x17d0
            r0 = 4595473059768854118(0x3fc6666666666666, double:0.175)
            return r0
        L_0x17d0:
            r0 = 4594212051873190380(0x3fc1eb851eb851ec, double:0.14)
            return r0
        L_0x17d6:
            return r22
        L_0x17d7:
            r0 = 4599030903474476810(0x3fd30a3d70a3d70a, double:0.2975)
            return r0
        L_0x17dd:
            r34.hashCode()
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x17ec
            r0 = 4599346155448392745(0x3fd428f5c28f5c29, double:0.315)
            return r0
        L_0x17ec:
            r0 = 4593491475932811100(0x3fbf5c28f5c28f5c, double:0.1225)
            return r0
        L_0x17f2:
            r0 = 4596418815690601923(0x3fc9c28f5c28f5c3, double:0.20125)
            return r0
        L_0x17f8:
            r0 = 4599661407422308680(0x3fd547ae147ae148, double:0.3325)
            return r0
        L_0x17fe:
            r33.hashCode()
            java.lang.String r0 = "SM-G920V"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x181d
            java.lang.String r0 = "SM-G935F"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1817
            r0 = 4600370814435612080(0x3fd7cce1c58255b0, double:0.37188)
            return r0
        L_0x1817:
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x181d:
            return r22
        L_0x181e:
            r33.hashCode()
            int r0 = r33.hashCode()
            switch(r0) {
                case -143589744: goto L_0x1840;
                case -143589743: goto L_0x1835;
                case -142337348: goto L_0x182a;
                default: goto L_0x1828;
            }
        L_0x1828:
            r0 = r13
            goto L_0x184a
        L_0x182a:
            java.lang.String r0 = "FIG-LX1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1833
            goto L_0x1828
        L_0x1833:
            r0 = 2
            goto L_0x184a
        L_0x1835:
            java.lang.String r0 = "ANE-LX2"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x183e
            goto L_0x1828
        L_0x183e:
            r0 = r11
            goto L_0x184a
        L_0x1840:
            java.lang.String r0 = "ANE-LX1"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x1849
            goto L_0x1828
        L_0x1849:
            r0 = r12
        L_0x184a:
            switch(r0) {
                case 0: goto L_0x184f;
                case 1: goto L_0x184f;
                case 2: goto L_0x184e;
                default: goto L_0x184d;
            }
        L_0x184d:
            return r24
        L_0x184e:
            return r22
        L_0x184f:
            r34.hashCode()
            int r0 = r34.hashCode()
            switch(r0) {
                case -1719904874: goto L_0x186d;
                case 802059049: goto L_0x1864;
                case 1514345136: goto L_0x185b;
                default: goto L_0x1859;
            }
        L_0x1859:
            r10 = r13
            goto L_0x1875
        L_0x185b:
            boolean r0 = r3.equals(r5)
            if (r0 != 0) goto L_0x1862
            goto L_0x1859
        L_0x1862:
            r10 = 2
            goto L_0x1875
        L_0x1864:
            boolean r0 = r3.equals(r10)
            if (r0 != 0) goto L_0x186b
            goto L_0x1859
        L_0x186b:
            r10 = r11
            goto L_0x1875
        L_0x186d:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L_0x1874
            goto L_0x1859
        L_0x1874:
            r10 = r12
        L_0x1875:
            switch(r10) {
                case 0: goto L_0x188d;
                case 1: goto L_0x187f;
                case 2: goto L_0x1879;
                default: goto L_0x1878;
            }
        L_0x1878:
            return r24
        L_0x1879:
            r0 = 4597529583502696582(0x3fcdb4cc25072086, double:0.23208)
            return r0
        L_0x187f:
            if (r4 == r6) goto L_0x1887
            r0 = 4598400399526644941(0x3fd0cccccccccccd, double:0.2625)
            return r0
        L_0x1887:
            r0 = 4596103563716685988(0x3fc8a3d70a3d70a4, double:0.1925)
            return r0
        L_0x188d:
            return r22
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.DeviceMappedEncoderBitrateProvider.getBitrateMultiplierFromMapping(java.lang.String, int, java.lang.String, java.lang.String, int):double");
    }
}
