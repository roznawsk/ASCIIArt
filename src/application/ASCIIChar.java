package application;

import java.util.ArrayList;
import java.util.Collections;

public enum ASCIIChar {
    number_sign('#', 0.2858967451589389),
    grave_accent('`', 0.0314071790018394),
    at_sign('@', 0.24444759634804006),
    dollar_sign('$', 0.24889522363163333),
    exclamation_mark('!', 0.10838282786784892),
    quotation_mark('"', 0.19080287023530626),
    asterisk('*', 0.1606851761555928),
    slash('/', 0.08669609838143395),
    percent_sign('%', 0.2560557622439505),
    plus_sign('+', 0.2166537981825741),
    ampersand('&', 0.2518085510747085),
    question_mark( '?', 0.1819949207461249),
    round_brackets_left( '(', 0.06373338442498588),
    round_brackets_right( ')', 0.06373338442498589),
    dot('.', 0.05824114240644264),
    comma(',', 0.06208703367783836),
    colon(':', 0.05869249192089273),
    semicolon(';', 0.13381939358020273),
    hyphen('-', 0.09691241884087919),
    less_than_sign('<', 0.19678167508488903),
    equals_sign('=', 0.2393591948921687),
    caret('^', 0.07994470438111442),
    tilde('~', 0.09489548925471736),
    underscore('_', 0.12344132441652052),
    square_brackets_left('[', 0.1787084067585778),
    square_brackets_right(']', 0.1787084067585779),
    curly_brackets_left('{', 0.12494455366383914),
    curly_brackets_right('}', 0.12494455366383915),
    vertical_bar('|', 0.12448132780082988);

    char toChar;
    double ratio;

    ASCIIChar(char toChar, double ratio){
        this.toChar = toChar;
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return String.valueOf(toChar);
    }

    public static char getChar(double grayValue){
        ArrayList<ASCIIChar> chars = new ArrayList<>();
        Collections.addAll(chars, ASCIIChar.values());
        chars.sort((t1, t2) -> (int) (1000 * (t1.ratio - t2.ratio)));
        double min = chars.get(0).ratio;
        double max = chars.get(chars.size() - 1).ratio;
        double wanted = (max - min) * (1 - grayValue) + min;
        int i = 0;
        while(i < chars.size() - 1 && chars.get(i + 1).ratio < wanted){
            i++;
        }
        if( Math.abs(chars.get(i).ratio - wanted) < Math.abs(chars.get(i + 1).ratio - wanted)){
            return chars.get(i).toChar;
        }
        return chars.get(i + 1).toChar;
    }

}
