import { Button, Col, Form, Input, InputNumber, Row } from "antd";
import {
  EyeInvisibleOutlined,
  EyeTwoTone,
  LockOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { useEffect, useRef, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { User } from "../user";
import { errorMessage, successMessage } from "../../common/SweetAlert2/alerts";
import { useActions } from "../../../hooks/useActions";

const MainEditUser = () => {
  const { id } = useParams();
  const [isLoad, setIsLoad] = useState<boolean>(false);
  const navigate = useNavigate();
  const { EditUser } = useActions();

  const idRef = useRef<any>();
  const emailRef = useRef<any>();
  const imageRef = useRef<any>();
  const passwordRef = useRef<any>();
  const phoneNumberRef = useRef<any>();
  const ageRef = useRef<any>();

  useEffect(() => {

    const fetch = async () => {
      const response = await axios.get("http://localhost:8080/user/" + id);

      idRef.current.value = response.data.id;
      emailRef.current.input.value = response.data.email;
      imageRef.current.input.value = response.data.image;
      passwordRef.current.input.value = response.data.password;
      phoneNumberRef.current.input.value = response.data.phoneNumber;
      ageRef.current.value = response.data.age;
    };

    fetch();

    setIsLoad(true);
  }, []);

  const onFinish = async (values: User) => {
    try {
      await EditUser(values);
      successMessage().then(() => { navigate("/user") });
    } catch (error) {
      errorMessage();
    }
  };

  const onFinishFailed = () => {};

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
        {isLoad === true && (
          <Row>
            <Col span={12} offset={6}>
              <h2 style={{ textAlign: "center" }}>Edit User</h2>
              <Form.Item
                name="id"
                label="Id"
                rules={[{ required: true, message: "Please input your Id!" }]}
                hasFeedback
              >
                <InputNumber
                  ref={idRef as any}
                  prefix={<UserOutlined className="site-form-item-icon" />}
                  style={{ width: "100%" }}
                  placeholder="Enter your id"
                />
              </Form.Item>
              <Form.Item
                name="email"
                label="Email"
                rules={[
                  { required: true, message: "Please input your Email!" },
                ]}
                hasFeedback
              >
                <Input
                  ref={emailRef as any}
                  //   value={emailRef.current.input.value as any}
                  prefix={<UserOutlined className="site-form-item-icon" />}
                  placeholder="Email"
                />
              </Form.Item>
              <Form.Item
                name="image"
                label="Image"
                rules={[
                  { required: true, message: "Please input your Image!" },
                ]}
                hasFeedback
              >
                <Input
                  //   value={imageRef.current.input.value as any}
                  ref={imageRef as any}
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
                  //   value={passwordRef.current.input.value as any}
                  ref={passwordRef as any}
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
                  {
                    required: true,
                    message: "Please input your Phone Number!",
                  },
                ]}
                hasFeedback
              >
                <Input
                  //   value={phoneNumberRef.current.input.value as any}
                  ref={phoneNumberRef as any}
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
                  ref={ageRef as any}
                  //   value={ageRef.current.value as any}
                  prefix={<LockOutlined className="site-form-item-icon" />}
                  style={{ width: "100%" }}
                  placeholder="Enter your Age"
                />
              </Form.Item>
              <Form.Item {...tailFormItemLayout}>
                <Button
                  type="primary"
                  htmlType="submit"
                  className="login-form-button"
                >
                  Edit
                </Button>
              </Form.Item>
            </Col>
          </Row>
        )}
      </Form>
    </section>
  );
};

export default MainEditUser;
