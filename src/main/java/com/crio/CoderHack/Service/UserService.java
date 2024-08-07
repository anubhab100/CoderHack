package com.crio.CoderHack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.CoderHack.Repository.UserRepository;
import com.crio.CoderHack.entities.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User registerUser(User user) {
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int score) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setScore(score);
            user.setBadges(updateBadges(score, user.getBadges()));
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    private Set<String> updateBadges(int score, Set<String> currentBadges) {
        if (score >= 1 && score < 30)
            currentBadges.add("Code Ninja");
        else if (score >= 30 && score < 60)
            currentBadges.add("Code Champ");
        else if (score >= 60 && score <= 100)
            currentBadges.add("Code Master");
        return currentBadges;
    }
}
