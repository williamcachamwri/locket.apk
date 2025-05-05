package com.locket.Locket.Ads;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.adsbynimbus.NimbusAdManager;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.render.AdController;
import com.adsbynimbus.render.AdEvent;
import com.adsbynimbus.request.NimbusRequest;
import com.adsbynimbus.request.NimbusResponse;
import expo.modules.devlauncher.launcher.manifest.DevLauncherOrientation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class NimbusAdLoader {
    private static final String TAG = "NimbusAdLoader";
    private static final ExecutorService TRACKER_EXECUTOR = Executors.newFixedThreadPool(5);
    private final NimbusAdManager adManager;
    /* access modifiers changed from: private */
    public final NimbusAnalytics analytics;
    /* access modifiers changed from: private */
    public AdController controller;
    /* access modifiers changed from: private */
    public final ReactAdEventEmitter emitter;
    /* access modifiers changed from: private */
    public NimbusResponse response;

    public NimbusAdLoader(NimbusAdManager nimbusAdManager, NimbusAnalytics nimbusAnalytics, ReactAdEventEmitter reactAdEventEmitter) {
        this.adManager = nimbusAdManager;
        this.analytics = nimbusAnalytics;
        this.emitter = reactAdEventEmitter;
    }

    public void load(Activity activity, final FrameLayout frameLayout, final int i, final String str) {
        this.analytics.logAdRequested(i, NimbusAdRequestType.NORMAL, false, false);
        this.adManager.showAd(NimbusRequest.forInterstitialAd(str), frameLayout, new NimbusAdManager.Listener() {
            public void onAdResponse(NimbusResponse nimbusResponse) {
                Log.d(NimbusAdLoader.TAG, "onAdResponse: " + nimbusResponse);
                NimbusAdLoader.this.response = nimbusResponse;
                NimbusAdLoader.this.analytics.logAdRequestCompleted(i, NimbusAdRequestType.NORMAL, false, NimbusAdResponseType.SUCCESS, (Integer) null, (String) null, NimbusAdLoader.getAdResult(nimbusResponse));
            }

            public void onAdRendered(AdController adController) {
                adController.setVolume(0);
                NimbusAdLoader.this.controller = adController;
                if (NimbusAdLoader.this.response != null) {
                    HashMap<String, Object> adResult = NimbusAdLoader.getAdResult(NimbusAdLoader.this.response);
                    String r6 = NimbusAdLoader.getAspectRatio(NimbusAdLoader.this.response);
                    NimbusAdLoader nimbusAdLoader = NimbusAdLoader.this;
                    nimbusAdLoader.bindImpressionAndClick(adController, nimbusAdLoader.response, str, adResult, r6, i, frameLayout);
                    NimbusAdLoader.this.emitter.emitRendered(frameLayout.getId());
                    FrameLayout frameLayout = frameLayout;
                    frameLayout.post(new NimbusAdLoader$1$$ExternalSyntheticLambda0(this, frameLayout, adResult));
                }
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onAdRendered$0(FrameLayout frameLayout, HashMap hashMap) {
                View childAt = frameLayout.getChildCount() > 0 ? frameLayout.getChildAt(0) : null;
                if (childAt != null && childAt.getWidth() > 0 && childAt.getHeight() > 0) {
                    NimbusAdLoader.this.emitter.emitReady(frameLayout.getId());
                    NimbusAdLoader.this.analytics.logAdServed(hashMap, (Map<String, Object>) null);
                }
            }

            public void onError(NimbusError nimbusError) {
                NimbusAdLoader.this.analytics.logAdRequestCompleted(i, NimbusAdRequestType.NORMAL, false, NimbusAdResponseType.ERROR, (Integer) null, nimbusError.errorType.toString(), NimbusAdLoader.getAdResult(NimbusAdLoader.this.response));
                NimbusAdLoader.this.emitter.emitError(frameLayout.getId(), nimbusError.toString(), nimbusError.errorType);
            }
        });
    }

    public AdController getController() {
        return this.controller;
    }

    public static HashMap<String, Object> getAdResult(NimbusResponse nimbusResponse) {
        if (nimbusResponse == null) {
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("auction_id", nimbusResponse.getAuctionId());
        hashMap.put("auction_type", nimbusResponse.type());
        hashMap.put("network", nimbusResponse.bid.network);
        hashMap.put("content_type", nimbusResponse.bid.content_type);
        hashMap.put("is_interstitial", true);
        return hashMap;
    }

    /* access modifiers changed from: private */
    public static String getAspectRatio(NimbusResponse nimbusResponse) {
        int i = nimbusResponse.bid.width;
        int i2 = nimbusResponse.bid.height;
        if (i == 0 || i2 == 0) {
            return "unknown";
        }
        if (i > i2) {
            return DevLauncherOrientation.LANDSCAPE;
        }
        return i2 > i ? DevLauncherOrientation.PORTRAIT : "square";
    }

    /* access modifiers changed from: private */
    public void bindImpressionAndClick(AdController adController, NimbusResponse nimbusResponse, String str, HashMap<String, Object> hashMap, String str2, int i, FrameLayout frameLayout) {
        NimbusResponse nimbusResponse2 = nimbusResponse;
        final Collection<String> trackersForEvent = nimbusResponse.trackersForEvent(AdEvent.IMPRESSION);
        final Collection<String> trackersForEvent2 = nimbusResponse.trackersForEvent(AdEvent.CLICKED);
        if ((trackersForEvent != null && !trackersForEvent.isEmpty()) || (trackersForEvent2 != null && !trackersForEvent2.isEmpty())) {
            final String str3 = str;
            final HashMap<String, Object> hashMap2 = hashMap;
            final String str4 = str2;
            final int i2 = i;
            final FrameLayout frameLayout2 = frameLayout;
            adController.listeners.add(new AdController.Listener() {
                public void onAdEvent(AdEvent adEvent) {
                    if (adEvent == AdEvent.IMPRESSION && trackersForEvent != null) {
                        NimbusAdLoader.this.analytics.logAdViewed(str3, hashMap2, str4, Integer.valueOf(i2), (String) null, (Integer) null);
                        NimbusAdLoader.this.emitter.emitImpression(frameLayout2.getId());
                        NimbusAdLoader.fire(trackersForEvent);
                    } else if (adEvent == AdEvent.CLICKED && trackersForEvent2 != null) {
                        NimbusAdLoader.this.analytics.logAdClicked(str3, hashMap2, (String) null, Integer.valueOf(i2), (String) null, (Integer) null);
                        NimbusAdLoader.this.emitter.emitClick(frameLayout2.getId());
                        NimbusAdLoader.fire(trackersForEvent2);
                    }
                }

                public void onError(NimbusError nimbusError) {
                    NimbusAdLoader.this.emitter.emitError(frameLayout2.getId(), nimbusError.toString(), nimbusError.errorType);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void fire(Collection<String> collection) {
        collection.forEach(new NimbusAdLoader$$ExternalSyntheticLambda1());
    }

    /* access modifiers changed from: private */
    public static void hit(String str) {
        TRACKER_EXECUTOR.execute(new NimbusAdLoader$$ExternalSyntheticLambda0(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void lambda$hit$0(java.lang.String r6) {
        /*
            java.lang.String r0 = "NimbusAdLoader"
            java.lang.String r1 = "Tracker "
            java.lang.String r2 = "Tracker failed "
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ IOException -> 0x0051 }
            r4.<init>(r6)     // Catch:{ IOException -> 0x0051 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ IOException -> 0x0051 }
            java.lang.Object r4 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r4)     // Catch:{ IOException -> 0x0051 }
            java.net.URLConnection r4 = (java.net.URLConnection) r4     // Catch:{ IOException -> 0x0051 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ IOException -> 0x0051 }
            java.lang.String r3 = "GET"
            r4.setRequestMethod(r3)     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            r3 = 3000(0xbb8, float:4.204E-42)
            r4.setConnectTimeout(r3)     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            r4.setReadTimeout(r3)     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            int r3 = r4.getResponseCode()     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            java.lang.StringBuilder r1 = r5.append(r6)     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            java.lang.String r5 = " ➜ "
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            android.util.Log.d(r0, r1)     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            if (r4 == 0) goto L_0x0067
            r4.disconnect()
            goto L_0x0067
        L_0x0049:
            r6 = move-exception
            r3 = r4
            goto L_0x0068
        L_0x004c:
            r1 = move-exception
            r3 = r4
            goto L_0x0052
        L_0x004f:
            r6 = move-exception
            goto L_0x0068
        L_0x0051:
            r1 = move-exception
        L_0x0052:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x004f }
            r4.<init>(r2)     // Catch:{ all -> 0x004f }
            java.lang.StringBuilder r6 = r4.append(r6)     // Catch:{ all -> 0x004f }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x004f }
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r6, r1)     // Catch:{ all -> 0x004f }
            if (r3 == 0) goto L_0x0067
            r3.disconnect()
        L_0x0067:
            return
        L_0x0068:
            if (r3 == 0) goto L_0x006d
            r3.disconnect()
        L_0x006d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.locket.Locket.Ads.NimbusAdLoader.lambda$hit$0(java.lang.String):void");
    }
}
