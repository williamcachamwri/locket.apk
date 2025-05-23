package androidx.media3.exoplayer.source;

import java.util.Arrays;
import java.util.Random;

public interface ShuffleOrder {
    ShuffleOrder cloneAndClear();

    ShuffleOrder cloneAndInsert(int i, int i2);

    ShuffleOrder cloneAndRemove(int i, int i2);

    int getFirstIndex();

    int getLastIndex();

    int getLength();

    int getNextIndex(int i);

    int getPreviousIndex(int i);

    public static class DefaultShuffleOrder implements ShuffleOrder {
        private final int[] indexInShuffled;
        private final Random random;
        private final int[] shuffled;

        public DefaultShuffleOrder(int i) {
            this(i, new Random());
        }

        public DefaultShuffleOrder(int i, long j) {
            this(i, new Random(j));
        }

        public DefaultShuffleOrder(int[] iArr, long j) {
            this(Arrays.copyOf(iArr, iArr.length), new Random(j));
        }

        private DefaultShuffleOrder(int i, Random random2) {
            this(createShuffledList(i, random2), random2);
        }

        private DefaultShuffleOrder(int[] iArr, Random random2) {
            this.shuffled = iArr;
            this.random = random2;
            this.indexInShuffled = new int[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                this.indexInShuffled[iArr[i]] = i;
            }
        }

        public int getLength() {
            return this.shuffled.length;
        }

        public int getNextIndex(int i) {
            int i2 = this.indexInShuffled[i] + 1;
            int[] iArr = this.shuffled;
            if (i2 < iArr.length) {
                return iArr[i2];
            }
            return -1;
        }

        public int getPreviousIndex(int i) {
            int i2 = this.indexInShuffled[i] - 1;
            if (i2 >= 0) {
                return this.shuffled[i2];
            }
            return -1;
        }

        public int getLastIndex() {
            int[] iArr = this.shuffled;
            if (iArr.length > 0) {
                return iArr[iArr.length - 1];
            }
            return -1;
        }

        public int getFirstIndex() {
            int[] iArr = this.shuffled;
            if (iArr.length > 0) {
                return iArr[0];
            }
            return -1;
        }

        public ShuffleOrder cloneAndInsert(int i, int i2) {
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int i3 = 0;
            int i4 = 0;
            while (i4 < i2) {
                iArr[i4] = this.random.nextInt(this.shuffled.length + 1);
                int i5 = i4 + 1;
                int nextInt = this.random.nextInt(i5);
                iArr2[i4] = iArr2[nextInt];
                iArr2[nextInt] = i4 + i;
                i4 = i5;
            }
            Arrays.sort(iArr);
            int[] iArr3 = new int[(this.shuffled.length + i2)];
            int i6 = 0;
            int i7 = 0;
            while (true) {
                int[] iArr4 = this.shuffled;
                if (i3 >= iArr4.length + i2) {
                    return new DefaultShuffleOrder(iArr3, new Random(this.random.nextLong()));
                }
                if (i6 >= i2 || i7 != iArr[i6]) {
                    int i8 = i7 + 1;
                    int i9 = iArr4[i7];
                    iArr3[i3] = i9;
                    if (i9 >= i) {
                        iArr3[i3] = i9 + i2;
                    }
                    i7 = i8;
                } else {
                    iArr3[i3] = iArr2[i6];
                    i6++;
                }
                i3++;
            }
        }

        public ShuffleOrder cloneAndRemove(int i, int i2) {
            int i3 = i2 - i;
            int[] iArr = new int[(this.shuffled.length - i3)];
            int i4 = 0;
            int i5 = 0;
            while (true) {
                int[] iArr2 = this.shuffled;
                if (i4 >= iArr2.length) {
                    return new DefaultShuffleOrder(iArr, new Random(this.random.nextLong()));
                }
                int i6 = iArr2[i4];
                if (i6 < i || i6 >= i2) {
                    int i7 = i4 - i5;
                    if (i6 >= i) {
                        i6 -= i3;
                    }
                    iArr[i7] = i6;
                } else {
                    i5++;
                }
                i4++;
            }
        }

        public ShuffleOrder cloneAndClear() {
            return new DefaultShuffleOrder(0, new Random(this.random.nextLong()));
        }

        private static int[] createShuffledList(int i, Random random2) {
            int[] iArr = new int[i];
            int i2 = 0;
            while (i2 < i) {
                int i3 = i2 + 1;
                int nextInt = random2.nextInt(i3);
                iArr[i2] = iArr[nextInt];
                iArr[nextInt] = i2;
                i2 = i3;
            }
            return iArr;
        }
    }

    public static final class UnshuffledShuffleOrder implements ShuffleOrder {
        private final int length;

        public int getPreviousIndex(int i) {
            int i2 = i - 1;
            if (i2 >= 0) {
                return i2;
            }
            return -1;
        }

        public UnshuffledShuffleOrder(int i) {
            this.length = i;
        }

        public int getLength() {
            return this.length;
        }

        public int getNextIndex(int i) {
            int i2 = i + 1;
            if (i2 < this.length) {
                return i2;
            }
            return -1;
        }

        public int getLastIndex() {
            int i = this.length;
            if (i > 0) {
                return i - 1;
            }
            return -1;
        }

        public int getFirstIndex() {
            return this.length > 0 ? 0 : -1;
        }

        public ShuffleOrder cloneAndInsert(int i, int i2) {
            return new UnshuffledShuffleOrder(this.length + i2);
        }

        public ShuffleOrder cloneAndRemove(int i, int i2) {
            return new UnshuffledShuffleOrder((this.length - i2) + i);
        }

        public ShuffleOrder cloneAndClear() {
            return new UnshuffledShuffleOrder(0);
        }
    }
}
