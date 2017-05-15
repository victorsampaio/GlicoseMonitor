package vs.com.br.glicosemonitor.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vs.com.br.glicosemonitor.R;
import vs.com.br.glicosemonitor.dao.GlucoseDao;
import vs.com.br.glicosemonitor.model.Glucose;

public class ReportActivity extends AppCompatActivity {

    @BindView(R.id.txvUsername)
    TextView userName;

    @BindView(R.id.listViewReportGlucose)
    ListView listView;


    Dao<Glucose, Integer> glucoseDao = null;
    Glucose glucose = new Glucose();
    GlucoseDao daoglucose = new GlucoseDao(this);
    private List<Glucose> glucoses;
    private Glucose passengerL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        Glucose glucose = new Glucose();

        Dao<Glucose, Integer> glucoseIntegerDao;
        GlucoseDao glucoseDao = new GlucoseDao(this);

        glucoseIntegerDao = glucoseDao.getGlucoseDao();

        /*try {
            List<Glucose> glucoses = glucoseIntegerDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        met();
    }

    private void met() {

        List idPassengersList = new ArrayList();

        glucoses = new ArrayList<Glucose>();
        Log.d("DEBUG", "1 - passengers: " + glucoses);

        glucoseDao = daoglucose.getGlucoseDao();

        try {
            glucoses = glucoseDao.queryForAll();
            //glucoses = glucoseDao.queryBuilder().where().eq("idCurrentTravel", idTravelActual).query();
            //passengers = passengerDao.queryForAll();
            Log.d("DEBUG", "2 - passengers: " + glucoses);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Glucose glucoseL : glucoses) {
            //Log.i("Script", "Name: " + glucoseL.getIdPassenger());
            //Log.d("DEBUGP", "--->> AllPassengers: " + glucoseL.getIdPassenger());

            //Toast.makeText(getApplicationContext(), "Passageiro: " + glucoseL.getIdPassenger(), Toast.LENGTH_SHORT).show();

            //-------CAPTURAR NOME DOS PASSAGEIROS-----
            //idPassengersList.add(glucoseL.getIdPassenger());

            /*passengeRs = new ArrayList<Passenger>();
            Log.d("DEBUG", "1 - passengers: " + passengeRs);

            try {
                passengeRDao = daoPassengeR.getPassengerDao();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }
        /*try {
            passengerList = passengeRDao.queryBuilder().where().in("id", idPassengersList).query();
            setRestulListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        //Log.d("DEBUGP", "--->> AllPassengers: " + passengers);
        //Log.d("DEBUGP", "--->> AllPassengers: " + passengerList.get(0).getName());

        if (glucoses.isEmpty()) {
            Log.d("DEBUG", "passengers vazio");
            // Toast.makeText(getApplicationContext(), "CPF Não Encontrado. Tente Novamente",
            //         Toast.LENGTH_SHORT).show();
        }

    }
}
