package vs.com.br.glicosemonitor;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import vs.com.br.glicosemonitor.view.MenuRegisterActivity;

public class SplashScreenMainActivity extends AppCompatActivity implements Runnable{
    private static final String Debug = " Debug: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_main);

        Handler action = new Handler();
        int DELAY = 5000;
        action.postDelayed(this, DELAY);

    }

    public void run() {
        Log.d(Debug, "Method - run() ");
        //checkNetworkConnection();
        startApplication();
    }

    private void startApplication() {
        Log.d(Debug, "Method - startLogin() ");
        Intent intent = new Intent(this, MenuRegisterActivity.class);
        intent.putExtra("message", "Splash");
        startActivity(intent);
        finish();
    }

}
