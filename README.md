# brightSparkAssessment

Code Assessment:</br>
•	Takes in a CSV file containing the following columns:
  o	 firstname: String
  o	lastname: String
  o	date: String (format YYYY-MM-DD)
  o	division: Integer
  o	points: Integer
  o	summary: String
•	Sorts the records by division and points.
•	Selects the top three records.
•	Prints the records to stdout in the following YAML format:
records:
	- name: <firstname> <lastname>
	  details: In division <division> from <date> performing <summary>
