package com.macky.fileShareSystem.common.mail;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/21 21:51
 */
public class MailUtilBy587 {

    public static void sendMail(String address) throws Exception {

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect("smtp.qq.com", "973151766", "fjmginxvbyinbbgf");
        //4、创建邮件
        Message message = createSimpleMail(address, session);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     *
     * @param session
     * @return
     * @throws Exception
     */
    public static MimeMessage createSimpleMail(String address, Session session)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("973151766@qq.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        //邮件的标题
        message.setSubject("听闻您忘记了您的文件管家的密码");
        //邮件的文本内容
        message.setContent("不用担心您的密码，现在点击下面的链接重置您的密码!<br />▼链接：<br /><a href='http://www.ifmm.vip/file/remake.do'>http://www.ifmm.vip/file/remake.do</a>", "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }

    public static void main(String[] args) throws Exception {
        String mail = "973151766@qq.com";
//        System.out.println(mail.substring(mail.lastIndexOf('@') + 1, mail.lastIndexOf('.')));
        MailUtilBy587.sendMail(mail);
    }
}
