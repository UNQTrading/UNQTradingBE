Feature: Vender acciones

  Scenario: Usuario compra acciones correspondientes a una orden y el monto se acredita en el saldo de la empresa
    Given una orden de venta con 10 acciones y precio 100 de la empresa "UNQT"
    When una persona con saldo suficiente compra la orden
    Then el monto se ve reflejado en saldo del vendedor

  Scenario: Usuario compra acciones correspondientes a una orden creada por una persona y el monto se acredita en el saldo del creador
    Given una orden de venta con 10 acciones y precio 100 de la empresa "UNQT2" creada por una persona
    When otra persona con saldo suficiente compra la orden
    Then el monto se ve reflejado en saldo del vendedor