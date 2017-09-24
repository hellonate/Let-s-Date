package com.hmxl.yuedemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hmxl.yuedemo.R;
import com.hmxl.yuedemo.adapter.MyUnknowFriendAdapter;
import com.hmxl.yuedemo.bean.Friend;
import com.hmxl.yuedemo.bean.User;
import com.hmxl.yuedemo.bmobMessage.AddFriendMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class SearchfriendActivity extends AppCompatActivity {
    String username ;
    private EditText et_find_name;
    private Button btn_search;
    private ListView lv_searchfriend;
    MyUnknowFriendAdapter adapter ;
    private ArrayList<BmobObject> objects = new ArrayList<>();
    private ArrayList<Boolean> isFriendList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfriend);
        et_find_name = (EditText) findViewById(R.id.et_find_name);

        btn_search = (Button) findViewById(R.id.btn_search);
        lv_searchfriend = (ListView) findViewById(R.id.lv_searchfriend);
        adapter = new MyUnknowFriendAdapter(getBaseContext());
        lv_searchfriend.setAdapter(adapter);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.clear();
                isFriendList.clear();
                //查询用户
                //System.out.println("aaa");
                username = et_find_name.getText().toString().trim();
                // 判断username是否已经是你的好友，如果不是则添加

                BmobQuery<Friend> query = new BmobQuery<>();
                query.addWhereEqualTo("user",BmobUser.getCurrentUser());
                query.include("friendUser");
                query.findObjects(new FindListener<Friend>() {
                    @Override
                    public void done(List<Friend> list, BmobException e) {
                        if(e==null){
                            for(Friend f:list){
                                //System.out.println("id:"+f.getFriendUser().getObjectId());
                                if (f.getFriendUser().getUsername().equals(username)) {
                                    // 假如是你的好友，则按钮不可点击
                                    isFriendList.add(true);
                                    objects.add(f);
                                    adapter.addAllData(objects, isFriendList);
                                    adapter.notifyDataSetChanged();
                                    return;
                                }
                            }
                            BmobQuery<User> bquery = new BmobQuery<User>();
                            bquery.addWhereEqualTo("username",username);
                            bquery.setLimit(50);
                            bquery.findObjects(new FindListener<User>() {
                                @Override
                                public void done(List<User> list, BmobException e) {
                                    // 查询成功,
                                    System.out.println("userList size:"+list.size());
                                    if(list.size()>0){
                                        if (list.get(0).getUsername().equals(username)) {
                                            // 不是你的好友，则按钮可点击
                                            isFriendList.add(false);
                                            objects.add(list.get(0));
                                            adapter.addAllData(objects, isFriendList);
                                            adapter.notifyDataSetChanged();
                                        }
                                    }else{
                                        isFriendList.clear();
                                        objects.clear();
                                        adapter.addAllData(objects,isFriendList);
                                        adapter.notifyDataSetChanged();
                                        Toast.makeText(getBaseContext(),
                                                "该用户不存在",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });


            }
        });





    }
        //TODO
//    private void sendAddFriendMessage(){
//        //启动一个暂态会话，也就是isTransient为true,表明该会话仅执行发送消息的操作，不会保存会话和消息到本地数据库中，
//        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, true,null);
//        //这个obtain方法才是真正创建一个管理消息发送的会话
//        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), c);
//        //新建一个添加好友的自定义消息实体
//        AddFriendMessage msg =new AddFriendMessage();
//        User currentUser = BmobUser.getCurrentUser(User.class);
//        msg.setContent("很高兴认识你，可以加个好友吗?");//给对方的一个留言信息
//        Map<String,Object> map =new HashMap<>();
//        map.put("name", currentUser.getUsername());//发送者姓名，这里只是举个例子，其实可以不需要传发送者的信息过去
//        map.put("avatar",currentUser.getAvatar());//发送者的头像
//        map.put("uid",currentUser.getObjectId());//发送者的uid
//        msg.setExtraMap(map);
//        conversation.sendMessage(msg, new MessageSendListener() {
//            @Override
//            public void done(BmobIMMessage msg, BmobException e) {
//                if (e == null) {//发送成功
//                    toast("好友请求发送成功，等待验证");
//                } else {//发送失败
//                    toast("发送失败:" + e.getMessage());
//                }
//            }
//        });
//    }
}
