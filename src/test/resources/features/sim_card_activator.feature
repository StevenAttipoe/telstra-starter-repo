Feature: SIM Card Activation

  As a user of the SIM card activation service
  I want to ensure that SIM card activation requests are processed correctly
  So that I can confirm successful activations and handle failures

  Scenario: SIM Card Not Activated
    Given a functional sim card
    When a request is not made
    Then  the sim card is not found and an error message is return

  Scenario: Successful SIM Card Activation
    Given a functional sim card
    When a request to activate the sim card is submitted
    Then the sim card is activated and its state is recorded to the database

  Scenario: Failed SIM Card Activation
    Given a broken sim card
    When a request to activate the sim card is submitted
    Then the sim card fails to activate and its state is recorded to the database



