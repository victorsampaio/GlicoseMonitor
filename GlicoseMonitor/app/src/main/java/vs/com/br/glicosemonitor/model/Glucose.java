package vs.com.br.glicosemonitor.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by VictorSampaio on 21/04/2017.
 */

@DatabaseTable(tableName = Glucose.TABLE_NAME_GLUCOSE)
public class Glucose {

    public static final String TABLE_NAME_GLUCOSE = "Glucose";
    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_VALUE   = "value";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_VALUE)
    private double mValue;

    public Glucose() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getmValue() {
        return mValue;
    }

    public void setmValue(double mValue) {
        this.mValue = mValue;
    }

}
