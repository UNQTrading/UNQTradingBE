Feature: Vender acciones

  Scenario: Usuario compra acciones correspondientes a una orden y el monto se acredita en el saldo de la empresa
    Given una orden de venta con 10 acciones y precio 100 de la empresa "UNQT"
    When un usuario con saldo suficiente compra la orden
    Then el monto se ve reflejado en saldo de la empresa