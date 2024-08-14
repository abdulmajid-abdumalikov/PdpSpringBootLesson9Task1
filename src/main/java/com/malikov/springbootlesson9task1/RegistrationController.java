package com.malikov.springbootlesson9task1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Registration logic
        sendActivationEmail(user.getEmail(), user.getProfile());
        return ResponseEntity.ok("User registered successfully");
    }

    private void sendActivationEmail(String email, String profile) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Profile Activation");

            Map<String, Object> model = new HashMap<>();
            model.put("profile", profile);
            Template template = freemarkerConfig.getTemplate("activation-email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

