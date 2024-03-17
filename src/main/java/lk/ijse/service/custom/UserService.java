package lk.ijse.service.custom;

import javafx.scene.control.TextField;
import lk.ijse.dto.UserDto;

import java.util.List;

public interface UserService {
    boolean save(UserDto userDto);
    List<UserDto> getAll();
    public String generateNewUserId();
    public List<UserDto> getAll1();

    boolean update(String text);

    boolean updatePassword(String encryptPassword);

    boolean updateEmail(String text);
}
