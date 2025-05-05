package com.google.firebase.firestore.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {
    public static void delete(File file) throws IOException {
        DefaultFileDeleter.delete(file);
    }

    private static class DefaultFileDeleter {
        private DefaultFileDeleter() {
        }

        public static void delete(File file) throws IOException {
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException e) {
                throw new IOException("Failed to delete file " + file + ": " + e);
            }
        }
    }

    private static class LegacyFileDeleter {
        private LegacyFileDeleter() {
        }

        public static void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("Failed to delete file " + file);
            }
        }
    }
}
