# VehicleValidationTest

This is BDD Framework with Page Object Model Design pattern.

Functionality:
Get the Vehicle Registration numbers from the given input file and find the vehicle details from "https://cartaxcheck.co.uk/".  Then compare those Vehicle details with the given output file.

Input file has 4 Registrations and in those 3 are valid vehicles and 1 is an invalid vehicle.

It works on both chrome driver and geko driver. We can add more drivers, but for time constraints, I have added only 2 drivers.

Run Tests:
We can run the test in 3 ways
1) Open the CarValuation.feature, right click and choose run or debug
2) Can run from the TestRunner
3) We can run the test with "mvn verify" 
