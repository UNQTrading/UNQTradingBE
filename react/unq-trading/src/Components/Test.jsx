import React, { Component, useState } from 'react';
import axios from 'axios';
import { Button, Form, Col, Container, Label } from 'react-bootstrap';

export default function Test() {
    /*
     * El useState devuelve una pareja de valores: el estado actual
     * y una función que lo actualiza. Por eso escribimos
     * const [testField, handleChange] = useState().
     */
    const [testField, handleFirst] = useState('');
    /*
     * Cada constante corresponde a una propiedad del estado
     * la funcion que lo actualiza no está definida, donde sea que use el hook puedo definir lo que quiera, incluso valores fijos
     */
    const [secondField, handleSecond] = useState('');

 
    return (
        <div>
            <Container>
                <Form>
                    <Form.Row>
                        <Col>
                            <Form.Control placeholder="Ingrese texto de prueba" onChange={event => handleFirst(event.target.value)} />
                        </Col>
                        <Col>
                            <Form.Control 
                                placeholder="Valor second field" 
                                onChange={event => handleSecond(event.target.value)} 
                            />
                        </Col>
                        <Button variant="primary" onClick={ev => sent(testField)}>Enviar</Button>
                        <Button 
                            variant="secondary" 
                            onClick={event => handleSecond("Valor hardcodeado, acá vemos que el handleSecond puede ser diferente en distintos lugares")} > 
                            Cambiar valor second field
                        </Button>
                    </Form.Row>
                    <Form.Label> Valor del second field: {secondField}</Form.Label>
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
