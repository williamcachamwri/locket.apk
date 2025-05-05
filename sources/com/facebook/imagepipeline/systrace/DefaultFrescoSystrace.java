package com.facebook.imagepipeline.systrace;

import android.os.Trace;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/systrace/DefaultFrescoSystrace;", "Lcom/facebook/imagepipeline/systrace/FrescoSystrace$Systrace;", "()V", "beginSection", "", "name", "", "beginSectionWithArgs", "Lcom/facebook/imagepipeline/systrace/FrescoSystrace$ArgsBuilder;", "endSection", "isTracing", "", "DefaultArgsBuilder", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultFrescoSystrace.kt */
public final class DefaultFrescoSystrace implements FrescoSystrace.Systrace {
    public boolean isTracing() {
        return false;
    }

    public void beginSection(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        if (isTracing()) {
            Trace.beginSection(str);
        }
    }

    public FrescoSystrace.ArgsBuilder beginSectionWithArgs(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        if (isTracing()) {
            return new DefaultArgsBuilder(str);
        }
        return FrescoSystrace.NO_OP_ARGS_BUILDER;
    }

    public void endSection() {
        if (isTracing()) {
            Trace.endSection();
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\b\u001a\n \t*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\u0018\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u000fH\u0016J\u0018\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0012\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/imagepipeline/systrace/DefaultFrescoSystrace$DefaultArgsBuilder;", "Lcom/facebook/imagepipeline/systrace/FrescoSystrace$ArgsBuilder;", "name", "", "(Ljava/lang/String;)V", "stringBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "appendArgument", "kotlin.jvm.PlatformType", "key", "value", "", "arg", "", "", "", "flush", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DefaultFrescoSystrace.kt */
    private static final class DefaultArgsBuilder implements FrescoSystrace.ArgsBuilder {
        private final StringBuilder stringBuilder;

        public DefaultArgsBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.stringBuilder = new StringBuilder(str);
        }

        public void flush() {
            if (this.stringBuilder.length() > 127) {
                this.stringBuilder.setLength(127);
            }
            Trace.beginSection(this.stringBuilder.toString());
        }

        public DefaultArgsBuilder arg(String str, Object obj) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(obj, "value");
            DefaultArgsBuilder defaultArgsBuilder = this;
            appendArgument(str, obj);
            return this;
        }

        public DefaultArgsBuilder arg(String str, int i) {
            Intrinsics.checkNotNullParameter(str, "key");
            DefaultArgsBuilder defaultArgsBuilder = this;
            appendArgument(str, Integer.valueOf(i));
            return this;
        }

        public DefaultArgsBuilder arg(String str, long j) {
            Intrinsics.checkNotNullParameter(str, "key");
            DefaultArgsBuilder defaultArgsBuilder = this;
            appendArgument(str, Long.valueOf(j));
            return this;
        }

        public DefaultArgsBuilder arg(String str, double d) {
            Intrinsics.checkNotNullParameter(str, "key");
            DefaultArgsBuilder defaultArgsBuilder = this;
            appendArgument(str, Double.valueOf(d));
            return this;
        }

        private final StringBuilder appendArgument(String str, Object obj) {
            return this.stringBuilder.append(';').append(str + '=' + obj);
        }
    }
}
