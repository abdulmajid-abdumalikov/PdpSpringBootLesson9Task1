package com.malikov.springbootlesson9task1;

import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskScheduler {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Scheduled(cron = "0 0 7,19 * * *")
    public void sendUncompletedTasks() {
        List<Task> tasks = taskRepository.findUncompletedTasks();
        tasks.forEach(this::sendTaskReminderEmail);
    }

    private void sendTaskReminderEmail(Task task) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(task.getAssignedUser().getEmail());
            helper.setSubject("Reminder: Uncompleted Task");

            Map<String, Object> model = new HashMap<>();
            model.put("task", task);
            Template template = freemarkerConfig.getTemplate("task-reminder.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

