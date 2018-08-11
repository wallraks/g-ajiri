package com.example.brian.ejiajiri;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.brian.ejiajiri.Fragments.AboutFragment;
import com.example.brian.ejiajiri.Fragments.JobsFragment;
import com.example.brian.ejiajiri.Fragments.PaymentsFragment;
import com.example.brian.ejiajiri.Fragments.ProfileFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;


public class MainActivity extends AppCompatActivity {
    private BottomBar mBottomBar;
    FrameLayout frameLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);


        setupBottomBar();
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        mBottomBar = (BottomBar) findViewById(R.id.bottombar);

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelected(@IdRes int tabId) {

                Fragment fragment = null;
                switch (tabId) {
                    case R.id.home:
                        replace_fragment(new JobsFragment());
                        break;
                    case R.id.payments:
                        replace_fragment(new PaymentsFragment());
                        break;
                    case R.id.support:
                        replace_fragment(new ProfileFragment());
                        break;
                    case R.id.about:
                        replace_fragment(new AboutFragment());
                        break;

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_job) {
            startActivity(new Intent(MainActivity.this, NewJobActivity.class));
            return true;
        }
        else if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupBottomBar() {
        mBottomBar = (BottomBar) findViewById(R.id.bottombar);

        for (int i = 0; i < mBottomBar.getTabCount(); i++) {
            BottomBarTab tab = mBottomBar.getTabAtPosition(i);
            tab.setGravity(Gravity.CENTER);

            View icon = tab.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_icon);
            // the paddingTop will be modified when select/deselect,
            // so, in order to make the icon always center in tab,
            // we need set the paddingBottom equals paddingTop
            icon.setPadding(0, icon.getPaddingTop(), 0, icon.getPaddingTop());

            View title = tab.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_title);
            title.setVisibility(View.GONE);
        }
    }

    public void replace_fragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }
}
