package com.assac.controldecombustible.Storage.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "BD_ASSAC_CTRL_CTBLE_V3";

    public static final String TB_USER = "tb_user";
    public static final String TB_TRANSACTION = "tb_transaction";
    public static final String TB_PERSON = "tb_person";
    public static final String TB_DRIVER = "tb_driver";
    public static final String TB_OPERATOR = "tb_operator";
    public static final String TB_HARDWARE = "tb_hardware";
    public static final String TB_HOSE = "tb_hose";
    public static final String TB_PLATE = "tb_plate";
    public static final String TB_WORKSHIFT = "tb_workshift";
    public static final String TB_MIGRATION= "tb_migration";
    public static final String TB_MIGRATION_ERROR = "tb_migration_error";
    public static final String TB_ERROR= "tb_error";
    public static final String TB_CONFIGURATION = "tb_configuration";

    //Columnas de la Tabla USUARIO
    public static final String KEY_ID_SQLLITE_TB_USER = "idSqlLiteUser";
    public static final String KEY_ID_TB_USER = "IdUser";
    public static final String KEY_ID_PERSON_TB_USER = "IdPerson";
    public static final String KEY_PERSON_NAME_TB_USER = "PersonName";
    public static final String KEY_FIRST_LAST_NAME_TB_USER = "FirstLastName";
    public static final String KEY_SECOND_LAST_NAME_TB_USER = "SecondLastName";
    public static final String KEY_PHOTOCHECK_TB_USER = "Photocheck";
    public static final String KEY_LEVEL_TB_USER = "ULevel";
    public static final String KEY_USER_TB_USER = "UUser";
    public static final String KEY_PASSWORD_TB_USER = "UPassword";
    public static final String KEY_REGISTRATION_STATUS_TB_USER = "RegistrationStatus";

    //Columnas de la Tabla PERSONA
    public static final String KEY_ID_SQLLITE_TB_PERSON = "idSqlLitePerson";
    public static final String KEY_ID_TB_PERSON = "IdPerson";
    public static final String KEY_PERSON_NAME_TB_PERSON = "PersonName";
    public static final String KEY_FIRST_LAST_NAME_TB_PERSON = "FirstLastName";
    public static final String KEY_SECOND_LAST_NAME_TB_PERSON = "SecondLastName";
    public static final String KEY_PHOTOCHECK_TB_PERSON = "Photocheck";
    public static final String KEY_DOCUMENT_NUMBER_TB_PERSON = "DocumentNumber";
    public static final String KEY_DOCUMENT_PATHBASE64_TB_PERSON = "PathFileBase64";
    public static final String KEY_REGISTRATION_STATUS_TB_PERSON = "RegistrationStatus";

    //Columnas de la Tabla DRIVER
    public static final String KEY_ID_SQLLITE_TB_DRIVER = "idSqlLiteDrive";
    public static final String KEY_DRIVER_KEY_TB_DRIVER = "DriverKey";
    public static final String KEY_DRIVER_NAME_TB_DRIVER = "DriverName";

    //Columnas de la Tabla OPERATOR
    public static final String KEY_ID_SQLLITE_TB_OPERATOR = "idSqlLiteOperator";
    public static final String KEY_ID_TB_OPERATOR = "IdOperator";
    public static final String KEY_OPERATOR_KEY_TB_OPERATOR = "Operatorkey";
    public static final String KEY_USER_TB_OPERATOR = "OperatorUser";
    public static final String KEY_PASSWORD_TB_OPERATOR = "OperatorPassword";
    public static final String KEY_PERSON_NAME_TB_OPERATOR = "PersonName";
    public static final String KEY_FIRST_LAST_NAME_TB_OPERATOR = "FirstLastName";
    public static final String KEY_SECOND_LAST_NAME_TB_OPERATOR = "SecondLastName";
    public static final String KEY_PHOTOCHECK_TB_OPERATOR = "Photocheck";
    public static final String KEY_ID_VALIDATION_TB_OPERATOR = "IdOperatorValidationMap";
    public static final String KEY_DESCRIPTION_VALIDATION_TB_OPERATOR = "ValidationDescription";
    public static final String KEY_REGISTRATION_STATUS_TB_OPERATOR = "RegistrationStatus";


    //Columnas de la Tabla HARDWARE
    public static final String KEY_ID_SQLLITE_TB_HARDWARE = "idSqlLiteHardware";
    public static final String KEY_ID_TB_HARDWARE = "IdHardware";
    public static final String KEY_NAME_STATION_TB_HARDWARE = "StationName";
    public static final String KEY_LAST_TICKET_STATION_TB_HARDWARE = "StationLastTicket";

    //Columnas de la Tabla HOSE
    public static final String KEY_ID_SQLLITE_TB_HOSE = "idSqlLiteHose";
    public static final String KEY_NUMBER_HOSE_TB_HOSE = "HoseNumber";
    public static final String KEY_NAME_HOSE_TB_HOSE = "HoseName";
    public static final String KEY_NAME_PRODUCT_TB_HOSE = "ProductName";
    public static final String KEY_LAST_TICKET_TB_HOSE = "HoseLastTicket";
    public static final String KEY_LAST_QUANTITY_FUEL_TB_HOSE = "HoseLastQuantityFuel";
    public static final String KEY_STATUS_CURRENT_TB_HOSE = "HoseStatusCurrent";
    public static final String KEY_ID_HARDWARE_TB_HOSE = "HoseHardwareId";
    public static final String KEY_TOTALIZATOR_TB_HOSE = "HoseTotalizatorFuel";

    //Columnas de la Tabla PLATE
    public static final String KEY_ID_SQLLITE_TB_PLATE= "idSqlLitePlate";
    public static final String KEY_CODE_TB_PLATE = "PlateCode";
    public static final String KEY_STATUS_TB_PLATE = "PlateStatus";
    public static final String KEY_COMPANY_TB_PLATE = "PlateCompany";

    //Columnas de la Tabla MIGRACI??N
    public static final String KEY_ID_SQLLITE_TB_MIGRATION = "IdSqlLiteMigration";
    public static final String KEY_MIGRATION_START_DATE_TB_MIGRATION = "MigrationStartDate";
    public static final String KEY_DESCRIPTION_TB_MIGRATION = "MigrationDescription";
    public static final String KEY_MIGRATION_END_DATE_TB_MIGRATION = "MigrationEndDate";
    public static final String KEY_REGISTRATION_STATUS_TB_MIGRATION = "RegistrationStatus";
    public static final String KEY_REGISTRATION_USER_TB_MIGRATION = "RegistrationUser";

    //Columnas de la Tabla ERROR MIGRACI??N
    public static final String KEY_ID_SQLLITE_TB_ERROR = "IdSqlLiteErrorMigration";
    public static final String KEY_MIGRATION_START_DATE_TB_ERROR = "MigrationStartDate";
    public static final String KEY_ERROR_DESCRIPTION_TB_ERROR = "ErrorDescription";
    public static final String KEY_ERROR_DATE_TB_ERROR = "ErrorDate";
    public static final String KEY_REGISTRATION_USER_TB_ERROR = "RegistrationUser";

    //Columnas de la Tabla WORKSHIFT
    public static final String KEY_ID_SQLLITE_TB_WORKSHIFT = "IdSqlLiteWorkShift";
    public static final String KEY_ID_TB_WORKSHIFT = "IdWorkShift";
    public static final String KEY_NAME_SHIFT_TB_WORKSHIFT = "NameWorkShift";
    public static final String KEY_NICK_NAME_SHIFT_TB_WORKSHIFT = "NickNameWorkShift";
    public static final String KEY_START_TIME_TB_WORKSHIFT = "StartTimeWorkShift";
    public static final String KEY_END_TIME_TB_WORKSHIFT = "EndTimeWorkShift";

    //Columnas de la Tabla TRANSACTION
    public static final String KEY_ID_SQLLITE_TB_TRANSACTION = "idSqlLiteTransaction";
    public static final String KEY_ID_TB_TRANSACTION = "IdTransaction";
    public static final String KEY_TICKET_NUMBER_TB_TRANSACTION = "TicketNumber";
    public static final String KEY_HARDWARE_ID_TB_TRANSACTION = "HardwareId";
    public static final String KEY_HOSE_NUMBER_TB_TRANSACTION = "HoseNumber";
    public static final String KEY_HOSE_NAME_TB_TRANSACTION = "HoseName";
    public static final String KEY_VEHICLE_ID_TB_TRANSACTION = "VehicleId";
    public static final String KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION = "VehicleCodePlate";
    public static final String KEY_VEHICLE_HOROMETER_TB_TRANSACTION = "VehicleHorometer";
    public static final String KEY_VEHICLE_KILOMETER_TB_TRANSACTION = "VehicleKilometer";
    public static final String KEY_CONDUCTOR_KEY_TB_TRANSACTION = "ConductorKey";
    public static final String KEY_START_DATE_TB_TRANSACTION= "StartDateTransaction";
    public static final String KEY_START_HOUR_TB_TRANSACTION= "StartHourTransaction";
    public static final String KEY_END_DATE_TB_TRANSACTION = "EndDateTransaction";
    public static final String KEY_END_HOUR_TB_TRANSACTION= "EndHourTransaction";
    public static final String KEY_FUEL_QUANTITY_TB_TRANSACTION= "FuelQuantity";
    public static final String KEY_FUEL_TEMPERATURE_TB_TRANSACTION = "FuelTemperature";
    public static final String KEY_COMMENT_TB_TRANSACTION = "Comment";
    public static final String KEY_PRODUCT_NAME_TB_TRANSACTION = "ProductName";
    public static final String KEY_OPERATOR_KEY_TB_TRANSACTION = "OperatorKey";
    public static final String KEY_START_CONTOMETER_TB_TRANSACTION = "StartContometer";
    public static final String KEY_END_CONTOMETER_TB_TRANSACTION = "EndContometer";
    public static final String KEY_IMAGE_URI_TB_TRANSACTION = "ImageUri";
    public static final String KEY_REGISTRATION_USER_TB_TRANSACTION = "RegistrationUser";
    public static final String KEY_REGISTRATION_STATUS_TB_TRANSACTION= "RegistrationStatus";
    public static final String KEY_MIGRATION_STATUS_TB_TRANSACTION = "MigrationStatus";


    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    // TODO Auto-generated method stub
        String sqlUser= "CREATE TABLE " + TB_USER + "("
                + KEY_ID_SQLLITE_TB_USER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_ID_TB_USER + " INTEGER,"
                + KEY_ID_PERSON_TB_USER + " INTEGER,"
                + KEY_PERSON_NAME_TB_USER + " TEXT,"
                + KEY_FIRST_LAST_NAME_TB_USER + " TEXT,"
                + KEY_SECOND_LAST_NAME_TB_USER + " TEXT,"
                + KEY_PHOTOCHECK_TB_USER + " TEXT,"
                + KEY_LEVEL_TB_USER + " INTEGER,"
                + KEY_USER_TB_USER + " TEXT,"
                + KEY_PASSWORD_TB_USER + " TEXT,"
                + KEY_REGISTRATION_STATUS_TB_USER + " TEXT" + ")";
        db.execSQL(sqlUser);

        String sqlPerson= "CREATE TABLE " + TB_PERSON + "("
                + KEY_ID_SQLLITE_TB_PERSON + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_ID_TB_PERSON + " INTEGER,"
                + KEY_PERSON_NAME_TB_PERSON + " TEXT,"
                + KEY_FIRST_LAST_NAME_TB_PERSON + " TEXT,"
                + KEY_SECOND_LAST_NAME_TB_PERSON + " TEXT,"
                + KEY_PHOTOCHECK_TB_PERSON + " TEXT,"
                + KEY_DOCUMENT_NUMBER_TB_PERSON + " TEXT,"
                + KEY_DOCUMENT_PATHBASE64_TB_PERSON + " TEXT,"
                + KEY_REGISTRATION_STATUS_TB_PERSON + " TEXT" + ")";
        db.execSQL(sqlPerson);

        String sqlTransaction= "CREATE TABLE " + TB_TRANSACTION + "("
                + KEY_ID_SQLLITE_TB_TRANSACTION + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_ID_TB_TRANSACTION + " INTEGER,"
                + KEY_TICKET_NUMBER_TB_TRANSACTION + " INTEGER,"
                + KEY_HARDWARE_ID_TB_TRANSACTION + " INTEGER,"
                + KEY_HOSE_NUMBER_TB_TRANSACTION + " INTEGER,"
                + KEY_HOSE_NAME_TB_TRANSACTION + " TEXT,"
                + KEY_VEHICLE_ID_TB_TRANSACTION + " TEXT,"
                + KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION + " TEXT,"
                + KEY_VEHICLE_HOROMETER_TB_TRANSACTION + " TEXT,"
                + KEY_VEHICLE_KILOMETER_TB_TRANSACTION + " TEXT,"
                + KEY_START_DATE_TB_TRANSACTION + " TEXT,"
                + KEY_START_HOUR_TB_TRANSACTION + " TEXT,"
                + KEY_END_DATE_TB_TRANSACTION + " TEXT,"
                + KEY_END_HOUR_TB_TRANSACTION + " TEXT,"
                + KEY_CONDUCTOR_KEY_TB_TRANSACTION + " TEXT,"
                + KEY_FUEL_QUANTITY_TB_TRANSACTION + " TEXT,"
                + KEY_FUEL_TEMPERATURE_TB_TRANSACTION + " TEXT,"
                + KEY_COMMENT_TB_TRANSACTION + " TEXT,"
                + KEY_PRODUCT_NAME_TB_TRANSACTION + " TEXT,"
                + KEY_OPERATOR_KEY_TB_TRANSACTION + " TEXT,"
                + KEY_START_CONTOMETER_TB_TRANSACTION + " TEXT,"
                + KEY_END_CONTOMETER_TB_TRANSACTION + " TEXT,"
                + KEY_IMAGE_URI_TB_TRANSACTION + " TEXT,"
                + KEY_REGISTRATION_USER_TB_TRANSACTION + " TEXT,"
                + KEY_REGISTRATION_STATUS_TB_TRANSACTION + " TEXT,"
                + KEY_MIGRATION_STATUS_TB_TRANSACTION + " TEXT "
                + ")";
        db.execSQL(sqlTransaction);

        String sqlMigrationError= "CREATE TABLE " + TB_MIGRATION_ERROR + "("
                + KEY_ID_SQLLITE_TB_ERROR+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_MIGRATION_START_DATE_TB_ERROR + " TEXT,"
                + KEY_ERROR_DESCRIPTION_TB_ERROR + " TEXT,"
                + KEY_ERROR_DATE_TB_ERROR + " TEXT,"
                + KEY_REGISTRATION_USER_TB_ERROR + " TEXT" + ")";
        db.execSQL(sqlMigrationError);

        String sqlMigration= "CREATE TABLE " + TB_MIGRATION + "("
                + KEY_ID_SQLLITE_TB_MIGRATION + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_MIGRATION_START_DATE_TB_MIGRATION + " TEXT,"
                + KEY_DESCRIPTION_TB_MIGRATION + " TEXT,"
                + KEY_MIGRATION_END_DATE_TB_MIGRATION +  " TEXT,"
                + KEY_REGISTRATION_STATUS_TB_MIGRATION + " TEXT,"
                + KEY_REGISTRATION_USER_TB_MIGRATION + " TEXT "
                + ")";
        db.execSQL(sqlMigration);


        String sqlDriver= "CREATE TABLE " + TB_DRIVER + "("
                + KEY_ID_SQLLITE_TB_DRIVER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_DRIVER_KEY_TB_DRIVER + " TEXT,"
                + KEY_DRIVER_NAME_TB_DRIVER +  " TEXT" + ")";
        db.execSQL(sqlDriver);


        String sqlOperator= "CREATE TABLE " + TB_OPERATOR + "("
                + KEY_ID_SQLLITE_TB_OPERATOR + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_ID_TB_OPERATOR+ " INTEGER,"
                + KEY_OPERATOR_KEY_TB_OPERATOR+ " TEXT,"
                + KEY_USER_TB_OPERATOR+ " TEXT,"
                + KEY_PASSWORD_TB_OPERATOR+ " TEXT,"
                + KEY_PERSON_NAME_TB_OPERATOR+ " TEXT,"
                + KEY_FIRST_LAST_NAME_TB_OPERATOR+ " TEXT,"
                + KEY_SECOND_LAST_NAME_TB_OPERATOR+ " TEXT,"
                + KEY_PHOTOCHECK_TB_OPERATOR+ " TEXT,"
                + KEY_ID_VALIDATION_TB_OPERATOR+ " INTEGER,"
                + KEY_DESCRIPTION_VALIDATION_TB_OPERATOR+ " TEXT,"
                + KEY_REGISTRATION_STATUS_TB_OPERATOR + " TEXT" + ")";
        db.execSQL(sqlOperator);

        String sqlHardware= "CREATE TABLE " + TB_HARDWARE + "("
                + KEY_ID_SQLLITE_TB_HARDWARE + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_ID_TB_HARDWARE + " INTEGER,"
                + KEY_NAME_STATION_TB_HARDWARE +  " TEXT,"
                + KEY_LAST_TICKET_STATION_TB_HARDWARE +  " INTEGER"
                + ")";
        db.execSQL(sqlHardware);

        String sqlHose= "CREATE TABLE " + TB_HOSE + "("
                + KEY_ID_SQLLITE_TB_HOSE + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_NUMBER_HOSE_TB_HOSE + " INTEGER,"
                + KEY_NAME_HOSE_TB_HOSE + " TEXT,"
                + KEY_NAME_PRODUCT_TB_HOSE +  " TEXT,"
                + KEY_LAST_TICKET_TB_HOSE +  " INTEGER,"
                + KEY_LAST_QUANTITY_FUEL_TB_HOSE +  " REAL,"
                + KEY_STATUS_CURRENT_TB_HOSE +  " INTEGER,"
                + KEY_ID_HARDWARE_TB_HOSE +  " INTEGER,"
                + KEY_TOTALIZATOR_TB_HOSE +  " TEXT"
                + ")";
        db.execSQL(sqlHose);

        String sqlPlate= "CREATE TABLE " + TB_PLATE + "("
                + KEY_ID_SQLLITE_TB_PLATE + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_CODE_TB_PLATE + " TEXT,"
                + KEY_STATUS_TB_PLATE + " TEXT,"
                + KEY_COMPANY_TB_PLATE +  " TEXT"
                + ")";
        db.execSQL(sqlPlate);

        String sqlWorkShift= "CREATE TABLE " + TB_WORKSHIFT + "("
                + KEY_ID_SQLLITE_TB_WORKSHIFT + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_ID_TB_WORKSHIFT + " INTEGER,"
                + KEY_NAME_SHIFT_TB_WORKSHIFT + " TEXT,"
                + KEY_NICK_NAME_SHIFT_TB_WORKSHIFT +  " TEXT,"
                + KEY_START_TIME_TB_WORKSHIFT +  " TEXT,"
                + KEY_END_TIME_TB_WORKSHIFT +  " TEXT"
                + ")";
        db.execSQL(sqlWorkShift);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sqlDropUser= "DROP TABLE IF EXISTS " + TB_USER;
        db.execSQL(sqlDropUser);
        String sqlDropTransaction= "DROP TABLE IF EXISTS " + TB_TRANSACTION;
        db.execSQL(sqlDropTransaction);
        String sqlDropMigrationError= "DROP TABLE IF EXISTS " + TB_MIGRATION_ERROR;
        db.execSQL(sqlDropMigrationError);
        String sqlDropMigration= "DROP TABLE IF EXISTS " + TB_MIGRATION;
        db.execSQL(sqlDropMigration);
        String sqlDropPerson= "DROP TABLE IF EXISTS " + TB_PERSON;
        db.execSQL(sqlDropPerson);
        String sqlDropDriver= "DROP TABLE IF EXISTS " + TB_DRIVER;
        db.execSQL(sqlDropDriver);
        String sqlDropOperator= "DROP TABLE IF EXISTS " + TB_OPERATOR;
        db.execSQL(sqlDropOperator);
        String sqlDropHardware= "DROP TABLE IF EXISTS " + TB_HARDWARE;
        db.execSQL(sqlDropHardware);
        String sqlDropHose= "DROP TABLE IF EXISTS " + TB_HOSE;
        db.execSQL(sqlDropHose);
        String sqlDropPlate= "DROP TABLE IF EXISTS " + TB_PLATE;
        db.execSQL(sqlDropPlate);
        String sqlDropWorkShift= "DROP TABLE IF EXISTS " + TB_WORKSHIFT;
        db.execSQL(sqlDropWorkShift);
    }
}
