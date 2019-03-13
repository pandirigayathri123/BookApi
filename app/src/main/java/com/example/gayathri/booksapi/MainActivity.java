package com.example.gayathri.booksapi;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String bookurl = "https://www.googleapis.com/books/v1/volumes?q=";
    EditText booknames;
    Button ok;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<MyModel> bookArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        booknames = findViewById(R.id.bookname);
        ok = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recycle);
        progressBar = findViewById(R.id.progress);

        bookArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //BookAdapter bookAdapter=new BookAdapter(this,bookModels);
        recyclerView.setAdapter(new MyAdapter(this, bookArrayList));
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BookUrl().execute(bookurl);
            }
        });
    }

    class BookUrl extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override

        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]+ booknames.getText().toString());
                HttpURLConnection urlConnection=(HttpURLConnection)
                        url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
               /* Scanner scanner=new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()){
                    return scanner.next();
                }else {
                    return null;
                }
                */
               if (inputStream !=null)
               {
                   BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                   String line = null;
                   StringBuilder sb = new StringBuilder();

                   while ((line = br.readLine()) !=null)
                   {
                        sb.append(line);
                   }
                   return sb.toString();
               }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            try {
                Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                if (s != null) {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject obj = jsonArray.getJSONObject(a);
                        JSONObject volumeinfo = obj.getJSONObject("volumeInfo");
                        String title = volumeinfo.getString("title");

                        JSONArray authorArray = volumeinfo.getJSONArray("authors");
                        String authors = null;
                        for (int i = 0; i < authorArray.length(); i++) {
                            authors = authors + (authorArray.getString(i) + "\n");
                        }

                        String des = volumeinfo.getString("description");

                        JSONObject imagelink = volumeinfo.getJSONObject("imageLinks");
                        String img = imagelink.getString("thumbnail");

                        bookArrayList.add(new MyModel(title, authors, des, img));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}