package com.adsbynimbus.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.internal.Platform;
import com.adsbynimbus.openrtb.request.App;
import com.adsbynimbus.openrtb.request.Asset;
import com.adsbynimbus.openrtb.request.Banner;
import com.adsbynimbus.openrtb.request.BidRequest;
import com.adsbynimbus.openrtb.request.Data;
import com.adsbynimbus.openrtb.request.Extension;
import com.adsbynimbus.openrtb.request.Format;
import com.adsbynimbus.openrtb.request.Impression;
import com.adsbynimbus.openrtb.request.Native;
import com.adsbynimbus.openrtb.request.NimbusNative;
import com.adsbynimbus.openrtb.request.Publisher;
import com.adsbynimbus.openrtb.request.Regs;
import com.adsbynimbus.openrtb.request.User;
import com.adsbynimbus.openrtb.request.Video;
import com.adsbynimbus.render.CompanionAd;
import com.adsbynimbus.request.NimbusRequest;
import com.adsbynimbus.request.internal.RequestExtensionsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lcom/adsbynimbus/request/NimbusRequest;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.request.RequestExtensions$build$2", f = "RequestExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RequestExtensions.kt */
final class RequestExtensions$build$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NimbusRequest>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $manufacturer;
    final /* synthetic */ String $model;
    final /* synthetic */ String $osVersion;
    final /* synthetic */ SharedPreferences $preferences;
    final /* synthetic */ NimbusRequest $this_build;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RequestExtensions$build$2(Context context, NimbusRequest nimbusRequest, String str, String str2, String str3, SharedPreferences sharedPreferences, Continuation<? super RequestExtensions$build$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$this_build = nimbusRequest;
        this.$manufacturer = str;
        this.$model = str2;
        this.$osVersion = str3;
        this.$preferences = sharedPreferences;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RequestExtensions$build$2(this.$context, this.$this_build, this.$manufacturer, this.$model, this.$osVersion, this.$preferences, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NimbusRequest> continuation) {
        return ((RequestExtensions$build$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        User user;
        User.Extension extension;
        Extension extension2;
        NimbusNative nimbusNative;
        List<Asset> list;
        Banner[] bannerArr;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Resources resources = this.$context.getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
            Format format = RequestExtensions.format(resources, this.$this_build.getInterstitialOrientation());
            String str = this.$context.getPackageManager().getPackageInfo(this.$context.getPackageName(), 0).versionName;
            Impression impression = this.$this_build.request.imp[0];
            NimbusRequest nimbusRequest = this.$this_build;
            Context context = this.$context;
            Banner banner = impression.banner;
            if (banner != null && banner.api == null) {
                banner.api = NimbusRequest.defaultApis;
            }
            Video video = impression.video;
            boolean z = true;
            if (video != null) {
                if (video.w == 0) {
                    video.w = format.w;
                }
                if (video.h == 0) {
                    video.h = format.h;
                }
                if (video.companionad == null) {
                    Byte b = video.ext.get("is_rewarded");
                    if ((b != null ? b.byteValue() : 0) > 0) {
                        bannerArr = RequestExtensions.endCard(nimbusRequest, context);
                    } else {
                        CompanionAd[] companionAds = nimbusRequest.getCompanionAds();
                        if (!Boxing.boxBoolean(!(companionAds.length == 0)).booleanValue()) {
                            companionAds = null;
                        }
                        if (companionAds != null) {
                            Collection arrayList = new ArrayList(companionAds.length);
                            for (CompanionAd companionAd : companionAds) {
                                arrayList.add(new Banner(companionAd.getWidth(), companionAd.getHeight(), (Format[]) null, 0.0f, (byte[]) null, (byte) 0, (byte[]) null, Boxing.boxByte(RequestExtensions.getByteValue(companionAd.isEndCard())), 124, (DefaultConstructorMarker) null));
                            }
                            bannerArr = (Banner[]) ((List) arrayList).toArray(new Banner[0]);
                        } else {
                            bannerArr = null;
                        }
                    }
                    video.companionad = bannerArr;
                }
                if (video.protocols == null) {
                    video.protocols = NimbusRequest.defaultProtocols;
                }
                if (video.mimes == null) {
                    video.mimes = Nimbus.videoMimeTypes;
                }
            }
            Native nativeR = impression.f0native;
            if (!(nativeR == null || (extension2 = nativeR.ext) == null || (nimbusNative = extension2.nimbusNative) == null || (list = nimbusNative.assets) == null)) {
                Collection arrayList2 = new ArrayList();
                for (Asset asset : list) {
                    Asset.VideoObject videoObject = asset.video;
                    if (videoObject != null) {
                        arrayList2.add(videoObject);
                    }
                }
                for (Asset.VideoObject videoObject2 : (List) arrayList2) {
                    if (videoObject2.protocols == null) {
                        videoObject2.protocols = NimbusRequest.defaultProtocols;
                    }
                    if (videoObject2.mimes == null) {
                        videoObject2.mimes = Nimbus.videoMimeTypes;
                    }
                }
            }
            BidRequest bidRequest = this.$this_build.request;
            App app = RequestExtensions.app;
            if (app != null) {
                app.ver = str;
            } else {
                app = new App((String) null, (String) null, (String) null, (String) null, str, (String) null, (String[]) null, (String[]) null, (String[]) null, (Byte) null, (Byte) null, (Publisher) null, 4079, (DefaultConstructorMarker) null);
            }
            bidRequest.app = app;
            if (this.$this_build.request.device == null) {
                BidRequest bidRequest2 = this.$this_build.request;
                String id = Platform.adInfo.getId();
                if (id == null) {
                    id = Nimbus.EMPTY_AD_ID;
                }
                String str2 = id;
                byte byteValue = RequestExtensions.getByteValue(Platform.adInfo.isLimitAdTrackingEnabled());
                String userAgent = Platform.INSTANCE.getUserAgent();
                String languageCode = RequestExtensions.getLanguageCode(this.$context);
                int i = format.w;
                int i2 = format.h;
                float f = this.$context.getResources().getDisplayMetrics().density;
                Context applicationContext = this.$context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                bidRequest2.device = RequestExtensions.device(str2, (byte) byteValue, userAgent, languageCode, (byte) ConnectionTypeKt.getConnectionType(applicationContext), i, i2, f, this.$manufacturer, this.$model, this.$osVersion);
            }
            this.$this_build.request.format = format;
            BidRequest bidRequest3 = this.$this_build.request;
            Regs regs = this.$this_build.request.regs;
            if (regs == null) {
                regs = new Regs((byte) 0, (Regs.Extension) null, 3, (DefaultConstructorMarker) null);
            }
            bidRequest3.regs = PrivacyExtensions.applyPrivacyRegs(regs, this.$preferences);
            this.$this_build.request.test = RequestExtensions.getByteValue(Nimbus.testMode);
            BidRequest bidRequest4 = this.$this_build.request;
            User user2 = RequestExtensions.user;
            if (user2 != null) {
                user = new User(user2.age, user2.buyeruid, user2.yob, user2.gender, user2.keywords, user2.custom_data, user2.data, user2.ext);
            } else {
                user = new User(0, (String) null, 0, (String) null, (String) null, (String) null, (Data[]) null, (User.Extension) null, 255, (DefaultConstructorMarker) null);
            }
            bidRequest4.user = PrivacyExtensions.applyTCFv2(user, this.$preferences);
            BidRequest bidRequest5 = this.$this_build.request;
            if (Nimbus.getThirdPartyViewabilityEnabled() && bidRequest5.source != null) {
                z = false;
            }
            if (z) {
                RequestExtensions.removeOMIDFlag(this.$this_build.request.imp[0]);
            }
            this.$this_build.getExtendedIds().addAll(RequestExtensionsKt.getGlobalExtendedIds());
            Iterator<NimbusRequest.Interceptor> it = RequestManager.interceptors.iterator();
            while (it.hasNext()) {
                it.next().modifyRequest(this.$this_build);
            }
            NimbusRequest nimbusRequest2 = this.$this_build;
            for (NimbusRequest.Interceptor modifyRequest : this.$this_build.getInterceptors()) {
                modifyRequest.modifyRequest(nimbusRequest2);
            }
            User user3 = this.$this_build.request.user;
            if (user3 != null) {
                User user4 = this.$this_build.request.user;
                if (user4 == null || (extension = user4.ext) == null) {
                    extension = new User.Extension((String) null, (String) null, (String) null, (String) null, (String) null, (Set) this.$this_build.getExtendedIds(), (Map) null, (Map) null, 223, (DefaultConstructorMarker) null);
                } else {
                    extension.eids = this.$this_build.getExtendedIds();
                }
                user3.ext = extension;
            }
            return this.$this_build;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
