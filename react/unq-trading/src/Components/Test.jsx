import React, { Component, useState } from 'react';
import axios from 'axios';
import { Button, Form, Col, Container } from 'react-bootstrap';

export default function Test() {
    /*
     * El useState devuelve una pareja de valores: el estado actual
     * y una función que lo actualiza. Por eso escribimos
     * const [testField, handleChange] = useState().
     */
    const [testField, handleChange] = useState('');

    return (
        <div>
            <Container>
                <Form>
                    <Form.Row>
                        <Col>
                            <Form.Control placeholder="Ingrese texto de prueba" onChange={event => handleChange(event.target.value)} />
                        </Col>
                        <Button variant="primary" onClick={ev => sent(testField)}>Enviar</Button>{' '}
                    </Form.Row>
                </Form>
            </Container>
        </div>
    );
}

function sent(testField) {
    axios.post(`http://localhost:8080/api/test/save`, {
        id: null,
        testField: testField
    }).then((response) => {
        alert("ok")
    }).catch((error) => {
        alert("fail")
    })
}
