import React, { useEffect, useState } from "react";
import {
  Container,
  Row,
  Col,
  Spinner,
  ListGroup,
  Button,
} from "react-bootstrap";
import { Link, Outlet, useNavigate } from "react-router-dom";
import "../style.css";
import { getCounterServiceList, getCounterInfo, getAllCounter, deleteCounter } from "../services/CounterAPI";
import { getAllServices, deleteService, getCounterList } from "../services/ServiceTypeAPI";

function DefaultLayout() {
  const [isClicked1, setIsClicked1] = useState(true);
  const [isClicked2, setIsClicked2] = useState(false);

  const handleLink1Click = () => {
    setIsClicked1(true);
    setIsClicked2(false); // Imposta l'altro link su false
  };

  const handleLink2Click = () => {
    setIsClicked2(true);
    setIsClicked1(false); // Imposta l'altro link su false
  };

  const link1Style = {
    textDecoration: "none",
    fontWeight: "bold",
    fontSize: "1.2rem",
    color: "black",
    backgroundColor: isClicked1 ? "whitesmoke" : "white",
  };

  const link2Style = {
    textDecoration: "none",
    fontWeight: "bold",
    fontSize: "1.2rem",
    color: "black",
    backgroundColor: isClicked2 ? "whitesmoke" : "white",
  };

  return (
    <>
      <Container fluid>
        <Row className="vh-100 justify-content-center">
          {/* Sidebar */}
          <Col md={3} style={{ backgroundColor: "white" }}>
            <div style={{ marginTop: "2rem" }}>
              <h2 className="mb-5">Admin Panel</h2>
              <ListGroup defaultActiveKey="#link1">
                <Link to="/" style={link1Style} onClick={handleLink1Click}>
                  {" "}
                  Service Types
                </Link>
                <Link
                  to="/counters"
                  style={link2Style}
                  onClick={handleLink2Click}
                >
                  {" "}
                  Counters{" "}
                </Link>
              </ListGroup>
            </div>
          </Col>

          {/* Contenuto principale */}
          <Col md={9} style={{ backgroundColor: "whitesmoke" }}>
            <div style={{ padding: "2rem" }}>
              <Outlet />
            </div>
          </Col>
        </Row>
      </Container>
    </>
  );
}

function MainLayout() {
  const navigate = useNavigate();

  const [services, setServices] = useState([]);
  const [loading, setLoading] = useState(false);

  /**
   All tested counter API endpoints: 

    getCounterServiceList(1); => Works
    getCounterInfo(1); => Works
    getAllCounter(); => Works
    addNewCounter(2, ["1"], "The counter is located at the main entrance of the building"); => Works
    changeCounterServices(2, []); 
    deleteCounter(2);

  
  All tested service API endpoints: 

    getAllServices(); => Works
    addNewService("Get a new passport", 30, "Create a new passport for the client"); => Works
    changeService("Get a new passport", "Get a new ID card", 30, "Create a new ID card for client"); => Works
    deleteService("Get a new ID card"); => Works
    getCounterList("Get a new ID card"); => Works
   */

  useEffect(() => {
    setLoading(true);
    getAllServices().then((list) => {
      setServices(list);
      setLoading(false);
    });
  }, [services.length]);
  
  const handleDelete = async (id) => {
    setLoading(true);
    await deleteService(id).then(()=>{
      setServices(services.filter((service) => service.tagName !== id));
      setLoading(false);
      navigate("/");
    });
  };

  return (
    <>
      <h2 className="mb-2">Service Overview</h2>
      {loading ? (
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
          <div className="mb-5">
            <Button
              className="createButton"
              onClick={() => {
                navigate(`/services/newService`);
              }}
            >
              {" "}
              Create{" "}
            </Button>
          </div>
          <table>
            <thead>
              <tr>
                <th>Service Name</th>
                <th>Counters</th>
              </tr>
            </thead>
            <tbody>
              {services.map((service) => (
                <tr key={service.serviceId}>
                  <td>{service.tagName}</td>
                  {service.counters !== undefined ? (
                    <td>{service.counters.map((counter)=> counter.number).join(", ")}</td>
                  ) : (
                    <td>{service.counters}</td>
                  ) }
                  <td>
                    <Button
                      className="editButton"
                      onClick={() => {
                        navigate(`/services/${service.serviceId}/edit`, {
                          state: {
                            name: service.tagName,
                            description: service.description,
                          },
                        });
                      }}
                    >
                      Edit
                    </Button>
                  </td>
                  <td>
                    <Button
                      className="deleteButton"
                      onClick={() => {
                        handleDelete(service.tagName);
                      }}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}
    </>
  );
}

function CountersOverview() {
  const navigate = useNavigate();

  const [counters, setCounters] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    getAllCounter().then((list) => {
      setCounters(list);
      setLoading(false);
    });
  }, [counters.length]);

  const handleDelete = async (id) => {
    setLoading(true);
    await deleteCounter(id).then(()=>{  
      setCounters(counters.filter((counter) => counter.number !== id));
      setLoading(false);
      navigate("/counters");
    });
  };

  return (
    <>
      <h2 className="mb-2">Counter Overview</h2>
      {loading ? (
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
          <div className="mb-5">
          <Button
              className="createButton"
              onClick={() => {
                navigate(`/counters/newCounter`);
              }}
            >
              {" "}
              Create{" "}
            </Button>
          </div>
          <table>
            <thead>
              <tr>
                <th>Counter Number</th>
                <th>Services</th>
              </tr>
            </thead>
            <tbody>
              {counters.map((counter) => (
                <tr key={counter.number}>
                  <td>{counter.number}</td>
                  {counter.listOfServices !== undefined ? (
                    <td>{counter.listOfServices.map(service => service.tagName).join(", ")}</td>
                  ) : (
                    <td>"[]"</td>
                  ) }
                  <td>
                  <Button
                      className="editButton"
                      onClick={() => {
                        navigate(`/counters/${counter.number}/edit`, {
                          state: {
                            number: counter.number,
                            description: counter.description,
                            listOfServices: counter.listOfServices,
                          },
                        });
                      }}
                    >
                      Edit
                    </Button>
                  </td>
                  <td>
                    <Button
                      className="deleteButton"
                      onClick={() => {
                        handleDelete(counter.number);
                      }}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}
    </>
  );
}

export { DefaultLayout, MainLayout, CountersOverview };
