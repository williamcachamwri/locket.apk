package com.canhub.cropper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.canhub.cropper.CropImageView;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 Y2\u00020\u0001:\u0001YB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0014\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010S\u001a\u00020\u0007H\u0016J\u0006\u0010T\u001a\u00020UJ\u0018\u0010V\u001a\u00020U2\u0006\u0010W\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u0007H\u0016R\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001a\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001e\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010 \u001a\u00020!8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\u0004\u0018\u00010#8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010$\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010%\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u0004\u0018\u00010'8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010(\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010)\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010*\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010+\u001a\u00020,8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010-\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010.\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010/\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u00100\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u00101\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\u0004\u0018\u0001038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u00104\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\n\u0012\u0004\u0012\u00020#\u0018\u0001068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\u0004\u0018\u00010#8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u00108\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u00109\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010:\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010;\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010<\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010=\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010>\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010?\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010@\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010A\u001a\u00020B8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010C\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010D\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010E\u001a\u00020F8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010G\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010H\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010I\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010J\u001a\u00020K8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010L\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010M\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010N\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010O\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010P\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010Q\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010R\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lcom/canhub/cropper/CropImageOptions;", "Landroid/os/Parcelable;", "()V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "activityMenuIconColor", "", "activityTitle", "", "allowCounterRotation", "", "allowFlipping", "allowRotation", "aspectRatioX", "aspectRatioY", "autoZoomEnabled", "backgroundColor", "borderCornerColor", "borderCornerLength", "", "borderCornerOffset", "borderCornerThickness", "borderLineColor", "borderLineThickness", "centerMoveEnabled", "circleCornerFillColorHexValue", "cornerShape", "Lcom/canhub/cropper/CropImageView$CropCornerShape;", "cropCornerRadius", "cropMenuCropButtonIcon", "cropMenuCropButtonTitle", "cropShape", "Lcom/canhub/cropper/CropImageView$CropShape;", "cropperLabelText", "", "cropperLabelTextColor", "cropperLabelTextSize", "customOutputUri", "Landroid/net/Uri;", "fixAspectRatio", "flipHorizontally", "flipVertically", "guidelines", "Lcom/canhub/cropper/CropImageView$Guidelines;", "guidelinesColor", "guidelinesThickness", "imageSourceIncludeCamera", "imageSourceIncludeGallery", "initialCropWindowPaddingRatio", "initialCropWindowRectangle", "Landroid/graphics/Rect;", "initialRotation", "intentChooserPriorityList", "", "intentChooserTitle", "maxCropResultHeight", "maxCropResultWidth", "maxZoom", "minCropResultHeight", "minCropResultWidth", "minCropWindowHeight", "minCropWindowWidth", "multiTouchEnabled", "noOutputImage", "outputCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "outputCompressQuality", "outputRequestHeight", "outputRequestSizeOptions", "Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "outputRequestWidth", "progressBarColor", "rotationDegrees", "scaleType", "Lcom/canhub/cropper/CropImageView$ScaleType;", "showCropLabel", "showCropOverlay", "showIntentChooser", "showProgressBar", "skipEditing", "snapRadius", "touchRadius", "describeContents", "validate", "", "writeToParcel", "dest", "flags", "Companion", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageOptions.kt */
public class CropImageOptions implements Parcelable {
    public static final Parcelable.Creator<CropImageOptions> CREATOR = new CropImageOptions$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DEGREES_360 = 360;
    public int activityMenuIconColor;
    public CharSequence activityTitle;
    public boolean allowCounterRotation;
    public boolean allowFlipping;
    public boolean allowRotation;
    public int aspectRatioX;
    public int aspectRatioY;
    public boolean autoZoomEnabled;
    public int backgroundColor;
    public int borderCornerColor;
    public float borderCornerLength;
    public float borderCornerOffset;
    public float borderCornerThickness;
    public int borderLineColor;
    public float borderLineThickness;
    public boolean centerMoveEnabled;
    public int circleCornerFillColorHexValue;
    public CropImageView.CropCornerShape cornerShape;
    public float cropCornerRadius;
    public int cropMenuCropButtonIcon;
    public CharSequence cropMenuCropButtonTitle;
    public CropImageView.CropShape cropShape;
    public String cropperLabelText = "";
    public int cropperLabelTextColor;
    public float cropperLabelTextSize;
    public Uri customOutputUri;
    public boolean fixAspectRatio;
    public boolean flipHorizontally;
    public boolean flipVertically;
    public CropImageView.Guidelines guidelines;
    public int guidelinesColor;
    public float guidelinesThickness;
    public boolean imageSourceIncludeCamera;
    public boolean imageSourceIncludeGallery;
    public float initialCropWindowPaddingRatio;
    public Rect initialCropWindowRectangle;
    public int initialRotation;
    public List<String> intentChooserPriorityList;
    public String intentChooserTitle;
    public int maxCropResultHeight;
    public int maxCropResultWidth;
    public int maxZoom;
    public int minCropResultHeight;
    public int minCropResultWidth;
    public int minCropWindowHeight;
    public int minCropWindowWidth;
    public boolean multiTouchEnabled;
    public boolean noOutputImage;
    public Bitmap.CompressFormat outputCompressFormat;
    public int outputCompressQuality;
    public int outputRequestHeight;
    public CropImageView.RequestSizeOptions outputRequestSizeOptions;
    public int outputRequestWidth;
    public int progressBarColor;
    public int rotationDegrees;
    public CropImageView.ScaleType scaleType;
    public boolean showCropLabel;
    public boolean showCropOverlay;
    public boolean showIntentChooser;
    public boolean showProgressBar;
    public boolean skipEditing;
    public float snapRadius;
    public float touchRadius;

    public int describeContents() {
        return 0;
    }

    public CropImageOptions() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        this.imageSourceIncludeCamera = true;
        this.imageSourceIncludeGallery = true;
        this.cropShape = CropImageView.CropShape.RECTANGLE;
        this.cornerShape = CropImageView.CropCornerShape.RECTANGLE;
        this.circleCornerFillColorHexValue = -1;
        this.cropCornerRadius = TypedValue.applyDimension(1, 10.0f, displayMetrics);
        this.snapRadius = TypedValue.applyDimension(1, 3.0f, displayMetrics);
        this.touchRadius = TypedValue.applyDimension(1, 24.0f, displayMetrics);
        this.guidelines = CropImageView.Guidelines.ON_TOUCH;
        this.scaleType = CropImageView.ScaleType.FIT_CENTER;
        this.showCropOverlay = true;
        this.showProgressBar = true;
        this.progressBarColor = CropImageOptionsKt.COLOR_PURPLE;
        this.autoZoomEnabled = true;
        this.multiTouchEnabled = false;
        this.centerMoveEnabled = true;
        this.maxZoom = 4;
        this.initialCropWindowPaddingRatio = 0.1f;
        this.fixAspectRatio = false;
        this.aspectRatioX = 1;
        this.aspectRatioY = 1;
        this.borderLineThickness = TypedValue.applyDimension(1, 3.0f, displayMetrics);
        this.borderLineColor = Color.argb(170, 255, 255, 255);
        this.borderCornerThickness = TypedValue.applyDimension(1, 2.0f, displayMetrics);
        this.borderCornerOffset = TypedValue.applyDimension(1, 5.0f, displayMetrics);
        this.borderCornerLength = TypedValue.applyDimension(1, 14.0f, displayMetrics);
        this.borderCornerColor = -1;
        this.guidelinesThickness = TypedValue.applyDimension(1, 1.0f, displayMetrics);
        this.guidelinesColor = Color.argb(170, 255, 255, 255);
        this.backgroundColor = Color.argb(119, 0, 0, 0);
        this.minCropWindowWidth = (int) TypedValue.applyDimension(1, 42.0f, displayMetrics);
        this.minCropWindowHeight = (int) TypedValue.applyDimension(1, 42.0f, displayMetrics);
        this.minCropResultWidth = 40;
        this.minCropResultHeight = 40;
        this.maxCropResultWidth = 99999;
        this.maxCropResultHeight = 99999;
        this.activityTitle = "";
        this.activityMenuIconColor = 0;
        this.customOutputUri = null;
        this.outputCompressFormat = Bitmap.CompressFormat.JPEG;
        this.outputCompressQuality = 90;
        this.outputRequestWidth = 0;
        this.outputRequestHeight = 0;
        this.outputRequestSizeOptions = CropImageView.RequestSizeOptions.NONE;
        this.noOutputImage = false;
        this.initialCropWindowRectangle = null;
        this.initialRotation = -1;
        this.allowRotation = true;
        this.allowFlipping = true;
        this.allowCounterRotation = false;
        this.rotationDegrees = 90;
        this.flipHorizontally = false;
        this.flipVertically = false;
        this.cropMenuCropButtonTitle = null;
        this.cropMenuCropButtonIcon = 0;
        this.skipEditing = false;
        this.showIntentChooser = false;
        this.intentChooserTitle = null;
        this.intentChooserPriorityList = CollectionsKt.emptyList();
        this.cropperLabelTextSize = TypedValue.applyDimension(2, 20.0f, displayMetrics);
        this.cropperLabelTextColor = -1;
        this.showCropLabel = false;
    }

    protected CropImageOptions(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        boolean z = true;
        this.imageSourceIncludeCamera = parcel.readByte() != 0;
        this.imageSourceIncludeGallery = parcel.readByte() != 0;
        this.cropShape = CropImageView.CropShape.values()[parcel.readInt()];
        this.cornerShape = CropImageView.CropCornerShape.values()[parcel.readInt()];
        this.cropCornerRadius = parcel.readFloat();
        this.snapRadius = parcel.readFloat();
        this.touchRadius = parcel.readFloat();
        this.guidelines = CropImageView.Guidelines.values()[parcel.readInt()];
        this.scaleType = CropImageView.ScaleType.values()[parcel.readInt()];
        this.showCropOverlay = parcel.readByte() != 0;
        this.showProgressBar = parcel.readByte() != 0;
        this.progressBarColor = parcel.readInt();
        this.autoZoomEnabled = parcel.readByte() != 0;
        this.multiTouchEnabled = parcel.readByte() != 0;
        this.centerMoveEnabled = parcel.readByte() != 0;
        this.maxZoom = parcel.readInt();
        this.initialCropWindowPaddingRatio = parcel.readFloat();
        this.fixAspectRatio = parcel.readByte() != 0;
        this.aspectRatioX = parcel.readInt();
        this.aspectRatioY = parcel.readInt();
        this.borderLineThickness = parcel.readFloat();
        this.borderLineColor = parcel.readInt();
        this.borderCornerThickness = parcel.readFloat();
        this.borderCornerOffset = parcel.readFloat();
        this.borderCornerLength = parcel.readFloat();
        this.borderCornerColor = parcel.readInt();
        this.circleCornerFillColorHexValue = parcel.readInt();
        this.guidelinesThickness = parcel.readFloat();
        this.guidelinesColor = parcel.readInt();
        this.backgroundColor = parcel.readInt();
        this.minCropWindowWidth = parcel.readInt();
        this.minCropWindowHeight = parcel.readInt();
        this.minCropResultWidth = parcel.readInt();
        this.minCropResultHeight = parcel.readInt();
        this.maxCropResultWidth = parcel.readInt();
        this.maxCropResultHeight = parcel.readInt();
        Object createFromParcel = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        Intrinsics.checkNotNullExpressionValue(createFromParcel, "CHAR_SEQUENCE_CREATOR.createFromParcel(parcel)");
        this.activityTitle = (CharSequence) createFromParcel;
        this.activityMenuIconColor = parcel.readInt();
        this.customOutputUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        String readString = parcel.readString();
        Intrinsics.checkNotNull(readString);
        this.outputCompressFormat = Bitmap.CompressFormat.valueOf(readString);
        this.outputCompressQuality = parcel.readInt();
        this.outputRequestWidth = parcel.readInt();
        this.outputRequestHeight = parcel.readInt();
        this.outputRequestSizeOptions = CropImageView.RequestSizeOptions.values()[parcel.readInt()];
        this.noOutputImage = parcel.readByte() != 0;
        this.initialCropWindowRectangle = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
        this.initialRotation = parcel.readInt();
        this.allowRotation = parcel.readByte() != 0;
        this.allowFlipping = parcel.readByte() != 0;
        this.allowCounterRotation = parcel.readByte() != 0;
        this.rotationDegrees = parcel.readInt();
        this.flipHorizontally = parcel.readByte() != 0;
        this.flipVertically = parcel.readByte() != 0;
        this.cropMenuCropButtonTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.cropMenuCropButtonIcon = parcel.readInt();
        this.skipEditing = parcel.readByte() != 0;
        this.showIntentChooser = parcel.readByte() != 0;
        this.intentChooserTitle = parcel.readString();
        this.intentChooserPriorityList = parcel.createStringArrayList();
        this.cropperLabelTextSize = parcel.readFloat();
        this.cropperLabelTextColor = parcel.readInt();
        String readString2 = parcel.readString();
        Intrinsics.checkNotNull(readString2);
        this.cropperLabelText = readString2;
        this.showCropLabel = parcel.readByte() == 0 ? false : z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeByte(this.imageSourceIncludeCamera ? (byte) 1 : 0);
        parcel.writeByte(this.imageSourceIncludeGallery ? (byte) 1 : 0);
        parcel.writeInt(this.cropShape.ordinal());
        parcel.writeInt(this.cornerShape.ordinal());
        parcel.writeFloat(this.cropCornerRadius);
        parcel.writeFloat(this.snapRadius);
        parcel.writeFloat(this.touchRadius);
        parcel.writeInt(this.guidelines.ordinal());
        parcel.writeInt(this.scaleType.ordinal());
        parcel.writeByte(this.showCropOverlay ? (byte) 1 : 0);
        parcel.writeByte(this.showProgressBar ? (byte) 1 : 0);
        parcel.writeInt(this.progressBarColor);
        parcel.writeByte(this.autoZoomEnabled ? (byte) 1 : 0);
        parcel.writeByte(this.multiTouchEnabled ? (byte) 1 : 0);
        parcel.writeByte(this.centerMoveEnabled ? (byte) 1 : 0);
        parcel.writeInt(this.maxZoom);
        parcel.writeFloat(this.initialCropWindowPaddingRatio);
        parcel.writeByte(this.fixAspectRatio ? (byte) 1 : 0);
        parcel.writeInt(this.aspectRatioX);
        parcel.writeInt(this.aspectRatioY);
        parcel.writeFloat(this.borderLineThickness);
        parcel.writeInt(this.borderLineColor);
        parcel.writeFloat(this.borderCornerThickness);
        parcel.writeFloat(this.borderCornerOffset);
        parcel.writeFloat(this.borderCornerLength);
        parcel.writeInt(this.borderCornerColor);
        parcel.writeInt(this.circleCornerFillColorHexValue);
        parcel.writeFloat(this.guidelinesThickness);
        parcel.writeInt(this.guidelinesColor);
        parcel.writeInt(this.backgroundColor);
        parcel.writeInt(this.minCropWindowWidth);
        parcel.writeInt(this.minCropWindowHeight);
        parcel.writeInt(this.minCropResultWidth);
        parcel.writeInt(this.minCropResultHeight);
        parcel.writeInt(this.maxCropResultWidth);
        parcel.writeInt(this.maxCropResultHeight);
        TextUtils.writeToParcel(this.activityTitle, parcel, i);
        parcel.writeInt(this.activityMenuIconColor);
        parcel.writeParcelable(this.customOutputUri, i);
        parcel.writeString(this.outputCompressFormat.name());
        parcel.writeInt(this.outputCompressQuality);
        parcel.writeInt(this.outputRequestWidth);
        parcel.writeInt(this.outputRequestHeight);
        parcel.writeInt(this.outputRequestSizeOptions.ordinal());
        parcel.writeInt(this.noOutputImage ? 1 : 0);
        parcel.writeParcelable(this.initialCropWindowRectangle, i);
        parcel.writeInt(this.initialRotation);
        parcel.writeByte(this.allowRotation ? (byte) 1 : 0);
        parcel.writeByte(this.allowFlipping ? (byte) 1 : 0);
        parcel.writeByte(this.allowCounterRotation ? (byte) 1 : 0);
        parcel.writeInt(this.rotationDegrees);
        parcel.writeByte(this.flipHorizontally ? (byte) 1 : 0);
        parcel.writeByte(this.flipVertically ? (byte) 1 : 0);
        TextUtils.writeToParcel(this.cropMenuCropButtonTitle, parcel, i);
        parcel.writeInt(this.cropMenuCropButtonIcon);
        parcel.writeByte(this.skipEditing ? (byte) 1 : 0);
        parcel.writeByte(this.showIntentChooser ? (byte) 1 : 0);
        parcel.writeString(this.intentChooserTitle);
        parcel.writeStringList(this.intentChooserPriorityList);
        parcel.writeFloat(this.cropperLabelTextSize);
        parcel.writeInt(this.cropperLabelTextColor);
        parcel.writeString(this.cropperLabelText);
        parcel.writeByte(this.showCropLabel ? (byte) 1 : 0);
    }

    public final void validate() {
        boolean z = true;
        if (this.maxZoom >= 0) {
            if (this.touchRadius >= 0.0f) {
                float f = this.initialCropWindowPaddingRatio;
                if (f >= 0.0f && ((double) f) < 0.5d) {
                    if (this.aspectRatioX > 0) {
                        if (this.aspectRatioY > 0) {
                            if (this.borderLineThickness >= 0.0f) {
                                if (this.borderCornerThickness >= 0.0f) {
                                    if (this.guidelinesThickness >= 0.0f) {
                                        if (this.minCropWindowHeight >= 0) {
                                            int i = this.minCropResultWidth;
                                            if (i >= 0) {
                                                int i2 = this.minCropResultHeight;
                                                if (i2 >= 0) {
                                                    if (this.maxCropResultWidth >= i) {
                                                        if (this.maxCropResultHeight >= i2) {
                                                            if (this.outputRequestWidth >= 0) {
                                                                if (this.outputRequestHeight >= 0) {
                                                                    int i3 = this.rotationDegrees;
                                                                    if (i3 < 0 || i3 > 360) {
                                                                        z = false;
                                                                    }
                                                                    if (!z) {
                                                                        throw new IllegalArgumentException("Cannot set rotation degrees value to a number < 0 or > 360".toString());
                                                                    }
                                                                    return;
                                                                }
                                                                throw new IllegalArgumentException("Cannot set request height value to a number < 0 ".toString());
                                                            }
                                                            throw new IllegalArgumentException("Cannot set request width value to a number < 0 ".toString());
                                                        }
                                                        throw new IllegalArgumentException("Cannot set max crop result height to smaller value than min crop result height".toString());
                                                    }
                                                    throw new IllegalArgumentException("Cannot set max crop result width to smaller value than min crop result width".toString());
                                                }
                                                throw new IllegalArgumentException("Cannot set min crop result height value to a number < 0 ".toString());
                                            }
                                            throw new IllegalArgumentException("Cannot set min crop result width value to a number < 0 ".toString());
                                        }
                                        throw new IllegalArgumentException("Cannot set min crop window height value to a number < 0 ".toString());
                                    }
                                    throw new IllegalArgumentException("Cannot set guidelines thickness value to a number less than 0.".toString());
                                }
                                throw new IllegalArgumentException("Cannot set corner thickness value to a number less than 0.".toString());
                            }
                            throw new IllegalArgumentException("Cannot set line thickness value to a number less than 0.".toString());
                        }
                        throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.".toString());
                    }
                    throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.".toString());
                }
                throw new IllegalArgumentException("Cannot set initial crop window padding value to a number < 0 or >= 0.5".toString());
            }
            throw new IllegalArgumentException("Cannot set touch radius value to a number <= 0 ".toString());
        }
        throw new IllegalArgumentException("Cannot set max zoom to a number < 1".toString());
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/canhub/cropper/CropImageOptions$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/canhub/cropper/CropImageOptions;", "DEGREES_360", "", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
