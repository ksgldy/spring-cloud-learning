mvn clean package -DskipTests=true

java -jar target/idc-provider1.jar --server.port=2001

java -jar target/idc-provider1.jar --spring.profiles.active=dev --server.port=8080

consul agent -dev

