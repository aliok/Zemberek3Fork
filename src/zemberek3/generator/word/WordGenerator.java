package zemberek3.generator.word;

import zemberek3.structure.WordParseResult;

public interface WordGenerator {

    CharSequence generate(WordParseResult wordParseResult);

}
