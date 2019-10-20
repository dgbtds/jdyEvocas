package cn.ihep.jdy.release.Util.JPush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.*;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class JPushConfig {

    private static final Logger logger = LoggerFactory.getLogger(JPushConfig.class);
    //private static final String MASTER_SECRET="b1dd60fc7b5fd76985d23299";
    //private static final String APP_KEY="1fd347c6837727e5c46b1c70";
    private static final String MASTER_SECRET="408cec58a8f855f250b81fe2";//JPush服务器端下发的master_key
    private static final String APP_KEY="fdef37f47313298c09f12c9b";//JPush服务器端下发的app_key

    /**
     * 构建推送对象：对所有平台，所有设备，内容为 alert的通知
     * @param alter
     * @return
     */
    public static PushPayload buildPushObject_all_all_alert(String alter) {
        return PushPayload.alertAll(alter);
    }
    /**
     * 所有平台，推送目标是别名为 "alias"，通知内容为 alert
     * @param alias
     * @param alert
     * @return
     */
    public static PushPayload buildPushObject_all_alias_alert(String alias,Object alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .build();
    }
    /**
     * 构建推送对象：平台是 Android，目标是 tag的设备，通知内容是alert，并且标题为title。
     * @param tag
     * @param alert
     * @param title
     * @param extras
     * @return
     */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String tag,String alert,String title,Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(tag))
                .setNotification(Notification.android(alert, title, extras))
                .build();
    }
    /**
     * 构建推送对象：平台是 iOS，推送目标是 tags(可以是一个设备也可以是多个设备)，推送内容同时包括通知与消息 - 通知信息是alert，消息内容是 msgContent，角标数字为badge(应用程序左上角或者右上角的数字)，通知声音为sound，并且附加字段 from = "JPush"。
     * 通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。
     * APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
     * @param alert
     * @param msgContent
     * @param badge
     * @param sound
     * @param tags
     * @return
     */
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(Object alert,String msgContent,int badge,String sound,String...tags) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and(tags))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(badge)
                                .setSound(sound)
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(msgContent))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }
    /**
     * 构建推送对象：平台是 Andorid 与 iOS，推送的设备有（推送目标为tags和推送目标别名为aliases），推送内容是 - 内容为 msg_content的消息，并且附加字段 from = JPush。
     * @param msg_content
     * @param aliases
     * @return
     */
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String msg_content,String aliases) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
//                        .addAudienceTarget(AudienceTarget.tag(tags))
                        .addAudienceTarget(AudienceTarget.alias(aliases))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    /**
     * 构建推送对象：推送内容包含SMS信息
     * @param title
     * @param sendSMSContent
     * @param delayTime
     * @param aliases
     */
    public static void testSendWithSMS(String title,String sendSMSContent,int delayTime,String... aliases) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            SMS sms = SMS.content(sendSMSContent, delayTime);
            PushResult result = jpushClient.sendAndroidMessageWithAlias(title, sendSMSContent, sms, aliases);
            System.out.println("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.info("Connection error. Should retry later. "+e);
        } catch (APIRequestException e) {
            logger.info("Error response from JPush server. Should review and fix it. "+e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
    }

    public void send(String msg,String aliases){
        String master_secret=JPushConfig.MASTER_SECRET;
        String app_key=JPushConfig.APP_KEY;
        JPushClient jpushClient = new JPushClient(master_secret,app_key, null, ClientConfig.getInstance());
//        PushPayload payload = JPushConfig.buildPushObject_ios_audienceMore_messageWithExtras(msg,new String[1], new String[1]);
//        PushPayload payload = JPushConfig.buildPushObject_all_all_alert(msg);
        PushPayload payload = JPushConfig.buildPushObject_ios_audienceMore_messageWithExtras(msg,aliases);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.info("Connection error, should retry later "+e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.info("根据返回的错误信息核查请求是否正确"+e);
            logger.info("HTTP 状态信息码: " + e.getStatus());
            logger.info("JPush返回的错误码: " + e.getErrorCode());
            logger.info("JPush返回的错误信息: " + e.getErrorMessage());
        }
    }


    public static void main(String[] args) {
        JPushConfig jPushConfig = new JPushConfig();
        Map<String,Object> map = new HashMap<>();
        map.put("customerName","zjy");
        map.put("mobile","15611530236");
        map.put("visitNum","2");
        map.put("counselorName","wdh");
        map.put("userId","9");
        map.put("customerId","2");
        System.out.println(JSONObject.toJSONString(map));
        jPushConfig.send(JSONObject.toJSONString(map),"6");


//        String master_secret=JPushConfig.MASTER_SECRET;
//        String app_key=JPushConfig.APP_KEY;
//        JPushClient jpushClient = new JPushClient(master_secret,app_key, null, ClientConfig.getInstance());
//        //PushPayload payload = PushConfig.buildPushObject_all_all_alert("消息推送");
//        //PushPayload payload=PushConfig.buildPushObject_android_tag_alertWithTitle("tag1", "123", "123", null);
//        PushPayload payload = JPushConfig.buildPushObject_all_all_alert("测试");
//
//        try {
//            PushResult result = jpushClient.sendPush(payload);
//            System.out.println("Got result - " + result);
//
//        } catch (APIConnectionException e) {
//            // Connection error, should retry later
//            System.out.print("Connection error, should retry later "+e);
//
//        } catch (APIRequestException e) {
//            // Should review the error, and fix the request
//            System.out.println("根据返回的错误信息核查请求是否正确"+e);
//            System.out.println("HTTP 状态信息码: " + e.getStatus());
//            System.out.println("JPush返回的错误码: " + e.getErrorCode());
//            System.out.println("JPush返回的错误信息: " + e.getErrorMessage());
//        }
    }

}
