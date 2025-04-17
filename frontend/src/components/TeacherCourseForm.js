import React, { useState } from 'react';
import { Button, Upload, message } from 'antd';
import { createDraftCourse, uploadMaterials } from '../api/course';

const TeacherCourseForm = () => {
    const [courseData, setCourseData] = useState({
        title: '',
        category: '编程',
        price: 0,
        startTime: ''
    });

    const handleSubmit = async () => {
        const draft = await createDraftCourse(courseData);
        message.success('草稿保存成功');
    };

    const handleFileUpload = async (file, type) => {
        const formData = new FormData();
        formData.append('file', file);
        formData.append('type', type);
        const res = await uploadMaterials(draft.id, formData);
        message.success(`${type === 'video' ? '视频' : '课件'}上传成功`);
    };

    return (
        <div>
            <input placeholder="课程标题" onChange={(e) => setCourseData({...courseData, title: e.target.value})} />
            <select onChange={(e) => setCourseData({...courseData, category: e.target.value})}>
                <option value="编程">编程</option>
                <option value="设计">设计</option>
            </select>
            <Button onClick={handleSubmit}>保存草稿</Button>
            <Upload
                beforeUpload={(file) => {
                    if (!['video/mp4', 'video/webm'].includes(file.type)) {
                        message.error('仅支持MP4/WebM格式');
                        return false;
                    }
                    handleFileUpload(file, 'video');
                    return false;
                }}
            >
                <Button>上传视频</Button>
            </Upload>
        </div>
    );
};