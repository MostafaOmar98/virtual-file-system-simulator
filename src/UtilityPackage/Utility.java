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

    public static void printError(VFSError error)
    {
        switch(error)
        {
            case OK:
                System.out.println("Command Executed Succesfully");
                break;
            case INV_COMMAND:
                System.out.println("Invalid Command");
                break;
            case NUM_ARGUMENTS:
                System.out.println("Invalid Number of arguments");
                break;
            case FILE_NOT_EXIST:
                System.out.println("Given file path does not exist");
                break;
            case FOLDER_NOT_EXIST:
                System.out.println("Given folder path does not exist");
                break;
            case FILE_EXISTS:
                System.out.println("A file already exists with that name");
                break;
            case FOLDER_EXISTS:
                System.out.println("A folder already exists with that name");
                break;
            case INV_ARGUMENT:
                System.out.println("Invalid argument input");
                break;
            case NO_SPACE:
                System.out.println("No free space");
                break;
            default:
                assert(false);
        }
    }
}
