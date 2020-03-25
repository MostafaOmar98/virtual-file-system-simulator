public class DirectoryStructure {
    Directory root;
    static String ROOT_NAME = "root", DELIMETER = "/";
    DirectoryStructure()
    {
        root = new Directory(ROOT_NAME);
    }

    public boolean createFile(String path, AllocatedBlocks blocks)
    {
        String[] splittedPath = path.split(DELIMETER);
        if (!splittedPath[0].equals(ROOT_NAME))
            return false;
        return root.createFile(Utility.join(splittedPath, DELIMETER), blocks);
    }

    public boolean createDirectory(String path)
    {
        String[] splittedPath = path.split(DELIMETER);
        if (!splittedPath[0].equals(ROOT_NAME))
            return false;
        return root.createDirectory(Utility.join(splittedPath, DELIMETER));
    }

    public void showStructure()
    {
        root.print();
    }
}
