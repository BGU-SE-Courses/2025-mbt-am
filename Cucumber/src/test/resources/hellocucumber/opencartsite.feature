Feature: A set of scenarios for testing the review operation on Opencart

  Scenario Outline:The user is writing a review to a product
    Given The user is on the product page
    And The user is on review tab
    When The user is writing review with the name "<name>" and "<review>" and "<rating>"
    Then Message display successfully
    Examples:
      | name           | review  | rating |
      | test            | The MacBook is a premium laptop with excellent performance, stunning display quality, and long battery life, making it ideal for professionals.  |    5   |
#
#
#  Scenario Outline: The admin deletes the product
#    Given The admin is logged in with "<adminName>" and "<Password>"
#    And The admin on the Products Page
#    And the admin selected specific product
#    When The admin deletes the product
#    Then Message display successfully
#
#    Examples:
#      | adminName           | Password  |
#      | admin               | am1234  |