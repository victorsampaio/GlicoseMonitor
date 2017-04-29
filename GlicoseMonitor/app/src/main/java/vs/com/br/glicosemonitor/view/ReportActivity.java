package vs.com.br.glicosemonitor.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vs.com.br.glicosemonitor.R;

public class ReportActivity extends AppCompatActivity {

    @BindView(R.id.txvUsername)
    TextView userName;

    @BindView(R.id.listViewReportGlucose)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
    }
}
