import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { PageNotFound } from "./components/PageNotFound";
import ServicePage from "./components/ServicePage";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route element={<ServicePage />}>
            <Route path="*" element={<PageNotFound />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
