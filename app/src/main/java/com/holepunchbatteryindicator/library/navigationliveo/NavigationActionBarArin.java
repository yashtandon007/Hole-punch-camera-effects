package com.holepunchbatteryindicator.library.navigationliveo;

import android.app.Activity;
import android.content.res.Configuration;
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

public abstract class NavigationActionBarArin extends AppCompatActivity {
    public static final String CURRENT_CHECK_POSITION = "CURRENT_CHECK_POSITION";
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
    public DrawerLayout mDrawerLayout;
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
        //TODO yash
//        mountListNavigation(bundle);
//        if (bundle != null) {
//            this.isSaveInstance = true;
//            setCurrentPosition(bundle.getInt("CURRENT_POSITION"));
//            setCurrentCheckPosition(bundle.getInt(CURRENT_CHECK_POSITION));
//        }
//        if (bundle == null) {
//            this.mOnItemClickLiveo.onItemClick(this.mCurrentPosition);
//        }
//        setCheckedItemNavigation(this.mCurrentCheckPosition, true);
    }

    private void configureFindView() {
        ListView listView = (ListView) findViewById(R.id.list);
        this.mList = listView;
        listView.setOnItemClickListener(new DrawerItemClickListener());
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggleCompat actionBarDrawerToggleCompat = new ActionBarDrawerToggleCompat(this, this.mDrawerLayout, this.mToolbar);
        this.mDrawerToggle = actionBarDrawerToggleCompat;
        this.mDrawerLayout.addDrawerListener(actionBarDrawerToggleCompat);
        this.mTitleFooter = (TextView) findViewById(R.id.titleFooter);
        this.mIconFooter = (ImageView) findViewById(R.id.iconFooter);
        this.mTitleSecondFooter = (TextView) findViewById(R.id.titleSecondFooter);
        this.mIconSecondFooter = (ImageView) findViewById(R.id.iconSecondFooter);
        this.mFooterDrawer = (RelativeLayout) findViewById(R.id.footerDrawer);
        this.mFooterSecondDrawer = (RelativeLayout) findViewById(R.id.footerSecondDrawer);
        this.mRelativeDrawer = (ScrimInsetsFrameLayout) findViewById(R.id.relativeDrawer);
        setSupportActionBar(this.mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                this.mDrawerLayout.setStatusBarBackground(getTheme().obtainStyledAttributes(new int[]{16843827}).getResourceId(0, 0));
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

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.mOnPrepareOptionsMenu != null) {
            this.mOnPrepareOptionsMenu.onPrepareOptionsMenu(menu, this.mCurrentPosition, this.mDrawerLayout.isDrawerOpen((View) this.mRelativeDrawer));
        }
        return super.onPrepareOptionsMenu(menu);
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
            NavigationActionBarArin.this.supportInvalidateOptionsMenu();
        }

        public void onDrawerOpened(View view) {
            NavigationActionBarArin.this.supportInvalidateOptionsMenu();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionBarDrawerToggleCompat actionBarDrawerToggleCompat = this.mDrawerToggle;
        if (actionBarDrawerToggleCompat != null) {
            actionBarDrawerToggleCompat.onConfigurationChanged(configuration);
        }
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        private DrawerItemClickListener() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int i2 = (!NavigationActionBarArin.this.mRemoveHeader || !NavigationActionBarArin.this.mCustomHeader) ? i - 1 : i;
            if (i2 == -1) {
                NavigationActionBarArin.this.mDrawerLayout.closeDrawer((View) NavigationActionBarArin.this.mRelativeDrawer);
                return;
            }
            HelpItem helpItem = (HelpItem) NavigationActionBarArin.this.mHelpItem.get(i2);
            if (!helpItem.isHeader()) {
                if (i != 0 || (NavigationActionBarArin.this.mRemoveHeader && NavigationActionBarArin.this.mCustomHeader)) {
                    NavigationActionBarArin.this.setCurrentPosition(i2);
                    if (helpItem.isCheck()) {
                        NavigationActionBarArin.this.setCurrentCheckPosition(i2);
                        NavigationActionBarArin.this.setCheckedItemNavigation(i2, true);
                    }
                    NavigationActionBarArin.this.mOnItemClickLiveo.onItemClick(i2);
                }
                NavigationActionBarArin.this.mDrawerLayout.closeDrawer((View) NavigationActionBarArin.this.mRelativeDrawer);
            }
        }
    }

    private void mountListNavigation(Bundle bundle) {
        if (this.mOnItemClickLiveo == null) {
            createUserDefaultHeader();
            onInt(bundle);
        }
    }

    private void addHeaderView() {
        if (!this.mRemoveHeader) {
            this.mList.addHeaderView(this.mHeader);
        }
    }

    public NavigationActionBarArin removeElevationToolBar() {
        this.mElevationToolBar = 0.0f;
        return this;
    }

    public void setAdapterNavigation() {
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
            this.mList.setAdapter(this.mNavigationAdapter);
            return;
        }
        throw new RuntimeException(getString(R.string.start_navigation_listener));
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
            this.mList.setAdapter(this.mNavigationAdapter);
            return;
        }
        throw new RuntimeException(getString(R.string.start_navigation_listener));
    }

    private void createUserDefaultHeader() {
        View inflate = getLayoutInflater().inflate(R.layout.navigation_list_header, this.mList, false);
        this.mHeader = inflate;
        this.userName = (TextView) inflate.findViewById(R.id.userName);
        this.userEmail = (TextView) this.mHeader.findViewById(R.id.userEmail);
        this.userPhoto = (ImageView) this.mHeader.findViewById(R.id.userPhoto);
        this.userBackground = (ImageView) this.mHeader.findViewById(R.id.userBackground);
    }

    public NavigationActionBarArin backgroundList(int i) {
        this.mSelectorDefault = i;
        this.mList.setBackgroundResource(i);
        this.mFooterDrawer.setBackgroundResource(i);
        this.mFooterSecondDrawer.setBackgroundResource(i);
        return this;
    }

    public NavigationActionBarArin footerBackground(int i) {
        this.mFooterDrawer.setBackgroundResource(i);
        this.mFooterSecondDrawer.setBackgroundResource(i);
        return this;
    }

    public NavigationActionBarArin with(OnItemClickListener onItemClickListener) {
        setContentView(R.layout.navigation_main_actionbar_dark);
        this.mOnItemClickLiveo = onItemClickListener;
        configureFindView();
        return this;
    }

    public NavigationActionBarArin with(OnItemClickListener onItemClickListener, int i) {
        setContentView(i == 0 ? R.layout.navigation_main_actionbar_dark : R.layout.navigation_main_actionbar_light);
        this.mOnItemClickLiveo = onItemClickListener;
        configureFindView();
        return this;
    }

    public NavigationActionBarArin addAllHelpItem(List<HelpItem> list) {
        this.mHelpItem = list;
        return this;
    }

    public NavigationActionBarArin nameItem(List<String> list) {
        this.mNavigation.nameItem = list;
        return this;
    }

    public NavigationActionBarArin iconItem(List<Integer> list) {
        this.mNavigation.iconItem = list;
        return this;
    }

    public NavigationActionBarArin headerItem(List<Integer> list) {
        this.mNavigation.headerItem = list;
        return this;
    }

    public NavigationActionBarArin countItem(SparseIntArray sparseIntArray) {
        this.mNavigation.countItem = sparseIntArray;
        return this;
    }

    public void setNavigationAdapter(List<String> list, List<Integer> list2, List<Integer> list3, SparseIntArray sparseIntArray) {
        nameItem(list);
        iconItem(list2);
        headerItem(list3);
        countItem(sparseIntArray);
    }

    public void setNavigationAdapter(List<String> list, List<Integer> list2) {
        nameItem(list);
        iconItem(list2);
    }

    public NavigationActionBarArin hideItem(List<Integer> list) {
        this.mNavigation.hideItem = list;
        return this;
    }

    public void showNavigationItem(List<Integer> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                setVisibleItemNavigation(list.get(i).intValue(), true);
            }
            return;
        }
        throw new RuntimeException(getString(R.string.list_hide_item));
    }

    public void setNavigationListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickLiveo = onItemClickListener;
    }

    public void setDefaultStartPositionNavigation(int i) {
        if (!this.isSaveInstance) {
            this.mCurrentPosition = i;
            this.mCurrentCheckPosition = i;
        }
    }

    public NavigationActionBarArin startingPosition(int i) {
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

    public int getCurrentCheckPosition() {
        return this.mCurrentCheckPosition;
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

    public void setFooterInformationDrawer(String str, int i) {
        if (str == null) {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        } else if (!str.trim().equals("")) {
            this.mTitleFooter.setText(str);
            if (i == 0) {
                this.mIconFooter.setVisibility(8);
            } else {
                this.mIconFooter.setImageResource(i);
            }
        } else {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        }
    }

    public NavigationActionBarArin footerItem(String str, int i) {
        if (str == null) {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        } else if (!str.trim().equals("")) {
            this.mTitleFooter.setText(str);
            if (i == 0) {
                this.mIconFooter.setVisibility(8);
            } else {
                this.mIconFooter.setImageResource(i);
            }
            return this;
        } else {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        }
    }

    public NavigationActionBarArin footerSecondItem(String str, int i, int i2) {
        if (str == null) {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        } else if (!str.trim().equals("")) {
            this.mTitleSecondFooter.setText(str);
            if (i2 > 0) {
                this.mTitleSecondFooter.setTextColor(ContextCompat.getColor(this, i2));
            }
            if (i == 0) {
                this.mIconSecondFooter.setVisibility(8);
            } else {
                this.mIconSecondFooter.setImageResource(i);
                if (i2 > 0) {
                    this.mIconSecondFooter.setColorFilter(ContextCompat.getColor(this, i2));
                }
            }
            return this;
        } else {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        }
    }

    public void setFooterInformationDrawer(String str, int i, int i2, int i3) {
        if (str == null) {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        } else if (!str.trim().equals("")) {
            this.mTitleFooter.setText(str);
            if (i2 > 0) {
                this.mTitleFooter.setTextColor(ContextCompat.getColor(this, i2));
            }
            if (i == 0) {
                this.mIconFooter.setVisibility(8);
                return;
            }
            this.mIconFooter.setImageResource(i);
            if (i3 > 0) {
                this.mIconFooter.setColorFilter(ContextCompat.getColor(this, i3));
            }
        } else {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        }
    }

    public NavigationActionBarArin footerInformationDrawer(String str, int i, int i2, int i3) {
        if (str == null) {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        } else if (!str.trim().equals("")) {
            this.mTitleFooter.setText(str);
            if (i2 > 0) {
                this.mTitleFooter.setTextColor(ContextCompat.getColor(this, i2));
            }
            if (i == 0) {
                this.mIconFooter.setVisibility(8);
            } else {
                this.mIconFooter.setImageResource(i);
                if (i3 > 0) {
                    this.mIconFooter.setColorFilter(ContextCompat.getColor(this, i3));
                }
            }
            return this;
        } else {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        }
    }

    public NavigationActionBarArin setFooterInformationDrawer(int i, int i2) {
        if (i != 0) {
            this.mTitleFooter.setText(getString(i));
            if (i2 == 0) {
                this.mIconFooter.setVisibility(8);
            } else {
                this.mIconFooter.setImageResource(i2);
            }
            int i3 = this.mColorDefault;
            if (i3 > 0) {
                footerNameColor(i3);
                footerIconColor(this.mColorDefault);
            }
            return this;
        }
        throw new RuntimeException(getString(R.string.title_null_or_empty));
    }

    public NavigationActionBarArin footerItem(int i, int i2) {
        if (i != 0) {
            this.mTitleFooter.setText(getString(i));
            if (i2 == 0) {
                this.mIconFooter.setVisibility(8);
            } else {
                this.mIconFooter.setImageResource(i2);
            }
            int i3 = this.mColorDefault;
            if (i3 > 0) {
                footerNameColor(i3);
                footerIconColor(this.mColorDefault);
            }
            return this;
        }
        throw new RuntimeException(getString(R.string.title_null_or_empty));
    }

    public NavigationActionBarArin footerSecondItem(int i, int i2) {
        if (i != 0) {
            this.mTitleSecondFooter.setText(getString(i));
            if (i2 == 0) {
                this.mIconSecondFooter.setVisibility(8);
            } else {
                this.mIconSecondFooter.setImageResource(i2);
            }
            int i3 = this.mColorDefault;
            if (i3 > 0) {
                footerSecondNameColor(i3);
                footerSecondIconColor(this.mColorDefault);
            }
            this.mFooterSecondDrawer.setVisibility(0);
            return this;
        }
        throw new RuntimeException(getString(R.string.title_null_or_empty));
    }

    public void setFooterInformationDrawer(int i, int i2, int i3, int i4) {
        if (i != 0) {
            this.mTitleFooter.setText(i);
            if (i3 > 0) {
                this.mTitleFooter.setTextColor(ContextCompat.getColor(this, i3));
            }
            if (i2 == 0) {
                this.mIconFooter.setVisibility(8);
                return;
            }
            this.mIconFooter.setImageResource(i2);
            if (i4 > 0) {
                this.mIconFooter.setColorFilter(ContextCompat.getColor(this, i4));
                return;
            }
            return;
        }
        throw new RuntimeException(getString(R.string.title_null_or_empty));
    }

    public NavigationActionBarArin footerItem(int i, int i2, int i3, int i4) {
        if (i != 0) {
            this.mTitleFooter.setText(i);
            if (i3 > 0) {
                this.mTitleFooter.setTextColor(ContextCompat.getColor(this, i3));
            }
            if (i2 == 0) {
                this.mIconFooter.setVisibility(8);
            } else {
                this.mIconFooter.setImageResource(i2);
                if (i4 > 0) {
                    this.mIconFooter.setColorFilter(ContextCompat.getColor(this, i4));
                }
            }
            return this;
        }
        throw new RuntimeException(getString(R.string.title_null_or_empty));
    }

    public NavigationActionBarArin footerSecondItem(int i, int i2, int i3, int i4) {
        if (i != 0) {
            this.mTitleSecondFooter.setText(i);
            if (i3 > 0) {
                this.mTitleSecondFooter.setTextColor(ContextCompat.getColor(this, i3));
            }
            if (i2 == 0) {
                this.mIconSecondFooter.setVisibility(8);
            } else {
                this.mIconSecondFooter.setImageResource(i2);
                if (i4 > 0) {
                    this.mIconSecondFooter.setColorFilter(ContextCompat.getColor(this, i4));
                }
            }
            this.mFooterSecondDrawer.setVisibility(0);
            return this;
        }
        throw new RuntimeException(getString(R.string.title_null_or_empty));
    }

    public NavigationActionBarArin footerItem(int i, int i2, int i3) {
        if (i != 0) {
            this.mTitleFooter.setText(i);
            if (i3 > 0) {
                this.mTitleFooter.setTextColor(ContextCompat.getColor(this, i3));
            }
            if (i2 == 0) {
                this.mIconFooter.setVisibility(8);
            } else {
                this.mIconFooter.setImageResource(i2);
                if (i3 > 0) {
                    this.mIconFooter.setColorFilter(ContextCompat.getColor(this, i3));
                }
            }
            return this;
        }
        throw new RuntimeException(getString(R.string.title_null_or_empty));
    }

    public NavigationActionBarArin footerSecondItem(int i, int i2, int i3) {
        if (i != 0) {
            this.mTitleSecondFooter.setText(i);
            if (i3 > 0) {
                this.mTitleSecondFooter.setTextColor(ContextCompat.getColor(this, i3));
            }
            if (i2 == 0) {
                this.mIconSecondFooter.setVisibility(8);
            } else {
                this.mIconSecondFooter.setImageResource(i2);
                if (i3 > 0) {
                    this.mIconSecondFooter.setColorFilter(ContextCompat.getColor(this, i3));
                }
            }
            this.mFooterSecondDrawer.setVisibility(0);
            return this;
        }
        throw new RuntimeException(getString(R.string.title_null_or_empty));
    }

    public NavigationActionBarArin footerItem(String str, int i, int i2) {
        if (str == null) {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        } else if (!str.trim().equals("")) {
            this.mTitleFooter.setText(str);
            if (i2 > 0) {
                this.mTitleFooter.setTextColor(ContextCompat.getColor(this, i2));
            }
            if (i == 0) {
                this.mIconFooter.setVisibility(8);
            } else {
                this.mIconFooter.setImageResource(i);
                if (i2 > 0) {
                    this.mIconFooter.setColorFilter(ContextCompat.getColor(this, i2));
                }
            }
            return this;
        } else {
            throw new RuntimeException(getString(R.string.title_null_or_empty));
        }
    }

    public void setFooterNavigationVisible(boolean z) {
        this.mFooterDrawer.setVisibility(z ? 0 : 8);
    }

    public NavigationActionBarArin removeFooter() {
        this.mFooterDrawer.setVisibility(8);
        return this;
    }

    public void setColorSelectedItemNavigation(int i) {
        this.mNavigation.colorSelected = i;
    }

    public NavigationActionBarArin colorItemSelected(int i) {
        this.mNavigation.colorSelected = i;
        return this;
    }

    public void setFooterNameColorNavigation(int i) {
        this.mTitleFooter.setTextColor(ContextCompat.getColor(this, i));
    }

    public NavigationActionBarArin footerNameColor(int i) {
        this.mTitleFooter.setTextColor(ContextCompat.getColor(this, i));
        return this;
    }

    public NavigationActionBarArin footerSecondNameColor(int i) {
        this.mTitleSecondFooter.setTextColor(ContextCompat.getColor(this, i));
        return this;
    }

    public NavigationActionBarArin footerIconColor(int i) {
        this.mIconFooter.setColorFilter(ContextCompat.getColor(this, i));
        return this;
    }

    public NavigationActionBarArin footerSecondIconColor(int i) {
        this.mIconSecondFooter.setColorFilter(ContextCompat.getColor(this, i));
        return this;
    }

    public void setFooterIconColorNavigation(int i) {
        this.mIconFooter.setColorFilter(ContextCompat.getColor(this, i));
    }

    public void setColorDefaultItemNavigation(int i) {
        this.mColorDefault = i;
    }

    public NavigationActionBarArin colorItemDefault(int i) {
        this.mColorDefault = i;
        return this;
    }

    public void setColorIconItemNavigation(int i) {
        this.mColorIcon = i;
    }

    public NavigationActionBarArin colorItemIcon(int i) {
        this.mColorIcon = i;
        return this;
    }

    public void setColorSeparatorItemSubHeaderNavigation(int i) {
        this.mColorSeparator = i;
    }

    public NavigationActionBarArin colorNameSubHeader(int i) {
        this.mColorSubHeader = i;
        return this;
    }

    public NavigationActionBarArin colorLineSeparator(int i) {
        this.mColorSeparator = i;
        return this;
    }

    public void setColorCounterItemNavigation(int i) {
        this.mColorCounter = i;
    }

    public NavigationActionBarArin colorItemCounter(int i) {
        this.mColorCounter = i;
        return this;
    }

    public void setColorNameItemNavigation(int i) {
        this.mColorName = i;
    }

    public NavigationActionBarArin colorItemName(int i) {
        this.mColorName = i;
        return this;
    }

    public void setNewSelectorNavigation(int i) {
        if (!this.mNavigation.removeSelector) {
            this.mNewSelector = i;
            return;
        }
        throw new RuntimeException(getString(R.string.remove_selector_navigation));
    }

    public NavigationActionBarArin selectorCheck(int i) {
        if (!this.mNavigation.removeSelector) {
            this.mNewSelector = i;
            return this;
        }
        throw new RuntimeException(getString(R.string.remove_selector_navigation));
    }

    public void removeSelectorNavigation() {
        this.mNavigation.removeSelector = true;
    }

    public NavigationActionBarArin removeSelector() {
        this.mNavigation.removeSelector = true;
        return this;
    }

    public void setNewName(int i, String str) {
        this.mNavigationAdapter.setNewName(i, str);
    }

    public void setNewName(int i, int i2) {
        this.mNavigationAdapter.setNewName(i, getString(i2));
    }

    public void setNewIcon(int i, int i2) {
        this.mNavigationAdapter.setNewIcon(i, i2);
    }

    public void setNewInformationItem(int i, int i2, int i3, int i4) {
        this.mNavigationAdapter.setNewInformationItem(i, getString(i2), i3, i4);
    }

    public void setNewInformationItem(int i, String str, int i2, int i3) {
        this.mNavigationAdapter.setNewInformationItem(i, str, i2, i3);
    }

    public void setNewCounterValue(int i, int i2) {
        this.mNavigationAdapter.setNewCounterValue(i, i2);
    }

    public void setIncreasingCounterValue(int i, int i2) {
        this.mNavigationAdapter.setIncreasingCounterValue(i, i2);
    }

    public void setDecreaseCountervalue(int i, int i2) {
        this.mNavigationAdapter.setDecreaseCountervalue(i, i2);
    }

    public void setVisibleItemNavigation(int i, boolean z) {
        this.mNavigationAdapter.setVisibleItemNavigation(i, z);
    }

    public void removeAlphaItemNavigation() {
        this.mRemoveAlpha = !this.mRemoveAlpha;
    }

    public NavigationActionBarArin removeAlpha() {
        this.mRemoveAlpha = !this.mRemoveAlpha;
        return this;
    }

    public NavigationActionBarArin removeColorFilter() {
        this.mRemoveColorFilter = !this.mRemoveColorFilter;
        return this;
    }

    public void setElevationToolBar(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mElevationToolBar = f;
            getToolbar().setElevation(f);
        }
    }

    public void showDefaultHeader() {
        View view = this.mHeader;
        if (view != null) {
            this.mList.addHeaderView(view);
            return;
        }
        throw new RuntimeException(getString(R.string.header_not_created));
    }

    private void removeDefaultHeader() {
        View view = this.mHeader;
        if (view != null) {
            this.mList.removeHeaderView(view);
            return;
        }
        throw new RuntimeException(getString(R.string.header_not_created));
    }

    public NavigationActionBarArin removeHeader() {
        this.mRemoveHeader = true;
        this.mCustomHeader = true;
        return this;
    }

    public void addCustomHeader(View view) {
        if (view != null) {
            removeDefaultHeader();
            this.mList.addHeaderView(view);
            return;
        }
        throw new RuntimeException(getString(R.string.custom_header_not_created));
    }

    public NavigationActionBarArin customHeader(View view) {
        if (view != null) {
            removeHeader();
            this.mCustomHeader = false;
            this.mRelativeDrawer.setFitsSystemWindows(true);
            this.mList.addHeaderView(view);
            return this;
        }
        throw new RuntimeException(getString(R.string.custom_header_not_created));
    }

    public void removeCustomdHeader(View view) {
        if (view != null) {
            this.mList.removeHeaderView(view);
            return;
        }
        throw new RuntimeException(getString(R.string.custom_header_not_created));
    }

    public ListView getListView() {
        return this.mList;
    }

    public Toolbar getToolbar() {
        return this.mToolbar;
    }

    public boolean isDrawerOpen() {
        return this.mDrawerLayout.isDrawerOpen((View) this.mRelativeDrawer);
    }

    public void openDrawer() {
        this.mDrawerLayout.openDrawer((View) this.mRelativeDrawer);
    }

    public void closeDrawer() {
        this.mDrawerLayout.closeDrawer((View) this.mRelativeDrawer);
    }

    public NavigationActionBarArin setOnItemClick(OnItemClickListener onItemClickListener) {
        this.mOnItemClickLiveo = onItemClickListener;
        return this;
    }

    public NavigationActionBarArin setOnPrepareOptionsMenu(OnPrepareOptionsMenuArin onPrepareOptionsMenuArin) {
        this.mOnPrepareOptionsMenu = onPrepareOptionsMenuArin;
        return this;
    }

    public NavigationActionBarArin setOnClickUser(View.OnClickListener onClickListener) {
        this.userPhoto.setOnClickListener(onClickListener);
        return this;
    }

    public NavigationActionBarArin setOnClickFooter(View.OnClickListener onClickListener) {
        this.mFooterDrawer.setOnClickListener(onClickListener);
        return this;
    }

    public NavigationActionBarArin setOnClickFooterSecond(View.OnClickListener onClickListener) {
        this.mFooterSecondDrawer.setOnClickListener(onClickListener);
        return this;
    }

    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen((View) this.mRelativeDrawer)) {
            this.mDrawerLayout.closeDrawer((View) this.mRelativeDrawer);
        } else {
            super.onBackPressed();
        }
    }
}
