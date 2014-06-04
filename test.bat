del termoplan.jar
rd /s /q bin
mkdir bin

javac -g -sourcepath "src" -d bin "src/app/ConsoleApplication.java"
jar -cmf manifest.mf termoplan.jar -C bin .

java -jar termoplan.jar