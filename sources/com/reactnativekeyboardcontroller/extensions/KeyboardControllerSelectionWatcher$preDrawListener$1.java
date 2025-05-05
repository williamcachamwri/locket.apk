package com.reactnativekeyboardcontroller.extensions;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/reactnativekeyboardcontroller/extensions/KeyboardControllerSelectionWatcher$preDrawListener$1", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "onPreDraw", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EditText.kt */
public final class KeyboardControllerSelectionWatcher$preDrawListener$1 implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ KeyboardControllerSelectionWatcher this$0;

    KeyboardControllerSelectionWatcher$preDrawListener$1(KeyboardControllerSelectionWatcher keyboardControllerSelectionWatcher) {
        this.this$0 = keyboardControllerSelectionWatcher;
    }

    public boolean onPreDraw() {
        int i;
        int i2;
        Drawable textCursorDrawable;
        int selectionStart = this.this$0.editText.getSelectionStart();
        int selectionEnd = this.this$0.editText.getSelectionEnd();
        int height = this.this$0.editText.getHeight();
        EditText access$getEditText$p = this.this$0.editText;
        Layout layout = access$getEditText$p.getLayout();
        if (layout == null) {
            return true;
        }
        if (!(this.this$0.lastSelectionStart == selectionStart && this.this$0.lastSelectionEnd == selectionEnd && this.this$0.lastEditTextHeight == height)) {
            this.this$0.lastSelectionStart = selectionStart;
            this.this$0.lastSelectionEnd = selectionEnd;
            this.this$0.lastEditTextHeight = height;
            int min = Math.min(selectionStart, selectionEnd);
            int max = Math.max(selectionStart, selectionEnd);
            int lineTop = layout.getLineTop(layout.getLineForOffset(min));
            int height2 = layout.getHeight();
            int i3 = 0;
            if (Build.VERSION.SDK_INT >= 29 && (textCursorDrawable = access$getEditText$p.getTextCursorDrawable()) != null) {
                i3 = textCursorDrawable.getIntrinsicWidth();
            }
            int gravity = this.this$0.editText.getGravity() & 112;
            int paddingTop = this.this$0.editText.getPaddingTop() + this.this$0.editText.getPaddingBottom();
            int lineHeight = this.this$0.editText.getLineHeight() / 2;
            int i4 = height - paddingTop;
            if (height2 > i4) {
                i2 = this.this$0.editText.getPaddingTop();
            } else if (gravity == 16) {
                i2 = ((i4 - height2) / 2) + this.this$0.editText.getPaddingTop();
            } else if (gravity != 80) {
                i2 = this.this$0.editText.getPaddingTop();
            } else {
                i = this.this$0.editText.getPaddingTop() + (i4 - height2) + lineHeight;
                float primaryHorizontal = layout.getPrimaryHorizontal(min);
                int lineForOffset = layout.getLineForOffset(max);
                float primaryHorizontal2 = layout.getPrimaryHorizontal(max);
                float f = primaryHorizontal2 + ((float) i3);
                this.this$0.action.invoke(Integer.valueOf(selectionStart), Integer.valueOf(selectionEnd), Double.valueOf(FloatKt.getDp(primaryHorizontal)), Double.valueOf(FloatKt.getDp((float) ((lineTop + i) - access$getEditText$p.getScrollY()))), Double.valueOf(FloatKt.getDp(f)), Double.valueOf(FloatKt.getDp((float) ((layout.getLineBottom(lineForOffset) + i) - access$getEditText$p.getScrollY()))));
            }
            i = i2 + lineHeight;
            float primaryHorizontal3 = layout.getPrimaryHorizontal(min);
            int lineForOffset2 = layout.getLineForOffset(max);
            float primaryHorizontal22 = layout.getPrimaryHorizontal(max);
            float f2 = primaryHorizontal22 + ((float) i3);
            this.this$0.action.invoke(Integer.valueOf(selectionStart), Integer.valueOf(selectionEnd), Double.valueOf(FloatKt.getDp(primaryHorizontal3)), Double.valueOf(FloatKt.getDp((float) ((lineTop + i) - access$getEditText$p.getScrollY()))), Double.valueOf(FloatKt.getDp(f2)), Double.valueOf(FloatKt.getDp((float) ((layout.getLineBottom(lineForOffset2) + i) - access$getEditText$p.getScrollY()))));
        }
        return true;
    }
}
