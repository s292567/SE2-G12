import { useEffect, useState, useContext } from "react";
import { Form, Button, Container, FormLabel, Spinner } from "react-bootstrap";
import { useNavigate, useLocation } from "react-router-dom";

import {addNewService, changeService } from "../services/ServiceTypeAPI";

function ServiceTypeForm() {
  // addNewService("Get a new passport", 30, "Create a new passport for the client"); => Works
  // changeService("Get a new passport", "Get a new ID card", 30, "Create a new ID card for client"); => Works

  const navigate = useNavigate();
  const location = useLocation();

  let serviceType = undefined;
  if (location.state) {
    serviceType = { ...location.state };
  }

  const [name, setName] = useState(serviceType ? serviceType.name : " ");
  const [description, setDescription] = useState(
    serviceType ? serviceType.description : " "
  );
  const [waiting, setWaiting] = useState(false);

  const buttonStyle = {
    backgroundColor: serviceType ? "lightblue" : "#8FEB98",
    color: "black",
    borderRadius: "1rem",
    borderColor: "transparent",
    padding: "0.5rem 1rem",
  };

  //TO DO: add the API call to create/update the service type (with all the considerations about the time needed to do it)
  const handleSubmit = async (event) => {
    event.preventDefault();
    setWaiting(true);
    if (serviceType) {
      await changeService(serviceType.name, name,30, description).then(() => {
        setWaiting(false);
        navigate("/");
      });
    } else {
      await addNewService(name, 30, description).then(() => {
        setWaiting(false);
        navigate("/");
      });
    }
  };

  return (
    <>
      <Container>
        {waiting ? (
          <div className="d-flex justify-content-center align-items-center">
            <Spinner
              as="span"
              animation="border"
              role="status"
              className="me-2"
            />
            <b>Loading...</b>
          </div>
        ) : (
          <>
            <h2 className="mb-5 mt-4">Service Type</h2>
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="name" className="mb-3">
                <Form.Label>Service Name: </Form.Label>
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
                  style={{
                    height: "100px",
                    width: "50%",
                    borderColor: "black",
                  }}
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                />
              </Form.Group>
              <Button
                style={buttonStyle}
                onClick={(event) => handleSubmit(event)}
              >
                {serviceType ? "Update" : "Create"}
              </Button>
            </Form>
          </>
        )}
      </Container>
    </>
  );
}

export { ServiceTypeForm };
