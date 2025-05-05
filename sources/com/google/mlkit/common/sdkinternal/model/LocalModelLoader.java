package com.google.mlkit.common.sdkinternal.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzi;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.LocalModel;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class LocalModelLoader {
    private MappedByteBuffer zza;
    private final Context zzb;
    private final LocalModel zzc;

    public LocalModelLoader(Context context, LocalModel localModel) {
        this.zzb = context;
        this.zzc = localModel;
    }

    public LocalModel getLocalModel() {
        return this.zzc;
    }

    public MappedByteBuffer load() throws MlKitException {
        FileChannel channel;
        FileChannel channel2;
        FileChannel channel3;
        Preconditions.checkNotNull(this.zzb, "Context can not be null");
        Preconditions.checkNotNull(this.zzc, "Model source can not be null");
        MappedByteBuffer mappedByteBuffer = this.zza;
        if (mappedByteBuffer != null) {
            return mappedByteBuffer;
        }
        LocalModel localModel = this.zzc;
        String absoluteFilePath = localModel.getAbsoluteFilePath();
        String assetFilePath = localModel.getAssetFilePath();
        Uri uri = localModel.getUri();
        if (absoluteFilePath != null) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(absoluteFilePath, "r");
                try {
                    channel3 = randomAccessFile.getChannel();
                    this.zza = channel3.map(FileChannel.MapMode.READ_ONLY, 0, channel3.size());
                    if (channel3 != null) {
                        channel3.close();
                    }
                    randomAccessFile.close();
                } catch (Throwable th) {
                    randomAccessFile.close();
                    throw th;
                }
            } catch (IOException e) {
                throw new MlKitException("Can not open the local file: ".concat(String.valueOf(this.zzc.getAbsoluteFilePath())), 14, e);
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else if (assetFilePath != null) {
            try {
                AssetFileDescriptor openFd = this.zzb.getAssets().openFd(assetFilePath);
                try {
                    FileDescriptor fileDescriptor = openFd.getFileDescriptor();
                    channel2 = SentryFileInputStream.Factory.create(new FileInputStream(fileDescriptor), fileDescriptor).getChannel();
                    FileChannel fileChannel = channel2;
                    this.zza = fileChannel.map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength());
                    if (channel2 != null) {
                        channel2.close();
                    }
                    if (openFd != null) {
                        openFd.close();
                    }
                } catch (Throwable th3) {
                    if (openFd != null) {
                        openFd.close();
                    }
                    throw th3;
                }
            } catch (IOException e2) {
                throw new MlKitException("Can not load the file from asset: " + assetFilePath + ". Please double check your asset file name and ensure it's not compressed. See documentation for details how to use aaptOptions to skip file compression", 14, e2);
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
        } else if (uri != null) {
            try {
                AssetFileDescriptor zza2 = zzi.zza(this.zzb, uri, "r");
                try {
                    channel = zza2.createInputStream().getChannel();
                    FileChannel fileChannel2 = channel;
                    this.zza = fileChannel2.map(FileChannel.MapMode.READ_ONLY, zza2.getStartOffset(), zza2.getLength());
                    if (channel != null) {
                        channel.close();
                    }
                    if (zza2 != null) {
                        zza2.close();
                    }
                } catch (Throwable th5) {
                    if (zza2 != null) {
                        zza2.close();
                    }
                    throw th5;
                }
            } catch (IOException e3) {
                throw new MlKitException("Can not load the file from URI: ".concat(uri.toString()), 14, e3);
            } catch (Throwable th6) {
                th5.addSuppressed(th6);
            }
        } else {
            throw new MlKitException("Can not load the model. One of filePath, assetFilePath or URI must be set for the model.", 14);
        }
        return this.zza;
        throw th;
        throw th;
        throw th;
    }
}
