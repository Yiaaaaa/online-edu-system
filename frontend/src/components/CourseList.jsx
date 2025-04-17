import React, { useEffect, useState } from 'react';
import { Card, Button, Select, message } from 'antd';
import { getCourses, enrollCourse } from '../api/course';

const { Option } = Select;

const CourseList = () => {
    const [courses, setCourses] = useState([]);
    const [category, setCategory] = useState('all');

    useEffect(() => {
        getCourses(category).then(res => setCourses(res.data));
    }, [category]);

    const handleEnroll = (courseId, isPaid) => {
        if (isPaid) {
            // 跳转支付页面
            window.location.href = `/payment/${courseId}`;
        } else {
            enrollCourse(courseId)
                .then(() => message.success('选课成功'))
                .catch(err => message.error(err.response.data.error));
        }
    };

    return (
        <div>
            <Select defaultValue="all" onChange={setCategory}>
                <Option value="all">全部</Option>
                <Option value="编程">编程</Option>
                <Option value="设计">设计</Option>
            </Select>
            {courses.map(course => (
                <Card key={course.id} title={course.title} style={{ margin: '10px' }}>
                    <p>教师：{course.teacher}</p>
                    <p>简介：{course.description}</p>
                    <Button onClick={() => handleEnroll(course.id, course.isPaid)}>
                        {course.isPaid ? `购买（￥${course.price}）` : '免费加入'}
                    </Button>
                </Card>
            ))}
        </div>
    );
};

export default CourseList