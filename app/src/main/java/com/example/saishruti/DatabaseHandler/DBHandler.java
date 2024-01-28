package com.example.saishruti.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.saishruti.DatabaseHandler.DBModal.BhajanLiteModal;
import com.example.saishruti.DatabaseHandler.DBModal.BhajanModal;
import com.example.saishruti.DatabaseHandler.DBModal.DeityModal;
import com.example.saishruti.DatabaseHandler.DBModal.FavouriteItemModal;
import com.example.saishruti.Utils.MySharedPreferences;
import com.example.saishruti.Utils.Utility;
import com.example.saishruti.Utils.constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBHandler extends SQLiteOpenHelper {
    private SQLiteDatabase dbWriter;
    private SQLiteDatabase dbReader;

    public DBHandler(@Nullable Context context) {
        super(context, Constants.DBName, null, Constants.DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "onCreateDB");
        String TableDeity= String.format("create Table IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s BLOB )",Constants.TableDeity, Constants.deityId, Constants.deityName, Constants.deityCategory, Constants.deityImage);
        String TableBhajan= String.format("create Table IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, FOREIGN KEY (%s) REFERENCES %s (%s), FOREIGN KEY (%s) REFERENCES %s (%s))",Constants.TableBhajan, Constants.bhajanId, Constants.bhajanName, Constants.BHAJAN_DEITY,Constants.bhajan_Rag, Constants.bhajanLyrics,Constants.bhajanCategoryID, Constants.bhajanStatus, Constants.BHAJAN_CONTRIBUTER_ID, Constants.bhajanLikeCount, Constants.BHAJAN_DEITY, Constants.TableDeity, Constants.deityId, Constants.BHAJAN_CONTRIBUTER_ID, Constants.TableUser, Constants.userId);
        String TableVedam= String.format("create Table IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, FOREIGN KEY (%s) REFERENCES %s (%s))",Constants.TableVedam, Constants.VedamId, Constants.VedamName, Constants.VEDAM_DEITY_ID, Constants.VEDAM_DEITY_ID, Constants.TableDeity, Constants.deityId);
        String TableUser= String.format("create Table IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER )", Constants.TableUser, Constants.userId, Constants.userName, Constants.userEmail, Constants.userMobile, Constants.userPassword, Constants.userType, Constants.userbhajanAdded, Constants.usersongsAdded);
//        String TableBhajanCategory= String.format("create Table IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",Constants.TableBhajanCategory, Constants.categoryId, Constants.categoryName);
        String TableFavourite= String.format("create Table IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, FOREIGN KEY (%s) REFERENCES %s (%s))",Constants.TableFavourite,Constants.FavouriteId, Constants.FAVOURITE_USER_ID, Constants.FAVOURITE_ITEM_ID, Constants.FavouriteItemType, Constants.FavouriteItemTitle, Constants.FAVOURITE_USER_ID, Constants.TableUser, Constants.userId);
//

        db.execSQL(TableDeity);
        db.execSQL(TableBhajan);
        db.execSQL(TableVedam);
        db.execSQL(TableUser);
        db.execSQL(TableFavourite);
//        db.execSQL(TableBhajanCategory);

        PopulateDeityTable(db);
        populateAmdinUser(db);
    }


    // ----------------------------------------------------------------  User Related DB Operations ----------------------------------------------------------------
    private void populateAmdinUser(SQLiteDatabase db) {

        String query= String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s ) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", Constants.TableUser, Constants.userName, Constants.userEmail, Constants.userMobile, Constants.userPassword, Constants.userType, Constants.userbhajanAdded, Constants.usersongsAdded, Constants.userNameValue, Constants.userEmailValue, "", Utility.encryptPassword(Constants.userPasswordValue), Constants.userTypeValue, Integer.valueOf(0), Integer.valueOf(0));
        Log.d("Database", "query "+query);
        db.execSQL(query);
    }

    public String validateUserLogin (String username, String password) {
        password= Utility.encryptPassword(password);
        dbReader= getReadableDatabase();
        String query= String.format("Select %s, %s, %s from %s where %s = '%s' or %s = '%s' or %s = '%s' ",Constants.userPassword, Constants.userId, Constants.userPassword, Constants.TableUser, Constants.userName, username,  Constants.userEmail, username, Constants.userMobile, username);
        Cursor userCursor= dbReader.rawQuery(query, null);
        if (userCursor.moveToFirst()) {
            if (password.equals(userCursor.getString(0).toString())) {
                Log.d("Database", "UserName and password is valid");
                MySharedPreferences.setUserDetails(userCursor.getString(2).toString(), Integer.parseInt(userCursor.getString(1)));
                return Constants.Success;
            }
            else {
                Log.d("Database", "UserName is valid but password is wrong");
                return "Wrong Password";
            }
        }
        userCursor.close();
        Log.d("Database", "UserName is invalid");
        return "Wrong Username/Email/Mobile No";
    }


    // ----------------------------------------------------------------  Favourite Related DB Operations ----------------------------------------------------------------
//   public Map<Integer, String[]> getUserFavouriteItem(int userId) {
   public ArrayList<FavouriteItemModal> getUserFavouriteItem(int userId) {
       Log.d("Database", "Getting Favourite marked item for user "+userId);
       dbReader= getReadableDatabase();
       String query= String.format("Select %s, %s, %s from %s where %s = %d", Constants.FavouriteItemType, Constants.FAVOURITE_ITEM_ID, Constants.FavouriteItemTitle, Constants.TableFavourite, Constants.FAVOURITE_USER_ID, userId);
       Cursor favouriteItemCursor= dbReader.rawQuery(query, null);
       ArrayList<FavouriteItemModal> FavouriteData= new ArrayList<>();
       if (favouriteItemCursor.moveToFirst()) {
           do {
               FavouriteData.add(new FavouriteItemModal(Integer.parseInt(favouriteItemCursor.getString(1)), favouriteItemCursor.getString(0), favouriteItemCursor.getString(2)));
               Log.d("Database", "ItemType "+favouriteItemCursor.getString(0)+" itemId "+favouriteItemCursor.getString(1)+" "+favouriteItemCursor.getString(2));
//               FavouriteData.put(Integer.parseInt(favouriteItemCursor.getString(1)), new String[]{favouriteItemCursor.getString(0), favouriteItemCursor.getString(2)});
           } while(favouriteItemCursor.moveToNext());
       } else {
           Log.e("Database", "Error while fetching the Favourite Item list");
       }
       favouriteItemCursor.close();
       dbReader.close();
       return FavouriteData;
   }

    public String markItemFavourite(int UserId, int ItemID, String ItemType, String ItemTitle) {
        Log.d("Database", String.format("Marking Item: %s, Id: %d, Title: %s as Favourite for User :%d",ItemType, ItemID, ItemTitle, UserId));

        dbWriter= getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(Constants.FAVOURITE_USER_ID, UserId);
        values.put(Constants.FAVOURITE_ITEM_ID, ItemID);
        values.put(Constants.FavouriteItemTitle, ItemTitle);
        values.put(Constants.FavouriteItemType, ItemType);

        if (dbWriter.insert(Constants.TableFavourite, null, values) == -1) {
            dbWriter.close();
            Log.e("Database","Internal error while marking the Item as Favourite.");
            return "Internal error while marking the Item as Favourite 😞";
        }
        dbWriter.close();
        return Constants.Success;
    }

    public String ReniveItemFromFavourite(int UserId, int ItemID, String ItemType) {
        Log.d("Database", String.format("Removing Item: %s, Id: %d from Favourite for User :%d",ItemType, ItemID, UserId));
        return Constants.Success;
    }


    // ---------------------------------------------------------------- Deity Related DB Operations ----------------------------------------------------------------
    public void PopulateDeityTable(SQLiteDatabase db){
        Log.d("Database", "Populating the Deity Data Now");
        addDeitytoTable(db, Constants.MaleDeities, Constants.DeityFilterMale);
        addDeitytoTable(db, Constants.FemaleDeities, Constants.DeityFilterFemale);
        addDeitytoTable(db, Constants.Others, Constants.DeityFilterSarwaDharma);
    }

    private void addDeitytoTable(SQLiteDatabase db, String[] DeityData, String Category) {
        Log.d("Database", "AddingDeity to Table Category "+Category);
        String query;
        for (String deityName : DeityData) {
            query= String.format("INSERT INTO %s (%s, %s ) VALUES ('%s', '%s')", Constants.TableDeity, Constants.deityName, Constants.deityCategory, deityName.toString(), Category.toString());
            Log.d("Database", "query "+query);
            db.execSQL(query);
        }

    }

    public Map<String, String[]> getDeityMapFromDB(){
        Log.d("Database", "Reading DeityMapData from DB");
        dbReader= getReadableDatabase();
        String query= String.format("Select %s, %s, %s from %s",Constants.deityId, Constants.deityName, Constants.deityCategory, Constants.TableDeity);
        Cursor deityCursor= dbReader.rawQuery(query, null);
        Map<String, String[]> deityMap= new HashMap<>();

        if(deityCursor.moveToFirst()) {
            do {
                deityMap.put(deityCursor.getString(1),new String[]{deityCursor.getString(0), deityCursor.getString(2)});
            }while (deityCursor.moveToNext());
        }
        deityCursor.close();
        dbReader.close();
        return deityMap;
    }

    public Map<Integer, Integer> getDeityWistBhajanCount(){
        dbReader= getReadableDatabase();
//        String query= String.format("Select %s, count(%s) as total_bhajans from %s GROUP BY %s",Constants.BHAJAN_DEITY, Constants.BHAJAN_DEITY, Constants.TableBhajan, Constants.BHAJAN_DEITY);
//        Cursor BhajanCountData= dbReader.rawQuery(query, null);
        Cursor BhajanCountData= dbReader.query(
                Constants.TableBhajan,
                new String[]{Constants.BHAJAN_DEITY, String.format("count(%s)",Constants.BHAJAN_DEITY)},
                null, null, Constants.BHAJAN_DEITY, null, null );
        Map<Integer, Integer> deityWiseBhajanCoint= new HashMap<>();

        if(BhajanCountData.moveToFirst()) {
            do {
                deityWiseBhajanCoint.put(BhajanCountData.getInt(0), BhajanCountData.getInt(1));
            }while (BhajanCountData.moveToNext());
        }
        BhajanCountData.close();
        dbReader.close();

        return deityWiseBhajanCoint;
    }

    public ArrayList<DeityModal> getDeityDataFromDB(){
        Log.d("Database", "Reading DeityData from DB");
        dbReader= getReadableDatabase();
//        String query= String.format("Select %s, %s, %s, %s from %s",Constants.deityId, Constants.deityName, Constants.deityCategory, Constants.deityImage, Constants.TableDeity);
//        Cursor deityCursor= dbReader.rawQuery(query, null);
        Cursor deityCursor= dbReader.query(
                Constants.TableDeity,
                new String[]{Constants.deityId, Constants.deityName, Constants.deityCategory, Constants.deityImage},
                null, null, null, null,  Constants.deityOrder);
        ArrayList<DeityModal> deityData= new ArrayList<>();

        if(deityCursor.moveToFirst()) {
            do {
//                Log.d("Database", "Size of data read is "+deityCursor.getString(0)+" "+deityCursor.getString(1)+" "+deityCursor.getString(2));
                deityData.add(new DeityModal(
                        Integer.parseInt(deityCursor.getString(0)),
                        deityCursor.getString(1),
                        deityCursor.getString(2),
                        deityCursor.getBlob(3)
                ));
            }while (deityCursor.moveToNext());
        }
        deityCursor.close();
        dbReader.close();
        return deityData;
    }

    //Todo delete later
    public void addDeitytoTable(String deityName, String Category){
        SQLiteDatabase dbWriter= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Constants.deityName, deityName);
        values.put(Constants.deityCategory, Category);
//            values.put(Constants.deityImage, );
        Log.d("Database", "Adding Deity "+deityName);
        dbWriter.insert(Constants.TableDeity, null, values);
        dbWriter.close();
    }

    // ---------------------------------------------------------------- Bhajan Related DB Operations ----------------------------------------------------------------
    public ArrayList<BhajanLiteModal> getBhajanByDeityId(int DeityID, int leftIndex, int rightIndex) {
        Log.d("Database", "Reading Bhajan from DB, for deity "+DeityID);
        dbReader= getReadableDatabase();
        String query= String.format("Select %s, %s from %s where %s = %d limit %d, %d", Constants.bhajanId, Constants.bhajanName, Constants.TableBhajan, Constants.BHAJAN_DEITY, DeityID, leftIndex, rightIndex);
        Cursor bhajanLiteCursor= dbReader.rawQuery(query, null);
        ArrayList<BhajanLiteModal> bhajanLiteData= new ArrayList<>();

        if(bhajanLiteCursor.moveToFirst()) {
            do {
                bhajanLiteData.add(new BhajanLiteModal(
                    Integer.parseInt(bhajanLiteCursor.getString(0)),
                    bhajanLiteCursor.getString(1)
                ));
            }while (bhajanLiteCursor.moveToNext());
        }
        bhajanLiteCursor.close();
        dbReader.close();
        return bhajanLiteData;
    }

    public BhajanModal getBhajanData(int BhajanId) {
        Log.d("Database", "Reading Bhajan Complete Data from DB "+BhajanId);
        dbReader= getReadableDatabase();
        String query= String.format("Select %s, %s, %s, %s, %s, %s, %s from %s where %s = %d", Constants.bhajanName, Constants.bhajanLyrics, Constants.bhajan_Rag, Constants.bhajanCategoryID, Constants.bhajanStatus, Constants.BHAJAN_CONTRIBUTER_ID, Constants.bhajanLikeCount, Constants.TableBhajan, Constants.bhajanId, BhajanId);
        Cursor bhajanDataCursor= dbReader.rawQuery(query, null);
        BhajanModal bjm= null;
        if (bhajanDataCursor.moveToFirst()) {
            bjm= new BhajanModal(BhajanId, bhajanDataCursor.getString(0).toString(), bhajanDataCursor.getString(1).toString(), Integer.parseInt( bhajanDataCursor.getString(5).toString()) );
            bjm.setBhajanRaag(bhajanDataCursor.getString(2));
            bjm.setBhajanCategory(bhajanDataCursor.getString(3));
            bjm.setBhajanStatus(bhajanDataCursor.getString(4));
            bjm.setBhajanLikeCount(bhajanDataCursor.getString(6));
        }
        bhajanDataCursor.close();
        dbReader.close();
        return bjm;
    }

    // ---------------------------------------------------------------- Contribution Related DB Operations ----------------------------------------------------------------
    public String contributeBhajan(String title, String lyrics, int userId, int DeityId, String Raag ) {
        Log.d("Database", String.format("Contributing Bhajan %s Deity: %d , Raag %s",title, DeityId, Raag));
        dbWriter= getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(Constants.bhajanName, title);
        values.put(Constants.bhajanLyrics, lyrics);
        values.put(Constants.BHAJAN_CONTRIBUTER_ID, userId );
        values.put(Constants.bhajanCategoryID, "" );
        values.put(Constants.bhajanStatus, Constants.bhajanStatusPending);
        values.put(Constants.bhajanLikeCount, 0 );
        if (DeityId != -1)
            values.put(Constants.BHAJAN_DEITY, DeityId );
        if (Raag != null)
            values.put(Constants.bhajan_Rag, Raag );
//        Log.d("Database", "Adding Deity "+deityName);
        if (dbWriter.insert(Constants.TableBhajan, null, values) == -1) {
            dbWriter.close();
            return "Internal error while adding new Bhajan 😞";
        }
        dbWriter.close();
        IncrementUserContribution(userId,"Bhajan");
        return Constants.Success;
    }

    private boolean IncrementUserContribution(int userId, String category) {
        dbReader= getReadableDatabase();
        String query= String.format("Select %s from %s where %s = %d",Constants.userbhajanAdded, Constants.TableUser, Constants.userId, userId);
        Cursor userCursor= dbReader.rawQuery(query, null);
        if (userCursor.moveToFirst()) {
            int BhajanAdded= userCursor.getInt(0);
            Log.d("Database", "User: "+userId+String.valueOf(userId)+" has added "+BhajanAdded+" Bhajan(s)");
            userCursor.close();

            dbWriter= getWritableDatabase();
            ContentValues values= new ContentValues();
            BhajanAdded++;

            values.put(Constants.userbhajanAdded, BhajanAdded);
    //        Log.d("Database", "Adding Deity "+deityName);
            if (dbWriter.update(Constants.TableUser, values, Constants.userId+"=?",new String[]{String.valueOf(userId)}) < 1)
            {
                dbWriter.close();
                return false;
            }
        }
        userCursor.close();
        dbWriter.close();
        return true;
    }

    // ---------------------------------------------------------------- User related ----------------------------------------------------------------
    public String getBhajanContributerName(int userId) {
        Log.d("Database", "Getting Bhajan Contributer Name for BhajanId: "+userId);
        String query= String.format("Select %s from %s where %s = %d",Constants.userName, Constants.TableUser, Constants.userId, userId);
        String userName= null;
        dbReader= getReadableDatabase();
        Cursor userCursor= dbReader.rawQuery(query, null);
        if (userCursor.moveToFirst())
            userName= userCursor.getString(0);
        userCursor.close();
        dbReader.close();
        return userName;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Database", "Upgrading so dropping and creating a new DB");
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TableDeity);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TableBhajan);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TableVedam);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TableUser);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TableFavourite);
//        db.execSQL("DROP TABLE IF EXISTS " + Constants.TableBhajanCategory);
        onCreate(db);
    }
}
