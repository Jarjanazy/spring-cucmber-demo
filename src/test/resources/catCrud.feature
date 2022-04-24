Feature: Cat Crud Operations
  Scenario:
    Given I have a cat:
    | name  |
    | Phibi |

    When I ask for Phibi
    Then I get Phibi