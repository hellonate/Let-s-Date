package com.hmxl.yuedemo.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hmxl.yuedemo.R;
import com.hmxl.yuedemo.fragment.ContactFragment;
import com.hmxl.yuedemo.fragment.FindFragment;
import com.hmxl.yuedemo.fragment.FriendFragment;
import com.hmxl.yuedemo.fragment.MineFragment;

public class All_fragmment_Activity extends AppCompatActivity {
    private ContactFragment communityFragment;
    private FriendFragment messageFragment;
    private FindFragment findFragment_;
    private MineFragment mineFragment;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_fragmment);

        radioGroup = (RadioGroup) findViewById(R.id.radiogruop);
        communityFragment = new ContactFragment();
        messageFragment = new FriendFragment();
        findFragment_ = new FindFragment();
        mineFragment = new MineFragment();

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",1);
        showIndex(index);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();
                if (checkedId==R.id.rd_community){
                    ft.replace(R.id.fg_container, communityFragment);
                }else if (checkedId==R.id.rd_message){
                    ft.replace(R.id.fg_container, messageFragment);
                } else if (checkedId==R.id.rd_find){
                    ft.replace(R.id.fg_container, findFragment_);
                }else{
                    ft.replace(R.id.fg_container, mineFragment);
                }
                ft.commit();
            }
        });

    }

    private void showIndex(int index){
        if (index == 1){
            ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        }else if (index == 2){
            ((RadioButton)radioGroup.getChildAt(1)).setChecked(true);
        } else if (index == 3){
            ((RadioButton)radioGroup.getChildAt(2)).setChecked(true);
        }else{
            ((RadioButton)radioGroup.getChildAt(3)).setChecked(true);
        }
    }


    public void showFunctionPopupWindow(View view, final ImageButton btn_function) {
        final Context context = view.getContext();

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(R.layout.popwindow_function, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                200, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        // 设置按钮的点击事件


        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(context.getResources()
                .getDrawable(R.drawable.bg_filling));

        // 设置好参数之后再show
        //popupWindow.showAtLocation(view, Gravity.TOP,700,260);
        popupWindow.showAsDropDown(view.findViewById(R.id.btn_function));
    }

}
