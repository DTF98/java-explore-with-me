package ru.DTF98.ewm.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.DTF98.ewm.error.NotFoundException;
import ru.DTF98.ewm.user.model.User;
import ru.DTF98.ewm.user.repository.UserRepository;
import ru.DTF98.ewm.user.service.UserService;
import ru.DTF98.ewm.utils.Pagination;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers(List<Long> ids, int from, int size) {
        if (ids != null) {
            if (!ids.isEmpty()) {
                return userRepository.findAllByIdIn(ids);
            } else {
                return List.of();
            }
        } else {
            Pageable page = Pagination.getPage(from, size);
            return userRepository.findAllBy(page);
        }
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        userRepository.delete(user);
    }
}
