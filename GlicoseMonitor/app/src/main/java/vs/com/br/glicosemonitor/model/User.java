package vs.com.br.glicosemonitor.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by VictorSampaio on 20/04/2017.
 */
// daoClass = CustomDao.class
@DatabaseTable(tableName = User.TABLE_NAME_USERS)
public class User {

    public static final String TABLE_NAME_USERS = "users";
    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_NAME_EMAIL   = "email";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    @DatabaseField(columnName = FIELD_NAME_EMAIL)
    private String mEmail;


    public User() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
