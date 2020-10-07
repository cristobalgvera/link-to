import {useEffect} from "react";
import {useHistory} from "react-router";
import AxiosAPI from "../services/AxiosAPI";

const RedirectTo = () => {
    const shortUri = window.location.pathname.split('/').reverse()[0];
    const history = useHistory();

    useEffect(() => {
        AxiosAPI.get(`/${shortUri}`)
            .then(response => window.location = `${response}`)
            .catch(reason => {
                window.alert(`Short URL: '${shortUri}' does not exist, you will be redirected to home`);
                history.push('/');
            });
    }, [history, shortUri]);

    return null;
}

export default RedirectTo;