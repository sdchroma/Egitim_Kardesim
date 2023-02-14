package com.egitim.kardesligi.Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.egitim.kardesligi.Entity.CreateStudentApplicationModel;

public class StudentApplicationOperations {
    public static Boolean CreateStudentApplication(CreateStudentApplicationModel studentApplications) throws Exception {
        ConfigurationPropertiesOperations _cpo = new ConfigurationPropertiesOperations();
        String url = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "URL_EGITIM");
        String username = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "USERNAME_EGITIM");
        String password = _cpo.GetProperties(_cpo.GetPropertiesFile("WEB_PROPERTY_FILE"), "PASSWORD_EGITIM");


        String _bulkInsertQuery="INSERT INTO STUDENT_CRAM_SCHOOL_APPLICATIONS(`IDENTITY_NUMBER`, `NAME`, `SURNAME`, `BIRTHDAY`, `PHONE_NUMBER`, `CRAM_SCHOOL_ID`, `CLASS_LEVEL_ID`, `CREATED_BY`, `MODIFIED_BY`) VALUES('{IDENTITY_NUMBER}','{NAME}','{SURNAME}','{BIRTHDAY}','{PHONE_NUMBER}',{CRAM_SCHOOL_ID},{CLASS_LEVEL_ID},1,1);";
        _bulkInsertQuery = _bulkInsertQuery.replace("{IDENTITY_NUMBER}", studentApplications.getIdentityNumber());
        _bulkInsertQuery = _bulkInsertQuery.replace("{NAME}", studentApplications.getName());
        _bulkInsertQuery = _bulkInsertQuery.replace("{SURNAME}", studentApplications.getSurname());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        _bulkInsertQuery = _bulkInsertQuery.replace("{BIRTHDAY}", String.valueOf(formatter.format(studentApplications.getBirthday())));
        _bulkInsertQuery = _bulkInsertQuery.replace("{PHONE_NUMBER}", studentApplications.getPhoneNumber());
        _bulkInsertQuery = _bulkInsertQuery.replace("{CRAM_SCHOOL_ID}", String.valueOf(studentApplications.getCramSchoolId()));
        _bulkInsertQuery = _bulkInsertQuery.replace("{CLASS_LEVEL_ID}", String.valueOf(studentApplications.getClassLevelId()));

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection _connection = DriverManager.getConnection(url, username, password);
        Statement _statement = _connection.createStatement();
        int _postBulkInsertResult = _statement.executeUpdate(_bulkInsertQuery);
        _statement.close();
        _connection.close();
        return true;
    }

    public static Boolean IsStudentIdentityExist(String identityNumber){
        return true;
    }
}
