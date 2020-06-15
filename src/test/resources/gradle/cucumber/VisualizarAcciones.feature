Feature: Visualizar acciones de un usuario

  Scenario: Usuario con 1 accion con cantidad 10
    Given un usuario con una accion con cantidad 1 de la empresa "UNQ"
    When pregunto cuales son sus acciones
    Then debo visualizar 1 acciones
