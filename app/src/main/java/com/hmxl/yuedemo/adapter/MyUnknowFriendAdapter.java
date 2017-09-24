package com.hmxl.yuedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hmxl.yuedemo.R;

import com.hmxl.yuedemo.bean.Friend;
import com.hmxl.yuedemo.bean.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by Nate on 2017/5/22.
 */

public class MyUnknowFriendAdapter extends BaseAdapter{
    private Button btn_add;
    private ArrayList<BmobObject> list=new ArrayList<>();
    private ArrayList<Boolean> isFriendList = new ArrayList<>();

    private Context context;
    LayoutInflater inflater;

    public MyUnknowFriendAdapter(Context context){
        this.context=context;
        inflater= LayoutInflater.from(context);
    }
    // 添加一堆
    public void addAllData(List<BmobObject> templist,ArrayList<Boolean> isFriendList){
        list.clear();
        list.addAll(templist);
        this.isFriendList.clear();
        this.isFriendList.addAll(isFriendList);
    }
    // 添加一行
//    public void addData(User user){
//        list.add(user);
//    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(R.layout.inflater_unknowfriendlist_item,null);
        }
        ImageView iv_listImage= (ImageView) convertView.findViewById(R.id.iv_listImage);
        TextView tv_content= (TextView) convertView.findViewById(R.id.tv_content);
        btn_add = (Button) convertView.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(context, "发送好友请求信息", Toast.LENGTH_SHORT).show();
            }
        });
        BmobObject object = list.get(position);
        String username = "";
        if(isFriendList.get(position)){
            username = ((Friend) object).getFriendUser().getUsername();
            btn_add.setText("已是好友");
            btn_add.setClickable(false);
        }else{
            username = ((User) object).getUsername();
            btn_add.setText("添加好友");
            btn_add.setClickable(true);
        }
        System.out.println(username + "--");
        iv_listImage.setBackgroundResource(R.drawable.icon_map_male);
        tv_content.setText(username);
        return convertView;
    }
}
