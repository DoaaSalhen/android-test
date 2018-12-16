package com.example.android.agenda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.agenda.DataClass;
import com.example.android.agenda.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AL-MOMEN on 12/09/2018.
 */

public class DataAdapter extends ArrayAdapter<DataClass> {
    private int my_resource;
    private ImageView image;
    private TextView title,author;
    public DataAdapter(@NonNull Context context, int resource, @NonNull List<DataClass> objects) {
        super(context, resource, objects);
        this.my_resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=convertView;
        if(view==null)
        {
            view= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        DataClass dataclass=getItem(position);
        title=view.findViewById(R.id.booktitle);
        author=(TextView) view.findViewById(R.id.bookauthor);
        image=view.findViewById(R.id.bookimg);
        title.setText(dataclass.getTitle());
        author.setText(dataclass.getAuthor());
        if(dataclass.getImageurl().length()!=0)
        {
            Picasso.with(getContext())
                    .load(dataclass.getImageurl())
                    .placeholder(R.drawable.library)
                    .resize(80, 80)
                    .into(image);
        }
        else
        {
            image.setImageResource(R.drawable.library);
        }

        return view;
    }
}
