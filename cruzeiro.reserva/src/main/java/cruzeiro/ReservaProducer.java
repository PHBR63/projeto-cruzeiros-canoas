package cruzeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ReservaProducer {
	
	@Value("${topic.name.producer}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void enviar(String reserva) {
		
		kafkaTemplate.send(topicName, reserva);
	}
}