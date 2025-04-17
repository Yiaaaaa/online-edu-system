package org.example.chat.repository;

import org.example.chat.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ, String> {
}