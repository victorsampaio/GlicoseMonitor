package vs.com.br.glicosemonitor.dao;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;

import vs.com.br.glicosemonitor.database.DatabaseHelper;
import vs.com.br.glicosemonitor.model.User;

/**
 * Created by VictorSampaio on 21/04/2017.
 */

public class UserDao extends DatabaseHelper{

    public UserDao(Context context) {
        super(context);
    }

    private Dao<User, Integer> mUserDao = null;

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (mUserDao == null) {
            try {
                mUserDao = getDao(User.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return mUserDao;
    }

    @Override
    public void close() {
        mUserDao = null;
        super.close();
    }
}
