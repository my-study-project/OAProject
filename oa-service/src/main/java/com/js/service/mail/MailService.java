package com.js.service.mail;

import com.js.common.exception.SystemException;
import com.js.config.OaSysConfig;
import com.js.service.RedisService;
import com.js.vo.system.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * @Author: 姜爽
 * @Description: 邮件相关服务
 * @Date: 2020/5/19 6:59
 */
@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OaSysConfig oaSysConfig;

    public String sendCodeMail(SysUserVo sysUserVo, String methodCode) {
        //生成短信验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head><title></title></head><body>");
        stringBuilder.append("您好<br/>");
        stringBuilder.append("您的验证码是：").append(verifyCode).append("<br/>");
        stringBuilder.append("您可以复制此验证码并返回至密码设置界面，以验证您的邮箱。<br/>");
        stringBuilder.append("此验证码只能使用一次，在5分钟内有效。验证成功则自动失效。<br/>");
        stringBuilder.append("如果您没有进行上述操作，请忽略此邮件。");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        //发送验证码到手机或者是邮箱
        if ("1".equals(methodCode)){
            //邮箱
            try{
                //将验证码和过期时间更新到数redis据库
                redisService.rePassCode("oa"+sysUserVo.getEmail(),verifyCode);
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
                //这里只是设置username 并没有设置host和password，因为host和password在springboot启动创建JavaMailSender实例的时候已经读取了
                mimeMessageHelper.setFrom(oaSysConfig.getSender());
                mimeMessageHelper.setTo(sysUserVo.getEmail());
                mimeMessage.setSubject("黑大OA密码重置");
                mimeMessageHelper.setText(stringBuilder.toString(),true);
                javaMailSender.send(mimeMessage);
            }catch (Exception e){
                log.info("验证码发送失败{}",e);
                throw new SystemException("验证码发送失败，请重试");
            }

        }else if ("0".equals(methodCode)){
            //手机验证，需要注册短信通等暂时未开发

        }
        return verifyCode;
    }
}
