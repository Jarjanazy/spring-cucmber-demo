Feature: Cat Crud Operations
  Scenario:
    Given I add a cat:
    | name  | Phibi |

    When I ask for Phibi

    Then I get Phibi