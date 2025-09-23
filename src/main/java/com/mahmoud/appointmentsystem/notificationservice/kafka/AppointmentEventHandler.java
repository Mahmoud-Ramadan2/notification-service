package com.mahmoud.appointmentsystem.notificationservice.kafka;


import com.mahmoud.appointmentsystem.notificationservice.kafka.eventsDTO.AppointmentEvent;

public interface AppointmentEventHandler {

    String eventType();

    void handleAppointmentEvent(AppointmentEvent event);
}
