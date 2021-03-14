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
