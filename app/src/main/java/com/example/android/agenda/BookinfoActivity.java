package com.example.android.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class BookinfoActivity extends AppCompatActivity {

    TextView title,author,publisher,publishdate,description;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinfo2);
        title=findViewById(R.id.bookname);
        author=findViewById(R.id.author);
        publisher=findViewById(R.id.publishername);
        publishdate=findViewById(R.id.publish_date);
        description=findViewById(R.id.desc);
        img=findViewById(R.id.imgbook);
        Intent intent=getIntent();
        title.setText(intent.getStringExtra("bookname"));
        author.setText(intent.getStringExtra("bookauthor"));
        publisher.setText(intent.getStringExtra("bookpublisher"));
        publishdate.setText(intent.getStringExtra("bookpublishdate"));
        description.setText(intent.getStringExtra("bookdescription"));
        String url=intent.getStringExtra("bookimgurl");
        //7Toast.makeText(this,url,Toast.LENGTH_LONG).show();
        if(url.length()==0)
        {
            img.setImageResource(R.drawable.notebook);

        }
        else
        {
            Picasso.with(this)
                    .load(url)
                    .placeholder(R.drawable.notebook)
                    .resize(80, 80)
                    .into(img);
        }


    }
}
