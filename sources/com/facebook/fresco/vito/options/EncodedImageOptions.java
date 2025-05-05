package com.facebook.fresco.vito.options;

import com.facebook.common.internal.Objects;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0017B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0000H\u0004J\u0013\u0010\u0010\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/facebook/fresco/vito/options/EncodedImageOptions;", "", "builder", "Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;", "(Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;)V", "cacheChoice", "Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;", "getCacheChoice", "()Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;", "priority", "Lcom/facebook/imagepipeline/common/Priority;", "getPriority", "()Lcom/facebook/imagepipeline/common/Priority;", "equalEncodedOptions", "", "other", "equals", "hashCode", "", "toString", "", "toStringHelper", "Lcom/facebook/common/internal/Objects$ToStringHelper;", "Builder", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EncodedImageOptions.kt */
public class EncodedImageOptions {
    private final ImageRequest.CacheChoice cacheChoice;
    private final Priority priority;

    public EncodedImageOptions(Builder<?> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.priority = builder.getPriority$options_release();
        this.cacheChoice = builder.getCacheChoice$options_release();
    }

    public final Priority getPriority() {
        return this.priority;
    }

    public final ImageRequest.CacheChoice getCacheChoice() {
        return this.cacheChoice;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Intrinsics.areEqual((Object) getClass(), (Object) obj.getClass())) {
            return false;
        }
        return equalEncodedOptions((EncodedImageOptions) obj);
    }

    /* access modifiers changed from: protected */
    public final boolean equalEncodedOptions(EncodedImageOptions encodedImageOptions) {
        Intrinsics.checkNotNullParameter(encodedImageOptions, "other");
        return Objects.equal(this.priority, encodedImageOptions.priority) && Objects.equal(this.cacheChoice, encodedImageOptions.cacheChoice);
    }

    public int hashCode() {
        Priority priority2 = this.priority;
        int i = 0;
        int hashCode = (priority2 != null ? priority2.hashCode() : 0) * 31;
        ImageRequest.CacheChoice cacheChoice2 = this.cacheChoice;
        if (cacheChoice2 != null) {
            i = cacheChoice2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        String toStringHelper = toStringHelper().toString();
        Intrinsics.checkNotNullExpressionValue(toStringHelper, "toStringHelper().toString()");
        return toStringHelper;
    }

    /* access modifiers changed from: protected */
    public Objects.ToStringHelper toStringHelper() {
        Objects.ToStringHelper add = Objects.toStringHelper((Object) this).add(SentryThread.JsonKeys.PRIORITY, (Object) this.priority).add("cacheChoice", (Object) this.cacheChoice);
        Intrinsics.checkNotNullExpressionValue(add, "toStringHelper(this).add…acheChoice\", cacheChoice)");
        return add;
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u0007\b\u0014¢\u0006\u0002\u0010\u0003B\u000f\b\u0014\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0005H\u0016J\u0015\u0010\u0007\u001a\u00028\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0014J\r\u0010\u0015\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0016J-\u0010\u0017\u001a\u00028\u00002\u001d\u0010\u0018\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\u0002\b\u001bH\b¢\u0006\u0002\u0010\u001cJ\u0015\u0010\r\u001a\u00028\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u001dR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;", "T", "", "()V", "defaultOptions", "Lcom/facebook/fresco/vito/options/EncodedImageOptions;", "(Lcom/facebook/fresco/vito/options/EncodedImageOptions;)V", "cacheChoice", "Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;", "getCacheChoice$options_release", "()Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;", "setCacheChoice$options_release", "(Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;)V", "priority", "Lcom/facebook/imagepipeline/common/Priority;", "getPriority$options_release", "()Lcom/facebook/imagepipeline/common/Priority;", "setPriority$options_release", "(Lcom/facebook/imagepipeline/common/Priority;)V", "build", "(Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;)Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;", "getThis", "()Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;", "modify", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;", "(Lcom/facebook/imagepipeline/common/Priority;)Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: EncodedImageOptions.kt */
    public static class Builder<T extends Builder<T>> {
        private ImageRequest.CacheChoice cacheChoice;
        private Priority priority;

        public final Priority getPriority$options_release() {
            return this.priority;
        }

        public final void setPriority$options_release(Priority priority2) {
            this.priority = priority2;
        }

        public final ImageRequest.CacheChoice getCacheChoice$options_release() {
            return this.cacheChoice;
        }

        public final void setCacheChoice$options_release(ImageRequest.CacheChoice cacheChoice2) {
            this.cacheChoice = cacheChoice2;
        }

        protected Builder() {
        }

        protected Builder(EncodedImageOptions encodedImageOptions) {
            Intrinsics.checkNotNullParameter(encodedImageOptions, "defaultOptions");
            this.priority = encodedImageOptions.getPriority();
            this.cacheChoice = encodedImageOptions.getCacheChoice();
        }

        public EncodedImageOptions build() {
            return new EncodedImageOptions(this);
        }

        /* access modifiers changed from: protected */
        public final T getThis() {
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type T of com.facebook.fresco.vito.options.EncodedImageOptions.Builder");
            Builder builder = this;
            return this;
        }

        private final T modify(Function1<? super Builder<T>, Unit> function1) {
            function1.invoke(this);
            return getThis();
        }

        public final T priority(Priority priority2) {
            Builder builder = this;
            this.priority = priority2;
            return getThis();
        }

        public final T cacheChoice(ImageRequest.CacheChoice cacheChoice2) {
            Builder builder = this;
            this.cacheChoice = cacheChoice2;
            return getThis();
        }
    }
}
