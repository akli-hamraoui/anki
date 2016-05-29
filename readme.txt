
1) mvn clean install
Build the project (jdk 1.7)



2) mvn package appassembler:assemble
create the script sh or bat by maven plugin
 to run the main class with input file path as argument
The generated script is in target/appassembler/bin
execute the script for example: 

smartholiday-anki/target/appassembler/bin$ ./anki /home/user/workspace/smartholiday-anki/src/test/resources/input.dat



The test unit "AnkiGameServiceTest" takes input file from src/test/resources/input.dat
and compare the result with the output file expected
src/test/resources/output.dat
