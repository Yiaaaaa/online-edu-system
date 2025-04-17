import React, { useState } from 'react';
import { Card, Input, Button, List, message, Upload } from 'antd';
import { SendOutlined, UploadOutlined } from '@ant-design/icons';

const ChatAndAssignment = () => {
    const [messages, setMessages] = useState([
        { user: '学生A', content: '如何理解闭包？', time: '10:00' },
        { user: '教师B', content: '闭包是函数和其相关引用环境的组合...', time: '10:05' }
    ]);
    const [inputMsg, setInputMsg] = useState('');

    const handleSend = () => {
        if (inputMsg.trim()) {
            setMessages([...messages, { user: '学生A', content: inputMsg, time: new Date().toLocaleTimeString() }]);
            setInputMsg('');
        }
    };

    return (
        <div className="chat-assignment">
            <Card title="课堂交流" style={{ marginBottom: 20 }}>
                <List
                    dataSource={messages}
                    renderItem={item => (
                        <List.Item>
                            <strong>{item.user}:</strong> {item.content}
                            <span style={{ float: 'right', color: '#999' }}>{item.time}</span>
                        </List.Item>
                    )}
                />
                <Input
                    value={inputMsg}
                    onChange={(e) => setInputMsg(e.target.value)}
                    onPressEnter={handleSend}
                    suffix={<SendOutlined onClick={handleSend} style={{ cursor: 'pointer' }} />}
                />
            </Card>

            <Card title="作业提交与反馈">
                <Upload
                    beforeUpload={(file) => {
                        if (file.size > 50 * 1024 * 1024) {
                            message.error('文件大小不能超过50MB');
                            return false;
                        }
                        message.success('作业提交成功！');
                        return false;
                    }}
                >
                    <Button icon={<UploadOutlined />}>上传作业文件</Button>
                </Upload>
                <div className="feedback" style={{ marginTop: 20 }}>
                    <h4>教师反馈：</h4>
                    <p>评分：85/100</p>
                    <p>评语：代码逻辑清晰，但需要注意代码规范</p>
                </div>
            </Card>
        </div>
    );
};

export default ChatAndAssignment;