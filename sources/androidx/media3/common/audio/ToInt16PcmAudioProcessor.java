package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;

public final class ToInt16PcmAudioProcessor extends BaseAudioProcessor {
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int i = audioFormat.encoding;
        if (i != 3 && i != 2 && i != 268435456 && i != 21 && i != 1342177280 && i != 22 && i != 1610612736 && i != 4) {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        } else if (i != 2) {
            return new AudioProcessor.AudioFormat(audioFormat.sampleRate, audioFormat.channelCount, 2);
        } else {
            return AudioProcessor.AudioFormat.NOT_SET;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e5 A[LOOP:6: B:35:0x00e5->B:36:0x00e7, LOOP_START, PHI: r0 
      PHI: (r0v2 int) = (r0v0 int), (r0v3 int) binds: [B:14:0x003f, B:36:0x00e7] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void queueInput(java.nio.ByteBuffer r12) {
        /*
            r11 = this;
            int r0 = r12.position()
            int r1 = r12.limit()
            int r2 = r1 - r0
            androidx.media3.common.audio.AudioProcessor$AudioFormat r3 = r11.inputAudioFormat
            int r3 = r3.encoding
            r4 = 1610612736(0x60000000, float:3.6893488E19)
            r5 = 1342177280(0x50000000, float:8.5899346E9)
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            r7 = 22
            r8 = 21
            r9 = 4
            r10 = 3
            if (r3 == r10) goto L_0x0035
            if (r3 == r9) goto L_0x0032
            if (r3 == r8) goto L_0x002f
            if (r3 == r7) goto L_0x0032
            if (r3 == r6) goto L_0x0037
            if (r3 == r5) goto L_0x002f
            if (r3 != r4) goto L_0x0029
            goto L_0x0032
        L_0x0029:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r12.<init>()
            throw r12
        L_0x002f:
            int r2 = r2 / 3
            goto L_0x0035
        L_0x0032:
            int r2 = r2 / 2
            goto L_0x0037
        L_0x0035:
            int r2 = r2 * 2
        L_0x0037:
            java.nio.ByteBuffer r2 = r11.replaceOutputBuffer(r2)
            androidx.media3.common.audio.AudioProcessor$AudioFormat r3 = r11.inputAudioFormat
            int r3 = r3.encoding
            if (r3 == r10) goto L_0x00e5
            if (r3 == r9) goto L_0x00c0
            if (r3 == r8) goto L_0x00a9
            if (r3 == r7) goto L_0x0092
            if (r3 == r6) goto L_0x007d
            if (r3 == r5) goto L_0x0068
            if (r3 != r4) goto L_0x0062
        L_0x004d:
            if (r0 >= r1) goto L_0x00fa
            int r3 = r0 + 1
            byte r3 = r12.get(r3)
            r2.put(r3)
            byte r3 = r12.get(r0)
            r2.put(r3)
            int r0 = r0 + 4
            goto L_0x004d
        L_0x0062:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r12.<init>()
            throw r12
        L_0x0068:
            if (r0 >= r1) goto L_0x00fa
            int r3 = r0 + 1
            byte r3 = r12.get(r3)
            r2.put(r3)
            byte r3 = r12.get(r0)
            r2.put(r3)
            int r0 = r0 + 3
            goto L_0x0068
        L_0x007d:
            if (r0 >= r1) goto L_0x00fa
            int r3 = r0 + 1
            byte r3 = r12.get(r3)
            r2.put(r3)
            byte r3 = r12.get(r0)
            r2.put(r3)
            int r0 = r0 + 2
            goto L_0x007d
        L_0x0092:
            if (r0 >= r1) goto L_0x00fa
            int r3 = r0 + 2
            byte r3 = r12.get(r3)
            r2.put(r3)
            int r3 = r0 + 3
            byte r3 = r12.get(r3)
            r2.put(r3)
            int r0 = r0 + 4
            goto L_0x0092
        L_0x00a9:
            if (r0 >= r1) goto L_0x00fa
            int r3 = r0 + 1
            byte r3 = r12.get(r3)
            r2.put(r3)
            int r3 = r0 + 2
            byte r3 = r12.get(r3)
            r2.put(r3)
            int r0 = r0 + 3
            goto L_0x00a9
        L_0x00c0:
            if (r0 >= r1) goto L_0x00fa
            float r3 = r12.getFloat(r0)
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            r5 = 1065353216(0x3f800000, float:1.0)
            float r3 = androidx.media3.common.util.Util.constrainValue((float) r3, (float) r4, (float) r5)
            r4 = 1191181824(0x46fffe00, float:32767.0)
            float r3 = r3 * r4
            int r3 = (int) r3
            short r3 = (short) r3
            r4 = r3 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r2.put(r4)
            int r3 = r3 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r2.put(r3)
            int r0 = r0 + 4
            goto L_0x00c0
        L_0x00e5:
            if (r0 >= r1) goto L_0x00fa
            r3 = 0
            r2.put(r3)
            byte r3 = r12.get(r0)
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 + -128
            byte r3 = (byte) r3
            r2.put(r3)
            int r0 = r0 + 1
            goto L_0x00e5
        L_0x00fa:
            int r0 = r12.limit()
            r12.position(r0)
            r2.flip()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.audio.ToInt16PcmAudioProcessor.queueInput(java.nio.ByteBuffer):void");
    }
}
