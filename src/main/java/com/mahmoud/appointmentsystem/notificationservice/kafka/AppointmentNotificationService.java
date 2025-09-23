package com.mahmoud.appointmentsystem.notificationservice.kafka;

import com.mahmoud.appointmentsystem.notificationservice.kafka.eventsDTO.AppointmentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentNotificationService {

    List <AppointmentEventHandler> handlers;
   private final Logger logger = LoggerFactory.getLogger(AppointmentNotificationService.class);


    public AppointmentNotificationService(List<AppointmentEventHandler> handlers) {
        this.handlers = handlers;
    }

    public void processEvent(AppointmentEvent event) {
        handlers.stream()
                .filter(handler -> handler.eventType().equalsIgnoreCase(event.getEventType()))
                .findFirst()
                .ifPresentOrElse(
                        handler -> handler.handleAppointmentEvent(event),
                        () -> logger.warn("No handler found for event type: {}", event.getEventType())
                );

    }

    @EventListener
    public void handleContextClosedEvent(ContextClosedEvent event) {
        logger.info("Shutting down AppointmentNotificationService...");
        System.out.println("Shutting down AppointmentNotificationService...");
        // Perform any necessary cleanup here

    }
}
