package com.facebook.fresco.vito.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/facebook/fresco/vito/renderer/PathShape;", "Lcom/facebook/fresco/vito/renderer/Shape;", "path", "Landroid/graphics/Path;", "(Landroid/graphics/Path;)V", "getPath", "()Landroid/graphics/Path;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Shape.kt */
public final class PathShape extends Shape {
    private final Path path;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PathShape(Path path2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(path2, "path");
        this.path = path2;
    }

    public final Path getPath() {
        return this.path;
    }

    public void draw(Canvas canvas, Paint paint) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(paint, "paint");
        canvas.drawPath(this.path, paint);
    }
}
