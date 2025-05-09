package io.perfmark;

import javax.annotation.Nullable;

public class Impl {
    static final Link NO_LINK = new Link(Long.MIN_VALUE);
    private static final long NO_LINK_ID = Long.MIN_VALUE;
    static final Tag NO_TAG = new Tag("", Long.MIN_VALUE);
    static final long NO_TAG_ID = Long.MIN_VALUE;
    static final String NO_TAG_NAME = "";

    /* access modifiers changed from: protected */
    public void attachTag(Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void attachTag(String str, long j) {
    }

    /* access modifiers changed from: protected */
    public void attachTag(String str, long j, long j2) {
    }

    /* access modifiers changed from: protected */
    public <T> void attachTag(String str, T t, StringFunction<? super T> stringFunction) {
    }

    /* access modifiers changed from: protected */
    public void attachTag(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void event(String str) {
    }

    /* access modifiers changed from: protected */
    public void event(String str, Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void event(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void linkIn(Link link) {
    }

    /* access modifiers changed from: protected */
    public void setEnabled(boolean z) {
    }

    /* access modifiers changed from: protected */
    public boolean setEnabled(boolean z, boolean z2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public <T> void startTask(T t, StringFunction<? super T> stringFunction) {
    }

    /* access modifiers changed from: protected */
    public void startTask(String str) {
    }

    /* access modifiers changed from: protected */
    public void startTask(String str, Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void startTask(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void stopTask() {
    }

    /* access modifiers changed from: protected */
    public void stopTask(String str) {
    }

    /* access modifiers changed from: protected */
    public void stopTask(String str, Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void stopTask(String str, String str2) {
    }

    protected Impl(Tag tag) {
        if (tag != NO_TAG) {
            throw new AssertionError("nope");
        }
    }

    /* access modifiers changed from: protected */
    public Link linkOut() {
        return NO_LINK;
    }

    /* access modifiers changed from: protected */
    public Tag createTag(@Nullable String str, long j) {
        return NO_TAG;
    }

    @Nullable
    protected static String unpackTagName(Tag tag) {
        return tag.tagName;
    }

    protected static long unpackTagId(Tag tag) {
        return tag.tagId;
    }

    protected static long unpackLinkId(Link link) {
        return link.linkId;
    }

    protected static Tag packTag(@Nullable String str, long j) {
        return new Tag(str, j);
    }

    protected static Link packLink(long j) {
        return new Link(j);
    }
}
