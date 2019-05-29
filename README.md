How to run kafki in PC with Mac OS 
====================

### 1.Download kafka and zookeeper server. Run two server.

- (`<link>` : https://kafka.apache.org/quickstart#quickstart_download)
download kafka
go to dir kafka 


- ##### start zookeeper in dir:
>bin/zookeeper-server-start.sh config/zookeeper.properties

- ##### start kafka server:
>bin/kafka-server-start.sh config/server.properties

- ##### OPTIONAL! create kafka topic: 
>bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test

- ##### start producer kafka: (write text in console and click enter)
>bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

- ##### start consumer kafka (important command into check produser implementation):
>bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning

### 2.Implement kafka consumer.

- ##### Add dependency to pom.xml
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-stream-binder-kafka</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.kafka</groupId>
  <artifactId>spring-kafka</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-stream</artifactId>
</dependency>
```

- ##### Create interface with binder
RESERVATION_IN is topic kafka, and must equals topic with application.properties
```java
        interface ReservationBinding {
           String RESERVATION_IN = "reservationsin";
        
           @Input(RESERVATION_IN)
           SubscribableChannel reservationIn();
        
        }
```

- ##### Add annotation in run class with binder kafka
ReservationBinter this is interface binder, create ahead
```java
@EnableBinding(ReservationBinding.class)
```

- ##### Create method listener topic kafka
```java
@StreamListener(ReservationBinding.RESERVATION_IN)
public void processReservationListener(Reservation rn) {
   System.out.println("CONSUMER EVENT: " + rn.getReservationName());
}
```

- ##### Add posts to application.properties
```java
  spring.cloud.stream.kafka.streams.binder.configuration.commit.interval.mms=1000
  spring.cloud.stream.kafka.streams.binder.configuration.default.key.serde=org.apache.kafka.common.serialization.Serdes$StringSerde
  spring.cloud.stream.kafka.streams.binder.configuration.default.value.serde=org.apache.kafka.common.serialization.Serdes$StringSerde
  
  spring.cloud.stream.bindings.reservationsin.destination=reservations
  spring.cloud.stream.bindings.reservationsin.consumer.header-mode=raw
  
  spring.cloud.stream.kafka.streams.bindings.output.producer.keySerde==org.apache.kafka.common.serialization.StringSerializer
  spring.cloud.stream.kafka.streams.bindings.output.producer.valueSerde==org.apache.kafka.common.serialization.StringSerializer
  spring.cloud.stream.bindings.output.producer.use-native-encoding=true
```

### 3.Implement Producer Kafka look to link `<link>` :  https://github.com/michalk933/reservation-client
