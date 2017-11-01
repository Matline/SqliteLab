package strathmore.com.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matline on 17/10/2017.
 * this class is like contract class that initialize variables
 * this is a small project so we don't need contract class
 * big project with many tables need a contract class
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //all Static variables that would have been defined in the contract class
    //Database Version
    private static final int DATABASE_VERSION = 2;

    //Database Name
    private static final String DATABASE_NAME = "contractsManager";

    //Database table name
    private static final String TABLE_CONTACTS = "contracts";
    private static final String TABLE_STUDENTS = "students";

    //Contact Table contracts Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    //Contact Table student Column Names
    private static final String KEY_STD_ID = "std_id";
    private static final String KEY_COURSE_NAME = "course_name";
    private static final String KEY_YEAR = "year ";

    //a constractor
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS+ "("
             + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +
              KEY_PH_NO + " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS+ "("
                + KEY_STD_ID + " INTEGER PRIMARY KEY," + KEY_COURSE_NAME + " TEXT," +
                KEY_YEAR + " TEXT" +")";
        db.execSQL(CREATE_STUDENTS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_STUDENTS);
        //Create tables again
        onCreate(db);
    }


    //CRUD OPERATIONS

    //adding new contact
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_PH_NO, contact.get_phone_number());

        //inserting row
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public void addStudents(Students students){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, students.getCourse_name());
        values.put(KEY_YEAR, students.get_year());

        //inserting row
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }

    //Reading Rows
    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {KEY_ID,
                KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[] { String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

                Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2));

        //return contact
        return contact;

    }

    public Students getStudents(int std_id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {KEY_ID,
                        KEY_COURSE_NAME, KEY_YEAR}, KEY_STD_ID + "=?",
                new String[] { String.valueOf(std_id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Students students = new Students(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        //return contact
        return students;

    }

    //Reading Rows2
    //Getting all Contacts
    public  List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phone_number(cursor.getString(2));
                //Adding contact to list
                contactList.add(contact);
            }while (cursor.moveToFirst());
        }

        //return contact
        return  contactList;
    }

    public  List<Students> getAllStudents(){
        List<Students> studentsList = new ArrayList<Students>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do{
                Students students = new Students();
                students.setStd_id(Integer.parseInt(cursor.getString(0)));
                students.setCourse_name(cursor.getString(1));
                students.set_year(cursor.getString(2));
                //Adding contact to list
                studentsList.add(students);
            }while (cursor.moveToFirst());
        }

        //return students
        return  studentsList;
    }

//Reading Row 3
    public  int getContactsCount(){
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return  cursor.getCount();
    }

    public  int getStudentsCount(){
        String countQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return  cursor.getCount();
    }
//Updating Contact Records
    //Updating single contact
    public  int upDateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_PH_NO, contact.get_phone_number());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + "= ?",
        new String[]{String.valueOf(contact.get_name())});
    }

    //Updating  Student Records
    //Updating single contact
    public  int upDateStudents(Students students){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, students.getCourse_name());
        values.put(KEY_YEAR, students.get_year());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_STD_ID + "= ?",
                new String[]{String.valueOf(students.getCourse_name())});
    }

//Deleting Record
    public void  deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.get_id())});
        db.close();
    }
    //Deleting Record
    public void  deleteStudents(Students students){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_STD_ID + " = ?",
                new String[]{String.valueOf(students.getStd_id())});
        db.close();
    }


}

