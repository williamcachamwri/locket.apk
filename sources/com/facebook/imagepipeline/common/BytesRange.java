package com.facebook.imagepipeline.common;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0006\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0000H\u0002J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0010H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/imagepipeline/common/BytesRange;", "", "from", "", "to", "(II)V", "component1", "component2", "contains", "", "compare", "copy", "equals", "other", "hashCode", "toHttpRangeHeaderValue", "", "toString", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BytesRange.kt */
public final class BytesRange {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int TO_END_OF_CONTENT = Integer.MAX_VALUE;
    /* access modifiers changed from: private */
    public static final Lazy<Pattern> headerParsingRegEx$delegate = LazyKt.lazy(BytesRange$Companion$headerParsingRegEx$2.INSTANCE);
    public final int from;
    public final int to;

    public static /* synthetic */ BytesRange copy$default(BytesRange bytesRange, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bytesRange.from;
        }
        if ((i3 & 2) != 0) {
            i2 = bytesRange.to;
        }
        return bytesRange.copy(i, i2);
    }

    @JvmStatic
    public static final BytesRange from(int i) {
        return Companion.from(i);
    }

    @JvmStatic
    public static final BytesRange fromContentRangeHeader(String str) throws IllegalArgumentException {
        return Companion.fromContentRangeHeader(str);
    }

    @JvmStatic
    public static final BytesRange toMax(int i) {
        return Companion.toMax(i);
    }

    public final int component1() {
        return this.from;
    }

    public final int component2() {
        return this.to;
    }

    public final BytesRange copy(int i, int i2) {
        return new BytesRange(i, i2);
    }

    public BytesRange(int i, int i2) {
        this.from = i;
        this.to = i2;
    }

    public final String toHttpRangeHeaderValue() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Companion companion = Companion;
        String format = String.format((Locale) null, "bytes=%s-%s", Arrays.copyOf(new Object[]{companion.valueOrEmpty(this.from), companion.valueOrEmpty(this.to)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }

    public final boolean contains(BytesRange bytesRange) {
        return bytesRange != null && this.from <= bytesRange.from && bytesRange.to <= this.to;
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Companion companion = Companion;
        String format = String.format((Locale) null, "%s-%s", Arrays.copyOf(new Object[]{companion.valueOrEmpty(this.from), companion.valueOrEmpty(this.to)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.imagepipeline.common.BytesRange");
        BytesRange bytesRange = (BytesRange) obj;
        if (this.from == bytesRange.from && this.to == bytesRange.to) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.from * 31) + this.to;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0014\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0007J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/facebook/imagepipeline/common/BytesRange$Companion;", "", "()V", "TO_END_OF_CONTENT", "", "headerParsingRegEx", "Ljava/util/regex/Pattern;", "getHeaderParsingRegEx", "()Ljava/util/regex/Pattern;", "headerParsingRegEx$delegate", "Lkotlin/Lazy;", "from", "Lcom/facebook/imagepipeline/common/BytesRange;", "fromContentRangeHeader", "header", "", "toMax", "to", "valueOrEmpty", "n", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BytesRange.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Pattern getHeaderParsingRegEx() {
            Object value = BytesRange.headerParsingRegEx$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-headerParsingRegEx>(...)");
            return (Pattern) value;
        }

        /* access modifiers changed from: private */
        public final String valueOrEmpty(int i) {
            return i == Integer.MAX_VALUE ? "" : String.valueOf(i);
        }

        @JvmStatic
        public final BytesRange from(int i) {
            Preconditions.checkArgument(Boolean.valueOf(i >= 0));
            return new BytesRange(i, Integer.MAX_VALUE);
        }

        @JvmStatic
        public final BytesRange toMax(int i) {
            Preconditions.checkArgument(Boolean.valueOf(i > 0));
            return new BytesRange(0, i);
        }

        @JvmStatic
        public final BytesRange fromContentRangeHeader(String str) throws IllegalArgumentException {
            if (str == null) {
                return null;
            }
            try {
                String[] split = getHeaderParsingRegEx().split(str);
                boolean z = false;
                Preconditions.checkArgument(Boolean.valueOf(split.length == 4));
                Preconditions.checkArgument(Boolean.valueOf(Intrinsics.areEqual((Object) split[0], (Object) "bytes")));
                String str2 = split[1];
                Intrinsics.checkNotNullExpressionValue(str2, "headerParts[1]");
                int parseInt = Integer.parseInt(str2);
                String str3 = split[2];
                Intrinsics.checkNotNullExpressionValue(str3, "headerParts[2]");
                int parseInt2 = Integer.parseInt(str3);
                String str4 = split[3];
                Intrinsics.checkNotNullExpressionValue(str4, "headerParts[3]");
                int parseInt3 = Integer.parseInt(str4);
                Preconditions.checkArgument(Boolean.valueOf(parseInt2 > parseInt));
                if (parseInt3 > parseInt2) {
                    z = true;
                }
                Preconditions.checkArgument(Boolean.valueOf(z));
                if (parseInt2 < parseInt3 - 1) {
                    return new BytesRange(parseInt, parseInt2);
                }
                return new BytesRange(parseInt, Integer.MAX_VALUE);
            } catch (IllegalArgumentException e) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format((Locale) null, "Invalid Content-Range header value: \"%s\"", Arrays.copyOf(new Object[]{str}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                throw new IllegalArgumentException(format, e);
            }
        }
    }
}
