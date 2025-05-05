package androidx.media3.session;

import android.os.Bundle;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class SessionCommands {
    public static final SessionCommands EMPTY = new Builder().build();
    private static final String FIELD_SESSION_COMMANDS = Util.intToStringMaxRadix(0);
    private static final String TAG = "SessionCommands";
    public final ImmutableSet<SessionCommand> commands;

    public static final class Builder {
        private final Set<SessionCommand> commands;

        public Builder() {
            this.commands = new HashSet();
        }

        private Builder(SessionCommands sessionCommands) {
            this.commands = new HashSet(((SessionCommands) Assertions.checkNotNull(sessionCommands)).commands);
        }

        public Builder add(SessionCommand sessionCommand) {
            this.commands.add((SessionCommand) Assertions.checkNotNull(sessionCommand));
            return this;
        }

        public Builder add(int i) {
            Assertions.checkArgument(i != 0);
            this.commands.add(new SessionCommand(i));
            return this;
        }

        public Builder addSessionCommands(Collection<SessionCommand> collection) {
            this.commands.addAll(collection);
            return this;
        }

        public Builder remove(SessionCommand sessionCommand) {
            this.commands.remove(Assertions.checkNotNull(sessionCommand));
            return this;
        }

        public Builder remove(int i) {
            Assertions.checkArgument(i != 0);
            Iterator<SessionCommand> it = this.commands.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SessionCommand next = it.next();
                if (next.commandCode == i) {
                    this.commands.remove(next);
                    break;
                }
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllSessionCommands() {
            addCommandCodes(SessionCommand.SESSION_COMMANDS);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllLibraryCommands() {
            addCommandCodes(SessionCommand.LIBRARY_COMMANDS);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllPredefinedCommands() {
            addAllSessionCommands();
            addAllLibraryCommands();
            return this;
        }

        private void addCommandCodes(List<Integer> list) {
            for (int i = 0; i < list.size(); i++) {
                add(new SessionCommand(list.get(i).intValue()));
            }
        }

        public SessionCommands build() {
            return new SessionCommands(this.commands);
        }
    }

    private SessionCommands(Collection<SessionCommand> collection) {
        this.commands = ImmutableSet.copyOf(collection);
    }

    public boolean contains(SessionCommand sessionCommand) {
        return this.commands.contains(Assertions.checkNotNull(sessionCommand));
    }

    public boolean contains(int i) {
        Assertions.checkArgument(i != 0, "Use contains(Command) for custom command");
        return containsCommandCode(this.commands, i);
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionCommands)) {
            return false;
        }
        return this.commands.equals(((SessionCommands) obj).commands);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.commands);
    }

    private static boolean containsCommandCode(Collection<SessionCommand> collection, int i) {
        for (SessionCommand sessionCommand : collection) {
            if (sessionCommand.commandCode == i) {
                return true;
            }
        }
        return false;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<SessionCommand> it = this.commands.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toBundle());
        }
        bundle.putParcelableArrayList(FIELD_SESSION_COMMANDS, arrayList);
        return bundle;
    }

    public static SessionCommands fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_SESSION_COMMANDS);
        if (parcelableArrayList == null) {
            Log.w(TAG, "Missing commands. Creating an empty SessionCommands");
            return EMPTY;
        }
        Builder builder = new Builder();
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            builder.add(SessionCommand.fromBundle((Bundle) parcelableArrayList.get(i)));
        }
        return builder.build();
    }
}
