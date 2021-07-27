package com.example.logintutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logintutorial.Features.FeedActivity;
import com.example.logintutorial.databinding.ActivityMainBinding;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.logintutorial.Constants.*;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText emailTxt, passTxt;
    private String email, password;
    private Object Base64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        emailTxt = findViewById(R.id.editTextTextPersonName);
        passTxt = findViewById(R.id.editTextTextPassword2);

        //emailTxt.setText("test1");
        //passTxt.setText("pass1");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailTxt.getText().toString();
                password = passTxt.getText().toString();
                login(email, password);
            }
        });
    }

    private void checkLoginDetails(String authToken) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

        Call<String> call = api.checkLogin(authToken);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(response.body().matches("success")){

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("TAG", t.toString());
                t.printStackTrace();

            }
        });
    }

    private void login(String username, String password) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);

        LoginRequest lr = new LoginRequest(username, password);

        Call<LoginResponse> call = api.login(lr);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                //TODO: Save the Token in a content provider
                SharedPreferences settings = getApplication().getSharedPreferences(APP_PREFS, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(AUTH_TOKEN,response.body().token);
                editor.apply();


                //TODO: Navigate to the FeedActivity
                Intent in=new Intent(MainActivity.this,FeedActivity.class);
                startActivity(in);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }

        });
    }
}