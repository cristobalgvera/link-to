import React, {useEffect, useState} from 'react';
import {UrlForm} from "../components/UrlShortener/UrlForm";
import * as Yup from "yup";
import AxiosAPI from "../services/AxiosAPI";
import {useHistory} from "react-router";

const initialState = {
    shortUri: '',
    uri: ''
};

const uriSchema = Yup.object().shape({
    shortUri: Yup.string()
        .max(5, 'Short uri must contains between 1 to 5 characters')
        .matches(/[\w-]/, {
                message: 'Short uri must contains just alphanumeric values, underscore or middle dash',
                excludeEmptyString: true
            }
        )
        .nullable(),
    uri: Yup.string()
        .url('Invalid URL')
        .required('URL is required')
});

const UrlShortener = () => {
    const [newUri, setNewUri] = useState(initialState);
    const [errorMsg, setErrorMsg] = useState('');
    const history = useHistory();

    useEffect(() => {
        document.title = 'UrlShortener 🔗';
    }, [])

    useEffect(() => {
        if (newUri !== initialState) {
            const isValid = uriSchema.validate(newUri, {abortEarly: true})
                .catch(reason => setErrorMsg(reason.message));

            if (isValid)
                setErrorMsg('');
        }
    }, [newUri])

    const addUri = async () => {
        if (window.confirm("Add URL?")) {
            const model = Object.assign({}, newUri);
            if (model.shortUri === '')
                model.shortUri = null;

            AxiosAPI.post(`/`, model)
                .then(response => history.push({
                    pathname: `/shorten/${response.shortUri}`,
                    state: {shortenedUri: response}
                }))
                .catch(reason => setErrorMsg(JSON.stringify(reason.response.data.message)));
        }
    }

    const handleFormChange = async (type, value) => {
        await setNewUri(prevState => ({...prevState, [type]: value}));
    };

    const handleFormSubmit = async () => {
        const isValid = await uriSchema.validate(newUri, {abortEarly: true})
            .catch(reason => setErrorMsg(reason.message));

        if (isValid)
            await addUri();
    }

    return (
        <div className={"App-header"}>
            <div className={"form-div"}>
                <UrlForm
                    newUri={newUri}
                    errorMsg={errorMsg}
                    isEditing={false}
                    handleChange={handleFormChange}
                    handleSubmit={handleFormSubmit}
                />
            </div>
        </div>
    );
};

export default UrlShortener;