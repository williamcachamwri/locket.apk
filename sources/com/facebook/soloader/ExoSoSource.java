package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public final class ExoSoSource extends UnpackingSoSource {
    public String getName() {
        return "ExoSoSource";
    }

    public ExoSoSource(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public MessageDigest getHashingAlgorithm() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
    }

    /* access modifiers changed from: protected */
    public UnpackingSoSource.Unpacker makeUnpacker(boolean z) throws IOException {
        return new ExoUnpacker(this, this);
    }

    private final class ExoUnpacker extends UnpackingSoSource.Unpacker {
        private final FileDso[] mDsos;
        final /* synthetic */ ExoSoSource this$0;

        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00fe, code lost:
            r17 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            r9.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0103, code lost:
            r11.close();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ExoUnpacker(com.facebook.soloader.ExoSoSource r19, com.facebook.soloader.UnpackingSoSource r20) throws java.io.IOException {
            /*
                r18 = this;
                r1 = r18
                r0 = r19
                java.lang.String r2 = ".so"
                r1.this$0 = r0
                r18.<init>()
                android.content.Context r0 = r0.mContext
                java.io.File r3 = new java.io.File
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                java.lang.String r5 = "/data/local/tmp/exopackage/"
                r4.<init>(r5)
                java.lang.String r0 = r0.getPackageName()
                java.lang.StringBuilder r0 = r4.append(r0)
                java.lang.String r4 = "/native-libs/"
                java.lang.StringBuilder r0 = r0.append(r4)
                java.lang.String r0 = r0.toString()
                r3.<init>(r0)
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
                r4.<init>()
                java.lang.String[] r5 = com.facebook.soloader.SysUtil.getSupportedAbis()
                int r6 = r5.length
                r7 = 0
                r8 = r7
            L_0x003c:
                if (r8 >= r6) goto L_0x0125
                r9 = r5[r8]
                java.io.File r10 = new java.io.File
                r10.<init>(r3, r9)
                boolean r11 = r10.isDirectory()
                if (r11 != 0) goto L_0x004f
            L_0x004b:
                r17 = r3
                goto L_0x0106
            L_0x004f:
                r4.add(r9)
                java.io.File r9 = new java.io.File
                java.lang.String r11 = "metadata.txt"
                r9.<init>(r10, r11)
                boolean r11 = r9.isFile()
                if (r11 != 0) goto L_0x0060
                goto L_0x004b
            L_0x0060:
                io.sentry.instrumentation.file.SentryFileReader r11 = new io.sentry.instrumentation.file.SentryFileReader
                r11.<init>((java.io.File) r9)
                java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ all -> 0x0119 }
                r9.<init>(r11)     // Catch:{ all -> 0x0119 }
            L_0x006a:
                java.lang.String r12 = r9.readLine()     // Catch:{ all -> 0x010d }
                if (r12 == 0) goto L_0x00fe
                int r13 = r12.length()     // Catch:{ all -> 0x010d }
                if (r13 != 0) goto L_0x0077
                goto L_0x006a
            L_0x0077:
                r13 = 32
                int r13 = r12.indexOf(r13)     // Catch:{ all -> 0x010d }
                r14 = -1
                if (r13 == r14) goto L_0x00df
                java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x010d }
                r14.<init>()     // Catch:{ all -> 0x010d }
                java.lang.String r15 = r12.substring(r7, r13)     // Catch:{ all -> 0x010d }
                java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x010d }
                java.lang.StringBuilder r14 = r14.append(r2)     // Catch:{ all -> 0x010d }
                java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x010d }
                int r15 = r0.size()     // Catch:{ all -> 0x010d }
            L_0x0099:
                if (r7 >= r15) goto L_0x00b4
                java.lang.Object r16 = r0.get(r7)     // Catch:{ all -> 0x010d }
                r17 = r3
                r3 = r16
                com.facebook.soloader.ExoSoSource$FileDso r3 = (com.facebook.soloader.ExoSoSource.FileDso) r3     // Catch:{ all -> 0x010d }
                java.lang.String r3 = r3.name     // Catch:{ all -> 0x010d }
                boolean r3 = r3.equals(r14)     // Catch:{ all -> 0x010d }
                if (r3 == 0) goto L_0x00af
                r3 = 1
                goto L_0x00b7
            L_0x00af:
                int r7 = r7 + 1
                r3 = r17
                goto L_0x0099
            L_0x00b4:
                r17 = r3
                r3 = 0
            L_0x00b7:
                if (r3 == 0) goto L_0x00bd
            L_0x00b9:
                r3 = r17
                r7 = 0
                goto L_0x006a
            L_0x00bd:
                int r13 = r13 + 1
                java.lang.String r3 = r12.substring(r13)     // Catch:{ all -> 0x010d }
                r7 = 45
                int r7 = r3.indexOf(r7)     // Catch:{ all -> 0x010d }
                int r12 = r3.indexOf(r2)     // Catch:{ all -> 0x010d }
                java.lang.String r7 = r3.substring(r7, r12)     // Catch:{ all -> 0x010d }
                com.facebook.soloader.ExoSoSource$FileDso r12 = new com.facebook.soloader.ExoSoSource$FileDso     // Catch:{ all -> 0x010d }
                java.io.File r13 = new java.io.File     // Catch:{ all -> 0x010d }
                r13.<init>(r10, r3)     // Catch:{ all -> 0x010d }
                r12.<init>(r14, r7, r13)     // Catch:{ all -> 0x010d }
                r0.add(r12)     // Catch:{ all -> 0x010d }
                goto L_0x00b9
            L_0x00df:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x010d }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x010d }
                r2.<init>()     // Catch:{ all -> 0x010d }
                java.lang.String r3 = "illegal line in exopackage metadata: ["
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x010d }
                java.lang.StringBuilder r2 = r2.append(r12)     // Catch:{ all -> 0x010d }
                java.lang.String r3 = "]"
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x010d }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x010d }
                r0.<init>(r2)     // Catch:{ all -> 0x010d }
                throw r0     // Catch:{ all -> 0x010d }
            L_0x00fe:
                r17 = r3
                r9.close()     // Catch:{ all -> 0x0119 }
                r11.close()
            L_0x0106:
                int r8 = r8 + 1
                r3 = r17
                r7 = 0
                goto L_0x003c
            L_0x010d:
                r0 = move-exception
                r2 = r0
                r9.close()     // Catch:{ all -> 0x0113 }
                goto L_0x0118
            L_0x0113:
                r0 = move-exception
                r3 = r0
                r2.addSuppressed(r3)     // Catch:{ all -> 0x0119 }
            L_0x0118:
                throw r2     // Catch:{ all -> 0x0119 }
            L_0x0119:
                r0 = move-exception
                r2 = r0
                r11.close()     // Catch:{ all -> 0x011f }
                goto L_0x0124
            L_0x011f:
                r0 = move-exception
                r3 = r0
                r2.addSuppressed(r3)
            L_0x0124:
                throw r2
            L_0x0125:
                int r2 = r4.size()
                java.lang.String[] r2 = new java.lang.String[r2]
                java.lang.Object[] r2 = r4.toArray(r2)
                java.lang.String[] r2 = (java.lang.String[]) r2
                r3 = r20
                r3.setSoSourceAbis(r2)
                int r2 = r0.size()
                com.facebook.soloader.ExoSoSource$FileDso[] r2 = new com.facebook.soloader.ExoSoSource.FileDso[r2]
                java.lang.Object[] r0 = r0.toArray(r2)
                com.facebook.soloader.ExoSoSource$FileDso[] r0 = (com.facebook.soloader.ExoSoSource.FileDso[]) r0
                r1.mDsos = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.ExoSoSource.ExoUnpacker.<init>(com.facebook.soloader.ExoSoSource, com.facebook.soloader.UnpackingSoSource):void");
        }

        public UnpackingSoSource.Dso[] getDsos() throws IOException {
            return this.mDsos;
        }

        public void unpack(File file) throws IOException {
            UnpackingSoSource.InputDso inputDso;
            byte[] bArr = new byte[32768];
            FileDso[] fileDsoArr = this.mDsos;
            int length = fileDsoArr.length;
            int i = 0;
            while (i < length) {
                FileDso fileDso = fileDsoArr[i];
                File file2 = fileDso.backingFile;
                FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(file2), file2);
                try {
                    inputDso = new UnpackingSoSource.InputDso(fileDso, create);
                    create = null;
                    extractDso(inputDso, bArr, file);
                    inputDso.close();
                    i++;
                } catch (Throwable th) {
                    if (create != null) {
                        create.close();
                    }
                    throw th;
                }
            }
            return;
            throw th;
        }
    }

    private static final class FileDso extends UnpackingSoSource.Dso {
        final File backingFile;

        FileDso(String str, String str2, File file) {
            super(str, str2);
            this.backingFile = file;
        }
    }
}
