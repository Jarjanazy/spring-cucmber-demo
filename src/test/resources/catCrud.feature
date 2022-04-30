Feature: Cat Crud Operations
  Scenario:
    Given I add a cat:
    | name  | Phibi |

    When I ask for Phibi

    Then I get Phibi

  Scenario:
    Given I have a cat:
    | name | Monica |

    When I delete Monica

    Then Monica is removed from the database