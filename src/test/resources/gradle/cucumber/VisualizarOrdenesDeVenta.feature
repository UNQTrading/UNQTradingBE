Feature: Visualizar Ordenes De Venta
  visualizar ordenes de venta de una empresa

  Scenario: Empresa UNQ con 2 ordenes
    Given una empresa con nombre "UNQ" y 2 ordenes de venta
    When pregunto cuales son sus ordenes de venta
    Then debo visualizar 2 ordenes de venta

  Scenario: Empresa UNQ crea una orden de venta
    Given una empresa con nombre "UNQ"
    When creo una orden de venta
    Then la orden de venta tiene un id
