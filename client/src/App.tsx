import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Error from "./pages/Error";

function App() {
  return (
    <Router>
      <Routes>
        {/* Route for the Home Page */}
        <Route path="/" element={<Home />} />
        {/* Route for the Error Page */}
        <Route path="/handle-error" element={<Error />} />
        {/* Redirect unknown paths to Home */}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </Router>
  );
}

export default App;
