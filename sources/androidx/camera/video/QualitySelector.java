package androidx.camera.video;

import android.util.Size;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.video.FallbackStrategy;
import androidx.camera.video.internal.VideoValidatedEncoderProfilesProxy;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class QualitySelector {
    private static final String TAG = "QualitySelector";
    private final FallbackStrategy mFallbackStrategy;
    private final List<Quality> mPreferredQualityList;

    @Deprecated
    public static List<Quality> getSupportedQualities(CameraInfo cameraInfo) {
        return Recorder.getVideoCapabilities(cameraInfo).getSupportedQualities(DynamicRange.SDR);
    }

    @Deprecated
    public static boolean isQualitySupported(CameraInfo cameraInfo, Quality quality) {
        return Recorder.getVideoCapabilities(cameraInfo).isQualitySupported(quality, DynamicRange.SDR);
    }

    public static Size getResolution(CameraInfo cameraInfo, Quality quality) {
        checkQualityConstantsOrThrow(quality);
        VideoValidatedEncoderProfilesProxy profiles = Recorder.getVideoCapabilities(cameraInfo).getProfiles(quality, DynamicRange.SDR);
        if (profiles != null) {
            return getProfileVideoSize(profiles);
        }
        return null;
    }

    public static Map<Quality, Size> getQualityToResolutionMap(VideoCapabilities videoCapabilities, DynamicRange dynamicRange) {
        HashMap hashMap = new HashMap();
        for (Quality next : videoCapabilities.getSupportedQualities(dynamicRange)) {
            hashMap.put(next, getProfileVideoSize((VideoValidatedEncoderProfilesProxy) Objects.requireNonNull(videoCapabilities.getProfiles(next, dynamicRange))));
        }
        return hashMap;
    }

    QualitySelector(List<Quality> list, FallbackStrategy fallbackStrategy) {
        Preconditions.checkArgument(!list.isEmpty() || fallbackStrategy != FallbackStrategy.NONE, "No preferred quality and fallback strategy.");
        this.mPreferredQualityList = Collections.unmodifiableList(new ArrayList(list));
        this.mFallbackStrategy = fallbackStrategy;
    }

    public static QualitySelector from(Quality quality) {
        return from(quality, FallbackStrategy.NONE);
    }

    public static QualitySelector from(Quality quality, FallbackStrategy fallbackStrategy) {
        Preconditions.checkNotNull(quality, "quality cannot be null");
        Preconditions.checkNotNull(fallbackStrategy, "fallbackStrategy cannot be null");
        checkQualityConstantsOrThrow(quality);
        return new QualitySelector(Collections.singletonList(quality), fallbackStrategy);
    }

    public static QualitySelector fromOrderedList(List<Quality> list) {
        return fromOrderedList(list, FallbackStrategy.NONE);
    }

    public static QualitySelector fromOrderedList(List<Quality> list, FallbackStrategy fallbackStrategy) {
        Preconditions.checkNotNull(list, "qualities cannot be null");
        Preconditions.checkNotNull(fallbackStrategy, "fallbackStrategy cannot be null");
        Preconditions.checkArgument(!list.isEmpty(), "qualities cannot be empty");
        checkQualityConstantsOrThrow(list);
        return new QualitySelector(list, fallbackStrategy);
    }

    public List<Quality> getPrioritizedQualities(List<Quality> list) {
        if (list.isEmpty()) {
            Logger.w(TAG, "No supported quality on the device.");
            return new ArrayList();
        }
        Logger.d(TAG, "supportedQualities = " + list);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Quality> it = this.mPreferredQualityList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Quality next = it.next();
            if (next == Quality.HIGHEST) {
                linkedHashSet.addAll(list);
                break;
            } else if (next == Quality.LOWEST) {
                ArrayList arrayList = new ArrayList(list);
                Collections.reverse(arrayList);
                linkedHashSet.addAll(arrayList);
                break;
            } else if (list.contains(next)) {
                linkedHashSet.add(next);
            } else {
                Logger.w(TAG, "quality is not supported and will be ignored: " + next);
            }
        }
        addByFallbackStrategy(list, linkedHashSet);
        return new ArrayList(linkedHashSet);
    }

    public String toString() {
        return "QualitySelector{preferredQualities=" + this.mPreferredQualityList + ", fallbackStrategy=" + this.mFallbackStrategy + "}";
    }

    private void addByFallbackStrategy(List<Quality> list, Set<Quality> set) {
        Quality quality;
        if (!list.isEmpty() && !set.containsAll(list)) {
            Logger.d(TAG, "Select quality by fallbackStrategy = " + this.mFallbackStrategy);
            if (this.mFallbackStrategy != FallbackStrategy.NONE) {
                Preconditions.checkState(this.mFallbackStrategy instanceof FallbackStrategy.RuleStrategy, "Currently only support type RuleStrategy");
                FallbackStrategy.RuleStrategy ruleStrategy = (FallbackStrategy.RuleStrategy) this.mFallbackStrategy;
                List<Quality> sortedQualities = Quality.getSortedQualities();
                boolean z = false;
                if (ruleStrategy.getFallbackQuality() == Quality.HIGHEST) {
                    quality = sortedQualities.get(0);
                } else if (ruleStrategy.getFallbackQuality() == Quality.LOWEST) {
                    quality = sortedQualities.get(sortedQualities.size() - 1);
                } else {
                    quality = ruleStrategy.getFallbackQuality();
                }
                int indexOf = sortedQualities.indexOf(quality);
                if (indexOf != -1) {
                    z = true;
                }
                Preconditions.checkState(z);
                ArrayList arrayList = new ArrayList();
                for (int i = indexOf - 1; i >= 0; i--) {
                    Quality quality2 = sortedQualities.get(i);
                    if (list.contains(quality2)) {
                        arrayList.add(quality2);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = indexOf + 1; i2 < sortedQualities.size(); i2++) {
                    Quality quality3 = sortedQualities.get(i2);
                    if (list.contains(quality3)) {
                        arrayList2.add(quality3);
                    }
                }
                Logger.d(TAG, "sizeSortedQualities = " + sortedQualities + ", fallback quality = " + quality + ", largerQualities = " + arrayList + ", smallerQualities = " + arrayList2);
                int fallbackRule = ruleStrategy.getFallbackRule();
                if (fallbackRule == 0) {
                    return;
                }
                if (fallbackRule == 1) {
                    set.addAll(arrayList);
                    set.addAll(arrayList2);
                } else if (fallbackRule == 2) {
                    set.addAll(arrayList);
                } else if (fallbackRule == 3) {
                    set.addAll(arrayList2);
                    set.addAll(arrayList);
                } else if (fallbackRule == 4) {
                    set.addAll(arrayList2);
                } else {
                    throw new AssertionError("Unhandled fallback strategy: " + this.mFallbackStrategy);
                }
            }
        }
    }

    private static Size getProfileVideoSize(VideoValidatedEncoderProfilesProxy videoValidatedEncoderProfilesProxy) {
        EncoderProfilesProxy.VideoProfileProxy defaultVideoProfile = videoValidatedEncoderProfilesProxy.getDefaultVideoProfile();
        return new Size(defaultVideoProfile.getWidth(), defaultVideoProfile.getHeight());
    }

    private static void checkQualityConstantsOrThrow(List<Quality> list) {
        for (Quality next : list) {
            Preconditions.checkArgument(Quality.containsQuality(next), "qualities contain invalid quality: " + next);
        }
    }

    private static void checkQualityConstantsOrThrow(Quality quality) {
        Preconditions.checkArgument(Quality.containsQuality(quality), "Invalid quality: " + quality);
    }
}
