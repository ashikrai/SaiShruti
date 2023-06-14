package com.example.learningandroidstudio.activity_login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningandroidstudio.AppBarNavigation;
import com.example.learningandroidstudio.DatabaseHandler.DBHandler;
import com.example.learningandroidstudio.R;
import com.example.learningandroidstudio.SelectProduct;
import com.example.learningandroidstudio.Utils.MySharedPreferences;
import com.example.learningandroidstudio.Utils.constants.Constants;

public class MainActivity extends AppBarNavigation {

    private TextView loginStatus;
    private TextView userName;
    private TextView password;
    private TextView passwordText;
    private SharedPreferences sp;
    private DBHandler dbHandler;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView createNewAcc;
        Button loginBtn;
        ImageView backButton;
        ImageView favButton;

        Toast.makeText(MainActivity.this,"Sairam 🙏", Toast.LENGTH_SHORT ).show();

        MySharedPreferences.sp= getSharedPreferences(Constants.PreferenceName, MODE_PRIVATE);
        MySharedPreferences.createSharedPreference();
        MySharedPreferences.setFavouriteChanged(true);
        if (MySharedPreferences.getLoginStatus()) {
            navigateToSelectItem();
        }
//
//        // Database
//        dbHandler = new DBHandler(MainActivity.this);
//        String pass= dbHandler.encryptPassword("Sairam@123Baps");
//        dbHandler.decryptPassword(pass, "2B22cS3UC5s35WBihLBo8w==");
//        Log.d("Database", "Getting Deity DB");
//        dbHandler.addDeitytoTable("yalamber", "rai");

        loginBtn= findViewById(R.id.Btn_Login);
        loginStatus= findViewById(R.id.LoginStatusMsg);
        userName= findViewById(R.id.userName);
        password= findViewById(R.id.password);
        backButton= findViewById(R.id.AppBarBackMenu);
        createNewAcc= findViewById(R.id.createAccount);
        favButton= findViewById(R.id.AppBarFavourite);
        passwordText= findViewById(R.id.passwordText);


        backButton.setVisibility(View.GONE);
        favButton.setVisibility(View.GONE);

//        Create New Acc Login
        createNewAcc.setOnClickListener(v -> {
            Log.d("LoginPage", "Creating new Account");
            navigateToCreateNewAccount();
        });

//        Login Validation login
        loginBtn.setOnClickListener(v -> {
            Log.d("LoginPage", "Password "+password.getText().toString());
            if (userName.getText().length() != 0  && password.getText().length() != 0 ) {
                DBHandler dbHandler= new DBHandler(MainActivity.this);
                String result= dbHandler.validateUserLogin(userName.getText().toString(), password.getText().toString());
                if (result.equals(Constants.Success)) {
                    loginStatus.setText("Login Successful");
                    Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT ).show();
                    Log.d("LoginPage", "Login SuccessFull");
                    MySharedPreferences.setLoginStatus(true);
                    navigateToSelectItem();
                }else {
                    loginStatus.setText(result);
                    Log.e("LoginPage", result);
                    Toast.makeText(MainActivity.this,"Login UnSuccessful", Toast.LENGTH_SHORT ).show();
                }
            }else {
                loginStatus.setText("Please Fill all Fields");
                Log.e("LoginPage", "Please fill all the fields");
                Toast.makeText(MainActivity.this,"Login UnSuccessful", Toast.LENGTH_SHORT ).show();
            }
        });

        //Todo Delete Later
//        NavigateToContributeActivity();
    }

    public void ChangePasswordVisibility(View v) {
        ImageView passVisibility= findViewById(R.id.passwordVisible);
        if (password.getVisibility() == View.VISIBLE && password.getText().length() > 0) {
            passVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24);
            passwordText.setText(password.getText().toString());
            passwordText.setVisibility(View.VISIBLE);
            password.setVisibility(View.INVISIBLE);
        } else {
            password.setVisibility(View.VISIBLE);
            password.setText(passwordText.getText().toString());
            passwordText.setVisibility(View.INVISIBLE);
            passVisibility.setImageResource(R.drawable.ic_baseline_visibility_24);
        }
    }

    private void navigateToCreateNewAccount(){
        Intent intent= new Intent(this, CreateNewAccount.class);
        startActivity(intent);
    }

    private void navigateToSelectItem(){
        Intent intent= new Intent(this, SelectProduct.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}