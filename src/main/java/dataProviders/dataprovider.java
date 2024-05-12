package dataProviders;

public class dataprovider {
    public static Object[][] VALID_SEARCH_KEYWORDS = new Object[][]{
            {"card"},
            {"RAY"}
    };

    public static Object[][] NO_RESULT_SEARCH_KEYWORDS = new Object[][]{
            {"сйкдфксд"},
            {"աասդփփճրկասդշշասֆասֆսդ"},
            {"rrkkkajdjensknf854"}
    };

    public static Object[][] INVALID_SEARCH_KEYWORDS = new Object[][]{
            {"PC"},
            {"a"},
            {"."}
    };

    public static Object[][] VALID_QUANTITY_ADD_TO_CART_MAC = new Object[][]{
            {2},
            {4}
    };

    public static Object[][] INVALID_QUANTITY_ADD_TO_CART = new Object[][]{
            {0},
            {-1},
            {1}
    };
}
