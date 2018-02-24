@echo off
REM classpath:
REM -d <directory> specify where to place generated class files
REM -classpath specify where to find user class files and annotaton processors
REM -sourcepath specify where to find input source files
javac -classpath target\classes -sourcepath src src\JPvt.java -d target\classes
