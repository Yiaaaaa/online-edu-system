package org.example.chat.service;

import org.example.chat.entity.FAQ;
import org.example.chat.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FAQService {
    @Autowired
    private FAQRepository faqRepository;

    // 将消息保存为FAQ
    public void saveFAQ(String courseId, String question, String answer) {
        FAQ faq = new FAQ();
        faq.setCourseId(courseId);
        faq.setQuestion(question);
        faq.setAnswer(answer);
        faqRepository.save(faq);
    }
}