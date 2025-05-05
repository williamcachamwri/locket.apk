package com.adsbynimbus.render.mraid;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.StaticAdController;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.devlauncher.launcher.manifest.DevLauncherOrientation;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.StringFormat;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001a \u0010\u0013\u001a\u00020\u0001*\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0012H\u0000\u001a2\u0010\u0018\u001a\u00020\u0010*\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0012H\u0000\u001a\u0016\u0010\u001c\u001a\u00020\u0001*\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0000\u001a\u001c\u0010\u001e\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"AUDIO_VOLUME_CHANGE", "", "DEFAULT", "ERROR", "EXPANDED", "EXPOSURE_CHANGE", "HIDDEN", "INLINE", "INTERSTITIAL", "LOADING", "READY", "RESIZED", "SIZE_CHANGE", "STATE_CHANGE", "VIEWABLE_CHANGE", "initJs", "Lcom/adsbynimbus/render/mraid/Host;", "includeReady", "", "initMraid", "Lcom/adsbynimbus/render/StaticAdController;", "position", "Lcom/adsbynimbus/render/mraid/Position;", "viewable", "mraidHost", "placementType", "maxSize", "Lcom/adsbynimbus/render/mraid/Size;", "onMraidCommand", "json", "updateExposure", "exposure", "", "visibleRect", "static_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Host.kt */
public final class HostKt {
    public static final String AUDIO_VOLUME_CHANGE = "audioVolumeChange";
    public static final String DEFAULT = "default";
    public static final String ERROR = "error";
    public static final String EXPANDED = "expanded";
    public static final String EXPOSURE_CHANGE = "exposureChange";
    public static final String HIDDEN = "hidden";
    public static final String INLINE = "inline";
    public static final String INTERSTITIAL = "interstitial";
    public static final String LOADING = "loading";
    public static final String READY = "ready";
    public static final String RESIZED = "resized";
    public static final String SIZE_CHANGE = "sizeChange";
    public static final String STATE_CHANGE = "stateChange";
    public static final String VIEWABLE_CHANGE = "viewableChange";

    public static /* synthetic */ Host mraidHost$default(StaticAdController staticAdController, String str, Size size, Position position, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            View view = staticAdController.getView();
            DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
            Intrinsics.checkNotNullExpressionValue(displayMetrics, "_get_maxSize_$lambda$2");
            size = new Size(EnvironmentKt.pxToDp(displayMetrics, view.getRootView().getWidth()), EnvironmentKt.pxToDp(displayMetrics, view.getRootView().getHeight()));
        }
        if ((i & 4) != 0) {
            View view2 = staticAdController.getView();
            DisplayMetrics displayMetrics2 = view2.getResources().getDisplayMetrics();
            Intrinsics.checkNotNullExpressionValue(displayMetrics2, "_get_position_$lambda$34");
            position = new Position(EnvironmentKt.pxToDp(displayMetrics2, view2.getWidth()), EnvironmentKt.pxToDp(displayMetrics2, view2.getHeight()), EnvironmentKt.pxToDp(displayMetrics2, view2.getLeft()), EnvironmentKt.pxToDp(displayMetrics2, view2.getTop()));
        }
        if ((i & 8) != 0) {
            z = staticAdController.getView().isVisibleInWindow() && staticAdController.getView().getGlobalVisibleRect(new Rect());
        }
        return mraidHost(staticAdController, str, size, position, z);
    }

    public static final Host mraidHost(StaticAdController staticAdController, String str, Size size, Position position, boolean z) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(staticAdController, "<this>");
        Intrinsics.checkNotNullParameter(str2, "placementType");
        Intrinsics.checkNotNullParameter(size, "maxSize");
        Intrinsics.checkNotNullParameter(position, ViewProps.POSITION);
        Context context = staticAdController.getView().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "view.context");
        AppOrientation appOrientation = new AppOrientation(context.getResources().getConfiguration().orientation == 2 ? DevLauncherOrientation.LANDSCAPE : DevLauncherOrientation.PORTRAIT, true);
        DisplayMetrics displayMetrics = staticAdController.getView().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "_get_screenSize_$lambda$1");
        return new Host(appOrientation, position, z, str, size, new Size(EnvironmentKt.pxToDp(displayMetrics, displayMetrics.widthPixels), EnvironmentKt.pxToDp(displayMetrics, displayMetrics.heightPixels)), (OrientationProperties) null, (ResizeProperties) null, position, LOADING, new ExpandProperties(size.getWidth(), size.getHeight(), Intrinsics.areEqual((Object) str2, (Object) INTERSTITIAL), false, 8, (DefaultConstructorMarker) null), MapsKt.mapOf(TuplesKt.to("inlineVideo", true)), "3.0", 192, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ String initMraid$default(StaticAdController staticAdController, Position position, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            View view = staticAdController.getView();
            DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
            Intrinsics.checkNotNullExpressionValue(displayMetrics, "_get_position_$lambda$34");
            position = new Position(EnvironmentKt.pxToDp(displayMetrics, view.getWidth()), EnvironmentKt.pxToDp(displayMetrics, view.getHeight()), EnvironmentKt.pxToDp(displayMetrics, view.getLeft()), EnvironmentKt.pxToDp(displayMetrics, view.getTop()));
        }
        if ((i & 2) != 0) {
            z = staticAdController.getView().isVisibleInWindow() && staticAdController.getView().getGlobalVisibleRect(new Rect());
        }
        return initMraid(staticAdController, position, z);
    }

    public static final String initMraid(StaticAdController staticAdController, Position position, boolean z) {
        Intrinsics.checkNotNullParameter(staticAdController, "<this>");
        Intrinsics.checkNotNullParameter(position, ViewProps.POSITION);
        StringBuilder sb = new StringBuilder();
        staticAdController.setMraidInitialized(true);
        Host mraidHost = staticAdController.getMraidHost();
        mraidHost.CurrentPosition = position;
        mraidHost.DefaultPosition = mraidHost.CurrentPosition;
        mraidHost.State = "default";
        mraidHost.isViewable = z;
        CommandKt.updatePosition$default(sb, position, false, 2, (Object) null);
        CommandKt.updateState(sb, "default");
        CommandKt.updateProperty(sb, "isViewable", String.valueOf(z));
        CommandKt.dispatchStateChange(sb, "default");
        CommandKt.dispatch(sb, READY, new String[0]);
        return sb.toString();
    }

    public static final String onMraidCommand(StaticAdController staticAdController, String str) {
        boolean z;
        Object obj;
        Intrinsics.checkNotNullParameter(staticAdController, "<this>");
        StringBuilder sb = new StringBuilder();
        Host mraidHost = staticAdController.getMraidHost();
        if (!SetsKt.setOf("hidden", LOADING).contains(mraidHost.State)) {
            Object obj2 = null;
            if (str != null) {
                try {
                    Result.Companion companion = Result.Companion;
                    obj = Result.m2444constructorimpl((Command) CommandKt.getMraidSerializer().decodeFromString(Command.Companion.serializer(), str));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
                }
                Throwable r3 = Result.m2447exceptionOrNullimpl(obj);
                if (r3 != null) {
                    Logger.log(5, r3.getMessage());
                }
                if (!Result.m2450isFailureimpl(obj)) {
                    obj2 = obj;
                }
                obj2 = (Command) obj2;
            }
            if (obj2 instanceof ExposureChange) {
                int exposure = staticAdController.getView().getExposure();
                Rect visibleRect = staticAdController.getView().getVisibleRect();
                CommandKt.dispatchExposureChange(sb, exposure, new Position(visibleRect.width(), visibleRect.height(), visibleRect.left, visibleRect.top));
            } else if (obj2 instanceof Close) {
                ControllerKt.close(staticAdController);
            } else if (obj2 instanceof Expand) {
                if (Intrinsics.areEqual((Object) mraidHost.PlacementType, (Object) INLINE) && !Intrinsics.areEqual((Object) mraidHost.State, (Object) EXPANDED)) {
                    ControllerKt.expand(staticAdController);
                }
            } else if (obj2 instanceof Open) {
                Uri parse = Uri.parse(((Open) obj2).getUrl());
                Intrinsics.checkNotNullExpressionValue(parse, "parse(command.url)");
                staticAdController.openClickThrough$static_release(parse);
            } else if (obj2 instanceof Unload) {
                staticAdController.destroy();
            } else if (obj2 instanceof Resize) {
                if (Intrinsics.areEqual((Object) mraidHost.PlacementType, (Object) INLINE)) {
                    if (Intrinsics.areEqual((Object) mraidHost.State, (Object) EXPANDED)) {
                        CommandKt.dispatchError(sb, "invalid state");
                    } else if (mraidHost.ResizeProperties == null) {
                        CommandKt.dispatchError(sb, "calling resize without setting properties");
                    } else {
                        ControllerKt.resize(staticAdController);
                    }
                }
            } else if (obj2 instanceof SetExpandProperties) {
                SetExpandProperties setExpandProperties = (SetExpandProperties) obj2;
                mraidHost.ExpandProperties = setExpandProperties.getProperties();
                StringFormat mraidSerializer = CommandKt.getMraidSerializer();
                ExpandProperties properties = setExpandProperties.getProperties();
                mraidSerializer.getSerializersModule();
                CommandKt.updateProperty(sb, "ExpandProperties", mraidSerializer.encodeToString(ExpandProperties.Companion.serializer(), properties));
            } else if (obj2 instanceof SetOrientationProperties) {
                SetOrientationProperties setOrientationProperties = (SetOrientationProperties) obj2;
                mraidHost.OrientationProperties = setOrientationProperties.getProperties();
                StringFormat mraidSerializer2 = CommandKt.getMraidSerializer();
                OrientationProperties properties2 = setOrientationProperties.getProperties();
                mraidSerializer2.getSerializersModule();
                CommandKt.updateProperty(sb, "OrientationProperties", mraidSerializer2.encodeToString(OrientationProperties.Companion.serializer(), properties2));
            } else if (obj2 instanceof SetResizeProperties) {
                SetResizeProperties setResizeProperties = (SetResizeProperties) obj2;
                if (PropertiesKt.isValidFor(setResizeProperties.getProperties(), mraidHost.MaxSize)) {
                    mraidHost.ResizeProperties = setResizeProperties.getProperties();
                    StringFormat mraidSerializer3 = CommandKt.getMraidSerializer();
                    ResizeProperties properties3 = setResizeProperties.getProperties();
                    mraidSerializer3.getSerializersModule();
                    CommandKt.updateProperty(sb, "ResizeProperties", mraidSerializer3.encodeToString(ResizeProperties.Companion.serializer(), properties3));
                } else {
                    CommandKt.dispatchError(sb, "invalid resize properties");
                }
            } else {
                boolean z2 = true;
                if (obj2 instanceof StorePicture) {
                    z = true;
                } else {
                    z = obj2 instanceof PlayVideo;
                }
                if (!z) {
                    z2 = obj2 instanceof CreateCalendarEvent;
                }
                if (z2) {
                    CommandKt.dispatchError(sb, "not supported");
                } else {
                    CommandKt.dispatchError(sb, "invalid command");
                }
            }
        }
        return sb.toString();
    }

    public static final String updateExposure(Host host, int i, Position position) {
        Intrinsics.checkNotNullParameter(host, "<this>");
        Intrinsics.checkNotNullParameter(position, "visibleRect");
        StringBuilder sb = new StringBuilder();
        if (!Intrinsics.areEqual((Object) host.State, (Object) LOADING)) {
            if (i == 0 && host.isViewable) {
                host.isViewable = false;
                CommandKt.updateProperty(sb, "isViewable", Constants.CASEFIRST_FALSE);
                CommandKt.dispatchExposureChange(sb, i, position);
                CommandKt.dispatch(sb, VIEWABLE_CHANGE, Constants.CASEFIRST_FALSE);
            } else if (i <= 0 || host.isViewable) {
                CommandKt.dispatchExposureChange(sb, i, position);
            } else {
                host.isViewable = true;
                CommandKt.updateProperty(sb, "isViewable", "true");
                CommandKt.dispatchExposureChange(sb, i, position);
                CommandKt.dispatch(sb, VIEWABLE_CHANGE, "true");
            }
        }
        return sb.toString();
    }

    public static final String initJs(Host host, boolean z) {
        Intrinsics.checkNotNullParameter(host, "<this>");
        StringBuilder sb = new StringBuilder("window.MRAID_ENV=window.top.MRAID_ENV;mraid.b=window.top.Adsbynimbus;");
        sb.append("Object.assign(mraid.h," + CommandKt.getMraidSerializer().encodeToString(Host.Companion.serializer(), host) + ");");
        if (z) {
            sb.append("mraid.b.postMessage('ready');");
        }
        return sb.toString();
    }
}
