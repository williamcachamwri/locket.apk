package com.facebook.common.internal;

import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Files {
    private Files() {
    }

    static byte[] readFile(InputStream inputStream, long j) throws IOException {
        if (j > 2147483647L) {
            throw new OutOfMemoryError("file is too large to fit in a byte array: " + j + " bytes");
        } else if (j == 0) {
            return ByteStreams.toByteArray(inputStream);
        } else {
            return ByteStreams.toByteArray(inputStream, (int) j);
        }
    }

    public static byte[] toByteArray(File file) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
            return readFile(fileInputStream, fileInputStream.getChannel().size());
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
