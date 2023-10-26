const SERVER_URL = 'http://localhost:8080/API/'

const createTicket = async (serviceType) => {
    const url= `${SERVER_URL}ticket/create/`;
    const response = await fetch(url,{
        method: 'POST',
        headers:{
            'Content-Type': 'application/json',
        },
        body: `{"serviceType":"${serviceType}"}`
    });
    if (response.ok){
        const json=await response.json();
        return json.ticketId;
    }else {
        const error=await response.json();
        throw new Error(`API Error: ${response.status} - ${error}`)
    }
}

const TicketAPI ={createTicket};
export default TicketAPI;