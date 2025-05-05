package expo.modules.updatesinterface;

import android.content.Context;
import java.util.HashMap;
import org.json.JSONObject;

public interface UpdatesInterface {

    public interface Update {
        String getLaunchAssetPath();

        JSONObject getManifest();
    }

    public interface UpdateCallback {
        void onFailure(Exception exc);

        boolean onManifestLoaded(JSONObject jSONObject);

        void onProgress(int i, int i2, int i3);

        void onSuccess(Update update);
    }

    void fetchUpdateWithConfiguration(HashMap<String, Object> hashMap, Context context, UpdateCallback updateCallback);

    void reset();
}
