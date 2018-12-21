package com.hc.utils.properties;

import org.ho.yaml.Yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    /**
     * 从classpath路径下load配置文件
     * @param path
     * @return
     * @throws IOException
     */
    public static Properties loadProperties(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
        return properties;
    }


    /**
     * 从classpath下面load yaml配置文件
     * @param path
     * @param clazz
     * @param <T>
     * @return
     * @throws FileNotFoundException
     */
    public static <T> T loadYaml(String path,Class<T> clazz) throws FileNotFoundException {
        return Yaml.loadType(Thread.currentThread().getContextClassLoader().getResourceAsStream(path),clazz);
    }
}
