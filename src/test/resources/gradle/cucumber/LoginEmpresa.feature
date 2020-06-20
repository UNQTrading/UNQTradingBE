Feature: Login Empresa

  Scenario: Login de la empresa UNQ
    Given la empresa UNQ previamente registrada
    When me logeo con CUIT 98765541111 y contraseña "1234578"
    Then el status code de la respuesta es 200