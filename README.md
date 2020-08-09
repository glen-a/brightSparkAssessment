# brightSparkAssessment

Code Assessment:</br>
•	Takes in a CSV file containing the following columns:</br>
  o	 firstname: String</br>
  o	lastname: String</br>
  o	date: String (format YYYY-MM-DD)</br>
  o	division: Integer</br>
  o	points: Integer</br>
  o	summary: String</br>
•	Sorts the records by division and points.</br>
•	Selects the top three records.</br>
•	Prints the records to stdout in the following YAML format:</br>
records:</br>
	- name: <firstname> <lastname></br>
	  details: In division <division> from <date> performing <summary></br>
