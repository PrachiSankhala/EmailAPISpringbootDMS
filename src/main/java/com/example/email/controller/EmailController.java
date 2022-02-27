package com.example.email.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.model.EmailRequest;
import com.example.email.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@RequestMapping("/welcome")
	public String welcome() {
		return "Hello this is my email api";
	}

	//@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	@PostMapping("/sendmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) throws MessagingException {

		System.out.println(request);
		boolean result;
		result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
		if (result) {
			return ResponseEntity.ok("Email sent successfully....");
		} else {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not send...");

		}

	}
	
	@RequestMapping("/sendEmailToFor/{email}/{code}")
	public List<String> sendEmail(@PathVariable String email, @PathVariable int code) throws MessagingException {

		// System.out.println(request);

		String subject = emailService.getSubject(code);

		String message = emailService.getMessage(code);

		boolean result;
		result = this.emailService.sendEmail(subject, message, email);
		if (result) {
			return new ArrayList<>();
		} else {
			return new ArrayList<>();

		}
	}

}
