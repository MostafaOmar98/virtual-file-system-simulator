import java.nio.file.FileSystem;
import java.util.ArrayList;

public class FSSubComponents
{
    ArrayList<File> files;
    ArrayList<Directory> dirs;

    public ArrayList<File> getFiles()
    {
        return files;
    }

    public ArrayList<Directory> getDirs()
    {
        return dirs;
    }

    FSSubComponents()
    {
        files = new ArrayList<>();
        dirs = new ArrayList<>();
    }

    public File getFile(String name)
    {
        for (File f : files)
            if (f.getName().equals(name))
                return f;
        return null;
    }

    public Directory getDirectory(String name)
    {
        for (Directory dir : dirs)
            if (dir.getName().equals(name))
                return dir;
        return null;
    }


    public void addFile(File f)
    {
        files.add(f);
    }
    public void addDirectory(Directory dir)
    {
        dirs.add(dir);
    }
}
