package com.brentvatne.react;

import com.brentvatne.common.toolbox.DebugLog;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0001J\u000e\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0001J\u000e\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0005R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0004j\b\u0012\u0004\u0012\u00020\u0001`\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/brentvatne/react/ReactNativeVideoManager;", "Lcom/brentvatne/react/RNVPlugin;", "()V", "instanceList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "pluginList", "onInstanceCreated", "", "id", "", "player", "onInstanceRemoved", "registerPlugin", "plugin", "registerView", "newInstance", "unregisterPlugin", "unregisterView", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactNativeVideoManager.kt */
public final class ReactNativeVideoManager implements RNVPlugin {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "ReactNativeVideoManager";
    /* access modifiers changed from: private */
    public static volatile ReactNativeVideoManager instance;
    private ArrayList<Object> instanceList = new ArrayList<>();
    private ArrayList<RNVPlugin> pluginList = new ArrayList<>();

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/brentvatne/react/ReactNativeVideoManager$Companion;", "", "()V", "TAG", "", "instance", "Lcom/brentvatne/react/ReactNativeVideoManager;", "getInstance", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ReactNativeVideoManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ReactNativeVideoManager getInstance() {
            ReactNativeVideoManager access$getInstance$cp = ReactNativeVideoManager.instance;
            if (access$getInstance$cp == null) {
                synchronized (this) {
                    access$getInstance$cp = ReactNativeVideoManager.instance;
                    if (access$getInstance$cp == null) {
                        access$getInstance$cp = new ReactNativeVideoManager();
                        Companion companion = ReactNativeVideoManager.Companion;
                        ReactNativeVideoManager.instance = access$getInstance$cp;
                    }
                }
            }
            return access$getInstance$cp;
        }
    }

    public final void registerView(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "newInstance");
        if (this.instanceList.size() > 2) {
            DebugLog.d(TAG, "multiple Video displayed ?");
        }
        this.instanceList.add(obj);
    }

    public final void unregisterView(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "newInstance");
        this.instanceList.remove(obj);
    }

    public final void registerPlugin(RNVPlugin rNVPlugin) {
        Intrinsics.checkNotNullParameter(rNVPlugin, "plugin");
        this.pluginList.add(rNVPlugin);
    }

    public final void unregisterPlugin(RNVPlugin rNVPlugin) {
        Intrinsics.checkNotNullParameter(rNVPlugin, "plugin");
        this.pluginList.remove(rNVPlugin);
    }

    public void onInstanceCreated(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(obj, "player");
        for (RNVPlugin onInstanceCreated : this.pluginList) {
            onInstanceCreated.onInstanceCreated(str, obj);
        }
    }

    public void onInstanceRemoved(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(obj, "player");
        for (RNVPlugin onInstanceRemoved : this.pluginList) {
            onInstanceRemoved.onInstanceRemoved(str, obj);
        }
    }
}
