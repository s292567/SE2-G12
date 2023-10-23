import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { DefaultLayout, MainLayout, CountersOverview } from "./components/Layouts";
import { PageNotFound } from "./components/PageNotFound";
import { ServiceTypeForm } from "./components/ServiceTypeForm";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route element={<DefaultLayout />}>
            <Route index element={<MainLayout />} />
            <Route path="/counters" element={<CountersOverview />} />
            <Route path="/services/:serviceId/edit" element={<ServiceTypeForm />} />
            <Route path="/services/newService" element={<ServiceTypeForm />} />
            <Route path="*" element={<PageNotFound />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
