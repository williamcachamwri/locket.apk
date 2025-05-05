package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.SupportedSurfaceCombination;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.streamsharing.StreamSharingConfig;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class StreamUseCaseUtil {
    public static final Config.Option<Long> STREAM_USE_CASE_STREAM_SPEC_OPTION = Config.Option.create("camera2.streamSpec.streamUseCase", Long.TYPE);
    private static final Map<Long, Set<UseCaseConfigFactory.CaptureType>> STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP;
    private static final Map<Long, Set<UseCaseConfigFactory.CaptureType>> STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP;
    private static final String TAG = "StreamUseCaseUtil";

    static {
        HashMap hashMap = new HashMap();
        STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP = hashMap;
        HashMap hashMap2 = new HashMap();
        STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP = hashMap2;
        if (Build.VERSION.SDK_INT >= 33) {
            HashSet hashSet = new HashSet();
            hashSet.add(UseCaseConfigFactory.CaptureType.PREVIEW);
            hashSet.add(UseCaseConfigFactory.CaptureType.METERING_REPEATING);
            hashMap.put(4L, hashSet);
            HashSet hashSet2 = new HashSet();
            hashSet2.add(UseCaseConfigFactory.CaptureType.PREVIEW);
            hashSet2.add(UseCaseConfigFactory.CaptureType.METERING_REPEATING);
            hashSet2.add(UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS);
            hashMap.put(1L, hashSet2);
            HashSet hashSet3 = new HashSet();
            hashSet3.add(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE);
            hashMap.put(2L, hashSet3);
            HashSet hashSet4 = new HashSet();
            hashSet4.add(UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE);
            hashMap.put(3L, hashSet4);
            HashSet hashSet5 = new HashSet();
            hashSet5.add(UseCaseConfigFactory.CaptureType.PREVIEW);
            hashSet5.add(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE);
            hashSet5.add(UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE);
            hashMap2.put(4L, hashSet5);
            HashSet hashSet6 = new HashSet();
            hashSet6.add(UseCaseConfigFactory.CaptureType.PREVIEW);
            hashSet6.add(UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE);
            hashMap2.put(3L, hashSet6);
        }
    }

    private StreamUseCaseUtil() {
    }

    public static void populateSurfaceToStreamUseCaseMapping(Collection<SessionConfig> collection, Collection<UseCaseConfig<?>> collection2, Map<DeferrableSurface, Long> map) {
        boolean z;
        ArrayList arrayList = new ArrayList(collection2);
        Iterator<SessionConfig> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            SessionConfig next = it.next();
            Config implementationOptions = next.getImplementationOptions();
            Config.Option<Long> option = STREAM_USE_CASE_STREAM_SPEC_OPTION;
            if (!implementationOptions.containsOption(option) || next.getSurfaces().size() == 1) {
                if (next.getImplementationOptions().containsOption(option)) {
                    z = true;
                    break;
                }
            } else {
                Logger.e(TAG, String.format("SessionConfig has stream use case but also contains %d surfaces, abort populateSurfaceToStreamUseCaseMapping().", new Object[]{Integer.valueOf(next.getSurfaces().size())}));
                return;
            }
        }
        if (z) {
            int i = 0;
            for (SessionConfig next2 : collection) {
                if (((UseCaseConfig) arrayList.get(i)).getCaptureType() == UseCaseConfigFactory.CaptureType.METERING_REPEATING) {
                    Preconditions.checkState(!next2.getSurfaces().isEmpty(), "MeteringRepeating should contain a surface");
                    map.put(next2.getSurfaces().get(0), 1L);
                } else {
                    Config implementationOptions2 = next2.getImplementationOptions();
                    Config.Option<Long> option2 = STREAM_USE_CASE_STREAM_SPEC_OPTION;
                    if (implementationOptions2.containsOption(option2) && !next2.getSurfaces().isEmpty()) {
                        map.put(next2.getSurfaces().get(0), (Long) next2.getImplementationOptions().retrieveOption(option2));
                    }
                }
                i++;
            }
        }
    }

    public static Camera2ImplConfig getStreamSpecImplementationOptions(UseCaseConfig<?> useCaseConfig) {
        MutableOptionsBundle create = MutableOptionsBundle.create();
        if (useCaseConfig.containsOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)) {
            create.insertOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION, (Long) useCaseConfig.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION));
        }
        if (useCaseConfig.containsOption(UseCaseConfig.OPTION_ZSL_DISABLED)) {
            create.insertOption(UseCaseConfig.OPTION_ZSL_DISABLED, (Boolean) useCaseConfig.retrieveOption(UseCaseConfig.OPTION_ZSL_DISABLED));
        }
        if (useCaseConfig.containsOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE)) {
            create.insertOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE, (Integer) useCaseConfig.retrieveOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE));
        }
        if (useCaseConfig.containsOption(UseCaseConfig.OPTION_INPUT_FORMAT)) {
            create.insertOption(UseCaseConfig.OPTION_INPUT_FORMAT, (Integer) useCaseConfig.retrieveOption(UseCaseConfig.OPTION_INPUT_FORMAT));
        }
        return new Camera2ImplConfig(create);
    }

    public static boolean isStreamUseCaseSupported(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        long[] jArr;
        if (Build.VERSION.SDK_INT < 33 || (jArr = (long[]) cameraCharacteristicsCompat.get(CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES)) == null || jArr.length == 0) {
            return false;
        }
        return true;
    }

    public static boolean shouldUseStreamUseCase(SupportedSurfaceCombination.FeatureSettings featureSettings) {
        return featureSettings.getCameraMode() == 0 && featureSettings.getRequiredMaxBitDepth() == 8;
    }

    public static boolean populateStreamUseCaseStreamSpecOptionWithInteropOverride(CameraCharacteristicsCompat cameraCharacteristicsCompat, List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, StreamSpec> map, Map<AttachedSurfaceInfo, StreamSpec> map2) {
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        ArrayList<UseCaseConfig> arrayList = new ArrayList<>(map.keySet());
        for (AttachedSurfaceInfo implementationOptions : list) {
            Preconditions.checkNotNull(implementationOptions.getImplementationOptions());
        }
        for (UseCaseConfig useCaseConfig : arrayList) {
            Preconditions.checkNotNull(((StreamSpec) Preconditions.checkNotNull(map.get(useCaseConfig))).getImplementationOptions());
        }
        long[] jArr = (long[]) cameraCharacteristicsCompat.get(CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES);
        if (!(jArr == null || jArr.length == 0)) {
            HashSet hashSet = new HashSet();
            for (long valueOf : jArr) {
                hashSet.add(Long.valueOf(valueOf));
            }
            if (isValidCamera2InteropOverride(list, arrayList, hashSet)) {
                for (AttachedSurfaceInfo next : list) {
                    Config implementationOptions2 = next.getImplementationOptions();
                    Config updatedImplementationOptionsWithUseCaseStreamSpecOption = getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(implementationOptions2, ((Long) implementationOptions2.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)).longValue());
                    if (updatedImplementationOptionsWithUseCaseStreamSpecOption != null) {
                        map2.put(next, next.toStreamSpec(updatedImplementationOptionsWithUseCaseStreamSpecOption));
                    }
                }
                for (UseCaseConfig useCaseConfig2 : arrayList) {
                    StreamSpec streamSpec = map.get(useCaseConfig2);
                    Config implementationOptions3 = streamSpec.getImplementationOptions();
                    Config updatedImplementationOptionsWithUseCaseStreamSpecOption2 = getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(implementationOptions3, ((Long) implementationOptions3.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)).longValue());
                    if (updatedImplementationOptionsWithUseCaseStreamSpecOption2 != null) {
                        map.put(useCaseConfig2, streamSpec.toBuilder().setImplementationOptions(updatedImplementationOptionsWithUseCaseStreamSpecOption2).build());
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean areStreamUseCasesAvailableForSurfaceConfigs(CameraCharacteristicsCompat cameraCharacteristicsCompat, List<SurfaceConfig> list) {
        long[] jArr;
        if (Build.VERSION.SDK_INT < 33 || (jArr = (long[]) cameraCharacteristicsCompat.get(CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES)) == null || jArr.length == 0) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (long valueOf : jArr) {
            hashSet.add(Long.valueOf(valueOf));
        }
        for (SurfaceConfig streamUseCase : list) {
            if (!hashSet.contains(Long.valueOf(streamUseCase.getStreamUseCase()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEligibleCaptureType(UseCaseConfigFactory.CaptureType captureType, long j, List<UseCaseConfigFactory.CaptureType> list) {
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        if (captureType == UseCaseConfigFactory.CaptureType.STREAM_SHARING) {
            Map<Long, Set<UseCaseConfigFactory.CaptureType>> map = STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP;
            if (!map.containsKey(Long.valueOf(j))) {
                return false;
            }
            Set set = map.get(Long.valueOf(j));
            if (list.size() != set.size()) {
                return false;
            }
            for (UseCaseConfigFactory.CaptureType contains : list) {
                if (!set.contains(contains)) {
                    return false;
                }
            }
            return true;
        }
        Map<Long, Set<UseCaseConfigFactory.CaptureType>> map2 = STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP;
        if (!map2.containsKey(Long.valueOf(j)) || !map2.get(Long.valueOf(j)).contains(captureType)) {
            return false;
        }
        return true;
    }

    public static boolean areCaptureTypesEligible(Map<Integer, AttachedSurfaceInfo> map, Map<Integer, UseCaseConfig<?>> map2, List<SurfaceConfig> list) {
        List<UseCaseConfigFactory.CaptureType> list2;
        UseCaseConfigFactory.CaptureType captureType;
        for (int i = 0; i < list.size(); i++) {
            long streamUseCase = list.get(i).getStreamUseCase();
            if (map.containsKey(Integer.valueOf(i))) {
                AttachedSurfaceInfo attachedSurfaceInfo = map.get(Integer.valueOf(i));
                if (attachedSurfaceInfo.getCaptureTypes().size() == 1) {
                    captureType = attachedSurfaceInfo.getCaptureTypes().get(0);
                } else {
                    captureType = UseCaseConfigFactory.CaptureType.STREAM_SHARING;
                }
                if (!isEligibleCaptureType(captureType, streamUseCase, attachedSurfaceInfo.getCaptureTypes())) {
                    return false;
                }
            } else if (map2.containsKey(Integer.valueOf(i))) {
                UseCaseConfig useCaseConfig = map2.get(Integer.valueOf(i));
                UseCaseConfigFactory.CaptureType captureType2 = useCaseConfig.getCaptureType();
                if (useCaseConfig.getCaptureType() == UseCaseConfigFactory.CaptureType.STREAM_SHARING) {
                    list2 = ((StreamSharingConfig) useCaseConfig).getCaptureTypes();
                } else {
                    list2 = Collections.emptyList();
                }
                if (!isEligibleCaptureType(captureType2, streamUseCase, list2)) {
                    return false;
                }
            } else {
                throw new AssertionError("SurfaceConfig does not map to any use case");
            }
        }
        return true;
    }

    public static void populateStreamUseCaseStreamSpecOptionWithSupportedSurfaceConfigs(Map<UseCaseConfig<?>, StreamSpec> map, Map<AttachedSurfaceInfo, StreamSpec> map2, Map<Integer, AttachedSurfaceInfo> map3, Map<Integer, UseCaseConfig<?>> map4, List<SurfaceConfig> list) {
        for (int i = 0; i < list.size(); i++) {
            long streamUseCase = list.get(i).getStreamUseCase();
            if (map3.containsKey(Integer.valueOf(i))) {
                AttachedSurfaceInfo attachedSurfaceInfo = map3.get(Integer.valueOf(i));
                Config updatedImplementationOptionsWithUseCaseStreamSpecOption = getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(attachedSurfaceInfo.getImplementationOptions(), streamUseCase);
                if (updatedImplementationOptionsWithUseCaseStreamSpecOption != null) {
                    map2.put(attachedSurfaceInfo, attachedSurfaceInfo.toStreamSpec(updatedImplementationOptionsWithUseCaseStreamSpecOption));
                }
            } else if (map4.containsKey(Integer.valueOf(i))) {
                UseCaseConfig useCaseConfig = map4.get(Integer.valueOf(i));
                StreamSpec streamSpec = map.get(useCaseConfig);
                Config updatedImplementationOptionsWithUseCaseStreamSpecOption2 = getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(streamSpec.getImplementationOptions(), streamUseCase);
                if (updatedImplementationOptionsWithUseCaseStreamSpecOption2 != null) {
                    map.put(useCaseConfig, streamSpec.toBuilder().setImplementationOptions(updatedImplementationOptionsWithUseCaseStreamSpecOption2).build());
                }
            } else {
                throw new AssertionError("SurfaceConfig does not map to any use case");
            }
        }
    }

    private static Config getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(Config config, long j) {
        Config.Option<Long> option = STREAM_USE_CASE_STREAM_SPEC_OPTION;
        if (config.containsOption(option) && ((Long) config.retrieveOption(option)).longValue() == j) {
            return null;
        }
        MutableOptionsBundle from = MutableOptionsBundle.from(config);
        from.insertOption(option, Long.valueOf(j));
        return new Camera2ImplConfig(from);
    }

    public static boolean containsZslUseCase(List<AttachedSurfaceInfo> list, List<UseCaseConfig<?>> list2) {
        for (AttachedSurfaceInfo next : list) {
            if (isZslUseCase(next.getImplementationOptions(), next.getCaptureTypes().get(0))) {
                return true;
            }
        }
        for (UseCaseConfig next2 : list2) {
            if (isZslUseCase(next2, next2.getCaptureType())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isZslUseCase(Config config, UseCaseConfigFactory.CaptureType captureType) {
        if (!((Boolean) config.retrieveOption(UseCaseConfig.OPTION_ZSL_DISABLED, false)).booleanValue() && config.containsOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE) && TemplateTypeUtil.getSessionConfigTemplateType(captureType, ((Integer) config.retrieveOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE)).intValue()) == 5) {
            return true;
        }
        return false;
    }

    private static boolean areStreamUseCasesAvailable(Set<Long> set, Set<Long> set2) {
        for (Long contains : set2) {
            if (!set.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    private static void throwInvalidCamera2InteropOverrideException() {
        throw new IllegalArgumentException("Either all use cases must have non-default stream use case assigned or none should have it");
    }

    private static boolean isValidCamera2InteropOverride(List<AttachedSurfaceInfo> list, List<UseCaseConfig<?>> list2, Set<Long> set) {
        boolean z;
        boolean z2;
        HashSet hashSet = new HashSet();
        Iterator<AttachedSurfaceInfo> it = list.iterator();
        if (it.hasNext()) {
            AttachedSurfaceInfo next = it.next();
            if (next.getImplementationOptions().containsOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION) && ((Long) next.getImplementationOptions().retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)).longValue() != 0) {
                z = true;
                z2 = false;
            } else {
                z2 = true;
                z = false;
            }
        } else {
            z = false;
            z2 = false;
        }
        for (UseCaseConfig next2 : list2) {
            if (next2.containsOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)) {
                long longValue = ((Long) next2.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)).longValue();
                if (longValue != 0) {
                    if (z2) {
                        throwInvalidCamera2InteropOverrideException();
                    }
                    hashSet.add(Long.valueOf(longValue));
                    z = true;
                } else if (z) {
                    throwInvalidCamera2InteropOverrideException();
                }
            } else if (z) {
                throwInvalidCamera2InteropOverrideException();
            }
            z2 = true;
        }
        if (z2 || !areStreamUseCasesAvailable(set, hashSet)) {
            return false;
        }
        return true;
    }
}
