package com.hmxl.yuedemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hmxl.yuedemo.R;
import com.hmxl.yuedemo.activities.SearchfriendActivity;
import com.hmxl.yuedemo.adapter.UserListViewAdapter;
import com.hmxl.yuedemo.bean.Friend;
import com.hmxl.yuedemo.bean.User;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {
    String TAG = "FriendFragement";
    private LinearLayout layout ;
    ArrayList<Friend> friendList = new ArrayList<Friend>();
    UserListViewAdapter adapter;
    private ListView lv_friend;
    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        lv_friend = (ListView) view.findViewById(R.id.lv_friend);
        layout = (LinearLayout) view.findViewById(R.id.layout_addfriend);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击后跳转到搜索界面
                Intent intent = new Intent(getContext(), SearchfriendActivity.class);
                startActivity(intent);
            }
        });
        adapter = new UserListViewAdapter(view.getContext());

        // 获取当前用户的好友列表
        BmobQuery<Friend> query = new BmobQuery<Friend>();
        query.addWhereEqualTo("user",BmobUser.getCurrentUser());
        query.findObjects(new FindListener<Friend>() {
            @Override
            public void done(List<Friend> list, BmobException e) {
                if(e==null){
                       System.out.println("共查询到"+list.size()+"个好友");
                        lv_friend.setAdapter(adapter);
                        adapter.addAllData(list);
                        adapter.notifyDataSetChanged();

                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });

       return view;

    }

}
