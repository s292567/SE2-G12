import { useEffect, useState, useContext } from "react";
import { Form, Button, Container, FormLabel, Spinner } from "react-bootstrap";
import { useNavigate, useLocation } from "react-router-dom";

import { addNewCounter, changeCounterServices, getAllCounter, } from "../services/CounterAPI";
import { getAllServices, } from "../services/ServiceTypeAPI";

import "../style.css";

function CounterTypeForm() {
  
  // addNewCounter(2, ["1"], "The counter is located at the main entrance of the building"); => Works
  // changeCounterServices(2, []); 

  const navigate = useNavigate();
  const location = useLocation();

  let counterType = undefined;
  if (location.state) {
    counterType = { ...location.state };
  }

  const [waiting, setWaiting] = useState(false);

  const [services_type, setServices_type] = useState([]);
  
  const [number, setNumber] = useState([]) ;

  useEffect(() => {
    getAllServices().then((list) => {
      setServices_type(list.map((service) => service.tagName));
    });
    getAllCounter().then((list) => {
      setNumber(list.length + 1);
    });
  }, []);
  
  const [selectedServices, setSelectedServices] = useState(counterType ? counterType.listOfServices.map((service) => service.tagName) : []);

  
  const [description, setDescription] = useState(
    counterType ? counterType.description : " "
  );

  const buttonStyle = {
    backgroundColor: counterType ? "lightblue" : "#8FEB98",
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

  const handleSubmit = async (event) => {
    event.preventDefault();
    setWaiting(true);
    if (counterType) {
        await changeCounterServices(counterType.number, selectedServices).then(() => {
          setWaiting(false);
          navigate("/counters");
        });
      
    } else {
      await addNewCounter(number, selectedServices, description).then(async () => {
        await changeCounterServices(number, selectedServices).then(() => {
          setWaiting(false);
          navigate("/counters");
        });
      });
    }
  };

  //TO DO: add the API call to create/update the counter (with all the considerations about the time needed to do it)
  
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
        <h2 className="mb-5 mt-4">Counter </h2>
        <Form onSubmit={handleSubmit}>
            { counterType ? <> <h2>Counter Number:</h2><h5 className="mb-4">{counterType.number}</h5></> : <></>}
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
            onClick={(event) => {
              handleSubmit(event);
            }}
          >
            {counterType ? "Update" : "Create"}
          </Button>
        </Form>
      </>
        )}
      </Container>
    </>
  );
}

export { CounterTypeForm };
