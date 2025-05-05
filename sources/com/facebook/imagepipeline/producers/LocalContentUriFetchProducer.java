package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import expo.modules.contacts.Columns;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/imagepipeline/producers/LocalContentUriFetchProducer;", "Lcom/facebook/imagepipeline/producers/LocalFetchProducer;", "executor", "Ljava/util/concurrent/Executor;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "contentResolver", "Landroid/content/ContentResolver;", "(Ljava/util/concurrent/Executor;Lcom/facebook/common/memory/PooledByteBufferFactory;Landroid/content/ContentResolver;)V", "getCameraImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "uri", "Landroid/net/Uri;", "getEncodedImage", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "getProducerName", "", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LocalContentUriFetchProducer.kt */
public final class LocalContentUriFetchProducer extends LocalFetchProducer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String PRODUCER_NAME = "LocalContentUriFetchProducer";
    private static final String[] PROJECTION = {Columns.ID, "_data"};
    private final ContentResolver contentResolver;

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return PRODUCER_NAME;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LocalContentUriFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver2) {
        super(executor, pooledByteBufferFactory);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(contentResolver2, "contentResolver");
        this.contentResolver = contentResolver2;
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        EncodedImage cameraImage;
        InputStream inputStream;
        InputStream inputStream2;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Uri sourceUri = imageRequest.getSourceUri();
        Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
        if (UriUtil.isLocalContactUri(sourceUri)) {
            String uri = sourceUri.toString();
            Intrinsics.checkNotNullExpressionValue(uri, "uri.toString()");
            if (StringsKt.endsWith$default(uri, "/photo", false, 2, (Object) null)) {
                inputStream = this.contentResolver.openInputStream(sourceUri);
            } else {
                String uri2 = sourceUri.toString();
                Intrinsics.checkNotNullExpressionValue(uri2, "uri.toString()");
                if (StringsKt.endsWith$default(uri2, "/display_photo", false, 2, (Object) null)) {
                    try {
                        AssetFileDescriptor openAssetFileDescriptor = this.contentResolver.openAssetFileDescriptor(sourceUri, "r");
                        if (openAssetFileDescriptor != null) {
                            inputStream2 = openAssetFileDescriptor.createInputStream();
                        } else {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                    } catch (IOException unused) {
                        throw new IOException("Contact photo does not exist: " + sourceUri);
                    }
                } else {
                    inputStream2 = ContactsContract.Contacts.openContactPhotoInputStream(this.contentResolver, sourceUri);
                    if (inputStream2 == null) {
                        throw new IOException("Contact photo does not exist: " + sourceUri);
                    }
                }
                inputStream = inputStream2;
            }
            if (inputStream != null) {
                return getEncodedImage(inputStream, -1);
            }
            throw new IllegalStateException("Required value was null.".toString());
        } else if (UriUtil.isLocalCameraUri(sourceUri) && (cameraImage = getCameraImage(sourceUri)) != null) {
            return cameraImage;
        } else {
            InputStream openInputStream = this.contentResolver.openInputStream(sourceUri);
            if (openInputStream != null) {
                return getEncodedImage(openInputStream, -1);
            }
            throw new IllegalStateException("Required value was null.".toString());
        }
    }

    private final EncodedImage getCameraImage(Uri uri) throws IOException {
        try {
            ParcelFileDescriptor openFileDescriptor = this.contentResolver.openFileDescriptor(uri, "r");
            if (openFileDescriptor != null) {
                FileDescriptor fileDescriptor = openFileDescriptor.getFileDescriptor();
                EncodedImage encodedImage = getEncodedImage(SentryFileInputStream.Factory.create(new FileInputStream(fileDescriptor), fileDescriptor), (int) openFileDescriptor.getStatSize());
                Intrinsics.checkNotNullExpressionValue(encodedImage, "this.getEncodedImage(Fil…criptor.statSize.toInt())");
                openFileDescriptor.close();
                return encodedImage;
            }
            throw new IllegalStateException("Required value was null.".toString());
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/imagepipeline/producers/LocalContentUriFetchProducer$Companion;", "", "()V", "PRODUCER_NAME", "", "PROJECTION", "", "[Ljava/lang/String;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LocalContentUriFetchProducer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
