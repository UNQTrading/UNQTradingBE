Feature:  Obtener saldo empresa

  Scenario: Una empresa carga saldo a su cuenta
    Given una empresa previamente registrada
    When cargo saldo al cuit 98765541111 con un monto de 100 pesos
    Then se cargaron 100 pesos a la cuenta