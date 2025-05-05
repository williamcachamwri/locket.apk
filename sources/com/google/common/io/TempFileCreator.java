package com.google.common.io;

import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import io.sentry.protocol.SentryThread;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.FileAttribute;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

@ElementTypesAreNonnullByDefault
abstract class TempFileCreator {
    static final TempFileCreator INSTANCE = pickSecureCreator();

    /* access modifiers changed from: package-private */
    public abstract File createTempDir();

    /* access modifiers changed from: package-private */
    public abstract File createTempFile(String str) throws IOException;

    private static TempFileCreator pickSecureCreator() {
        try {
            Class.forName("java.nio.file.Path");
            return new JavaNioCreator();
        } catch (ClassNotFoundException unused) {
            try {
                if (((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null)).intValue() < ((Integer) Class.forName("android.os.Build$VERSION_CODES").getField("JELLY_BEAN").get((Object) null)).intValue()) {
                    return new ThrowingCreator();
                }
                return new JavaIoCreator();
            } catch (NoSuchFieldException unused2) {
                return new ThrowingCreator();
            } catch (ClassNotFoundException unused3) {
                return new ThrowingCreator();
            } catch (IllegalAccessException unused4) {
                return new ThrowingCreator();
            }
        }
    }

    static void testMakingUserPermissionsFromScratch() throws IOException {
        JavaNioCreator.userPermissions().get();
    }

    private static final class JavaNioCreator extends TempFileCreator {
        private static final PermissionSupplier directoryPermissions;
        private static final PermissionSupplier filePermissions;

        private interface PermissionSupplier {
            FileAttribute<?> get() throws IOException;
        }

        static /* synthetic */ FileAttribute lambda$userPermissions$3(FileAttribute fileAttribute) throws IOException {
            return fileAttribute;
        }

        private JavaNioCreator() {
            super();
        }

        /* access modifiers changed from: package-private */
        public File createTempDir() {
            try {
                return Files.createTempDirectory(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value(), new String[0]), (String) null, new FileAttribute[]{directoryPermissions.get()}).toFile();
            } catch (IOException e) {
                throw new IllegalStateException("Failed to create directory", e);
            }
        }

        /* access modifiers changed from: package-private */
        public File createTempFile(String str) throws IOException {
            return Files.createTempFile(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value(), new String[0]), str, (String) null, new FileAttribute[]{filePermissions.get()}).toFile();
        }

        static {
            Set<String> supportedFileAttributeViews = FileSystems.getDefault().supportedFileAttributeViews();
            if (supportedFileAttributeViews.contains("posix")) {
                filePermissions = new TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda0();
                directoryPermissions = new TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda1();
            } else if (supportedFileAttributeViews.contains("acl")) {
                PermissionSupplier userPermissions = userPermissions();
                directoryPermissions = userPermissions;
                filePermissions = userPermissions;
            } else {
                TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda2 tempFileCreator$JavaNioCreator$$ExternalSyntheticLambda2 = new TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda2();
                directoryPermissions = tempFileCreator$JavaNioCreator$$ExternalSyntheticLambda2;
                filePermissions = tempFileCreator$JavaNioCreator$$ExternalSyntheticLambda2;
            }
        }

        static /* synthetic */ FileAttribute lambda$static$2() throws IOException {
            throw new IOException("unrecognized FileSystem type " + FileSystems.getDefault());
        }

        /* access modifiers changed from: private */
        public static PermissionSupplier userPermissions() {
            try {
                final ImmutableList of = ImmutableList.of(AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName(getUsername())).setPermissions(EnumSet.allOf(AclEntryPermission.class)).setFlags(new AclEntryFlag[]{AclEntryFlag.DIRECTORY_INHERIT, AclEntryFlag.FILE_INHERIT}).build());
                return new TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda3(new FileAttribute<ImmutableList<AclEntry>>() {
                    public String name() {
                        return "acl:acl";
                    }

                    public ImmutableList<AclEntry> value() {
                        return ImmutableList.this;
                    }
                });
            } catch (IOException e) {
                return new TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda4(e);
            }
        }

        static /* synthetic */ FileAttribute lambda$userPermissions$4(IOException iOException) throws IOException {
            throw new IOException("Could not find user", iOException);
        }

        private static String getUsername() {
            String str = (String) Objects.requireNonNull(StandardSystemProperty.USER_NAME.value());
            try {
                Class<?> cls = Class.forName("java.lang.ProcessHandle");
                Class<?> cls2 = Class.forName("java.lang.ProcessHandle$Info");
                Class<?> cls3 = Class.forName("java.util.Optional");
                Method method = cls.getMethod(SentryThread.JsonKeys.CURRENT, new Class[0]);
                Method method2 = cls.getMethod("info", new Class[0]);
                return (String) Objects.requireNonNull(cls3.getMethod("orElse", new Class[]{Object.class}).invoke(cls2.getMethod("user", new Class[0]).invoke(method2.invoke(method.invoke((Object) null, new Object[0]), new Object[0]), new Object[0]), new Object[]{str}));
            } catch (ClassNotFoundException unused) {
                return str;
            } catch (InvocationTargetException e) {
                Throwables.throwIfUnchecked(e.getCause());
                return str;
            } catch (IllegalAccessException | NoSuchMethodException unused2) {
                return str;
            }
        }
    }

    private static final class JavaIoCreator extends TempFileCreator {
        private static final int TEMP_DIR_ATTEMPTS = 10000;

        private JavaIoCreator() {
            super();
        }

        /* access modifiers changed from: package-private */
        public File createTempDir() {
            File file = new File(StandardSystemProperty.JAVA_IO_TMPDIR.value());
            String str = System.currentTimeMillis() + "-";
            for (int i = 0; i < 10000; i++) {
                File file2 = new File(file, str + i);
                if (file2.mkdir()) {
                    return file2;
                }
            }
            throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + str + "0 to " + str + "9999)");
        }

        /* access modifiers changed from: package-private */
        public File createTempFile(String str) throws IOException {
            return File.createTempFile(str, (String) null, (File) null);
        }
    }

    private static final class ThrowingCreator extends TempFileCreator {
        private static final String MESSAGE = "Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().";

        private ThrowingCreator() {
            super();
        }

        /* access modifiers changed from: package-private */
        public File createTempDir() {
            throw new IllegalStateException(MESSAGE);
        }

        /* access modifiers changed from: package-private */
        public File createTempFile(String str) throws IOException {
            throw new IOException(MESSAGE);
        }
    }

    private TempFileCreator() {
    }
}
