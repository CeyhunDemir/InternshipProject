package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.domain.service.IUserService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    @Override
    public boolean checkUser(String userEmail) {
        if (userRepository.findByEmail(userEmail).isEmpty()) {
            return false;
        }
        else
            return true;
    }
}
