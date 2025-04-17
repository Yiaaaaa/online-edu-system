module.exports = {
    content: [
        "./index.html",
        "./src/**/*.{js,jsx,ts,tsx}",
    ],
    theme: {
        extend: {} // 清空自定义渐变配置
    },
    plugins: [require("@tailwindcss/forms")],
    darkMode: "class"
};