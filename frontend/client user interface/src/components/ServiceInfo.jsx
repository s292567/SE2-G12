import React, { useState, useEffect } from 'react';

const ServiceInfo = ({ selectedService }) => {
  const [counter, setCounter] = useState(null);

  useEffect(() => {
    if (selectedService) {
      fetch(`http://localhost:3001/counters/${selectedService}`)
        .then((response) => response.json())
        .then((data) => {
          if (selectedService in data) {
            // Access the counter value from the data object
            setCounter(data[selectedService].counter);
          } else {
            // Handle the case where the selected service doesn't have a counter
            setCounter(null);
          }
        })
        .catch((error) => {
          console.error('Error fetching counter data:', error);
          setCounter(null);
        });
    } else {
      // Handle the case where no service is selected
      setCounter(null);
    }
  }, [selectedService]);

  return (
    <div className="service-info">
      {selectedService ? (
        counter !== null ? (
          <p>
            You have selected the service: {selectedService} (Counter {counter})
          </p>
        ) : (
          <p>
            You have selected the service: {selectedService} (Counter information not available)
          </p>
        )
      ) : (
        <p>Please select a service.</p>
      )}
    </div>
  );
};

export default ServiceInfo;
