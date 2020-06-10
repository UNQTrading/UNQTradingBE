Feature: Comprar acciones

  Scenario: Usuario compra acciones correspondientes a una orden
    Given una orden de venta con 10 acciones de la empresa "UNQ"
    When un usuario con nombre "user" compra la orden
    Then el usuario debe tener esa orden en su cuenta
