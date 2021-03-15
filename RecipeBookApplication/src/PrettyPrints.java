public class PrettyPrints {

    public char defaultSurroundChar = '*';
    public int defaultSurroundSize = 128;

    public PrettyPrints() {
        
    }



    public String SurroundString(String content, int length, char c) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(c);
        }
        int start = (length / 2) - (content.length() / 2);
        text.replace(start, start + content.length(), content);
        return text.toString();
    }

    public String SurroundString(String content, char c) {
        return SurroundString(content, defaultSurroundSize, c);
    }

    public String SurroundString(String content) {
        return SurroundString(content, defaultSurroundSize, defaultSurroundChar);
    }

    public String FrameString(String content, int length, String frame) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i < frame.length()) {
                text.append(frame.charAt(i));
            //} else if (i > (length - frame.length())) {
            //    text.append(frame.charAt(frame.length() - (length - i)));
            } else {
                text.append(' ');
            }
        }
        int start = (length / 2) - (content.length() / 2);
        text.replace(start, start + content.length(), content);
        text.replace(length - frame.length(), length, new StringBuilder(frame).reverse().toString());
        return text.toString();
    }

    public String FrameString(String content, String frame) {
        return FrameString(content, defaultSurroundSize, frame);
    }

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

    public void Println(String content) {
        System.out.println(content);
    }

    public void Print(String content) {
        System.out.print(content);
    }
}
