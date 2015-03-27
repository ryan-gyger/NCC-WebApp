package example.com.team14;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity implements
        StockListFragment.OnFragmentInteractionListener,
        StockDetailedFragment.OnFragmentInteractionListener,
        StockEditFragment.OnFragmentInteractionListener{
    public static final int STOCKLISTFRAGMENT = 0;
    public static final int STOCKBUYFRAGMENT = 1;
    public static final int STOCKADDFRAGMENT = 2;
    public static final int STOCKDETAILEDFRAGMENT = 11;
    public static final int STOCKEDITFRAGMENT = 12;

    private List<String> mNavTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String mTitle = "Team 14";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavTitles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.nav_titles_array)));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mNavTitles));
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                null,
                R.string.drawer_open,
                R.string.drawer_close
        );

        Fragment fragment = new StockListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();

        mDrawerLayout.closeDrawer(mDrawerList);

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }



    @Override
    public void onFragmentInteraction(Long id) {
        StockDetailedFragment.selectedStock = Stock.load(Stock.class, id);
        changeFragment(STOCKDETAILEDFRAGMENT);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            changeFragment(position);
        }

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
        if (id == R.id.action_settings) {
            return true;
        }

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }


    public void changeFragment(int position) {
        Bundle args = new Bundle();
        changeFragment(position, args);
    }

    public void changeFragment(int position, Bundle args) {
        Fragment fragment = null;
        switch (position) {
            case STOCKLISTFRAGMENT:
                fragment = new StockListFragment();
                break;
            case STOCKADDFRAGMENT:
                fragment = new StockEditFragment();
                break;
            case STOCKDETAILEDFRAGMENT:
                fragment = new StockDetailedFragment();
                break;
            case STOCKEDITFRAGMENT:
                fragment = new StockEditFragment();
                break;
            default:
                fragment = new StockListFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).addToBackStack("home").commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            if ( position < mNavTitles.size() ) {
                setTitle(mNavTitles.get(position));
            } else {
                setTitle(mTitle);
            }

            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}
