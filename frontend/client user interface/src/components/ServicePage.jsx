// src/ServicePage.js
import React, { useState, useEffect } from 'react';
import './ServicePage.css';
import './ServiceInfo.css';
import ServiceInfo from './ServiceInfo';

const ServicePage = () => {
  // State to store the selected service
  const [selectedService, setSelectedService] = useState('');
  const [services, setServices] = useState([]);

  useEffect(() => {
    // Fetch services from an API endpoint when the component mounts
    fetch('http://localhost:3001/services')
      .then((response) => response.json())
      .then((data) => setServices(data));
  }, []);

  const handleServiceChange = (event) => {
    setSelectedService(event.target.value);
  };

  const handleSubmit = () => {
    // Handle submission, e.g., send selectedService to the server
    console.log('Selected service: ', selectedService);
  };

  return (
    <div className="service-page">
      <p>Welcome please choose the service you require:</p>
      <select value={selectedService} onChange={handleServiceChange}>
        <option value="">Select a service</option>
        {services.map((service) => (
          <option key={service.id} value={service.id}>
            {service.name}
          </option>
        ))}
      </select>

      <ServiceInfo selectedService={selectedService} />
    </div>
  );
};

export default ServicePage;
