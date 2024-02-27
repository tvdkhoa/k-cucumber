Feature: Manage Category

  Background:
    Given user logged in the CMS system with "Admin" role
    And click Login button

  Scenario: Add new Category
    Given user has access to the Category page
    When user has finished entering the category information
    And I takes screenshot
    And click the Save button
    Then the message "Category has been inserted successfullysss" displays

  Scenario: Update the Category existing
    Given user has access to the Category page
    When user search a category existing "Art Major"
    And user edit the category information
    And click the Save button
    Then the message "Category has been updated successfully" displays

#  Scenario: Add multi category
#    #Given user has access to the Category page
#    When the user adds some categories below
#      | category_name | order_number | meta_title | description |
#      | Computer 1    | 1            | Computer 1 | Build PC    |
#      | Computer 2    | 2            | Computer 2 | Build PC    |
