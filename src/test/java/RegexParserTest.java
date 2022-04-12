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
    void splitItems() {
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
    void removeColonsFromSplitString() {
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
    void testStandardizeNameKey() {

        String PARSE_ME = RegexParser.splitItems(testString);
        String actual = RegexParser.standardizeNameKey(PARSE_ME);
        String expected = "Name:Milk\n" +
                "price:3.23\n" +
                "type:Food\n" +
                "expiration:1/25/2016\n" +
                "\n" +
                "Name:BreaD\n" +
                "price:1.23\n" +
                "type:Food\n" +
                "expiration:1/02/2016\n" +
                "\n" +
                "Name:BrEAD\n" +
                "price:1.23\n" +
                "type:Food\n" +
                "expiration:2/25/2016\n" +
                "\n" +
                "Name:MiLK\n" +
                "price:3.23\n" +
                "type:Food\n" +
                "expiration:1/11/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }

    @Test
    void standardizePriceKey() {
        String PARSE_ME = RegexParser.splitItems(testString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        String actual = RegexParser.standardizePriceKey(PARSE_ME);
        String expected = "Name:Milk\n" +
                "Price:3.23\n" +
                "type:Food\n" +
                "expiration:1/25/2016\n" +
                "\n" +
                "Name:BreaD\n" +
                "Price:1.23\n" +
                "type:Food\n" +
                "expiration:1/02/2016\n" +
                "\n" +
                "Name:BrEAD\n" +
                "Price:1.23\n" +
                "type:Food\n" +
                "expiration:2/25/2016\n" +
                "\n" +
                "Name:MiLK\n" +
                "Price:3.23\n" +
                "type:Food\n" +
                "expiration:1/11/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }
    @Test
    void standardizeTypeKey() {
        String PARSE_ME = RegexParser.splitItems(testString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        String actual = RegexParser.standardizeTypeKey(PARSE_ME);
        String expected = "Name:Milk\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "expiration:1/25/2016\n" +
                "\n" +
                "Name:BreaD\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "expiration:1/02/2016\n" +
                "\n" +
                "Name:BrEAD\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "expiration:2/25/2016\n" +
                "\n" +
                "Name:MiLK\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "expiration:1/11/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }
    @Test
    void standardizeExpirationKey() {
        String PARSE_ME = RegexParser.splitItems(testString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        String actual = RegexParser.standardizeExpirationKey(PARSE_ME);
        String expected = "Name:Milk\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "Expiration:1/25/2016\n" +
                "\n" +
                "Name:BreaD\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "Expiration:1/02/2016\n" +
                "\n" +
                "Name:BrEAD\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "Expiration:2/25/2016\n" +
                "\n" +
                "Name:MiLK\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "Expiration:1/11/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }

    @Test
    void standardizeBreadValue() {
        String PARSE_ME = RegexParser.splitItems(testString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        String actual = RegexParser.standardizeBreadValue(PARSE_ME);

        String expected = "Name:Milk\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "Expiration:1/25/2016\n" +
                "\n" +
                "Name:Bread\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "Expiration:1/02/2016\n" +
                "\n" +
                "Name:Bread\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "Expiration:2/25/2016\n" +
                "\n" +
                "Name:MiLK\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "Expiration:1/11/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }
    @Test
    void standardizeCookiesValue() {
        String PARSE_ME = RegexParser.splitItems(testCookieString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        String actual = RegexParser.standardizeCookieValue(PARSE_ME);

        String expected = "Name:Cookies\n" +
                "Price:2.25\n" +
                "Type:Food\n" +
                "Expiration:1/25/2016\n" +
                "\n" +
                "Name:Cookies\n" +
                "Price:2.25\n" +
                "Type:Food\n" +
                "Expiration:1/25/2016\n" +
                "\n" +
                "Name:Cookies\n" +
                "Price:2.25\n" +
                "Type:Food\n" +
                "Expiration:3/22/2016\n" +
                "\n" +
                "Name:Cookies\n" +
                "Price:2.25\n" +
                "Type:Food\n" +
                "Expiration:1/25/2016\n" +
                "\n"
                ;

        assertEquals(actual,expected);
    }

    @Test
    void standardizeAppleValue() {
        String PARSE_ME = RegexParser.splitItems(testAppleMilkString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeCookieValue(PARSE_ME);
        String actual = RegexParser.standardizeApplesValue(PARSE_ME);

        String expected = "Name:MilK\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "Expiration:1/17/2016\n" +
                "\n" +
                "Name:MilK\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "Expiration:4/25/2016\n" +
                "\n" +
                "Name:Apples\n" +
                "Price:0.25\n" +
                "Type:Food\n" +
                "Expiration:1/23/2016\n" +
                "\n" +
                "Name:Apples\n" +
                "Price:0.23\n" +
                "Type:Food\n" +
                "Expiration:5/02/2016\n" +
                "\n";

       assertEquals(actual,expected);
    }
    @Test
    void standardizeMilkValue() {
        String PARSE_ME = RegexParser.splitItems(testAppleMilkString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeCookieValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeApplesValue(PARSE_ME);
        String actual = RegexParser.standardizeMilkValue(PARSE_ME);

        String expected = "Name:Milk\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "Expiration:1/17/2016\n" +
                "\n" +
                "Name:Milk\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "Expiration:4/25/2016\n" +
                "\n" +
                "Name:Apples\n" +
                "Price:0.25\n" +
                "Type:Food\n" +
                "Expiration:1/23/2016\n" +
                "\n" +
                "Name:Apples\n" +
                "Price:0.23\n" +
                "Type:Food\n" +
                "Expiration:5/02/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }

    @Test
    void removeExcessNewLines() {
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

        String expected = "Name:Milk\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "Expiration:1/17/2016,\n" +
                "Name:Milk\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "Expiration:4/25/2016,\n" +
                "Name:Apples\n" +
                "Price:0.25\n" +
                "Type:Food\n" +
                "Expiration:1/23/2016,\n" +
                "Name:Apples\n" +
                "Price:0.23\n" +
                "Type:Food\n" +
                "Expiration:5/02/2016,\n";

        assertEquals(actual,expected);
    }



    @Test
    void populateJerkSON() {
        String PARSE_ME = RegexParser.splitItems(testAppleMilkString);
        PARSE_ME = RegexParser.standardizeNameKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizePriceKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeTypeKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeExpirationKey(PARSE_ME);
        PARSE_ME = RegexParser.standardizeBreadValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeCookieValue(PARSE_ME);
        PARSE_ME = RegexParser.standardizeApplesValue(PARSE_ME);
        String actual = RegexParser.standardizeMilkValue(PARSE_ME);

        String expected = "Name:Milk\n" +
                "Price:3.23\n" +
                "Type:Food\n" +
                "Expiration:1/17/2016\n" +
                "\n" +
                "Name:Milk\n" +
                "Price:1.23\n" +
                "Type:Food\n" +
                "Expiration:4/25/2016\n" +
                "\n" +
                "Name:Apples\n" +
                "Price:0.25\n" +
                "Type:Food\n" +
                "Expiration:1/23/2016\n" +
                "\n" +
                "Name:Apples\n" +
                "Price:0.23\n" +
                "Type:Food\n" +
                "Expiration:5/02/2016\n" +
                "\n";

        assertEquals(actual,expected);
    }
}