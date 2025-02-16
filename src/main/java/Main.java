import org.apache.commons.io.IOUtils;
import java.io.IOException;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        output = RegexParser.splitItems(output);
        output = RegexParser.standardizeNameKey(output);
        output = RegexParser.standardizePriceKey(output);
        output = RegexParser.standardizeTypeKey(output);
        output = RegexParser.standardizeExpirationKey(output);
        output = RegexParser.standardizeBreadValue(output);
        output = RegexParser.standardizeCookieValue(output);
        output = RegexParser.standardizeApplesValue(output);
        output = RegexParser.standardizeMilkValue(output);
        output = RegexParser.removeExcessNewLines(output);
        output = RegexParser.removeUnusedKeys(output);
        output = RegexParser.setNullNameValues(output);
        output = RegexParser.setNullPriceValues(output);
        System.out.println(output);

    }
}
