package com.holepunchbatteryindicator.library.navigationliveo;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.library.Model.HelpItem;
import com.holepunchbatteryindicator.library.Model.Navigation;
import com.holepunchbatteryindicator.library.adapter.NavigationArinAdapter;
import com.holepunchbatteryindicator.library.interfaces.OnItemClickListener;
import com.holepunchbatteryindicator.library.interfaces.OnPrepareOptionsMenuArin;

import java.util.ArrayList;
import java.util.List;

public abstract class NavigationArin extends AppCompatActivity {
    public static final String CURRENT_CHECK_POSITION = "CURRENT_CHEKC_POSITION";
    public static final String CURRENT_POSITION = "CURRENT_POSITION";
    private boolean isSaveInstance = false;
    private int mColorCounter = 0;
    private int mColorDefault = 0;
    private int mColorIcon = 0;
    private int mColorName = 0;
    private int mColorSeparator = 0;
    private int mColorSubHeader = 0;
    private int mCurrentCheckPosition = 1;
    private int mCurrentPosition = 1;
    /* access modifiers changed from: private */
    public boolean mCustomHeader = false;
    /* access modifiers changed from: private */
    private ActionBarDrawerToggleCompat mDrawerToggle;
    private float mElevationToolBar = 15.0f;
    private RelativeLayout mFooterDrawer;
    private RelativeLayout mFooterSecondDrawer;
    private View mHeader;
    /* access modifiers changed from: private */
    public List<HelpItem> mHelpItem;
    private ImageView mIconFooter;
    private ImageView mIconSecondFooter;
    private ListView mList;
    private Navigation mNavigation = new Navigation();
    private NavigationArinAdapter mNavigationAdapter;
    private int mNewSelector = 0;
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickLiveo;
    private OnPrepareOptionsMenuArin mOnPrepareOptionsMenu;
    /* access modifiers changed from: private */
    public ScrimInsetsFrameLayout mRelativeDrawer;
    private boolean mRemoveAlpha = false;
    private boolean mRemoveColorFilter = false;
    /* access modifiers changed from: private */
    public boolean mRemoveHeader = false;
    private int mSelectorDefault = 0;
    private TextView mTitleFooter;
    private TextView mTitleSecondFooter;
    private Toolbar mToolbar;
    public ImageView userBackground;
    public TextView userEmail;
    public TextView userName;
    public ImageView userPhoto;

    public abstract void onInt(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mountListNavigation(bundle);
        if (bundle != null) {
            this.isSaveInstance = true;
            setCurrentPosition(bundle.getInt("CURRENT_POSITION"));
            setCurrentCheckPosition(bundle.getInt(CURRENT_CHECK_POSITION));
        }
        if (bundle == null) {
            this.mOnItemClickLiveo.onItemClick(this.mCurrentPosition);
        }
        setCheckedItemNavigation(this.mCurrentCheckPosition, true);
    }

    private void configureFindView() {
        ListView listView = (ListView) findViewById(R.id.list);
        this.mList = listView;

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
       this.mTitleFooter = (TextView) findViewById(R.id.titleFooter);
        this.mIconFooter = (ImageView) findViewById(R.id.iconFooter);
        this.mTitleSecondFooter = (TextView) findViewById(R.id.titleSecondFooter);
        this.mIconSecondFooter = (ImageView) findViewById(R.id.iconSecondFooter);
        this.mFooterDrawer = (RelativeLayout) findViewById(R.id.footerDrawer);
        this.mFooterSecondDrawer = (RelativeLayout) findViewById(R.id.footerSecondDrawer);
        this.mRelativeDrawer = (ScrimInsetsFrameLayout) findViewById(R.id.relativeDrawer);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {

            } catch (Exception e) {
                e.getMessage();
            }
            setElevationToolBar(this.mElevationToolBar);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("CURRENT_POSITION", this.mCurrentPosition);
        bundle.putInt(CURRENT_CHECK_POSITION, this.mCurrentCheckPosition);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ActionBarDrawerToggleCompat actionBarDrawerToggleCompat = this.mDrawerToggle;
        if (actionBarDrawerToggleCompat == null || !actionBarDrawerToggleCompat.onOptionsItemSelected(menuItem)) {
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }


    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ActionBarDrawerToggleCompat actionBarDrawerToggleCompat = this.mDrawerToggle;
        if (actionBarDrawerToggleCompat != null) {
            actionBarDrawerToggleCompat.syncState();
        }
    }

    private class ActionBarDrawerToggleCompat extends ActionBarDrawerToggle {
        public ActionBarDrawerToggleCompat(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar) {
            super(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        }

        public void onDrawerClosed(View view) {
            NavigationArin.this.supportInvalidateOptionsMenu();
        }

        public void onDrawerOpened(View view) {
            NavigationArin.this.supportInvalidateOptionsMenu();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionBarDrawerToggleCompat actionBarDrawerToggleCompat = this.mDrawerToggle;
        if (actionBarDrawerToggleCompat != null) {
            actionBarDrawerToggleCompat.onConfigurationChanged(configuration);
        }
    }



    private void mountListNavigation(Bundle bundle) {
        if (this.mOnItemClickLiveo == null) {
            createUserDefaultHeader();
            onInt(bundle);
        }
    }

    public void build() {
        if (this.mOnItemClickLiveo != null) {
            addHeaderView();
            ArrayList arrayList = new ArrayList();
            arrayList.add(0, Integer.valueOf(this.mNewSelector));
            arrayList.add(1, Integer.valueOf(this.mColorDefault));
            arrayList.add(2, Integer.valueOf(this.mColorIcon));
            arrayList.add(3, Integer.valueOf(this.mColorName));
            arrayList.add(4, Integer.valueOf(this.mColorSeparator));
            arrayList.add(5, Integer.valueOf(this.mColorCounter));
            arrayList.add(6, Integer.valueOf(this.mSelectorDefault));
            arrayList.add(7, Integer.valueOf(this.mColorSubHeader));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(0, Boolean.valueOf(this.mRemoveAlpha));
            arrayList2.add(1, Boolean.valueOf(this.mRemoveColorFilter));
            if (this.mHelpItem != null) {
                this.mNavigationAdapter = new NavigationArinAdapter(this, NavigationArinList.getNavigationAdapter(this, this.mHelpItem, this.mNavigation.colorSelected, this.mNavigation.removeSelector), arrayList2, arrayList);
            } else {
                this.mNavigationAdapter = new NavigationArinAdapter(this, NavigationArinList.getNavigationAdapter(this, this.mNavigation), arrayList2, arrayList);
            }
            setAdapter();
            return;
        }
        throw new RuntimeException(getString(R.string.start_navigation_listener));
    }

    private void setAdapter() {
        //TODO yash
//        NavigationArinAdapter navigationArinAdapter = this.mNavigationAdapter;
//        if (navigationArinAdapter != null) {
//            this.mList.setAdapter(navigationArinAdapter);
//        }
    }

    private void createUserDefaultHeader() {
        View inflate = getLayoutInflater().inflate(R.layout.navigation_list_header, this.mList, false);
        this.mHeader = inflate;
        this.userName = (TextView) inflate.findViewById(R.id.userName);
        this.userEmail = (TextView) this.mHeader.findViewById(R.id.userEmail);
        this.userPhoto = (ImageView) this.mHeader.findViewById(R.id.userPhoto);
        this.userBackground = (ImageView) this.mHeader.findViewById(R.id.userBackground);
    }

    private void addHeaderView() {
        if (!this.mRemoveHeader) {
            this.mList.addHeaderView(this.mHeader);
            this.mRelativeDrawer.setFitsSystemWindows(true);
        }
    }

    public NavigationArin removeHeader() {
        this.mRemoveHeader = true;
        this.mCustomHeader = true;
        this.mRelativeDrawer.setFitsSystemWindows(false);
        return this;
    }


    public NavigationArin backgroundList(int i) {
        this.mSelectorDefault = i;
        this.mList.setBackgroundResource(i);
        this.mFooterDrawer.setBackgroundResource(i);
        this.mFooterSecondDrawer.setBackgroundResource(i);
        return this;
    }


    public NavigationArin with(OnItemClickListener onItemClickListener) {
        setContentView(R.layout.navigation_main_dark);
        this.mOnItemClickLiveo = onItemClickListener;
        configureFindView();
        return this;
    }


    public NavigationArin addAllHelpItem(List<HelpItem> list) {
        this.mHelpItem = list;
        return this;
    }

    public NavigationArin startingPosition(int i) {
        if (!this.isSaveInstance) {
            this.mCurrentPosition = i;
            this.mCurrentCheckPosition = i;
        }
        return this;
    }

    /* access modifiers changed from: private */
    public void setCurrentPosition(int i) {
        this.mCurrentPosition = i;
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }

    /* access modifiers changed from: private */
    public void setCurrentCheckPosition(int i) {
        this.mCurrentCheckPosition = i;
    }


    public void setCheckedItemNavigation(int i, boolean z) {
        NavigationArinAdapter navigationArinAdapter = this.mNavigationAdapter;
        if (navigationArinAdapter != null) {
            navigationArinAdapter.resetarCheck();
            this.mNavigationAdapter.setChecked(i, z);
            return;
        }
        throw new RuntimeException(getString(R.string.start_navigation_listener));
    }

    public NavigationArin colorItemSelected(int i) {
        this.mNavigation.colorSelected = i;
        return this;
    }

    public NavigationArin colorItemDefault(int i) {
        this.mColorDefault = i;
        return this;
    }



    public void setElevationToolBar(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mElevationToolBar = f;
            getToolbar().setElevation(f);
        }
    }

     public NavigationArin customHeader(View view) {
        if (view != null) {
            removeHeader();
            this.mCustomHeader = false;
            this.mRelativeDrawer.setFitsSystemWindows(true);
            this.mList.addHeaderView(view);
            return this;
        }
        throw new RuntimeException(getString(R.string.custom_header_not_created));
    }


    public ListView getListView() {
        return this.mList;
    }

    public Toolbar getToolbar() {
        return this.mToolbar;
    }




}
