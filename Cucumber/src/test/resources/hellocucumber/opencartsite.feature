Feature: A set of scenarios for testing the review operation on Opencart

  Scenario Outline:The user is writing a review to a product
    Given The user is on the product page
    And The user is on review tab
    When The user is writing review with the name "<name>" and "<review>" and "<rating>"
    Then Message display successfully
    Examples:
      | name | review | rating |
      | test | 0123456789012345678901234 | 5 |


  Scenario Outline: The admin hides the product
    Given The admin is logged in with "<adminName>" and "<Password>"
    And The admin on the Products Page
    And the admin selected specific product
    When The admin hides the product
    Then check product is hidden from catalog

    Examples:
      | adminName           | Password  |
      | admin               | am1234  |