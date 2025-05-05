package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

public class ExtractFromZipSoSource extends UnpackingSoSource {
    private static final String TAG = "soloader.ExtractFromZipSoSource";
    protected final File mZipFileName;
    protected final String mZipSearchPattern;

    public String getName() {
        return "ExtractFromZipSoSource";
    }

    public ExtractFromZipSoSource(Context context, String str, File file, String str2) {
        super(context, str);
        this.mZipFileName = file;
        this.mZipSearchPattern = str2;
    }

    /* access modifiers changed from: protected */
    public UnpackingSoSource.Unpacker makeUnpacker(boolean z) throws IOException {
        return new ZipUnpacker(this);
    }

    protected class ZipUnpacker extends UnpackingSoSource.Unpacker {
        @Nullable
        protected ZipDso[] mDsos;
        private final UnpackingSoSource mSoSource;
        private final ZipFile mZipFile;

        ZipUnpacker(UnpackingSoSource unpackingSoSource) throws IOException {
            this.mZipFile = new ZipFile(ExtractFromZipSoSource.this.mZipFileName);
            this.mSoSource = unpackingSoSource;
        }

        /* access modifiers changed from: package-private */
        public ZipDso[] computeDsosFromZip() {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            HashMap hashMap = new HashMap();
            Pattern compile = Pattern.compile(ExtractFromZipSoSource.this.mZipSearchPattern);
            String[] supportedAbis = SysUtil.getSupportedAbis();
            Enumeration<? extends ZipEntry> entries = this.mZipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                Matcher matcher = compile.matcher(zipEntry.getName());
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    int findAbiScore = SysUtil.findAbiScore(supportedAbis, group);
                    if (findAbiScore >= 0) {
                        linkedHashSet.add(group);
                        ZipDso zipDso = (ZipDso) hashMap.get(group2);
                        if (zipDso == null || findAbiScore < zipDso.abiScore) {
                            hashMap.put(group2, new ZipDso(group2, zipEntry, findAbiScore));
                        }
                    }
                }
            }
            this.mSoSource.setSoSourceAbis((String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]));
            ZipDso[] zipDsoArr = (ZipDso[]) hashMap.values().toArray(new ZipDso[hashMap.size()]);
            Arrays.sort(zipDsoArr);
            return zipDsoArr;
        }

        /* access modifiers changed from: package-private */
        public ZipDso[] getExtractableDsosFromZip() {
            ZipDso[] zipDsoArr = this.mDsos;
            if (zipDsoArr != null) {
                return zipDsoArr;
            }
            ZipDso[] computeDsosFromZip = computeDsosFromZip();
            this.mDsos = computeDsosFromZip;
            return computeDsosFromZip;
        }

        public void close() throws IOException {
            this.mZipFile.close();
        }

        public final UnpackingSoSource.Dso[] getDsos() throws IOException {
            return getExtractableDsosFromZip();
        }

        public void unpack(File file) throws IOException {
            UnpackingSoSource.InputDso inputDso;
            ZipDso[] extractableDsosFromZip = getExtractableDsosFromZip();
            byte[] bArr = new byte[32768];
            int length = extractableDsosFromZip.length;
            int i = 0;
            while (i < length) {
                ZipDso zipDso = extractableDsosFromZip[i];
                InputStream inputStream = this.mZipFile.getInputStream(zipDso.backingEntry);
                try {
                    inputDso = new UnpackingSoSource.InputDso(zipDso, inputStream);
                    inputStream = null;
                    extractDso(inputDso, bArr, file);
                    inputDso.close();
                    i++;
                } catch (Throwable th) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            }
            return;
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public String computeFileHash(File file) {
        FileInputStream create;
        try {
            create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
            CRC32 crc32 = new CRC32();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = create.read(bArr);
                if (read != -1) {
                    crc32.update(bArr, 0, read);
                } else {
                    String valueOf = String.valueOf(crc32.getValue());
                    create.close();
                    return valueOf;
                }
            }
        } catch (IOException e) {
            LogUtil.w(TAG, "Failed to compute file hash ", e);
            return "";
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    protected static final class ZipDso extends UnpackingSoSource.Dso implements Comparable<ZipDso> {
        final int abiScore;
        final ZipEntry backingEntry;

        ZipDso(String str, ZipEntry zipEntry, int i) {
            super(str, String.valueOf(zipEntry.getCrc()));
            this.backingEntry = zipEntry;
            this.abiScore = i;
        }

        public int compareTo(ZipDso zipDso) {
            return this.name.compareTo(zipDso.name);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ZipDso zipDso = (ZipDso) obj;
            if (!this.backingEntry.equals(zipDso.backingEntry) || this.abiScore != zipDso.abiScore) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.abiScore * 31) + this.backingEntry.hashCode();
        }
    }
}
