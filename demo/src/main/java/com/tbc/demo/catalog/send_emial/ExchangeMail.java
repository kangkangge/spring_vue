package com.tbc.demo.catalog.send_emial;

import microsoft.exchange.webservices.data.*;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author superman
 */

public class ExchangeMail {

	private static String username = "###";//账户，这里只有账号没有域名
	private static String password = "###";//密码
	private static String demand = "shax7hcdc101.apac.dell.com";//服务器地址。
	private static ExchangeService service;

	/**
	 * 初始化连接
	 */
	public static void init() { 
		/**
		 * 把InstallCert生成class文件
		 * 在CMD命令中输入【java InstallCert 服务器地址】生成jssecacerts文件
		 * 你可以跟我这个一样或者拷贝到..\jdk\jre\lib\security下
		 * */
		System.setProperty("javax.net.ssl.trustStore",
				"C:\\jssecacerts");//导入证书。通过InstallCert.java文件生成证书.
		service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
		ExchangeCredentials credentials = new WebCredentials(username,
				password, "#####");//用户名，密码，域名
		service.setCredentials(credentials);
		try {
			service.setUrl(new URI(
					"https://"+demand+"/ews/Exchange.asmx"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用Exchange协议发送
	 * 
	 * @param subject
	 *            邮件主题
	 * @param to
	 *            收件人
	 * @param cc
	 *            抄送
	 * @param bodyText
	 *            正文
	 * @param realPath
	 *            附件
	 * 
	 * @throws Exception
	 */
	public static void doSend(String subject, List<String> to, List<String> cc,
			String bodyText, String realPath) throws Exception {

		EmailMessage msg = new EmailMessage(service);
		msg.setSubject(subject);
		MessageBody body = MessageBody.getMessageBodyFromText(bodyText);
		body.setBodyType(BodyType.HTML);
		msg.setBody(body);
		for (String s : to) {
			msg.getToRecipients().add(s);
		}
		for (String s : cc) {
			msg.getCcRecipients().add(s);
		}
		if (realPath != null && !"".equals(realPath)) {
			msg.getAttachments().addFileAttachment(realPath);
		}
		msg.send();
	}

	public static void send(String subject, List<String> to, List<String> cc,
			String bodyText) throws Exception {
		doSend(subject, to, cc, bodyText, null);
	}

	/**
	 * 使用Exchange协议接收邮件
	 * 
	 * @throws Exception
	 */

	public static void GetUnreadEmails() throws Exception {
		SearchFilter sf = new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead,
				false); // 使用拦位搜寻
		// 查找Inbox,加入过滤器条件,结果10条 未读邮件
		FindItemsResults<Item> findResults = service.findItems(
				WellKnownFolderName.Inbox, sf, new ItemView(10));
		int unRead = Folder.bind(service, WellKnownFolderName.Inbox)
				.getUnreadCount();//查询有多少邮件未读
		for (Item item : findResults) {
			EmailMessage email = EmailMessage.bind(service, item.getId());
			if (!email.getIsRead()) {
				//发件人和抄送人
				List<String> to = new ArrayList<String>();
				List<String> cc = new ArrayList<String>();
				to.add(email.getFrom().getAddress());
				for (EmailAddress e : email.getCcRecipients()) {
					cc.add(e.getAddress());
					System.out.println(e.getAddress());
				}
				email.load();
						//下载附件
				for (Attachment it : email.getAttachments()) {
					FileAttachment iAttachment = (FileAttachment) it;
					// 下载附件Attachments FileAttachment
					iAttachment.load( "C:\\" + it.getName());
				}
				System.out.println(email.getSubject());//标题
				System.out.println(email.getBody());//内容
				// 标记为已读
				email.setIsRead(true);
				// 将对邮件的改动提交到服务器
				try {
					email.update(ConflictResolutionMode.NeverOverwrite);//我这里邮件虽然修改成功但是抱错，直接忽视。
				} catch (Exception e) {
					System.out.println("---");
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		List<String> to = new ArrayList<String>();
		List<String> cc = new ArrayList<String>();
		to.add("####@####.com");//收件人
		cc.add("####@####.com");//抄送人
		send("test",to,cc,"hello");
		GetUnreadEmails();
	
	}
}
