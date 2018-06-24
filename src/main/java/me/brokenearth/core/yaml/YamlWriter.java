package me.brokenearth.core.yaml;


import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class YamlWriter {
    /**
     * The yaml file
     */
    private File file;
    /**
     * The text that will be written on the file
     */
    private String toWrite = "";
    /**
     * The path - value contents
     */
    Map<String, Object> map = new HashMap<String, Object>();
    /**
     * Specifies whether the file has been saved or not
     */
    boolean saved = false;
    /**
     * Is what is going to be written
     */
    private Map<String, Object> stream = new HashMap<String, Object>();
    YamlWriter(YamlConfiguration config) {
        this.file = config.getFile();
    }
    /**
     * Writes a file and saves it
     * @param path the path that will be written to
     * @param value the value of the path
     */
    public void write(String path, Object value) {
        if (String.valueOf(value).contains(":") || String.valueOf(path).contains(":")) {
            try {
                throw new InterruptedException("Grouping character found");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        map.put(path, value);
        stream.clear();
        stream.put(path, value);
        toWrite += eliminateChar(new Yaml().dump(stream));
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Eliminates unwanted characters
     * @param string the string where unwanted characters will be eliminated
     * @return the string without the unwanted characters
     */
    private String eliminateChar(String string) {
        return string.replace("{", "").replace("}", "");
    }
    /**
     * Saves the file
     * @throws IOException since it uses BufferedWriter
     */
    private void save() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(toWrite);
        writer.close();
        saved = true;
    }
}
