package ru.specialist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.specialist.model.UserVM;

@Controller//Указывает что это бин спринга,котор.будет использован диспетчерсервлетом в качестве контроллера
@RequestMapping("/hello")// указывает что диспетчерсервлет будет перенапрвлять запросы по адресу /hello на этот контрроллер
public class HelloController {
	
	@Autowired
	private UserVM uservm;//С помощью автосвязывания спринга поместили в поле uservm ссылку на бин модели
	
	@RequestMapping(method=RequestMethod.GET) //указывает что диспетчерсервлет будет перенаправлять запросы отправленные методом GET протокол Http(по адресу /hello)
	public String showForm(Model uiModel) {
		//uiModel это модель данных представления(view).Через именованные атрибуты этой модели передаются данные котор.мы хотим отобразить на странице
		uiModel.addAttribute("user",uservm);
		//возвращаем название jsp страницы( view-представление)
		// к этому названию добавляется суффикс и префикс сконфигурированные в движке рендеринга и получается путь к файлу JSPстраницы
		return "hello/form"; ///WEB-INF/views/hello/form.jsp 
		
	}
	@RequestMapping(method=RequestMethod.POST)
	public String showHello(UserVM inputModel, Model uiModel) {
		uservm.setUserName(inputModel.getUserName());//Копируем Имя пользователя из входной модели пришедшего с запросом )inputModel) в нашу модель(userVM)
		uservm.setPrefix(inputModel.getPrefix());
		uiModel.addAttribute("user",uservm);
		return "hello/form";
	}  
}
