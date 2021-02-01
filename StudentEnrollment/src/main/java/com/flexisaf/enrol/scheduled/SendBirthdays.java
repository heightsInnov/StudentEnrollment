package com.flexisaf.enrol.scheduled;

import com.flexisaf.enrol.model.Student;
import com.flexisaf.enrol.repository.StudentEnrollmentRepository;
import com.flexisaf.enrol.service.SendBirthdayMail;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SendBirthdays {

    private static final String CRON_TIMER = "0 0 0 * * *";
    private final StudentEnrollmentRepository repo;
    private final SendBirthdayMail sendEMail;

    public SendBirthdays(StudentEnrollmentRepository repo, SendBirthdayMail sendEMail) {
        this.repo = repo;
        this.sendEMail = sendEMail;
    }

    @Scheduled(cron = CRON_TIMER)
    public void sendBirthdayMessage() {
        try {
            @SuppressWarnings("unchecked")
            List<Student> birthdays = repo.findAll().stream().filter(x -> x.getDateofbirth().equals(LocalDate.now())).collect(Collectors.toList());
            List<String> recipients = new ArrayList<>();
            if (!birthdays.isEmpty()) {
                birthdays.forEach(x -> {
                    if (!x.getStudentemail().isEmpty())
                        recipients.add(x.getStudentemail());
                });
            }
            sendEMail.sendEMail(recipients);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
