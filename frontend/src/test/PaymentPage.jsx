import React, { useState } from 'react';
import { Button, Card, Steps, QRCode, message } from 'antd';
import { SmileOutlined } from '@ant-design/icons';
import { useNavigate, useParams } from 'react-router-dom';

const PaymentPage = () => {
    const { courseId } = useParams();
    const [currentStep, setCurrentStep] = useState(0);
    const navigate = useNavigate();

    const steps = [
        { title: '生成订单' },
        { title: '扫码支付' },
        { title: '支付成功' }
    ];

    const handlePaymentSuccess = () => {
        setCurrentStep(2);
        setTimeout(() => {
            message.success('支付成功！');
            navigate('/my-courses');
        }, 2000);
    };

    return (
        <div className="payment-page">
            <Steps current={currentStep} items={steps} />
            <Card style={{ marginTop: 20 }}>
                {currentStep === 0 && (
                    <Button type="primary" onClick={() => setCurrentStep(1)}>
                        确认订单并支付
                    </Button>
                )}
                {currentStep === 1 && (
                    <div className="wechat-pay">
                        <QRCode value="weixin://wxpay/bizpayurl?pr=XXXXXX" />
                        <p style={{ marginTop: 20 }}>请使用微信扫码支付</p>
                        <Button type="link" onClick={handlePaymentSuccess}>
                            模拟支付成功
                        </Button>
                    </div>
                )}
                {currentStep === 2 && (
                    <div className="success-page">
                        <SmileOutlined style={{ fontSize: 64, color: '#52c41a' }} />
                        <h2>支付成功！</h2>
                    </div>
                )}
            </Card>
        </div>
    );
};

export default PaymentPage;