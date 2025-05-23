package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.sentry.Session;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class EquivalentAddressGroup {
    public static final Attributes.Key<String> ATTR_AUTHORITY_OVERRIDE = Attributes.Key.create("io.grpc.EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE");
    private final List<SocketAddress> addrs;
    private final Attributes attrs;
    private final int hashCode;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Attr {
    }

    public EquivalentAddressGroup(List<SocketAddress> list) {
        this(list, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(List<SocketAddress> list, Attributes attributes) {
        Preconditions.checkArgument(!list.isEmpty(), "addrs is empty");
        List<SocketAddress> unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.addrs = unmodifiableList;
        this.attrs = (Attributes) Preconditions.checkNotNull(attributes, Session.JsonKeys.ATTRS);
        this.hashCode = unmodifiableList.hashCode();
    }

    public EquivalentAddressGroup(SocketAddress socketAddress) {
        this(socketAddress, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(SocketAddress socketAddress, Attributes attributes) {
        this((List<SocketAddress>) Collections.singletonList(socketAddress), attributes);
    }

    public List<SocketAddress> getAddresses() {
        return this.addrs;
    }

    public Attributes getAttributes() {
        return this.attrs;
    }

    public String toString() {
        return "[" + this.addrs + "/" + this.attrs + "]";
    }

    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EquivalentAddressGroup)) {
            return false;
        }
        EquivalentAddressGroup equivalentAddressGroup = (EquivalentAddressGroup) obj;
        if (this.addrs.size() != equivalentAddressGroup.addrs.size()) {
            return false;
        }
        for (int i = 0; i < this.addrs.size(); i++) {
            if (!this.addrs.get(i).equals(equivalentAddressGroup.addrs.get(i))) {
                return false;
            }
        }
        return this.attrs.equals(equivalentAddressGroup.attrs);
    }
}
