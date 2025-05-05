package com.adsbynimbus.render.internal;

import android.view.View;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.AdController;
import com.adsbynimbus.render.AdEvent;
import com.adsbynimbus.render.R;
import com.iab.omid.library.adsbynimbus.adsession.AdEvents;
import com.iab.omid.library.adsbynimbus.adsession.AdSession;
import com.iab.omid.library.adsbynimbus.adsession.AdSessionConfiguration;
import com.iab.omid.library.adsbynimbus.adsession.CreativeType;
import com.iab.omid.library.adsbynimbus.adsession.ErrorType;
import com.iab.omid.library.adsbynimbus.adsession.FriendlyObstructionPurpose;
import com.iab.omid.library.adsbynimbus.adsession.Owner;
import com.iab.omid.library.adsbynimbus.adsession.VerificationScriptResource;
import com.iab.omid.library.adsbynimbus.adsession.media.InteractionType;
import com.iab.omid.library.adsbynimbus.adsession.media.MediaEvents;
import com.iab.omid.library.adsbynimbus.adsession.media.Position;
import com.iab.omid.library.adsbynimbus.adsession.media.VastProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0016J\u0010\u00101\u001a\u00020.2\u0006\u00102\u001a\u000203H\u0016R\u001b\u0010\n\u001a\u00020\u000b8FX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168FX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001d\u0010\"\u001a\u0004\u0018\u00010#8FX\u0002¢\u0006\f\n\u0004\b&\u0010\u000f\u001a\u0004\b$\u0010%R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u00064"}, d2 = {"Lcom/adsbynimbus/render/internal/OMSession;", "Lcom/adsbynimbus/render/AdController$Listener;", "creativeType", "Lcom/iab/omid/library/adsbynimbus/adsession/CreativeType;", "verificationScripts", "", "Lcom/iab/omid/library/adsbynimbus/adsession/VerificationScriptResource;", "controller", "Lcom/adsbynimbus/render/AdController;", "(Lcom/iab/omid/library/adsbynimbus/adsession/CreativeType;Ljava/util/List;Lcom/adsbynimbus/render/AdController;)V", "adEvents", "Lcom/iab/omid/library/adsbynimbus/adsession/AdEvents;", "getAdEvents", "()Lcom/iab/omid/library/adsbynimbus/adsession/AdEvents;", "adEvents$delegate", "Lkotlin/Lazy;", "adSession", "Lcom/iab/omid/library/adsbynimbus/adsession/AdSession;", "getAdSession", "()Lcom/iab/omid/library/adsbynimbus/adsession/AdSession;", "adSession$delegate", "configuration", "Lcom/iab/omid/library/adsbynimbus/adsession/AdSessionConfiguration;", "getConfiguration", "()Lcom/iab/omid/library/adsbynimbus/adsession/AdSessionConfiguration;", "configuration$delegate", "getController", "()Lcom/adsbynimbus/render/AdController;", "didStart", "", "getDidStart", "()Z", "setDidStart", "(Z)V", "mediaEvents", "Lcom/iab/omid/library/adsbynimbus/adsession/media/MediaEvents;", "getMediaEvents", "()Lcom/iab/omid/library/adsbynimbus/adsession/media/MediaEvents;", "mediaEvents$delegate", "mediaEventsOwner", "Lcom/iab/omid/library/adsbynimbus/adsession/Owner;", "getMediaEventsOwner", "()Lcom/iab/omid/library/adsbynimbus/adsession/Owner;", "getVerificationScripts", "()Ljava/util/List;", "onAdEvent", "", "adEvent", "Lcom/adsbynimbus/render/AdEvent;", "onError", "error", "Lcom/adsbynimbus/NimbusError;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OpenMeasurement.kt */
public final class OMSession implements AdController.Listener {
    private final Lazy adEvents$delegate;
    private final Lazy adSession$delegate;
    private final Lazy configuration$delegate;
    private final AdController controller;
    private boolean didStart;
    private final Lazy mediaEvents$delegate;
    private final Owner mediaEventsOwner;
    private final List<VerificationScriptResource> verificationScripts;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OpenMeasurement.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|25) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.adsbynimbus.render.AdEvent[] r0 = com.adsbynimbus.render.AdEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.LOADED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.IMPRESSION     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.CLICKED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.VOLUME_CHANGED     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.PAUSED     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.RESUMED     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.FIRST_QUARTILE     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.MIDPOINT     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.THIRD_QUARTILE     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.COMPLETED     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.DESTROYED     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.OMSession.WhenMappings.<clinit>():void");
        }
    }

    public OMSession(CreativeType creativeType, List<VerificationScriptResource> list, AdController adController) {
        Intrinsics.checkNotNullParameter(creativeType, "creativeType");
        Intrinsics.checkNotNullParameter(list, "verificationScripts");
        Intrinsics.checkNotNullParameter(adController, "controller");
        this.verificationScripts = list;
        this.controller = adController;
        this.mediaEventsOwner = creativeType == CreativeType.VIDEO ? Owner.NATIVE : Owner.NONE;
        this.configuration$delegate = LazyKt.lazy(new OMSession$configuration$2(creativeType, this));
        this.adSession$delegate = LazyKt.lazy(new OMSession$adSession$2(this, creativeType));
        this.adEvents$delegate = LazyKt.lazy(new OMSession$adEvents$2(this));
        this.mediaEvents$delegate = LazyKt.lazy(new OMSession$mediaEvents$2(creativeType, this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OMSession(CreativeType creativeType, List list, AdController adController, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(creativeType, (i & 2) != 0 ? new ArrayList() : list, adController);
    }

    public final List<VerificationScriptResource> getVerificationScripts() {
        return this.verificationScripts;
    }

    public final AdController getController() {
        return this.controller;
    }

    public final Owner getMediaEventsOwner() {
        return this.mediaEventsOwner;
    }

    public final AdSessionConfiguration getConfiguration() {
        Object value = this.configuration$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-configuration>(...)");
        return (AdSessionConfiguration) value;
    }

    public final boolean getDidStart() {
        return this.didStart;
    }

    public final void setDidStart(boolean z) {
        this.didStart = z;
    }

    public final AdSession getAdSession() {
        Object value = this.adSession$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-adSession>(...)");
        return (AdSession) value;
    }

    public final AdEvents getAdEvents() {
        Object value = this.adEvents$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-adEvents>(...)");
        return (AdEvents) value;
    }

    public final MediaEvents getMediaEvents() {
        return (MediaEvents) this.mediaEvents$delegate.getValue();
    }

    public void onAdEvent(AdEvent adEvent) {
        Pair pair;
        MediaEvents mediaEvents;
        Intrinsics.checkNotNullParameter(adEvent, "adEvent");
        try {
            switch (WhenMappings.$EnumSwitchMapping$0[adEvent.ordinal()]) {
                case 1:
                    if (!this.didStart) {
                        Unit unit = null;
                        VastProperties createVastPropertiesForNonSkippableMedia = getMediaEvents() != null ? VastProperties.createVastPropertiesForNonSkippableMedia(true, Position.STANDALONE) : null;
                        AdEvents adEvents = getAdEvents();
                        getAdSession().registerAdView(this.controller.getView());
                        for (View view : this.controller.friendlyObstructions()) {
                            if (view.getId() == R.id.nimbus_mute) {
                                pair = new Pair(FriendlyObstructionPurpose.VIDEO_CONTROLS, "Mute Button");
                            } else if (view.getId() == R.id.nimbus_close) {
                                pair = new Pair(FriendlyObstructionPurpose.CLOSE_AD, "Close Button");
                            } else {
                                if (!(view.getAlpha() == 0.0f)) {
                                    if (view.getVisibility() == 0) {
                                        Object tag = view.getTag(R.id.nimbus_obstruction);
                                        FriendlyObstructionPurpose friendlyObstructionPurpose = tag instanceof FriendlyObstructionPurpose ? (FriendlyObstructionPurpose) tag : null;
                                        pair = friendlyObstructionPurpose != null ? new Pair(friendlyObstructionPurpose, view.getContentDescription().toString()) : null;
                                    }
                                }
                                pair = new Pair(FriendlyObstructionPurpose.NOT_VISIBLE, "Invisible");
                            }
                            if (pair != null) {
                                getAdSession().addFriendlyObstruction(view, (FriendlyObstructionPurpose) pair.getFirst(), (String) pair.getSecond());
                                Unit unit2 = Unit.INSTANCE;
                                StringBuilder append = new StringBuilder().append("Registered ");
                                String lowerCase = ((String) pair.getSecond()).toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                Logger.log(2, append.append(lowerCase).append(" friendly obstruction").toString());
                            }
                        }
                        getAdSession().start();
                        if (createVastPropertiesForNonSkippableMedia != null) {
                            adEvents.loaded(createVastPropertiesForNonSkippableMedia);
                            unit = Unit.INSTANCE;
                        }
                        if (unit == null) {
                            adEvents.loaded();
                        }
                        this.didStart = true;
                        return;
                    }
                    return;
                case 2:
                    if (this.didStart) {
                        MediaEvents mediaEvents2 = getMediaEvents();
                        if (mediaEvents2 != null) {
                            mediaEvents2.start(this.controller.getDuration(), ((float) this.controller.getVolume()) / 100.0f);
                        }
                        getAdEvents().impressionOccurred();
                        return;
                    }
                    return;
                case 3:
                    MediaEvents mediaEvents3 = getMediaEvents();
                    if (mediaEvents3 != null) {
                        mediaEvents3.adUserInteraction(InteractionType.CLICK);
                        return;
                    }
                    return;
                case 4:
                    if (this.didStart && (mediaEvents = getMediaEvents()) != null) {
                        mediaEvents.volumeChange(((float) this.controller.getVolume()) / 100.0f);
                        return;
                    }
                    return;
                case 5:
                    MediaEvents mediaEvents4 = getMediaEvents();
                    if (mediaEvents4 != null) {
                        mediaEvents4.pause();
                        return;
                    }
                    return;
                case 6:
                    MediaEvents mediaEvents5 = getMediaEvents();
                    if (mediaEvents5 != null) {
                        mediaEvents5.resume();
                        return;
                    }
                    return;
                case 7:
                    MediaEvents mediaEvents6 = getMediaEvents();
                    if (mediaEvents6 != null) {
                        mediaEvents6.firstQuartile();
                        return;
                    }
                    return;
                case 8:
                    MediaEvents mediaEvents7 = getMediaEvents();
                    if (mediaEvents7 != null) {
                        mediaEvents7.midpoint();
                        return;
                    }
                    return;
                case 9:
                    MediaEvents mediaEvents8 = getMediaEvents();
                    if (mediaEvents8 != null) {
                        mediaEvents8.thirdQuartile();
                        return;
                    }
                    return;
                case 10:
                    MediaEvents mediaEvents9 = getMediaEvents();
                    if (mediaEvents9 != null) {
                        mediaEvents9.complete();
                        return;
                    }
                    return;
                case 11:
                    if (this.didStart) {
                        getAdSession().finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            Logger.log(5, e.toString());
        }
    }

    public void onError(NimbusError nimbusError) {
        Object obj;
        Intrinsics.checkNotNullParameter(nimbusError, "error");
        try {
            Result.Companion companion = Result.Companion;
            OMSession oMSession = this;
            if (this.didStart) {
                getAdSession().error(ErrorType.GENERIC, nimbusError.getMessage());
            }
            obj = Result.m2444constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r3 = Result.m2447exceptionOrNullimpl(obj);
        if (r3 != null) {
            Logger.log(5, r3.toString());
        }
    }
}
