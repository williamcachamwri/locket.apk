package com.amplitude.api;

import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigManager {
    private static String KEY_INGESTION_ENDPOINT = "ingestionEndpoint";
    private static ConfigManager instance;
    private String ingestionEndpoint = Constants.EVENT_LOG_URL;

    interface RefreshListener {
        void onFinished();
    }

    public String getIngestionEndpoint() {
        return this.ingestionEndpoint;
    }

    private ConfigManager() {
    }

    public void refresh(RefreshListener refreshListener, AmplitudeServerZone amplitudeServerZone) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(AmplitudeServerZone.getDynamicConfigApi(amplitudeServerZone)).openConnection()));
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                bufferedReader.close();
                JSONObject jSONObject = new JSONObject(stringBuffer.toString());
                if (jSONObject.has(KEY_INGESTION_ENDPOINT)) {
                    this.ingestionEndpoint = "https://" + jSONObject.getString(KEY_INGESTION_ENDPOINT);
                }
            }
        } catch (IOException | Exception | MalformedURLException | JSONException unused) {
        }
        refreshListener.onFinished();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
}
