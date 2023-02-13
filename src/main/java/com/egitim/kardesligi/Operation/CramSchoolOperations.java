package com.egitim.kardesligi.Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.egitim.kardesligi.Entity.CramSchool;
import com.egitim.kardesligi.Entity.CramSchoolQuota;

public class CramSchoolOperations {
    
    public static List<CramSchool> GetDistrictsByCityId(long districtId) throws Exception {
        ConfigurationPropertiesOperations _cpo = new ConfigurationPropertiesOperations();
        String url = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "URL_EGITIM");
        String username = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "USERNAME_EGITIM");
        String password = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "PASSWORD_EGITIM");

        String _getCramSchoolsQueryBuilder ="SELECT `CS`.`ID` , `CS`.`CRAM_SCHOOL_NAME` , `CS`.`DISTRICT_ID` , `CS`.`CREATED_BY` , `CS`.`CREATED_DATE` , `CS`.`MODIFIED_BY` , `CS`.`MODIFIED_DATE` FROM `CRAM_SCHOOLS` AS `CS` INNER JOIN ( SELECT `QR`.`ID` , `QR`.`CRAM_SCHOOL_ID` , `QR`.`CLASS_LEVEL_ID` , `QR`.`EXISTING_QUOTA` , `QR`.`CREATED_BY` , `QR`.`CREATED_DATE` , `QR`.`MODIFIED_BY` , `QR`.`MODIFIED_DATE` FROM ( SELECT `CSQ`.`ID` , `CSQ`.`CRAM_SCHOOL_ID` , `CSQ`.`CLASS_LEVEL_ID` , (`CSQ`.`QUOTA` - IFNULL(`AC`.`APPLICATION_COUNT`, 0)) AS `EXISTING_QUOTA` , `CSQ`.`CREATED_BY` , `CSQ`.`CREATED_DATE` , `CSQ`.`MODIFIED_BY` , `CSQ`.`MODIFIED_DATE` FROM `CRAM_SCHOOL_QUOTAS` AS `CSQ` LEFT JOIN ( SELECT `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID`, COUNT(1) AS `APPLICATION_COUNT` FROM `STUDENT_CRAM_SCHOOL_APPLICATIONS` AS `SCSA` GROUP BY `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID` ) AS `AC` ON(`CSQ`.`CRAM_SCHOOL_ID`=`AC`.`CRAM_SCHOOL_ID` AND `CSQ`.`CLASS_LEVEL_ID`=`AC`.`CLASS_LEVEL_ID`) ) AS `QR` WHERE `EXISTING_QUOTA`>0 ) AS `CSQ2` ON(`CS`.`ID`=`CSQ2`.`CRAM_SCHOOL_ID`) WHERE `CS`.`DISTRICT_ID` = {DISTRICT_ID} GROUP BY `CS`.`ID`;";
        _getCramSchoolsQueryBuilder = _getCramSchoolsQueryBuilder.replace("{DISTRICT_ID}", String.valueOf(districtId));
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection _connection = DriverManager.getConnection(url, username, password);
        Statement _statement = _connection.createStatement();
        String _getCramSchoolsQuery = _getCramSchoolsQueryBuilder.toString();
        ResultSet _cramSchoolsResultSet = _statement.executeQuery(_getCramSchoolsQuery);
        List<CramSchool> _cramSchools = new ArrayList<>();
        while(_cramSchoolsResultSet.next())
        {
            CramSchool _cramSchool = new CramSchool();
            _cramSchool.setId(_cramSchoolsResultSet.getLong("ID"));
            _cramSchool.setCramSchoolName(_cramSchoolsResultSet.getString("CRAM_SCHOOL_NAME"));
            _cramSchool.setDistrictId(_cramSchoolsResultSet.getLong("DISTRICT_ID"));
            _cramSchool.setCreatedBy(_cramSchoolsResultSet.getLong("CREATED_BY"));
            _cramSchool.setCreatedDate(_cramSchoolsResultSet.getDate("CREATED_DATE"));
            _cramSchool.setModifiedBy(_cramSchoolsResultSet.getLong("MODIFIED_BY"));
            _cramSchool.setModifiedDate(_cramSchoolsResultSet.getDate("MODIFIED_DATE"));
            _cramSchools.add(_cramSchool);
        }
        _statement.close();
        _connection.close();

        return _cramSchools;
    }

    public static List<CramSchoolQuota> GetCramSchoolQuotasBySchoolId(long cramSchoolId) throws Exception {
        ConfigurationPropertiesOperations _cpo = new ConfigurationPropertiesOperations();
        String url = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "URL_EGITIM");
        String username = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "USERNAME_EGITIM");
        String password = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "PASSWORD_EGITIM");

        String _getCramSchoolQuotasQueryBuilder ="SELECT `QR`.`ID` , `QR`.`CRAM_SCHOOL_ID` , `QR`.`CLASS_LEVEL_ID` , `CL`.`CLASS_LEVEL_NAME` , `QR`.`EXISTING_QUOTA` , `QR`.`CREATED_BY` , `QR`.`CREATED_DATE` , `QR`.`MODIFIED_BY` , `QR`.`MODIFIED_DATE` FROM ( SELECT `CSQ`.`ID` , `CSQ`.`CRAM_SCHOOL_ID` , `CSQ`.`CLASS_LEVEL_ID` , (`CSQ`.`QUOTA` - IFNULL(`AC`.`APPLICATION_COUNT`, 0)) AS `EXISTING_QUOTA` , `CSQ`.`CREATED_BY` , `CSQ`.`CREATED_DATE` , `CSQ`.`MODIFIED_BY` , `CSQ`.`MODIFIED_DATE` FROM `CRAM_SCHOOL_QUOTAS` AS `CSQ` LEFT JOIN ( SELECT `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID`, COUNT(1) AS `APPLICATION_COUNT` FROM `STUDENT_CRAM_SCHOOL_APPLICATIONS` AS `SCSA` GROUP BY `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID` ) AS `AC` ON(`CSQ`.`CRAM_SCHOOL_ID`=`AC`.`CRAM_SCHOOL_ID` AND `CSQ`.`CLASS_LEVEL_ID`=`AC`.`CLASS_LEVEL_ID`) ) AS `QR` INNER JOIN `CLASS_LEVELS` AS `CL` ON(`QR`.`CLASS_LEVEL_ID`=`CL`.`ID`) WHERE `EXISTING_QUOTA`>0 AND `QR`.`CRAM_SCHOOL_ID` = {CRAM_SCHOOL_ID} ORDER BY `CL`.`ID`;";
        _getCramSchoolQuotasQueryBuilder = _getCramSchoolQuotasQueryBuilder.replace("{CRAM_SCHOOL_ID}", String.valueOf(cramSchoolId));
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection _connection = DriverManager.getConnection(url, username, password);
        Statement _statement = _connection.createStatement();
        String _getCramSchoolQuotasQuery = _getCramSchoolQuotasQueryBuilder.toString();
        ResultSet _cramSchoolQuotasResultSet = _statement.executeQuery(_getCramSchoolQuotasQuery);
        List<CramSchoolQuota> _cramSchoolQuotas = new ArrayList<>();
        while(_cramSchoolQuotasResultSet.next())
        {
            CramSchoolQuota _cramSchoolQuota = new CramSchoolQuota();
            _cramSchoolQuota.setId(_cramSchoolQuotasResultSet.getLong("ID"));
            _cramSchoolQuota.setCramSchoolId(_cramSchoolQuotasResultSet.getLong("CRAM_SCHOOL_ID"));
            _cramSchoolQuota.setClassLevelId(_cramSchoolQuotasResultSet.getLong("CLASS_LEVEL_ID"));
            _cramSchoolQuota.setClassLevelName(_cramSchoolQuotasResultSet.getString("CLASS_LEVEL_NAME"));
            _cramSchoolQuota.setExistingQuota(_cramSchoolQuotasResultSet.getInt("EXISTING_QUOTA"));
            _cramSchoolQuota.setCreatedBy(_cramSchoolQuotasResultSet.getLong("CREATED_BY"));
            _cramSchoolQuota.setCreatedDate(_cramSchoolQuotasResultSet.getDate("CREATED_DATE"));
            _cramSchoolQuota.setModifiedBy(_cramSchoolQuotasResultSet.getLong("MODIFIED_BY"));
            _cramSchoolQuota.setModifiedDate(_cramSchoolQuotasResultSet.getDate("MODIFIED_DATE"));
            _cramSchoolQuotas.add(_cramSchoolQuota);
        }
        _statement.close();
        _connection.close();

        return _cramSchoolQuotas;
    }
}
