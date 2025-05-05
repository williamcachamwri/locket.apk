package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.FadeThroughUpdateListener;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.RectEvaluator;
import com.google.android.material.internal.ReversableAnimatedValueInterpolator;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.search.SearchView;
import java.util.Objects;

class SearchViewAnimationHelper {
    private static final float CONTENT_FROM_SCALE = 0.95f;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_DURATION_MS = 42;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 0;
    private static final long HIDE_CONTENT_ALPHA_DURATION_MS = 83;
    private static final long HIDE_CONTENT_ALPHA_START_DELAY_MS = 0;
    private static final long HIDE_CONTENT_SCALE_DURATION_MS = 250;
    private static final long HIDE_DURATION_MS = 250;
    private static final long HIDE_TRANSLATE_DURATION_MS = 300;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS = 50;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 250;
    private static final long SHOW_CONTENT_ALPHA_DURATION_MS = 150;
    private static final long SHOW_CONTENT_ALPHA_START_DELAY_MS = 75;
    private static final long SHOW_CONTENT_SCALE_DURATION_MS = 300;
    private static final long SHOW_DURATION_MS = 300;
    private static final long SHOW_TRANSLATE_DURATION_MS = 350;
    private static final long SHOW_TRANSLATE_KEYBOARD_START_DELAY_MS = 150;
    private final ImageButton clearButton;
    private final TouchObserverFrameLayout contentContainer;
    private final View divider;
    private final Toolbar dummyToolbar;
    private final EditText editText;
    private final FrameLayout headerContainer;
    /* access modifiers changed from: private */
    public final ClippableRoundedCornerLayout rootView;
    private final View scrim;
    /* access modifiers changed from: private */
    public SearchBar searchBar;
    private final TextView searchPrefix;
    /* access modifiers changed from: private */
    public final SearchView searchView;
    private final Toolbar toolbar;
    private final FrameLayout toolbarContainer;

    SearchViewAnimationHelper(SearchView searchView2) {
        this.searchView = searchView2;
        this.scrim = searchView2.scrim;
        this.rootView = searchView2.rootView;
        this.headerContainer = searchView2.headerContainer;
        this.toolbarContainer = searchView2.toolbarContainer;
        this.toolbar = searchView2.toolbar;
        this.dummyToolbar = searchView2.dummyToolbar;
        this.searchPrefix = searchView2.searchPrefix;
        this.editText = searchView2.editText;
        this.clearButton = searchView2.clearButton;
        this.divider = searchView2.divider;
        this.contentContainer = searchView2.contentContainer;
    }

    /* access modifiers changed from: package-private */
    public void setSearchBar(SearchBar searchBar2) {
        this.searchBar = searchBar2;
    }

    /* access modifiers changed from: package-private */
    public void show() {
        if (this.searchBar != null) {
            startShowAnimationExpand();
        } else {
            startShowAnimationTranslate();
        }
    }

    /* access modifiers changed from: package-private */
    public void hide() {
        if (this.searchBar != null) {
            startHideAnimationCollapse();
        } else {
            startHideAnimationTranslate();
        }
    }

    private void startShowAnimationExpand() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.requestFocusAndShowKeyboardIfNeeded();
        }
        this.searchView.setTransitionState(SearchView.TransitionState.SHOWING);
        setUpDummyToolbarIfNeeded();
        this.editText.setText(this.searchBar.getText());
        EditText editText2 = this.editText;
        editText2.setSelection(editText2.getText().length());
        this.rootView.setVisibility(4);
        this.rootView.post(new SearchViewAnimationHelper$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startShowAnimationExpand$0$com-google-android-material-search-SearchViewAnimationHelper  reason: not valid java name */
    public /* synthetic */ void m2148lambda$startShowAnimationExpand$0$comgoogleandroidmaterialsearchSearchViewAnimationHelper() {
        AnimatorSet expandCollapseAnimatorSet = getExpandCollapseAnimatorSet(true);
        expandCollapseAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.rootView.setVisibility(0);
                SearchViewAnimationHelper.this.searchBar.stopOnLoadAnimation();
            }

            public void onAnimationEnd(Animator animator) {
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.requestFocusAndShowKeyboardIfNeeded();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWN);
            }
        });
        expandCollapseAnimatorSet.start();
    }

    private void startHideAnimationCollapse() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.clearFocusAndHideKeyboard();
        }
        AnimatorSet expandCollapseAnimatorSet = getExpandCollapseAnimatorSet(false);
        expandCollapseAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDING);
            }

            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.rootView.setVisibility(8);
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.clearFocusAndHideKeyboard();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDDEN);
            }
        });
        expandCollapseAnimatorSet.start();
    }

    private void startShowAnimationTranslate() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            SearchView searchView2 = this.searchView;
            Objects.requireNonNull(searchView2);
            searchView2.postDelayed(new SearchViewAnimationHelper$$ExternalSyntheticLambda3(searchView2), 150);
        }
        this.rootView.setVisibility(4);
        this.rootView.post(new SearchViewAnimationHelper$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startShowAnimationTranslate$1$com-google-android-material-search-SearchViewAnimationHelper  reason: not valid java name */
    public /* synthetic */ void m2149lambda$startShowAnimationTranslate$1$comgoogleandroidmaterialsearchSearchViewAnimationHelper() {
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.rootView;
        clippableRoundedCornerLayout.setTranslationY((float) clippableRoundedCornerLayout.getHeight());
        AnimatorSet translateAnimatorSet = getTranslateAnimatorSet(true);
        translateAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.rootView.setVisibility(0);
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWING);
            }

            public void onAnimationEnd(Animator animator) {
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.requestFocusAndShowKeyboardIfNeeded();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWN);
            }
        });
        translateAnimatorSet.start();
    }

    private void startHideAnimationTranslate() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.clearFocusAndHideKeyboard();
        }
        AnimatorSet translateAnimatorSet = getTranslateAnimatorSet(false);
        translateAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDING);
            }

            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.rootView.setVisibility(8);
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.clearFocusAndHideKeyboard();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDDEN);
            }
        });
        translateAnimatorSet.start();
    }

    private AnimatorSet getTranslateAnimatorSet(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{getTranslationYAnimator()});
        addBackButtonProgressAnimatorIfNeeded(animatorSet);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        animatorSet.setDuration(z ? SHOW_TRANSLATE_DURATION_MS : 300);
        return animatorSet;
    }

    private Animator getTranslationYAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) this.rootView.getHeight(), 0.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.translationYListener(this.rootView));
        return ofFloat;
    }

    private AnimatorSet getExpandCollapseAnimatorSet(final boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{getScrimAlphaAnimator(z), getRootViewAnimator(z), getClearButtonAnimator(z), getContentAnimator(z), getButtonsAnimator(z), getHeaderContainerAnimator(z), getDummyToolbarAnimator(z), getActionMenuViewsAlphaAnimator(z), getEditTextAnimator(z), getSearchPrefixAnimator(z)});
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.setContentViewsAlpha(z ? 0.0f : 1.0f);
            }

            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.setContentViewsAlpha(z ? 1.0f : 0.0f);
                if (z) {
                    SearchViewAnimationHelper.this.rootView.resetClipBoundsAndCornerRadius();
                }
            }
        });
        return animatorSet;
    }

    /* access modifiers changed from: private */
    public void setContentViewsAlpha(float f) {
        this.clearButton.setAlpha(f);
        this.divider.setAlpha(f);
        this.contentContainer.setAlpha(f);
        setActionMenuViewAlphaIfNeeded(f);
    }

    private void setActionMenuViewAlphaIfNeeded(float f) {
        ActionMenuView actionMenuView;
        if (this.searchView.isMenuItemsAnimated() && (actionMenuView = ToolbarUtils.getActionMenuView(this.toolbar)) != null) {
            actionMenuView.setAlpha(f);
        }
    }

    private Animator getScrimAlphaAnimator(boolean z) {
        TimeInterpolator timeInterpolator = z ? AnimationUtils.LINEAR_INTERPOLATOR : AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(z ? 300 : 250);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z, timeInterpolator));
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(this.scrim));
        return ofFloat;
    }

    private Animator getRootViewAnimator(boolean z) {
        Rect calculateRectFromBounds = ViewUtils.calculateRectFromBounds(this.searchView);
        Rect calculateFromClipBounds = calculateFromClipBounds();
        Rect rect = new Rect(calculateFromClipBounds);
        float cornerSize = this.searchBar.getCornerSize();
        ValueAnimator ofObject = ValueAnimator.ofObject(new RectEvaluator(rect), new Object[]{calculateFromClipBounds, calculateRectFromBounds});
        ofObject.addUpdateListener(new SearchViewAnimationHelper$$ExternalSyntheticLambda5(this, cornerSize, rect));
        ofObject.setDuration(z ? 300 : 250);
        ofObject.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return ofObject;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getRootViewAnimator$2$com-google-android-material-search-SearchViewAnimationHelper  reason: not valid java name */
    public /* synthetic */ void m2147lambda$getRootViewAnimator$2$comgoogleandroidmaterialsearchSearchViewAnimationHelper(float f, Rect rect, ValueAnimator valueAnimator) {
        this.rootView.updateClipBoundsAndCornerRadius(rect, f * (1.0f - valueAnimator.getAnimatedFraction()));
    }

    private Rect calculateFromClipBounds() {
        int[] iArr = new int[2];
        this.searchBar.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int[] iArr2 = new int[2];
        this.rootView.getLocationOnScreen(iArr2);
        int i3 = i - iArr2[0];
        int i4 = i2 - iArr2[1];
        return new Rect(i3, i4, this.searchBar.getWidth() + i3, this.searchBar.getHeight() + i4);
    }

    private Animator getClearButtonAnimator(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(z ? SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS : HIDE_CLEAR_BUTTON_ALPHA_DURATION_MS);
        ofFloat.setStartDelay(z ? 250 : 0);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.LINEAR_INTERPOLATOR));
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(this.clearButton));
        return ofFloat;
    }

    private Animator getButtonsAnimator(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        addBackButtonTranslationAnimatorIfNeeded(animatorSet);
        addBackButtonProgressAnimatorIfNeeded(animatorSet);
        addActionMenuViewAnimatorIfNeeded(animatorSet);
        animatorSet.setDuration(z ? 300 : 250);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    private void addBackButtonTranslationAnimatorIfNeeded(AnimatorSet animatorSet) {
        ImageButton navigationIconButton = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if (navigationIconButton != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationXStart(navigationIconButton), 0.0f});
            ofFloat.addUpdateListener(MultiViewUpdateListener.translationXListener(navigationIconButton));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationY(), 0.0f});
            ofFloat2.addUpdateListener(MultiViewUpdateListener.translationYListener(navigationIconButton));
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        }
    }

    private void addBackButtonProgressAnimatorIfNeeded(AnimatorSet animatorSet) {
        ImageButton navigationIconButton = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if (navigationIconButton != null) {
            Drawable unwrap = DrawableCompat.unwrap(navigationIconButton.getDrawable());
            if (this.searchView.isAnimatedNavigationIcon()) {
                addDrawerArrowDrawableAnimatorIfNeeded(animatorSet, unwrap);
                addFadeThroughDrawableAnimatorIfNeeded(animatorSet, unwrap);
                return;
            }
            setFullDrawableProgressIfNeeded(unwrap);
        }
    }

    private void addDrawerArrowDrawableAnimatorIfNeeded(AnimatorSet animatorSet, Drawable drawable) {
        if (drawable instanceof DrawerArrowDrawable) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.addUpdateListener(new SearchViewAnimationHelper$$ExternalSyntheticLambda1((DrawerArrowDrawable) drawable));
            animatorSet.playTogether(new Animator[]{ofFloat});
        }
    }

    private void addFadeThroughDrawableAnimatorIfNeeded(AnimatorSet animatorSet, Drawable drawable) {
        if (drawable instanceof FadeThroughDrawable) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.addUpdateListener(new SearchViewAnimationHelper$$ExternalSyntheticLambda0((FadeThroughDrawable) drawable));
            animatorSet.playTogether(new Animator[]{ofFloat});
        }
    }

    private void setFullDrawableProgressIfNeeded(Drawable drawable) {
        if (drawable instanceof DrawerArrowDrawable) {
            ((DrawerArrowDrawable) drawable).setProgress(1.0f);
        }
        if (drawable instanceof FadeThroughDrawable) {
            ((FadeThroughDrawable) drawable).setProgress(1.0f);
        }
    }

    private void addActionMenuViewAnimatorIfNeeded(AnimatorSet animatorSet) {
        ActionMenuView actionMenuView = ToolbarUtils.getActionMenuView(this.toolbar);
        if (actionMenuView != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationXEnd(actionMenuView), 0.0f});
            ofFloat.addUpdateListener(MultiViewUpdateListener.translationXListener(actionMenuView));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationY(), 0.0f});
            ofFloat2.addUpdateListener(MultiViewUpdateListener.translationYListener(actionMenuView));
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        }
    }

    private Animator getDummyToolbarAnimator(boolean z) {
        return getTranslationAnimator(z, false, this.dummyToolbar);
    }

    private Animator getHeaderContainerAnimator(boolean z) {
        return getTranslationAnimator(z, false, this.headerContainer);
    }

    private Animator getActionMenuViewsAlphaAnimator(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(z ? 300 : 250);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        if (this.searchView.isMenuItemsAnimated()) {
            ofFloat.addUpdateListener(new FadeThroughUpdateListener(ToolbarUtils.getActionMenuView(this.dummyToolbar), ToolbarUtils.getActionMenuView(this.toolbar)));
        }
        return ofFloat;
    }

    private Animator getSearchPrefixAnimator(boolean z) {
        return getTranslationAnimator(z, true, this.searchPrefix);
    }

    private Animator getEditTextAnimator(boolean z) {
        return getTranslationAnimator(z, true, this.editText);
    }

    private Animator getContentAnimator(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{getContentAlphaAnimator(z), getDividerAnimator(z), getContentScaleAnimator(z)});
        return animatorSet;
    }

    private Animator getContentAlphaAnimator(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(z ? 150 : HIDE_CONTENT_ALPHA_DURATION_MS);
        ofFloat.setStartDelay(z ? 75 : 0);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.LINEAR_INTERPOLATOR));
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(this.divider, this.contentContainer));
        return ofFloat;
    }

    private Animator getDividerAnimator(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(((float) this.contentContainer.getHeight()) * 0.050000012f) / 2.0f, 0.0f});
        ofFloat.setDuration(z ? 300 : 250);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        ofFloat.addUpdateListener(MultiViewUpdateListener.translationYListener(this.divider));
        return ofFloat;
    }

    private Animator getContentScaleAnimator(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{CONTENT_FROM_SCALE, 1.0f});
        ofFloat.setDuration(z ? 300 : 250);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        ofFloat.addUpdateListener(MultiViewUpdateListener.scaleListener(this.contentContainer));
        return ofFloat;
    }

    private Animator getTranslationAnimator(boolean z, boolean z2, View view) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) (z2 ? getFromTranslationXStart(view) : getFromTranslationXEnd(view)), 0.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.translationXListener(view));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationY(), 0.0f});
        ofFloat2.addUpdateListener(MultiViewUpdateListener.translationYListener(view));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(z ? 300 : 250);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    private int getFromTranslationXStart(View view) {
        int marginStart = MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) view.getLayoutParams());
        int paddingStart = ViewCompat.getPaddingStart(this.searchBar);
        if (ViewUtils.isLayoutRtl(this.searchBar)) {
            return ((this.searchBar.getWidth() - this.searchBar.getRight()) + marginStart) - paddingStart;
        }
        return (this.searchBar.getLeft() - marginStart) + paddingStart;
    }

    private int getFromTranslationXEnd(View view) {
        int marginEnd = MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) view.getLayoutParams());
        if (ViewUtils.isLayoutRtl(this.searchBar)) {
            return this.searchBar.getLeft() - marginEnd;
        }
        return (this.searchBar.getRight() - this.searchView.getWidth()) + marginEnd;
    }

    private int getFromTranslationY() {
        return ((this.searchBar.getTop() + this.searchBar.getBottom()) / 2) - ((this.toolbarContainer.getTop() + this.toolbarContainer.getBottom()) / 2);
    }

    private void setUpDummyToolbarIfNeeded() {
        Menu menu = this.dummyToolbar.getMenu();
        if (menu != null) {
            menu.clear();
        }
        if (this.searchBar.getMenuResId() == -1 || !this.searchView.isMenuItemsAnimated()) {
            this.dummyToolbar.setVisibility(8);
            return;
        }
        this.dummyToolbar.inflateMenu(this.searchBar.getMenuResId());
        setMenuItemsNotClickable(this.dummyToolbar);
        this.dummyToolbar.setVisibility(0);
    }

    private void setMenuItemsNotClickable(Toolbar toolbar2) {
        ActionMenuView actionMenuView = ToolbarUtils.getActionMenuView(toolbar2);
        if (actionMenuView != null) {
            for (int i = 0; i < actionMenuView.getChildCount(); i++) {
                View childAt = actionMenuView.getChildAt(i);
                childAt.setClickable(false);
                childAt.setFocusable(false);
                childAt.setFocusableInTouchMode(false);
            }
        }
    }
}
