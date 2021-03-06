package com.assac.controldecombustible.Storage.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assac.controldecombustible.Entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CRUDOperations {

    private MyDatabase helper;
    public CRUDOperations(SQLiteOpenHelper _helper) {
        super();
        // TODO Auto-generated constructor stub
        helper =(MyDatabase)_helper;
    }

    //TB_USER
    public int addUser(UserEntity userEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_ID_TB_USER, userEntity.getIdUser());
        values.put(MyDatabase.KEY_ID_PERSON_TB_USER, userEntity.getIdPerson());
        values.put(MyDatabase.KEY_PERSON_NAME_TB_USER, userEntity.getPersonName());
        values.put(MyDatabase.KEY_FIRST_LAST_NAME_TB_USER, userEntity.getFirstLastName());
        values.put(MyDatabase.KEY_SECOND_LAST_NAME_TB_USER, userEntity.getSecondLastName());
        values.put(MyDatabase.KEY_PHOTOCHECK_TB_USER, userEntity.getPhotocheck());
        values.put(MyDatabase.KEY_LEVEL_TB_USER, userEntity.getULevel());
        values.put(MyDatabase.KEY_USER_TB_USER, userEntity.getUUser().toUpperCase());
        values.put(MyDatabase.KEY_PASSWORD_TB_USER, userEntity.getUPassword());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_USER, userEntity.getRegistrationStatus());

        int row = (int) db.insert(MyDatabase.TB_USER, null, values);
        db.close();
        return row;
    }

    public int updateUser(UserEntity userEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_ID_PERSON_TB_USER, userEntity.getIdPerson());
        values.put(MyDatabase.KEY_PERSON_NAME_TB_USER, userEntity.getPersonName());
        values.put(MyDatabase.KEY_FIRST_LAST_NAME_TB_USER, userEntity.getFirstLastName());
        values.put(MyDatabase.KEY_SECOND_LAST_NAME_TB_USER, userEntity.getSecondLastName());
        values.put(MyDatabase.KEY_PHOTOCHECK_TB_USER, userEntity.getPhotocheck());
        values.put(MyDatabase.KEY_LEVEL_TB_USER, userEntity.getULevel());
        values.put(MyDatabase.KEY_USER_TB_USER, userEntity.getUUser().toUpperCase());
        values.put(MyDatabase.KEY_PASSWORD_TB_USER, userEntity.getUPassword());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_USER, userEntity.getRegistrationStatus());

        int row =db.update(MyDatabase.TB_USER,
                values,
                MyDatabase.KEY_ID_TB_USER+"=? AND "+MyDatabase.KEY_LEVEL_TB_USER+"=?",
                new String[]{String.valueOf(userEntity.getIdUser()),String.valueOf(userEntity.getULevel())});
        db.close();

        return row;
    }

    public int deleteUser(UserEntity userEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        int row= db.delete(MyDatabase.TB_USER,
                MyDatabase.KEY_ID_TB_USER+"=? AND "+MyDatabase.KEY_LEVEL_TB_USER+"=?",
                new String[]{String.valueOf(userEntity.getIdUser()),String.valueOf(userEntity.getULevel())});
        db.close();
        return row;
    }

    public UserEntity getUser(int id, int level)
    {
        UserEntity userEntity= new UserEntity();
        SQLiteDatabase db = helper.getReadableDatabase(); //modo lectura
        Cursor cursor = db.query(MyDatabase.TB_USER,
                new String[]{
                        MyDatabase.KEY_ID_SQLLITE_TB_USER,
                        MyDatabase.KEY_ID_TB_USER,
                        MyDatabase.KEY_ID_PERSON_TB_USER,
                        MyDatabase.KEY_PERSON_NAME_TB_USER,
                        MyDatabase.KEY_FIRST_LAST_NAME_TB_USER,
                        MyDatabase.KEY_SECOND_LAST_NAME_TB_USER,
                        MyDatabase.KEY_PHOTOCHECK_TB_USER,
                        MyDatabase.KEY_LEVEL_TB_USER,
                        MyDatabase.KEY_USER_TB_USER,
                        MyDatabase.KEY_PASSWORD_TB_USER,
                        MyDatabase.KEY_REGISTRATION_STATUS_TB_USER},
                MyDatabase.KEY_ID_TB_USER+"=? AND "+MyDatabase.KEY_LEVEL_TB_USER+"=?",
                new String[]{String.valueOf(id),String.valueOf(level)}, null, null, null);
        if(cursor!=null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            int idSqlLite = Integer.parseInt(cursor.getString(0));
            int IdUser = Integer.parseInt(cursor.getString(1));
            int IdPerson = Integer.parseInt(cursor.getString(2));
            String PersonName = cursor.getString(3);
            String FirstLastName = cursor.getString(4);
            String SecondLastName = cursor.getString(5);
            String Photocheck = cursor.getString(6);
            int ULevel = Integer.parseInt(cursor.getString(7));
            String UUser = cursor.getString(8);
            String UPassword = cursor.getString(9);
            String RegistrationStatus = cursor.getString(10);

            userEntity= new UserEntity(idSqlLite, IdUser,IdPerson, PersonName,Photocheck,FirstLastName,SecondLastName,ULevel,UUser,UPassword,RegistrationStatus);
        }

        return userEntity;
    }

    public UserEntity getUserForLogin(String user, String password)
    {
        UserEntity userEntity= new UserEntity();
        SQLiteDatabase db = helper.getReadableDatabase(); //modo lectura
        Cursor cursor = db.query(MyDatabase.TB_USER,
                new String[]{
                        MyDatabase.KEY_ID_SQLLITE_TB_USER,
                        MyDatabase.KEY_ID_TB_USER,
                        MyDatabase.KEY_ID_PERSON_TB_USER,
                        MyDatabase.KEY_PERSON_NAME_TB_USER,
                        MyDatabase.KEY_FIRST_LAST_NAME_TB_USER,
                        MyDatabase.KEY_SECOND_LAST_NAME_TB_USER,
                        MyDatabase.KEY_PHOTOCHECK_TB_USER,
                        MyDatabase.KEY_LEVEL_TB_USER,
                        MyDatabase.KEY_USER_TB_USER,
                        MyDatabase.KEY_PASSWORD_TB_USER,
                        MyDatabase.KEY_REGISTRATION_STATUS_TB_USER},
                MyDatabase.KEY_USER_TB_USER + "=? AND "+ MyDatabase.KEY_PASSWORD_TB_USER + "=?",
                new String[]{user,password}, null, null, null);
        if(cursor!=null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            int idSqlLite = Integer.parseInt(cursor.getString(0));
            int IdUser = Integer.parseInt(cursor.getString(1));
            int IdPerson = Integer.parseInt(cursor.getString(2));
            String PersonName = cursor.getString(3);
            String FirstLastName = cursor.getString(4);
            String SecondLastName = cursor.getString(5);
            String Photocheck = cursor.getString(6);
            int ULevel = Integer.parseInt(cursor.getString(7));
            String UUser = cursor.getString(8);
            String UPassword = cursor.getString(9);
            String RegistrationStatus = cursor.getString(10);

            userEntity= new UserEntity(idSqlLite, IdUser,IdPerson, PersonName,Photocheck,FirstLastName,SecondLastName,ULevel,UUser,UPassword,RegistrationStatus);
        }

        return userEntity;
    }

    public List<UserEntity> getAllUsers()
    {
        List<UserEntity> lst =new ArrayList<UserEntity>();
        String sql= "SELECT  * FROM " + MyDatabase.TB_USER;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                UserEntity userEntity =new UserEntity();
                userEntity.setIdSqlLite(Integer.parseInt(cursor.getString(0)));
                userEntity.setIdUser(Integer.parseInt(cursor.getString(1)));
                userEntity.setIdPerson(Integer.parseInt(cursor.getString(2)));
                userEntity.setPersonName(cursor.getString(3));
                userEntity.setFirstLastName(cursor.getString(4));
                userEntity.setSecondLastName(cursor.getString(5));
                userEntity.setPhotocheck(cursor.getString(6));
                userEntity.setULevel(Integer.parseInt(cursor.getString(7)));
                userEntity.setUUser(cursor.getString(8));
                userEntity.setUPassword(cursor.getString(9));
                userEntity.setRegistrationStatus(cursor.getString(10));

                lst.add(userEntity);
            }while(cursor.moveToNext());
        }
        return lst;
    }

    public int getUserCount()
    {
        int totalCount = 0;
        String sql= "SELECT * FROM "+MyDatabase.TB_USER;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        totalCount = cursor.getCount();
        cursor.close();

        return totalCount;
    }

    //TB_MIGRATION_ERROR
    public int addMigrationError(MigrationErrorEntity migrationErrorEntity)
    {
        int row = 0;
        try{
            SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
            ContentValues values = new ContentValues();
            values.put(MyDatabase.KEY_MIGRATION_START_DATE_TB_ERROR, migrationErrorEntity.getMigrationStartDate());
            values.put(MyDatabase.KEY_ERROR_DESCRIPTION_TB_ERROR, migrationErrorEntity.getErrorDescription());
            values.put(MyDatabase.KEY_ERROR_DATE_TB_ERROR, migrationErrorEntity.getErrorDate());
            values.put(MyDatabase.KEY_REGISTRATION_USER_TB_ERROR, migrationErrorEntity.getRegistrationUser());

            row = (int) db.insert(MyDatabase.TB_MIGRATION_ERROR, null, values);
            db.close();

        }catch (Exception ex){
            Log.e("ERROR CRUD", "addMigrationError /"+ ex.getMessage());
        }
        return row;
    }

    public List<MigrationErrorEntity> getAllMigrationError(String fecha)
    {
        List<MigrationErrorEntity> lst =new ArrayList<MigrationErrorEntity>();
        try{

            String todayX = "'"+fecha + "'";
            String sql= "SELECT  * FROM " + MyDatabase.TB_MIGRATION_ERROR + " WHERE substr("+ MyDatabase.KEY_ERROR_DATE_TB_ERROR + ",1,10) = " + todayX +" ORDER BY " + MyDatabase.KEY_ID_SQLLITE_TB_ERROR + " DESC";
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor.moveToFirst())
            {
                do
                {
                    MigrationErrorEntity migrationErrorEntity =new MigrationErrorEntity();
                    migrationErrorEntity.setIdMigrationError(Integer.parseInt(cursor.getString(0)));
                    migrationErrorEntity.setMigrationStartDate(cursor.getString(1));
                    migrationErrorEntity.setErrorDescription(cursor.getString(2));
                    migrationErrorEntity.setErrorDate(cursor.getString(3));
                    migrationErrorEntity.setRegistrationUser(cursor.getString(4));
                    lst.add(migrationErrorEntity);
                }while(cursor.moveToNext());
            }

        }catch (Exception ex){
            Log.e("ERROR CRUD", "getAllMigrationError /"+ ex.getMessage());
        }
        return lst;

    }

    //TRANSACTION
    public int addTransaction(TransactionEntity transactionEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_ID_TB_TRANSACTION, transactionEntity.getIdTransaction());
        values.put(MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION, transactionEntity.getNumeroTransaccion());
        values.put(MyDatabase.KEY_HARDWARE_ID_TB_TRANSACTION, transactionEntity.getIdHardware());
        values.put(MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION, transactionEntity.getIdBomba());
        values.put(MyDatabase.KEY_HOSE_NAME_TB_TRANSACTION, transactionEntity.getNombreManguera());
        values.put(MyDatabase.KEY_VEHICLE_ID_TB_TRANSACTION, transactionEntity.getIdVehiculo());
        values.put(MyDatabase.KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION, transactionEntity.getPlaca());
        values.put(MyDatabase.KEY_VEHICLE_HOROMETER_TB_TRANSACTION, transactionEntity.getHorometro());
        values.put(MyDatabase.KEY_VEHICLE_KILOMETER_TB_TRANSACTION, transactionEntity.getKilometraje());
        values.put(MyDatabase.KEY_START_DATE_TB_TRANSACTION, transactionEntity.getFechaInicio());
        values.put(MyDatabase.KEY_START_HOUR_TB_TRANSACTION, transactionEntity.getHoraInicio());
        values.put(MyDatabase.KEY_END_DATE_TB_TRANSACTION, transactionEntity.getFechaFin());
        values.put(MyDatabase.KEY_END_HOUR_TB_TRANSACTION, transactionEntity.getHoraFin());
        values.put(MyDatabase.KEY_CONDUCTOR_KEY_TB_TRANSACTION, transactionEntity.getIdConductor());
        values.put(MyDatabase.KEY_FUEL_QUANTITY_TB_TRANSACTION, transactionEntity.getVolumen());
        values.put(MyDatabase.KEY_FUEL_TEMPERATURE_TB_TRANSACTION, transactionEntity.getTemperatura());
        values.put(MyDatabase.KEY_COMMENT_TB_TRANSACTION, transactionEntity.getComentario());
        values.put(MyDatabase.KEY_PRODUCT_NAME_TB_TRANSACTION, transactionEntity.getNombreProducto());
        values.put(MyDatabase.KEY_OPERATOR_KEY_TB_TRANSACTION, transactionEntity.getIdOperador());
        values.put(MyDatabase.KEY_START_CONTOMETER_TB_TRANSACTION, transactionEntity.getContometroInicial());
        values.put(MyDatabase.KEY_END_CONTOMETER_TB_TRANSACTION, transactionEntity.getContometroFinal());
        values.put(MyDatabase.KEY_IMAGE_URI_TB_TRANSACTION, transactionEntity.getImageUri());
        values.put(MyDatabase.KEY_REGISTRATION_USER_TB_TRANSACTION, transactionEntity.getIdUsuarioRegistro());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION, transactionEntity.getEstadoRegistro());
        values.put(MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION, transactionEntity.getEstadoMigracion());
        int row = (int) db.insert(MyDatabase.TB_TRANSACTION, null, values);
        db.close();
        return row;
    }

    public void incrementTicketStation(int numManguera){
        int numTicket=getNumTicketStation();
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        String sqlStation ="UPDATE "+MyDatabase.TB_HARDWARE+" SET "+MyDatabase.KEY_LAST_TICKET_STATION_TB_HARDWARE +" = "+ numTicket;
        db.execSQL(sqlStation);

        String sqlHose ="UPDATE "+MyDatabase.TB_HOSE+" SET "+MyDatabase.KEY_LAST_TICKET_TB_HOSE +" = "+ numTicket;

        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_LAST_TICKET_TB_HOSE, numTicket);
        values.put(MyDatabase.KEY_LAST_QUANTITY_FUEL_TB_HOSE, 0.0);

        int row =db.update(MyDatabase.TB_HOSE,
                values,
                MyDatabase.KEY_NUMBER_HOSE_TB_HOSE+"=?",
                new String[]{String.valueOf(numManguera)});

        db.close();
    }

    public int updateTransaction(TransactionEntity transactionEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = null;
        values = new ContentValues();
        values.put(MyDatabase.KEY_FUEL_QUANTITY_TB_TRANSACTION, transactionEntity.getVolumen());
        values.put(MyDatabase.KEY_FUEL_TEMPERATURE_TB_TRANSACTION, transactionEntity.getTemperatura());
        values.put(MyDatabase.KEY_END_DATE_TB_TRANSACTION, transactionEntity.getFechaFin());
        values.put(MyDatabase.KEY_END_HOUR_TB_TRANSACTION, transactionEntity.getHoraFin());
        //values.put(MyDatabase.KEY_START_CONTOMETER_TB_TRANSACTION, transactionEntity.getContometroInicial());
        values.put(MyDatabase.KEY_END_CONTOMETER_TB_TRANSACTION, transactionEntity.getContometroFinal());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION, transactionEntity.getEstadoRegistro());

        int row =db.update(MyDatabase.TB_TRANSACTION,
                values,
                MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION+"=? ",
                new String[]{String.valueOf(transactionEntity.numeroTransaccion)});

        values = new ContentValues();
        values.put(MyDatabase.KEY_LAST_QUANTITY_FUEL_TB_HOSE, transactionEntity.getVolumen());
        values.put(MyDatabase.KEY_TOTALIZATOR_TB_HOSE, transactionEntity.getTotalizatorHose());

        db.update(MyDatabase.TB_HOSE,
                values,
                MyDatabase.KEY_NUMBER_HOSE_TB_HOSE+"=?",
                new String[]{String.valueOf(transactionEntity.getIdBomba()) });

        db.close();

        return row;

    }

    public List<TransactionEntity> getTransactionByTransactionAndHose(String nroTransaccion, String estado)
    {
        List<TransactionEntity> lst =new ArrayList<TransactionEntity>();
        //String fecha = "'07/05/2020'";
        //String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        // String todayX = "'"+fecha + "'";
        String sql = "";
        sql = "SELECT " +
                " RA." + MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_QUANTITY_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_TEMPERATURE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_PRODUCT_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_COMMENT_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_IMAGE_URI_TB_TRANSACTION +" , " +

                " RA." +MyDatabase.KEY_START_CONTOMETER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_CONTOMETER_TB_TRANSACTION +" , " +

                " RA." +MyDatabase.KEY_REGISTRATION_USER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION  +
                " FROM " + MyDatabase.TB_TRANSACTION + " RA " +
                " WHERE RA." + MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION + " = " + nroTransaccion+
                " AND RA."+ MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +" = '"+estado+"' ";

        //" AND RA." + MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION + " = " + nroBomba;


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        //Log.v("CRUDOPERATIONS",sql2);
        if(cursor.moveToFirst())
        {
            do
            {
                TransactionEntity transactionEntity =new TransactionEntity();
                int idSqlite = cursor.getInt(0);
                int idTransaction = cursor.getInt(1);
                int ticketNumber = cursor.getInt(2);
                int hoseNumber = cursor.getInt(3);
                String hoseName = ""+cursor.getString(4);
                String startDate = ""+cursor.getString(5);
                String startHour = ""+cursor.getString(6);
                String endDate = ""+cursor.getString(7);
                String endHour = ""+cursor.getString(8);
                String vehicleId = ""+cursor.getString(9);
                String vehicleCodePlate = ""+cursor.getString(10);
                String fuelQuantity = ""+cursor.getString(11);
                String fuelTemperature = ""+cursor.getString(12);
                String productName = ""+cursor.getString(13);
                String comment = ""+cursor.getString(14);
                String imageUri = ""+cursor.getString(15);
                String startContometer = ""+cursor.getString(16);
                String endContometer = ""+cursor.getString(17);
                String idRegistrationUser = ""+cursor.getString(18);
                String registrationStatus = ""+cursor.getString(19);
                String migrationStatus = ""+cursor.getString(20);

                transactionEntity.setIdSqlite(idSqlite);
                transactionEntity.setIdTransaction(idTransaction);
                transactionEntity.setNumeroTransaccion(""+ticketNumber);
                transactionEntity.setIdBomba(hoseNumber);
                transactionEntity.setNombreManguera(hoseName);
                transactionEntity.setFechaInicio(startDate);
                transactionEntity.setHoraInicio(startHour);
                transactionEntity.setFechaFin(endDate);
                transactionEntity.setHoraFin(endHour);
                transactionEntity.setIdVehiculo(vehicleId);
                transactionEntity.setPlaca(vehicleCodePlate);
                transactionEntity.setVolumen(fuelQuantity);
                transactionEntity.setTemperatura(fuelTemperature);
                transactionEntity.setNombreProducto(productName);
                transactionEntity.setComentario(comment);
                transactionEntity.setImageUri(imageUri);
                transactionEntity.setContometroInicial(startContometer);
                transactionEntity.setContometroFinal(endContometer);
                transactionEntity.setIdUsuarioRegistro(idRegistrationUser);
                transactionEntity.setEstadoRegistro(registrationStatus);
                transactionEntity.setEstadoMigracion(migrationStatus);

                lst.add(transactionEntity);
            }while(cursor.moveToNext());
        }
        return lst;

    }

    public List<TransactionEntity> getTransaction(String nroTransaccion, String estado)
    {
        List<TransactionEntity> lst =new ArrayList<TransactionEntity>();
        //String fecha = "'07/05/2020'";
        //String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        // String todayX = "'"+fecha + "'";
        String sql = "";
        sql = "SELECT " +
                " RA." +MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_QUANTITY_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_TEMPERATURE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_PRODUCT_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_COMMENT_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_IMAGE_URI_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_USER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION  +
                " FROM " + MyDatabase.TB_TRANSACTION + " RA " +
                " WHERE RA." + MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION +" LIKE '%" + nroTransaccion + "%' " +
                " AND RA."+ MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +" = '"+estado+"' "+
                " ORDER BY RA." + MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION + " DESC ";


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        //Log.v("CRUDOPERATIONS",sql2);
        if(cursor.moveToFirst())
        {
            do
            {
                TransactionEntity transactionEntity =new TransactionEntity();
                int idSqlite = cursor.getInt(0);
                int idTransaction = cursor.getInt(1);
                int ticketNumber = cursor.getInt(2);
                int hoseNumber = cursor.getInt(3);
                String hoseName = ""+cursor.getString(4);
                String startDate = ""+cursor.getString(5);
                String startHour = ""+cursor.getString(6);
                String endDate = ""+cursor.getString(7);
                String endHour = ""+cursor.getString(8);
                String vehicleId = ""+cursor.getString(9);
                String vehicleCodePlate = ""+cursor.getString(10);
                String fuelQuantity = ""+cursor.getString(11);
                String fuelTemperature = ""+cursor.getString(12);
                String productName = ""+cursor.getString(13);
                String comment = ""+cursor.getString(14);
                String imageUri = ""+cursor.getString(15);
                String idRegistrationUser = ""+cursor.getString(16);
                String registrationStatus = ""+cursor.getString(17);
                String migrationStatus = ""+cursor.getString(18);

                transactionEntity.setIdSqlite(idSqlite);
                transactionEntity.setIdTransaction(idTransaction);
                transactionEntity.setNumeroTransaccion(""+ticketNumber);
                transactionEntity.setIdBomba(hoseNumber);
                transactionEntity.setNombreManguera(hoseName);
                transactionEntity.setFechaInicio(startDate);
                transactionEntity.setHoraInicio(startHour);
                transactionEntity.setFechaFin(endDate);
                transactionEntity.setHoraFin(endHour);
                transactionEntity.setIdVehiculo(vehicleId);
                transactionEntity.setPlaca(vehicleCodePlate);
                transactionEntity.setVolumen(fuelQuantity);
                transactionEntity.setTemperatura(fuelTemperature);
                transactionEntity.setNombreProducto(productName);
                transactionEntity.setComentario(comment);
                transactionEntity.setImageUri(imageUri);
                transactionEntity.setIdUsuarioRegistro(idRegistrationUser);
                transactionEntity.setEstadoRegistro(registrationStatus);
                transactionEntity.setEstadoMigracion(migrationStatus);

                lst.add(transactionEntity);
            }while(cursor.moveToNext());
        }
        return lst;

    }

    public List<TransactionEntity> getTransactionforDuplicateTicket(String nroTransaccion, String fechaTransaccion,int estadoMigracionTransaccion, String estado)
    {

        String sqlMigration = "";

        switch (estadoMigracionTransaccion){
            case 0: sqlMigration = " ";
                break;
            case 1: sqlMigration = " AND RA." +MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION + " = '"+"M"+"' ";
                break;
            case 2: sqlMigration = " AND RA." +MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION + " IS NULL ";
                break;
            default:
                sqlMigration = "";
                break;
        }

        List<TransactionEntity> lst =new ArrayList<TransactionEntity>();
        //String fecha = "'07/05/2020'";
        //String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        // String todayX = "'"+fecha + "'";
        String sql = "";
        sql = "SELECT " +
                " RA." +MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_QUANTITY_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_TEMPERATURE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_PRODUCT_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_COMMENT_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_IMAGE_URI_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_USER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION  +
                " FROM " + MyDatabase.TB_TRANSACTION + " RA " +
                " WHERE RA." + MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION +" LIKE '%" + nroTransaccion + "%' " +
                " AND RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION + " = '"+fechaTransaccion+"'"+
                " AND RA."+ MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +" = '"+estado+"' "+
                sqlMigration +
                " ORDER BY RA." + MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION + " DESC ";


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        //Log.v("CRUDOPERATIONS",sql2);
        if(cursor.moveToFirst())
        {
            do
            {
                TransactionEntity transactionEntity =new TransactionEntity();
                int idSqlite = cursor.getInt(0);
                int idTransaction = cursor.getInt(1);
                int ticketNumber = cursor.getInt(2);
                int hoseNumber = cursor.getInt(3);
                String hoseName = ""+cursor.getString(4);
                String startDate = ""+cursor.getString(5);
                String startHour = ""+cursor.getString(6);
                String endDate = ""+cursor.getString(7);
                String endHour = ""+cursor.getString(8);
                String vehicleId = ""+cursor.getString(9);
                String vehicleCodePlate = ""+cursor.getString(10);
                String fuelQuantity = ""+cursor.getString(11);
                String fuelTemperature = ""+cursor.getString(12);
                String productName = ""+cursor.getString(13);
                String comment = ""+cursor.getString(14);
                String imageUri = ""+cursor.getString(15);
                String idRegistrationUser = ""+cursor.getString(16);
                String registrationStatus = ""+cursor.getString(17);
                String migrationStatus = ""+cursor.getString(18);

                transactionEntity.setIdSqlite(idSqlite);
                transactionEntity.setIdTransaction(idTransaction);
                transactionEntity.setNumeroTransaccion(""+ticketNumber);
                transactionEntity.setIdBomba(hoseNumber);
                transactionEntity.setNombreManguera(hoseName);
                transactionEntity.setFechaInicio(startDate);
                transactionEntity.setHoraInicio(startHour);
                transactionEntity.setFechaFin(endDate);
                transactionEntity.setHoraFin(endHour);
                transactionEntity.setIdVehiculo(vehicleId);
                transactionEntity.setPlaca(vehicleCodePlate);
                transactionEntity.setVolumen(fuelQuantity);
                transactionEntity.setTemperatura(fuelTemperature);
                transactionEntity.setNombreProducto(productName);
                transactionEntity.setComentario(comment);
                transactionEntity.setImageUri(imageUri);
                transactionEntity.setIdUsuarioRegistro(idRegistrationUser);
                transactionEntity.setEstadoRegistro(registrationStatus);
                transactionEntity.setEstadoMigracion(migrationStatus);

                lst.add(transactionEntity);
            }while(cursor.moveToNext());
        }
        return lst;

    }

    public List<TransactionEntity> getPendingMigrationTransaction()
    {
        List<TransactionEntity> lst =new ArrayList<TransactionEntity>();

        String sql = "";
        sql = "SELECT " +
                " RA." +MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HARDWARE_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_CONDUCTOR_KEY_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_OPERATOR_KEY_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_HOROMETER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_KILOMETER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_QUANTITY_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_TEMPERATURE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_IMAGE_URI_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_USER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +
                " FROM " + MyDatabase.TB_TRANSACTION + " RA " +
                " WHERE RA."+ MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION + " IS NULL "+
                " AND RA." + MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION + " = 'P' ";


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        //Log.v("CRUDOPERATIONS",sql2);
        if(cursor.moveToFirst())
        {
            do
            {
                TransactionEntity transactionEntity =new TransactionEntity();
                int idSqlite = cursor.getInt(0);
                int ticketNumber = cursor.getInt(1);
                int idHardware = cursor.getInt(2);
                int hoseNumber = cursor.getInt(3);

                String conductorKey =""+cursor.getString(4);
                String operatorKey =""+cursor.getString(5);
                String vehicleCodePlate = ""+cursor.getString(6);
                String vehicleHorometer = ""+cursor.getString(7);
                String vehicleKilometer = ""+cursor.getString(8);
                String fuelQuantity = ""+cursor.getString(9);
                String fuelTemperature = ""+cursor.getString(10);
                String imageUri = ""+cursor.getString(11);
                String startDate = ""+cursor.getString(12);
                String startHour = ""+cursor.getString(13);
                String endDate = ""+cursor.getString(14);
                String endHour = ""+cursor.getString(15);
                String idRegistrationUser = ""+cursor.getString(16);
                String registrationStatus = ""+cursor.getString(17);

                transactionEntity.setIdSqlite(idSqlite);
                transactionEntity.setNumeroTransaccion(""+ticketNumber);
                transactionEntity.setIdHardware(idHardware);
                transactionEntity.setIdBomba(hoseNumber);
                transactionEntity.setIdConductor(conductorKey);
                transactionEntity.setIdOperador(operatorKey);
                transactionEntity.setPlaca(vehicleCodePlate);
                transactionEntity.setHorometro(vehicleHorometer);
                transactionEntity.setKilometraje(vehicleKilometer);
                transactionEntity.setFechaInicio(startDate);
                transactionEntity.setHoraInicio(startHour);
                transactionEntity.setFechaFin(endDate);
                transactionEntity.setHoraFin(endHour);
                transactionEntity.setVolumen(fuelQuantity);
                transactionEntity.setTemperatura(fuelTemperature);
                transactionEntity.setImageUri(imageUri);
                transactionEntity.setIdUsuarioRegistro(idRegistrationUser);
                transactionEntity.setEstadoRegistro(registrationStatus);

                //Log.v("Imagen", Utils.getStringImagen(context, Uri.parse(imageUri)));

                lst.add(transactionEntity);

            }while(cursor.moveToNext());
        }


        return lst;
    }

    public List<TransactionEntity> getTransactionByHose(String dateTimeStart, String dateTimeEnd, int numberHose)
    {
        List<TransactionEntity> lst =new ArrayList<TransactionEntity>();
        String sql = "";
        sql = "SELECT " +
                " RA." +MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_QUANTITY_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_TEMPERATURE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_PRODUCT_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_COMMENT_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_USER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION  +
                " FROM " + MyDatabase.TB_TRANSACTION + " RA" +
                " WHERE RA." + MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION + " = " + numberHose +
                " AND RA."+ MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION + " =  'P' " +
                " AND substr(RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION +",7,4) || '-' || substr(RA."+MyDatabase.KEY_START_DATE_TB_TRANSACTION +",4,2) || '-' || substr(RA."+MyDatabase.KEY_START_DATE_TB_TRANSACTION +",1,2)"+
                " || ' ' || RA." +MyDatabase.KEY_START_HOUR_TB_TRANSACTION +" BETWEEN '"+dateTimeStart +"' AND '"+dateTimeEnd+"' "+
                //" AND RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION +" || ' ' || RA." +MyDatabase.KEY_START_HOUR_TB_TRANSACTION +" >= '"+dateTimeStart+"' " +
                " ORDER BY RA." + MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION + " DESC";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor=null;

        cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst())
        {
            do
            {
                TransactionEntity transactionEntity =new TransactionEntity();
                int idSqlite = cursor.getInt(0);
                int idTransaction = cursor.getInt(1);
                int ticketNumber = cursor.getInt(2);
                int hoseNumber = cursor.getInt(3);
                String hoseName = ""+cursor.getString(4);
                String startDate = ""+cursor.getString(5);
                String startHour = ""+cursor.getString(6);
                String endDate = ""+cursor.getString(7);
                String endHour = ""+cursor.getString(8);
                String vehicleId = ""+cursor.getString(9);
                String vehicleCodePlate = ""+cursor.getString(10);
                String fuelQuantity = ""+cursor.getString(11);
                String fuelTemperature = ""+cursor.getString(12);
                String productName = ""+cursor.getString(13);
                String comment = ""+cursor.getString(14);
                String idRegistrationUser = ""+cursor.getString(15);
                String registrationStatus = ""+cursor.getString(16);
                String migrationStatus = ""+cursor.getString(17);

                transactionEntity.setIdSqlite(idSqlite);
                transactionEntity.setIdTransaction(idTransaction);
                transactionEntity.setNumeroTransaccion(""+ticketNumber);
                transactionEntity.setIdBomba(hoseNumber);
                transactionEntity.setFechaInicio(startDate);
                transactionEntity.setHoraInicio(startHour);
                transactionEntity.setFechaFin(endDate);
                transactionEntity.setHoraFin(endHour);
                transactionEntity.setIdVehiculo(vehicleId);
                transactionEntity.setPlaca(vehicleCodePlate);
                transactionEntity.setVolumen(fuelQuantity);
                transactionEntity.setTemperatura(fuelTemperature);
                transactionEntity.setComentario(comment);
                transactionEntity.setNombreProducto(productName);
                transactionEntity.setIdUsuarioRegistro(idRegistrationUser);
                transactionEntity.setEstadoRegistro(registrationStatus);
                transactionEntity.setEstadoMigracion(migrationStatus);

                lst.add(transactionEntity);
            }while(cursor.moveToNext());
        }
        return lst;

    }

    public List<TransactionEntity> getTransactionByBomba(int nroBomba)
    {
        List<TransactionEntity> lst =new ArrayList<TransactionEntity>();
        //String fecha = "'07/05/2020'";
        //String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        // String todayX = "'"+fecha + "'";
        String sql = "";
        sql = "SELECT " +
                " RA." +MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_START_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_DATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_END_HOUR_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_ID_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_QUANTITY_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_FUEL_TEMPERATURE_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_PRODUCT_NAME_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_COMMENT_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_USER_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION +" , " +
                " RA." +MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION  +

                " FROM " + MyDatabase.TB_TRANSACTION +
                " RA WHERE RA." + MyDatabase.KEY_HOSE_NUMBER_TB_TRANSACTION + " = " + nroBomba +
                " ORDER BY RA." + MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION + " DESC LIMIT 1";


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        //Log.v("CRUDOPERATIONS",sql2);
        if(cursor.moveToFirst())
        {
            do
            {
                TransactionEntity transactionEntity =new TransactionEntity();
                int idSqlite = cursor.getInt(0);
                int idTransaction = cursor.getInt(1);
                int ticketNumber = cursor.getInt(2);
                int hoseNumber = cursor.getInt(3);
                String startDate = ""+cursor.getString(4);
                String startHour = ""+cursor.getString(5);
                String endDate = ""+cursor.getString(6);
                String endHour = ""+cursor.getString(7);
                String vehicleId = ""+cursor.getString(8);
                String vehicleCodePlate = ""+cursor.getString(9);
                String fuelQuantity = ""+cursor.getString(10);
                String fuelTemperature = ""+cursor.getString(11);
                String productName = ""+cursor.getString(12);
                String comment = ""+cursor.getString(13);
                String idRegistrationUser = ""+cursor.getString(14);
                String registrationStatus = ""+cursor.getString(15);
                String migrationStatus = ""+cursor.getString(16);

                transactionEntity.setIdSqlite(idSqlite);
                transactionEntity.setIdTransaction(idTransaction);
                transactionEntity.setNumeroTransaccion(""+ticketNumber);
                transactionEntity.setIdBomba(hoseNumber);
                transactionEntity.setFechaInicio(startDate);
                transactionEntity.setHoraInicio(startHour);
                transactionEntity.setFechaFin(endDate);
                transactionEntity.setHoraFin(endHour);
                transactionEntity.setIdVehiculo(vehicleId);
                transactionEntity.setPlaca(vehicleCodePlate);
                transactionEntity.setVolumen(fuelQuantity);
                transactionEntity.setTemperatura(fuelTemperature);
                transactionEntity.setComentario(comment);
                transactionEntity.setNombreProducto(productName);
                transactionEntity.setIdUsuarioRegistro(idRegistrationUser);
                transactionEntity.setEstadoRegistro(registrationStatus);
                transactionEntity.setEstadoMigracion(migrationStatus);

                lst.add(transactionEntity);
            }while(cursor.moveToNext());
        }
        return lst;

    }

    public int getLastTicketTransaction(){
        int ticket=0;
        SQLiteDatabase db= helper.getReadableDatabase();
        Cursor c = db.query(MyDatabase.TB_TRANSACTION, new String[]{"MAX("+MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION+")"}, null, null, null, null, null);
        if(c.getCount()>0){
            c.moveToFirst();
            String max_ticket=c.getString(0);
            ticket = Integer.parseInt(max_ticket);
        }
        c.close();
        return ticket;
    }

    public int getFirstTicketTransactionDaysAgo(){
        int ticket=0;

        //Obtener fecha de incio a considerar
        int dias=0;

        SimpleDateFormat formatDate= new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;

        String fecha = formatDate.format(new Date());

        try {
            date = formatDate.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, dias*-1);
        date = ca.getTime();

        fecha = formatDate.format(date);

        SQLiteDatabase db= helper.getReadableDatabase();
        Cursor c = db.query(MyDatabase.TB_TRANSACTION, new String[]{"IFNULL(MIN("+MyDatabase.KEY_TICKET_NUMBER_TB_TRANSACTION+"),0)"},
                MyDatabase.KEY_START_DATE_TB_TRANSACTION + " = ? ", new String[]{fecha}, null, null, null);
        if(c.getCount()>0){
            c.moveToFirst();
            String min_ticket=c.getString(0);
            ticket = Integer.parseInt(min_ticket);
        }

        c.close();
        return ticket;
    }

    public int updateStatusMigrationTransactions(String idsSqliteTransactions){

        idsSqliteTransactions = idsSqliteTransactions.replace("[","(");
        idsSqliteTransactions = idsSqliteTransactions.replace("]",")");

        Log.v("ArrayString",idsSqliteTransactions);

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = null;
        values = new ContentValues();
        values.put(MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION, "M");

        int row =db.update(MyDatabase.TB_TRANSACTION,
                values,
                MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION+" IN " + idsSqliteTransactions,
                null);

        return row;
    }

    public int updateStatusMigrationTransaction(int idSqliteTransaction){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = null;
        values = new ContentValues();
        values.put(MyDatabase.KEY_MIGRATION_STATUS_TB_TRANSACTION, "M");

        int row =db.update(MyDatabase.TB_TRANSACTION,
                values,
                MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION+" = " + idSqliteTransaction,
                null);

        return row;
    }


    public int addMigration(MigrationEntity migrationEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_MIGRATION_START_DATE_TB_MIGRATION, migrationEntity.getMigrationStartDate());
        values.put(MyDatabase.KEY_DESCRIPTION_TB_MIGRATION, migrationEntity.getMigrationDescription());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_MIGRATION, migrationEntity.getRegistrationStatus());
        values.put(MyDatabase.KEY_REGISTRATION_USER_TB_MIGRATION, migrationEntity.getRegistrationUser());
        values.put(MyDatabase.KEY_MIGRATION_END_DATE_TB_MIGRATION, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));

        int row = (int) db.insert(MyDatabase.TB_MIGRATION, null, values);
        db.close();
        return row;
    }

    public int updateStatusTransaction(int idSqLite, String status)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION, idSqLite);

        int row =db.update(MyDatabase.TB_TRANSACTION,
                values,
                MyDatabase.KEY_ID_SQLLITE_TB_TRANSACTION+"=?",
                new String[]{String.valueOf(status)});
        db.close();

        return row;
    }

    //TB_PERSON
    public int addPerson(PersonEntity personEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_ID_TB_PERSON, personEntity.getIdPerson());
        values.put(MyDatabase.KEY_PERSON_NAME_TB_PERSON, personEntity.getPersonName());
        values.put(MyDatabase.KEY_FIRST_LAST_NAME_TB_PERSON, personEntity.getFirstLastName());
        values.put(MyDatabase.KEY_SECOND_LAST_NAME_TB_PERSON, personEntity.getSecondLastName());
        values.put(MyDatabase.KEY_PHOTOCHECK_TB_PERSON, personEntity.getPhotocheck());
        values.put(MyDatabase.KEY_DOCUMENT_NUMBER_TB_PERSON, personEntity.getDocumentNumber());
        values.put(MyDatabase.KEY_DOCUMENT_PATHBASE64_TB_PERSON, personEntity.getPathFileBase64());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_PERSON, personEntity.getRegistrationStatus());

        int row = (int) db.insert(MyDatabase.TB_PERSON, null, values);
        db.close();
        return row;
    }

    public int updatePerson(PersonEntity personEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_PERSON_NAME_TB_PERSON, personEntity.getPersonName());
        values.put(MyDatabase.KEY_FIRST_LAST_NAME_TB_PERSON, personEntity.getFirstLastName());
        values.put(MyDatabase.KEY_SECOND_LAST_NAME_TB_PERSON, personEntity.getSecondLastName());
        values.put(MyDatabase.KEY_PHOTOCHECK_TB_PERSON, personEntity.getPhotocheck());
        values.put(MyDatabase.KEY_DOCUMENT_NUMBER_TB_PERSON, personEntity.getDocumentNumber());
        values.put(MyDatabase.KEY_DOCUMENT_PATHBASE64_TB_PERSON, personEntity.getPathFileBase64());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_PERSON, personEntity.getRegistrationStatus());

        int row =db.update(MyDatabase.TB_PERSON,
                values,
                MyDatabase.KEY_ID_TB_PERSON+"=?",
                new String[]{String.valueOf(personEntity.getIdPerson())});
        db.close();

        return row;
    }

    public int deletePerson(PersonEntity personEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        int row= db.delete(MyDatabase.TB_PERSON,
                MyDatabase.KEY_ID_TB_PERSON+"=?",
                new String[]{String.valueOf(personEntity.getIdPerson())});
        db.close();
        return row;
    }

    public PersonEntity getPerson(int id)
    {
        PersonEntity personEntity= new PersonEntity();
        SQLiteDatabase db = helper.getReadableDatabase(); //modo lectura
        Cursor cursor = db.query(MyDatabase.TB_PERSON,
                new String[]{
                        MyDatabase.KEY_ID_SQLLITE_TB_PERSON,
                        MyDatabase.KEY_ID_TB_PERSON,
                        MyDatabase.KEY_PERSON_NAME_TB_PERSON,
                        MyDatabase.KEY_FIRST_LAST_NAME_TB_PERSON,
                        MyDatabase.KEY_SECOND_LAST_NAME_TB_PERSON,
                        MyDatabase.KEY_PHOTOCHECK_TB_PERSON,
                        MyDatabase.KEY_DOCUMENT_NUMBER_TB_PERSON,
                        MyDatabase.KEY_DOCUMENT_PATHBASE64_TB_PERSON,
                        MyDatabase.KEY_REGISTRATION_STATUS_TB_PERSON},
                MyDatabase.KEY_ID_TB_PERSON + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor!=null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            int idSqlLite = Integer.parseInt(cursor.getString(0));
            int IdPerson = Integer.parseInt(cursor.getString(1));
            String PersonName = cursor.getString(2);
            String FirstLastName = cursor.getString(3);
            String SecondLastName = cursor.getString(4);
            String Photocheck = cursor.getString(5);
            String DocumentNumber = cursor.getString(6);
            String PathBase64 = cursor.getString(7);
            String RegistrationStatus = cursor.getString(8);

            personEntity= new PersonEntity(idSqlLite, IdPerson, PersonName,FirstLastName,SecondLastName,Photocheck,DocumentNumber,RegistrationStatus, PathBase64);
        }

        return personEntity;
    }

    public PersonEntity getPersonByPhotocheck(String photocheck)
    {
        PersonEntity personEntity= new PersonEntity();
        SQLiteDatabase db = helper.getReadableDatabase(); //modo lectura
        Cursor cursor = db.query(MyDatabase.TB_PERSON,
                new String[]{
                        MyDatabase.KEY_ID_SQLLITE_TB_PERSON,
                        MyDatabase.KEY_ID_TB_PERSON,
                        MyDatabase.KEY_PERSON_NAME_TB_PERSON,
                        MyDatabase.KEY_FIRST_LAST_NAME_TB_PERSON,
                        MyDatabase.KEY_SECOND_LAST_NAME_TB_PERSON,
                        MyDatabase.KEY_PHOTOCHECK_TB_PERSON,
                        MyDatabase.KEY_DOCUMENT_NUMBER_TB_PERSON,
                        MyDatabase.KEY_DOCUMENT_PATHBASE64_TB_PERSON,
                        MyDatabase.KEY_REGISTRATION_STATUS_TB_PERSON},
                MyDatabase.KEY_PHOTOCHECK_TB_PERSON + "=?",
                new String[]{String.valueOf(photocheck)}, null, null, null);
        if(cursor!=null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            int idSqlLite = Integer.parseInt(cursor.getString(0));
            int IdPerson = Integer.parseInt(cursor.getString(1));
            String PersonName = cursor.getString(2);
            String FirstLastName = cursor.getString(3);
            String SecondLastName = cursor.getString(4);
            String Photocheck = cursor.getString(5);
            String DocumentNumber = cursor.getString(6);
            String PathBase64 = cursor.getString(7);
            String RegistrationStatus = cursor.getString(8);

            personEntity= new PersonEntity(idSqlLite, IdPerson, PersonName,FirstLastName,SecondLastName,Photocheck,DocumentNumber,RegistrationStatus, PathBase64);
        }

        return personEntity;
    }

    public List<PersonEntity> getAllPerson()
    {
        List<PersonEntity> lst =new ArrayList<PersonEntity>();
        String sql= "SELECT  * FROM " + MyDatabase.TB_PERSON;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                PersonEntity personEntity =new PersonEntity();
                personEntity.setIdPersonSqlLite(Integer.parseInt(cursor.getString(0)));
                personEntity.setIdPerson(Integer.parseInt(cursor.getString(1)));
                personEntity.setPersonName(cursor.getString(2));
                personEntity.setFirstLastName(cursor.getString(3));
                personEntity.setSecondLastName(cursor.getString(4));
                personEntity.setPhotocheck(cursor.getString(5));
                personEntity.setDocumentNumber(cursor.getString(6));
                personEntity.setPathFileBase64(cursor.getString(7));
                personEntity.setRegistrationStatus(cursor.getString(8));

                lst.add(personEntity);
            }while(cursor.moveToNext());
        }
        return lst;
    }

    public List<PersonEntity> getAllActivePerson(String busqueda)
    {
        List<PersonEntity> lst =new ArrayList<PersonEntity>();
        String sql= "SELECT  * FROM " + MyDatabase.TB_PERSON + " WHERE " + MyDatabase.KEY_REGISTRATION_STATUS_TB_PERSON + " = 'A'" +
                " AND (" + MyDatabase.KEY_PERSON_NAME_TB_PERSON + " LIKE '%" + busqueda + "%' OR " +
                MyDatabase.KEY_FIRST_LAST_NAME_TB_PERSON + " LIKE '%" + busqueda + "%' OR " +
                MyDatabase.KEY_SECOND_LAST_NAME_TB_PERSON + " LIKE '%" + busqueda + "%' OR " +
                MyDatabase.KEY_PHOTOCHECK_TB_PERSON + " LIKE '%" + busqueda + "%' OR " +
                MyDatabase.KEY_DOCUMENT_NUMBER_TB_PERSON + " LIKE '%" + busqueda + "%' )";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                PersonEntity personEntity =new PersonEntity();
                personEntity.setIdPersonSqlLite(Integer.parseInt(cursor.getString(0)));
                personEntity.setIdPerson(Integer.parseInt(cursor.getString(1)));
                personEntity.setPersonName(cursor.getString(2));
                personEntity.setFirstLastName(cursor.getString(3));
                personEntity.setSecondLastName(cursor.getString(4));
                personEntity.setPhotocheck(cursor.getString(5));
                personEntity.setDocumentNumber(cursor.getString(6));
                personEntity.setPathFileBase64(cursor.getString(7));
                personEntity.setRegistrationStatus(cursor.getString(8));

                lst.add(personEntity);
            }while(cursor.moveToNext());
        }
        return lst;
    }

    public int getPersonCount()
    {
        int totalCount = 0;
        String sql= "SELECT * FROM "+MyDatabase.TB_PERSON;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        totalCount = cursor.getCount();
        cursor.close();

        return totalCount;
    }

    //TB_OPERATOR
    public int addOperator(OperatorEntity operatorEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_ID_TB_OPERATOR, operatorEntity.getIdOperator());
        values.put(MyDatabase.KEY_OPERATOR_KEY_TB_OPERATOR, operatorEntity.getOperatorKey());
        values.put(MyDatabase.KEY_USER_TB_OPERATOR, operatorEntity.getOperatorUser());
        values.put(MyDatabase.KEY_PASSWORD_TB_OPERATOR, operatorEntity.getOperatorPassword());
        values.put(MyDatabase.KEY_PERSON_NAME_TB_OPERATOR, operatorEntity.getPersonName());
        values.put(MyDatabase.KEY_FIRST_LAST_NAME_TB_OPERATOR, operatorEntity.getFirstLastName());
        values.put(MyDatabase.KEY_SECOND_LAST_NAME_TB_OPERATOR, operatorEntity.getSecondLastName());
        values.put(MyDatabase.KEY_PHOTOCHECK_TB_OPERATOR, operatorEntity.getPhotocheck());
        values.put(MyDatabase.KEY_ID_VALIDATION_TB_OPERATOR, operatorEntity.getIdOperatorValidationMap());
        values.put(MyDatabase.KEY_DESCRIPTION_VALIDATION_TB_OPERATOR, operatorEntity.getValidationDescription());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_OPERATOR, operatorEntity.getRegistrationStatus());

        int row = (int) db.insert(MyDatabase.TB_OPERATOR, null, values);
        db.close();
        return row;
    }

    public int updateOperator(OperatorEntity operatorEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_ID_TB_OPERATOR, operatorEntity.getIdOperator());
        values.put(MyDatabase.KEY_OPERATOR_KEY_TB_OPERATOR, operatorEntity.getOperatorKey());
        values.put(MyDatabase.KEY_USER_TB_OPERATOR, operatorEntity.getOperatorUser());
        values.put(MyDatabase.KEY_PASSWORD_TB_OPERATOR, operatorEntity.getOperatorPassword());
        values.put(MyDatabase.KEY_PERSON_NAME_TB_OPERATOR, operatorEntity.getPersonName());
        values.put(MyDatabase.KEY_FIRST_LAST_NAME_TB_OPERATOR, operatorEntity.getFirstLastName());
        values.put(MyDatabase.KEY_SECOND_LAST_NAME_TB_OPERATOR, operatorEntity.getSecondLastName());
        values.put(MyDatabase.KEY_PHOTOCHECK_TB_OPERATOR, operatorEntity.getPhotocheck());
        values.put(MyDatabase.KEY_ID_VALIDATION_TB_OPERATOR, operatorEntity.getIdOperatorValidationMap());
        values.put(MyDatabase.KEY_DESCRIPTION_VALIDATION_TB_OPERATOR, operatorEntity.getValidationDescription());
        values.put(MyDatabase.KEY_REGISTRATION_STATUS_TB_PERSON, operatorEntity.getRegistrationStatus());

        int row =db.update(MyDatabase.TB_OPERATOR,
                values,
                MyDatabase.KEY_ID_TB_OPERATOR+"=?",
                new String[]{String.valueOf(operatorEntity.getIdOperator())});
        db.close();

        return row;
    }

    public int deleteOperator(OperatorEntity operatorEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        int row= db.delete(MyDatabase.TB_OPERATOR,
                MyDatabase.KEY_ID_TB_OPERATOR+"=?",
                new String[]{String.valueOf(operatorEntity.getIdOperator())});
        db.close();
        return row;
    }

    public OperatorEntity getOperator(int id)
    {
        OperatorEntity operatorEntity= new OperatorEntity();
        SQLiteDatabase db = helper.getReadableDatabase(); //modo lectura
        Cursor cursor = db.query(MyDatabase.TB_OPERATOR,
                new String[]{
                        MyDatabase.KEY_ID_SQLLITE_TB_OPERATOR,
                        MyDatabase.KEY_ID_TB_OPERATOR,
                        MyDatabase.KEY_OPERATOR_KEY_TB_OPERATOR,
                        MyDatabase.KEY_USER_TB_OPERATOR,
                        MyDatabase.KEY_PASSWORD_TB_OPERATOR,
                        MyDatabase.KEY_PERSON_NAME_TB_OPERATOR,
                        MyDatabase.KEY_FIRST_LAST_NAME_TB_OPERATOR,
                        MyDatabase.KEY_SECOND_LAST_NAME_TB_OPERATOR,
                        MyDatabase.KEY_PHOTOCHECK_TB_OPERATOR,
                        MyDatabase.KEY_ID_VALIDATION_TB_OPERATOR,
                        MyDatabase.KEY_DESCRIPTION_VALIDATION_TB_OPERATOR,
                        MyDatabase.KEY_REGISTRATION_STATUS_TB_OPERATOR
                },
                MyDatabase.KEY_ID_TB_OPERATOR + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor!=null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            int idSqlLite = Integer.parseInt(cursor.getString(0));
            int IdOperator = Integer.parseInt(cursor.getString(1));
            String OperatorKey = cursor.getString(2);
            String OperatorUser = cursor.getString(3);
            String OperatorPassword = cursor.getString(4);
            String PersonName = cursor.getString(5);
            String FirstLastName = cursor.getString(6);
            String SecondLastName = cursor.getString(7);
            String Photocheck = cursor.getString(8);
            int IdValidation = Integer.parseInt(cursor.getString(9));
            String DescriptionValidation = cursor.getString(10);
            String RegistrationStatus = cursor.getString(11);

            operatorEntity= new OperatorEntity(idSqlLite,
                    IdOperator, OperatorKey, OperatorUser, OperatorPassword, PersonName,FirstLastName,SecondLastName,Photocheck,IdValidation,DescriptionValidation,RegistrationStatus);
        }

        return operatorEntity;
    }

    public List<OperatorEntity> getAllOperator()
    {
        List<OperatorEntity> lst =new ArrayList<OperatorEntity>();
        String sql= "SELECT  * FROM " + MyDatabase.TB_OPERATOR;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                OperatorEntity operatorEntity =new OperatorEntity();
                operatorEntity.setIdSqlLite(Integer.parseInt(cursor.getString(0)));
                operatorEntity.setIdOperator(Integer.parseInt(cursor.getString(1)));
                operatorEntity.setOperatorKey(cursor.getString(2));
                operatorEntity.setOperatorUser(cursor.getString(3));
                operatorEntity.setOperatorPassword(cursor.getString(4));
                operatorEntity.setPersonName(cursor.getString(5));
                operatorEntity.setFirstLastName(cursor.getString(6));
                operatorEntity.setSecondLastName(cursor.getString(7));
                operatorEntity.setPhotocheck(cursor.getString(8));
                operatorEntity.setIdOperatorValidationMap(Integer.parseInt(cursor.getString(9)));
                operatorEntity.setValidationDescription(cursor.getString(10));
                operatorEntity.setRegistrationStatus(cursor.getString(11));

                lst.add(operatorEntity);
            }while(cursor.moveToNext());
        }
        return lst;
    }

    public int getOperatorCount()
    {
        int totalCount = 0;
        String sql= "SELECT * FROM "+MyDatabase.TB_OPERATOR;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        totalCount = cursor.getCount();
        cursor.close();

        return totalCount;
    }

    public OperatorEntity getUserOperatorForLogin(String userOperator, String passwordOperator)
    {
        OperatorEntity operatorEntity= new OperatorEntity();
        SQLiteDatabase db = helper.getReadableDatabase(); //modo lectura
        Cursor cursor = db.query(MyDatabase.TB_OPERATOR,
                new String[]{
                        MyDatabase.KEY_ID_SQLLITE_TB_OPERATOR,
                        MyDatabase.KEY_ID_TB_OPERATOR,
                        MyDatabase.KEY_OPERATOR_KEY_TB_OPERATOR,
                        MyDatabase.KEY_USER_TB_OPERATOR,
                        MyDatabase.KEY_PASSWORD_TB_OPERATOR,
                        MyDatabase.KEY_PERSON_NAME_TB_OPERATOR,
                        MyDatabase.KEY_FIRST_LAST_NAME_TB_OPERATOR,
                        MyDatabase.KEY_SECOND_LAST_NAME_TB_OPERATOR,
                        MyDatabase.KEY_PHOTOCHECK_TB_OPERATOR,
                        MyDatabase.KEY_ID_VALIDATION_TB_OPERATOR,
                        MyDatabase.KEY_DESCRIPTION_VALIDATION_TB_OPERATOR,
                        MyDatabase.KEY_REGISTRATION_STATUS_TB_PERSON},
                MyDatabase.KEY_USER_TB_OPERATOR + "=? AND "+ MyDatabase.KEY_PASSWORD_TB_OPERATOR + "=?",
                new String[]{userOperator,passwordOperator}, null, null, null);
        if(cursor!=null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            int idSqlLite = Integer.parseInt(cursor.getString(0));
            int IdOperator = Integer.parseInt(cursor.getString(1));
            String OperatorKey = cursor.getString(2);
            String OperatorUser = cursor.getString(3);
            String OperatorPassword = cursor.getString(4);
            String PersonName = cursor.getString(5);
            String FirstLastName = cursor.getString(6);
            String SecondLastName = cursor.getString(7);
            String Photocheck = cursor.getString(8);
            int IdValidation = Integer.parseInt(cursor.getString(9));
            String DescriptionValidation = cursor.getString(10);
            String RegistrationStatus = cursor.getString(11);

            operatorEntity= new OperatorEntity(idSqlLite,
                    IdOperator, OperatorKey, OperatorUser, OperatorPassword, PersonName,FirstLastName,SecondLastName,Photocheck,IdValidation,DescriptionValidation,RegistrationStatus);
        }

        return operatorEntity;
    }

    //LIMPIAR MAESTROS A PARTIR DE SEGUNDA SINCRONIZACION
    public void clearTablesMasters() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM "+ MyDatabase.TB_DRIVER);
        db.execSQL("DELETE FROM "+ MyDatabase.TB_OPERATOR);
        db.execSQL("DELETE FROM "+ MyDatabase.TB_PLATE);
        db.execSQL("DELETE FROM "+ MyDatabase.TB_WORKSHIFT);
    }

    //LIMPIAR MAESTROS PRIMERA SINCRONIZACION
    public void clearTablesAllMasters() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM "+ MyDatabase.TB_DRIVER);
        db.execSQL("DELETE FROM "+ MyDatabase.TB_OPERATOR);
        db.execSQL("DELETE FROM "+ MyDatabase.TB_PLATE);
        db.execSQL("DELETE FROM "+ MyDatabase.TB_HOSE);
        db.execSQL("DELETE FROM "+ MyDatabase.TB_HARDWARE);
        db.execSQL("DELETE FROM "+ MyDatabase.TB_WORKSHIFT);
    }

    //INSERTAR MAESTROS
    public void insertMastersSQLite(Maestros maestros){
        //dbHelper.onUpgrade();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values;
        int lastTicket;

        //llenar Drivers
        if(maestros.getDrivers() != null) {
            for (Driver driver : maestros.getDrivers()) {
                values = new ContentValues();
                values.put(MyDatabase.KEY_DRIVER_KEY_TB_DRIVER, driver.getDriverKey());
                values.put(MyDatabase.KEY_DRIVER_NAME_TB_DRIVER, driver.getDriverName());
                db.insert(MyDatabase.TB_DRIVER, null, values);
            }
        }

        //llenar Operators
        if(maestros.getOperatorEntities() != null) {
            for (OperatorEntity operatorEntity : maestros.getOperatorEntities()) {
                values = new ContentValues();
                values.put(MyDatabase.KEY_OPERATOR_KEY_TB_OPERATOR, operatorEntity.getOperatorKey());
                //values.put(MyDatabase.KEY_OPERATOR_NAME_TB_OPERATOR, operatorEntity.getOperatorName());
                db.insert(MyDatabase.TB_OPERATOR, null, values);
            }
        }

        //llenar Plates
        if(maestros.getPlates() != null) {
            for (Plate plate : maestros.getPlates()) {
                values = new ContentValues();
                values.put(MyDatabase.KEY_CODE_TB_PLATE, plate.getVehicleCodePlate());
                values.put(MyDatabase.KEY_COMPANY_TB_PLATE, plate.getCompany());
                values.put(MyDatabase.KEY_STATUS_TB_PLATE, plate.getStatus());
                db.insert(MyDatabase.TB_PLATE, null, values);
            }
        }

        //llenar WorkShifts
        if(maestros.getWorkShifts() != null) {
            for (WorkShift workShift : maestros.getWorkShifts()) {
                values = new ContentValues();
                values.put(MyDatabase.KEY_ID_TB_WORKSHIFT, workShift.getIdWorkShift());
                values.put(MyDatabase.KEY_NAME_SHIFT_TB_WORKSHIFT, workShift.getShiftName());
                values.put(MyDatabase.KEY_NICK_NAME_SHIFT_TB_WORKSHIFT, workShift.getShiftNickName());
                values.put(MyDatabase.KEY_START_TIME_TB_WORKSHIFT, workShift.getStartTime());
                values.put(MyDatabase.KEY_END_TIME_TB_WORKSHIFT, workShift.getEndTime());
                db.insert(MyDatabase.TB_WORKSHIFT, null, values);
            }
        }

        //llenar Hardwares
        lastTicket=0;
        if(maestros.getHardwares() != null) {
            for (Hardware hardware : maestros.getHardwares()) {
                lastTicket = getLastTicketHardwareById(hardware);
                if (lastTicket == -1) {
                    values = new ContentValues();
                    values.put(MyDatabase.KEY_ID_TB_HARDWARE, hardware.getHardwareId());
                    values.put(MyDatabase.KEY_NAME_STATION_TB_HARDWARE, hardware.getStationName());
                    values.put(MyDatabase.KEY_LAST_TICKET_STATION_TB_HARDWARE, hardware.getLastTicket());
                    db.insert(MyDatabase.TB_HARDWARE, null, values);
                } else if (hardware.LastTicket > lastTicket) {
                    //UPDATE
                    values = new ContentValues();
                    values.put(MyDatabase.KEY_LAST_TICKET_STATION_TB_HARDWARE, hardware.getLastTicket());

                    db.update(MyDatabase.TB_HARDWARE,
                            values,
                            MyDatabase.KEY_ID_TB_HARDWARE + "=?",
                            new String[]{String.valueOf(hardware.getHardwareId())});
                }

            }
        }

        //llenar Hoses
        lastTicket=0;
        if(maestros.getHoses() != null) {
            for (Hose hose : maestros.getHoses()) {
                lastTicket = getLastTicketHoseByNumber(hose);
                if (lastTicket == -1) {
                    values = new ContentValues();
                    values.put(MyDatabase.KEY_NUMBER_HOSE_TB_HOSE, hose.getHoseNumber());
                    values.put(MyDatabase.KEY_NAME_HOSE_TB_HOSE, hose.getHoseName());
                    values.put(MyDatabase.KEY_NAME_PRODUCT_TB_HOSE, hose.getNameProduct());
                    values.put(MyDatabase.KEY_LAST_TICKET_TB_HOSE, hose.getLastTicket());
                    values.put(MyDatabase.KEY_LAST_QUANTITY_FUEL_TB_HOSE, hose.getFuelQuantity());
                    values.put(MyDatabase.KEY_ID_HARDWARE_TB_HOSE, hose.getHardwareId());
                    db.insert(MyDatabase.TB_HOSE, null, values);
                } else if (hose.LastTicket > lastTicket) {

                    //UPDATE
                    values = new ContentValues();
                    values.put(MyDatabase.KEY_LAST_TICKET_TB_HOSE, hose.getLastTicket());

                    db.update(MyDatabase.TB_HOSE,
                            values,
                            MyDatabase.KEY_NUMBER_HOSE_TB_HOSE + "=?",
                            new String[]{String.valueOf(hose.getHoseNumber())});
                }
            }
        }


        db.close();
    }

    private int getLastTicketHardwareById(Hardware hardware){
        int retorno=-1;

        String sql = "";
        sql =   " SELECT " + MyDatabase.KEY_LAST_TICKET_STATION_TB_HARDWARE +
                " FROM " + MyDatabase.TB_HARDWARE + " HA  WHERE  HA."+ MyDatabase.KEY_ID_TB_HARDWARE + " = "+hardware.HardwareId;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                retorno =  cursor.getInt(0);
            }while(cursor.moveToNext());
        }

        return retorno;
    }

    private int getLastTicketHoseByNumber(Hose hose){
        int retorno=-1;

        List<Hose> lst =new ArrayList<Hose>();
        String sql = "";
        sql =   " SELECT " + MyDatabase.KEY_LAST_TICKET_TB_HOSE +
                " FROM " + MyDatabase.TB_HOSE + " HO  WHERE  HO."+ MyDatabase.KEY_NUMBER_HOSE_TB_HOSE + " = "+hose.getHoseNumber();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                retorno =  cursor.getInt(0);
            }while(cursor.moveToNext());
        }

        return retorno;
    }

    public List<Driver> getAllDriver()
    {
        List<Driver> lst =new ArrayList<Driver>();
        String sql= "SELECT  * FROM " + MyDatabase.TB_DRIVER;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Driver driverEntity =new Driver();
                driverEntity.setDriverKey(cursor.getString(1));
                driverEntity.setDriverName(cursor.getString(2));
                lst.add(driverEntity);
            }while(cursor.moveToNext());
        }
        return lst;
    }

    public List<Plate> getAllPlate()
    {
        List<Plate> lst =new ArrayList<Plate>();
        String sql= "SELECT  * FROM " + MyDatabase.TB_PLATE;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Plate plateEntity =new Plate();
                plateEntity.setVehicleCodePlate(cursor.getString(1));
                plateEntity.setStatus(cursor.getString(2));
                plateEntity.setCompany(cursor.getString(3));
                lst.add(plateEntity);
            }while(cursor.moveToNext());
        }
        return lst;
    }

    public List<Hose> getAllHose()
    {
        List<Hose> lst =new ArrayList<Hose>();
        String sql= "SELECT  * FROM " + MyDatabase.TB_HOSE;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Hose hoseEntity =new Hose();
                hoseEntity.setHoseNumber(cursor.getInt(1));
                hoseEntity.setHoseName(cursor.getString(2));
                hoseEntity.setNameProduct(cursor.getString(3));
                hoseEntity.setLastTicket(cursor.getInt(4));
                hoseEntity.setFuelQuantity(cursor.getDouble(5));
                hoseEntity.setHardwareId(cursor.getInt(6));
                hoseEntity.setTotalizator(cursor.getString(7));
                lst.add(hoseEntity);
            }while(cursor.moveToNext());
        }
        return lst;
    }

    public List<WorkShift> getAllWorkShift()
    {
        List<WorkShift> lst =new ArrayList<WorkShift>();
        String sql= "SELECT  * FROM " + MyDatabase.TB_WORKSHIFT;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                WorkShift workShiftEntity =new WorkShift();
                workShiftEntity.setIdWorkShift(cursor.getInt(1));
                workShiftEntity.setShiftName(cursor.getString(2));
                workShiftEntity.setShiftNickName(cursor.getString(3));
                workShiftEntity.setStartTime(cursor.getString(4));
                workShiftEntity.setEndTime(cursor.getString(5));
                lst.add(workShiftEntity);
            }while(cursor.moveToNext());
        }
        return lst;
    }

    public List<Plate> getPlate(String codePlate)
    {
        List<Plate> lst =new ArrayList<Plate>();
        //String fecha = "'07/05/2020'";
        //String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        // String todayX = "'"+fecha + "'";
        String sql = "";
        sql = "SELECT " +
                " PL." +MyDatabase.KEY_CODE_TB_PLATE +" , " +
                " PL." +MyDatabase.KEY_COMPANY_TB_PLATE +" , " +
                " PL." +MyDatabase.KEY_STATUS_TB_PLATE +
                " FROM " + MyDatabase.TB_PLATE + " PL WHERE PL." + MyDatabase.KEY_CODE_TB_PLATE +
                " LIKE '%" + codePlate + "%' " ;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Plate plateEntity =new Plate();
                String plateCode = ""+cursor.getString(0);
                String plateCompany = ""+cursor.getString(1);
                String plateStatus = ""+cursor.getString(2);

                plateEntity.setVehicleCodePlate(plateCode);
                plateEntity.setCompany(plateCompany);
                plateEntity.setStatus(plateStatus);

                lst.add(plateEntity);
            }while(cursor.moveToNext());
        }
        return lst;

    }

    public List<TransactionEntity> getDuplicatePlate(String codePlate)
    {
        List<TransactionEntity> lst =new ArrayList<TransactionEntity>();
        //String fecha = "'07/05/2020'";
        //String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        // String todayX = "'"+fecha + "'";
        String sql = "";
        sql = "SELECT " +
                " TR." +MyDatabase.KEY_HOSE_NAME_TB_TRANSACTION +
                " FROM " + MyDatabase.TB_TRANSACTION + " TR "+
                " WHERE TR." + MyDatabase.KEY_VEHICLE_CODE_PLATE_TB_TRANSACTION + " = '" + codePlate + "' " +
                " AND TR." + MyDatabase.KEY_REGISTRATION_STATUS_TB_TRANSACTION + " = 'N'"  ;


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                TransactionEntity transactionEntity =new TransactionEntity();
                String nameHose = ""+cursor.getString(0);
                transactionEntity.setNombreManguera(nameHose);

                lst.add(transactionEntity);
            }while(cursor.moveToNext());
        }
        return lst;

    }

    public int getNumTicketStation(){
        int retorno = 0;

        String sql= "SELECT "+ MyDatabase.KEY_LAST_TICKET_STATION_TB_HARDWARE +" FROM " + MyDatabase.TB_HARDWARE;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                retorno= Integer.parseInt(cursor.getString(0))+1;
            }while(cursor.moveToNext());
        }

        return retorno;
    }

    public List<MigrationEntity> getLastMigrationSuccess(){
        List<MigrationEntity> lst = new ArrayList<MigrationEntity>();;

        String sql = "";
        sql = "SELECT " +
                " MI." + MyDatabase.KEY_MIGRATION_START_DATE_TB_MIGRATION + " , " +
                " MI." + MyDatabase.KEY_MIGRATION_END_DATE_TB_MIGRATION + " , " +
                " MI." + MyDatabase.KEY_REGISTRATION_STATUS_TB_MIGRATION +
                " FROM " + MyDatabase.TB_MIGRATION + " MI "+
                " WHERE MI." + MyDatabase.KEY_REGISTRATION_STATUS_TB_MIGRATION + " = 'P' "+
                " ORDER BY MI." + MyDatabase.KEY_ID_SQLLITE_TB_MIGRATION + " DESC " +
                " LIMIT 1";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                MigrationEntity migrationEntity =new MigrationEntity();
                String startDate = ""+cursor.getString(0);
                String endDate = ""+cursor.getString(1);
                String status = ""+cursor.getString(2);

                migrationEntity.setMigrationStartDate(startDate);
                migrationEntity.setMigrationEndDate(endDate);
                migrationEntity.setRegistrationStatus(status);

                lst.add(migrationEntity);
            }while(cursor.moveToNext());
        }

        return lst;
    }

    public int getHoseCount()
    {
        int totalCount = 0;
        String sql= "SELECT * FROM "+MyDatabase.TB_HOSE;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        totalCount = cursor.getCount();
        cursor.close();

        return totalCount;
    }

    public boolean validateTotalizateHose(){
        boolean retorno=true;

        int totalCount = 0;
        String sql= "SELECT * FROM "+MyDatabase.TB_HOSE +" HO WHERE HO."+MyDatabase.KEY_TOTALIZATOR_TB_HOSE+" IS NULL";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        totalCount = cursor.getCount();
        cursor.close();

        if(totalCount>0)
            retorno=false;

        return retorno;

    }

    public int updateTotalizatorHoses(List<Hose> hoses){
        int numTicket=getNumTicketStation();
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        ContentValues values;
        int row=0;


        for (Hose hose:hoses){
            values = new ContentValues();
            values.put(MyDatabase.KEY_TOTALIZATOR_TB_HOSE,hose.Totalizator);
            row =db.update(MyDatabase.TB_HOSE,
                    values,
                    MyDatabase.KEY_NUMBER_HOSE_TB_HOSE+"=?",
                    new String[]{String.valueOf(hose.HoseNumber)});
        }

        db.close();

        return row;
    }



}

