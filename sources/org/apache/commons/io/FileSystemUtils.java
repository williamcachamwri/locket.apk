package org.apache.commons.io;

import io.sentry.protocol.ViewHierarchy;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

@Deprecated
public class FileSystemUtils {
    private static final String DF;
    private static final int INIT_PROBLEM = -1;
    private static final FileSystemUtils INSTANCE = new FileSystemUtils();
    private static final int OS;
    private static final int OTHER = 0;
    private static final int POSIX_UNIX = 3;
    private static final int UNIX = 2;
    private static final int WINDOWS = 1;

    static {
        int i;
        String str = "df";
        try {
            String property = System.getProperty("os.name");
            if (property != null) {
                String lowerCase = property.toLowerCase(Locale.ENGLISH);
                if (lowerCase.contains(ViewHierarchy.JsonKeys.WINDOWS)) {
                    i = 1;
                } else {
                    if (!lowerCase.contains("linux") && !lowerCase.contains("mpe/ix") && !lowerCase.contains("freebsd") && !lowerCase.contains("openbsd") && !lowerCase.contains("irix") && !lowerCase.contains("digital unix") && !lowerCase.contains("unix")) {
                        if (!lowerCase.contains("mac os x")) {
                            if (!lowerCase.contains("sun os") && !lowerCase.contains("sunos")) {
                                if (!lowerCase.contains("solaris")) {
                                    if (!lowerCase.contains("hp-ux")) {
                                        if (!lowerCase.contains("aix")) {
                                            i = 0;
                                        }
                                    }
                                    i = 3;
                                }
                            }
                            str = "/usr/xpg4/bin/df";
                            i = 3;
                        }
                    }
                    i = 2;
                }
                OS = i;
                DF = str;
                return;
            }
            throw new IOException("os.name not found");
        } catch (Exception unused) {
            i = -1;
        }
    }

    @Deprecated
    public static long freeSpace(String str) throws IOException {
        return INSTANCE.freeSpaceOS(str, OS, false, -1);
    }

    @Deprecated
    public static long freeSpaceKb(String str) throws IOException {
        return freeSpaceKb(str, -1);
    }

    @Deprecated
    public static long freeSpaceKb(String str, long j) throws IOException {
        return INSTANCE.freeSpaceOS(str, OS, true, j);
    }

    @Deprecated
    public static long freeSpaceKb() throws IOException {
        return freeSpaceKb(-1);
    }

    @Deprecated
    public static long freeSpaceKb(long j) throws IOException {
        return freeSpaceKb(new File(".").getAbsolutePath(), j);
    }

    /* access modifiers changed from: package-private */
    public long freeSpaceOS(String str, int i, boolean z, long j) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Path must not be null");
        } else if (i == 0) {
            throw new IllegalStateException("Unsupported operating system");
        } else if (i == 1) {
            long freeSpaceWindows = freeSpaceWindows(str, j);
            return z ? freeSpaceWindows / 1024 : freeSpaceWindows;
        } else if (i == 2) {
            return freeSpaceUnix(str, z, false, j);
        } else {
            if (i == 3) {
                return freeSpaceUnix(str, z, true, j);
            }
            throw new IllegalStateException("Exception caught when determining operating system");
        }
    }

    /* access modifiers changed from: package-private */
    public long freeSpaceWindows(String str, long j) throws IOException {
        String normalize = FilenameUtils.normalize(str, false);
        if (normalize != null) {
            if (normalize.length() > 0 && normalize.charAt(0) != '\"') {
                normalize = "\"" + normalize + "\"";
            }
            List<String> performCommand = performCommand(new String[]{"cmd.exe", "/C", "dir /a /-c " + normalize}, Integer.MAX_VALUE, j);
            for (int size = performCommand.size() - 1; size >= 0; size--) {
                String str2 = performCommand.get(size);
                if (str2.length() > 0) {
                    return parseDir(str2, normalize);
                }
            }
            throw new IOException("Command line 'dir /-c' did not return any info for path '" + normalize + "'");
        }
        throw new IllegalArgumentException(str);
    }

    /* access modifiers changed from: package-private */
    public long parseDir(String str, String str2) throws IOException {
        int i;
        int i2;
        int i3;
        int length = str.length();
        while (true) {
            length--;
            i = 0;
            if (length < 0) {
                i2 = 0;
                break;
            } else if (Character.isDigit(str.charAt(length))) {
                i2 = length + 1;
                break;
            }
        }
        while (true) {
            if (length < 0) {
                i3 = 0;
                break;
            }
            char charAt = str.charAt(length);
            if (!Character.isDigit(charAt) && charAt != ',' && charAt != '.') {
                i3 = length + 1;
                break;
            }
            length--;
        }
        if (length >= 0) {
            StringBuilder sb = new StringBuilder(str.substring(i3, i2));
            while (i < sb.length()) {
                if (sb.charAt(i) == ',' || sb.charAt(i) == '.') {
                    sb.deleteCharAt(i);
                    i--;
                }
                i++;
            }
            return parseBytes(sb.toString(), str2);
        }
        throw new IOException("Command line 'dir /-c' did not return valid info for path '" + str2 + "'");
    }

    /* access modifiers changed from: package-private */
    public long freeSpaceUnix(String str, boolean z, boolean z2, long j) throws IOException {
        if (!str.isEmpty()) {
            String str2 = z ? "-k" : "-";
            if (z2) {
                str2 = str2 + "P";
            }
            List<String> performCommand = performCommand(str2.length() > 1 ? new String[]{DF, str2, str} : new String[]{DF, str}, 3, j);
            if (performCommand.size() >= 2) {
                StringTokenizer stringTokenizer = new StringTokenizer(performCommand.get(1), " ");
                if (stringTokenizer.countTokens() >= 4) {
                    stringTokenizer.nextToken();
                } else if (stringTokenizer.countTokens() != 1 || performCommand.size() < 3) {
                    throw new IOException("Command line '" + DF + "' did not return data as expected for path '" + str + "'- check path is valid");
                } else {
                    stringTokenizer = new StringTokenizer(performCommand.get(2), " ");
                }
                stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                return parseBytes(stringTokenizer.nextToken(), str);
            }
            throw new IOException("Command line '" + DF + "' did not return info as expected for path '" + str + "'- response was " + performCommand);
        }
        throw new IllegalArgumentException("Path must not be empty");
    }

    /* access modifiers changed from: package-private */
    public long parseBytes(String str, String str2) throws IOException {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong >= 0) {
                return parseLong;
            }
            throw new IOException("Command line '" + DF + "' did not find free space in response for path '" + str2 + "'- check path is valid");
        } catch (NumberFormatException e) {
            throw new IOException("Command line '" + DF + "' did not return numeric data as expected for path '" + str2 + "'- check path is valid", e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.Process} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.Process} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.Process} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Process} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.lang.Process} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v17, resolved type: java.io.BufferedReader} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0137  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> performCommand(java.lang.String[] r11, int r12, long r13) throws java.io.IOException {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 20
            r0.<init>(r1)
            r1 = 0
            java.lang.Thread r2 = org.apache.commons.io.ThreadMonitor.start(r13)     // Catch:{ InterruptedException -> 0x00fa, all -> 0x00f4 }
            java.lang.Process r3 = r10.openProcess(r11)     // Catch:{ InterruptedException -> 0x00fa, all -> 0x00f4 }
            java.io.InputStream r4 = r3.getInputStream()     // Catch:{ InterruptedException -> 0x00ed, all -> 0x00ea }
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ InterruptedException -> 0x00e7, all -> 0x00e2 }
            java.io.InputStream r6 = r3.getErrorStream()     // Catch:{ InterruptedException -> 0x00df, all -> 0x00dc }
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ InterruptedException -> 0x00d9, all -> 0x00d5 }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ InterruptedException -> 0x00d9, all -> 0x00d5 }
            java.nio.charset.Charset r9 = java.nio.charset.Charset.defaultCharset()     // Catch:{ InterruptedException -> 0x00d9, all -> 0x00d5 }
            r8.<init>(r4, r9)     // Catch:{ InterruptedException -> 0x00d9, all -> 0x00d5 }
            r7.<init>(r8)     // Catch:{ InterruptedException -> 0x00d9, all -> 0x00d5 }
            java.lang.String r8 = r7.readLine()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
        L_0x002e:
            if (r8 == 0) goto L_0x0048
            int r9 = r0.size()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            if (r9 >= r12) goto L_0x0048
            java.util.Locale r9 = java.util.Locale.ENGLISH     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.String r8 = r8.toLowerCase(r9)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.String r8 = r8.trim()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            r0.add(r8)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.String r8 = r7.readLine()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            goto L_0x002e
        L_0x0048:
            r3.waitFor()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            org.apache.commons.io.ThreadMonitor.stop(r2)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            int r12 = r3.exitValue()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            if (r12 != 0) goto L_0x00a5
            boolean r12 = r0.isEmpty()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            if (r12 != 0) goto L_0x0088
            r7.close()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            r4.close()     // Catch:{ InterruptedException -> 0x00d9, all -> 0x00d5 }
            if (r5 == 0) goto L_0x0070
            r5.close()     // Catch:{ InterruptedException -> 0x006b, all -> 0x0067 }
            r5 = r1
            goto L_0x0070
        L_0x0067:
            r11 = move-exception
            r7 = r1
            goto L_0x0129
        L_0x006b:
            r12 = move-exception
            r4 = r1
            r7 = r4
            goto L_0x00f2
        L_0x0070:
            if (r6 == 0) goto L_0x0076
            r6.close()     // Catch:{ InterruptedException -> 0x006b, all -> 0x0067 }
            r6 = r1
        L_0x0076:
            org.apache.commons.io.IOUtils.closeQuietly((java.io.InputStream) r1)
            org.apache.commons.io.IOUtils.closeQuietly((java.io.OutputStream) r5)
            org.apache.commons.io.IOUtils.closeQuietly((java.io.InputStream) r6)
            org.apache.commons.io.IOUtils.closeQuietly((java.io.Reader) r1)
            if (r3 == 0) goto L_0x0087
            r3.destroy()
        L_0x0087:
            return r0
        L_0x0088:
            java.io.IOException r12 = new java.io.IOException     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            r0.<init>()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.String r1 = "Command line did not return any info for command "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.util.List r1 = java.util.Arrays.asList(r11)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.String r0 = r0.toString()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            r12.<init>(r0)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            throw r12     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
        L_0x00a5:
            java.io.IOException r12 = new java.io.IOException     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            r0.<init>()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.String r1 = "Command line returned OS error code '"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            int r1 = r3.exitValue()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.String r1 = "' for command "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.util.List r1 = java.util.Arrays.asList(r11)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            java.lang.String r0 = r0.toString()     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            r12.<init>(r0)     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
            throw r12     // Catch:{ InterruptedException -> 0x00d3, all -> 0x00d0 }
        L_0x00d0:
            r11 = move-exception
            goto L_0x0128
        L_0x00d3:
            r12 = move-exception
            goto L_0x00f2
        L_0x00d5:
            r11 = move-exception
            r7 = r1
            goto L_0x0128
        L_0x00d9:
            r12 = move-exception
            r7 = r1
            goto L_0x00f2
        L_0x00dc:
            r11 = move-exception
            r6 = r1
            goto L_0x00e5
        L_0x00df:
            r12 = move-exception
            r6 = r1
            goto L_0x00f1
        L_0x00e2:
            r11 = move-exception
            r5 = r1
            r6 = r5
        L_0x00e5:
            r7 = r6
            goto L_0x0128
        L_0x00e7:
            r12 = move-exception
            r5 = r1
            goto L_0x00f0
        L_0x00ea:
            r11 = move-exception
            r5 = r1
            goto L_0x00f7
        L_0x00ed:
            r12 = move-exception
            r4 = r1
            r5 = r4
        L_0x00f0:
            r6 = r5
        L_0x00f1:
            r7 = r6
        L_0x00f2:
            r1 = r3
            goto L_0x00ff
        L_0x00f4:
            r11 = move-exception
            r3 = r1
            r5 = r3
        L_0x00f7:
            r6 = r5
            r7 = r6
            goto L_0x0129
        L_0x00fa:
            r12 = move-exception
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x00ff:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0126 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0126 }
            r2.<init>()     // Catch:{ all -> 0x0126 }
            java.lang.String r3 = "Command line threw an InterruptedException for command "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0126 }
            java.util.List r11 = java.util.Arrays.asList(r11)     // Catch:{ all -> 0x0126 }
            java.lang.StringBuilder r11 = r2.append(r11)     // Catch:{ all -> 0x0126 }
            java.lang.String r2 = " timeout="
            java.lang.StringBuilder r11 = r11.append(r2)     // Catch:{ all -> 0x0126 }
            java.lang.StringBuilder r11 = r11.append(r13)     // Catch:{ all -> 0x0126 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0126 }
            r0.<init>(r11, r12)     // Catch:{ all -> 0x0126 }
            throw r0     // Catch:{ all -> 0x0126 }
        L_0x0126:
            r11 = move-exception
            r3 = r1
        L_0x0128:
            r1 = r4
        L_0x0129:
            org.apache.commons.io.IOUtils.closeQuietly((java.io.InputStream) r1)
            org.apache.commons.io.IOUtils.closeQuietly((java.io.OutputStream) r5)
            org.apache.commons.io.IOUtils.closeQuietly((java.io.InputStream) r6)
            org.apache.commons.io.IOUtils.closeQuietly((java.io.Reader) r7)
            if (r3 == 0) goto L_0x013a
            r3.destroy()
        L_0x013a:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.FileSystemUtils.performCommand(java.lang.String[], int, long):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public Process openProcess(String[] strArr) throws IOException {
        return Runtime.getRuntime().exec(strArr);
    }
}
