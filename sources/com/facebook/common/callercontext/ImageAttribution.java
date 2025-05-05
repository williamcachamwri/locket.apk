package com.facebook.common.callercontext;

import javax.annotation.Nullable;

public interface ImageAttribution {
    String getCallingClassName();

    @Nullable
    ContextChain getContextChain();
}
