package com.hmxl.yuedemo.bmobMessage;

import android.widget.Toast;
import com.hmxl.yuedemo.bean.NewFriend;
import com.hmxl.yuedemo.bmobMessage.Event.RefreshEvent;
import com.hmxl.yuedemo.tools.exception.MyLog;
import org.greenrobot.eventbus.EventBus;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * Created by Nate on 2017/5/22.
 */

public class DemoMessageHandler extends BmobIMMessageHandler {

    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);
    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
    }

    /**
     * 处理自定义消息类型
     *
     * @param msg
     */
//    private void processCustomMessage(BmobIMMessage msg, BmobIMUserInfo info) {
//        //自行处理自定义消息类型
//        MyLog.i("DemoMessage", msg.getMsgType() + "," + msg.getContent() + "," + msg.getExtra());
//        String type = msg.getMsgType();
//        //发送页面刷新的广播
//        EventBus.getDefault().post(new RefreshEvent());
//        //处理消息
//        if (type.equals("add")) {//接收到的添加好友的请求
//            NewFriend friend = AddFriendMessage.convert(msg);
//            //本地好友请求表做下校验，本地没有的才允许显示通知栏--有可能离线消息会有些重复
//            long id = NewFriendManager.getInstance(context).insertOrUpdateNewFriend(friend);
//            if (id > 0) {
//                showAddNotify(friend);
//            }
//        } else if (type.equals("agree")) {//接收到的对方同意添加自己为好友,此时需要做的事情：1、添加对方为好友，2、显示通知
//            AgreeAddFriendMessage agree = AgreeAddFriendMessage.convert(msg);
//            addFriend(agree.getFromId());//添加消息的发送方为好友
//            //这里应该也需要做下校验--来检测下是否已经同意过该好友请求，我这里省略了
//            showAgreeNotify(info, agree);
//        } else {
//            Toast.makeText(context, "接收到的自定义消息：" + msg.getMsgType() + "," + msg.getContent() + "," + msg.getExtra(), Toast.LENGTH_SHORT).show();
//        }
//    }
}
