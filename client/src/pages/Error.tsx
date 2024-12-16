import { useLocation } from "react-router-dom";
import { Container, Row, Col, Button } from "react-bootstrap";

function ErrorPage() {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);

    const status = queryParams.get("HttpStatus");
    const message = decodeURIComponent(queryParams.get("Message") || "An error occurred.");

    return (
        <Container fluid className="d-flex flex-column vh-100 justify-content-center align-items-center bg-danger text-white">
            <Row className="text-center">
                <Col>
                    <h1 className="display-4">Error {status}</h1>
                    <p className="lead">{message}</p>
                    <Button variant="light" href="/" className="mt-3">
                        Back to Home
                    </Button>
                </Col>
            </Row>
        </Container>
    );
}

export default ErrorPage;
