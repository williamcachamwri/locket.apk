package com.google.mlkit.common.sdkinternal;

import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTaskInput;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public abstract class MLTask<T, S extends MLTaskInput> extends ModelResource {
    public MLTask() {
    }

    public abstract T run(S s) throws MlKitException;

    protected MLTask(TaskQueue taskQueue) {
        super(taskQueue);
    }
}
