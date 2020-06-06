Feature: visualizarOrdenesDeVenta
  visualizar ordenes de venta de una empresa

  Scenario: Sunday isn't Friday
    Given una empresa con nombre "UNQ" y "2" ordenes de venta
    When pregunto cuales son sus ordenes de venta
    Then debo visualizar "2" ordenes de venta
