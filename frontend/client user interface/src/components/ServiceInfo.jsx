import React, { useState, useEffect } from 'react';
import TicketAPI from "../services/TicketAPI.js";

const ServiceInfo = ({ selectedService }) => {
  const [ticketNum, setTicketNum] = useState(null);

  useEffect(() => {
    if (selectedService) {
      TicketAPI.createTicket(selectedService)
          .then((response) => {
            setTicketNum(response)})
          .catch((error) => {
              console.error('Error fetching counter data:', error);
              setTicketNum(null);
          });
    } else {
      // Handle the case where no service is selected
      setTicketNum(null);
    }
  }, [selectedService]);

  return (
    <div className="service-info">
      {selectedService ? (
        ticketNum !== null ? (
          <p>
            You have selected the service: {selectedService} (Ticket Number: {ticketNum})
          </p>
        ) : (
          <p>
            You have selected the service: {selectedService} (Ticket Number not available)
          </p>
        )
      ) : (
        <p>Please select a service.</p>
      )}
    </div>
  );
};

export default ServiceInfo;
