import Axios from "axios";

const instance = Axios.create({
    baseURL: `http://localhost:8081`
});

const get = async (url) => {
    return instance.get(url)
        .then(response => response.data)
        // .catch(error => console.log(error));
};

const post = async (url, body = null, queryParams = null) => {
    return instance.post(url, body, {params: queryParams})
        .then(response => response.data)
        // .catch(error => error);
};

const put = (url, body = null, queryParams = null) => {
    return instance.put(url, body, {params: queryParams})
        .then(response => response.data)
        // .catch(error => error);
};

const remove = (url) => {
    return instance.delete(url)
        .then(response => response.data)
        // .catch(error => error);
};

export default {get, post, put, remove};