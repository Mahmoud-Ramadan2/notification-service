package com.mahmoud.appointmentsystem.notificationservice.kafka;


import com.mahmoud.appointmentsystem.notificationservice.kafka.eventsDTO.AppointmentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentEventConsumer {

    private final AppointmentNotificationService notificationService;
   @KafkaListener(topics = "appointments-topic", groupId = "notification-group")
    public void consume(AppointmentEvent event) {

        notificationService.processEvent(event);
        System.out.println("Message received from topic: " + event.toString());
        log.info("Notification Service received event: {}", event);
    }
}
