Feature: Registrar empresa

  Scenario: Un usuario registra una empresa nueva
    Given una empresa con nombre: "UNQ" email: "unq@unq.com" password: "test123" y cuit: 11111111111
    When la empresa se registra
    Then la empresa se guardo en el sistema
