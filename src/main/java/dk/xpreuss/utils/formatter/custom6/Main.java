package dk.xpreuss.utils.formatter.custom6;

import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {
    	final String DP = "\"";
        String messageToFormat = DP+"{0}_{1}"+DP;
        StringFormatter formatter = new StringFormatter(messageToFormat);

        String [] arguments = new String[]{"Hello world", "another time dude"};
        String output = formatter.formatWith(arguments);
        System.out.println(output);
    }
}
