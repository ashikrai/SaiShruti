package com.example.learningandroidstudio.Utils.constants;

import com.example.learningandroidstudio.R;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public final static String saiPratibimba= "https://www.youtube.com/@saipratibimba7363";


    // DataBaseRelated
    public final static String DBName= "SaiShruti_DB";
    public final static int DBVersion= 1;
    public final static String secureEncryptionMessage= "Sairam@123";
    public final static String Success= "Success";
    public final static int dbFetchSize= 40;

    // Table Names
    public final static String TableBhajan= "Bhajan";
    public final static String TableDeity= "Deity";
    public final static String TableVedam= "Vedam";
    public final static String TableBhajanCategory = "BhajanCategory";
    public final static String TableUser = "User";
    public final static String TableFavourite = "Favourite";


//    Favourite Table Columns
    public final static String FavouriteId= "favouriteID";
    public final static String FAVOURITE_USER_ID= "favouriteUserId";
    public final static String FAVOURITE_ITEM_ID= "favouriteItemID";
    public final static String FavouriteItemType= "favouriteItemType";
    public final static String FavouriteItemTitle= "favouriteItemTitle";
    public final static String FavItemBhajan= "favBhajan";
    public final static String FavItemVedam= "favVedam";
    public final static String FavItemSong= "favSong";

    // User Table Columns
    public final static String userId= "userID";
    public final static String userName= "userName";
    public final static String userPassword= "userPassword";
    public final static String userEmail= "userEmail";
    public final static String userMobile= "userMobile";
    public final static String userType= "userType";
    public final static String userbhajanAdded= "bhajanAdded";
    public final static String usersongsAdded= "songsAdded";

    public final static String userNameValue= "admin";
    public final static String userEmailValue= "sssssharsing@gmail.com";
    public final static String userPasswordValue= "sairam@123";
    public final static String userTypeValue= "admin";

    // BhajanCategory Table Columns
    public final static String categoryName= "bhajanCategoryName";
    public final static String categoryId= "bhajanCategoryID";

    // Bhajan Table Columns
    public final static String bhajanId= "bhajanID";
    public final static String bhajanName= "bhajanName";
    public final static String BHAJAN_DEITY= "bhajanDeity";
    public final static String bhajan_Rag= "bhajanRag";
    public final static String bhajanLyrics= "bhajanLyrics";
    public final static String bhajanCategoryID= "bhajanCategoryId";
    public final static String BHAJAN_CONTRIBUTER_ID= "bhajanContributedId";
    public final static String bhajanStatus= "bhajanStatus";
    public final static String bhajanLikeCount= "bhajanLikeCount";

    public final static String bhajanStatusApproved= "BhajanStatusApproved";
    public final static String bhajanStatusPending= "BhajanStatusPending";
    public final static String bhajanStatusRejected= "BhajanStatusRejected";
    public final static String bhajanStatusCorrection= "BhajanStatusCorrection";

    // Deity Table Columns
    public final static String deityId= "DeityID";
    public final static String deityName= "DeityName";
    public final static String deityCategory= "DeityCategory";
    public final static String deityImage= "DeityImage";

    // Vedam Table Columns
    public final static String VedamId= "VedamID";
    public final static String VedamName= "VedamName";
    public final static String VEDAM_DEITY_ID= "VedamDeity";
//    public final static String VedamPDFPath= "Vedam";

    // Preference Related
    public final static String PreferenceName= "SaiShruti_Preferences";
    public final static String LoginPref= "UserLoggedIn";
    public final static String DeityFilterMale= "Male";
    public final static String DeityFilterFemale= "Female";
    public final static String DeityFilterSarwaDharma= "SarwaDharma";
    public final static String userNamePref= "userName";
    public final static String userIdPref= "userID";
    public final static String favouriteListChanged= "favoriteListChanged";


    //Todo: Later store in DB with related params such as religion, gender, keywords, Language, for filtering
    public final static String [] MaleDeities= {"Ganesha","Swami","Allah","Buddha","Guru","Guru Nanak","Hanuman","Jesus","Krishna","Ram","Shiva","Vishnu" };
    public final static String [] FemaleDeities= {"Mata","Radha","Saraswati"};
    public final static String [] Others= {"SarwaDharma"};
    public final static Map<String, Integer> DeityImageData= new HashMap<String, Integer>(){{
        put("Ganesha", R.drawable.ganesha);
        put("Shiva", R.drawable.shiva);
        put("Krishna", R.drawable.krihsna);
        put("Hanuman", R.drawable.hanuman);
        put("Ram", R.drawable.ram);
        put("Guru Nanak", R.drawable.guru_nanak);
        put("Jesus", R.drawable.jesus);
        put("Swami", R.drawable.ganesha);
        put("Buddha", R.drawable.buddha);
        put("Allah", R.drawable.allah);
        put("Vishnu", R.drawable.buddha);
        put("Mata", R.drawable.ganesha);
        put("Radha", R.drawable.krihsna);
        put("Saraswati", R.drawable.ganesha);
        put("SarwaDharma", R.drawable.ganesha);
    }};
}
