import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexParserTest {
    String testString = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##" +
            "naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##" +
            "NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##" +
            "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##";
    String testCookieString = "naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##" +
            "naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##" +
            "naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##" +
            "naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##";
    String testAppleMilkString = "NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##" +
            "naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##" +
            "naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##" +
            "naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##";

    @Test
    void splitItemsTest() {
        String expected = "naMe:Milk\n" +
                "price:3.23\n" +
                "type:Food\n" +
                "expiration:1/25/2016\n" +
                "\n" +
                "naME:BreaD\n" +
                "price:1.23\n" +
                "type:Food\n" +
                "expiration:1/02/2016\n" +
                "\n" +
                "NAMe:BrEAD\n" +
                "price:1.23\n" +
                "type:Food\n" +
                "expiration:2/25/2016\n" +
                "\n" +
                "naMe:MiLK\n" +
                "price:3.23\n" +
                "type:Food\n" +
                "expiration:1/11/2016\n" +
                "\n";
        String actual = RegexParser.splitItems(testString);

        assertEquals(actual, expected);
    }


    @Test
    void removeColonsFromSplitStringTest() {
       String PARSE_ME = RegexParser.splitItems(testString);
       String actual = RegexParser.removeColonsFromSplitString(PARSE_ME);
       String expected = "naMe\tMilk\n" +
               "price\t3.23\n" +
               "type\tFood\n" +
               "expiration\t1/25/2016\n" +
               "\n" +
               "naME\tBreaD\n" +
               "price\t1.23\n" +
               "type\tFood\n" +
               "expiration\t1/02/2016\n" +
               "\n" +
               "NAMe\tBrEAD\n" +
               "price\t1.23\n" +
               "type\tFood\n" +
               "expiration\t2/25/2016\n" +
               "\n" +
               "naMe\tMiLK\n" +
               "price\t3.23\n" +
               "type\tFood\n" +
               "expiration\t1/11/2016\n" +
               "\n";

       assertEquals(actual,expected);

    }


    @Test
    void testStandardizeNameKeyTest() {

        String PARSE_ME = RegexParser.splitItems(testString);
        String actual = RegexParser.standardizeNameKey(PARSE_ME);
        String expected = "{\n\"Name\":Milk\n" +
                "price:3.23\n" +
                "type:Food\n" +
                "expiration:1/25/2016\n" +
                "\n" +
                "{\n\"Name\":BreaD\n" +
                "price:1.23\n" +
                "type:Food\n" +
                "expiration:1/02/2016\n" +
                "\n" +
                "{\n\"Name\":BrEAD\n" +
                "price:1.23\n" +
                "type:Food\n" +
                "expiration:2/25/2016\n" +
                "\n" +
                "{\n\"Name\":MiLK\n" +
                "price:3.23\n" +
                "type:Food\n" +
                "expiration:1/11/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }

    @Test
    void standardizePriceKeyTest() {
        String PARSE_ME = RegexParser.splitItems(testString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        String actual = RegexParser.standardizePriceKey(PARSE_ME);
        String expected = "{\n\"Name\":Milk\n" +
                "\"Price\":3.23\n" +
                "type:Food\n" +
                "expiration:1/25/2016\n" +
                "\n" +
                "{\n\"Name\":BreaD\n" +
                "\"Price\":1.23\n" +
                "type:Food\n" +
                "expiration:1/02/2016\n" +
                "\n" +
                "{\n\"Name\":BrEAD\n" +
                "\"Price\":1.23\n" +
                "type:Food\n" +
                "expiration:2/25/2016\n" +
                "\n" +
                "{\n\"Name\":MiLK\n" +
                "\"Price\":3.23\n" +
                "type:Food\n" +
                "expiration:1/11/2016\n" +
                "\n";


        assertEquals(actual,expected);
    }
    @Test
    void standardizeTypeKeyTest() {
        String PARSE_ME = RegexParser.splitItems(testString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        String actual = RegexParser.standardizeTypeKey(PARSE_ME);
        String expected = "{\n\"Name\":Milk\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "expiration:1/25/2016\n" +
                "\n" +
                "{\n\"Name\":BreaD\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "expiration:1/02/2016\n" +
                "\n" +
                "{\n\"Name\":BrEAD\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "expiration:2/25/2016\n" +
                "\n" +
                "{\n\"Name\":MiLK\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "expiration:1/11/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }
    @Test
    void standardizeExpirationKeyTest() {
        String PARSE_ME = RegexParser.splitItems(testString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        String actual = RegexParser.standardizeExpirationKey(PARSE_ME);
        String expected = "{\n\"Name\":Milk\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/25/2016\n" +
                "\n" +
                "{\n\"Name\":BreaD\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/02/2016\n" +
                "\n" +
                "{\n\"Name\":BrEAD\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":2/25/2016\n" +
                "\n" +
                "{\n\"Name\":MiLK\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/11/2016\n" +
                "\n";


        assertEquals(actual,expected);
    }

    @Test
    void standardizeBreadValueTest() {
        String PARSE_ME = RegexParser.splitItems(testString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        String actual = RegexParser.standardizeBreadValue(PARSE_ME);

        String expected = "{\n\"Name\":Milk\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/25/2016\n" +
                "\n" +
                "{\n\"Name\":\"Bread\",\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/02/2016\n" +
                "\n" +
                "{\n\"Name\":\"Bread\",\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":2/25/2016\n" +
                "\n" +
                "{\n\"Name\":MiLK\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/11/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }
    @Test
    void standardizeCookiesValueTest() {
        String PARSE_ME = RegexParser.splitItems(testCookieString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        String actual = RegexParser.standardizeCookieValue(PARSE_ME);

        String expected = "{\n" +
                "\"Name\":\"Cookies\",\n" +
                "\"Price\":2.25\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/25/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":\"Cookies\",\n" +
                "\"Price\":2.25\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/25/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":\"Cookies\",\n" +
                "\"Price\":2.25\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":3/22/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":\"Cookies\",\n" +
                "\"Price\":2.25\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/25/2016\n"
                + "\n";

        assertEquals(actual,expected);
    }

    @Test
    void standardizeAppleValueTest() {
        String PARSE_ME = RegexParser.splitItems(testAppleMilkString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeCookieValue(PARSE_ME);
        String actual = RegexParser.standardizeApplesValue(PARSE_ME);

        String expected = "{\n" +
                "\"Name\":MilK\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/17/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":MilK\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":4/25/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":\"Apples\",\n" +
                "\"Price\":0.25\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/23/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":\"Apples\",\n" +
                "\"Price\":0.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":5/02/2016\n"
                + "\n";

       assertEquals(actual,expected);
    }
    @Test
    void standardizeMilkValueTest() {
        String PARSE_ME = RegexParser.splitItems(testAppleMilkString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeCookieValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeApplesValue(PARSE_ME);
        String actual = RegexParser.standardizeMilkValue(PARSE_ME);

        String expected = "{\n" +
                "\"Name\":\"Milk\",\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/17/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":\"Milk\",\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":4/25/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":\"Apples\",\n" +
                "\"Price\":0.25\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/23/2016\n" +
                "\n" +
                "{\n" +
                "\"Name\":\"Apples\",\n" +
                "\"Price\":0.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":5/02/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }

    @Test
    void removeExcessNewLinesTest() {
        String PARSE_ME = RegexParser.splitItems(testAppleMilkString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeCookieValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeApplesValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeMilkValue(PARSE_ME);
        String actual = RegexParser.removeExcessNewLines(PARSE_ME);

        String expected = "{\n" +
                "\"Name\":\"Milk\",\n" +
                "\"Price\":3.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/17/2016,\n" +
                "},{\n" +
                "\"Name\":\"Milk\",\n" +
                "\"Price\":1.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":4/25/2016,\n" +
                "},{\n" +
                "\"Name\":\"Apples\",\n" +
                "\"Price\":0.25\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":1/23/2016,\n" +
                "},{\n" +
                "\"Name\":\"Apples\",\n" +
                "\"Price\":0.23\n" +
                "\"Type\":Food\n" +
                "\"Expiration\":5/02/2016,\n" +
                "},";

        assertEquals(actual,expected);
    }

    @Test
    void removeUnusedKeys() {
        String PARSE_ME = RegexParser.splitItems(testAppleMilkString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeCookieValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeApplesValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeMilkValue(PARSE_ME);
        PARSE_ME = RegexParser.removeExcessNewLines(PARSE_ME);
        String actual = RegexParser.removeUnusedKeys(PARSE_ME);

        String expected = "{\n" +
                "\"Name\":\"Milk\",\n" +
                "\"Price\":3.23\n" +
                "},{\n" +
                "\"Name\":\"Milk\",\n" +
                "\"Price\":1.23\n" +
                "},{\n" +
                "\"Name\":\"Apples\",\n" +
                "\"Price\":0.25\n" +
                "},{\n" +
                "\"Name\":\"Apples\",\n" +
                "\"Price\":0.23\n" +
                "},";

        assertEquals(actual,expected);
    }
    @Test
    void setNullNameValuesTest() {
        String testNullNameString = "naMe:;price:3.23;type:Food;expiration:1/04/2016##";
        String PARSE_ME = RegexParser.splitItems(testNullNameString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeCookieValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeApplesValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeMilkValue(PARSE_ME);
        PARSE_ME = RegexParser.removeExcessNewLines(PARSE_ME);
        PARSE_ME= RegexParser.removeUnusedKeys(PARSE_ME);
        String actual = RegexParser.setNullNameValues(PARSE_ME);

        String expected = "{\n" +
                "\"Name\":null,\n" +
                "\"Price\":3.23\n" +
                "},";

        assertEquals(actual,expected);
    }

}