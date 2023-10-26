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
import { getCounterServiceList, getCounterInfo, getAllCounter, addNewCounter, changeCounterServices, deleteCounter } from "../services/CounterAPI";
import { getAllServices, addNewService, changeService, deleteService, getCounterList } from "../services/ServiceTypeAPI";

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

    
  /**
  TO DO:
  from the API retrieve the counters list and set it to the counters state
  useEffect(() => {
    setLoading(true);
    getContents().then((list) => {
      setCounters(list);
      setLoading(false);
    });
  }, [counters.length]);
  */

  // delete when the API will be ready
  useEffect(() => {
    setServices([
      {
        id: 1,
        serviceName: "Servizio A",
        counters: [1, 3, 5],
        description: "Servizio A",
      },
      {
        id: 2,
        serviceName: "Servizio B",
        counters: [2, 4],
        description: "Servizio B",
      },
      {
        id: 3,
        serviceName: "Servizio C",
        counters: [1, 5],
        description: "Servizio C",
      },
      {
        id: 4,
        serviceName: "Servizio D",
        counters: [3],
        description: "Servizio D",
      },
      {
        id: 5,
        serviceName: "Servizio E",
        counters: [1, 2, 4],
        description: "Servizio E",
      },
    ]);
  }, []);

  const handleDelete = async (id) => {
    /*
    setLoading(true);
    await deleteService(id);
    setLoading(false);
    */
    navigate("/");
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
                <th>ID</th>
                <th>Service Name</th>
                <th>Counters</th>
              </tr>
            </thead>
            <tbody>
              {services.map((service) => (
                <tr key={service.id}>
                  <td>{service.id}</td>
                  <td>{service.serviceName}</td>
                  <td>{service.counters.join(", ")}</td>
                  <td>
                    <Button
                      className="editButton"
                      onClick={() => {
                        navigate(`/services/${service.id}/edit`, {
                          state: {
                            name: service.serviceName,
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
                        handleDelete(service.id);
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

  /**
  TO DO:
  from the API retrieve the counters list and set it to the counters state
  useEffect(() => {
    setLoading(true);
    getContents().then((list) => {
      setCounters(list);
      setLoading(false);
    });
  }, [counters.length]);
  */

  // delete when the API will be ready
  useEffect(() => {
    setCounters([
      { id: 1, counterName: "Counter A", services: [1, 3, 5] },
      { id: 2, counterName: "Counter B", services: [2, 4] },
      { id: 3, counterName: "Counter C", services: [1, 5] },
      { id: 4, counterName: "Counter D", services: [3] },
      { id: 5, counterName: "Counter E", services: [1, 2, 4] },
    ]);
  }, []);

  const handleDelete = async (id) => {
    /*
    setLoading(true);
    await deleteCounter(id);
    setLoading(false);
    */
    navigate("/counters");
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
                <th>ID</th>
                <th>Counter Name</th>
                <th>Services</th>
              </tr>
            </thead>
            <tbody>
              {counters.map((counter) => (
                <tr key={counter.id}>
                  <td>{counter.id}</td>
                  <td>{counter.counterName}</td>
                  <td>{counter.services.join(", ")}</td>
                  <td>
                  <Button
                      className="editButton"
                      onClick={() => {
                        navigate(`/counters/${counter.id}/edit`, {
                          state: {
                            name: counter.counterName,
                            description: counter.description,
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
                        handleDelete(counter.id);
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
