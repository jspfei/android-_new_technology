package net.tt.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sky.downloader.greendao.UserDao;

import net.tt.greendao.bean.User;
import net.tt.greendao.bean.UserDataOp;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;
    private TextView showTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTest = (TextView) findViewById(R.id.showTest) ;
        //userDao = GreenDaoApp.getDaoSession().getUserDao();

       /* add();
        showUserList();
        UserDataOp.deleteUser((long) 3);
        showUserList();*/

       /* User user = new User((long)1,"a","18","100");
        UserDataOp.insertUser(user);
        showUserList();*/


        showUserList(UserDataOp.queryUserByName("a"));
    }

    private void add() {
        User user = new User((long)1,"a","18","90");
        UserDataOp.insertUser(user);
        User user1 = new User((long)2,"a","18","90");
        UserDataOp.insertUser(user1);
        User user2 = new User((long)3,"b","18","70");
        UserDataOp.insertUser(user2);
        User user3= new User((long)4,"b","18","67");
        UserDataOp.insertUser(user3);
    }
    private void showUserList(){
        System.out.print("size "+UserDataOp.queryAll().size()) ;

        List<User> listuser= UserDataOp.queryAll();
        Iterator<User> it = listuser.iterator();
        String str = "";
        while (it.hasNext()){
            User user =(User) it.next();
            str = str + user.getId()+" "+user.getName()+" "+user.getAge()+" "+user.getScore()+ " \n";
        }
        showTest.setText(str);
    }
    private void showUserList(List<User> listuser){

        Iterator<User> it = listuser.iterator();
        String str = "";
        while (it.hasNext()){
            User user =(User) it.next();
            str = str + user.getId()+" "+user.getName()+" "+user.getAge()+" "+user.getScore()+ " \n";
        }
        showTest.setText(str);
    }
}
