package com.example.android.agenda;

import android.text.TextUtils;
import android.util.Log;

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
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by AL-MOMEN on 14/09/2018.
 */

public class Networkapi {
    private  static String title,author,publisher,publish_date,description,thumbnail;
    public static ArrayList<DataClass>featchBooksData(String requestUrl)throws IOException
    {
        URL url=createUrl(requestUrl);
        String jasonResponse=null;
        ArrayList<DataClass>books=null;
        try {
                 jasonResponse = makeHttpRequest(url);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try {
            books=extractFeaturesFromJason(jasonResponse);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return books;
    }
    private static URL createUrl(String urlString)
    {
        URL url=null;
        try{
            url=new URL(urlString);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        return url;
    }
    private static String makeHttpRequest(URL url)throws IOException
    {
        String jasonResponse="";
        if(url==null)
        {
            return jasonResponse;
        }
        //declare new httpUrlConnection
        HttpURLConnection urlConnection=null;
        InputStream inputStream =null;
        try {
            //create http request
            urlConnection=(HttpURLConnection) url.openConnection();
            //set request method
            urlConnection.setRequestMethod("GET");
            //connect to server
            urlConnection.connect();
            //if the request was successful is response=200
            //then read the input stream and parse the response
            if(urlConnection.getResponseCode()==200)
            {
                //get inputStream with data to read it
                inputStream=urlConnection.getInputStream();
                jasonResponse=readFromStream(inputStream);
            }
            else
            {
                Log.e("tag","connection failed");
            }
        }
        catch (IOException e)
        {

        }
        finally {
            if(urlConnection!=null)
            {
                urlConnection.disconnect();
            }
            if(inputStream!=null)
            {
                inputStream.close();
            }
        }
        return jasonResponse;
    }

    private static String readFromStream(InputStream inputStream)throws IOException
    {
        StringBuilder data=new StringBuilder();

        InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader reader=new BufferedReader(inputStreamReader);
        String line=reader.readLine();
        while (line!=null)
        {
            data.append(line);
            line=reader.readLine();
        }
        return data.toString();
    }
    private static ArrayList<DataClass> extractFeaturesFromJason(String jasonBooks)throws JSONException
    {
        if(TextUtils.isEmpty(jasonBooks))
        {
            return null;
        }
        ArrayList<DataClass> books=new ArrayList<>();
        try
        {
            JSONObject baseJsonObject=new JSONObject(jasonBooks);
            JSONArray itemsArray=baseJsonObject.getJSONArray("items");
            for(int i=0;i<=itemsArray.length();i++)
            {
                //Json object to get data from each item
                JSONObject item=itemsArray.getJSONObject(i);
                //jason object to get data from volumeInfo
                //int infoNom=Integer.parseInt("volumeInfo");
                JSONObject info=item.getJSONObject("volumeInfo");
                if(info.has("title"))
                {
                    title=info.getString("title");
                }
                else
                {
                    title="title not found";
                }
                if(info.has("authors"))
                {
                    author=info.getString("authors");
                }
                else
                {
                    author="authors not found";
                }
                if(info.has("publisher"))
                {
                    publisher=info.getString("publisher");
                }
                else
                {
                    publisher="publisher not found";
                }
                if(info.has("publishedDate"))
                {
                    publish_date=info.getString("publishedDate");
                }
                else
                {
                    publish_date="publish_date not found";
                }
                if(info.has("description"))
                {
                    description=info.getString("description");
                }
                else
                {
                    description="description not found";
                }
                if(info.has("imageLinks"))
                {
                  JSONObject imageLinks=info.getJSONObject("imageLinks");
                    thumbnail=imageLinks.getString("thumbnail");
                }
                else
                {
                    thumbnail="";
                }
                books.add(new DataClass(title,author,publisher,publish_date,description,thumbnail));
            }
       }
        catch (JSONException e)
        {
          e.printStackTrace();
        }

        return books;
    }
}
