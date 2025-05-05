package com.mrousavy.camera.core.extensions;

import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"byId", "Landroidx/camera/core/CameraSelector$Builder;", "id", "", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSelector+byId.kt */
public final class CameraSelector_byIdKt {
    public static final CameraSelector.Builder byId(CameraSelector.Builder builder, String str) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(str, "id");
        CameraSelector.Builder addCameraFilter = builder.addCameraFilter(new CameraSelector_byIdKt$$ExternalSyntheticLambda0(str));
        Intrinsics.checkNotNullExpressionValue(addCameraFilter, "addCameraFilter(...)");
        return addCameraFilter;
    }

    /* access modifiers changed from: private */
    public static final List byId$lambda$1(String str, List list) {
        Intrinsics.checkNotNullParameter(str, "$id");
        Intrinsics.checkNotNullParameter(list, "cameraInfos");
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            CameraInfo cameraInfo = (CameraInfo) next;
            Intrinsics.checkNotNull(cameraInfo);
            if (Intrinsics.areEqual((Object) CameraInfo_idKt.getId(cameraInfo), (Object) str)) {
                arrayList.add(next);
            }
        }
        return CollectionsKt.toMutableList((List) arrayList);
    }
}
