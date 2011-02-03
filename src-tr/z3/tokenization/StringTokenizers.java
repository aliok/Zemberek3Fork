package z3.tokenization;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringTokenizers {

    public static StringTokenizer whiteSpaceTokenizer() {
        return new StringTokenizer() {
            public List<String> tokens(String input) {
                return Arrays.asList(input.split("\\s+"));
            }
        };
    }

    public static StringTokenizer spaceTokenizer() {
        return new StringTokenizer() {
            public List<String> tokens(String input) {
                return Arrays.asList(input.split("[ ]+"));
            }
        };
    }

    public static StringTokenizer regexpTokenizer(String regexp) {
        return new RegexpTokenizer(regexp);
    }

    private static class RegexpTokenizer implements StringTokenizer {

        Pattern pattern;

        private RegexpTokenizer(String string) {
            this.pattern = Pattern.compile(string);
        }

        public List<String> tokens(String input) {
            return Arrays.asList(pattern.split(input));
        }
    }

}
