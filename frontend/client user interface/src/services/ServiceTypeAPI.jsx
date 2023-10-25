'use strict'
const SERVER_URL = 'http://localhost:8080/API/admin/'

const getServices = async () =>{
    const url= `${SERVER_URL}service/info/`;
    const response = await fetch(url);
    if (response.ok){
        const json=await response.json();
        return json.map(s => {
            return {id: s.serviceId, name: s.tagName}
        });
    }else {
        const error=await response.json();
        throw new Error(`API Error: ${response.status} - ${error}`)
    }
}

const serviceTypeAPI={getServices};
export default serviceTypeAPI;