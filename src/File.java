public class File extends FileSystemComponent {
    private AllocatedBlocks blocks;

    public File(String path)
    {
        super(path);
    }

    public File(String path, int level, AllocatedBlocks blocks)
    {
        super(path, level);
        this.blocks = blocks;
    }

    public AllocatedBlocks getAllocatedBlocks()
    {
        return blocks;
    }

    @Override
    public void print()
    {
        printInfo();
        System.out.println();
    }

}
