import React, { useState } from 'react';
import { Card, Input, Rate, Button, Alert, Tag } from 'antd';
import { SmileOutlined } from '@ant-design/icons';

const GradingDetail = () => {
    const [score, setScore] = useState(88);
    const [feedback, setFeedback] = useState('');

    return (
        <Card
            title="作业批改 - 学生C《二叉树遍历》"
            extra={<Tag color="red">代码相似度72%</Tag>}
            style={{ maxWidth: 800, margin: '20px auto' }}
        >
            <Alert
                message="抄袭检测警告：系统检测到代码相似度过高"
                type="error"
                showIcon
                style={{ marginBottom: 20 }}
            />

            <div className="submission-info">
                <p>提交文件：二叉树遍历代码.zip</p>
                <p>提交说明：已实现递归与非递归解法</p>
                <p>测试通过率：90%</p>
            </div>

            <div className="grading-area">
                <Rate
                    count={10}
                    value={score/10}
                    onChange={v => setScore(v*10)}
                    style={{ fontSize: 24, margin: '20px 0' }}
                />
                <Input.TextArea
                    value={feedback}
                    onChange={e => setFeedback(e.target.value)}
                    placeholder="填写评语（如：非递归解法效率可优化）"
                    rows={4}
                />
            </div>

            <Button
                type="primary"
                icon={<SmileOutlined />}
                style={{ marginTop: 20 }}
                onClick={() => Alert.success('批改结果已保存！')}
            >
                提交评分
            </Button>
        </Card>
    );
};

export default GradingDetail;