import java.util.ArrayList;

public class DirectoryStructure {
    Directory root;
    static String ROOT_NAME = "root", DELIMETER = "/";
    DirectoryStructure()
    {
        root = new Directory(ROOT_NAME);
    }



    public void showStructure()
    {
        root.print();
    }
}
