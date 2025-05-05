package androidx.camera.core.processing;

import androidx.camera.core.ImageProcessor;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InternalImageProcessor$$ExternalSyntheticLambda1 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ InternalImageProcessor f$0;
    public final /* synthetic */ ImageProcessor.Request f$1;

    public /* synthetic */ InternalImageProcessor$$ExternalSyntheticLambda1(InternalImageProcessor internalImageProcessor, ImageProcessor.Request request) {
        this.f$0 = internalImageProcessor;
        this.f$1 = request;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m201lambda$safeProcess$1$androidxcameracoreprocessingInternalImageProcessor(this.f$1, completer);
    }
}
