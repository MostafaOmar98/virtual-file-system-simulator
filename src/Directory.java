import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemComponent {
    List<FileSystemComponent> children;

    public Directory(String path)
    {
        super(path);
        children = new ArrayList<>();
    }

    public Directory(String path, int level, FileSystemComponent parent)
    {
        super(path, level, parent);
        children = new ArrayList<>();
    }

    @Override
    public void print()
    {
        printInfo();
        System.out.println("/");
        for (FileSystemComponent child : children)
            child.print();
    }

}
