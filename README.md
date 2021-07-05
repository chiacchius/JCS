# JCS

### How to generate jacoco.exec
>mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package

### Create the direcotry as showed below
>mkdir -p target/jacoco-gen/jcs-coverage/

### How to convert jacoco.exec in report
>java -jar ./jacoco-0-2/lib/jacococli.jar report target/jacoco.exec --classfiles ./jcs-1.3.jar --sourcefiles ./src/ --html ./target/jacoco-gen/jcs-coverage/ --xml ./target/jacoco-gen/jcs-coverage/file.xml --csv ./target/jacoco-gen/jcs-coverage/file.csv

