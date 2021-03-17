/**
 * PrettyPrints
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 * 
 * PrettyPrints is used to easily create nice-looking text strings and console prints.
 * The regular print methods println and print can also be called by PrettyPrints.
 */
public class PrettyPrints {

    public char defaultSurroundChar = '*';
    public int defaultSurroundSize = 128;

    /* Takes a string and surrounds it with a single character - good for creating headers, and
       can also be used to just center content if you set space as the surrounding character */
    public String SurroundString(String content, int length, char c) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(c);
        }
        int start = (length / 2) - (content.length() / 2);
        if (length >= content.length()) {
            text.replace(start, start + content.length(), content);
            return text.toString();
        } else {
            return content;
        }
    }

    public String SurroundString(String content, char c) {
        return SurroundString(content, defaultSurroundSize, c);
    }

    public String SurroundString(String content) {
        return SurroundString(content, defaultSurroundSize, defaultSurroundChar);
    }

    /* Does the same thing as SurroundString, but prints the resulting string to the console rather
       than returning it. */
    public void SurroundPrint(String content, int size, char c) {
        System.out.print(SurroundString(content, size, c));
    }

    public void SurroundPrint(String content, char c) {
        System.out.print(SurroundString(content, defaultSurroundSize, c));
    }

    public void SurroundPrint(String content) {
        System.out.print(SurroundString(content, defaultSurroundSize, defaultSurroundChar));
    }

    public void SurroundPrintln(String content, int size, char c) {
        System.out.println(SurroundString(content, size, c));
    }

    public void SurroundPrintln(String content, char c) {
        System.out.println(SurroundString(content, defaultSurroundSize, c));
    }

    public void SurroundPrintln(String content) {
        System.out.println(SurroundString(content, defaultSurroundSize, defaultSurroundChar));
    }

    /* Returns a string where the content is centered within a frame, consisting of the characters defined in
       the frame parameter. SurroundString can be used to create the top and bottom of the frame. */
    public String FrameString(String content, int length, String frame) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i < frame.length()) {
                text.append(frame.charAt(i));
            } else {
                text.append(' ');
            }
        }
        int start = (length / 2) - (content.length() / 2);
        text.replace(start, start + content.length(), content);
        text.replace(length - frame.length(), length, new StringBuilder(frame).reverse().toString());
        return text.toString();
    }

    /* Does the same thing as SurroundString, but prints the resulting string to the console rather
       than returning it. */
    public String FrameString(String content, String frame) {
        return FrameString(content, defaultSurroundSize, frame);
    }

    /* Calls the System.out.println() method */
    public void Println(String content) {
        System.out.println(content);
    }

    /* Calls the System.out.print() method */
    public void Print(String content) {
        System.out.print(content);
    }
}
