package peaksoft.appplaza.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.RegistrationRequest;
import peaksoft.appplaza.model.dto.RegistrationResponse;
import peaksoft.appplaza.model.dto.UserResponse;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;
@Component // бул класстын объектисин тузуш учун керек
public class UserMapper {

    // Бул метод requestтен келген классты entity класска откорот
    public User mapToEntity(RegistrationRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setStatus(Status.BASIC);
        user.setLocalDate(LocalDate.now());
        user.setSubscribe(request.isSubscribe());
        return user;
    }
    public RegistrationResponse mapToResponse(User user){
//        RegistrationResponse response = new RegistrationResponse();
//        response.setName(user.getName());
//        response.setLastName(user.getLastName());
//        response.setEmail(user.getEmail());
//        response.setAge(user.getAge());
//        response.setStatus(user.getStatus());   ушундай кылып чыксак да болот же болбосо builderдин жардамы
//        return response;                            менен женилдетсек болот кодту
        return RegistrationResponse.builder()   // builderти подключение кылыш учун RegistrationResponse класска
                .id(user.getId())                   // getter setter ден кийин  @Builder деп кошуп коюш керек
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .status(user.getStatus())
                .response("Успешно зарегистрерован!").build(); // акырында build(); деп код жабып коюш керек
    }

    public UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())                   // getter setter ден кийин  @Builder деп кошуп коюш керек
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .status(user.getStatus())
                .subscribe(user.isSubscribe())
                .localDate(user.getLocalDate()).build();
    }
}
