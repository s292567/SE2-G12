import axiosInstance from './APIConfig';
import routes from '../assets/routes.json'; 

/**
 * Returns a list of all services in the db 
 */
export const getAllServices = () => {
    return axiosInstance.get(routes.getAllServices).then(response => {
        if (response.status == 200) {
            console.log(response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while retrieving all services: ', error); 
    }); 
};

/**
 * Add a new service to the db
 */
export const addNewService = (tagName, serviceTime, description) => {
    return axiosInstance.post(routes.addNewService, {
        "tagName": String(tagName),
        "serviceTime": Number(serviceTime),
        "description": String(description)
    }).then(response => {
        if (response.status == 200 || response.status == 201) {
            console.log(response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while creating new service: ', error); 
    }); 
};

/**
 * Update an existing service 
 */
export const changeService = (currentTagName, tagName, serviceTime, description) => {
    return axiosInstance.post(routes.changeService + `${String(currentTagName)}`, {
        "tagName": String(tagName),
        "serviceTime": Number(serviceTime),
        "description": String(description)
    }).then(response => {
        if (response.status == 200 || response.status == 201) {
            console.log(response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while updating a service: ', error); 
    }); 
};

/**
 * Deletes a service from the db 
 */
export const deleteService = (tagName) => {
    return axiosInstance.delete(routes.deleteService + `${String(tagName)}`).then(response => {
        if (response.status == 200) {
            console.log(response.data); 
            return true; 

        } else {
            console.error('Request failed with status: ', response.status); 
            return false; 
        }
    }).catch((error) => {
        console.error('Error while deleting a counter: ', error); 
    }); 
};

/**
 * Get all counters which offer the service 
 */
export const getCounterList = (tagName) => {
    return axiosInstance.get(routes.getCounterList + `${String(tagName)}`).then(response => {
        if (response.status == 200) {
            console.log(response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while retrieving all counter which offer a service: ', error); 
    }); 
};
