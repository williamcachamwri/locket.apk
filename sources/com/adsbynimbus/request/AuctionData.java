package com.adsbynimbus.request;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/adsbynimbus/request/AuctionData;", "", "auctionPrice", "", "auctionMinToWin", "winningSource", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuctionMinToWin", "()Ljava/lang/String;", "getAuctionPrice", "getWinningSource", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WinLossExtension.kt */
public final class AuctionData {
    private final String auctionMinToWin;
    private final String auctionPrice;
    private final String winningSource;

    public AuctionData() {
        this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    public AuctionData(String str, String str2, String str3) {
        this.auctionPrice = str;
        this.auctionMinToWin = str2;
        this.winningSource = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AuctionData(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getAuctionPrice() {
        return this.auctionPrice;
    }

    public final String getAuctionMinToWin() {
        return this.auctionMinToWin;
    }

    public final String getWinningSource() {
        return this.winningSource;
    }
}
