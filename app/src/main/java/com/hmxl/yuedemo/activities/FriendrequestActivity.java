package com.hmxl.yuedemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hmxl.yuedemo.R;
import com.hmxl.yuedemo.bean.User;
import com.hmxl.yuedemo.bmobMessage.AddFriendMessage;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class FriendrequestActivity extends AppCompatActivity {

    private ImageView iv_avator;
    private TextView tv_name;
    private Button btn_add_friend;
    private Button btn_chat;
    User user;
    BmobIMUserInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendrequest);
        iv_avator = (ImageView) findViewById(R.id.iv_avator);
        tv_name = (TextView) findViewById(R.id.tv_name);
        btn_add_friend = (Button) findViewById(R.id.btn_add_friend);
        btn_chat = (Button) findViewById(R.id.btn_chat);
//         user = (User)getIntent().getSerializableExtra("u"); //Todo
       // user=(User)getBundle().getSerializable("u"); 官方的


//        if(user.getObjectId().equals(getCurrentUid())){
//            btn_add_friend.setVisibility(View.GONE);
//            btn_chat.setVisibility(View.GONE);
//        }else{
//            btn_add_friend.setVisibility(View.VISIBLE);
//            btn_chat.setVisibility(View.VISIBLE);
//        }
        //构造聊天方的用户信息:传入用户id、用户名和用户头像三个参数
//        info = new BmobIMUserInfo(user.getObjectId(),user.getUsername(),user.getAvatar());
//        ImageLoaderFactory.getLoader().loadAvator(iv_avator,user.getAvatar(),R.mipmap.head);
//        tv_name.setText(user.getUsername());



    }
    private void sendAddFriendMessage(){
        //启动一个暂态会话，也就是isTransient为true,表明该会话仅执行发送消息的操作，不会保存会话和消息到本地数据库中，
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, true,null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), c);
        //新建一个添加好友的自定义消息实体
        AddFriendMessage msg =new AddFriendMessage();
        //Todo 修改过
        User currentUser = BmobUser.getCurrentUser(User.class);
        msg.setContent("很高兴认识你，可以加个好友吗?");//给对方的一个留言信息
        Map<String,Object> map =new HashMap<>();
        map.put("name", currentUser.getUsername());//发送者姓名，这里只是举个例子，其实可以不需要传发送者的信息过去
        map.put("avatar",currentUser.getAvatar());//发送者的头像
        map.put("uid",currentUser.getObjectId());//发送者的uid
        msg.setExtraMap(map);
        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e) {
                if (e == null) {//发送成功
                    Log.e("A", "好友请求发送成功，等待验证");
                } else {//发送失败
                    Log.e("send Request","发送失败:" + e.getMessage());
                }
            }
        });
    }
}
