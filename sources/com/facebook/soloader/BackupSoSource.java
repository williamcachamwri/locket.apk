package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import com.facebook.soloader.ExtractFromZipSoSource;
import com.facebook.soloader.UnpackingSoSource;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;

public class BackupSoSource extends ExtractFromZipSoSource {
    private static final byte APK_SO_SOURCE_SIGNATURE_VERSION = 2;
    private static final byte LIBS_DIR_DOESNT_EXIST = 1;
    private static final byte LIBS_DIR_DONT_CARE = 0;
    private static final byte LIBS_DIR_SNAPSHOT = 2;
    public static final int PREFER_ANDROID_LIBS_DIRECTORY = 1;
    private static final String TAG = "BackupSoSource";
    /* access modifiers changed from: private */
    public final int mFlags;

    public String getName() {
        return TAG;
    }

    public BackupSoSource(Context context, String str, int i) {
        this(context, new File(context.getApplicationInfo().sourceDir), str, i);
    }

    public BackupSoSource(Context context, File file, String str, int i) {
        super(context, str, file, "^lib/([^/]+)/([^/]+\\.so)$");
        this.mFlags = i;
    }

    public boolean hasZippedLibs() throws IOException {
        boolean z = false;
        ApkUnpacker apkUnpacker = new ApkUnpacker(this, false);
        try {
            if (apkUnpacker.computeDsosFromZip().length != 0) {
                z = true;
            }
            apkUnpacker.close();
            return z;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public UnpackingSoSource.Unpacker makeUnpacker(boolean z) throws IOException {
        return new ApkUnpacker(this, z);
    }

    protected class ApkUnpacker extends ExtractFromZipSoSource.ZipUnpacker {
        private final int mFlags;
        private final boolean mForceUnpacking;
        private final File mLibDir;

        ApkUnpacker(ExtractFromZipSoSource extractFromZipSoSource, boolean z) throws IOException {
            super(extractFromZipSoSource);
            this.mForceUnpacking = z;
            this.mLibDir = new File(BackupSoSource.this.mContext.getApplicationInfo().nativeLibraryDir);
            this.mFlags = BackupSoSource.this.mFlags;
        }

        /* access modifiers changed from: protected */
        public ExtractFromZipSoSource.ZipDso[] getExtractableDsosFromZip() {
            if (this.mDsos != null) {
                return this.mDsos;
            }
            ExtractFromZipSoSource.ZipDso[] computeDsosFromZip = computeDsosFromZip();
            this.mDsos = computeDsosFromZip;
            if (this.mForceUnpacking) {
                LogUtil.w(BackupSoSource.TAG, "Unconditonally extracting all DSOs from zip");
                return this.mDsos;
            } else if ((this.mFlags & 1) == 0) {
                LogUtil.w(BackupSoSource.TAG, "Self-extraction preferred (PREFER_ANDROID_LIBS_DRIECTORY not set)");
                return this.mDsos;
            } else {
                for (ExtractFromZipSoSource.ZipDso zipDso : computeDsosFromZip) {
                    if (shouldExtract(zipDso.backingEntry, zipDso.name)) {
                        return this.mDsos;
                    }
                }
                this.mDsos = new ExtractFromZipSoSource.ZipDso[0];
                return this.mDsos;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean shouldExtract(ZipEntry zipEntry, String str) {
            String name = zipEntry.getName();
            File file = new File(this.mLibDir, str);
            try {
                if (!file.getCanonicalPath().startsWith(this.mLibDir.getCanonicalPath())) {
                    LogUtil.d(BackupSoSource.TAG, "Not allowing consideration of " + name + ": " + str + " not in lib dir.");
                    return false;
                } else if (!file.isFile()) {
                    LogUtil.w(BackupSoSource.TAG, "Allowing consideration of " + name + ": " + str + " not in system lib dir");
                    return true;
                } else {
                    long length = file.length();
                    long size = zipEntry.getSize();
                    if (length != size) {
                        LogUtil.w(BackupSoSource.TAG, "Allowing consideration of " + file + ": sysdir file length is " + length + ", but the file is " + size + " bytes long in the APK");
                        return true;
                    }
                    LogUtil.w(BackupSoSource.TAG, "Not allowing consideration of " + name + ": deferring to libdir");
                    return false;
                }
            } catch (IOException e) {
                LogUtil.w(BackupSoSource.TAG, "Not allowing consideration of " + name + ": " + str + ", IOException when constructing path: " + e.toString());
                return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getDepsBlock() throws IOException {
        File canonicalFile = this.mZipFileName.getCanonicalFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte((byte) 2);
            obtain.writeString(canonicalFile.getPath());
            obtain.writeLong(canonicalFile.lastModified());
            obtain.writeInt(SysUtil.getAppVersionCode(this.mContext));
            if ((this.mFlags & 1) == 0) {
                obtain.writeByte((byte) 0);
                return obtain.marshall();
            }
            String str = this.mContext.getApplicationInfo().nativeLibraryDir;
            if (str == null) {
                obtain.writeByte((byte) 1);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                return marshall;
            }
            File canonicalFile2 = new File(str).getCanonicalFile();
            if (!canonicalFile2.exists()) {
                obtain.writeByte((byte) 1);
                byte[] marshall2 = obtain.marshall();
                obtain.recycle();
                return marshall2;
            }
            obtain.writeByte((byte) 2);
            obtain.writeString(canonicalFile2.getPath());
            obtain.writeLong(canonicalFile2.lastModified());
            byte[] marshall3 = obtain.marshall();
            obtain.recycle();
            return marshall3;
        } finally {
            obtain.recycle();
        }
    }
}
