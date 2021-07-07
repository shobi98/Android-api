package com.keos.earnwealth.tryit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.keos.earnwealth.MainActivity;
import com.keos.earnwealth.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends AppCompatActivity {

    ListView lstData;
    CustomAdapter adapter;
    ArrayAdapter<String> arrayAdapter ;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lstData = findViewById(R.id.list);
        search=findViewById(R.id.seach);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Main.this.adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        new MyAsyncTask().execute();

        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(Main.this,UserData.class);
                intent.putExtra("userdata",i);
                startActivity(intent);

            }
        });
    }

    class MyAsyncTask extends AsyncTask {

        ProgressDialog dialog;
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Main.this);
            dialog.show();

        }


        @Override
        protected Object doInBackground(Object[] objects) {
            StringBuffer response = new StringBuffer();

            try {
                URL url = new URL(MyUtils.URL_USER);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader ir = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader br = new BufferedReader(ir);
                String inputLine = null;

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                ir.close();

                MyUtils.jsonArray = new JSONArray(response.toString());

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            /* ----------- End Using Internet this method ----------- */

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            adapter = new CustomAdapter(Main.this,  MyUtils.jsonArray);
            lstData.setAdapter(adapter);

            if (dialog.isShowing())dialog.dismiss();
        }

    }
}
