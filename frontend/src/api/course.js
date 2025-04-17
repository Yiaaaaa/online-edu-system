import apiClient from './client';  // 假设已配置axios实例

// 获取课程列表
export const getCourses = async (category) => {
    try {
        const response = await apiClient.get('/courses', {
            params: { category }
        });
        return response.data;
    } catch (error) {
        throw error.response.data;
    }
};

// 学生选课
export const enrollCourse = async (courseId) => {
    try {
        const response = await apiClient.post(`/courses/${courseId}/enroll`);
        return response.data;
    } catch (error) {
        throw error.response.data;
    }
};