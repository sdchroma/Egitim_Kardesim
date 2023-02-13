package com.egitim.kardesligi.Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.egitim.kardesligi.Entity.City;

public class CityOperations {
    
    public static List<City> GetCities() throws Exception {
        ConfigurationPropertiesOperations _cpo = new ConfigurationPropertiesOperations();
        String url = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "URL_EGITIM");
        String username = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "USERNAME_EGITIM");
        String password = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "PASSWORD_EGITIM");

        StringBuilder _getCitiesQueryBuilder = new StringBuilder();
        _getCitiesQueryBuilder.append("SELECT `C`.`ID` , `C`.`CITY_CODE` , `C`.`CITY_NAME` , `C`.`COUNTRY_ID` , `C`.`CREATED_BY` , `C`.`CREATED_DATE` , `C`.`MODIFIED_BY` , `C`.`MODIFIED_DATE` FROM `CITIES` AS `C` INNER JOIN `DISTRICTS` AS `D` ON(`C`.`ID`=`D`.`CITY_ID`) INNER JOIN `CRAM_SCHOOLS` AS `CS` ON(`D`.`ID`=`CS`.`DISTRICT_ID`) INNER JOIN ( SELECT `QR`.`ID` , `QR`.`CRAM_SCHOOL_ID` , `QR`.`CLASS_LEVEL_ID` , `QR`.`EXISTING_QUOTA` , `QR`.`CREATED_BY` , `QR`.`CREATED_DATE` , `QR`.`MODIFIED_BY` , `QR`.`MODIFIED_DATE` FROM ( SELECT `CSQ`.`ID` , `CSQ`.`CRAM_SCHOOL_ID` , `CSQ`.`CLASS_LEVEL_ID` , (`CSQ`.`QUOTA` - IFNULL(`AC`.`APPLICATION_COUNT`, 0)) AS `EXISTING_QUOTA` , `CSQ`.`CREATED_BY` , `CSQ`.`CREATED_DATE` , `CSQ`.`MODIFIED_BY` , `CSQ`.`MODIFIED_DATE` FROM `CRAM_SCHOOL_QUOTAS` AS `CSQ` LEFT JOIN ( SELECT `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID`, COUNT(1) AS `APPLICATION_COUNT` FROM `STUDENT_CRAM_SCHOOL_APPLICATIONS` AS `SCSA` GROUP BY `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID` ) AS `AC` ON(`CSQ`.`CRAM_SCHOOL_ID`=`AC`.`CRAM_SCHOOL_ID` AND `CSQ`.`CLASS_LEVEL_ID`=`AC`.`CLASS_LEVEL_ID`) ) AS `QR` WHERE `EXISTING_QUOTA`>0 ) AS `CSQ2` ON(`CS`.`ID`=`CSQ2`.`CRAM_SCHOOL_ID`) GROUP BY `C`.`ID`;");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection _connection = DriverManager.getConnection(url, username, password);
        Statement _statement = _connection.createStatement();
        String _getCitiesQuery = _getCitiesQueryBuilder.toString();
        ResultSet _citiesResultSet = _statement.executeQuery(_getCitiesQuery);
        List<City> _cities = new ArrayList<>();
        while(_citiesResultSet.next())
        {
            City _city = new City();
            _city.setId(_citiesResultSet.getLong("ID"));
            _city.setCityCode(_citiesResultSet.getString("CITY_CODE"));
            _city.setCityName(_citiesResultSet.getString("CITY_NAME"));
            _city.setCountryId(_citiesResultSet.getLong("COUNTRY_ID"));
            _city.setCreatedBy(_citiesResultSet.getLong("CREATED_BY"));
            _city.setCreatedDate(_citiesResultSet.getDate("CREATED_DATE"));
            _city.setModifiedBy(_citiesResultSet.getLong("MODIFIED_BY"));
            _city.setModifiedDate(_citiesResultSet.getDate("MODIFIED_DATE"));
            _cities.add(_city);
        }
        _statement.close();
        _connection.close();

        return _cities;
    }
}
