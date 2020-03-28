package PersistenceManager;

import MainPack.VirtualFileSystem;

public interface FileManager
{
    public void save();
    public void load();
    public VirtualFileSystem getVirtualFileSystem();
}
