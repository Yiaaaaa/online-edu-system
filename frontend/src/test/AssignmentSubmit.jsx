import React, { useState } from 'react';
import { Card, Upload, Button, Input, Alert } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

const AssignmentSubmit = () => {
    const [fileList, setFileList] = useState([]);
    const [comment, setComment] = useState('');
    const [isLate, setIsLate] = useState(false);

    const beforeUpload = (file) => {
        const isValidType = ['application/zip', 'application/pdf'].includes(file.type);
        const isSizeValid = file.size <= 50 * 1024 * 1024; // 50MB

        if (!isValidType) {
            Alert.error('仅支持ZIP/PDF格式！');
            return false;
        }
        if (!isSizeValid) {
            Alert.error('文件大小超过限制！');
            return false;
        }
        return true;
    };

    return (
        <Card title="《数据结构》作业提交" style={{ maxWidth: 800, margin: '20px auto' }}>
            {isLate && <Alert message="本次提交已超时，标记为迟交" type="warning" showIcon />}

            <Upload
                beforeUpload={beforeUpload}
                fileList={fileList}
                onChange={({ fileList }) => setFileList(fileList)}
                maxCount={1}
            >
                <Button icon={<UploadOutlined />}>选择作业文件</Button>
            </Upload>

            <Input.TextArea
                value={comment}
                onChange={(e) => setComment(e.target.value)}
                placeholder="填写作业说明（如：已实现递归与非递归解法）"
                style={{ margin: '20px 0' }}
                rows={4}
            />

            <Button
                type="primary"
                disabled={fileList.length === 0}
                onClick={() => Alert.success('提交成功！状态：待批改')}
            >
                确认提交
            </Button>
        </Card>
    );
};

export default AssignmentSubmit;