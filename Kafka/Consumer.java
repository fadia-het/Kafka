import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Consumer {


    public static void main(String[] args) {
        String bootstrapServers="localhost:9092";
        String consumerGroupId="java-consumer";
        Properties p = new Properties();
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG,consumerGroupId);
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(p);
        consumer.subscribe(Arrays.asList("het"));
        while(true){
            ConsumerRecords<String,String> records= consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord record:records){
                System.out.println("key"+record.key());
            }
        }
    }
}