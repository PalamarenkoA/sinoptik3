package ua.sinoptik.sinoptik.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ua.sinoptik.sinoptik.Helper;
import ua.sinoptik.sinoptik.MyService;
import ua.sinoptik.sinoptik.Network;
import ua.sinoptik.sinoptik.R;

public class start extends AppCompatActivity {
   public static boolean isNetwork;
   public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_start);
        Intent intent1 = new Intent(this, MyService.class);
        startService(intent1);
        isNetwork = isNetworkAvailable();
        context = this;
        if(isNetwork){
            new Network().execute();
        }else{
          Helper.showToast("Проверить соединение с интернетом", context);
            startActivity( new Intent(this, Main2Activity.class));
        }




    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    protected void onPause() {
        super.onPause();
       finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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
}
