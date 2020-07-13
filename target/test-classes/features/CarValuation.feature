Feature: Car value estimation

Scenario: verify the user compares the car valuation results with the given text file
  Given user reads the input file name "car_input.txt" from the project path
  When vehicle registration numbers are extracted from the above file
  Then compare the results with the given output file name "car_output.txt"