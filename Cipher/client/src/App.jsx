import { Route, Routes, BrowserRouter } from "react-router-dom";
import Cifrado from "./pages/Cifrado";

function App() {
  return (

      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Cifrado />} />
        </Routes>
      </BrowserRouter>
  );
}
export default App;
