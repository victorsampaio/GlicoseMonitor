package vs.com.br.glicosemonitor.view.report;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vs.com.br.glicosemonitor.R;
import vs.com.br.glicosemonitor.dao.GlucoseDao;
import vs.com.br.glicosemonitor.dao.UnitOfMeasurementDao;
import vs.com.br.glicosemonitor.model.Glucose;
import vs.com.br.glicosemonitor.model.UnitOfMeasurement;

public class ReportBottonNavigationActivity extends AppCompatActivity {

    final Context context = this;

    private TextView mTextMessage;
    String unitOfMeasurementSt, unitOfMeasurementStDb;

    @BindView(R.id.layout_mgdl)
    RelativeLayout layoutMgdl;
    @BindView(R.id.layout_mmoll)
    RelativeLayout layoutMmoll;
    @BindView(R.id.layout_home)
    RelativeLayout layoutHome;
    @BindView(R.id.layout_dashboard)
    RelativeLayout layoutDashboard;
    @BindView(R.id.notifications_screen)
    RelativeLayout layoutNotifications;
    @BindView(R.id.edtWeight)
    EditText edtWeight;
    @BindView(R.id.edtGlucoseInt)
    EditText edtGlucoseInt;
    @BindView(R.id.edtGlucoseDecimal)
    EditText edtGlucoseDecimal;

    int glucoseValueInt;
    double weightValue, glucoseValueDecimal, glucoseValueDecimalDiv, glucoseValue, valueDb;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // mTextMessage.setText(R.string.title_home);
                    layoutDashboard.setVisibility(View.INVISIBLE);
                    layoutNotifications.setVisibility(View.INVISIBLE);
                    layoutHome.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    layoutHome.setVisibility(View.INVISIBLE);
                    layoutNotifications.setVisibility(View.INVISIBLE);
                    layoutDashboard.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    layoutHome.setVisibility(View.INVISIBLE);
                    layoutDashboard.setVisibility(View.INVISIBLE);
                    layoutNotifications.setVisibility(View.VISIBLE);
                    return true;

            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation_top, menu);
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
            Toast.makeText(getApplicationContext(), "OPA", Toast.LENGTH_SHORT).show();
            return true;
        }

        /* Intent itBtTest = new Intent(getApplicationContext(), SampleActivity.class);
            startActivity(itBtTest);*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_botton_navigation);
        ButterKnife.bind(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifyFieldsToSave();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //saveUserRegister();
            }
        });

        verifyUnitOfMeasurement();
    }

    private void verifyUnitOfMeasurement() {

        UnitOfMeasurementDao dao = new UnitOfMeasurementDao(this);
        Dao<UnitOfMeasurement, Integer> unitOfMeasurementIntegerDao = null;

        try {
            unitOfMeasurementIntegerDao = dao.getUnitOfMeasurementIntegerDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<UnitOfMeasurement> unitOfMeasurements = new ArrayList<UnitOfMeasurement>();

        try {
            unitOfMeasurements = unitOfMeasurementIntegerDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (UnitOfMeasurement unitOfMeasurementListDb : unitOfMeasurements) {
            unitOfMeasurementSt = unitOfMeasurementListDb.getUnitName();

            if (unitOfMeasurementSt.equals("mg/dl")){
                layoutMgdl.setVisibility(View.VISIBLE);
            }
            if (unitOfMeasurementSt.equals("mmol/l")){
                layoutMmoll.setVisibility(View.VISIBLE);

            }
        }

        if (unitOfMeasurements.isEmpty()) {
            alertToUnitOfMeasurement();
        }
    }

    private void alertToUnitOfMeasurement() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_alert_unit_measurement);
        dialog.setTitle(R.string.alert_unit_measurement);

        Button btnMgdl, btnMmoll;
        btnMgdl = (Button) dialog.findViewById(R.id.btnMgdl);
        btnMmoll = (Button) dialog.findViewById(R.id.btnMmoll);

        btnMgdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitOfMeasurementStDb = "mg/dl";
                registerUnitMeasurement();
                dialog.dismiss();
            }
        });
        btnMmoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitOfMeasurementStDb = "mmol/l";
                registerUnitMeasurement();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void registerUnitMeasurement() {

        UnitOfMeasurementDao dao = new UnitOfMeasurementDao(this);
        Dao<UnitOfMeasurement, Integer> unitOfMeasurementIntegerDao = null;

        try {
            unitOfMeasurementIntegerDao = dao.getUnitOfMeasurementIntegerDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement();
        try {
            unitOfMeasurement.setUnitName(unitOfMeasurementStDb);
        } catch (android.database.SQLException e ){
            e.printStackTrace();
        }

        try {
            unitOfMeasurementIntegerDao.createOrUpdate(unitOfMeasurement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void verifyFieldsToSave() {

        if (edtWeight.getText().toString().equals("") || edtWeight == null || edtWeight.getText().toString() == null) {
            edtWeight.setError("Verifique o valor");
            return;
        } else {
            weightValue = Double.parseDouble(edtWeight.getText().toString());
        }

        if (edtGlucoseInt.getText().toString().equals("") || edtGlucoseInt == null || edtWeight.getText().toString() == null) {
            edtGlucoseInt.setError("Verifique o valor");
            return;
        } else {
            glucoseValueInt = Integer.parseInt(edtGlucoseInt.getText().toString());
        }

        if (edtGlucoseDecimal.getText().toString().equals("") || edtGlucoseDecimal == null || edtGlucoseDecimal.getText().toString() == null) {
            edtGlucoseDecimal.setError("Verifique o valor");
            return;
        } else {
            glucoseValueDecimal = Double.parseDouble(edtGlucoseDecimal.getText().toString());
        }

        //glucoseValueInt = Integer.parseInt(edtGlucoseInt.getText().toString());
        //glucoseValueDecimal = Integer.parseInt(edtGlucoseDecimal.getText().toString());
        verifyWeigth();
    }

    private void verifyWeigth() {

        if (weightValue < 1 || weightValue > 400) {
            edtWeight.setError("Verifique o valor");
            return;
        } else {
            verifyFieldGlucoseInt();
        }
    }

    private void verifyFieldGlucoseInt() {

        if (glucoseValueInt < 0 || glucoseValueInt > 40) {
            edtWeight.setError("Verifique o valor");
        } else {
            verifyFieldGlucoseDecimal();
        }

    }

    private void verifyFieldGlucoseDecimal() {

        if (glucoseValueDecimal < 0 || glucoseValueDecimal > 9) {
            edtGlucoseDecimal.setError("Verifique o valor");
        } else {
            glucoseValueDecimalDiv = glucoseValueDecimal / 10;
        }

        glucoseValue = glucoseValueDecimalDiv + glucoseValueInt;

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
            valueDb = glucoseL.getmValue();
        }

        if (glucoses.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Usuario Não Encontrado. Tente Novamente",
                    Toast.LENGTH_SHORT).show();
        }
    }
}