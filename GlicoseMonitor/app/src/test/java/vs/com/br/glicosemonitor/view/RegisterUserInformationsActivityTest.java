package vs.com.br.glicosemonitor.view;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;

import org.junit.Before;
import org.junit.Test;

import vs.com.br.glicosemonitor.dao.UserDao;
import vs.com.br.glicosemonitor.model.User;

/**
 * Created by VictorSampaio on 02/05/2017.
 */
public class RegisterUserInformationsActivityTest {

    UserDao dao;
    Dao<User, Integer> userDao;
    User user;
    Context context;

    @Before
    public void setUp() throws Exception {
        dao = new UserDao(context);
        userDao = null;
        user = new User();
    }

    @Test
    public void submit() throws Exception {

        String userName = "usuario";
        String email = "email";

        //UserDao dao = new UserDao(this);
        //Dao<User, Integer> userDao = null;

        try {
            userDao = dao.getUserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //User user = new User();
        user.setmName(userName);
        user.setmEmail(email);

        try {
            try {
                //userDao.create(user);
                userDao.createOrUpdate(user);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}