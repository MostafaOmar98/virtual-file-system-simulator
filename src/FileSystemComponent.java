public abstract class FileSystemComponent {
    String name;
    int level;
    FileSystemComponent parent;
    FileSystemComponent(String name)
    {
        this.name = name;
        level = 0;
        parent = null;
    }

    FileSystemComponent(String name, int level, FileSystemComponent parent)
    {
        this.name = name;
        this.level = level;
        this.parent = parent;
    }

    public abstract void print();

    protected void printInfo()
    {
        for (int i = 0; i < 2 * level; ++i)
            System.out.print(" ");
        System.out.print("-" + name); // change to only name
    }
}
