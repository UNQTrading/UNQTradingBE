Feature: Login Persona

  Scenario: Login del usuario Fede
    Given el usuario Fede previamente registrado
    When me logeo con DNI 12345678, username "Fede" y contraseña "123456"
    Then el codigo de la respuesta es 200