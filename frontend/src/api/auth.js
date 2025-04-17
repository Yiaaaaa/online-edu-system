import apiClient from './client';  // 假设已配置axios实例

export const login = async (username, password) => {
    try {
        const response = await apiClient.post('/auth/login', {
            username,
            password
        });
        return response.data;
    } catch (error) {
        throw error.response.data;
    }
};