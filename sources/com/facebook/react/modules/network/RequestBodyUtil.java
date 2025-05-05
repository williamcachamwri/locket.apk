package com.facebook.react.modules.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import io.sentry.instrumentation.file.SentryFileInputStream;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.GZIPOutputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

class RequestBodyUtil {
    private static final String CONTENT_ENCODING_GZIP = "gzip";
    private static final String NAME = "RequestBodyUtil";
    private static final String TEMP_FILE_SUFFIX = "temp";

    RequestBodyUtil() {
    }

    public static boolean isGzipEncoding(String str) {
        return CONTENT_ENCODING_GZIP.equalsIgnoreCase(str);
    }

    public static InputStream getFileInputStream(Context context, String str) {
        try {
            Uri parse = Uri.parse(str);
            if (parse.getScheme().startsWith("http")) {
                return getDownloadFileInputStream(context, parse);
            }
            if (!str.startsWith("data:")) {
                return context.getContentResolver().openInputStream(parse);
            }
            byte[] decode = Base64.decode(str.split(",")[1], 0);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeByteArray.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            FLog.e(ReactConstants.TAG, "Could not retrieve file for contentUri " + str, (Throwable) e);
            return null;
        }
    }

    private static InputStream getDownloadFileInputStream(Context context, Uri uri) throws IOException {
        FileOutputStream create;
        File createTempFile = File.createTempFile(NAME, TEMP_FILE_SUFFIX, context.getApplicationContext().getCacheDir());
        createTempFile.deleteOnExit();
        InputStream openStream = FirebasePerfUrlConnection.openStream(new URL(uri.toString()));
        try {
            ReadableByteChannel newChannel = Channels.newChannel(openStream);
            try {
                create = SentryFileOutputStream.Factory.create(new FileOutputStream(createTempFile), createTempFile);
                create.getChannel().transferFrom(newChannel, 0, Long.MAX_VALUE);
                FileInputStream create2 = SentryFileInputStream.Factory.create(new FileInputStream(createTempFile), createTempFile);
                create.close();
                newChannel.close();
                return create2;
            } catch (Throwable th) {
                newChannel.close();
                throw th;
            }
        } finally {
            openStream.close();
        }
    }

    public static RequestBody createGzip(MediaType mediaType, String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.close();
            return RequestBody.create(mediaType, byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void closeQuietly(Source source) {
        try {
            source.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static RequestBody create(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() {
            public MediaType contentType() {
                return MediaType.this;
            }

            public long contentLength() {
                try {
                    return (long) inputStream.available();
                } catch (IOException unused) {
                    return 0;
                }
            }

            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(inputStream);
                    bufferedSink.writeAll(source);
                } finally {
                    RequestBodyUtil.closeQuietly(source);
                }
            }
        };
    }

    public static ProgressRequestBody createProgressRequest(RequestBody requestBody, ProgressListener progressListener) {
        return new ProgressRequestBody(requestBody, progressListener);
    }

    public static RequestBody getEmptyBody(String str) {
        if (str.equals("POST") || str.equals(FirebasePerformance.HttpMethod.PUT) || str.equals(FirebasePerformance.HttpMethod.PATCH)) {
            return RequestBody.create((MediaType) null, ByteString.EMPTY);
        }
        return null;
    }
}
