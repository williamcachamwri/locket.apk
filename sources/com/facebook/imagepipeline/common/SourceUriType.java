package com.facebook.imagepipeline.common;

import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/facebook/imagepipeline/common/SourceUriType;", "", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
/* compiled from: SourceUriType.kt */
public @interface SourceUriType {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int SOURCE_TYPE_DATA = 7;
    public static final int SOURCE_TYPE_LOCAL_ASSET = 5;
    public static final int SOURCE_TYPE_LOCAL_CONTENT = 4;
    public static final int SOURCE_TYPE_LOCAL_FILE = 1;
    public static final int SOURCE_TYPE_LOCAL_IMAGE_FILE = 3;
    public static final int SOURCE_TYPE_LOCAL_RESOURCE = 6;
    public static final int SOURCE_TYPE_LOCAL_VIDEO_FILE = 2;
    public static final int SOURCE_TYPE_NETWORK = 0;
    public static final int SOURCE_TYPE_QUALIFIED_RESOURCE = 8;
    public static final int SOURCE_TYPE_UNKNOWN = -1;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/imagepipeline/common/SourceUriType$Companion;", "", "()V", "SOURCE_TYPE_DATA", "", "SOURCE_TYPE_LOCAL_ASSET", "SOURCE_TYPE_LOCAL_CONTENT", "SOURCE_TYPE_LOCAL_FILE", "SOURCE_TYPE_LOCAL_IMAGE_FILE", "SOURCE_TYPE_LOCAL_RESOURCE", "SOURCE_TYPE_LOCAL_VIDEO_FILE", "SOURCE_TYPE_NETWORK", "SOURCE_TYPE_QUALIFIED_RESOURCE", "SOURCE_TYPE_UNKNOWN", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SourceUriType.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int SOURCE_TYPE_DATA = 7;
        public static final int SOURCE_TYPE_LOCAL_ASSET = 5;
        public static final int SOURCE_TYPE_LOCAL_CONTENT = 4;
        public static final int SOURCE_TYPE_LOCAL_FILE = 1;
        public static final int SOURCE_TYPE_LOCAL_IMAGE_FILE = 3;
        public static final int SOURCE_TYPE_LOCAL_RESOURCE = 6;
        public static final int SOURCE_TYPE_LOCAL_VIDEO_FILE = 2;
        public static final int SOURCE_TYPE_NETWORK = 0;
        public static final int SOURCE_TYPE_QUALIFIED_RESOURCE = 8;
        public static final int SOURCE_TYPE_UNKNOWN = -1;

        private Companion() {
        }
    }
}
