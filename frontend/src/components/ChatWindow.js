import React, { useState, useEffect, useRef } from 'react';
import { Input, Button, List } from 'antd';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

const ChatWindow = ({ courseId, user }) => {
    const [messages, setMessages] = useState([]);
    const [inputMsg, setInputMsg] = useState('');
    const stompClient = useRef(null);

    useEffect(() => {
        const socket = new SockJS('http://localhost:8080/chat');
        stompClient.current = Stomp.over(socket);
        stompClient.current.connect({}, () => {
            stompClient.current.subscribe(`/topic/chat/${courseId}`, (message) => {
                const newMessage = JSON.parse(message.body);
                setMessages(prev => [...prev, newMessage]);
            });
        });

        return () => {
            if (stompClient.current) stompClient.current.disconnect();
        };
    }, [courseId]);

    const sendMessage = () => {
        if (inputMsg.trim()) {
            const message = {
                courseId,
                sender: user.name,
                content: inputMsg,
                timestamp: new Date().toISOString()
            };
            stompClient.current.send(`/app/send/${courseId}`, {}, JSON.stringify(message));
            setInputMsg('');
        }
    };

    return (
        <div style={{ border: '1px solid #ddd', padding: '10px' }}>
            <List
                dataSource={messages}
                renderItem={item => (
                    <List.Item>
                        <strong>{item.sender}: </strong>{item.content}
                    </List.Item>
                )}
            />
            <Input
                value={inputMsg}
                onChange={(e) => setInputMsg(e.target.value)}
                onPressEnter={sendMessage}
                placeholder="输入消息..."
            />
            <Button onClick={sendMessage} style={{ marginTop: '10px' }}>发送</Button>
        </div>
    );
};