package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzfz implements Continuation {
    public /* synthetic */ zzfz(zzgd zzgd) {
    }

    public final Object then(Task task) {
        List<Task> list = (List) task.getResult();
        ArrayList arrayList = new ArrayList(list.size());
        for (Task task2 : list) {
            if (task2.isSuccessful()) {
                arrayList.add(task2.getResult());
            }
        }
        return arrayList;
    }
}
