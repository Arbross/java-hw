import { Button, Col, Form, Input, InputNumber, Row } from "antd";
import {
  EyeInvisibleOutlined,
  EyeTwoTone,
  LockOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { User } from "../user";
import axios from "axios";
import { errorMessage, successMessage } from "../../common/SweetAlert2/alerts";
import { useNavigate } from "react-router-dom";
import { useActions } from "../../../hooks/useActions";

const MainAddUser = () => {
  const navigate = useNavigate();
  const { AddUser } = useActions();

  const onFinish = async (values: User) => {
    try {
      await AddUser(values);
      successMessage("User successfully created!").then(() => { navigate("/user") });
    } catch (error) {
      errorMessage(error as string);
    }
  };

  const tailFormItemLayout = {
    wrapperCol: {
      xs: {
        span: 24,
        offset: 0,
      },
      sm: {
        span: 16,
        offset: 6,
      },
    },
  };

  const onFinishFailed = () => {};

  return (
    <section style={{ padding: "20px" }}>
      <Form
        labelCol={{ span: 6 }}
        wrapperCol={{ span: 16 }}
        initialValues={{ remember: true }}
        autoComplete="on"
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        scrollToFirstError
      >
        <Row>
          <Col span={12} offset={6}>
            <h2 style={{ textAlign: "center" }}>Add User</h2>
            <Form.Item
              name="id"
              label="Id"
              rules={[{ required: true, message: "Please input your Id!" }]}
              hasFeedback
            >
              <InputNumber
                prefix={<UserOutlined className="site-form-item-icon" />}
                style={{ width: "100%" }}
                placeholder="Enter your id"
              />
            </Form.Item>
            <Form.Item
              name="email"
              label="Email"
              rules={[{ required: true, message: "Please input your Email!" }]}
              hasFeedback
            >
              <Input
                prefix={<UserOutlined className="site-form-item-icon" />}
                placeholder="Email"
              />
            </Form.Item>
            <Form.Item
              name="image"
              label="Image"
              rules={[{ required: true, message: "Please input your Image!" }]}
              hasFeedback
            >
              <Input
                prefix={<UserOutlined className="site-form-item-icon" />}
                placeholder="Enter your Image"
              />
            </Form.Item>
            <Form.Item
              name="password"
              label="Password"
              rules={[
                { required: true, message: "Please input your Password!" },
              ]}
              hasFeedback
            >
              <Input.Password
                prefix={<LockOutlined className="site-form-item-icon" />}
                type="password"
                placeholder="Password"
                iconRender={(visible) =>
                  visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                }
              />
            </Form.Item>
            <Form.Item
              name="phoneNumber"
              label="PhoneNumber"
              rules={[
                { required: true, message: "Please input your Phone Number!" },
              ]}
              hasFeedback
            >
              <Input
                prefix={<LockOutlined className="site-form-item-icon" />}
                type="phone"
                placeholder="Enter your Phone Number"
              />
            </Form.Item>
            <Form.Item
              name="age"
              label="Age"
              rules={[{ required: true, message: "Please input your Age!" }]}
              hasFeedback
            >
              <InputNumber
                prefix={<LockOutlined className="site-form-item-icon" />}
                style={{ width: "100%" }}
                type="phone"
                placeholder="Enter your Age"
              />
            </Form.Item>
            <Form.Item {...tailFormItemLayout}>
              <Button
                type="primary"
                htmlType="submit"
                className="login-form-button"
              >
                Create
              </Button>
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </section>
  );
};

export default MainAddUser;
