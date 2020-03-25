public class File extends FileSystemComponent {
    AllocatedBlocks blocks;

    public File(String path)
    {
        super(path);
    }

    public File(String path, int level, AllocatedBlocks blocks)
    {
        super(path, level);
        this.blocks = blocks;
    }

    @Override
    public void print()
    {
        printInfo();
    }

}
