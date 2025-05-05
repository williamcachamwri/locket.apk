package com.google.mlkit.common.sdkinternal.model;

import com.google.mlkit.common.MlKitException;
import java.nio.MappedByteBuffer;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public interface RemoteModelLoaderHelper {
    MappedByteBuffer loadModelAtPath(String str) throws MlKitException;
}
