package DAOs;

import java.io.*;

public class FileIO {
    public BufferedReader getBufferedReader(String path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(path));
    }

    public BufferedWriter getBufferedWriter(String path, boolean append) throws IOException {
        return new BufferedWriter(new FileWriter(path, append));
    }
}
