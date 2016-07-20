package spring;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailingSender {
	Properties props = null;
	Authenticator auth = null;
	Session session = null;
	MimeMessage message = null;
	Multipart mp = null;
	MimeBodyPart mbp1 = null;
	MailcapCommandMap mc = null;

	private MailingSender() {
		props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("jdh7941@gmail.com", "yfezsafrybrbmkxy");
			}
		};

		session = Session.getDefaultInstance(props, auth);

		message = new MimeMessage(session);
		mp = new MimeMultipart();
		mbp1 = new MimeBodyPart();

		mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);
	}

	private static MailingSender instance = new MailingSender();

	public static MailingSender getInstance() {
		return instance;
	}

	/** String sender = �������� ���ͳ� ��巹��
	 *  String receiver = �޴����� �̸��� ��巹��
	 *  String subject = ����
	 *  String content = �̸��� ����
	 * */
	public MailingSender initPost(String sender, String receiver, String subject, String content) throws AddressException, MessagingException {
		mp.addBodyPart(mbp1);
		message.setSender(new InternetAddress(sender));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
		message.setContent(mp);
		message.setSubject(subject);
		mbp1.setText(content);

		return instance;
	}

	/** �̸��� ����
	 * */
	public void send() throws MessagingException {
		Transport.send(message);
		mp.removeBodyPart(0);
	}
}
