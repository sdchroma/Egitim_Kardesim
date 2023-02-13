/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egitim.kardesligi.Operation;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Burag
 */
public class ConfigurationPropertiesOperations {

    private final Map<String, String> PROPERTIES_FILE_MAP = new HashMap<>();

    public ConfigurationPropertiesOperations() {
        PROPERTIES_FILE_MAP.put("WEB_PROPERTY_FILE", "application.properties");
    }

    public String GetPropertiesFile(String fileName) {
        return PROPERTIES_FILE_MAP.get(fileName);
    }

    public String GetProperties(String propertyFile, String propertyKey) throws Exception {
        InputStream _inputStream;
        String _properyValue;
        try {
            if ("".equals(propertyKey)) {
                throw new Exception("property key can not be empty");
            }
            Properties _prop = new Properties();
            String _propFileName = propertyFile;
            _inputStream = getClass().getClassLoader().getResourceAsStream(_propFileName);
            if (_inputStream == null) {
                throw new Exception("property file is empty");
            }
            _prop.load(_inputStream);
            _properyValue = _prop.getProperty(propertyKey);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return _properyValue;
    }

    public InputStream GetJKSCredentials(String propertyFile) throws Exception {
        InputStream _inputStream = (InputStream) getClass().getClassLoader().getResourceAsStream(GetProperties(propertyFile, "JKSFile"));
        return _inputStream;
    }
}
