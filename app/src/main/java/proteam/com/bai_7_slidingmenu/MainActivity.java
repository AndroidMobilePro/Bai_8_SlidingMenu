package proteam.com.bai_7_slidingmenu;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    String[] danhsach;
    DrawerLayout drawerlayout;
    ListView listview;

    private ActionBarDrawerToggle drawertoggle;
    String drawertitle;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sliding_2);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listview = (ListView) findViewById(R.id.drawer_list);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);

        danhsach = getResources().getStringArray(R.array.thanhpho);


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, R.layout.drawer_list_item, danhsach);
        listview.setAdapter(adapter);

        // get text from title of actionBar
        title = drawertitle = (String) getTitle();

        drawertoggle = new ActionBarDrawerToggle(
                this, //ap vao activity nao
                drawerlayout, //drawerlayout
                mToolbar,//icon
                R.string.drawer_open, //chuoi mo
                R.string.drawer_close  /*chuoidong*/) {

            //goi khi drawer da hoan tat close
            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(title);
                //bao menu da thay doi can tao lai
                //no se goi onPrepareOptionsMenu
                invalidateOptionsMenu();

            }

            //goi khi drawer da hoan tat open
            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stubx
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Chon thanh pho");

                invalidateOptionsMenu();
            }
        };

        //co lenh nay moi thuc hien duoc ham onDrawerClosed va onDrawerOpened
        drawerlayout.addDrawerListener(drawertoggle);

        //hien nut home len goc trai cua actionbar
        getSupportActionBar().setHomeButtonEnabled(true);
        // change button home default of actionbar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        //nhan nut home tra ve 1 level chu khong ve top level
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                String[] dstp = getResources().getStringArray(R.array.thanhpho);
                title = dstp[position];
                CityFragment fragment = new CityFragment();
                Bundle data = new Bundle();
                data.putInt("position", position);
                fragment.setArguments(data);

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction tran = manager.beginTransaction();
                // compare using add vs replace fragment
//                tran.add(R.id.content_frame, fragment);
                tran.replace(R.id.content_frame, fragment);
                tran.commit();

                drawerlayout.closeDrawer(listview);

            }
        });

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        boolean dangmo = drawerlayout.isDrawerOpen(listview);
        menu.findItem(R.id.action_test).setVisible(!dangmo);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (drawertoggle.onOptionsItemSelected(item))
            return true;

        //....dieu kien cac action bar item khac
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
