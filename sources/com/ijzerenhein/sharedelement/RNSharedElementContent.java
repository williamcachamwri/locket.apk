package com.ijzerenhein.sharedelement;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.GenericDraweeView;

class RNSharedElementContent {
    RectF size;
    View view;

    RNSharedElementContent() {
    }

    static RectF getSize(View view2) {
        if (view2 instanceof GenericDraweeView) {
            GenericDraweeView genericDraweeView = (GenericDraweeView) view2;
            DraweeController controller = genericDraweeView.getController();
            GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) genericDraweeView.getHierarchy();
            if (controller == null || controller.toString().contains("fetchedImage=0,")) {
                return null;
            }
            RectF rectF = new RectF();
            genericDraweeHierarchy.getActualImageBounds(rectF);
            return rectF;
        } else if (!(view2 instanceof ImageView)) {
            return new RectF(0.0f, 0.0f, (float) view2.getWidth(), (float) view2.getHeight());
        } else {
            Drawable drawable = ((ImageView) view2).getDrawable();
            if (drawable == null) {
                return null;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
                return null;
            }
            return new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
        }
    }
}
