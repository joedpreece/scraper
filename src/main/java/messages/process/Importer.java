package messages.process;

import java.io.File;
import java.util.ArrayList;

public class Importer {

    public static ArrayList<File> obtainFilePaths(String filepath) {
        File file = new File(filepath);
        ArrayList<File> files = new ArrayList<>();
        recursiveSearch(file, files);
        return files;
    }

    private static void recursiveSearch(File file, ArrayList<File> files) {
        if (file.isDirectory()) {
            for (File subfile : file.listFiles()) {
                recursiveSearch(subfile, files);
            }
        } else if (file.getName().equals("message_1.json")) {
            files.add(file);
        }

    }

}
