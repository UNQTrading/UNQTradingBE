Feature: Cargar saldo

  Scenario: Una persona carga saldo a su cuenta
    Given un usuario previamente registrado
    When cargo saldo al dni 12345678 con un monto de 100 pesos
    Then los 100 pesos fueron cargados a su cuenta