import React, { useState } from 'react';
import { Upload, Button, message } from 'antd';
import { submitHomework } from '../api/course';

const HomeworkForm = ({ courseId }) => {
    const [fileList, setFileList] = useState([]);

    const beforeUpload = (file) => {
        const isAllowedFormat = ['application/pdf', 'application/zip'].includes(file.type);
        if (!isAllowedFormat) {
            message.error('仅支持PDF或ZIP文件');
            return false;
        }
        const isLt50M = file.size / 1024 / 1024 < 50;
        if (!isLt50M) {
            message.error('文件大小不能超过50MB');
            return false;
        }
        return true;
    };

    const handleSubmit = () => {
        const formData = new FormData();
        formData.append('file', fileList[0]);
        submitHomework(courseId, formData)
            .then(() => message.success('提交成功'))
            .catch(err => message.error(err.response.data.error));
    };

    return (
        <div>
            <Upload
                beforeUpload={beforeUpload}
                onRemove={() => setFileList([])}
                onChange={({ file }) => setFileList([file])}
                fileList={fileList}
            >
                <Button>选择文件</Button>
            </Upload>
            <Button onClick={handleSubmit} disabled={fileList.length === 0}>
                提交作业
            </Button>
        </div>
    );
};