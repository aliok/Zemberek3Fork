package zemberek3.structure;

/**
 * This is a Letter which contains Turkic language specific attributes, such as vowel type, englishEquivalent characters.
 */
public class TurkicLetter {

    public final char charValue;
    public final int alphabeticIndex;
    public final boolean vowel;
    public final boolean frontalVowel;
    public final boolean roundedVowel;
    public final boolean voiceless;
    public final boolean stopConsonant;
    public final boolean inAscii;
    public final boolean foreign;
    public final char englishEquivalentChar;

    public static final TurkicLetter UNDEFINED = new TurkicLetter((char) 0, -1);

    public static Builder builder(char charValue, int alphabeticIndex) {
        return new Builder(charValue).alphabeticIndex(alphabeticIndex);
    }

    public static class Builder {
        private char _charValue = 0;
        private int _alphabeticIndex = -1;
        private boolean _vowel = false;
        private boolean _frontalVowel = false;
        private boolean _roundedVowel = false;
        private boolean _voiceless = false;
        private boolean _stopConsonant = false;
        private boolean _inAscii = true;
        private boolean _foreign = false;
        private char _englishEquivalentChar = 0;

        public Builder(char charValue) {
            this._charValue = charValue;
            this._englishEquivalentChar = charValue;
        }

        public Builder alphabeticIndex(int alphabeticIndex) {
            this._alphabeticIndex = alphabeticIndex;
            return this;
        }

        public Builder vowel() {
            this._vowel = true;
            return this;
        }

        public Builder frontalVowel() {
            this._frontalVowel = true;
            return this;
        }

        public Builder roundedVowel() {
            this._roundedVowel = true;
            return this;
        }

        public Builder voiceless() {
            this._voiceless = true;
            return this;
        }

        public Builder stopConsosnant() {
            this._stopConsonant = true;
            return this;
        }

        public Builder notInAscii() {
            this._inAscii = false;
            return this;
        }

        public Builder foreign() {
            this._foreign = true;
            return this;
        }

        public Builder similarAscii(char equivalent) {
            this._englishEquivalentChar = equivalent;
            return this;
        }

        public TurkicLetter build() {
            TurkicLetter tl = new TurkicLetter(this);
            tl.checkConsistency();
            return tl;
        }
    }

    private TurkicLetter(Builder builder) {
        this.charValue = builder._charValue;
        this.alphabeticIndex = builder._alphabeticIndex;
        this.vowel = builder._vowel;
        this.frontalVowel = builder._frontalVowel;
        this.roundedVowel = builder._roundedVowel;
        this.voiceless = builder._voiceless;
        this.stopConsonant = builder._stopConsonant;
        this.inAscii = builder._inAscii;
        this.foreign = builder._foreign;
        this.englishEquivalentChar = builder._englishEquivalentChar;
    }

    // only used for illegal letter.
    private TurkicLetter(char c, int alphabeticIndex) {
        this.charValue = c;
        this.alphabeticIndex = alphabeticIndex;
        vowel = false;
        frontalVowel = false;
        roundedVowel = false;
        voiceless = false;
        stopConsonant = false;
        inAscii = false;
        foreign = false;
        englishEquivalentChar = c;
    }

    private void checkConsistency() {
        if (((voiceless||stopConsonant) && vowel) || (!vowel && (frontalVowel || roundedVowel))) {
            throw new IllegalArgumentException("Letter seems to have both vowel and Consonant attributes");
        } else if ((!inAscii) && (charValue < 'a' && charValue > 'z')) {
            throw new IllegalArgumentException("Marked as english alphabet but it is not." + charValue);
        } else if (alphabeticIndex < 0) {
            throw new IllegalArgumentException("Alphabetical index must be positive:" + alphabeticIndex);
        }
    }

    public char charValue() {
        return charValue;
    }

    public int alphabeticIndex() {
        return alphabeticIndex;
    }

    public boolean isVowel() {
        return vowel;
    }

    public boolean isConsonant() {
        return !vowel;
    }

    public boolean isFrontal() {
        return frontalVowel;
    }

    public boolean isRounded() {
        return roundedVowel;
    }

    public boolean isVoiceless() {
        return voiceless;
    }

    public boolean isInAscii() {
        return inAscii;
    }

    public char englishEquivalentChar() {
        return englishEquivalentChar;
    }

    public boolean isStopConsonant() {
        return stopConsonant;
    }

    @Override
    public String toString() {
        return String.valueOf(charValue + ":" + englishEquivalentChar);
    }
}
