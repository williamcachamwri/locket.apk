package com.adsbynimbus.request;

import com.adsbynimbus.internal.Components;
import com.adsbynimbus.internal.Logger;
import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a$\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0000\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0000\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\f\u001a\u0004\u0018\u00010\r\u001a\u001c\u0010\u0015\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\f\u001a\u0004\u0018\u00010\r\u001a\u0016\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0000\u001a\u001e\u0010\u0017\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00022\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0000\"&\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u001a"}, d2 = {"connectionProvider", "Lkotlin/Function1;", "", "Ljava/net/HttpURLConnection;", "getConnectionProvider", "()Lkotlin/jvm/functions/Function1;", "setConnectionProvider", "(Lkotlin/jvm/functions/Function1;)V", "notifyAuction", "", "url", "name", "auctionData", "Lcom/adsbynimbus/request/AuctionData;", "notifyTracker", "Lkotlinx/coroutines/Job;", "notifyLoss", "", "Lcom/adsbynimbus/request/RequestManager;", "nimbusResponse", "Lcom/adsbynimbus/request/NimbusResponse;", "notifyWin", "replaceAuctionMacros", "replaceMacro", "macro", "data", "request_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: WinLossExtension.kt */
public final class WinLossExtension {
    private static Function1<? super String, ? extends HttpURLConnection> connectionProvider = WinLossExtension$connectionProvider$1.INSTANCE;

    public static final void notifyWin(RequestManager requestManager, NimbusResponse nimbusResponse, AuctionData auctionData) {
        Intrinsics.checkNotNullParameter(requestManager, "<this>");
        Intrinsics.checkNotNullParameter(nimbusResponse, "nimbusResponse");
        notifyAuction(nimbusResponse.bid.getWin_response(), "Win", auctionData);
    }

    public static final void notifyLoss(RequestManager requestManager, NimbusResponse nimbusResponse, AuctionData auctionData) {
        Intrinsics.checkNotNullParameter(requestManager, "<this>");
        Intrinsics.checkNotNullParameter(nimbusResponse, "nimbusResponse");
        notifyAuction(nimbusResponse.bid.getLoss_response(), "Loss", auctionData);
    }

    public static final Object notifyAuction(String str, String str2, AuctionData auctionData) {
        String replaceAuctionMacros;
        Job notifyTracker;
        Intrinsics.checkNotNullParameter(str2, "name");
        if (str != null && (replaceAuctionMacros = replaceAuctionMacros(str, auctionData)) != null && (notifyTracker = notifyTracker(str2, replaceAuctionMacros)) != null) {
            return notifyTracker;
        }
        Logger.log(5, "Error firing " + str2 + " event tracker, empty url");
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r8 = kotlin.text.StringsKt.replace$default(r7, r8, r9, false, 4, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String replaceMacro(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "macro"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            if (r9 == 0) goto L_0x001a
            r4 = 0
            r5 = 4
            r6 = 0
            r1 = r7
            r2 = r8
            r3 = r9
            java.lang.String r8 = kotlin.text.StringsKt.replace$default((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            if (r8 != 0) goto L_0x0019
            goto L_0x001a
        L_0x0019:
            r7 = r8
        L_0x001a:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.WinLossExtension.replaceMacro(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r4 = replaceMacro(replaceMacro(replaceMacro(r3, "[AUCTION_PRICE]", r4.getAuctionPrice()), "[AUCTION_MIN_TO_WIN]", r4.getAuctionMinToWin()), "[WINNING_SOURCE]", r4.getWinningSource());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String replaceAuctionMacros(java.lang.String r3, com.adsbynimbus.request.AuctionData r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            if (r4 == 0) goto L_0x0029
            java.lang.String r0 = "[AUCTION_PRICE]"
            java.lang.String r1 = r4.getAuctionPrice()
            java.lang.String r0 = replaceMacro(r3, r0, r1)
            java.lang.String r1 = "[AUCTION_MIN_TO_WIN]"
            java.lang.String r2 = r4.getAuctionMinToWin()
            java.lang.String r0 = replaceMacro(r0, r1, r2)
            java.lang.String r1 = "[WINNING_SOURCE]"
            java.lang.String r4 = r4.getWinningSource()
            java.lang.String r4 = replaceMacro(r0, r1, r4)
            if (r4 != 0) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            r3 = r4
        L_0x0029:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.WinLossExtension.replaceAuctionMacros(java.lang.String, com.adsbynimbus.request.AuctionData):java.lang.String");
    }

    public static final Job notifyTracker(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "url");
        return BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), (CoroutineContext) null, (CoroutineStart) null, new WinLossExtension$notifyTracker$1(str, str2, (Continuation<? super WinLossExtension$notifyTracker$1>) null), 3, (Object) null);
    }

    public static final Function1<String, HttpURLConnection> getConnectionProvider() {
        return connectionProvider;
    }

    public static final void setConnectionProvider(Function1<? super String, ? extends HttpURLConnection> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        connectionProvider = function1;
    }
}
