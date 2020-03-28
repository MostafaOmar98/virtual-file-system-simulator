public class File implements IPrintable{
    private String name;
    private int level;
    private Directory parent;
    private AllocatedBlocks blocks;

    public File(String name, int level, AllocatedBlocks blocks, Directory parent)
    {
        this.name = name;
        this.level = level;
        this.blocks = blocks;
        this.parent = parent;
    }

    public String getName()
    {
        return name;
    }

    public int getLevel()
    {
        return level;
    }

    public Directory getParent()
    {
        return parent;
    }

    public AllocatedBlocks getBlocks()
    {
        return blocks;
    }

    public AllocatedBlocks getAllocatedBlocks()
    {
        return blocks;
    }

    @Override
    public void print()
    {
        Utility.printLevel(level, "-" + name + "\n");
    }

}
