package com.androidwind.androidquick.demo.features.function.ui.barbottom;

import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.features.function.json.JsonFragment;
import com.androidwind.androidquick.demo.features.function.permission.PermissionFragment;
import com.androidwind.androidquick.demo.features.module.db.greendao.GreenDaoFragment;
import com.androidwind.androidquick.demo.ui.fragment.ExampleFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import androidx.fragment.app.Fragment;
import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"bottombar"}, description = "RadioButton实例")
public class RadioButtonFragment extends BaseFragment {

    private Fragment mCatalogue1Fragment;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mCatalogue1Fragment = new ExampleFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, mCatalogue1Fragment, ExampleFragment.TAG).commit();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_radiobutton;
    }

    @OnClick({R.id.catalogue1, R.id.catalogue2, R.id.catalogue3, R.id.catalogue4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.catalogue1:
                switchPage(ExampleFragment.TAG, ExampleFragment.class);
                break;
            case R.id.catalogue2:
                switchPage(PermissionFragment.TAG, PermissionFragment.class);
                break;
            case R.id.catalogue3:
                switchPage(JsonFragment.TAG, JsonFragment.class);
                break;
            case R.id.catalogue4:
                switchPage(GreenDaoFragment.TAG, GreenDaoFragment.class);
                break;
        }
    }


    /**
     * @param tag 标记名
     * @param cls 需要创建的Fragment的类名
     */
    private void switchPage(String tag, Class cls) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            try {
                // 通过反射创建出该类对象
                Fragment instance = (Fragment) cls.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, instance, tag).hide(mCatalogue1Fragment).commit();
                mCatalogue1Fragment = instance;
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            getSupportFragmentManager().beginTransaction().hide(mCatalogue1Fragment).show(fragment).commit();
            mCatalogue1Fragment = fragment;
        }
    }
}
