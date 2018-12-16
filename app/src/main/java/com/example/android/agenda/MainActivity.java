package com.example.android.agenda;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText searchbox;
    Button  searchbtn;
    ListView listView;
    ImageView bookimg;
    ProgressBar bar;
    DataAdapter adapter;
    String search_text,Google_BOOks_API;
    ArrayList<DataClass>new_books=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbox=findViewById(R.id.searchtxt);
        searchbtn=findViewById(R.id.searchbtn);
        listView=findViewById(R.id.listview);
        bookimg=findViewById(R.id.bookimg);
        bar=findViewById(R.id.progressbar);
        bar.setVisibility(View.GONE);
        adapter=new DataAdapter(getApplicationContext(),0,new ArrayList<DataClass>());
        listView.setAdapter(adapter);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                bookimg.setVisibility(View.GONE);
                search_text=searchbox.getText().toString();
                if(search_text.length()==0)
                {
                    Toast.makeText(MainActivity.this,"you must eanter search text",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Google_BOOks_API="https://www.googleapis.com/books/v1/volumes?q="+search_text;
                    book newBook=new book();
                    newBook.execute(Google_BOOks_API);
                    InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInput(inputMethodManager.HIDE_IMPLICIT_ONLY,0);
                    searchbox.setText("");
                }


            }
        });
        listView.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DataClass selected_book=(DataClass) listView.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this,BookinfoActivity.class);
                intent.putExtra("bookname",selected_book.getTitle());
                intent.putExtra("bookauthor",selected_book.getAuthor());
                intent.putExtra("bookdescription",selected_book.getDescription());
                intent.putExtra("bookpublishdate",selected_book.getPublish_date());
                intent.putExtra("bookpublisher",selected_book.getPublisher());
                intent.putExtra("bookimgurl",selected_book.getImageurl());
                startActivity(intent);



            }
        });



    }
    public class book extends AsyncTask<String,Void,ArrayList<DataClass>>
    {
        @Override
        protected ArrayList<DataClass> doInBackground(String... strings) {

            if(strings[0]!=null&& strings.length<1)
            {
                return null;
            }

            ArrayList<DataClass>books= null;
            try {
                books = Networkapi.featchBooksData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<DataClass> dataClasses)
        {
            adapter.clear();
            if(dataClasses!=null && !dataClasses.isEmpty())
            {
                bar.setVisibility(View.INVISIBLE);
                adapter.addAll(dataClasses);
            }
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);

        }
    }

}
