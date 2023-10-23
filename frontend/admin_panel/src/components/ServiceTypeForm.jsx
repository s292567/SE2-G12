import { useEffect, useState, useContext } from "react";
import { Form, Button, Container, FormLabel } from "react-bootstrap";
import { useNavigate, useLocation } from "react-router-dom";

function ServiceTypeForm(){
    
    const navigate = useNavigate();
    const location = useLocation();

    let serviceType = undefined;
    if(location.state){
        serviceType = {...location.state };
    }

    const [ name, setName ] = useState(serviceType ? serviceType.name : " ");
    const [ description, setDescription ] = useState(serviceType ? serviceType.description : " ");

    const buttonStyle = {
        backgroundColor: serviceType ?  "lightblue" : "#8FEB98",
        color: "black",
        borderRadius: "1rem",
        borderColor: "transparent",
        padding: "0.5rem 1rem",
    };

    return <>
        <Container>
            <h2 className="mb-5 mt-4">Service Type</h2>
            <Form>
                <Form.Group controlId="name" className="mb-3">
                    <Form.Label>Service Name: </Form.Label>
                    <Form.Control as="textarea" placeholder="Enter name" style={{height: "25px", width: '40%', borderColor: "black"}} value={name} onChange={(e) => setName(e.target.value)}/>
                </Form.Group>

                <Form.Group controlId="description" className="mb-4">
                    <Form.Label>Description: </Form.Label>
                    <Form.Control as="textarea" placeholder="Enter description"  style={{height: "100px", width: '50%', borderColor: "black"}} value={description} onChange={(e) => setDescription(e.target.value)}/>
                </Form.Group>
                <Button style={buttonStyle} onClick={()=>{navigate("/");}}>
                    { serviceType ? "Update" : "Create" }
                </Button>
            </Form>
        </Container>
    </>;
}

export { ServiceTypeForm };