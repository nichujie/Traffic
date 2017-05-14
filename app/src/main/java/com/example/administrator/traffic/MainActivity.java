package com.example.administrator.traffic;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String hint[] = {
            "奇数东西直行\n偶数南北直行",
            "奇数东西左转\n偶数南北左转",
            "奇数南北直行\n偶数东西直行",
            "奇数南北左转\n偶数东西左转"};
    String ending = "Game Over!";
    String playerList = "";
    String playerName[] = {
            "守法司机",
            "不守法司机",
            "严格执法交警",
            "不严格执法交警"
    };
    int round = 1;
    int status = 0;
    int player[] = new  int[4];
    boolean used[] = new boolean[4];
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Button bnt1;
    private Button bnt2;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    Random randomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Init
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        bnt1 = (Button) findViewById(R.id.bnt1);
        bnt2 = (Button) findViewById(R.id.bnt2);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        randomer = new Random();
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        bnt1.setVisibility(View.INVISIBLE);
        txt3.setText("");

        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for(int i = 0; i < 4; i ++) used[i] = false;
                for(int i = 0; i < 4; i ++){
                    int tmp = randomer.nextInt(4);
                    while(used[tmp]){
                        tmp = randomer.nextInt(4);
                    }
                    player[i] = tmp;
                    used[tmp] = true;
                }
                status = randomer.nextInt(4);
                for(int i = 0; i < 4; i ++){
                    playerList = playerList + "玩家";
                    playerList = playerList + String.valueOf(i+1);
                    playerList = playerList + ": ";
                    playerList = playerList + playerName[player[i]];
                    playerList = playerList + "\n";
                }
                int tmp = randomer.nextInt(4) + 1;
                playerList = playerList + "不守法司机目标: ";
                playerList = playerList + String.valueOf(tmp);
                playerList = playerList + "\n";
                tmp = randomer.nextInt(16) + 1;
                playerList = playerList + "入口: ";
                playerList = playerList + String.valueOf(tmp);
                playerList = playerList + "\n";
                int tmp2;
                tmp2 = tmp;
                while(tmp == tmp2){
                    tmp = randomer.nextInt(16) + 1;
                }
                playerList = playerList + "出口: ";
                playerList = playerList + String.valueOf(tmp);
                playerList = playerList + "\n";
                bnt2.setVisibility(View.INVISIBLE);
                bnt1.setVisibility(View.VISIBLE);
                txt3.setText("回合数");
                txt2.setText(String.valueOf(round));
                txt4.setText(playerList);
                txt1.setText(hint[status]);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status++;
                round++;
                txt1.setText(hint[status % 4]);
                if (round >= 25) {
                    txt2.setText("");
                    txt1.setText(ending);
                } else txt2.setText(String.valueOf(round));
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
