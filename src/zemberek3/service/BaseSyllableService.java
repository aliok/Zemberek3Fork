package zemberek3.service;

import java.util.List;

public abstract class BaseSyllableService implements SyllableService{

    public String[] split(String input, int spaceAvailable) {

        // handle big space amount.
        if (spaceAvailable >= input.length())
            return new String[]{input, ""};

        List<String> pieces = syllables(input);

        // handle no syllable.
        if (pieces.isEmpty())
            return new String[0];

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
            return new String[0];

        // split.
        final String[] result = new String[2];
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < index; j++) {
            sb.append(pieces.get(j));
        }
        result[0] = sb.toString();
        sb = new StringBuilder();
        for (int j = index; j < pieces.size(); j++) {
            sb.append(pieces.get(j));
        }
        result[1]= sb.toString();
        return result;
    }
}