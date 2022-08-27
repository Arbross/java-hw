import { Route, Routes } from "react-router-dom";

import MainLayout from "./components/containers/layout";
import MissingPage from "./components/containers/missing";
import MainHome from "./components/home/home";
import MainAddUser from "./components/user/add/addUser";
import MainEditUser from "./components/user/edit/editUser";
import MainUser from "./components/user/user";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route index element={<MainHome />} />

        <Route path="/user" element={<MainUser />} />
        <Route path="/add-user" element={<MainAddUser />} />
        <Route path="/edit-user/:id" element={<MainEditUser />} />

        <Route path="*" element={<MissingPage />} />
      </Route>
    </Routes>
  );
}

export default App;
