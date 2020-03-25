public abstract class FileSystemComponent {
    String name;
    int level;

    FileSystemComponent(String name)
    {
        this.name = name;
        level = 0;
    }

    FileSystemComponent(String name, int level)
    {
        this.name = name;
        this.level = level;
    }

    public abstract void print();

    protected void printInfo()
    {
        for (int i = 0; i < 2 * level; ++i)
            System.out.print(" ");
        System.out.print("-" + name); // change to only name
    }
}
