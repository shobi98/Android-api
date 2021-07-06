package com.keos.earnwealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Username,Password;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username=findViewById(R.id.editTextTextPersonName);
        Password=findViewById(R.id.editTextTextPassword);
        Login=findViewById(R.id.button2);
        Login.setOnClickListener(v -> {
            final String Name=Username.getText().toString();
            final String Pass=Password.getText().toString();
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if(Name.length()==0)
            {
                Username.requestFocus();
                Username.setError("FIELD CANNOT BE EMPTY");

            }
            else if(!Name.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
            {
                Username.requestFocus();
                Username.setError("ENTER ONLY Email Address");
            }
            else if(Pass.length()<=7)
            {
                Password.requestFocus();
                Password.setError("FIELD CANNOT BE EMPTY");
            }
            else
            {

            }
//             if(Name=="admin@gmail.com"||Pass=="admin@123")
          //  {
                Intent nextactivity=new Intent(MainActivity.this,SearchListView.class);
                startActivity(nextactivity);
                Toast.makeText(MainActivity.this,"Successfully login",Toast.LENGTH_LONG).show();
//            }

        });


    }
}