package utils;

import org.testng.annotations.Parameters;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class YamlReader {
    private static final Map<String, Object> yamlData;

    static {
        try {
            yamlData = new Yaml().load(new FileInputStream("src/test/resources/application.yml"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValue(String key) {
        Map<String, Object> node = yamlData;
        String[] keys = key.split("\\.");
        for (int i = 0; i < keys.length; i++) {
            if (i == keys.length - 1) return (String) node.get(keys[i]);
            node = (Map<String, Object>) node.get(keys[i]);
            if (node == null) return null;
        }
        return null;
    }

    public static void main(String[] args) {
        String s = getValue("url.todo") + getValue("path.todo.update");
        System.out.println(s);

    }
}
