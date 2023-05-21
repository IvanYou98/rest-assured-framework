@user
Feature: I want to test CRUD functionality of User API

  Scenario Outline: After I create a user, I can fetch the info of that user using id
    When I create a user with <name>, <sex> and <age>
    And I get the user info with id
    Then The user info should match the <name>, <sex> and <age>
    Examples:
      | name      | sex      | age |
      | "johnson" | "male"   | 10  |
      | "mary"    | "female" | 12  |
      | "david"   | "male"   | 14  |