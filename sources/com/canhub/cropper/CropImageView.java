package com.canhub.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.exifinterface.media.ExifInterface;
import com.canhub.cropper.BitmapCroppingWorkerJob;
import com.canhub.cropper.BitmapLoadingWorkerJob;
import com.canhub.cropper.BitmapUtils;
import com.canhub.cropper.CropOverlayView;
import com.canhub.cropper.utils.GetFilePathFromUriKt;
import java.lang.ref.WeakReference;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b#\u0018\u0000 \u00022\u00020\u00012\u00020\u0002:\u0018\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002B\u001b\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J.\u0010¬\u0001\u001a\u00030­\u00012\u0007\u0010®\u0001\u001a\u00020$2\u0007\u0010¯\u0001\u001a\u00020$2\u0007\u0010°\u0001\u001a\u00020Y2\u0007\u0010±\u0001\u001a\u00020YH\u0002J\b\u0010²\u0001\u001a\u00030­\u0001J\b\u0010³\u0001\u001a\u00030­\u0001J\n\u0010´\u0001\u001a\u00030­\u0001H\u0002JM\u0010µ\u0001\u001a\u00030­\u00012\n\b\u0002\u0010¶\u0001\u001a\u00030·\u00012\t\b\u0002\u0010¸\u0001\u001a\u00020\n2\t\b\u0002\u0010¹\u0001\u001a\u00020\n2\t\b\u0002\u0010º\u0001\u001a\u00020\n2\n\b\u0002\u0010»\u0001\u001a\u00030¼\u00012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DJ\b\u0010½\u0001\u001a\u00030­\u0001J\b\u0010¾\u0001\u001a\u00030­\u0001J\u001a\u0010A\u001a\u0004\u0018\u00010@2\u0007\u0010¹\u0001\u001a\u00020\n2\u0007\u0010º\u0001\u001a\u00020\nJ$\u0010A\u001a\u0004\u0018\u00010@2\u0007\u0010¹\u0001\u001a\u00020\n2\u0007\u0010º\u0001\u001a\u00020\n2\b\u0010»\u0001\u001a\u00030¼\u0001J\u001c\u0010¿\u0001\u001a\u00030­\u00012\u0007\u0010À\u0001\u001a\u00020Y2\u0007\u0010±\u0001\u001a\u00020YH\u0002J\n\u0010Á\u0001\u001a\u00030­\u0001H\u0002J\u0013\u0010Â\u0001\u001a\u00030­\u00012\u0007\u0010À\u0001\u001a\u00020YH\u0016J\u0012\u0010Ã\u0001\u001a\u00030­\u00012\b\u0010Ä\u0001\u001a\u00030Å\u0001J7\u0010Æ\u0001\u001a\u00030­\u00012\u0007\u0010Ç\u0001\u001a\u00020Y2\u0007\u0010È\u0001\u001a\u00020\n2\u0007\u0010É\u0001\u001a\u00020\n2\u0007\u0010Ê\u0001\u001a\u00020\n2\u0007\u0010Ë\u0001\u001a\u00020\nH\u0014J\u001c\u0010Ì\u0001\u001a\u00030­\u00012\u0007\u0010Í\u0001\u001a\u00020\n2\u0007\u0010Î\u0001\u001a\u00020\nH\u0014J\u0014\u0010Ï\u0001\u001a\u00030­\u00012\b\u0010Ð\u0001\u001a\u00030Ñ\u0001H\u0016J\f\u0010Ò\u0001\u001a\u0005\u0018\u00010Ñ\u0001H\u0016J\u0012\u0010Ó\u0001\u001a\u00030­\u00012\b\u0010Ä\u0001\u001a\u00030Ô\u0001J.\u0010Õ\u0001\u001a\u00030­\u00012\u0007\u0010Ö\u0001\u001a\u00020\n2\u0007\u0010×\u0001\u001a\u00020\n2\u0007\u0010Ø\u0001\u001a\u00020\n2\u0007\u0010Ù\u0001\u001a\u00020\nH\u0014J\b\u0010Ú\u0001\u001a\u00030­\u0001J\u0011\u0010Û\u0001\u001a\u00030­\u00012\u0007\u0010¡\u0001\u001a\u00020\nJ\u001a\u0010Ü\u0001\u001a\u00030­\u00012\u0007\u0010Ý\u0001\u001a\u00020\n2\u0007\u0010Þ\u0001\u001a\u00020\nJ9\u0010ß\u0001\u001a\u00030­\u00012\t\u0010à\u0001\u001a\u0004\u0018\u00010@2\u0006\u0010P\u001a\u00020\n2\b\u0010T\u001a\u0004\u0018\u00010D2\u0007\u0010á\u0001\u001a\u00020\n2\u0007\u0010â\u0001\u001a\u00020\nH\u0002J\u0011\u0010ã\u0001\u001a\u00030­\u00012\u0007\u0010ä\u0001\u001a\u00020YJ\n\u0010å\u0001\u001a\u00030­\u0001H\u0002J\u0011\u0010æ\u0001\u001a\u00030­\u00012\u0007\u0010ç\u0001\u001a\u00020YJ\u0013\u0010è\u0001\u001a\u00030­\u00012\t\u0010à\u0001\u001a\u0004\u0018\u00010@J\u001f\u0010è\u0001\u001a\u00030­\u00012\t\u0010à\u0001\u001a\u0004\u0018\u00010@2\n\u0010é\u0001\u001a\u0005\u0018\u00010ê\u0001J\u0013\u0010ë\u0001\u001a\u00030­\u00012\t\u0010ì\u0001\u001a\u0004\u0018\u00010DJ\u001a\u0010í\u0001\u001a\u00030­\u00012\u0007\u0010î\u0001\u001a\u00020\n2\u0007\u0010ï\u0001\u001a\u00020\nJ\u001a\u0010ð\u0001\u001a\u00030­\u00012\u0007\u0010ñ\u0001\u001a\u00020\n2\u0007\u0010ò\u0001\u001a\u00020\nJ\u0011\u0010ó\u0001\u001a\u00030­\u00012\u0007\u0010ô\u0001\u001a\u00020YJ\u0014\u0010õ\u0001\u001a\u00030­\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u0001J\u0014\u0010÷\u0001\u001a\u00030­\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u0001J\u0014\u0010ø\u0001\u001a\u00030­\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u0001J\u0014\u0010ù\u0001\u001a\u00030­\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u0001J\u0014\u0010ú\u0001\u001a\u00030­\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u0001J\n\u0010û\u0001\u001a\u00030­\u0001H\u0002J\u0011\u0010ü\u0001\u001a\u00030­\u00012\u0007\u0010ý\u0001\u001a\u00020$JA\u0010þ\u0001\u001a\u00030­\u00012\u0007\u0010¹\u0001\u001a\u00020\n2\u0007\u0010º\u0001\u001a\u00020\n2\b\u0010»\u0001\u001a\u00030¼\u00012\b\u0010¶\u0001\u001a\u00030·\u00012\u0007\u0010¸\u0001\u001a\u00020\n2\b\u0010C\u001a\u0004\u0018\u00010DJ\u0013\u0010ÿ\u0001\u001a\u00030­\u00012\u0007\u0010\u0002\u001a\u00020YH\u0002R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R(\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u00138F@FX\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198F@FX\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n8F@FX\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020$8F@FX\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b,\u0010-R(\u00100\u001a\u0004\u0018\u00010/2\b\u0010.\u001a\u0004\u0018\u00010/8F@FX\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104R(\u00105\u001a\u0004\u0018\u0001062\b\u00105\u001a\u0004\u0018\u0001068F@FX\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0013\u0010;\u001a\u0004\u0018\u00010<8F¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0013\u0010?\u001a\u0004\u0018\u00010@8F¢\u0006\u0006\u001a\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010DX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR(\u0010I\u001a\u0004\u0018\u00010J2\b\u0010I\u001a\u0004\u0018\u00010J8F@FX\u000e¢\u0006\f\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR$\u0010P\u001a\u00020\n2\u0006\u0010O\u001a\u00020\n8F@FX\u000e¢\u0006\f\u001a\u0004\bQ\u0010 \"\u0004\bR\u0010\"R\"\u0010T\u001a\u0004\u0018\u00010D2\b\u0010S\u001a\u0004\u0018\u00010D@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\bU\u0010FR\u000e\u0010V\u001a\u00020WX\u0004¢\u0006\u0002\n\u0000R$\u0010Z\u001a\u00020Y2\u0006\u0010X\u001a\u00020Y8F@FX\u000e¢\u0006\f\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u0011\u0010^\u001a\u00020Y8F¢\u0006\u0006\u001a\u0004\b^\u0010[R$\u0010`\u001a\u00020Y2\u0006\u0010_\u001a\u00020Y8F@FX\u000e¢\u0006\f\u001a\u0004\b`\u0010[\"\u0004\ba\u0010]R$\u0010c\u001a\u00020Y2\u0006\u0010b\u001a\u00020Y8F@FX\u000e¢\u0006\f\u001a\u0004\bc\u0010[\"\u0004\bd\u0010]R\u001a\u0010e\u001a\u00020YX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010[\"\u0004\bf\u0010]R$\u0010h\u001a\u00020Y2\u0006\u0010g\u001a\u00020Y8F@FX\u000e¢\u0006\f\u001a\u0004\bh\u0010[\"\u0004\bi\u0010]R$\u0010k\u001a\u00020Y2\u0006\u0010j\u001a\u00020Y8F@FX\u000e¢\u0006\f\u001a\u0004\bk\u0010[\"\u0004\bl\u0010]R$\u0010n\u001a\u00020Y2\u0006\u0010m\u001a\u00020Y8F@FX\u000e¢\u0006\f\u001a\u0004\bn\u0010[\"\u0004\bo\u0010]R\u000e\u0010p\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010q\u001a\u0004\u0018\u00010rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020YX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010v\u001a\u0004\u0018\u00010wX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020YX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020YX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020}X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020}X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u00020+X\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0001\u001a\u0005\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0001\u001a\u0005\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0001\u001a\u0005\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0001\u001a\u0005\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0001\u001a\u0005\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0001\u001a\u00030\u0001X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0001\u001a\u0004\u0018\u00010<X\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020+X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0001\u001a\u00030\u0001X\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020YX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020YX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020YX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020YX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000R(\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\n8F@FX\u000e¢\u0006\u000e\u001a\u0005\b\u0001\u0010 \"\u0005\b\u0001\u0010\"R\u0011\u0010 \u0001\u001a\u0004\u0018\u00010@X\u000e¢\u0006\u0002\n\u0000R(\u0010¢\u0001\u001a\u00020\n2\u0007\u0010¡\u0001\u001a\u00020\n8F@FX\u000e¢\u0006\u000e\u001a\u0005\b£\u0001\u0010 \"\u0005\b¤\u0001\u0010\"R,\u0010¥\u0001\u001a\u00030\u00012\b\u0010¥\u0001\u001a\u00030\u00018F@FX\u000e¢\u0006\u0010\u001a\u0006\b¦\u0001\u0010§\u0001\"\u0006\b¨\u0001\u0010©\u0001R\u0015\u0010ª\u0001\u001a\u0004\u0018\u00010/8F¢\u0006\u0007\u001a\u0005\b«\u0001\u00102¨\u0006\u0002"}, d2 = {"Lcom/canhub/cropper/CropImageView;", "Landroid/widget/FrameLayout;", "Lcom/canhub/cropper/CropOverlayView$CropWindowChangeListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "aspectRatio", "Landroid/util/Pair;", "", "getAspectRatio", "()Landroid/util/Pair;", "bitmapCroppingWorkerJob", "Ljava/lang/ref/WeakReference;", "Lcom/canhub/cropper/BitmapCroppingWorkerJob;", "bitmapLoadingWorkerJob", "Lcom/canhub/cropper/BitmapLoadingWorkerJob;", "cornerShape", "Lcom/canhub/cropper/CropImageView$CropCornerShape;", "getCornerShape", "()Lcom/canhub/cropper/CropImageView$CropCornerShape;", "setCornerShape", "(Lcom/canhub/cropper/CropImageView$CropCornerShape;)V", "cropLabelText", "", "getCropLabelText", "()Ljava/lang/String;", "setCropLabelText", "(Ljava/lang/String;)V", "cropLabelTextColor", "getCropLabelTextColor", "()I", "setCropLabelTextColor", "(I)V", "textSize", "", "cropLabelTextSize", "getCropLabelTextSize", "()F", "setCropLabelTextSize", "(F)V", "cropPoints", "", "getCropPoints", "()[F", "rect", "Landroid/graphics/Rect;", "cropRect", "getCropRect", "()Landroid/graphics/Rect;", "setCropRect", "(Landroid/graphics/Rect;)V", "cropShape", "Lcom/canhub/cropper/CropImageView$CropShape;", "getCropShape", "()Lcom/canhub/cropper/CropImageView$CropShape;", "setCropShape", "(Lcom/canhub/cropper/CropImageView$CropShape;)V", "cropWindowRect", "Landroid/graphics/RectF;", "getCropWindowRect", "()Landroid/graphics/RectF;", "croppedImage", "Landroid/graphics/Bitmap;", "getCroppedImage", "()Landroid/graphics/Bitmap;", "customOutputUri", "Landroid/net/Uri;", "getCustomOutputUri", "()Landroid/net/Uri;", "setCustomOutputUri", "(Landroid/net/Uri;)V", "guidelines", "Lcom/canhub/cropper/CropImageView$Guidelines;", "getGuidelines", "()Lcom/canhub/cropper/CropImageView$Guidelines;", "setGuidelines", "(Lcom/canhub/cropper/CropImageView$Guidelines;)V", "resId", "imageResource", "getImageResource", "setImageResource", "<set-?>", "imageUri", "getImageUri", "imageView", "Landroid/widget/ImageView;", "autoZoomEnabled", "", "isAutoZoomEnabled", "()Z", "setAutoZoomEnabled", "(Z)V", "isFixAspectRatio", "flipHorizontally", "isFlippedHorizontally", "setFlippedHorizontally", "flipVertically", "isFlippedVertically", "setFlippedVertically", "isSaveBitmapToInstanceState", "setSaveBitmapToInstanceState", "showCropLabel", "isShowCropLabel", "setShowCropLabel", "showCropOverlay", "isShowCropOverlay", "setShowCropOverlay", "showProgressBar", "isShowProgressBar", "setShowProgressBar", "loadedSampleSize", "mAnimation", "Lcom/canhub/cropper/CropImageAnimation;", "mAutoZoomEnabled", "mCropLabelTextColor", "mCropLabelTextSize", "mCropOverlayView", "Lcom/canhub/cropper/CropOverlayView;", "mCropTextLabel", "mDegreesRotated", "mFlipHorizontally", "mFlipVertically", "mImageInverseMatrix", "Landroid/graphics/Matrix;", "mImageMatrix", "mImagePoints", "mImageResource", "mInitialDegreesRotated", "mLayoutHeight", "mLayoutWidth", "mMaxZoom", "mOnCropImageCompleteListener", "Lcom/canhub/cropper/CropImageView$OnCropImageCompleteListener;", "mOnCropOverlayReleasedListener", "Lcom/canhub/cropper/CropImageView$OnSetCropOverlayReleasedListener;", "mOnSetCropOverlayMovedListener", "Lcom/canhub/cropper/CropImageView$OnSetCropOverlayMovedListener;", "mOnSetCropWindowChangeListener", "Lcom/canhub/cropper/CropImageView$OnSetCropWindowChangeListener;", "mOnSetImageUriCompleteListener", "Lcom/canhub/cropper/CropImageView$OnSetImageUriCompleteListener;", "mProgressBar", "Landroid/widget/ProgressBar;", "mRestoreCropWindowRect", "mRestoreDegreesRotated", "mScaleImagePoints", "mScaleType", "Lcom/canhub/cropper/CropImageView$ScaleType;", "mShowCropLabel", "mShowCropOverlay", "mShowProgressBar", "mSizeChanged", "mZoom", "mZoomOffsetX", "mZoomOffsetY", "maxZoom", "getMaxZoom", "setMaxZoom", "originalBitmap", "degrees", "rotatedDegrees", "getRotatedDegrees", "setRotatedDegrees", "scaleType", "getScaleType", "()Lcom/canhub/cropper/CropImageView$ScaleType;", "setScaleType", "(Lcom/canhub/cropper/CropImageView$ScaleType;)V", "wholeImageRect", "getWholeImageRect", "applyImageMatrix", "", "width", "height", "center", "animate", "clearAspectRatio", "clearImage", "clearImageInt", "croppedImageAsync", "saveCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "saveCompressQuality", "reqWidth", "reqHeight", "options", "Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "flipImageHorizontally", "flipImageVertically", "handleCropWindowChanged", "inProgress", "mapImagePointsByImageMatrix", "onCropWindowChanged", "onImageCroppingAsyncComplete", "result", "Lcom/canhub/cropper/BitmapCroppingWorkerJob$Result;", "onLayout", "changed", "l", "t", "r", "b", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onRestoreInstanceState", "state", "Landroid/os/Parcelable;", "onSaveInstanceState", "onSetImageUriAsyncComplete", "Lcom/canhub/cropper/BitmapLoadingWorkerJob$Result;", "onSizeChanged", "w", "h", "oldw", "oldh", "resetCropRect", "rotateImage", "setAspectRatio", "aspectRatioX", "aspectRatioY", "setBitmap", "bitmap", "loadSampleSize", "degreesRotated", "setCenterMoveEnabled", "centerMoveEnabled", "setCropOverlayVisibility", "setFixedAspectRatio", "fixAspectRatio", "setImageBitmap", "exif", "Landroidx/exifinterface/media/ExifInterface;", "setImageUriAsync", "uri", "setMaxCropResultSize", "maxCropResultWidth", "maxCropResultHeight", "setMinCropResultSize", "minCropResultWidth", "minCropResultHeight", "setMultiTouchEnabled", "multiTouchEnabled", "setOnCropImageCompleteListener", "listener", "setOnCropWindowChangedListener", "setOnSetCropOverlayMovedListener", "setOnSetCropOverlayReleasedListener", "setOnSetImageUriCompleteListener", "setProgressBarVisibility", "setSnapRadius", "snapRadius", "startCropWorkerTask", "updateImageBounds", "clear", "Companion", "CropCornerShape", "CropResult", "CropShape", "Guidelines", "OnCropImageCompleteListener", "OnSetCropOverlayMovedListener", "OnSetCropOverlayReleasedListener", "OnSetCropWindowChangeListener", "OnSetImageUriCompleteListener", "RequestSizeOptions", "ScaleType", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageView.kt */
public final class CropImageView extends FrameLayout implements CropOverlayView.CropWindowChangeListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private WeakReference<BitmapCroppingWorkerJob> bitmapCroppingWorkerJob;
    private WeakReference<BitmapLoadingWorkerJob> bitmapLoadingWorkerJob;
    private Uri customOutputUri;
    private Uri imageUri;
    private final ImageView imageView;
    private boolean isSaveBitmapToInstanceState;
    private int loadedSampleSize;
    private CropImageAnimation mAnimation;
    private boolean mAutoZoomEnabled;
    private int mCropLabelTextColor;
    private float mCropLabelTextSize;
    private final CropOverlayView mCropOverlayView;
    private String mCropTextLabel;
    private int mDegreesRotated;
    private boolean mFlipHorizontally;
    private boolean mFlipVertically;
    private final Matrix mImageInverseMatrix;
    private final Matrix mImageMatrix;
    private final float[] mImagePoints;
    private int mImageResource;
    private int mInitialDegreesRotated;
    private int mLayoutHeight;
    private int mLayoutWidth;
    private int mMaxZoom;
    private OnCropImageCompleteListener mOnCropImageCompleteListener;
    private OnSetCropOverlayReleasedListener mOnCropOverlayReleasedListener;
    private OnSetCropOverlayMovedListener mOnSetCropOverlayMovedListener;
    private OnSetCropWindowChangeListener mOnSetCropWindowChangeListener;
    private OnSetImageUriCompleteListener mOnSetImageUriCompleteListener;
    private final ProgressBar mProgressBar;
    private RectF mRestoreCropWindowRect;
    private int mRestoreDegreesRotated;
    private final float[] mScaleImagePoints;
    private ScaleType mScaleType;
    private boolean mShowCropLabel;
    private boolean mShowCropOverlay;
    private boolean mShowProgressBar;
    private boolean mSizeChanged;
    private float mZoom;
    private float mZoomOffsetX;
    private float mZoomOffsetY;
    private Bitmap originalBitmap;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/canhub/cropper/CropImageView$CropCornerShape;", "", "(Ljava/lang/String;I)V", "RECTANGLE", "OVAL", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public enum CropCornerShape {
        RECTANGLE,
        OVAL
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/canhub/cropper/CropImageView$CropShape;", "", "(Ljava/lang/String;I)V", "RECTANGLE", "OVAL", "RECTANGLE_VERTICAL_ONLY", "RECTANGLE_HORIZONTAL_ONLY", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public enum CropShape {
        RECTANGLE,
        OVAL,
        RECTANGLE_VERTICAL_ONLY,
        RECTANGLE_HORIZONTAL_ONLY
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/canhub/cropper/CropImageView$Guidelines;", "", "(Ljava/lang/String;I)V", "OFF", "ON_TOUCH", "ON", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public enum Guidelines {
        OFF,
        ON_TOUCH,
        ON
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/canhub/cropper/CropImageView$OnCropImageCompleteListener;", "", "onCropImageComplete", "", "view", "Lcom/canhub/cropper/CropImageView;", "result", "Lcom/canhub/cropper/CropImageView$CropResult;", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public interface OnCropImageCompleteListener {
        void onCropImageComplete(CropImageView cropImageView, CropResult cropResult);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/canhub/cropper/CropImageView$OnSetCropOverlayMovedListener;", "", "onCropOverlayMoved", "", "rect", "Landroid/graphics/Rect;", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public interface OnSetCropOverlayMovedListener {
        void onCropOverlayMoved(Rect rect);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/canhub/cropper/CropImageView$OnSetCropOverlayReleasedListener;", "", "onCropOverlayReleased", "", "rect", "Landroid/graphics/Rect;", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public interface OnSetCropOverlayReleasedListener {
        void onCropOverlayReleased(Rect rect);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/canhub/cropper/CropImageView$OnSetCropWindowChangeListener;", "", "onCropWindowChanged", "", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public interface OnSetCropWindowChangeListener {
        void onCropWindowChanged();
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nH&¨\u0006\u000b"}, d2 = {"Lcom/canhub/cropper/CropImageView$OnSetImageUriCompleteListener;", "", "onSetImageUriComplete", "", "view", "Lcom/canhub/cropper/CropImageView;", "uri", "Landroid/net/Uri;", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public interface OnSetImageUriCompleteListener {
        void onSetImageUriComplete(CropImageView cropImageView, Uri uri, Exception exc);
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "", "(Ljava/lang/String;I)V", "NONE", "SAMPLING", "RESIZE_INSIDE", "RESIZE_FIT", "RESIZE_EXACT", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public enum RequestSizeOptions {
        NONE,
        SAMPLING,
        RESIZE_INSIDE,
        RESIZE_FIT,
        RESIZE_EXACT
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/canhub/cropper/CropImageView$ScaleType;", "", "(Ljava/lang/String;I)V", "FIT_CENTER", "CENTER", "CENTER_CROP", "CENTER_INSIDE", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public enum ScaleType {
        FIT_CENTER,
        CENTER,
        CENTER_CROP,
        CENTER_INSIDE
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CropImageView(Context context) {
        this(context, (AttributeSet) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CropImageView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARNING: type inference failed for: r1v106, types: [android.os.Parcelable] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CropImageView(android.content.Context r6, android.util.AttributeSet r7) {
        /*
            r5 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r5.<init>(r6, r7)
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            r5.mImageMatrix = r0
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            r5.mImageInverseMatrix = r0
            r0 = 8
            float[] r1 = new float[r0]
            r5.mImagePoints = r1
            float[] r0 = new float[r0]
            r5.mScaleImagePoints = r0
            r0 = 1
            r5.mShowCropOverlay = r0
            java.lang.String r1 = ""
            r5.mCropTextLabel = r1
            r1 = 1101004800(0x41a00000, float:20.0)
            r5.mCropLabelTextSize = r1
            r1 = -1
            r5.mCropLabelTextColor = r1
            r5.mShowProgressBar = r0
            r5.mAutoZoomEnabled = r0
            r5.loadedSampleSize = r0
            r1 = 1065353216(0x3f800000, float:1.0)
            r5.mZoom = r1
            boolean r1 = r6 instanceof android.app.Activity
            r2 = 0
            if (r1 == 0) goto L_0x0045
            r1 = r6
            android.app.Activity r1 = (android.app.Activity) r1
            android.content.Intent r1 = r1.getIntent()
            goto L_0x0046
        L_0x0045:
            r1 = r2
        L_0x0046:
            if (r1 == 0) goto L_0x0059
            java.lang.String r3 = "CROP_IMAGE_EXTRA_BUNDLE"
            android.os.Bundle r1 = r1.getBundleExtra(r3)
            if (r1 == 0) goto L_0x0059
            java.lang.String r2 = "CROP_IMAGE_EXTRA_OPTIONS"
            android.os.Parcelable r1 = r1.getParcelable(r2)
            r2 = r1
            com.canhub.cropper.CropImageOptions r2 = (com.canhub.cropper.CropImageOptions) r2
        L_0x0059:
            if (r2 != 0) goto L_0x025d
            com.canhub.cropper.CropImageOptions r2 = new com.canhub.cropper.CropImageOptions
            r2.<init>()
            if (r7 == 0) goto L_0x025d
            int[] r1 = com.canhub.cropper.R.styleable.CropImageView
            r3 = 0
            android.content.res.TypedArray r7 = r6.obtainStyledAttributes(r7, r1, r3, r3)
            java.lang.String r1 = "context.obtainStyledAttr…able.CropImageView, 0, 0)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r1)
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropFixAspectRatio     // Catch:{ all -> 0x0258 }
            boolean r3 = r2.fixAspectRatio     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.fixAspectRatio = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropAspectRatioX     // Catch:{ all -> 0x0258 }
            int r3 = r2.aspectRatioX     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.aspectRatioX = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropAspectRatioY     // Catch:{ all -> 0x0258 }
            int r3 = r2.aspectRatioY     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.aspectRatioY = r1     // Catch:{ all -> 0x0258 }
            com.canhub.cropper.CropImageView$ScaleType[] r1 = com.canhub.cropper.CropImageView.ScaleType.values()     // Catch:{ all -> 0x0258 }
            int r3 = com.canhub.cropper.R.styleable.CropImageView_cropScaleType     // Catch:{ all -> 0x0258 }
            com.canhub.cropper.CropImageView$ScaleType r4 = r2.scaleType     // Catch:{ all -> 0x0258 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0258 }
            int r3 = r7.getInt(r3, r4)     // Catch:{ all -> 0x0258 }
            r1 = r1[r3]     // Catch:{ all -> 0x0258 }
            r2.scaleType = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropAutoZoomEnabled     // Catch:{ all -> 0x0258 }
            boolean r3 = r2.autoZoomEnabled     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.autoZoomEnabled = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropMultiTouchEnabled     // Catch:{ all -> 0x0258 }
            boolean r3 = r2.multiTouchEnabled     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.multiTouchEnabled = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropCenterMoveEnabled     // Catch:{ all -> 0x0258 }
            boolean r3 = r2.centerMoveEnabled     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.centerMoveEnabled = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropMaxZoom     // Catch:{ all -> 0x0258 }
            int r3 = r2.maxZoom     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.maxZoom = r1     // Catch:{ all -> 0x0258 }
            com.canhub.cropper.CropImageView$CropShape[] r1 = com.canhub.cropper.CropImageView.CropShape.values()     // Catch:{ all -> 0x0258 }
            int r3 = com.canhub.cropper.R.styleable.CropImageView_cropShape     // Catch:{ all -> 0x0258 }
            com.canhub.cropper.CropImageView$CropShape r4 = r2.cropShape     // Catch:{ all -> 0x0258 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0258 }
            int r3 = r7.getInt(r3, r4)     // Catch:{ all -> 0x0258 }
            r1 = r1[r3]     // Catch:{ all -> 0x0258 }
            r2.cropShape = r1     // Catch:{ all -> 0x0258 }
            com.canhub.cropper.CropImageView$CropCornerShape[] r1 = com.canhub.cropper.CropImageView.CropCornerShape.values()     // Catch:{ all -> 0x0258 }
            int r3 = com.canhub.cropper.R.styleable.CropImageView_cornerShape     // Catch:{ all -> 0x0258 }
            com.canhub.cropper.CropImageView$CropCornerShape r4 = r2.cornerShape     // Catch:{ all -> 0x0258 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0258 }
            int r3 = r7.getInt(r3, r4)     // Catch:{ all -> 0x0258 }
            r1 = r1[r3]     // Catch:{ all -> 0x0258 }
            r2.cornerShape = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropCornerRadius     // Catch:{ all -> 0x0258 }
            float r3 = r2.cropCornerRadius     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.cropCornerRadius = r1     // Catch:{ all -> 0x0258 }
            com.canhub.cropper.CropImageView$Guidelines[] r1 = com.canhub.cropper.CropImageView.Guidelines.values()     // Catch:{ all -> 0x0258 }
            int r3 = com.canhub.cropper.R.styleable.CropImageView_cropGuidelines     // Catch:{ all -> 0x0258 }
            com.canhub.cropper.CropImageView$Guidelines r4 = r2.guidelines     // Catch:{ all -> 0x0258 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0258 }
            int r3 = r7.getInt(r3, r4)     // Catch:{ all -> 0x0258 }
            r1 = r1[r3]     // Catch:{ all -> 0x0258 }
            r2.guidelines = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropSnapRadius     // Catch:{ all -> 0x0258 }
            float r3 = r2.snapRadius     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.snapRadius = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropTouchRadius     // Catch:{ all -> 0x0258 }
            float r3 = r2.touchRadius     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.touchRadius = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropInitialCropWindowPaddingRatio     // Catch:{ all -> 0x0258 }
            float r3 = r2.initialCropWindowPaddingRatio     // Catch:{ all -> 0x0258 }
            float r1 = r7.getFloat(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.initialCropWindowPaddingRatio = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropCornerCircleFillColor     // Catch:{ all -> 0x0258 }
            int r3 = r2.circleCornerFillColorHexValue     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.circleCornerFillColorHexValue = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropBorderLineThickness     // Catch:{ all -> 0x0258 }
            float r3 = r2.borderLineThickness     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.borderLineThickness = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropBorderLineColor     // Catch:{ all -> 0x0258 }
            int r3 = r2.borderLineColor     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.borderLineColor = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropBorderCornerThickness     // Catch:{ all -> 0x0258 }
            float r3 = r2.borderCornerThickness     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.borderCornerThickness = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropBorderCornerOffset     // Catch:{ all -> 0x0258 }
            float r3 = r2.borderCornerOffset     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.borderCornerOffset = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropBorderCornerLength     // Catch:{ all -> 0x0258 }
            float r3 = r2.borderCornerLength     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.borderCornerLength = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropBorderCornerColor     // Catch:{ all -> 0x0258 }
            int r3 = r2.borderCornerColor     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.borderCornerColor = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropGuidelinesThickness     // Catch:{ all -> 0x0258 }
            float r3 = r2.guidelinesThickness     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.guidelinesThickness = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropGuidelinesColor     // Catch:{ all -> 0x0258 }
            int r3 = r2.guidelinesColor     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.guidelinesColor = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropBackgroundColor     // Catch:{ all -> 0x0258 }
            int r3 = r2.backgroundColor     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.backgroundColor = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropShowCropOverlay     // Catch:{ all -> 0x0258 }
            boolean r3 = r5.mShowCropOverlay     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.showCropOverlay = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropShowProgressBar     // Catch:{ all -> 0x0258 }
            boolean r3 = r5.mShowProgressBar     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.showProgressBar = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropBorderCornerThickness     // Catch:{ all -> 0x0258 }
            float r3 = r2.borderCornerThickness     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.borderCornerThickness = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropMinCropWindowWidth     // Catch:{ all -> 0x0258 }
            int r3 = r2.minCropWindowWidth     // Catch:{ all -> 0x0258 }
            float r3 = (float) r3     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            int r1 = (int) r1     // Catch:{ all -> 0x0258 }
            r2.minCropWindowWidth = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropMinCropWindowHeight     // Catch:{ all -> 0x0258 }
            int r3 = r2.minCropWindowHeight     // Catch:{ all -> 0x0258 }
            float r3 = (float) r3     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            int r1 = (int) r1     // Catch:{ all -> 0x0258 }
            r2.minCropWindowHeight = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropMinCropResultWidthPX     // Catch:{ all -> 0x0258 }
            int r3 = r2.minCropResultWidth     // Catch:{ all -> 0x0258 }
            float r3 = (float) r3     // Catch:{ all -> 0x0258 }
            float r1 = r7.getFloat(r1, r3)     // Catch:{ all -> 0x0258 }
            int r1 = (int) r1     // Catch:{ all -> 0x0258 }
            r2.minCropResultWidth = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropMinCropResultHeightPX     // Catch:{ all -> 0x0258 }
            int r3 = r2.minCropResultHeight     // Catch:{ all -> 0x0258 }
            float r3 = (float) r3     // Catch:{ all -> 0x0258 }
            float r1 = r7.getFloat(r1, r3)     // Catch:{ all -> 0x0258 }
            int r1 = (int) r1     // Catch:{ all -> 0x0258 }
            r2.minCropResultHeight = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropMaxCropResultWidthPX     // Catch:{ all -> 0x0258 }
            int r3 = r2.maxCropResultWidth     // Catch:{ all -> 0x0258 }
            float r3 = (float) r3     // Catch:{ all -> 0x0258 }
            float r1 = r7.getFloat(r1, r3)     // Catch:{ all -> 0x0258 }
            int r1 = (int) r1     // Catch:{ all -> 0x0258 }
            r2.maxCropResultWidth = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropMaxCropResultHeightPX     // Catch:{ all -> 0x0258 }
            int r3 = r2.maxCropResultHeight     // Catch:{ all -> 0x0258 }
            float r3 = (float) r3     // Catch:{ all -> 0x0258 }
            float r1 = r7.getFloat(r1, r3)     // Catch:{ all -> 0x0258 }
            int r1 = (int) r1     // Catch:{ all -> 0x0258 }
            r2.maxCropResultHeight = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropFlipHorizontally     // Catch:{ all -> 0x0258 }
            boolean r3 = r2.flipHorizontally     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.flipHorizontally = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropFlipHorizontally     // Catch:{ all -> 0x0258 }
            boolean r3 = r2.flipVertically     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.flipVertically = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropperLabelTextSize     // Catch:{ all -> 0x0258 }
            float r3 = r2.cropperLabelTextSize     // Catch:{ all -> 0x0258 }
            float r1 = r7.getDimension(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.cropperLabelTextSize = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropperLabelTextColor     // Catch:{ all -> 0x0258 }
            int r3 = r2.cropperLabelTextColor     // Catch:{ all -> 0x0258 }
            int r1 = r7.getInteger(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.cropperLabelTextColor = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropperLabelText     // Catch:{ all -> 0x0258 }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ all -> 0x0258 }
            r2.cropperLabelText = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropShowLabel     // Catch:{ all -> 0x0258 }
            boolean r3 = r2.showCropLabel     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r2.showCropLabel = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropSaveBitmapToInstanceState     // Catch:{ all -> 0x0258 }
            boolean r3 = r5.isSaveBitmapToInstanceState     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.getBoolean(r1, r3)     // Catch:{ all -> 0x0258 }
            r5.isSaveBitmapToInstanceState = r1     // Catch:{ all -> 0x0258 }
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropAspectRatioX     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.hasValue(r1)     // Catch:{ all -> 0x0258 }
            if (r1 == 0) goto L_0x0254
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropAspectRatioX     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.hasValue(r1)     // Catch:{ all -> 0x0258 }
            if (r1 == 0) goto L_0x0254
            int r1 = com.canhub.cropper.R.styleable.CropImageView_cropFixAspectRatio     // Catch:{ all -> 0x0258 }
            boolean r1 = r7.hasValue(r1)     // Catch:{ all -> 0x0258 }
            if (r1 != 0) goto L_0x0254
            r2.fixAspectRatio = r0     // Catch:{ all -> 0x0258 }
        L_0x0254:
            r7.recycle()
            goto L_0x025d
        L_0x0258:
            r6 = move-exception
            r7.recycle()
            throw r6
        L_0x025d:
            r2.validate()
            com.canhub.cropper.CropImageView$ScaleType r7 = r2.scaleType
            r5.mScaleType = r7
            boolean r7 = r2.autoZoomEnabled
            r5.mAutoZoomEnabled = r7
            int r7 = r2.maxZoom
            r5.mMaxZoom = r7
            float r7 = r2.cropperLabelTextSize
            r5.mCropLabelTextSize = r7
            boolean r7 = r2.showCropLabel
            r5.mShowCropLabel = r7
            boolean r7 = r2.showCropOverlay
            r5.mShowCropOverlay = r7
            boolean r7 = r2.showProgressBar
            r5.mShowProgressBar = r7
            boolean r7 = r2.flipHorizontally
            r5.mFlipHorizontally = r7
            boolean r7 = r2.flipVertically
            r5.mFlipVertically = r7
            android.view.LayoutInflater r6 = android.view.LayoutInflater.from(r6)
            int r7 = com.canhub.cropper.R.layout.crop_image_view
            r1 = r5
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            android.view.View r6 = r6.inflate(r7, r1, r0)
            int r7 = com.canhub.cropper.R.id.ImageView_image
            android.view.View r7 = r6.findViewById(r7)
            java.lang.String r0 = "v.findViewById(R.id.ImageView_image)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            r5.imageView = r7
            android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.MATRIX
            r7.setScaleType(r0)
            int r7 = com.canhub.cropper.R.id.CropOverlayView
            android.view.View r7 = r6.findViewById(r7)
            com.canhub.cropper.CropOverlayView r7 = (com.canhub.cropper.CropOverlayView) r7
            r5.mCropOverlayView = r7
            r0 = r5
            com.canhub.cropper.CropOverlayView$CropWindowChangeListener r0 = (com.canhub.cropper.CropOverlayView.CropWindowChangeListener) r0
            r7.setCropWindowChangeListener(r0)
            r7.setInitialAttributeValues(r2)
            int r7 = com.canhub.cropper.R.id.CropProgressBar
            android.view.View r6 = r6.findViewById(r7)
            java.lang.String r7 = "v.findViewById(R.id.CropProgressBar)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            android.widget.ProgressBar r6 = (android.widget.ProgressBar) r6
            r5.mProgressBar = r6
            int r7 = r2.progressBarColor
            android.content.res.ColorStateList r7 = android.content.res.ColorStateList.valueOf(r7)
            r6.setIndeterminateTintList(r7)
            r5.setProgressBarVisibility()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.CropImageView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final boolean isSaveBitmapToInstanceState() {
        return this.isSaveBitmapToInstanceState;
    }

    public final void setSaveBitmapToInstanceState(boolean z) {
        this.isSaveBitmapToInstanceState = z;
    }

    public final Uri getImageUri() {
        return this.imageUri;
    }

    public final ScaleType getScaleType() {
        return this.mScaleType;
    }

    public final void setScaleType(ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        if (scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            this.mZoom = 1.0f;
            this.mZoomOffsetY = 0.0f;
            this.mZoomOffsetX = 0.0f;
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            if (cropOverlayView != null) {
                cropOverlayView.resetCropOverlayView();
            }
            requestLayout();
        }
    }

    public final CropShape getCropShape() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return cropOverlayView.getCropShape();
    }

    public final void setCropShape(CropShape cropShape) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        Intrinsics.checkNotNull(cropShape);
        cropOverlayView.setCropShape(cropShape);
    }

    public final CropCornerShape getCornerShape() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return cropOverlayView.getCornerShape();
    }

    public final void setCornerShape(CropCornerShape cropCornerShape) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        Intrinsics.checkNotNull(cropCornerShape);
        cropOverlayView.setCropCornerShape(cropCornerShape);
    }

    public final boolean isAutoZoomEnabled() {
        return this.mAutoZoomEnabled;
    }

    public final void setAutoZoomEnabled(boolean z) {
        if (this.mAutoZoomEnabled != z) {
            this.mAutoZoomEnabled = z;
            handleCropWindowChanged(false, false);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.invalidate();
        }
    }

    public final void setMultiTouchEnabled(boolean z) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        if (cropOverlayView.setMultiTouchEnabled(z)) {
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public final void setCenterMoveEnabled(boolean z) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        if (cropOverlayView.setCenterMoveEnabled(z)) {
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public final int getMaxZoom() {
        return this.mMaxZoom;
    }

    public final void setMaxZoom(int i) {
        if (this.mMaxZoom != i && i > 0) {
            this.mMaxZoom = i;
            handleCropWindowChanged(false, false);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.invalidate();
        }
    }

    public final void setMinCropResultSize(int i, int i2) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setMinCropResultSize(i, i2);
    }

    public final void setMaxCropResultSize(int i, int i2) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setMaxCropResultSize(i, i2);
    }

    public final int getRotatedDegrees() {
        return this.mDegreesRotated;
    }

    public final void setRotatedDegrees(int i) {
        int i2 = this.mDegreesRotated;
        if (i2 != i) {
            rotateImage(i - i2);
        }
    }

    public final boolean isFixAspectRatio() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return cropOverlayView.isFixAspectRatio();
    }

    public final void setFixedAspectRatio(boolean z) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setFixedAspectRatio(z);
    }

    public final boolean isFlippedHorizontally() {
        return this.mFlipHorizontally;
    }

    public final void setFlippedHorizontally(boolean z) {
        if (this.mFlipHorizontally != z) {
            this.mFlipHorizontally = z;
            applyImageMatrix((float) getWidth(), (float) getHeight(), true, false);
        }
    }

    public final Uri getCustomOutputUri() {
        return this.customOutputUri;
    }

    public final void setCustomOutputUri(Uri uri) {
        this.customOutputUri = uri;
    }

    public final boolean isFlippedVertically() {
        return this.mFlipVertically;
    }

    public final void setFlippedVertically(boolean z) {
        if (this.mFlipVertically != z) {
            this.mFlipVertically = z;
            applyImageMatrix((float) getWidth(), (float) getHeight(), true, false);
        }
    }

    public final Guidelines getGuidelines() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return cropOverlayView.getGuidelines();
    }

    public final void setGuidelines(Guidelines guidelines) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        Intrinsics.checkNotNull(guidelines);
        cropOverlayView.setGuidelines(guidelines);
    }

    public final Pair<Integer, Integer> getAspectRatio() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return new Pair<>(Integer.valueOf(cropOverlayView.getAspectRatioX()), Integer.valueOf(this.mCropOverlayView.getAspectRatioY()));
    }

    public final void setAspectRatio(int i, int i2) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setAspectRatioX(i);
        this.mCropOverlayView.setAspectRatioY(i2);
        setFixedAspectRatio(true);
    }

    public final void clearAspectRatio() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setAspectRatioX(1);
        this.mCropOverlayView.setAspectRatioY(1);
        setFixedAspectRatio(false);
    }

    public final void setSnapRadius(float f) {
        if (f >= 0.0f) {
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.setSnapRadius(f);
        }
    }

    public final boolean isShowProgressBar() {
        return this.mShowProgressBar;
    }

    public final void setShowProgressBar(boolean z) {
        if (this.mShowProgressBar != z) {
            this.mShowProgressBar = z;
            setProgressBarVisibility();
        }
    }

    public final boolean isShowCropOverlay() {
        return this.mShowCropOverlay;
    }

    public final void setShowCropOverlay(boolean z) {
        if (this.mShowCropOverlay != z) {
            this.mShowCropOverlay = z;
            setCropOverlayVisibility();
        }
    }

    public final boolean isShowCropLabel() {
        return this.mShowCropLabel;
    }

    public final void setShowCropLabel(boolean z) {
        if (this.mShowCropLabel != z) {
            this.mShowCropLabel = z;
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            if (cropOverlayView != null) {
                cropOverlayView.setCropperTextLabelVisibility(z);
            }
        }
    }

    public final String getCropLabelText() {
        return this.mCropTextLabel;
    }

    public final void setCropLabelText(String str) {
        Intrinsics.checkNotNullParameter(str, "cropLabelText");
        this.mCropTextLabel = str;
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            cropOverlayView.setCropLabelText(str);
        }
    }

    public final float getCropLabelTextSize() {
        return this.mCropLabelTextSize;
    }

    public final void setCropLabelTextSize(float f) {
        this.mCropLabelTextSize = getCropLabelTextSize();
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            cropOverlayView.setCropLabelTextSize(f);
        }
    }

    public final int getCropLabelTextColor() {
        return this.mCropLabelTextColor;
    }

    public final void setCropLabelTextColor(int i) {
        this.mCropLabelTextColor = i;
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            cropOverlayView.setCropLabelTextColor(i);
        }
    }

    public final int getImageResource() {
        return this.mImageResource;
    }

    public final void setImageResource(int i) {
        if (i != 0) {
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.setInitialCropWindowRect((Rect) null);
            setBitmap(BitmapFactory.decodeResource(getResources(), i), i, (Uri) null, 1, 0);
        }
    }

    public final Rect getWholeImageRect() {
        int i = this.loadedSampleSize;
        Bitmap bitmap = this.originalBitmap;
        if (bitmap == null) {
            return null;
        }
        return new Rect(0, 0, bitmap.getWidth() * i, bitmap.getHeight() * i);
    }

    public final Rect getCropRect() {
        int i = this.loadedSampleSize;
        Bitmap bitmap = this.originalBitmap;
        if (bitmap == null) {
            return null;
        }
        float[] cropPoints = getCropPoints();
        int width = bitmap.getWidth() * i;
        int height = bitmap.getHeight() * i;
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return bitmapUtils.getRectFromPoints(cropPoints, width, height, cropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY());
    }

    public final void setCropRect(Rect rect) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setInitialCropWindowRect(rect);
    }

    public final RectF getCropWindowRect() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            return cropOverlayView.getCropWindowRect();
        }
        return null;
    }

    public final float[] getCropPoints() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        RectF cropWindowRect = cropOverlayView.getCropWindowRect();
        float[] fArr = {cropWindowRect.left, cropWindowRect.top, cropWindowRect.right, cropWindowRect.top, cropWindowRect.right, cropWindowRect.bottom, cropWindowRect.left, cropWindowRect.bottom};
        this.mImageMatrix.invert(this.mImageInverseMatrix);
        this.mImageInverseMatrix.mapPoints(fArr);
        float[] fArr2 = new float[8];
        for (int i = 0; i < 8; i++) {
            fArr2[i] = fArr[i] * ((float) this.loadedSampleSize);
        }
        return fArr2;
    }

    public final void resetCropRect() {
        this.mZoom = 1.0f;
        this.mZoomOffsetX = 0.0f;
        this.mZoomOffsetY = 0.0f;
        this.mDegreesRotated = this.mInitialDegreesRotated;
        this.mFlipHorizontally = false;
        this.mFlipVertically = false;
        applyImageMatrix((float) getWidth(), (float) getHeight(), false, false);
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.resetCropWindowRect();
    }

    public final Bitmap getCroppedImage() {
        return getCroppedImage(0, 0, RequestSizeOptions.NONE);
    }

    public final Bitmap getCroppedImage(int i, int i2) {
        return getCroppedImage(i, i2, RequestSizeOptions.RESIZE_INSIDE);
    }

    public final Bitmap getCroppedImage(int i, int i2, RequestSizeOptions requestSizeOptions) {
        Bitmap bitmap;
        RequestSizeOptions requestSizeOptions2 = requestSizeOptions;
        Intrinsics.checkNotNullParameter(requestSizeOptions2, "options");
        if (this.originalBitmap == null) {
            return null;
        }
        int i3 = 0;
        int i4 = requestSizeOptions2 != RequestSizeOptions.NONE ? i : 0;
        if (requestSizeOptions2 != RequestSizeOptions.NONE) {
            i3 = i2;
        }
        if (this.imageUri == null || (this.loadedSampleSize <= 1 && requestSizeOptions2 != RequestSizeOptions.SAMPLING)) {
            BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
            Bitmap bitmap2 = this.originalBitmap;
            float[] cropPoints = getCropPoints();
            int i5 = this.mDegreesRotated;
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            bitmap = bitmapUtils.cropBitmapObjectHandleOOM(bitmap2, cropPoints, i5, cropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), this.mFlipHorizontally, this.mFlipVertically).getBitmap();
        } else {
            Bitmap bitmap3 = this.originalBitmap;
            Intrinsics.checkNotNull(bitmap3);
            int width = bitmap3.getWidth() * this.loadedSampleSize;
            Bitmap bitmap4 = this.originalBitmap;
            Intrinsics.checkNotNull(bitmap4);
            int height = bitmap4.getHeight() * this.loadedSampleSize;
            BitmapUtils bitmapUtils2 = BitmapUtils.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            Uri uri = this.imageUri;
            float[] cropPoints2 = getCropPoints();
            int i6 = this.mDegreesRotated;
            CropOverlayView cropOverlayView2 = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView2);
            bitmap = bitmapUtils2.cropBitmap(context, uri, cropPoints2, i6, width, height, cropOverlayView2.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), i4, i3, this.mFlipHorizontally, this.mFlipVertically).getBitmap();
        }
        return BitmapUtils.INSTANCE.resizeBitmap(bitmap, i4, i3, requestSizeOptions2);
    }

    public static /* synthetic */ void croppedImageAsync$default(CropImageView cropImageView, Bitmap.CompressFormat compressFormat, int i, int i2, int i3, RequestSizeOptions requestSizeOptions, Uri uri, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            compressFormat = Bitmap.CompressFormat.JPEG;
        }
        if ((i4 & 2) != 0) {
            i = 90;
        }
        int i5 = i;
        int i6 = 0;
        int i7 = (i4 & 4) != 0 ? 0 : i2;
        if ((i4 & 8) == 0) {
            i6 = i3;
        }
        if ((i4 & 16) != 0) {
            requestSizeOptions = RequestSizeOptions.RESIZE_INSIDE;
        }
        RequestSizeOptions requestSizeOptions2 = requestSizeOptions;
        if ((i4 & 32) != 0) {
            uri = null;
        }
        cropImageView.croppedImageAsync(compressFormat, i5, i7, i6, requestSizeOptions2, uri);
    }

    public final void croppedImageAsync(Bitmap.CompressFormat compressFormat, int i, int i2, int i3, RequestSizeOptions requestSizeOptions, Uri uri) {
        Intrinsics.checkNotNullParameter(compressFormat, "saveCompressFormat");
        Intrinsics.checkNotNullParameter(requestSizeOptions, "options");
        if (this.mOnCropImageCompleteListener != null) {
            startCropWorkerTask(i2, i3, requestSizeOptions, compressFormat, i, uri);
            return;
        }
        throw new IllegalArgumentException("mOnCropImageCompleteListener is not set".toString());
    }

    public final void setOnSetCropOverlayReleasedListener(OnSetCropOverlayReleasedListener onSetCropOverlayReleasedListener) {
        this.mOnCropOverlayReleasedListener = onSetCropOverlayReleasedListener;
    }

    public final void setOnSetCropOverlayMovedListener(OnSetCropOverlayMovedListener onSetCropOverlayMovedListener) {
        this.mOnSetCropOverlayMovedListener = onSetCropOverlayMovedListener;
    }

    public final void setOnCropWindowChangedListener(OnSetCropWindowChangeListener onSetCropWindowChangeListener) {
        this.mOnSetCropWindowChangeListener = onSetCropWindowChangeListener;
    }

    public final void setOnSetImageUriCompleteListener(OnSetImageUriCompleteListener onSetImageUriCompleteListener) {
        this.mOnSetImageUriCompleteListener = onSetImageUriCompleteListener;
    }

    public final void setOnCropImageCompleteListener(OnCropImageCompleteListener onCropImageCompleteListener) {
        this.mOnCropImageCompleteListener = onCropImageCompleteListener;
    }

    public final void setImageBitmap(Bitmap bitmap) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setInitialCropWindowRect((Rect) null);
        setBitmap(bitmap, 0, (Uri) null, 1, 0);
    }

    public final void setImageBitmap(Bitmap bitmap, ExifInterface exifInterface) {
        Bitmap bitmap2;
        int i;
        if (bitmap == null || exifInterface == null) {
            i = 0;
            bitmap2 = bitmap;
        } else {
            BitmapUtils.RotateBitmapResult orientateBitmapByExif = BitmapUtils.INSTANCE.orientateBitmapByExif(bitmap, exifInterface);
            Bitmap bitmap3 = orientateBitmapByExif.getBitmap();
            i = orientateBitmapByExif.getDegrees();
            this.mFlipHorizontally = orientateBitmapByExif.getFlipHorizontally();
            this.mFlipVertically = orientateBitmapByExif.getFlipVertically();
            this.mInitialDegreesRotated = orientateBitmapByExif.getDegrees();
            bitmap2 = bitmap3;
        }
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setInitialCropWindowRect((Rect) null);
        setBitmap(bitmap2, 0, (Uri) null, 1, i);
    }

    public final void setImageUriAsync(Uri uri) {
        BitmapLoadingWorkerJob bitmapLoadingWorkerJob2;
        if (uri != null) {
            WeakReference<BitmapLoadingWorkerJob> weakReference = this.bitmapLoadingWorkerJob;
            if (weakReference != null) {
                Intrinsics.checkNotNull(weakReference);
                bitmapLoadingWorkerJob2 = (BitmapLoadingWorkerJob) weakReference.get();
            } else {
                bitmapLoadingWorkerJob2 = null;
            }
            if (bitmapLoadingWorkerJob2 != null) {
                bitmapLoadingWorkerJob2.cancel();
            }
            clearImageInt();
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.setInitialCropWindowRect((Rect) null);
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            WeakReference<BitmapLoadingWorkerJob> weakReference2 = new WeakReference<>(new BitmapLoadingWorkerJob(context, this, uri));
            this.bitmapLoadingWorkerJob = weakReference2;
            Intrinsics.checkNotNull(weakReference2);
            Object obj = weakReference2.get();
            Intrinsics.checkNotNull(obj);
            ((BitmapLoadingWorkerJob) obj).start();
            setProgressBarVisibility();
        }
    }

    public final void clearImage() {
        clearImageInt();
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            cropOverlayView.setInitialCropWindowRect((Rect) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        if ((216 <= r1 && r1 < 305) != false) goto L_0x0038;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void rotateImage(int r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            android.graphics.Bitmap r2 = r0.originalBitmap
            if (r2 == 0) goto L_0x01e5
            if (r1 >= 0) goto L_0x000f
            int r1 = r1 % 360
            int r1 = r1 + 360
            goto L_0x0011
        L_0x000f:
            int r1 = r1 % 360
        L_0x0011:
            com.canhub.cropper.CropOverlayView r2 = r0.mCropOverlayView
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            boolean r2 = r2.isFixAspectRatio()
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L_0x003a
            r2 = 46
            if (r2 > r1) goto L_0x0028
            r2 = 135(0x87, float:1.89E-43)
            if (r1 >= r2) goto L_0x0028
            r2 = r3
            goto L_0x0029
        L_0x0028:
            r2 = r4
        L_0x0029:
            if (r2 != 0) goto L_0x0038
            r2 = 216(0xd8, float:3.03E-43)
            if (r2 > r1) goto L_0x0035
            r2 = 305(0x131, float:4.27E-43)
            if (r1 >= r2) goto L_0x0035
            r2 = r3
            goto L_0x0036
        L_0x0035:
            r2 = r4
        L_0x0036:
            if (r2 == 0) goto L_0x003a
        L_0x0038:
            r2 = r3
            goto L_0x003b
        L_0x003a:
            r2 = r4
        L_0x003b:
            com.canhub.cropper.BitmapUtils r5 = com.canhub.cropper.BitmapUtils.INSTANCE
            android.graphics.RectF r5 = r5.getRECT()
            com.canhub.cropper.CropOverlayView r6 = r0.mCropOverlayView
            android.graphics.RectF r6 = r6.getCropWindowRect()
            r5.set(r6)
            com.canhub.cropper.BitmapUtils r5 = com.canhub.cropper.BitmapUtils.INSTANCE
            android.graphics.RectF r5 = r5.getRECT()
            if (r2 == 0) goto L_0x0057
            float r5 = r5.height()
            goto L_0x005b
        L_0x0057:
            float r5 = r5.width()
        L_0x005b:
            r6 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r6
            com.canhub.cropper.BitmapUtils r7 = com.canhub.cropper.BitmapUtils.INSTANCE
            android.graphics.RectF r7 = r7.getRECT()
            if (r2 == 0) goto L_0x006b
            float r7 = r7.width()
            goto L_0x006f
        L_0x006b:
            float r7 = r7.height()
        L_0x006f:
            float r7 = r7 / r6
            if (r2 == 0) goto L_0x007a
            boolean r2 = r0.mFlipHorizontally
            boolean r6 = r0.mFlipVertically
            r0.mFlipHorizontally = r6
            r0.mFlipVertically = r2
        L_0x007a:
            android.graphics.Matrix r2 = r0.mImageMatrix
            android.graphics.Matrix r6 = r0.mImageInverseMatrix
            r2.invert(r6)
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS()
            com.canhub.cropper.BitmapUtils r6 = com.canhub.cropper.BitmapUtils.INSTANCE
            android.graphics.RectF r6 = r6.getRECT()
            float r6 = r6.centerX()
            r2[r4] = r6
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS()
            com.canhub.cropper.BitmapUtils r6 = com.canhub.cropper.BitmapUtils.INSTANCE
            android.graphics.RectF r6 = r6.getRECT()
            float r6 = r6.centerY()
            r2[r3] = r6
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS()
            r6 = 2
            r8 = 0
            r2[r6] = r8
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS()
            r9 = 3
            r2[r9] = r8
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS()
            r10 = 4
            r11 = 1065353216(0x3f800000, float:1.0)
            r2[r10] = r11
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS()
            r12 = 5
            r2[r12] = r8
            android.graphics.Matrix r2 = r0.mImageInverseMatrix
            com.canhub.cropper.BitmapUtils r8 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r8 = r8.getPOINTS()
            r2.mapPoints(r8)
            int r2 = r0.mDegreesRotated
            int r2 = r2 + r1
            int r2 = r2 % 360
            r0.mDegreesRotated = r2
            int r1 = r16.getWidth()
            float r1 = (float) r1
            int r2 = r16.getHeight()
            float r2 = (float) r2
            r0.applyImageMatrix(r1, r2, r3, r4)
            android.graphics.Matrix r1 = r0.mImageMatrix
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS2()
            com.canhub.cropper.BitmapUtils r8 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r8 = r8.getPOINTS()
            r1.mapPoints(r2, r8)
            float r1 = r0.mZoom
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS2()
            r2 = r2[r10]
            com.canhub.cropper.BitmapUtils r8 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r8 = r8.getPOINTS2()
            r8 = r8[r6]
            float r2 = r2 - r8
            double r13 = (double) r2
            r17 = r7
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r13 = java.lang.Math.pow(r13, r6)
            com.canhub.cropper.BitmapUtils r8 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r8 = r8.getPOINTS2()
            r8 = r8[r12]
            com.canhub.cropper.BitmapUtils r15 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r15 = r15.getPOINTS2()
            r15 = r15[r9]
            float r8 = r8 - r15
            double r9 = (double) r8
            double r8 = java.lang.Math.pow(r9, r6)
            double r13 = r13 + r8
            double r8 = java.lang.Math.sqrt(r13)
            float r8 = (float) r8
            float r1 = r1 / r8
            r0.mZoom = r1
            float r1 = java.lang.Math.max(r1, r11)
            r0.mZoom = r1
            int r1 = r16.getWidth()
            float r1 = (float) r1
            int r8 = r16.getHeight()
            float r8 = (float) r8
            r0.applyImageMatrix(r1, r8, r3, r4)
            android.graphics.Matrix r1 = r0.mImageMatrix
            com.canhub.cropper.BitmapUtils r8 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r8 = r8.getPOINTS2()
            com.canhub.cropper.BitmapUtils r9 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r9 = r9.getPOINTS()
            r1.mapPoints(r8, r9)
            com.canhub.cropper.BitmapUtils r1 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r1 = r1.getPOINTS2()
            r8 = 4
            r1 = r1[r8]
            com.canhub.cropper.BitmapUtils r8 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r8 = r8.getPOINTS2()
            r2 = 2
            r2 = r8[r2]
            float r1 = r1 - r2
            double r1 = (double) r1
            double r1 = java.lang.Math.pow(r1, r6)
            com.canhub.cropper.BitmapUtils r8 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r8 = r8.getPOINTS2()
            r8 = r8[r12]
            com.canhub.cropper.BitmapUtils r9 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r9 = r9.getPOINTS2()
            r10 = 3
            r9 = r9[r10]
            float r8 = r8 - r9
            double r8 = (double) r8
            double r6 = java.lang.Math.pow(r8, r6)
            double r1 = r1 + r6
            double r1 = java.lang.Math.sqrt(r1)
            float r1 = (float) r1
            float r5 = r5 * r1
            float r7 = r17 * r1
            com.canhub.cropper.BitmapUtils r1 = com.canhub.cropper.BitmapUtils.INSTANCE
            android.graphics.RectF r1 = r1.getRECT()
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r2 = r2.getPOINTS2()
            r2 = r2[r4]
            float r2 = r2 - r5
            com.canhub.cropper.BitmapUtils r6 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r6 = r6.getPOINTS2()
            r6 = r6[r3]
            float r6 = r6 - r7
            com.canhub.cropper.BitmapUtils r8 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r8 = r8.getPOINTS2()
            r8 = r8[r4]
            float r8 = r8 + r5
            com.canhub.cropper.BitmapUtils r5 = com.canhub.cropper.BitmapUtils.INSTANCE
            float[] r5 = r5.getPOINTS2()
            r5 = r5[r3]
            float r5 = r5 + r7
            r1.set(r2, r6, r8, r5)
            com.canhub.cropper.CropOverlayView r1 = r0.mCropOverlayView
            r1.resetCropOverlayView()
            com.canhub.cropper.CropOverlayView r1 = r0.mCropOverlayView
            com.canhub.cropper.BitmapUtils r2 = com.canhub.cropper.BitmapUtils.INSTANCE
            android.graphics.RectF r2 = r2.getRECT()
            r1.setCropWindowRect(r2)
            int r1 = r16.getWidth()
            float r1 = (float) r1
            int r2 = r16.getHeight()
            float r2 = (float) r2
            r0.applyImageMatrix(r1, r2, r3, r4)
            r0.handleCropWindowChanged(r4, r4)
            com.canhub.cropper.CropOverlayView r1 = r0.mCropOverlayView
            r1.fixCurrentCropWindowRect()
        L_0x01e5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.CropImageView.rotateImage(int):void");
    }

    public final void flipImageHorizontally() {
        this.mFlipHorizontally = !this.mFlipHorizontally;
        applyImageMatrix((float) getWidth(), (float) getHeight(), true, false);
    }

    public final void flipImageVertically() {
        this.mFlipVertically = !this.mFlipVertically;
        applyImageMatrix((float) getWidth(), (float) getHeight(), true, false);
    }

    public final void onSetImageUriAsyncComplete(BitmapLoadingWorkerJob.Result result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.bitmapLoadingWorkerJob = null;
        setProgressBarVisibility();
        if (result.getError() == null) {
            this.mInitialDegreesRotated = result.getDegreesRotated();
            this.mFlipHorizontally = result.getFlipHorizontally();
            this.mFlipVertically = result.getFlipVertically();
            setBitmap(result.getBitmap(), 0, result.getUriContent(), result.getLoadSampleSize(), result.getDegreesRotated());
        }
        OnSetImageUriCompleteListener onSetImageUriCompleteListener = this.mOnSetImageUriCompleteListener;
        if (onSetImageUriCompleteListener != null) {
            onSetImageUriCompleteListener.onSetImageUriComplete(this, result.getUriContent(), result.getError());
        }
    }

    public final void onImageCroppingAsyncComplete(BitmapCroppingWorkerJob.Result result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.bitmapCroppingWorkerJob = null;
        setProgressBarVisibility();
        OnCropImageCompleteListener onCropImageCompleteListener = this.mOnCropImageCompleteListener;
        if (onCropImageCompleteListener != null) {
            onCropImageCompleteListener.onCropImageComplete(this, new CropResult(this.originalBitmap, this.imageUri, result.getBitmap(), result.getUri(), result.getError(), getCropPoints(), getCropRect(), getWholeImageRect(), getRotatedDegrees(), result.getSampleSize()));
        }
    }

    private final void setBitmap(Bitmap bitmap, int i, Uri uri, int i2, int i3) {
        Bitmap bitmap2 = this.originalBitmap;
        if (bitmap2 == null || !Intrinsics.areEqual((Object) bitmap2, (Object) bitmap)) {
            clearImageInt();
            this.originalBitmap = bitmap;
            this.imageView.setImageBitmap(bitmap);
            this.imageUri = uri;
            this.mImageResource = i;
            this.loadedSampleSize = i2;
            this.mDegreesRotated = i3;
            applyImageMatrix((float) getWidth(), (float) getHeight(), true, false);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            if (cropOverlayView != null) {
                cropOverlayView.resetCropOverlayView();
                setCropOverlayVisibility();
            }
        }
    }

    private final void clearImageInt() {
        Bitmap bitmap = this.originalBitmap;
        if (bitmap != null && (this.mImageResource > 0 || this.imageUri != null)) {
            Intrinsics.checkNotNull(bitmap);
            bitmap.recycle();
        }
        this.originalBitmap = null;
        this.mImageResource = 0;
        this.imageUri = null;
        this.loadedSampleSize = 1;
        this.mDegreesRotated = 0;
        this.mZoom = 1.0f;
        this.mZoomOffsetX = 0.0f;
        this.mZoomOffsetY = 0.0f;
        this.mImageMatrix.reset();
        this.mRestoreCropWindowRect = null;
        this.mRestoreDegreesRotated = 0;
        this.imageView.setImageBitmap((Bitmap) null);
        setCropOverlayVisibility();
    }

    public final void startCropWorkerTask(int i, int i2, RequestSizeOptions requestSizeOptions, Bitmap.CompressFormat compressFormat, int i3, Uri uri) {
        BitmapCroppingWorkerJob bitmapCroppingWorkerJob2;
        Pair pair;
        Uri uri2;
        RequestSizeOptions requestSizeOptions2 = requestSizeOptions;
        Intrinsics.checkNotNullParameter(requestSizeOptions2, "options");
        Intrinsics.checkNotNullParameter(compressFormat, "saveCompressFormat");
        Bitmap bitmap = this.originalBitmap;
        if (bitmap != null) {
            WeakReference<BitmapCroppingWorkerJob> weakReference = this.bitmapCroppingWorkerJob;
            if (weakReference != null) {
                Intrinsics.checkNotNull(weakReference);
                bitmapCroppingWorkerJob2 = (BitmapCroppingWorkerJob) weakReference.get();
            } else {
                bitmapCroppingWorkerJob2 = null;
            }
            if (bitmapCroppingWorkerJob2 != null) {
                bitmapCroppingWorkerJob2.cancel();
            }
            if (this.loadedSampleSize > 1 || requestSizeOptions2 == RequestSizeOptions.SAMPLING) {
                pair = new Pair(Integer.valueOf(bitmap.getWidth() * this.loadedSampleSize), Integer.valueOf(bitmap.getHeight() * this.loadedSampleSize));
            } else {
                pair = new Pair(0, 0);
            }
            Integer num = (Integer) pair.first;
            Integer num2 = (Integer) pair.second;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            WeakReference weakReference2 = new WeakReference(this);
            Uri uri3 = this.imageUri;
            float[] cropPoints = getCropPoints();
            int i4 = this.mDegreesRotated;
            Intrinsics.checkNotNullExpressionValue(num, "orgWidth");
            int intValue = num.intValue();
            Intrinsics.checkNotNullExpressionValue(num2, "orgHeight");
            int intValue2 = num2.intValue();
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            boolean isFixAspectRatio = cropOverlayView.isFixAspectRatio();
            int aspectRatioX = this.mCropOverlayView.getAspectRatioX();
            int aspectRatioY = this.mCropOverlayView.getAspectRatioY();
            int i5 = requestSizeOptions2 != RequestSizeOptions.NONE ? i : 0;
            int i6 = requestSizeOptions2 != RequestSizeOptions.NONE ? i2 : 0;
            boolean z = this.mFlipHorizontally;
            boolean z2 = this.mFlipVertically;
            if (uri == null) {
                uri2 = this.customOutputUri;
            } else {
                uri2 = uri;
            }
            BitmapCroppingWorkerJob bitmapCroppingWorkerJob3 = r1;
            BitmapCroppingWorkerJob bitmapCroppingWorkerJob4 = new BitmapCroppingWorkerJob(context, weakReference2, uri3, bitmap, cropPoints, i4, intValue, intValue2, isFixAspectRatio, aspectRatioX, aspectRatioY, i5, i6, z, z2, requestSizeOptions, compressFormat, i3, uri2);
            WeakReference<BitmapCroppingWorkerJob> weakReference3 = new WeakReference<>(bitmapCroppingWorkerJob3);
            this.bitmapCroppingWorkerJob = weakReference3;
            Intrinsics.checkNotNull(weakReference3);
            Object obj = weakReference3.get();
            Intrinsics.checkNotNull(obj);
            ((BitmapCroppingWorkerJob) obj).start();
            setProgressBarVisibility();
        }
    }

    public Parcelable onSaveInstanceState() {
        Uri uri;
        if (this.imageUri == null && this.originalBitmap == null && this.mImageResource < 1) {
            return super.onSaveInstanceState();
        }
        Bundle bundle = new Bundle();
        if (!this.isSaveBitmapToInstanceState || this.imageUri != null || this.mImageResource >= 1) {
            uri = this.imageUri;
        } else {
            BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            uri = bitmapUtils.writeTempStateStoreBitmap(context, this.originalBitmap, this.customOutputUri);
        }
        if (!(uri == null || this.originalBitmap == null)) {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
            BitmapUtils.INSTANCE.setMStateBitmap(new Pair(uuid, new WeakReference(this.originalBitmap)));
            bundle.putString("LOADED_IMAGE_STATE_BITMAP_KEY", uuid);
        }
        WeakReference<BitmapLoadingWorkerJob> weakReference = this.bitmapLoadingWorkerJob;
        if (weakReference != null) {
            Intrinsics.checkNotNull(weakReference);
            BitmapLoadingWorkerJob bitmapLoadingWorkerJob2 = (BitmapLoadingWorkerJob) weakReference.get();
            if (bitmapLoadingWorkerJob2 != null) {
                bundle.putParcelable("LOADING_IMAGE_URI", bitmapLoadingWorkerJob2.getUri());
            }
        }
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putParcelable("LOADED_IMAGE_URI", uri);
        bundle.putInt("LOADED_IMAGE_RESOURCE", this.mImageResource);
        bundle.putInt("LOADED_SAMPLE_SIZE", this.loadedSampleSize);
        bundle.putInt("DEGREES_ROTATED", this.mDegreesRotated);
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        bundle.putParcelable("INITIAL_CROP_RECT", cropOverlayView.getInitialCropWindowRect());
        BitmapUtils.INSTANCE.getRECT().set(this.mCropOverlayView.getCropWindowRect());
        this.mImageMatrix.invert(this.mImageInverseMatrix);
        this.mImageInverseMatrix.mapRect(BitmapUtils.INSTANCE.getRECT());
        bundle.putParcelable("CROP_WINDOW_RECT", BitmapUtils.INSTANCE.getRECT());
        CropShape cropShape = this.mCropOverlayView.getCropShape();
        Intrinsics.checkNotNull(cropShape);
        bundle.putString("CROP_SHAPE", cropShape.name());
        bundle.putBoolean("CROP_AUTO_ZOOM_ENABLED", this.mAutoZoomEnabled);
        bundle.putInt("CROP_MAX_ZOOM", this.mMaxZoom);
        bundle.putBoolean("CROP_FLIP_HORIZONTALLY", this.mFlipHorizontally);
        bundle.putBoolean("CROP_FLIP_VERTICALLY", this.mFlipVertically);
        bundle.putBoolean("SHOW_CROP_LABEL", this.mShowCropLabel);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(parcelable, "state");
        if (parcelable instanceof Bundle) {
            if (this.bitmapLoadingWorkerJob == null && this.imageUri == null && this.originalBitmap == null && this.mImageResource == 0) {
                Bundle bundle = (Bundle) parcelable;
                Parcelable parcelable2 = bundle.getParcelable("LOADED_IMAGE_URI");
                if (parcelable2 != null) {
                    String string = bundle.getString("LOADED_IMAGE_STATE_BITMAP_KEY");
                    if (string != null) {
                        Pair<String, WeakReference<Bitmap>> mStateBitmap = BitmapUtils.INSTANCE.getMStateBitmap();
                        if (mStateBitmap != null) {
                            bitmap = Intrinsics.areEqual(mStateBitmap.first, (Object) string) ? (Bitmap) ((WeakReference) mStateBitmap.second).get() : null;
                        } else {
                            bitmap = null;
                        }
                        BitmapUtils.INSTANCE.setMStateBitmap((Pair<String, WeakReference<Bitmap>>) null);
                        if (bitmap != null && !bitmap.isRecycled()) {
                            setBitmap(bitmap, 0, (Uri) parcelable2, bundle.getInt("LOADED_SAMPLE_SIZE"), 0);
                        }
                    }
                    if (this.imageUri == null) {
                        setImageUriAsync((Uri) parcelable2);
                        Unit unit = Unit.INSTANCE;
                    }
                } else {
                    int i = bundle.getInt("LOADED_IMAGE_RESOURCE");
                    if (i > 0) {
                        setImageResource(i);
                    } else {
                        Uri uri = (Uri) bundle.getParcelable("LOADING_IMAGE_URI");
                        if (uri != null) {
                            setImageUriAsync(uri);
                        }
                    }
                }
                int i2 = bundle.getInt("DEGREES_ROTATED");
                this.mRestoreDegreesRotated = i2;
                this.mDegreesRotated = i2;
                Rect rect = (Rect) bundle.getParcelable("INITIAL_CROP_RECT");
                if (rect != null && (rect.width() > 0 || rect.height() > 0)) {
                    CropOverlayView cropOverlayView = this.mCropOverlayView;
                    Intrinsics.checkNotNull(cropOverlayView);
                    cropOverlayView.setInitialCropWindowRect(rect);
                }
                RectF rectF = (RectF) bundle.getParcelable("CROP_WINDOW_RECT");
                if (rectF != null && (rectF.width() > 0.0f || rectF.height() > 0.0f)) {
                    this.mRestoreCropWindowRect = rectF;
                }
                CropOverlayView cropOverlayView2 = this.mCropOverlayView;
                Intrinsics.checkNotNull(cropOverlayView2);
                String string2 = bundle.getString("CROP_SHAPE");
                Intrinsics.checkNotNull(string2);
                cropOverlayView2.setCropShape(CropShape.valueOf(string2));
                this.mAutoZoomEnabled = bundle.getBoolean("CROP_AUTO_ZOOM_ENABLED");
                this.mMaxZoom = bundle.getInt("CROP_MAX_ZOOM");
                this.mFlipHorizontally = bundle.getBoolean("CROP_FLIP_HORIZONTALLY");
                this.mFlipVertically = bundle.getBoolean("CROP_FLIP_VERTICALLY");
                boolean z = bundle.getBoolean("SHOW_CROP_LABEL");
                this.mShowCropLabel = z;
                this.mCropOverlayView.setCropperTextLabelVisibility(z);
            }
            super.onRestoreInstanceState(((Bundle) parcelable).getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        Bitmap bitmap = this.originalBitmap;
        if (bitmap != null) {
            if (size2 == 0) {
                size2 = bitmap.getHeight();
            }
            double width = size < bitmap.getWidth() ? ((double) size) / ((double) bitmap.getWidth()) : Double.POSITIVE_INFINITY;
            double height = size2 < bitmap.getHeight() ? ((double) size2) / ((double) bitmap.getHeight()) : Double.POSITIVE_INFINITY;
            boolean z = true;
            if (width == Double.POSITIVE_INFINITY) {
                if (height != Double.POSITIVE_INFINITY) {
                    z = false;
                }
                if (z) {
                    i3 = bitmap.getWidth();
                    i4 = bitmap.getHeight();
                    Companion companion = Companion;
                    int access$getOnMeasureSpec = companion.getOnMeasureSpec(mode, size, i3);
                    int access$getOnMeasureSpec2 = companion.getOnMeasureSpec(mode2, size2, i4);
                    this.mLayoutWidth = access$getOnMeasureSpec;
                    this.mLayoutHeight = access$getOnMeasureSpec2;
                    setMeasuredDimension(access$getOnMeasureSpec, access$getOnMeasureSpec2);
                    return;
                }
            }
            if (width <= height) {
                i4 = (int) (((double) bitmap.getHeight()) * width);
                i3 = size;
            } else {
                i3 = (int) (((double) bitmap.getWidth()) * height);
                i4 = size2;
            }
            Companion companion2 = Companion;
            int access$getOnMeasureSpec3 = companion2.getOnMeasureSpec(mode, size, i3);
            int access$getOnMeasureSpec22 = companion2.getOnMeasureSpec(mode2, size2, i4);
            this.mLayoutWidth = access$getOnMeasureSpec3;
            this.mLayoutHeight = access$getOnMeasureSpec22;
            setMeasuredDimension(access$getOnMeasureSpec3, access$getOnMeasureSpec22);
            return;
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mLayoutWidth <= 0 || this.mLayoutHeight <= 0) {
            updateImageBounds(true);
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = this.mLayoutWidth;
        layoutParams.height = this.mLayoutHeight;
        setLayoutParams(layoutParams);
        if (this.originalBitmap != null) {
            float f = (float) (i3 - i);
            float f2 = (float) (i4 - i2);
            applyImageMatrix(f, f2, true, false);
            RectF rectF = this.mRestoreCropWindowRect;
            if (rectF != null) {
                int i5 = this.mRestoreDegreesRotated;
                if (i5 != this.mInitialDegreesRotated) {
                    this.mDegreesRotated = i5;
                    applyImageMatrix(f, f2, true, false);
                    this.mRestoreDegreesRotated = 0;
                }
                this.mImageMatrix.mapRect(this.mRestoreCropWindowRect);
                CropOverlayView cropOverlayView = this.mCropOverlayView;
                if (cropOverlayView != null) {
                    cropOverlayView.setCropWindowRect(rectF);
                }
                handleCropWindowChanged(false, false);
                CropOverlayView cropOverlayView2 = this.mCropOverlayView;
                if (cropOverlayView2 != null) {
                    cropOverlayView2.fixCurrentCropWindowRect();
                }
                this.mRestoreCropWindowRect = null;
            } else if (this.mSizeChanged) {
                this.mSizeChanged = false;
                handleCropWindowChanged(false, false);
            }
        } else {
            updateImageBounds(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mSizeChanged = i3 > 0 && i4 > 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void handleCropWindowChanged(boolean r13, boolean r14) {
        /*
            r12 = this;
            int r0 = r12.getWidth()
            int r1 = r12.getHeight()
            android.graphics.Bitmap r2 = r12.originalBitmap
            if (r2 == 0) goto L_0x010c
            if (r0 <= 0) goto L_0x010c
            if (r1 <= 0) goto L_0x010c
            com.canhub.cropper.CropOverlayView r2 = r12.mCropOverlayView
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            android.graphics.RectF r2 = r2.getCropWindowRect()
            r3 = 0
            r4 = 0
            if (r13 == 0) goto L_0x003e
            float r14 = r2.left
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 < 0) goto L_0x0037
            float r14 = r2.top
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 < 0) goto L_0x0037
            float r14 = r2.right
            float r4 = (float) r0
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 > 0) goto L_0x0037
            float r14 = r2.bottom
            float r2 = (float) r1
            int r14 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r14 <= 0) goto L_0x0100
        L_0x0037:
            float r14 = (float) r0
            float r0 = (float) r1
            r12.applyImageMatrix(r14, r0, r3, r3)
            goto L_0x0100
        L_0x003e:
            boolean r5 = r12.mAutoZoomEnabled
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r5 != 0) goto L_0x004a
            float r5 = r12.mZoom
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L_0x0100
        L_0x004a:
            float r5 = r12.mZoom
            int r7 = r12.mMaxZoom
            float r7 = (float) r7
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L_0x008b
            float r5 = r2.width()
            float r7 = (float) r0
            r8 = 1056964608(0x3f000000, float:0.5)
            float r9 = r7 * r8
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 >= 0) goto L_0x008b
            float r5 = r2.height()
            float r9 = (float) r1
            float r8 = r8 * r9
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 >= 0) goto L_0x008b
            int r5 = r12.mMaxZoom
            float r5 = (float) r5
            float r8 = r2.width()
            float r10 = r12.mZoom
            float r8 = r8 / r10
            r10 = 1059313418(0x3f23d70a, float:0.64)
            float r8 = r8 / r10
            float r7 = r7 / r8
            float r8 = r2.height()
            float r11 = r12.mZoom
            float r8 = r8 / r11
            float r8 = r8 / r10
            float r9 = r9 / r8
            float r7 = java.lang.Math.min(r7, r9)
            float r5 = java.lang.Math.min(r5, r7)
            goto L_0x008c
        L_0x008b:
            r5 = r4
        L_0x008c:
            float r7 = r12.mZoom
            int r7 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r7 <= 0) goto L_0x00c8
            float r7 = r2.width()
            float r8 = (float) r0
            r9 = 1059481190(0x3f266666, float:0.65)
            float r10 = r8 * r9
            int r7 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r7 > 0) goto L_0x00aa
            float r7 = r2.height()
            float r10 = (float) r1
            float r10 = r10 * r9
            int r7 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r7 <= 0) goto L_0x00c8
        L_0x00aa:
            float r5 = r2.width()
            float r7 = r12.mZoom
            float r5 = r5 / r7
            r7 = 1057132380(0x3f028f5c, float:0.51)
            float r5 = r5 / r7
            float r8 = r8 / r5
            float r5 = (float) r1
            float r2 = r2.height()
            float r9 = r12.mZoom
            float r2 = r2 / r9
            float r2 = r2 / r7
            float r5 = r5 / r2
            float r2 = java.lang.Math.min(r8, r5)
            float r5 = java.lang.Math.max(r6, r2)
        L_0x00c8:
            boolean r2 = r12.mAutoZoomEnabled
            if (r2 != 0) goto L_0x00cd
            goto L_0x00ce
        L_0x00cd:
            r6 = r5
        L_0x00ce:
            int r2 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x0100
            float r2 = r12.mZoom
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            r4 = 1
            if (r2 != 0) goto L_0x00da
            r3 = r4
        L_0x00da:
            if (r3 != 0) goto L_0x0100
            if (r14 == 0) goto L_0x00f9
            com.canhub.cropper.CropImageAnimation r2 = r12.mAnimation
            if (r2 != 0) goto L_0x00ed
            com.canhub.cropper.CropImageAnimation r2 = new com.canhub.cropper.CropImageAnimation
            android.widget.ImageView r3 = r12.imageView
            com.canhub.cropper.CropOverlayView r5 = r12.mCropOverlayView
            r2.<init>(r3, r5)
            r12.mAnimation = r2
        L_0x00ed:
            com.canhub.cropper.CropImageAnimation r2 = r12.mAnimation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            float[] r3 = r12.mImagePoints
            android.graphics.Matrix r5 = r12.mImageMatrix
            r2.setStartState(r3, r5)
        L_0x00f9:
            r12.mZoom = r6
            float r0 = (float) r0
            float r1 = (float) r1
            r12.applyImageMatrix(r0, r1, r4, r14)
        L_0x0100:
            com.canhub.cropper.CropImageView$OnSetCropWindowChangeListener r14 = r12.mOnSetCropWindowChangeListener
            if (r14 == 0) goto L_0x010c
            if (r13 != 0) goto L_0x010c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            r14.onCropWindowChanged()
        L_0x010c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.CropImageView.handleCropWindowChanged(boolean, boolean):void");
    }

    private final void applyImageMatrix(float f, float f2, boolean z, boolean z2) {
        float f3;
        Bitmap bitmap = this.originalBitmap;
        if (bitmap != null) {
            float f4 = 0.0f;
            if (f > 0.0f && f2 > 0.0f) {
                this.mImageMatrix.invert(this.mImageInverseMatrix);
                CropOverlayView cropOverlayView = this.mCropOverlayView;
                Intrinsics.checkNotNull(cropOverlayView);
                RectF cropWindowRect = cropOverlayView.getCropWindowRect();
                this.mImageInverseMatrix.mapRect(cropWindowRect);
                this.mImageMatrix.reset();
                float f5 = (float) 2;
                this.mImageMatrix.postTranslate((f - ((float) bitmap.getWidth())) / f5, (f2 - ((float) bitmap.getHeight())) / f5);
                mapImagePointsByImageMatrix();
                int i = this.mDegreesRotated;
                if (i > 0) {
                    this.mImageMatrix.postRotate((float) i, BitmapUtils.INSTANCE.getRectCenterX(this.mImagePoints), BitmapUtils.INSTANCE.getRectCenterY(this.mImagePoints));
                    mapImagePointsByImageMatrix();
                }
                float min = Math.min(f / BitmapUtils.INSTANCE.getRectWidth(this.mImagePoints), f2 / BitmapUtils.INSTANCE.getRectHeight(this.mImagePoints));
                if (this.mScaleType == ScaleType.FIT_CENTER || ((this.mScaleType == ScaleType.CENTER_INSIDE && min < 1.0f) || (min > 1.0f && this.mAutoZoomEnabled))) {
                    this.mImageMatrix.postScale(min, min, BitmapUtils.INSTANCE.getRectCenterX(this.mImagePoints), BitmapUtils.INSTANCE.getRectCenterY(this.mImagePoints));
                    mapImagePointsByImageMatrix();
                } else if (this.mScaleType == ScaleType.CENTER_CROP) {
                    this.mZoom = Math.max(((float) getWidth()) / BitmapUtils.INSTANCE.getRectWidth(this.mImagePoints), ((float) getHeight()) / BitmapUtils.INSTANCE.getRectHeight(this.mImagePoints));
                }
                float f6 = this.mFlipHorizontally ? -this.mZoom : this.mZoom;
                float f7 = this.mFlipVertically ? -this.mZoom : this.mZoom;
                this.mImageMatrix.postScale(f6, f7, BitmapUtils.INSTANCE.getRectCenterX(this.mImagePoints), BitmapUtils.INSTANCE.getRectCenterY(this.mImagePoints));
                mapImagePointsByImageMatrix();
                this.mImageMatrix.mapRect(cropWindowRect);
                if (this.mScaleType == ScaleType.CENTER_CROP && z && !z2) {
                    this.mZoomOffsetX = 0.0f;
                    this.mZoomOffsetY = 0.0f;
                } else if (z) {
                    if (f > BitmapUtils.INSTANCE.getRectWidth(this.mImagePoints)) {
                        f3 = 0.0f;
                    } else {
                        f3 = Math.max(Math.min((f / f5) - cropWindowRect.centerX(), -BitmapUtils.INSTANCE.getRectLeft(this.mImagePoints)), ((float) getWidth()) - BitmapUtils.INSTANCE.getRectRight(this.mImagePoints)) / f6;
                    }
                    this.mZoomOffsetX = f3;
                    if (f2 <= BitmapUtils.INSTANCE.getRectHeight(this.mImagePoints)) {
                        f4 = Math.max(Math.min((f2 / f5) - cropWindowRect.centerY(), -BitmapUtils.INSTANCE.getRectTop(this.mImagePoints)), ((float) getHeight()) - BitmapUtils.INSTANCE.getRectBottom(this.mImagePoints)) / f7;
                    }
                    this.mZoomOffsetY = f4;
                } else {
                    this.mZoomOffsetX = Math.min(Math.max(this.mZoomOffsetX * f6, -cropWindowRect.left), (-cropWindowRect.right) + f) / f6;
                    this.mZoomOffsetY = Math.min(Math.max(this.mZoomOffsetY * f7, -cropWindowRect.top), (-cropWindowRect.bottom) + f2) / f7;
                }
                this.mImageMatrix.postTranslate(this.mZoomOffsetX * f6, this.mZoomOffsetY * f7);
                cropWindowRect.offset(this.mZoomOffsetX * f6, this.mZoomOffsetY * f7);
                this.mCropOverlayView.setCropWindowRect(cropWindowRect);
                mapImagePointsByImageMatrix();
                this.mCropOverlayView.invalidate();
                if (z2) {
                    CropImageAnimation cropImageAnimation = this.mAnimation;
                    Intrinsics.checkNotNull(cropImageAnimation);
                    cropImageAnimation.setEndState(this.mImagePoints, this.mImageMatrix);
                    this.imageView.startAnimation(this.mAnimation);
                } else {
                    this.imageView.setImageMatrix(this.mImageMatrix);
                }
                updateImageBounds(false);
            }
        }
    }

    private final void mapImagePointsByImageMatrix() {
        float[] fArr = this.mImagePoints;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        Bitmap bitmap = this.originalBitmap;
        Intrinsics.checkNotNull(bitmap);
        fArr[2] = (float) bitmap.getWidth();
        float[] fArr2 = this.mImagePoints;
        fArr2[3] = 0.0f;
        Bitmap bitmap2 = this.originalBitmap;
        Intrinsics.checkNotNull(bitmap2);
        fArr2[4] = (float) bitmap2.getWidth();
        float[] fArr3 = this.mImagePoints;
        Bitmap bitmap3 = this.originalBitmap;
        Intrinsics.checkNotNull(bitmap3);
        fArr3[5] = (float) bitmap3.getHeight();
        float[] fArr4 = this.mImagePoints;
        fArr4[6] = 0.0f;
        Bitmap bitmap4 = this.originalBitmap;
        Intrinsics.checkNotNull(bitmap4);
        fArr4[7] = (float) bitmap4.getHeight();
        this.mImageMatrix.mapPoints(this.mImagePoints);
        float[] fArr5 = this.mScaleImagePoints;
        fArr5[0] = 0.0f;
        fArr5[1] = 0.0f;
        fArr5[2] = 100.0f;
        fArr5[3] = 0.0f;
        fArr5[4] = 100.0f;
        fArr5[5] = 100.0f;
        fArr5[6] = 0.0f;
        fArr5[7] = 100.0f;
        this.mImageMatrix.mapPoints(fArr5);
    }

    private final void setCropOverlayVisibility() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            cropOverlayView.setVisibility((!this.mShowCropOverlay || this.originalBitmap == null) ? 4 : 0);
        }
    }

    private final void setProgressBarVisibility() {
        int i = 0;
        boolean z = this.mShowProgressBar && ((this.originalBitmap == null && this.bitmapLoadingWorkerJob != null) || this.bitmapCroppingWorkerJob != null);
        ProgressBar progressBar = this.mProgressBar;
        if (!z) {
            i = 4;
        }
        progressBar.setVisibility(i);
    }

    private final void updateImageBounds(boolean z) {
        if (this.originalBitmap != null && !z) {
            float rectWidth = (((float) this.loadedSampleSize) * 100.0f) / BitmapUtils.INSTANCE.getRectWidth(this.mScaleImagePoints);
            float rectHeight = (((float) this.loadedSampleSize) * 100.0f) / BitmapUtils.INSTANCE.getRectHeight(this.mScaleImagePoints);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.setCropWindowLimits((float) getWidth(), (float) getHeight(), rectWidth, rectHeight);
        }
        CropOverlayView cropOverlayView2 = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView2);
        cropOverlayView2.setBounds(z ? null : this.mImagePoints, getWidth(), getHeight());
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001Bk\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0006\u0010'\u001a\u00020(J\u001a\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010'\u001a\u00020(2\b\b\u0002\u0010+\u001a\u00020\u001dR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0012\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019¨\u0006,"}, d2 = {"Lcom/canhub/cropper/CropImageView$CropResult;", "", "originalBitmap", "Landroid/graphics/Bitmap;", "originalUri", "Landroid/net/Uri;", "bitmap", "uriContent", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cropPoints", "", "cropRect", "Landroid/graphics/Rect;", "wholeImageRect", "rotation", "", "sampleSize", "(Landroid/graphics/Bitmap;Landroid/net/Uri;Landroid/graphics/Bitmap;Landroid/net/Uri;Ljava/lang/Exception;[FLandroid/graphics/Rect;Landroid/graphics/Rect;II)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getCropPoints", "()[F", "getCropRect", "()Landroid/graphics/Rect;", "getError", "()Ljava/lang/Exception;", "isSuccessful", "", "()Z", "getOriginalBitmap", "getOriginalUri", "()Landroid/net/Uri;", "getRotation", "()I", "getSampleSize", "getUriContent", "getWholeImageRect", "context", "Landroid/content/Context;", "getUriFilePath", "", "uniqueName", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public static class CropResult {
        private final Bitmap bitmap;
        private final float[] cropPoints;
        private final Rect cropRect;
        private final Exception error;
        private final Bitmap originalBitmap;
        private final Uri originalUri;
        private final int rotation;
        private final int sampleSize;
        private final Uri uriContent;
        private final Rect wholeImageRect;

        public CropResult(Bitmap bitmap2, Uri uri, Bitmap bitmap3, Uri uri2, Exception exc, float[] fArr, Rect rect, Rect rect2, int i, int i2) {
            Intrinsics.checkNotNullParameter(fArr, "cropPoints");
            this.originalBitmap = bitmap2;
            this.originalUri = uri;
            this.bitmap = bitmap3;
            this.uriContent = uri2;
            this.error = exc;
            this.cropPoints = fArr;
            this.cropRect = rect;
            this.wholeImageRect = rect2;
            this.rotation = i;
            this.sampleSize = i2;
        }

        public final Bitmap getOriginalBitmap() {
            return this.originalBitmap;
        }

        public final Uri getOriginalUri() {
            return this.originalUri;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Uri getUriContent() {
            return this.uriContent;
        }

        public final Exception getError() {
            return this.error;
        }

        public final float[] getCropPoints() {
            return this.cropPoints;
        }

        public final Rect getCropRect() {
            return this.cropRect;
        }

        public final Rect getWholeImageRect() {
            return this.wholeImageRect;
        }

        public final int getRotation() {
            return this.rotation;
        }

        public final int getSampleSize() {
            return this.sampleSize;
        }

        public final boolean isSuccessful() {
            return this.error == null;
        }

        public final Bitmap getBitmap(Context context) {
            Bitmap bitmap2;
            Intrinsics.checkNotNullParameter(context, "context");
            Bitmap bitmap3 = this.bitmap;
            if (bitmap3 != null) {
                return bitmap3;
            }
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(context.getContentResolver(), this.uriContent);
            } catch (Exception unused) {
                bitmap2 = null;
                Bitmap bitmap4 = null;
            }
            return bitmap2;
        }

        public static /* synthetic */ String getUriFilePath$default(CropResult cropResult, Context context, boolean z, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    z = false;
                }
                return cropResult.getUriFilePath(context, z);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUriFilePath");
        }

        public final String getUriFilePath(Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Uri uri = this.uriContent;
            if (uri != null) {
                return GetFilePathFromUriKt.getFilePathFromUri(context, uri, z);
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002¨\u0006\b"}, d2 = {"Lcom/canhub/cropper/CropImageView$Companion;", "", "()V", "getOnMeasureSpec", "", "measureSpecMode", "measureSpecSize", "desiredSize", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageView.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final int getOnMeasureSpec(int i, int i2, int i3) {
            if (i != Integer.MIN_VALUE) {
                return i != 1073741824 ? i3 : i2;
            }
            return Math.min(i3, i2);
        }
    }

    public void onCropWindowChanged(boolean z) {
        handleCropWindowChanged(z, true);
        OnSetCropOverlayReleasedListener onSetCropOverlayReleasedListener = this.mOnCropOverlayReleasedListener;
        if (onSetCropOverlayReleasedListener != null && !z) {
            onSetCropOverlayReleasedListener.onCropOverlayReleased(getCropRect());
        }
        OnSetCropOverlayMovedListener onSetCropOverlayMovedListener = this.mOnSetCropOverlayMovedListener;
        if (onSetCropOverlayMovedListener != null && z) {
            onSetCropOverlayMovedListener.onCropOverlayMoved(getCropRect());
        }
    }
}
