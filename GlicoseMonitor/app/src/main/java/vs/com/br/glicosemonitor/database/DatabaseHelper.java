package vs.com.br.glicosemonitor.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import vs.com.br.glicosemonitor.model.Glucose;
import vs.com.br.glicosemonitor.model.UnitOfMeasurement;
import vs.com.br.glicosemonitor.model.User;

/**
 * Created by VictorSampaio on 20/04/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "GlicoseMonitor.sqlite";
    private static final int DATABASE_VERSION = 1;

    private Dao<User, Integer> mUserDao = null;
    private Dao<Glucose, Integer> mGlucoseDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            try {
                TableUtils.createTable(connectionSource, User.class);
                TableUtils.createTable(connectionSource, Glucose.class);
                TableUtils.createTable(connectionSource, UnitOfMeasurement.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Glucose.class, true);
            TableUtils.dropTable(connectionSource, UnitOfMeasurement.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


/*
    @Override
    public void close() {
        mUserDao = null;
        mGlucoseDao = null;
        super.close();
    }
*/

}
