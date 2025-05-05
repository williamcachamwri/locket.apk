package expo.modules.devlauncher.launcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.google.gson.Gson;
import expo.modules.manifests.core.Manifest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010\u000f\u001a\u00020\bJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lexpo/modules/devlauncher/launcher/DevLauncherRecentlyOpenedAppsRegistry;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "appWasOpened", "", "url", "", "queryParams", "", "manifest", "Lexpo/modules/manifests/core/Manifest;", "clearRegistry", "getMostRecentApp", "Lexpo/modules/devlauncher/launcher/DevLauncherAppEntry;", "getRecentlyOpenedApps", "", "TimeHelper", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherRecentlyOpenedAppsRegistry.kt */
public final class DevLauncherRecentlyOpenedAppsRegistry {
    private final SharedPreferences sharedPreferences;

    public DevLauncherRecentlyOpenedAppsRegistry(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("expo.modules.devlauncher.recentyopenedapps", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences2, "getSharedPreferences(...)");
        this.sharedPreferences = sharedPreferences2;
    }

    public final void appWasOpened(String str, Map<String, String> map, Manifest manifest) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(map, "queryParams");
        Map linkedHashMap = new LinkedHashMap();
        Uri parse = Uri.parse(str);
        if (this.sharedPreferences.contains(str)) {
            Map map2 = (Map) new Gson().fromJson(this.sharedPreferences.getString(str, (String) null), Map.class);
            Intrinsics.checkNotNull(map2);
            Map mutableMap = MapsKt.toMutableMap(map2);
            Intrinsics.checkNotNull(mutableMap, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any>");
            linkedHashMap = TypeIntrinsics.asMutableMap(mutableMap);
        }
        long currentTime = TimeHelper.INSTANCE.getCurrentTime();
        boolean z = parse.getHost() == "u.expo.dev" || Intrinsics.areEqual((Object) parse.getHost(), (Object) "staging-u.expo.dev");
        linkedHashMap.put("isEASUpdate", Boolean.valueOf(z));
        if (z && map.get("updateMessage") != null) {
            String str2 = map.get("updateMessage");
            Intrinsics.checkNotNull(str2, "null cannot be cast to non-null type kotlin.String");
            linkedHashMap.put("updateMessage", str2);
        }
        if (manifest != null) {
            String name = manifest.getName();
            Intrinsics.checkNotNull(name, "null cannot be cast to non-null type kotlin.String");
            linkedHashMap.put("name", name);
            JSONObject rawJson = manifest.getRawJson();
            if (z) {
                Object obj = rawJson.getJSONObject(TtmlNode.TAG_METADATA).get("branchName");
                if (obj == null) {
                    obj = "";
                }
                linkedHashMap.put("branchName", obj);
            }
        }
        linkedHashMap.put("timestamp", Long.valueOf(currentTime));
        linkedHashMap.put("url", str);
        this.sharedPreferences.edit().putString(str, new Gson().toJson((Object) linkedHashMap)).apply();
    }

    public final List<DevLauncherAppEntry> getRecentlyOpenedApps() {
        List<DevLauncherAppEntry> arrayList = new ArrayList<>();
        List<String> arrayList2 = new ArrayList<>();
        Gson gson = new Gson();
        Map<String, ?> all = this.sharedPreferences.getAll();
        Intrinsics.checkNotNullExpressionValue(all, "getAll(...)");
        for (Map.Entry next : all.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.String");
            DevLauncherAppEntry devLauncherAppEntry = (DevLauncherAppEntry) gson.fromJson((String) value, DevLauncherAppEntry.class);
            if (TimeHelper.INSTANCE.getCurrentTime() - devLauncherAppEntry.getTimestamp() > 259200000) {
                Intrinsics.checkNotNull(str);
                arrayList2.add(str);
            } else {
                Intrinsics.checkNotNull(devLauncherAppEntry);
                arrayList.add(devLauncherAppEntry);
            }
        }
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        for (String remove : arrayList2) {
            edit.remove(remove);
        }
        edit.apply();
        return arrayList;
    }

    public final DevLauncherAppEntry getMostRecentApp() {
        List<DevLauncherAppEntry> recentlyOpenedApps = getRecentlyOpenedApps();
        Object obj = null;
        if (!(!recentlyOpenedApps.isEmpty())) {
            return null;
        }
        Iterator it = recentlyOpenedApps.iterator();
        if (it.hasNext()) {
            obj = it.next();
            if (it.hasNext()) {
                long timestamp = ((DevLauncherAppEntry) obj).getTimestamp();
                do {
                    Object next = it.next();
                    long timestamp2 = ((DevLauncherAppEntry) next).getTimestamp();
                    if (timestamp < timestamp2) {
                        obj = next;
                        timestamp = timestamp2;
                    }
                } while (it.hasNext());
            }
        }
        return (DevLauncherAppEntry) obj;
    }

    public final void clearRegistry() {
        this.sharedPreferences.edit().clear().apply();
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/devlauncher/launcher/DevLauncherRecentlyOpenedAppsRegistry$TimeHelper;", "", "()V", "getCurrentTime", "", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevLauncherRecentlyOpenedAppsRegistry.kt */
    public static final class TimeHelper {
        public static final TimeHelper INSTANCE = new TimeHelper();

        private TimeHelper() {
        }

        public final long getCurrentTime() {
            return System.currentTimeMillis();
        }
    }
}
