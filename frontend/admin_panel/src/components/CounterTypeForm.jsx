import { useEffect, useState, useContext } from "react";
import { Form, Button, Container, FormLabel } from "react-bootstrap";
import { useNavigate, useLocation } from "react-router-dom";

import "../style.css";

function CounterTypeForm() {
  const navigate = useNavigate();
  const location = useLocation();

  let serviceType = undefined;
  if (location.state) {
    serviceType = { ...location.state };
  }

  const services_type = ["Service A", "Service B", "Service C", "Service D"]; // La tua lista di stringhe
  const [selectedServices, setSelectedServices] = useState([]);

  const [name, setName] = useState(serviceType ? serviceType.name : " ");
  const [description, setDescription] = useState(
    serviceType ? serviceType.description : " "
  );

  const buttonStyle = {
    backgroundColor: serviceType ? "lightblue" : "#8FEB98",
    color: "black",
    borderRadius: "1rem",
    borderColor: "transparent",
    padding: "0.5rem 1rem",
  };

  const handleCheckboxChange = (e) => {
    const { value, checked } = e.target;
    if (checked) {
      setSelectedServices([...selectedServices, value]);
    } else {
      setSelectedServices(
        selectedServices.filter((service) => service !== value)
      );
    }
  };

  //TO DO: add the API call to create/update the counter (with all the considerations about the time needed to do it)
  
  return (
    <>
      <Container>
        <h2 className="mb-5 mt-4">Counter </h2>
        <Form>
          <Form.Group controlId="name" className="mb-3">
            <Form.Label>Counter Name: </Form.Label>
            <Form.Control
              as="textarea"
              placeholder="Enter name"
              style={{ height: "25px", width: "40%", borderColor: "black" }}
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="description" className="mb-4">
            <Form.Label>Description: </Form.Label>
            <Form.Control
              as="textarea"
              placeholder="Enter description"
              style={{ height: "100px", width: "50%", borderColor: "black" }}
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="type" className="mb-4">
            <FormLabel><h5>Services Type:</h5> </FormLabel>
            {services_type.map((service, index) => (
              <Form.Check
                key={index}
                type="checkbox"
                label={service}
                id={`service-${index}`}
                value={service}
                checked={selectedServices.includes(service)}
                onChange={handleCheckboxChange}
                className="customCheckbox"
              />
            ))}
          </Form.Group>

          <Button
            style={buttonStyle}
            onClick={() => {
              navigate("/counters");
            }}
          >
            {serviceType ? "Update" : "Create"}
          </Button>
        </Form>
      </Container>
    </>
  );
}

export { CounterTypeForm };
