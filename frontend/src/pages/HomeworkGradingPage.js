import React, { useState } from 'react';
import { Table, Button, Input } from 'antd';
import { downloadHomeworkZip, gradeHomework } from '../api/homework';

const HomeworkGradingPage = ({ courseId }) => {
    const [gradingTemplate, setGradingTemplate] = useState({
        代码规范性: 30,
        功能完成度: 70
    });

    const handleDownload = () => {
        downloadHomeworkZip(courseId)
            .then(() => message.success('下载成功'));
    };

    const handleGradeSubmit = (homeworkId, score, feedback) => {
        gradeHomework({ homeworkId, score, feedback, template: JSON.stringify(gradingTemplate) })
            .then(() => message.success('评分已提交'));
    };

    return (
        <div>
            <Button onClick={handleDownload}>批量下载作业</Button>
            <Table
                columns={[
                    { title: '学生', dataIndex: 'studentName' },
                    {
                        title: '评分',
                        render: (_, record) => (
                            <Input
                                type="number"
                                onChange={(e) => handleGradeSubmit(record.id, e.target.value)}
                            />
                        )
                    }
                ]}
            />
        </div>
    );
};