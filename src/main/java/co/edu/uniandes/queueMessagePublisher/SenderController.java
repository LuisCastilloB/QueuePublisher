
package co.edu.uniandes.queueMessagePublisher;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queue/v1.0.0")
public class SenderController {
	
	@Autowired
	JmsTemplate jmsTemplate;

	@GetMapping("/send/{queue}/{message}")
	public String send(@PathVariable("queue") String queue, @PathVariable("message") String message) {
            
            for(int i = 1; i <= 1000; i++) {
                String newmessage = message+" ("+i+")";
		jmsTemplate.send(queue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
                                ObjectMessage object = session.createObjectMessage(newmessage);
                                return object;
			}
		});
            }
            return "OK";
	}
}
