Feature: Registrar persona

  Scenario: Una persona se registra en UNQTrading
    Given una persona con nombre: "Juan" apellido: "Perez" email: "jperez@gmail.com" password: "test123" dni: 99999999 username: "jperez" y cuil: 11111111111
    When la persona se registra
    Then la persona se guardo en el sistema
