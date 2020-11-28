package com.lcq.pet.server.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.json.JSONObject;

import javax.swing.text.html.FormSubmitEvent;
import java.util.Objects;

/**
 * @program: Health_Parent
 * @description:
 * @author:
 * @create: 2020-11-26 15:41
 */
public class AliSmsUtil {
    /**
     * 发送注册验证码
     * @param phone 手机号
     * @param code 验证码*/
    public static boolean sendSmsCode(String phone,int code){
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
//                "LTAI4GJ8sHLwCeZpsaXymiPv", "gzEohcUWuRcFnf7b27nfbUPTZzL74h");
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2017-05-26");
//        request.setSysAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", phone);
//        request.putQueryParameter("SignName", "乐宠圈");
//        request.putQueryParameter("TemplateCode", "SMS_205880654");
//        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAI4GHztNKmQ7PzYbeoRp7U", "FtbW3hdYQKJeMFTIEzZ83jvKAXZMPc");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "爱萌宠");
        request.putQueryParameter("TemplateCode", "SMS_205880654");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            if(response.getHttpStatus()==200){
                JSONObject jsonObject=new JSONObject(response.getData());
                return Objects.equals(jsonObject.getString("Code"),"OK");
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
