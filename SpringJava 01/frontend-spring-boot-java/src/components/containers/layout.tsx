import { Link, Outlet } from "react-router-dom";
import { Button, Layout } from "antd";
import { Content, Footer, Header } from "antd/lib/layout/layout";

const MainLayout = () => {
  return (
      <Layout style={{ height: "100vh" }}>
        <Header>
          <Button ghost><Link to="/">Home</Link></Button>
          <Button style={{ marginLeft: "10px" }} ghost><Link to="/user">User List</Link></Button>
          <Button style={{ marginLeft: "10px" }} ghost><Link to="/add-user">Add User</Link></Button>
        </Header>
        <Content>
          <Outlet />
        </Content>
        <Footer>

        </Footer>
      </Layout>
  );
};

export default MainLayout;
