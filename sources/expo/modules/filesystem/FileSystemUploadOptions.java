package expo.modules.filesystem;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Ba\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\u0017\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0017\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003Jo\u0010*\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u00020\u0004HÖ\u0001R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R*\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u001a\u0010\u0012R*\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001c\u0010\u0015R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0010\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010\t\u001a\u00020\n8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0010\u001a\u0004\b!\u0010\"¨\u00062"}, d2 = {"Lexpo/modules/filesystem/FileSystemUploadOptions;", "Lexpo/modules/kotlin/records/Record;", "headers", "", "", "httpMethod", "Lexpo/modules/filesystem/HttpMethod;", "sessionType", "Lexpo/modules/filesystem/SessionType;", "uploadType", "Lexpo/modules/filesystem/FileSystemUploadType;", "fieldName", "mimeType", "parameters", "(Ljava/util/Map;Lexpo/modules/filesystem/HttpMethod;Lexpo/modules/filesystem/SessionType;Lexpo/modules/filesystem/FileSystemUploadType;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getFieldName$annotations", "()V", "getFieldName", "()Ljava/lang/String;", "getHeaders$annotations", "getHeaders", "()Ljava/util/Map;", "getHttpMethod$annotations", "getHttpMethod", "()Lexpo/modules/filesystem/HttpMethod;", "getMimeType$annotations", "getMimeType", "getParameters$annotations", "getParameters", "getSessionType$annotations", "getSessionType", "()Lexpo/modules/filesystem/SessionType;", "getUploadType$annotations", "getUploadType", "()Lexpo/modules/filesystem/FileSystemUploadType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemRecords.kt */
public final class FileSystemUploadOptions implements Record {
    private final String fieldName;
    private final Map<String, String> headers;
    private final HttpMethod httpMethod;
    private final String mimeType;
    private final Map<String, String> parameters;
    private final SessionType sessionType;
    private final FileSystemUploadType uploadType;

    public static /* synthetic */ FileSystemUploadOptions copy$default(FileSystemUploadOptions fileSystemUploadOptions, Map<String, String> map, HttpMethod httpMethod2, SessionType sessionType2, FileSystemUploadType fileSystemUploadType, String str, String str2, Map<String, String> map2, int i, Object obj) {
        if ((i & 1) != 0) {
            map = fileSystemUploadOptions.headers;
        }
        if ((i & 2) != 0) {
            httpMethod2 = fileSystemUploadOptions.httpMethod;
        }
        HttpMethod httpMethod3 = httpMethod2;
        if ((i & 4) != 0) {
            sessionType2 = fileSystemUploadOptions.sessionType;
        }
        SessionType sessionType3 = sessionType2;
        if ((i & 8) != 0) {
            fileSystemUploadType = fileSystemUploadOptions.uploadType;
        }
        FileSystemUploadType fileSystemUploadType2 = fileSystemUploadType;
        if ((i & 16) != 0) {
            str = fileSystemUploadOptions.fieldName;
        }
        String str3 = str;
        if ((i & 32) != 0) {
            str2 = fileSystemUploadOptions.mimeType;
        }
        String str4 = str2;
        if ((i & 64) != 0) {
            map2 = fileSystemUploadOptions.parameters;
        }
        return fileSystemUploadOptions.copy(map, httpMethod3, sessionType3, fileSystemUploadType2, str3, str4, map2);
    }

    @Field
    public static /* synthetic */ void getFieldName$annotations() {
    }

    @Field
    public static /* synthetic */ void getHeaders$annotations() {
    }

    @Field
    public static /* synthetic */ void getHttpMethod$annotations() {
    }

    @Field
    public static /* synthetic */ void getMimeType$annotations() {
    }

    @Field
    public static /* synthetic */ void getParameters$annotations() {
    }

    @Field
    public static /* synthetic */ void getSessionType$annotations() {
    }

    @Field
    public static /* synthetic */ void getUploadType$annotations() {
    }

    public final Map<String, String> component1() {
        return this.headers;
    }

    public final HttpMethod component2() {
        return this.httpMethod;
    }

    public final SessionType component3() {
        return this.sessionType;
    }

    public final FileSystemUploadType component4() {
        return this.uploadType;
    }

    public final String component5() {
        return this.fieldName;
    }

    public final String component6() {
        return this.mimeType;
    }

    public final Map<String, String> component7() {
        return this.parameters;
    }

    public final FileSystemUploadOptions copy(Map<String, String> map, HttpMethod httpMethod2, SessionType sessionType2, FileSystemUploadType fileSystemUploadType, String str, String str2, Map<String, String> map2) {
        Intrinsics.checkNotNullParameter(httpMethod2, "httpMethod");
        Intrinsics.checkNotNullParameter(sessionType2, "sessionType");
        Intrinsics.checkNotNullParameter(fileSystemUploadType, "uploadType");
        return new FileSystemUploadOptions(map, httpMethod2, sessionType2, fileSystemUploadType, str, str2, map2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FileSystemUploadOptions)) {
            return false;
        }
        FileSystemUploadOptions fileSystemUploadOptions = (FileSystemUploadOptions) obj;
        return Intrinsics.areEqual((Object) this.headers, (Object) fileSystemUploadOptions.headers) && this.httpMethod == fileSystemUploadOptions.httpMethod && this.sessionType == fileSystemUploadOptions.sessionType && this.uploadType == fileSystemUploadOptions.uploadType && Intrinsics.areEqual((Object) this.fieldName, (Object) fileSystemUploadOptions.fieldName) && Intrinsics.areEqual((Object) this.mimeType, (Object) fileSystemUploadOptions.mimeType) && Intrinsics.areEqual((Object) this.parameters, (Object) fileSystemUploadOptions.parameters);
    }

    public int hashCode() {
        Map<String, String> map = this.headers;
        int i = 0;
        int hashCode = (((((((map == null ? 0 : map.hashCode()) * 31) + this.httpMethod.hashCode()) * 31) + this.sessionType.hashCode()) * 31) + this.uploadType.hashCode()) * 31;
        String str = this.fieldName;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.mimeType;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, String> map2 = this.parameters;
        if (map2 != null) {
            i = map2.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        Map<String, String> map = this.headers;
        HttpMethod httpMethod2 = this.httpMethod;
        SessionType sessionType2 = this.sessionType;
        FileSystemUploadType fileSystemUploadType = this.uploadType;
        String str = this.fieldName;
        String str2 = this.mimeType;
        return "FileSystemUploadOptions(headers=" + map + ", httpMethod=" + httpMethod2 + ", sessionType=" + sessionType2 + ", uploadType=" + fileSystemUploadType + ", fieldName=" + str + ", mimeType=" + str2 + ", parameters=" + this.parameters + ")";
    }

    public FileSystemUploadOptions(Map<String, String> map, HttpMethod httpMethod2, SessionType sessionType2, FileSystemUploadType fileSystemUploadType, String str, String str2, Map<String, String> map2) {
        Intrinsics.checkNotNullParameter(httpMethod2, "httpMethod");
        Intrinsics.checkNotNullParameter(sessionType2, "sessionType");
        Intrinsics.checkNotNullParameter(fileSystemUploadType, "uploadType");
        this.headers = map;
        this.httpMethod = httpMethod2;
        this.sessionType = sessionType2;
        this.uploadType = fileSystemUploadType;
        this.fieldName = str;
        this.mimeType = str2;
        this.parameters = map2;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ FileSystemUploadOptions(java.util.Map r10, expo.modules.filesystem.HttpMethod r11, expo.modules.filesystem.SessionType r12, expo.modules.filesystem.FileSystemUploadType r13, java.lang.String r14, java.lang.String r15, java.util.Map r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r9 = this;
            r0 = r17 & 2
            if (r0 == 0) goto L_0x0008
            expo.modules.filesystem.HttpMethod r0 = expo.modules.filesystem.HttpMethod.POST
            r3 = r0
            goto L_0x0009
        L_0x0008:
            r3 = r11
        L_0x0009:
            r0 = r17 & 4
            if (r0 == 0) goto L_0x0011
            expo.modules.filesystem.SessionType r0 = expo.modules.filesystem.SessionType.BACKGROUND
            r4 = r0
            goto L_0x0012
        L_0x0011:
            r4 = r12
        L_0x0012:
            r1 = r9
            r2 = r10
            r5 = r13
            r6 = r14
            r7 = r15
            r8 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemUploadOptions.<init>(java.util.Map, expo.modules.filesystem.HttpMethod, expo.modules.filesystem.SessionType, expo.modules.filesystem.FileSystemUploadType, java.lang.String, java.lang.String, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public final SessionType getSessionType() {
        return this.sessionType;
    }

    public final FileSystemUploadType getUploadType() {
        return this.uploadType;
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final Map<String, String> getParameters() {
        return this.parameters;
    }
}
