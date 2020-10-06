import React, {useEffect, useState} from "react";
import {Button} from "react-bootstrap";
import {UrlForm} from "../components/UrlShortener/UrlForm";
import {useHistory} from "react-router";
import AxiosAPI from "../services/AxiosAPI";
import * as Yup from "yup";

const uriSchema = Yup.object().shape({
    id: Yup.number()
        .required("There was a weird error, reload page to continue"),

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

const initialState = {
    id: 0,
    shortUri: '',
    uri: '',
    clickLogs: []
};

const ShortenUrl = (props) => {
    const [shortenedUrl, setShortenedUrl] = useState(initialState);
    const [errorMsg, setErrorMsg] = useState('');
    const history = useHistory();
    const shortUri = window.location.pathname.split('/').reverse()[0];

    useEffect(() => {
        document.title = 'Shorten URL 💻';
    }, []);

    useEffect(() => {
        async function getShortenedUrl() {
            await AxiosAPI.get(`/actions/${shortUri}`)
                .then(response => {
                    console.log(response)
                    setShortenedUrl(response)
                })
                .catch(reason => {
                    window.alert(`Short URL: '${shortUri}' does not exist, you will be redirected`);
                    history.push('/');
                });
        }

        getShortenedUrl().then(r => r);
    }, [history, shortUri]);


    useEffect(() => {
        const isValid = uriSchema.validate(shortenedUrl, {abortEarly: true})
            .catch(reason => setErrorMsg(reason.message));

        if (isValid)
            setErrorMsg('');
    }, [shortenedUrl])

    const updateUri = async () => {
        if (window.confirm("Update URL?")) {
            const model = Object.assign({}, shortenedUrl);
            if (model.shortUri === '')
                model.shortUri = null;

            AxiosAPI.put(`/actions/${shortUri}/modify`, model)
                .then(response => history.push({
                    pathname: `/shorten/${response.shortUri}`,
                    state: {shortenedUri: response}
                }))
                .catch(reason => setErrorMsg(JSON.stringify(reason.response.data.message)));
        }
    }

    const handleFormChange = async (type, value) => {
        await setShortenedUrl(prevState => ({...prevState, [type]: value}));
    };

    const handleFormSubmit = async () => {
        const isValid = await uriSchema.validate(shortenedUrl, {abortEarly: true})
            .catch(reason => setErrorMsg(reason.message));

        if (isValid)
            await updateUri();
    }

    return (
        <div className={"App-header"}>
            <header>
                {/* eslint-disable-next-line jsx-a11y/accessible-emoji */}
                <h1>🎉🎉 Use your shortened URL 🎉🎉</h1>
                <h3>It was pressed {shortenedUrl.clickLogs.length} times</h3>
            </header>
            <h1>
                <a href={`${window.location.origin}/${shortUri}`}>
                    {`${window.location.host}/${shortUri}`}
                </a>
            </h1>
            <div className={"form-div"}>
                <UrlForm
                    newUri={shortenedUrl}
                    errorMsg={errorMsg}
                    isEditing={true}
                    handleChange={handleFormChange}
                    handleSubmit={handleFormSubmit}
                />
            </div>
        </div>
    );
}

export default ShortenUrl;