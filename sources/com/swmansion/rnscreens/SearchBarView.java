package com.swmansion.rnscreens;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.widget.SearchView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;
import com.swmansion.rnscreens.events.SearchBarBlurEvent;
import com.swmansion.rnscreens.events.SearchBarChangeTextEvent;
import com.swmansion.rnscreens.events.SearchBarCloseEvent;
import com.swmansion.rnscreens.events.SearchBarFocusEvent;
import com.swmansion.rnscreens.events.SearchBarOpenEvent;
import com.swmansion.rnscreens.events.SearchBarSearchButtonPressEvent;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002YZB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010?\u001a\u00020@J\u0006\u0010A\u001a\u00020@J\b\u0010B\u001a\u00020@H\u0002J\u0010\u0010C\u001a\u00020@2\u0006\u0010D\u001a\u00020\fH\u0002J\u0006\u0010E\u001a\u00020@J\b\u0010F\u001a\u00020@H\u0002J\u0010\u0010G\u001a\u00020@2\b\u0010H\u001a\u0004\u0018\u00010)J\u0012\u0010I\u001a\u00020@2\b\u0010J\u001a\u0004\u0018\u00010)H\u0002J\u0012\u0010K\u001a\u00020@2\b\u0010J\u001a\u0004\u0018\u00010)H\u0002J\u000e\u0010L\u001a\u00020@2\u0006\u0010M\u001a\u00020\fJ\b\u0010N\u001a\u00020@H\u0014J\u0006\u0010O\u001a\u00020@J\u0014\u0010P\u001a\u00020@2\n\u0010Q\u001a\u0006\u0012\u0002\b\u00030RH\u0002J\u0010\u0010S\u001a\u00020@2\u0006\u0010T\u001a\u00020UH\u0002J\b\u0010V\u001a\u00020@H\u0002J\u0010\u0010W\u001a\u00020@2\u0006\u0010X\u001a\u00020\u0016H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128BX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0016\u0010.\u001a\u0004\u0018\u00010/8BX\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u001a\u00102\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u000e\"\u0004\b4\u0010\u0010R\u001a\u00105\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u000e\"\u0004\b7\u0010\u0010R\u000e\u00108\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u001e\u00109\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b:\u0010\u0018\"\u0004\b;\u0010\u001aR\u001e\u0010<\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b=\u0010\u0018\"\u0004\b>\u0010\u001a¨\u0006["}, d2 = {"Lcom/swmansion/rnscreens/SearchBarView;", "Lcom/facebook/react/views/view/ReactViewGroup;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "autoCapitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "getAutoCapitalize", "()Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "setAutoCapitalize", "(Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;)V", "autoFocus", "", "getAutoFocus", "()Z", "setAutoFocus", "(Z)V", "headerConfig", "Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "getHeaderConfig", "()Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "headerIconColor", "", "getHeaderIconColor", "()Ljava/lang/Integer;", "setHeaderIconColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "hintTextColor", "getHintTextColor", "setHintTextColor", "inputType", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "getInputType", "()Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "setInputType", "(Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;)V", "mAreListenersSet", "mSearchViewFormatter", "Lcom/swmansion/rnscreens/SearchViewFormatter;", "placeholder", "", "getPlaceholder", "()Ljava/lang/String;", "setPlaceholder", "(Ljava/lang/String;)V", "screenStackFragment", "Lcom/swmansion/rnscreens/ScreenStackFragment;", "getScreenStackFragment", "()Lcom/swmansion/rnscreens/ScreenStackFragment;", "shouldOverrideBackButton", "getShouldOverrideBackButton", "setShouldOverrideBackButton", "shouldShowHintSearchIcon", "getShouldShowHintSearchIcon", "setShouldShowHintSearchIcon", "surfaceId", "textColor", "getTextColor", "setTextColor", "tintColor", "getTintColor", "setTintColor", "handleBlurJsRequest", "", "handleClearTextJsRequest", "handleClose", "handleFocusChange", "hasFocus", "handleFocusJsRequest", "handleOpen", "handleSetTextJsRequest", "text", "handleTextChange", "newText", "handleTextSubmit", "handleToggleCancelButtonJsRequest", "flag", "onAttachedToWindow", "onUpdate", "sendEvent", "event", "Lcom/facebook/react/uimanager/events/Event;", "setSearchViewListeners", "searchView", "Landroidx/appcompat/widget/SearchView;", "setSearchViewProps", "setToolbarElementsVisibility", "visibility", "SearchBarAutoCapitalize", "SearchBarInputTypes", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SearchBarView.kt */
public final class SearchBarView extends ReactViewGroup {
    private SearchBarAutoCapitalize autoCapitalize = SearchBarAutoCapitalize.NONE;
    private boolean autoFocus;
    private Integer headerIconColor;
    private Integer hintTextColor;
    private SearchBarInputTypes inputType = SearchBarInputTypes.TEXT;
    private boolean mAreListenersSet;
    /* access modifiers changed from: private */
    public SearchViewFormatter mSearchViewFormatter;
    private String placeholder = "";
    private boolean shouldOverrideBackButton = true;
    private boolean shouldShowHintSearchIcon = true;
    private final int surfaceId = UIManagerHelper.getSurfaceId((View) this);
    private Integer textColor;
    private Integer tintColor;

    public final void handleToggleCancelButtonJsRequest(boolean z) {
    }

    public SearchBarView(ReactContext reactContext) {
        super(reactContext);
    }

    public final SearchBarInputTypes getInputType() {
        return this.inputType;
    }

    public final void setInputType(SearchBarInputTypes searchBarInputTypes) {
        Intrinsics.checkNotNullParameter(searchBarInputTypes, "<set-?>");
        this.inputType = searchBarInputTypes;
    }

    public final SearchBarAutoCapitalize getAutoCapitalize() {
        return this.autoCapitalize;
    }

    public final void setAutoCapitalize(SearchBarAutoCapitalize searchBarAutoCapitalize) {
        Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "<set-?>");
        this.autoCapitalize = searchBarAutoCapitalize;
    }

    public final Integer getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(Integer num) {
        this.textColor = num;
    }

    public final Integer getTintColor() {
        return this.tintColor;
    }

    public final void setTintColor(Integer num) {
        this.tintColor = num;
    }

    public final Integer getHeaderIconColor() {
        return this.headerIconColor;
    }

    public final void setHeaderIconColor(Integer num) {
        this.headerIconColor = num;
    }

    public final Integer getHintTextColor() {
        return this.hintTextColor;
    }

    public final void setHintTextColor(Integer num) {
        this.hintTextColor = num;
    }

    public final String getPlaceholder() {
        return this.placeholder;
    }

    public final void setPlaceholder(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.placeholder = str;
    }

    public final boolean getShouldOverrideBackButton() {
        return this.shouldOverrideBackButton;
    }

    public final void setShouldOverrideBackButton(boolean z) {
        this.shouldOverrideBackButton = z;
    }

    public final boolean getAutoFocus() {
        return this.autoFocus;
    }

    public final void setAutoFocus(boolean z) {
        this.autoFocus = z;
    }

    public final boolean getShouldShowHintSearchIcon() {
        return this.shouldShowHintSearchIcon;
    }

    public final void setShouldShowHintSearchIcon(boolean z) {
        this.shouldShowHintSearchIcon = z;
    }

    private final ScreenStackHeaderConfig getHeaderConfig() {
        ViewParent parent = getParent();
        if (parent instanceof ScreenStackHeaderSubview) {
            return ((ScreenStackHeaderSubview) parent).getConfig();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final ScreenStackFragment getScreenStackFragment() {
        ScreenStackHeaderConfig headerConfig = getHeaderConfig();
        if (headerConfig != null) {
            return headerConfig.getScreenFragment();
        }
        return null;
    }

    public final void onUpdate() {
        setSearchViewProps();
    }

    /* access modifiers changed from: private */
    public final void setSearchViewProps() {
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        CustomSearchView searchView = screenStackFragment != null ? screenStackFragment.getSearchView() : null;
        if (searchView != null) {
            if (!this.mAreListenersSet) {
                setSearchViewListeners(searchView);
                this.mAreListenersSet = true;
            }
            searchView.setInputType(this.inputType.toAndroidInputType(this.autoCapitalize));
            SearchViewFormatter searchViewFormatter = this.mSearchViewFormatter;
            if (searchViewFormatter != null) {
                searchViewFormatter.setTextColor(this.textColor);
            }
            SearchViewFormatter searchViewFormatter2 = this.mSearchViewFormatter;
            if (searchViewFormatter2 != null) {
                searchViewFormatter2.setTintColor(this.tintColor);
            }
            SearchViewFormatter searchViewFormatter3 = this.mSearchViewFormatter;
            if (searchViewFormatter3 != null) {
                searchViewFormatter3.setHeaderIconColor(this.headerIconColor);
            }
            SearchViewFormatter searchViewFormatter4 = this.mSearchViewFormatter;
            if (searchViewFormatter4 != null) {
                searchViewFormatter4.setHintTextColor(this.hintTextColor);
            }
            SearchViewFormatter searchViewFormatter5 = this.mSearchViewFormatter;
            if (searchViewFormatter5 != null) {
                searchViewFormatter5.setPlaceholder(this.placeholder, this.shouldShowHintSearchIcon);
            }
            searchView.setOverrideBackAction(this.shouldOverrideBackButton);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment != null) {
            screenStackFragment.setOnSearchViewCreate(new SearchBarView$onAttachedToWindow$1(this));
        }
    }

    private final void setSearchViewListeners(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchBarView$setSearchViewListeners$1(this));
        searchView.setOnQueryTextFocusChangeListener(new SearchBarView$$ExternalSyntheticLambda0(this));
        searchView.setOnCloseListener(new SearchBarView$$ExternalSyntheticLambda1(this));
        searchView.setOnSearchClickListener(new SearchBarView$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: private */
    public static final void setSearchViewListeners$lambda$0(SearchBarView searchBarView, View view, boolean z) {
        Intrinsics.checkNotNullParameter(searchBarView, "this$0");
        searchBarView.handleFocusChange(z);
    }

    /* access modifiers changed from: private */
    public static final boolean setSearchViewListeners$lambda$1(SearchBarView searchBarView) {
        Intrinsics.checkNotNullParameter(searchBarView, "this$0");
        searchBarView.handleClose();
        return false;
    }

    /* access modifiers changed from: private */
    public static final void setSearchViewListeners$lambda$2(SearchBarView searchBarView, View view) {
        Intrinsics.checkNotNullParameter(searchBarView, "this$0");
        searchBarView.handleOpen();
    }

    /* access modifiers changed from: private */
    public final void handleTextChange(String str) {
        sendEvent(new SearchBarChangeTextEvent(this.surfaceId, getId(), str));
    }

    private final void handleFocusChange(boolean z) {
        sendEvent(z ? new SearchBarFocusEvent(this.surfaceId, getId()) : new SearchBarBlurEvent(this.surfaceId, getId()));
    }

    private final void handleClose() {
        sendEvent(new SearchBarCloseEvent(this.surfaceId, getId()));
        setToolbarElementsVisibility(0);
    }

    private final void handleOpen() {
        sendEvent(new SearchBarOpenEvent(this.surfaceId, getId()));
        setToolbarElementsVisibility(8);
    }

    /* access modifiers changed from: private */
    public final void handleTextSubmit(String str) {
        sendEvent(new SearchBarSearchButtonPressEvent(this.surfaceId, getId(), str));
    }

    private final void sendEvent(Event<?> event) {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(event);
        }
    }

    public final void handleClearTextJsRequest() {
        CustomSearchView searchView;
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment != null && (searchView = screenStackFragment.getSearchView()) != null) {
            searchView.clearText();
        }
    }

    public final void handleFocusJsRequest() {
        CustomSearchView searchView;
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment != null && (searchView = screenStackFragment.getSearchView()) != null) {
            searchView.focus();
        }
    }

    public final void handleBlurJsRequest() {
        CustomSearchView searchView;
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment != null && (searchView = screenStackFragment.getSearchView()) != null) {
            searchView.clearFocus();
        }
    }

    public final void handleSetTextJsRequest(String str) {
        ScreenStackFragment screenStackFragment;
        CustomSearchView searchView;
        if (str != null && (screenStackFragment = getScreenStackFragment()) != null && (searchView = screenStackFragment.getSearchView()) != null) {
            searchView.setText(str);
        }
    }

    private final void setToolbarElementsVisibility(int i) {
        ScreenStackHeaderConfig headerConfig = getHeaderConfig();
        int i2 = 0;
        int configSubviewsCount = headerConfig != null ? headerConfig.getConfigSubviewsCount() - 1 : 0;
        if (configSubviewsCount >= 0) {
            while (true) {
                ScreenStackHeaderConfig headerConfig2 = getHeaderConfig();
                ScreenStackHeaderSubview.Type type = null;
                ScreenStackHeaderSubview configSubview = headerConfig2 != null ? headerConfig2.getConfigSubview(i2) : null;
                if (configSubview != null) {
                    type = configSubview.getType();
                }
                if (!(type == ScreenStackHeaderSubview.Type.SEARCH_BAR || configSubview == null)) {
                    configSubview.setVisibility(i);
                }
                if (i2 != configSubviewsCount) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "", "(Ljava/lang/String;I)V", "NONE", "WORDS", "SENTENCES", "CHARACTERS", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SearchBarView.kt */
    public enum SearchBarAutoCapitalize {
        NONE,
        WORDS,
        SENTENCES,
        CHARACTERS;

        public static EnumEntries<SearchBarAutoCapitalize> getEntries() {
            return $ENTRIES;
        }

        static {
            SearchBarAutoCapitalize[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "", "(Ljava/lang/String;I)V", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "TEXT", "PHONE", "NUMBER", "EMAIL", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SearchBarView.kt */
    public enum SearchBarInputTypes {
        ;

        public static EnumEntries<SearchBarInputTypes> getEntries() {
            return $ENTRIES;
        }

        public abstract int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize);

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes$TEXT;", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: SearchBarView.kt */
        static final class TEXT extends SearchBarInputTypes {

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* compiled from: SearchBarView.kt */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

                /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
                static {
                    /*
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize[] r0 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.values()
                        int r0 = r0.length
                        int[] r0 = new int[r0]
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r1 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                        r2 = 1
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                    L_0x0010:
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r1 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.WORDS     // Catch:{ NoSuchFieldError -> 0x0019 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                        r2 = 2
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                    L_0x0019:
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r1 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.SENTENCES     // Catch:{ NoSuchFieldError -> 0x0022 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                        r2 = 3
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                    L_0x0022:
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r1 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.CHARACTERS     // Catch:{ NoSuchFieldError -> 0x002b }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                        r2 = 4
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                    L_0x002b:
                        $EnumSwitchMapping$0 = r0
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes.TEXT.WhenMappings.<clinit>():void");
                }
            }

            TEXT(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }

            public int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize) {
                Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "capitalize");
                int i = WhenMappings.$EnumSwitchMapping$0[searchBarAutoCapitalize.ordinal()];
                if (i == 1) {
                    return 1;
                }
                if (i == 2) {
                    return 8192;
                }
                if (i == 3) {
                    return 16384;
                }
                if (i == 4) {
                    return 4096;
                }
                throw new NoWhenBranchMatchedException();
            }
        }

        static {
            SearchBarInputTypes[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes$PHONE;", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: SearchBarView.kt */
        static final class PHONE extends SearchBarInputTypes {
            public int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize) {
                Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "capitalize");
                return 3;
            }

            PHONE(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes$NUMBER;", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: SearchBarView.kt */
        static final class NUMBER extends SearchBarInputTypes {
            public int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize) {
                Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "capitalize");
                return 2;
            }

            NUMBER(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes$EMAIL;", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: SearchBarView.kt */
        static final class EMAIL extends SearchBarInputTypes {
            public int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize) {
                Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "capitalize");
                return 32;
            }

            EMAIL(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }
        }
    }
}
