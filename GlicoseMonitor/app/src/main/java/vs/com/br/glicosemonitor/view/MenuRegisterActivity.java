package vs.com.br.glicosemonitor.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vs.com.br.glicosemonitor.R;
import vs.com.br.glicosemonitor.dao.GlucoseDao;
import vs.com.br.glicosemonitor.model.Glucose;
import vs.com.br.glicosemonitor.view.report.ReportBottonNavigationActivity;
import vs.com.br.glicosemonitor.view.report.ReportScrollingActivity;

public class MenuRegisterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "DEBUG: " + MenuRegisterActivity.class.getName();

    @BindView(R.id.edtValueGlucose)
    EditText edtValueGlucose;

    @BindView(R.id.button)
    Button button;

    int glucoseValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                glucoseValue = Integer.parseInt(edtValueGlucose.getText().toString());
                Log.d(TAG, "Glucose Value: " + glucoseValue);

                //saveDataGlucoseValue();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @OnClick(R.id.button)
    public void submit(View view) {
        // TODO submit data to server...
        String glucose = edtValueGlucose.getText().toString();

        glucoseValue = Integer.parseInt(glucose);
        Log.d(TAG, "glucoseValue: " + glucoseValue);
        saveUserRegister();
    }

    private void saveUserRegister() {

        GlucoseDao dao = new GlucoseDao(this);
        Dao<Glucose, Integer> glucoseDao = null;

        try {
            glucoseDao = dao.getGlucoseDao();
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        Glucose glucose = new Glucose();
        glucose.setmValue(glucoseValue);

        try {
            try {
                glucoseDao.create(glucose);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        verifySaveAndPutScreen();
    }

    private void verifySaveAndPutScreen() {

        /* USER */
        Dao<Glucose, Integer> glucoseDao = null;
        GlucoseDao dao = new GlucoseDao(this);
        Glucose glucose = new Glucose();
        List<Glucose> glucoses;
        glucoses = new ArrayList<Glucose>();

        try {
            glucoseDao = dao.getGlucoseDao();
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        try {
            glucoses = glucoseDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Glucose glucoseL : glucoses) {
            //idDriver = driverL.getId();
            double valueDb = glucoseL.getmValue();
        }

        if (glucoses.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Usuario Não Encontrado. Tente Novamente",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent it = new Intent(getApplicationContext(), ReportActivity.class);
            startActivity(it);

        } else if (id == R.id.nav_manage) {
            Intent it = new Intent(getApplicationContext(), ReportBottonNavigationActivity.class);
            startActivity(it);

        } else if (id == R.id.nav_share) {
            Intent it = new Intent(getApplicationContext(), ReportScrollingActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
