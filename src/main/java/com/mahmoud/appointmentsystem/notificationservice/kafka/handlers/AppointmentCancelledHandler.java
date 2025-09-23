package com.mahmoud.appointmentsystem.notificationservice.kafka.handlers;

import com.mahmoud.appointmentsystem.notificationservice.kafka.AppointmentEventHandler;
import com.mahmoud.appointmentsystem.notificationservice.kafka.eventsDTO.AppointmentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppointmentCancelledHandler implements AppointmentEventHandler {
    @Override
    public String eventType() {
        return "APPOINTMENT_CANCELLED";
    }

    @Override
    public void handleAppointmentEvent(AppointmentEvent event) {
        log.info("Appointment cancelled  {}", event.toString());

    }
}
