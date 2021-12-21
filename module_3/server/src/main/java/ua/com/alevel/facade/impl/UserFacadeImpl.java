package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.api.dto.request.UserRequestDto;
import ua.com.alevel.api.dto.response.UserResponseDto;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userService.create(user);
    }

    @Override
    public void update(UserRequestDto request, Long id) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userService.update(user);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return new UserResponseDto(userService.findById(id));
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userService.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
