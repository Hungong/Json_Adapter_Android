package com.example.hungo.jsonn;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hungo.jsonn.constant.Constant;
import com.example.hungo.jsonn.control.VideoAdapter;
import com.example.hungo.jsonn.model.VideoItem;
import com.example.hungo.jsonn.until.ParseJson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends Activity {
    Button but1;
    Button but2;
    TextView tv1;
    TextView tv2;
    ListView lv;
    Context context;
    ArrayList<VideoItem> lstData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but1 = (Button) findViewById(R.id.button1);
        but2 = (Button) findViewById(R.id.button2);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        lv = (ListView) findViewById(R.id.listView1);
        this.context = this;
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFile(Constant.url, lv).execute();
            }
        });

    }

    public class DownloadFile extends AsyncTask<String, Integer, ArrayList<VideoItem>> {
        ListView listView;
        String url;

        public DownloadFile(String url, ListView lv) {
            this.url = url;
            this.listView = lv;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv1.setText("Start DownloadFile");
        }

        @Override
        protected ArrayList<VideoItem> doInBackground(String... params) {
            lstData = new ArrayList<VideoItem>();
            String stringData = null;
            Log.d("Start", "1.1");
            try {
                stringData = executeHttpGet(Constant.url);
                lstData = ParseJson.processData(stringData);
                Log.d("ListData", lstData.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return lstData;
        }

        @Override
        protected void onPostExecute(ArrayList<VideoItem> videoItems) {
            super.onPostExecute(videoItems);
            Log.d("Start", "2" + videoItems.toString());
            tv2.setText(videoItems.size() + "");
            listView.setAdapter(new VideoAdapter(context, videoItems));
            Log.d("Start", "3");
        }
    }


    public String executeHttpGet(String URL) throws Exception {
        BufferedReader in = null;
        try {
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
            HttpGet request = new HttpGet();
            request.setHeader("Content-Type", "text/plain; charset=utf-8");
            request.setURI(new URI(URL));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            String page = sb.toString();
            //System.out.println(page);
            return page;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.d("BBB", e.toString());
                }
            }
        }
    }

    public ArrayList<VideoItem> getListData() {
        return lstData;
    }

}
