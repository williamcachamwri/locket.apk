package com.squareup.kotlinpoet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"com/squareup/kotlinpoet/FileSpec$toJavaFileObject$1", "Ljavax/tools/SimpleJavaFileObject;", "lastModified", "", "getCharContent", "", "ignoreEncodingErrors", "", "getLastModified", "openInputStream", "Ljava/io/InputStream;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: FileSpec.kt */
public final class FileSpec$toJavaFileObject$1 extends SimpleJavaFileObject {
    private final long lastModified = System.currentTimeMillis();
    final /* synthetic */ FileSpec this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileSpec$toJavaFileObject$1(URI uri, FileSpec fileSpec, JavaFileObject.Kind kind) {
        super(uri, kind);
        this.this$0 = fileSpec;
    }

    public String getCharContent(boolean z) {
        return this.this$0.toString();
    }

    public InputStream openInputStream() {
        String charContent = getCharContent(true);
        Charset charset = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(charset, "UTF_8");
        byte[] bytes = charContent.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(bytes);
    }

    public long getLastModified() {
        return this.lastModified;
    }
}
