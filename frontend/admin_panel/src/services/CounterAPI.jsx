import axiosInstance from "./APIConfig";
import routes from '../assets/routes.json'; 

/**
 * Return all services at a single counter by ID 
 */
export const getCounterServiceList = (number) => {
    return axiosInstance.get(routes.getCounterServiceList + `${String(number)}`).then(response => {
        if (response.status == 200) {
            console.log("getCounterServiceList: ", response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while retrieving all services from a specific counter: ', error); 
    }); 
}; 

/**
 * Get the details of a specific counter
 */
export const getCounterInfo = (number) => {
    return axiosInstance.get(routes.getCounterInfo + `${String(number)}`).then(response => {
        if (response.status == 200) {
            console.log("getCounterInfo: ", response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while retrieving counter info: ', error); 
    });
};

/**
 * Return all counters saved in the database 
 */
export const getAllCounter = () => {
    return axiosInstance.get(routes.getAllCounter).then(response => {
        if (response.status == 200) {
            console.log(response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while retrieving all counters: ', error); 
    }); 
};

/**
 * Add a new counter to the database
 */
export const addNewCounter = (number, tagNameList, description) => {
    return axiosInstance.post(routes.addNewCounter, {
        "number": Number(number),
        "tagNameList": tagNameList,
        "description": String(description)
    }).then(response => {
        if (response.status == 200 || response.status == 201) {
            console.log(response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while creating new counter: ', error); 
    }); 
};

/**
 * Update the list of services at a specific counter 
 */
export const changeCounterServices = (number, tagNameList) => {
    return axiosInstance.post(routes.changeCounterServices, {
        "number": Number(number),
        "tagNameList": tagNameList
    }).then(response => {
        if (response.status == 200 || response.status == 201) {
            console.log(response.data); 
            return response.data; 

        } else {
            console.error('Request failed with status: ', response.status); 
        }
    }).catch((error) => {
        console.error('Error while updating the service list of a counter: ', error); 
    }); 
};

/**
 * Delete counter and returns boolean if deletion was successful
 */
export const deleteCounter = (number) => {
    return axiosInstance.delete(routes.deleteCounter + `${String(number)}`).then(response => {
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
