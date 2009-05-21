package zemberek3.service;

import java.util.List;

public interface SyllableService {

    List<String> syllables(String input);

    int[] syllableIndexes(String input);
}
