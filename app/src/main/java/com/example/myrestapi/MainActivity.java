package com.example.myrestapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String s,s1,s2,s3;
    Button b;
    ArrayList<String> ar;
    RecyclerView re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.t1);
        ar=new ArrayList<>();
        re=findViewById(R.id.re1);
        b=findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Asynclas asyn =new Asynclas();
                asyn.execute();
            }
            class Asynclas extends AsyncTask {
                @Override
                protected Object doInBackground(Object[] objects) {
                    Anything any = new Anything();
                    s = any.abc("https://mocki.io/v1/19154358-a8f2-4c39-8333-8e932f833873");
                    try {
                        JSONObject json=new JSONObject(s);
                        JSONObject jwid=json.getJSONObject("widget");
                        JSONObject jwin =jwid.getJSONObject("window");
                        s= jwin.getString("title");
                        s1= jwid.getString("debug");
                        s2=jwin.getString("name");
                        s3=jwin.getString("width");
                        ar.add(s1);
                        ar.add(s2);
                        ar.add(s3);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    tv.setText(s);
                    re.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    Adap ad= new Adap(MainActivity.this,ar);
                    re.setAdapter(ad);



                }
            }


        });
    }
}