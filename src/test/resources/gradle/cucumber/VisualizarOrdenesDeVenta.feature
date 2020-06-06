Feature: visualizarOrdenesDeVenta
  visualizar ordenes de venta de una empresa

  Scenario: Empresa UNQ con 2 ordenes
    Given una empresa con nombre "UNQ" y "2" ordenes de venta
    When pregunto cuales son sus ordenes de venta
    Then debo visualizar "2" ordenes de venta
