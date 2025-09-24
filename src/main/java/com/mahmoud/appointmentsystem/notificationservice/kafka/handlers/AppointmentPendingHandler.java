package com.mahmoud.appointmentsystem.notificationservice.kafka.handlers;

import com.mahmoud.appointmentsystem.notificationservice.constants.AppointmentConstants;
import com.mahmoud.appointmentsystem.notificationservice.kafka.AppointmentEventHandler;
import com.mahmoud.appointmentsystem.notificationservice.kafka.eventsDTO.AppointmentEvent;
import com.mahmoud.appointmentsystem.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentPendingHandler implements AppointmentEventHandler {
   @Autowired
    private EmailService emailService;
    @Override
    public String eventType() {
     return AppointmentConstants.EVENT_APPOINTMENT_PENDING;
    }

    @Override
    public void handleAppointmentEvent (AppointmentEvent event) {

        System.out.println("Handling PENDING event for appointment ID: " + event.getAppointmentId()) ;
        // send email to patient
        emailService.sendEmail(event.getPatientEmail(),
                "Appointment Pending Confirmation",
                "Your appointment with ID " + event.getAppointmentId() + " At " + event.getAppointmentTime() + " is now pending confirmation and will feedback you.");

        // Sending email to doctor to accept or reject the appointment
        // TODO: Implement links generation

        String confirmationLink =  "http://ocalhost:8080/appointments/acceptt?id="
                + event.getDoctorId()+ "appointmentId=" + event.getAppointmentId();
        String rejectionLink =
                "http://ocalhost:8080/appointments/reject?id="
                        + event.getDoctorId()+ "appointmentId=" + event.getAppointmentId();

        emailService.sendEmail(event.getDoctorEmail(),
                "New Appointment Pending Confirmation",
                "You have a new appointment with ID " + event.getAppointmentId() + " At " + event.getAppointmentTime() + " that is pending confirmation."
                        + " Please confirm or reject the appointment.\n"
                        + "Confirm: " + confirmationLink + "\n"
                        + "Reject: " + rejectionLink);


log.info("Handled PENDING event for appointment ID: {}", event.getAppointmentId());

    }
}
