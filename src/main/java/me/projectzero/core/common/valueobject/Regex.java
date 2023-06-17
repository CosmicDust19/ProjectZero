package me.projectzero.core.common.valueobject;

import lombok.experimental.UtilityClass;

/**
 *  Application-wide regular expression constants
 */
@UtilityClass
public class Regex {

    public static final String ALPHA_2 = "[A-Z]{2}";
    public static final String ALPHA_3 = "[A-Z]{3}";
    public static final String DOMAIN = "((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}";
    public static final String EMAIL = "[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}";
    public static final String ISO_DATE = "\\d{4}-\\d{2}-\\d{2}";
    public static final String PHONE_NUMBER = "\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{2}-\\d{2}";
    public static final String TCKN = "\\d{11}";
    public static final String URL = "((http|https)://)?(www.)?" + DOMAIN + "\\b([-a-zA-Z0-9@:%._+~#?&/=]*)";
    public static final String UUID = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";

}
