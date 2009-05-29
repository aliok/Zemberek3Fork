package zemberek3.tokenization;

import java.util.Iterator;

public interface Tokenizer<T extends CharSequence> {
    Iterator<T> iterator();
}
