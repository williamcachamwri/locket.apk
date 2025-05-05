package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.Waiter;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/channels/WaiterEB;", "", "waiter", "Lkotlinx/coroutines/Waiter;", "<init>", "(Lkotlinx/coroutines/Waiter;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: BufferedChannel.kt */
final class WaiterEB {
    public final Waiter waiter;

    public WaiterEB(Waiter waiter2) {
        this.waiter = waiter2;
    }

    public String toString() {
        return "WaiterEB(" + this.waiter + ')';
    }
}
