package expo.modules.imagemanipulator.arguments;

import expo.modules.imagemanipulator.CropRect;
import expo.modules.imagemanipulator.FlipType;
import expo.modules.imagemanipulator.ManipulateAction;
import expo.modules.imagemanipulator.ResizeOptions;
import expo.modules.imagemanipulator.actions.Action;
import expo.modules.imagemanipulator.actions.CropAction;
import expo.modules.imagemanipulator.actions.FlipAction;
import expo.modules.imagemanipulator.actions.ResizeAction;
import expo.modules.imagemanipulator.actions.RotateAction;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lexpo/modules/imagemanipulator/arguments/Actions;", "", "actions", "", "Lexpo/modules/imagemanipulator/actions/Action;", "(Ljava/util/List;)V", "getActions", "()Ljava/util/List;", "Companion", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Actions.kt */
public final class Actions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<Action> actions;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¨\u0006\b"}, d2 = {"Lexpo/modules/imagemanipulator/arguments/Actions$Companion;", "", "()V", "fromArgument", "Lexpo/modules/imagemanipulator/arguments/Actions;", "actionList", "", "Lexpo/modules/imagemanipulator/ManipulateAction;", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Actions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Actions fromArgument(List<ManipulateAction> list) {
            Intrinsics.checkNotNullParameter(list, "actionList");
            List arrayList = new ArrayList();
            for (ManipulateAction next : list) {
                FlipType flip = next.getFlip();
                if (flip != null) {
                    arrayList.add(new FlipAction(flip));
                }
                CropRect crop = next.getCrop();
                if (crop != null) {
                    arrayList.add(new CropAction(crop));
                }
                Double rotate = next.getRotate();
                if (rotate != null) {
                    arrayList.add(new RotateAction((int) rotate.doubleValue()));
                }
                ResizeOptions resize = next.getResize();
                if (resize != null) {
                    arrayList.add(new ResizeAction(resize));
                }
            }
            return new Actions(arrayList);
        }
    }

    public Actions(List<? extends Action> list) {
        Intrinsics.checkNotNullParameter(list, "actions");
        this.actions = list;
    }

    public final List<Action> getActions() {
        return this.actions;
    }
}
