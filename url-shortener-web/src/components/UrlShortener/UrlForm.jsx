import React, {useRef} from "react";
import {Button, Form} from "react-bootstrap";
import FormRowInput from "../FormRow/FormRowInput";

export const UrlForm = ({errorMsg, handleChange, handleSubmit, newUri, isEditing}) => {
    const {shortUri, uri} = newUri;
    const formRef = useRef();

    return (
        <Form className={"form"} ref={formRef}>
            <FormRowInput
                label={"URL"}
                type={"text"}
                placeholder={"https://www.example.com"}
                value={uri}
                handleChange={handleChange}
                property={"uri"}
            />
            <FormRowInput
                label={"Shorten URL code"}
                type={"text"}
                placeholder={"e4Ysa"}
                value={shortUri}
                handleChange={handleChange}
                property={"shortUri"}
            />

            {errorMsg !== '' &&
            <Form.Group className="alert-danger">
                {errorMsg}
            </Form.Group>
            }
            <Button variant={"primary"} className={"mr-2"}
                    onClick={handleSubmit}>{isEditing ? 'Modify' : 'Shorten'} URL!</Button>
        </Form>
    );
};