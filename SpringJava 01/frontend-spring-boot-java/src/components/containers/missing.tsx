import { Button } from "antd";
import { useNavigate } from "react-router-dom";

const MissingPage = () => {
  const navigate = useNavigate();

  return (
    <section style={{ padding: "20px" }}>
      <h2>Oops!</h2>
      <br />
      <strong>404 Not Found</strong>
      <div>
        <br />
        <Button onClick={() => navigate("/")}>Home page</Button>
      </div>
    </section>
  );
};

export default MissingPage;
