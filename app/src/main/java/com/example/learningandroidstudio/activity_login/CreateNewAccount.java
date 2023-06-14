package com.example.learningandroidstudio.activity_login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningandroidstudio.R;

public class CreateNewAccount extends AppCompatActivity {

    private EditText Email;
    private EditText phoneNumber;
    private TextView errorText;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView menuIcon;
        ImageView FavIcon;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        Email= findViewById(R.id.CreateAcc_Email);
        phoneNumber= findViewById(R.id.CreateAcc_PhoneNumber);
        errorText= findViewById(R.id.createAcc_ErrorText);
        menuIcon= findViewById(R.id.AppBarMenu);
        FavIcon= findViewById(R.id.AppBarFavourite);

        menuIcon.setVisibility(View.GONE);
        FavIcon.setVisibility(View.GONE);

        TextView slogan= findViewById(R.id.AppBar_Slogan);
        slogan.setText("Create New Account 👤");
        Toast.makeText(this, "👤 Create New Account", Toast.LENGTH_SHORT).show();
    }

    public void createAccount(View v)
    {
        if (!Email.getText().toString().isEmpty()){
            String emailID = Email.getText().toString().trim();
            Log.d("LoginPage","Using Email: "+emailID);
            errorText.setText("");
            if (emailID.matches(emailPattern))
            {
                Log.d("LoginPage","Email Format correct: "+phoneNumber.getText());
            }else
            {
                errorText.setText(R.string.invalid_username);
                return;
            }
        }
        else if (!phoneNumber.getText().toString().isEmpty())
        {
            Log.d("LoginPage","Using phoneNumber: "+phoneNumber.getText());
            errorText.setText("");
        }
        else
        {
            Log.d("LoginPage","both Values Empty");
            errorText.setText("Fill any one detail");
            return;
        }
        Toast.makeText(CreateNewAccount.this, "Created New Account", Toast.LENGTH_SHORT).show();
    }

    public void NavigateToPrevActivity (View v){
        finish();
        Log.d("AppBar", "CreateNewAccount: Back Pressed");
    }
}