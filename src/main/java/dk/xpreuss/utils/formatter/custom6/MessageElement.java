package dk.xpreuss.utils.formatter.custom6;

public class MessageElement implements StringFormat {
    private final int numberedIndex;

    public MessageElement(int numberedIndex) {
        this.numberedIndex = numberedIndex;
    }

    public int getNumberedIndex() {
        return numberedIndex;
    }

    @Override
    public String formatWith(String argument) {
        return argument;
    }
}
