package zemberek3.service;

import java.util.List;

public abstract class BaseSyllableService implements SyllableService {

    public int splitIndex(String input, int spaceAvailable) {

        // handle big space amount.
        if (spaceAvailable >= input.length())
            return input.length();

        List<String> pieces = this.syllables(input);

        // handle no syllable.
        if (pieces.isEmpty())
            return -1;

        // find breaking syllable index.
        int remainingSpace = spaceAvailable;
        int index = 0;
        for (String piece : pieces) {
            if (piece.length() < remainingSpace) {
                remainingSpace -= piece.length();
                index++;
            } else
                break;
        }

        // handle first syllable does not fit spaceAvailable.
        if (index == 0)
            return -1;

        // find breaking letter index +1 .
        int k = 0;
        for (int j = 0; j < index; j++) {
            k += pieces.get(j).length();
        }
        return k;
    }
}