package com.egitim.kardesligi.Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.egitim.kardesligi.Entity.District;

public class DistrictOperation {
    
    public static List<District> GetDistrictsByCityId(long cityId) throws Exception {
        ConfigurationPropertiesOperations _cpo = new ConfigurationPropertiesOperations();
        String url = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "URL_EGITIM");
        String username = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "USERNAME_EGITIM");
        String password = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "PASSWORD_EGITIM");

        String _getDistrictsQueryBuilder ="SELECT `D`.`ID` , `D`.`DISTRICT_NAME` , `D`.`CITY_ID` , `D`.`CREATED_BY` , `D`.`CREATED_DATE` , `D`.`MODIFIED_BY` , `D`.`MODIFIED_DATE` FROM `DISTRICTS` AS `D` INNER JOIN `CRAM_SCHOOLS` AS `CS` ON(`D`.`ID`=`CS`.`DISTRICT_ID`) INNER JOIN ( SELECT `QR`.`ID` , `QR`.`CRAM_SCHOOL_ID` , `QR`.`CLASS_LEVEL_ID` , `QR`.`EXISTING_QUOTA` , `QR`.`CREATED_BY` , `QR`.`CREATED_DATE` , `QR`.`MODIFIED_BY` , `QR`.`MODIFIED_DATE` FROM ( SELECT `CSQ`.`ID` , `CSQ`.`CRAM_SCHOOL_ID` , `CSQ`.`CLASS_LEVEL_ID` , (`CSQ`.`QUOTA` - IFNULL(`AC`.`APPLICATION_COUNT`, 0)) AS `EXISTING_QUOTA` , `CSQ`.`CREATED_BY` , `CSQ`.`CREATED_DATE` , `CSQ`.`MODIFIED_BY` , `CSQ`.`MODIFIED_DATE` FROM `CRAM_SCHOOL_QUOTAS` AS `CSQ` LEFT JOIN ( SELECT `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID`, COUNT(1) AS `APPLICATION_COUNT` FROM `STUDENT_CRAM_SCHOOL_APPLICATIONS` AS `SCSA` GROUP BY `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID` ) AS `AC` ON(`CSQ`.`CRAM_SCHOOL_ID`=`AC`.`CRAM_SCHOOL_ID` AND `CSQ`.`CLASS_LEVEL_ID`=`AC`.`CLASS_LEVEL_ID`) ) AS `QR` WHERE `EXISTING_QUOTA`>0 ) AS `CSQ2` ON(`CS`.`ID`=`CSQ2`.`CRAM_SCHOOL_ID`) WHERE CITY_ID={CITY_ID} GROUP BY `D`.`ID`;";
        _getDistrictsQueryBuilder = _getDistrictsQueryBuilder.replace("{CITY_ID}", String.valueOf(cityId));
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection _connection = DriverManager.getConnection(url, username, password);
        Statement _statement = _connection.createStatement();
        String _getDistrictsQuery = _getDistrictsQueryBuilder.toString();
        ResultSet _districtsResultSet = _statement.executeQuery(_getDistrictsQuery);
        List<District> _districts = new ArrayList<>();
        while(_districtsResultSet.next())
        {
            District _district = new District();
            _district.setId(_districtsResultSet.getLong("ID"));
            _district.setDistrictName(_districtsResultSet.getString("DISTRICT_NAME"));
            _district.setCityId(_districtsResultSet.getLong("CITY_ID"));
            _district.setCreatedBy(_districtsResultSet.getLong("CREATED_BY"));
            _district.setCreatedDate(_districtsResultSet.getDate("CREATED_DATE"));
            _district.setModifiedBy(_districtsResultSet.getLong("MODIFIED_BY"));
            _district.setModifiedDate(_districtsResultSet.getDate("MODIFIED_DATE"));
            _districts.add(_district);
        }
        _statement.close();
        _connection.close();

        return _districts;
    }
}
