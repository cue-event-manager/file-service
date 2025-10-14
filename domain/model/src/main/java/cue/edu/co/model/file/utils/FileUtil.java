package cue.edu.co.model.file.utils;

public class FileUtil {
    private FileUtil(){}

    public static String extractExtension(String filename) {
        int index = filename.lastIndexOf('.');
        return (index == -1) ? "" : filename.substring(index + 1);
    }
}
