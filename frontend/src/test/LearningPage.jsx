import React from 'react';
import { Layout, Menu, Card, Button } from 'antd';
import { VideoCameraOutlined, MessageOutlined } from '@ant-design/icons';

const { Sider, Content } = Layout;

const LearningPage = () => {
    const chapters = [
        { key: '1', title: '第一章: Python基础语法' },
        { key: '2', title: '第二章: 函数与模块' },
        { key: '3', title: '第三章: 面向对象编程' }
    ];

    return (
        <Layout className="learning-layout">
            <Sider width={300} theme="light">
                <Menu
                    mode="inline"
                    defaultSelectedKeys={['1']}
                    items={chapters.map(ch => ({
                        key: ch.key,
                        icon: <VideoCameraOutlined />,
                        label: ch.title
                    }))}
                />
            </Sider>
            <Content style={{ padding: '24px' }}>
                <Card title="第一章: Python基础语法">
                    <video controls src="https://example.com/chapter1.mp4" style={{ width: '100%' }} />
                    <div className="action-bar" style={{ marginTop: 20 }}>
                        <Button type="primary" icon={<MessageOutlined />}>
                            提问
                        </Button>
                        <Button style={{ marginLeft: 10 }}>提交作业</Button>
                    </div>
                </Card>
            </Content>
        </Layout>
    );
};

export default LearningPage;