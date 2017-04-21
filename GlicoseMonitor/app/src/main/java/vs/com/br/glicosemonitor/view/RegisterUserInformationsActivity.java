package vs.com.br.glicosemonitor.view;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.j256.ormlite.dao.Dao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vs.com.br.glicosemonitor.R;
import vs.com.br.glicosemonitor.dao.UserDao;
import vs.com.br.glicosemonitor.database.DatabaseHelper;
import vs.com.br.glicosemonitor.model.User;

public class RegisterUserInformationsActivity extends AppCompatActivity {

    @BindView(R.id.btnSaveUserRegister)
    Button btnSaveUserRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_informations);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnSaveUserRegister)
    public void submit(View view) {
        // TODO submit data to server...
        saveUserRegister();
    }

    private void saveUserRegister() {

        UserDao dao = new UserDao(this);
        Dao<User, Integer> userDao = null;
        try {
            userDao = dao.getUserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setmName("Mike");

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

        goToMenuRegisterActivity();
    }

    private void goToMenuRegisterActivity() {
        Intent itMenuRegister = new Intent(getApplicationContext(), MenuRegisterActivity.class);
        startActivity(itMenuRegister);
    }
}