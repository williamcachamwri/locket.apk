package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.Motion;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import com.facebook.hermes.intl.Constants;
import java.util.ArrayList;
import java.util.HashMap;

public class Transition implements TypedValues {
    static final int ANTICIPATE = 6;
    static final int BOUNCE = 4;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    public static final int END = 1;
    public static final int INTERPOLATED = 2;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    private static final int SPLINE_STRING = -1;
    public static final int START = 0;
    HashMap<Integer, HashMap<String, KeyPosition>> keyPositions = new HashMap<>();
    private int mAutoTransition = 0;
    TypedBundle mBundle = new TypedBundle();
    private int mDefaultInterpolator = 0;
    private String mDefaultInterpolatorString = null;
    private int mDuration = 400;
    private Easing mEasing = null;
    private float mStagger = 0.0f;
    private HashMap<String, WidgetState> state = new HashMap<>();

    public int getId(String str) {
        return 0;
    }

    public boolean setValue(int i, int i2) {
        return false;
    }

    public boolean setValue(int i, boolean z) {
        return false;
    }

    static /* synthetic */ float lambda$getInterpolator$0(String str, float f) {
        return (float) Easing.getInterpolator(str).get((double) f);
    }

    static /* synthetic */ float lambda$getInterpolator$1(float f) {
        return (float) Easing.getInterpolator(Constants.COLLATION_STANDARD).get((double) f);
    }

    static /* synthetic */ float lambda$getInterpolator$2(float f) {
        return (float) Easing.getInterpolator("accelerate").get((double) f);
    }

    static /* synthetic */ float lambda$getInterpolator$3(float f) {
        return (float) Easing.getInterpolator("decelerate").get((double) f);
    }

    static /* synthetic */ float lambda$getInterpolator$4(float f) {
        return (float) Easing.getInterpolator("linear").get((double) f);
    }

    public static Interpolator getInterpolator(int i, String str) {
        switch (i) {
            case -1:
                return new Transition$$ExternalSyntheticLambda0(str);
            case 0:
                return new Transition$$ExternalSyntheticLambda1();
            case 1:
                return new Transition$$ExternalSyntheticLambda2();
            case 2:
                return new Transition$$ExternalSyntheticLambda3();
            case 3:
                return new Transition$$ExternalSyntheticLambda4();
            case 4:
                return new Transition$$ExternalSyntheticLambda7();
            case 5:
                return new Transition$$ExternalSyntheticLambda6();
            case 6:
                return new Transition$$ExternalSyntheticLambda5();
            default:
                return null;
        }
    }

    static /* synthetic */ float lambda$getInterpolator$5(float f) {
        return (float) Easing.getInterpolator("anticipate").get((double) f);
    }

    static /* synthetic */ float lambda$getInterpolator$6(float f) {
        return (float) Easing.getInterpolator("overshoot").get((double) f);
    }

    static /* synthetic */ float lambda$getInterpolator$7(float f) {
        return (float) Easing.getInterpolator("spline(0.0, 0.2, 0.4, 0.6, 0.8 ,1.0, 0.8, 1.0, 0.9, 1.0)").get((double) f);
    }

    public KeyPosition findPreviousPosition(String str, int i) {
        KeyPosition keyPosition;
        while (i >= 0) {
            HashMap hashMap = this.keyPositions.get(Integer.valueOf(i));
            if (hashMap != null && (keyPosition = (KeyPosition) hashMap.get(str)) != null) {
                return keyPosition;
            }
            i--;
        }
        return null;
    }

    public KeyPosition findNextPosition(String str, int i) {
        KeyPosition keyPosition;
        while (i <= 100) {
            HashMap hashMap = this.keyPositions.get(Integer.valueOf(i));
            if (hashMap != null && (keyPosition = (KeyPosition) hashMap.get(str)) != null) {
                return keyPosition;
            }
            i++;
        }
        return null;
    }

    public int getNumberKeyPositions(WidgetFrame widgetFrame) {
        int i = 0;
        for (int i2 = 0; i2 <= 100; i2++) {
            HashMap hashMap = this.keyPositions.get(Integer.valueOf(i2));
            if (!(hashMap == null || ((KeyPosition) hashMap.get(widgetFrame.widget.stringId)) == null)) {
                i++;
            }
        }
        return i;
    }

    public Motion getMotion(String str) {
        return getWidgetState(str, (ConstraintWidget) null, 0).motionControl;
    }

    public void fillKeyPositions(WidgetFrame widgetFrame, float[] fArr, float[] fArr2, float[] fArr3) {
        KeyPosition keyPosition;
        int i = 0;
        for (int i2 = 0; i2 <= 100; i2++) {
            HashMap hashMap = this.keyPositions.get(Integer.valueOf(i2));
            if (!(hashMap == null || (keyPosition = (KeyPosition) hashMap.get(widgetFrame.widget.stringId)) == null)) {
                fArr[i] = keyPosition.x;
                fArr2[i] = keyPosition.y;
                fArr3[i] = (float) keyPosition.frame;
                i++;
            }
        }
    }

    public boolean hasPositionKeyframes() {
        return this.keyPositions.size() > 0;
    }

    public void setTransitionProperties(TypedBundle typedBundle) {
        typedBundle.applyDelta(this.mBundle);
        typedBundle.applyDelta((TypedValues) this);
    }

    public boolean setValue(int i, float f) {
        if (i != 706) {
            return false;
        }
        this.mStagger = f;
        return false;
    }

    public boolean setValue(int i, String str) {
        if (i != 705) {
            return false;
        }
        this.mDefaultInterpolatorString = str;
        this.mEasing = Easing.getInterpolator(str);
        return false;
    }

    public boolean isEmpty() {
        return this.state.isEmpty();
    }

    public void clear() {
        this.state.clear();
    }

    public boolean contains(String str) {
        return this.state.containsKey(str);
    }

    public void addKeyPosition(String str, TypedBundle typedBundle) {
        getWidgetState(str, (ConstraintWidget) null, 0).setKeyPosition(typedBundle);
    }

    public void addKeyAttribute(String str, TypedBundle typedBundle) {
        getWidgetState(str, (ConstraintWidget) null, 0).setKeyAttribute(typedBundle);
    }

    public void addKeyCycle(String str, TypedBundle typedBundle) {
        getWidgetState(str, (ConstraintWidget) null, 0).setKeyCycle(typedBundle);
    }

    public void addKeyPosition(String str, int i, int i2, float f, float f2) {
        TypedBundle typedBundle = new TypedBundle();
        typedBundle.add((int) TypedValues.PositionType.TYPE_POSITION_TYPE, 2);
        typedBundle.add(100, i);
        typedBundle.add((int) TypedValues.PositionType.TYPE_PERCENT_X, f);
        typedBundle.add((int) TypedValues.PositionType.TYPE_PERCENT_Y, f2);
        getWidgetState(str, (ConstraintWidget) null, 0).setKeyPosition(typedBundle);
        KeyPosition keyPosition = new KeyPosition(str, i, i2, f, f2);
        HashMap hashMap = this.keyPositions.get(Integer.valueOf(i));
        if (hashMap == null) {
            hashMap = new HashMap();
            this.keyPositions.put(Integer.valueOf(i), hashMap);
        }
        hashMap.put(str, keyPosition);
    }

    public void addCustomFloat(int i, String str, String str2, float f) {
        getWidgetState(str, (ConstraintWidget) null, i).getFrame(i).addCustomFloat(str2, f);
    }

    public void addCustomColor(int i, String str, String str2, int i2) {
        getWidgetState(str, (ConstraintWidget) null, i).getFrame(i).addCustomColor(str2, i2);
    }

    public void updateFrom(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = children.get(i2);
            getWidgetState(constraintWidget.stringId, (ConstraintWidget) null, i).update(constraintWidget, i);
        }
    }

    public void interpolate(int i, int i2, float f) {
        Easing easing = this.mEasing;
        if (easing != null) {
            f = (float) easing.get((double) f);
        }
        for (String str : this.state.keySet()) {
            this.state.get(str).interpolate(i, i2, f, this);
        }
    }

    public WidgetFrame getStart(String str) {
        WidgetState widgetState = this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.start;
    }

    public WidgetFrame getEnd(String str) {
        WidgetState widgetState = this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.end;
    }

    public WidgetFrame getInterpolated(String str) {
        WidgetState widgetState = this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.interpolated;
    }

    public float[] getPath(String str) {
        float[] fArr = new float[124];
        this.state.get(str).motionControl.buildPath(fArr, 62);
        return fArr;
    }

    public int getKeyFrames(String str, float[] fArr, int[] iArr, int[] iArr2) {
        return this.state.get(str).motionControl.buildKeyFrames(fArr, iArr, iArr2);
    }

    private WidgetState getWidgetState(String str) {
        return this.state.get(str);
    }

    private WidgetState getWidgetState(String str, ConstraintWidget constraintWidget, int i) {
        WidgetState widgetState = this.state.get(str);
        if (widgetState == null) {
            widgetState = new WidgetState();
            this.mBundle.applyDelta((TypedValues) widgetState.motionControl);
            this.state.put(str, widgetState);
            if (constraintWidget != null) {
                widgetState.update(constraintWidget, i);
            }
        }
        return widgetState;
    }

    public WidgetFrame getStart(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, (ConstraintWidget) null, 0).start;
    }

    public WidgetFrame getEnd(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, (ConstraintWidget) null, 1).end;
    }

    public WidgetFrame getInterpolated(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, (ConstraintWidget) null, 2).interpolated;
    }

    public Interpolator getInterpolator() {
        return getInterpolator(this.mDefaultInterpolator, this.mDefaultInterpolatorString);
    }

    public int getAutoTransition() {
        return this.mAutoTransition;
    }

    static class WidgetState {
        WidgetFrame end = new WidgetFrame();
        WidgetFrame interpolated = new WidgetFrame();
        Motion motionControl;
        MotionWidget motionWidgetEnd = new MotionWidget(this.end);
        MotionWidget motionWidgetInterpolated = new MotionWidget(this.interpolated);
        MotionWidget motionWidgetStart = new MotionWidget(this.start);
        KeyCache myKeyCache = new KeyCache();
        int myParentHeight = -1;
        int myParentWidth = -1;
        WidgetFrame start = new WidgetFrame();

        public WidgetState() {
            Motion motion = new Motion(this.motionWidgetStart);
            this.motionControl = motion;
            motion.setStart(this.motionWidgetStart);
            this.motionControl.setEnd(this.motionWidgetEnd);
        }

        public void setKeyPosition(TypedBundle typedBundle) {
            MotionKeyPosition motionKeyPosition = new MotionKeyPosition();
            typedBundle.applyDelta((TypedValues) motionKeyPosition);
            this.motionControl.addKey(motionKeyPosition);
        }

        public void setKeyAttribute(TypedBundle typedBundle) {
            MotionKeyAttributes motionKeyAttributes = new MotionKeyAttributes();
            typedBundle.applyDelta((TypedValues) motionKeyAttributes);
            this.motionControl.addKey(motionKeyAttributes);
        }

        public void setKeyCycle(TypedBundle typedBundle) {
            MotionKeyCycle motionKeyCycle = new MotionKeyCycle();
            typedBundle.applyDelta((TypedValues) motionKeyCycle);
            this.motionControl.addKey(motionKeyCycle);
        }

        public void update(ConstraintWidget constraintWidget, int i) {
            if (i == 0) {
                this.start.update(constraintWidget);
                this.motionControl.setStart(this.motionWidgetStart);
            } else if (i == 1) {
                this.end.update(constraintWidget);
                this.motionControl.setEnd(this.motionWidgetEnd);
            }
            this.myParentWidth = -1;
        }

        public WidgetFrame getFrame(int i) {
            if (i == 0) {
                return this.start;
            }
            if (i == 1) {
                return this.end;
            }
            return this.interpolated;
        }

        public void interpolate(int i, int i2, float f, Transition transition) {
            this.myParentHeight = i2;
            this.myParentWidth = i;
            this.motionControl.setup(i, i2, 1.0f, System.nanoTime());
            WidgetFrame.interpolate(i, i2, this.interpolated, this.start, this.end, transition, f);
            this.interpolated.interpolatedPos = f;
            this.motionControl.interpolate(this.motionWidgetInterpolated, f, System.nanoTime(), this.myKeyCache);
        }
    }

    static class KeyPosition {
        int frame;
        String target;
        int type;
        float x;
        float y;

        public KeyPosition(String str, int i, int i2, float f, float f2) {
            this.target = str;
            this.frame = i;
            this.type = i2;
            this.x = f;
            this.y = f2;
        }
    }
}
