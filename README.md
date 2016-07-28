# mvc-edge-service
Demo of spring mvc as edge service for akka 

Java verion: 8

build: mvn clean install

Run edge: java -jar edge-service/target/edge-sevice-0.0.1-SNAPSHOT.jar or java -jar -DHTTP_PORT={8082} -DAKKA_PORT={2552} edge-service/target/edge-sevice-0.0.1-SNAPSHOT.jar

Run processing: java -jar target/akka-procssing-0.0.1-SNAPSHOT.one-jar.jar or java -jar -DAKKA_PORT=2552 target/akka-procssing-0.0.1-SNAPSHOT.one-jar.jar or java -jar -DAKKA_PORT=2552 akka-processing/target/akka-procssing-0.0.1-SNAPSHOT.one-jar.jar 
