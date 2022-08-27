import { Button, Table } from "antd";
import { ColumnsType } from "antd/lib/table";
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useActions } from "../../hooks/useActions";
import { useTypedSelector } from "../../hooks/useTypedSelector";
import { errorMessage, successMessage } from "../common/SweetAlert2/alerts";

export interface User {
  id: string;
  email: string;
  image: string;
  password: string;
  phoneNumber: string;
  age: number;
}

const columns: ColumnsType<User> = [
  {
    title: "Id",
    dataIndex: "id",
  },
  {
    title: "Email",
    dataIndex: "email",
  },
  {
    title: "Image",
    dataIndex: "image",
  },
  {
    title: "Password",
    dataIndex: "password",
  },
  {
    title: "Phone Number",
    dataIndex: "phoneNumber",
  },
  {
    title: "Age",
    dataIndex: "age",
  },
];

const MainUser = () => {
  const { GetData, DeleteUser } = useActions();
  const { dataTable } = useTypedSelector((store) => store.user);

  const [selectedRowKeys, setSelectedRowKeys] = useState<React.Key[]>([]);

  const navigate = useNavigate();

  // Table rows selection

  const onSelectChange = (newSelectedRowKeys: React.Key[]) => {
    setSelectedRowKeys(newSelectedRowKeys);
  };

  const rowSelection = {
    selectedRowKeys,
    onChange: onSelectChange,
  };
  const hasSelected = selectedRowKeys.length > 0;

  // Delete

  const deleteUsers = async () => {
    if (selectedRowKeys.length === 0) {
      errorMessage("Error!", "No selected values to delete.");
      return;
    }

    dataTable.forEach((item) => {
      selectedRowKeys.forEach(async (id) => {
        if (item.id === id) {
          await DeleteUser(id);
        }
      });
    });

    await GetData();

    successMessage();
  };

  // Edit

  const editUser = async () => {
    if (selectedRowKeys.length !== 1) {
      errorMessage("Error!", "No selected value or selected more then one.");
      return;
    }

    selectedRowKeys.forEach(async (id) => {
      navigate("/edit-user/" + id);
      return;
    });
  };

  // Get

  useEffect(() => {
    GetData();
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <Button style={{ marginBottom: "10px" }} onClick={deleteUsers}>
        Delete
      </Button>
      <Button
        style={{ marginBottom: "10px", marginLeft: "10px" }}
        onClick={editUser}
      >
        Edit User
      </Button>
      <Table
        columns={columns}
        rowSelection={rowSelection}
        dataSource={[...dataTable]}
        rowKey={"id"}
      />
    </div>
  );
};

export default MainUser;
