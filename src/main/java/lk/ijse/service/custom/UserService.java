package lk.ijse.service.custom;

import lk.ijse.dto.UserDto;

import java.util.List;

public interface UserService {
    boolean save(UserDto userDto);

    List<UserDto> getAll();
}
