import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { message } from 'antd';
import { getVideoUrl } from '../api/course';

const CourseVideo = () => {
    const { courseId } = useParams();
    const [videoUrl, setVideoUrl] = useState('');

    useEffect(() => {
        getVideoUrl(courseId)
            .then(res => setVideoUrl(res.data))
            .catch(err => message.error('无法观看视频：' + err.message));
    }, [courseId]);

    return (
        <div className="video-container">
            <video controls src={videoUrl} style={{ width: '100%' }} />
        </div>
    );
};