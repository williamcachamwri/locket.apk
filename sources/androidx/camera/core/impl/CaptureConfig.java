package androidx.camera.core.impl;

import android.util.Range;
import androidx.camera.core.impl.Config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class CaptureConfig {
    public static final String CAPTURE_CONFIG_ID_TAG_KEY = "CAPTURE_CONFIG_ID_KEY";
    public static final int DEFAULT_ID = -1;
    public static final Config.Option<Integer> OPTION_JPEG_QUALITY = Config.Option.create("camerax.core.captureConfig.jpegQuality", Integer.class);
    /* access modifiers changed from: private */
    public static final Config.Option<Range<Integer>> OPTION_RESOLVED_FRAME_RATE = Config.Option.create("camerax.core.captureConfig.resolvedFrameRate", Range.class);
    public static final Config.Option<Integer> OPTION_ROTATION = Config.Option.create("camerax.core.captureConfig.rotation", Integer.TYPE);
    public static final int TEMPLATE_TYPE_NONE = -1;
    final List<CameraCaptureCallback> mCameraCaptureCallbacks;
    private final CameraCaptureResult mCameraCaptureResult;
    final Config mImplementationOptions;
    final boolean mPostviewEnabled;
    final List<DeferrableSurface> mSurfaces;
    private final TagBundle mTagBundle;
    final int mTemplateType;
    private final boolean mUseRepeatingSurface;

    public interface OptionUnpacker {
        void unpack(UseCaseConfig<?> useCaseConfig, Builder builder);
    }

    CaptureConfig(List<DeferrableSurface> list, Config config, int i, boolean z, List<CameraCaptureCallback> list2, boolean z2, TagBundle tagBundle, CameraCaptureResult cameraCaptureResult) {
        this.mSurfaces = list;
        this.mImplementationOptions = config;
        this.mTemplateType = i;
        this.mCameraCaptureCallbacks = Collections.unmodifiableList(list2);
        this.mUseRepeatingSurface = z2;
        this.mTagBundle = tagBundle;
        this.mCameraCaptureResult = cameraCaptureResult;
        this.mPostviewEnabled = z;
    }

    public static CaptureConfig defaultEmptyCaptureConfig() {
        return new Builder().build();
    }

    public CameraCaptureResult getCameraCaptureResult() {
        return this.mCameraCaptureResult;
    }

    public List<DeferrableSurface> getSurfaces() {
        return Collections.unmodifiableList(this.mSurfaces);
    }

    public Config getImplementationOptions() {
        return this.mImplementationOptions;
    }

    public int getTemplateType() {
        return this.mTemplateType;
    }

    public int getId() {
        Object tag = this.mTagBundle.getTag(CAPTURE_CONFIG_ID_TAG_KEY);
        if (tag == null) {
            return -1;
        }
        return ((Integer) tag).intValue();
    }

    public Range<Integer> getExpectedFrameRateRange() {
        return (Range) Objects.requireNonNull((Range) this.mImplementationOptions.retrieveOption(OPTION_RESOLVED_FRAME_RATE, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED));
    }

    public int getPreviewStabilizationMode() {
        return ((Integer) Objects.requireNonNull((Integer) this.mImplementationOptions.retrieveOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, 0))).intValue();
    }

    public int getVideoStabilizationMode() {
        return ((Integer) Objects.requireNonNull((Integer) this.mImplementationOptions.retrieveOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, 0))).intValue();
    }

    public boolean isPostviewEnabled() {
        return this.mPostviewEnabled;
    }

    public boolean isUseRepeatingSurface() {
        return this.mUseRepeatingSurface;
    }

    public List<CameraCaptureCallback> getCameraCaptureCallbacks() {
        return this.mCameraCaptureCallbacks;
    }

    public TagBundle getTagBundle() {
        return this.mTagBundle;
    }

    public static final class Builder {
        private List<CameraCaptureCallback> mCameraCaptureCallbacks;
        private CameraCaptureResult mCameraCaptureResult;
        private MutableConfig mImplementationOptions;
        private MutableTagBundle mMutableTagBundle;
        private boolean mPostviewEnabled;
        private final Set<DeferrableSurface> mSurfaces;
        private int mTemplateType;
        private boolean mUseRepeatingSurface;

        public Builder() {
            this.mSurfaces = new HashSet();
            this.mImplementationOptions = MutableOptionsBundle.create();
            this.mTemplateType = -1;
            this.mPostviewEnabled = false;
            this.mCameraCaptureCallbacks = new ArrayList();
            this.mUseRepeatingSurface = false;
            this.mMutableTagBundle = MutableTagBundle.create();
        }

        private Builder(CaptureConfig captureConfig) {
            HashSet hashSet = new HashSet();
            this.mSurfaces = hashSet;
            this.mImplementationOptions = MutableOptionsBundle.create();
            this.mTemplateType = -1;
            this.mPostviewEnabled = false;
            this.mCameraCaptureCallbacks = new ArrayList();
            this.mUseRepeatingSurface = false;
            this.mMutableTagBundle = MutableTagBundle.create();
            hashSet.addAll(captureConfig.mSurfaces);
            this.mImplementationOptions = MutableOptionsBundle.from(captureConfig.mImplementationOptions);
            this.mTemplateType = captureConfig.mTemplateType;
            this.mCameraCaptureCallbacks.addAll(captureConfig.getCameraCaptureCallbacks());
            this.mUseRepeatingSurface = captureConfig.isUseRepeatingSurface();
            this.mMutableTagBundle = MutableTagBundle.from(captureConfig.getTagBundle());
            this.mPostviewEnabled = captureConfig.mPostviewEnabled;
        }

        public static Builder createFrom(UseCaseConfig<?> useCaseConfig) {
            OptionUnpacker captureOptionUnpacker = useCaseConfig.getCaptureOptionUnpacker((OptionUnpacker) null);
            if (captureOptionUnpacker != null) {
                Builder builder = new Builder();
                captureOptionUnpacker.unpack(useCaseConfig, builder);
                return builder;
            }
            throw new IllegalStateException("Implementation is missing option unpacker for " + useCaseConfig.getTargetName(useCaseConfig.toString()));
        }

        public static Builder from(CaptureConfig captureConfig) {
            return new Builder(captureConfig);
        }

        public void setCameraCaptureResult(CameraCaptureResult cameraCaptureResult) {
            this.mCameraCaptureResult = cameraCaptureResult;
        }

        public int getTemplateType() {
            return this.mTemplateType;
        }

        public Range<Integer> getExpectedFrameRateRange() {
            return (Range) this.mImplementationOptions.retrieveOption(CaptureConfig.OPTION_RESOLVED_FRAME_RATE, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED);
        }

        public void setTemplateType(int i) {
            this.mTemplateType = i;
        }

        public void setExpectedFrameRateRange(Range<Integer> range) {
            addImplementationOption(CaptureConfig.OPTION_RESOLVED_FRAME_RATE, range);
        }

        public void setPreviewStabilization(int i) {
            if (i != 0) {
                addImplementationOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, Integer.valueOf(i));
            }
        }

        public void setVideoStabilization(int i) {
            if (i != 0) {
                addImplementationOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, Integer.valueOf(i));
            }
        }

        public void setPostviewEnabled(boolean z) {
            this.mPostviewEnabled = z;
        }

        public void addCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            if (!this.mCameraCaptureCallbacks.contains(cameraCaptureCallback)) {
                this.mCameraCaptureCallbacks.add(cameraCaptureCallback);
            }
        }

        public void addAllCameraCaptureCallbacks(Collection<CameraCaptureCallback> collection) {
            for (CameraCaptureCallback addCameraCaptureCallback : collection) {
                addCameraCaptureCallback(addCameraCaptureCallback);
            }
        }

        public boolean removeCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            return this.mCameraCaptureCallbacks.remove(cameraCaptureCallback);
        }

        public void addSurface(DeferrableSurface deferrableSurface) {
            this.mSurfaces.add(deferrableSurface);
        }

        public void removeSurface(DeferrableSurface deferrableSurface) {
            this.mSurfaces.remove(deferrableSurface);
        }

        public void clearSurfaces() {
            this.mSurfaces.clear();
        }

        public Set<DeferrableSurface> getSurfaces() {
            return this.mSurfaces;
        }

        public void setImplementationOptions(Config config) {
            this.mImplementationOptions = MutableOptionsBundle.from(config);
        }

        public void addImplementationOptions(Config config) {
            for (Config.Option next : config.listOptions()) {
                Object retrieveOption = this.mImplementationOptions.retrieveOption(next, null);
                Object retrieveOption2 = config.retrieveOption(next);
                if (retrieveOption instanceof MultiValueSet) {
                    ((MultiValueSet) retrieveOption).addAll(((MultiValueSet) retrieveOption2).getAllItems());
                } else {
                    if (retrieveOption2 instanceof MultiValueSet) {
                        retrieveOption2 = ((MultiValueSet) retrieveOption2).clone();
                    }
                    this.mImplementationOptions.insertOption(next, config.getOptionPriority(next), retrieveOption2);
                }
            }
        }

        public <T> void addImplementationOption(Config.Option<T> option, T t) {
            this.mImplementationOptions.insertOption(option, t);
        }

        public Config getImplementationOptions() {
            return this.mImplementationOptions;
        }

        public boolean isUseRepeatingSurface() {
            return this.mUseRepeatingSurface;
        }

        public void setUseRepeatingSurface(boolean z) {
            this.mUseRepeatingSurface = z;
        }

        public Object getTag(String str) {
            return this.mMutableTagBundle.getTag(str);
        }

        public void addTag(String str, Object obj) {
            this.mMutableTagBundle.putTag(str, obj);
        }

        public void setId(int i) {
            this.mMutableTagBundle.putTag(CaptureConfig.CAPTURE_CONFIG_ID_TAG_KEY, Integer.valueOf(i));
        }

        public void addAllTags(TagBundle tagBundle) {
            this.mMutableTagBundle.addTagBundle(tagBundle);
        }

        public CaptureConfig build() {
            return new CaptureConfig(new ArrayList(this.mSurfaces), OptionsBundle.from(this.mImplementationOptions), this.mTemplateType, this.mPostviewEnabled, new ArrayList(this.mCameraCaptureCallbacks), this.mUseRepeatingSurface, TagBundle.from(this.mMutableTagBundle), this.mCameraCaptureResult);
        }
    }
}
