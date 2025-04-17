package org.example.chat.repository;


import org.example.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, String> {
}