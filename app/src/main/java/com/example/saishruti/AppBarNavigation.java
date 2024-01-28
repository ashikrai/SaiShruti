package com.example.saishruti;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.saishruti.Bhajans_Songs.contribute_activity.ContributeActivity;
import com.example.saishruti.Utils.MySharedPreferences;
import com.example.saishruti.Utils.Utility;
import com.example.saishruti.activity_login.MainActivity;
import com.example.saishruti.favourite.FavouriteItems;

public class AppBarNavigation extends AppCompatActivity {
    private ImageView appBarMenu;
//    private ConstraintLayout customAppBar;

    public void NavigateToFavouriteActivity(View v){
        Log.d("AppBarNavigation", "Navigating to Faourite Activity");
        Toast.makeText(this, "Favourite Items 💗", Toast.LENGTH_SHORT).show();
        makeVibration();
        Intent fav= new Intent(this, FavouriteItems.class);
        startActivity(fav);
    }

    protected void makeVibration() {
        Utility.vibrate((Vibrator) getSystemService(Context.VIBRATOR_SERVICE));
    }

    protected void toggleBaseNav( boolean flag) {
        Log.d("scrollChange", "ToggleAppBar Called");
        LinearLayout baseNavLayout= (LinearLayout)findViewById(R.id.baseNav_Layout);
        if (baseNavLayout == null)
            return;
        baseNavLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        TransitionManager.beginDelayedTransition(baseNavLayout, new AutoTransition());

        if (flag) {
            Log.d("scrollChange", "Appbar Visible");
            baseNavLayout.setVisibility(View.VISIBLE);
        } else {
            Log.d("scrollChange", "Appbar Hide");
            baseNavLayout.setVisibility(View.GONE);
        }
    }

    public void showBaseNavigation(View v){
        LinearLayout baseNavLayout= (LinearLayout)findViewById(R.id.baseNav_Layout);
        baseNavLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        baseNavLayout.setVisibility(baseNavLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

        TransitionManager.beginDelayedTransition(baseNavLayout, new AutoTransition());
    }

    public void NavigateToPrevActivity (View v){
        finish();
        Log.d("AppBarNavigation", "SelectProduct: Back Pressed");
    }

    public void Logout(){
        Log.d("AppBarNavigation", "Navigating to Main Activity after Logout");
        Intent logoutIntent= new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logoutIntent);
        finish();
    }

    public void NavigateToContributeActivity(){
        Log.d("AppBarNavigation", "Navigating to Contribute Activity");
        Intent contributeIntent= new Intent(this, ContributeActivity.class);
        makeVibration();
        startActivity(contributeIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void openPopUpMenu(View v){
        appBarMenu= findViewById(R.id.AppBarMenu);
        PopupMenu popupMenu = new PopupMenu(AppBarNavigation.this, appBarMenu);

        // Inflating popup menu from app_bar_menu.xml file
        popupMenu.getMenuInflater().inflate(R.menu.app_bar_menu, popupMenu.getMenu());
//        popupMenu.getMenuInflater().inflate(R.menu.item_context_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String menuItemName= menuItem.getTitle().toString();
                switch (menuItemName){
                    case "Contribute":
                        NavigateToContributeActivity();
                        break;
                    case "Logout":
                        Toast.makeText(AppBarNavigation.this, "Sairam 🙏.", Toast.LENGTH_SHORT).show();
                        MySharedPreferences.clearSharedPreferences();
                        Logout();
                        break;
                    case "Refresh":
                        Toast.makeText(AppBarNavigation.this, "Wait a minute ⏳", Toast.LENGTH_SHORT).show();
                        break;
                    case "Clear cache":
                        Toast.makeText(AppBarNavigation.this, "Clearing your data 🗑", Toast.LENGTH_SHORT).show();
                        MySharedPreferences.clearSharedPreferences();
                        finish();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true);
        }
        // Showing the popup menu
        popupMenu.show();
    }

    public void BaseNav_toContributePage(View v){
        Toast.makeText(this, "Contribute Page", Toast.LENGTH_SHORT).show();
        NavigateToContributeActivity();
    }
}
