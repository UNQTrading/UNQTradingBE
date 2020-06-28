Feature: Comprar acciones

  Scenario: Usuario compra acciones correspondientes a una orden
    Given una orden de venta con 10 acciones de la empresa "UNQ"
    When una persona con nombre "user" compra la orden
    Then la persona debe tener esa orden en su cuenta
