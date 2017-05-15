package vs.com.br.glicosemonitor.dao;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;

import vs.com.br.glicosemonitor.database.DatabaseHelper;
import vs.com.br.glicosemonitor.model.UnitOfMeasurement;

/**
 * Created by VictorSampaio on 15/05/2017.
 */

public class UnitOfMeasurementDao extends DatabaseHelper{

    public UnitOfMeasurementDao(Context context) {
        super(context);
    }

    private Dao<UnitOfMeasurement, Integer > unitOfMeasurementIntegerDao;

    public Dao<UnitOfMeasurement, Integer> getUnitOfMeasurementIntegerDao() throws SQLException, java.sql.SQLException {
        if (unitOfMeasurementIntegerDao == null){
            try {
                unitOfMeasurementIntegerDao = getDao(UnitOfMeasurement.class);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return unitOfMeasurementIntegerDao;
    }
}