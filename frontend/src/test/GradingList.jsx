import React from 'react';
import { List, Tag, Button } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';

const submissions = [
    {
        id: 'sub001',
        student: '学生C',
        assignment: '二叉树遍历',
        status: '待批改',
        isLate: true
    },
    {
        id: 'sub002',
        student: '学生D',
        assignment: '图算法',
        status: '已批改',
        isLate: false
    }
];

const GradingList = () => {
    return (
        <List
            itemLayout="horizontal"
            dataSource={submissions}
            renderItem={item => (
                <List.Item
                    actions={[
                        <Button type="link" icon={<DownloadOutlined />}>下载作业</Button>,
                        <Button type="primary">进入批改</Button>
                    ]}
                >
                    <List.Item.Meta
                        title={`${item.student} - ${item.assignment}`}
                        description={
                            <>
                                <Tag color={item.status === '待批改' ? 'orange' : 'green'}>{item.status}</Tag>
                                {item.isLate && <Tag color="red">迟交</Tag>}
                            </>
                        }
                    />
                </List.Item>
            )}
        />
    );
};

export default GradingList;