package com.rms.util;

import org.springframework.mail.javamail.JavaMailSender;

import com.rms.entity.Order;
import com.rms.entity.OrderItem;

public class OrderApprovedMailUtil {

	private OrderApprovedMailUtil() {}
	
	public static void orderConfirmation(JavaMailSender javaMailSender,Order order) {
		
		String subject= "Your Order Confirmation Details";
		
		if(order.getStatus().equals("Approved")) {
		
		
		String message="Hi "+order.getCustomer().getName()+","+"\n\n"+"Your Order Details\n\n";
		
		message+="Food Item "+" Quantity "+" Price\n";
		for(OrderItem orderItem:order.getOrderItem()) {
			message+=orderItem.getProduct().getName()+"            "+orderItem.getQuantity()+"      "+orderItem.getPrice()+"\n";
		}
		message+="Your Order has been "+order.getStatus()+"\nTotal Amount= "+order.getTotalPrice()+"\n Thank you.";
		
		MailSenderUtil.sendMail(javaMailSender,order.getCustomer().getEmail(), subject, message);
		
		}
		else {
			String message="Hi "+order.getCustomer().getName()+"\n\n";
			message+="Your Order has been "+order.getStatus()+",Your Payment will be returned within 7 working days.\n"+"Thank You.";
			MailSenderUtil.sendMail(javaMailSender,order.getCustomer().getEmail(), subject, message);
			
		}
		
	}
	
}
