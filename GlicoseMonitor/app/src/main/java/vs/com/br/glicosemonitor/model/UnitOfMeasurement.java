package vs.com.br.glicosemonitor.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by VictorSampaio on 15/05/2017.
 */

public class UnitOfMeasurement {

    public static final String TABLE_NAME = "UnitOfMeasurement";
    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_UNIT   = "unit";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_UNIT)
    private String unitName;

    public UnitOfMeasurement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
