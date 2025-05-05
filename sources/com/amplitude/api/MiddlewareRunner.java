package com.amplitude.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MiddlewareRunner {
    private final ConcurrentLinkedQueue<Middleware> middlewares = new ConcurrentLinkedQueue<>();

    public void add(Middleware middleware) {
        this.middlewares.add(middleware);
    }

    /* access modifiers changed from: private */
    public void runMiddlewares(final List<Middleware> list, MiddlewarePayload middlewarePayload, final MiddlewareNext middlewareNext) {
        if (list.size() == 0) {
            middlewareNext.run(middlewarePayload);
        } else {
            list.get(0).run(middlewarePayload, new MiddlewareNext() {
                public void run(MiddlewarePayload middlewarePayload) {
                    MiddlewareRunner middlewareRunner = MiddlewareRunner.this;
                    List list = list;
                    middlewareRunner.runMiddlewares(list.subList(1, list.size()), middlewarePayload, middlewareNext);
                }
            });
        }
    }

    public boolean run(MiddlewarePayload middlewarePayload) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        run(middlewarePayload, new MiddlewareNext() {
            public void run(MiddlewarePayload middlewarePayload) {
                atomicBoolean.set(true);
            }
        });
        return atomicBoolean.get();
    }

    public void run(MiddlewarePayload middlewarePayload, MiddlewareNext middlewareNext) {
        runMiddlewares(new ArrayList(this.middlewares), middlewarePayload, middlewareNext);
    }
}
