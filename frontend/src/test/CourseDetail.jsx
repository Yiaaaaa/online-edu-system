import React from 'react';
import { Button, Card, Row, Col, Divider } from 'antd';
import { PlayCircleOutlined, ShoppingCartOutlined } from '@ant-design/icons';
import { useNavigate } from 'react-router-dom';

const CourseDetail = () => {
    const navigate = useNavigate();
    const course = {
        id: 'python',
        title: 'Python编程',
        price: 299,
        teacher: '张老师',
        description: '从零基础到精通Python编程',
        previewVideo: 'https://example.com/preview.mp4'
    };

    return (
        <div className="course-detail">
            <Card title={course.title}>
                <Row gutter={16}>
                    <Col span={12}>
                        <div className="video-container">
                            <video controls src={course.previewVideo} />
                        </div>
                    </Col>
                    <Col span={12}>
                        <h3>课程信息</h3>
                        <p>讲师：{course.teacher}</p>
                        <p>价格：¥{course.price}</p>
                        <p>{course.description}</p>
                        <Divider />
                        <Button
                            type="primary"
                            icon={<ShoppingCartOutlined />}
                            onClick={() => navigate(`/payment/${course.id}`)}
                        >
                            立即购买
                        </Button>
                    </Col>
                </Row>
            </Card>
        </div>
    );
};

export default CourseDetail;