package ru.specialist;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration //включение конфогиурации spring
@EnableWebSocketMessageBroker//включение обработки сообщений по протоколу WS на сервере
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//конфигурирование очереди сообщений(встроенный брокер для обработки сообщ по протоколу ws)
		registry.enableSimpleBroker("/course");
		registry.setApplicationDestinationPrefixes("/app");
		registry.setUserDestinationPrefix("/course");
		 
	}
	// STOMP(simple streaming text orientied message protocol) Протокол котор определяет правила
	// обмена м/у сервером и клиентом
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//конфигурирование точки доступа
		registry
			.addEndpoint("/ws")//добавление точки доступа
			//.setAllowedOrigins("*")//разрешение доступа из любого домена(отключение Cors-cross origin requests-кроссдоменные запросы)
			.withSockJS();//включение поддержки библиотеки Sockjs, кот.на js для того чтобы взаимодействовать по протоколу ws
				
	}
	@Override
	//добавл.конветер json из библиотеки jackson
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }
	
	
}
