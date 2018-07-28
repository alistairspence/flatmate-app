cd
rm -rf flatmate
git clone https://github.com/alistairspence/flatmate-app
cd flatmate-app/
mvn clean install
cd target/
java -jar flatmateapp-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:mysql://127.0.0.1:3306/testdb --spring.datasource.username=root --spring.datasource.password=root