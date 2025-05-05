package com.google.firebase.functions;

import com.google.android.gms.tasks.Task;

interface ContextProvider {
    Task<HttpsCallableContext> getContext(boolean z);
}
