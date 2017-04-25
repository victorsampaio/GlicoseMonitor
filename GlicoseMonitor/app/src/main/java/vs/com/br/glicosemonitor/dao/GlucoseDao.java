package vs.com.br.glicosemonitor.dao;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;

import vs.com.br.glicosemonitor.database.DatabaseHelper;
import vs.com.br.glicosemonitor.model.Glucose;
import vs.com.br.glicosemonitor.model.User;

/**
 * Created by VictorSampaio on 21/04/2017.
 */

public class GlucoseDao extends DatabaseHelper {

    public GlucoseDao(Context context) {
        super(context);
    }

    private Dao<Glucose, Integer> mGlucoseDao = null;

    public Dao<Glucose, Integer> getGlucoseDao() throws SQLException {
        if (mGlucoseDao == null) {
            try {
                mGlucoseDao = getDao(Glucose.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return mGlucoseDao;
    }

    @Override
    public void close() {
        mGlucoseDao = null;
        super.close();
    }


}
