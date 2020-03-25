public class Utility
{
    public static String join (String[] lst, String regex)
    {
        String ret = new String();
        for (int i = 1; i < lst.length; ++i)
        {
            ret += lst[i];
            if (i + 1 < lst.length)
                ret += regex;
        }
        return ret;
    }
}
