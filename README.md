How to Run

#Run All Test
mvn clean test

#Run Spesific Test Case
Open folder test -> java
Open 1 file
Get method name who had tag @Test
And replace FullFlow with File Name
And replace #fullFlow with Method Name

mvn -Dtest=FullFlow#fullFlow test
