package com.canhub.cropper;

import android.os.Parcel;
import android.os.Parcelable;
import com.canhub.cropper.CropImage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001d\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"com/canhub/cropper/CropImage$ActivityResult$Companion$CREATOR$1", "Landroid/os/Parcelable$Creator;", "Lcom/canhub/cropper/CropImage$ActivityResult;", "createFromParcel", "in", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/canhub/cropper/CropImage$ActivityResult;", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImage.kt */
public final class CropImage$ActivityResult$Companion$CREATOR$1 implements Parcelable.Creator<CropImage.ActivityResult> {
    CropImage$ActivityResult$Companion$CREATOR$1() {
    }

    public CropImage.ActivityResult createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "in");
        return new CropImage.ActivityResult(parcel);
    }

    public CropImage.ActivityResult[] newArray(int i) {
        return new CropImage.ActivityResult[i];
    }
}
