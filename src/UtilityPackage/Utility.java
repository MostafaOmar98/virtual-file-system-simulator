package UtilityPackage;

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

    public static String[] subarray(String[] arr, int start, int length)
    {
        String[] ret = new String[length];
        for (int i = 0; i < length; ++i)
            ret[i] = arr[i + start];
        return ret;
    }

    public static String back(String[] arr)
    {
        if (arr.length == 0)
            return null;
        return arr[arr.length - 1];
    }

    public static void printLevel(int level, String end)
    {
        for (int i = 0; i < 2 * level; ++i)
            System.out.print(" ");
        System.out.print(end);
    }
}
