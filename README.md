# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called OpenCart (https://www.opencart.com/).


OpenCart is a popular open-source eCommerce platform that enables businesses to create and manage online stores with ease. It offers a user-friendly interface, multi-store management, multi-language and currency support, and thousands of extensions for customization.

## Installation
For a step-by-step installation: https://www.youtube.com/watch?v=GftTTFm58d8

## What we tested
We tested the OpenCart e-commerce platform by implementing test scenarios for both Admin and User roles. Specifically, we focused on the review management and product visibility functionalities.

*User story:* As a user, I want to submit a review for a product

*Preconditions:* The user is on the product page

*Expected outcome:* A success message is displayed, confirming that the review was successfully submitted.

*User story:* As an admin, I want to hide a specific product from the catalog, so that it is no longer available for customers to view or purchase.

*Preconditions:* The admin is logged in to the OpenCart admin dashboard.

*Expected outcome:* A success message is displayed, confirming that the product is hidden from the catalog.
$$

## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results




