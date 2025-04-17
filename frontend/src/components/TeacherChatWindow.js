import React from 'react';
import { Button, Input, message } from 'antd';
import { saveFAQ } from '../api/chat';

const TeacherChatWindow = ({ courseId }) => {
    const [currentMessage, setCurrentMessage] = useState('');

    const handleResolve = (msgId) => {
        saveFAQ(courseId, msgId)
            .then(() => message.success('已保存到FAQ'));
    };

    return (
        <div>
            <Input.TextArea
                value={currentMessage}
                onChange={(e) => setCurrentMessage(e.target.value)}
                onPressEnter={() => sendMessage(currentMessage)}
                placeholder="@学生ID 进行提及"
            />
            <Button onClick={() => handleResolve(selectedMessageId)}>
                标记为已解决
            </Button>
        </div>
    );
};