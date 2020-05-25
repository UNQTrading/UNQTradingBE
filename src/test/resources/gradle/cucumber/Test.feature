Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday
    Given today is "Sunday"
    When I ask whether it's "Friday"
    Then I should be told "No"

  Scenario: Friday is Friday
    Given today is "Friday"
    When I ask whether it's "Friday"
    Then I should be told "Yes"